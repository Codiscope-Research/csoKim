package es.intos.gdscso.on;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
public class FactorCrecimientoFactura extends Basic{

	private String	codeFactura;

	@Expose
	private Integer	month;

	@Expose
	private Double	factor;

	public FactorCrecimientoFactura() {

		super();
	}


	public Integer getMonth(){

		return month;
	}

	public void setMonth( Integer month ){

		this.month = month;
	}	

	public Double getFactor(){

		return factor;
	}

	public void setFactor( Double factor ){

		this.factor = factor;
	}


	public String getCodeFactura(){
	
		return codeFactura;
	}


	public void setCodeFactura( String codeFactura ){
	
		this.codeFactura = codeFactura;
	}


	
	
	

}
