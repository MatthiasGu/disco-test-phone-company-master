lazy val phoneCompany = (project in file("."))

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
   "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
