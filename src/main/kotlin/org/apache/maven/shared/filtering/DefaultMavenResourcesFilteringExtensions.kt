package org.apache.maven.shared.filtering

import com.github.monosoul.markdown.page.generator.gradle.plugin.support.ReflectionAccessor
import org.sonatype.plexus.build.incremental.BuildContext

var DefaultMavenResourcesFiltering.buildContext: BuildContext by ReflectionAccessor
var DefaultMavenResourcesFiltering.mavenFileFilter: MavenFileFilter by ReflectionAccessor