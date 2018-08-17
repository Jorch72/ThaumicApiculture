package me.apemanzilla.thaumicapiculture.items

import forestry.api.core.IToolScoop
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Enchantments
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import thaumcraft.api.ThaumcraftMaterials.TOOLMAT_VOID
import thaumcraft.api.items.IWarpingGear

open class CustomScoop(id: String, material: Item.ToolMaterial, durability: Int) : BasicTool(id, material, durability), IToolScoop, IWarpingGear {
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
