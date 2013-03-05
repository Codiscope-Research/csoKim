package es.intos.gdscso.on;

import java.util.Date;

@SuppressWarnings("serial")
public class FacturaHistoric extends Basic{

	private Integer	idFactura;
	private Integer	idEstat;
	private Date	dataEstat;
	private Integer	idEstatAnterior;
	private Date	dataEstatAnterior;
	private String	incidencia;

	public FacturaHistoric( Integer id, String desc, Integer idFactura, Integer idEstat, Date dataEstat, Integer idEstatAnterior,
			Date dataEstatAnterior, String incidencia ) {

		super(id, desc);
		this.idFactura = idFactura;
		this.idEstat = idEstat;
		this.dataEstat = dataEstat;
		this.idEstatAnterior = idEstatAnterior;
		this.dataEstatAnterior = dataEstatAnterior;
		this.incidencia = incidencia;
	}

	public FacturaHistoric() {

		super();
	}

	public Integer getIdFactura(){

		return idFactura;
	}

	public void setIdFactura( Integer idFactura ){

		this.idFactura = idFactura;
	}

	public Integer getIdEstat(){

		return idEstat;
	}

	public void setIdEstat( Integer idEstat ){

		this.idEstat = idEstat;
	}

	public Date getDataEstat(){

		return dataEstat;
	}

	public void setDataEstat( Date dataEstat ){

		this.dataEstat = dataEstat;
	}

	public Integer getIdEstatAnterior(){

		return idEstatAnterior;
	}

	public void setIdEstatAnterior( Integer idEstatAnterior ){

		this.idEstatAnterior = idEstatAnterior;
	}

	public Date getDataEstatAnterior(){

		return dataEstatAnterior;
	}

	public void setDataEstatAnterior( Date dataEstatAnterior ){

		this.dataEstatAnterior = dataEstatAnterior;
	}

	public String getIncidencia(){

		return incidencia;
	}

	public void setIncidencia( String incidencia ){

		this.incidencia = incidencia;
	}

}
