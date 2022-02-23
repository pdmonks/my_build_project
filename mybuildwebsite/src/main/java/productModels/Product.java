package productModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Product contains attributes of the 'product' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * The class is linked to its component subclasses by 'product_id',
 * representing an inheritance relationship.
 * Methods for CRUD operations are implemented in the ProductDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="product")
@Inheritance(strategy=InheritanceType.JOINED)
public class Product {

	// variables with persistence annotation and mapping as per DB attributes
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id") private int product_id;
	
	@Column(name="product_type") private String product_type;
	@Column(name="product_supplier") private String product_supplier;
	@Column(name="product_description") private String product_description;
	@Column(name="product_wattage") private int product_wattage;
	@Column(name="product_price") private double product_price;
	@Column(name="product_stock") private int product_stock;
	@Column(name="product_flag_general") private char product_flag_general;
	@Column(name="product_flag_gaming") private char product_flag_gaming;
	@Column(name="product_flag_workstation") private char product_flag_workstation;
	@Column(name="product_flag_budget") private char product_flag_budget;
	
	/** default no argument constructor */
	public Product() {}
	
	/**
	 * hibernate constructor (without auto-generated id)
	 * @param type product type
	 * @param supplier product supplier
	 * @param description product description
	 * @param wattage product wattage
	 * @param price product price
	 * @param stock product stock
	 * @param general product general flag
	 * @param gaming product gaming flag
	 * @param workstation product workstation flag
	 * @param budget product budget flag
	 */
	public Product(String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget) {
		this.product_type = type;
		this.product_supplier = supplier;
		this.product_description = description;
		this.product_wattage = wattage;
		this.product_price = price;
		this.product_stock = stock;
		this.product_flag_general = general;
		this.product_flag_gaming = gaming;
		this.product_flag_workstation = workstation;
		this.product_flag_budget = budget;
	}
	
	/**
	 * full constructor
	 * @param id product ID
	 * @param type product type
	 * @param supplier product supplier
	 * @param description product description
	 * @param wattage product wattage
	 * @param price product price
	 * @param stock product stock
	 * @param general product general flag
	 * @param gaming product gaming flag
	 * @param workstation product workstation flag
	 * @param budget product budget flag
	 */
	public Product(int id, String type, String supplier, String description, int wattage,
					double price, int stock, char general, char gaming, char workstation, char budget) {
		this.product_id = id;
		this.product_type = type;
		this.product_supplier = supplier;
		this.product_description = description;
		this.product_wattage = wattage;
		this.product_price = price;
		this.product_stock = stock;
		this.product_flag_general = general;
		this.product_flag_gaming = gaming;
		this.product_flag_workstation = workstation;
		this.product_flag_budget = budget;
	}

	// getter and setter methods
	
	/**
	 * @return the product_id
	 */
	public int getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(int id) {
		this.product_id = id;
	}

	/**
	 * @return the product_type
	 */
	public String getProduct_type() {
		return product_type;
	}

	/**
	 * @param product_type the product_type to set
	 */
	public void setProduct_type(String type) {
		this.product_type = type;
	}

	/**
	 * @return the product_supplier
	 */
	public String getProduct_supplier() {
		return product_supplier;
	}

	/**
	 * @param product_supplier the product_supplier to set
	 */
	public void setProduct_supplier(String supplier) {
		this.product_supplier = supplier;
	}

	/**
	 * @return the product_description
	 */
	public String getProduct_description() {
		return product_description;
	}

	/**
	 * @param product_description the product_description to set
	 */
	public void setProduct_description(String description) {
		this.product_description = description;
	}

	/**
	 * @return the product_wattage
	 */
	public int getProduct_wattage() {
		return product_wattage;
	}

	/**
	 * @param product_wattage the product_wattage to set
	 */
	public void setProduct_wattage(int wattage) {
		this.product_wattage = wattage;
	}

	/**
	 * @return the product_price
	 */
	public double getProduct_price() {
		return product_price;
	}

	/**
	 * @param product_price the product_price to set
	 */
	public void setProduct_price(double price) {
		this.product_price = price;
	}

	/**
	 * @return the product_stock
	 */
	public int getProduct_stock() {
		return product_stock;
	}

	/**
	 * @param product_stock the product_stock to set
	 */
	public void setProduct_stock(int stock) {
		this.product_stock = stock;
	}

	/**
	 * @return the product_flag_general
	 */
	public char getProduct_flag_general() {
		return product_flag_general;
	}

	/**
	 * @param product_flag_general the product_flag_general to set
	 */
	public void setProduct_flag_general(char general) {
		this.product_flag_general = general;
	}

	/**
	 * @return the product_flag_gaming
	 */
	public char getProduct_flag_gaming() {
		return product_flag_gaming;
	}

	/**
	 * @param product_flag_gaming the product_flag_gaming to set
	 */
	public void setProduct_flag_gaming(char gaming) {
		this.product_flag_gaming = gaming;
	}

	/**
	 * @return the product_flag_workstation
	 */
	public char getProduct_flag_workstation() {
		return product_flag_workstation;
	}

	/**
	 * @param product_flag_workstation the product_flag_workstation to set
	 */
	public void setProduct_flag_workstation(char workstation) {
		this.product_flag_workstation = workstation;
	}

	/**
	 * @return the product_flag_budget
	 */
	public char getProduct_flag_budget() {
		return product_flag_budget;
	}

	/**
	 * @param product_flag_budget the product_flag_budget to set
	 */
	public void setProduct_flag_budget(char budget) {
		this.product_flag_budget = budget;
	}
	
	/** object equals comparison function
     * @param product product object to compare
     * @return true if objects are equal, false if not
     */
    public boolean equals(Product product) {
        if (this.product_id != 0) {
            return this.product_id == product.product_id
            	&& this.product_type.equals(product.product_type)
    			&& this.product_supplier.equals(product.product_supplier)
    			&& this.product_description.equals(product.product_description)
    			&& this.product_wattage == product.product_wattage
    			&& this.product_price == product.product_price
    			&& this.product_stock == product.product_stock
    			&& this.product_flag_general == product.product_flag_general
    			&& this.product_flag_gaming == product.product_flag_gaming
    			&& this.product_flag_workstation == product.product_flag_workstation
    			&& this.product_flag_budget == product.product_flag_budget
    			&& this.product_price == product.product_price;
        }
        else return false;
    }
    
	/** check if all product attributes have been completed, eg when updating product records
	 * @param product product object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeProductObject(Product product) {
		if(product.product_id != 0
			&& product.product_type != null
			&& product.product_supplier != null
			&& product.product_description != null
			&& (((!product.product_type.equals("pc_case") || !product.product_type.equals("power_supply")) && product.product_wattage != 0)
				|| ((product.product_type.equals("pc_case") || product.product_type.equals("power_supply")) && product.product_wattage == 0))
			&& product.product_price != 0
			//&& product.product_stock != 0 // stock can be nil
			&& product.product_flag_general != 0
			&& product.product_flag_gaming != 0
			&& product.product_flag_workstation != 0
			&& product.product_flag_budget != 0) {
			return true;
		}
		else return false;	
	}
	
	/** check if all product attributes except id have been completed, eg when creating new product records
	 * @param product product object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeProductRecord(Product product) {
		if(product.product_type != null
			&& product.product_supplier != null
			&& product.product_description != null
			&& (((!product.product_type.equals("pc_case") || !product.product_type.equals("power_supply")) && product.product_wattage != 0)
				|| ((product.product_type.equals("pc_case") || product.product_type.equals("power_supply")) && product.product_wattage == 0))
			&& product.product_price != 0
			//&& product.product_stock != 0 // stock can be nil
			&& product.product_flag_general != 0
			&& product.product_flag_gaming != 0
			&& product.product_flag_workstation != 0
			&& product.product_flag_budget != 0) {
			return true;
		}
		else return false;	
	}
	
	/** product object to string function
	 * @return product object information as a string
	 */
	@Override
	public String toString() {
		return "Product ["
			+ "product_id=" + product_id + ", "
			+ "product_type=" + product_type + ", "
			+ "product_supplier=" + product_supplier + ", "
			+ "product_description=" + product_description + ", "
			+ "product_wattage=" + product_wattage + ", "
			+ "product_price=" + product_price + ", "
			+ "product_stock=" + product_stock + ", "
			+ "product_general=" + product_flag_general + ", "
			+ "product_gaming=" + product_flag_gaming + ", "
			+ "product_workstation=" + product_flag_workstation + ", "
			+ "product_budget=" + product_flag_budget
			+ "]";
	}
	
}
