/*
 * NifCaixa.java
 *
 * Created on 9 de desembre de 2010
 *
 */

package es.intos.gdscso.validator;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.*;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * @author t702223
 */
@SuppressWarnings({ "serial" })
public class NifCaixa implements Serializable{

	/** Creates a new instance of Validator */
	public NifCaixa() {

	}// end constructor

	public static boolean validateNifCaixa( Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request ){

		// por ahora no se implementa la parte java de validacion de nif

		return true;
	}// end validateTwoFields

}// end Validator
