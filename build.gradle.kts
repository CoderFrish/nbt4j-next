plugins {
    `java-library`
    signing
    `maven-publish`
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    java {
        withJavadocJar()
        withSourcesJar()
    }

    test {
        useJUnitPlatform()
    }
}

val githubPath = "CoderFrish/nbt4j-next"
val github = "https://github.com"
val githubRaw = "https://raw.githubusercontent.com"

val licensePath = "refs/heads/master/LICENSE.md"

publishing {
    repositories {
        mavenCentral() {
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                description = "A Named Binary Tag Library."

                name = project.name
                url = "$github/$githubPath"

                licenses {
                    license {
                        name = "MIT"
                        url.set(
                            "$githubRaw/$githubPath/$licensePath"
                        )
                    }
                }

                developers {
                    developer {
                        id = "coderfrish"
                        name = "Frish2021"
                        email = "1573880184@qq.com"
                        url = "https://coderfrish.github.io/"
                    }
                }

                scm {
                    connection = "scm:git:$github/$githubPath.git"
                    url = "$github/$githubPath.git"
                }
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getenv("SECRET_KEY"),
        System.getenv("SECRET_PASSWORD"),
    )
    sign(publishing.publications["mavenJava"])
}

