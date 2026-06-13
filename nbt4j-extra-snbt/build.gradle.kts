version = rootProject.providers.gradleProperty("snbt_version").get()

dependencies {
    implementation(project(":nbt4j-next-core"))
}

sonatypeUploader {
    pom = Action<MavenPom> {
        description = "A SNBT parser extra library for nbt4j-next"

        name = "nbt4j-extra-snbt"
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
