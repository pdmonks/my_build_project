package productModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * GPU contains attributes of the 'gpu' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * The class is linked to the parent Product class by 'product_id',
 * representing an inheritance relationship.
 * Methods for CRUD operations are implemented in the GPUDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="gpu")
@PrimaryKeyJoinColumn(name="product_id")
public class GPU extends Product {

	// variables with persistence annotation and mapping as per DB attributes
	
	@Column(name="gpu_id") private String gpu_id;
	@Column(name="gpu_category") private String gpu_category;
	@Column(name="gpu_base_clock") private int gpu_base_clock;
	@Column(name="gpu_ram_capacity") private int gpu_ram_capacity;
	@Column(name="gpu_ram_ddr") private int gpu_ram_ddr;
	@Column(name="gpu_cores") private int gpu_cores;
	
	/** default no argument constructor */
	public GPU() {
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
	 * @param gpuID GPU ID
	 * @param category GPU category
	 * @param baseClock GPU base clock speed
	 * @param capacity GPU RAM capacity
	 * @param ddr GPU RAM DDR type
	 * @param cores GPU cores
	 */
	public GPU(String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String gpuID, String category, int baseClock, int capacity,
			int ddr, int cores) {
		super(type, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.gpu_id = gpuID;
		this.gpu_category = category;
		this.gpu_base_clock = baseClock;
		this.gpu_ram_capacity = capacity;
		this.gpu_ram_ddr = ddr;
		this.gpu_cores = cores;
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
	 * @param gpuID GPU ID
	 * @param category GPU category
	 * @param baseClock GPU base clock speed
	 * @param capacity GPU RAM capacity
	 * @param ddr GPU RAM DDR type
	 * @param cores GPU cores
	 */
	public GPU(int productID, String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String gpuID, String category, int baseClock, int capacity,
			int ddr, int cores) {
		super(productID, type, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.gpu_id = gpuID;
		this.gpu_category = category;
		this.gpu_base_clock = baseClock;
		this.gpu_ram_capacity = capacity;
		this.gpu_ram_ddr = ddr;
		this.gpu_cores = cores;
	}

	// getter and setter methods
	
	/**
	 * @return the gpu_id
	 */
	public String getGpu_id() {
		return gpu_id;
	}

	/**
	 * @param gpu_id the gpu_id to set
	 */
	public void setGpu_id(String gpuID) {
		this.gpu_id = gpuID;
	}

	/**
	 * @return the gpu_category
	 */
	public String getGpu_category() {
		return gpu_category;
	}

	/**
	 * @param gpu_category the gpu_category to set
	 */
	public void setGpu_category(String category) {
		this.gpu_category = category;
	}

	/**
	 * @return the gpu_base_clock
	 */
	public int getGpu_base_clock() {
		return gpu_base_clock;
	}

	/**
	 * @param gpu_base_clock the gpu_base_clock to set
	 */
	public void setGpu_base_clock(int baseClock) {
		this.gpu_base_clock = baseClock;
	}

	/**
	 * @return the gpu_ram_capacity
	 */
	public int getGpu_ram_capacity() {
		return gpu_ram_capacity;
	}

	/**
	 * @param gpu_ram_capacity the gpu_ram_capacity to set
	 */
	public void setGpu_ram_capacity(int capacity) {
		this.gpu_ram_capacity = capacity;
	}

	/**
	 * @return the gpu_ram_ddr
	 */
	public int getGpu_ram_ddr() {
		return gpu_ram_ddr;
	}

	/**
	 * @param gpu_ram_ddr the gpu_ram_ddr to set
	 */
	public void setGpu_ram_ddr(int ddr) {
		this.gpu_ram_ddr = ddr;
	}

	/**
	 * @return the gpu_cores
	 */
	public int getGpu_cores() {
		return gpu_cores;
	}

	/**
	 * @param gpu_cores the gpu_cores to set
	 */
	public void setGpu_cores(int cores) {
		this.gpu_cores = cores;
	}
	
	/** check if all product attributes have been completed, eg when updating product records
	 * @param gpu gpu object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeGPUObject(GPU gpu) {
		if(gpu.getProduct_id() != 0
			&& gpu.getProduct_type() != null
			&& gpu.getProduct_supplier() != null
			&& gpu.getProduct_description() != null
			&& gpu.getProduct_wattage() != 0
			&& gpu.getProduct_price() != 0
			//&& gpu.getProduct_stock() != 0 // stock can be nil :)
			&& gpu.getProduct_flag_general() != 0
			&& gpu.getProduct_flag_gaming() != 0
			&& gpu.getProduct_flag_workstation() != 0
			&& gpu.getProduct_flag_budget() != 0
			&& gpu.gpu_id != null
			&& gpu.gpu_category != null
			&& gpu.gpu_base_clock != 0
			&& gpu.gpu_ram_capacity != 0
			&& gpu.gpu_ram_ddr != 0
			&& gpu.gpu_cores != 0) {
			return true;
		}
		else return false;	
	}
	
	/** check if all product attributes except id have been completed, eg when creating new product records
	 * @param gpu gpu object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeGPURecord(GPU gpu) {
		if(gpu.getProduct_type() != null
			&& gpu.getProduct_supplier() != null
			&& gpu.getProduct_description() != null
			&& gpu.getProduct_wattage() != 0
			&& gpu.getProduct_price() != 0
			//&& gpu.getProduct_stock() != 0 // stock can be nil :)
			&& gpu.getProduct_flag_general() != 0
			&& gpu.getProduct_flag_gaming() != 0
			&& gpu.getProduct_flag_workstation() != 0
			&& gpu.getProduct_flag_budget() != 0
			&& gpu.gpu_category != null
			&& gpu.gpu_base_clock != 0
			&& gpu.gpu_ram_capacity != 0
			&& gpu.gpu_ram_ddr != 0
			&& gpu.gpu_cores != 0) {
			return true;
		}
		else return false;	
	}
    
	/** gpu object to string function
	 * @return gpu object information as a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", "
			+ "GPU ["
			+ "gpu_id=" + gpu_id + ", "
			+ "gpu_category=" + gpu_category + ", "
			+ "gpu_base_clock=" + gpu_base_clock + ", "
			+ "gpu_ram_capacity=" + gpu_ram_capacity + ", "
			+ "gpu_ram_ddr=" + gpu_ram_ddr + ", "
			+ "gpu_cores=" + gpu_cores
			+ "]";
	}

}
