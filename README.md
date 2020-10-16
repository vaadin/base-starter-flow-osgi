# Base Starter for Vaadin Flow and OSGi in NPM mode

This project can be used as a starting point to create your own Vaadin Flow application bundle for OSGi.
It has the necessary dependencies and files to help you get started.

The best way to use it is via [vaadin.com/start](https://vaadin.com/start) - you can get only the necessary parts and choose the package naming you want to use.

To access it directly from github, clone the repository and import the project to the IDE of your choice as a Maven project. You need to have Java 8 or 11 installed.

Run using `mvn -Pprepare-osgi-container -Prun-osgi-container -Pproduction verify` and open [http://localhost:8080](http://localhost:8080) in browser.
This command will run profiles which downloads for you Felix OSGi container and installs `felix-jetty` 
as OSGi web container. It deploys all necessary bundles to Felix and start it synchronously.
The container will be stopped  when you stop your maven build (e.g. by killing it via `Ctrl+C`).

Servlet initial parameters may be passed via `Properties` object in the `registerService` method as a last
parameter value. Every servlet init parameter should be prefixed by `"servlet.init."`  and stored in the 
`Properties` object. Make sure that you store a `String` value as a parameter value: so you may not store
`true`  as a boolean value to set init parameter value to `true` , you should set `"true"` String value.
In the example production mode property value is set via the call:

```java
properties.setProperty(
                "servlet.init."
                        + InitParameters.SERVLET_PARAMETER_PRODUCTION_MODE,
                Boolean.TRUE.toString());
```

You may use `"false"` as a value to switch off production mode.

:warning:
At the moment, Vaadin 14 supports OSGi only in production mode. 