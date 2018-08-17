package me.apemanzilla.thaumicapiculture.items

import net.minecraft.item.Item

/** A basic ThaumicApiculture item */
open class ItemBase(id: String, stackSize: Int? = null) : Item(), IBaseItem {
	init {
		init(id, stackSize)
	}
}

