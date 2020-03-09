package com.ruleoftech.markdown.page.generator.plugin

import java.lang.reflect.Field
import kotlin.reflect.KProperty

var MdPageGeneratorMojo.defaultTitle: String by ReflectionAccessor
var MdPageGeneratorMojo.alwaysUseDefaultTitle: Boolean by ReflectionAccessor
var MdPageGeneratorMojo.headerHtmlFile: String by ReflectionAccessor
var MdPageGeneratorMojo.footerHtmlFile: String by ReflectionAccessor
var MdPageGeneratorMojo.failIfFilesAreMissing: Boolean by ReflectionAccessor
var MdPageGeneratorMojo.recursiveInput: Boolean by ReflectionAccessor
var MdPageGeneratorMojo.transformRelativeMarkdownLinks: Boolean by ReflectionAccessor
var MdPageGeneratorMojo.inputEncoding: String by ReflectionAccessor
var MdPageGeneratorMojo.outputEncoding: String by ReflectionAccessor
var MdPageGeneratorMojo.parsingTimeoutInMillis: Long by ReflectionAccessor
fun MdPageGeneratorMojo.setInputFileExtensions(inputFileExtensions: String) {
    val field = getAccessibleField("inputFileExtensions")

    field.set(this, inputFileExtensions)
}

var MdPageGeneratorMojo.applyFiltering: Boolean by ReflectionAccessor
var MdPageGeneratorMojo.timestampFormat: String by ReflectionAccessor
var MdPageGeneratorMojo.attributes: Array<String> by ReflectionAccessor
var MdPageGeneratorMojo.pegdownExtensions: String by ReflectionAccessor

private fun getAccessibleField(fieldName: String): Field {
    val field = MdPageGeneratorMojo::class.java.getDeclaredField(fieldName)
    field.isAccessible.takeIf { !it }?.let { field.isAccessible = true }

    return field
}

private object ReflectionAccessor {
    @Suppress("UNCHECKED_CAST")
    operator fun <T> getValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>): T {
        val field = getAccessibleField(property.name)

        return field.get(thisRef) as T
    }

    operator fun <T> setValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>, value: T) {
        val field = getAccessibleField(property.name)

        field.set(thisRef, value)
    }
}