package com.mybuild.utilities;

import javax.ws.rs.core.Response;

import userModels.Customer;
import userModels.CustomerDAO;
// import userModels.CustomerDTO;
import userModels.UserUtilities;

/**
 * CustomerPostMethods is a utility which executes all POST requests submitted to 
 * the MyBuild web application Rest customer services.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class CustomerPostMethods {

	// variables
	private static CustomerDAO dao = CustomerDAO.getCustomerDAO();
	private static Customer customer;
    
    /**
     * create a new Customer based on object submitted from JAXB element in Rest service (JSON)
     * @param customer Customer object
     * @return HTTP response code, with requested data if successful
     */
    public static Response createNewCustomerJSON(Customer customer) {
    	int responseCode = 400;
    	
    	try {
			if (dao.createCustomer(customer) == 1) {
				responseCode = 201;
			};
		}
		catch (RuntimeException exception) {
			System.out.println("Resource could not be created with this data");
		}
    	System.out.println("returning response: " + responseCode);
    	return Response.status(responseCode).build();
    	
    }
    
    /**
     * customer login process - check credentials and return session token if successful
     * @param cust customer object
     * @return HTTP response code, with requested data if successful
     */
    public static Response loginCustomerJSON(Customer cust) {
    	String custEmail = cust.getCustomer_email();
    	String custPassword = cust.getCustomer_password();
    	String custPasswordHashed = UserUtilities.hashString(custPassword);
    	String custToken = "";
    	int custId;
    	String responseJson = "";
    	
    	try {
	    	customer = dao.retrieveCustomerByEmail(custEmail);
	    	if(customer.getCustomer_email().equals(custEmail)
	    		&& customer.getCustomer_password().equals(custPasswordHashed)) {
	    		custToken = dao.createCustomerToken(custEmail);
	    		customer.setCustomer_token(custToken);
	    		custId = customer.getCustomer_id();
	    		responseJson = "{\"id\": " + custId + ", \"token\": \"" + custToken + "\"}";
	    	}
    	}
    	catch (RuntimeException exception) {
			System.out.println("Error retrieving account details");
		}
    	System.out.println("returning responseJSON: " + responseJson);
		if (!responseJson.equals("")) {
			return Response.ok(responseJson).build();
		}
		return Response.status(400).build();
    }
    
    /**
     * customer logout process
     * @param requestToken
     * @return HTTP response code, with requested data if successful
     */
    public static Response logoutCustomerJSON(String requestToken) {
    	System.out.println(requestToken);
    	int responseCode = 500;
    	
    	try {
    		if (CustomerCheckToken.checkTokenMatch(requestToken)) {
    			dao.removeCustomerToken(requestToken);
    			responseCode = 200;
    		} else {
    			responseCode = 401;
    		}
    	}
    	catch (RuntimeException exception) {
			System.out.println("Error retrieving account details");
		}
    	
    	System.out.println("returning response: " + responseCode);
    	return Response.status(responseCode).build();
    	
    }
	
}
