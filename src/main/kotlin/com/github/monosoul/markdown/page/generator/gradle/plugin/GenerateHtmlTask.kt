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
    @Optional
    var alwaysUseDefaultTitle: Boolean = false

    @InputDirectory
    var inputDirectory: File = File(project.projectDir, "/src/main/resources/markdown/")

    @OutputDirectory
    var outputDirectory: File = File(project.buildDir, "/html/")

    @Input
    @Optional
    var headerHtmlFile: String? = null

    @Input
    @Optional
    var footerHtmlFile: String? = null

    @Input
    @Optional
    var failIfFilesAreMissing: Boolean = true

    @Input
    @Optional
    var recursiveInput: Boolean = false

    @Input
    @Optional
    var transformRelativeMarkdownLinks: Boolean = false

    @Input
    @Optional
    var inputEncoding: String = defaultEncoding

    @Input
    @Optional
    var outputEncoding: String = defaultEncoding

    @Input
    @Optional
    var parsingTimeoutInMillis: Long? = null

    @Input
    @Optional
    var inputFileExtensions: String = "md"

    @Input
    @Optional
    var applyFiltering: Boolean = false

    @Input
    @Optional
    var timestampFormat: String = "yyyy-MM-dd\\'T\\'HH:mm:ss\\'Z\\'"

    @Input
    @Optional
    var attributes: Array<String> = arrayOf()

    @Input
    @Optional
    var pegdownExtensions: String = "TABLES"

    @TaskAction
    fun callMavenPlugin() {
        val pageGenMojo = MdPageGeneratorMojo()
        defaultTitle?.let {
            pageGenMojo.setDefaultTitle(it)
        }
        pageGenMojo.setAlwaysUseDefaultTitle(alwaysUseDefaultTitle)
        pageGenMojo.inputDirectory = inputDirectory.path
        pageGenMojo.outputDirectory = outputDirectory.path
        headerHtmlFile?.let {
            pageGenMojo.setHeaderHtmlFile(it)
        }
        footerHtmlFile?.let {
            pageGenMojo.setFooterHtmlFile(it)
        }
        pageGenMojo.setFailIfFilesAreMissing(failIfFilesAreMissing)
        pageGenMojo.setRecursiveInput(recursiveInput)
        pageGenMojo.setTransformRelativeMarkdownLinks(transformRelativeMarkdownLinks)
        pageGenMojo.setInputEncoding(inputEncoding)
        pageGenMojo.setOutputEncoding(outputEncoding)
        parsingTimeoutInMillis?.let {
            pageGenMojo.setParsingTimeoutInMillis(it)
        }
        pageGenMojo.setInputFileExtensions(inputFileExtensions)
        pageGenMojo.setApplyFiltering(applyFiltering)
        pageGenMojo.setTimestampFormat(timestampFormat)
        pageGenMojo.setAttributes(attributes)
        pageGenMojo.setPegdownExtensions(pegdownExtensions)

        pageGenMojo.execute()
    }
}