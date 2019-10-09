package com.example.starter.base.osgi;

import com.vaadin.flow.function.DeploymentConfiguration;
import java.util.Hashtable;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletConfiguration;
import java.util.Properties;
import javax.servlet.annotation.WebServlet;

/**
 * Register a VaadinServlet via HTTP Whiteboard specification
 */
@Component(immediate = true)
public class VaadinServletRegistration {

    /**
     * This class is a workaround for #4367. This will be removed after the
     * issue is fixed.
     */
    private static class FixedVaadinServlet extends VaadinServlet {
        @Override
        public void init(ServletConfig servletConfig) throws ServletException {
            super.init(servletConfig);

            getService().setClassLoader(getClass().getClassLoader());
        }

        @Override
        protected DeploymentConfiguration createDeploymentConfiguration(Properties initParameters) {
            // npm mode is not currently supported
            initParameters.setProperty("compatibilityMode", "true");
            return super.createDeploymentConfiguration(initParameters);
        }
        
    }

    @Activate
    void activate(BundleContext ctx) {
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put(
                HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_ASYNC_SUPPORTED,
                true);
        properties.put(HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN,
                "/*");
        ctx.registerService(Servlet.class, new FixedVaadinServlet(),
                properties);
    }

}
