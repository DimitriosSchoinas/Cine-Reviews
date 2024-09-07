package Exceptions;

@SuppressWarnings("serial")
public class TitleNotAssociatedWithShowException extends Exception {

private static final String MESSAGE = "Show %s does not exist!\n";
	
	public TitleNotAssociatedWithShowException() {
		
		super(MESSAGE);
	}
}
