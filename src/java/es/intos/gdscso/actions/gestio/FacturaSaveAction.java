package es.intos.gdscso.actions.gestio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.gestio.GestionFacturasForm;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class FacturaSaveAction extends LogadoBaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		Integer idFactura = null;
		String observacions = "";

		try {
			GestionFacturasForm frm = (GestionFacturasForm) form;
			idFactura = frm.getId() == null || frm.getId().equals("") ? null : Integer.parseInt(frm.getId());
			observacions = frm.getObservaciones() == null ? "" : frm.getObservaciones();
		} catch (NumberFormatException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("error.params"));
			addErrors(request, errors);
		}

		LNFacturas.updateObservacionesFactura(idFactura, observacions);

		return mapping.findForward("Success");
	}
}
