package es.intos.gdscso.forms.generar;

public class GenerarFacturasForm extends FactorsForm{

	private static final long	serialVersionUID	= 1L;

	private String				idCso;
	private String				serveis;
	private String				month;
	private String				year;
	private String				code;

	public GenerarFacturasForm() {

		super();
	}

	public String getIdCso(){

		return idCso;
	}

	public void setIdCso( String idCso ){

		this.idCso = idCso;
	}

	public String getServeis(){

		return serveis;
	}

	public void setServeis( String serveis ){

		this.serveis = serveis;
	}

	public String getMonth(){

		return month;
	}

	public void setMonth( String month ){

		this.month = month;
	}

	public String getYear(){

		return year;
	}

	public void setYear( String year ){

		this.year = year;
	}

	public String getCode(){

		return code;
	}

	public void setCode( String code ){

		this.code = code;
	}

}
