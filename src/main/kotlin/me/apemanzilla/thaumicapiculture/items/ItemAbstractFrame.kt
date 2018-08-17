package me.apemanzilla.thaumicapiculture.items

import forestry.api.apiculture.IBee
import forestry.api.apiculture.IBeeHousing
import forestry.api.apiculture.IHiveFrame
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World

abstract class ItemAbstractFrame(id: String, durability: Int) : ItemBase(id, 1), IHiveFrame {
	init {
		maxDamage = durability
	}

	override fun frameUsed(housing: IBeeHousing, frame: ItemStack, queen: IBee, wear: Int): ItemStack {
		frame.itemDamage += wear

		return if (frame.itemDamage > frame.maxDamage) ItemStack.EMPTY else frame
	}

	abstract override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: ITooltipFlag)
}
