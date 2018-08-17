package me.apemanzilla.thaumicapiculture.lib

import forestry.api.apiculture.IBeeModifier
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World

interface DetailedBeeModifier : IBeeModifier {
	/** Add description information about this modifier to the given item stack */
	fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: ITooltipFlag)
}
