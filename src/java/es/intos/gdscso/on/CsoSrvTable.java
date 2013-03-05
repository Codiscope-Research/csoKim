package es.intos.gdscso.on;


@SuppressWarnings("serial")
public class CsoSrvTable extends Basic{

	private String	cso;

	private String	tServeis;

	private String	tFactures;

	private String	estat;

	// CONTRUCT
	public CsoSrvTable() {

		super();
	}

	// GETTERS I SETTERS
	public String getCso(){

		return cso;
	}

	public void setCso( String cso ){

		this.cso = cso;
	}

	public String gettServeis(){

		return tServeis;
	}

	public void settServeis( String tServeis ){

		this.tServeis = tServeis;
	}

	public String gettFactures(){

		return tFactures;
	}

	public void settFactures( String tFactures ){

		this.tFactures = tFactures;
	}

	public String getEstat(){

		return estat;
	}

	public void setEstat( String estat ){

		this.estat = estat;
	}

}
