package es.intos.gdscso.forms.partes;

import org.apache.struts.action.ActionMapping;

public class BusquedaNoConformidadesFormDTO{

	private String	f_cso;
	private String	f_estado;
	private String	f_idfact;
	private String	f_mes;
	private String	f_any;
	private String	order_by;

	public BusquedaNoConformidadesFormDTO() {

		super();
	}

	public void reset(){

		this.f_any = "";
		this.f_cso = "";
		this.f_estado = "";
		this.f_mes = "";
		this.f_idfact = "";
	}

	public void reset( ActionMapping mapping, javax.servlet.http.HttpServletRequest request ){

		reset();
	}

	public String getF_mes(){

		return f_mes;
	}

	public void setF_mes( String f_mes ){

		this.f_mes = f_mes;
	}

	public String getF_cso(){

		return f_cso;
	}

	public void setF_cso( String f_cso ){

		this.f_cso = f_cso;
	}

	public String getF_estado(){

		return f_estado;
	}

	public void setF_estado( String f_estado ){

		this.f_estado = f_estado;
	}

	public String getF_any(){

		return f_any;
	}

	public void setF_any( String f_any ){

		this.f_any = f_any;
	}

	public String getOrder_by(){

		return order_by;
	}

	public void setOrder_by( String order_by ){

		this.order_by = order_by;
	}

	public String getF_idfact(){

		return f_idfact;
	}

	public void setF_idfact( String f_idfact ){

		this.f_idfact = f_idfact;
	}

}
