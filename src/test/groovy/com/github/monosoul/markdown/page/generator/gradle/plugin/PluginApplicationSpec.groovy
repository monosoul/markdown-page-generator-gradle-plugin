package com.github.monosoul.markdown.page.generator.gradle.plugin


import org.gradle.testkit.runner.GradleRunner
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.TempDir
import spock.lang.Unroll

import static org.apache.commons.io.FileUtils.copyDirectory
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

@Unroll
class PluginApplicationSpec extends Specification {

	@Shared
	def gradleVersions = [
			'7.1.1',
			'6.8.3'
	]
	@Shared
	def exampleFileName = 'example'

	@TempDir
	File testProjectDir

	def "should work with kotlin dsl and Gradle #gradleVersion"() {
		setup:
			def buildFile = new File(testProjectDir, 'build.gradle.kts')
			new File(testProjectDir, 'settings.gradle.kts') << "rootProject.name = \"markdownGeneratorTest\""
			buildFile << """
            import com.github.monosoul.markdown.page.generator.gradle.plugin.GenerateHtmlTask

            plugins {
                java
                id("com.github.monosoul.markdown.page.generator")
            }
            
            tasks {
                "generateHtml"(GenerateHtmlTask::class) {
                    pegdownExtensions = \"\"\"
                        TABLES,
                        FENCED_CODE_BLOCKS,
                        STRIKETHROUGH,
                        SMARTYPANTS,
                        SMARTS,
                        AUTOLINKS
                        \"\"\".trimIndent()
                }
            }
            """
			copyResources()
		when:
			def result = GradleRunner.create()
					.withProjectDir(testProjectDir)
					.withGradleVersion(gradleVersion)
					.withArguments('generateHtml', '--stacktrace')
					.withPluginClasspath()
					.build()
		then:
			result.task(':generateHtml').outcome == SUCCESS
		and:
			def expectedHtml = new File(getClass().getResource("/html/${exampleFileName}.html").toURI()).text
			def actualHtml = new File(testProjectDir, "/build/html/${exampleFileName}.html").text
			actualHtml == expectedHtml
		where:
			gradleVersion << gradleVersions
	}

	def "should work with groovy dsl and Gradle #gradleVersion"() {
		setup:
			def buildFile = new File(testProjectDir, 'build.gradle')
		new File(testProjectDir, 'settings.gradle') << "rootProject.name = \"markdownGeneratorTest\""
			buildFile << """
            import com.github.monosoul.markdown.page.generator.gradle.plugin.GenerateHtmlTask

            plugins {
                id 'java'
                id 'com.github.monosoul.markdown.page.generator'
            }

			def task = tasks.getByName('generateHtml')
			task.pegdownExtensions = \"\"\"\
                    TABLES,
                    FENCED_CODE_BLOCKS,
                    STRIKETHROUGH,
                    SMARTYPANTS,
                    SMARTS,
                    AUTOLINKS\"\"\".stripIndent()
            """
			copyResources()
		when:
			def result = GradleRunner.create()
					.withProjectDir(testProjectDir)
					.withGradleVersion(gradleVersion)
					.withArguments('generateHtml', '--stacktrace')
					.withPluginClasspath()
					.build()
		then:
			result.task(':generateHtml').outcome == SUCCESS
		and:
			def expectedHtml = new File(getClass().getResource("/html/${exampleFileName}.html").toURI()).text
			def actualHtml = new File(testProjectDir, "/build/html/${exampleFileName}.html").text
			actualHtml == expectedHtml
		where:
			gradleVersion << gradleVersions
	}

	def copyResources() {
		def markdown = new File(getClass().getResource('/markdown').toURI())
		def target = new File(testProjectDir, 'src/main/resources/markdown')
		copyDirectory(markdown, target)
	}
}
