import org.gradle.api.JavaVersion.VERSION_1_8
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.github.monosoul"
version = "2.3.1.1"

plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "1.1.0"
    groovy
}

val targetJava = VERSION_1_8
java {
    sourceCompatibility = targetJava
    targetCompatibility = targetJava
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.ruleoftech:markdown-page-generator-plugin:2.3.1")
    implementation("org.apache.maven:maven-model-builder:3.8.6")
    testImplementation("org.spockframework:spock-core:2.3-groovy-3.0") {
        exclude("org.codehaus.groovy")
    }
    testImplementation(gradleTestKit())
}

gradlePlugin {
    website.set("https://github.com/monosoul/markdown-page-generator-gradle-plugin")
    vcsUrl.set("https://github.com/monosoul/markdown-page-generator-gradle-plugin")

    plugins.create("mdPageGeneratorPlugin") {
        id = "com.github.monosoul.markdown.page.generator"
        implementationClass = "com.github.monosoul.markdown.page.generator.gradle.plugin.MdPageGeneratorPlugin"
        version = project.version

        displayName = "Markdown to HTML Page Generator Gradle Plugin"
        description = "This plugins wraps the maven markdown-page-generator-plugin by " +
                "Marko Wallin (walokra) so it can be used in Gradle."
        tags.set(
            listOf(
                "markdown",
                "html",
                "header",
                "footer",
                "walokra",
                "com.ruleoftech",
                "markdown-page-generator-plugin"
            )
        )
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

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "$targetJava"
        }
    }
}
