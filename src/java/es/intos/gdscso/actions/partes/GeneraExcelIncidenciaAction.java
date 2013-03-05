package es.intos.gdscso.actions.partes;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.FacturaDialogNC;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class GeneraExcelIncidenciaAction extends LogadoBaseAction{

	private Locale				locale		= null;
	private MessageResources	missatges	= null;

	@Override
	protected ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		Integer idFactura = null;
		try {
			inizialize(request);
			idFactura = inizializeIdFactura(request);
		} catch (NumberFormatException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("error.params"));
			addErrors(request, errors);
			return null;
		}

		FacturaDialogNC factInfoNC = LNFacturas.getInfoFacturaNoConformidades(idFactura, this.locale.getLanguage());

		GeneraExcelIncidencia excel = new GeneraExcelIncidencia("excel.control.sheet", user, missatges, locale);
		HSSFWorkbook workbook = null;
		workbook = excel.generaExcel(factInfoNC);

		flushExcel(missatges.getMessage(locale, "excel.control.sheet"), response, workbook, user);

		return null;
	}

	// FUNCTIONS
	private void inizialize( HttpServletRequest request ) throws Exception{

		this.locale = getLocale(request);
		this.missatges = getResources(request);

	}

	private Integer inizializeIdFactura( HttpServletRequest request ) throws Exception{

		return request.getParameter("idfactura") != null && !request.getParameter("idfactura").equals("") ? Integer.parseInt(request
				.getParameter("idfactura")) : null;
	}

}
