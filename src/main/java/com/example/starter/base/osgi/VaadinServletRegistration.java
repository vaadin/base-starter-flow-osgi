package com.example.starter.base.osgi;

import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.DefaultDeploymentConfiguration;
import com.vaadin.flow.server.DeploymentConfigurationFactory;
import com.vaadin.flow.server.InitParameters;
import com.vaadin.flow.server.VaadinServlet;

/**
 * Register a VaadinServlet via HTTP Whiteboard specification
 */
@Component(immediate = true)
public class VaadinServletRegistration {

    private HttpService httpService;

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
        protected DeploymentConfiguration createDeploymentConfiguration(
                Properties initParameters) {
            initParameters.remove(
                    DeploymentConfigurationFactory.DEV_MODE_ENABLE_STRATEGY);
            initParameters.put(InitParameters.SERVLET_PARAMETER_PRODUCTION_MODE,
                    true);
            DeploymentConfiguration config = new DefaultDeploymentConfiguration(
                    getClass(), initParameters) {
                @Override
                public boolean isProductionMode() {
                    return true;
                }

                @Override
                public boolean isStatsExternal() {
                    return true;
                }

                @Override
                public String getExternalStatsUrl() {
                    return "/VAADIN/config/stats.json";
                }
            };

            return config;
        }

    }

    @Activate
    void activate(BundleContext ctx) throws NamespaceException {
        Hashtable<String, Object> properties = new Hashtable<>();
        properties.put(
                HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_ASYNC_SUPPORTED,
                true);
        properties.put(InitParameters.SERVLET_PARAMETER_COMPATIBILITY_MODE,
                false);
        properties.put(InitParameters.SERVLET_PARAMETER_PRODUCTION_MODE, true);
        properties.put(HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN,
                "/*");
        ctx.registerService(Servlet.class, new FixedVaadinServlet(),
                properties);

        httpService.registerResources("/VAADIN/config/stats.json",
                "/META-INF/VAADIN/config/stats.json",
                httpService.createDefaultHttpContext());
    }

    @Reference
    void setHttpService(HttpService service) {
        this.httpService = service;
    }

    void unsetHttpService(HttpService service) {
        this.httpService = null;
    }

}
