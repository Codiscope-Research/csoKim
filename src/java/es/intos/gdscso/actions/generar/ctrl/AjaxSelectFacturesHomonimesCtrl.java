/*
 * AjaxCausisticaTipologiaAction.java
 *
 * Created on 22 de desembre de 2010
 */

package es.intos.gdscso.actions.generar.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.Basic;
import es.intos.util.Usuario;

public class AjaxSelectFacturesHomonimesCtrl{

	private Integer	idCso	= null;

	public String ctrl( HttpServletRequest request, HttpServletResponse response, Usuario user ) throws Exception, ErrorParamsException{

		String result = "";
		InicializeParam(request);
		result = searchInfoAndCreateJson();
		return result;
	}

	private void InicializeParam( HttpServletRequest request ) throws ErrorParamsException{

		try {
			this.idCso = (request.getParameter("id") == null || request.getParameter("id").equals("")) ? null : Integer.parseInt(request
					.getParameter("id"));
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}
		if (this.idCso == null) {
			throw new ErrorParamsException();
		}

	}

	private String searchInfoAndCreateJson() throws Exception{

		List<Basic> facturesHomonimesCodes = LNFacturas.getCodesOfFacturesHomonimes(this.idCso);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(facturesHomonimesCodes);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");
		return jsonSB.toString();
	}
}
