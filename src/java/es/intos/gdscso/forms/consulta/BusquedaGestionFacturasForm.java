package es.intos.gdscso.forms.consulta;

import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.forms.GestionForm;

public class BusquedaGestionFacturasForm extends GestionForm{

	private static final long	serialVersionUID	= 1L;

	private String				f_cso;
	private String				f_estado;
	private String				f_fechaFacDesde;
	private String				f_fechaFacHasta;
	private String				f_impdesde;
	private String				f_imphasta;
	private String				f_mes;
	private String				f_any;
	private String				order_by;

	public BusquedaGestionFacturasForm() {

		super();
	}

	public void reset( ActionMapping arg0, javax.servlet.http.HttpServletRequest request ){

		this.reset();
		super.reset(arg0, request);
	}

	public void reset(){

		this.f_any = "";
		this.f_cso = "";
		this.f_estado = "";
		this.f_fechaFacDesde = "";
		this.f_fechaFacHasta = "";
		this.f_impdesde = "";
		this.f_imphasta = "";
		this.f_mes = "";
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

	public String getF_fechaFacDesde(){

		return f_fechaFacDesde;
	}

	public void setF_fechaFacDesde( String f_fechaFacDesde ){

		this.f_fechaFacDesde = f_fechaFacDesde;
	}

	public String getF_fechaFacHasta(){

		return f_fechaFacHasta;
	}

	public void setF_fechaFacHasta( String f_fechaFacHasta ){

		this.f_fechaFacHasta = f_fechaFacHasta;
	}

	public String getF_impdesde(){

		return f_impdesde;
	}

	public void setF_impdesde( String f_impdesde ){

		this.f_impdesde = f_impdesde;
	}

	public String getF_imphasta(){

		return f_imphasta;
	}

	public void setF_imphasta( String f_imphasta ){

		this.f_imphasta = f_imphasta;
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

}
