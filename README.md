# Base Starter Parent project for Vaadin Flow and OSGi in NPM mode

This project can be used as a starting point to create your own Vaadin Flow application bundle for OSGi.
It has the necessary dependencies and files to help you get started.
**This project has been revised for Vaadin 19 (GA in March 2021) which brings back OSGi support in npm mode.
For now, it Vaadin OSGi support is only for Flow based views (Java UIs), but not for Fusion (TypeScript UIs).**

For more Vaadin usage samples, you can go to vaadin.com/start.

To access it directly from github, clone the repository and import the project to the IDE of your choice as a Maven project. You need to have Java 8 or 11 installed.

The project consist of two subprojects: 
- starter
- app

The `starter` project contains the code for Web Application Bundle (WAB) which can be deployed
to any OSGi container.

The `app` project contains infrastructure which allows to build an executable JAR file.
The resulting application starts OSGi container with all necessary dependencies. It's a fast
way to check the WAB inside OSGi environment.


## Build and run a Vaadin web application OSGi bundle 

The simplest way to start the project is run command `mvn install` and then
run `java -jar app/target/app.jar`.

The starter project may be built via `mvn -pl starter install` command.
Then `mvn -pl app bnd-resolver:resolve` command may be used to generate list of bundles required 
at runtime in OSGi container and the app can be started via `mvn -pl app bnd-run:run` command.


## Deploying Vaadin specific bundles to an OSGi container

Vaadin application generally contains dependencies to other bundles: e.g. Vaadin components like `Button`,
`TextField` , etc. Every Vaadin component is based on a Web Component which
is represented by frontend resources. All frontend resources are built into a bundle
along with Vaadin WAB. As a result:
 - any Vaadin component bundle update is possible only within the same minor version, 
 so that the Web Component version stays the same (and only Java code is updated). We don't recommend 
 updating any version over a minor for Flow or the web component Flow integrations (even though it's
 not prevented anyhow at the moment).
 - Updating any bundle that has frontend resources requires that the frontend build goal `build-frontend` 
 is run and the WAB is redeployed to get the static frontend bundle updated.


## Limitations

See Limitations section in the starter project [README.md](starter/README.md).

:warning:
At the moment, it is not possible to modify frontend resources on the fly (webpack dev server is not available at runtime). This means that for every frontend change, you have to rebuild the frontend bundle. ([Issue for devmode](https://github.com/vaadin/flow/issues/9108))
