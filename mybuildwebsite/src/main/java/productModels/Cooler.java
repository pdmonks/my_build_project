package productModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Cooler contains attributes of the 'cooler' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * The class is linked to the parent Product class by 'product_id',
 * representing an inheritance relationship.
 * Methods for CRUD operations are implemented in the CoolerDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="cooler")
@PrimaryKeyJoinColumn(name="product_id")
public class Cooler extends Product {

	// variables with persistence annotation and mapping as per DB attributes
	
	@Column(name="cooler_id") private String cooler_id;
	@Column(name="cooler_fclga1200") private char cooler_fclga1200;
	@Column(name="cooler_fclga1151") private char cooler_fclga1151;
	@Column(name="cooler_fclga2066") private char cooler_fclga2066;
	@Column(name="cooler_am4") private char cooler_am4;
	@Column(name="cooler_strx4") private char cooler_strx4;
	@Column(name="cooler_fan_speed") private int cooler_fan_speed;
	@Column(name="cooler_material") private String cooler_material;
	
	/** default no argument constructor */
	public Cooler() {
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
	 * @param coolerID cooler ID
	 * @param fclga1200 fclga1200 socket flag
	 * @param fclga1151 fclga1151 socket flag
	 * @param fclga2066 fclga2066 socket flag
	 * @param am4 am4 socket flag
	 * @param strx4 strx4 socket flag
	 * @param speed cooler fan speed
	 * @param material cooler material
	 */
	public Cooler(String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String coolerID, char fclga1200, char fclga1151, char fclga2066,
			char am4, char strx4, int speed, String material) {
		super();
		this.cooler_id = coolerID;
		this.cooler_fclga1200 = fclga1200;
		this.cooler_fclga1151 = fclga1151;
		this.cooler_fclga2066 = fclga2066;
		this.cooler_am4 = am4;
		this.cooler_strx4 = strx4;
		this.cooler_fan_speed = speed;
		this.cooler_material = material;
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
	 * @param coolerID cooler ID
	 * @param fclga1200 fclga1200 socket flag
	 * @param fclga1151 fclga1151 socket flag
	 * @param fclga2066 fclga2066 socket flag
	 * @param am4 am4 socket flag
	 * @param strx4 strx4 socket flag
	 * @param speed cooler fan speed
	 * @param material cooler material
	 */
	public Cooler(int productID, String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String coolerID, char fclga1200, char fclga1151, char fclga2066,
			char am4, char strx4, int speed, String material) {
		super();
		this.cooler_id = coolerID;
		this.cooler_fclga1200 = fclga1200;
		this.cooler_fclga1151 = fclga1151;
		this.cooler_fclga2066 = fclga2066;
		this.cooler_am4 = am4;
		this.cooler_strx4 = strx4;
		this.cooler_fan_speed = speed;
		this.cooler_material = material;
	}

	// getter and setter methods
	
	/**
	 * @return the cooler_id
	 */
	public String getCooler_id() {
		return cooler_id;
	}

	/**
	 * @param cooler_id the cooler_id to set
	 */
	public void setCooler_id(String coolerID) {
		this.cooler_id = coolerID;
	}

	/**
	 * @return the cooler_fclga1200
	 */
	public char getCooler_fclga1200() {
		return cooler_fclga1200;
	}

	/**
	 * @param cooler_fclga1200 the cooler_fclga1200 to set
	 */
	public void setCooler_fclga1200(char fclga1200) {
		this.cooler_fclga1200 = fclga1200;
	}

	/**
	 * @return the cooler_fclga1151
	 */
	public char getCooler_fclga1151() {
		return cooler_fclga1151;
	}

	/**
	 * @param cooler_fclga1151 the cooler_fclga1151 to set
	 */
	public void setCooler_fclga1151(char fclga1151) {
		this.cooler_fclga1151 = fclga1151;
	}

	/**
	 * @return the cooler_fclga2066
	 */
	public char getCooler_fclga2066() {
		return cooler_fclga2066;
	}

	/**
	 * @param cooler_fclga2066 the cooler_fclga2066 to set
	 */
	public void setCooler_fclga2066(char fclga2066) {
		this.cooler_fclga2066 = fclga2066;
	}

	/**
	 * @return the cooler_am4
	 */
	public char getCooler_am4() {
		return cooler_am4;
	}

	/**
	 * @param cooler_am4 the cooler_am4 to set
	 */
	public void setCooler_am4(char am4) {
		this.cooler_am4 = am4;
	}

	/**
	 * @return the cooler_strx4
	 */
	public char getCooler_strx4() {
		return cooler_strx4;
	}

	/**
	 * @param cooler_strx4 the cooler_strx4 to set
	 */
	public void setCooler_strx4(char strx4) {
		this.cooler_strx4 = strx4;
	}

	/**
	 * @return the cooler_fan_speed
	 */
	public int getCooler_fan_speed() {
		return cooler_fan_speed;
	}

	/**
	 * @param cooler_fan_speed the cooler_fan_speed to set
	 */
	public void setCooler_fan_speed(int speed) {
		this.cooler_fan_speed = speed;
	}

	/**
	 * @return the cooler_material
	 */
	public String getCooler_material() {
		return cooler_material;
	}

	/**
	 * @param cooler_material the cooler_material to set
	 */
	public void setCooler_material(String material) {
		this.cooler_material = material;
	}
	
	/** check if all product attributes have been completed, eg when updating product records
	 * @param cooler cooler object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeCoolerObject(Cooler cooler) {
		if(cooler.getProduct_id() != 0
			&& cooler.getProduct_type() != null
			&& cooler.getProduct_supplier() != null
			&& cooler.getProduct_description() != null
			&& cooler.getProduct_wattage() != 0
			&& cooler.getProduct_price() != 0
			//&& cooler.getProduct_stock() != 0 // stock can be nil :)
			&& cooler.getProduct_flag_general() != 0
			&& cooler.getProduct_flag_gaming() != 0
			&& cooler.getProduct_flag_workstation() != 0
			&& cooler.getProduct_flag_budget() != 0
			&& cooler.cooler_id != null
			&& cooler.cooler_fclga1200 != 0
			&& cooler.cooler_fclga1151 != 0
			&& cooler.cooler_fclga2066 != 0
			&& cooler.cooler_am4 != 0
			&& cooler.cooler_strx4 != 0
			&& cooler.cooler_fan_speed != 0
			&& cooler.cooler_material != null) {
			return true;
		}
		else return false;	
	}
	
	/** check if all product attributes except id have been completed, eg when creating new product records
	 * @param cooler cooler object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeCoolerRecord(Cooler cooler) {
		if(cooler.getProduct_type() != null
			&& cooler.getProduct_supplier() != null
			&& cooler.getProduct_description() != null
			&& cooler.getProduct_wattage() != 0
			&& cooler.getProduct_price() != 0
			//&& cooler.getProduct_stock() != 0 // stock can be nil :)
			&& cooler.getProduct_flag_general() != 0
			&& cooler.getProduct_flag_gaming() != 0
			&& cooler.getProduct_flag_workstation() != 0
			&& cooler.getProduct_flag_budget() != 0
			&& cooler.cooler_fclga1200 != 0
			&& cooler.cooler_fclga1151 != 0
			&& cooler.cooler_fclga2066 != 0
			&& cooler.cooler_am4 != 0
			&& cooler.cooler_strx4 != 0
			&& cooler.cooler_fan_speed != 0
			&& cooler.cooler_material != null) {
			return true;
		}
		else return false;	
	}
    
	/** cooler object to string function
	 * @return cooler object information as a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", "
			+ "Cooler ["
			+ "cooler_id=" + cooler_id + ", "
			+ "cooler_fclga1200=" + cooler_fclga1200 + ", "
			+ "cooler_fclga1151=" + cooler_fclga1151 + ", "
			+ "cooler_fclga2066=" + cooler_fclga2066 + ", "
			+ "cooler_am4=" + cooler_am4 + ", "
			+ "cooler_strx4=" + cooler_strx4 + ", "
			+ "cooler_fan_speed=" + cooler_fan_speed + ", "
			+ "cooler_material=" + cooler_material
			+ "]";
	}
	
}
