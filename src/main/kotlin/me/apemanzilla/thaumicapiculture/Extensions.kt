package me.apemanzilla.thaumicapiculture

import java.util.*

fun Random.nextFromRange(range: IntRange) = nextInt(range.last - range.first + 1) + range.first
