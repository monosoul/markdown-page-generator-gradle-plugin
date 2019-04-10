package com.ruleoftech.markdown.page.generator.plugin

import java.lang.reflect.Field
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

val nullableStringDelegate = object : ReadWriteProperty<MdPageGeneratorMojo, String?> {
    override fun getValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>): String? {
        val field = getAccessibleField(property.name)

        return field.get(thisRef) as String?
    }

    override fun setValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>, value: String?) {
        val field = getAccessibleField(property.name)

        field.set(thisRef, value)
    }
}

val stringDelegate = object : ReadWriteProperty<MdPageGeneratorMojo, String> {
    override fun getValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>): String {
        val field = getAccessibleField(property.name)

        return field.get(thisRef) as String
    }

    override fun setValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>, value: String) {
        val field = getAccessibleField(property.name)

        field.set(thisRef, value)
    }
}

val booleanDelegate = object : ReadWriteProperty<MdPageGeneratorMojo, Boolean> {
    override fun getValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>): Boolean {
        val field = getAccessibleField(property.name)

        return field.getBoolean(thisRef)
    }

    override fun setValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>, value: Boolean) {
        val field = getAccessibleField(property.name)

        field.setBoolean(thisRef, value)
    }
}

val longDelegate = object : ReadWriteProperty<MdPageGeneratorMojo, Long> {
    override fun getValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>): Long {
        val field = getAccessibleField(property.name)

        return field.getLong(thisRef)
    }

    override fun setValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>, value: Long) {
        val field = getAccessibleField(property.name)

        field.setLong(thisRef, value)
    }
}

val stringArrayDelegate = object : ReadWriteProperty<MdPageGeneratorMojo, Array<String>> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>): Array<String> {
        val field = getAccessibleField(property.name)

        return field.get(thisRef) as Array<String>
    }

    override fun setValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>, value: Array<String>) {
        val field = getAccessibleField(property.name)

        field.set(thisRef, value)
    }
}

fun getAccessibleField(fieldName: String): Field {
    val field = MdPageGeneratorMojo::class.java.getDeclaredField(fieldName)
    field.isAccessible = true

    return field
}