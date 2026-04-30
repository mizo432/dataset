import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.time.Duration

buildscript {
    repositories {
        mavenCentral() // Maven Centralリポジトリから依存関係を取得する
    }
    dependencies {
        // Flyway（データベース移行ツール）のクラスパス追加
        // classpath("org.flywaydb:flyway-database-postgresql:11.3.4")
    }
}
plugins {
    // Javaプラグインを適用（Javaプロジェクトのサポート）
    java
    // Spring Bootプラグイン
    id("org.springframework.boot") version "4.0.6"
    // Spring関連の依存関係の管理用プラグイン
    id("io.spring.dependency-management") version "1.1.7"
    id("se.patrikerdes.use-latest-versions") version "0.2.19"
    id("com.github.ben-manes.versions") version "0.53.0"
    // Flywayプラグイン（DBマイグレーション）
    // id("org.flywaydb.flyway") version "11.3.4"
    jacoco
}
group = "org.venus"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }

}

tasks {
    withType<JavaCompile> {
        // コンパイラの警告を有効化（未チェック警告）
        options.compilerArgs.add("-Xlint:unchecked")
    }
}
configurations.compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://company/com/maven2")
    }
    mavenLocal()
}

extra["guavaVersion"] = "33.4.8-jre"
extra["icu4jVersion"] = "77.1"
extra["jiltVersion"] = "1.8.2"
extra["junitVersion"] = "6.0.0"
extra["spotbugsAnnotationVersion"] = "4.9.3"
extra["libphonenumberVersion"] = "9.0.9"
extra["poiVersion"] = "5.5.0"
extra["jspecifyVersion"] = "1.0.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("com.github.spotbugs:spotbugs-annotations:${property("spotbugsAnnotationVersion")}")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.google.guava:guava:${property("guavaVersion")}")
    implementation("org.jspecify:jspecify:${property("jspecifyVersion")}")
    implementation("com.ibm.icu:icu4j:${property("icu4jVersion")}")
    testRuntimeOnly("com.h2database:h2")
    implementation("com.fasterxml.uuid:java-uuid-generator:5.1.0")
    // assertJ
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation("org.mockito:mockito-core:5.23.0")

    annotationProcessor("cc.jilt:jilt:${property("jiltVersion")}")
    // https://mvnrepository.com/artifact/com.googlecode.libphonenumber/libphonenumber
    implementation("com.googlecode.libphonenumber:libphonenumber:${property("libphonenumberVersion")}")
    implementation("com.github.ben-manes.caffeine:caffeine")

    // Apache POI for Excel processing
    implementation("org.apache.poi:poi:${property("poiVersion")}")
    implementation("org.apache.poi:poi-ooxml:${property("poiVersion")}")
}
tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    (options as StandardJavadocDocletOptions).addStringOption("encoding", "UTF-8")
}

dependencyManagement {
    imports {
    }
}
jacoco {
    toolVersion = "0.8.14"
    // reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("reports/jacoco")
    }
}
tasks.test {
    useJUnitPlatform {
        excludeTags("medium", "large")
        timeout.set(Duration.ofSeconds(60))
    }
    finalizedBy(tasks.jacocoTestReport) // a report is always generated after tests run
}

val mediumTest = tasks.register("mediumTest", Test::class.java) {
    description = "ミデアムサイズのテストを実行します"
    group = "verification"
    useJUnitPlatform {
        includeTags("medium")
    }
    timeout.set(Duration.ofMinutes(5))
    shouldRunAfter("test")
}
val largeTest = tasks.register("largeTest", Test::class.java) {
    description = "ラージサイズのテストを実行します"
    group = "verification"
    useJUnitPlatform {
        includeTags("large")
    }
    timeout.set(Duration.ofHours(1))
    shouldRunAfter("mediumTest")
}

apply(plugin = "com.github.ben-manes.versions")


tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {

    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}
fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

