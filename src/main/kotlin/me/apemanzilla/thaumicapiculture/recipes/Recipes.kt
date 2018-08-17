package me.apemanzilla.thaumicapiculture.recipes

import forestry.core.ModuleCore
import me.apemanzilla.thaumicapiculture.ThaumicApiculture.MODID
import me.apemanzilla.thaumicapiculture.items.ModItems
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import thaumcraft.api.ThaumcraftApi.addArcaneCraftingRecipe
import thaumcraft.api.ThaumcraftApi.addCrucibleRecipe
import thaumcraft.api.aspects.Aspect.*
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.blocks.BlocksTC
import thaumcraft.api.crafting.CrucibleRecipe
import thaumcraft.api.crafting.ShapedArcaneRecipe
import thaumcraft.api.crafting.ShapelessArcaneRecipe
import thaumcraft.api.items.ItemsTC

object Recipes {
	fun registerAll() {
		registerArcaneRecipes()
		registerCrucibleRecipes()
	}

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
				null, "VOIDSCOOP", 50, AspectList().add(ORDER, 1), ModItems.void_scoop,
				"NFN",
				"NIN",
				" N ",
				'N', "nuggetVoid", 'I', "ingotVoid", 'F', ItemStack(ItemsTC.fabric)
		))

		addArcaneCraftingRecipe(ModItems.void_grafter.registryName, ShapedArcaneRecipe(
				null, "VOIDSCOOP", 50, AspectList().add(ORDER, 1), ModItems.void_grafter,
				"  I",
				" S ",
				"S  ",
				'I', "ingotVoid", 'S', "stickWood"
		))

		addArcaneCraftingRecipe(ModItems.silverwood_frame.registryName, ShapedArcaneRecipe(
				null, "SILVERWOODFRAME", 25, AspectList().add(AIR, 1).add(WATER, 1), ModItems.silverwood_frame,
				"SSS",
				"SFS",
				"SSS",
				'S', ItemStack(BlocksTC.plankSilverwood), 'F', ItemStack(ItemsTC.fabric)
		))

		addArcaneCraftingRecipe(ModItems.channeling_frame.registryName, ShapelessArcaneRecipe(
				null, "CHANNELINGFRAME", 25, AspectList().add(ORDER, 1), ModItems.channeling_frame,
				ItemStack(ItemsTC.visResonator), ItemStack(ModItems.silverwood_frame)
		))
	}

	fun registerCrucibleRecipes() {
		val apatite = ModuleCore.items!!.apatite
		val fertilizer = ModuleCore.items!!.fertilizerCompound

		addCrucibleRecipe(ResourceLocation(MODID, "efficient_fertilizer"), CrucibleRecipe(
				"APATITEENRICHMENT", ItemStack(fertilizer, 20), ItemStack(apatite, 1),
				AspectList().add(PLANT, 10).add(LIFE, 10).add(ORDER, 5)
		))
	}
}
