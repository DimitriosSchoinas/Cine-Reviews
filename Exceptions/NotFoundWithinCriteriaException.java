package Exceptions;

@SuppressWarnings("serial")
public class NotFoundWithinCriteriaException extends Exception {

private static final String MESSAGE = "No show was found within the criteria.\n";
	
	public NotFoundWithinCriteriaException() {
		
		super(MESSAGE);
	}
}
