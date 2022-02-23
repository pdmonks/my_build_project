package hibernateUtilities;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import debugOutput.DebugOptions;

/**
 * HibernateDelete is a general purpose utility for executing Hibernate DB deletions.
 * It utilises the 'HibernateSessionManager' class to perform DB operations.
 * 
 * @author Peter Monks
 * @version 1.1
 */

public class HibernateDelete {

	// variables
	private static Session session;
	private static Transaction transaction;
	private static String objectType;
	
	/**
	 * delete the submitted object's record from the DB
	 * @param object object relating the record to be deleted
	 * @return int 1 if successful, 0 if unsuccessful
	 */
	public static int deleteObject(Object object) {
		
		// determine the object type and open the DB session
		int success = 0;
		DebugOptions.debugOutput("\nMethod: deleteObject");
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
			
			session.delete(object);
			DebugOptions.debugOutput(object.toString() + "\nRecord deleted");
			success = 1;

			// commit and close the DB operation
			transaction.commit();
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
