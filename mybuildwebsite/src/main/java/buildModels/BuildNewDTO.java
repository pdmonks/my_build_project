package buildModels;

/**
 * BuildNewDTO contains attributes of the 'Build' and 'Build_Line' classes.
 * It is a DTO allowing the attributes of both classes needed to manage builds
 * in the web application to be sent and recevied in a single object.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class BuildNewDTO {

	// variables (build)
	private int build_id;
	private int customer_id;
	private String build_status;
	private String build_flag_usage;
	private String build_flag_budget;
	private double build_delivery_charge;
	private double build_total;
	
	// variables (build_Line)
	private int product_id;
	private String build_line_product_type;
	private String build_line_product_description;
	private double build_line_price;
	private int build_line_quantity;
	
	/** default no argument constructor */
	public BuildNewDTO() {}
	
	/** full constructor from submitted information
	 * @param buildID build id
	 * @param customerID customer id
	 * @param status build status
	 * @param usage usage flag for build
	 * @param budget budget flag for build
	 * @param delivery delivery charge for build
	 * @param total total price of build (excluding VAT)
	 * @param productID product id (this will be a CPU as the first component selected)
	 * @param type product type
	 * @param description product description
	 * @param price product price at time of selection
	 * @param quantity quantity selected (should be 1 for all essential components
	 */
	public BuildNewDTO(int buildID, int customerID, String status, String usage, String budget, double delivery,
			double total, int productID, String type, String description, double price, int quantity) {
		this.build_id = buildID;
		this.customer_id = customerID;
		this.build_status = status;
		this.build_flag_usage = usage;
		this.build_flag_budget = budget;
		this.build_delivery_charge = delivery;
		this.build_total = total;
		this.product_id = productID;
		this.build_line_product_type = type;
		this.build_line_product_description = description;
		this.build_line_price = price;
		this.build_line_quantity = quantity;
	}

	/** clone constructor
	 * @param BuildNewDTO BuildNewDTO object to clone
	 */
	public BuildNewDTO(BuildNewDTO buildNewDTO) {
		this.build_id = buildNewDTO.build_id;
		this.customer_id = buildNewDTO.customer_id;
		this.build_status = buildNewDTO.build_status;
		this.build_flag_usage = buildNewDTO.build_flag_usage;
		this.build_flag_budget = buildNewDTO.build_flag_budget;
		this.build_delivery_charge = buildNewDTO.build_delivery_charge;
		this.build_total = buildNewDTO.build_total;
		this.product_id = buildNewDTO.product_id;
		this.build_line_product_type = buildNewDTO.build_line_product_type;
		this.build_line_product_description = buildNewDTO.build_line_product_description;
		this.build_line_price = buildNewDTO.build_line_price;
		this.build_line_quantity = buildNewDTO.build_line_quantity;
	}
	
	/** copy BuildNewDTO object function
     * @param BuildNewDTO BuildNewDTO object to copy
     */
    public void copyBuildNewDTO(BuildNewDTO buildNewDTO) {
    	this.build_id = buildNewDTO.build_id;
		this.customer_id = buildNewDTO.customer_id;
		this.build_status = buildNewDTO.build_status;
		this.build_flag_usage = buildNewDTO.build_flag_usage;
		this.build_flag_budget = buildNewDTO.build_flag_budget;
		this.build_delivery_charge = buildNewDTO.build_delivery_charge;
		this.build_total = buildNewDTO.build_total;
		this.product_id = buildNewDTO.product_id;
		this.build_line_product_type = buildNewDTO.build_line_product_type;
		this.build_line_product_description = buildNewDTO.build_line_product_description;
		this.build_line_price = buildNewDTO.build_line_price;
		this.build_line_quantity = buildNewDTO.build_line_quantity;
	}

    // getter and setter methods
    
	/**
	 * @return the build_id
	 */
	public int getBuild_id() {
		return build_id;
	}

	/**
	 * @param build_id the build_id to set
	 */
	public void setBuild_id(int build_id) {
		this.build_id = build_id;
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
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	/**
	 * @return the build_status
	 */
	public String getBuild_status() {
		return build_status;
	}

	/**
	 * @param build_status the build_status to set
	 */
	public void setBuild_status(String build_status) {
		this.build_status = build_status;
	}

	/**
	 * @return the build_flag_usage
	 */
	public String getBuild_flag_usage() {
		return build_flag_usage;
	}

	/**
	 * @param build_flag_usage the build_flag_usage to set
	 */
	public void setBuild_flag_usage(String build_flag_usage) {
		this.build_flag_usage = build_flag_usage;
	}

	/**
	 * @return the build_flag_budget
	 */
	public String getBuild_flag_budget() {
		return build_flag_budget;
	}

	/**
	 * @param build_flag_budget the build_flag_budget to set
	 */
	public void setBuild_flag_budget(String build_flag_budget) {
		this.build_flag_budget = build_flag_budget;
	}
	
	/**
	 * @return the build_delivery_charge
	 */
	public double getBuild_delivery_charge() {
		return build_delivery_charge;
	}

	/**
	 * @param build_delivery_charge the build_delivery_charge to set
	 */
	public void setBuild_delivery_charge(double build_delivery_charge) {
		this.build_delivery_charge = build_delivery_charge;
	}

	/**
	 * @return the build_total
	 */
	public double getBuild_total() {
		return build_total;
	}

	/**
	 * @param build_total the build_total to set
	 */
	public void setBuild_total(double build_total) {
		this.build_total = build_total;
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
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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
	public void setBuild_line_product_type(String build_line_product_type) {
		this.build_line_product_type = build_line_product_type;
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
	public void setBuild_line_product_description(String build_line_product_description) {
		this.build_line_product_description = build_line_product_description;
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
	public void setBuild_line_price(double build_line_price) {
		this.build_line_price = build_line_price;
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
	public void setBuild_line_quantity(int build_line_quantity) {
		this.build_line_quantity = build_line_quantity;
	}

	/** BuildNewDTO object to string function
	 * @return BuildNewDTO object information as a string
	 */
	@Override
	public String toString() {
		return "BuildNewDTO [build_id=" + build_id
			+ ", customer_id=" + customer_id
			+ ", build_status=" + build_status
			+ ", build_flag_usage=" + build_flag_usage
			+ ", build_flag_budget=" + build_flag_budget
			+ ", build_delivery_charge=" + build_delivery_charge
			+ ", build_total=" + build_total
			+ ", product_id=" + product_id
			+ ", build_line_product_type=" + build_line_product_type
			+ ", build_line_product_description=" + build_line_product_description
			+ ", build_line_price=" + build_line_price
			+ ", build_line_quantity=" + build_line_quantity
			+ "]";
	}
	
}
