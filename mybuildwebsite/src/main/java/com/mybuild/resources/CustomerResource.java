package com.mybuild.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.mybuild.utilities.CustomerGetMethods;
import com.mybuild.utilities.CustomerPutMethods;

import userModels.Customer;

/**
 * CustomerResource is a class which processes Rest service requests for the customer
 * areas of the MyBuild web service.
 * 
 * The class is called by the 'CustomersResource' class for requests to access or update
 * existing single customer resources (GET, PUT and DELETE requests).  
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class CustomerResource {

	// variables
	
	// To insert contextual objects into the class, e.g. ServletContext, Request, Response, UriInfo
	@Context private UriInfo uriInfo;
	@Context private Request request;
	
	private String customerId;
	private Customer customer;
	
	/**
	 * constructor
	 * @param uriInfo URI information
	 * @param request request
	 * @param id customer ID address as a String
	 */
	public CustomerResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.customerId = id;
	}

	/**
	 * retrieve a single customer by id in application JSON format
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerJSON(@HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving customer: " + customerId + ", in application JSON format");
		return CustomerGetMethods.retrieveCustomerById(customerId, requestToken);
	}
    
	/**
	 * update an existing customer using application JSON data
	 * @param cust Customer object
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putCustomerJSON(JAXBElement<Customer> cust, @HeaderParam("authorization") String requestToken) {
		customer = cust.getValue();
		System.out.println("Updating customer from JSON application data: " + customer.getCustomer_id());
		return CustomerPutMethods.putAndGetResponse(customer, uriInfo, requestToken);
	}
	
}


