package cinereviews;

public class ReviewsClass implements Reviews {

	private static final String EXCELLENT = "excellent";
	private static final String GOOD = "good";
	private static final String AVERAGE = "average";
	private static final String POOR = "poor";
	private static final String TERRIBLE = "terrible";
	protected static final String AUDIENCE_ACCOUNT = "audience";

	private String showTitle;
	private User poster;
	private String comment;
	private String classification;
	private float points;
	private int pointsWithoutMultiplier;

	public ReviewsClass(String showTitle, User poster, String comment, String classification) {

		this.points = 0;
		this.pointsWithoutMultiplier = 0;
		this.showTitle = showTitle;
		this.poster = poster;
		this.comment = comment;
		this.classification = classification;

		switch (classification) {
		case EXCELLENT -> {
			pointsWithoutMultiplier = 5;
		}
		case GOOD -> {
			pointsWithoutMultiplier = 4;
		}
		case AVERAGE -> {
			pointsWithoutMultiplier = 3;
		}
		case POOR -> {
			pointsWithoutMultiplier = 2;
		}
		case TERRIBLE -> {
			pointsWithoutMultiplier = 1;
		}
		default -> {
		}
		}

		if (this.poster instanceof CriticAccountClass)
			points = 5 * pointsWithoutMultiplier;
		else
			points = pointsWithoutMultiplier;
	}

	@Override
	public String getClassification() {
		return this.classification;
	}

	@Override
	public String getUsername() {

		return this.poster.getName();
	}

	@Override
	public String getComment() {
		return this.comment;
	}

	@Override
	public String getUserType() {
		return poster.getType();
	}

	@Override
	public float getPoints() {

		return this.points;
	}

	@Override
	public User getUser() {

		return this.poster;
	}

	@Override
	public int getPointsWithoutMultiplier() {

		return pointsWithoutMultiplier;
	}

	@Override
	public String getShowTitle() {
		return this.showTitle;
	}
}
