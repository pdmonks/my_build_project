package com.mybuild.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.mybuild.utilities.ProductGetMethods;

/**
 * ProductsResource is a class which captures Rest service requests for the
 * product areas of the MyBuild web service.
 * 
 * The class calls the appropriate utility class for GET requests involving
 * multiple object responses and POST requests for new products.
 * 
 * The class calls the 'ProductResource' class for requests to access or update
 * existing single product resources (GET, PUT and DELETE requests).  
 * 
 * @author Peter Monks
 * @version 1.0
 */

// The path will map the resource to the URL customers
@Path("/product")
public class ProductsResource {

	// variables
	
	// To insert contextual objects into the class, e.g. ServletContext, Request, Response, UriInfo
	@Context private UriInfo uriInfo;
	@Context private Request request;
	
	/**
	 * Retrieve CPU options, based on usage and budget criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@GET
	@Path("type/cpu/{usage}/{budget}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCPUByUsageAndBudgetJSON(@PathParam("usage") String usage,
			@PathParam("budget") String budget, @HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving CPUs by usage and budget application JSON data format");
		return ProductGetMethods.retrieveCPUs(usage, budget, requestToken);
	}
	
	/**
	 * Retrieve Motherboard options, based on usage, budget and CPU socket criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param socket CPU socket criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@GET
	@Path("type/motherboard/{usage}/{budget}/{socket}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMotherboardJSON(@PathParam("usage") String usage,
			@PathParam("budget") String budget, @PathParam("socket") String socket,
			@HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving Motherboards by usage, budget and socket type in application JSON data format");
		return ProductGetMethods.retrieveMotherboards(usage, budget, socket, requestToken);
	}
	
	/**
	 * Retrieve Cooler options, based on usage, budget and CPU socket criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param socket CPU socket criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@GET
	@Path("type/cooler/{usage}/{budget}/{socket}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCoolerJSON(@PathParam("usage") String usage,
			@PathParam("budget") String budget, @PathParam("socket") String socket,
			@HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving Coolers by usage, budget and socket type in application JSON data format");
		return ProductGetMethods.retrieveCoolers(usage, budget, socket, requestToken);
	}
	
	/**
	 * Retrieve RAM options, based on usage, budget, capacity and speed criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param capacity capacity criteria
	 * @param speed speed criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@GET
	@Path("type/ram/{usage}/{budget}/{capacity}/{speed}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRAMJSON(@PathParam("usage") String usage,
			@PathParam("budget") String budget, @PathParam("capacity") String capacity,
			@PathParam("speed") String speed, @HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving RAM by usage, budget, capacity and speed in application JSON data format");
		return ProductGetMethods.retrieveRAM(usage, budget, capacity, speed, requestToken);
	}
	
	/**
	 * Retrieve GPU options, based on usage and budget criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@GET
	@Path("type/gpu/{usage}/{budget}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGPUJSON(@PathParam("usage") String usage,
			@PathParam("budget") String budget, @HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving GPU by usage and budget in application JSON data format");
		return ProductGetMethods.retrieveGPU(usage, budget, requestToken);
	}
	
	/**
	 * Retrieve Storage options, based on usage, budget and connection type criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param type connection type criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@GET
	@Path("type/storage/{usage}/{budget}/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStorageJSON(@PathParam("usage") String usage,
			@PathParam("budget") String budget, @PathParam("type") String type,
			@HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving Storage by usage, budget and type in application JSON data format");
		return ProductGetMethods.retrieveStorage(usage, budget, type, requestToken);
	}
	
	/**
	 * Retrieve Case options, based on usage, budget and form factor criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param formFactor form factor criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	@GET
	@Path("type/pccase/{usage}/{budget}/{formfactor}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCaseJSON(@PathParam("usage") String usage,
			@PathParam("budget") String budget, @PathParam("formfactor") String formFactor,
			@HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving Case by usage, budget and form factor in application JSON data format");
		return ProductGetMethods.retrieveCase(usage, budget, formFactor, requestToken);
	}
	
	/**
	 * Retrieve Power Supply options, based on usage, budget and power requirement criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param powerRequired power requirement criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if succesful
	 */
	@GET
	@Path("type/powersupply/{usage}/{budget}/{powerrequired}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPowerSupplyJSON(@PathParam("usage") String usage,
			@PathParam("budget") String budget, @PathParam("powerrequired") String powerRequired,
			@HeaderParam("authorization") String requestToken) {
		System.out.println("Retrieving Power Supply by usage, budget and power required in application JSON data format");
		return ProductGetMethods.retrievePowerSupply(usage, budget, powerRequired, requestToken);
	}
	
}
