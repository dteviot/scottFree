plugins {
  java
  kotlin("jvm") // Using the kotlin("jvm") plugin for JVM projects
}

dependencies {
  // implementation("org.jetbrains.kotlinx:multik-core:0.2.3")
  // implementation("org.jetbrains.kotlinx:multik-default:0.2.3")
  implementation("org.jetbrains.kotlin:kotlin-stdlib")
  testImplementation("junit:junit:4.13-beta-2")
}
