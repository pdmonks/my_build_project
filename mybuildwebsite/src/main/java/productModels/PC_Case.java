package productModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * PC_Case contains attributes of the 'pc_case' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * The class is linked to the parent Product class by 'product_id',
 * representing an inheritance relationship.
 * Methods for CRUD operations are implemented in the PC_CaseDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="pc_case")
@PrimaryKeyJoinColumn(name="product_id")
public class PC_Case extends Product {

	// variables with persistence annotation and mapping as per DB attributes
	
	@Column(name="pc_case_id") private String pc_case_id;
	@Column(name="pc_case_micro_atx") private char pc_case_micro_atx;
	@Column(name="pc_case_mini_itx") private char pc_case_mini_itx;
	@Column(name="pc_case_atx") private char pc_case_atx;
	@Column(name="pc_case_ceb") private char pc_case_ceb;
	@Column(name="pc_case_e_atx") private char pc_case_e_atx;
	@Column(name="pc_case_cooling_slots") private int pc_case_cooling_slots;
	@Column(name="pc_case_internal_bays") private int pc_case_internal_bays;
	@Column(name="pc_case_external_bays") private int pc_case_external_bays;

	/** default no argument constructor */
	public PC_Case() {
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
	 * @param caseID case ID
	 * @param microATX case micro ATX form factor flag
	 * @param miniITX case mini ITX form factor flag
	 * @param atx case ATX form factor flag
	 * @param ceb case CEB form factor flag
	 * @param eATX case eATX form factor flag
	 * @param coolingSlots case number of cooling slots
	 * @param internalBays case number of internal bays
	 * @param externalBays case number of external bays
	 */
	public PC_Case(String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String caseID, char microATX, char miniITX, char atx, char ceb,
			char eATX, int coolingSlots, int internalBays, int externalBays) {
		super();
		this.pc_case_id = caseID;
		this.pc_case_micro_atx = microATX;
		this.pc_case_mini_itx = miniITX;
		this.pc_case_atx = atx;
		this.pc_case_ceb = ceb;
		this.pc_case_e_atx = eATX;
		this.pc_case_cooling_slots = coolingSlots;
		this.pc_case_internal_bays = internalBays;
		this.pc_case_external_bays = externalBays;
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
	 * @param caseID case ID
	 * @param microATX case micro ATX form factor flag
	 * @param miniITX case mini ITX form factor flag
	 * @param atx case ATX form factor flag
	 * @param ceb case CEB form factor flag
	 * @param eATX case eATX form factor flag
	 * @param coolingSlots case number of cooling slots
	 * @param internalBays case number of internal bays
	 * @param externalBays case number of external bays
	 */
	public PC_Case(int productID, String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String caseID, char microATX, char miniITX, char atx, char ceb,
			char eATX, int coolingSlots, int internalBays, int externalBays) {
		super();
		this.pc_case_id = caseID;
		this.pc_case_micro_atx = microATX;
		this.pc_case_mini_itx = miniITX;
		this.pc_case_atx = atx;
		this.pc_case_ceb = ceb;
		this.pc_case_e_atx = eATX;
		this.pc_case_cooling_slots = coolingSlots;
		this.pc_case_internal_bays = internalBays;
		this.pc_case_external_bays = externalBays;
	}

	// getter and setter methods
	
	/**
	 * @return the case_id
	 */
	public String getPc_case_id() {
		return pc_case_id;
	}

	/**
	 * @param case_id the case_id to set
	 */
	public void setPc_case_id(String caseID) {
		this.pc_case_id = caseID;
	}

	/**
	 * @return the pc_case_micro_atx
	 */
	public char getPc_case_micro_atx() {
		return pc_case_micro_atx;
	}

	/**
	 * @param pc_case_micro_atx the pc_case_micro_atx to set
	 */
	public void setPc_case_micro_atx(char microATX) {
		this.pc_case_micro_atx = microATX;
	}

	/**
	 * @return the pc_case_mini_itx
	 */
	public char getPc_case_mini_itx() {
		return pc_case_mini_itx;
	}

	/**
	 * @param pc_case_mini_itx the pc_case_mini_itx to set
	 */
	public void setPc_case_mini_itx(char miniITX) {
		this.pc_case_mini_itx = miniITX;
	}

	/**
	 * @return the pc_case_atx
	 */
	public char getPc_case_atx() {
		return pc_case_atx;
	}

	/**
	 * @param pc_case_atx the pc_case_atx to set
	 */
	public void setPc_case_atx(char atx) {
		this.pc_case_atx = atx;
	}

	/**
	 * @return the pc_case_ceb
	 */
	public char getPc_case_ceb() {
		return pc_case_ceb;
	}

	/**
	 * @param pc_case_ceb the pc_case_ceb to set
	 */
	public void setPc_case_ceb(char ceb) {
		this.pc_case_ceb = ceb;
	}

	/**
	 * @return the pc_case_e_atx
	 */
	public char getPc_case_e_atx() {
		return pc_case_e_atx;
	}

	/**
	 * @param pc_case_e_atx the pc_case_e_atx to set
	 */
	public void setPc_case_e_atx(char eATX) {
		this.pc_case_e_atx = eATX;
	}

	/**
	 * @return the pc_case_cooling_slots
	 */
	public int getPc_case_cooling_slots() {
		return pc_case_cooling_slots;
	}

	/**
	 * @param pc_case_cooling_slots the pc_case_cooling_slots to set
	 */
	public void setPc_case_cooling_slots(int coolingSlots) {
		this.pc_case_cooling_slots = coolingSlots;
	}

	/**
	 * @return the pc_case_internal_bays
	 */
	public int getPc_case_internal_bays() {
		return pc_case_internal_bays;
	}

	/**
	 * @param pc_case_internal_bays the pc_case_internal_bays to set
	 */
	public void setPc_case_internal_bays(int internalBays) {
		this.pc_case_internal_bays = internalBays;
	}

	/**
	 * @return the pc_case_external_bays
	 */
	public int getPc_case_external_bays() {
		return pc_case_external_bays;
	}

	/**
	 * @param pc_case_external_bays the pc_case_external_bays to set
	 */
	public void setPc_case_external_bays(int externalBays) {
		this.pc_case_external_bays = externalBays;
	}
	
	/** check if all product attributes have been completed, eg when updating product records
	 * @param pc_case pc_case object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completePC_CaseObject(PC_Case pc_case) {
		if(pc_case.getProduct_id() != 0
			&& pc_case.getProduct_type() != null
			&& pc_case.getProduct_supplier() != null
			&& pc_case.getProduct_description() != null
			//&& pc_case.getProduct_wattage() != 0 // case does not require power
			&& pc_case.getProduct_price() != 0
			//&& pc_case.getProduct_stock() != 0 // stock can be nil :)
			&& pc_case.getProduct_flag_general() != 0
			&& pc_case.getProduct_flag_gaming() != 0
			&& pc_case.getProduct_flag_workstation() != 0
			&& pc_case.getProduct_flag_budget() != 0
			&& pc_case.pc_case_id != null
			&& pc_case.pc_case_micro_atx != 0
			&& pc_case.pc_case_mini_itx != 0
			&& pc_case.pc_case_atx != 0
			&& pc_case.pc_case_ceb != 0
			&& pc_case.pc_case_e_atx != 0
			&& pc_case.pc_case_cooling_slots != 0
			&& pc_case.pc_case_internal_bays != 0
			// && pc_case.pc_case_external_bays != 0 // may be nil in some cases
			) {
			return true;
		}
		else return false;	
	}
	
	/** check if all product attributes except id have been completed, eg when creating new product records
	 * @param pc_case pc_case object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completePC_CaseRecord(PC_Case pc_case) {
		if(pc_case.getProduct_type() != null
			&& pc_case.getProduct_supplier() != null
			&& pc_case.getProduct_description() != null
			&& pc_case.getProduct_supplier() != null
			&& pc_case.getProduct_description() != null
			//&& pc_case.getProduct_wattage() != 0 // case does not require power
			&& pc_case.getProduct_price() != 0
			//&& pc_case.getProduct_stock() != 0 // stock can be nil :)
			&& pc_case.getProduct_flag_general() != 0
			&& pc_case.getProduct_flag_gaming() != 0
			&& pc_case.getProduct_flag_workstation() != 0
			&& pc_case.getProduct_flag_budget() != 0
			&& pc_case.pc_case_micro_atx != 0
			&& pc_case.pc_case_mini_itx != 0
			&& pc_case.pc_case_atx != 0
			&& pc_case.pc_case_ceb != 0
			&& pc_case.pc_case_e_atx != 0
			&& pc_case.pc_case_cooling_slots != 0
			&& pc_case.pc_case_internal_bays != 0
			// && pc_case.pc_case_external_bays != 0 // may be nil in some cases
			) {
			return true;
		}
		else return false;	
	}
    
	/** pc_case object to string function
	 * @return pc_case object information as a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", "
			+ "PC_Case ["
			+ "pc_case_id=" + pc_case_id + ", "
			+ "pc_case_micro_atx=" + pc_case_micro_atx + ", "
			+ "pc_case_mini_itx=" + pc_case_mini_itx + ", "
			+ "pc_case_atx=" + pc_case_atx + ", "
			+ "pc_case_ceb=" + pc_case_ceb + ", "
			+ "pc_case_e_atx=" + pc_case_e_atx + ", "
			+ "pc_case_cooling_slots=" + pc_case_cooling_slots + ", "
			+ "pc_case_internal_bays=" + pc_case_internal_bays + ", "
			+ "pc_case_external_bays=" + pc_case_external_bays
			+ "]";
	}
	
}
