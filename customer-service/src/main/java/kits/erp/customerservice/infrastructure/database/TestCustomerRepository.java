package kits.erp.customerservice.infrastructure.database;

import java.util.LinkedList;
import java.util.List;

import kits.erp.customerservice.domain.core.Customer;
import kits.erp.customerservice.domain.core.CustomerData;
import kits.erp.customerservice.domain.core.CustomerId;
import kits.erp.customerservice.domain.core.CustomerRepository;

public class TestCustomerRepository implements CustomerRepository {

	private final List<Customer> customers;
	
	private final TestCustomerFactory testCustomerFactory = new TestCustomerFactory(); 
	
	public TestCustomerRepository(int numberOfCustomers) {
		customers = generateRandomCustomers(numberOfCustomers);
	}
	
	public List<Customer> loadAllCustomers() {
		return customers;
	}

	public void saveCustomer(Customer customer) {
		customers.add(customer);
	}

	public void updateCustomer(CustomerId customerId, CustomerData customerData) {
		deleteCustomer(customerId);
		saveCustomer(new Customer(customerId, customerData));
	}

	public void deleteCustomer(CustomerId customerId) {
		customers.stream().filter(c -> c.customerId.equals(customerId)).findAny().ifPresent(customers::remove);
	}
	
	private List<Customer> generateRandomCustomers(int n) {
		List<Customer> customers = new LinkedList<Customer>();
		for(int i=0;i<n;i++) {
			customers.add(testCustomerFactory.createRandomCustomer());
		}
		return customers;
	}

	@Override
	public boolean doesCustomerIdExist(CustomerId customerId) {
		return customers.stream().filter(c -> c.customerId.equals(customerId)).findAny().isPresent();
	}
	
}
