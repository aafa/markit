lazy val root = project
  .in(file("."))
  .settings(
    inThisBuild(
      List(
        organization := "aafa",
        version := "1.0",
        scalaVersion := "2.11.11"
      )),
    name := "markit",
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % "0.9.6",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )

