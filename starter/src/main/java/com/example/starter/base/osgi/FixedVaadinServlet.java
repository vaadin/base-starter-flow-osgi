package com.example.starter.base.osgi;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletAsyncSupported;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;

import com.vaadin.flow.server.VaadinServlet;

@Component(immediate = true, service = Servlet.class)
/*
 * Same like @VaadinMode
 *
 * , property = {
 * HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX +
 * InitParameters.SERVLET_PARAMETER_COMPATIBILITY_MODE + "=false"})
 */
@VaadinMode
@HttpWhiteboardServletAsyncSupported
@HttpWhiteboardServletPattern("/*")
public class FixedVaadinServlet extends VaadinServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        super.init(servletConfig);

        getService().setClassLoader(getClass().getClassLoader());
    }

}