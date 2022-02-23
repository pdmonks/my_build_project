package productModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

/**
 * ProductDAO allows the data and methods of the 'Product' class to be
 * accessed indirectly, protecting the integrity of the original class.
 * Applications can utilise this class to perform CRUD operations on product
 * data, database functions are decoupled meaning they can utilise multiple
 * database access techniques; at present configured to utilise Hibernate,
 * (hibernateUtilities package) but can easily be reconfigured.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class ProductDAO {

	// variables
	private static ProductDAO productDAO;
	private String query;
	private Product product;
	private ArrayList<Product> productList;
	private String objectType = "class productModels.Product";
	
	/** private constructor */
	private ProductDAO() {}
	
	/** singleton object manager
	 * @return ProductDAO object (new object only if not already instantiated)
	 */
	public static synchronized ProductDAO getProductDAO() {
		if(productDAO == null) {
			productDAO = new ProductDAO();
		}
		return productDAO;
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
	 * @param clone ProductDAO clone object
	 */
	public ProductDAO(ProductDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by product id value
	 * @param id product ID
	 * @return product object
	 */
	public Product retrieveProductById(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveProductById");
		query = ("FROM Product WHERE product_id = '" + id + "'");
		productList = queryProduct(query);
		if (productList.size() > 0) {
			return productList.get(0); // return build 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return array list of products, one for each record retrieved
	 */
	private ArrayList<Product> queryProduct(String query) {
		DebugOptions.debugOutput("\nMethod: queryProduct"); 
		
		productList = new ArrayList<Product>();
		
		// retrieve products as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move products into product list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			Product[] products = objects.toArray(new Product[objects.size()]);
			
			for(Product p: products) {
				productList.add(p);
			}
		}
		return productList;
	}
	
	/**
	 * update existing record
	 * @param productParameter product object
	 * @return int 1 for success 0 for failure
	 */
	public int updateProduct(Product productParameter) {
		DebugOptions.debugOutput("\nMethod: updateProduct");
		// check complete object has been submitted
		if(Product.completeProductObject(productParameter)) {
			// check that the product exists in DB before updating
			String id = String.valueOf(productParameter.getProduct_id());
			product = retrieveProductById(id);
			if(product !=null) {
				// customerParameter.setCustomer_password(customer.getCustomer_password());
				return HibernateSaveOrUpdate.saveOrUpdateObject(productParameter);
			} else {
				System.out.println("Requires product object with valid id");
			}
		} else {
			System.out.println("Product object incomplete:\n" + productParameter.toString());
		}
		return 0;
	}
	
}
