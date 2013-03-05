package es.intos.gdscso.on;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
public class SrvCSOTable extends Basic{

	@Expose
	private String	servei;

	@Expose
	private String	factura;

	@Expose
	private String	volum;

	@Expose
	private String	estatFactura;

	private int		idFact;

	@Expose
	private String	preu;

	// CONTRUCT
	public SrvCSOTable() {

		super();
	}

	// GETTERS I SETTERS
	public String getServei(){

		return servei;
	}

	public void setServei( String servei ){

		this.servei = servei;
	}

	public int getIdFact(){

		return idFact;
	}

	public void setIdFact( int idFact ){

		this.idFact = idFact;
	}

	public String getFactura(){

		return factura;
	}

	public void setFactura( String factura ){

		this.factura = factura;
	}

	public String getVolum(){

		return volum;
	}

	public void setVolum( String volum ){

		this.volum = volum;
	}

	public String getEstatFactura(){

		return estatFactura;
	}

	public void setEstatFactura( String estatFactura ){

		this.estatFactura = estatFactura;
	}

	public String getPreu(){

		return preu;
	}

	public void setPreu( String preu ){

		this.preu = preu;
	}

}
