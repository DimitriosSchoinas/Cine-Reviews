package cinereviews;

import java.util.Iterator;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877
 *
 */
public interface Artist {

	/**
	 * return the name of the artist
	 * 
	 * @return return the name of the artist
	 */
	String getName();

	/**
	 * return true if has bio
	 * 
	 * @return return true if has bio
	 */
	boolean hasBio();

	/**
	 * updates the bio of the artist
	 * 
	 * @param dateOfBirth  date of birth of the artist
	 * @param placeOfBirth place of birth of the artist
	 */
	void updateBio(String dateOfBirth, String placeOfBirth);

	/**
	 * return the date of birth of the artist
	 * 
	 * @return return the date of birth of the artist
	 */
	String getBirthDate();

	/**
	 * return the place of birth of the artist
	 * 
	 * @return return the place of birth of the artist
	 */
	String getBirthPlace();

	/**
	 * adds the show that the artist participated
	 * 
	 * @param s show to add
	 */
	void addShowParticipated(Show s);

	/**
	 * lists the shows that the artist participated
	 * 
	 * @return return the shows that the artist participated
	 */
	Iterator<Show> listShowsParticipated();

	/**
	 * return true if has participated in any show
	 * 
	 * @return return true if has participated in any show
	 */
	boolean hasParticipatedInShow();

	/**
	 * return the number of shows that the artist has participated
	 * 
	 * @return return the number of shows that the artist has participated
	 */
	int getNumberOfParticipatedShows();

	int compareTo(Artist next);

}
