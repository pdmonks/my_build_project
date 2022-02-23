package hibernateModels;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import hibernateUtilities.HibernateSessionFactory;
import productModels.Product;

/**
 * HibernateSessionFactoryBuild is the implementation of the HibernateSessionFactory for the
 * Product class Hibernate DB operations.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class HibernateSessionFactoryProduct extends HibernateSessionFactory{

	// variables
	private SessionFactory sessionFactory;
	
	/**
	 * create the required Product Session Factory
	 * @return the created Product Session Factory
	 */
	@Override
	public SessionFactory makeSessionFactory() {
		try {
			sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(Product.class).buildSessionFactory();
		}
		catch (Throwable exception) {
			System.err.println("Failed to create sessionFactory object. " + exception);
			throw new ExceptionInInitializerError(exception);
		}
		return sessionFactory;
	}
}
