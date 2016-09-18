package kits.erp.customerservice.application;

import kits.erp.customerservice.domain.core.CustomerRepository;
import kits.erp.customerservice.domain.core.CustomerService;

public class ApplicationContext {

	private static ApplicationContext instance;
	
	public final CustomerService customerService;

	private ApplicationContext(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public static void init(CustomerRepository customerRepository){
		if(instance == null) {
			instance = new ApplicationContext(new CustomerService(customerRepository));
		} else {
			throw new IllegalStateException("ApplicationContext is already initialized");
		}
	}
	
	public static ApplicationContext get(){
		if(instance == null) {
			throw new IllegalStateException("ApplicationContext is not yet initialized");
		} else {
			return instance;
		}
	}
	
}
