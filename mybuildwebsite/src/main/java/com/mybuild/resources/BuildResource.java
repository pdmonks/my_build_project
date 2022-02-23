package com.mybuild.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import buildModels.BuildNewDTO;

import com.mybuild.utilities.BuildPutMethods;

/**
 * BuildResource is a class which processes Rest service requests for the build
 * areas of the MyBuild web service.
 * 
 * The class is called by the 'BuildsResource' class for requests to access or update
 * existing single build resources (GET, PUT and DELETE requests).  
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class BuildResource {

	// variables
	
	// To insert contextual objects into the class, e.g. ServletContext, Request, Response, UriInfo
	@Context private UriInfo uriInfo;
	@Context private Request request;
	
	private String buildId;
	private BuildNewDTO buildNewDTO;
	
	/**
	 * constructor
	 * @param uriInfo URI information
	 * @param request request
	 * @param id build id address as a String
	 */
	public BuildResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.buildId = id;
	}
    
	/**
	 * update an existing build using application JSON data
	 * @param buildNewDTOJAXB BuildNewDTO object
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putBuildJSON(JAXBElement<BuildNewDTO> buildNewDTOJAXB, @HeaderParam("authorization") String requestToken) {
		buildNewDTO = buildNewDTOJAXB.getValue();
		System.out.println("Updating build from JSON application data: " + buildNewDTO.getBuild_id());
		return BuildPutMethods.putAndGetResponse(buildNewDTO, uriInfo, requestToken);
	}
	
}