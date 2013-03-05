package es.intos.gdscso.forms;

import org.apache.struts.action.ActionMapping;

@SuppressWarnings({ "serial" })
public class GestionForm extends PaginadoForm{

	private String	idTransaccion;

	public void reset( ActionMapping mapping, javax.servlet.http.HttpServletRequest request ){

		super.reset(mapping, request);
	}

	public String getIdTransaccion(){

		return idTransaccion;
	}

	public void setIdTransaccion( String idTransaccion ){

		this.idTransaccion = idTransaccion;
	}

}
