plugins {
    id("java")
    id("org.springframework.boot") version "2.5.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.sql2o:sql2o:1.6.0")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("junit:junit:4.13.1")
    implementation("com.h2database:h2:1.4.191")
    implementation("org.springframework:spring-core:6.0.9")
    implementation("org.springframework:spring-web:6.0.9")
    implementation("org.springframework.boot:spring-boot-starter:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.mockito:mockito-core:2.24.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
}

sourceSets{
    main{
        java.srcDirs("backend/src/main/java")
        resources.srcDirs("backend/src/main/resources")
    }
    test{
        java.srcDirs("backend/src/test/java")
        resources.srcDirs("backend/src/test/resources")
    }
}

tasks.test {
    useJUnitPlatform()
}
