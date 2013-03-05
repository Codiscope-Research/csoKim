package es.intos.gdscso.actions.gestio;

import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.actions.gestio.ctrl.GestioFacturaCtrl;
import es.intos.gdscso.forms.gestio.GestionFacturasForm;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class GestioFacturaAction extends LogadoBaseAction{

	private Locale	locale	= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		GestioFacturaCtrl ctrl = new GestioFacturaCtrl();
		try {
			InizializeVariables(request);

			String url_image = ctrl.ctrl(form, request, response, user, locale);

			GestionFacturasForm frm = ctrl.getFrm();
			Vector<Basic> inclist = ctrl.getInclist();
			
			 Integer idFactura = (request.getParameter("id") != null && !request.getParameter("id").equals("")) ? Integer.parseInt(request
					.getParameter("id")) : null;
			List<Basic> names = LNFacturas.getFacturaPDFNames(idFactura);

			// posem info a la request
			request.setAttribute("user_cso", ctrl.getProcedenciaUser());
			request.setAttribute("locale", locale.getLanguage().toUpperCase());
			request.setAttribute("estats", ctrl.getEstats());
			request.setAttribute("urlimage", url_image);
			request.setAttribute("inc", inclist);
			request.setAttribute("frm", frm);
			request.setAttribute("names", names);

		} catch (NumberFormatException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("error.params"));
			addErrors(request, errors);
			return mapping.findForward("Wrongparams");
		}

		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private void InizializeVariables( HttpServletRequest request ) throws Exception{

		this.locale = getLocale(request);

	}

}
