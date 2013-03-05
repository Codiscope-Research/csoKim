/*
 * AjaxCausisticaTipologiaAction.java
 *
 * Created on 22 de desembre de 2010
 */

package es.intos.gdscso.actions.modificar.ctrl;

import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.ln.LNVolum;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.on.SrvDispTable;
import es.intos.util.Usuario;

public class AjaxTableSrvDispCtrl{

	private Locale				locale			= null;
	private int					lenght			= 0;
	private int					inici			= 0;
	private int					columna			= 0;
	private String				echo			= null;
	private String[]			order2			= new String[] { "sr.DESCRIP", "srvfac.IDFACTURA", "est.DESCRIPCIO" };
	private String				sortDireccio	= null;
	private Integer				idFactura		= null;


	public String ctrl( HttpServletRequest request,
			HttpServletResponse response, Usuario user, Locale locale) throws Exception,ErrorParamsException{

			String result = "";
			this.locale = locale;
			inizializeParams(request);			
			Factura factura = getFactura(this.idFactura);
			if (factura == null || factura.getIdCSO() == null || factura.getIdCSO().toString().equals("0")) {
				result = createEmptyJson();
			} else {
				result = searchInfoAndcreateJson(factura);
			}

			return result;
	}

	private void inizializeParams( HttpServletRequest request ) throws ErrorParamsException{

		try {
			
			if (locale.getLanguage().equals("CA")) {
				order2 = new String[] { "sr.DESCRIP", "srvfac.IDFACTURA", "est.DESCRIPCIO_CA" };
			}
			this.idFactura = (request.getParameter("idfactura") != null && !request.getParameter("idfactura").equals("")) ? Integer
					.parseInt(request.getParameter("idfactura")) : null;
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
	}

	private String createEmptyJson(){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\": []} ");
		return jsonSB.toString();
	}

	private Factura getFactura( Integer idFactura ) throws Exception{

		Vector<Factura> facturaVector = LNFacturas.getFacturas(idFactura, null, null, null, null);
		Factura factura = null;
		if (!facturaVector.isEmpty()) {
			factura = facturaVector.get(0);
		}
		return factura;
	}

	private String searchInfoAndcreateJson( Factura factura ) throws Exception{

		Vector<SrvDispTable> srvsT = LNVolum.getSrvCsoModif(factura.getId(), factura.getIdCSO(), factura.getYear(), factura.getMonth(),
				locale.getLanguage(), this.inici, this.lenght, this.order2[this.columna] + " " + this.sortDireccio);
		int totalRegistres = LNVolum.getSrvCsoCount(factura.getIdCSO(), factura.getYear(), factura.getMonth(), locale.getLanguage(),
				this.inici, this.lenght, this.order2[this.columna] + " " + this.sortDireccio);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(srvsT);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"" + totalRegistres + "\", \"iTotalDisplayRecords\":\"" + totalRegistres
				+ "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();
	}	
}
