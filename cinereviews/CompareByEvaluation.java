package cinereviews;

import java.util.Comparator;

public class CompareByEvaluation implements Comparator<Reviews> {

	@Override
	public int compare(Reviews o1, Reviews o2) {

		return o2.getPointsWithoutMultiplier() - o1.getPointsWithoutMultiplier();
	}
}
