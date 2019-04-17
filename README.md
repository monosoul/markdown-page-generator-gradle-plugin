# Markdown to HTML Page Generator Gradle Plugin
This Gradle plugin's goal is to provide a way of using [the maven markdown-page-generator-plugin by walokra](https://github.com/walokra/markdown-page-generator-plugin).

[![Build Status](https://travis-ci.com/monosoul/markdown-page-generator-gradle-plugin.svg?branch=master)](https://travis-ci.com/monosoul/markdown-page-generator-gradle-plugin)
![license](https://img.shields.io/github/license/monosoul/markdown-page-generator-gradle-plugin.svg)
 
[Gradle Plugin Portal page](https://plugins.gradle.org/plugin/com.github.monosoul.markdown.page.generator)

## Gradle compatibility table
| Plugin version | Gradle version |
|:----------------:|:--------------:|
| 2.1.0.1 | \>= 4.9 |
| 2.1.0 | \>= 5.0 |

## Getting Started
To apply the plugin simply add it to the plugins block of your build script:
```kotlin
plugins {
  id("com.github.monosoul.markdown.page.generator") version "2.1.0.1"
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
    classpath("gradle.plugin.com.github.monosoul:markdown-page-generator-gradle-plugin:2.1.0.1")
  }
}

apply(plugin = "com.github.monosoul.markdown.page.generator")
```

### Prerequisites
Just Gradle, nothing else is needed.

### Usage example
Using Kotlin DSL:
```kotlin
buildscript {
    dependencies {
        classpath("com.github.monosoul:markdown-page-generator-gradle-plugin:2.1.0.1")
    }

    repositories {
        jcenter()
    }
}

apply(plugin = "com.github.monosoul.markdown.page.generator")

tasks {

    val generateHtml = getByName("generateHtml", GenerateHtmlTask::class) {
        dependsOn(processResources)

        val encoding = "UTF-8"

        recursiveInput = true
        pegdownExtensions = "TABLES,FENCED_CODE_BLOCKS"
        inputEncoding = encoding
        outputEncoding = encoding

        val sourceDir = File(buildDir, "resources/main/markdown")
        val outputDir = File(buildDir, "html")

        inputDirectory = sourceDir
        outputDirectory = outputDir
        headerHtmlFile = File(sourceDir, "header.html")
        footerHtmlFile = File(sourceDir, "footer.html")
        transformRelativeMarkdownLinks = true
    }
}
```
This way execution of the task named `generateHtml` would also cause the task `processResources` execution so processed resources would 
be used for the html generation.

### Configuration options
You can find the detailed configuration options description in the [the maven markdown-page-generator-plugin's readme](https://github.com/walokra/markdown-page-generator-plugin/blob/master/Readme.md).

## Release History
* 2.1.0.1
    * Added compatibility with Gradle 4.10.x and 4.9 (fixes [issue #1](https://github.com/monosoul/markdown-page-generator-gradle-plugin/issues/1))
* 2.1.0
    * Initial release
    
## License
The software is licensed under the [Apache-2.0 License](LICENSE).