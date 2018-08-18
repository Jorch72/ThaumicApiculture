package me.apemanzilla.thaumicapiculture.items

import forestry.api.apiculture.IBee
import forestry.api.apiculture.IBeeHousing
import me.apemanzilla.thaumicapiculture.lib.StaticBeeModifier
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import thaumcraft.api.aura.AuraHelper

/** A magical frame, with a static modifier and optional vis/flux interactions */
open class ItemMagicFrame(
		id: String,
		durability: Int,
		val modifier: StaticBeeModifier,
		val visCost: Float? = null,
		val fluxReleased: Float? = null
) : ItemAbstractFrame(id, durability) {
	override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: ITooltipFlag) {
		modifier.addInformation(stack, world, tooltip, advanced)
		visCost?.let { vis -> tooltip += I18n.format("thaumicapiculture.modifier.viscost", vis) }
		fluxReleased?.let { flux -> tooltip += "§5${I18n.format("thaumicapiculture.modifier.fluxreleased", flux)}" }
	}

	override fun frameUsed(housing: IBeeHousing, frame: ItemStack, queen: IBee, wear: Int): ItemStack {
		visCost?.let { vis ->
			val drained = AuraHelper.drainVis(housing.worldObj, housing.coordinates, vis, false)
			if (drained < vis) AuraHelper.polluteAura(housing.worldObj, housing.coordinates, (vis - drained) / 10, true)
		}

		fluxReleased?.let { flux -> AuraHelper.polluteAura(housing.worldObj, housing.coordinates, flux, true) }

		return super.frameUsed(housing, frame, queen, wear)
	}

	override fun getBeeModifier() = modifier
}
