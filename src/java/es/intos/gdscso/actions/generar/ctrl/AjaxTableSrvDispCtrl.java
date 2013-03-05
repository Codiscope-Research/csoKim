/*
 * AjaxCausisticaTipologiaAction.java
 *
 * Created on 22 de desembre de 2010
 */

package es.intos.gdscso.actions.generar.ctrl;

import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNVolum;
import es.intos.gdscso.on.SrvDispTable;
import es.intos.util.Usuario;

public class AjaxTableSrvDispCtrl{

	private Locale		locale			= null;
	private int			lenght			= 0;
	private int			inici			= 0;
	private int			columna			= 0;
	private String		echo			= null;
	private String[]	order2			= new String[] { "sr.DESCRIP", "srvfac.IDFACTURA", "est.DESCRIPCIO" };
	private String		sortDireccio	= null;
	private String		code			= null;
	private Integer		idcso			= null;
	private Integer		idany			= null;
	private Integer		idmes			= null;

	public String ctrl( HttpServletRequest request, HttpServletResponse response, Usuario user, Locale locale ) throws Exception,
			ErrorParamsException{

		String result = "";
		this.locale = locale;
		inizializeParams(request);

		if (this.idcso == null || this.idcso == 0) {

			result = createEmptyJson();

		} else {

			result = searchInfoAndcreateJson();

		}
		return result;
	}

	// FUNCTIONS
	private void inizializeParams( HttpServletRequest request ) throws ErrorParamsException{

		try {

			if (this.locale.getLanguage().equals("CA")) {
				this.order2 = new String[] { "sr.DESCRIP", "srvfac.IDFACTURA", "est.DESCRIPCIO_CA" };
			}
			this.idcso = (request.getParameter("idcso") != null) ? Integer.parseInt(request.getParameter("idcso")) : null;
			this.code = (request.getParameter("code") != null) ? request.getParameter("code") : null;
			this.idany = (request.getParameter("idany") != null && !request.getParameter("idany").equals("")) ? Integer.parseInt(request
					.getParameter("idany")) : null;
			this.idmes = (request.getParameter("idmes") != null && !request.getParameter("idmes").equals("")) ? Integer.parseInt(request
					.getParameter("idmes")) : null;
			this.echo = request.getParameter("sEcho");
			this.lenght = (request.getParameter("iDisplayLength") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayLength"));
			this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
			this.sortDireccio = request.getParameter("sSortDir_0");
			if (this.sortDireccio == null)
				this.sortDireccio = "ASC";
			this.columna = 0;
			if (request.getParameter("iSortCol_0") != null)
				this.columna = Integer.parseInt(request.getParameter("iSortCol_0"));
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}
		if (this.idcso == null) {
			throw new ErrorParamsException();
		}
	}

	private String createEmptyJson(){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\": []} ");
		return jsonSB.toString();
	}

	private String searchInfoAndcreateJson() throws Exception{

		StringBuffer jsonSB = new StringBuffer("{");
		if (this.code != null && !this.code.equals("")) {// quan hem seleccionat
															// una factura
															// homonima
			Vector<SrvDispTable> srvsT = LNVolum.getSrvCsoForHomonima(this.code, this.idcso, this.idany, this.idmes,
					this.locale.getLanguage(), this.inici, this.lenght, this.order2[this.columna] + " " + this.sortDireccio);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
			String json = "";

			if (!srvsT.isEmpty() && srvsT != null && srvsT.lastElement() == null) {
				srvsT.remove(srvsT.size() - 1);
				json = gson.toJson(srvsT);
				jsonSB.append("\"sEcho\": " + echo + ",\"error\": \"yes\", \"iTotalRecords\":\"" + (srvsT.size() - 1)
						+ "\", \"iTotalDisplayRecords\":\"" + (srvsT.size() - 1) + "\", \"aaData\":  ");
			} else {
				json = gson.toJson(srvsT);
				jsonSB.append("\"sEcho\": " + echo + ",\"error\": \"no\", \"iTotalRecords\":\"" + srvsT.size()
						+ "\", \"iTotalDisplayRecords\":\"" + srvsT.size() + "\", \"aaData\":  ");
			}
			jsonSB.append(json);
			jsonSB.append("}");
		} else {
			Vector<SrvDispTable> srvsT = LNVolum.getSrvCso(this.idcso, this.idany, this.idmes, this.locale.getLanguage(), this.inici,
					this.lenght, this.order2[this.columna] + " " + this.sortDireccio);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
			String json = gson.toJson(srvsT);

			jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"" + srvsT.size() + "\", \"iTotalDisplayRecords\":\"" + srvsT.size()
					+ "\", \"aaData\":  ");
			jsonSB.append(json);
			jsonSB.append("}");
		}
		return jsonSB.toString();
	}

}
