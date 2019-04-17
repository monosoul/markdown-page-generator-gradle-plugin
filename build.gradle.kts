import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

group = "com.github.monosoul"
version = "2.1.0"

plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "0.10.1"
    groovy
}

repositories {
    jcenter()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    implementation("com.ruleoftech:markdown-page-generator-plugin:2.1.0")
    testImplementation("org.spockframework", "spock-core", "1.3-groovy-2.5") {
        exclude("org.codehaus.groovy")
    }
    testImplementation(gradleTestKit())
}

gradlePlugin {
    plugins {
        create("mdPageGeneratorPlugin") {
            id = "com.github.monosoul.markdown.page.generator"
            implementationClass = "com.github.monosoul.markdown.page.generator.gradle.plugin.MdPageGeneratorPlugin"
        }
    }
}

pluginBundle {
    (plugins) {
        "mdPageGeneratorPlugin" {
            displayName = "Markdown to HTML Page Generator Gradle Plugin"
            description = "This plugins wraps the maven markdown-page-generator-plugin by walokra so it can be used in Gradle."
            tags = listOf("markdown", "html", "header", "footer", "walokra")
            version = project.version as String

            website = "https://github.com/monosoul/markdown-page-generator-gradle-plugin"
            vcsUrl = "https://github.com/monosoul/markdown-page-generator-gradle-plugin"
        }
    }

    mavenCoordinates {
        artifactId = project.name
    }
}

tasks {
    withType(Test::class) {
        useJUnit()

        testLogging {
            events = setOf(PASSED, SKIPPED, FAILED)
            exceptionFormat = FULL
        }
    }
}