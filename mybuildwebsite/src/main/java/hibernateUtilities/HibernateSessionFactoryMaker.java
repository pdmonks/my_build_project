package hibernateUtilities;

import debugOutput.DebugOptions;
import hibernateModels.HibernateSessionFactoryBuild;
import hibernateModels.HibernateSessionFactoryCPU;
import hibernateModels.HibernateSessionFactoryCooler;
import hibernateModels.HibernateSessionFactoryCustomer;
import hibernateModels.HibernateSessionFactoryFeedback;
import hibernateModels.HibernateSessionFactoryGPU;
import hibernateModels.HibernateSessionFactoryMotherboard;
import hibernateModels.HibernateSessionFactoryPC_Case;
import hibernateModels.HibernateSessionFactoryPower_Supply;
import hibernateModels.HibernateSessionFactoryProduct;
import hibernateModels.HibernateSessionFactoryRAM;
import hibernateModels.HibernateSessionFactoryStorage;

/**
 * HibernateSessionFactoryMaker is the class which constructs the Factory for the required
 * class for Hibernate DB sessions, as part of the factory design pattern structure
 * 
 * @author Peter Monks
 * @version 1.1
 */

public class HibernateSessionFactoryMaker {

	/**
	 * create the required Session Factory
	 * @return the created Session Factory
	 */
	public static HibernateSessionFactory getSessionFactory(String objectType) {
		
		// customer models
		if (objectType.equals("class userModels.Customer")) {
			return new HibernateSessionFactoryCustomer();
		}
		if (objectType.equals("class userModels.Feedback")) {
			return new HibernateSessionFactoryFeedback();
		}
		
		// build and build_line models
		if (objectType.equals("class buildModels.Build")) {
			return new HibernateSessionFactoryBuild();
		}
		
		// product models
		if (objectType.equals("class productModels.Product")) {
			return new HibernateSessionFactoryProduct();
		}
		if (objectType.equals("class productModels.Cooler")) {
			return new HibernateSessionFactoryCooler();
		}
		if (objectType.equals("class productModels.CPU")) {
			return new HibernateSessionFactoryCPU();
		}
		if (objectType.equals("class productModels.GPU")) {
			return new HibernateSessionFactoryGPU();
		}
		if (objectType.equals("class productModels.Motherboard")) {
			return new HibernateSessionFactoryMotherboard();
		}
		if (objectType.equals("class productModels.PC_Case")) {
			return new HibernateSessionFactoryPC_Case();
		}
		if (objectType.equals("class productModels.Power_Supply")) {
			return new HibernateSessionFactoryPower_Supply();
		}
		if (objectType.equals("class productModels.RAM")) {
			return new HibernateSessionFactoryRAM();
		}
		if (objectType.equals("class productModels.Storage")) {
			return new HibernateSessionFactoryStorage();
		}
		
		DebugOptions.debugOutput("Object type not in HibernateSessionFactoryMaker");
		return null;		
	}
	
}
