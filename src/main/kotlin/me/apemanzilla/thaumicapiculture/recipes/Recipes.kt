package me.apemanzilla.thaumicapiculture.recipes

import forestry.apiculture.ModuleApiculture
import forestry.core.ModuleCore
import me.apemanzilla.thaumicapiculture.ThaumicApiculture.MODID
import me.apemanzilla.thaumicapiculture.items.ModItems
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import thaumcraft.api.ThaumcraftApi.*
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.ThaumcraftApiHelper.makeCrystal
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.Aspect.*
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.blocks.BlocksTC
import thaumcraft.api.crafting.CrucibleRecipe
import thaumcraft.api.crafting.InfusionRecipe
import thaumcraft.api.crafting.ShapedArcaneRecipe
import thaumcraft.api.crafting.ShapelessArcaneRecipe
import thaumcraft.api.items.ItemsTC
import thaumcraft.common.config.ConfigBlocks
import thaumcraft.common.config.ConfigBlocks.*

object Recipes {
	fun registerAll() {
		registerArcaneRecipes()
		registerCrucibleRecipes()
		registerInfusionRecipes()
	}

	fun registerArcaneRecipes() {
		addArcaneCraftingRecipe(ModItems.thaumium_scoop.registryName, ShapedArcaneRecipe(
				null, "THAUMIUMSCOOP@2", 20, AspectList(), ModItems.thaumium_scoop,
				"NFN",
				"NIN",
				" N ",
				'N', "nuggetThaumium", 'I', "ingotThaumium", 'F', ItemStack(ItemsTC.fabric)
		))

		addArcaneCraftingRecipe(ModItems.thaumium_grafter.registryName, ShapedArcaneRecipe(
				null, "THAUMIUMSCOOP@2", 20, AspectList(), ModItems.thaumium_grafter,
				"  I",
				" S ",
				"S  ",
				'I', "ingotThaumium", 'S', "stickWood"
		))

		addArcaneCraftingRecipe(ModItems.void_scoop.registryName, ShapedArcaneRecipe(
				null, "VOIDSCOOP@2", 50, AspectList().add(ORDER, 1), ModItems.void_scoop,
				"NFN",
				"NIN",
				" N ",
				'N', "nuggetVoid", 'I', "ingotVoid", 'F', ItemStack(ItemsTC.fabric)
		))

		addArcaneCraftingRecipe(ModItems.void_grafter.registryName, ShapedArcaneRecipe(
				null, "VOIDSCOOP@2", 50, AspectList().add(ORDER, 1), ModItems.void_grafter,
				"  I",
				" S ",
				"S  ",
				'I', "ingotVoid", 'S', "stickWood"
		))

		addArcaneCraftingRecipe(ModItems.silverwood_frame.registryName, ShapedArcaneRecipe(
				null, "MAGICFRAMES@2", 25, AspectList().add(AIR, 1).add(ORDER, 1), ModItems.silverwood_frame,
				"SSS",
				"SFS",
				"SSS",
				'S', ItemStack(BlocksTC.plankSilverwood), 'F', ItemStack(ItemsTC.fabric)
		))

		addArcaneCraftingRecipe(ModItems.greatwood_frame.registryName, ShapedArcaneRecipe(
				null, "MAGICFRAMES@2", 25, AspectList().add(AIR, 1).add(WATER, 1), ModItems.greatwood_frame,
				"GGG",
				"GFG",
				"GGG",
				'G', ItemStack(BlocksTC.plankGreatwood), 'F', ItemStack(ItemsTC.fabric)
		))
	}

	fun registerCrucibleRecipes() {
		val apatite = ModuleCore.getItems().apatite
		val fertilizer = ModuleCore.getItems().fertilizerCompound

		addCrucibleRecipe(ResourceLocation(MODID, "efficient_fertilizer"), CrucibleRecipe(
				"APATITEENRICHMENT@2", ItemStack(fertilizer, 20), ItemStack(apatite),
				AspectList().add(PLANT, 10).add(LIFE, 10).add(ORDER, 5)
		))
	}

	fun registerInfusionRecipes() {
		val royalJelly = ModuleApiculture.getItems().royalJelly

		addInfusionCraftingRecipe(ModItems.energizing_frame.registryName, InfusionRecipe(
				"ENERGIZINGFRAME@2", ItemStack(ModItems.energizing_frame), 3,
				AspectList().add(ENERGY, 50).add(AIR, 25).add(MOTION, 25), ItemStack(ModItems.greatwood_frame),
				ItemStack(Items.SUGAR), ItemStack(royalJelly)
		))

		addInfusionCraftingRecipe(ModItems.purifying_frame.registryName, InfusionRecipe(
				"PURIFYINGFRAME@2", ItemStack(ModItems.purifying_frame), 3,
				AspectList().add(AURA, 50).add(AIR, 25).add(LIFE, 25), ItemStack(ModItems.silverwood_frame),
				makeCrystal(LIFE), ItemStack(BlocksTC.shimmerleaf)
		))

		val plateThaumium = ItemStack(ItemsTC.plate, 1, 2)
		val deathBucket = FluidUtil.getFilledBucket(FluidStack(FluidDeath.instance, 1000))

		addInfusionCraftingRecipe(ModItems.death_frame.registryName, InfusionRecipe(
				"DEATHFRAME@4", ItemStack(ModItems.death_frame), 4,
				AspectList().add(DEATH, 25).add(AIR, 25).add(FLUX, 25), ItemStack(ModItems.greatwood_frame),
				plateThaumium, deathBucket, plateThaumium, Items.WATER_BUCKET
		))
	}
}
