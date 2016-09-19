package kits.erp.customerservice.infrastructure.webservice;

import java.lang.invoke.MethodHandles;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerServiceServer extends Server {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	public CustomerServiceServer(int httpPort) {
		super(httpPort);
		setHandler(createServletContextHandler());
		logger.info("KITS-ERP Customer Service initialised on port " + httpPort);
	}
	
	public void startServer() throws Exception {
		super.start();
		logger.info("KITS-ERP Customer Service started");
		super.join();
	}
	
	private ServletContextHandler createServletContextHandler() {
		ServletContextHandler servletContextHandler = new ServletContextHandler();
		//servletContextHandler.setContextPath("");
		servletContextHandler.addServlet(createServletHolder(), "/*");
		
		return servletContextHandler;
	}
	
	private ServletHolder createServletHolder() {
		ResourceConfig config = new ResourceConfig();
		config.register(CustomerServiceExceptionMapper.class);
		config.register(CustomerWebService.class);
		return new ServletHolder(new ServletContainer(config));
	}
	
}
