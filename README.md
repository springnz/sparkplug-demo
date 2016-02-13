# Neon Data #

A combined repository for Neon data transforming, processing, analysing and mining projects.

### Included Projects ###

* `spark-lib`: Spark execution library for running Spark jobs
* `seeker-importer`: Importer 
* `solver-importer`: Importer

### Adding a new Project ###

Edit the `build.sbt` file.

Define the project dependencies:
```
lazy val newDependencies = Seq(
  "com.example" %% "library" % "1.0.0",
  "com.example" %% "service" % "1.0.0"
)
```

Add a new project:
```
lazy val newProject = Common.CreateProject("project-name")
  .settings(libraryDependencies ++= newDependencies)
  .dependsOn(utilLib)
```

Add it to the main project aggregates:
```
lazy val main = project.in(file("."))
  .aggregate(..., newProject)
```

Customise for project-specific settings. 

Job done. 

### Contribution guidelines ###

* Firstly read the Confluence page on Scala Development (https://springdom.atlassian.net/wiki/display/SD/Scala+Development)
* All production code must be covered in unit tests
* All code must conform to standard naming conventions and be formatted with Scalariform
* All merges to master must be accompanied with a related pull-request
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact