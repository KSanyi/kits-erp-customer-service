package kits.erp.customerservice.infrastructure.webservice;

import java.util.List;
import java.util.Map;

import org.httprpc.RPC;
import org.httprpc.WebService;

import kits.erp.customerservice.application.ApplicationContext;
import kits.erp.customerservice.domain.core.Customer;
import kits.erp.customerservice.domain.core.CustomerService;
import kits.util.collections.Mappers;

public class CustomerWebService extends WebService {

	private final CustomerService customerService;
	
	public CustomerWebService() {
		customerService = ApplicationContext.get().customerService;
	}
	
	@RPC(method="GET", path="allCustomers")
	public List<Map<String, ?>> allCustomers(){
		List<Customer> customers = customerService.loadAllCustomers();
		return Mappers.map(customers, WebServiceDataMappers::mapCustomer);
	}
	
}
