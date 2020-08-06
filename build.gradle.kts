plugins { `java-library`; jacoco; `maven-publish` }
repositories { jcenter() }

group = "io.vacco.oruzka"
version = "0.0.1"

java {
  withJavadocJar()
  withSourcesJar()
  sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
  targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
}

dependencies {
  testImplementation("io.github.j8spec:j8spec:3.0.0")
}

object Publishing {
  const val gitUrl = "https://github.com/vaccovecrana/oruzka.git"
  const val siteUrl = "https://github.com/vaccovecrana/oruzka"
  const val libraryDesc = "Minimal unsafe operation utilities"
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])
      versionMapping {
        usage("java-api") { fromResolutionOf("runtimeClasspath") }
        usage("java-runtime") { fromResolutionResult() }
      }
      pom {
        name.set(project.name)
        description.set(Publishing.libraryDesc)
        url.set(Publishing.siteUrl)
        licenses {
          license {
            name.set("Apache License, Version 2.0")
            url.set("https://opensource.org/licenses/Apache-2.0")
          }
        }
        developers {
          developer {
            id.set("jjzazuet")
            name.set("Jesus Zazueta")
            email.set("jjzazuet@vacco.io")
          }
        }
        scm {
          connection.set(Publishing.gitUrl)
          developerConnection.set(Publishing.gitUrl)
          url.set(Publishing.siteUrl)
        }
      }
    }
  }
  repositories {
    maven {
      name = "bintray"
      val bintrayUser = System.getenv("BINTRAY_USER")
      setUrl("https://api.bintray.com/maven/vaccovecrana/vacco-oss/${project.name}")
      credentials {
        username = bintrayUser
        password = System.getenv("BINTRAY_KEY")
      }
    }
  }
}
