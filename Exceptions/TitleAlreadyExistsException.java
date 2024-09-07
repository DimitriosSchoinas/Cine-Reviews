package Exceptions;

@SuppressWarnings("serial")
public class TitleAlreadyExistsException extends Exception {

private static final String MESSAGE = "Show %s already exists!\n";
	
	public TitleAlreadyExistsException() {
		
		super(MESSAGE);
	}
}
