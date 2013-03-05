package es.intos.gdscso.on;

import es.intos.gdscso.exceptions.ErrorParamsException;

public class FactorsCorreccioFactura extends Basic{

	private static final long	serialVersionUID	= 1L;

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
	private String				code;

	public FactorsCorreccioFactura() {

		super();
	}

	private String getEne(){

		return ene;
	}

	public void setEne( String ene ){

		this.ene = ene;
	}

	private String getFeb(){

		return feb;
	}

	public void setFeb( String feb ){

		this.feb = feb;
	}

	private String getMar(){

		return mar;
	}

	public void setMar( String mar ){

		this.mar = mar;
	}

	private String getAbr(){

		return abr;
	}

	public void setAbr( String abr ){

		this.abr = abr;
	}

	private String getMai(){

		return mai;
	}

	public void setMai( String mai ){

		this.mai = mai;
	}

	private String getJun(){

		return jun;
	}

	public void setJun( String jun ){

		this.jun = jun;
	}

	private String getJul(){

		return jul;
	}

	public void setJul( String jul ){

		this.jul = jul;
	}

	private String getAgo(){

		return ago;
	}

	public void setAgo( String ago ){

		this.ago = ago;
	}

	private String getSet(){

		return set;
	}

	public void setSet( String set ){

		this.set = set;
	}

	private String getOct(){

		return oct;
	}

	public void setOct( String oct ){

		this.oct = oct;
	}

	private String getNov(){

		return nov;
	}

	public void setNov( String nov ){

		this.nov = nov;
	}

	private String getDes(){

		return des;
	}

	public void setDes( String des ){

		this.des = des;
	}

	public String getCode(){

		return code;
	}

	public void setCode( String code ){

		this.code = code;
	}

	public FactorCrecimientoFactura getFactor( int numeroMes ) throws ErrorParamsException{

		FactorCrecimientoFactura factorCrecimiento = new FactorCrecimientoFactura();
		try {
			switch (numeroMes) {
				case 1:
					factorCrecimiento.setFactor((this.getEne() == null || this.getEne().equals("")) ? 0.0 : Double.parseDouble(this
							.getEne()));
					break;
				case 2:
					factorCrecimiento.setFactor((this.getFeb() == null || this.getFeb().equals("")) ? 0.0 : Double.parseDouble(this
							.getFeb()));
					break;
				case 3:
					factorCrecimiento.setFactor((this.getMar() == null || this.getMar().equals("")) ? 0.0 : Double.parseDouble(this
							.getMar()));
					break;
				case 4:
					factorCrecimiento.setFactor((this.getAbr() == null || this.getAbr().equals("")) ? 0.0 : Double.parseDouble(this
							.getAbr()));
					break;
				case 5:
					factorCrecimiento.setFactor((this.getMai() == null || this.getMai().equals("")) ? 0.0 : Double.parseDouble(this
							.getMai()));
					break;
				case 6:
					factorCrecimiento.setFactor((this.getJun() == null || this.getJun().equals("")) ? 0.0 : Double.parseDouble(this
							.getJun()));
					break;
				case 7:
					factorCrecimiento.setFactor((this.getJul() == null || this.getJul().equals("")) ? 0.0 : Double.parseDouble(this
							.getJul()));
					break;
				case 8:
					factorCrecimiento.setFactor((this.getAgo() == null || this.getAgo().equals("")) ? 0.0 : Double.parseDouble(this
							.getAgo()));
					break;
				case 9:
					factorCrecimiento.setFactor((this.getSet() == null || this.getSet().equals("")) ? 0.0 : Double.parseDouble(this
							.getSet()));
					break;
				case 10:
					factorCrecimiento.setFactor((this.getOct() == null || this.getOct().equals("")) ? 0.0 : Double.parseDouble(this
							.getOct()));
					break;
				case 11:
					factorCrecimiento.setFactor((this.getNov() == null || this.getNov().equals("")) ? 0.0 : Double.parseDouble(this
							.getNov()));
					break;
				case 12:
					factorCrecimiento.setFactor((this.getDes() == null || this.getDes().equals("")) ? 0.0 : Double.parseDouble(this
							.getDes()));
					break;
			}
		} catch (NumberFormatException ne) {
			throw new ErrorParamsException();
		}
		factorCrecimiento.setMonth(numeroMes);
		factorCrecimiento.setCodeFactura((this.getCode() == null || this.getCode().equals("")) ? null : this.getCode());
		return factorCrecimiento;
	}

}
