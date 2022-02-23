package com.mybuild.utilities;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import buildModels.Build;
import buildModels.BuildDAO;

/**
 * BuildGetMethods is a utility which executes all GET requests submitted to 
 * the MyBuild web application Rest build services.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class BuildGetMethods {

	// variables
	private static BuildDAO dao = BuildDAO.getBuildDAO();
	private static ArrayList<Build> buildList;
	
	/**
	 * retrieve a customer's build records from the DB
	 * @param id customer ID
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response retrieveBuildsByCustomer(String id, String requestToken) {
		
		buildList = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				buildList = dao.retrieveBuildHistoryByCustomerId(id);
				if(buildList == null) {
					responseCode = 400;
				} else {
					// need to use Gson as returning a response, not just an object
					Gson gson = new Gson();
					String res = gson.toJson(buildList);
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
