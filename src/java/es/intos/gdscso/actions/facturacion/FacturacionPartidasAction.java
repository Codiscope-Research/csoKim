package es.intos.gdscso.actions.facturacion;

import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class FacturacionPartidasAction extends LogadoBaseAction{

	private Integer				currentYear	= null;
	private String[]			mesos		= null;
	private Vector<Basic>		years		= new Vector<Basic>(1, 1);
	private MessageResources	missatges	= null;
	private Locale				locale		= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		inicialize(request);

		// posem info a la requets
		request.setAttribute("years", years);
		request.setAttribute("year", this.currentYear);
		request.setAttribute("mesos", mesos);

		return mapping.findForward("Success");
	}

	// FUNCTIONS

	private void inicialize( HttpServletRequest request ){

		InizializeCurrentYearAndMonth();
		this.locale = getLocale(request);
		this.missatges = getResources(request);
		this.mesos = Utils.getMonths(missatges, locale);
		this.years = Utils.getListOfYears();
	}

	private void InizializeCurrentYearAndMonth(){

		this.currentYear = Utils.getCurrentYear();

	}
}
