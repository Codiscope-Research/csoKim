package es.intos.gdscso.actions.manteniments;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNImportsPactats;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class AjaxGetImporteAction extends LogadoBaseAction{

	private Integer				currentYear	= null;
	private MessageResources	messages	= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		ServletOutputStream out = null;
		String result = "";

		try {
			Integer year = InizializeYear(request);
			InizializeParams(request);
			out = response.getOutputStream();
			result = searchInfoAndCreateJson(year);

		} catch (ErrorParamsException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("parametros.incorrectos"));
			addErrors(request, errors);

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
	private void InizializeParams( HttpServletRequest request ) throws Exception{

		try {
			this.messages = getResources(request);
			InizializeCurrentYear();
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}

	}

	private Integer InizializeYear( HttpServletRequest request ) throws Exception{

		try {
			return (request.getParameter("year") != null) ? Integer.parseInt(request.getParameter("year")) : null;
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}

	}

	private void InizializeCurrentYear(){

		this.currentYear = Utils.getCurrentYear();

	}

	private String searchInfoAndCreateJson( Integer year ) throws Exception{

		Integer searchYear = null;
		if (year == null)
			searchYear = this.currentYear;
		else
			searchYear = year;
		Basic basic = LNImportsPactats.getImportPactat(searchYear);
		if (basic == null)
			basic = new Basic(0, "0.0");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(basic);
		StringBuffer jsonSB = new StringBuffer("");
		jsonSB.append(json);
		jsonSB.append("");
		return jsonSB.toString();
	}

	private String setErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\": " + error + ", \"sEcho\": " + 0 + ", \"iTotalRecords\":\"" + 5 + "\", \"iTotalDisplayRecords\":\"" + 5
				+ "\", \"aaData\": [ ");
		jsonSB.append("]}");
		return jsonSB.toString();

	}
}
