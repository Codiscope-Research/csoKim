package es.intos.gdscso.actions.consulta;

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
import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasForm;
import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasFormDTO;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.ln.LNEstats;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.PaginacionBD;
import es.intos.util.Usuario;

public class BusquedaGestionAction extends LogadoBaseAction{

	public Integer					currentYear		= null;

	// Ordenacions
	private static final String[]	ordenacioASC	= LNFacturas.getOrderBy_ASC();
	private static final String[]	ordenacioDESC	= LNFacturas.getOrderBy_DESC();
	private MessageResources		missatges		= null;
	private String[]				mesos			= null;
	private Locale					locale			= null;
	private Vector<Basic>			years			= new Vector<Basic>(1, 1);

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		inizialize(request);

		// pass form a DTO
		BusquedaGestionFacturasForm facturaForm = (BusquedaGestionFacturasForm) form;
		BusquedaGestionFacturasFormDTO facturaDTO = getDTO(facturaForm);

		Vector<Basic> estats = LNEstats.getEstats(locale.getLanguage());
		Vector<Basic> csolist = LNCso.getCSOs();

		facturaForm.setRpp(20);
		if (facturaForm.getPagina() == 0) {
			facturaForm.setPagina(1);
		}

		// busquem les factures
		Vector<Factura> lista = LNFacturas.getFacturas(null, facturaDTO, facturaForm.getOrder_by(), facturaForm.getPagina(),
				facturaForm.getRpp());
		int numReg = LNFacturas.getNumReg(facturaDTO);

		if (lista.size() > 0) {
			PaginacionBD paginacion = new PaginacionBD(lista, facturaForm.getRpp(), facturaForm.getPagina(), numReg);
			request.setAttribute("paginacion", paginacion);
		}

		// posem info a la request
		request.setAttribute("OrderBy_ASC", ordenacioASC);
		request.setAttribute("OrderBy_DESC", ordenacioDESC);
		request.setAttribute("estats", estats);
		request.setAttribute("mesos", mesos);
		request.setAttribute("years", years);
		request.setAttribute("csos", csolist);

		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private void inizialize( HttpServletRequest request ){

		InizializeCurrentYear();

		this.missatges = getResources(request);
		this.locale = getLocale(request);
		this.years = Utils.getListOfYears();
		this.mesos = Utils.getMonths(missatges, locale);
	}

	private void InizializeCurrentYear(){

		this.currentYear = Utils.getCurrentYear();

	}

	private BusquedaGestionFacturasFormDTO getDTO( BusquedaGestionFacturasForm form ) throws IllegalAccessException,
			InvocationTargetException{

		// traspas del from
		BusquedaGestionFacturasFormDTO busquedaGestionFacturasFormDTO = new BusquedaGestionFacturasFormDTO();
		BeanUtils.copyProperties(busquedaGestionFacturasFormDTO, form);
		return busquedaGestionFacturasFormDTO;

	}

}
