package com.example.starter.base.osgi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.osgi.service.component.annotations.ComponentPropertyType;
import org.osgi.service.http.whiteboard.annotations.RequireHttpWhiteboard;

import com.vaadin.flow.server.frontend.FrontendUtils;

@ComponentPropertyType
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@RequireHttpWhiteboard
public @interface VaadinMode {

    String PREFIX_ = "servlet.init.";

    /**
     * {@code frontendDirectory} service property.
     *
     * @return the frontendDirectory initial parameter value
     * @see org.osgi.service.http.whiteboard.HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX
     */
    String frontendDirectory() default "${project.basedir}/" + FrontendUtils.FRONTEND;

}
