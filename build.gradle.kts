plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.test"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.postgresql:postgresql")
	implementation("org.springframework.boot:spring-boot-starter-jetty")
	implementation("org.flywaydb:flyway-core")
	implementation("org.projectlombok:lombok:1.18.34")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
	implementation("com.fasterxml.jackson.core:jackson-annotations:2.17.2")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootJar {
	manifest {
		attributes["Main-Class"] = "com.test.test.TestApplication"
	}
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.jar {
	enabled = false
}