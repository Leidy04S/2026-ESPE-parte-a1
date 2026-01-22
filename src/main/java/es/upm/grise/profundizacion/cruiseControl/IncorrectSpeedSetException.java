package es.upm.grise.profundizacion.cruiseControl;

public class IncorrectSpeedSetException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public IncorrectSpeedSetException() {
		super("SpeedSet must be a positive value (strictly greater than zero)");
	}
	
	public IncorrectSpeedSetException(String message) {
		super(message);
	}
	
}
