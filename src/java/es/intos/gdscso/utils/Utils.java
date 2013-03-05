package es.intos.gdscso.utils;

import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import org.apache.struts.util.MessageResources;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import es.intos.gdscso.on.Basic;
import es.intos.util.Usuario;

public class Utils{

	public static String changeEncode( String st ){

		String changed = st;

		changed = changed.replace("Á", "&Aacute;");
		changed = changed.replace("É", "&Eacute;");
		changed = changed.replace("Í", "&Iacute;");
		changed = changed.replace("Ó", "&Oacute;");
		changed = changed.replace("Ú", "&Uacute;");

		changed = changed.replace("À", "&Agrave;");
		changed = changed.replace("È", "&Egrave;");
		changed = changed.replace("Ò", "&Ograve;");

		changed = changed.replace("Ñ", "&Ntilde;");

		changed = changed.replace("á", "&aacute;");
		changed = changed.replace("é", "&eacute;");
		changed = changed.replace("í", "&iacute;");
		changed = changed.replace("ó", "&oacute;");
		changed = changed.replace("ú", "&uacute;");

		changed = changed.replace("à", "&agrave;");
		changed = changed.replace("è", "&egrave;");
		changed = changed.replace("ò", "&ograve;");

		changed = changed.replace("ñ", "&ntilde;");
		changed = changed.replace("·", "&middot;");
		changed = changed.replace("\"", "\\\'");
		changed = changed.replace("\t", "\\t");
		changed = changed.replace("\n", "\\n");
		changed = changed.replace("\f", "\\f");
		changed = changed.replace("\r", "\\r");
		changed = changed.replace("\b", "\\b");

		return changed;

	}

	public static String decode( String st ){

		if (st == null)
			return "";
		String changed = st;

		changed = changed.replace("&Aacute;", "Á");
		changed = changed.replace("&Eacute;", "É");
		changed = changed.replace("&Iacute;", "Í");
		changed = changed.replace("&Oacute;", "Ó");
		changed = changed.replace("&Uacute;", "Ú");

		changed = changed.replace("&Agrave;", "À");
		changed = changed.replace("&Egrave;", "È");
		changed = changed.replace("&Ograve;", "Ò");

		changed = changed.replace("&Ntilde;", "Ñ");

		changed = changed.replace("&aacute;", "á");
		changed = changed.replace("&eacute;", "é");
		changed = changed.replace("&iacute;", "í");
		changed = changed.replace("&oacute;", "ó");
		changed = changed.replace("&uacute;", "ú");

		changed = changed.replace("&agrave;", "à");
		changed = changed.replace("&egrave;", "è");
		changed = changed.replace("&ograve;", "ò");

		changed = changed.replace("&ntilde;", "ñ");
		changed = changed.replace("&middot;", "·");

		return changed;

	}

	public static String procedenciaUser( Usuario user ){

		String procedencia = "";
		if (user.getENumCen().equals(Recursos.CENTROS_GDS[0]) || user.getENumCen().equals(Recursos.CENTROS_GDS[1])) {
			procedencia = Recursos.PROCEDENCIA_GDS;
		} else {
			procedencia = Recursos.PROCEDENCIA_CSO;
		}
		return procedencia;
	}

	public static boolean isValidJSON( final String json ){

		boolean valid = true;
		try {
			final JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(json);
			valid = jsonElement.isJsonObject();

		} catch (JsonParseException jpe) {
			valid = false;
		}

		return valid;
	}

	public static String[] getMonths( MessageResources missatges, Locale locale ){

		String[] mesos = new String[] { missatges.getMessage(locale, "txt.consulta.select.mes1"),
				missatges.getMessage(locale, "txt.consulta.select.mes2"), missatges.getMessage(locale, "txt.consulta.select.mes3"),
				missatges.getMessage(locale, "txt.consulta.select.mes4"), missatges.getMessage(locale, "txt.consulta.select.mes5"),
				missatges.getMessage(locale, "txt.consulta.select.mes6"), missatges.getMessage(locale, "txt.consulta.select.mes7"),
				missatges.getMessage(locale, "txt.consulta.select.mes8"), missatges.getMessage(locale, "txt.consulta.select.mes9"),
				missatges.getMessage(locale, "txt.consulta.select.mes10"), missatges.getMessage(locale, "txt.consulta.select.mes11"),
				missatges.getMessage(locale, "txt.consulta.select.mes12") };
		return mesos;
	}

	public static Integer getCurrentYear(){

		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);

	}

	public static Integer getCurrentMonth(){

		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;

	}

	public static Vector<Basic> getListOfYears(){

		int numOfYearsInList = 0;
		int year = getCurrentYear();
		Vector<Basic> years = new Vector<Basic>(1, 1);

		for (numOfYearsInList = 0; numOfYearsInList < 5; numOfYearsInList++) {
			Basic yearBasic = new Basic(year, String.valueOf(year));
			years.add(yearBasic);
			year--;
		}
		return years;
	}
	
	public static String getDoubleFrom(String db){
		String dbFormat="";
		if(db==null)return "";
		String[] dbVec = db.split(",");
		if(dbVec.length==2){
			dbFormat = dbVec[0].replace(".","") +"."+dbVec[1];
		}else if (dbVec.length==1){
			dbFormat = dbVec[0].replace(".","");
		}else{
			return "";
		}
		return dbFormat;
	}
}
