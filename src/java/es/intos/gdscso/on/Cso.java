package es.intos.gdscso.on;

@SuppressWarnings("serial")
public class Cso extends Basic{

	private boolean	iva;
	private boolean	igic;
	private Double	descuento;
	private Double	impuesto;
	private String	servicioRecurso;
	private Double	nrecursos;
	private Double	preuunitari;

	public Cso() {

		super();
	}

	public Cso( boolean iva, boolean igic, Double descuento, Double impuesto, String servicioRecurso ) {

		super();
		this.iva = iva;
		this.igic = igic;
		this.descuento = descuento;
		this.impuesto = impuesto;
		this.servicioRecurso = servicioRecurso;
	}

	// GETTERS I SETTERS
	public boolean isIva(){

		return iva;
	}

	public void setIva( boolean iva ){

		this.iva = iva;
	}

	public boolean isIgic(){

		return igic;
	}

	public void setIgic( boolean igic ){

		this.igic = igic;
	}

	public Double getDescuento(){

		return descuento;
	}

	public void setDescuento( Double descuento ){

		this.descuento = descuento;
	}

	public Double getImpuesto(){

		return impuesto;
	}

	public void setImpuesto( Double impuesto ){

		this.impuesto = impuesto;
	}

	public String getServicioRecurso(){

		return servicioRecurso;
	}

	public void setServicioRecurso( String servicioRecurso ){

		this.servicioRecurso = servicioRecurso;
	}

	public Double getNrecursos(){

		return nrecursos;
	}

	public void setNrecursos( Double nrecursos ){

		this.nrecursos = nrecursos;
	}

	public Double getPreuunitari(){

		return preuunitari;
	}

	public void setPreuunitari( Double preuunitari ){

		this.preuunitari = preuunitari;
	}

}
