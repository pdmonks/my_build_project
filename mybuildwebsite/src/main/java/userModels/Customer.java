package userModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	// variables
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_id") private int customer_id;
	
	@Column(name="customer_surname") private String customer_surname;
	@Column(name="customer_forename") private String customer_forename;
	@Column(name="customer_address") private String customer_address;
	@Column(name="customer_town") private String customer_town;
	@Column(name="customer_postcode") private String customer_postcode;
	@Column(name="customer_telephone") private String customer_telephone;
	@Column(name="customer_email") private String customer_email;
	@Column(name="customer_password") private String customer_password;
	@Column(name="customer_token") private String customer_token;
	
	/** default no argument constructor */
	public Customer() {}
	
	/**
	 * hibernate constructor (without auto-generated id)
	 * @param surname customer surname
	 * @param forename customer forename
	 * @param address customer address
	 * @param town customer town
	 * @param postcode customer postcode
	 * @param telephone customer telephone
	 * @param email customer email
	 * @param password customer password
	 * @param token customer session token
	 */
	public Customer(String surname, String forename, String address,
			String town, String postcode, String telephone,
			String email, String password, String token) {
		this.customer_surname = surname;
		this.customer_forename = forename;
		this.customer_address = address;
		this.customer_town = town;
		this.customer_postcode = postcode;
		this.customer_telephone = telephone;
		this.customer_email = email;
		this.customer_password = password;
		this.customer_token = token;
	}
	
	/**
	 * full constructor
	 * @param id customer ID
	 * @param surname customer surname
	 * @param forename customer forename
	 * @param address customer address
	 * @param town customer town
	 * @param postcode customer postcode
	 * @param telephone customer telephone
	 * @param email customer email
	 * @param password customer password
	 * @param token customer session token
	 */
	public Customer(int id, String surname, String forename, String address,
					String town, String postcode, String telephone,
					String email, String password, String token) {
		this.customer_id = id;
		this.customer_surname = surname;
		this.customer_forename = forename;
		this.customer_address = address;
		this.customer_town = town;
		this.customer_postcode = postcode;
		this.customer_telephone = telephone;
		this.customer_email = email;
		this.customer_password = password;
		this.customer_token = token;
	}

	/**
	 * @return the customer_id
	 */
	public int getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id the customer_id to set
	 */
	public void setCustomer_id(int id) {
		this.customer_id = id;
	}

	/**
	 * @return the customer_surname
	 */
	public String getCustomer_surname() {
		return customer_surname;
	}

	/**
	 * @param customer_surname the customer_surname to set
	 */
	public void setCustomer_surname(String surname) {
		this.customer_surname = surname;
	}

	/**
	 * @return the customer_forename
	 */
	public String getCustomer_forename() {
		return customer_forename;
	}

	/**
	 * @param customer_forename the customer_forename to set
	 */
	public void setCustomer_forename(String forename) {
		this.customer_forename = forename;
	}

	/**
	 * @return the customer_address
	 */
	public String getCustomer_address() {
		return customer_address;
	}

	/**
	 * @param customer_address the customer_address to set
	 */
	public void setCustomer_address(String address) {
		this.customer_address = address;
	}

	/**
	 * @return the customer_town
	 */
	public String getCustomer_town() {
		return customer_town;
	}

	/**
	 * @param customer_town the customer_town to set
	 */
	public void setCustomer_town(String town) {
		this.customer_town = town;
	}

	/**
	 * @return the customer_postcode
	 */
	public String getCustomer_postcode() {
		return customer_postcode;
	}

	/**
	 * @param customer_postcode the customer_postcode to set
	 */
	public void setCustomer_postcode(String postcode) {
		this.customer_postcode = postcode;
	}

	/**
	 * @return the customer_telephone
	 */
	public String getCustomer_telephone() {
		return customer_telephone;
	}

	/**
	 * @param customer_telephone the customer_telephone to set
	 */
	public void setCustomer_telephone(String telephone) {
		this.customer_telephone = telephone;
	}

	/**
	 * @return the customer_email
	 */
	public String getCustomer_email() {
		return customer_email;
	}

	/**
	 * @param customer_email the customer_email to set
	 */
	public void setCustomer_email(String email) {
		this.customer_email = email;
	}

	/**
	 * @return the customer_password
	 */
	public String getCustomer_password() {
		return customer_password;
	}

	/**
	 * @param customer_password the customer_password to set
	 */
	public void setCustomer_password(String password) {
		this.customer_password = password;
	}
	
	/**
	 * @return the customer_token
	 */
	public String getCustomer_token() {
		return customer_token;
	}

	/**
	 * @param customer_token the customer_token to set
	 */
	public void setCustomer_token(String customer_token) {
		this.customer_token = customer_token;
	}

	/** object equals comparison function
     * @param customer customer object to compare
     * @return true if objects are equal, false if not
     */
    public boolean equals(Customer customer) {
        if(this.customer_id != 0) {
            return this.customer_id == customer.customer_id
            	&& this.customer_surname.equals(customer.customer_surname)
            	&& this.customer_forename.equals(customer.customer_forename)
            	&& this.customer_address.equals(customer.customer_address)
            	&& this.customer_town.equals(customer.customer_town)
            	&& this.customer_postcode.equals(customer.customer_postcode)
            	&& this.customer_telephone.equals(customer.customer_telephone)
            	&& this.customer_email.equals(customer.customer_email)
            	&& this.customer_password.equals(customer.customer_password)
            	&& this.customer_token.equals(customer.customer_token);
        }
        else return false;
    }

	/** check if all customer attributes have been completed, eg when updating customer records
	 * @param customer customer object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeCustomerObject(Customer customer) {
		if(customer.getCustomer_id() != 0
			&& customer.getCustomer_surname()!= null
			&& customer.getCustomer_forename() != null
			&& customer.getCustomer_address() != null
			&& customer.getCustomer_town() != null
			&& customer.getCustomer_postcode() != null
			&& customer.getCustomer_telephone() != null
			&& customer.getCustomer_email() != null
			&& customer.getCustomer_password() != null
			&& customer.getCustomer_token() != null) {
			return true;
		}
		else return false;	
	}
  
	/** check if all customer attributes except id have been completed, eg when creating new customer records
	 * @param customer customer object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeCustomerRecord(Customer customer) {
		if(customer.getCustomer_surname()!= null
			&& customer.getCustomer_forename() != null
			&& customer.getCustomer_address() != null
			&& customer.getCustomer_town() != null
			&& customer.getCustomer_postcode() != null
			&& customer.getCustomer_telephone() != null
			&& customer.getCustomer_email() != null
			&& customer.getCustomer_password() != null) {
			// && customer.getCustomer_token() != null) {	
			return true;
		}
		else return false;	
	}
	
	/** customer object to string function
	 * @return customer object information as a string
	 */
	@Override
	public String toString() {
		return "Customer ["
			+ "customer_id=" + customer_id + ", "
			+ "customer_surname=" + customer_surname + ", "
			+ "customer_forename=" + customer_forename + ", "
			+ "customer_address=" + customer_address + ", "
			+ "customer_town=" + customer_town + ", "
			+ "customer_postcode=" + customer_postcode + ", "
			+ "customer_telephone=" + customer_telephone + ", "
			+ "customer_email=" + customer_email + ", "
			+ "customer_password=" + customer_password + ", "
			+ "customer_token=" + customer_token + ", "
			+ "]";
	}
}
