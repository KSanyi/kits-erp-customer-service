package kits.erp.customerservice;

import kits.erp.customerservice.application.ApplicationContext;
import kits.erp.customerservice.infrastructure.webservice.CustomerServiceServer;

public class TestMain {

	public static void main(String[] args) throws Exception {

		ApplicationContext applicationContext = new ApplicationContext(new TestCustomerRepository(100));
		
		new CustomerServiceServer(8888, applicationContext).startServer();
	}

}
