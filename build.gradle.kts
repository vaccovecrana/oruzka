plugins { id("io.vacco.oss.gitflow") version "0.9.8" }

group = "io.vacco.oruzka"
version = "0.2.0"

configure<io.vacco.oss.gitflow.GsPluginProfileExtension> {
  addJ8Spec()
  addClasspathHell()
  sharedLibrary(true, false)
}

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  testImplementation("com.esotericsoftware.yamlbeans:yamlbeans:1.15")
}
