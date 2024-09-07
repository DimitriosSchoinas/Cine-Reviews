package cinereviews;

import java.util.Comparator;

public class CompareByUserType implements Comparator<Reviews> {

	@Override
	public int compare(Reviews o1, Reviews o2) {

		int number1 = 0;
		int number2 = 0;
		if (o1.getUser() instanceof CriticAccountClass)
			number1 = 1;

		if (o2.getUser() instanceof CriticAccountClass)
			number2 = 1;

		return number2 - number1;
	}

}
