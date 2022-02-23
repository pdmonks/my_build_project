package hibernateModels;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import hibernateUtilities.HibernateSessionFactory;
import userModels.Feedback;

/**
 * HibernateSessionFactoryBuild is the implementation of the HibernateSessionFactory for the
 * Feedback class Hibernate DB operations.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class HibernateSessionFactoryFeedback extends HibernateSessionFactory{

	// variables
	private SessionFactory sessionFactory;
	
	/**
	 * create the required Feedback Session Factory
	 * @return the created Feedback Session Factory
	 */
	@Override
	public SessionFactory makeSessionFactory() {
		try {
			sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(Feedback.class).buildSessionFactory();
		}
		catch (Throwable exception) {
			System.err.println("Failed to create sessionFactory object. " + exception);
			throw new ExceptionInInitializerError(exception);
		}
		return sessionFactory;
	}
}
