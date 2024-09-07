package Exceptions;

@SuppressWarnings("serial")
public class NotAnAdminException extends Exception {

private static final String MESSAGE = "Admin %s does not exist!\n";
	
	public NotAnAdminException() {
		
		super(MESSAGE);
	}
}
