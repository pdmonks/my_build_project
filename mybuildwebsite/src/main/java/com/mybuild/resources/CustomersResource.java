package com.mybuild.resources;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import userModels.Customer;

import com.mybuild.utilities.CustomerPostMethods;

/**
 * CustomersResource is a class which captures Rest service requests for the
 * customer areas of the MyBuild web service.
 * 
 * The class calls the appropriate utility class for GET requests involving
 * multiple object responses and POST requests for new customers.
 * 
 * The class calls the 'CustomerResource' class for requests to access or update
 * existing single customer resources (GET, PUT and DELETE requests).  
 * 
 * @author Peter Monks
 * @version 1.0
 */

// The path will map the resource to the URL customers
@Path("/customer")
public class CustomersResource {

	// variables
	
	// To insert contextual objects into the class, e.g. ServletContext, Request, Response, UriInfo
	@Context private UriInfo uriInfo;
	@Context private Request request;
	
	private Customer customer;
	// private CustomerDTO customerDTO;
	
	/**
	 * create a new customer with JSON data
	 * @param customerJAXB Customer object
	 * @return HTTP response code, with requested data if successful
	 * @throws IOException
	 */
	// create a new customer using JSON data object
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Response newCustomerJSON(JAXBElement<Customer> customerJAXB) throws IOException {
		customer = customerJAXB.getValue();
		System.out.println("Creating new customer from JSON data: " + customer.getCustomer_email());
		return CustomerPostMethods.createNewCustomerJSON(customer);
	}
	
	/**
	 * customer login
	 * @param customerJAXB Customer object
	 * @return HTTP response code, with requested data if successful
	 */
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginCustomerJSON(JAXBElement<Customer> customerJAXB) {
		customer = customerJAXB.getValue();
		System.out.println("Logging customer into account");
		return CustomerPostMethods.loginCustomerJSON(customer);
	}
	
	/**
	 * customer logout
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@POST
	@Path("logout")
	public Response logoutCustomerJSON(@HeaderParam("authorization") String requestToken) {
		System.out.println("Logging customer out of account");
		return CustomerPostMethods.logoutCustomerJSON(requestToken);
	}
	
	/**
	 * create a CustomerResource object passing the next path parameter after customers as a parameter
	 * @param id ID of the customer to be the resource 
	 * @return CustomerResource object
	 */
	@Path("{id}")
	public CustomerResource getCustomer(@PathParam("id") String id) {
		System.out.println("New resource");
		return new CustomerResource(uriInfo, request, id);
	}
	
}
