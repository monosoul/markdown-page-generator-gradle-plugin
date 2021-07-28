package org.apache.maven.shared.filtering

import com.github.monosoul.markdown.page.generator.gradle.plugin.support.ReflectionAccessor
import org.sonatype.plexus.build.incremental.BuildContext

var DefaultMavenFileFilter.readerFilter: MavenReaderFilter by ReflectionAccessor
var DefaultMavenFileFilter.buildContext: BuildContext by ReflectionAccessor