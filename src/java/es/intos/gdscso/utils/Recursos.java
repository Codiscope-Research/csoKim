package es.intos.gdscso.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.intos.util.sql.GestorConexionesBD;

public final class Recursos{

	public static String				diccionario			= "diccionario_LOCAL";
	public static String				nombd				= "gdsodm_LOCAL";
	public static String				contexte			= "Gdscso";
	public static String				aplicatiu			= "GDSCSO";
	public static boolean				pruebas				= true;

	public static String				titolOficina		= "titol.jsp";
	public static String				titolCUSA			= "titolCusa.jsp";
	public static String				infoCUSA			= "infoCusa.jsp";
	public static String				infoOficina			= "info.jsp";

	public static String[]				CENTROS_GDS			= new String[] { "5316", "7300" };
	public static String				PROCEDENCIA_GDS		= "gdscusa";
	public static String				PROCEDENCIA_CSO		= "cso";

	public static String				version				= "v.1.0.0";

	public static int					rppDefecto			= 30;
	// public static MemLog memLog;

	public static GestorConexionesBD	gbd;
	public static String				EMAIL_SENDER		= "joaquim.orra@gmail.com";

	public static String				EMAIL_TO			= "joaquim.orra@gmail.com";

	public static String[]				EMAIL_PROVS		= new String[] { "joaquim.orra@gmail.com" };

	// Dades per l'enviament de mails***********************************
	// public static String HOST="217.16.255.65";//HOST OK de GDS
	public static String				HOST				= "192.168.44.20";								// HOST
																											// INTOS
																											// PROVES
	public final static String			HOST_SEGURO			= "10.1.1.20";
	// public final static String FROM ="subrogaciones@gdscusa.es";
	public final static String			FROM				= "joancarles.duran@serviciosbancarios.com";
	public final static String			TO					= "@lacaixa.es";
	public static boolean				modoServidorSeguro	= false;
	// ************************************************************
	private static Logger				__log				= Logger.getLogger(Recursos.class.getName());

	public static Properties doConfigureFile( String configFileName ){

		Properties props = new Properties();
		FileInputStream istream = null;
		try {
			istream = new FileInputStream(configFileName);
			props.load(istream);
			istream.close();
			fillValues(props);
		} catch (Exception e) {
			__log.error("Could not read configuration file [" + configFileName + "].", e);
			__log.error("Ignoring configuration file [" + configFileName + "].");
			return null;
		} finally {
			if (istream != null) {
				try {
					istream.close();
				} catch (Throwable ignore) {
				}
			}
		}
		return props;
	}

	public static void fillValues( Properties props ){

		if (props != null) {

			nombd = props.getProperty("nombd");
			__log.info("Configuración de la Base de datos: " + nombd);
			diccionario = props.getProperty("diccionario");
			__log.info("Configuración de la Base de datos: " + diccionario);
			HOST = props.getProperty("HOST");
			__log.info("Configuración del host de correo: " + HOST);
		}
	}

}// end Recursos
