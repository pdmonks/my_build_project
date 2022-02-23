package productModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

/**
 * Power_SupplyDAO allows the data and methods of the 'Power_Supply' class to be
 * accessed indirectly, protecting the integrity of the original class.
 * Applications can utilise this class to perform CRUD operations on product
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class Power_SupplyDAO {

	// variables
	private static Power_SupplyDAO power_supplyDAO;
	private String query;
	private Power_Supply power_supply;
	private ArrayList<Power_Supply> power_supplyList;
	private String objectType = "class productModels.Power_Supply";
	
	/** private constructor */
	private Power_SupplyDAO() {}
	
	/** singleton object manager
	 * @return Power_SupplyDAO object (new object only if not already instantiated)
	 */
	public static synchronized Power_SupplyDAO getPower_SupplyDAO() {
		if(power_supplyDAO == null) {
			power_supplyDAO = new Power_SupplyDAO();
		}
		return power_supplyDAO;
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
	 * @param clone Power_SupplyDAO clone object
	 */
	public Power_SupplyDAO(Power_SupplyDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by product id value
	 * @param id product ID
	 * @return
	 */
	public Power_Supply retrievePower_SupplyById(String id) {
		DebugOptions.debugOutput("\nMethod: retrievePower_SupplyById");
		query = ("FROM Power_Supply WHERE product_id = '" + id + "'");
		power_supplyList = queryPower_Supply(query);
		if (power_supplyList.size() > 0) {
			return power_supplyList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product budget value
	 * @param budget budget criteria
	 * @return ArrayList of power_supply objects
	 */
	public ArrayList<Power_Supply> retrievePower_SupplyByBudget(char budget) {
		DebugOptions.debugOutput("\nMethod: retrievePower_SupplyByBudget");
		query = ("FROM Power_Supply WHERE product_flag_budget = '" + budget + "'");
		power_supplyList = queryPower_Supply(query);
		if (power_supplyList.size() > 0) {
			return power_supplyList;
		}
		return null;
	}
	
	/**
	 * retrieve existing record by power requirement value
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param powerRequired power requirement criteria
	 * @return ArrayList of power_supply objects
	 */
	public ArrayList<Power_Supply> retrievePower_SupplyByPowerRequired(char usage, char budget, String powerRequired) {
		DebugOptions.debugOutput("\nMethod: retrievePower_SupplyByPowerRequired");
		String usageFlag = "general"; // default value for general computers
		if (usage == 'g') {usageFlag = "gaming";}
		if (usage == 'w') {usageFlag = "workstation";}
		query = ("FROM Power_Supply WHERE product_flag_" + usageFlag + " = 'y"
				+ "' AND product_flag_budget = '" + budget
				+ "' AND power_supply_wattage >='" + (Integer.parseInt(powerRequired) * 1.2) + "'");
		power_supplyList = queryPower_Supply(query);
		if (power_supplyList.size() > 0) {
			return power_supplyList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of products, one for each record retrieved
	 */
	private ArrayList<Power_Supply> queryPower_Supply(String query) {
		DebugOptions.debugOutput("\nMethod: queryPower_Supply"); 
		
		power_supplyList = new ArrayList<Power_Supply>();
		
		// retrieve products as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move products into product list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			Power_Supply[] power_supplies = objects.toArray(new Power_Supply[objects.size()]);
			
			for(Power_Supply p: power_supplies) {
				System.out.println(p.getPower_supply_id());
				System.out.println(p.getProduct_description());
				power_supplyList.add(p);
			}
		}
		return power_supplyList;
	}
	
	/**
	 * create new record
	 * @param power_supply power_supply object
	 * @return int 1 for success 0 for failure
	 */
	public int createPower_Supply(Power_Supply power_supply) {
		DebugOptions.debugOutput("\nMethod: createPower_Supply");
		// check complete object has been submitted
		if(Power_Supply.completePower_SupplyRecord(power_supply)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(power_supply);
		} else {
			System.out.println("Product record information incomplete:\n" + power_supply.toString());
		}
		return 0;
	}
	
	/**
	 * update existing record
	 * @param power_supplyParameter power_supply object
	 * @return int 1 for success 0 for failure
	 */
	public int updatePower_Supply(Power_Supply power_supplyParameter) {
		DebugOptions.debugOutput("\nMethod: updatePower_Supply");
		// check complete object has been submitted
		if(Power_Supply.completePower_SupplyObject(power_supplyParameter)) {
			// check that the product exists in DB before updating
			String id = String.valueOf(power_supplyParameter.getProduct_id());
			power_supply = retrievePower_SupplyById(id);
			if(power_supply !=null) {
				// customerParameter.setCustomer_password(customer.getCustomer_password());
				return HibernateSaveOrUpdate.saveOrUpdateObject(power_supply);
			} else {
				System.out.println("Requires product object with valid id");
			}
		} else {
			System.out.println("Product object incomplete:\n" + power_supplyParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing record
	 * @param id product ID
	 * @return int 1 for success 0 for failure
	 */
	public int deletePower_Supply(String id) {
		DebugOptions.debugOutput("\nMethod: deletePower_Supply");
		// check that the product exists in DB before deleting
		power_supply = retrievePower_SupplyById(id);
		if(power_supply != null) {			
			return HibernateDelete.deleteObject(power_supply);
		} else {
			System.out.println("Requires product object with valid id");
		}
		return 0;
	}
	
}
