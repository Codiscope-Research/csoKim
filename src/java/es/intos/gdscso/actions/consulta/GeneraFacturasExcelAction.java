package es.intos.gdscso.actions.consulta;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasForm;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class GeneraFacturasExcelAction extends LogadoBaseAction{

	@Override
	protected ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		Locale locale = getLocale(request);
		MessageResources missatges = getResources(request);
		BusquedaGestionFacturasForm facturaForm = (BusquedaGestionFacturasForm) form;
		GeneraFacturasExcel excel = new GeneraFacturasExcel("excel.control.sheet", user, missatges, locale);
		HSSFWorkbook workbook = null;
		workbook = excel.generaExcel(facturaForm);

		flushExcel(missatges.getMessage(locale, "excel.control.sheet"), response, workbook, user);

		return null;
	}

}
