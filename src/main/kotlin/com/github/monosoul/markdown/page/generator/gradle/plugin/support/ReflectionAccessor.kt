package com.github.monosoul.markdown.page.generator.gradle.plugin.support

import java.lang.reflect.Field
import kotlin.reflect.KProperty

object ReflectionAccessor {
    inline operator fun <reified O : Any, T> getValue(obj: O, property: KProperty<*>): T =
        obj.reflectiveGet(property.name)

    inline operator fun <reified O : Any, T> setValue(obj: O, property: KProperty<*>, value: T) =
        obj.reflectiveSet(property.name, value)

    @Suppress("UNCHECKED_CAST")
    inline fun <reified O : Any, T> O.reflectiveGet(fieldName: String): T = getAccessibleField(fieldName).get(this) as T
    inline fun <reified O : Any, T> O.reflectiveSet(fieldName: String, value: T): Unit = getAccessibleField(fieldName)
        .set(this, value)

    inline fun <reified O : Any> O.getAccessibleField(fieldName: String): Field = O::class.java
        .getDeclaredField(fieldName)
        .apply {
            if (!isAccessible) {
                isAccessible = true
            }
        }
}