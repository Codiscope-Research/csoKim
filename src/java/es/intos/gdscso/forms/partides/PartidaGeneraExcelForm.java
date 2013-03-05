package es.intos.gdscso.forms.partides;

import es.intos.gdscso.forms.GestionForm;

@SuppressWarnings("serial")
public class PartidaGeneraExcelForm extends GestionForm{

	private String	idpartida;
	private String	year;

	public PartidaGeneraExcelForm() {

		super();
	}

	public String getYear(){

		return year;
	}

	public void setYear( String year ){

		this.year = year;
	}

	public String getIdpartida(){

		return idpartida;
	}

	public void setIdpartida( String idpartida ){

		this.idpartida = idpartida;
	}

}
