package me.apemanzilla.thaumicapiculture.utils

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A utility for initializing values using a delegate, and adding them to a map for later use
 * @param S the supertype for delegates stored in this map
 */
class DelegateMap<S> {
	val values = mutableMapOf<String, Delegate<out S>>()

	class Delegate<T>(val value: T) : ReadOnlyProperty<Any?, T> {
		override operator fun getValue(thisRef: Any?, property: KProperty<*>) = value
	}

	interface Provider<T> {
		operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): Delegate<T>
	}

	operator fun <T : S> invoke(initializer: (String) -> T) = object : Provider<T> {
		override operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): Delegate<T> {
			return Delegate(initializer(property.name)).also { values[property.name] = it }
		}
	}
}
