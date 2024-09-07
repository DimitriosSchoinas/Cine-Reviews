package Exceptions;

@SuppressWarnings("serial")
public class WrongPasswordException extends Exception {

private static final String MESSAGE = "Invalid authentication!\n";
	
	public WrongPasswordException() {
		
		super(MESSAGE);
	}
}
