package es.intos.gdscso.on;

@SuppressWarnings("serial")
public class Centro extends Basic{

	private String	responsable;
	private String	nombreResponsable;
	private String	nombreResponsableArea;
	private String	centroSuperior;
	private String	area;
	private String	dg;
	private String	gerencia;
	private String	jerarquia;

	public Centro( Integer id, String desc, String responsable, String centroSuperior, String area, String dg, String gerencia,
			String nombreResponsable, String nombreResponsableArea, String jerarquia ) {

		super(id, desc);
		this.responsable = responsable;
		this.nombreResponsable = nombreResponsable;
		this.nombreResponsableArea = nombreResponsableArea;
		this.centroSuperior = centroSuperior;
		this.area = area;
		this.dg = dg;
		this.gerencia = gerencia;
		this.jerarquia = jerarquia;
	}

	public Centro() {

		super();
	}

	// GETTERS I SETTERS
	public String getResponsable(){

		return responsable;
	}

	public String getArea(){

		return area;
	}

	public void setArea( String area ){

		this.area = area;
	}

	public String getDg(){

		return dg;
	}

	public void setDg( String dg ){

		this.dg = dg;
	}

	public String getGerencia(){

		return gerencia;
	}

	public void setGerencia( String gerencia ){

		this.gerencia = gerencia;
	}

	public void setResponsable( String responsable ){

		this.responsable = responsable;
	}

	public String getCentroSuperior(){

		return centroSuperior;
	}

	public void setCentroSuperior( String centroSuperior ){

		this.centroSuperior = centroSuperior;
	}

	public String getNombreResponsable(){

		return nombreResponsable;
	}

	public void setNombreResponsable( String nombreResponsable ){

		this.nombreResponsable = nombreResponsable;
	}

	public String getJerarquia(){

		return jerarquia;
	}

	public void setJerarquia( String jerarquia ){

		this.jerarquia = jerarquia;
	}

	public String getNombreResponsableArea(){

		return nombreResponsableArea;
	}

	public void setNombreResponsableArea( String nombreResponsableArea ){

		this.nombreResponsableArea = nombreResponsableArea;
	}

}
