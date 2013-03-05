/*
 * FieldChecks.java
 *
 * Created on 11 de marzo de 2009, 12:11
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package es.intos.gdscso.validator;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.*;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

/**
 * 
 * @author t702223
 */
@SuppressWarnings({ "serial" })
public class FieldChecks implements Serializable{

	/** Creates a new instance of Validator */
	public FieldChecks() {

	}// end constructor

	public static boolean validateTwoEqualFields( Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request ){

		String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		String sProperty2 = field.getVarValue("secondProperty");
		String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				if (!value.equals(value2)) {
					errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));

					return false;
				}
			} catch (Exception e) {
				errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
				return false;
			}
		}

		return true;
	}// end validateTwoFields

}// end Validator