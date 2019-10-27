plugins {
    kotlin("jvm") version "1.3.10"
    id("org.springframework.boot") version "2.1.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}


group = "ru.aleksei_kirzhaev"
version = "1.0"

ext {
    val kotlinVersion = "1.3.10"
}

repositories {
    mavenCentral()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.telegram:telegrambots-spring-boot-starter:4.1")
    compile(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = "2.9.7")
    compileClasspath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.10")
}
