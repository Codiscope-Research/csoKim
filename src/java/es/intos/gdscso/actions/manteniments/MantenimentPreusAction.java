package es.intos.gdscso.actions.manteniments;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.ln.LNServeis;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class MantenimentPreusAction extends LogadoBaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		// recuperar la llista de cso's i els seus serveis.
		Vector<Basic> csolist = LNCso.getCSOs();
		request.setAttribute("csos", csolist);

		Vector<Basic> srvs = LNServeis.getServeis();
		request.setAttribute("srvs", srvs);

		return mapping.findForward("Success");
	}

}
