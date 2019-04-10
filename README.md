# Markdown to HTML Page Generator Gradle Plugin
This Gradle plugin's goal is to provide a way of using [the maven markdown-page-generator-plugin
 by walokra](https://github.com/walokra/markdown-page-generator-plugin).

Usage example (Kotlin DSL):
```kotlin
buildscript {
    dependencies {
        classpath("com.github.monosoul:markdown-page-generator-gradle-plugin:2.1.0")
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
        val outputDir by extra { File(buildDir, "html") }

        inputDirectory = sourceDir
        outputDirectory = outputDir
        headerHtmlFile = File(sourceDir, "header.html")
        footerHtmlFile = File(sourceDir, "footer.html")
        transformRelativeMarkdownLinks = true
    }
}
```