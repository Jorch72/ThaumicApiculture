package me.apemanzilla.thaumicapiculture.items

import me.apemanzilla.thaumicapiculture.ThaumicApiculture
import me.apemanzilla.thaumicapiculture.ThaumicApiculture.MODID
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.RegistryEvent

interface IBaseItem {
	/** Get a reference to the [Item] instance */
	val item get() = this as? Item ?: throw IllegalStateException("IBaseItem not instance of Item: $this")

	/** Initializes the [item] using the given parameters. Intended to be called by the constructor. */
	fun init(id: String, stackSize: Int? = null) {
		item.registryName = ResourceLocation(MODID, id)
		item.unlocalizedName = "$MODID.$id"
		item.creativeTab = ThaumicApiculture.CREATIVE_TAB
		stackSize?.let { size -> item.setMaxStackSize(size) }
	}

	/** Registers the item */
	fun registerItem(e: RegistryEvent.Register<Item>) = e.registry.register(item)

	/** Registers the model for this item */
	fun registerModel(e: ModelRegistryEvent) {
		ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation(item.registryName!!, "inventory"))
	}
}
