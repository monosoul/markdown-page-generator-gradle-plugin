package com.github.monosoul.markdown.page.generator.gradle.plugin.support

import org.apache.maven.shared.filtering.DefaultMavenFileFilter
import org.apache.maven.shared.filtering.DefaultMavenReaderFilter
import org.apache.maven.shared.filtering.DefaultMavenResourcesFiltering
import org.apache.maven.shared.filtering.buildContext
import org.apache.maven.shared.filtering.mavenFileFilter
import org.apache.maven.shared.filtering.readerFilter
import org.codehaus.plexus.logging.PlexusLoggerAdapter
import org.codehaus.plexus.logging.setLogger
import org.gradle.api.Task
import org.sonatype.plexus.build.incremental.DefaultBuildContext

fun Task.buildMavenResourcesFiltering() = DefaultMavenResourcesFiltering().apply {
    val plexusLoggerAdapter = PlexusLoggerAdapter(logger)
    val defaultBuildContext = DefaultBuildContext().apply {
        setLogger(plexusLoggerAdapter)
    }

    buildContext = defaultBuildContext
    mavenFileFilter = DefaultMavenFileFilter().apply {
        buildContext = defaultBuildContext
        readerFilter = DefaultMavenReaderFilter().apply {
            setLogger(plexusLoggerAdapter)
        }
        setLogger(plexusLoggerAdapter)
    }
    setLogger(plexusLoggerAdapter)

    initialize()
}