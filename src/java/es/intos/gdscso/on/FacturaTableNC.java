package es.intos.gdscso.on;

@SuppressWarnings("serial")
public class FacturaTableNC extends Basic{

	private Integer		idFactura;
	private String		estat;
	private String		cso;
	private String		year;
	private String		month;
	private String[]	incidencia;
	private Integer		nNoConformitats;

	public FacturaTableNC() {

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

	public Integer getnNoConformitats(){

		return nNoConformitats;
	}

	public void setnNoConformitats( Integer nNoConformitats ){

		this.nNoConformitats = nNoConformitats;
	}

	public String getCso(){

		return cso;
	}

	public void setCso( String cso ){

		this.cso = cso;
	}

}
