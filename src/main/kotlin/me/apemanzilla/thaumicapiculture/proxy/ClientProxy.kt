package me.apemanzilla.thaumicapiculture.proxy

import me.apemanzilla.thaumicapiculture.items.ModItems
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ClientProxy : CommonProxy() {
	init {
		MinecraftForge.EVENT_BUS.register(ClientEvents)
	}

	companion object ClientEvents {
		@SubscribeEvent
		fun registerModels(e: ModelRegistryEvent) = ModItems.registerModels(e)
	}
}
