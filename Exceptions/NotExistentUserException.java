package Exceptions;

@SuppressWarnings("serial")
public class NotExistentUserException extends Exception{
	
private static final String MESSAGE = "User %s does not exist!\n";
	
	public NotExistentUserException() {
		super(MESSAGE);
	}
}
