package me.apemanzilla.thaumicapiculture.recipes

import forestry.api.apiculture.IArmorApiarist
import forestry.core.ModuleCore
import me.apemanzilla.thaumicapiculture.recipes.InfusionApiaristRecipe.nbtKey
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.nbt.NBTTagByte
import net.minecraft.world.World
import net.minecraftforge.common.util.RecipeMatcher
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.capabilities.ThaumcraftCapabilities
import thaumcraft.api.crafting.InfusionRecipe

/** Check whether an [ItemStack] has the apiarist infusion already */
fun ItemStack.hasApiaristEnchant() = tagCompound?.getBoolean(nbtKey) == true || item is IArmorApiarist

object InfusionApiaristRecipe : InfusionRecipe(
		"APIARISTENCHANT", null, 2, AspectList().add(Aspect.PROTECT, 50).add(Aspect.AIR, 25), Ingredient.EMPTY,
		*Array(6) { ItemStack(ModuleCore.getItems().craftingMaterial, 1, 3) }
) {
	val nbtKey = "apiaristinfusion"

	override fun matches(input: List<ItemStack>, central: ItemStack?, world: World, player: EntityPlayer): Boolean {
		// check that we have a central item
		if (central?.isEmpty != false) return false

		// check that the central item is a piece of armor
		if (central.item !is ItemArmor) return false

		// check that the central item doesn't already have the apiarist infusion
		if (central.hasApiaristEnchant()) return false

		// check that the player has the research
		if (!ThaumcraftCapabilities.knowsResearch(player, research)) return false

		// check that the outer items are valid
		if (RecipeMatcher.findMatches(input, components) == null) return false

		return true
	}

	override fun getRecipeOutput(player: EntityPlayer, input: ItemStack, comps: List<ItemStack>) = input.copy().apply {
		setTagInfo(nbtKey, NBTTagByte(1))
	}

	/** A fake sample used for display, e.g. in thaumonomicon/JEI */
	val fakeRecipe = InfusionRecipe(
			research,
			ItemStack(Items.DIAMOND_CHESTPLATE).apply { setTagInfo(nbtKey, NBTTagByte(1)) },
			instability,
			aspects,
			ItemStack(Items.DIAMOND_CHESTPLATE),
			*components.toArray()
	)
}
