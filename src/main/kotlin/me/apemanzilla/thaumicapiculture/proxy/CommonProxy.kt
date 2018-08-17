package me.apemanzilla.thaumicapiculture.proxy

import me.apemanzilla.thaumicapiculture.ThaumicApiculture
import me.apemanzilla.thaumicapiculture.ThaumicApiculture.MODID
import me.apemanzilla.thaumicapiculture.items.ModItems
import me.apemanzilla.thaumicapiculture.recipes.Recipes
import me.apemanzilla.thaumicapiculture.research.BeeHouseTheorycraftAid
import me.apemanzilla.thaumicapiculture.research.CardObserveBees
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.Aspect.*
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.research.ResearchCategories
import thaumcraft.api.research.theorycraft.TheorycraftManager

open class CommonProxy {
	init {
		MinecraftForge.EVENT_BUS.register(CommonEvents)
	}

	open fun preInit(e: FMLPreInitializationEvent) {
		ThaumicApiculture.log = e.modLog
	}

	open fun init(e: FMLInitializationEvent) {
		ResearchCategories.registerCategory(
				"THAUMICAPICULTURE",
				null, // no previous research needed to discover this category
				AspectList().add(LIFE, 10).add(BEAST, 5).add(AIR, 5).add(PLANT, 5),
				ResourceLocation(MODID, "textures/items/thaumium_scoop.png"),
				ResourceLocation("thaumcraft", "textures/gui/gui_research_back_1.jpg"),
				ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png")
		)
		ThaumcraftApi.registerResearchLocation(ResourceLocation(MODID, "research/apiculture"))

		Recipes.registerAll()
	}

	open fun postInit(e: FMLPostInitializationEvent) {
		TheorycraftManager.registerCard(CardObserveBees::class.java)
		TheorycraftManager.registerAid(BeeHouseTheorycraftAid)
	}

	companion object CommonEvents {
		@SubscribeEvent
		fun registerItems(e: RegistryEvent.Register<Item>) = ModItems.registerItems(e)
	}
}
