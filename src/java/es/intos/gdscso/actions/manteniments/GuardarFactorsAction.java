package es.intos.gdscso.actions.manteniments;

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
import es.intos.gdscso.forms.manteniments.FactorsForm;
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.FactorsCorreccio;
import es.intos.gdscso.on.Partida;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class GuardarFactorsAction extends LogadoBaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		FactorsForm factorsForm = (FactorsForm) form;
		String procedenciaUser = setProcedenciaUser(user);
		if (!procedenciaUser.equals(Recursos.PROCEDENCIA_GDS)) {
			throw new es.intos.gdscso.exceptions.AccesoDenegadoException();
		}

		try {
			saveFactorsCorreccio(factorsForm);
		} catch (NumberFormatException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("fc.error.double"));
			addErrors(request, errors);
			return mapping.findForward("Success");
		}
		Vector<Basic> years = Utils.getListOfYears();
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

	private void saveFactorsCorreccio( FactorsForm factorsForm ) throws Exception{

		FactorsCorreccio factorsCorreccio = new FactorsCorreccio();
		BeanUtils.copyProperties(factorsCorreccio, factorsForm);
		LNPartidas.saveFactoresCrecimientoPartidas(factorsCorreccio);
		LNPartidas.saveImportPactatPartida(factorsCorreccio);

	}
}
