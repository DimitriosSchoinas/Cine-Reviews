package Exceptions;

@SuppressWarnings("serial")
public class SmallWorldException extends Exception {

private static final String MESSAGE = "It is a small world!\n";
	
	public SmallWorldException() {
		
		super(MESSAGE);
	}
}
