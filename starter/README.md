# Base Starter for Vaadin Flow and OSGi in NPM mode

Vaadin Servlet initial parameters may be passed via `@Component` annotation parameter `property`, e.g.

```java
@Component(immediate = false, service = Servlet.class, property = {
  HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX +
  InitParameters.SERVLET_PARAMETER_PRODUCTION_MODE + "=false"})
```
or via some annotation like `VaadinMode` which is available in the example.

Another way to set the `productionMode` value is explicitly set it via the Vaadin maven plugin configuration:

```xml
 <configuration>
    <productionMode>false</productionMode>
</configuration>

```

The values will be set in the token file (`flow-build-info.json`) which is read to 
create a deployment configuration for a Vaadin servlet.

Note: Not any property can be set via Vaadin maven plugin configuration.
There is a limited number of Maven configuration parameters which are reflected to the same Vaadin Servlet init property name.
`productionMode` is mapped exactly as is: the same name is init parameter and the same maven config parameter can be used in the pom.xml.
There are config Maven parameters which have different names comparing to init parameters.
And there are a lot of Vaadin Servlet init parameters (see `com.vaadin.flow.server.InitParameters`) and only few Maven configuration parameters (see `com.vaadin.flow.plugin.maven.FlowModeAbstractMojo`).

## Vaadin Servlet registration in OSGi

At the moment Flow doesn't provide automatic servlet registration so servlet needs to be 
registered manually in the web application project. The example how to do it is 
provided in the project (see `FixedVaadinServlet`).


## Limitations

Here is a list of things which are not currently supported:

- NPM dev mode: it's possible to run Vaadin web application in both production 
and dev mode, but dev mode can be used only with precompiled frontend
 resources (via vaadin-maven-plugin) and it's not possible to use a dev server (Webpack) within OSGi.
- it's not possible to use OSGi declarative services with Vaadin components: 
you may not inject a service declaratively in Vaadin classes (using annotations) 
just because UI objects are not managed by OSGi. But you still may call OSGi services programmatically of course.
- as mentioned above: there is no yet automatic servlet registration. So the web application 
bundle should register the servlet itself.
- there is no documentation and it's not clear how to make Push working with WebSockets: the main question 
here is enabling WS on pure OSGi container. It works on hybrid OSGi container which allows
to deploy WARs (like Karaf) but this is exactly the same as for plain web server. It's not clear what
needs to be done to enable WS for a pure OSGi container.
