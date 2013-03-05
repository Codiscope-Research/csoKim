package es.intos.gdscso.actions.facturacion.ctrl;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.FacturacioDetallePartida;
import es.intos.util.Usuario;

public class DetallePartidaCtrl{

	private StringBuffer	json		= null;
	private Vector<Basic>	years		= new Vector<Basic>(1, 1);
	private Integer			idPartida	= null;

	public StringBuffer ctrl( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response,
			Usuario user, Vector<Basic> years ) throws Exception{

		this.years = years;
		inizializeParams(request);
		createJsonForChart();

		return this.json;
	}

	// FUNCTIONS

	private void inizializeParams( HttpServletRequest request ){

		this.idPartida = (request.getParameter("id") != null && !request.getParameter("id").equals("") && !request.getParameter("id")
				.equals("null")) ? Integer.parseInt(request.getParameter("id")) : null;
	}

	private void createJsonForChart() throws Exception{

		this.json = new StringBuffer("");
		List<FacturacioDetallePartida> facturacioDetallePartidaList = LNPartidas.getInfoOfFacturacionPartida(this.years, this.idPartida);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String json = gson.toJson(facturacioDetallePartidaList);
		this.json.append(json);

	}
}
