package es.intos.gdscso.actions.facturacion.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.DetallPartidaTable;
import es.intos.util.Usuario;

public class AjaxTableDetallPartidaCtrl{

	private String		echo			= null;
	private Integer		yearOfConsult	= null;
	private String[]	order2			= new String[] { "srv.descrip", "sumENE.importe", "sumFEB.importe", "sumMAR.importe",
			"sumABR.importe", "sumMAI.importe", "sumJUN.importe", "sumJUL.importe", "sumAGO.importe", "sumSET.importe", "sumOCT.importe",
			"sumNOV.importe", "sumDES.importe" };
	private int			lenght			= 0;
	private int			inici			= 0;
	private int			columna			= 0;
	private String		sortDireccio	= null;
	private Integer		idPartida		= null;

	public String ctrl( HttpServletRequest request, HttpServletResponse response, Usuario user ) throws Exception, ErrorParamsException{

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
	private void inizializeParams( HttpServletRequest request ) throws ErrorParamsException{

		try {

			// Params del filtre
			this.yearOfConsult = (request.getParameter("idany") != null) ? Integer.parseInt(request.getParameter("idany")) : null;
			this.idPartida = (request.getParameter("idpartida") != null && !request.getParameter("idpartida").equals("")) ? Integer
					.parseInt(request.getParameter("idpartida")) : null;
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

		List<DetallPartidaTable> facturacioPartidasFirstTableList = LNPartidas.getInfoTableDetallPartida(this.yearOfConsult,
				this.idPartida, this.inici, this.lenght, this.order2[this.columna] + " " + this.sortDireccio);
		int numsrv = LNPartidas.getNumSrvPartides(this.yearOfConsult, this.idPartida);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(facturacioPartidasFirstTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"" + numsrv + "\", \"iTotalDisplayRecords\":\"" + numsrv
				+ "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();
	}
}
