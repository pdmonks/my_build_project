package hibernateUtilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import debugOutput.DebugOptions;

/**
 * HibernateQuery is a general purpose utility for executing Hibernate DB retrievals.
 * It utilises the 'HibernateSessionManager' class to perform DB operations.
 * 
 * @author Peter Monks
 * @version 1.1
 */

public class HibernateQuery {

	// variables
	private static Session session;
	private static Transaction transaction;
	private static ArrayList<Object> objectList;
	
	/**
	 * retrieve records based on query and return a list of objects
	 * @param query retrieve query as a String
	 * @param objectType required object type as a String 
	 * @return Array list of objects, one for each record returned from DB
	 */
	public static ArrayList<Object> queryObject(String query, String objectType) {
		
		// instantiate list of return objects and open the DB session
		objectList = new ArrayList<Object>();
		session = HibernateSessionManager.openSession(session, objectType);
	
		try {
			// execute the DB operation
			transaction = null;
			transaction = session.beginTransaction();
			List allObjects = session.createQuery(query).list();
			int records = 0;
			
			// add records retrieved to the list of objects
			for (Iterator iterator = allObjects.iterator(); iterator.hasNext();){
				Object object = (Object) iterator.next();
				DebugOptions.debugOutput(object.toString());
				objectList.add(object);
				records += 1;
			}
			DebugOptions.debugOutput("Records retrieved: " + records);
			// if no records retrieved, create null object to be handled by application
			if(objectList.size() == 0) {
				objectList.add(new Object());
				DebugOptions.debugOutput("Null object returned"); 
			}
			// commit and close the DB operation
			transaction.commit();
		}
		catch (HibernateException exception) {
			HibernateSessionManager.handleException(exception, transaction);
		}
		finally {
			HibernateSessionManager.closeSession(session);
		}

		return objectList;

	}
	
}