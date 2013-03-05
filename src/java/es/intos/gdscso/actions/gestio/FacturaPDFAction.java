
package es.intos.gdscso.actions.gestio;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.itextpdf.text.DocumentException;

import es.intos.gdscso.actions.LogadoBaseAction;
import es.intos.gdscso.forms.gestio.GestionFacturasForm;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.FactSrvTable;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.utils.FacturaPDF;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;

public class FacturaPDFAction extends LogadoBaseAction{

	private MessageResources	messages	= null;
	private Integer				idFactura	= null;

	@Override
	protected ActionForward executeLogadoAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Usuario user ) throws Exception{

		check(Recursos.aplicatiu, user);

		try {
			inizialize(request, form);
		} catch (NumberFormatException ne) {
			ActionMessages errors = new ActionMessages();
			errors.add("error", new ActionMessage("error.params"));
			addErrors(request, errors);
		}

		try {
			if (this.idFactura == null)
				return null;
			Vector<FactSrvTable> list = LNFacturas.getTableSrvFactures(this.idFactura, "");
			Vector<Factura> factList = LNFacturas.getFacturas(this.idFactura, null, null, null, null);
			Factura fct = new Factura();
			if (!factList.isEmpty()) {
				fct = factList.get(0);
			}
			FacturaPDF factPDF = new FacturaPDF(messages);
			String ruta = request.getRealPath("/web/img/marcaPDF.png");
			ByteArrayOutputStream baos = factPDF.generaFacturaPDF(list, fct, ruta);
			
			//Guardem en disc una copia de la factura i updatem el nom al camp 
			String name = savePDFToServer(fct.getId(),request,baos);
			LNFacturas.setFacturaPDF(idFactura, name);
			
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

		} catch (DocumentException e) {
			e.printStackTrace();
			throw e;
		}

		return null;
	}

	// FUNCTIONS
	private String savePDFToServer(Integer idFact, HttpServletRequest request, ByteArrayOutputStream baos) throws IOException{
		
		try {
			
			 byte[] bytes = baos.toByteArray();
			 String name= "FAC_"+idFact+"_"+Calendar.getInstance().getTimeInMillis();
			 String whereToSave=request.getRealPath("/")+name+".pdf";
			 FileOutputStream fos = new FileOutputStream(whereToSave);
		     fos.write(bytes);
		     fos.flush();
		     fos.close();			    
			 
		     return name;
			} catch (IOException e) {
			    throw new IOException(e);
			}						
	}
	
	private void inizialize( HttpServletRequest request, ActionForm form ) throws Exception{

		this.messages = getResources(request);
		GestionFacturasForm frm = (GestionFacturasForm) form;
		this.idFactura = frm.getId() == null || frm.getId().equals("") ? null : Integer.parseInt(frm.getId());
	}

}
