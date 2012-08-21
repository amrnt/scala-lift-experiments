name := "Lift 2.5 starter template"

version := "0.0.1"

organization := "com.aioha"

scalaVersion := "2.9.2"

resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
                "releases"        at "http://oss.sonatype.org/content/repositories/releases"
                )

seq(com.github.siasia.WebPlugin.webSettings :_*)

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.5-SNAPSHOT"
  val cxVersion   = "2.0"
  Seq(
    "net.liftweb"       %% "lift-webkit"        % liftVersion        % "compile",
    "net.liftweb"       %% "lift-mapper"        % liftVersion        % "compile",
    "ru.circumflex"     %  "circumflex-orm"     % cxVersion          % "compile->default",
    "postgresql"        %  "postgresql"         % "9.1-901.jdbc4"    % "compile",
    "org.eclipse.jetty" %  "jetty-webapp"       % "7.5.4.v20111024"  % "container; test",
    "ch.qos.logback"    %  "logback-classic"    % "1.0.6",
    "org.specs2"        %% "specs2"             % "1.11"             % "test"
  )
}

