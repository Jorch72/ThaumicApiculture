package me.apemanzilla.thaumicapiculture.recipes

import forestry.core.ModuleCore
import me.apemanzilla.thaumicapiculture.ThaumicApiculture.MODID
import me.apemanzilla.thaumicapiculture.items.ModItems
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import thaumcraft.api.ThaumcraftApi.*
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.ThaumcraftApiHelper.makeCrystal
import thaumcraft.api.aspects.Aspect.*
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.blocks.BlocksTC
import thaumcraft.api.crafting.CrucibleRecipe
import thaumcraft.api.crafting.InfusionRecipe
import thaumcraft.api.crafting.ShapedArcaneRecipe
import thaumcraft.api.crafting.ShapelessArcaneRecipe
import thaumcraft.api.items.ItemsTC

object Recipes {
	fun registerAll() {
		registerArcaneRecipes()
		registerCrucibleRecipes()
		registerInfusionRecipes()
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
	}

	fun registerCrucibleRecipes() {
		val apatite = ModuleCore.items!!.apatite
		val fertilizer = ModuleCore.items!!.fertilizerCompound

		addCrucibleRecipe(ResourceLocation(MODID, "efficient_fertilizer"), CrucibleRecipe(
				"APATITEENRICHMENT", ItemStack(fertilizer, 20), ItemStack(apatite, 1),
				AspectList().add(PLANT, 10).add(LIFE, 10).add(ORDER, 5)
		))
	}

	fun registerInfusionRecipes() {
		addInfusionCraftingRecipe(ModItems.channeling_frame.registryName, InfusionRecipe(
				"CHANNELINGFRAME", ItemStack(ModItems.channeling_frame), 1,
				AspectList().add(ORDER, 10).add(AURA, 10).add(ENERGY, 20), ItemStack(ModItems.silverwood_frame),
				makeCrystal(BEAST), ItemsTC.visResonator
		))
	}
}
