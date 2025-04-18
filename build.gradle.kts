plugins {
  kotlin("jvm") version "2.1.20"
  id("com.google.devtools.ksp") version "2.1.20-2.0.0"
}

repositories {
  mavenCentral()
  gradlePluginPortal()
  google()
}

tasks.withType<Test> {
  useJUnitPlatform()
}

dependencies {
  implementation(kotlin("reflect"))
  implementation("com.google.devtools.ksp:symbol-processing-api:2.1.20-2.0.0")

  testImplementation(kotlin("test-junit5"))
  testImplementation("dev.zacsweers.kctfork:core:0.7.0")
  testImplementation("dev.zacsweers.kctfork:ksp:0.7.0")
}
