package com.example.starter.base.osgi;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardResource;

/**
 * Register frontend resources.
 * 
 * @author Vaadin Ltd
 *
 */
@Component(service = FrontendResources.class)
@HttpWhiteboardResource(pattern = "/frontend/*", prefix = "/frontend")
public class FrontendResources {

}

