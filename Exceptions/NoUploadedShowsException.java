package Exceptions;

@SuppressWarnings("serial")
public class NoUploadedShowsException extends Exception {

private static final String MESSAGE = "No shows have been uploaded.\n";
	
	public NoUploadedShowsException() {
		
		super(MESSAGE);
	}
}
