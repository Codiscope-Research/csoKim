package es.intos.gdscso.actions.gestio;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class DeletePDF extends LogadoBaseAction{

	private String	name	= null;
	private Integer idFactura = null;

	@Override
	protected ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		try {
			inizialize(request);
		} catch (NumberFormatException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("error.params"));
			addErrors(request, errors);
		}

		try {

			deletePDFOnServer(request);
			LNFacturas.deleteFacturaPDF(idFactura, name);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return mapping.findForward("Success");
	}

	// FUNCTIONS
	private void deletePDFOnServer( HttpServletRequest request ) throws IOException{

		String whereToGet = request.getRealPath("/") + this.name + ".pdf";
		File file = new File(whereToGet);
		file.delete();

	}

	private void inizialize( HttpServletRequest request ) throws Exception{

		this.name = request.getParameter("name");
		this.idFactura = (request.getParameter("idFactura")==null || request.getParameter("idFactura").equals(""))? null :Integer.parseInt(request.getParameter("idFactura"));
		if (this.name == null || this.name.equals("") || this.idFactura==null) {
			throw new Exception("No PDF name to do lookup");
		}
	}

}
