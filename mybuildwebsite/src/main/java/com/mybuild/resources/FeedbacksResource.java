package com.mybuild.resources;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import userModels.Feedback;

import com.mybuild.utilities.FeedbackPostMethods;

/**
 * FeedbacksResource is a class which captures Rest service requests for the
 * feedback areas of the MyBuild web service.
 * 
 * The class calls the appropriate utility class for GET requests involving
 * multiple object responses and POST requests for new feedback.
 * 
 * The class calls the 'FeedbackResource' class for requests to access or update
 * existing single feedback resources (GET, PUT and DELETE requests).  
 * 
 * @author Peter Monks
 * @version 1.0
 */

// The path will map the resource to the URL feedback
@Path("/feedback")
public class FeedbacksResource {

	// variables
	private Feedback feedback;
	
	// To insert contextual objects into the class, e.g. ServletContext, Request, Response, UriInfo
	@Context private UriInfo uriInfo;
	@Context private Request request;
	
	/**
	 * create a new feedback with JSON data
	 * @param feedbackJAXB Feedback object
	 * @return HTTP response code, with requested data if successful
	 * @throws IOException
	 */
	// create a new feedback using JSON data object
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newFeedbackJSON(JAXBElement<Feedback> feedbackJAXB) throws IOException {
		feedback = feedbackJAXB.getValue();
		System.out.println("Creating new feedback from JSON data");
		return FeedbackPostMethods.createNewFeedbackJSON(feedback);
	}
	
	/**
	 * create a FeedbackResource object passing the next path parameter after feedback as a parameter
	 * @param id ID of the feedback to be the resource 
	 * @return FeedbackResource object
	 */
	/* @Path("{id}")
	public FeedbackResource getFeedback(@PathParam("id") String id) {
		System.out.println("New resource");
		return new FeedbackResource(uriInfo, request, id);
	} */
	
}
