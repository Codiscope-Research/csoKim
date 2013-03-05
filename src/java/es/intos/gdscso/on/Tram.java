package es.intos.gdscso.on;

@SuppressWarnings("serial")
public class Tram extends Basic{

	private Integer	numTram;
	private Double	desde;
	private Double	hasta;
	private Double	preu;

	public Tram() {

		super();
	}

	public Tram( Integer numTram, Double desde, Double hasta, Double preu ) {

		super();
		this.numTram = numTram;
		this.desde = desde;
		this.hasta = hasta;
		this.preu = preu;
	}

	public Integer getNumTram(){

		return numTram;
	}

	public void setNumTram( Integer numTram ){

		this.numTram = numTram;
	}

	public Double getDesde(){

		return desde;
	}

	public void setDesde( Double desde ){

		this.desde = desde;
	}

	public Double getHasta(){

		return hasta;
	}

	public void setHasta( Double hasta ){

		this.hasta = hasta;
	}

	public Double getPreu(){

		return preu;
	}

	public void setPreu( Double preu ){

		this.preu = preu;
	}

}
