name          := """gss-aws-lambda"""
organization  := "ro.purecore.gss"
version       := "0.0.1"
scalaVersion  := "2.11.8"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.jcenterRepo

libraryDependencies ++= {
  val ficusV     = "1.2.7"
  val scalaTestV = "3.0.0"
  val scalaMockV = "3.3.0"
  Seq(
    "io.github.yeghishe"  %% "scala-aws-lambda-utils"      % "0.0.2",
    "com.iheart"          %% "ficus"                       % ficusV,
    "com.amazonaws"        % "aws-lambda-java-events"      % "1.3.0",
    "org.scalatest"       %% "scalatest"                   % scalaTestV % "it,test",
    "org.scalamock"       %% "scalamock-scalatest-support" % scalaMockV % "it,test"
  )
}

lazy val root = project.in(file(".")).configs(IntegrationTest)
Defaults.itSettings

initialCommands := """|import ro.purecore.gss._
                      |import io.github.yeghishe.lambda._
                      |import scala.concurrent._
                      |import scala.concurrent.duration._""".stripMargin

assemblyJarName in assembly := s"${name.value}.jar"
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

import S3._
s3Settings
mappings in upload := Seq((file(s"target/scala-2.11/${name.value}.jar"), s"${name.value}.jar"))
host in upload := "<YOUR S3 BUCKET>.s3.amazonaws.com"
progress in upload := true
upload <<= upload dependsOn assembly
