package kits.erp.customerservice.infrastructure.webservice;

import java.lang.invoke.MethodHandles;
import java.net.URI;

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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kits.erp.customerservice.application.ApplicationContext;
import kits.erp.customerservice.domain.core.CustomerId;
import kits.erp.customerservice.domain.core.CustomerService;

@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerWebService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final CustomerService customerService;

	public CustomerWebService(ApplicationContext ac) {
		customerService = ac.customerService;
	}

	@GET
	public JsonArray findCustomers(@DefaultValue("") @QueryParam("searchString") String searchString) {
		logger.debug("Searching customers with searchString: '" + searchString + "'");
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		customerService.findCustomer(searchString).stream()
			.map(WebServiceDataMappers::mapToJsonObject)
			.forEach(arrayBuilder::add);
		return arrayBuilder.build();
	}

	@GET
	@Path("{customerId}")
	public JsonObject loadCustomer(@PathParam("customerId") CustomerId customerId) {
		logger.debug("Loading customer with customerId: " + customerId);
		return customerService.loadCustomer(customerId)
				.map(WebServiceDataMappers::mapToJsonObject)
					.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
	}

	@DELETE
	@Path("{customerId}")
	public void deleteCustomer(@PathParam("customerId") CustomerId customerId) {
		logger.debug("Deleting customer with customerId: " + customerId);
		boolean customerFound = customerService.deleteCustomer(customerId);
		if(!customerFound){
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@POST
	public Response createCustomer(JsonObject jsonObject) {
		logger.debug("Creating customer with customerdata: " + jsonObject);
		CustomerId customerId = customerService.createCustomer(WebServiceDataMappers.mapToCustomerData(jsonObject));
		URI createdUri = URI.create("customer/" + customerId.toString());
		return Response.created(createdUri).build();
	}

}
