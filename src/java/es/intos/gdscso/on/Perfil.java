package es.intos.gdscso.on;

public class Perfil{

	public static String	PERFIL_USUARIO			= "0";
	public static String	PERFIL_ADMINISTRADOR	= "1";

	private String			idPerfil;
	private String			nomPerfil;

	public String getIdPerfil(){

		return idPerfil;
	}

	public void setIdPerfil( String idPerfil ){

		this.idPerfil = idPerfil;
	}

	public String getNomPerfil(){

		return nomPerfil;
	}

	public void setNomPerfil( String nomPerfil ){

		this.nomPerfil = nomPerfil;
	}

}
