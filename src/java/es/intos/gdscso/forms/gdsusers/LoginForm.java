package es.intos.gdscso.forms.gdsusers;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.forms.GestionForm;

@SuppressWarnings({ "serial" })
public class LoginForm extends GestionForm{
	// paramentros del filtro
	private String	f_user;
	private String	f_pass;
	private String	f_centre;

	private String	operacion;

	/** Creates a new instance of AltaSolicitudesForm */
	public LoginForm() {

	}

	public void reset( ActionMapping mapping, javax.servlet.http.HttpServletRequest request ){

		super.reset(mapping, request);
		this.f_user = "";
		this.f_pass = "";
		this.f_centre = "";
		this.operacion = "";
	}

	public ActionErrors validate( ActionMapping mapping, javax.servlet.http.HttpServletRequest request ){

		return super.validate(mapping, request);
	}

	public String getF_user(){

		return f_user;
	}

	public void setF_user( String f_user ){

		this.f_user = f_user;
	}

	public String getF_pass(){

		return f_pass;
	}

	public void setF_pass( String f_pass ){

		this.f_pass = f_pass;
	}

	public String getF_centre(){

		return f_centre;
	}

	public void setF_centre( String f_centre ){

		this.f_centre = f_centre;
	}

	public String getOperacion(){

		return operacion;
	}

	public void setOperacion( String operacion ){

		this.operacion = operacion;
	}
}
