package buildModels;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Build contains attributes of the 'build' table in the database.
 * The class is mapped to the DB table using Persistence annotation.
 * A List of Build_Line objects is implemented in the Build class,
 * representing a one-to-many data relationship.
 * Methods for CRUD operations are implemented in the BuildDAO class.
 * 
 * @author Peter Monks
 * @version 1.0
 */

@Entity
@Table(name="build")
public class Build {

	// variables with persistence annotation and mapping as per DB attributes

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="build_id") private int build_id;
	
	@Column(name="customer_id") private int customer_id;
	@Column(name="staff_id") private int staff_id;
	@Column(name="build_date") private Date build_date;
	@Column(name="build_dispatch_date") private Date build_dispatch_date;
	@Column(name="build_status") private String build_status;
	@Column(name="build_flag_usage") private String build_flag_usage;
	@Column(name="build_flag_budget") private String build_flag_budget;
	@Column(name="build_delivery_charge") private double build_delivery_charge;
	@Column(name="build_total") private double build_total;
	@Column(name="build_vat_rate") private double build_vat_rate;
	
	// FOR ONE TO MANY RELATIONSHIP
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="build_id")
	private List<Build_Line> build_Lines;
	
	/** default no argument constructor */
	public Build() {}
	
	/**
	 * hibernate constructor (without auto-generated id)
	 * @param customerID customer ID
	 * @param staffID staff ID
	 * @param buildDate build date
	 * @param dispatchDate dispatch date
	 * @param status build status
	 * @param usage build usage flag
	 * @param budget build budget flag
	 * @param delivery build delivery price
	 * @param total build total price (excluding VAT)
	 * @param vatRate build VAT rate
	 */
	public Build(int customerID, int staffID, Date buildDate, Date dispatchDate, String status,
			String usage, String budget, double delivery, double total, double vatRate) {
		this.customer_id = customerID;
		this.staff_id = staffID;
		this.build_date = buildDate;
		this.build_dispatch_date = dispatchDate;
		this.build_status = status;
		this.build_flag_usage = usage;
		this.build_flag_budget = budget;
		this.build_delivery_charge = delivery;
		this.build_total = total;
		this.build_vat_rate = vatRate;
	}
	
	/**
	 * full constructor
	 * @param buildID build ID
	 * @param customerID customer ID
	 * @param staffID staff ID
	 * @param buildDate build date
	 * @param dispatchDate dispatch date
	 * @param status build status
	 * @param usage build usage flag
	 * @param budget build budget flag
	 * @param delivery build delivery price
	 * @param total build total price (excluding VAT)
	 * @param vatRate build VAT rate
	 */
	public Build(int buildID, int customerID, int staffID, Date buildDate, Date dispatchDate, String status,
			String usage, String budget, double delivery, double total, double vatRate) {
		this.build_id = buildID;
		this.customer_id = customerID;
		this.staff_id = staffID;
		this.build_date = buildDate;
		this.build_dispatch_date = dispatchDate;
		this.build_status = status;
		this.build_flag_usage = usage;
		this.build_flag_budget = budget;
		this.build_delivery_charge = delivery;
		this.build_total = total;
		this.build_vat_rate = vatRate;
	}

	// getter and setter methods
	
	// FOR ONE TO MANY RELATIONSHIP
	/**
	 * @return a list of build lines
	 */
	public List<Build_Line> getBuildLines() {
		return build_Lines;
	}
	
	// FOR ONE TO MANY RELATIONSHIP
	/**
	 * 
	 * @param build_Lines a list of build lines
	 */
	public void setBuildLines(List<Build_Line> build_Lines) {
		this.build_Lines = build_Lines;
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
	 * @return the customer_id
	 */
	public int getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id the customer_id to set
	 */
	public void setCustomer_id(int customerID) {
		this.customer_id = customerID;
	}

	/**
	 * @return the staff_id
	 */
	public int getStaff_id() {
		return staff_id;
	}

	/**
	 * @param staff_id the staff_id to set
	 */
	public void setStaff_id(int staffID) {
		this.staff_id = staffID;
	}

	/**
	 * @return the build_date
	 */
	public Date getBuild_date() {
		return build_date;
	}

	/**
	 * @param build_date the build_date to set
	 */
	public void setBuild_date(Date buildDate) {
		this.build_date = buildDate;
	}

	/**
	 * @return the build_dispatch_date
	 */
	public Date getBuild_dispatch_date() {
		return build_dispatch_date;
	}

	/**
	 * @param build_dispatch_date the build_dispatch_date to set
	 */
	public void setBuild_dispatch_date(Date dispatchDate) {
		this.build_dispatch_date = dispatchDate;
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
	public void setBuild_status(String status) {
		this.build_status = status;
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
	public void setBuild_flag_usage(String usage) {
		this.build_flag_usage = usage;
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
	public void setBuild_flag_budget(String budget) {
		this.build_flag_budget = budget;
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
	public void setBuild_total(double total) {
		this.build_total = total;
	}

	/**
	 * @return the build_vat_rate
	 */
	public double getBuild_vat_rate() {
		return build_vat_rate;
	}

	/**
	 * @param build_vat_rate the build_vat_rate to set
	 */
	public void setBuild_vat_rate(double vatRate) {
		this.build_vat_rate = vatRate;
	}
	
	/** object equals comparison function
     * @param build build object to compare
     * @return true if objects are equal, false if not
     */
    public boolean equals(Build build) {
        if (this.build_id != 0) {
            return this.build_id == build.build_id
            	&& this.customer_id == build.customer_id
	    		&& this.staff_id == build.staff_id
	    		&& this.build_date == build.build_date
	    		&& this.build_dispatch_date == build.build_dispatch_date
	    		&& this.build_status == build.build_status
	    		&& this.build_flag_usage == build.build_flag_usage
	    		&& this.build_flag_budget == build.build_flag_budget
	    		&& this.build_delivery_charge == build.build_delivery_charge
	    		&& this.build_total == build.build_total
	    		&& this.build_vat_rate == build.build_vat_rate;
        }
        else return false;
    }
    
	/** check if all build attributes have been completed, eg when updating build records
	 * @param build build object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeBuildObject(Build build) {
		if(build.build_id != 0
			&& build.getCustomer_id()!= 0
			&& build.getStaff_id() != 0
			&& build.getBuild_date() != null
			&& build.getBuild_status() != null
			&& build.getBuild_flag_usage() != null
			&& build.getBuild_flag_budget() != null
			&& build.getBuild_delivery_charge() != 0
			&& build.getBuild_total() != 0
			&& build.getBuild_vat_rate() != 0
			&& build.getBuildLines() != null
			
			&& (build.getBuild_dispatch_date() != null && build.getBuild_status().equals("d")
				|| build.getBuild_dispatch_date() == null)) {
			return true;
		}
		else return false;	
	}
    
	/** check if all build attributes except id have been completed, eg when creating new build records
	 * (excludes dispatch date as this will not be populated when the build is initially created)
	 * @param build build object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeBuildRecord(Build build) {
		if (build.getCustomer_id() != 0
			&& build.getStaff_id() != 0
			&& build.getBuild_date() != null
			&& build.getBuild_status() != null
			&& build.getBuild_flag_usage() != null
			&& build.getBuild_flag_budget() != null
			&& build.getBuild_delivery_charge() != 0
			&& build.getBuild_total() != 0
			&& build.getBuild_vat_rate() != 0
			&& build.getBuildLines() != null) {
			return true;
		}
		else return false;	
	}
	
	/** build object to string function
	 * @return build object information as a string
	 */
	@Override
	public String toString() {
		return "Build ["
			+ "build_id=" + build_id + ", "
			+ "customer_id=" + customer_id + ", "
			+ "staff_id=" + staff_id + ", "
			+ "build_date=" + build_date + ", "
			+ "build_dispatch_date=" + build_dispatch_date + ", "
			+ "build_status=" + build_status + ", "
			+ "build_flag_usage=" + build_flag_usage + ", "
			+ "build_flag_budget=" + build_flag_budget + ", "
			+ "build_delivery_charge=" + build_delivery_charge + ", "
			+ "build_total=" + build_total + ", "
			+ "build_vat_rate=" + build_vat_rate + ", "
			+ "build_lines=" + build_Lines.toString()
			+ "]";
	}

}
