package es.intos.gdscso.actions.manteniments;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import es.intos.gdscso.forms.manteniments.BusquedaComparativaServeisForm;
import es.intos.gdscso.forms.manteniments.BusquedaComparativaServeisFormDTO;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.ln.LNServeis;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.ComparativaSrvTable;
import es.intos.gdscso.utils.Constants;
import es.intos.gdscso.utils.OrderComparativaServByName;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.PaginacionBD;
import es.intos.util.Usuario;

public class MantenimentServAction extends LogadoBaseAction{

	private Integer				currentYear		= null;
	private Integer				currentMonth	= null;
	private Vector<Basic>		years			= new Vector<Basic>(1, 1);
	private Vector<Basic>		csolist			= new Vector<Basic>(1, 1);
	private MessageResources	missatges		= null;
	private String[]			mesos			= null;
	private Locale				locale			= null;
	// Ordenacions
	private String[]			ordenacioASC	= LNServeis.getOrderBy_ASC_Manteniment();
	private String[]			ordenacioDESC	= LNServeis.getOrderBy_DESC_Manteniment();

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		inizialize(request);

		BusquedaComparativaServeisForm comparativaSrvForm = (BusquedaComparativaServeisForm) form;
		BusquedaComparativaServeisFormDTO comparativaSrvFormDTO = getDTO(comparativaSrvForm);

		comparativaSrvForm.setRpp(800);
		if (comparativaSrvForm.getPagina() == 0) {
			comparativaSrvForm.setPagina(1);
		}

		// busquem les factures
		List<ComparativaSrvTable> comparativaServeislist = new ArrayList<ComparativaSrvTable>();

		comparativaServeislist = getInfoTable(comparativaSrvFormDTO, comparativaSrvForm);
		int numReg = getNumRegistresTotals(comparativaSrvFormDTO);

		if (comparativaServeislist.size() > 0) {
			// si hi ha registres creem l'objecte paginacio

			Vector<ComparativaSrvTable> comparativaServeisVector = new Vector<ComparativaSrvTable>(comparativaServeislist.size(), 1);
			comparativaServeisVector = fromListToVector(comparativaServeislist);
			PaginacionBD paginacion = new PaginacionBD(comparativaServeisVector, comparativaSrvForm.getRpp(),
					comparativaSrvForm.getPagina(), numReg);
			request.setAttribute("paginacion", paginacion);

		}

		// posem info a la requets
		request.setAttribute("OrderBy_ASC", this.ordenacioASC);
		request.setAttribute("OrderBy_DESC", this.ordenacioDESC);
		request.setAttribute("mesos", this.mesos);
		request.setAttribute("csos", this.csolist);
		request.setAttribute("years", this.years);
		
		if(this.currentMonth==1){
			request.setAttribute("year", this.currentYear-1);		
			request.setAttribute("month", this.mesos[11]);
		}else{
			request.setAttribute("year", this.currentYear);
			request.setAttribute("month", this.mesos[this.currentMonth-2]);
		}
	

		return mapping.findForward("Success");
	}

	// FUNCTIONS

	private void inizialize( HttpServletRequest request ) throws Exception{

		InizializeCurrentYearAndMonth();
		this.locale = getLocale(request);
		this.csolist = LNCso.getCSOs();
		this.years = Utils.getListOfYears();
		this.missatges = getResources(request);
		this.mesos = Utils.getMonths(missatges, locale);
	}

	private void InizializeCurrentYearAndMonth(){

		this.currentYear = Utils.getCurrentYear();
		this.currentMonth = Utils.getCurrentMonth();

	}

	@SuppressWarnings("unchecked")
	private List<ComparativaSrvTable> retallaIOrdena( Vector<ComparativaSrvTable> comparativaServeis,
			BusquedaComparativaServeisForm comparativaSrvForm ){

		List<ComparativaSrvTable> comparativaServeislist = new ArrayList<ComparativaSrvTable>();
		
			
		comparativaServeislist = (List<ComparativaSrvTable>) comparativaServeis.subList((comparativaSrvForm.getPagina() - 1)
				* comparativaSrvForm.getRpp(), (((comparativaSrvForm.getPagina()) * comparativaSrvForm.getRpp()) > comparativaServeis
				.size()) ? comparativaServeis.size() : (comparativaSrvForm.getPagina() * comparativaSrvForm.getRpp()));

		// ordenem la llista
		if (comparativaSrvForm.getOrder_by().equals("")) {
			// si no esta definit no fem res
		} else if (comparativaSrvForm.getOrder_by().equals(ordenacioASC[Constants.ORDRE_BY_NOM_TABLE_FIRST_COL])
				|| comparativaSrvForm.getOrder_by().equals(ordenacioASC[Constants.ORDRE_BY_NOM_TABLE_SECOND_COL])) {

			Collections.sort(comparativaServeislist, new OrderComparativaServByName(OrderComparativaServByName.order[0]));

		} else if (comparativaSrvForm.getOrder_by().equals(ordenacioDESC[Constants.ORDRE_BY_NOM_TABLE_FIRST_COL])
				|| comparativaSrvForm.getOrder_by().equals(ordenacioDESC[Constants.ORDRE_BY_NOM_TABLE_SECOND_COL])) {

			Collections.sort(comparativaServeislist, new OrderComparativaServByName(OrderComparativaServByName.order[1]));

		} else if (comparativaSrvForm.getOrder_by().equals(ordenacioASC[Constants.ORDRE_BY_ESTAT_TABLE_THIRD_COL])) {

			Collections.sort(comparativaServeislist, new OrderComparativaServByName(OrderComparativaServByName.order[2]));

		} else if (comparativaSrvForm.getOrder_by().equals(ordenacioDESC[Constants.ORDRE_BY_ESTAT_TABLE_THIRD_COL])) {

			Collections.sort(comparativaServeislist, new OrderComparativaServByName(OrderComparativaServByName.order[3]));

		}
		return comparativaServeislist;
	}

	private Vector<ComparativaSrvTable> fromListToVector( List<ComparativaSrvTable> comparativaServeislist ){

		Vector<ComparativaSrvTable> comparativaServeisVector = new Vector<ComparativaSrvTable>(comparativaServeislist.size(), 1);
		comparativaServeisVector.addAll(comparativaServeislist);
		return comparativaServeisVector;

	}

	private List<ComparativaSrvTable> getInfoTable( BusquedaComparativaServeisFormDTO comparativaSrvFormDTO,
			BusquedaComparativaServeisForm comparativaSrvForm ) throws Exception{

		Vector<ComparativaSrvTable> comparativaServeis = new Vector<ComparativaSrvTable>(1, 1);
		List<ComparativaSrvTable> comparativaServeislist = new ArrayList<ComparativaSrvTable>();

		if (comparativaSrvFormDTO.getF_mes().equals("") || comparativaSrvFormDTO.getF_any().equals("")) {
			// Dades insuficients
		} else {
			// busquem info per la taula
			try {
				comparativaServeis = LNServeis.getComparativaServeis(comparativaSrvFormDTO,this.missatges);
			} catch (Exception e) {
				throw e;
			}
			comparativaServeislist = retallaIOrdena(comparativaServeis, comparativaSrvForm);

		}
		return comparativaServeislist;
	}

	private int getNumRegistresTotals( BusquedaComparativaServeisFormDTO comparativaSrvFormDTO ) throws Exception{

		int numReg = 0;
		try {
			if (comparativaSrvFormDTO.getF_cso().equals("") || comparativaSrvFormDTO.getF_csoActual().equals("")
					|| comparativaSrvFormDTO.getF_mes().equals("") || comparativaSrvFormDTO.getF_any().equals("")) {
				// Dades insuficients
			} else {
				numReg = LNServeis.getComparativaServeisNumReg(comparativaSrvFormDTO);
			}
		} catch (Exception e) {
			throw e;
		}
		return numReg;
	}

	private BusquedaComparativaServeisFormDTO getDTO( BusquedaComparativaServeisForm form ) throws IllegalAccessException,
			InvocationTargetException{

		// traspas del from
		BusquedaComparativaServeisFormDTO comparativaSrvFormDTO = new BusquedaComparativaServeisFormDTO();
		BeanUtils.copyProperties(comparativaSrvFormDTO, form);
		return comparativaSrvFormDTO;

	}

}
