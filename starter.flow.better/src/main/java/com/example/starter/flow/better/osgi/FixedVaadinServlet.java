package com.example.starter.flow.better.osgi;

import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.DefaultDeploymentConfiguration;
import com.vaadin.flow.server.DeploymentConfigurationFactory;
import com.vaadin.flow.server.InitParameters;
import com.vaadin.flow.server.VaadinServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardResource;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletAsyncSupported;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;

/*
 * @author Stefan Bischof
 * @author Vaadin Ltd
 */
@Component(immediate = true, service = { Servlet.class })
/*
 * Same like @VaadinMode
 *
 * , property = {
 * HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX +
 * InitParameters.SERVLET_PARAMETER_COMPATIBILITY_MODE + "=false",
 * HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_INIT_PARAM_PREFIX +
 * InitParameters.SERVLET_PARAMETER_PRODUCTION_MODE + "=true" })
 */
@VaadinMode
@HttpWhiteboardServletAsyncSupported
@HttpWhiteboardServletPattern("/*")
@HttpWhiteboardResource(pattern = "/VAADIN/config/stats.json", prefix = "/META-INF/VAADIN/config/stats.json")
public class FixedVaadinServlet extends VaadinServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {

		super.init(servletConfig);

		getService().setClassLoader(getClass().getClassLoader());
	}

	@Override
	protected DeploymentConfiguration createDeploymentConfiguration(Properties initParameters) {

		initParameters.remove(DeploymentConfigurationFactory.DEV_MODE_ENABLE_STRATEGY);
		initParameters.put(InitParameters.SERVLET_PARAMETER_PRODUCTION_MODE, true);
		DeploymentConfiguration config = new DefaultDeploymentConfiguration(getClass(),
				initParameters) {

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