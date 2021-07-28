package org.codehaus.plexus.logging

import org.gradle.api.logging.Logger
import org.codehaus.plexus.logging.Logger as PlexusLogger

class PlexusLoggerAdapter(
    private val logger: Logger,
) : AbstractLogger(logger.threshold, logger.name) {

    override fun debug(message: String?, throwable: Throwable?) {
        logger.debug(message, throwable)
    }

    override fun info(message: String?, throwable: Throwable?) {
        logger.info(message, throwable)
    }

    override fun warn(message: String?, throwable: Throwable?) {
        logger.warn(message, throwable)
    }

    override fun error(message: String?, throwable: Throwable?) {
        logger.error(message, throwable)
    }

    override fun fatalError(message: String?, throwable: Throwable?) {
        logger.error(message, throwable)
    }

    override fun getChildLogger(name: String?): PlexusLogger = this

    private companion object {
        val Logger.threshold: Int
            get() = when {
                isTraceEnabled -> 0
                isDebugEnabled -> 0
                isInfoEnabled -> 1
                isWarnEnabled -> 2
                isErrorEnabled -> 4
                else -> 5
            }
    }
}