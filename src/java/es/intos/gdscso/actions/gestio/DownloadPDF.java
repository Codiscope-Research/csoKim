package es.intos.gdscso.actions.gestio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class DownloadPDF extends LogadoBaseAction{

	private String	name	= null;

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

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.write(getPDFOnServer(request));

			// setting some response headers
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setHeader("Content-Disposition", "attachment; filename=fitxaProjecte" + ".pdf");
			// setting the content type
			response.setContentType("application/pdf");
			// the contentlength
			response.setContentLength(baos.size());
			// write ByteArrayOutputStream to the ServletOutputStream
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return null;
	}

	// FUNCTIONS
	private byte[] getPDFOnServer( HttpServletRequest request ) throws IOException{

		try {

			String whereToGet = request.getRealPath("/") + this.name + ".pdf";
			File file = new File(whereToGet);

			return toByteArray(file);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	private byte[] toByteArray( File file ) throws IOException{

		byte[] b = new byte[(int) file.length()];
		try {

			FileInputStream fileInputStream = new FileInputStream(file);

			fileInputStream.read(b);

		} catch (IOException e) {
			throw new IOException("File not found" + e);
		}
		return b;
	}

	private void inizialize( HttpServletRequest request ) throws Exception{

		this.name = request.getParameter("name");

		if (this.name == null || this.name.equals("")) {
			throw new Exception("No PDF name to do lookup");
		}
	}

}
