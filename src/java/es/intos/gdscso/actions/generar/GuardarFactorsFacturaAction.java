package es.intos.gdscso.actions.generar;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.forms.generar.FactorsForm;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.FactorsCorreccioFactura;
import es.intos.gdscso.on.Partida;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class GuardarFactorsFacturaAction extends LogadoBaseAction{

	private Integer			currentYear	= null;
	private Vector<Basic>	years		= new Vector<Basic>(1, 1);

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		FactorsForm factorsForm = (FactorsForm) form;
		String procedenciaUser = setProcedenciaUser(user);
		inizializeParams(request, form);

		if (!procedenciaUser.equals(Recursos.PROCEDENCIA_GDS)) {
			throw new es.intos.gdscso.exceptions.AccesoDenegadoException();
		}
		try {
			saveFactorsCorreccio(factorsForm);
		} catch (ErrorParamsException ep) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("error.params"));
			addErrors(request, errors);
		}
		// Carreguem la linia de partides.
		List<Partida> list = LNPartidas.getAllPartidas();
		request.setAttribute("listaPartidas", list);
		request.setAttribute("years", years);

		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private String setProcedenciaUser( Usuario user ){

		return Utils.procedenciaUser(user);
	}

	private void inizializeParams( HttpServletRequest request, ActionForm form ){

		inizializeCurrentYear();
		this.years = Utils.getListOfYears();
	}

	private void inizializeCurrentYear(){

		this.currentYear = Utils.getCurrentYear();

	}

	private void saveFactorsCorreccio( FactorsForm factorsForm ) throws Exception{

		try {
			FactorsCorreccioFactura factorsCorreccio = new FactorsCorreccioFactura();
			BeanUtils.copyProperties(factorsCorreccio, factorsForm);
			LNFacturas.saveFactoresCrecimientoFactura(factorsCorreccio);
		} catch (ErrorParamsException ep) {
			throw ep;
		}
	}

}
