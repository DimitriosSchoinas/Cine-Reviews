package Exceptions;

@SuppressWarnings("serial")
public class NoCollaborationsException extends Exception {

private static final String MESSAGE = "No collaborations yet!\n";
	
	public NoCollaborationsException() {
		
		super(MESSAGE);
	}
}
