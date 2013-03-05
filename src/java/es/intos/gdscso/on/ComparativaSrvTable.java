package es.intos.gdscso.on;

@SuppressWarnings("serial")
public class ComparativaSrvTable extends Basic{

	private String	serveiActual;
	private String	serveiAnterior;
	private String	estat;

	public ComparativaSrvTable() {

		super();
	}

	public String getServeiActual(){

		return serveiActual;
	}

	public void setServeiActual( String serveiActual ){

		this.serveiActual = serveiActual;
	}

	public String getServeiAnterior(){

		return serveiAnterior;
	}

	public void setServeiAnterior( String serveiAnterior ){

		this.serveiAnterior = serveiAnterior;
	}

	public String getEstat(){

		return estat;
	}

	public void setEstat( String estat ){

		this.estat = estat;
	}

}
