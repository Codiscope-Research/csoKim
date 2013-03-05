package es.intos.gdscso.on;

import java.util.Date;

public class GdsactivosUsuari{

	private int		idUsuari;
	private String	matricula;
	private String	nomUsuari;
	private String	pcognomUsuari;
	private String	scognomUsuari;
	private String	perfil;
	private String	perfildesc;
	private Date	faltaD;
	private String	falta;
	private String	useralta;
	private Date	fmodifD;
	private String	fmodif;
	private String	usermodif;
	private String	email;
	private String	telefono;

	public String getNomComplert(){

		return nomUsuari + " " + pcognomUsuari + " " + scognomUsuari;
	}

	public int getIdUsuari(){

		return idUsuari;
	}

	public void setIdUsuari( int idUsuari ){

		this.idUsuari = idUsuari;
	}

	public String getMatricula(){

		return matricula;
	}

	public void setMatricula( String matricula ){

		this.matricula = matricula;
	}

	public String getPerfil(){

		return perfil;
	}

	public void setPerfil( String perfil ){

		this.perfil = perfil;
	}

	public Date getFaltaD(){

		return faltaD;
	}

	public void setFaltaD( Date faltaD ){

		this.faltaD = faltaD;
	}

	public String getFalta(){

		return falta;
	}

	public void setFalta( String falta ){

		this.falta = falta;
	}

	public String getUseralta(){

		return useralta;
	}

	public void setUseralta( String useralta ){

		this.useralta = useralta;
	}

	public Date getFmodifD(){

		return fmodifD;
	}

	public void setFmodifD( Date fmodifD ){

		this.fmodifD = fmodifD;
	}

	public String getFmodif(){

		return fmodif;
	}

	public void setFmodif( String fmodif ){

		this.fmodif = fmodif;
	}

	public String getUsermodif(){

		return usermodif;
	}

	public void setUsermodif( String usermodif ){

		this.usermodif = usermodif;
	}

	public String getNomUsuari(){

		return nomUsuari;
	}

	public void setNomUsuari( String nomUsuari ){

		this.nomUsuari = nomUsuari;
	}

	public String getPcognomUsuari(){

		return pcognomUsuari;
	}

	public void setPcognomUsuari( String pcognomUsuari ){

		this.pcognomUsuari = pcognomUsuari;
	}

	public String getScognomUsuari(){

		return scognomUsuari;
	}

	public void setScognomUsuari( String scognomUsuari ){

		this.scognomUsuari = scognomUsuari;
	}

	public String getPerfildesc(){

		return perfildesc;
	}

	public void setPerfildesc( String perfildesc ){

		this.perfildesc = perfildesc;
	}

	public String getEmail(){

		return email;
	}

	public void setEmail( String email ){

		this.email = email;
	}

	public String getTelefono(){

		return telefono;
	}

	public void setTelefono( String telefono ){

		this.telefono = telefono;
	}

}
