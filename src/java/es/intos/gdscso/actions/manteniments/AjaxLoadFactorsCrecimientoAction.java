package es.intos.gdscso.actions.manteniments;

import java.util.List;

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
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.FactorCrecimiento;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class AjaxLoadFactorsCrecimientoAction extends LogadoBaseAction{

	private Integer				currentYear	= null;
	private MessageResources	messages	= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		ServletOutputStream out = null;
		String result = "";
		Integer idPartida = null;
		Integer year = null;

		try {
			out = response.getOutputStream();
			InizializeParams(request);
			idPartida = InizializeIdPartida(request);
			year = InizializeYear(request);

			result = searchInfoAndCreateJson(idPartida, year);

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
			return (request.getParameter("year") != null && !request.getParameter("year").equals("")) ? Integer.parseInt(request
					.getParameter("year")) : null;
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}

	}

	private Integer InizializeIdPartida( HttpServletRequest request ) throws Exception{

		try {
			return (request.getParameter("idpartida") != null && !request.getParameter("idpartida").equals("")) ? Integer.parseInt(request
					.getParameter("idpartida")) : null;
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}

	}

	private void InizializeCurrentYear(){

		this.currentYear = Utils.getCurrentYear();

	}

	private String searchInfoAndCreateJson( Integer idPartida, Integer year ) throws Exception{

		Integer searchYear = null;
		if (year == null)
			searchYear = this.currentYear;
		else
			searchYear = year;
		List<FactorCrecimiento> list = LNPartidas.getFactoresCrecimientoPartidas(searchYear, idPartida);
		Double importe = LNPartidas.getImportPactatPartida(searchYear, idPartida);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String json = gson.toJson(list);
		StringBuffer jsonSB = new StringBuffer("{ \"aaData\" : ");
		jsonSB.append(json);
		if (importe == null)
			importe = 0.0;
		jsonSB.append(", \"importe\" : \"" + importe + "\" }");
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
