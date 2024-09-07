package cinereviews;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877
 *
 */
public interface Reviews {

	/**
	 * return the classification of a show
	 * 
	 * @return return the classification of a show
	 */
	String getClassification();

	/**
	 * return the user type of the person that made the review
	 * 
	 * @return return the user type of the person that made the review
	 */
	String getUserType();

	/**
	 * return the name of the person that made the review
	 * 
	 * @return return the name of the person that made the review
	 */
	String getUsername();

	/**
	 * return the comment of the person that made the review
	 * 
	 * @return return the comment of the person that made the review
	 */
	String getComment();

	/**
	 * return the title of the show that was reviewed
	 * 
	 * @return return the title of the show that was reviewed
	 */
	String getShowTitle();

	/**
	 * return the value of points of the classification multiplied by 1 or 5
	 * depending on who votes
	 * 
	 * @return return the value of points of the classification multiplied by 1 or 5
	 *         depending on who votes
	 */
	float getPoints();

	/**
	 * return the user who voted
	 * 
	 * @return return the user who voted
	 */
	User getUser();

	/**
	 * return the value of points of the classification without any multiplication
	 * 
	 * @return return the value of points of the classification without any
	 *         multiplication
	 */
	int getPointsWithoutMultiplier();

}
