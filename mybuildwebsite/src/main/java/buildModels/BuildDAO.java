package buildModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;
import productModels.Product;
import productModels.ProductDAO;

/**
 * BuildDAO allows the data and methods of the 'Build' and'Build_Line' classes
 * to be accessed indirectly, protecting the integrity of the original classes.
 * Applications can utilise this class to perform CRUD operations on Build
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class BuildDAO {

	// variables
	private static BuildDAO buildDAO;
	private String query;
	private Build build;
	private ArrayList<Build> buildList;
	private String objectType = "class buildModels.Build"; // for Hibernate session factory maker
	
	/** private constructor */
	private BuildDAO() {}
	
	/** singleton object manager
	 * @return BuildDAO object (new object only if not already instantiated)
	 */
	public static synchronized BuildDAO getBuildDAO() {
		if(buildDAO == null) {
			buildDAO = new BuildDAO();
		}
		return buildDAO;
	}

	/** overridden clone constructor
	 * prevents construction of clone objects
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/** clone constructor
	 * prevents construction of clone objects
	 * @param clone BuildDAO clone object
	 */
	public BuildDAO(BuildDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}	
	
	/**
	 * retrieve existing record by build id value
	 * @param id Build id
	 * @return Build object
	 */
	public Build retrieveBuildByBuildId(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveBuildById");
		query = ("FROM Build where build_id = '" + id + "'");
		buildList = queryBuild(query);
		if (buildList.size() > 0) {
			return buildList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve existing records by customer id value
	 * @param id Customer ID
	 * @return ArrayList of Build objects
	 */
	public ArrayList<Build> retrieveBuildsByCustomerId(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveBuildsByCustomerId");
		query = ("FROM Build where customer_id = '" + id + "' ORDER BY build_id DESC");
		buildList = queryBuild(query);
		if (buildList.size() > 0) {
			return buildList;
		}
		return null;
	}
	
	/**
	 * retrieve existing records with complete/dispatched status by customer id value for build history
	 * @param id Customer ID
	 * @return ArrayList of Build objects
	 */
	public ArrayList<Build> retrieveBuildHistoryByCustomerId(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveBuildHistoryByCustomerId");
		query = ("FROM Build where customer_id = '" + id + "' AND build_status = 'p' OR build_status = 'd' ORDER BY build_id DESC");
		buildList = queryBuild(query);
		if (buildList.size() > 0) {
			return buildList;
		}
		return null;
	}
	
	/** retrieve existing records by build status value */
	public ArrayList<Build> retrieveBuildsByStatus(String status) {
		DebugOptions.debugOutput("\nMethod: retrieveBuildByStatus");
		query = ("FROM Build where build_status = '" + status + "'");
		buildList = queryBuild(query);
		if (buildList.size() > 0) {
			return buildList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of builds, one for each record retrieved
	 */
	private ArrayList<Build> queryBuild(String query) {
		DebugOptions.debugOutput("\nMethod: queryBuild"); 
		
		buildList = new ArrayList<Build>();
		
		// retrieve builds as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move builds into list of builds
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			Build[] builds = objects.toArray(new Build[objects.size()]);
			
			for(Build b: builds) {
				buildList.add(b);
			}
		}
		return buildList;	
	}
	
	/**
	 * create new record
	 * @param build Build object
	 * @return int 1 for success 0 for failure
	 */
	public int createBuild(Build build) {
		DebugOptions.debugOutput("\nMethod: createBuild");
		// check complete object has been submitted
		if(Build.completeBuildRecord(build)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(build);
		} else {
			System.out.println("Build record information incomplete:\n" + build.toString());
		}
		return 0;
	}		
	
	/**
	 * update existing record
	 * @param buildParameter Build object
	 * @return int 1 for success 0 for failure
	 */
	public int updateBuild(Build buildParameter) {
		DebugOptions.debugOutput("\nMethod: updateBuild");
		// check complete object has been submitted
		if(Build.completeBuildObject(buildParameter)) {
			// check that the build exists in DB before updating
			String id = String.valueOf(buildParameter.getBuild_id());
			build = retrieveBuildByBuildId(id);
			if(build !=null) {
				return HibernateSaveOrUpdate.saveOrUpdateObject(buildParameter);
			} else {
				System.out.println("Requires build object with valid id");
			}
		} else {
			System.out.println("Build object incomplete:\n" + buildParameter.toString());
		}
		return 0;
	}
	
	/**
	 * update Product stock records for each Build_Line component when build is completed
	 * @param buildParameter Build object
	 * @return int 1 for success 0 for failure
	 */
	public int updateBuildStock(Build buildParameter) {
		DebugOptions.debugOutput("\nMethod: updateBuildStock");
		ProductDAO productDAO = ProductDAO.getProductDAO();
		Product product;
		int result = 0;
		try {
			for (int i = 0; i < buildParameter.getBuildLines().size(); i++ ) {
				product = productDAO.retrieveProductById(String.valueOf(buildParameter.getBuildLines().get(i).getProduct_id()));
				product.setProduct_stock(product.getProduct_stock() - 1);
				System.out.println(product.getProduct_type());
				productDAO.updateProduct(product);
			}
			result = 1;
		}
		catch (Exception exception){
			System.out.println(exception);
		}
		return result;
	}
	
	/**
	 * delete existing record
	 * @param id Build ID
	 * @return int 1 for success 0 for failure
	 */
	public int deleteBuild(String id) {
		DebugOptions.debugOutput("\nMethod: deleteBuild");
		// check that the build exists in DB before deleting
		build = retrieveBuildByBuildId(id);
		if(build != null) {			
			return HibernateDelete.deleteObject(build);
		} else {
			System.out.println("Requires build object with valid id");
		}
		return 0;
	}
	
	/**
	 * utility function to delete builds by status
	 * needs administrator privileges to run
	 * @param status build status, eg "o" for open
	 */
	public void deleteBuildsByStatus(String status) {
		DebugOptions.debugOutput("\nMethod: deleteBuildsByStatus");
		buildList = retrieveBuildsByStatus(status);
		for (Build b: buildList) {
			deleteBuild(String.valueOf(b.getBuild_id()));
		}
	}
	
}
