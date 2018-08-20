package me.apemanzilla.thaumicapiculture.proxy

import me.apemanzilla.thaumicapiculture.items.ModItems
import me.apemanzilla.thaumicapiculture.recipes.hasApiaristEnchant
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemArmor
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ClientProxy : CommonProxy() {
	init {
		MinecraftForge.EVENT_BUS.register(ClientEvents)
	}

	companion object ClientEvents {
		@SubscribeEvent
		fun registerModels(e: ModelRegistryEvent) = ModItems.registerModels(e)

		@SubscribeEvent
		fun onItemTooltip(e: ItemTooltipEvent) {
			if (e.itemStack.item is ItemArmor && e.itemStack.hasApiaristEnchant()) {
				e.toolTip.add(1, "§6${I18n.format("tooltip.thaumicapiculture.apiaristinfusion")}")
			}
		}

	}
}
