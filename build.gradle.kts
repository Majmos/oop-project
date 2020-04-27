/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.1.1/userguide/tutorial_java_projects.html
 */

val umlDoclet by configurations.creating

tasks.register("configureJavadoc") {
    doLast {
        tasks.javadoc {
            options.docletpath = umlDoclet.files.toList()
            options.doclet = "nl.talsmasoftware.umldoclet.UMLDoclet"
        }
    }
}

tasks.javadoc {
    dependsOn("configureJavadoc")
}

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:28.1-jre")
    // Use JUnit test framework
    testImplementation("junit:junit:4.12")

    umlDoclet("nl.talsmasoftware:umldoclet:2.+")
}

application {
    // Define the main class for the application.
    mainClassName = "pwr.sim.App"
}
