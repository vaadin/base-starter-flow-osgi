# Base Starter for Vaadin Flow and OSGi in NPM mode

This project can be used as a starting point to create your own Vaadin Flow application bundle for OSGi.
It has the necessary dependencies and files to help you get started.

The best way to use it is via [vaadin.com/start](https://vaadin.com/start) - you can get only the necessary parts and choose the package naming you want to use.

To access it directly from github, clone the repository and import the project to the IDE of your choice as a Maven project. You need to have Java 8 or 11 installed.

Main Command: `mvn clean install`
- build all bundles
- run tests inside the osgi framework when `tests.bndrun` file exists in project.
- generates the Application if `application.bndrun` file exists in project.

Run the Application using `java -jar application/application.jar`


Special Commands:
- Resolve the Framework using `mvn bnd-resolver:resolve application/pom.xml`
- Run the Application using `mvn bnd-run:run -f application/pom.xml`
- Deploy changes into an running Framework `mvn install -DskipTests -f starter.flow.better/pom.xml`
- Export the Application using `mvn bnd-export:export -f application/pom.xml`



Test in browser
open Vaadin App [http://localhost:8080](http://localhost:8080).
open Felix Webconsole [http://localhost:8080/system/console](http://localhost:8080/system/console).

## Artefacts
* [**Base Starter for Vaadin Flow and OSGi**](starter.flow.simple)
* [**Base Starter for Vaadin Flow and OSGi**](starter.flow.better)
* [**The Aplication that runs Vaadin Flow and in OSGi**](application)

:warning:
At the moment, Vaadin 14 supports OSGi only in production mode. 