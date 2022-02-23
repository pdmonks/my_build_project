package productModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Power_Supply contains attributes of the 'power_supply' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * The class is linked to the parent Product class by 'product_id',
 * representing an inheritance relationship.
 * Methods for CRUD operations are implemented in the Power_SupplyDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="power_supply")
@PrimaryKeyJoinColumn(name="product_id")
public class Power_Supply extends Product {

	// variables with persistence annotation and mapping as per DB attributes
	
	@Column(name="power_supply_id") private String power_supply_id;
	@Column(name="power_supply_wattage") private int power_supply_wattage;
	@Column(name="power_supply_type") private String power_supply_type;
	
	/** default constructor */
	public Power_Supply() {
		super();
	}

	/**
	 * hibernate constructor
	 * @param productType product type
	 * @param supplier product supplier
	 * @param description product description
	 * @param productWattage product wattage
	 * @param price product price
	 * @param stock product stock
	 * @param general product general flag
	 * @param gaming product gaming flag
	 * @param workstation product workstation flag
	 * @param budget product budget flag
	 * @param powerSupplyID power supply ID
	 * @param powerSupplyWattage power supply wattage output
	 * @param powerSupplyType power supply module type
	 */
	public Power_Supply(String productType, String supplier, String description, int productWattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String powerSupplyID, int powerSupplyWattage, String powerSupplyType) {
		super(productType, supplier, description, productWattage, price, stock, general, gaming, workstation, budget);
		this.power_supply_id = powerSupplyID;
		this.power_supply_wattage = powerSupplyWattage;
		this.power_supply_type = powerSupplyType;
	}	
	
	/**
	 * full constructor
	 * @param productID product ID
	 * @param productType product type
	 * @param supplier product supplier
	 * @param description product description
	 * @param productWattage product wattage
	 * @param price product price
	 * @param stock product stock
	 * @param general product general flag
	 * @param gaming product gaming flag
	 * @param workstation product workstation flag
	 * @param budget product budget flag
	 * @param powerSupplyID power supply ID
	 * @param powerSupplyWattage power supply wattage output
	 * @param powerSupplyType power supply module type
	 */
	public Power_Supply(int productID, String productType, String supplier, String description, int productWattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String powerSupplyID, int powerSupplyWattage, String powerSupplyType) {
		super(productID, productType, supplier, description, productWattage, price, stock, general, gaming, workstation, budget);
		this.power_supply_id = powerSupplyID;
		this.power_supply_wattage = powerSupplyWattage;
		this.power_supply_type = powerSupplyType;
	}

	// getter and setter methods
	
	/**
	 * @return the power_supply_id
	 */
	public String getPower_supply_id() {
		return power_supply_id;
	}

	/**
	 * @param power_supply_id the power_supply_id to set
	 */
	public void setPower_supply_id(String powerSupplyID) {
		this.power_supply_id = powerSupplyID;
	}

	/**
	 * @return the power_supply_wattage
	 */
	public int getPower_supply_wattage() {
		return power_supply_wattage;
	}

	/**
	 * @param power_supply_wattage the power_supply_wattage to set
	 */
	public void setPower_supply_wattage(int powerSupplyWattage) {
		this.power_supply_wattage = powerSupplyWattage;
	}
	
	/**
	 * @return the power_supply_type
	 */
	public String getPower_supply_type() {
		return power_supply_type;
	}

	/**
	 * @param power_supply_type the power_supply_type to set
	 */
	public void setPower_supply_type(String powerSupplyType) {
		this.power_supply_type = powerSupplyType;
	}
	
	/** check if all product attributes have been completed, eg when updating product records
	 * @param power_supply power_supply object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completePower_SupplyObject(Power_Supply power_supply) {
		if(power_supply.getProduct_id() != 0
			&& power_supply.getProduct_type() != null
			&& power_supply.getProduct_supplier() != null
			&& power_supply.getProduct_description() != null
			//&& power_supply.getProduct_wattage() != 0 // psu powers itself
			&& power_supply.getProduct_price() != 0
			//&& power_supply.getProduct_stock() != 0 // stock can be nil :)
			&& power_supply.getProduct_flag_general() != 0
			&& power_supply.getProduct_flag_gaming() != 0
			&& power_supply.getProduct_flag_workstation() != 0
			&& power_supply.getProduct_flag_budget() != 0
			&& power_supply.power_supply_id != null
			&& power_supply.power_supply_wattage != 0
			&& power_supply.power_supply_type != null) {
			return true;
		}
		else return false;	
	}
	
	/** check if all product attributes except id have been completed, eg when creating new product records
	 * @param power_supply power_supply object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completePower_SupplyRecord(Power_Supply power_supply) {
		if(power_supply.getProduct_type() != null
			&& power_supply.getProduct_supplier() != null
			&& power_supply.getProduct_description() != null
			//&& power_supply.getProduct_wattage() != 0 // psu powers itself
			&& power_supply.getProduct_price() != 0
			//&& power_supply.getProduct_stock() != 0 // stock can be nil :)
			&& power_supply.getProduct_flag_general() != 0
			&& power_supply.getProduct_flag_gaming() != 0
			&& power_supply.getProduct_flag_workstation() != 0
			&& power_supply.getProduct_flag_budget() != 0
			&& power_supply.power_supply_wattage != 0
			&& power_supply.power_supply_type != null) {
			return true;
		}
		else return false;	
	}
    
	/** cpu object to string function
	 * @return power_supply object information as a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", "
			+ "Power_Supply ["
			+ "power_supply_id=" + power_supply_id + ", "
			+ "power_supply_wattage=" + power_supply_wattage + ", "
			+ "power_supply_type=" + power_supply_type
			+ "]";
	}

}
