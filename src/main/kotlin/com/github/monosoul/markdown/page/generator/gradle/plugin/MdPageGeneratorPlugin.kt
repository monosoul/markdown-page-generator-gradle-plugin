package com.github.monosoul.markdown.page.generator.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class MdPageGeneratorPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit {
        project.tasks.register<GenerateHtmlTask>("generateHtml")
    }
}
