package es.intos.gdscso.on;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FacturacioPartidasFirstTable implements Serializable{

	private String	partida;
	private String	ene;
	private String	feb;
	private String	mar;
	private String	abr;
	private String	mai;
	private String	jun;
	private String	jul;
	private String	ago;
	private String	set;
	private String	oct;
	private String	nov;
	private String	des;

	// CONTRUCT
	public FacturacioPartidasFirstTable() {

		super();
	}

	public String getEne(){

		return ene;
	}

	public void setEne( String ene ){

		this.ene = ene;
	}

	public String getFeb(){

		return feb;
	}

	public void setFeb( String feb ){

		this.feb = feb;
	}

	public String getMar(){

		return mar;
	}

	public void setMar( String mar ){

		this.mar = mar;
	}

	public String getAbr(){

		return abr;
	}

	public void setAbr( String abr ){

		this.abr = abr;
	}

	public String getMai(){

		return mai;
	}

	public void setMai( String mai ){

		this.mai = mai;
	}

	public String getJun(){

		return jun;
	}

	public void setJun( String jun ){

		this.jun = jun;
	}

	public String getJul(){

		return jul;
	}

	public void setJul( String jul ){

		this.jul = jul;
	}

	public String getAgo(){

		return ago;
	}

	public void setAgo( String ago ){

		this.ago = ago;
	}

	public String getSet(){

		return set;
	}

	public void setSet( String set ){

		this.set = set;
	}

	public String getOct(){

		return oct;
	}

	public void setOct( String oct ){

		this.oct = oct;
	}

	public String getNov(){

		return nov;
	}

	public void setNov( String nov ){

		this.nov = nov;
	}

	public String getDes(){

		return des;
	}

	public void setDes( String des ){

		this.des = des;
	}

	public String getPartida(){

		return partida;
	}

	public void setPartida( String partida ){

		this.partida = partida;
	}

}
