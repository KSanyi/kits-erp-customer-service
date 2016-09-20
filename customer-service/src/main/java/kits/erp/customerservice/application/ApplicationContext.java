package kits.erp.customerservice.application;

import kits.erp.customerservice.domain.core.CustomerRepository;
import kits.erp.customerservice.domain.core.CustomerService;

public class ApplicationContext {

	public final CustomerService customerService;

	public ApplicationContext(CustomerRepository customerRepository) {
		this.customerService = new CustomerService(customerRepository);
	}
	
}
