package me.apemanzilla.thaumicapiculture.recipes

import forestry.api.apiculture.*
import forestry.api.genetics.*
import forestry.apiculture.genetics.BeeDefinition
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.world.World
import net.minecraftforge.common.util.RecipeMatcher
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.capabilities.ThaumcraftCapabilities
import thaumcraft.api.crafting.InfusionRecipe
import java.lang.IllegalArgumentException

class IncrementalBeeInfusion(
		val name: String,
		research: String,
		instability: Int,
		aspects: AspectList,
		val chromosome: EnumBeeChromosome,
		vararg recipe: Any
) : InfusionRecipe(research, null, instability, aspects, Ingredient.EMPTY, *recipe) {
	private companion object {
		val beeRoot by lazy { BeeManager.beeRoot!! }

		fun IGenome.primaryAlleles() = chromosomes.map { it.primaryAllele }.toTypedArray()
		fun IGenome.secondaryAlleles() = chromosomes.map { it.secondaryAllele }.toTypedArray()
	}

	private val alleleSorter: Comparator<IAllele> = when (chromosome.alleleClass) {
		IAlleleFloat::class.java -> Comparator { a, b -> (a as IAlleleFloat).value.compareTo((b as IAlleleFloat).value) }
		IAlleleInteger::class.java -> Comparator { a, b -> (a as IAlleleInteger).value.compareTo((b as IAlleleInteger).value) }
		else -> throw IllegalArgumentException("Allele type ${chromosome.alleleClass} cannot be sorted")
	}

	private val alleles by lazy {
		AlleleManager.alleleRegistry
				.getRegisteredAlleles(chromosome)
				.sortedWith(alleleSorter)
				.toTypedArray()
	}

	private fun IBee.increment(): IBee {
		fun Array<IAllele>.increment(): Array<IAllele> {
			val currentIndex = alleles.indexOf(this[chromosome.ordinal])
			if (currentIndex > -1 && currentIndex < alleles.size) this[chromosome.ordinal] = alleles[currentIndex + 1]
			return this
		}

		return beeRoot.templateAsIndividual(genome.primaryAlleles().increment(), genome.secondaryAlleles().increment())
	}

	private fun IBee.canIncrement() = !increment().genome.isGeneticEqual(genome)

	override fun matches(input: List<ItemStack>, central: ItemStack, world: World, player: EntityPlayer): Boolean {
		if (central.isEmpty) return false
		if (!ThaumcraftCapabilities.knowsResearch(player, research)) return false
		if (beeRoot.isMated(central)) return false
		val bee = beeRoot.getMember(central) ?: return false
		if (!bee.canIncrement()) return false
		if (RecipeMatcher.findMatches(input, components) == null) return false
		return true
	}

	private fun getRecipeOutput(input: ItemStack): ItemStack {
		// create modified bee
		val oldBee = beeRoot.getMember(input)!!
		val bee = oldBee.increment()

		// update other traits to match
		bee.health = oldBee.health
		bee.setIsNatural(oldBee.isNatural)
		if (oldBee.isAnalyzed) bee.analyze()

		// create the item
		return beeRoot.getMemberStack(bee, beeRoot.getType(input)!!)
	}

	override fun getRecipeOutput(player: EntityPlayer, input: ItemStack, comps: List<ItemStack>): ItemStack {
		return getRecipeOutput(input)
	}

	fun fakeRecipe(species: BeeDefinition): InfusionRecipe {
		val inBee = beeRoot.templateAsIndividual(species.template).apply { analyze() }
		val inItem = beeRoot.getMemberStack(inBee, EnumBeeType.DRONE)
		val outItem = getRecipeOutput(inItem)

		return InfusionRecipe(research, outItem, instability, aspects, inItem, *components.toArray())
	}
}
