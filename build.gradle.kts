group = "com.github.monosoul"
version = "0.0.1"

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    implementation("com.ruleoftech:markdown-page-generator-plugin:2.1.0")
}

gradlePlugin {
    plugins {
        create("mdPageGeneratorPlugin") {
            id = "com.github.monosoul.markdown.page.generator"
            implementationClass = "com.github.monosoul.markdown.page.generator.gradle.plugin.MdPageGeneratorPlugin"
        }
    }
}