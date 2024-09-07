package cinereviews;

public class AudienceAccountClass extends AbstractUserClass implements AudienceOrCriticAccount {

	protected static final String AUDIENCE_ACCOUNT = "audience";

	private int reviewCounter;

	public AudienceAccountClass(String username) {
		super(username);
		this.reviewCounter = 0;
		this.type = AUDIENCE_ACCOUNT;
	}

	@Override
	public int getAmountOfReviewsOrShows() {
		return this.reviewCounter;
	}

	@Override
	public void incrementReviewCount() {
		this.reviewCounter++;
	}
}
