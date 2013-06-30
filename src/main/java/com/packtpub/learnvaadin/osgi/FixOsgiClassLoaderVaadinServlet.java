package com.packtpub.learnvaadin.osgi;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

@SuppressWarnings("serial")
public class FixOsgiClassLoaderVaadinServlet extends VaadinServlet {

	@Override
	protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration) throws ServiceException {

		VaadinServletService servletService = new VaadinServletService(this, deploymentConfiguration) {

			@Override
			public ClassLoader getClassLoader() {

				return getClass().getClassLoader();
			}
		};

		servletService.init();

		return servletService;
	}
}
