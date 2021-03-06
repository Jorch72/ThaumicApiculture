package me.apemanzilla.thaumicapiculture.research

import me.apemanzilla.thaumicapiculture.nextFromRange
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.text.TextComponentTranslation
import thaumcraft.api.research.theorycraft.ResearchTableData
import thaumcraft.api.research.theorycraft.TheorycraftCard

class CardObserveBees : TheorycraftCard() {
	override fun getInspirationCost() = 1

	override fun isAidOnly() = true

	override fun getResearchCategory() = "THAUMICAPICULTURE"

	val progressRange = 20..30

	override fun getLocalizedName() = TextComponentTranslation("thaumicapiculture.card.observebees.name").unformattedText

	override fun getLocalizedText() = TextComponentTranslation("thaumicapiculture.card.observebees.text", progressRange.first, progressRange.last).unformattedText

	override fun initialize(player: EntityPlayer, data: ResearchTableData) = true

	override fun activate(player: EntityPlayer, data: ResearchTableData): Boolean {
		data.addTotal(researchCategory, player.rng.nextFromRange(progressRange))
		return true
	}
}
