import java.util.*;
import Exceptions.*;
import cinereviews.*;

/**
 * 
 * @author Dimitrios Schoinas 65313 e João Rivera 62877
 *
 */
public class Main {

	/**
	 * Help command messages
	 */
	private static final String REGISTER_H = "register - registers a user in the system\n";
	private static final String USERS_H = "users - lists all registered users\n";
	private static final String MOVIE_H = "movie - uploads a new movie\n";
	private static final String SERIES_H = "series - uploads a new series\n";
	private static final String SHOWS_H = "shows - lists all shows\n";
	private static final String ARTIST_H = "artist - adds bio information about an artist\n";
	private static final String CREDITS_H = "credits - lists the bio and credits of an artist\n";
	private static final String REVIEW_H = "review - adds a review to a show\n";
	private static final String REVIEWS_H = "reviews - lists the reviews of a show\n";
	private static final String GENRE_H = "genre - lists shows of given genres\n";
	private static final String RELEASED_H = "released - lists shows released in a given year\n";
	private static final String AVOIDERS_H = "avoiders - lists artists that have no common projects\n";
	private static final String FRIENDS_H = "friends - lists artists that have more projects together\n";
	private static final String HELP_H = "help - shows the available commands\n";
	private static final String EXIT_H = "exit - terminates the execution of the program\n";

	/**
	 * Program feedback
	 */
	private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
	private static final String EXIT_MESSAGE = "Bye!\n";
	private static final String HELP_MESSAGE = REGISTER_H + USERS_H + MOVIE_H + SERIES_H + SHOWS_H + ARTIST_H
			+ CREDITS_H + REVIEW_H + REVIEWS_H + GENRE_H + RELEASED_H + AVOIDERS_H + FRIENDS_H + HELP_H + EXIT_H;
	private static final String REGISTERED_SUCCESS_CRITIC = "User %s was registered as critic.\n";
	private static final String REGISTERED_SUCCESS_AUDIENCE = "User %s was registered as audience.\n";
	private static final String REGISTERED_SUCCESS_ADMIN = "User %s was registered as admin.\n";
	private static final String UNKOWN_USER_TYPE = "Unknown user type!\n";
	private static final String LIST_USERS_SUCCESS_HEADER = "All registered users:\n";
	private static final String LIST_USERS_SUCCESS_BODY_CRITIC_AND_AUDIENCE_PERSON = "User %s has posted %d reviews\n";
	private static final String LIST_USERS_BODY_SUCCESS_ADMIN = "Admin %s has uploaded %d shows\n";
	private static final String MOVIE_SUCCESS_MESSAGE = "Movie %s (%d) was uploaded [%d new artists were created].\n";
	private static final String SERIES_SUCCESS_MESSAGE = "Series %s (%d) was uploaded [%d new artists were created].\n";
	private static final String SHOWS_SUCCESS_HEADER = "All shows:\n";
	private static final String SHOWS_SUCCESS_BODY_MOVIE_START = "%s; %s; %d; %s; %d; %s";
	private static final String SHOWS_SUCCESS_BODY_MOVIE_CAST = "; %s";
	private static final String SHOWS_SUCCESS_BODY_SERIES_START = "%s; %s; %d; %s; %d; %s";
	private static final String SHOWS_SUCCESS_BODY_SERIES_CAST = "; %s";
	private static final String ARTIST_SUCCESS_MESSAGE_ALREADY_EXISTED = "%s bio was updated.\n";
	private static final String ARTIST_SUCCESS_MESSAGE_DIDNT_ALREADY_EXIST = "%s bio was created.\n";
	private static final String CREDITS_SUCCESS_ARTIST_HAS_BIRTHDATE = "%s\n";
	private static final String CREDITS_SUCCESS_ARTIST_HAS_BIRTHPLACE = "%s\n";
	private static final String CREDITS_SUCCESS_BODY = "%s; %d; %s [%s]\n";
	private static final String REVIEW_SUCCESS_MESSAGE = "Review for %s was registered [%d reviews].\n";
	private static final String REVIEWS_NO_REVIEWS_ERROR = "Show %s has no reviews.\n";
	private static final String REVIEWS_SUCCESS_HEADER = "Reviews of %s [%.1f]:\n";
	private static final String REVIEWS_SUCCESS_BODY = "Review of %s (%s): %s [%s]\n";
	private static final String GENRE_SUCCESS_HEADER = "Search by genre:\n";
	private static final String GENRE_SUCCES_BODY_MOVIE = "Movie %s by %s released on %d [%.1f]\n";
	private static final String GENRE_SUCCES_BODY_SERIES = "Series %s by %s released on %d [%.1f]\n";
	private static final String RELEASED_SUCCESS_HEADER = "Shows released on %d:\n";
	private static final String RELEASED_SUCCESS_MOVIE = "Movie %s by %s released on %d [%.1f]\n";
	private static final String RELEASED_SUCCESS_SERIES = "Series %s by %s released on %d [%.1f]\n";
	private static final String AVOIDERS_SUCCESS_HEADER = "These %d artists never worked together:\n";
	private static final String AVOIDERS_SUCCESS_BODY = ", %s";
	private static final String AVOIDERS_SUCCESS_BEGINNING = "%s";
	private static final String AVOIDERS_SUCCESS_END = ", %s\n";
	private static final String FRIENDS_SUCCESS_HEADER = "These artists have worked on %d projects together:\n";

	private static final String ADMIN_ACCOUNT = "admin";
	private static final String DIRECTOR = "director";
	private static final String CREATOR = "creator";
	private static final String ACTOR = "actor";

	/**
	 * User Commands
	 */
	private enum Command {
		EXIT("exit"), HELP("help"), REGISTER_USER("register"), LIST_USERS("users"), UPLOAD_MOVIE("movie"),
		UPLOAD_SERIES("series"), LIST_SHOWS("shows"), ADD_BIO_ARTIST_INFO("artist"), LIST_ARTIST_CREDITS("credits"),
		ADD_REVIEW("review"), LIST_SHOW_REVIEWS("reviews"), LIST_BY_GENRE("genre"), LIST_BY_YEAR("released"),
		LIST_ARTISTS_NO_COMMON_JOB("avoiders"), LIST_ARTISTS_MORE_COMMON_JOB("friends"), UNKNOWN("");

		private final String commandInputName;

		private Command(String commandInputName) {
			this.commandInputName = commandInputName;
		}

		public String getCommandInputName() {
			return this.commandInputName;
		}
	}

	private enum RegisterUserType {
		CRITIC("critic"), AUDIENCE("audience"), ADMIN("admin"), UNKNOWN("");

		private final String commandInputName;

		private RegisterUserType(String commandInputName) {
			this.commandInputName = commandInputName;
		}

		public String getCommandInputName() {
			return this.commandInputName;
		}
	}

	/**
	 * Main program. Invokes the command interpreter
	 * 
	 * @param args - arguments for running the application. Not used in this
	 *             program.
	 */
	public static void main(String[] args) {
		Main.executeCommands();
	}

	/**
	 * command interpreter
	 */
	private static void executeCommands() {
		Scanner in = new Scanner(System.in);
		CineReviews cr = new CineReviewsClass();
		Command comm = readCommand(in);
		while (!comm.equals(Command.EXIT)) {
			switch (comm) {
			case HELP -> {
				help();
			}
			case REGISTER_USER -> {
				RegisterUserType userType = readRegisterUserTypeCommand(in);
				String username = in.next();
				switch (userType) {
				case CRITIC -> {
					addCritic(cr, username);
				}
				case AUDIENCE -> {
					addAudience(cr, username);
				}
				case ADMIN -> {
					addAdmin(cr, in, username);
				}
				default -> {
					System.out.printf(UNKOWN_USER_TYPE);
				}
				}
			}
			case LIST_USERS -> {
				listUsers(cr);
			}
			case UPLOAD_MOVIE -> {
				uploadMovie(cr, in);
			}
			case UPLOAD_SERIES -> {
				uploadSeries(cr, in);
			}
			case LIST_SHOWS -> {
				listShows(cr);
			}
			case ADD_BIO_ARTIST_INFO -> {
				addBioArtistInfo(cr, in);
			}
			case LIST_ARTIST_CREDITS -> {
				listArtistCredits(cr, in);
			}
			case ADD_REVIEW -> {
				addReview(cr, in);
			}
			case LIST_SHOW_REVIEWS -> {
				listShowReviews(cr, in);
			}
			case LIST_BY_GENRE -> {
				listByGenre(cr, in);
			}
			case LIST_BY_YEAR -> {
				listByYear(cr, in);
			}
			case LIST_ARTISTS_NO_COMMON_JOB -> {
				listArtistsNoCommonJob(cr);
			}
			case LIST_ARTISTS_MORE_COMMON_JOB -> {
				listArtistsMoreCommonJob(cr);
			}
			default -> {
				System.out.println(Main.UNKNOWN_COMMAND);
			}
			}
			comm = readCommand(in);
		}
		System.out.printf(Main.EXIT_MESSAGE);
		in.close();
	}

	private static Command readCommand(Scanner in) {
		String input = in.next().toLowerCase();
		for (Command c : Command.values())
			if (c.getCommandInputName().equals(input))
				return c;
		return Command.UNKNOWN;
	}

	private static RegisterUserType readRegisterUserTypeCommand(Scanner in) {
		String input = in.next().toLowerCase();
		for (RegisterUserType ruc : RegisterUserType.values())
			if (ruc.getCommandInputName().equals(input))
				return ruc;
		return RegisterUserType.UNKNOWN;
	}

	/**
	 * Shows the available commands
	 */
	private static void help() {
		System.out.printf(HELP_MESSAGE);
	}

	/**
	 * adds a critic in the system
	 * 
	 * @param cr       cine review system
	 * @param username name of the critic
	 */
	private static void addCritic(CineReviews cr, String username) {

		try {
			cr.addCritic(username);
			System.out.printf(REGISTERED_SUCCESS_CRITIC, username);
		} catch (AlreadyExistentIdentifierException e) {
			System.out.printf(e.getMessage(), username);
		}

	}

	/**
	 * adds an audience member in the system
	 * 
	 * @param cr       cine review system
	 * @param username name of the audience member
	 */
	private static void addAudience(CineReviews cr, String username) {

		try {
			cr.addAudience(username);
			System.out.printf(REGISTERED_SUCCESS_AUDIENCE, username);
		} catch (AlreadyExistentIdentifierException e) {
			System.out.printf(e.getMessage(), username);
		}
	}

	/**
	 * adds an admin in the system
	 * 
	 * @param cr       cine review system
	 * @param in       scanner input
	 * @param username username name of the admin
	 */
	private static void addAdmin(CineReviews cr, Scanner in, String username) {

		String password = in.nextLine().trim();

		try {
			cr.addAdmin(username, password);
			System.out.printf(REGISTERED_SUCCESS_ADMIN, username);
		} catch (AlreadyExistentIdentifierException e) {
			System.out.printf(e.getMessage(), username);
		}
	}

	/**
	 * Lists all registered users
	 * 
	 * @param cr cine review system
	 */
	private static void listUsers(CineReviews cr) {

		try {
			Iterator<User> it = cr.listUsers();
			System.out.printf(LIST_USERS_SUCCESS_HEADER);
			while (it.hasNext()) {
				User user = it.next();
				if (user.getType().equals(ADMIN_ACCOUNT)) {
					System.out.printf(LIST_USERS_BODY_SUCCESS_ADMIN, user.getName(), user.getAmountOfReviewsOrShows());
				} else {
					System.out.printf(LIST_USERS_SUCCESS_BODY_CRITIC_AND_AUDIENCE_PERSON, user.getName(),
							user.getAmountOfReviewsOrShows());
				}
			}
		} catch (NoUsersRegisteredException e) {
			System.out.printf(e.getMessage());
		}

	}

	/**
	 * Uploads a new movie
	 * 
	 * @param cr cine review system
	 * @param in scanner input
	 */
	private static void uploadMovie(CineReviews cr, Scanner in) {
		String adminName, adminPassword, movieTitle, movieDirectorName, movieAgeCertification, castMemberName,
				movieGenresType;

		int movieDuration, movieRealeseDate, numberOfGenres, numberCastMembers;

		List<String> movieGenres = new ArrayList<String>();
		List<String> castMembers = new ArrayList<String>();

		adminName = in.next();
		adminPassword = in.next();
		in.nextLine();
		movieTitle = in.nextLine().trim();
		movieDirectorName = in.nextLine().trim();
		movieDuration = in.nextInt();
		in.nextLine();
		movieAgeCertification = in.nextLine().trim();
		movieRealeseDate = in.nextInt();
		in.nextLine();
		numberOfGenres = in.nextInt();
		in.nextLine();
		for (int i = 0; i < numberOfGenres; i++) {
			movieGenresType = in.nextLine().trim();
			movieGenres.add(movieGenresType);
		}
		numberCastMembers = in.nextInt();
		in.nextLine();
		for (int i = 0; i < numberCastMembers; i++) {
			castMemberName = in.nextLine().trim();
			castMembers.add(castMemberName);
		}

		try {
			cr.uploadMovie(adminName, adminPassword, movieTitle, movieDirectorName, movieDuration,
					movieAgeCertification, movieRealeseDate, movieGenres, castMembers);
			System.out.printf(MOVIE_SUCCESS_MESSAGE, movieTitle, movieRealeseDate, cr.numberOfNewArtists());

		} catch (NotAnAdminException e) {
			System.out.printf(e.getMessage(), adminName);
		} catch (WrongPasswordException e) {
			System.out.printf(e.getMessage());
		} catch (TitleAlreadyExistsException e) {
			System.out.printf(e.getMessage(), movieTitle);
		}
	}

	/**<
	 * Uploads a new series
	 * 
	 * @param cr cine review system
	 * @param in scanner input
	 */
	private static void uploadSeries(CineReviews cr, Scanner in) {
		String adminName, adminPassword, seriesTitle, seriesCreatorName, seriesAgeCertification, castMemberName,
				seriesGenresType;

		int numberOfSeasons, seriesRealeseDate, numberOfGenres, numberCastMembers;

		List<String> seriesGenres = new ArrayList<String>();
		List<String> castMembers = new ArrayList<String>();

		adminName = in.next();
		adminPassword = in.next();
		in.nextLine();
		seriesTitle = in.nextLine().trim();
		seriesCreatorName = in.nextLine().trim();
		numberOfSeasons = in.nextInt();
		in.nextLine();
		seriesAgeCertification = in.nextLine();
		seriesRealeseDate = in.nextInt();
		in.nextLine();
		numberOfGenres = in.nextInt();
		in.nextLine();
		for (int i = 0; i < numberOfGenres; i++) {
			seriesGenresType = in.nextLine().trim();
			seriesGenres.add(seriesGenresType);
		}
		numberCastMembers = in.nextInt();
		in.nextLine();
		for (int i = 0; i < numberCastMembers; i++) {
			castMemberName = in.nextLine().trim();
			castMembers.add(castMemberName);
		}
		try {
			cr.uploadSeries(adminName, adminPassword, seriesTitle, seriesCreatorName, numberOfSeasons,
					seriesAgeCertification, seriesRealeseDate, seriesGenres, castMembers);
			System.out.printf(SERIES_SUCCESS_MESSAGE, seriesTitle, seriesRealeseDate, cr.numberOfNewArtists());
		} catch (NotAnAdminException e) {
			System.out.printf(e.getMessage(), adminName);
		} catch (WrongPasswordException e) {
			System.out.printf(e.getMessage());
		} catch (TitleAlreadyExistsException e) {
			System.out.printf(e.getMessage(), seriesTitle);
		}
	}

	/**
	 * Lists all shows
	 * 
	 * @param cr cine review system
	 */
	private static void listShows(CineReviews cr) {
		try {
			Iterator<Show> it = cr.listShows();
			System.out.printf(SHOWS_SUCCESS_HEADER);
			while (it.hasNext()) {
				Show show = it.next();
				if (show instanceof MovieClass) {
					System.out.printf(SHOWS_SUCCESS_BODY_MOVIE_START, show.getTitle(), show.getDirectorOrCreator(),
							show.getDurationOrNumOfSeasons(), show.getAgeCertification(), show.getReleaseYear(),
							show.getMainGenre());
					Iterator<Artist> iterator = show.listCast();
					while (iterator.hasNext()) {
						Artist castMember = iterator.next();
						System.out.printf(SHOWS_SUCCESS_BODY_MOVIE_CAST, castMember.getName());
					}
					System.out.println();
				} else {
					System.out.printf(SHOWS_SUCCESS_BODY_SERIES_START, show.getTitle(), show.getDirectorOrCreator(),
							show.getDurationOrNumOfSeasons(), show.getAgeCertification(), show.getReleaseYear(),
							show.getMainGenre());
					Iterator<Artist> iterator = show.listCast();
					while (iterator.hasNext()) {
						Artist castMember = iterator.next();
						System.out.printf(SHOWS_SUCCESS_BODY_SERIES_CAST, castMember.getName());
					}
					System.out.println();

				}
			}
		} catch (NoUploadedShowsException e) {
			System.out.printf(e.getMessage());
		}
	}

	/**
	 * Adds bio information about an artist
	 * 
	 * @param cr cine review system
	 * @param in scanner input
	 */
	private static void addBioArtistInfo(CineReviews cr, Scanner in) {

		String artistName, artistBirthDate, artistBirthPlace;

		artistName = in.nextLine().trim();
		artistBirthDate = in.nextLine().trim();
		artistBirthPlace = in.nextLine().trim();

		try {
			if (cr.existsArtist(artistName)) {
				cr.addBioArtistInfo(artistName, artistBirthDate, artistBirthPlace);
				System.out.printf(ARTIST_SUCCESS_MESSAGE_ALREADY_EXISTED, artistName);
			} else {
				cr.addArtist(artistName, artistBirthDate, artistBirthPlace);
				System.out.printf(ARTIST_SUCCESS_MESSAGE_DIDNT_ALREADY_EXIST, artistName);
			}
		} catch (BioAlreadyExistsException e) {
			System.out.printf(e.getMessage(), artistName);
		}

	}

	/**
	 * Lists the bio and credits of an artist.
	 * 
	 * @param cr cine review system
	 * @param in scanner input
	 */
	private static void listArtistCredits(CineReviews cr, Scanner in) {

		String artistName = in.nextLine().trim();

		try {
			if (cr.hasBio(artistName)) {
				System.out.printf(CREDITS_SUCCESS_ARTIST_HAS_BIRTHDATE, cr.getBirthDateInfo(artistName));
				System.out.printf(CREDITS_SUCCESS_ARTIST_HAS_BIRTHPLACE, cr.getBirthPlaceInfo(artistName));
			}
			if (cr.hasParticipatedInShow(artistName)) {
				Iterator<Show> it = cr.listShowsParticipated(artistName);
				while (it.hasNext()) {
					Show show = it.next();
					if (show.isDirectorOrCreator(artistName)) {
						if (show instanceof MovieClass) {
							System.out.printf(CREDITS_SUCCESS_BODY, show.getTitle(), show.getReleaseYear(), DIRECTOR,
									show.getType());
						} else {
							System.out.printf(CREDITS_SUCCESS_BODY, show.getTitle(), show.getReleaseYear(), CREATOR,
									show.getType());
						}
					} else {
						System.out.printf(CREDITS_SUCCESS_BODY, show.getTitle(), show.getReleaseYear(), ACTOR,
								show.getType());
					}
				}

			}
		} catch (NotExistentNameException e) {
			System.out.printf(e.getMessage(), artistName);
		}
	}

	/**
	 * Adds a review to a show
	 * 
	 * @param cr cine review system
	 * @param in scanner input
	 */
	private static void addReview(CineReviews cr, Scanner in) {

		String username, title, comment, classification;

		username = in.next();
		title = in.nextLine().trim();
		comment = in.nextLine().trim();
		classification = in.nextLine().trim();

		try {
			cr.addReview(username, title, comment, classification);
			System.out.printf(REVIEW_SUCCESS_MESSAGE, title, cr.getNumberOfReviews(title));
		} catch (NotExistentUserException e) {
			System.out.printf(e.getMessage(), username);
		} catch (UsernameAdminAssociationException e) {
			System.out.printf(e.getMessage(), username);
		} catch (TitleNotAssociatedWithShowException e) {
			System.out.printf(e.getMessage(), title);
		} catch (AlreadyReviewedShowException e) {
			System.out.printf(e.getMessage(), username, title);
		}
	}

	/**
	 * Lists the reviews of a show
	 * 
	 * @param cr cine review system
	 * @param in scanner input
	 */
	private static void listShowReviews(CineReviews cr, Scanner in) {

		String showTitle;
		showTitle = in.nextLine().trim();

		try {

			if (!cr.hasReviews(showTitle)) {
				System.out.printf(REVIEWS_NO_REVIEWS_ERROR, showTitle);
			} else {
				System.out.printf(REVIEWS_SUCCESS_HEADER, showTitle, cr.getAverageReviewScore(showTitle));
				Iterator<Reviews> it = cr.listShowReviews(showTitle);
				while (it.hasNext()) {
					Reviews review = it.next();
					System.out.printf(REVIEWS_SUCCESS_BODY, review.getUsername(), review.getUserType(),
							review.getComment(), review.getClassification());
				}
			}

		} catch (TitleNotAssociatedWithShowException e) {
			System.out.printf(e.getMessage(), showTitle);
		}
	}

	/**
	 * Lists shows of given genres.
	 * 
	 * @param cr cine review system
	 * @param in scanner input
	 */
	private static void listByGenre(CineReviews cr, Scanner in) {

		String genre;
		List<String> seriesGenres = new ArrayList<String>();
		int noGenre = in.nextInt();
		in.nextLine();

		try {
			for (int i = 0; i < noGenre; i++) {
				genre = in.nextLine().trim();
				seriesGenres.add(genre);
			}
			Iterator<Show> it = cr.listGenres(seriesGenres);
			System.out.printf(GENRE_SUCCESS_HEADER);
			while (it.hasNext()) {
				Show show = it.next();
				if (show instanceof MovieClass) {
					System.out.printf(GENRE_SUCCES_BODY_MOVIE, show.getTitle(), show.getDirectorOrCreator(),
							show.getReleaseYear(), show.getAverageReviews());
				} else {
					System.out.printf(GENRE_SUCCES_BODY_SERIES, show.getTitle(), show.getDirectorOrCreator(),
							show.getReleaseYear(), show.getAverageReviews());
				}
			}
		} catch (NotFoundWithinCriteriaException e) {
			System.out.printf(e.getMessage());
		}

	}

	/**
	 * Lists shows released on a given year
	 * 
	 * @param cr cine review system
	 * @param in scanner input
	 */
	private static void listByYear(CineReviews cr, Scanner in) {

		int releaseDate = in.nextInt();
		in.nextLine();

		try {
			Iterator<Show> it = cr.listReleased(releaseDate);
			System.out.printf(RELEASED_SUCCESS_HEADER, releaseDate);
			while (it.hasNext()) {
				Show show = it.next();
				if (show instanceof MovieClass) {
					System.out.printf(RELEASED_SUCCESS_MOVIE, show.getTitle(), show.getDirectorOrCreator(),
							show.getReleaseYear(), show.getAverageReviews());
				} else {
					System.out.printf(RELEASED_SUCCESS_SERIES, show.getTitle(), show.getDirectorOrCreator(),
							show.getReleaseYear(), show.getAverageReviews());
				}
			}

		} catch (NotFoundWithinCriteriaException e) {
			System.out.printf(e.getMessage());
		}
	}

	/**
	 * lists the artists that have no common projects
	 * 
	 * @param cr cine review system
	 */
	private static void listArtistsNoCommonJob(CineReviews cr) {

		try {

			Iterator<SortedSet<Artist>> filteredSuperSetIterator = cr.listAvoiders();
			System.out.printf(AVOIDERS_SUCCESS_HEADER, cr.getNumberOfAvoiders(cr.listAvoiders()));
			while (filteredSuperSetIterator.hasNext()) {
				SortedSet<Artist> subset = filteredSuperSetIterator.next();
				Iterator<Artist> subsetIterator = subset.iterator();
				Artist artist = subsetIterator.next();
				System.out.printf(AVOIDERS_SUCCESS_BEGINNING, artist.getName());
				while (subsetIterator.hasNext()) {
					artist = subsetIterator.next();
					if (!subsetIterator.hasNext()) {
						System.out.printf(AVOIDERS_SUCCESS_END, artist.getName());
					} else {
						System.out.printf(AVOIDERS_SUCCESS_BODY, artist.getName());
					}
				}
			}
		} catch (NoArtistsException e) {
			System.out.printf(e.getMessage());
		} catch (SmallWorldException e) {
			System.out.printf(e.getMessage());
		}

	}

	/**
	 * lists the artists that have more projects together
	 * 
	 * @param cr
	 */
	private static void listArtistsMoreCommonJob(CineReviews cr) {

		try {
			Iterator<String> it = cr.listFriends();
			System.out.printf(FRIENDS_SUCCESS_HEADER, cr.getMaxNumberOfCommonProjects());
			while (it.hasNext()) {
				String s = it.next();
				System.out.println(s);
			}

		} catch (NoArtistsException e) {
			System.out.printf(e.getMessage());
		} catch (NoCollaborationsException e) {
			System.out.printf(e.getMessage());
		}																							
	}

}
