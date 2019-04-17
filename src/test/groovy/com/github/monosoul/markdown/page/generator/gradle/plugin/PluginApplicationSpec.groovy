package com.github.monosoul.markdown.page.generator.gradle.plugin


import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static org.apache.commons.io.FileUtils.copyDirectory
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

@Unroll
class PluginApplicationSpec extends Specification {

	@Shared
	def gradleVersions = [
			'5.3.1',
			'5.2.1',
			'5.1.1',
			'5.0',
			'4.10.3',
			'4.9'
	]
	@Shared
	def exampleFileName = 'example'
	@Rule
	final TemporaryFolder testProjectDir = new TemporaryFolder()

	def "should work with kotlin dsl and Gradle #gradleVersion"() {
		setup:
			def buildFile = testProjectDir.newFile('build.gradle.kts')
			testProjectDir.newFile('settings.gradle.kts') << "rootProject.name = \"markdownGeneratorTest\""
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
					.withProjectDir(testProjectDir.root)
					.withGradleVersion(gradleVersion)
					.withArguments('generateHtml', '--stacktrace')
					.withPluginClasspath()
					.build()
		then:
			result.task(':generateHtml').outcome == SUCCESS
		and:
			def expectedHtml = new File(getClass().getResource("/html/${exampleFileName}.html").toURI()).text
			def actualHtml = new File(testProjectDir.root, "/build/html/${exampleFileName}.html").text
			actualHtml == expectedHtml
		where:
			gradleVersion << gradleVersions
	}

	def "should work with groovy dsl and Gradle #gradleVersion"() {
		setup:
			def buildFile = testProjectDir.newFile('build.gradle')
			testProjectDir.newFile('settings.gradle') << "rootProject.name = \"markdownGeneratorTest\""
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
					.withProjectDir(testProjectDir.root)
					.withGradleVersion(gradleVersion)
					.withArguments('generateHtml', '--stacktrace')
					.withPluginClasspath()
					.build()
		then:
			result.task(':generateHtml').outcome == SUCCESS
		and:
			def expectedHtml = new File(getClass().getResource("/html/${exampleFileName}.html").toURI()).text
			def actualHtml = new File(testProjectDir.root, "/build/html/${exampleFileName}.html").text
			actualHtml == expectedHtml
		where:
			gradleVersion << gradleVersions
	}

	def copyResources() {
		def markdown = new File(getClass().getResource('/markdown').toURI())
		def target = testProjectDir.newFolder('src', 'main', 'resources', 'markdown')
		copyDirectory(markdown, target)
	}
}
