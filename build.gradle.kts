plugins {
    kotlin("jvm") version "1.9.24"
    `maven-publish`
    `java-library`
    id("org.jetbrains.dokka") version "1.9.20"
}

group = "io.github.ttkalcevic"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    // Testing
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "human-readable-duration"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("Human Readable Duration")
                description.set("A Kotlin library for converting durations to human-readable formats")
                url.set("https://github.com/ttkalcevic/human-readable-duration-kotlin")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("ttkalcevic")
                        name.set("Tomislav Kalčević")
                        email.set("ttkalcevic@users.noreply.github.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/ttkalcevic/human-readable-duration-kotlin.git")
                    developerConnection.set("scm:git:ssh://github.com:ttkalcevic/human-readable-duration-kotlin.git")
                    url.set("https://github.com/ttkalcevic/human-readable-duration-kotlin/tree/main")
                }
            }
        }
    }
}