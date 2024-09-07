package cinereviews;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877
 *
 */
public interface Show extends Comparable<Show> {

	/**
	 * return the age certification value
	 * 
	 * @return return the age certification value
	 */
	String getAgeCertification();

	/**
	 * return the duration of a movie or the number of seasons depending on the type
	 * of show
	 * 
	 * @return return the duration of a movie or the number of season depending on
	 *         the type of show
	 */
	int getDurationOrNumOfSeasons();

	/**
	 * return the main genre of the show
	 * 
	 * @return return the main genre of the show
	 */
	String getMainGenre();

	/**
	 * return the name of the director of the movie or creator of the series
	 * depending on the type of show
	 * 
	 * @return return the name of the director of the movie or creator of the series
	 *         depending on the type of show
	 */
	String getDirectorOrCreator();

	/**
	 * return the title of the show
	 * 
	 * @return return the title of the show
	 */
	String getTitle();

	/**
	 * return the release year of the show
	 * 
	 * @return return the release year of the show
	 */
	int getReleaseYear();

	/**
	 * lists all the cast of a show
	 * 
	 * @return return a list of all the cast of a show
	 */
	Iterator<Artist> listCast();

	/**
	 * return true if the user is the director of the movie or the creator of the
	 * series depending on the type of show
	 * 
	 * @param artistName name of the artist
	 * @return return true if the user is the director of the movie or the creator
	 *         of the series depending on the type of show
	 */
	boolean isDirectorOrCreator(String artistName);

	/**
	 * return the type of show
	 * 
	 * @return return the type of show
	 */
	String getType();

	/**
	 * return the average reviews score
	 * 
	 * @return return the average reviews score
	 */
	float getAverageReviews();

	/**
	 * compares 2 shows by title
	 */
	int compareTo(Show other);

	/**
	 * return true if the show has already been reviewed by the specified user
	 * 
	 * @param username username of the reviewer
	 * @return return true if the show has already been reviewed by the specified
	 *         user
	 */
	boolean hasAlreadyBeenReviewed(String username);

	/**
	 * adds a review to a show
	 * 
	 * @param title          title of the show
	 * @param user           user that review the show
	 * @param comment        show comment
	 * @param classification show classification
	 */
	void addReview(String title, User user, String comment, String classification);

	/**
	 * return true if the movie has reviews
	 * 
	 * @return return true if the movie has reviews
	 */
	boolean hasReviews();

	/**
	 * return the number of reviews that the show has
	 * 
	 * @return return the number of reviews that the show has
	 */
	int getNumberOfReviews();

	/**
	 * return true if the show is within the criteria list
	 * 
	 * @param genresToCheck list of genres to check
	 * @return return true if the show is within the criteria list
	 */
	boolean isShowWithinGenreCriteria(List<String> genresToCheck);
}
