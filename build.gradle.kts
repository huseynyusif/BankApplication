plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "az.uniTech"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    //Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    //liquibase
    implementation("org.liquibase:liquibase-core")
    //Data
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-junit-jupiter:3.11.2")
    testImplementation("org.spockframework:spock-spring:2.3-groovy-4.0")
    testImplementation("io.github.benas:random-beans:3.9.0")
    testImplementation("org.hamcrest:hamcrest-library:2.2")

    //mapstruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    //validation
    implementation("org.springframework.boot:spring-boot-starter-validation:3.2.4")
    //feign-client
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
