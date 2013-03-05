package es.intos.gdscso.on;

import java.util.Date;

@SuppressWarnings("serial")
public class EstatHistoric extends Basic{

	private String	newStat;
	private String	oldStat;
	private String	incidencia;
	private Date	dateOfChange;

	public EstatHistoric() {

		super();
	}

	public String getNewStat(){

		return newStat;
	}

	public void setNewStat( String newStat ){

		this.newStat = newStat;
	}

	public String getOldStat(){

		return oldStat;
	}

	public void setOldStat( String oldStat ){

		this.oldStat = oldStat;
	}

	public String getIncidencia(){

		return incidencia;
	}

	public void setIncidencia( String incidencia ){

		this.incidencia = incidencia;
	}

	public Date getDateOfChange(){
	
		return dateOfChange;
	}

	public void setDateOfChange( Date dateOfChange ){
	
		this.dateOfChange = dateOfChange;
	}
	
	

}
