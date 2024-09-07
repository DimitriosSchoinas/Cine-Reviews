package cinereviews;

import java.util.ArrayList;
import java.util.List;

public class AdminAccountClass extends AbstractUserClass implements AdminAccount {

	protected static final String ADMIN_ACCOUNT = "admin";

	private String password;
	List<Show> shows;
	List<Artist> artists;

	public AdminAccountClass(String username, String password) {
		super(username);
		this.password = password;
		shows = new ArrayList<Show>();
		artists = new ArrayList<Artist>();
		this.type = ADMIN_ACCOUNT;
	}

	@Override
	public int getAmountOfReviewsOrShows() {
		return shows.size();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void addShow(Show s) {
		shows.add(s);
	}
}
