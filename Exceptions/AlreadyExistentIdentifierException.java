package Exceptions;

@SuppressWarnings("serial")
public class AlreadyExistentIdentifierException extends Exception {

	private static final String MESSAGE = "User %s already exists!\n";
	
	public AlreadyExistentIdentifierException() {
		
		super(MESSAGE);
	}
	}
