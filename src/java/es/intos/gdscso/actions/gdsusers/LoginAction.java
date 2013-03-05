package es.intos.gdscso.actions.gdsusers;


import java.util.Locale;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.BaseAction;
import es.intos.gdscso.forms.gdsusers.LoginForm;
import es.intos.gdscso.ln.LNUsers;
import es.intos.util.Usuario;

public class LoginAction extends BaseAction{

	protected ActionForward executeAction( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, Object oo ) throws java.lang.Exception{

		ActionMessages errors = new ActionMessages();

		LoginForm frm = (LoginForm) form;
        Locale locale = getLocale(request);
		// ******************* VALIDACION USUARIO *********************
		Usuario user=LNUsers.consultaUser(frm, locale, errors);
		// LNUsers.consultaAplicatiu(frm, Recursos.aplicatiu, locale, errors);

		if (!errors.isEmpty())

			// ***************************************************************************

			if (!errors.isEmpty()) {
				this.saveErrors(request, errors);
				return mapping.findForward("Failure");
			}
		request.getSession().setAttribute("user", user );

		return mapping.findForward("Success");
	}// end executeAction

}
