import sbt._
import Keys._

object ScatoBuild extends Build {
  val testDeps = Seq("org.scalacheck" %% "scalacheck" % "1.12.5" % "test")

  def module(prjName: String) = Project(
    id = prjName,
    base = file(prjName)).settings(
    name := s"scato-$prjName",
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq("-feature","-deprecation", "-Xlint", "-language:higherKinds"),
    libraryDependencies ++= testDeps ++ Seq(
      compilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1")
    )
  )

  lazy val root = Project(
    id = "root",
    base = file(".")
  ).aggregate ( typeclass
              , baze
              , transformers
              , examples )

  //lazy val prelude  = module("prelude")
  lazy val typeclass    = module("typeclass")
  lazy val baze         = module("base").dependsOn(typeclass)

  lazy val transformers = module("transformers").dependsOn(baze)

  lazy val examples     = module("examples").dependsOn(baze, transformers)
}
