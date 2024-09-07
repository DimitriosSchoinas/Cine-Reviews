package cinereviews;

abstract class AbstractUserClass implements User {

	private String username;
	protected String type;

	public AbstractUserClass(String username) {
		this.username = username;
	}

	@Override
	public String getName() {
		return this.username;
	}

	public abstract int getAmountOfReviewsOrShows();

	@Override
	public int compareTo(User other) {
		return this.getName().compareTo(other.getName());
	}

	public String getType() {
		return this.type;
	}
}
