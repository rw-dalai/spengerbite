// --- Plugins ---
// Boot brings the BOM (Bill of Materials), it chooses the matching versions of all Spring dependencies.
// Starter = which dependencies, BOM = which versions.

plugins {
    java
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "at.spengergasse"
version = "0.0.1-SNAPSHOT"
description = "SpengerBite"


// --- Java Toolchain ---
// The JDK for our code, independent of the JVM Gradle runs on.

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}


// --- Repositories ---
// Where do I get the dependencies from?

repositories {
    mavenCentral()
}


// --- Dependencies ---
// What dependencies do I need?

// Scope
// Implementation = compile + run
// RuntimeOnly = run only
// CompileOnly = compile only

dependencies {
    // Web: Tomcat, Controller, Jackson
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    // Persistence: JPA, Hibernate, H2 in memory
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-h2console")
    runtimeOnly("com.h2database:h2")

    // Code Generation: Lombok, compile time only
    compileOnly("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    // Testing: JUnit, Spring Test, AssertJ, Mockito
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // libphone
    implementation("com.googlecode.libphonenumber:libphonenumber:9.0.40")
}


// --- Tasks ---
// Plugins add tasks. We can also add our own tasks.

// configure all tasks of a given type
tasks.withType<Test> {
    useJUnitPlatform()
}

// configure an existing task
tasks.bootJar {
    archiveFileName = "spengerbite.jar"
}

// custom task (demo only)
//tasks.register("hello") {
//	doLast { println("hello") }
//}
