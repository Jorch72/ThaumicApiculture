package me.apemanzilla.thaumicapiculture.items

import me.apemanzilla.thaumicapiculture.ThaumicApiculture.CREATIVE_TAB
import me.apemanzilla.thaumicapiculture.ThaumicApiculture.MODID
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTool
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation
import net.minecraftforge.event.RegistryEvent
import thaumcraft.api.ThaumcraftMaterials.TOOLMAT_VOID
import thaumcraft.api.items.IWarpingGear

/** Initialize properties of this item using the given parameters */
private fun Item.init(id: String, stackSize: Int? = null, creativeTab: CreativeTabs = CREATIVE_TAB) {
	this.registryName = ResourceLocation(MODID, id)
	this.unlocalizedName = "$MODID.$id"
	this.creativeTab = creativeTab
	stackSize?.let { size -> this.setMaxStackSize(size) }
}

interface IBaseItem {
	/** Get a reference to the [Item] instance */
	val item get() = this as? Item ?: throw IllegalStateException("IBaseItem not instance of Item: $this")

	/** Registers the item */
	fun registerItem(e: RegistryEvent.Register<Item>) = e.registry.register(item)

	/** Registers the model for this item */
	fun registerModel(e: ModelRegistryEvent) {
		setCustomModelResourceLocation(item, 0, ModelResourceLocation(item.registryName!!, "inventory"))
	}
}

/** A basic ThaumicApiculture item */
open class BasicItem(id: String, stackSize: Int? = null) : Item(), IBaseItem {
	init {
		init(id, stackSize)
	}
}

/** A basic ThaumicApiculture tool */
open class BasicTool(
		id: String,
		material: ToolMaterial,
		durability: Int? = null
) : ItemTool(material, setOf()), IBaseItem, IWarpingGear {
	init {
		init(id)
		durability?.let { uses -> maxDamage = uses }
	}

	/** Whether this tool self-repairs (i.e. void metal) */
	open val selfRepair = toolMaterial == TOOLMAT_VOID

	@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
	override fun onUpdate(stack: ItemStack, worldIn: World?, entity: Entity?, itemSlot: Int, isSelected: Boolean) {
		super.onUpdate(stack, worldIn, entity, itemSlot, isSelected)

		if (selfRepair && stack.isItemDamaged && entity != null && entity.ticksExisted % 20 == 0 && entity is EntityLivingBase) {
			stack.damageItem(-1, entity)
		}
	}

	override fun getWarp(stack: ItemStack?, player: EntityPlayer?) = when (toolMaterial) {
		TOOLMAT_VOID -> 1
		else -> 0
	}

	override fun getIsRepairable(toRepair: ItemStack, repair: ItemStack): Boolean {
		return repair.isItemEqual(toolMaterial.repairItemStack) || super.getIsRepairable(toRepair, repair)
	}
}
