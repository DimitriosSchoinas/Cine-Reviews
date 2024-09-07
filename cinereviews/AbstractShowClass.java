package cinereviews;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class AbstractShowClass implements Show {

	protected static final String AUDIENCE_ACCOUNT = "audience";

	private String title;
	private String creator_director;
	private int duration_numSeasons;
	private String ageCertification;
	private int releaseDate;
	private List<String> genres;
	private List<Artist> castMembers;
	private List<Reviews> reviews;
	protected String type;

	public AbstractShowClass(String title, String creator_director, int duration_numSeasons, String ageCertification,
			int realeseDate, List<String> genres, List<Artist> castMembers) {
		this.title = title;
		this.ageCertification = ageCertification;
		this.creator_director = creator_director;
		this.duration_numSeasons = duration_numSeasons;
		this.releaseDate = realeseDate;
		this.title = title;
		this.genres = genres;
		this.castMembers = castMembers;
		reviews = new ArrayList<Reviews>();
	}

	@Override
	public String getAgeCertification() {
		return this.ageCertification;
	}

	@Override
	public int getDurationOrNumOfSeasons() {
		return this.duration_numSeasons;
	}

	@Override
	public String getMainGenre() {
		return this.genres.get(0);
	}

	@Override
	public String getDirectorOrCreator() {
		return this.creator_director;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	@Override
	public int getReleaseYear() {
		return this.releaseDate;
	}

	@Override
	public Iterator<Artist> listCast() {
		List<Artist> artistsToBeListed = new ArrayList<>();
		for (int i = 0; i < 3 && i < castMembers.size(); i++) {
			artistsToBeListed.add(castMembers.get(i));
		}
		return artistsToBeListed.iterator();
	}

	@Override
	public boolean isDirectorOrCreator(String artistName) {
		return artistName.equals(this.creator_director);
	}

	@Override
	public float getAverageReviews() {

		float totalPointSum = 0;
		float totalUserSum = 0;
		Iterator<Reviews> it = reviews.iterator();
		while (it.hasNext()) {
			Reviews review = it.next();
			totalPointSum = totalPointSum + review.getPoints();
			if (review.getUser() instanceof CriticAccountClass) {
				totalUserSum = totalUserSum + 5;
			} else {
				totalUserSum = totalUserSum + 1;
			}
		}
		if (totalPointSum == 0 && totalUserSum == 0)
			return 0;
		float result = totalPointSum / totalUserSum;
		return result;

	}

	public boolean hasAlreadyBeenReviewed(String username) {

		boolean result = false;
		Iterator<Reviews> it = reviews.iterator();
		while (it.hasNext()) {
			Reviews review = it.next();
			if (review.getUser().getName().equals(username))
				result = true;
		}
		return result;
	}

	public void addReview(String title, User user, String comment, String classification) {

		reviews.add(new ReviewsClass(title, user, comment, classification));
	}

	public boolean hasReviews() {

		return !reviews.isEmpty();
	}

	public int compareTo(Show other) {
		return this.getTitle().compareTo(other.getTitle());
	}

	public int getNumberOfReviews() {
		return reviews.size();
	}

	public boolean isShowWithinGenreCriteria(List<String> genresToCheck) {
		boolean result = true;
		if (genresToCheck.size() > genres.size())
			result = false;
		else {
			int i = 0;
			while (i < genresToCheck.size() && result) {
				int j = 0;
				boolean found = false;
				while (j < genres.size() && !found) {
					if (genresToCheck.get(i).equals(genres.get(j)))
						found = true;
					else
						j++;
				}
				if (j == genres.size())
					result = false;
				else
					i++;
			}
		}
		return result;
	}
}
