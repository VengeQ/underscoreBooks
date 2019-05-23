name := "ScalaEssential"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.creativescala" %% "doodle" % "0.9.0"

scalacOptions += "-Ypartial-unification"
scalacOptions += "-language:higherKinds"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.6.0",
  "org.typelevel" %% "cats-macros" % "1.6.0",
  "org.typelevel" %% "cats-kernel" % "1.6.0"

)

fork in test := true
