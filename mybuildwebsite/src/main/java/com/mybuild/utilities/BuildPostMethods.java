package com.mybuild.utilities;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import buildModels.Build;
import buildModels.BuildDAO;
import buildModels.BuildNewDTO;
import buildModels.Build_Line;
//import userModels.Customer;
//import userModels.CustomerDAO;
// import userModels.CustomerDTO;

/**
 * BuildPostMethods is a utility which executes all POST requests submitted to 
 * the MyBuild web application Rest build services.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class BuildPostMethods {

	// variables
	private static BuildDAO dao = BuildDAO.getBuildDAO();
	private static Build build;
	private static ArrayList<Build> buildList;
	private static Build_Line buildLine;
	private static ArrayList<Build_Line> buildLineList;
	private static int webStaffID = 9999;					// default staff id for web sales
	private static double vatRate = 20;						// VAT rate at time of build
	private static String customerID;
	private static int buildID;
	
	/**
	 * create a new Build based on object submitted from JAXB element in Rest service (JSON)
	 * @param buildNewDTO BuildNewDTO object
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
    public static Response createNewBuildJSON(BuildNewDTO buildNewDTO, String requestToken) {
    	String responseJson = "";
    	
    	// get current date for build date
    	java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
    	
    	// create build object from request data
    	build = new Build(
    		buildNewDTO.getCustomer_id(),
    		webStaffID,								
    		date,									// current date for build date
    		null,									// dispatch date not yet known
    		buildNewDTO.getBuild_status(),
    		buildNewDTO.getBuild_flag_usage(),
    		buildNewDTO.getBuild_flag_budget(),
    		buildNewDTO.getBuild_delivery_charge(),
    		buildNewDTO.getBuild_total(),
    		vatRate									
    	);
    	
    	// create build_Line object from request data for first component added (CPU)
    	buildLine = new Build_Line(
    		buildNewDTO.getProduct_id(),
    		buildNewDTO.getBuild_line_product_type(),
    		buildNewDTO.getBuild_line_product_description(),
    		buildNewDTO.getBuild_line_price(),
    		buildNewDTO.getBuild_line_quantity()
    	);
    	
    	// add build_Line to build
    	buildLineList = new ArrayList<Build_Line>();
    	buildLineList.add(buildLine);
    	build.setBuildLines(buildLineList);
    	
    	customerID = String.valueOf(buildNewDTO.getCustomer_id());
    	
    	try {
			if (dao.createBuild(build) == 1) {
				// get the latest build id for this customer to send back to client side
				buildList = dao.retrieveBuildsByCustomerId(customerID);
				buildID = buildList.get(0).getBuild_id();
				responseJson = "{\"build_id\": " + buildID + "}";
			};
		}
		catch (RuntimeException exception) {
			System.out.println("Resource could not be created with this data");
		}
    	System.out.println("returning responseJSON: " + responseJson);
		if (!responseJson.equals("")) {
			return Response.ok(responseJson).build();
		}
		return Response.status(400).build();
    }
	
}
