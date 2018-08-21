package me.apemanzilla.thaumicapiculture.recipes

import me.apemanzilla.thaumicapiculture.ThaumicApiculture.MODID
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.nbt.NBTTagByte
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.RecipeMatcher
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.capabilities.ThaumcraftCapabilities
import thaumcraft.api.crafting.InfusionRecipe

class CustomInfusionEnchantRecipe(
		val name: String,
		research: String,
		instability: Int,
		aspects: AspectList,
		val capability: Capability<*>,
		vararg recipe: Any,
		val matchCenter: (ItemStack) -> Boolean
) : InfusionRecipe(research, null, instability, aspects, Ingredient.EMPTY, *recipe) {
	init {
		MinecraftForge.EVENT_BUS.register(this)
	}

	/** Check whether the given ItemStack has this enchantment */
	fun ItemStack.hasEnchant() = tagCompound?.getBoolean("infusionenchant.$name") == true

	/** Apply this enchantment to the given ItemStack - mutates stack! copy it first! */
	fun ItemStack.applyEnchant() = apply { setTagInfo("infusionenchant.$name", NBTTagByte(1)) }

	@SubscribeEvent
	fun onTooltipEvent(e: ItemTooltipEvent) {
		if (e.itemStack.hasEnchant()) e.toolTip.add(1, "§6" + I18n.format("tooltip.$MODID.$name"))
	}

	@SubscribeEvent
	fun onAttachItemCapabilities(e: AttachCapabilitiesEvent<ItemStack>) {
		// item's NBT may not be set yet, so we always return a provider
		e.addCapability(ResourceLocation(MODID, name), object : ICapabilityProvider {
			override fun hasCapability(cap: Capability<*>, f: EnumFacing?) =
					e.`object`.hasEnchant() && cap == capability

			override fun <T : Any?> getCapability(cap: Capability<T>, f: EnumFacing?): T? {
				if (e.`object`.hasEnchant() && cap == capability) return capability.defaultInstance as T

				return null
			}
		})
	}

	override fun matches(input: List<ItemStack>, central: ItemStack?, world: World, player: EntityPlayer): Boolean {
		// check that we have a central item
		if (central?.isEmpty != false) return false

		// check that central item isn't already enchanted with this effect
		if (central.hasEnchant()) return false

		// check that central item matches given requirement
		if (!matchCenter(central)) return false

		// check that player knows research
		if (!ThaumcraftCapabilities.knowsResearch(player, research)) return false

		// check that outer items are valid
		if (RecipeMatcher.findMatches(input, components) == null) return false

		// valid infusion
		return true
	}

	override fun getRecipeOutput(p: EntityPlayer, item: ItemStack, comps: List<ItemStack>) = item.copy().applyEnchant()

	/** Creates a fake InfusionRecipe to be used for display */
	fun fakeRecipe(central: ItemStack) = object : InfusionRecipe(
			research, null, instability, aspects, central, *components.toArray()
	) {
		override fun getRecipeOutput() = central.copy().applyEnchant()
		override fun getRecipeOutput(p: EntityPlayer, item: ItemStack, comps: List<ItemStack>) = item.copy().applyEnchant()
	}
}
