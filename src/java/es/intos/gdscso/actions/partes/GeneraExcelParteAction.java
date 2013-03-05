package es.intos.gdscso.actions.partes;

import java.lang.reflect.InvocationTargetException;
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
import es.intos.gdscso.forms.partes.BusquedaNoConformidadesForm;
import es.intos.gdscso.forms.partes.BusquedaNoConformidadesFormDTO;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.FacturaTableNC;
import es.intos.util.Usuario;

public class GeneraExcelParteAction extends LogadoBaseAction{

	private Locale				locale		= null;
	private MessageResources	missatges	= null;

	@Override
	protected ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		try {
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
					facturaNoConformidadesForm.getOrder_by(), 1, 999);

			GeneraExcelParte excel = new GeneraExcelParte("excel.control.sheet", user, missatges, locale);
			HSSFWorkbook workbook = null;
			workbook = excel.generaExcel(lista, facturaNoConformidadesDTO);

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
	private void inizialize( HttpServletRequest request ) throws Exception{

		this.locale = getLocale(request);
		this.missatges = getResources(request);

	}

	private BusquedaNoConformidadesFormDTO getDTO( BusquedaNoConformidadesForm form ) throws IllegalAccessException,
			InvocationTargetException{

		// traspas del from
		BusquedaNoConformidadesFormDTO busquedaNoConformidadesFormDTO = new BusquedaNoConformidadesFormDTO();
		BeanUtils.copyProperties(busquedaNoConformidadesFormDTO, form);
		return busquedaNoConformidadesFormDTO;

	}

}
