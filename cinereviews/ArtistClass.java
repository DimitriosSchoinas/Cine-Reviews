package cinereviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArtistClass implements Artist, Comparable<ArtistClass> {

	private String artistName;
	private String artistDateOfBirth;
	private String artistPlaceOfBirth;
	private List<Show> showsThatParticipated;

	public ArtistClass(String artistName, String artistDateOfBirth, String artistPlaceOfBirth) {
		this.artistName = artistName;
		this.artistDateOfBirth = artistDateOfBirth;
		this.artistPlaceOfBirth = artistPlaceOfBirth;
		showsThatParticipated = new ArrayList<Show>();
	}

	public ArtistClass(String artistName) {
		this.artistName = artistName;
		this.artistDateOfBirth = null;
		this.artistPlaceOfBirth = null;
		showsThatParticipated = new ArrayList<Show>();
	}

	@Override
	public String getName() {
		return this.artistName;
	}

	@Override
	public boolean hasBio() {
		return this.artistDateOfBirth != null && this.artistPlaceOfBirth != null;
	}

	@Override
	public void updateBio(String dateOfBirth, String placeOfBirth) {
		this.artistDateOfBirth = dateOfBirth;
		this.artistPlaceOfBirth = placeOfBirth;
	}

	@Override
	public String getBirthDate() {
		return this.artistDateOfBirth;
	}

	@Override
	public String getBirthPlace() {
		return this.artistPlaceOfBirth;
	}

	@Override
	public void addShowParticipated(Show s) {
		this.showsThatParticipated.add(s);
	}

	@Override
	public Iterator<Show> listShowsParticipated() {
		Collections.sort(showsThatParticipated);
		Collections.sort(showsThatParticipated, new ComparatorByReleaseDate());
		return showsThatParticipated.iterator();
	}

	@Override
	public boolean hasParticipatedInShow() {
		return showsThatParticipated.size() > 0;
	}

	@Override
	public int getNumberOfParticipatedShows() {

		return showsThatParticipated.size();
	}

	@Override
	public int compareTo(Artist next) {
		return artistName.compareTo(next.getName());

	}

	@Override
	public int compareTo(ArtistClass o) {
		return this.artistName.compareTo(o.getName());
	}

}
