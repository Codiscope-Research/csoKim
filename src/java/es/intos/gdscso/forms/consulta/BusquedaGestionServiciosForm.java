package es.intos.gdscso.forms.consulta;

import es.intos.gdscso.forms.GestionForm;

public class BusquedaGestionServiciosForm extends GestionForm{

	private static final long	serialVersionUID	= 1L;

	private String				f_cso;
	private String				f_srv;
	private String				f_tipo;
	private String				f_estado;
	private String				f_fechaFacDesde;
	private String				f_fechaFacHasta;
	private String				f_voldesde;
	private String				f_volhasta;
	private String				f_impdesde;
	private String				f_imphasta;
	private String				f_mes;

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

	public String getF_srv(){

		return f_srv;
	}

	public void setF_srv( String f_srv ){

		this.f_srv = f_srv;
	}

	public String getF_tipo(){

		return f_tipo;
	}

	public void setF_tipo( String f_tipo ){

		this.f_tipo = f_tipo;
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

	public String getF_voldesde(){

		return f_voldesde;
	}

	public void setF_voldesde( String f_voldesde ){

		this.f_voldesde = f_voldesde;
	}

	public String getF_volhasta(){

		return f_volhasta;
	}

	public void setF_volhasta( String f_volhasta ){

		this.f_volhasta = f_volhasta;
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

	public static long getSerialversionuid(){

		return serialVersionUID;
	}

}
