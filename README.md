# Markdown to HTML Page Generator Gradle Plugin
This Gradle plugin's goal is to provide a way of using [the maven markdown-page-generator-plugin by Marko Wallin (walokra)](https://github.com/walokra/markdown-page-generator-plugin).

[![Build Status](https://github.com/monosoul/markdown-page-generator-gradle-plugin/actions/workflows/build-on-push-to-master.yml/badge.svg?branch=master)](https://github.com/monosoul/markdown-page-generator-gradle-plugin/actions/workflows/build-on-push-to-master.yml?query=branch%3Amaster)
[![GitHub Release](https://img.shields.io/github/release/monosoul/markdown-page-generator-gradle-plugin.svg?label=GitHub%20Release)](https://github.com/monosoul/markdown-page-generator-gradle-plugin/releases)
[![Gradle Plugins Release](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com/github/monosoul/markdown/page/generator/com.github.monosoul.markdown.page.generator.gradle.plugin/maven-metadata.xml.svg?label=Gradle%20Plugin%20Portal)](https://plugins.gradle.org/plugin/com.github.monosoul.markdown.page.generator)
![license](https://img.shields.io/github/license/monosoul/markdown-page-generator-gradle-plugin.svg)
 
[Gradle Plugin Portal page](https://plugins.gradle.org/plugin/com.github.monosoul.markdown.page.generator)

## Gradle compatibility table
| Plugin version | Gradle version |
|:--------------:|:--------------:|
|  \>= 2.3.1.0   | \>= 6.8.3 |
|    2.3.0.0     | \>= 4.9 |
|    2.1.0.1     | \>= 4.9 |
|     2.1.0      | \>= 5.0 |

## Getting Started
To apply the plugin simply add it to the plugins block of your build script:
```kotlin
plugins {
  id("com.github.monosoul.markdown.page.generator") version "2.4.0.0"
}
```

Or using legacy plugin application method:
```kotlin
buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("gradle.plugin.com.github.monosoul:markdown-page-generator-gradle-plugin:2.4.0.0")
  }
}

apply(plugin = "com.github.monosoul.markdown.page.generator")
```

### Prerequisites
Just Gradle, nothing else is needed.

### Usage example
Using Kotlin DSL:
```kotlin
plugins {
  id("com.github.monosoul.markdown.page.generator") version "2.4.0.0"
}

tasks {

    named<GenerateHtmlTask>("generateHtml") {
        dependsOn(processResources)

        val encoding = "UTF-8"

        recursiveInput.set(true)
        pegdownExtensions.set("TABLES,FENCED_CODE_BLOCKS")
        inputEncoding.set(encoding)
        outputEncoding.set(encoding)

        val sourceDir = project.layout.buildDirectory.dir("resources/main/markdown")
        val outputDir = project.layout.buildDirectory.dir("html")

        inputDirectory.set(sourceDir)
        outputDirectory.set(outputDir)
        headerHtmlFile.set(sourceDir.file("header.html"))
        footerHtmlFile.set(sourceDir.file("footer.html"))
        transformRelativeMarkdownLinks.set(true)
    }
}
```
This way execution of the task named `generateHtml` would also cause the task `processResources` execution so processed resources would 
be used for the html generation.

### Configuration options
You can find the detailed configuration options description in the [the maven markdown-page-generator-plugin's readme](https://github.com/walokra/markdown-page-generator-plugin/blob/master/Readme.md).
    
## License
The software is licensed under the [Apache-2.0 License](LICENSE).
