@file:Suppress("PropertyName")

plugins {
    id("java-library")
    id("maven-publish")
    id("net.neoforged.moddev") version "2.0.105"
    kotlin("jvm") version "2.1.21"
    idea
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
}

val mod_id: String by project
val mod_name: String by project
val mod_version: String by project
val mod_group_id: String by project
val mod_license: String by project
val mod_authors: String by project
val mod_description: String by project

val minecraft_version: String by project
val minecraft_version_range: String by project
val loader_version_range: String by project
val neo_version: String by project
val parchment_mappings_version: String by project
val parchment_minecraft_version: String by project

version = mod_version
group = mod_group_id

repositories {
    // Add here additional repositories if required
    mavenCentral()
    mavenLocal()

    maven {
        name = "Kotlin for Forge"
        url = uri("https://thedarkcolour.github.io/KotlinForForge/")
    }
}

base {
    archivesName = mod_id
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

neoForge {
    version = neo_version

    parchment {
        mappingsVersion.set(parchment_mappings_version)
        minecraftVersion.set(parchment_minecraft_version)
    }

    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        register("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        create("data") {
            data()
            programArguments.addAll(
                "--mod", mod_id,
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }

    }

    mods {
        create(mod_id) {
            sourceSet(sourceSets["main"])
        }
    }
}

sourceSets["main"].resources {
    srcDir("src/generated/resources")
}

configurations {
    maybeCreate("localRuntime")
    named("runtimeClasspath") {
        extendsFrom(configurations["localRuntime"])
    }
}

dependencies {
    // Add dependencies here
    implementation("thedarkcolour:kotlinforforge-neoforge:5.9.0")
}

val generateModMetadata by tasks.registering(ProcessResources::class) {
    val replaceProperties = mapOf(
        "minecraft_version" to minecraft_version,
        "minecraft_version_range" to minecraft_version_range,
        "neo_version" to neo_version,
        "loader_version_range" to loader_version_range,
        "mod_id" to mod_id,
        "mod_name" to mod_name,
        "mod_license" to mod_license,
        "mod_version" to mod_version,
        "mod_authors" to mod_authors,
        "mod_description" to mod_description
    )

    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}

sourceSets["main"].resources.srcDir(generateModMetadata)

neoForge.ideSyncTask(generateModMetadata)

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("file://${project.projectDir}/repo")
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
