import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.github.monosoul"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.3.21"
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(gradleApi())
}
repositories {
    mavenCentral()
}
tasks {
    getByName("compileKotlin", KotlinCompile::class) {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    getByName("compileTestKotlin", KotlinCompile::class) {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}