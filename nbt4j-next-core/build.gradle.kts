version = rootProject.providers.gradleProperty("core_version").get()

publishing {
    publications {
        create<MavenPublication>("mavenCore") {
            version = project.version as String
            from(components["java"])

            pom {
                description = "A Named Binary Tag Library."

                name = "nbt4j-next"
                url = "https://github.com/CoderFrish/nbt4j-next"

                licenses {
                    license {
                        name = "MIT"
                        url.set(
                            "https://raw.githubusercontent.com/CoderFrish/nbt4j-next/refs/heads/master/LICENSE.md"
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
                    connection = "scm:git:https://github.com/CoderFrish/nbt4j-next.git"
                    url = "https://github.com/CoderFrish/nbt4j-next.git"
                }
            }
        }
    }
}
