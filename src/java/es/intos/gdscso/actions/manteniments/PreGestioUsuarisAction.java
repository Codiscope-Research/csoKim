package es.intos.gdscso.actions.manteniments;

import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.admin.GestioUsuarisForm;
import es.intos.gdscso.ln.LNGestioUsuaris;
import es.intos.gdscso.on.GdsactivosPerfil;
import es.intos.gdscso.on.GdsactivosUsuari;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.PaginacionBD;
import es.intos.util.Usuario;

public class PreGestioUsuarisAction extends LogadoBaseAction{

	public ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		GestioUsuarisForm frm = (GestioUsuarisForm) form;
		frm.setPagina(1);
		frm.setRpp(Recursos.rppDefecto);
		Locale locale = getLocale(request);

		int numReg = LNGestioUsuaris.consultaNumReg(user, locale);
		Vector<GdsactivosUsuari> lista = LNGestioUsuaris.consultaList(frm, user, locale); // todos

		request.setAttribute("OrderBy_ASC", LNGestioUsuaris.getOrderBy_ASC());
		request.setAttribute("OrderBy_DESC", LNGestioUsuaris.getOrderBy_DESC());

		if (lista.size() > 0) {
			PaginacionBD paginacion = new PaginacionBD(lista, frm.getRpp(), frm.getPagina(), numReg);
			request.setAttribute("paginacion", paginacion);
		}

		Vector<GdsactivosPerfil> listPerfils = LNGestioUsuaris.consultaPerfils(user, locale); // todos
		request.setAttribute("listPerfils", listPerfils);

		return mapping.findForward("Success");
	}

}
