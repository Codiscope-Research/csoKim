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
import es.intos.gdscso.actions.facturacion.ctrl.DetallePartidaCtrl;
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class DetallePartidaAction extends LogadoBaseAction{

	private Integer				currentYear	= null;
	private MessageResources	missatges	= null;
	private Vector<Basic>		years		= new Vector<Basic>(1, 1);
	private String[]			mesos		= null;
	private Locale				locale		= null;

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		DetallePartidaCtrl ctrl = new DetallePartidaCtrl();
		Integer idPartida = null;
		String nomPartida = "";
		StringBuffer jsonChart = null;

		inizializeParams(request);
		idPartida = inizializeIdpartida(request);

		jsonChart = ctrl.ctrl(mapping, form, request, response, user, years);
		nomPartida = getNomPartida(idPartida);

		// posem info a la requets
		request.setAttribute("years", years);
		request.setAttribute("year", this.currentYear);
		request.setAttribute("mesos", mesos);
		request.setAttribute("data", jsonChart);
		request.setAttribute("nomPartida", nomPartida);
		request.setAttribute("idpartida", idPartida);

		return mapping.findForward("Success");
	}

	// FUNCTIONS

	private void inizializeCurrentYearAndMonth(){

		this.currentYear = Utils.getCurrentYear();

	}

	private void inizializeParams( HttpServletRequest request ){

		this.missatges = getResources(request);
		this.locale = getLocale(request);
		inizializeCurrentYearAndMonth();
		this.years = Utils.getListOfYears();
		this.mesos = Utils.getMonths(missatges, locale);
	}

	private Integer inizializeIdpartida( HttpServletRequest request ){

		return (request.getParameter("id") != null && !request.getParameter("id").equals("") && !request.getParameter("id").equals("null")) ? Integer
				.parseInt(request.getParameter("id")) : null;

	}

	private String getNomPartida( Integer idPartida ) throws Exception{

		if (idPartida == null) {
			return "";
		}
		return LNPartidas.getNomPartida(idPartida);
	}
}
