package Exceptions;

@SuppressWarnings("serial")
public class AlreadyReviewedShowException extends Exception {

private static final String MESSAGE = "%s has already reviewed %s!\n";
	
	public AlreadyReviewedShowException() {
		
		super(MESSAGE);
	}
}
