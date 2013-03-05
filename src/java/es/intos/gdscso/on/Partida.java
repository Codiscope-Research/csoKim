package es.intos.gdscso.on;

import java.util.List;

@SuppressWarnings("serial")
public class Partida extends Basic{

	private List<Servicio>	servicios;

	public Partida( Integer id, String desc, List<Servicio> list ) {

		super(id, desc);
		this.servicios = list;
	}

	public Partida() {

		super();
	}

	public List<Servicio> getServicios(){

		return servicios;
	}

	public void setServicios( List<Servicio> servicios ){

		this.servicios = servicios;
	}

}
