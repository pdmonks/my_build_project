package com.mybuild.utilities;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import userModels.Customer;
import userModels.CustomerDAO;
//import model.Film;
//import model.FilmDAO;
//import model.FilmDTO;
//import model.FilmUtilities;
import userModels.UserUtilities;

/**
 * CustomerPutMethods is a utility which executes all PUT requests submitted to 
 * the MyBuild web application Rest customer services.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class CustomerPutMethods {

	// variables
	private static CustomerDAO dao = CustomerDAO.getCustomerDAO();
	private static Customer customer;
	
	/**
	 * execute PUT requests from Customer Rest services
	 * @param customer Customer object
	 * @param uriInfo URI information
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response putAndGetResponse(Customer cust, UriInfo uriInfo, String requestToken) {
		
		// variables
		// Response response = null;
		int responseCode = 500;
		
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				
				customer = dao.retrieveCustomerById(String.valueOf(cust.getCustomer_id()));
				
				if (customer != null) {
					// response = Response.noContent().build();
					
					// update customer object with fields to update
					if (cust.getCustomer_surname() != null) {
						customer.setCustomer_surname(cust.getCustomer_surname());
					}
					if (cust.getCustomer_forename() != null) {
						customer.setCustomer_forename(cust.getCustomer_forename());
					}
					if (cust.getCustomer_address() != null) {
						customer.setCustomer_address(cust.getCustomer_address());
					}
					if (cust.getCustomer_town() != null) {
						customer.setCustomer_town(cust.getCustomer_town());
					}
					if (cust.getCustomer_postcode() != null) {
						customer.setCustomer_postcode(cust.getCustomer_postcode());
					}
					if (cust.getCustomer_telephone() != null) {
						customer.setCustomer_telephone(cust.getCustomer_telephone());
					}
					if (cust.getCustomer_email() != null) {
						customer.setCustomer_email(cust.getCustomer_email());
					}
					if (cust.getCustomer_password() != null
						&& !UserUtilities.hashString(cust.getCustomer_password()).equals(customer.getCustomer_password())) {
						customer.setCustomer_password(UserUtilities.hashString(cust.getCustomer_password()));
					}
					
					// update the record in the DB
					dao.updateCustomer(customer);
					
					responseCode = 200;
				} else {
					// response = Response.created(uriInfo.getAbsolutePath()).build();
					responseCode = 404;
				}
			} else {
				responseCode = 401;
			}
		}
		catch(RuntimeException exception) {
			System.out.println("Error, please try again");
		}
		// return response;
		return Response.status(responseCode).build();
	}
	
}
