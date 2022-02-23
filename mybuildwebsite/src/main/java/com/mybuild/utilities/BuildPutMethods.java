package com.mybuild.utilities;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import buildModels.Build;
import buildModels.Build_Line;
import buildModels.BuildDAO;
import buildModels.BuildNewDTO;

/**
 * BuildPutMethods is a utility which executes all PUT requests submitted to 
 * the MyBuild web application Rest build services.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class BuildPutMethods {

	// variables
	private static BuildDAO dao = BuildDAO.getBuildDAO();
	private static Build build;
	private static Build_Line build_line;
	private static List<Build_Line> buildLines;
	private static Response response;
	
	/**
	 * execute PUT requests from Build Rest services
	 * @param buildNewDTO BuildNewDTO object
	 * @param uriInfo URI information
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response putAndGetResponse(BuildNewDTO buildNewDTO, UriInfo uriInfo, String requestToken) {
		
		if (buildNewDTO.getBuild_status().equals("p")) {
			// update build status to 'paid' and update stock for all build components
			response = buildPaid(buildNewDTO, uriInfo, requestToken);
		} else {
			// update build with selected component
			response = updateBuild(buildNewDTO, uriInfo, requestToken);
		}
		
		return response;
	}
	
	/**
	 * update build with selected component
	 * @param buildNewDTO BuildNewDTO object
	 * @param uriInfo URI information
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	private static Response updateBuild(BuildNewDTO buildNewDTO, UriInfo uriInfo, String requestToken) {
		
		// variables
		int responseCode = 500;
		
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				
				// retrieve build from DB
				build = dao.retrieveBuildByBuildId(String.valueOf(buildNewDTO.getBuild_id()));
				
				if (build != null) {
					// update the build
					build.setBuild_delivery_charge(buildNewDTO.getBuild_delivery_charge());
					build.setBuild_total(buildNewDTO.getBuild_total());
					build.setBuild_status(buildNewDTO.getBuild_status());
					System.out.println(build.getBuild_total());
					
					// add a build line for the selected component
					buildLines = build.getBuildLines();
					
					build_line = new Build_Line(buildNewDTO.getProduct_id(),
						buildNewDTO.getBuild_line_product_type(),
						buildNewDTO.getBuild_line_product_description(),
						buildNewDTO.getBuild_line_price(),
						buildNewDTO.getBuild_line_quantity());
					
					buildLines.add(build_line);
					
					build.setBuildLines(buildLines);
					
					// update the record in the DB
					dao.updateBuild(build);
					
					responseCode = 200;
				} else {
					responseCode = 404;
				}
			} else {
				responseCode = 401;
			}
		}
		catch(RuntimeException exception) {
			System.out.println("Error, please try again");
		}
		// return response;
		return Response.status(responseCode).build();
	}
	
	/**
	 * update build status to 'paid' and update stock for all build components
	 * @param buildNewDTO BuildNewDTO object
	 * @param uriInfo URI information
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	private static Response buildPaid(BuildNewDTO buildNewDTO, UriInfo uriInfo, String requestToken) {
		
		// variables
		int responseCode = 500;
		
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				
				// retrieve build from DB
				build = dao.retrieveBuildByBuildId(String.valueOf(buildNewDTO.getBuild_id()));
				
				if (build != null) {
					// update the build status
					build.setBuild_status(buildNewDTO.getBuild_status());
					
					// update the build record in the DB
					dao.updateBuild(build);
					
					// update the product stock records in the DB
					dao.updateBuildStock(build);
					
					responseCode = 200;
				} else {
					responseCode = 404;
				}
			} else {
				responseCode = 401;
			}
		}
		catch(RuntimeException exception) {
			System.out.println("Error, please try again");
		}
		// return response;
		return Response.status(responseCode).build();
	}
	
}
