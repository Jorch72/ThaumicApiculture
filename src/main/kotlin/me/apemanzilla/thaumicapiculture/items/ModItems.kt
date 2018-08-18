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
				lifespan = 1.25f,
				geneticDecay = 0.4f,
				production = 1.5f
		))
	}

	val greatwood_frame by items { id ->
		ItemMagicFrame(id, 360, StaticBeeModifier(
				production = 2f,
				geneticDecay = 0.7f
		))
	}

	val energizing_frame by items { id ->
		ItemMagicFrame(id, 360, StaticBeeModifier(
				production = 2.25f,
				lifespan = 0.75f
		))
	}

	val purifying_frame by items { id ->
		ItemMagicFrame(id, 240, StaticBeeModifier(
				lifespan = 2f,
				geneticDecay = 0.25f,
				production = 1.75f,
				mutation = 0.75f
		))
	}

	val death_frame by items { id ->
		ItemMagicFrame(id, 30, StaticBeeModifier(
				lifespan = 0f,
				production = 0f,
				geneticDecay = 1.5f
		), fluxReleased = 0.25f)
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
