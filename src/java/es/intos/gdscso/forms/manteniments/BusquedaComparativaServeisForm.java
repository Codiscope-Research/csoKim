package es.intos.gdscso.forms.manteniments;

import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.forms.GestionForm;

public class BusquedaComparativaServeisForm extends GestionForm{

	private static final long	serialVersionUID	= 1L;

	private String				f_csoActual;
	private String				f_cso;
	private String				f_mes;
	private String				f_any;
	private String				order_by;

	public BusquedaComparativaServeisForm() {

		super();
	}

	public void reset(){

		this.f_any = "";
		this.f_cso = "";
		this.f_mes = "";
		this.f_csoActual = "";
		this.order_by = "";
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

	public String getF_csoActual(){

		return f_csoActual;
	}

	public void setF_csoActual( String f_csoActual ){

		this.f_csoActual = f_csoActual;
	}

}
