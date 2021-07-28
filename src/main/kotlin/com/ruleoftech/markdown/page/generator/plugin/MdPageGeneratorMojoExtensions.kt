package com.ruleoftech.markdown.page.generator.plugin

import com.github.monosoul.markdown.page.generator.gradle.plugin.support.ReflectionAccessor
import com.github.monosoul.markdown.page.generator.gradle.plugin.support.ReflectionAccessor.reflectiveSet
import org.apache.maven.project.MavenProject
import org.apache.maven.shared.filtering.MavenResourcesFiltering
import java.io.File

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

var MdPageGeneratorMojo.outputFileExtension: String by ReflectionAccessor
var MdPageGeneratorMojo.applyFiltering: Boolean by ReflectionAccessor
var MdPageGeneratorMojo.timestampFormat: String by ReflectionAccessor
var MdPageGeneratorMojo.attributes: Array<String> by ReflectionAccessor
var MdPageGeneratorMojo.pegdownExtensions: String by ReflectionAccessor
var MdPageGeneratorMojo.flexmarkParserOptions: String by ReflectionAccessor
var MdPageGeneratorMojo.project: MavenProject by ReflectionAccessor
var MdPageGeneratorMojo.mavenResourcesFiltering: MavenResourcesFiltering by ReflectionAccessor
var MdPageGeneratorMojo.filteredOutputDirectory: File by ReflectionAccessor