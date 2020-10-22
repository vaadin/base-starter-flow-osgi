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

## Build and run a Vaadin web application OSGi bundle 

At the moment Flow doesn't provide automatic servlet registration so servlet needs to be 
registered manually in the web application project. The example how to do it is 
provided in the project (see `VaadinServletRegistration`).

Vaadin dependencies and core OSGi dependencies are required to be able to build Vaadin web application bundle.
These artifact are listed in the `dependencies` section of the project pom file.
The project can be built using command `mvn -Pproduction package` which produces 
a bundle to deploy to OSGi container.

The generated package (bundle) is not enough to be able to run it on OSGi container.
There are a number of bundles which needs to be deployed to OSGi container: these bundles
provide services which are required to run the web application bundle.
Such bundles may vary per OSGi container. The project contains these dependencies for Felix Jetty
in `prepare-osgi-container` profile which copies them into the Felix bundle folder so that 
they will be deployed automatically when Felix starts.

This information also can be found [here](https://github.com/vaadin/flow-and-components-documentation/blob/V14.3/documentation/osgi/tutorial-osgi-basic.asciidoc). It's written for compatibility mode but the part related to the 
compatibility mode is obvious there: everything related to dependencies and running the app is the same.
There is no any need to register static resources in NPM mode since all frontend 
resources are bundled into one file which is packaged into the web app Jar 
automatically. Webjars section also should be omitted.

## Limitations

Here is a list of things which are not currently supported:

- NPM dev mode: it's only possible to run Vaadin web application in production mode
- it's not possible to use OSGi declarative services with Vaadin components: 
you may not inject a service declaratively in Vaadin classes (using annotations) 
just because UI objects are not managed by OSGi. But you still may call OSGi services programmatically of course.
- as mentioned above: there is no yet automatic servlet registration. So the web application 
bundle should register the servlet itself.
- there is no documentation and it's not clear how to make Push working with WebSockets: the main question 
here is enabling WS on pure OSGi container. It works on hybrid OSGi container which allows
to deploy WARs (like Karaf) but this is exactly the same as for plain web server. It's not clear what
needs to be done to enable WS for a pure OSGi container.

:warning:
At the moment, Vaadin 14 supports OSGi only in production mode. 