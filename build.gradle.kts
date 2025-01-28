plugins {
  kotlin("jvm") version "2.1.0"
  id("com.google.devtools.ksp") version "2.1.0-1.0.29"
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
  implementation("com.google.devtools.ksp:symbol-processing-api:2.1.0-1.0.29")

  testImplementation(kotlin("test-junit5"))
  testImplementation("dev.zacsweers.kctfork:core:0.7.0")
  testImplementation("dev.zacsweers.kctfork:ksp:0.7.0")
}
