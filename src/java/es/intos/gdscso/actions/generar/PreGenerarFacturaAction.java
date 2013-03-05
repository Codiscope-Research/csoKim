package es.intos.gdscso.actions.generar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class PreGenerarFacturaAction extends GenerarFacturaAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		return super.executeLogadoAction(mapping, form, request, response, user);
	}

}
