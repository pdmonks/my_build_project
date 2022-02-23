package productModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Storage contains attributes of the 'storage' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * The class is linked to the parent Product class by 'product_id',
 * representing an inheritance relationship.
 * Methods for CRUD operations are implemented in the StorageDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="storage")
@PrimaryKeyJoinColumn(name="product_id")
public class Storage extends Product {

	// variables with persistence annotation and mapping as per DB attributes
	
	@Column(name="storage_id") private String storage_id;
	@Column(name="storage_type") private String storage_type;
	@Column(name="storage_capacity") private int storage_capacity;
	@Column(name="storage_read_speed") private int storage_read_speed;
	@Column(name="storage_write_speed") private int storage_write_speed;
	
	/** default no argument constructor */
	public Storage() {
		super();
	}
	
	/**
	 * hibernate constructor
	 * @param productType product type
	 * @param supplier product supplier
	 * @param description product description
	 * @param wattage product wattage
	 * @param price product price
	 * @param stock product stock
	 * @param general product general flag
	 * @param gaming product gaming flag
	 * @param workstation product workstation flag
	 * @param budget product budget flag
	 * @param storageID storage ID
	 * @param storageType storage interface type
	 * @param capacity storage capacity
	 * @param readSpeed storage read speed
	 * @param writeSpeed storage write speed
	 */
	public Storage(String productType, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String storageID, String storageType, int capacity, int readSpeed,
			int writeSpeed) {
		super(productType, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.storage_id = storageID;
		this.storage_type = storageType;
		this.storage_capacity = capacity;
		this.storage_read_speed = readSpeed;
		this.storage_write_speed = writeSpeed;
	}
	
	/**
	 * full constructor
	 * @param productID product ID
	 * @param productType product type
	 * @param supplier product supplier
	 * @param description product description
	 * @param wattage product wattage
	 * @param price product price
	 * @param stock product stock
	 * @param general product general flag
	 * @param gaming product gaming flag
	 * @param workstation product workstation flag
	 * @param budget product budget flag
	 * @param storageID storage ID
	 * @param storageType storage interface type
	 * @param capacity storage capacity
	 * @param readSpeed storage read speed
	 * @param writeSpeed storage write speed
	 */
	public Storage(int productID, String productType, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String storageID, String storageType, int capacity, int readSpeed,
			int writeSpeed) {
		super(productID, productType, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.storage_id = storageID;
		this.storage_type = storageType;
		this.storage_capacity = capacity;
		this.storage_read_speed = readSpeed;
		this.storage_write_speed = writeSpeed;
	}

	// getter and setter methods
	
	/**
	 * @return the storage_id
	 */
	public String getStorage_id() {
		return storage_id;
	}

	/**
	 * @param storage_id the storage_id to set
	 */
	public void setStorage_id(String storageID) {
		this.storage_id = storageID;
	}

	/**
	 * @return the storage_type
	 */
	public String getStorage_type() {
		return storage_type;
	}

	/**
	 * @param storage_type the storage_type to set
	 */
	public void setStorage_type(String storageType) {
		this.storage_type = storageType;
	}

	/**
	 * @return the storage_capacity
	 */
	public int getStorage_capacity() {
		return storage_capacity;
	}

	/**
	 * @param storage_capacity the storage_capacity to set
	 */
	public void setStorage_capacity(int capacity) {
		this.storage_capacity = capacity;
	}

	/**
	 * @return the storage_read_speed
	 */
	public int getStorage_read_speed() {
		return storage_read_speed;
	}

	/**
	 * @param storage_read_speed the storage_read_speed to set
	 */
	public void setStorage_read_speed(int readSpeed) {
		this.storage_read_speed = readSpeed;
	}

	/**
	 * @return the storage_write_speed
	 */
	public int getStorage_write_speed() {
		return storage_write_speed;
	}

	/**
	 * @param storage_write_speed the storage_write_speed to set
	 */
	public void setStorage_write_speed(int writeSpeed) {
		this.storage_write_speed = writeSpeed;
	}
	
	/** check if all product attributes have been completed, eg when updating product records
	 * @param storage storage object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeStorageObject(Storage storage) {
		if(storage.getProduct_id() != 0
			&& storage.getProduct_type() != null
			&& storage.getProduct_supplier() != null
			&& storage.getProduct_description() != null
			&& storage.getProduct_wattage() != 0
			&& storage.getProduct_price() != 0
			//&& storage.getProduct_stock() != 0 // stock can be nil :)
			&& storage.getProduct_flag_general() != 0
			&& storage.getProduct_flag_gaming() != 0
			&& storage.getProduct_flag_workstation() != 0
			&& storage.getProduct_flag_budget() != 0
			&& storage.storage_id != null
			&& storage.storage_type != null
			&& storage.storage_capacity != 0
			&& storage.storage_read_speed != 0
			&& storage.storage_write_speed != 0) {
			return true;
		}
		else return false;	
	}
	
	/** check if all product attributes except id have been completed, eg when creating new product records
	 * @param storage storage object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeStorageRecord(Storage storage) {
		if(storage.getProduct_type() != null
			&& storage.getProduct_supplier() != null
			&& storage.getProduct_description() != null
			&& storage.getProduct_wattage() != 0
			&& storage.getProduct_price() != 0
			//&& storage.getProduct_stock() != 0 // stock can be nil :)
			&& storage.getProduct_flag_general() != 0
			&& storage.getProduct_flag_gaming() != 0
			&& storage.getProduct_flag_workstation() != 0
			&& storage.getProduct_flag_budget() != 0
			&& storage.storage_type != null
			&& storage.storage_capacity != 0
			&& storage.storage_read_speed != 0
			&& storage.storage_write_speed != 0) {
			return true;
		}
		else return false;	
	}
    
	/** storage object to string function
	 * @return storage object information as a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", "
			+ "Storage ["
			+ "storage_id=" + storage_id + ", "
			+ "storage_type=" + storage_type + ", "
			+ "storage_capacity=" + storage_capacity + ", "
			+ "storage_read_speed=" + storage_read_speed + ", "
			+ "storage_write_speed=" + storage_write_speed
			+ "]";
	}
	
}
