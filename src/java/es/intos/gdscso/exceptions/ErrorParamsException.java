package es.intos.gdscso.exceptions;

@SuppressWarnings({ "serial" })
public class ErrorParamsException extends RuntimeException{

	public ErrorParamsException() {

	}

	public ErrorParamsException( Exception e ) {

		super(e);
	}

	public ErrorParamsException( String aa ) {

		super(aa);
	}
}
