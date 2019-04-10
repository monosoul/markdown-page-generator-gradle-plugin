package com.ruleoftech.markdown.page.generator.plugin

import java.lang.reflect.Field

fun MdPageGeneratorMojo.setDefaultTitle(defaultTitle: String) {
    val field = getAccessibleField("defaultTitle")

    field.set(this, defaultTitle)
}

fun MdPageGeneratorMojo.setAlwaysUseDefaultTitle(alwaysUseDefaultTitle: Boolean) {
    val field = getAccessibleField("alwaysUseDefaultTitle")

    field.setBoolean(this, alwaysUseDefaultTitle)
}

fun MdPageGeneratorMojo.setHeaderHtmlFile(headerHtmlFile: String) {
    val field = getAccessibleField("headerHtmlFile")

    field.set(this, headerHtmlFile)
}

fun MdPageGeneratorMojo.setFooterHtmlFile(footerHtmlFile: String) {
    val field = getAccessibleField("footerHtmlFile")

    field.set(this, footerHtmlFile)
}

fun MdPageGeneratorMojo.setFailIfFilesAreMissing(failIfFilesAreMissing: Boolean) {
    val field = getAccessibleField("failIfFilesAreMissing")

    field.setBoolean(this, failIfFilesAreMissing)
}

fun MdPageGeneratorMojo.setRecursiveInput(recursiveInput: Boolean) {
    val field = getAccessibleField("recursiveInput")

    field.setBoolean(this, recursiveInput)
}

fun MdPageGeneratorMojo.setTransformRelativeMarkdownLinks(transformRelativeMarkdownLinks: Boolean) {
    val field = getAccessibleField("transformRelativeMarkdownLinks")

    field.setBoolean(this, transformRelativeMarkdownLinks)
}

fun MdPageGeneratorMojo.setInputEncoding(inputEncoding: String) {
    val field = getAccessibleField("inputEncoding")

    field.set(this, inputEncoding)
}

fun MdPageGeneratorMojo.setOutputEncoding(outputEncoding: String) {
    val field = getAccessibleField("outputEncoding")

    field.set(this, outputEncoding)
}

fun MdPageGeneratorMojo.setParsingTimeoutInMillis(parsingTimeoutInMillis: Long) {
    val field = getAccessibleField("parsingTimeoutInMillis")

    field.setLong(this, parsingTimeoutInMillis)
}

fun MdPageGeneratorMojo.setInputFileExtensions(inputFileExtensions: String) {
    val field = getAccessibleField("inputFileExtensions")

    field.set(this, inputFileExtensions)
}

fun MdPageGeneratorMojo.setApplyFiltering(applyFiltering: Boolean) {
    val field = getAccessibleField("applyFiltering")

    field.setBoolean(this, applyFiltering)
}

fun MdPageGeneratorMojo.setTimestampFormat(timestampFormat: String) {
    val field = getAccessibleField("timestampFormat")

    field.set(this, timestampFormat)
}

fun MdPageGeneratorMojo.setAttributes(attributes: Array<String>) {
    val field = getAccessibleField("attributes")

    field.set(this, attributes)
}

fun MdPageGeneratorMojo.setPegdownExtensions(pegdownExtensions: String) {
    val field = getAccessibleField("pegdownExtensions")

    field.set(this, pegdownExtensions)
}

fun getAccessibleField(fieldName: String): Field {
    val field = MdPageGeneratorMojo::class.java.getDeclaredField(fieldName)
    field.isAccessible = true

    return field
}