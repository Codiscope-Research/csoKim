package es.intos.gdscso.on;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
public class FacturaDialogNC extends Basic{

	@Expose
	private Integer		idFactura;

	@Expose
	private String		estat;

	@Expose
	private String		cso;

	@Expose
	private String		year;

	@Expose
	private String		month;

	@Expose
	private String[]	incidencia;
	
	@Expose
	private String	code;

	public FacturaDialogNC() {

		super();
	}

	public Integer getIdFactura(){

		return idFactura;
	}

	public void setIdFactura( Integer idFactura ){

		this.idFactura = idFactura;
	}

	public String[] getIncidencia(){

		return incidencia;
	}

	public void setIncidencia( String[] incidencia ){

		this.incidencia = incidencia;
	}

	public String getEstat(){

		return estat;
	}

	public void setEstat( String estat ){

		this.estat = estat;
	}

	public String getYear(){

		return year;
	}

	public void setYear( String year ){

		this.year = year;
	}

	public String getMonth(){

		return month;
	}

	public void setMonth( String month ){

		this.month = month;
	}

	public String getCso(){

		return cso;
	}

	public void setCso( String cso ){

		this.cso = cso;
	}

	public String getCode(){
	
		return code;
	}

	public void setCode( String code ){
	
		this.code = code;
	}
	
	

}
