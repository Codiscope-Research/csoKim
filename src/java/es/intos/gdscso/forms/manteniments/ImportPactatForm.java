package es.intos.gdscso.forms.manteniments;

import es.intos.gdscso.forms.GestionForm;

public class ImportPactatForm extends GestionForm{

	private static final long	serialVersionUID	= 1L;

	private String				f_any;
	private String				importe;

	public ImportPactatForm() {

		super();
	}

	public String getF_any(){

		return f_any;
	}

	public void setF_any( String f_any ){

		this.f_any = f_any;
	}

	public String getImporte(){

		return importe;
	}

	public void setImporte( String importe ){

		this.importe = importe;
	}

}
