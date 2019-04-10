package com.ruleoftech.markdown.page.generator.plugin

var MdPageGeneratorMojo.defaultTitle by reflection<String>()
var MdPageGeneratorMojo.alwaysUseDefaultTitle by reflection<Boolean>()
var MdPageGeneratorMojo.headerHtmlFile by reflection<String>()
var MdPageGeneratorMojo.footerHtmlFile by reflection<String>()
var MdPageGeneratorMojo.failIfFilesAreMissing by reflection<Boolean>()
var MdPageGeneratorMojo.recursiveInput by reflection<Boolean>()
var MdPageGeneratorMojo.transformRelativeMarkdownLinks by reflection<Boolean>()
var MdPageGeneratorMojo.inputEncoding by reflection<String>()
var MdPageGeneratorMojo.outputEncoding by reflection<String>()
var MdPageGeneratorMojo.parsingTimeoutInMillis by reflection<Long>()
fun MdPageGeneratorMojo.setInputFileExtensions(inputFileExtensions: String) {
    val field = getAccessibleField("inputFileExtensions")

    field.set(this, inputFileExtensions)
}

var MdPageGeneratorMojo.applyFiltering by reflection<Boolean>()
var MdPageGeneratorMojo.timestampFormat by reflection<String>()
var MdPageGeneratorMojo.attributes by reflection<Array<String>>()
var MdPageGeneratorMojo.pegdownExtensions by reflection<String>()