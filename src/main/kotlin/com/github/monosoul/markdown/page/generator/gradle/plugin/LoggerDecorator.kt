package com.github.monosoul.markdown.page.generator.gradle.plugin

import org.apache.maven.plugin.logging.Log
import org.gradle.api.logging.Logger

class LoggerDecorator(
    private val logger: Logger
) : Log {
    override fun isDebugEnabled() = logger.isDebugEnabled

    override fun debug(content: CharSequence?) = logger.debug(content?.toString())

    override fun debug(content: CharSequence?, error: Throwable?) = logger.debug(content?.toString(), error)

    override fun debug(error: Throwable?) = logger.debug(null, error)

    override fun isInfoEnabled(): Boolean = logger.isInfoEnabled

    override fun info(content: CharSequence?) = logger.info(content?.toString())

    override fun info(content: CharSequence?, error: Throwable?) = logger.info(content?.toString(), error)

    override fun info(error: Throwable?) = logger.info(null, error)

    override fun isWarnEnabled(): Boolean = logger.isWarnEnabled

    override fun warn(content: CharSequence?) = logger.warn(content?.toString())

    override fun warn(content: CharSequence?, error: Throwable?) = logger.warn(content?.toString(), error)

    override fun warn(error: Throwable?) = logger.warn(null, error)

    override fun isErrorEnabled(): Boolean = logger.isErrorEnabled

    override fun error(content: CharSequence?) = logger.error(content?.toString())

    override fun error(content: CharSequence?, error: Throwable?) = logger.error(content?.toString(), error)

    override fun error(error: Throwable?) = logger.error(null, error)
}