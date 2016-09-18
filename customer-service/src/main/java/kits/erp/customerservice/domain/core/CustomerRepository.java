package kits.erp.customerservice.domain.core;

import java.util.List;

public interface CustomerRepository {

	List<Customer> loadAllCustomers();
	
	void saveCustomer(Customer customer);
	
	void updateCustomer(CustomerId customerId, CustomerData customerData);
	
	void deleteCustomer(CustomerId customerId);
	
}
