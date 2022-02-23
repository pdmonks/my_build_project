package userModels;

import java.util.ArrayList;

import debugOutput.DebugOptions;
import hibernateUtilities.HibernateDelete;
import hibernateUtilities.HibernateQuery;
import hibernateUtilities.HibernateSaveOrUpdate;

public class FeedbackDAO {

	// variables
	private static FeedbackDAO feedbackDAO;
	private String query;
	private Feedback feedback;
	private ArrayList<Feedback> feedbackList;
	private String objectType = "class userModels.Feedback";
	
	/** private constructor */
	private FeedbackDAO() {}
	
	/** singleton object manager
	 * @return FeedbackDAO object (new object only if not already instantiated)
	 */
	public static synchronized FeedbackDAO getFeedbackDAO() {
		if(feedbackDAO == null) {
			feedbackDAO = new FeedbackDAO();
		}
		return feedbackDAO;
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
	 * @param clone CustomerDAO clone object
	 */
	public FeedbackDAO(FeedbackDAO clone) {
		System.out.println("Cloning of this singleton object not allowed");
	}
	
	/**
	 * retrieve existing record by id
	 * @param id feedback ID
	 * @return feedback object
	 */
	public Feedback retrieveFeedbackById(String id) {
		DebugOptions.debugOutput("\nMethod: retrieveFeedbackById");
		query = ("FROM Feedback where feedback_id = '" + id + "'");
		feedbackList = queryFeedback(query);
		if (feedbackList.size() > 0) {
			return feedbackList.get(0); // return customer 0 - must be unique as retrieved by id
		}
		return null;
	}
	
	/**
	 * retrieve all records
	 * @return ArrayList of feedback objects
	 */
	public ArrayList<Feedback> retrieveAllFeedback() {
		DebugOptions.debugOutput("\nMethod: retrieveAllFeedback");
		query = ("FROM Feedback");
		feedbackList = queryFeedback(query);
		if (feedbackList.size() > 0) {
			return feedbackList;
		}
		return null;
	}
	
	/** retrieve requested records
	 * @param query received from calling function
	 * @return ArrayList of feedback objects, one for each record retrieved
	 */
	private ArrayList<Feedback> queryFeedback(String query) {
		DebugOptions.debugOutput("\nMethod: queryFeedback"); 
		
		feedbackList = new ArrayList<Feedback>();
		
		// retrieve feedback as a list of objects (generic object method called)
		ArrayList<Object> objects = HibernateQuery.queryObject(query, objectType);
		
		// if valid object list returned, move feedback into feedback list
		if(!objects.get(0).toString().substring(0, 9).equals("java.lang")) {
			Feedback[] feedbacks = objects.toArray(new Feedback[objects.size()]);
			
			for(Feedback f: feedbacks) {
				feedbackList.add(f);
			}
		}
		return feedbackList;	
	}
	
	/**
	 * create new record
	 * @param feedback feedback object
	 * @return int 1 for success 0 for failure
	 */
	public int createFeedback(Feedback feedback) {
		DebugOptions.debugOutput("\nMethod: createFeedback");
		// check complete object has been submitted
		if(Feedback.completeFeedbackRecord(feedback)) {
			return HibernateSaveOrUpdate.saveOrUpdateObject(feedback);
		} else {
			System.out.println("Feedback record information incomplete:\n" + feedback.toString());
		}
		return 0;
	}
	
	/**
	 * update existing record
	 * @param feedbackParameter feedback object
	 * @return int 1 for success 0 for failure
	 */
	public int updateFeedback(Feedback feedbackParameter) {
		DebugOptions.debugOutput("\nMethod: updateFeedback");
		// check complete object has been submitted
		if(Feedback.completeFeedbackObject(feedbackParameter)) {
			// check that the feedback exists in DB before updating
			String id = String.valueOf(feedbackParameter.getFeedback_id());
			feedback = retrieveFeedbackById(id);
			if(feedback !=null) {
				return HibernateSaveOrUpdate.saveOrUpdateObject(feedbackParameter);
			} else {
				System.out.println("Requires feedback object with valid id");
			}
		} else {
			System.out.println("Feedback object incomplete:\n" + feedbackParameter.toString());
		}
		return 0;
	}
	
	/**
	 * delete existing record
	 * @param id feedback ID
	 * @return int 1 for success 0 for failure
	 */
	public int deleteFeedback(String id) {
		DebugOptions.debugOutput("\nMethod: deleteFeedback");
		// check that the customer exists in DB before deleting
		feedback = retrieveFeedbackById(id);
		if(feedback != null) {			
			return HibernateDelete.deleteObject(feedback);
		} else {
			System.out.println("Requires feedback object with valid ID");
		}
		return 0;
	}
	
}
