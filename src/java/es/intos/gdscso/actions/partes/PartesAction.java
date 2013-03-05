package es.intos.gdscso.actions.partes;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.partes.BusquedaNoConformidadesForm;
import es.intos.gdscso.forms.partes.BusquedaNoConformidadesFormDTO;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.FacturaTableNC;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.PaginacionBD;
import es.intos.util.Usuario;

public class PartesAction extends LogadoBaseAction{

	private Integer				currentMonth	= null;
	private Vector<Basic>		years			= new Vector<Basic>(1, 1);
	private Locale				locale			= null;
	private MessageResources	missatges		= null;
	private Vector<Basic>		csolist			= new Vector<Basic>(1, 1);
	private String[]			mesos			= null;

	// Ordenacions
	private String[]			ordenacioASC	= LNFacturas.getOrderByNC_ASC();
	private String[]			ordenacioDESC	= LNFacturas.getOrderByNC_DESC();

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		inizialize(request);

		// traspas del from
		BusquedaNoConformidadesForm facturaNoConformidadesForm = (BusquedaNoConformidadesForm) form;
		BusquedaNoConformidadesFormDTO facturaNoConformidadesDTO = getDTO(facturaNoConformidadesForm);

		facturaNoConformidadesForm.setRpp(20);
		if (facturaNoConformidadesForm.getPagina() == 0) {
			facturaNoConformidadesForm.setPagina(1);
		}

		// busquem les factures
		Vector<FacturaTableNC> lista = LNFacturas.getFacturasWithNoConformidades(facturaNoConformidadesDTO, locale.getLanguage(),
				facturaNoConformidadesForm.getOrder_by(), facturaNoConformidadesForm.getPagina(), facturaNoConformidadesForm.getRpp());

		int numReg = LNFacturas.getNumRegNoConformidades(facturaNoConformidadesDTO);

		if (lista.size() > 0) {
			PaginacionBD paginacion = new PaginacionBD(lista, facturaNoConformidadesForm.getRpp(), facturaNoConformidadesForm.getPagina(),
					numReg);
			request.setAttribute("paginacion", paginacion);
		}

		// posem info a la request
		request.setAttribute("OrderBy_ASC", ordenacioASC);
		request.setAttribute("OrderBy_DESC", ordenacioDESC);
		request.setAttribute("years", years);
		request.setAttribute("mesos", mesos);
		request.setAttribute("csos", csolist);
		request.setAttribute("month", this.currentMonth);
		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private void inizialize( HttpServletRequest request ) throws Exception{

		InizializeCurrentYearAndMonth();
		this.locale = getLocale(request);
		this.years = Utils.getListOfYears();
		this.missatges = getResources(request);
		this.csolist = LNCso.getCSOs();
		this.mesos = Utils.getMonths(missatges, locale);
	}

	private void InizializeCurrentYearAndMonth(){

		this.currentMonth = Utils.getCurrentMonth();

	}

	private BusquedaNoConformidadesFormDTO getDTO( BusquedaNoConformidadesForm form ) throws IllegalAccessException,
			InvocationTargetException{

		// traspas del from
		BusquedaNoConformidadesFormDTO busquedaNoConformidadesFormDTO = new BusquedaNoConformidadesFormDTO();
		BeanUtils.copyProperties(busquedaNoConformidadesFormDTO, form);
		return busquedaNoConformidadesFormDTO;

	}

}
