package kits.erp.customerservice.infrastructure.webservice;

import java.lang.invoke.MethodHandles;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kits.erp.customerservice.domain.core.CustomerServiceException;

@Provider
public class CustomerServiceExceptionMapper implements ExceptionMapper<CustomerServiceException> {
    
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Override
    public Response toResponse(CustomerServiceException ex) {
		logger.error(ex.toString());
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(ex.getMessage())
				.type("text/plain")
				.build();
        
    }
}
