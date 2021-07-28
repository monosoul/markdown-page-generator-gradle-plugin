package com.github.monosoul.markdown.page.generator.gradle.plugin.support

import org.apache.maven.project.MavenProject
import org.gradle.api.Task

fun Task.buildMavenProject(): MavenProject = MavenProject().apply {
    groupId = project.group.toString()
    artifactId = project.name
    version = project.version.toString()
}