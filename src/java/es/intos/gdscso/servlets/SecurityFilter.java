package es.intos.gdscso.servlets;

import es.intos.gdscso.utils.Recursos;
import es.intos.util.BasicDataCookie;
import es.intos.util.SecureCookieProtocol;
import es.intos.util.Usuario;
import java.io.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter{
	// private FilterConfig config = null;
	public void init( FilterConfig config ) throws ServletException{

		// this.config = config;
	}

	public void destroy(){

		// config = null;
	}

	public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException, ServletException{

		// String url = ((HttpServletRequest)req).getRequestURL().toString();

		// es.intos.Recursos.modoServidorSeguro=true;//CODI PER FORÇAR QUE
		// SEMPRE ENTRI COM A SERVIDOR SEGUR

		if (!es.intos.Recursos.modoServidorSeguro && !"es.intos.segurss".equals(req.getParameter("SEGUR"))) {
			chain.doFilter(req, resp);
			return;
		}

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		BasicDataCookie basicData = new BasicDataCookie();
		int result = SecureCookieProtocol.authentication(basicData, request, response);
		if (0 != result) {
			request.getSession().setAttribute("user", null);
			response.sendRedirect("/SegurSS/SubmitLoginAction.do");
			return;
		}

		// //////////////////////////////////////////////////////// mirar si
		// user caducado
		if (basicData.esUsuarioCaducado()) {
			request.getSession().setAttribute("user", null);
			response.sendRedirect("/SegurSS/SubmitLoginAction.do");
			return;
		}
		// //////////////////////////////////////////////////////// mirar si
		// user caducado

		if (!tienePermiso(basicData, request)) {
			request.getSession().setAttribute("user", null);
			response.sendRedirect("/SegurSS/SubmitLoginAction.do");
			return;
		}

		chain.doFilter(request, response);

		// if (!tienePermiso(app, request)) {
		// response.sendRedirect("/SegurSS/SubmitLoginAction.do");
		// return;
		// }
	}// end doFilter

	private static boolean tienePermiso( BasicDataCookie basicData, HttpServletRequest request ){

		String app = basicData.getapplication();
		if (null == app)
			return false; // no esta asegurado el acceso a la aplicacion
		if (!Recursos.aplicatiu.equals(app))
			return false;

		Usuario usuario = (Usuario) request.getSession().getAttribute("user");
		if (null == usuario)
			return true;// es la primera vez que entra

		String numEmp = basicData.getNumEmp();
		if (!numEmp.equals(usuario.getNumEmp())) {
			request.getSession().setAttribute("user", null);
			return true;
		}

		return true;
	}// end tienePermiso

}// end SecurityFilter