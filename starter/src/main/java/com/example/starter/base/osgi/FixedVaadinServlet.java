package com.example.starter.base.osgi;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletAsyncSupported;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;

import com.vaadin.flow.server.VaadinServlet;

@Component(service = Servlet.class
/*
 * Same like @VaadinMode
 *
 * , property = {
 * HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX +
 * InitParameters.SERVLET_PARAMETER_COMPATIBILITY_MODE + "=false"})
 */
)
@VaadinMode
@HttpWhiteboardServletAsyncSupported
@HttpWhiteboardServletPattern("/*")
public class FixedVaadinServlet extends VaadinServlet {

    @Override
    protected void servletInitialized() throws ServletException {
        getService().setClassLoader(getClass().getClassLoader());
    }

}