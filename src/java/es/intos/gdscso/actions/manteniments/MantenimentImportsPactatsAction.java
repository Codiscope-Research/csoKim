package es.intos.gdscso.actions.manteniments;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.manteniments.ImportPactatForm;
import es.intos.gdscso.ln.LNImportsPactats;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class MantenimentImportsPactatsAction extends LogadoBaseAction{

	private Integer			currentYear			= null;
	private Double			importeCurrentYear	= null;
	private Vector<Basic>	years				= new Vector<Basic>(1, 1);

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		inizialize(request);
		ImportPactatForm importform = (ImportPactatForm) form;
		getImporteCurrentYear();

		request.setAttribute("years", this.years);
		if (importform.getImporte() != null && !importform.getImporte().equals("")) {
			// Passa per aqui si ve de l'action SaveImportPactatAction
			request.setAttribute("importe", importform.getImporte());
		} else {
			request.setAttribute("importe", this.importeCurrentYear);
		}
		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private void inizialize( HttpServletRequest request ){

		InizializeCurrentYear();
		this.years = Utils.getListOfYears();
	}

	private void InizializeCurrentYear(){

		this.currentYear = Utils.getCurrentYear();

	}

	private void getImporteCurrentYear() throws Exception{

		try {
			Basic basic = LNImportsPactats.getImportPactat(this.currentYear);
			if (basic != null && basic.getDescripcio() != null && !basic.getDescripcio().equals(""))
				this.importeCurrentYear = Double.parseDouble(basic.getDescripcio());
			if (this.importeCurrentYear == null)
				this.importeCurrentYear = 0.0;
		} catch (Exception e) {
			throw e;
		}

	}
}
