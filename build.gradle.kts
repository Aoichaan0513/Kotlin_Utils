import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.21"
    id("maven-publish")
}

group = "com.github.aoichaan0513"
version = "2.0.21_1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("joda-time", "joda-time", "2.13.0")
}

java {
    withSourcesJar()
    withJavadocJar()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()

val sourcesJar: Jar by tasks
val javadocJar: Jar by tasks
artifacts {
    archives(sourcesJar)
    archives(javadocJar)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["kotlin"])
                groupId = this@afterEvaluate.group.toString()
                artifactId = rootProject.name
                version = this@afterEvaluate.version.toString()
            }
        }
    }
}