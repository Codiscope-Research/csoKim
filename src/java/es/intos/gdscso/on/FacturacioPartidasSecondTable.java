package es.intos.gdscso.on;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FacturacioPartidasSecondTable implements Serializable{

	private String	partida;
	private String	importpactat;
	private String	importestimat;
	private String	importconsumit;
	private String	rentabilitat;

	// CONTRUCT
	public FacturacioPartidasSecondTable() {

		super();
	}

	public String getPartida(){

		return partida;
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
