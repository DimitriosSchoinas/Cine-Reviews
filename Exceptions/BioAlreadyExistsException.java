package Exceptions;

@SuppressWarnings("serial")
public class BioAlreadyExistsException extends Exception {

private static final String MESSAGE = "Bio of %s is already available!\n";
	
	public BioAlreadyExistsException() {
		
		super(MESSAGE);
	}
}
