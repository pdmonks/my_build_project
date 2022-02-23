package productModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * RAM contains attributes of the 'ram' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * The class is linked to the parent Product class by 'product_id',
 * representing an inheritance relationship.
 * Methods for CRUD operations are implemented in the RAMDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="ram")
@PrimaryKeyJoinColumn(name="product_id")
public class RAM extends Product {

	// variables with persistence annotation and mapping as per DB attributes
	
	@Column(name="ram_id") private String ram_id;
	@Column(name="ram_ddr") private int ram_ddr;
	@Column(name="ram_capacity") private int ram_capacity;
	@Column(name="ram_base_clock") private int ram_base_clock;
	@Column(name="ram_modules") private int ram_modules;
	
	/** default no argument constructor */
	public RAM() {
		super();
	}
	
	/**
	 * hibernate constructor
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
	 * @param ramID RAM ID
	 * @param ddr RAM DDR type
	 * @param capacity RAM capacity
	 * @param baseClock RAM base clock
	 * @param modules RAM number of modules
	 */
	public RAM(String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String ramID, int ddr, int capacity, int baseClock, int modules) {
		super(type, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.ram_id = ramID;
		this.ram_ddr = ddr;
		this.ram_capacity = capacity;
		this.ram_base_clock = baseClock;
		this.ram_modules = modules;
	}
	
	/**
	 * full constructor
	 * @param productID product ID
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
	 * @param ramID RAM ID
	 * @param ddr RAM DDR type
	 * @param capacity RAM capacity
	 * @param baseClock RAM base clock
	 * @param modules RAM number of modules
	 */
	public RAM(int productID, String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String ramID, int ddr, int capacity, int baseClock, int modules) {
		super(productID, type, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.ram_id = ramID;
		this.ram_ddr = ddr;
		this.ram_capacity = capacity;
		this.ram_base_clock = baseClock;
		this.ram_modules = modules;
	}

	// getter and setter methods
	
	/**
	 * @return the ram_id
	 */
	public String getRam_id() {
		return ram_id;
	}

	/**
	 * @param ram_id the ram_id to set
	 */
	public void setRam_id(String ramID) {
		this.ram_id = ramID;
	}

	/**
	 * @return the ram_ddr
	 */
	public int getRam_ddr() {
		return ram_ddr;
	}

	/**
	 * @param ram_ddr the ram_ddr to set
	 */
	public void setRam_ddr(int ddr) {
		this.ram_ddr = ddr;
	}

	/**
	 * @return the ram_capacity
	 */
	public int getRam_capacity() {
		return ram_capacity;
	}

	/**
	 * @param ram_capacity the ram_capacity to set
	 */
	public void setRam_capacity(int capacity) {
		this.ram_capacity = capacity;
	}

	/**
	 * @return the ram_base_clock
	 */
	public int getRam_base_clock() {
		return ram_base_clock;
	}

	/**
	 * @param ram_base_clock the ram_base_clock to set
	 */
	public void setRam_base_clock(int baseClock) {
		this.ram_base_clock = baseClock;
	}

	/**
	 * @return the ram_modules
	 */
	public int getRam_modules() {
		return ram_modules;
	}

	/**
	 * @param ram_modules the ram_modules to set
	 */
	public void setRam_modules(int modules) {
		this.ram_modules = modules;
	}
	
	/** check if all product attributes have been completed, eg when updating product records
	 * @param ram ram object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeRAMObject(RAM ram) {
		if(ram.getProduct_id() != 0
			&& ram.getProduct_type() != null
			&& ram.getProduct_supplier() != null
			&& ram.getProduct_description() != null
			&& ram.getProduct_wattage() != 0
			&& ram.getProduct_price() != 0
			//&& ram.getProduct_stock() != 0 // stock can be nil :)
			&& ram.getProduct_flag_general() != 0
			&& ram.getProduct_flag_gaming() != 0
			&& ram.getProduct_flag_workstation() != 0
			&& ram.getProduct_flag_budget() != 0
			&& ram.ram_id != null
			&& ram.ram_ddr != 0
			&& ram.ram_capacity != 0
			&& ram.ram_base_clock != 0
			&& ram.ram_modules != 0) {
			return true;
		}
		else return false;	
	}
	
	/** check if all product attributes except id have been completed, eg when creating new product records
	 * @param ram ram object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeRAMRecord(RAM ram) {
		if(ram.getProduct_type() != null
			&& ram.getProduct_supplier() != null
			&& ram.getProduct_description() != null
			&& ram.getProduct_wattage() != 0
			&& ram.getProduct_price() != 0
			//&& ram.getProduct_stock() != 0 // stock can be nil :)
			&& ram.getProduct_flag_general() != 0
			&& ram.getProduct_flag_gaming() != 0
			&& ram.getProduct_flag_workstation() != 0
			&& ram.getProduct_flag_budget() != 0
			&& ram.ram_ddr != 0
			&& ram.ram_capacity != 0
			&& ram.ram_base_clock != 0
			&& ram.ram_modules != 0) {
			return true;
		}
		else return false;	
	}
    
	/** ram object to string function
	 * @return ram object information as a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", "
			+ "RAM ["
			+ "ram_id=" + ram_id + ", "
			+ "ram_ddr=" + ram_ddr + ", "
			+ "ram_capacity=" + ram_capacity + ", "
			+ "ram_base_clock=" + ram_base_clock + ", "
			+ "ram_modules=" + ram_modules
			+ "]";
	}
	
}
