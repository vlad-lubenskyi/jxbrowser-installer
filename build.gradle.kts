/*
 * Copyright 2000-2025 TeamDev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.panteleyev.jpackage.ImageType.*

plugins {
    java
    id("com.gradleup.shadow") version "9.0.2"
    id("com.teamdev.jxbrowser") version "2.0.0"
    id("org.panteleyev.jpackageplugin") version "1.7.3"
}

group = "com.teamdev.examples"
version = "1.0"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

jxbrowser {
    version = "8.11.0"
}

dependencies {
    implementation(jxbrowser.swing)
    implementation(jxbrowser.currentPlatform)
}

tasks {
    val jarName = "main.jar"
    shadowJar {
        manifest {
            attributes["Main-Class"] = "com.teamdev.examples.PomodoroTracker"
        }
        archiveFileName.set(jarName)
    }

    val fatJarLocation by lazy {
        shadowJar.get().destinationDirectory
    }

    jpackage {
        // Build and gather JAR files before packaging.
        dependsOn(shadowJar)

        // The path to Java modules.
        input = fatJarLocation
        mainJar = jarName
        addModules = listOf("java.base", "java.desktop", "java.logging")
        destination = project.layout.buildDirectory.dir("dist")

        appName = "Pomodoro tracker"
        appVersion = "${project.version}"

        linux {
            type = DEB
            icon = projectDir.resolve("app-logo.png")
            linuxPackageName = "pomodoro-tracker"
        }
        windows {
            type = MSI
            icon = projectDir.resolve("app-logo.ico")
            winMenu = true
            winDirChooser = true
        }
        mac {
            type = DMG
            icon = projectDir.resolve("app-logo.icns")
        }
    }
}
