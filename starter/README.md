# Base Starter for Vaadin Flow and OSGi in NPM mode

Servlet initial parameters may be passed via `@Component` annotation parameter `property`, e.g.

```java
@Component(immediate = false, service = Servlet.class, property = {
  HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX +
  InitParameters.SERVLET_PARAMETER_COMPATIBILITY_MODE + "=false"}) 
```
or via some annotation like `VaadinMode` which is available in the example.

Same way can be used to set production mode via the initial parameters.
Another way to set these values is explicitly set them via the vaadin maven plugin configuration:

```xml
 <configuration>
    <compatibilityMode>false</compatibilityMode>
    <productionMode>false</productionMode>
</configuration>

```

The values will be set in the token file (`flow-build-info.json`) which is read to 
create a deployment configuration for a Vaadin servlet.

## Vaadin Servlet registration in OSGi

At the moment Flow doesn't provide automatic servlet registration so servlet needs to be 
registered manually in the web application project. The example how to do it is 
provided in the project (see `FixedVaadinServlet`).


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
