package cinereviews;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877
 *
 */
public interface AdminAccount extends User {

	/**
	 * return the password of the admin
	 * 
	 * @return return the password of the admin
	 */
	String getPassword();

	/**
	 * adds a show by the admin
	 * 
	 * @param s show to add
	 */
	void addShow(Show s);
}
