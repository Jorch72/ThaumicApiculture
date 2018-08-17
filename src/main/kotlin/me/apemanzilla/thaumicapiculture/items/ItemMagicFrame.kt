package me.apemanzilla.thaumicapiculture.items

import forestry.api.apiculture.IBee
import forestry.api.apiculture.IBeeHousing
import me.apemanzilla.thaumicapiculture.lib.StaticBeeModifier
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import thaumcraft.api.aura.AuraHelper

/** A magical frame, with a static modifier and passive vis drain */
open class ItemMagicFrame(
		id: String,
		durability: Int,
		val modifier: StaticBeeModifier,
		val visCost: Float = 0f,
		val polluteRate: Float = 0.1f
) : ItemAbstractFrame(id, durability) {
	override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: ITooltipFlag) {
		modifier.addInformation(stack, world, tooltip, advanced)
		if (visCost > 0) tooltip += I18n.format("thaumicapiculture.modifier.viscost", visCost)
	}

	override fun frameUsed(housing: IBeeHousing, frame: ItemStack, queen: IBee, wear: Int): ItemStack {
		if (visCost > 0) {
			val drained = AuraHelper.drainVis(housing.worldObj, housing.coordinates, visCost, false)

			if (drained < visCost && polluteRate > 0) {
				AuraHelper.polluteAura(housing.worldObj, housing.coordinates, (visCost - drained) * polluteRate, true)
			}
		}

		return super.frameUsed(housing, frame, queen, wear)
	}

	override fun getBeeModifier() = modifier
}
