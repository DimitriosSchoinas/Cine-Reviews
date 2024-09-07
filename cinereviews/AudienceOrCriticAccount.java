package cinereviews;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877
 *
 */
public interface AudienceOrCriticAccount extends User {

	/**
	 * increments the review count by one
	 */
	void incrementReviewCount();
}
