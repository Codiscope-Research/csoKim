package es.intos.gdscso.actions.generar.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.FactorCrecimientoFactura;
import es.intos.gdscso.utils.Constants;
import es.intos.util.Usuario;

public class AjaxLoadFactorsFacturaCtrl{

	private String	codefactura	= null;

	public String ctrl( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Usuario user )
			throws Exception{

		String result = "";
		InizializeParams(request);
		if (this.codefactura != null)
			result = searchInfoAndCreateJson();
		else
			result = CreateEmptyJson();

		return result;
	}

	// FUNCTIONS
	private void InizializeParams( HttpServletRequest request ) throws Exception{

		this.codefactura = (request.getParameter("codefactura") != null && !request.getParameter("codefactura").equals("")) ? request
				.getParameter("codefactura") : null;

	}

	private String searchInfoAndCreateJson() throws Exception{

		List<FactorCrecimientoFactura> list = LNFacturas.getFactoresCrecimientoFacturas(this.codefactura);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String json = gson.toJson(list);
		StringBuffer jsonSB = new StringBuffer("{ \"aaData\" : ");
		jsonSB.append(json);
		jsonSB.append(" }");
		return jsonSB.toString();
	}

	private String CreateEmptyJson() throws Exception{

		List<FactorCrecimientoFactura> list = new ArrayList<FactorCrecimientoFactura>();
		int numMonth = 0;
		for (numMonth = 0; numMonth < Constants.mesos.length; numMonth++) {
			FactorCrecimientoFactura fc = new FactorCrecimientoFactura();
			fc.setMonth(numMonth + 1);
			fc.setFactor(1.0);
			list.add(fc);
		}
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String json = gson.toJson(list);
		StringBuffer jsonSB = new StringBuffer("{ \"aaData\" : ");
		jsonSB.append(json);
		jsonSB.append(" }");
		return jsonSB.toString();
	}
}
