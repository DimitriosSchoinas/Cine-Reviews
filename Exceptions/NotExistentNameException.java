package Exceptions;

@SuppressWarnings("serial")
public class NotExistentNameException extends Exception {

	private static final String MESSAGE = "No information about %s!\n";
	
	public NotExistentNameException() {
		super(MESSAGE);
	}
}
