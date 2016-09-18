package kits.erp.customerservice.domain.core;

import java.util.List;

public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public CustomerId createCustomer(CustomerData customerData){
		validateCustomerData(customerData);
		CustomerId customerId = generateCustomerId();
		Customer customer = new Customer(customerId, customerData);
		customerRepository.saveCustomer(customer);
		return customerId;
	}
	
	private CustomerId generateCustomerId() {
		return new CustomerId("XXX");
	}
	
	private void validateCustomerData(CustomerData customerData) {
		
	}
	
	public void deleteCustomer(CustomerId customerId) {
		customerRepository.deleteCustomer(customerId);
	}
	
	public void updateCustomerData(Customer customer, CustomerData updatedCustomerData) {
		customerRepository.updateCustomer(customer.customerId, updatedCustomerData);
	}
	
	public List<Customer> loadAllCustomers() {
		return customerRepository.loadAllCustomers();
	}
	
}
