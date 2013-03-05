package es.intos.gdscso.actions.partes;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.FacturaDialogNC;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

//Retorna info per el dialog d'info de partes de no conformidades
public class AjaxCargaInfoNoConfirmadosAction extends LogadoBaseAction{

	private Locale				locale		= null;
	private MessageResources	messages	= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		ServletOutputStream out = null;
		Integer idFactura = null;
		String result = "";
		try {

			out = response.getOutputStream();
			inziallize(request);
			idFactura = inziallizeIdFactura(request);
			result = searchInfoAndCreateJSON(idFactura);

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

	// FUNCTIONS
	private void inziallize( HttpServletRequest request ) throws Exception{

		this.locale = getLocale(request);
		this.messages = getResources(request);

	}

	private Integer inziallizeIdFactura( HttpServletRequest request ) throws ErrorParamsException{

		try {
			return request.getParameter("idfactura") != null ? Integer.parseInt(request.getParameter("idfactura")) : null;
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}
	}

	private String searchInfoAndCreateJSON( Integer idFactura ) throws Exception{

		FacturaDialogNC factInfoNC = LNFacturas.getInfoFacturaNoConformidades(idFactura, locale.getLanguage());
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String json = gson.toJson(factInfoNC);
		return json;

	}

	private String setErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\": " + error + ", \"sEcho\": " + 0 + ", \"iTotalRecords\":\"" + 5 + "\", \"iTotalDisplayRecords\":\"" + 5
				+ "\", \"aaData\": [ ");
		jsonSB.append("]}");
		return jsonSB.toString();

	}
}
