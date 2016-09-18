package kits.erp.customerservice.infrastructure.webservice;

import java.util.Map;

import kits.erp.customerservice.domain.core.Customer;
import kits.erp.customerservice.domain.core.CustomerData;
import kits.util.collections.ImmutableMap;

class WebServiceDataMappers {

	static Map<String, ?> mapCustomer(Customer customer){
		
		CustomerData customerData = customer.customerData;
		
		return ImmutableMap.mapOf(
				"id", customer.customerId.value,
				"name", customerData.name.toString(),
				"email", customerData.emailAddress.toString(),
				"address", ImmutableMap.mapOf(
						"town", customerData.address.town,
						"street", customerData.address.street,
						"houseNumber", customerData.address.houseNumber));
	}
	
}
