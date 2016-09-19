package kits.erp.customerservice.infrastructure.webservice;

import java.lang.invoke.MethodHandles;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kits.erp.customerservice.domain.core.CustomerServiceException;

@Provider
class CustomerServiceExceptionMapper implements ExceptionMapper<Exception> {
    
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Override
    public Response toResponse(Exception ex) {
    	
    	if(ex instanceof CustomerServiceException) {
    		logger.error(ex.toString());
    		return Response
    				.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.entity(ex.getMessage())
    				.type("text/plain")
    				.build();
    	} else {
    		logger.error("Unexpected error: ", ex);
    		return Response
    				.status(Response.Status.INTERNAL_SERVER_ERROR)
    				.type("text/plain")
    				.build();
    	}
        
    }
}
