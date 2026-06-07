plugins {
    `java-library`
}

repositories {
    mavenCentral()
    mavenLocal()
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

dependencies {
    testImplementation(platform(
        "org.junit:junit-bom:5.10.0"
    ))
    testImplementation(
        "org.junit.jupiter:junit-jupiter"
    )
    testRuntimeOnly(
        "org.junit.platform:junit-platform-launcher"
    )
}
