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
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.on.Cso;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class AjaxGetCsoAction extends LogadoBaseAction{

	private MessageResources	messages	= null;
	private Double				preuunitari	= 0.0;
	private Double				nrecursos	= 0.0;
	private String				facturacio;
	private Double				impuesto;
	private Double				descuento;
	private boolean				igic		= false;
	private boolean				iva			= false;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		ServletOutputStream out = null;
		String result = "";

		try {
			Integer cso = InizializeCSO(request);
			String action = InizializeParams(request);
			out = response.getOutputStream();
			if (action.equals("getCso"))
				result = searchInfoAndCreateJson(cso);

		    if (action.equals("saveCso")) {
				InizializeParamsToSave(request);
				saveCso(cso);
				result = setOKJSON(this.messages.getMessage("manteniments.insert.ok"));
			}

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
	private void saveCso(Integer idCso) throws Exception{
		
		Cso cso = new Cso();
		cso.setId(idCso);
		cso.setDescuento(this.descuento);
		cso.setImpuesto(this.impuesto);
		cso.setIva(this.iva);
		cso.setIgic(this.igic);
		cso.setServicioRecurso(this.facturacio);
		cso.setPreuunitari(this.preuunitari);
		cso.setNrecursos(this.nrecursos);
		
		LNCso.saveCSOInManteniment(cso);
		
	}
	
	private String InizializeParams( HttpServletRequest request ) throws Exception{

		try {
			this.messages = getResources(request);
			return request.getParameter("action");
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}

	}

	private String InizializeParamsToSave( HttpServletRequest request ) throws Exception{

		try {
			this.messages = getResources(request);
			this.descuento = (request.getParameter("descuento") != null) ? Double.parseDouble(request.getParameter("descuento")) : 0.0;
			this.impuesto = (request.getParameter("impuesto") != null) ? Double.parseDouble(request.getParameter("impuesto")) : 0.0;
			this.iva = (request.getParameter("iva") != null) ? Boolean.parseBoolean(request.getParameter("iva")) : false;
			this.igic = (request.getParameter("igic") != null) ? Boolean.parseBoolean(request.getParameter("igic")) : false;
			this.facturacio = (request.getParameter("facturacio") != null) ? request.getParameter("facturacio") : "servei";
			if (this.facturacio.equals("recurs")) {

				this.nrecursos = (request.getParameter("nrecursos") != null) ? Double.parseDouble(request.getParameter("nrecursos")) : 0.0;
				this.preuunitari = (request.getParameter("preuunitari") != null) ? Double.parseDouble(request.getParameter("preuunitari"))
						: 0.0;

			}
			return request.getParameter("action");
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}

	}

	private Integer InizializeCSO( HttpServletRequest request ) throws Exception{

		try {
			return (request.getParameter("idCSO") != null) ? Integer.parseInt(request.getParameter("idCSO")) : null;
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}

	}

	private String searchInfoAndCreateJson( Integer idCso ) throws Exception{

		Cso cso = LNCso.getCSOFromManteniment(idCso);

		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		String json = gson.toJson(cso);

		return json;
	}

	private String setErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\": \"" + error + "\"");
		jsonSB.append("}");
		return jsonSB.toString();

	}
	
	private String setOKJSON( String ok ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"OK\": \"" + ok + "\"");
		jsonSB.append("}");
		return jsonSB.toString();

	}
}
