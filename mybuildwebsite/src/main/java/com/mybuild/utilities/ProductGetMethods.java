package com.mybuild.utilities;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import productModels.CPU;
import productModels.CPUDAO;
import productModels.Cooler;
import productModels.CoolerDAO;
import productModels.GPU;
import productModels.GPUDAO;
import productModels.Motherboard;
import productModels.MotherboardDAO;
import productModels.PC_Case;
import productModels.PC_CaseDAO;
import productModels.Power_Supply;
import productModels.Power_SupplyDAO;
import productModels.RAM;
import productModels.RAMDAO;
import productModels.Storage;
import productModels.StorageDAO;

/**
 * ProductGetMethods is a utility which executes all GET requests submitted to 
 * the MyBuild web application Rest product services.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class ProductGetMethods {

	// variables
	private static CPUDAO cpuDAO = CPUDAO.getCPUDAO();
	private static ArrayList<CPU> cpuList;
	private static MotherboardDAO motherboardDAO = MotherboardDAO.getMotherboardDAO();
	private static ArrayList<Motherboard> motherboardList;
	private static CoolerDAO coolerDAO = CoolerDAO.getCoolerDAO();
	private static ArrayList<Cooler> coolerList;
	private static RAMDAO ramDAO = RAMDAO.getRAMDAO();
	private static ArrayList<RAM> ramList;
	private static GPUDAO gpuDAO = GPUDAO.getGPUDAO();
	private static ArrayList<GPU> gpuList;
	private static StorageDAO storageDAO = StorageDAO.getStorageDAO();
	private static ArrayList<Storage> storageList;
	private static PC_CaseDAO pc_caseDAO = PC_CaseDAO.getPC_CaseDAO();
	private static ArrayList<PC_Case> pc_caseList;
	private static Power_SupplyDAO power_supplyDAO = Power_SupplyDAO.getPower_SupplyDAO();
	private static ArrayList<Power_Supply> power_supplyList;
	
	private static Gson gson = new Gson();
	
	/**
	 * utility to convert lists of objects to JSON formatted response data
	 * @param list list of objects to convert to JSON data
	 * @return HTTP response code, with requested data if successful
	 */
	private static Response listToJsonResponse(ArrayList list) {
		// need to use Gson as returning a response, not just an object
		String jsonResponse = gson.toJson(list);
		System.out.println(jsonResponse);
		return Response.ok(jsonResponse).build();
	}
	
	/**
	 * Retrieve CPU options, based on usage and budget criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response retrieveCPUs(String usage, String budget, String requestToken) {
		cpuList = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				cpuList = cpuDAO.retrieveCPUByUsageAndBudget(usage.charAt(0), budget.charAt(0));
				if(cpuList == null) {
					responseCode = 400;
				} else {
					return listToJsonResponse(cpuList);
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
	
	/**
	 * Retrieve Motherboard options, based on usage, budget and CPU socket criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param socket CPU socket criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response retrieveMotherboards(String usage, String budget, String socket, String requestToken) {
		motherboardList = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				motherboardList = motherboardDAO.retrieveMotherboardBySocket(usage.charAt(0), budget.charAt(0), socket);
				if(motherboardList == null) {
					responseCode = 400;
				} else {
					return listToJsonResponse(motherboardList);
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
	
	/**
	 * Retrieve Cooler options, based on usage, budget and CPU socket criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param socket CPU socket criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response retrieveCoolers(String usage, String budget, String socket, String requestToken) {
		coolerList = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				coolerList = coolerDAO.retrieveCoolerBySocket(usage.charAt(0), budget.charAt(0), socket);
				if(coolerList == null) {
					responseCode = 400;
				} else {
					return listToJsonResponse(coolerList);
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
	
	/**
	 * Retrieve RAM options, based on usage, budget, capacity and speed criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param capacity capacity criteria
	 * @param speed speed criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response retrieveRAM(String usage, String budget, String capacity, String speed, String requestToken) {
		ramList = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				ramList = ramDAO.retrieveRAMByCapacityAndSpeed(usage.charAt(0), budget.charAt(0), Integer.parseInt(capacity), Integer.parseInt(speed));
				if(ramList == null) {
					responseCode = 400;
				} else {
					return listToJsonResponse(ramList);
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
	
	/**
	 * Retrieve GPU options, based on usage and budget criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response retrieveGPU(String usage, String budget, String requestToken) {
		gpuList = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				gpuList = gpuDAO.retrieveGPUs(usage.charAt(0), budget.charAt(0));
				if(gpuList == null) {
					responseCode = 400;
				} else {
					return listToJsonResponse(gpuList);
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
	
	/**
	 * Retrieve Storage options, based on usage, budget and connection type criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param type connection type criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response retrieveStorage(String usage, String budget, String type, String requestToken) {
		storageList = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				storageList = storageDAO.retrieveStorageByType(usage.charAt(0), budget.charAt(0), type);
				if(storageList == null) {
					responseCode = 400;
				} else {
					return listToJsonResponse(storageList);
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
	
	/**
	 * Retrieve Case options, based on usage, budget and form factor criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param formFactor form factor criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if successful
	 */
	public static Response retrieveCase(String usage, String budget, String formFactor, String requestToken) {
		pc_caseList = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				pc_caseList = pc_caseDAO.retrievePC_CaseByFormFactor(usage.charAt(0), budget.charAt(0), formFactor);
				if(pc_caseList == null) {
					responseCode = 400;
				} else {
					return listToJsonResponse(pc_caseList);
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
	
	/**
	 * Retrieve Power Supply options, based on usage, budget and power requirement criteria
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param powerRequired power requirement criteria
	 * @param requestToken authorisation token
	 * @return HTTP response code, with requested data if succesful
	 */
	public static Response retrievePowerSupply(String usage, String budget, String powerRequired, String requestToken) {
		power_supplyList = null;
		int responseCode = 500;
		try {
			if (CustomerCheckToken.checkTokenMatch(requestToken)) {
				power_supplyList = power_supplyDAO.retrievePower_SupplyByPowerRequired(usage.charAt(0), budget.charAt(0), powerRequired);
				if(power_supplyList == null) {
					responseCode = 400;
				} else {
					return listToJsonResponse(power_supplyList);
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

