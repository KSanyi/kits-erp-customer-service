package kits.erp.customerservice;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import kits.erp.customerservice.application.ApplicationContext;
import kits.erp.customerservice.infrastructure.database.TestCustomerRepository;
import kits.erp.customerservice.infrastructure.webservice.CustomerWebService;

public class Main {

	public static void main(String[] args) throws Exception {

		ApplicationContext applicationContext = new ApplicationContext(new TestCustomerRepository(100));

		URI baseUri = UriBuilder.fromUri("http://localhost/").port(8888).build();
		ResourceConfig config = new ResourceConfig();
		config.register(new CustomerWebService(applicationContext));
		JettyHttpContainerFactory.createServer(baseUri, config);
	}

}
