package hibernateUtilities;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import debugOutput.DebugOptions;

/**
 * HibernateSaveOrUpdate is a general purpose utility for executing Hibernate DB updates.
 * It utilises the 'HibernateSessionManager' class to perform DB operations.
 * 
 * @author Peter Monks
 * @version 1.1
 */

public class HibernateSaveOrUpdate {

	// variables
	private static Session session;
	private static Transaction transaction;
	private static String objectType;
	
	/**
	 * create or update the submitted object's record in the DB
	 * @param object object relating the record to be created or updated
	 * @return int 1 if successful, 0 if unsuccessful
	 */
	public static int saveOrUpdateObject(Object object) {
		
		// determine the object type and open the DB session
		DebugOptions.debugOutput("\nMethod: saveOrUpdateObject");
		int success = 0;
		// amended to capture full object path for compatibility with all object types
		// System.out.println(object.getClass().toString());
		// objectType = object.getClass().toString().substring(12);
		objectType = object.getClass().toString();
		DebugOptions.debugOutput("Object type: " + objectType);
		session = HibernateSessionManager.openSession(session, objectType);

		try {
			// execute the DB operation
			transaction = null;
			transaction = session.beginTransaction();
			session.saveOrUpdate(object);
			
			// commit and close the DB operation
			transaction.commit();
			DebugOptions.debugOutput("Record updated to:\n" + object.toString());
			success = 1;
		}
		catch (HibernateException exception) {
			HibernateSessionManager.handleException(exception, transaction);
		}
		finally {
			HibernateSessionManager.closeSession(session);
		}		
		return success;
	}
	
}
