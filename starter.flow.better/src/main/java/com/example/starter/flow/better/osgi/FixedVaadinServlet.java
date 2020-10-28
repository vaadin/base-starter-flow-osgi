package com.example.starter.flow.better.osgi;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.vaadin.flow.di.ResourceProvider;
import com.vaadin.flow.server.VaadinServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletAsyncSupported;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;

/*
 * @author Stefan Bischof
 * @author Vaadin Ltd
 */
@Component(// immediate = true,
		service = { Servlet.class })
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
//@HttpWhiteboardResource(pattern = "/VAADIN/config/stats.json", prefix = "/META-INF/VAADIN/config/stats.json")
public class FixedVaadinServlet extends VaadinServlet {

	// FIXME: this service will only be activated when an ResourceProvider exists-
	// fixes an issue on bundle activation order. Must gibe a better way then this
	// or R8 Condition-Service
	@Reference
	ResourceProvider pr;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {

		super.init(servletConfig);

		getService().setClassLoader(getClass().getClassLoader());
	}

}