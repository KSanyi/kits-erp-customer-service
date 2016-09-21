package kits.erp.customerservice.infrastructure.webservice;

import java.lang.invoke.MethodHandles;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kits.erp.customerservice.application.ApplicationContext;

public class CustomerWebServiceServer {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final Server server;
	
	public CustomerWebServiceServer(ApplicationContext applicationContext, int httpPort){
		
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(httpPort).build();
		ResourceConfig config = new ResourceConfig();
		config.register(new CustomerWebService(applicationContext));
		server = JettyHttpContainerFactory.createServer(baseUri, config, false);
		
		logger.info("CustomerWebServiceServer initialized on port " + httpPort);
	}
	
	public void start() throws Exception {
		server.start();
		logger.info("CustomerWebServiceServer started");
	}
	
}
