package com.ruleoftech.markdown.page.generator.plugin

var MdPageGeneratorMojo.defaultTitle by delegate<String>()
var MdPageGeneratorMojo.alwaysUseDefaultTitle by delegate<Boolean>()
var MdPageGeneratorMojo.headerHtmlFile by delegate<String>()
var MdPageGeneratorMojo.footerHtmlFile by delegate<String>()
var MdPageGeneratorMojo.failIfFilesAreMissing by delegate<Boolean>()
var MdPageGeneratorMojo.recursiveInput by delegate<Boolean>()
var MdPageGeneratorMojo.transformRelativeMarkdownLinks by delegate<Boolean>()
var MdPageGeneratorMojo.inputEncoding by delegate<String>()
var MdPageGeneratorMojo.outputEncoding by delegate<String>()
var MdPageGeneratorMojo.parsingTimeoutInMillis by delegate<Long>()
fun MdPageGeneratorMojo.setInputFileExtensions(inputFileExtensions: String) {
    val field = getAccessibleField("inputFileExtensions")

    field.set(this, inputFileExtensions)
}

var MdPageGeneratorMojo.applyFiltering by delegate<Boolean>()
var MdPageGeneratorMojo.timestampFormat by delegate<String>()
var MdPageGeneratorMojo.attributes by delegate<Array<String>>()
var MdPageGeneratorMojo.pegdownExtensions by delegate<String>()