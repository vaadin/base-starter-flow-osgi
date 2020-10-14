package com.example.starter.base.osgi;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
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

    /**
     * This class is a workaround for #4367. This will be removed after the
     * issue is fixed.
     */
    private static class FixedVaadinServlet extends VaadinServlet {

        private final Bundle bundle;

        private FixedVaadinServlet(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        public void init(ServletConfig servletConfig) throws ServletException {
            super.init(servletConfig);

            getService().setClassLoader(getClass().getClassLoader());
        }

        @Override
        protected void service(HttpServletRequest request,
                HttpServletResponse response)
                throws ServletException, IOException {
            String pathInfo = request.getPathInfo();
            if ("/VAADIN/config/stats.json".equals(pathInfo)) {
                URL resource = bundle
                        .getResource("/META-INF/VAADIN/config/stats.json");
                List<String> lines = IOUtils.readLines(
                        resource.openConnection().getInputStream(),
                        StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(response.getOutputStream(),
                                UTF_8));
                for (String line : lines) {
                    writer.write(line);
                    writer.write("\n");
                }
                writer.flush();
            } else {
                super.service(request, response);
            }
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
        ctx.registerService(Servlet.class,
                new FixedVaadinServlet(ctx.getBundle()), properties);
    }

}
