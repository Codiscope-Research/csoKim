package es.intos.gdscso.actions.gdsusers;

//import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.actions.BaseAction;
import es.intos.util.Usuario;

public class PreLoginAction extends BaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		// INICIALITZEM LES DADES DEL FORMULARI DE ALTA DE SOLICITUD
		// LoginForm frm = new LoginForm();
		// Locale locale = getLocale(request);

		return mapping.findForward("Success");
	}

	protected ActionForward executeAction( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, Object oo ) throws java.lang.Exception{

		Usuario user = (Usuario) oo;
		return executeLogadoAction(mapping, form, request, response, user);
	}// end executeAction

}
