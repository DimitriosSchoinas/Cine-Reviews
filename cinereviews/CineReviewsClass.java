package cinereviews;

import java.util.*;

import Exceptions.*;

public class CineReviewsClass implements CineReviews {

	private Map<String, User> users;// chave e o nome
	private List<User> listUsers;// lista dos users para iterar
	private Map<String, Show> shows;// chave e o titulo do filme
	private Map<String, Artist> artists;// chave e o nome do artista
	private List<Show> listShows;// lista dos shows para iterar
	private Map<Show, List<Reviews>> listShowReviews;// show->lista de reviews, usado para iterar as reviews de um dado
														// show
	private Map<String, Map<String, Integer>> artistsThatWorkedTogether;
	private int amountOfNewArtistsInLatestShow;
	private static final String ADMIN_ACCOUNT = "admin";

	
	public CineReviewsClass() {
		users = new HashMap<String, User>();
		listUsers = new ArrayList<User>();
		shows = new HashMap<String, Show>();
		artists = new HashMap<String, Artist>();
		listShows = new ArrayList<Show>();
		listShowReviews = new HashMap<Show, List<Reviews>>();
		artistsThatWorkedTogether = new HashMap<String, Map<String, Integer>>();
		this.amountOfNewArtistsInLatestShow = 0;

	}

	@Override
	public void addCritic(String username) throws AlreadyExistentIdentifierException {

		if (users.containsKey(username)) {
			throw new AlreadyExistentIdentifierException();
		}

		users.put(username, new CriticAccountClass(username));
		listUsers.add(users.get(username));
	}

	@Override
	public void addAudience(String username) throws AlreadyExistentIdentifierException {

		if (users.containsKey(username)) {
			throw new AlreadyExistentIdentifierException();
		}

		users.put(username, new AudienceAccountClass(username));
		listUsers.add(users.get(username));
	}

	@Override
	public void addAdmin(String username, String password) throws AlreadyExistentIdentifierException {

		if (users.containsKey(username)) {
			throw new AlreadyExistentIdentifierException();
		}

		users.put(username, new AdminAccountClass(username, password));
		listUsers.add(users.get(username));
	}

	@Override
	public Iterator<User> listUsers() throws NoUsersRegisteredException {
		if (listUsers.size() == 0)
			throw new NoUsersRegisteredException();
		Collections.sort(listUsers);
		return listUsers.iterator();
	}

	@Override
	public void addArtist(String castMemberName, String artistBirthDate, String artistBirthPlace)
			throws BioAlreadyExistsException {
		artists.put(castMemberName, new ArtistClass(castMemberName, artistBirthDate, artistBirthPlace));
	}

	@Override
	public void uploadMovie(String username, String password, String movieTitle, String movieDirectorName,
			int movieDuration, String movieAgeCertification, int movieReleaseDate, List<String> movieGenres,
			List<String> castMembers) throws NotAnAdminException, WrongPasswordException, TitleAlreadyExistsException {

		if (!users.containsKey(username) || !users.get(username).getType().equals(ADMIN_ACCOUNT)) {
			throw new NotAnAdminException();
		}
		AdminAccount a = (AdminAccount) users.get(username);
		if (!a.getPassword().equals(password))
			throw new WrongPasswordException();
		if (shows.containsKey(movieTitle))
			throw new TitleAlreadyExistsException();

		this.amountOfNewArtistsInLatestShow = 0;
		List<Artist> artistsInMovie = new ArrayList<Artist>();
		if (!artists.containsKey(movieDirectorName)) {
			artists.put(movieDirectorName, new ArtistClass(movieDirectorName));
			this.amountOfNewArtistsInLatestShow++;
		}
		for (int i = 0; i < castMembers.size(); i++) {
			if (!artists.containsKey(castMembers.get(i))) {
				artists.put(castMembers.get(i), new ArtistClass(castMembers.get(i)));
				artistsInMovie.add(artists.get(castMembers.get(i)));

				this.amountOfNewArtistsInLatestShow++;
			} else {
				artistsInMovie.add(artists.get(castMembers.get(i)));
			}
		}
		shows.put(movieTitle, new MovieClass(movieTitle, movieDirectorName, movieDuration, movieAgeCertification,
				movieReleaseDate, movieGenres, artistsInMovie));
		listShows.add(shows.get(movieTitle));
		a.addShow(shows.get(movieTitle));
		this.addMovieParticipatedIn(movieDirectorName, artistsInMovie, shows.get(movieTitle));
		this.addArtistsThatCollaborated(castMembers, movieDirectorName);
	}

	private void addMovieParticipatedIn(String director, List<Artist> artistsToAdd, Show s) {
		artists.get(director).addShowParticipated(s);
		for (int i = 0; i < artistsToAdd.size(); i++) {
			artistsToAdd.get(i).addShowParticipated(s);
		}
	}

	private void addArtistsThatCollaborated(List<String> castMembers, String director_creator) {
		castMembers.add(director_creator);
		for (int i = 0; i < castMembers.size(); i++) {
			String artistName = castMembers.get(i);
			for (int j = 0; j < castMembers.size(); j++) {
				Map<String, Integer> map = this.artistsThatWorkedTogether.get(artistName);
				if (map == null) {
					map = new HashMap<String, Integer>();
					this.artistsThatWorkedTogether.put(artistName, map);
				}
				if (!artistName.equals(castMembers.get(j))) {
					if (map.containsKey(castMembers.get(j))) {
						Integer increment = map.get(castMembers.get(j));
						increment += 1;
						map.put(castMembers.get(j), increment);
					} else {
						map.put(castMembers.get(j), 1);
					}
				}
			}
		}
	}

	private void addSeriesParticipatedIn(String creator, List<Artist> artistsToAdd, Show s) {
		artists.get(creator).addShowParticipated(s);
		for (int i = 0; i < artistsToAdd.size(); i++) {
			artistsToAdd.get(i).addShowParticipated(s);
		}
	}

	@Override
	public int numberOfNewArtists() throws NotAnAdminException, WrongPasswordException, TitleAlreadyExistsException {
		return this.amountOfNewArtistsInLatestShow;
	}

	@Override
	public void uploadSeries(String username, String password, String seriesTitle, String seriesCreatorName,
			int numberOfSeasons, String seriesAgeCertification, int seriesRealeseDate, List<String> seriesGenres,
			List<String> castMembers) throws NotAnAdminException, WrongPasswordException, TitleAlreadyExistsException {
		if (!users.containsKey(username) || !users.get(username).getType().equals(ADMIN_ACCOUNT))
			throw new NotAnAdminException();
		AdminAccount a = (AdminAccount) users.get(username);
		if (!a.getPassword().equals(password))
			throw new WrongPasswordException();
		if (shows.containsKey(seriesTitle))
			throw new TitleAlreadyExistsException();
		this.amountOfNewArtistsInLatestShow = 0;
		List<Artist> artistsInSeries = new ArrayList<Artist>();
		if (!artists.containsKey(seriesCreatorName)) {
			artists.put(seriesCreatorName, new ArtistClass(seriesCreatorName));
			this.amountOfNewArtistsInLatestShow++;
		}
		for (int i = 0; i < castMembers.size(); i++) {
			if (!artists.containsKey(castMembers.get(i))) {
				artists.put(castMembers.get(i), new ArtistClass(castMembers.get(i)));
				artistsInSeries.add(artists.get(castMembers.get(i)));
				this.amountOfNewArtistsInLatestShow++;
			} else {
				artistsInSeries.add(artists.get(castMembers.get(i)));
			}
		}
		shows.put(seriesTitle, new SeriesClass(seriesTitle, seriesCreatorName, numberOfSeasons, seriesAgeCertification,
				seriesRealeseDate, seriesGenres, artistsInSeries));
		listShows.add(shows.get(seriesTitle));
		a.addShow(shows.get(seriesTitle));
		this.addSeriesParticipatedIn(seriesCreatorName, artistsInSeries, shows.get(seriesTitle));
		this.addArtistsThatCollaborated(castMembers, seriesCreatorName);
	}

	@Override
	public Iterator<Show> listShows() throws NoUploadedShowsException {
		if (shows.size() == 0)
			throw new NoUploadedShowsException();
		Collections.sort(listShows);
		return listShows.iterator();
	}

	@Override
	public void addBioArtistInfo(String artistName, String artistBirthDate, String artistBirthPlace)
			throws BioAlreadyExistsException {
		if (artists.get(artistName).hasBio())
			throw new BioAlreadyExistsException();
		artists.get(artistName).updateBio(artistBirthDate, artistBirthPlace);
	}

	@Override
	public boolean existsArtist(String artistName) {
		return artists.containsKey(artistName);
	}

	@Override
	public boolean hasBio(String artistName) throws NotExistentNameException {
		if (!artists.containsKey(artistName))
			throw new NotExistentNameException();
		return artists.get(artistName).hasBio();
	}

	@Override
	public String getBirthDateInfo(String artistName) {
		return artists.get(artistName).getBirthDate();
	}

	@Override
	public String getBirthPlaceInfo(String artistName) {
		return artists.get(artistName).getBirthPlace();
	}

	@Override
	public boolean hasParticipatedInShow(String artistName) throws NotExistentNameException {
		if (!artists.containsKey(artistName))
			throw new NotExistentNameException();
		return artists.get(artistName).hasParticipatedInShow();
	}

	@Override
	public Iterator<Show> listShowsParticipated(String artistName) {
		return artists.get(artistName).listShowsParticipated();
	}

	@Override
	public void addReview(String username, String title, String comment, String classification)
			throws NotExistentUserException, UsernameAdminAssociationException, TitleNotAssociatedWithShowException,
			AlreadyReviewedShowException {

		if (!users.containsKey(username))
			throw new NotExistentUserException();

		if (users.get(username).getType().equals(ADMIN_ACCOUNT))
			throw new UsernameAdminAssociationException();

		if (!shows.containsKey(title))
			throw new TitleNotAssociatedWithShowException();

		if (shows.get(title).hasAlreadyBeenReviewed(username))
			throw new AlreadyReviewedShowException();

		List<Reviews> list = this.listShowReviews.get(shows.get(title));
		if (list == null) {
			list = new ArrayList<Reviews>();
			listShowReviews.put(shows.get(title), list);
		}
		list.add(new ReviewsClass(title, users.get(username), comment, classification));
		shows.get(title).addReview(title, users.get(username), comment, classification);
		AudienceOrCriticAccount a = (AudienceOrCriticAccount) users.get(username);
		a.incrementReviewCount();
	}

	@Override
	public boolean hasReviews(String showTitle) throws TitleNotAssociatedWithShowException {

		if (!shows.containsKey(showTitle))
			throw new TitleNotAssociatedWithShowException();

		return shows.get(showTitle).hasReviews();
	}

	@Override
	public float getAverageReviewScore(String showTitle) throws TitleNotAssociatedWithShowException {

		if (!shows.containsKey(showTitle))
			throw new TitleNotAssociatedWithShowException();

		return shows.get(showTitle).getAverageReviews();
	}

	@Override
	public Iterator<Reviews> listShowReviews(String title) throws TitleNotAssociatedWithShowException {

		if (!shows.containsKey(title))
			throw new TitleNotAssociatedWithShowException();

		List<Reviews> list = listShowReviews.get(shows.get(title));
		Collections.sort(list, new CompareByName());
		Collections.sort(list, new CompareByEvaluation());
		Collections.sort(list, new CompareByUserType());
		return list.iterator();
	}

	@Override
	public Iterator<Show> listGenres(List<String> genres) throws NotFoundWithinCriteriaException {
		List<Show> filteredShowsByGenre = new ArrayList<Show>();
		for (int i = 0; i < listShows.size(); i++) {
			if (listShows.get(i).isShowWithinGenreCriteria(genres))
				filteredShowsByGenre.add(listShows.get(i));
		}
		if (filteredShowsByGenre.isEmpty())
			throw new NotFoundWithinCriteriaException();
		Collections.sort(filteredShowsByGenre);
		Collections.sort(filteredShowsByGenre, new ComparatorByReleaseDate());
		Collections.sort(filteredShowsByGenre, new CompareByAverageOfReviews());
		return filteredShowsByGenre.iterator();
	}

	@Override
	public Iterator<Show> listReleased(int releaseYear) throws NotFoundWithinCriteriaException {
		List<Show> filteredShowsByYear = new ArrayList<Show>();
		for (int i = 0; i < listShows.size(); i++) {
			if (listShows.get(i).getReleaseYear() == releaseYear)
				filteredShowsByYear.add(listShows.get(i));
		}
		if (filteredShowsByYear.isEmpty())
			throw new NotFoundWithinCriteriaException();
		Collections.sort(filteredShowsByYear);
		Collections.sort(filteredShowsByYear, new CompareByAverageOfReviews());
		return filteredShowsByYear.iterator();
	}

	private SortedSet<SortedSet<Artist>> powerSet() {
		SortedSet<SortedSet<Artist>> subsets = new TreeSet<>(new SetComparator());
		subsets.add(new TreeSet<>());

		for (Artist artist : artists.values()) {
			SortedSet<SortedSet<Artist>> current = new TreeSet<>(new SetComparator());
			for (SortedSet<Artist> set : subsets) {
				SortedSet<Artist> newSet = new TreeSet<>(set);
				newSet.add(artist);
				current.add(newSet);
			}
			subsets.addAll(current);
		}
		return subsets;
	}

	@Override
	public Iterator<SortedSet<Artist>> listAvoiders() throws NoArtistsException, SmallWorldException {

		if (artists.isEmpty())
			throw new NoArtistsException();
		if (allArtistsHaveCommonProjects() || artists.size() == 1)
			throw new SmallWorldException();

		SortedSet<SortedSet<Artist>> largestSubSets = powerSet();
		SortedSet<SortedSet<Artist>> loopSet = powerSet();

		for (SortedSet<Artist> subset : loopSet) {

			if (subset.size() < 2) {
				largestSubSets.remove(subset);
			} else {
				for (Artist artist : subset) {

					Map<String, Integer> map = artistsThatWorkedTogether.get(artist.getName());

					for (Artist artist2 : subset) {

						if (map != null && !artist.getName().equals(artist2.getName())
								&& map.containsKey(artist2.getName())) {

							largestSubSets.remove(subset);
						}
					}
				}
			}
			if (largestSubSets.contains(subset) && subset.size() < largestSubSets.first().size()) {
				largestSubSets.remove(subset);
			}

		}

		return largestSubSets.iterator();
	}

	private boolean allArtistsHaveCommonProjects() {

		boolean result = true;

		for (Artist artist : artists.values()) {
			Map<String, Integer> map = artistsThatWorkedTogether.get(artist.getName());
			if (map == null) {
				result = false;
			} else {
				if (map.size() != artists.size() - 1) {
					result = false;
				}
			}
		}
		return result;
	}

	@Override
	public int getNumberOfAvoiders(Iterator<SortedSet<Artist>> it) throws NoArtistsException, SmallWorldException {
		SortedSet<Artist> s = it.next();
		return s.size();
	}

	@Override
	public Iterator<String> listFriends() throws NoArtistsException, NoCollaborationsException {
		if (artists.isEmpty())
			throw new NoArtistsException();
		if (shows.isEmpty())
			throw new NoCollaborationsException();
		List<String> list = new ArrayList<String>();
		int max = this.getMaxNumberOfCommonProjects();
		for (Artist artist : artists.values()) {
			Map<String, Integer> map = this.artistsThatWorkedTogether.get(artist.getName());
			if (map != null) {
				for (Artist a : artists.values()) {
					if (!a.getName().equals(artist.getName())) {
						if (map.containsKey(a.getName()) && map.get(a.getName()) == max) {
							int i = a.getName().compareTo(artist.getName());
							String s = null;
							if (i < 0) {
								s = a.getName() + " and " + artist.getName();
							} else {
								s = artist.getName() + " and " + a.getName();
							}
							if (!list.contains(s)) {
								list.add(s);
							}
						}
					}
				}
			}
		}
		Collections.sort(list);
		return list.iterator();
	}

	@Override
	public int getMaxNumberOfCommonProjects() throws NoArtistsException, NoCollaborationsException {
		int max = 0;
		for (Artist artist : this.artists.values()) {
			Map<String, Integer> map = this.artistsThatWorkedTogether.get(artist.getName());
			if (map != null) {
				for (Integer i : map.values()) {
					if (i > max) {
						max = i;
					}
				}
			}
		}
		return max;
	}

	@Override
	public int getNumberOfReviews(String title) throws TitleNotAssociatedWithShowException {

		if (!shows.containsKey(title))
			throw new TitleNotAssociatedWithShowException();

		return shows.get(title).getNumberOfReviews();
	}

}
