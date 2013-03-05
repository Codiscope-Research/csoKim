package es.intos.gdscso.actions.gestio;

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
import es.intos.gdscso.forms.gestio.GestionFacturasForm;
import es.intos.gdscso.ln.LNEstats;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class EditFacturaAction extends LogadoBaseAction{

	private Locale	locale	= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		String procedenciaUser = procedenciaUser(user);
		Integer idFactura = null;

		try {

			InizializeVariables(request);
			idFactura = InizializeIdFactura(request, form);

		} catch (NumberFormatException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("parametres.incorrectes"));
			addErrors(request, errors);
			return mapping.findForward("Wrongparams");
		}

		if (!procedenciaUser.equals(Recursos.PROCEDENCIA_GDS)) {
			throw new es.intos.gdscso.exceptions.AccesoDenegadoException();
		}

		GestionFacturasForm frm = (GestionFacturasForm) form;

		Vector<Basic> estats = LNEstats.getEstats(locale.getLanguage());

		Vector<Factura> factList = LNFacturas.getFacturas(idFactura, null, null, null, null);
		if (!factList.isEmpty()) {
			Factura fct = factList.get(0);
			request.setAttribute("idfactura", fct.getId());
			request.setAttribute("factestat", fct.getNomEstat());
		}

		// posem info a la request

		request.setAttribute("user_cso", procedenciaUser);
		request.setAttribute("locale", locale.getLanguage().toUpperCase());
		request.setAttribute("estats", estats);

		request.setAttribute("frm", frm);

		return mapping.findForward("Success");
	}

	// FUNCTIONS

	private void InizializeVariables( HttpServletRequest request ) throws Exception{

		this.locale = getLocale(request);

	}

	private Integer InizializeIdFactura( HttpServletRequest request, ActionForm form ) throws Exception{

		GestionFacturasForm formEdit = (GestionFacturasForm) form;
		Integer idFactura = formEdit.getId() == null || formEdit.getId().equals("") ? null : Integer.parseInt(formEdit.getId());
		if (idFactura == null) {
			throw new NumberFormatException();
		}
		return idFactura;

	}

	private String procedenciaUser( Usuario user ){

		return Utils.procedenciaUser(user);
	}

}
