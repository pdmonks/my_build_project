package productModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

/**
 * CPUDAO allows the data and methods of the 'CPU' class to be
 * accessed indirectly, protecting the integrity of the original class.
 * Applications can utilise this class to perform CRUD operations on product
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class CPUDAO {

	// variables
	private static CPUDAO cpuDAO;
	private String query;
	private CPU cpu;
	private ArrayList<CPU> cpuList;
	private String objectType = "class productModels.CPU";
	
	/** private constructor */
	private CPUDAO() {}
	
	/** singleton object manager
	 * @return CPUDAO object (new object only if not already instantiated)
	 */
	public static synchronized CPUDAO getCPUDAO() {
		if(cpuDAO == null) {
			cpuDAO = new CPUDAO();
		}
		return cpuDAO;
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
	 * @param clone CPUDAO clone object
	 */
	public CPUDAO(CPUDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by product id value
	 * @param id product ID
	 * @return CPU object
	 */
	public CPU retrieveCPUById(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveCPUById");
		query = ("FROM CPU WHERE product_id = '" + id + "'");
		cpuList = queryCPU(query);
		if (cpuList.size() > 0) {
			return cpuList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product usage and budget values
	 * @param usage criteria
	 * @param budget criteria
	 * @return ArrayList of CPU objects
	 */
	public ArrayList<CPU> retrieveCPUByUsageAndBudget(char usage, char budget) {
		DebugOptions.debugOutput("\nMethod: retrieveCPUByUsageAndBudget");
		String usageFlag = "general"; // default value for general computers
		if (usage == 'g') {usageFlag = "gaming";}
		if (usage == 'w') {usageFlag = "workstation";}
		query = ("FROM CPU WHERE product_flag_" + usageFlag + " = 'y' AND product_flag_budget = '" + budget + "'");
		cpuList = queryCPU(query);
		if (cpuList.size() > 0) {
			return cpuList;
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product budget value
	 * @param budget criteria
	 * @return ArrayList of CPU objects
	 */
	public ArrayList<CPU> retrieveCPUByBudget(char budget) {
		DebugOptions.debugOutput("\nMethod: retrieveCPUByBudget");
		query = ("FROM CPU WHERE product_flag_budget = '" + budget + "'");
		cpuList = queryCPU(query);
		if (cpuList.size() > 0) {
			return cpuList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of products, one for each record retrieved
	 */
	private ArrayList<CPU> queryCPU(String query) {
		DebugOptions.debugOutput("\nMethod: queryCPU"); 
		
		cpuList = new ArrayList<CPU>();
		
		// retrieve products as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move products into product list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			CPU[] cpus = objects.toArray(new CPU[objects.size()]);
			
			for(CPU p: cpus) {
				//System.out.println(p.getCpu_id());
				//System.out.println(p.getProduct_description());
				cpuList.add(p);
			}
		}
		return cpuList;
	}
	
	/**
	 * create new record
	 * @param cpu CPU object
	 * @return int 1 for success 0 for failure
	 */
	public int createCPU(CPU cpu) {
		DebugOptions.debugOutput("\nMethod: createCPU");
		// check complete object has been submitted
		if(CPU.completeCPURecord(cpu)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(cpu);
		} else {
			System.out.println("Product record information incomplete:\n" + cpu.toString());
		}
		return 0;
	}
	
	/**
	 * update existing record
	 * @param cpuParameter CPU object
	 * @return int 1 for success 0 for failure
	 */
	public int updateCPU(CPU cpuParameter) {
		DebugOptions.debugOutput("\nMethod: updateCPU");
		// check complete object has been submitted
		if(CPU.completeCPUObject(cpuParameter)) {
			// check that the product exists in DB before updating
			String id = String.valueOf(cpuParameter.getProduct_id());
			cpu = retrieveCPUById(id);
			if(cpu !=null) {
				// customerParameter.setCustomer_password(customer.getCustomer_password());
				return HibernateSaveOrUpdate.saveOrUpdateObject(cpuParameter);
			} else {
				System.out.println("Requires product object with valid id");
			}
		} else {
			System.out.println("Product object incomplete:\n" + cpuParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing record
	 * @param id product ID
	 * @return int 1 for success 0 for failure
	 */
	public int deleteCPU(String id) {
		DebugOptions.debugOutput("\nMethod: deleteCPU");
		// check that the product exists in DB before deleting
		cpu = retrieveCPUById(id);
		if(cpu != null) {			
			return HibernateDelete.deleteObject(cpu);
		} else {
			System.out.println("Requires product object with valid id");
		}
		return 0;
	}
	
}
