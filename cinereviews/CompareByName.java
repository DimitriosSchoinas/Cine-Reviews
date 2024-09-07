package cinereviews;

import java.util.Comparator;

public class CompareByName implements Comparator<Reviews> {

	@Override
	public int compare(Reviews o1, Reviews o2) {

		return o1.getUser().getName().compareTo(o2.getUser().getName());
	}

}
