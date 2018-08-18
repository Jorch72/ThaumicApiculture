package me.apemanzilla.thaumicapiculture.research

import forestry.core.ModuleCore
import me.apemanzilla.thaumicapiculture.nextFromRange
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.text.TextComponentTranslation
import thaumcraft.api.research.theorycraft.ResearchTableData
import thaumcraft.api.research.theorycraft.TheorycraftCard

class CardAnalyzeBees : TheorycraftCard() {
	override fun getInspirationCost() = 2

	override fun isAidOnly() = true

	override fun getResearchCategory() = "THAUMICAPICULTURE"

	val progressRange = 30..50

	override fun getLocalizedName() = TextComponentTranslation("thaumicapiculture.card.analyzebees.name").unformattedText

	override fun getLocalizedText() = TextComponentTranslation("thaumicapiculture.card.analyzebees.text", progressRange.first, progressRange.last).unformattedText

	override fun initialize(player: EntityPlayer, data: ResearchTableData) = true

	override fun getRequiredItems() = arrayOf(ItemStack(ModuleCore.getItems().portableAlyzer))

	override fun activate(player: EntityPlayer, data: ResearchTableData): Boolean {
		data.addTotal(researchCategory, player.rng.nextFromRange(progressRange))
		data.bonusDraws++
		return true
	}
}
