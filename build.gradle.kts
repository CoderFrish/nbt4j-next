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

    extensions.configure<PublishingExtension> {
        repositories {
            maven("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/") {
                credentials {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }
            }
        }
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
