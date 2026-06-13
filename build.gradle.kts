import io.github.jeadyx.UploaderSigning

plugins {
    `java-library`
    id("io.github.jeadyx.sonatype-uploader") version "2.8"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


repositories {
    mavenCentral()
    mavenLocal()
}

group = "io.github.coderfrish"

java {
    withJavadocJar()
    withSourcesJar()
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

sonatypeUploader {
    tokenName = "KyPN5G"
    tokenPasswd = "xMe5lsjc5HCrwwUvdcLoljA77p8L7dUry"

    signing = Action<UploaderSigning> {
        keyId="6BA2164F"
        keyPasswd="20100422"
        secretKeyPath="D:\\nbt4j-next\\build\\CoderFrish_0xF06D5C7D6BA2164F_SECRET.gpg"
    }

    pom = Action<MavenPom> {
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
