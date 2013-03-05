package es.intos.gdscso.forms.control;

import es.intos.gdscso.forms.GestionForm;

@SuppressWarnings("serial")
public class GestionFacturasGeneraExcelForm extends GestionForm{

	private String	idCso;
	private String	year;
	private String	month;

	public GestionFacturasGeneraExcelForm() {

		super();
	}

	public String getIdCso(){

		return idCso;
	}

	public void setIdCso( String idCso ){

		this.idCso = idCso;
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

}
