package es.intos.gdscso.actions;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.ln.LNVolum;
import es.intos.util.Usuario;

public class portalAction extends LogadoBaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		// if (!Recursos.pruebas) return mapping.findForward("enconstruccion");

		Locale locale = getLocale(request);
		ActionMessages errors = new ActionMessages();
		ActionMessages messages = new ActionMessages();
		
		
		if (StringUtils.isEmpty(user.getPortal())) {
			user.setPortal(es.intos.util.Utils.getUrl(request, "portalTrans,portal,f_pass,f_user"));
		}

		try {
			es.intos.util.Autorizacion.throwUserReqCentro(user.getNumEmp(), user.getENumCen());
		} catch (Exception e) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.no.enumcen"));
		}

		final int ca_ES = 0;
		final int es_ES = 1;
		Locale CAT = new Locale("ca", "ES");
		if (CAT.equals(locale)) {
			request.setAttribute("idiomaInt", "" + ca_ES);
		} else {
			request.setAttribute("idiomaInt", "" + es_ES);
		}

		boolean existNewData = LNVolum.ckeckNewData();
		request.setAttribute("newdata", existNewData);

		if (!errors.isEmpty()) {
			this.saveErrors(request, errors);
			return mapping.findForward("failure");
		}

		this.saveMessages(request, messages);

		return mapping.findForward("Success");
	}


}
