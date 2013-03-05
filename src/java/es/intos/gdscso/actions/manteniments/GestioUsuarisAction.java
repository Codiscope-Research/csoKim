package es.intos.gdscso.actions.manteniments;

import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.admin.GestioUsuarisForm;
import es.intos.gdscso.ln.LNGestioUsuaris;
import es.intos.gdscso.on.GdsactivosPerfil;
import es.intos.gdscso.on.GdsactivosUsuari;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.PaginacionBD;
import es.intos.util.Usuario;

public class GestioUsuarisAction extends LogadoBaseAction{

	@Override
	protected ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		GestioUsuarisForm frm = (GestioUsuarisForm) form;
		Locale locale = getLocale(request);
		ActionMessages errors = new ActionMessages();
		ActionMessages messages = new ActionMessages();

		if (!"CONSULTA".equals(frm.getOperacion())) {
			if (frm.getIdTransaccion().equals(user.getIdTransaccion())) {
				if (LNGestioUsuaris.update(frm, user, errors, messages) == 0)
					LNGestioUsuaris.insert(frm, user, errors, messages);
				if (errors.isEmpty())
					frm.reset(mapping, request);
			} else {
				frm.reset(mapping, request);
			}
		}
		GestioUsuarisForm frm2 = new GestioUsuarisForm();
		frm2.setOrder_by(frm.getOrder_by());
		frm2.setOperacion(frm.getOperacion());
		frm2.setPagina(frm.getPagina());
		frm2.setRpp(frm.getRpp());
		int numReg = LNGestioUsuaris.consultaNumReg(user, locale);
		Vector<GdsactivosUsuari> lista = LNGestioUsuaris.consultaList(frm2, user, locale); // todos

		request.setAttribute("OrderBy_ASC", LNGestioUsuaris.getOrderBy_ASC());
		request.setAttribute("OrderBy_DESC", LNGestioUsuaris.getOrderBy_DESC());

		if (lista.size() > 0) {
			PaginacionBD paginacion = new PaginacionBD(lista, frm.getRpp(), frm.getPagina(), numReg);
			request.setAttribute("paginacion", paginacion);
		}

		Vector<GdsactivosPerfil> listPerfils = LNGestioUsuaris.consultaPerfils(user, locale); // todos
		request.setAttribute("listPerfils", listPerfils);

		if (!errors.isEmpty()) {
			frm.reset(mapping, request);
			this.saveErrors(request, errors);
			return mapping.findForward("Failure");
		}

		this.saveMessages(request, messages);

		return mapping.findForward("Success");
	}

}
