package productModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

/**
 * GPUDAO allows the data and methods of the 'GPU' class to be
 * accessed indirectly, protecting the integrity of the original class.
 * Applications can utilise this class to perform CRUD operations on product
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class GPUDAO {

	// variables
	private static GPUDAO gpuDAO;
	private String query;
	private GPU gpu;
	private ArrayList<GPU> gpuList;
	private String objectType = "class productModels.GPU";
	
	/** private constructor */
	private GPUDAO() {}
	
	/** singleton object manager
	 * @return GPUDAO object (new object only if not already instantiated)
	 */
	public static synchronized GPUDAO getGPUDAO() {
		if(gpuDAO == null) {
			gpuDAO = new GPUDAO();
		}
		return gpuDAO;
	}

	/** overridden clone constructor
	 * prevents construction of clone objects
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/** clone constructor
	 * prevents construction of clone objects
	 * @param clone GPUDAO clone object
	 */
	public GPUDAO(GPUDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by product id value
	 * @param id product ID
	 * @return GPU object
	 */
	public GPU retrieveGPUById(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveGPUById");
		query = ("FROM GPU WHERE product_id = '" + id + "'");
		gpuList = queryGPU(query);
		if (gpuList.size() > 0) {
			return gpuList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product budget value
	 * @param budget budget criteria
	 * @return ArrayList of GPU objects
	 */
	public ArrayList<GPU> retrieveGPUByBudget(char budget) {
		DebugOptions.debugOutput("\nMethod: retrieveGPUByBudget");
		query = ("FROM GPU WHERE product_flag_budget = '" + budget + "'");
		gpuList = queryGPU(query);
		if (gpuList.size() > 0) {
			return gpuList;
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product budget and usage values
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @return ArrayList of GPU objects
	 */
	public ArrayList<GPU> retrieveGPUs(char usage, char budget) {
		DebugOptions.debugOutput("\nMethod: retrieveGPUs");
		String usageFlag = "general"; // default value for general computers
		if (usage == 'g') {usageFlag = "gaming";}
		if (usage == 'w') {usageFlag = "workstation";}
		query = ("FROM GPU WHERE product_flag_" + usageFlag + " = 'y"
				+ "' AND product_flag_budget = '" + budget
				+ "'");
		gpuList = queryGPU(query);
		if (gpuList.size() > 0) {
			return gpuList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of products, one for each record retrieved
	 */
	private ArrayList<GPU> queryGPU(String query) {
		DebugOptions.debugOutput("\nMethod: queryGPU"); 
		
		gpuList = new ArrayList<GPU>();
		
		// retrieve products as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move products into product list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			GPU[] gpus = objects.toArray(new GPU[objects.size()]);
			
			for(GPU p: gpus) {
				System.out.println(p.getGpu_id());
				System.out.println(p.getProduct_description());
				gpuList.add(p);
			}
		}
		return gpuList;
	}
	
	/**
	 * create new record
	 * @param gpu GPU object
	 * @return int 1 for success 0 for failure
	 */
	public int createGPU(GPU gpu) {
		DebugOptions.debugOutput("\nMethod: createGPU");
		// check complete object has been submitted
		if(GPU.completeGPURecord(gpu)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(gpu);
		} else {
			System.out.println("Product record information incomplete:\n" + gpu.toString());
		}
		return 0;
	}
	
	/**
	 * update existing record
	 * @param gpuParameter GPU object
	 * @return int 1 for success 0 for failure
	 */
	public int updateGPU(GPU gpuParameter) {
		DebugOptions.debugOutput("\nMethod: updateGPU");
		// check complete object has been submitted
		if(GPU.completeGPUObject(gpuParameter)) {
			// check that the product exists in DB before updating
			String id = String.valueOf(gpuParameter.getProduct_id());
			gpu = retrieveGPUById(id);
			if(gpu !=null) {
				return HibernateSaveOrUpdate.saveOrUpdateObject(gpuParameter);
			} else {
				System.out.println("Requires product object with valid id");
			}
		} else {
			System.out.println("Product object incomplete:\n" + gpuParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing record
	 * @param id product ID
	 * @return int 1 for success 0 for failure
	 */
	public int deleteGPU(String id) {
		DebugOptions.debugOutput("\nMethod: deleteGPU");
		// check that the product exists in DB before deleting
		gpu = retrieveGPUById(id);
		if(gpu != null) {			
			return HibernateDelete.deleteObject(gpu);
		} else {
			System.out.println("Requires product object with valid id");
		}
		return 0;
	}
	
}
