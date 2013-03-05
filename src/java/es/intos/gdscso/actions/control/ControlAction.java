package es.intos.gdscso.actions.control;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class ControlAction extends LogadoBaseAction{

	public Integer	currentYear	= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		InizializeCurrentYear();
		// Busquem anys i CSOs
		Vector<Basic> years = new Vector<Basic>(1, 1);
		years = Utils.getListOfYears();

		Vector<Basic> csolist = LNCso.getCSOs();

		// posem info a la request
		request.setAttribute("csos", csolist);
		request.setAttribute("years", years);

		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private void InizializeCurrentYear(){

		this.currentYear = Utils.getCurrentYear();

	}

}
