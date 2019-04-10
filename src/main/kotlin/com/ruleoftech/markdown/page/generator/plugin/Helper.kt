package com.ruleoftech.markdown.page.generator.plugin

import java.lang.reflect.Field
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun getAccessibleField(fieldName: String): Field {
    val field = MdPageGeneratorMojo::class.java.getDeclaredField(fieldName)
    field.isAccessible.takeIf { !it }?.let { field.isAccessible = true }

    return field
}

fun <T : Any> reflection(): ReadWriteProperty<MdPageGeneratorMojo, T> {
    return object : ReadWriteProperty<MdPageGeneratorMojo, T> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>): T {
            val field = getAccessibleField(property.name)

            return field.get(thisRef) as T
        }

        override fun setValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>, value: T) {
            val field = getAccessibleField(property.name)

            field.set(thisRef, value)
        }
    }
}