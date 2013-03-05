package es.intos.gdscso.on;

@SuppressWarnings("serial")
public class ServicioFact extends Servicio{

	private Integer	idFactura;
	private Double	volumetria;
	private Integer	idPrecio;
	private String action;

	public ServicioFact() {

		super();
		this.action="";
	}

	public Integer getIdFactura(){

		return idFactura;
	}

	public void setIdFactura( Integer idFactura ){

		this.idFactura = idFactura;
	}

	public Integer getIdServicio(){

		return this.getId();
	}

	public void setIdServicio( Integer idServicio ){

		this.setId(idServicio);
	}

	public Double getVolumetria(){

		return volumetria;
	}

	public void setVolumetria( Double volumetria ){

		this.volumetria = volumetria;
	}

	public Integer getIdPrecio(){

		return idPrecio;
	}

	public void setIdPrecio( Integer idPrecio ){

		this.idPrecio = idPrecio;
	}

	public String getAction(){
	
		return action;
	}

	public void setAction( String action ){
	
		this.action = action;
	}
	

}
