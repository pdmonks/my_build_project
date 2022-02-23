package productModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

/**
 * RAMDAO allows the data and methods of the 'RAM' class to be
 * accessed indirectly, protecting the integrity of the original class.
 * Applications can utilise this class to perform CRUD operations on product
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class RAMDAO {

	// variables
	private static RAMDAO ramDAO;
	private String query;
	private RAM ram;
	private ArrayList<RAM> ramList;
	private String objectType = "class productModels.RAM";
	
	/** private constructor */
	private RAMDAO() {}
	
	/** singleton object manager
	 * @return RAMDAO object (new object only if not already instantiated)
	 */
	public static synchronized RAMDAO getRAMDAO() {
		if(ramDAO == null) {
			ramDAO = new RAMDAO();
		}
		return ramDAO;
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
	 * @param clone RAMDAO clone object
	 */
	public RAMDAO(RAMDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by product id value
	 * @param id product ID
	 * @return RAM object
	 */
	public RAM retrieveRAMById(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveRAMById");
		query = ("FROM RAM WHERE product_id = '" + id + "'");
		ramList = queryRAM(query);
		if (ramList.size() > 0) {
			return ramList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product budget value
	 * @param budget budget criteria
	 * @return ArrayList of RAM objects
	 */
	public ArrayList<RAM> retrieveRAMByBudget(char budget) {
		DebugOptions.debugOutput("\nMethod: retrieveRAMByBudget");
		query = ("FROM RAM WHERE product_flag_budget = '" + budget + "'");
		ramList = queryRAM(query);
		if (ramList.size() > 0) {
			return ramList;
		}
		return null;
	}
	
	/**
	 * retrieve existing records by product capacity and speed value
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param capacity capacity criteria
	 * @param speed speed criteria
	 * @return ArrayList of RAM objects
	 */
	public ArrayList<RAM> retrieveRAMByCapacityAndSpeed(char usage, char budget, int capacity, int speed) {
		DebugOptions.debugOutput("\nMethod: retrieveRAMByBudget");
		String usageFlag = "general"; // default value for general computers
		if (usage == 'g') {usageFlag = "gaming";}
		if (usage == 'w') {usageFlag = "workstation";}
		query = ("FROM RAM WHERE product_flag_" + usageFlag + " = 'y"
				+ "' AND product_flag_budget = '" + budget
				+ "' AND ram_capacity <= '" + capacity
				+ "' AND ram_base_clock >= '" + speed
				+ "'");
		ramList = queryRAM(query);
		if (ramList.size() > 0) {
			return ramList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of products, one for each record retrieved
	 */
	private ArrayList<RAM> queryRAM(String query) {
		DebugOptions.debugOutput("\nMethod: queryRAM"); 
		
		ramList = new ArrayList<RAM>();
		
		// retrieve products as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move products into product list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			RAM[] rams = objects.toArray(new RAM[objects.size()]);
			
			for(RAM p: rams) {
				System.out.println(p.getRam_id());
				System.out.println(p.getProduct_description());
				ramList.add(p);
			}
		}
		return ramList;
	}
	
	/**
	 * create new record
	 * @param ram RAM object
	 * @return int 1 for success 0 for failure
	 */
	public int createRAM(RAM ram) {
		DebugOptions.debugOutput("\nMethod: createRAM");
		// check complete object has been submitted
		if(RAM.completeRAMRecord(ram)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(ram);
		} else {
			System.out.println("Product record information incomplete:\n" + ram.toString());
		}
		return 0;
	}
	
	/**
	 * update existing record
	 * @param ramParameter RAM object
	 * @return int 1 for success 0 for failure
	 */
	public int updateRAM(RAM ramParameter) {
		DebugOptions.debugOutput("\nMethod: updateRAM");
		// check complete object has been submitted
		if(RAM.completeRAMObject(ramParameter)) {
			// check that the product exists in DB before updating
			String id = String.valueOf(ramParameter.getProduct_id());
			ram = retrieveRAMById(id);
			if(ram !=null) {
				return HibernateSaveOrUpdate.saveOrUpdateObject(ramParameter);
			} else {
				System.out.println("Requires product object with valid id");
			}
		} else {
			System.out.println("Product object incomplete:\n" + ramParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing record
	 * @param id product ID
	 * @return int 1 for success 0 for failure
	 */
	public int deleteRAM(String id) {
		DebugOptions.debugOutput("\nMethod: deleteRAM");
		// check that the product exists in DB before deleting
		ram = retrieveRAMById(id);
		if(ram != null) {			
			return HibernateDelete.deleteObject(ram);
		} else {
			System.out.println("Requires product object with valid id");
		}
		return 0;
	}
	
}
