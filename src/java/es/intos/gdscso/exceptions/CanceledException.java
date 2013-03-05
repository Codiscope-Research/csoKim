package es.intos.gdscso.exceptions;

@SuppressWarnings({ "serial" })
public class CanceledException extends RuntimeException{

	public CanceledException() {

	}

	public CanceledException( Exception e ) {

		super(e);
	}

	public CanceledException( String aa ) {

		super(aa);
	}
}
