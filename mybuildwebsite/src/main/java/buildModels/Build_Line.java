package buildModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Build_Line contains attributes of the 'build_line' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * A List of Build_Line objects is implemented in the Build class,
 * representing a one-to-many data relationship.
 * Methods for CRUD operations are implemented in the BuildDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="build_line")
public class Build_Line {

	// variables with persistence mapping as per DB attributes
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="build_line_id") private int build_line_id;
	
	@Column(name="build_id") private int build_id;
	@Column(name="product_id") private int product_id;
	
	@Column(name="build_line_product_type") private String build_line_product_type;
	@Column(name="build_line_product_description") private String build_line_product_description;
	@Column(name="build_line_price") private double build_line_price;
	@Column(name="build_line_quantity") private int build_line_quantity;
	
	/** default no argument constructor */
	public Build_Line() {}
 
	/**
	 * hibernate constructor (no auto-generated IDs)
	 * @param productID product ID
	 * @param productType product type
	 * @param productDescription product description
	 * @param price product price
	 * @param quantity product quantity
	 */
	public Build_Line(int productID, String productType, String productDescription, double price, int quantity) {
		this.product_id = productID;
		this.build_line_product_type = productType;
		this.build_line_product_description = productDescription;
		this.build_line_price = price;
		this.build_line_quantity = quantity;
	}
	
	/**
	 * full constructor
	 * @param build_line_ID build line ID
	 * @param buildID build ID
	 * @param productID product ID
	 * @param productType product type
	 * @param productDescription product description
	 * @param price product price
	 * @param quantity product quantity
	 */
	public Build_Line(int build_line_ID, int buildID, int productID, String productType, String productDescription, double price, int quantity) {
		this.build_line_id = build_line_ID;
		this.build_id = buildID;
		this.product_id = productID;
		this.build_line_product_type = productType;
		this.build_line_product_description = productDescription;
		this.build_line_price = price;
		this.build_line_quantity = quantity;
	}
	
	// getter and setter methods
	
	/**
	 * @return the build_line_id
	 */
	public int getBuild_line_id() {
		return build_line_id;
	}

	/**
	 * @param build_line_id the build_line_id to set
	 */
	public void setBuild_line_id(int build_line_id) {
		this.build_line_id = build_line_id;
	}

	/**
	 * @return the build_id
	 */
	public int getBuild_id() {
		return build_id;
	}

	/**
	 * @param build_id the build_id to set
	 */
	public void setBuild_id(int buildID) {
		this.build_id = buildID;
	}

	/**
	 * @return the product_id
	 */
	public int getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(int productID) {
		this.product_id = productID;
	}

	/**
	 * @return the build_line_product_type
	 */
	public String getBuild_line_product_type() {
		return build_line_product_type;
	}

	/**
	 * @param build_line_product_type the build_line_product_type to set
	 */
	public void setBuild_line_product_type(String productType) {
		this.build_line_product_type = productType;
	}
	
	/**
	 * @return the build_line_product_description
	 */
	public String getBuild_line_product_description() {
		return build_line_product_description;
	}

	/**
	 * @param build_line_product_description the build_line_product_description to set
	 */
	public void setBuild_line_product_description(String productDescription) {
		this.build_line_product_description = productDescription;
	}

	/**
	 * @return the build_line_price
	 */
	public double getBuild_line_price() {
		return build_line_price;
	}

	/**
	 * @param build_line_price the build_line_price to set
	 */
	public void setBuild_line_price(double price) {
		this.build_line_price = price;
	}

	/**
	 * @return the build_line_quantity
	 */
	public int getBuild_line_quantity() {
		return build_line_quantity;
	}

	/**
	 * @param build_line_quantity the build_line_quantity to set
	 */
	public void setBuild_line_quantity(int quantity) {
		this.build_line_quantity = quantity;
	}
	
	/** object equals comparison function
     * @param build_line build_line object to compare
     * @return true if objects are equal, false if not
     */
    public boolean equals(Build_Line build_line) {
        if(this.build_line_id != 0) {
            return this.build_line_id == build_line.build_line_id
            	&& this.build_id == build_line.build_id
	    		&& this.product_id == build_line.product_id
	    		&& this.build_line_product_type == build_line.build_line_product_type
	    		&& this.build_line_product_description.equals(build_line.build_line_product_description)
	    		&& this.build_line_price == build_line.build_line_price
	    		&& this.build_line_quantity == build_line.build_line_quantity;
        }
        else return false;
    }
	
	/** build_line object to string function
	 * @return build_line object information as a string
	 */
	@Override
	public String toString() {
		return "Build_Line ["
			+ "build_line_id=" + build_line_id + ", "
			+ "build_id=" + build_id + ", "
			+ "product_id=" + product_id + ", "
			+ "build_line_product_type=" + build_line_product_type + ", "
			+ "build_line_product_description=" + build_line_product_description + ", "
			+ "build_line_price=" + build_line_price + ", "
			+ "build_line_quantity=" + build_line_quantity + ", "
			+ "]";
	}
	
}
