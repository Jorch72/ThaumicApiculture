package me.apemanzilla.thaumicapiculture.research

import forestry.apiculture.ModuleApiculture
import thaumcraft.api.research.theorycraft.ITheorycraftAid
import thaumcraft.api.research.theorycraft.TheorycraftCard

object BeeHouseTheorycraftAid : ITheorycraftAid {
	override fun getAidObject() = ModuleApiculture.getBlocks().beeHouse

	@Suppress("UNCHECKED_CAST")
	override fun getCards() = listOf(
			CardObserveBees::class.java,
			CardObserveBees::class.java,
			CardObserveBees::class.java
	).map { it as Class<TheorycraftCard> }.toTypedArray()
}
