package es.intos.gdscso.actions.generar.ctrl;

import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.ln.LNVolum;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.CsoSrvTable;
import es.intos.util.Usuario;

public class AjaxTableCsoSrvCtrl{

	private Locale		locale			= null;
	private String[]	order2			= new String[] { "cso.name", "TSRV", "TFAC" };
	private String		echo			= null;
	private Integer		year			= null;
	private Integer		month			= null;
	private int			lenght			= 20;
	private int			inici			= 0;
	private String		sortDireccio	= "ASC";
	private int			column			= 0;

	public String ctrl( HttpServletRequest request, HttpServletResponse response, Usuario user, Locale locale ) throws Exception,
			ErrorParamsException{

		this.locale = locale;
		String result = "";
		inizialize(request);
		if (this.month == 0 && this.year == 0) {
			result = getEmptyJSON();
		} else {
			result = searchInfoAndgetJSON(request);
		}

		return result;
	}

	// FUNCTIONS
	private void inizialize( HttpServletRequest request ) throws ErrorParamsException{

		try {
			// Params del filtre
			this.echo = request.getParameter("sEcho");
			this.year = request.getParameter("idany") == null || request.getParameter("idany").equals("") ? 0 : Integer.parseInt(request
					.getParameter("idany"));
			this.month = request.getParameter("idmes") == null || request.getParameter("idmes").equals("") ? 0 : Integer.parseInt(request
					.getParameter("idmes"));

			// Params de datatables
			this.lenght = (request.getParameter("iDisplayLength") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayLength"));
			this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
			this.sortDireccio = request.getParameter("sSortDir_0") == null || request.getParameter("sSortDir_0").equals("") ? "ASC"
					: request.getParameter("sSortDir_0");
			this.column = request.getParameter("iSortCol_0") == null || request.getParameter("iSortCol_0").equals("") ? 0 : Integer
					.parseInt(request.getParameter("iSortCol_0"));
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}

	}

	private String getEmptyJSON(){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\": []} ");
		return jsonSB.toString();
	}

	private String searchInfoAndgetJSON(HttpServletRequest request) throws Exception{

		Vector<Basic> csos = LNCso.getCSOs();
		String context = request.getSession().getServletContext().getInitParameter("context");
		Vector<CsoSrvTable> srvsT = LNVolum.getSrvCsoFacts(this.year, this.month, this.locale.getLanguage(), this.inici, this.lenght,
				this.order2[this.column] + " " + this.sortDireccio, context);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(srvsT);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + this.echo + ", \"iTotalRecords\":\"" + csos.size() + "\", \"iTotalDisplayRecords\":\"" + csos.size()
				+ "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();
	}
}