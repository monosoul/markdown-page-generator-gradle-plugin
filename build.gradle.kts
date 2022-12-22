import org.gradle.api.JavaVersion.VERSION_1_8
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "gradle.plugin.com.github.monosoul"

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
    implementation("com.ruleoftech:markdown-page-generator-plugin:2.4.0")
    implementation("org.apache.maven:maven-model-builder:3.8.6")
    testImplementation("org.spockframework:spock-core:2.3-groovy-3.0") {
        exclude("org.codehaus.groovy")
    }
    testImplementation(gradleTestKit())
}

val siteUrl = "https://github.com/monosoul/markdown-page-generator-gradle-plugin"
val githubUrl = "https://github.com/monosoul/markdown-page-generator-gradle-plugin"

val pluginName = "Markdown to HTML Page Generator Gradle Plugin"
val pluginDescription = "This plugins wraps the maven markdown-page-generator-plugin by " +
        "Marko Wallin (walokra) so it can be used in Gradle."

gradlePlugin {
    website.set(siteUrl)
    vcsUrl.set(githubUrl)

    plugins.create("mdPageGeneratorPlugin") {
        id = "com.github.monosoul.markdown.page.generator"
        implementationClass = "com.github.monosoul.markdown.page.generator.gradle.plugin.MdPageGeneratorPlugin"
        version = project.version

        displayName = pluginName
        description = pluginDescription
        tags.set(
            listOf(
                "markdown", "html", "header", "footer", "walokra", "com.ruleoftech", "markdown-page-generator-plugin"
            )
        )
    }
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                name.set(pluginName)
                description.set(pluginDescription)
                url.set(siteUrl)
                scm {
                    url.set(githubUrl)
                    connection.set("scm:git:$githubUrl.git")
                    developerConnection.set("scm:git:$githubUrl.git")
                }
                developers {
                    developer {
                        id.set("monosoul")
                        name.set("Andrei Nevedomskii")
                    }
                }
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                issueManagement {
                    url.set("$githubUrl/issues")
                }
            }
        }
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
