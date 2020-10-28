package com.example.starter.flow.better.osgi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.osgi.service.component.annotations.ComponentPropertyType;
import org.osgi.service.http.whiteboard.annotations.RequireHttpWhiteboard;

/*
 * @author Stefan Bischof
 */

@ComponentPropertyType
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@RequireHttpWhiteboard
public @interface VaadinMode {

	String PREFIX_ = "servlet.init.";

	/**
	 * Service property CapabilityMode.
	 *
	 * @return The CapabilityMode.
	 * @see org.osgi.service.http.whiteboard.HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX
	 * @see com.vaadin.flow.server.InitParameters.SERVLET_PARAMETER_COMPATIBILITY_MODE
	 */
	boolean compatibilityMode() default false;

	//	/**
	//	 * Service property ProductionMode.
	//	 *
	//	 * @return The SERVLET_PARAMETER_PRODUCTION_MODE.
	//	 * @see org.osgi.service.http.whiteboard.HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX
	//	 * @see com.vaadin.flow.server.InitParameters.SERVLET_PARAMETER_PRODUCTION_MODE
	//	 */
	//	boolean productionMode() default true;
}
