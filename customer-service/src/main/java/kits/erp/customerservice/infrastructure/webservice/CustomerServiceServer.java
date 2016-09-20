package kits.erp.customerservice.infrastructure.webservice;

import java.lang.invoke.MethodHandles;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kits.erp.customerservice.application.ApplicationContext;

public class CustomerServiceServer extends Server {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	public CustomerServiceServer(int httpPort, ApplicationContext applicationContext) {
		super(httpPort);
		setHandler(createServletContextHandler(applicationContext));
		logger.info("KITS-ERP Customer Service initialised on port " + httpPort);
	}
	
	public void startServer() throws Exception {
		super.start();
		logger.info("KITS-ERP Customer Service started");
		super.join();
	}
	
	private ServletContextHandler createServletContextHandler(ApplicationContext applicationContext) {
		ServletContextHandler servletContextHandler = new ServletContextHandler();
		//servletContextHandler.setContextPath("alma");
		servletContextHandler.addServlet(createServletHolder(applicationContext), "/*");
		return servletContextHandler;
	}
	
	private ServletHolder createServletHolder(ApplicationContext applicationContext) {
		ResourceConfig config = new ResourceConfig();
		config.register(CustomerServiceExceptionMapper.class);
		config.register(new CustomerWebService(applicationContext));
		return new ServletHolder(new ServletContainer(config));
	}
	
}
