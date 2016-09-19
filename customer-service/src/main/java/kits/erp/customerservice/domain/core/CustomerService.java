package kits.erp.customerservice.domain.core;

import java.util.List;
import java.util.Optional;

import kits.util.collections.Mappers;

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
	
	public Optional<Customer> loadCustomer(CustomerId customerId){
		return customerRepository.loadAllCustomers().stream()
		.filter(c -> c.customerId.equals(customerId))
		.findAny();
	}
	
	public List<Customer> findCustomer(String searchString){
		return Mappers.filter(loadAllCustomers(), c -> c.matches(searchString));
	}
	
}
