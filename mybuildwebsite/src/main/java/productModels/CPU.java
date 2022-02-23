package productModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * CPU contains attributes of the 'cpu' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * The class is linked to the parent Product class by 'product_id',
 * representing an inheritance relationship.
 * Methods for CRUD operations are implemented in the CPUDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="cpu")   
@PrimaryKeyJoinColumn(name="product_id")
public class CPU extends Product {

	// variables with persistence annotation and mapping as per DB annotations
	
	@Column(name="cpu_id") private String cpu_id;
	@Column(name="cpu_socket") private String cpu_socket;
	@Column(name="cpu_category") private String cpu_category;
	@Column(name="cpu_cores") private int cpu_cores;
	@Column(name="cpu_threads") private int cpu_threads;
	@Column(name="cpu_base_clock") private int cpu_base_clock;
	@Column(name="cpu_cache") private int cpu_cache;
	@Column(name="cpu_graphics") private String cpu_graphics;
	@Column(name="cpu_cooler") private char cpu_cooler;
	
	/** default no argument constructor */
	public CPU() {
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
	 * @param cpuID CPU ID
	 * @param socket CPU socket type
	 * @param category CPU category
	 * @param cores CPU cores
	 * @param threads CPU threads
	 * @param baseClock CPU base clock speed
	 * @param cache CPU cache memory
	 * @param graphics CPU integrated graphics type
	 * @param cooler CPU bundled cooler flag
	 */
	public CPU(String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String cpuID, String socket, String category, int cores,
			int threads, int baseClock, int cache, String graphics, char cooler) {
		super(type, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.cpu_id = cpuID;
		this.cpu_socket = socket;
		this.cpu_category = category;
		this.cpu_cores = cores;
		this.cpu_threads = threads;
		this.cpu_base_clock = baseClock;
		this.cpu_cache = cache;
		this.cpu_graphics = graphics;
		this.cpu_cooler = cooler;
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
	 * @param cpuID CPU ID
	 * @param socket CPU socket type
	 * @param category CPU category
	 * @param cores CPU cores
	 * @param threads CPU threads
	 * @param baseClock CPU base clock speed
	 * @param cache CPU cache memory
	 * @param graphics CPU integrated graphics type
	 * @param cooler CPU bundled cooler flag
	 */
	public CPU(int productID, String type, String supplier, String description, int wattage,
			double price, int stock, char general, char gaming, char workstation, char budget,
			String cpuID, String socket, String category, int cores,
			int threads, int baseClock, int cache, String graphics, char cooler) {
		super(productID, type, supplier, description, wattage, price, stock, general, gaming, workstation, budget);
		this.cpu_id = cpuID;
		this.cpu_socket = socket;
		this.cpu_category = category;
		this.cpu_cores = cores;
		this.cpu_threads = threads;
		this.cpu_base_clock = baseClock;
		this.cpu_cache = cache;
		this.cpu_graphics = graphics;
		this.cpu_cooler = cooler;
	}

	// getter and setter methods
	
	/**
	 * @return the cpu_id
	 */
	public String getCpu_id() {
		return cpu_id;
	}

	/**
	 * @param cpu_id the cpu_id to set
	 */
	public void setCpu_id(String cpuID) {
		this.cpu_id = cpuID;
	}

	/**
	 * @return the cpu_socket
	 */
	public String getCpu_socket() {
		return cpu_socket;
	}

	/**
	 * @param cpu_socket the cpu_socket to set
	 */
	public void setCpu_socket(String socket) {
		this.cpu_socket = socket;
	}

	/**
	 * @return the cpu_category
	 */
	public String getCpu_category() {
		return cpu_category;
	}

	/**
	 * @param cpu_category the cpu_category to set
	 */
	public void setCpu_category(String category) {
		this.cpu_category = category;
	}

	/**
	 * @return the cpu_cores
	 */
	public int getCpu_cores() {
		return cpu_cores;
	}

	/**
	 * @param cpu_cores the cpu_cores to set
	 */
	public void setCpu_cores(int cores) {
		this.cpu_cores = cores;
	}

	/**
	 * @return the cpu_threads
	 */
	public int getCpu_threads() {
		return cpu_threads;
	}

	/**
	 * @param cpu_threads the cpu_threads to set
	 */
	public void setCpu_threads(int threads) {
		this.cpu_threads = threads;
	}

	/**
	 * @return the cpu_base_clock
	 */
	public int getCpu_base_clock() {
		return cpu_base_clock;
	}

	/**
	 * @param cpu_base_clock the cpu_base_clock to set
	 */
	public void setCpu_base_clock(int baseClock) {
		this.cpu_base_clock = baseClock;
	}

	/**
	 * @return the cpu_cache
	 */
	public int getCpu_cache() {
		return cpu_cache;
	}

	/**
	 * @param cpu_cache the cpu_cache to set
	 */
	public void setCpu_cache(int cache) {
		this.cpu_cache = cache;
	}

	/**
	 * @return the cpu_graphics
	 */
	public String getCpu_graphics() {
		return cpu_graphics;
	}

	/**
	 * @param cpu_graphics the cpu_graphics to set
	 */
	public void setCpu_graphics(String graphics) {
		this.cpu_graphics = graphics;
	}

	/**
	 * @return the cpu_cooler
	 */
	public char getCpu_cooler() {
		return cpu_cooler;
	}

	/**
	 * @param cpu_cooler the cpu_cooler to set
	 */
	public void setCpu_cooler(char cooler) {
		this.cpu_cooler = cooler;
	}
	
	/** check if all product attributes have been completed, eg when updating product records
	 * @param cpu cpu object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeCPUObject(CPU cpu) {
		if(cpu.getProduct_id() != 0
			&& cpu.getProduct_type() != null
			&& cpu.getProduct_supplier() != null
			&& cpu.getProduct_description() != null
			&& cpu.getProduct_wattage() != 0
			&& cpu.getProduct_price() != 0
			//&& cpu.getProduct_stock() != 0 // stock can be nil :)
			&& cpu.getProduct_flag_general() != 0
			&& cpu.getProduct_flag_gaming() != 0
			&& cpu.getProduct_flag_workstation() != 0
			&& cpu.getProduct_flag_budget() != 0
			&& cpu.cpu_id != null
			&& cpu.cpu_socket != null
			&& cpu.cpu_category != null
			&& cpu.cpu_cores != 0
			&& cpu.cpu_threads != 0
			&& cpu.cpu_base_clock != 0
			&& cpu.cpu_cache != 0
			&& cpu.cpu_graphics != null
			&& cpu.cpu_cooler != 0) {
			return true;
		}
		else return false;	
	}
	
	/** check if all product attributes except id have been completed, eg when creating new product records
	 * @param cpu cpu object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeCPURecord(CPU cpu) {
		if(cpu.getProduct_type() != null
			&& cpu.getProduct_supplier() != null
			&& cpu.getProduct_description() != null
			&& cpu.getProduct_wattage() != 0
			&& cpu.getProduct_price() != 0
			//&& cpu.getProduct_stock() != 0 // stock can be nil :)
			&& cpu.getProduct_flag_general() != 0
			&& cpu.getProduct_flag_gaming() != 0
			&& cpu.getProduct_flag_workstation() != 0
			&& cpu.getProduct_flag_budget() != 0
			&& cpu.cpu_socket != null
			&& cpu.cpu_category != null
			&& cpu.cpu_cores != 0
			&& cpu.cpu_threads != 0
			&& cpu.cpu_base_clock != 0
			&& cpu.cpu_cache != 0
			&& cpu.cpu_graphics != null
			&& cpu.cpu_cooler != 0) {
			return true;
		}
		else return false;	
	}
    
	/** cpu object to string function
	 * @return cpu object information as a string
	 */
	@Override
	public String toString() {
		return super.toString() + ", "
			+ "CPU ["
			+ "cpu_id=" + cpu_id + ", "
			+ "cpu_socket=" + cpu_socket + ", "
			+ "cpu_category=" + cpu_category + ", "
			+ "cpu_cores=" + cpu_cores + ", "
			+ "cpu_threads=" + cpu_threads + ", "
			+ "cpu_base_clock=" + cpu_base_clock + ", "
			+ "cpu_cache=" + cpu_cache + ", "
			+ "cpu_graphics=" + cpu_graphics + ", "
			+ "cpu_cooler=" + cpu_cooler
			+ "]";
	}
	
}
