@file:Suppress("RemoveSingleExpressionStringTemplate", "RemoveCurlyBracesFromTemplate")

package me.apemanzilla.thaumicapiculture

import me.apemanzilla.thaumicapiculture.ThaumicApiculture.MODID
import me.apemanzilla.thaumicapiculture.items.ModItems
import me.apemanzilla.thaumicapiculture.proxy.CommonProxy
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(
		modid = MODID, useMetadata = true,
		dependencies = "required-after:forgelin;required-after:forestry;required-after:thaumcraft",
		modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter"
)
object ThaumicApiculture {
	const val MODID = "thaumicapiculture"

	val CREATIVE_TAB = object : CreativeTabs("thaumicapiculture") {
		override fun getTabIconItem() = ItemStack(ModItems.thaumium_scoop)
	}

	internal lateinit var log: Logger

	@SidedProxy(
			clientSide = "me.apemanzilla.thaumicapiculture.proxy.ClientProxy",
			serverSide = "me.apemanzilla.thaumicapiculture.proxy.CommonProxy"
	)
	lateinit var proxy: CommonProxy

	@Mod.EventHandler
	fun preInit(e: FMLPreInitializationEvent) = proxy.preInit(e)

	@Mod.EventHandler
	fun init(e: FMLInitializationEvent) = proxy.init(e)

	@Mod.EventHandler
	fun postInit(e: FMLPostInitializationEvent) = proxy.postInit(e)
}
