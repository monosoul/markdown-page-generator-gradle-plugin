package com.github.monosoul.markdown.page.generator.gradle.plugin

import com.ruleoftech.markdown.page.generator.plugin.MdPageGeneratorMojo
import com.ruleoftech.markdown.page.generator.plugin.alwaysUseDefaultTitle
import com.ruleoftech.markdown.page.generator.plugin.applyFiltering
import com.ruleoftech.markdown.page.generator.plugin.attributes
import com.ruleoftech.markdown.page.generator.plugin.defaultTitle
import com.ruleoftech.markdown.page.generator.plugin.failIfFilesAreMissing
import com.ruleoftech.markdown.page.generator.plugin.flexmarkParserOptions
import com.ruleoftech.markdown.page.generator.plugin.footerHtmlFile
import com.ruleoftech.markdown.page.generator.plugin.headerHtmlFile
import com.ruleoftech.markdown.page.generator.plugin.inputEncoding
import com.ruleoftech.markdown.page.generator.plugin.outputEncoding
import com.ruleoftech.markdown.page.generator.plugin.outputFileExtension
import com.ruleoftech.markdown.page.generator.plugin.parsingTimeoutInMillis
import com.ruleoftech.markdown.page.generator.plugin.pegdownExtensions
import com.ruleoftech.markdown.page.generator.plugin.recursiveInput
import com.ruleoftech.markdown.page.generator.plugin.setInputFileExtensions
import com.ruleoftech.markdown.page.generator.plugin.timestampFormat
import com.ruleoftech.markdown.page.generator.plugin.transformRelativeMarkdownLinks
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.listProperty
import org.gradle.kotlin.dsl.property
import java.io.File
import javax.inject.Inject

open class GenerateHtmlTask @Inject constructor(
    objectFactory: ObjectFactory,
    providerFactory: ProviderFactory
) : DefaultTask() {

    init {
        group = "documentation"
        description = "Generates html files out of markdown for ${project.name}."
    }

    private val defaultEncoding = "UTF-8"

    @Input
    @Optional
    val defaultTitle: Property<String> = objectFactory.property()

    @Input
    val alwaysUseDefaultTitle: Property<Boolean> = objectFactory.property<Boolean>().convention(false)

    @InputDirectory
    val inputDirectory: DirectoryProperty = objectFactory.directoryProperty().convention(
        providerFactory.provider { project.layout.projectDirectory.dir("/src/main/resources/markdown/") }
    )

    @OutputDirectory
    val outputDirectory: DirectoryProperty = objectFactory.directoryProperty().convention(
        project.layout.buildDirectory.dir("/html/")
    )

    @InputFile
    @Optional
    val headerHtmlFile: Property<File> = objectFactory.property()

    @InputFile
    @Optional
    val footerHtmlFile: Property<File> = objectFactory.property()

    @Input
    val failIfFilesAreMissing: Property<Boolean> = objectFactory.property<Boolean>().convention(true)

    @Input
    val recursiveInput: Property<Boolean> = objectFactory.property<Boolean>().convention(false)

    @Input
    val transformRelativeMarkdownLinks: Property<Boolean> = objectFactory.property<Boolean>().convention(false)

    @Input
    val inputEncoding: Property<String> = objectFactory.property<String>().convention(defaultEncoding)

    @Input
    val outputEncoding: Property<String> = objectFactory.property<String>().convention(defaultEncoding)

    @Input
    @Optional
    val parsingTimeoutInMillis: Property<Long> = objectFactory.property()

    @Input
    val inputFileExtensions: Property<String> = objectFactory.property<String>().convention("md")

    @Input
    val outputFileExtension: Property<String> = objectFactory.property<String>().convention("html")

    @Input
    val applyFiltering: Property<Boolean> = objectFactory.property<Boolean>().convention(false)

    @Input
    val timestampFormat: Property<String> = objectFactory.property<String>()
        .convention("yyyy-MM-dd\\'T\\'HH:mm:ss\\'Z\\'")

    @Input
    val attributes: ListProperty<String> = objectFactory.listProperty<String>().convention(emptyList())

    @Input
    val pegdownExtensions: Property<String> = objectFactory.property<String>().convention("TABLES")

    @Input
    val flexmarkParserOptions: Property<String> = objectFactory.property<String>()
        .convention("LISTS_ORDERED_LIST_MANUAL_START")

    @TaskAction
    fun callMavenPlugin() {
        val pageGenMojo = MdPageGeneratorMojo()
        pageGenMojo.log = LoggerAdapter(logger)
        defaultTitle.orNull?.let {
            pageGenMojo.defaultTitle = it
        }
        pageGenMojo.alwaysUseDefaultTitle = alwaysUseDefaultTitle.get()
        pageGenMojo.inputDirectory = inputDirectory.asFile.get().path
        pageGenMojo.outputDirectory = outputDirectory.asFile.get().path
        headerHtmlFile.orNull?.let {
            pageGenMojo.headerHtmlFile = it.path
        }
        footerHtmlFile.orNull?.let {
            pageGenMojo.footerHtmlFile = it.path
        }
        pageGenMojo.failIfFilesAreMissing = failIfFilesAreMissing.get()
        pageGenMojo.recursiveInput = recursiveInput.get()
        pageGenMojo.transformRelativeMarkdownLinks = transformRelativeMarkdownLinks.get()
        pageGenMojo.inputEncoding = inputEncoding.get()
        pageGenMojo.outputEncoding = outputEncoding.get()
        parsingTimeoutInMillis.orNull?.let {
            pageGenMojo.parsingTimeoutInMillis = it
        }
        pageGenMojo.setInputFileExtensions(inputFileExtensions.get())
        pageGenMojo.applyFiltering = applyFiltering.get()
        pageGenMojo.outputFileExtension = outputFileExtension.get()
        pageGenMojo.timestampFormat = timestampFormat.get()
        pageGenMojo.attributes = attributes.get().toTypedArray()
        pageGenMojo.pegdownExtensions = pegdownExtensions.get()
        pageGenMojo.flexmarkParserOptions = flexmarkParserOptions.get()

        pageGenMojo.execute()
    }
}