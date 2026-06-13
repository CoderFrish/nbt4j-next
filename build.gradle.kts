plugins {
    id("io.github.jeadyx.sonatype-uploader") version "2.8" apply false
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "signing")
    apply(plugin = "io.github.jeadyx.sonatype-uploader")

    repositories {
        mavenCentral()
        mavenLocal()
    }

    group = "io.github.coderfrish"

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        withType<Test> {
            useJUnitPlatform()
        }
    }

    extensions.configure<JavaPluginExtension> {
        withJavadocJar()
        withSourcesJar()
    }
}
