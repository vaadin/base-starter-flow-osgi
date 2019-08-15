[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/vaadin-flow/Lobby#?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

# Base Starter for Vaadin Flow and OSGi

This project can be used as a starting point to create your own Vaadin Flow application bundle for OSGi.
It has the necessary dependencies and files to help you get started.

The best way to use it is via [vaadin.com/start](https://vaadin.com/start) - you can get only the necessary parts and choose the package naming you want to use.

To access it directly from github, clone the repository and import the project to the IDE of your choice as a Maven project. You need to have Java 8 or 11 installed.

Run using `mvn -Pprepare-osgi-container -Prun-osgi-container verify` and open [http://localhost:8080](http://localhost:8080) in browser.
This command will run profiles which downloads for you Felix OSGi container and installs `felix-jetty` 
as OSGi web container. It deploys all necessary bundles to Felix and start it synchronously.
The container will be stopped  when you stop your maven build (e.g. by killing it via `Ctrl+C`).

:warning:
At the moment, Vaadin 14 supports OSGi only in compatibility mode. 
