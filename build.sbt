lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    inThisBuild(
      List(
        organization := "aafa",
        version := "1.0",
        scalaVersion := "2.11.11"
      )),
    name := "markit",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.2",
      "com.lihaoyi" %%% "scalatags" % "0.6.5",
      "com.github.tototoshi" %% "scala-csv" % "1.3.4",
      "org.scalaj" %% "scalaj-http" % "2.3.0",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    ),
    scalaJSUseMainModuleInitializer := true
  )

// Automatically generate index-dev.html which uses *-fastopt.js
resourceGenerators in Compile += Def.task {
  val source = (resourceDirectory in Compile).value / "index.html"
  val target = (resourceManaged in Compile).value / "index-dev.html"

  val fullFileName = (artifactPath in (Compile, fullOptJS)).value.getName
  val fastFileName = (artifactPath in (Compile, fastOptJS)).value.getName

  IO.writeLines(target, IO.readLines(source).map { line =>
    line.replace(fullFileName, fastFileName)
  })

  Seq(target)
}.taskValue
