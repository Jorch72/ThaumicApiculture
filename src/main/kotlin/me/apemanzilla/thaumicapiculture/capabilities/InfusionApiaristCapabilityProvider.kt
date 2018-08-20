package me.apemanzilla.thaumicapiculture.capabilities

import forestry.api.apiculture.ApicultureCapabilities.ARMOR_APIARIST
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider

object InfusionApiaristCapabilityProvider : ICapabilityProvider {
	override fun hasCapability(cap: Capability<*>, f: EnumFacing?) = cap == ARMOR_APIARIST

	override fun <T : Any?> getCapability(cap: Capability<T>, f: EnumFacing?) = when (cap) {
		ARMOR_APIARIST -> cap.defaultInstance
		else -> null
	}
}
