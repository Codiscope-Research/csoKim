package es.intos.gdscso.actions;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;

public abstract class ExceptionAction extends ExceptionHandler{

	public static Logger	log	= Logger.getLogger(Exception.class);

	public ActionForward execute( ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response ) throws java.lang.Exception{

	
		try {
		
		} catch (Exception e) {
			log.error("ERROR - executeAction \n" + es.intos.exception.IntosException.getStackTrace(e));
			throw e;
		} finally {

		}
		return mapping.findForward("Success");

	}// end execute

}
