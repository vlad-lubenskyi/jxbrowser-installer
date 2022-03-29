import org.panteleyev.jpackage.ImageType.*

plugins {
    java
    id("org.panteleyev.jpackageplugin") version "1.3.1"
    id("com.teamdev.jxbrowser.gradle") version "0.0.3"
}


group = "com.teamdev.examples"
version = "1.0"

repositories {
    mavenCentral()

}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}


jxbrowser {
    version = "7.24"
    includePreviewBuilds()
}

dependencies {
    implementation(jxbrowser.swing())
    implementation(jxbrowser.currentPlatform())
}

tasks {
    val jarDirectory = file("$buildDir/jars")
    jar {
        manifest {
            attributes["Main-Class"] = "com.teamdev.examples.PomodoroTracker"
        }
        archiveFileName.set("main.jar")
        destinationDirectory.set(file(jarDirectory))
    }

    register<Copy>("copyDependencies") {
        from(configurations.runtimeClasspath).into(jarDirectory)
    }

    jpackage {
        // Build and gather JAR files before packaging.
        dependsOn("jar", "copyDependencies")

        // The path to Java modules.
        val javaModules = "${System.getProperty("java.home")}/jmods"
        input = jarDirectory.absolutePath
        mainJar = "main.jar"
        addModules = listOf("java.base", "java.desktop", "java.logging")
        modulePaths = listOf(javaModules)
        destination = "$buildDir/dist"

        appName = "JxBrowser Example"
        appVersion = "${project.version}"

        linux {
            type = DEB
            icon = "jxbrowser-logo.png"
            linuxPackageName = "jxbrowser-example"
        }
        windows {
            type = MSI
            icon = "jxbrowser-logo.ico"
            winMenu = true
            winDirChooser = true
        }
        mac {
            type = DMG
            icon = "jxbrowser-logo.icns"
        }
    }
}
