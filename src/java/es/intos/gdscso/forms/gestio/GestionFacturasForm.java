package es.intos.gdscso.forms.gestio;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionMapping;

import es.intos.gdscso.forms.GestionForm;
import es.intos.gdscso.on.EstatHistoric;

public class GestionFacturasForm extends GestionForm{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String				id;
	private String				observaciones;
	private Date				fentrada;
	private Date				fulltimestat;
	private Integer				idCSO;
	private Integer				month;
	private Integer				year;
	private Integer				idEstat;
	private String				nomEstat;
	private Double				importe;
	private String				descripcio;
	private String				newStat;
	private String				oldStat;
	private String				incidencia;
	private List<EstatHistoric>	fulltimeHistoricEstat;
	private String				nomCso;
	private String				code;

	public GestionFacturasForm() {

		super();
	}

	public void reset(){

		this.observaciones = "";
		this.id = "";
		this.fentrada = null;
		this.idCSO = null;
		this.fulltimestat = null;
		this.month = null;
		this.year = null;
		this.idEstat = null;
		this.nomEstat = null;
		this.importe = null;
		this.descripcio = "";
		this.newStat = "";
		this.oldStat = "";
		this.incidencia = "";
	}

	public void reset( ActionMapping mapping, javax.servlet.http.HttpServletRequest request ){

		if (request.getParameter("test.reset") == null || request.getParameter("test.reset").equals("true")) {
			reset();
		}
	}

	public Date getFentrada(){

		return fentrada;
	}

	public void setFentrada( Date fentrada ){

		this.fentrada = fentrada;
	}

	public Date getFulltimestat(){

		return fulltimestat;
	}

	public String getNomCso(){

		return nomCso;
	}

	public void setNomCso( String nomCso ){

		this.nomCso = nomCso;
	}

	public void setFulltimestat( Date fulltimestat ){

		this.fulltimestat = fulltimestat;
	}

	public Integer getIdCSO(){

		return idCSO;
	}

	public void setIdCSO( Integer idCSO ){

		this.idCSO = idCSO;
	}

	public Integer getMonth(){

		return month;
	}

	public void setMonth( Integer month ){

		this.month = month;
	}

	public Integer getYear(){

		return year;
	}

	public void setYear( Integer year ){

		this.year = year;
	}

	public Integer getIdEstat(){

		return idEstat;
	}

	public void setIdEstat( Integer idEstat ){

		this.idEstat = idEstat;
	}

	public String getNomEstat(){

		return nomEstat;
	}

	public void setNomEstat( String nomEstat ){

		this.nomEstat = nomEstat;
	}

	public Double getImporte(){

		return importe;
	}

	public void setImporte( Double importe ){

		this.importe = importe;
	}

	public String getId(){

		return id;
	}

	public void setId( String id ){

		this.id = id;
	}

	public String getObservaciones(){

		return observaciones;
	}

	public void setObservaciones( String observaciones ){

		this.observaciones = observaciones;
	}

	public String getDescripcio(){

		return descripcio;
	}

	public void setDescripcio( String descripcio ){

		this.descripcio = descripcio;
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

	public List<EstatHistoric> getFulltimeHistoricEstat(){

		return fulltimeHistoricEstat;
	}

	public void setFulltimeHistoricEstat( List<EstatHistoric> fulltimeHistoricEstat ){

		this.fulltimeHistoricEstat = fulltimeHistoricEstat;
	}

	public String getCode(){

		return code;
	}

	public void setCode( String code ){

		this.code = code;
	}

}
