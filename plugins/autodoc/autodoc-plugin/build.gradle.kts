plugins {
    `java-gradle-plugin`
}

val jupiterVersion: String by project
val assertj: String by project

gradlePlugin {
    // Define the plugin
    plugins {
        create("autodoc") {
            displayName = "autodoc"
            description =
                "Plugin to generate a documentation manifest for the EDC Metamodel, i.e. extensions, SPIs, etc."
            id = "autodoc"
            implementationClass = "org.eclipse.dataspaceconnector.plugins.autodoc.AutodocPlugin"
        }
    }
}

val groupId: String by project


pluginBundle {
    website = "https://projects.eclipse.org/proposals/eclipse-dataspace-connector"
    vcsUrl = "http://github.com/eclipse-dataspaceconnector/GradlePlugins"
    group = groupId
    version = version
    tags = listOf("build", "documentation", "generated", "autodoc")
}

publishing {
    repositories {
        maven {
            name = "Snapshots"
            description = "OSSR Snapshot repository"
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = System.getenv("OSSRH_USER")
                password = System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}