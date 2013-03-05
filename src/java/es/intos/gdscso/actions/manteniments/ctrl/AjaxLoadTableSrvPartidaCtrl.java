package es.intos.gdscso.actions.manteniments.ctrl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Servicio;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class AjaxLoadTableSrvPartidaCtrl{

	private String			echo	= null;
	private Integer			partida	= null;
	private Integer			lenght	= null;
	private Integer			inici	= null;
	private String			sortDir	= null;
	private Locale			locale	= null;
	private StringBuffer	jsonSB	= new StringBuffer("{");

	public String ctrl( HttpServletRequest request, HttpServletResponse response, Usuario user ) throws Exception, ErrorParamsException{

		inizializeParams(request, user);
		return getPartidesAndCreateJSON();

	}

	// FUNCTIONS
	private void inizializeParams( HttpServletRequest request, Usuario user ) throws ErrorParamsException{

		try {
			this.echo = request.getParameter("sEcho");
			this.partida = (request.getParameter("idpartida") != null && !request.getParameter("idpartida").equals("") && !request
					.getParameter("idpartida").equals("null")) ? Integer.parseInt(request.getParameter("idpartida")) : null;
			this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
			this.lenght = (request.getParameter("iDisplayLength") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayLength"));
			this.sortDir = (request.getParameter("sSortDir_0") == null || request.getParameter("sSortDir_0").equals("")) ? "ASC" : request
					.getParameter("sSortDir_0");
			this.jsonSB = new StringBuffer("{");
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}
	}

	private String getPartidesAndCreateJSON() throws Exception{

		if (partida != null && !partida.equals("") && !partida.equals("null")) {
			List<Servicio> listSrv = LNPartidas.getAllSrv(locale, true, partida, inici, lenght, sortDir);
			int rows = LNPartidas.getAllSrvCount(locale, true, partida);
			jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"" + rows + "\", \"iTotalDisplayRecords\":\"" + rows
					+ "\", \"aaData\": [ ");
			boolean registres = false;
			for (Servicio srv : listSrv) {
				jsonSB.append("[\"<a href='#' name='" + srv.getDescripcio() + "' id='" + srv.getId() + "' >"
						+ Utils.changeEncode(srv.getDescripcio()) + "</a>\"],");
				registres = true;
			}
			if (registres) {
				jsonSB.setLength(jsonSB.length() - 1);
			}
		} else {
			jsonSB.append("\"sEcho\": " + echo + ", \"iTotalRecords\":\"" + 0 + "\", \"iTotalDisplayRecords\":\"" + 0
					+ "\", \"aaData\": [ ");
		}
		jsonSB.append("]}");
		return jsonSB.toString();
	}
}
