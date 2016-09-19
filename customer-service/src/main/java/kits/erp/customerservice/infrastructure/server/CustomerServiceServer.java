package kits.erp.customerservice.infrastructure.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kits.erp.customerservice.infrastructure.webservice.CustomerWebService;

public class CustomerServiceServer extends Server {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceServer.class);
	
	public CustomerServiceServer(int httpPort) {
		super(httpPort);
		setHandler(createServletContextHandler());
		logger.info("KITS-ERP Server initialised on port " + httpPort);
	}
	
	public void startServer() throws Exception {
		super.start();
		logger.info("Erp Server started");
		super.join();
	}
	
	private ServletContextHandler createServletContextHandler() {
		ServletContextHandler servletContextHandler = new ServletContextHandler();
		//servletContextHandler.setContextPath("");
		servletContextHandler.addServlet(createServletHolder(), "/*");
		
		return servletContextHandler;
	}
	
	private ServletHolder createServletHolder() {
		ServletHolder servletHolder = new ServletHolder(ServletContainer.class);
		servletHolder.setInitParameter( "jersey.config.server.provider.classnames", CustomerWebService.class.getCanonicalName());
		return servletHolder;
	}
	
}
