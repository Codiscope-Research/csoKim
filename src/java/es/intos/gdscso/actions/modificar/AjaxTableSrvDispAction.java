/*
 * AjaxCausisticaTipologiaAction.java
 *
 * Created on 22 de desembre de 2010
 */

package es.intos.gdscso.actions.modificar;

import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.actions.modificar.ctrl.AjaxTableSrvDispCtrl;
import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class AjaxTableSrvDispAction extends LogadoBaseAction{

	private Locale				locale		= null;
	private MessageResources	messages	= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		ServletOutputStream out = null;
		String result = "";
		AjaxTableSrvDispCtrl ctrl = new AjaxTableSrvDispCtrl();

		try {
			out = response.getOutputStream();
			inizializeParams(request);
			result = ctrl.ctrl(request, response, user, locale);

		} catch (ErrorParamsException nfe) {

			result = setErrorJSON(this.messages.getMessage("error.ajax.params"));
			log.error(es.intos.exception.IntosException.getStackTrace(nfe));

		} catch (Exception e) {

			result = setErrorJSON(this.messages.getMessage("error.ajax"));
			log.error(es.intos.exception.IntosException.getStackTrace(e));
		}

		if (null != out) {
			if (!Utils.isValidJSON(result)) {
				ActionMessages errors = new ActionMessages();
				errors.add("error", new ActionMessage("JSON.erroni"));
				addErrors(request, errors);
				result = setErrorJSON(this.messages.getMessage("error.ajax.json.notvalid"));
			}
			log.info(result);
			out.print(result);
		}
		return null;
	}

	private void inizializeParams( HttpServletRequest request ) throws Exception{

		try {

			this.locale = getLocale(request);
			this.messages = getResources(request);

		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}
	}

	private String setErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\": " + error + ", \"sEcho\": " + 0 + ", \"iTotalRecords\":\"" + 5 + "\", \"iTotalDisplayRecords\":\"" + 5
				+ "\", \"aaData\": [ ");
		jsonSB.append("]}");
		return jsonSB.toString();

	}
}
