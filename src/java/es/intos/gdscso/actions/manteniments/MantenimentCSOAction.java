package es.intos.gdscso.actions.manteniments;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class MantenimentCSOAction extends LogadoBaseAction{

	

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);
		inizialize(request);
		
		Vector<Basic> csolist = LNCso.getCSOs();
		request.setAttribute("csos", csolist);
		
		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private void inizialize( HttpServletRequest request ){

	}
}
