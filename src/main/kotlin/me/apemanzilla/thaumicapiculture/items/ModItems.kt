package me.apemanzilla.thaumicapiculture.items

import me.apemanzilla.thaumicapiculture.ThaumicApiculture
import me.apemanzilla.thaumicapiculture.lib.StaticBeeModifier
import me.apemanzilla.thaumicapiculture.utils.DelegateMap
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import thaumcraft.api.ThaumcraftMaterials.TOOLMAT_THAUMIUM
import thaumcraft.api.ThaumcraftMaterials.TOOLMAT_VOID

object ModItems {
	val items = DelegateMap<IBaseItem>()

	val thaumium_scoop by items { id -> ItemCustomScoop(id, TOOLMAT_THAUMIUM, 20) }
	val thaumium_grafter by items { id -> ItemCustomGrafter(id, TOOLMAT_THAUMIUM, 20) }

	val void_scoop by items { id -> ItemCustomScoop(id, TOOLMAT_VOID, 5) }
	val void_grafter by items { id -> ItemCustomGrafter(id, TOOLMAT_VOID, 5) }

	val silverwood_frame by items { id ->
		ItemMagicFrame(id, 240, StaticBeeModifier(
				lifespan = 1.1f,
				geneticDecay = 0.4f,
				production = 2.0f
		))
	}

	val channeling_frame by items { id ->
		ItemMagicFrame(id, 240, StaticBeeModifier(
				lifespan = 1.25f,
				geneticDecay = 0.3f,
				production = 2.25f
		), visCost = 2f)
	}

	fun registerItems(e: RegistryEvent.Register<Item>) {
		items.values.forEach { (_, delegate) -> e.registry.register(delegate.value.item) }
		ThaumicApiculture.log.debug("Registered ${items.values.size} items")
	}

	fun registerModels(e: ModelRegistryEvent) {
		items.values.forEach { (_, delegate) -> delegate.value.registerModel(e) }
		ThaumicApiculture.log.debug("Registered ${items.values.size} models")
	}
}
