package hibernateUtilities;

import org.hibernate.SessionFactory;

/**
 * HibernateSessionFactory is the abstract class for the Hibernate session
 * factory design pattern structure, for DB operations
 * 
 * @author Peter Monks
 * @version 1.1
 */

public abstract class HibernateSessionFactory {
	
	/**
	 * create the required Session Factory
	 * @return the created Session Factory
	 */
	public abstract SessionFactory makeSessionFactory();
	
}
