package me.apemanzilla.thaumicapiculture.lib

import forestry.api.apiculture.IBeeGenome
import me.apemanzilla.thaumicapiculture.ThaumicApiculture
import net.minecraft.client.resources.I18n.format
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * A bee modifier with fixed values
 */
data class StaticBeeModifier(
		val territory: Float = 1f,
		val mutation: Float = 1f,
		val lifespan: Float = 1f,
		val production: Float = 1f,
		val flowering: Float = 1f,
		val geneticDecay: Float = 1f,
		val sealed: Boolean = false,
		val lit: Boolean = false,
		val sunlit: Boolean = false,
		val hellish: Boolean = false
) : DetailedBeeModifier {
	override fun getTerritoryModifier(genome: IBeeGenome?, currentModifier: Float) = territory
	override fun getMutationModifier(genome: IBeeGenome?, mate: IBeeGenome?, currentModifier: Float) = mutation
	override fun getLifespanModifier(genome: IBeeGenome?, mate: IBeeGenome?, currentModifier: Float) = lifespan
	override fun getProductionModifier(genome: IBeeGenome?, currentModifier: Float) = production
	override fun getFloweringModifier(genome: IBeeGenome?, currentModifier: Float) = flowering
	override fun getGeneticDecay(genome: IBeeGenome?, currentModifier: Float) = geneticDecay
	override fun isSealed() = sealed
	override fun isSelfLighted() = lit
	override fun isSunlightSimulated() = sunlit
	override fun isHellish() = hellish

	val description by lazy {
		mutableListOf<String>().also {
			if (production != 1f) it += format("thaumicapiculture.modifier.production", production)
			if (mutation != 1f) it += format("thaumicapiculture.modifier.mutation", mutation)
			if (territory != 1f) it += format("thaumicapiculture.modifier.territory", territory)
			if (lifespan != 1f) it += format("thaumicapiculture.modifier.lifespan", lifespan)
			if (flowering != 1f) it += format("thaumicapiculture.modifier.flowering", flowering)
			if (geneticDecay != 1f) it += format("thaumicapiculture.modifier.genetic_decay", geneticDecay)
		}
	}

	override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: ITooltipFlag) {
		tooltip += description
	}
}
