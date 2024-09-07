package Exceptions;

@SuppressWarnings("serial")
public class NoArtistsException extends Exception{
	
	private static final String MESSAGE = "No artists yet!\n";

	public NoArtistsException() {
		super(MESSAGE);
	}
}
