package com.mybuild.resources;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

import buildModels.BuildNewDTO;

import com.mybuild.utilities.BuildGetMethods;
import com.mybuild.utilities.BuildPostMethods;

/**
 * BuildsResource is a class which captures Rest service requests for the
 * build areas of the MyBuild web service.
 * 
 * The class calls the appropriate utility class for GET requests involving
 * multiple object responses and POST requests for new builds.
 * 
 * The class calls the 'BuildResource' class for requests to access or update
 * existing single build resources (GET, PUT and DELETE requests).  
 * 
 * @author Peter Monks
 * @version 1.0
 */

// The path will map the resource to the URL customers
@Path("/build")
public class BuildsResource {

	// variables
	
	// To insert contextual objects into the class, e.g. ServletContext, Request, Response, UriInfo
	@Context private UriInfo uriInfo;
	@Context private Request request;
	
	private BuildNewDTO buildNewDTO;
	
	/**
	 * return an array list of builds by customer id in application JSON format
	 * @param id customer ID
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@GET
	@Path("customer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBuildsByAttributeJSON(@PathParam("id") String id, @HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving builds by customer id in application JSON data format");
		return BuildGetMethods.retrieveBuildsByCustomer(id, requestToken);  // utility method to retrieve builds by customer id
	}
	
	/**
	 * create a new build with first component selection
	 * @param buildNewDTOJAXB BuildNewDTO object
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 * @throws IOException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newBuildJSON(JAXBElement<BuildNewDTO> buildNewDTOJAXB, @HeaderParam("authorization") String requestToken) throws IOException {
		System.out.println(buildNewDTOJAXB.getValue());
		buildNewDTO = buildNewDTOJAXB.getValue();
		System.out.println("Creating new build from JSON data for customer ID: " + buildNewDTO.getCustomer_id());
		return BuildPostMethods.createNewBuildJSON(buildNewDTO, requestToken);
	}
	
	/**
	 * create a BuildResource object passing the next path parameter after build as a parameter
	 * @param id id of the build to be the resource 
	 * @return BuildResource object
	 */
	@Path("{id}")
	public BuildResource getBuild(@PathParam("id") String id) {
		System.out.println("New resource");
		return new BuildResource(uriInfo, request, id);
	}
	
}