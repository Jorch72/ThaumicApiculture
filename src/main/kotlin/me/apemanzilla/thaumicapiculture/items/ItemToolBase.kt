package me.apemanzilla.thaumicapiculture.items

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTool
import net.minecraft.world.World
import thaumcraft.api.ThaumcraftMaterials
import thaumcraft.api.items.IWarpingGear

/** A basic ThaumicApiculture tool */
open class ItemToolBase(
		id: String,
		material: ToolMaterial,
		durability: Int? = null
) : ItemTool(material, setOf()), IBaseItem, IWarpingGear {
	init {
		init(id)
		durability?.let { uses -> maxDamage = uses }
	}

	/** Whether this tool self-repairs (i.e. void metal) */
	open val selfRepair = toolMaterial == ThaumcraftMaterials.TOOLMAT_VOID

	@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
	override fun onUpdate(stack: ItemStack, worldIn: World?, entity: Entity?, itemSlot: Int, isSelected: Boolean) {
		super.onUpdate(stack, worldIn, entity, itemSlot, isSelected)

		if (selfRepair && stack.isItemDamaged && entity != null && entity.ticksExisted % 20 == 0 && entity is EntityLivingBase) {
			stack.damageItem(-1, entity)
		}
	}

	override fun getWarp(stack: ItemStack?, player: EntityPlayer?) = when (toolMaterial) {
		ThaumcraftMaterials.TOOLMAT_VOID -> 1
		else -> 0
	}

	override fun getIsRepairable(toRepair: ItemStack, repair: ItemStack): Boolean {
		return repair.isItemEqual(toolMaterial.repairItemStack) || super.getIsRepairable(toRepair, repair)
	}
}
