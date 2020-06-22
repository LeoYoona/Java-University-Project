package nl.inholland;


public class UnauthorizedException  extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UnauthorizedException(String s) {
		super(s);
	}
}