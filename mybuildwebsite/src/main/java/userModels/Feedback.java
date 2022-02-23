package userModels;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback")
public class Feedback {
	
	// variables
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="feedback_id") private int feedback_id;
	
	@Column(name="feedback_date") private Date feedback_date;
	@Column(name="feedback_name") private String feedback_name;
	@Column(name="feedback_email") private String feedback_email;
	@Column(name="feedback_text") private String feedback_text;
		
	/** default no argument constructor */
	public Feedback() {}
	
	/**
	 * hibernate constructor (without auto-generated id)
	 * @param date feedback date
	 * @param name feedback sender name
	 * @param email feedback sender email
	 * @param text feedback text
	 */
	public Feedback(Date date, String name, String email, String text) {
		this.feedback_date = date;
		this.feedback_name = name;
		this.feedback_email = email;
		this.feedback_text = text;
	}
	
	/**
	 * full constructor
	 * @param id feedback ID
	 * @param date feedback date
	 * @param name feedback sender name
	 * @param email feedback sender email
	 * @param text feedback text
	 */
	public Feedback(int id, Date date, String name, String email, String text) {
		this.feedback_id = id;
		this.feedback_date = date;
		this.feedback_name = name;
		this.feedback_email = email;
		this.feedback_text = text;
	}

	/**
	 * @return the feedback_id
	 */
	public int getFeedback_id() {
		return feedback_id;
	}

	/**
	 * @param feedback_id the feedback_id to set
	 */
	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}
	
	/**
	 * @return the feedback_date
	 */
	public Date getFeedback_date() {
		return feedback_date;
	}

	/**
	 * @param feedback_date the feedback_date to set
	 */
	public void setFeedback_date(Date feedback_date) {
		this.feedback_date = feedback_date;
	}

	/**
	 * @return the feedback_name
	 */
	public String getFeedback_name() {
		return feedback_name;
	}

	/**
	 * @param feedback_name the feedback_name to set
	 */
	public void setFeedback_name(String feedback_name) {
		this.feedback_name = feedback_name;
	}

	/**
	 * @return the feedback_email
	 */
	public String getFeedback_email() {
		return feedback_email;
	}

	/**
	 * @param feedback_email the feedback_email to set
	 */
	public void setFeedback_email(String feedback_email) {
		this.feedback_email = feedback_email;
	}

	/**
	 * @return the feedback_text
	 */
	public String getFeedback_text() {
		return feedback_text;
	}

	/**
	 * @param feedback_text the feedback_text to set
	 */
	public void setFeedback_text(String feedback_text) {
		this.feedback_text = feedback_text;
	}
	
	/** check if all feedback attributes have been completed, eg when updating feedback records
	 * @param feedback feedback object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeFeedbackObject(Feedback feedback) {
		if(feedback.getFeedback_id() != 0
			&& feedback.getFeedback_date() != null
			// && feedback.getFeedback_name() != null		// feedback can be anonymous
			// && feedback.getFeedback_email() != null		// feedback can be anonymous
			&& feedback.getFeedback_text() != null) {
			return true;
		}
		else return false;	
	}
  
	/** check if all feedback attributes except id have been completed, eg when creating new feedback records
	 * @param feedback feedback object
	 * @return true if all attributes are populated, false if one or more attributes are not populated
	 */
	public static boolean completeFeedbackRecord(Feedback feedback) {
		if(feedback.getFeedback_date() != null
			// && feedback.getFeedback_name() != null		// feedback can be anonymous
			// && feedback.getFeedback_email() != null		// feedback can be anonymous
			&& feedback.getFeedback_text() != null) {	
			return true;
		}
		else return false;	
	}
	
	/** feedback object to string function
	 * @return feedback object information as a string
	 */
	@Override
	public String toString() {
		return "Feedback ["
			+ "feedback_id=" + feedback_id + ", "
			+ "feedback_date=" + feedback_date + ", "
			+ "feedback_name=" + feedback_name + ", "
			+ "feedback_email=" + feedback_email + ", "
			+ "feedback_text=" + feedback_text
			+ "]";
	}
	
}	