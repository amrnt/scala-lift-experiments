name := "amrtamimi"

organization := "com.amrtamimi"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.2"

resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
                "releases"        at "http://oss.sonatype.org/content/repositories/releases"
                )

seq(com.github.siasia.WebPlugin.webSettings :_*)

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.5-SNAPSHOT"
  Seq(
    "net.liftweb"       %% "lift-webkit"         % liftVersion        % "compile->default",
    "net.liftweb"       %% "lift-mapper"         % liftVersion        % "compile->default",
    "net.liftweb"       %% "lift-record"         % liftVersion        % "compile->default",
    "net.liftweb"       %% "lift-squeryl-record" % liftVersion        % "compile->default",
    "org.squeryl"       %% "squeryl"             % "0.9.5-2"          % "compile->runtime",
    "postgresql"        %  "postgresql"          % "9.1-901.jdbc4"    % "compile->default",
    "org.eclipse.jetty" %  "jetty-webapp"        % "7.5.4.v20111024"  % "container; test",
    "ch.qos.logback"    %  "logback-classic"     % "1.0.6",
    "org.specs2"        %% "specs2"              % "1.11"             % "test"
  )
}

