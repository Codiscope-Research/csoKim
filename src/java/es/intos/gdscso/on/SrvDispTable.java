package es.intos.gdscso.on;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
public class SrvDispTable extends Basic{

	@Expose
	private String	servei;

	@Expose
	private String	factura;

	@Expose
	private String	estatFactura;

	// CONTRUCT
	public SrvDispTable() {

		super();
	}

	// GETTERS I SETTERS
	public String getServei(){

		return servei;
	}

	public void setServei( String servei ){

		this.servei = servei;
	}

	public String getFactura(){

		return factura;
	}

	public void setFactura( String factura ){

		this.factura = factura;
	}

	public String getEstatFactura(){

		return estatFactura;
	}

	public void setEstatFactura( String estatFactura ){

		this.estatFactura = estatFactura;
	}

}
