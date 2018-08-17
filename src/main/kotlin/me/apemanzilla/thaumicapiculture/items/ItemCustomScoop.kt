package me.apemanzilla.thaumicapiculture.items

import forestry.api.core.IToolScoop
import net.minecraft.enchantment.Enchantment
import net.minecraft.init.Enchantments
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import thaumcraft.api.items.IWarpingGear

open class ItemCustomScoop(id: String, material: Item.ToolMaterial, durability: Int) : ItemToolBase(id, material, durability), IToolScoop, IWarpingGear {
	companion object {
		val allowedEnchants = setOf(
				Enchantments.EFFICIENCY,
				Enchantments.UNBREAKING,
				Enchantments.MENDING,
				Enchantments.VANISHING_CURSE
		)
	}

	init {
		super.setHarvestLevel("scoop", material.harvestLevel)
	}

	override fun canApplyAtEnchantingTable(stack: ItemStack, enchantment: Enchantment) = enchantment in allowedEnchants
}
