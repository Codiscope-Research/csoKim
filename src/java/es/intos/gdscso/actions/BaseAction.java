package es.intos.gdscso.actions;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.exceptions.AccesoDenegadoException;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Autorizacion;
import es.intos.util.Usuario;

public abstract class BaseAction extends Action{

	public static Logger	log	= Logger.getLogger(BaseAction.class);

	public ActionForward execute( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response ) throws java.lang.Exception{

		ActionForward actionFoward = null;
		Object oo = null;
		try {
			oo = executeBefore(mapping, form, request, response);
			actionFoward = executeAction(mapping, form, request, response, oo);
		} catch (Exception e) {
			log.error("ERROR - executeAction \n" + es.intos.exception.IntosException.getStackTrace(e));
			throw e;
		} finally {
			executeAfter(mapping, form, request, response, oo);
		}
		return actionFoward;

	}// end execute

	protected Object executeBefore( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response ) throws java.lang.Exception{

		log.debug("Begin Action ::" + this.getClass().getName());

		String Idioma = request.getParameter("Idioma");
		if ("CAT".equals(Idioma)) {
			setLocale(request, new Locale("ca", "ES"));
			log.info("Cambiamos idioma a CAT");
		} else if ("CAS".equals(Idioma)) {
			setLocale(request, new Locale("es", "ES"));
			log.info("Cambiamos idioma a CAS");
		} else {
			setLocale(request, new Locale("ca", "ES"));
			log.info("Cambiamos idioma a CAT");
		}

		if (es.intos.Recursos.memLog.getGestor() == null)
			es.intos.Recursos.memLog.setGestor(Recursos.gbd);
		// synchronized(es.intos.Recursos.memLog) {
		// es.intos.Recursos.memLog.grabaInfoMem(request); }
		return null;
	}// end executeBefore

	protected abstract ActionForward executeAction( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, Object oo ) throws java.lang.Exception;

	protected void executeAfter( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, Object oo ) throws java.lang.Exception{

		/*
		 * synchronized(es.intos.Recursos.memLog) {
		 * es.intos.Recursos.memLog.fueraServlet(); }
		 */

		log.debug("End   Action ::" + this.getClass().getName());

	}// end executeAfter

	public static String toFileName( String nombreFichero ){

		nombreFichero = StringUtils.remove(nombreFichero, '\\');
		nombreFichero = StringUtils.remove(nombreFichero, '/');
		nombreFichero = StringUtils.remove(nombreFichero, ':');
		nombreFichero = StringUtils.remove(nombreFichero, '*');
		nombreFichero = StringUtils.remove(nombreFichero, '?');
		nombreFichero = StringUtils.remove(nombreFichero, '"');
		nombreFichero = StringUtils.remove(nombreFichero, '<');
		nombreFichero = StringUtils.remove(nombreFichero, '>');
		nombreFichero = StringUtils.remove(nombreFichero, '|');
		return nombreFichero;
	}

	public static void flushExcel( String prefixFileName, HttpServletResponse response, HSSFWorkbook workbook, Usuario user )
			throws IOException{

		java.text.SimpleDateFormat sdfFile = new java.text.SimpleDateFormat("dd.MM.yyyy_HH.mm");
		String nombreFichero = prefixFileName + "." + user.getENumCen() + "_" + user.getENomCen() + "_" + user.getNumEmp() + "_"
				+ user.getNomApell() + "_" + sdfFile.format(new Date());
		nombreFichero = toFileName(nombreFichero);

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + nombreFichero + ".xls");
		workbook.write(response.getOutputStream());
	}

	public static void flushPdf( String prefixFileName, HttpServletResponse response, byte[] pdf, Usuario user ) throws IOException{

		java.text.SimpleDateFormat sdfFile = new java.text.SimpleDateFormat("dd.MM.yyyy_HH.mm");
		String nombreFichero = prefixFileName + "." + user.getENumCen() + "_" + user.getENomCen() + "_" + user.getNumEmp() + "_"
				+ user.getNomApell() + "_" + sdfFile.format(new Date());
		nombreFichero = toFileName(nombreFichero);

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=" + nombreFichero + ".pdf");
		response.getOutputStream().write(pdf);
	}

	public static void flushPdfDoc( String prefixFileName, HttpServletResponse response, byte[] pdf, Usuario user ) throws IOException{

		java.text.SimpleDateFormat sdfFile = new java.text.SimpleDateFormat("dd.MM.yyyy_HH.mm");
		String nombreFichero = prefixFileName + "." + user.getENumCen() + "_" + user.getENomCen() + "_" + user.getNumEmp() + "_"
				+ user.getNomApell() + "_" + sdfFile.format(new Date());
		nombreFichero = toFileName(nombreFichero);

		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Content-disposition", "attachment; filename=bar_codes_" + System.currentTimeMillis() + ".pdf");
		// response.setHeader("Content-Disposition", "inline; filename=" +
		// nombreFichero+".pdf");
		response.setHeader("Pragma", "public");
		response.setContentType("application/pdf");
		response.setContentLength(pdf.length);

		log.debug("Escribimos en el OutputStream del response");
		response.getOutputStream().write(pdf);
		response.getOutputStream().close();

	}

	public static void flushFile( String prefixFileName, String fileName, HttpServletResponse response, byte[] pdf, Usuario user )
			throws IOException{

		java.text.SimpleDateFormat sdfFile = new java.text.SimpleDateFormat("dd.MM.yyyy_HH.mm");
		// String
		// nombreFichero=prefixFileName+"."+user.getENumCen()+"_"+user.getENomCen()+"_"+user.getNumEmp()+"_"+user.getNomApell()+"_"+sdfFile.format(new
		// Date());
		String nombreFichero = prefixFileName + "." + user.getENumCen() + "_" + user.getNumEmp() + "_" + sdfFile.format(new Date()) + "_"
				+ fileName;
		nombreFichero = toFileName(nombreFichero);

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + nombreFichero);
		response.getOutputStream().write(pdf);
	}

	public static void check( String consulta, Usuario user ) throws AccesoDenegadoException{

		if (!Autorizacion.check(consulta, user.getENumCen(), user.getNumEmp()))
			throw new AccesoDenegadoException();
	}

	public static void multicheck( String consulta, String consulta2, Usuario user ) throws AccesoDenegadoException{

		if (!Autorizacion.check(consulta, user.getENumCen(), user.getNumEmp())
				&& !Autorizacion.check(consulta2, user.getENumCen(), user.getNumEmp()))
			throw new AccesoDenegadoException();
	}

	public static void multicheck( String[] arrayconsulta, Usuario user ) throws AccesoDenegadoException{

		boolean autorizado = false;
		int i = 0;
		while (!autorizado && i < arrayconsulta.length) {
			autorizado = autorizado || Autorizacion.check(arrayconsulta[i], user.getENumCen(), user.getNumEmp());
			i++;
		}
		if (!autorizado)
			throw new AccesoDenegadoException();
	}

	public static String entreCommetes( String s ){

		return "\"" + StringEscapeUtils.escapeJavaScript(s) + "\"";
	}// end entreCommetes

}
