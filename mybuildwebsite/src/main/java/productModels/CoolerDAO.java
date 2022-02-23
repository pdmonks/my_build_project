package productModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

/**
 * CoolerDAO allows the data and methods of the 'Cooler' class to be
 * accessed indirectly, protecting the integrity of the original class.
 * Applications can utilise this class to perform CRUD operations on product
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class CoolerDAO {

	// variables
	private static CoolerDAO coolerDAO;
	private String query;
	private Cooler cooler;
	private ArrayList<Cooler> coolerList;
	private String objectType = "class productModels.Cooler";
	
	/** private constructor */
	private CoolerDAO() {}
	
	/** singleton object manager
	 * @return CoolerDAO object (new object only if not already instantiated)
	 */
	public static synchronized CoolerDAO getCoolerDAO() {
		if(coolerDAO == null) {
			coolerDAO = new CoolerDAO();
		}
		return coolerDAO;
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
	 * @param clone CoolerDAO clone object
	 */
	public CoolerDAO(CoolerDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by product id value
	 * @param id product ID
	 * @return cooler object
	 */
	public Cooler retrieveCoolerById(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveCoolerById");
		query = ("FROM Cooler WHERE product_id = '" + id + "'");
		coolerList = queryCooler(query);
		if (coolerList.size() > 0) {
			return coolerList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product budget value
	 * @param budget budget criteria
	 * @return ArrayList of cooler objects
	 */
	public ArrayList<Cooler> retrieveCoolerByBudget(char budget) {
		DebugOptions.debugOutput("\nMethod: retrieveCoolerByBudget");
		query = ("FROM Cooler WHERE product_flag_budget = '" + budget + "'");
		coolerList = queryCooler(query);
		if (coolerList.size() > 0) {
			return coolerList;
		}
		return null;
	}
	
	/**
	 * retrieve existing record by CPU socket type
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param socket CPU socket type criteria
	 * @return ArrayList of cooler objects
	 */
	public ArrayList<Cooler> retrieveCoolerBySocket(char usage, char budget, String socket) {
		DebugOptions.debugOutput("\nMethod: retrieveCoolerBySocket");
		
		// determine which usage flag to retrieve
		String usageFlag = "general"; // default value for general computers
		if (usage == 'g') {usageFlag = "gaming";}
		if (usage == 'w') {usageFlag = "workstation";} 
		
		query = ("FROM Cooler WHERE product_flag_" + usageFlag + " = 'y"
			+ "' AND product_flag_budget = '" + budget
			+ "' AND cooler_" + socket.toLowerCase() + " = 'y'");
		coolerList = queryCooler(query);
		if (coolerList.size() > 0) {
			return coolerList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of products, one for each record retrieved
	 */
	private ArrayList<Cooler> queryCooler(String query) {
		DebugOptions.debugOutput("\nMethod: queryCooler"); 
		
		coolerList = new ArrayList<Cooler>();
		
		// retrieve products as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move products into product list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			Cooler[] coolers = objects.toArray(new Cooler[objects.size()]);
			
			for(Cooler p: coolers) {
				System.out.println(p.getCooler_id());
				System.out.println(p.getProduct_description());
				coolerList.add(p);
			}
		}
		return coolerList;
	}
	
	/**
	 * create new record
	 * @param cooler cooler object
	 * @return int 1 for success 0 for failure
	 */
	public int createCooler(Cooler cooler) {
		DebugOptions.debugOutput("\nMethod: createCooler");
		// check complete object has been submitted
		if(Cooler.completeCoolerRecord(cooler)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(cooler);
		} else {
			System.out.println("Product record information incomplete:\n" + cooler.toString());
		}
		return 0;
	}
	
	/**
	 * update existing record
	 * @param coolerParameter cooler object
	 * @return int 1 for success 0 for failure
	 */
	public int updateCooler(Cooler coolerParameter) {
		DebugOptions.debugOutput("\nMethod: updateCooler");
		// check complete object has been submitted
		if(Cooler.completeCoolerObject(coolerParameter)) {
			// check that the product exists in DB before updating
			String id = String.valueOf(coolerParameter.getProduct_id());
			cooler = retrieveCoolerById(id);
			if(cooler !=null) {
				return HibernateSaveOrUpdate.saveOrUpdateObject(coolerParameter);
			} else {
				System.out.println("Requires product object with valid id");
			}
		} else {
			System.out.println("Product object incomplete:\n" + coolerParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing record
	 * @param id product ID
	 * @return int 1 for success 0 for failure
	 */
	public int deleteCooler(String id) {
		DebugOptions.debugOutput("\nMethod: deleteCooler");
		// check that the product exists in DB before deleting
		cooler = retrieveCoolerById(id);
		if(cooler != null) {			
			return HibernateDelete.deleteObject(cooler);
		} else {
			System.out.println("Requires product object with valid id");
		}
		return 0;
	}
	
}