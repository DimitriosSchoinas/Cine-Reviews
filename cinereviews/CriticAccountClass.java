package cinereviews;

public class CriticAccountClass extends AbstractUserClass implements AudienceOrCriticAccount {

	private static final String CRITIC_ACCOUNT = "critic";

	private int reviewCounter;

	public CriticAccountClass(String username) {
		super(username);
		this.reviewCounter = 0;
		this.type = CRITIC_ACCOUNT;
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
