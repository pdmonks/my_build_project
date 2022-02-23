package com.mybuild.utilities;

import userModels.Customer;
import userModels.CustomerDAO;

/**
 * CustomerCheckToken is a utility which checks an authorisation token parameter 
 * against a Customer session token in the database.
 * Returns TRUE if the tokens match, FALSE if not.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class CustomerCheckToken {

	private static Customer customer;
	private static CustomerDAO dao = CustomerDAO.getCustomerDAO();
	
	public static boolean checkTokenMatch(String requestToken) {
		
		boolean tokenMatch = false;
		
		System.out.println("Checking request token: " + requestToken);
		
		try {
	    	customer = dao.retrieveCustomerByToken(requestToken);
	    	String dbToken = customer.getCustomer_token();
	    	System.out.println("Retrieved token: " + dbToken);
	    	
	    	if (requestToken.equals(dbToken)) {
	    		System.out.println("Tokens match");
	    		tokenMatch = true;
	    	} else {
	    		System.out.println("Tokens do not match");
	    	}
    	}
    	catch (RuntimeException exception) {
			System.out.println("Error retrieving account details");
		}
		
		return tokenMatch;
	}
	
}
