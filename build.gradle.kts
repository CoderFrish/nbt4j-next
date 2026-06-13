plugins {
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "signing")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
        mavenLocal()
    }

    group = "com.github.coderfrish"

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

    extensions.configure<SigningExtension> {
        useInMemoryPgpKeys(
            System.getenv("SECRET_KEY"),
            System.getenv("SECRET_PASSWORD"),
        )

        extensions.getByType<PublishingExtension>().publications
            .forEach(::sign) /* Sign Maven Jar */
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))

            username.set(System.getenv("MAVEN_USERNAME"))
            password.set(System.getenv("MAVEN_PASSWORD"))
        }
    }
}
