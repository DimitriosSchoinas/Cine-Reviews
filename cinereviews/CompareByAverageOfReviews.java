package cinereviews;

import java.util.Comparator;

public class CompareByAverageOfReviews implements Comparator<Show> {

	@Override
	public int compare(Show o1, Show o2) {
		float result = o2.getAverageReviews() - o1.getAverageReviews();
		if (result < 0)
			return -1;
		else if (result == 0)
			return 0;
		else
			return 1;
	}
}
