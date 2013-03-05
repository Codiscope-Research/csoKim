/*
 * AjaxCausisticaTipologiaAction.java
 *
 * Created on 22 de desembre de 2010
 */

package es.intos.gdscso.actions.manteniments;

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
import es.intos.gdscso.actions.manteniments.ctrl.AjaxLoadTableSrvDispCtrl;
import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class AjaxLoadTableSrvDispAction extends LogadoBaseAction{

	private Locale				locale		= null;
	private MessageResources	messages	= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		AjaxLoadTableSrvDispCtrl ctrl = new AjaxLoadTableSrvDispCtrl();
		ServletOutputStream out = null;
		String result = "";

		try {

			out = response.getOutputStream();
			inizializeParams(request, user);
			result = ctrl.ctrl(request, response, user, locale);

		} catch (ErrorParamsException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("parametros.incorrectos"));
			addErrors(request, errors);
			result = setErrorJSON(this.messages.getMessage("error.ajax.params"));
			log.error(es.intos.exception.IntosException.getStackTrace(ne));
			return null;
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

	// FUNCTIONS
	private void inizializeParams( HttpServletRequest request, Usuario user ) throws Exception{

		this.messages = getResources(request);
		this.locale = getLocale(request);

	}

	private String setErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\": " + error + ", \"sEcho\": " + 0 + ", \"iTotalRecords\":\"" + 5 + "\", \"iTotalDisplayRecords\":\"" + 5
				+ "\", \"aaData\": [ ");
		jsonSB.append("]}");
		return jsonSB.toString();

	}
}
