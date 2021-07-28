package org.codehaus.plexus.logging

import com.github.monosoul.markdown.page.generator.gradle.plugin.support.ReflectionAccessor.reflectiveSet

fun AbstractLogEnabled.setLogger(logger: Logger) = reflectiveSet("logger", logger)