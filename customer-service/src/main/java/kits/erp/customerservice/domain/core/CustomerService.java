package kits.erp.customerservice.domain.core;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kits.util.collections.Mappers;

public class CustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public CustomerId createCustomer(CustomerData customerData){
		validateCustomerData(customerData);
		CustomerId customerId = generateCustomerId();
		Customer customer = new Customer(customerId, customerData);
		customerRepository.saveCustomer(customer);
		logger.info("Customer with name " + customerData.name + " created with customer id " + customerId);
		return customerId;
	}
	
	private CustomerId generateCustomerId() {
		for(int attempt = 0;attempt < 100; attempt++){
			CustomerId customerId = CustomerIdGenerator.generateCustomerId();
			if(!customerRepository.doesCustomerIdExist(customerId)){
				return customerId;
			}
		}
		throw new CustomerServiceException("Could not generate unique customer id");
	}
	
	private void validateCustomerData(CustomerData customerData) {
		
	}
	
	public boolean deleteCustomer(CustomerId customerId) {
		if(loadCustomer(customerId).isPresent()){
			customerRepository.deleteCustomer(customerId);
			logger.info("Customer with customer id " + customerId + " deleted");
			return true;
		} else {
			return false;
		}
	}
	
	public void updateCustomerData(Customer customer, CustomerData updatedCustomerData) {
		customerRepository.updateCustomer(customer.customerId, updatedCustomerData);
	}
	
	public List<Customer> loadAllCustomers() {
		return customerRepository.loadAllCustomers();
	}
	
	public Optional<Customer> loadCustomer(CustomerId customerId){
		Optional<Customer> customer = customerRepository.loadAllCustomers().stream()
		.filter(c -> c.customerId.equals(customerId))
		.findAny();
		
		if(!customer.isPresent()){
			logger.warn("Customer with customerId " + customerId + " not found");
		}
		
		return customer;
	}
	
	public List<Customer> findCustomer(String searchString){
		List<Customer> foundCustomers = Mappers.filter(loadAllCustomers(), c -> c.matches(searchString));
		logger.debug(foundCustomers.size() + " customers found for searchString '" + searchString + "'");
		return foundCustomers;
	}
	
}
