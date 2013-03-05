package es.intos.gdscso.forms.admin;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.forms.GestionForm;

public class GestioUsuarisForm extends GestionForm{
	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;
	// paramentros del filtro
	private String				f_matricula;
	private String				f_nom;
	private String				f_pcognom;
	private String				f_scognom;
	private String				f_perfil;

	private String				operacion;
	private String				order_by;

	/** Creates a new instance of AdminEstandard1Form */
	public GestioUsuarisForm() {

	}

	public void reset( ActionMapping mapping, javax.servlet.http.HttpServletRequest request ){

		super.reset(mapping, request);
		this.f_matricula = "";
		this.f_nom = "";
		this.f_pcognom = "";
		this.f_scognom = "";
		this.f_perfil = "";
		this.operacion = "";
	}

	public ActionErrors validate( ActionMapping mapping, javax.servlet.http.HttpServletRequest request ){

		return super.validate(mapping, request);
	}

	public String getF_matricula(){

		return f_matricula;
	}

	public void setF_matricula( String f_matricula ){

		this.f_matricula = f_matricula;
	}

	public String getF_perfil(){

		return f_perfil;
	}

	public void setF_perfil( String f_perfil ){

		this.f_perfil = f_perfil;
	}

	public String getOperacion(){

		return operacion;
	}

	public void setOperacion( String operacion ){

		this.operacion = operacion;
	}

	public String getOrder_by(){

		return order_by;
	}

	public void setOrder_by( String order_by ){

		this.order_by = order_by;
	}

	public String getF_nom(){

		return f_nom;
	}

	public void setF_nom( String f_nom ){

		this.f_nom = f_nom;
	}

	public String getF_pcognom(){

		return f_pcognom;
	}

	public void setF_pcognom( String f_pcognom ){

		this.f_pcognom = f_pcognom;
	}

	public String getF_scognom(){

		return f_scognom;
	}

	public void setF_scognom( String f_scognom ){

		this.f_scognom = f_scognom;
	}

}
