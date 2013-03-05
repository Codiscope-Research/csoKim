package es.intos.gdscso.on;

import java.io.Serializable;

@SuppressWarnings({ "serial", "rawtypes" })
public class Basic implements Serializable, Comparable{

	private Integer	id;

	private String	descripcio;

	public Basic() {

	}

	public Basic( Integer id, String descripcio ) {

		this.id = id;
		this.descripcio = descripcio;
	}

	public String getDescripcio(){

		return descripcio;
	}

	public void setDescripcio( String descripcio ){

		this.descripcio = descripcio;
	}

	public Integer getId(){

		return id;
	}

	public void setId( Integer id ){

		this.id = id;
	}

	@Override
	public int compareTo( Object o ){

		Basic b = (Basic) o;
		return this.descripcio.compareTo(b.descripcio);
	}

}
