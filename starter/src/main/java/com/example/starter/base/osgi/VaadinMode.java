package com.example.starter.base.osgi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ComponentPropertyType;
import org.osgi.service.http.whiteboard.annotations.RequireHttpWhiteboard;

/**
 * Example of a User defined component property type annotation, which allows
 * to specify any of Vaadin init parameters in a type-safe manner, including
 * property names, property types and default values.
 *
 * This is an another way to set the parameters in addition to the one by
 * setting the {@link Component#property()} value.
 *
 * @see <a href=
 * "https://vaadin.com/docs/v18/flow/advanced/tutorial-flow-runtime-configuration.html">Vaadin Runtime Configuration</a>
 *
 * @see <a href=
 * "https://docs.osgi.org/specification/osgi.cmpn/7.0.0/service.component.html#service.component-component.property.types">Component property type</a>
 */
@ComponentPropertyType
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@RequireHttpWhiteboard
public @interface VaadinMode {

    String PREFIX_ = "servlet.init.";

    /**
     * {@code productionMode} service property.
     *
     * @return the productionMode initial parameter value
     * @see org.osgi.service.http.whiteboard.HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX
     * @see com.vaadin.flow.server.InitParameters.SERVLET_PARAMETER_PRODUCTION_MODE
     */
    boolean productionMode() default false;

}
