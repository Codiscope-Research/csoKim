package es.intos.gdscso.actions.consulta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasForm;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class PreBusquedaGestionAction extends BusquedaGestionAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		BusquedaGestionFacturasForm formBusqueda = (BusquedaGestionFacturasForm) form;
		if (request.getParameter("reload") != null && request.getParameter("reload").toString().equals("yes")) {
			request.setAttribute("reload", "yes");
		} else {
			request.setAttribute("reload", "no");
			formBusqueda.reset(mapping, request);
		}

		return super.executeLogadoAction(mapping, formBusqueda, request, response, user);
	}

}
