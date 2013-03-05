package es.intos.gdscso.exceptions;

@SuppressWarnings({ "serial" })
public class CsoPDFException extends RuntimeException{

	public CsoPDFException() {

	}

	public CsoPDFException( Exception e ) {

		super(e);
	}

	public CsoPDFException( String aa ) {

		super(aa);
	}
}
