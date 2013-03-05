package es.intos.gdscso.on;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ControlTable implements Serializable{

	private String	cso;
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
	public ControlTable() {

		super();
	}

	public void setMes( String bola, Integer mes ){

		switch (mes) {
			case 1:
				setEne(bola);
				break;
			case 2:
				setFeb(bola);
				break;
			case 3:
				setMar(bola);
				break;
			case 4:
				setAbr(bola);
				break;
			case 5:
				setMai(bola);
				break;
			case 6:
				setJun(bola);
				break;
			case 7:
				setJul(bola);
				break;
			case 8:
				setAgo(bola);
				break;
			case 9:
				setSet(bola);
				break;
			case 10:
				setOct(bola);
				break;
			case 11:
				setNov(bola);
				break;
			case 12:
				setDes(bola);
				break;
		}

	}

	public String getCso(){

		return cso;
	}

	public void setCso( String cso ){

		this.cso = cso;
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

}
