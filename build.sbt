import ReleaseTransformations._

name := "test-scala-lib"

organization := "uk.gov.homeoffice"

scalaVersion := "2.11.8"

fork in run := true

fork in Test := true

publishArtifact in Test := true

scalacOptions ++= Seq(
  "-feature",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:reflectiveCalls",
  "-language:postfixOps",
  "-Yrangepos",
  "-Yrepl-sync"
)

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
  "Kamon Repository" at "http://repo.kamon.io",
  "jitpack" at "https://jitpack.io",
  Resolver.bintrayRepo("hseeberger", "maven"),
  Resolver.bintrayRepo("findify", "maven")
)

val `specs2-version` = "3.8.6"
val `scalatest-version` = "3.0.1"

libraryDependencies ++= Seq(
  "org.clapper" %% "grizzled-slf4j" % "1.3.0",
  "ch.qos.logback" % "logback-classic" % "1.1.8",
  "org.specs2" %% "specs2-core" % `specs2-version` withSources(),
  "org.specs2" %% "specs2-mock" % `specs2-version` withSources(),
  "org.specs2" %% "specs2-matcher-extra" % `specs2-version` withSources(),
  "org.specs2" %% "specs2-junit" % `specs2-version` withSources(),
  "org.scalatest" %% "scalatest" % `scalatest-version` withSources()
)

//import sbtrelease._


releaseTagName := s"${if (releaseUseGlobalVersion.value) (version in ThisBuild).value else version.value}"


releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,       // ReleaseStep which performs the initial git checks
  tagRelease,
  // publishArtifacts,        // ReleaseStep which checks whether `publishTo` is properly set up
  setNextVersion,
  commitNextVersion,
  pushChanges                 // ReleaseStep which also checks that an upstream branch is properly configured
)