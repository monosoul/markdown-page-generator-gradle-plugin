package com.github.monosoul.markdown.page.generator.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

import org.gradle.kotlin.dsl.*

class MdPageGeneratorPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {

        tasks {
            register("generateHtml", GenerateHtmlTask::class)
        }
    }
}