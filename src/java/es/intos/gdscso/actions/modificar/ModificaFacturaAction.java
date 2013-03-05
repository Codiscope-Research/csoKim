package es.intos.gdscso.actions.modificar;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.modificar.ModificaFacturaForm;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.ln.LNVolum;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.on.ServicioFact;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class ModificaFacturaAction extends LogadoBaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		Factura factura = null;
		String procedenciaUser = null;

		ModificaFacturaForm frm = (ModificaFacturaForm) form;

		procedenciaUser = setProcedenciaUser(user);
		if (!procedenciaUser.equals(Recursos.PROCEDENCIA_GDS)) {
			throw new es.intos.gdscso.exceptions.AccesoDenegadoException();
		}

		Vector<Factura> facturaVector = LNFacturas.getFacturas(Integer.parseInt(frm.getIdFactura()), null, null, null, null);
		if (!facturaVector.isEmpty() && frm.getServeis() != null && !frm.getServeis().equals("")) {
			factura = facturaVector.get(0);

			String[] serveisIds = frm.getServeis().substring(1, frm.getServeis().length()).split(",");
			List<ServicioFact> servicios = new ArrayList<ServicioFact>();
			try {
				servicios = omplirLlistaServeisFactura(servicios, serveisIds, frm, factura);
			} catch (NumberFormatException ne) {
				ActionMessages errors = new ActionMessages();
				errors.add("error", new ActionMessage("cadena.serveis.mal.formada"));
				addErrors(request, errors);
				return mapping.findForward("Success");
			}
			LNFacturas.updateServeisFactura(factura.getId(), servicios,factura.getIdCSO());
			LNFacturas.updateImportFactura(factura.getId());
		}

		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private String setProcedenciaUser( Usuario user ){

		return Utils.procedenciaUser(user);

	}

	private List<ServicioFact> omplirLlistaServeisFactura( List<ServicioFact> servicios, String[] serveisIds, ModificaFacturaForm frm,
			Factura factura ) throws NumberFormatException, Exception{

		for (String servei : serveisIds) {
			String[] srv = servei.split(":");
			String idServei = srv[0];
			String action = srv[1];

			Vector<ServicioFact> serveiFactura = LNVolum.getSrv(Integer.parseInt(idServei.substring(2)), factura.getIdCSO(),
					factura.getMonth(), factura.getYear());

			if (!serveiFactura.isEmpty()) {
				ServicioFact srvFact = serveiFactura.firstElement();
				srvFact.setAction(action);
				servicios.add(serveiFactura.firstElement());
			}
		}
		return servicios;
	}

}
