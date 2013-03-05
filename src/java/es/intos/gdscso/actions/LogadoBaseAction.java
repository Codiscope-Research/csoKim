package es.intos.gdscso.actions;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.NDC;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.exceptions.SesionCaducadaException;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Autorizacion;
import es.intos.util.Usuario;

public abstract class LogadoBaseAction extends BaseAction{

	protected Object executeBefore( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response ) throws java.lang.Exception{

		HttpSession session = request.getSession();
		Usuario user = null;
		if (null != session)
			user = (Usuario) session.getAttribute("user");
		if (null == user) {
			user = new Usuario();
			user.cargarUsuario(request, response, es.intos.Recursos.modoServidorSeguro);
			if(user.getCentro_con().equals("5316") || user.getCentro_con().equals("7300")){
				user.setPerfil("gdscusa");
			}else{
				user.setPerfil("");
			}
			if (!StringUtils.isEmpty(user.getCentro_con()))
				// user.setJerarquia(LNSolicitudes.consultaJerarquia(user));
				user.setJerarquia(1);
			if (es.intos.Recursos.modoServidorSeguro || "es.intos.segurss".equals(request.getParameter("SEGUR"))) {
				user.setPortal("/SegurSS/Login.do");
			}

		}
		if (StringUtils.isEmpty(user.getNumEmp())) {
			// session.setAttribute("user", null);
			throw new SesionCaducadaException();
		}

		String centroEntra = request.getParameter("t_centroentra");
		if (StringUtils.isNotEmpty(centroEntra)) {
			user.setCentroEntra(centroEntra);
		}
		if (StringUtils.isEmpty(user.getAcces())) {
			user.setAcces("instranet");
		}
		session.setAttribute("user", user);

		NDC(user);

		request.setAttribute(Recursos.aplicatiu, "" + Autorizacion.check(Recursos.aplicatiu, user.getENumCen(), user.getNumEmp()));
		// if("gdsusers".equals(user.getAcces())){
		request.setAttribute("titol", Recursos.titolCUSA);
		request.setAttribute("infoUser", Recursos.infoCUSA);
		/*
		 * } else{ request.setAttribute("titol", Recursos.titolOficina);
		 * request.setAttribute("infoUser", Recursos.infoOficina); }
		 */

		super.executeBefore(mapping, form, request, response);

		return user;
	}// end executeBefore

	protected void NDC( Usuario user ){

		NDC.clear();
		if (null == user)
			return;
		NDC.push(" centre: " + user.getCentro_con() + " centreEntrada: " + user.getCentroEntra() + " us_user: " + user.getNumEmp()
				+ " us_nombre: " + user.getNomCognoms());
	}

	protected ActionForward executeAction( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, Object oo ) throws java.lang.Exception{

		Usuario user = (Usuario) oo;
		return executeLogadoAction(mapping, form, request, response, user);
	}// end executeAction

	protected abstract ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Usuario user )
			throws java.lang.Exception;

	protected void executeAfter( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, Object oo ) throws java.lang.Exception{

		Usuario user = (Usuario) oo;
		user.setIdTransaccion(new Date().getTime() + "");// reseteamos el
															// idtransaction
															// user
		request.setAttribute("user", user); // pasmos simpre el usuario hacia
											// las jsps
		super.executeAfter(mapping, form, request, response, oo);
		NDC.clear();
	}// end executeAfter

}// end LogadoBaseAction
