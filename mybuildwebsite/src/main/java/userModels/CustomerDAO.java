package userModels;

import java.util.ArrayList;
import java.util.Random;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

public class CustomerDAO {

	// variables
	private static CustomerDAO customerDAO;
	private String query;
	private Customer customer;
	private ArrayList<Customer> customerList;
	private String objectType = "class userModels.Customer";
	
	/** private constructor */
	private CustomerDAO() {}
	
	/** singleton object manager
	 * @return CustomerDAO object (new object only if not already instantiated)
	 */
	public static synchronized CustomerDAO getCustomerDAO() {
		if(customerDAO == null) {
			customerDAO = new CustomerDAO();
		}
		return customerDAO;
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
	 * @param clone CustomerDAO clone object
	 */
	public CustomerDAO(CustomerDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by email address
	 * @param email customer email
	 * @return customer object
	 */
	public Customer retrieveCustomerByEmail(String email) {
		DebugOptions.debugOutput("\nMethod: retrieveCustomerByEmail");
		query = ("FROM Customer where customer_email = '" + email + "'");
		customerList = queryCustomer(query);
		if (customerList.size() > 0) {
			return customerList.get(0); // return customer 0 - must be unique as retrieved by email address
		}
		return null;
	}
	
	/**
	 * retrieve existing record by session token value
	 * @param token session token
	 * @return customer object
	 */
	public Customer retrieveCustomerByToken(String token) {
		DebugOptions.debugOutput("\nMethod: retrieveCustomerByToken");
		query = ("FROM Customer where customer_token = '" + token + "'");
		customerList = queryCustomer(query);
		if (customerList.size() > 0) {
			return customerList.get(0); // return customer 0 - must be unique as retrieved by token
		}
		return null;
	}
	
	/**
	 * retrieve existing record by customer ID
	 * @param id customer ID
	 * @return customer object
	 */
	public Customer retrieveCustomerById(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveCustomerById");
		query = ("FROM Customer where customer_id = '" + id + "'");
		customerList = queryCustomer(query);
		if (customerList.size() > 0) {
			return customerList.get(0); // return customer 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of customer objects, one for each record retrieved
	 */
	private ArrayList<Customer> queryCustomer(String query) {
		DebugOptions.debugOutput("\nMethod: queryCustomer"); 
		
		customerList = new ArrayList<Customer>();
		
		// retrieve customers as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move customers into customer list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			Customer[] customers = objects.toArray(new Customer[objects.size()]);
			
			for(Customer c: customers) {
				customerList.add(c);
			}
		}
		return customerList;	
	}
	
	/**
	 * create new record
	 * @param customer object
	 * @return int 1 for success 0 for failure
	 */
	public int createCustomer(Customer customer) {
		DebugOptions.debugOutput("\nMethod: createCustomer");
		// check complete object has been submitted
		if(Customer.completeCustomerRecord(customer)) {
			customer.setCustomer_password(UserUtilities.hashString(customer.getCustomer_password()));
			return HibernateSaveOrUpdate.saveOrUpdateObject(customer);
		} else {
			System.out.println("Customer record information incomplete:\n" + customer.toString());
		}
		return 0;
	}
	
	/**
	 * update existing record
	 * @param customerParameter customer object
	 * @return int 1 for success 0 for failure
	 */
	public int updateCustomer(Customer customerParameter) {
		DebugOptions.debugOutput("\nMethod: updateCustomer");
		// check complete object has been submitted
		if(Customer.completeCustomerObject(customerParameter)) {
			// check that the customer exists in DB before updating
			String id = String.valueOf(customerParameter.getCustomer_id());
			customer = retrieveCustomerById(id);
			if(customer !=null) {
				System.out.println("password" + customer.getCustomer_password());
				return HibernateSaveOrUpdate.saveOrUpdateObject(customerParameter);
			} else {
				System.out.println("Requires customer object with valid id");
			}
		} else {
			System.out.println("Customer object incomplete:\n" + customerParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing record
	 * @param email customer email address
	 * @return int 1 for success 0 for failure
	 */
	public int deleteCustomer(String email) {
		DebugOptions.debugOutput("\nMethod: deleteCustomer");
		// check that the customer exists in DB before deleting
		customer = retrieveCustomerByEmail(email);
		if(customer != null) {			
			return HibernateDelete.deleteObject(customer);
		} else {
			System.out.println("Requires customer object with valid email");
		}
		return 0;
	}
	
	/**
	 * create new session token, eg to authorise customer requests
	 * @param email customer email address
	 * @return session new token as a string
	 */
	public String createCustomerToken(String email) {
		DebugOptions.debugOutput("\nMethod: createCustomerToken");
		customer = retrieveCustomerByEmail(email);
		String token = UserUtilities.generateToken();
		customer.setCustomer_token(token);
		updateCustomer(customer);
		return token;
	}
	
	/**
	 * retrieve customer current session token, eg to authorise requests
	 * @param email customer email address
	 * @return session current token as a string
	 */
	public String retrieveCustomerToken(String email) {
		DebugOptions.debugOutput("\nMethod: retrieveCustomerToken");
		customer = retrieveCustomerByEmail(email);
		return customer.getCustomer_token();
	}
	
	/**
	 * remove customer current session token, eg to log out customer
	 * @param token customer current session token
	 * @return int 1 for success 0 for failure
	 */
	public int removeCustomerToken(String token) {
		DebugOptions.debugOutput("\nMethod: removeCustomerToken");
		this.customer = retrieveCustomerByToken(token);
		this.customer.setCustomer_token("");
		return updateCustomer(this.customer);
		//return 1;
	}
	
}
