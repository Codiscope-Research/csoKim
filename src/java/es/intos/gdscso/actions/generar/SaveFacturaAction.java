package es.intos.gdscso.actions.generar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.exceptions.ErrorParamsException;
import es.intos.gdscso.forms.generar.FactorsForm;
import es.intos.gdscso.forms.generar.GenerarFacturasForm;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.ln.LNVolum;
import es.intos.gdscso.on.FactorsCorreccioFactura;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.on.ServicioFact;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class SaveFacturaAction extends LogadoBaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		GenerarFacturasForm frm = (GenerarFacturasForm) form;
		FactorsForm factorsForm = (FactorsForm) frm;

		String procedenciaUser = setProcedenciaUser(user);
		if (!procedenciaUser.equals(Recursos.PROCEDENCIA_GDS)) {
			throw new es.intos.gdscso.exceptions.AccesoDenegadoException();
		}
		// Guardem la factura
		Factura factura = new Factura();
		List<ServicioFact> servicios = new ArrayList<ServicioFact>();

		try {
			if (frm.getServeis().length() > 0) {
				String[] serveisIds = frm.getServeis().substring(1, frm.getServeis().length()).split(",");
				servicios = omplirLlistaServeisFactura(servicios, serveisIds, frm);
			}
		} catch (ErrorParamsException ep) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("error.params"));
			addErrors(request, errors);
		}

		factura.setServicios(servicios);
		factura.setIdCSO(Integer.parseInt(frm.getIdCso()));
		factura.setMonth(Integer.parseInt(frm.getMonth()));
		request.setAttribute("mesaved", frm.getMonth());
		factura.setYear(Integer.parseInt(frm.getYear()));
		request.setAttribute("yearsaved", frm.getYear());
		if (frm.getCode() == null || frm.getCode().equals("")) {
			factura.setCode(generaCodeFactura());
		} else {
			factura.setCode(frm.getCode());
		}

		LNFacturas.insertFactura(factura);
		factorsForm.setCode(factura.getCode());
		try {
			saveFactorsCorreccio(factorsForm);
		} catch (ErrorParamsException ep) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("error.params"));
			addErrors(request, errors);
		}
		request.setAttribute("saved", "ok");
		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private String setProcedenciaUser( Usuario user ){

		return Utils.procedenciaUser(user);
	}

	private List<ServicioFact> omplirLlistaServeisFactura( List<ServicioFact> servicios, String[] serveisIds, GenerarFacturasForm frm )
			throws ErrorParamsException, Exception{

		try {
			for (String idServei : serveisIds) {
				Vector<ServicioFact> serveiFactura = LNVolum.getSrv(Integer.parseInt(idServei.substring(2)),
						Integer.parseInt(frm.getIdCso()), Integer.parseInt(frm.getMonth()), Integer.parseInt(frm.getYear()));
				if (!serveiFactura.isEmpty()) {
					servicios.add(serveiFactura.firstElement());
				}
			}
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}
		return servicios;
	}

	private String generaCodeFactura() throws Exception{

		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		int numRegistres = LNFacturas.getNumReg(null);
		return "FA_" + year + "_" + fillWithZeros(numRegistres);
	}

	private String fillWithZeros( int numRegistres ){

		StringBuffer numRegistresStringBuffer = new StringBuffer(String.valueOf(numRegistres));
		if (numRegistresStringBuffer.length() < 4) {
			int numZerosToAdd = 4 - numRegistresStringBuffer.length();
			for (int i = 0; i < numZerosToAdd; i++) {
				numRegistresStringBuffer.append("0");
			}
		}
		return numRegistresStringBuffer.toString();
	}

	private void saveFactorsCorreccio( FactorsForm factorsForm ) throws Exception, ErrorParamsException{

		FactorsCorreccioFactura factorsCorreccio = new FactorsCorreccioFactura();
		BeanUtils.copyProperties(factorsCorreccio, factorsForm);
		LNFacturas.saveFactoresCrecimientoFactura(factorsCorreccio);

	}

}
