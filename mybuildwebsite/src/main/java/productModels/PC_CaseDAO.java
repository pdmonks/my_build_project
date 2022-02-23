package productModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

/**
 * PC_CaseDAO allows the data and methods of the 'PC_Case' class to be
 * accessed indirectly, protecting the integrity of the original class.
 * Applications can utilise this class to perform CRUD operations on product
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class PC_CaseDAO {

	// variables
	private static PC_CaseDAO pc_caseDAO;
	private String query;
	private PC_Case pc_case;
	private ArrayList<PC_Case> pc_caseList;
	private String objectType = "class productModels.PC_Case";
	
	/** private constructor */
	private PC_CaseDAO() {}
	
	/** singleton object manager
	 * @return PC_CaseDAO object (new object only if not already instantiated)
	 */
	public static synchronized PC_CaseDAO getPC_CaseDAO() {
		if(pc_caseDAO == null) {
			pc_caseDAO = new PC_CaseDAO();
		}
		return pc_caseDAO;
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
	 * @param clone PC_CaseDAO clone object
	 */
	public PC_CaseDAO(PC_CaseDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by product id value
	 * @param id product ID
	 * @return pc_case object
	 */
	public PC_Case retrievePC_CaseById(String id) {
		DebugOptions.debugOutput("\nMethod: retrievePC_CaseById");
		query = ("FROM PC_Case WHERE product_id = '" + id + "'");
		pc_caseList = queryPC_Case(query);
		if (pc_caseList.size() > 0) {
			return pc_caseList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product budget value
	 * @param budget budget criteria
	 * @return ArrayList of pc_case objects
	 */
	public ArrayList<PC_Case> retrievePC_CaseByBudget(char budget) {
		DebugOptions.debugOutput("\nMethod: retrievePC_CaseByBudget");
		query = ("FROM PC_Case WHERE product_flag_budget = '" + budget + "'");
		pc_caseList = queryPC_Case(query);
		if (pc_caseList.size() > 0) {
			return pc_caseList;
		}
		return null;
	}
	
	/**
	 * retrieve existing record by product form factor
	 * @param usage usage criteria
	 * @param budget budget criteria
	 * @param formFactor form factor criteria
	 * @return ArrayList of pc_case objects
	 */
	public ArrayList<PC_Case> retrievePC_CaseByFormFactor(char usage, char budget, String formFactor) {
		DebugOptions.debugOutput("\nMethod: retrievePC_CaseByBudget");
		String usageFlag = "general"; // default value for general computers
		if (usage == 'g') {usageFlag = "gaming";}
		if (usage == 'w') {usageFlag = "workstation";}
		String formFactorFlag = "atx"; // standard form factor for motherboards
		if (formFactor.equals("MICRO-ATX")) {formFactorFlag = "micro_atx";}
		if (formFactor.equals("MINI-ITX")) {formFactorFlag = "mini_itx";}
		if (formFactor.equals("CEB")) {formFactorFlag = "ceb";}
		if (formFactor.equals("E-ATX")) {formFactorFlag = "e_atx";}
		query = ("FROM PC_Case WHERE product_flag_" + usageFlag + " = 'y"
				+ "' AND product_flag_budget = '" + budget
				+ "' AND pc_case_" + formFactorFlag + " = 'y'");
		pc_caseList = queryPC_Case(query);
		if (pc_caseList.size() > 0) {
			return pc_caseList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of products, one for each record retrieved
	 */
	private ArrayList<PC_Case> queryPC_Case(String query) {
		DebugOptions.debugOutput("\nMethod: queryPC_Case"); 
		
		pc_caseList = new ArrayList<PC_Case>();
		
		// retrieve products as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move products into product list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			PC_Case[] pc_cases = objects.toArray(new PC_Case[objects.size()]);
			
			for(PC_Case p: pc_cases) {
				System.out.println(p.getPc_case_id());
				System.out.println(p.getProduct_description());
				pc_caseList.add(p);
			}
		}
		return pc_caseList;
	}
	
	/**
	 * create new record
	 * @param pc_case pc_case object
	 * @return int 1 for success 0 for failure
	 */
	public int createPC_Case(PC_Case pc_case) {
		DebugOptions.debugOutput("\nMethod: createPC_Case");
		// check complete object has been submitted
		if(PC_Case.completePC_CaseRecord(pc_case)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(pc_case);
		} else {
			System.out.println("Product record information incomplete:\n" + pc_case.toString());
		}
		return 0;
	}
	
	/**
	 * update existing record
	 * @param pc_caseParameter pc_case object
	 * @return int 1 for success 0 for failure
	 */
	public int updatePC_Case(PC_Case pc_caseParameter) {
		DebugOptions.debugOutput("\nMethod: updatePC_Case");
		// check complete object has been submitted
		if(PC_Case.completePC_CaseObject(pc_caseParameter)) {
			// check that the product exists in DB before updating
			String id = String.valueOf(pc_caseParameter.getProduct_id());
			pc_case = retrievePC_CaseById(id);
			if(pc_case !=null) {
				// customerParameter.setCustomer_password(customer.getCustomer_password());
				return HibernateSaveOrUpdate.saveOrUpdateObject(pc_caseParameter);
			} else {
				System.out.println("Requires product object with valid id");
			}
		} else {
			System.out.println("Product object incomplete:\n" + pc_caseParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing record
	 * @param id product ID
	 * @return int 1 for success 0 for failure
	 */
	public int deletePC_Case(String id) {
		DebugOptions.debugOutput("\nMethod: deletePC_Case");
		// check that the product exists in DB before deleting
		pc_case = retrievePC_CaseById(id);
		if(pc_case != null) {			
			return HibernateDelete.deleteObject(pc_case);
		} else {
			System.out.println("Requires product object with valid id");
		}
		return 0;
	}
	
}
