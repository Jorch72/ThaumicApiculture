package me.apemanzilla.thaumicapiculture.recipes

import me.apemanzilla.thaumicapiculture.items.ModItems
import net.minecraft.item.ItemStack
import thaumcraft.api.ThaumcraftApi.addArcaneCraftingRecipe
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.crafting.ShapedArcaneRecipe
import thaumcraft.api.items.ItemsTC

object Recipes {
	fun registerArcaneRecipes() {
		addArcaneCraftingRecipe(ModItems.thaumium_scoop.registryName, ShapedArcaneRecipe(
				null, "THAUMIUMSCOOP", 20, AspectList(), ModItems.thaumium_scoop,
				"NFN",
				"NIN",
				" N ",
				'N', "nuggetThaumium", 'I', "ingotThaumium", 'F', ItemStack(ItemsTC.fabric)
		))

		addArcaneCraftingRecipe(ModItems.thaumium_grafter.registryName, ShapedArcaneRecipe(
				null, "THAUMIUMSCOOP", 20, AspectList(), ModItems.thaumium_grafter,
				"  I",
				" S ",
				"S  ",
				'I', "ingotThaumium", 'S', "stickWood"
		))

		addArcaneCraftingRecipe(ModItems.void_scoop.registryName, ShapedArcaneRecipe(
				null, "VOIDSCOOP", 50, AspectList().add(Aspect.ORDER, 1), ModItems.void_scoop,
				"NFN",
				"NIN",
				" N ",
				'N', "nuggetVoid", 'I', "ingotVoid", 'F', ItemStack(ItemsTC.fabric)
		))

		addArcaneCraftingRecipe(ModItems.void_grafter.registryName, ShapedArcaneRecipe(
				null, "VOIDSCOOP", 50, AspectList().add(Aspect.ORDER, 1), ModItems.void_grafter,
				"  I",
				" S ",
				"S  ",
				'I', "ingotVoid", 'S', "stickWood"
		))
	}
}
