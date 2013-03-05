package es.intos.gdscso.actions.generar;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class GenerarFacturaAction extends LogadoBaseAction{

	public Integer	currentYear		= null;
	public Integer	currentMonth	= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		InizializeCurrentYearAndMonth();

		Vector<Basic> years = new Vector<Basic>(1, 1);
		years = Utils.getListOfYears();

		// posem info a la requets
		request.setAttribute("years", years);
		
		if(request.getAttribute("mesaved")!=null){
			request.setAttribute("month", request.getAttribute("mesaved"));
		}else{
			request.setAttribute("month", this.currentMonth-1);
		}
		
		if(request.getAttribute("saved")!=null){
			request.setAttribute("saved","ok");
		}
		
		if(request.getAttribute("yearsaved")!=null){
			request.setAttribute("year", request.getAttribute("yearsaved"));
		}else{
			request.setAttribute("year", this.currentYear);
		}

		return mapping.findForward("Success");
	}

	// FUNCTIONS

	private void InizializeCurrentYearAndMonth(){

		this.currentYear = Utils.getCurrentYear();
		this.currentMonth = Utils.getCurrentMonth();

	}

}
