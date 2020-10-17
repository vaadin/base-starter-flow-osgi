package com.example.starter.flow.better.osgi;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardResource;

/**
 * Registers icon Important: Declare the service= in the @Component!
 *
 * @author Stefan Bischof
 *
 */
@ServiceDescription("Registers icon using the OSGi-Http-Whiteboard - @HttpWhiteboardResource")
@HttpWhiteboardResource(pattern = "/icons/icon.png", prefix = "/META-INF/resources/icons/icon.png")
@Component(immediate = true, service = IconResource.class)
public class IconResource {

}