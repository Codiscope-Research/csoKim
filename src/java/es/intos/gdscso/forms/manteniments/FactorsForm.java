package es.intos.gdscso.forms.manteniments;

import es.intos.gdscso.forms.GestionForm;

public class FactorsForm extends GestionForm{

	private static final long	serialVersionUID	= 1L;

	private String				year;
	private String				ene;
	private String				feb;
	private String				mar;
	private String				abr;
	private String				mai;
	private String				jun;
	private String				jul;
	private String				ago;
	private String				set;
	private String				oct;
	private String				nov;
	private String				des;
	private String				idPartida;
	private String				importpactat;

	public FactorsForm() {

		super();
	}

	public String getYear(){

		return year;
	}

	public void setYear( String year ){

		this.year = year;
	}

	public String getEne(){

		return ene;
	}

	public String getImportpactat(){

		return importpactat;
	}

	public void setImportpactat( String importpactat ){

		this.importpactat = importpactat;
	}

	public String getIdPartida(){

		return idPartida;
	}

	public void setIdPartida( String idPartida ){

		this.idPartida = idPartida;
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

}
