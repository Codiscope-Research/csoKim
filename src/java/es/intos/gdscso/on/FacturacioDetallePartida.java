package es.intos.gdscso.on;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
public class FacturacioDetallePartida implements Serializable{

	private String	partida;

	@Expose
	private String	importpactat;

	@Expose
	private String	importestimat;

	@Expose
	private String	importconsumit;

	private String	rentabilitat;

	@Expose
	private String	year;

	// CONTRUCT
	public FacturacioDetallePartida() {

		super();
	}

	public String getPartida(){

		return partida;
	}

	public String getYear(){

		return year;
	}

	public void setYear( String year ){

		this.year = year;
	}

	public void setPartida( String partida ){

		this.partida = partida;
	}

	public String getImportpactat(){

		return importpactat;
	}

	public void setImportpactat( String importpactat ){

		this.importpactat = importpactat;
	}

	public String getImportestimat(){

		return importestimat;
	}

	public void setImportestimat( String importestimat ){

		this.importestimat = importestimat;
	}

	public String getImportconsumit(){

		return importconsumit;
	}

	public void setImportconsumit( String importconsumit ){

		this.importconsumit = importconsumit;
	}

	public String getRentabilitat(){

		return rentabilitat;
	}

	public void setRentabilitat( String rentabilitat ){

		this.rentabilitat = rentabilitat;
	}

}
