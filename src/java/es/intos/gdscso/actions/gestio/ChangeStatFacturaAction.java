package es.intos.gdscso.actions.gestio;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.gestio.GestionFacturasForm;
import es.intos.gdscso.ln.LNEstats;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.EstatHistoric;
import es.intos.gdscso.utils.Constants;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class ChangeStatFacturaAction extends LogadoBaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		GestionFacturasForm frm = (GestionFacturasForm) form;

		// guardem canvi a historics.
		EstatHistoric statH = copyProperties(frm);
		String procedenciaUser = setProcedenciaUser(user);

		if (procedenciaUser.equals(Recursos.PROCEDENCIA_GDS)) {
			if (!statH.getOldStat().equals(Constants.ESTAT_INC_FACT.toString())
					&& (statH.getNewStat().equals(Constants.ESTAT_NCONFORME.toString()) || statH.getNewStat().equals(
							Constants.ESTAT_CONFORME.toString()))) {
				throw new es.intos.gdscso.exceptions.AccesoDenegadoException();
			}
		}
		if (procedenciaUser.equals(Recursos.PROCEDENCIA_CSO)) {
			if (!statH.getNewStat().equals(Constants.ESTAT_NCONFORME.toString())
					&& !statH.getNewStat().equals(Constants.ESTAT_CONFORME.toString())) {
				throw new es.intos.gdscso.exceptions.AccesoDenegadoException();
			}
		}
		if (frm.getId() != null && !frm.getId().equals("") && frm.getNewStat() != null && !frm.getNewStat().equals("")) {
			try {
				statH.setId(Integer.parseInt(frm.getId()));
				LNEstats.saveHisEstats(statH);

				// Canviem l'estat
				LNFacturas.changeStateFactura(Integer.parseInt(frm.getId()), Integer.parseInt(frm.getNewStat()));
			} catch (NumberFormatException ne) {
				ActionMessages errors = new ActionMessages();
				errors.add("error", new ActionMessage("estats.erronis"));
				addErrors(request, errors);
				return mapping.findForward("Success");
			}
		}
		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private EstatHistoric copyProperties( GestionFacturasForm frm ) throws IllegalAccessException, InvocationTargetException{

		// traspas del from
		EstatHistoric statHistoric = new EstatHistoric();
		BeanUtils.copyProperties(statHistoric, frm);
		return statHistoric;

	}

	private String setProcedenciaUser( Usuario user ){

		return Utils.procedenciaUser(user);
	}
}
