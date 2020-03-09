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
fun MdPageGeneratorMojo.setInputFileExtensions(inputFileExtensions: String) =
        reflectiveSet("inputFileExtensions", inputFileExtensions)

var MdPageGeneratorMojo.applyFiltering: Boolean by ReflectionAccessor
var MdPageGeneratorMojo.timestampFormat: String by ReflectionAccessor
var MdPageGeneratorMojo.attributes: Array<String> by ReflectionAccessor
var MdPageGeneratorMojo.pegdownExtensions: String by ReflectionAccessor

private object ReflectionAccessor {
    operator fun <T> getValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>): T {
        return thisRef.reflectiveGet(property.name)
    }

    operator fun <T> setValue(thisRef: MdPageGeneratorMojo, property: KProperty<*>, value: T) {
        thisRef.reflectiveSet(property.name, value)
    }
}

@Suppress("UNCHECKED_CAST")
private fun <T> MdPageGeneratorMojo.reflectiveGet(fieldName: String): T = getAccessibleField(fieldName).get(this) as T
private fun <T> MdPageGeneratorMojo.reflectiveSet(fieldName: String, value: T): Unit = getAccessibleField(fieldName)
        .set(this, value)

private fun MdPageGeneratorMojo.getAccessibleField(fieldName: String): Field = javaClass.getDeclaredField(fieldName)
        .apply {
            if (!isAccessible) {
                isAccessible = true
            }
        }