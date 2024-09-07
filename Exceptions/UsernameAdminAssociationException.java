package Exceptions;

@SuppressWarnings("serial")
public class UsernameAdminAssociationException extends Exception {

private static final String MESSAGE = "Admin %s cannot review shows!\n";
	
	public UsernameAdminAssociationException() {
		
		super(MESSAGE);
	}
}
