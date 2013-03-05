package es.intos.gdscso.actions.manteniments;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.manteniments.ImportPactatForm;
import es.intos.gdscso.ln.LNImportsPactats;
import es.intos.gdscso.utils.Recursos;
import es.intos.gdscso.utils.Utils;
import es.intos.util.Usuario;

public class SaveImportPactatAction extends LogadoBaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		ImportPactatForm importPactatForm = (ImportPactatForm) form;
		String procedenciaUser = setProcedenciaUser(user);
		if (!procedenciaUser.equals(Recursos.PROCEDENCIA_GDS)) {
			throw new es.intos.gdscso.exceptions.AccesoDenegadoException();
		}
		if (importPactatForm != null && importPactatForm.getF_any() != null && importPactatForm.getImporte() != null) {
			try {
				LNImportsPactats.saveOrUpdateImportPactat(Integer.parseInt(importPactatForm.getF_any()),
						Double.parseDouble(importPactatForm.getImporte()));
				request.setAttribute("importe", importPactatForm.getImporte());
			} catch (NumberFormatException ne) {
				ActionMessages errors = new ActionMessages();
				errors.add("error", new ActionMessage("parametros.incorrectos"));
				addErrors(request, errors);
				return mapping.findForward("Success");
			}
		}

		return mapping.findForward("Success");
	}

	private String setProcedenciaUser( Usuario user ){

		return Utils.procedenciaUser(user);

	}

}
