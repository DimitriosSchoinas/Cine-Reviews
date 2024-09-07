package Exceptions;

@SuppressWarnings("serial")
public class NoUsersRegisteredException extends Exception {

private static final String MESSAGE = "No users registered.\n";
	
	public NoUsersRegisteredException() {
		
		super(MESSAGE);
	}
}
