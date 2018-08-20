package me.apemanzilla.thaumicapiculture.recipes

import forestry.apiculture.ModuleApiculture
import forestry.core.ModuleCore
import me.apemanzilla.thaumicapiculture.ThaumicApiculture.MODID
import me.apemanzilla.thaumicapiculture.items.ModItems
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import thaumcraft.api.ThaumcraftApi.*
import thaumcraft.api.ThaumcraftApiHelper.makeCrystal
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.Aspect.*
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.blocks.BlocksTC
import thaumcraft.api.crafting.CrucibleRecipe
import thaumcraft.api.crafting.InfusionRecipe
import thaumcraft.api.crafting.ShapedArcaneRecipe
import thaumcraft.api.items.ItemsTC
import thaumcraft.common.config.ConfigBlocks.FluidDeath
import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe

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

		addCrucibleRecipe(ModItems.energizing_frame.registryName, CrucibleRecipe(
				"ENERGIZINGFRAME@1", ItemStack(ModItems.energizing_frame), ItemStack(ModItems.greatwood_frame),
				AspectList().add(ENERGY, 100).add(MOTION, 25)
		))

		addCrucibleRecipe(ModItems.purifying_frame.registryName, CrucibleRecipe(
				"PURIFYINGFRAME@1", ItemStack(ModItems.purifying_frame), ItemStack(ModItems.silverwood_frame),
				AspectList().add(AURA, 50).add(LIFE, 25)
		))
	}

	fun registerInfusionRecipes() {
		val plateThaumium = ItemStack(ItemsTC.plate, 1, 2)
		val deathBucket = FluidUtil.getFilledBucket(FluidStack(FluidDeath.instance, 1000))

		addInfusionCraftingRecipe(ModItems.death_frame.registryName, InfusionRecipe(
				"DEATHFRAME@4", ItemStack(ModItems.death_frame), 5,
				AspectList().add(DEATH, 25).add(AIR, 25).add(FLUX, 25), ItemStack(ModItems.greatwood_frame),
				plateThaumium, deathBucket, plateThaumium, Items.WATER_BUCKET
		))

		addInfusionCraftingRecipe(ModItems.mutating_frame.registryName, InfusionRecipe(
				"MUTATINGFRAME@2", ItemStack(ModItems.mutating_frame), 5,
				AspectList().add(Aspect.EXCHANGE, 25).add(FLUX, 50).add(SOUL, 25), ItemStack(ModItems.silverwood_frame),
				ItemStack(ItemsTC.bottleTaint), ItemStack(Blocks.SOUL_SAND)
		))

		addInfusionCraftingRecipe(ResourceLocation(MODID, "apiarist_enchant"), InfusionApiaristRecipe)
		addFakeCraftingRecipe(ResourceLocation(MODID, "apiarist_enchant_fake"), InfusionApiaristRecipe.fakeRecipe)
	}
}
