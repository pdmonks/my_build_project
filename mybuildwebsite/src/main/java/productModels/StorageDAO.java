package productModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

/**
 * StorageDAO allows the data and methods of the 'Storage' class to be
 * accessed indirectly, protecting the integrity of the original class.
 * Applications can utilise this class to perform CRUD operations on product
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class StorageDAO {

	// variables
	private static StorageDAO storageDAO;
	private String query;
	private Storage storage;
	private ArrayList<Storage> storageList;
	private String objectType = "class productModels.Storage";
	
	/** private constructor */
	private StorageDAO() {}
	
	/** singleton object manager
	 * @return CPUDAO object (new object only if not already instantiated)
	 */
	public static synchronized StorageDAO getStorageDAO() {
		if(storageDAO == null) {
			storageDAO = new StorageDAO();
		}
		return storageDAO;
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
	 * @param clone StorageDAO clone object
	 */
	public StorageDAO(StorageDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by product id value
	 * @param id product ID
	 * @return storage object
	 */
	public Storage retrieveStorageById(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveStorageById");
		query = ("FROM Storage WHERE product_id = '" + id + "'");
		storageList = queryStorage(query);
		if (storageList.size() > 0) {
			return storageList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product budget value
	 * @param budget budget criteria
	 * @return ArrayList of storage objects
	 */
	public ArrayList<Storage> retrieveStorageByBudget(char budget) {
		DebugOptions.debugOutput("\nMethod: retrieveStorageByBudget");
		query = ("FROM Storage WHERE product_flag_budget = '" + budget + "'");
		storageList = queryStorage(query);
		if (storageList.size() > 0) {
			return storageList;
		}
		return null;
	}
	
	/**
	 * retrieve existing records by connection type
	 * @param usage criteria
	 * @param budget budget criteria
	 * @param type connection type criteria
	 * @return ArrayList of storage objects
	 */
	public ArrayList<Storage> retrieveStorageByType(char usage, char budget, String type) {
		DebugOptions.debugOutput("\nMethod: retrieveStorageByBudget");
		String usageFlag = "general"; // default value for general computers
		if (usage == 'g') {usageFlag = "gaming";}
		if (usage == 'w') {usageFlag = "workstation";}
		query = ("FROM Storage WHERE product_flag_" + usageFlag + " = 'y"
				+ "' AND product_flag_budget = '" + budget
				+ "' AND storage_type = '" + type
				+ "'");
		storageList = queryStorage(query);
		if (storageList.size() > 0) {
			return storageList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return ArrayList of products, one for each record retrieved
	 */
	private ArrayList<Storage> queryStorage(String query) {
		DebugOptions.debugOutput("\nMethod: queryStorage"); 
		
		storageList = new ArrayList<Storage>();
		
		// retrieve products as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move products into product list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			Storage[] stores = objects.toArray(new Storage[objects.size()]);
			
			for(Storage p: stores) {
				System.out.println(p.getStorage_id());
				System.out.println(p.getProduct_description());
				storageList.add(p);
			}
		}
		return storageList;
	}
	
	/**
	 * create new record
	 * @param storage storage object
	 * @return int 1 for success 0 for failure
	 */
	public int createStorage(Storage storage) {
		DebugOptions.debugOutput("\nMethod: createStorage");
		// check complete object has been submitted
		if(Storage.completeStorageRecord(storage)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(storage);
		} else {
			System.out.println("Product record information incomplete:\n" + storage.toString());
		}
		return 0;
	}
	
	/**
	 * update existing records
	 * @param storageParameter storage object
	 * @return int 1 for success 0 for failure
	 */
	public int updateStorage(Storage storageParameter) {
		DebugOptions.debugOutput("\nMethod: updateStorage");
		// check complete object has been submitted
		if(Storage.completeStorageObject(storageParameter)) {
			// check that the product exists in DB before updating
			String id = String.valueOf(storageParameter.getProduct_id());
			storage = retrieveStorageById(id);
			if(storage !=null) {
				// customerParameter.setCustomer_password(customer.getCustomer_password());
				return HibernateSaveOrUpdate.saveOrUpdateObject(storageParameter);
			} else {
				System.out.println("Requires product object with valid id");
			}
		} else {
			System.out.println("Product object incomplete:\n" + storageParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing records
	 * @param id product ID
	 * @return int 1 for success 0 for failure
	 */
	public int deleteStorage(String id) {
		DebugOptions.debugOutput("\nMethod: deleteStorage");
		// check that the product exists in DB before deleting
		storage = retrieveStorageById(id);
		if(storage != null) {			
			return HibernateDelete.deleteObject(storage);
		} else {
			System.out.println("Requires product object with valid id");
		}
		return 0;
	}
	
}