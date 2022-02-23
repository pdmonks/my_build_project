package com.mybuild.utilities;

import javax.ws.rs.core.Response;

import userModels.Feedback;
import userModels.FeedbackDAO;

/**
 * FeedbackPostMethods is a utility which executes all POST requests submitted to 
 * the MyBuild web application Rest feedback services.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class FeedbackPostMethods {

	// variables
	private static FeedbackDAO dao = FeedbackDAO.getFeedbackDAO();
    
    /**
     * create a new Feedback based on object submitted from JAXB element in Rest service (JSON)
     * @param feedback Feedback object
     * @return HTTP response code, with requested data if successful
     */
    public static Response createNewFeedbackJSON(Feedback feedback) {
    	int responseCode = 400;
    	
    	// get current date for feedback date
    	java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
    	feedback.setFeedback_date(date);
    	
    	try {
			if (dao.createFeedback(feedback) == 1) {
				responseCode = 201;
			};
		}
		catch (RuntimeException exception) {
			System.out.println("Resource could not be created with this data");
		}
    	System.out.println("returning response: " + responseCode);
    	return Response.status(responseCode).build();
    	
    }
	
}
