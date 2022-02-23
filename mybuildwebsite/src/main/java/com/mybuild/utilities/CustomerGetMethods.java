package com.mybuild.utilities;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import userModels.Customer;
import userModels.CustomerDAO;

/**
 * CustomerGetMethods is a utility which executes all GET requests submitted to 
 * the MyBuild web application Rest customer services.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class CustomerGetMethods {

	// variables
	private static CustomerDAO dao = CustomerDAO.getCustomerDAO();
	private static Customer customer;
	
	/**
	 * retrieve Customer records from the DB by customer ID
	 * @param id customer ID
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response retrieveCustomerById(String id, String requestToken) {
		customer = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				customer = dao.retrieveCustomerById(id);
				if(customer == null) {
					responseCode = 400;
				} else {
					// need to use Gson as returning a response, not just an object
					Gson gson = new Gson();
					String res = gson.toJson(customer);
					System.out.println(res);
					return Response.ok(res).build();
				}
			} else {
				responseCode = 401;
			}
		}
		catch (RuntimeException exception) {
			System.out.println("The requested resource was not found");
		}
		return Response.status(responseCode).build();
	}
	
}

