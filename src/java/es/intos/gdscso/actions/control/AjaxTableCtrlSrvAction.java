package es.intos.gdscso.actions.control;

import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

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
import es.intos.gdscso.ln.LNVolum;
import es.intos.gdscso.on.SrvCSOTable;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class AjaxTableCtrlSrvAction extends LogadoBaseAction{

	private String				echo			= null;
	private Integer				idcso			= null;
	private Integer				idany			= null;
	private Integer				idmes			= null;
	private Locale				locale			= null;
	private int					columna			= 0;
	private String				sortDireccio	= null;
	private String[]			order			= new String[] { "srv.DESCRIP", "fac.ID", "srvol.VOLUM", "srvol.importe", "est.descripcio" };
	private MessageResources	messages		= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		ServletOutputStream out = null;
		String result = "";
		try {

			out = response.getOutputStream();
			inizializeParams(request);

			// Agafem tots els serveis d'aquell mes i any amb la factura
			// corresponent.
			if (idany == null) {
				result = createEmptyJson();
			} else {

				result = createJson(request);
			}
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
	private void inizializeParams( HttpServletRequest request ) throws ErrorParamsException{

		try {
			this.locale = getLocale(request);
			this.messages = getResources(request);

			// Params del filtre
			this.idany = (request.getParameter("idany") != null && !request.getParameter("idany").equals("")) ? Integer.parseInt(request
					.getParameter("idany")) : null;
			this.idcso = (request.getParameter("idcso") != null && !request.getParameter("idcso").equals("")) ? Integer.parseInt(request
					.getParameter("idcso")) : null;
			this.idmes = (request.getParameter("idmes") != null && !request.getParameter("idmes").equals("")) ? Integer.parseInt(request
					.getParameter("idmes")) : Calendar.getInstance().get(Calendar.MONTH);
			// Params de datatables
			this.echo = request.getParameter("sEcho");
			this.sortDireccio = request.getParameter("sSortDir_0");
			if (this.sortDireccio == null)
				this.sortDireccio = "ASC";
			if (request.getParameter("iSortCol_0") != null)
				this.columna = Integer.parseInt(request.getParameter("iSortCol_0"));
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}
	}

	private String createEmptyJson(){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\": []} ");
		return jsonSB.toString();
	}

	private String createJson(HttpServletRequest request) throws Exception{

		String context = request.getSession().getServletContext().getInitParameter("context");
		Vector<SrvCSOTable> srvsT = LNVolum.getSrvWithVol(idcso, idany, idmes, locale.getLanguage(), order[this.columna] + " "
				+ this.sortDireccio, context);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String json = gson.toJson(srvsT);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"" + srvsT.size() + "\", \"iTotalDisplayRecords\":\"" + srvsT.size()
				+ "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
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
