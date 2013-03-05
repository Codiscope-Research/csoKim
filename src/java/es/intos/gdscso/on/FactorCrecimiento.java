package es.intos.gdscso.on;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
public class FactorCrecimiento extends Basic{

	private Integer	idpartida;

	@Expose
	private Integer	month;
	private Integer	year;

	@Expose
	private Double	factor;

	public FactorCrecimiento() {

		super();
	}

	public Integer getIdpartida(){

		return idpartida;
	}

	public void setIdpartida( Integer idpartida ){

		this.idpartida = idpartida;
	}

	public Integer getMonth(){

		return month;
	}

	public void setMonth( Integer month ){

		this.month = month;
	}

	public Integer getYear(){

		return year;
	}

	public void setYear( Integer year ){

		this.year = year;
	}

	public Double getFactor(){

		return factor;
	}

	public void setFactor( Double factor ){

		this.factor = factor;
	}

}
