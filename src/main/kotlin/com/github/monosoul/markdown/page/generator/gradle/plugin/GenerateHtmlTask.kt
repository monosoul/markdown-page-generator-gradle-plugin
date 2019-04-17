package com.github.monosoul.markdown.page.generator.gradle.plugin

import com.ruleoftech.markdown.page.generator.plugin.*
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

open class GenerateHtmlTask : DefaultTask() {

    init {
        group = "documentation"
        description = "Generates html files out of markdown for ${project.name}."
    }

    private val defaultEncoding = "UTF-8"

    @Input
    @Optional
    var defaultTitle: String? = null

    @Input
    var alwaysUseDefaultTitle: Boolean = false

    @InputDirectory
    var inputDirectory: File = File(project.projectDir, "/src/main/resources/markdown/")

    @OutputDirectory
    var outputDirectory: File = File(project.buildDir, "/html/")

    @InputFile
    @Optional
    var headerHtmlFile: File? = null

    @InputFile
    @Optional
    var footerHtmlFile: File? = null

    @Input
    var failIfFilesAreMissing: Boolean = true

    @Input
    var recursiveInput: Boolean = false

    @Input
    var transformRelativeMarkdownLinks: Boolean = false

    @Input
    var inputEncoding: String = defaultEncoding

    @Input
    var outputEncoding: String = defaultEncoding

    @Input
    @Optional
    var parsingTimeoutInMillis: Long? = null

    @Input
    var inputFileExtensions: String = "md"

    @Input
    var applyFiltering: Boolean = false

    @Input
    var timestampFormat: String = "yyyy-MM-dd\\'T\\'HH:mm:ss\\'Z\\'"

    @Input
    var attributes: Array<String> = arrayOf()

    @Input
    var pegdownExtensions: String = "TABLES"

    @TaskAction
    fun callMavenPlugin() {
        val pageGenMojo = MdPageGeneratorMojo()
        defaultTitle?.let {
            pageGenMojo.defaultTitle = it
        }
        pageGenMojo.alwaysUseDefaultTitle = alwaysUseDefaultTitle
        pageGenMojo.inputDirectory = inputDirectory.path
        pageGenMojo.outputDirectory = outputDirectory.path
        headerHtmlFile?.let {
            pageGenMojo.headerHtmlFile = it.path
        }
        footerHtmlFile?.let {
            pageGenMojo.footerHtmlFile = it.path
        }
        pageGenMojo.failIfFilesAreMissing = failIfFilesAreMissing
        pageGenMojo.recursiveInput = recursiveInput
        pageGenMojo.transformRelativeMarkdownLinks = transformRelativeMarkdownLinks
        pageGenMojo.inputEncoding = inputEncoding
        pageGenMojo.outputEncoding = outputEncoding
        parsingTimeoutInMillis?.let {
            pageGenMojo.parsingTimeoutInMillis = it
        }
        pageGenMojo.setInputFileExtensions(inputFileExtensions)
        pageGenMojo.applyFiltering = applyFiltering
        pageGenMojo.timestampFormat = timestampFormat
        pageGenMojo.attributes = attributes
        pageGenMojo.pegdownExtensions = pegdownExtensions

        pageGenMojo.execute()
    }
}