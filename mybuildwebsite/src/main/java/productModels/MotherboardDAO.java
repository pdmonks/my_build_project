package productModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

/**
 * MotherboardDAO allows the data and methods of the 'Motherboard' class to be
 * accessed indirectly, protecting the integrity of the original class.
 * Applications can utilise this class to perform CRUD operations on product
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class MotherboardDAO {

	// variables
	private static MotherboardDAO motherboardDAO;
	private String query;
	private Motherboard motherboard;
	private ArrayList<Motherboard> motherboardList;
	private String objectType = "class productModels.Motherboard";
	
	/** private constructor */
	private MotherboardDAO() {}
	
	/** singleton object manager
	 * @return MotherboardDAO object (new object only if not already instantiated)
	 */
	public static synchronized MotherboardDAO getMotherboardDAO() {
		if(motherboardDAO == null) {
			motherboardDAO = new MotherboardDAO();
		}
		return motherboardDAO;
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
	 * @param clone MotherboardDAO clone object
	 */
	public MotherboardDAO(MotherboardDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by product id value
	 * @param id product ID
	 * @return motherboard object
	 */
	public Motherboard retrieveMotherboardById(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveMotherboardById");
		query = ("FROM Motherboard WHERE product_id = '" + id + "'");
		motherboardList = queryMotherboard(query);
		if (motherboardList.size() > 0) {
			return motherboardList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product budget value
	 * @param budget budget criteria
	 * @return ArrayList of motherboard objects
	 */
	public ArrayList<Motherboard> retrieveMotherboardByBudget(char budget) {
		DebugOptions.debugOutput("\nMethod: retrieveMotherboardByBudget");
		query = ("FROM Motherboard WHERE product_flag_budget = '" + budget + "'");
		motherboardList = queryMotherboard(query);
		if (motherboardList.size() > 0) {
			return motherboardList;
		}
		return null;
	}
	
	/**
	 * retrieve existing record by CPU socket type
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param socket CPU socket criteria
	 * @return ArrayList of motherboard objects
	 */
	public ArrayList<Motherboard> retrieveMotherboardBySocket(char usage, char budget, String socket) {
		DebugOptions.debugOutput("\nMethod: retrieveMotherboardBySocket");
		String usageFlag = "general"; // default value for general computers
		if (usage == 'g') {usageFlag = "gaming";}
		if (usage == 'w') {usageFlag = "workstation";}
		query = ("FROM Motherboard WHERE product_flag_" + usageFlag + " = 'y"
			+ "' AND product_flag_budget = '" + budget
			+ "' AND motherboard_cpu_socket = '" + socket
			+ "'");
		motherboardList = queryMotherboard(query);
		if (motherboardList.size() > 0) {
			return motherboardList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of products, one for each record retrieved
	 */
	private ArrayList<Motherboard> queryMotherboard(String query) {
		DebugOptions.debugOutput("\nMethod: queryMotherboard"); 
		
		motherboardList = new ArrayList<Motherboard>();
		
		// retrieve products as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move products into product list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			Motherboard[] motherboards = objects.toArray(new Motherboard[objects.size()]);
			
			for(Motherboard p: motherboards) {
				System.out.println(p.getMotherboard_id());
				System.out.println(p.getProduct_description());
				motherboardList.add(p);
			}
		}
		return motherboardList;
	}
	
	/**
	 * create new record
	 * @param motherboard motherboard object
	 * @return int 1 for success 0 for failure
	 */
	public int createMotherboard(Motherboard motherboard) {
		DebugOptions.debugOutput("\nMethod: createMotherboard");
		// check complete object has been submitted
		if(Motherboard.completeMotherboardRecord(motherboard)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(motherboard);
		} else {
			System.out.println("Product record information incomplete:\n" + motherboard.toString());
		}
		return 0;
	}
	
	/**
	 * update existing record
	 * @param motherboardParameter motherboard object
	 * @return int 1 for success 0 for failure
	 */
	public int updateMotherboard(Motherboard motherboardParameter) {
		DebugOptions.debugOutput("\nMethod: updateMotherboard");
		// check complete object has been submitted
		if(Motherboard.completeMotherboardObject(motherboardParameter)) {
			// check that the product exists in DB before updating
			String id = String.valueOf(motherboardParameter.getProduct_id());
			motherboard = retrieveMotherboardById(id);
			if(motherboard !=null) {
				// customerParameter.setCustomer_password(customer.getCustomer_password());
				return HibernateSaveOrUpdate.saveOrUpdateObject(motherboardParameter);
			} else {
				System.out.println("Requires product object with valid id");
			}
		} else {
			System.out.println("Product object incomplete:\n" + motherboardParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing record
	 * @param id product ID
	 * @return int 1 for success 0 for failure
	 */
	public int deleteMotherboard(String id) {
		DebugOptions.debugOutput("\nMethod: deleteMotherboard");
		// check that the product exists in DB before deleting
		motherboard = retrieveMotherboardById(id);
		if(motherboard != null) {			
			return HibernateDelete.deleteObject(motherboard);
		} else {
			System.out.println("Requires product object with valid id");
		}
		return 0;
	}
	
}
