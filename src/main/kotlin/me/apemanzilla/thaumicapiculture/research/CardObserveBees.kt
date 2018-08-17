package me.apemanzilla.thaumicapiculture.research

import me.apemanzilla.thaumicapiculture.ThaumicApiculture
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.text.TextComponentTranslation
import thaumcraft.api.research.theorycraft.ResearchTableData
import thaumcraft.api.research.theorycraft.TheorycraftCard

open class CardObserveBees : TheorycraftCard() {
	override fun getInspirationCost() = 1

	override fun isAidOnly() = true

	override fun getResearchCategory() = "THAUMICAPICULTURE"

	override fun getLocalizedName() = TextComponentTranslation("thaumicapiculture.card.observebees.name").unformattedText

	override fun getLocalizedText() = TextComponentTranslation("thaumicapiculture.card.observebees.text").unformattedText

	override fun initialize(player: EntityPlayer?, data: ResearchTableData?): Boolean {
		ThaumicApiculture.log.info("$this initializing")
		return true
	}

	override fun activate(player: EntityPlayer, data: ResearchTableData): Boolean {
		ThaumicApiculture.log.info("$this activating")
		data.addTotal(researchCategory, 25)
		return true
	}
}
