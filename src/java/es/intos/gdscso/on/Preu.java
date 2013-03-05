package es.intos.gdscso.on;

import java.util.List;

@SuppressWarnings("serial")
public class Preu extends Basic{

	private List<Tram>	trams;
	private Integer		idCSO;
	private Integer		idSRV;
	private Double		mensual;

	public Preu( Integer id, String desc, Integer cso, Integer srv, List<Tram> trams, Double mensual ) {

		super(id, desc);
		this.trams = trams;
		this.idCSO = cso;
		this.idSRV = srv;
		this.mensual=mensual;
	}

	public Preu() {

		super();
	}

	public List<Tram> getTrams(){

		return trams;
	}

	public void setTrams( List<Tram> trams ){

		this.trams = trams;
	}

	public Integer getIdCSO(){

		return idCSO;
	}

	public void setIdCSO( Integer idCSO ){

		this.idCSO = idCSO;
	}

	public Integer getIdSRV(){

		return idSRV;
	}

	public void setIdSRV( Integer idSRV ){

		this.idSRV = idSRV;
	}

	public Double getMensual(){
	
		return mensual;
	}

	public void setMensual( Double mensual ){
	
		this.mensual = mensual;
	}

	
}
