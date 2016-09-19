package kits.erp.customerservice.infrastructure.webservice;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import kits.erp.customerservice.application.ApplicationContext;
import kits.erp.customerservice.domain.core.CustomerId;
import kits.erp.customerservice.domain.core.CustomerService;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerWebService {

	private final CustomerService customerService;
	
	public CustomerWebService() {
		customerService = ApplicationContext.get().customerService;
	}
	
	@GET
	public JsonArray allCustomers(@DefaultValue("") @QueryParam("searchString") String searchString){
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		customerService.findCustomer(searchString).stream()
			.map(WebServiceDataMappers::mapToJsonObject)
			.forEach(arrayBuilder::add);
		return arrayBuilder.build();
	}
	
	@GET
	@Path("{customerId}")
	public JsonObject loadCustomer(@PathParam("customerId") CustomerId customerId){
		return customerService.loadCustomer(customerId)
				.map(WebServiceDataMappers::mapToJsonObject)
				.orElse(null);
	}
	
	@DELETE
	@Path("{customerId}")
	public void deleteCustomer(@PathParam("customerId") CustomerId customerId){
		customerService.deleteCustomer(customerId);
	}
	
	@POST
	public String createCustomer(JsonObject jsonObject){
		return customerService.createCustomer(WebServiceDataMappers.mapToCustomerData(jsonObject)).toString();
	}
	
}
