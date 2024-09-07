package cinereviews;

import java.util.Comparator;

public class ComparatorByReleaseDate implements Comparator<Show> {

	@Override
	public int compare(Show o1, Show o2) {
		return o2.getReleaseYear() - o1.getReleaseYear();
	}

}
