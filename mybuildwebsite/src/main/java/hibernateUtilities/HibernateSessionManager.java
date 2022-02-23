package hibernateUtilities;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import debugOutput.DebugOptions;

/**
 * HibernateSessionManager is a general purpose utility for managing Hibernate DB operations.
 * It manages sessions for the CRUD operation classes, eg HibernateQuery
 * 
 * @author Peter Monks
 * @version 1.1
 */

public class HibernateSessionManager {

	// variables
	private static SessionFactory sessionFactory;
	
	/**
	 * create and open the appropriate session for the object type specified 
	 * @param session DB session
	 * @param objectType required object type as a String
	 * @return the opened DB session
	 */
	public static Session openSession(Session session, String objectType) {
		sessionFactory = HibernateSessionFactoryMaker.getSessionFactory(objectType).makeSessionFactory();
		session = sessionFactory.openSession();
		//transaction = null;
		DebugOptions.debugOutput("Session opened");
		return session;
	}
	
	/**
	 * handle any exceptions encountered during the DB session
	 * @param exception exception encountered
	 * @param transaction the transaction which caused the exception
	 */
	public static void handleException(HibernateException exception, Transaction transaction) {
		if (transaction != null) {
			transaction.rollback();
		}
		DebugOptions.debugOutput("Exception!");
		System.out.println(exception.getMessage());
		exception.printStackTrace();
	}
	
	/**
	 * close the DB session on completion of operations
	 * @param session session to be closed
	 */
	public static void closeSession(Session session) {
		session.close();
		sessionFactory.close(); // added to prevent multiple MySQL threads being open at once
		DebugOptions.debugOutput("Session closed");
	}
	
}
