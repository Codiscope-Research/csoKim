package es.intos.gdscso.on;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Factura extends Basic{

	private List<FacturaHistoric>	historic;
	private List<ServicioFact>		servicios;
	private Date					fentrada;
	private Date					fulltimestat;
	private Integer					idCSO;
	private Integer					month;
	private Integer					year;
	private Integer					idEstat;
	private String					nomEstat;
	private Double					importe;
	private String					nomCso;
	private String					code;
	private String 					pdf_names;

	public Factura( Integer id, String desc, List<FacturaHistoric> listFactHis, List<ServicioFact> servicios ) {

		super(id, desc);
		this.historic = listFactHis;
		this.servicios = servicios;
	}

	public Factura() {

		super();
	}

	public List<FacturaHistoric> getHistoric(){

		return historic;
	}

	public void setHistoric( List<FacturaHistoric> historic ){

		this.historic = historic;
	}

	public List<ServicioFact> getServicios(){

		return servicios;
	}

	public void setServicios( List<ServicioFact> servicios ){

		this.servicios = servicios;
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

	public Double getImporte(){

		return importe;
	}

	public void setImporte( Double importe ){

		this.importe = importe;
	}

	public String getNomEstat(){

		return nomEstat;
	}

	public void setNomEstat( String nomEstat ){

		this.nomEstat = nomEstat;
	}

	public String getNomCso(){

		return nomCso;
	}

	public void setNomCso( String nomCso ){

		this.nomCso = nomCso;
	}

	public String getCode(){

		return code;
	}

	public void setCode( String code ){

		this.code = code;
	}

	public String getPdf_names(){
	
		return pdf_names;
	}

	public void setPdf_names( String pdf_names ){
	
		this.pdf_names = pdf_names;
	}
	

}
