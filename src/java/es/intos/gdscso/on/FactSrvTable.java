package es.intos.gdscso.on;

@SuppressWarnings("serial")
public class FactSrvTable extends Basic{

	private String	servei;
	private String	volumetria;
	private String	importeOp;
	private String	importe;
	private String	preuOp;
	private int		idSrv;
	private int		idFact;

	// CONTRUCT
	public FactSrvTable() {

		super();
	}

	public FactSrvTable( String servei, String volumetria, String importeOp, String importe ) {

		super();
		this.servei = servei;
		this.volumetria = volumetria;
		this.importeOp = importeOp;
		this.importe = importe;
	}

	public String getPreuOp(){

		return preuOp;
	}

	public void setPreuOp( String preuOp ){

		this.preuOp = preuOp;
	}

	// GETTERS I SETTERS
	public String getServei(){

		return servei;
	}

	public void setServei( String servei ){

		this.servei = servei;
	}

	public String getVolumetria(){

		return volumetria;
	}

	public void setVolumetria( String volumetria ){

		this.volumetria = volumetria;
	}

	public String getImporteOp(){

		return importeOp;
	}

	public void setImporteOp( String importeOp ){

		this.importeOp = importeOp;
	}

	public String getImporte(){

		return importe;
	}

	public void setImporte( String importe ){

		this.importe = importe;
	}

	public int getIdSrv(){

		return idSrv;
	}

	public void setIdSrv( int idSrv ){

		this.idSrv = idSrv;
	}

	public int getIdFact(){

		return idFact;
	}

	public void setIdFact( int idFact ){

		this.idFact = idFact;
	}

}
