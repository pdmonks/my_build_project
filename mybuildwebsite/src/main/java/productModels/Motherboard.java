package productModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Motherboard contains attributes of the 'motherboard' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * The class is linked to the parent Product class by 'product_id',
 * representing an inheritance relationship.
 * Methods for CRUD operations are implemented in the MotherboardDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="motherboard")
@PrimaryKeyJoinColumn(name="product_id")
public class Motherboard extends Product {

	// variables with persistence annotation and mapping as per DB attributes
	
	@Column(name="motherboard_id") private String motherboard_id;
	@Column(name="motherboard_cpu_socket") private String motherboard_cpu_socket;
	@Column(name="motherboard_chipset") private String motherboard_chipset;
	@Column(name="motherboard_form_factor") private String motherboard_form_factor;
	@Column(name="motherboard_m2_slots") private int motherboard_m2_slots;
	@Column(name="motherboard_sata_slots") private int motherboard_sata_slots;
	@Column(name="motherboard_pcie_slots") private int motherboard_pcie_slots;
	@Column(name="motherboard_ram_slots") private int motherboard_ram_slots;
	@Column(name="motherboard_ram_max") private int motherboard_ram_max;
	@Column(name="motherboard_ram_channel") private int motherboard_ram_channel;
	@Column(name="motherboard_ram_ddr") private int motherboard_ram_ddr;
	@Column(name="motherboard_ram_speed") private int motherboard_ram_speed;
	
	/** default no argument constructor */
	public Motherboard() {
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
	 * @param motherboardID motherboard ID
	 * @param cpuSocket motherboard CPU socket type
	 * @param chipset motherboard chipset
	 * @param formFactor motherboard form factor
	 * @param m2Slots motherboard M.2 slots
	 * @param sataSlots motherboard SATA number of slots
	 * @param pcieSlots motherboard PCIE number of slots
	 * @param ramSlots motherboard RAM number of slots
	 * @param ramMax motherboard RAM maximum capacity
	 * @param ramChannel motherboard RAM number of channels
	 * @param ramDDR motherboard RAM DDR type
	 * @param ramSpeed motherboard RAM base speed
	 */
	public Motherboard(String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String motherboardID, String cpuSocket, String chipset, String formFactor,
			int m2Slots, int sataSlots, int pcieSlots, int ramSlots, int ramMax, int ramChannel,
			int ramDDR, int ramSpeed) {
		super(type, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.motherboard_id = motherboardID;
		this.motherboard_cpu_socket = cpuSocket;
		this.motherboard_chipset = chipset;
		this.motherboard_form_factor = formFactor;
		this.motherboard_m2_slots = m2Slots;
		this.motherboard_sata_slots = sataSlots;
		this.motherboard_pcie_slots = pcieSlots;
		this.motherboard_ram_slots = ramSlots;
		this.motherboard_ram_max = ramMax;
		this.motherboard_ram_channel = ramChannel;
		this.motherboard_ram_ddr = ramDDR;
		this.motherboard_ram_speed = ramSpeed;
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
	 * @param motherboardID motherboard ID
	 * @param cpuSocket motherboard CPU socket type
	 * @param chipset motherboard chipset
	 * @param formFactor motherboard form factor
	 * @param m2Slots motherboard M.2 slots
	 * @param sataSlots motherboard SATA number of slots
	 * @param pcieSlots motherboard PCIE number of slots
	 * @param ramSlots motherboard RAM number of slots
	 * @param ramMax motherboard RAM maximum capacity
	 * @param ramChannel motherboard RAM number of channels
	 * @param ramDDR motherboard RAM DDR type
	 * @param ramSpeed motherboard RAM base speed
	 */
	public Motherboard(int productID, String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String motherboardID, String cpuSocket, String chipset, String formFactor,
			int m2Slots, int sataSlots, int pcieSlots, int ramSlots, int ramMax, int ramChannel,
			int ramDDR, int ramSpeed) {
		super(productID, type, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.motherboard_id = motherboardID;
		this.motherboard_cpu_socket = cpuSocket;
		this.motherboard_chipset = chipset;
		this.motherboard_form_factor = formFactor;
		this.motherboard_m2_slots = m2Slots;
		this.motherboard_sata_slots = sataSlots;
		this.motherboard_pcie_slots = pcieSlots;
		this.motherboard_ram_slots = ramSlots;
		this.motherboard_ram_max = ramMax;
		this.motherboard_ram_channel = ramChannel;
		this.motherboard_ram_ddr = ramDDR;
		this.motherboard_ram_speed = ramSpeed;
	}

	// getter and setter methods
	
	/**
	 * @return the motherboard_id
	 */
	public String getMotherboard_id() {
		return motherboard_id;
	}

	/**
	 * @param motherboard_id the motherboard_id to set
	 */
	public void setMotherboard_id(String motherboardID) {
		this.motherboard_id = motherboardID;
	}

	/**
	 * @return the motherboard_cpu_socket
	 */
	public String getMotherboard_cpu_socket() {
		return motherboard_cpu_socket;
	}

	/**
	 * @param motherboard_cpu_socket the motherboard_cpu_socket to set
	 */
	public void setMotherboard_cpu_socket(String cpuSocket) {
		this.motherboard_cpu_socket = cpuSocket;
	}

	/**
	 * @return the motherboard_chipset
	 */
	public String getMotherboard_chipset() {
		return motherboard_chipset;
	}

	/**
	 * @param motherboard_chipset the motherboard_chipset to set
	 */
	public void setMotherboard_chipset(String chipset) {
		this.motherboard_chipset = chipset;
	}

	/**
	 * @return the motherboard_form_factor
	 */
	public String getMotherboard_form_factor() {
		return motherboard_form_factor;
	}

	/**
	 * @param motherboard_form_factor the motherboard_form_factor to set
	 */
	public void setMotherboard_form_factor(String formFactor) {
		this.motherboard_form_factor = formFactor;
	}

	/**
	 * @return the motherboard_m2_slots
	 */
	public int getMotherboard_m2_slots() {
		return motherboard_m2_slots;
	}

	/**
	 * @param motherboard_m2_slots the motherboard_m2_slots to set
	 */
	public void setMotherboard_m2_slots(int m2Slots) {
		this.motherboard_m2_slots = m2Slots;
	}

	/**
	 * @return the motherboard_sata_slots
	 */
	public int getMotherboard_sata_slots() {
		return motherboard_sata_slots;
	}

	/**
	 * @param motherboard_sata_slots the motherboard_sata_slots to set
	 */
	public void setMotherboard_sata_slots(int sataSlots) {
		this.motherboard_sata_slots = sataSlots;
	}

	/**
	 * @return the motherboard_pcie_slots
	 */
	public int getMotherboard_pcie_slots() {
		return motherboard_pcie_slots;
	}

	/**
	 * @param motherboard_pcie_slots the motherboard_pcie_slots to set
	 */
	public void setMotherboard_pcie_slots(int pcieSlots) {
		this.motherboard_pcie_slots = pcieSlots;
	}

	/**
	 * @return the motherboard_ram_slots
	 */
	public int getMotherboard_ram_slots() {
		return motherboard_ram_slots;
	}

	/**
	 * @param motherboard_ram_slots the motherboard_ram_slots to set
	 */
	public void setMotherboard_ram_slots(int ramSlots) {
		this.motherboard_ram_slots = ramSlots;
	}

	/**
	 * @return the motherboard_ram_max
	 */
	public int getMotherboard_ram_max() {
		return motherboard_ram_max;
	}

	/**
	 * @param motherboard_ram_max the motherboard_ram_max to set
	 */
	public void setMotherboard_ram_max(int ramMax) {
		this.motherboard_ram_max = ramMax;
	}

	/**
	 * @return the motherboard_ram_channel
	 */
	public int getMotherboard_ram_channel() {
		return motherboard_ram_channel;
	}

	/**
	 * @param motherboard_ram_channel the motherboard_ram_channel to set
	 */
	public void setMotherboard_ram_channel(int ramChannel) {
		this.motherboard_ram_channel = ramChannel;
	}

	/**
	 * @return the motherboard_ram_ddr
	 */
	public int getMotherboard_ram_ddr() {
		return motherboard_ram_ddr;
	}

	/**
	 * @param motherboard_ram_ddr the motherboard_ram_ddr to set
	 */
	public void setMotherboard_ram_ddr(int ramDDR) {
		this.motherboard_ram_ddr = ramDDR;
	}

	/**
	 * @return the motherboard_ram_speed
	 */
	public int getMotherboard_ram_speed() {
		return motherboard_ram_speed;
	}

	/**
	 * @param motherboard_ram_speed the motherboard_ram_speed to set
	 */
	public void setMotherboard_ram_speed(int ramSpeed) {
		this.motherboard_ram_speed = ramSpeed;
	}

	/** check if all product attributes have been completed, eg when updating product records
	 * @param motherboard motherboard object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeMotherboardObject(Motherboard motherboard) {
		if(motherboard.getProduct_id() != 0
			&& motherboard.getProduct_type() != null
			&& motherboard.getProduct_supplier() != null
			&& motherboard.getProduct_description() != null
			&& motherboard.getProduct_wattage() != 0
			&& motherboard.getProduct_price() != 0
			//&& motherboard.getProduct_stock() != 0 // stock can be nil :)
			&& motherboard.getProduct_flag_general() != 0
			&& motherboard.getProduct_flag_gaming() != 0
			&& motherboard.getProduct_flag_workstation() != 0
			&& motherboard.getProduct_flag_budget() != 0
			&& motherboard.motherboard_id != null
			&& motherboard.motherboard_cpu_socket != null
			&& motherboard.motherboard_chipset != null
			&& motherboard.motherboard_form_factor != null
			&& motherboard.motherboard_m2_slots != 0
			&& motherboard.motherboard_sata_slots != 0
			&& motherboard.motherboard_pcie_slots != 0
			&& motherboard.motherboard_ram_slots != 0
			&& motherboard.motherboard_ram_max != 0
			&& motherboard.motherboard_ram_channel != 0
			&& motherboard.motherboard_ram_ddr != 0
			&& motherboard.motherboard_ram_speed != 0) {
			return true;
		}
		else return false;	
	}
	
	/** check if all product attributes except id have been completed, eg when creating new product records
	 * @param motherboard motherboard object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeMotherboardRecord(Motherboard motherboard) {
		if(motherboard.getProduct_type() != null
			&& motherboard.getProduct_supplier() != null
			&& motherboard.getProduct_description() != null
			&& motherboard.getProduct_wattage() != 0
			&& motherboard.getProduct_price() != 0
			//&& motherboard.getProduct_stock() != 0 // stock can be nil :)
			&& motherboard.getProduct_flag_general() != 0
			&& motherboard.getProduct_flag_gaming() != 0
			&& motherboard.getProduct_flag_workstation() != 0
			&& motherboard.getProduct_flag_budget() != 0
			&& motherboard.motherboard_cpu_socket != null
			&& motherboard.motherboard_chipset != null
			&& motherboard.motherboard_form_factor != null
			// && motherboard.motherboard_m2_slots != 0 // may be nil in some motherboards
			&& motherboard.motherboard_sata_slots != 0
			&& motherboard.motherboard_pcie_slots != 0
			&& motherboard.motherboard_ram_slots != 0
			&& motherboard.motherboard_ram_max != 0
			&& motherboard.motherboard_ram_channel != 0
			&& motherboard.motherboard_ram_ddr != 0
			&& motherboard.motherboard_ram_speed != 0) {
			return true;
		}
		else return false;	
	}
    
	/** motherboard object to string function
	 * @return motherboard object information as a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", "
			+ "Motherboard ["
			+ "motherboard_id=" + motherboard_id + ", "
			+ "motherboard_cpu_socket=" + motherboard_cpu_socket + ", "
			+ "motherboard_chipset=" + motherboard_chipset + ", "
			+ "motherboard_form_factor=" + motherboard_form_factor + ", "
			+ "motherboard_m2_slots=" + motherboard_m2_slots + ", "
			+ "motherboard_sata_slots=" + motherboard_sata_slots + ", "
			+ "motherboard_pcie_slots=" + motherboard_pcie_slots + ", "
			+ "motherboard_ram_slots=" + motherboard_ram_slots + ", "
			+ "motherboard_ram_max=" + motherboard_ram_max + ", "
			+ "motherboard_ram_channel=" + motherboard_ram_channel + ", "
			+ "motherboard_ram_ddr=" + motherboard_ram_ddr + ", "
			+ "motherboard_ram_speed=" + motherboard_ram_speed
			+ "]";
	}
	
}
