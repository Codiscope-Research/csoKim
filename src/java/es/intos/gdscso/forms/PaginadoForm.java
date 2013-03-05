package es.intos.gdscso.forms;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import es.intos.gdscso.utils.Recursos;

@SuppressWarnings({ "serial" })
public class PaginadoForm extends ValidatorForm{

	// parametros para paginacion
	private int	pagina;
	private int	rpp;

	/** Creates a new instance of PaginadoForm */
	public PaginadoForm() {

	}

	public int getPagina(){

		return pagina;
	}

	public void setPagina( int pagina ){

		this.pagina = pagina;
	}

	public int getRpp(){

		return rpp;
	}

	public void setRpp( int rpp ){

		this.rpp = rpp;
	}

	public void reset( ActionMapping mapping, javax.servlet.http.HttpServletRequest request ){

		this.pagina = 1;
		this.rpp = Recursos.rppDefecto;
		super.reset(mapping, request);
	}
	/*
	 * public ActionErrors validate(ActionMapping mapping,
	 * javax.servlet.http.HttpServletRequest request) { ActionErrors
	 * actionErrors = new ActionErrors();
	 * 
	 * actionErrors.add(ActionMessages.GLOBAL_MESSAGE, new
	 * ActionMessage("error.database.missing"));
	 * 
	 * return actionErrors; }
	 */

}
