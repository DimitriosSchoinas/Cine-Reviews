package cinereviews;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import Exceptions.*;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877
 *
 */
public interface CineReviews {

	/**
	 * adds a critic account
	 * 
	 * @param username username of the critic
	 * @throws AlreadyExistentIdentifierException if the account already exists
	 */
	void addCritic(String username) throws AlreadyExistentIdentifierException;

	/**
	 * adds an audience account
	 * 
	 * @param username username of the audience account
	 * @throws AlreadyExistentIdentifierException if the account already exists
	 */
	void addAudience(String username) throws AlreadyExistentIdentifierException;

	/**
	 * adds an admin account
	 * 
	 * @param username username of the admin account
	 * @param password password of the admin account
	 * @throws AlreadyExistentIdentifierException if the account already exists
	 */
	void addAdmin(String username, String password) throws AlreadyExistentIdentifierException;

	/**
	 * Lists all registered users.
	 * 
	 * @return return the list of all registered users.
	 * @throws NoUsersRegisteredException if there are not any users registered
	 */
	Iterator<User> listUsers() throws NoUsersRegisteredException;

	/**
	 * adds an artist
	 * 
	 * @param castMemberName   name of the artist
	 * @param artistBirthDate  birth date of the artist
	 * @param artistBirthPlace birth place of the artist
	 * @throws BioAlreadyExistsException if the artist already had a bio
	 */
	void addArtist(String castMemberName, String artistBirthDate, String artistBirthPlace)
			throws BioAlreadyExistsException;

	/**
	 * uploads a new movie
	 * 
	 * @param username              admin username
	 * @param password              admin password
	 * @param movieTitle            movie title
	 * @param movieDirectorName     movie director name
	 * @param movieDuration         movie duration
	 * @param movieAgeCertification movie age certification
	 * @param movieRealeseDate      movie realese date
	 * @param movieGenres           movie genres
	 * @param castMembers           movie cast members
	 * @throws NotAnAdminException         if the user is not an admin
	 * @throws WrongPasswordException      if the admin´s password is wrong
	 * @throws TitleAlreadyExistsException if the title of the movie already exists
	 */
	void uploadMovie(String username, String password, String movieTitle, String movieDirectorName, int movieDuration,
			String movieAgeCertification, int movieRealeseDate, List<String> movieGenres, List<String> castMembers)
			throws NotAnAdminException, WrongPasswordException, TitleAlreadyExistsException;

	/**
	 * return the number of artists that participated in a movie for the first time
	 * 
	 * @return return the number of artists that participated in a movie for the
	 *         first time
	 * @throws NotAnAdminException         if the user is not an admin
	 * @throws WrongPasswordException      if the admin´s password is wrong
	 * @throws TitleAlreadyExistsException if the title of the movie already exists
	 */
	int numberOfNewArtists() throws NotAnAdminException, WrongPasswordException, TitleAlreadyExistsException;

	/**
	 * uploads a new series
	 * 
	 * @param username               username of admin
	 * @param password               password of the admin
	 * @param seriesTitle            title of the series
	 * @param seriesCreatorName      name of the creator of the series
	 * @param numberOfSeasons        number of seasons of the series
	 * @param seriesAgeCertification series age certification
	 * @param seriesRealeseDate      series realese date
	 * @param seriesGenres           series genres
	 * @param castMembers            series cast members
	 * @throws NotAnAdminException         if the user is not an admin
	 * @throws WrongPasswordException      if the admin´s password is wrong
	 * @throws TitleAlreadyExistsException if the title of the movie already exists
	 */
	void uploadSeries(String username, String password, String seriesTitle, String seriesCreatorName,
			int numberOfSeasons, String seriesAgeCertification, int seriesRealeseDate, List<String> seriesGenres,
			List<String> castMembers) throws NotAnAdminException, WrongPasswordException, TitleAlreadyExistsException;;

	/**
	 * return the list of shows
	 * 
	 * @return return the list of shows
	 * @throws NoUploadedShowsException if there are no uploaded shows
	 */
	Iterator<Show> listShows() throws NoUploadedShowsException;

	/**
	 * adds bio information about an artist.
	 * 
	 * @param artistName       artist name
	 * @param artistBirthDate  artist birth date
	 * @param artistBirthPlace artist birth place
	 * @throws BioAlreadyExistsException if the bio already exists
	 */
	void addBioArtistInfo(String artistName, String artistBirthDate, String artistBirthPlace)
			throws BioAlreadyExistsException;

	/**
	 * return true if the artist already exists
	 * 
	 * @param artistName name of the artist
	 * @return return true if the artist already exists
	 */
	boolean existsArtist(String artistName);

	/**
	 * return the birth date info of an artist
	 * 
	 * @param artistName artist name
	 * @return return the birth date info of an artist
	 */
	String getBirthDateInfo(String artistName);

	/**
	 * return the birth place info of an artist
	 * 
	 * @param artistName artist name
	 * @return return the birth place info of an artist
	 */
	String getBirthPlaceInfo(String artistName);

	/**
	 * return true if the artist has participated in a show
	 * 
	 * @param artistName artist name
	 * @return return true if the artist has participated in a show
	 * @throws NotExistentNameException if the name of the artist doesn t exist
	 */
	boolean hasParticipatedInShow(String artistName) throws NotExistentNameException;

	/**
	 * lists all the shows that the artist has participated in
	 * 
	 * @param artistName artist name
	 * @return return a list of all the shows that the artist has participated in
	 */
	Iterator<Show> listShowsParticipated(String artistName);

	/**
	 * adds a review
	 * 
	 * @param username       username of the critic
	 * @param title          title of the movie/series
	 * @param comment        comment of the movie/series
	 * @param classification classification of the movie/series
	 * @throws NotExistentUserException            if the user doesnt exists
	 * @throws UsernameAdminAssociationException   if the user is an admin
	 * @throws TitleNotAssociatedWithShowException if the title of the show doesnt
	 *                                             exist
	 * @throws AlreadyReviewedShowException        if the show had already been
	 *                                             reviewed
	 */
	void addReview(String username, String title, String comment, String classification)
			throws NotExistentUserException, UsernameAdminAssociationException, TitleNotAssociatedWithShowException,
			AlreadyReviewedShowException;

	/**
	 * return true if the show has any reviews
	 * 
	 * @param showTitle show title
	 * @return return true if the show has any reviews
	 * @throws TitleNotAssociatedWithShowException if the title of the show doesnt
	 *                                             exist
	 */
	boolean hasReviews(String showTitle) throws TitleNotAssociatedWithShowException;

	/**
	 * return the average review score
	 * 
	 * @param showTitle show title
	 * @return return the average review score
	 * @throws TitleNotAssociatedWithShowException if the title of the show doesnt
	 *                                             exist
	 */
	float getAverageReviewScore(String showTitle) throws TitleNotAssociatedWithShowException;

	/**
	 * lists all the reviews of a show
	 * 
	 * @param title title of the show
	 * @return return a list of all the reviews of a show
	 * @throws TitleNotAssociatedWithShowException if the title of the show doesnt
	 *                                             exist
	 */
	Iterator<Reviews> listShowReviews(String title) throws TitleNotAssociatedWithShowException;

	/**
	 * lists all the avoiders
	 * 
	 * @return return a list of all the avoiders
	 * @throws SmallWorldException if all artists have common projects
	 * @throws NoArtistsException  if there are no artists
	 */
	Iterator<SortedSet<Artist>> listAvoiders() throws SmallWorldException, NoArtistsException;

	/**
	 * return the number of avoiders
	 * 
	 * @return return the number of avoiders
	 * @throws NoArtistsException  if there are no artists
	 * @throws SmallWorldException if all artists have common projects
	 */
	int getNumberOfAvoiders(Iterator<SortedSet<Artist>> sortedSet) throws NoArtistsException, SmallWorldException;

	/**
	 * lists the artists that have more projects together
	 * 
	 * @return return a list of the artists that have more projects together
	 * @throws NoArtistsException        if there are no artists
	 * @throws NoCollaborationsException if are no shows
	 */
	Iterator<String> listFriends() throws NoArtistsException, NoCollaborationsException;

	/**
	 * return the max number of common projects
	 * 
	 * @return return the max number of common projects
	 * @throws NoArtistsException        if there are no artists
	 * @throws NoCollaborationsException if are no shows
	 */
	int getMaxNumberOfCommonProjects() throws NoArtistsException, NoCollaborationsException;

	/**
	 * return true if the artist has already a bio
	 * 
	 * @param artistName artist name
	 * @return return true if the artist has already a bio
	 * @throws NotExistentNameException if the name of the artist doesnt exists
	 */
	boolean hasBio(String artistName) throws NotExistentNameException;

	/**
	 * return the number of reviews of a show
	 * 
	 * @param title show title
	 * @return return the number of reviews of a show
	 * @throws TitleNotAssociatedWithShowException if the title of the show doesnt
	 *                                             exist
	 */
	int getNumberOfReviews(String title) throws TitleNotAssociatedWithShowException;

	/**
	 * lists all the shows of the specified genres
	 * 
	 * @param genres list of genres
	 * @return return a list of all the shows of the specified genres
	 * @throws NotFoundWithinCriteriaException if there are no shows with the
	 *                                         specified criteria
	 */
	Iterator<Show> listGenres(List<String> genres) throws NotFoundWithinCriteriaException;

	/**
	 * lists shows released on a given year
	 * 
	 * @param releaseYear year of release of a show
	 * @return return a list of shows released on a given year
	 * @throws NotFoundWithinCriteriaException if there are no shows with the
	 *                                         specified criteria
	 */
	Iterator<Show> listReleased(int releaseYear) throws NotFoundWithinCriteriaException;

}
