package com.ruleoftech.markdown.page.generator.plugin

var MdPageGeneratorMojo.defaultTitle: String? by nullableStringDelegate
var MdPageGeneratorMojo.alwaysUseDefaultTitle: Boolean by booleanDelegate
var MdPageGeneratorMojo.headerHtmlFile: String? by nullableStringDelegate
var MdPageGeneratorMojo.footerHtmlFile: String? by nullableStringDelegate
var MdPageGeneratorMojo.failIfFilesAreMissing: Boolean by booleanDelegate
var MdPageGeneratorMojo.recursiveInput: Boolean by booleanDelegate
var MdPageGeneratorMojo.transformRelativeMarkdownLinks: Boolean by booleanDelegate
var MdPageGeneratorMojo.inputEncoding: String by stringDelegate
var MdPageGeneratorMojo.outputEncoding: String by stringDelegate
var MdPageGeneratorMojo.parsingTimeoutInMillis: Long by longDelegate
fun MdPageGeneratorMojo.setInputFileExtensions(inputFileExtensions: String) {
    val field = getAccessibleField("inputFileExtensions")

    field.set(this, inputFileExtensions)
}

var MdPageGeneratorMojo.applyFiltering: Boolean by booleanDelegate
var MdPageGeneratorMojo.timestampFormat: String by stringDelegate
var MdPageGeneratorMojo.attributes: Array<String> by stringArrayDelegate
var MdPageGeneratorMojo.pegdownExtensions: String by stringDelegate