package es.intos.gdscso.forms.modificar;

import es.intos.gdscso.forms.GestionForm;

public class ModificaFacturaForm extends GestionForm{

	private static final long	serialVersionUID	= 1L;

	private String				idFactura;
	private String				serveis;

	public ModificaFacturaForm() {

		super();
	}

	public String getServeis(){

		return serveis;
	}

	public void setServeis( String serveis ){

		this.serveis = serveis;
	}

	public String getIdFactura(){

		return idFactura;
	}

	public void setIdFactura( String idFactura ){

		this.idFactura = idFactura;
	}

}
