package cinereviews;

import java.util.List;

public class MovieClass extends AbstractShowClass {

	private static final String MOVIE = "movie";

	public MovieClass(String title, String creator_director, int duration_numSeasons, String ageCertification,
			int realeseDate, List<String> genres, List<Artist> castMembers) {
		super(title, creator_director, duration_numSeasons, ageCertification, realeseDate, genres, castMembers);
		this.type = MOVIE;
	}

}
