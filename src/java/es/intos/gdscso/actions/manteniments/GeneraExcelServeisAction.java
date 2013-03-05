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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.manteniments.BusquedaComparativaServeisForm;
import es.intos.gdscso.forms.manteniments.BusquedaComparativaServeisFormDTO;
import es.intos.gdscso.ln.LNServeis;
import es.intos.gdscso.on.ComparativaSrvTable;
import es.intos.gdscso.utils.Constants;
import es.intos.gdscso.utils.OrderComparativaServByName;
import es.intos.util.Usuario;

public class GeneraExcelServeisAction extends LogadoBaseAction{

	private Locale				locale		= null;
	private MessageResources	missatges	= null;
	
	// Ordenacions
	private String[]			ordenacioASC	= LNServeis.getOrderBy_ASC_Manteniment();
	private String[]			ordenacioDESC	= LNServeis.getOrderBy_DESC_Manteniment();

	@Override
	protected ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		try {

			// traspas del from
			BusquedaComparativaServeisForm busquedaComparativaServeisForm = (BusquedaComparativaServeisForm) form;
			BusquedaComparativaServeisFormDTO comparativaSrvFormDTO = getDTO(busquedaComparativaServeisForm);

			busquedaComparativaServeisForm.setRpp(800);
			if (busquedaComparativaServeisForm.getPagina() == 0) {
				busquedaComparativaServeisForm.setPagina(1);
			}

			// busquem les factures
			List<ComparativaSrvTable> comparativaServeislist = new ArrayList<ComparativaSrvTable>();

			this.missatges = getResources(request);
			this.locale = getLocale(request);
			
			comparativaServeislist = getInfoTable(comparativaSrvFormDTO, busquedaComparativaServeisForm);
			
		
			
				// si hi ha registres creem l'objecte paginacio
				GeneraExcelServeis excel = new GeneraExcelServeis("excel.control.sheet", user, missatges, locale);
				HSSFWorkbook workbook = null;
				workbook = excel.generaExcel(comparativaSrvFormDTO, comparativaServeislist);
			
			

				flushExcel(missatges.getMessage(locale, "excel.control.sheet"), response, workbook, user);
		} catch (NumberFormatException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("error.params"));
			addErrors(request, errors);
			return null;
		}

		return null;
	}

	// FUNCTIONS
	private BusquedaComparativaServeisFormDTO getDTO( BusquedaComparativaServeisForm form ) throws IllegalAccessException,
			InvocationTargetException{

		// traspas del from
		BusquedaComparativaServeisFormDTO comparativaSrvFormDTO = new BusquedaComparativaServeisFormDTO();
		BeanUtils.copyProperties(comparativaSrvFormDTO, form);
		return comparativaSrvFormDTO;

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


}
