package me.apemanzilla.thaumicapiculture.items

import forestry.api.arboriculture.IToolGrafter
import net.minecraft.block.BlockLeaves
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Enchantments
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import thaumcraft.api.ThaumcraftMaterials.TOOLMAT_VOID
import thaumcraft.api.items.IWarpingGear

// TODO: figure out why this doesn't work for non-forestry leaves
open class CustomGrafter(id: String, material: Item.ToolMaterial, durability: Int) : BasicTool(id, material, durability), IToolGrafter, IWarpingGear {
	companion object {
		val allowedEnchants = setOf(
				Enchantments.EFFICIENCY,
				Enchantments.UNBREAKING,
				Enchantments.MENDING,
				Enchantments.VANISHING_CURSE
		)
	}

	init {
		super.setHarvestLevel("grafter", material.harvestLevel)
	}

	override fun onBlockDestroyed(s: ItemStack, w: World, b: IBlockState, p: BlockPos, e: EntityLivingBase) = true

	override fun canHarvestBlock(state: IBlockState) =
			state.block is BlockLeaves || state.material == Material.LEAVES || super.canHarvestBlock(state)

	override fun getSaplingModifier(stack: ItemStack, world: World, player: EntityPlayer, pos: BlockPos) = 100f

	override fun canApplyAtEnchantingTable(stack: ItemStack, enchantment: Enchantment) = enchantment in allowedEnchants
}
