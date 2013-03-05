package es.intos.gdscso.actions.facturacion.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.FacturacioPartidasFirstTable;
import es.intos.gdscso.utils.Constants;
import es.intos.util.Usuario;

public class AjaxTablePartidesCtrl{

	private String		echo			= null;
	private Integer		yearOfConsult	= null;
	private String[]	order2			= new String[] { "pt.nomlinea", Constants.mesos[Constants.MES_GENER],
			Constants.mesos[Constants.MES_FEBRER], Constants.mesos[Constants.MES_MARC], Constants.mesos[Constants.MES_ABRIL],
			Constants.mesos[Constants.MES_MAIG], Constants.mesos[Constants.MES_JUNY], Constants.mesos[Constants.MES_JULIOL],
			Constants.mesos[Constants.MES_AGOST], Constants.mesos[Constants.MES_SETEMBRE], Constants.mesos[Constants.MES_OCTUBRE],
			Constants.mesos[Constants.MES_NOVEMBRE], Constants.mesos[Constants.MES_DESEMBRE] };
	private int			lenght			= 0;
	private int			inici			= 0;
	private int			columna			= 0;
	private String		sortDireccio	= null;

	public String ctrl( HttpServletRequest request, HttpServletResponse response, Usuario user ) throws Exception, NumberFormatException{

		String result = "";
		inizializeParams(request);

		if (this.yearOfConsult == null || this.yearOfConsult.equals("0")) {
			result = createEmptyJson();
		} else {
			result = searchInfoAndcreateJson();
		}
		return result;
	}

	// FUNCTIONS
	private void inizializeParams( HttpServletRequest request ) throws NumberFormatException{

		try {
			// Params del filtre
			this.yearOfConsult = (request.getParameter("idany") == null || request.getParameter("idany").equals("")) ? null : Integer
					.parseInt(request.getParameter("idany"));
			// Params de datatables
			this.echo = request.getParameter("sEcho");
			this.lenght = (request.getParameter("iDisplayLength") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayLength"));
			this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
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

	private String searchInfoAndcreateJson() throws Exception{

		List<FacturacioPartidasFirstTable> facturacioPartidasFirstTableList = LNPartidas.getInfoTableOfFacturacionPartidas(
				this.yearOfConsult, this.inici, this.lenght, this.order2[this.columna] + " " + this.sortDireccio);
		int numPartides = LNPartidas.getNumPartides();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(facturacioPartidasFirstTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"" + numPartides + "\", \"iTotalDisplayRecords\":\"" + numPartides
				+ "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();
	}
}
