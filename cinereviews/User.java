package cinereviews;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877
 *
 */
public interface User extends Comparable<User> {

	/**
	 * return the name of the user
	 * 
	 * @return return the name of the user
	 */
	String getName();

	/**
	 * return the number of shows that the user has reviewed
	 * 
	 * @return return the number of shows that the user has reviewed
	 */
	int getAmountOfReviewsOrShows();

	/**
	 * compares 2 user names
	 */
	int compareTo(User other);

	/**
	 * return the type of user
	 * 
	 * @return
	 */
	String getType();
}
