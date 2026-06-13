dependencies {
    implementation(project(":nbt4j-next-core"))
    implementation(project(":nbt4j-extra-snbt"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
