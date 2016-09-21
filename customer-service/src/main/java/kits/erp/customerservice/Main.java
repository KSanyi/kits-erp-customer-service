package kits.erp.customerservice;

import kits.erp.customerservice.application.ApplicationContext;
import kits.erp.customerservice.infrastructure.database.TestCustomerRepository;
import kits.erp.customerservice.infrastructure.webservice.CustomerWebServiceServer;

public class Main {

	public static void main(String[] args) throws Exception {

		ApplicationContext applicationContext = new ApplicationContext(new TestCustomerRepository(100));
		
		new CustomerWebServiceServer(applicationContext, 8888).start();
	}

}
