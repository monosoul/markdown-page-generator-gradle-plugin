import org.gradle.api.JavaVersion.VERSION_1_8
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.github.monosoul"
version = "2.3.0.0"

plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "0.15.0"
    groovy
}

java {
    sourceCompatibility = VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.ruleoftech:markdown-page-generator-plugin:2.3.1")
    testImplementation("org.spockframework:spock-core:2.0-groovy-3.0") {
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
            description =
                "This plugins wraps the maven markdown-page-generator-plugin by walokra so it can be used in Gradle."
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
    withType<Test> {
        useJUnitPlatform()

        testLogging {
            events = setOf(PASSED, SKIPPED, FAILED)
            exceptionFormat = FULL
        }
    }

    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = VERSION_1_8.majorVersion
        }
    }
}