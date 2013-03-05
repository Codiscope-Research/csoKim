package es.intos.gdscso.utils;

public class Constants{

	public final static String		source								= "es.intos.gdscso.resources.admin.ApplicationResources";
	public final static String		DATA_NULA							= "09/09/9999";
	public final static String		FORMAT_DATA							= "dd/mm/yyyy";
	public final static String		ASCENDENT							= "ASC";
	public final static String		DESCENDENT							= "DESC";

	public final static String		LOCALE_LANGUAGE_CATALA				= "ca";
	public final static String		LOCALE_LANGUAGE_CASTELLA			= "es";

	public final static String		PAGINACION							= "paginacion";
	public final static String		SEPARACIO_NOM_FORMAT				= ".";
	public final static String		FORMAT_FITXER_EXCEL_2007			= "xlsx";
	public final static String		FORMAT_FITXER_WORD_97				= "doc";
	public final static String		NOM_FITXER_EXCEL_FASE3_MODELGDS		= "Document Objectius GDS";
	public final static String		NOM_FITXER_EXCEL_FASE3_MODELCAIXA	= "Document Objectius Caixa";

	public final static String		PARAMETRE_INICI_PENDENT_CANVI		= "[";
	public final static String		PARAMETRE_FINAL_PENDENT_CANVI		= "]";

	public final static String		INC_VAL								= "VALIDACIO";
	public final static String		INC_FACT							= "FACTURACIO";
	public final static String		INC_NCONFORME						= "NOCONFORME";

	public final static Integer		ESTAT_PENDENT_GEN					= 1;
	public final static Integer		ESTAT_GENERAT						= 2;
	public final static Integer		ESTAT_VALIDAT						= 3;
	public final static Integer		ESTAT_CONFORME						= 4;
	public final static Integer		ESTAT_NCONFORME						= 5;
	public final static Integer		ESTAT_CANCEL						= 7;
	public final static Integer		ESTAT_FIN							= 6;
	public final static Integer		ESTAT_INC_VAL						= 8;
	public final static Integer		ESTAT_INC_FACT						= 9;

	public final static Integer		MES_GENER							= 0;
	public final static Integer		MES_FEBRER							= 1;
	public final static Integer		MES_MARC							= 2;
	public final static Integer		MES_ABRIL							= 3;
	public final static Integer		MES_MAIG							= 4;
	public final static Integer		MES_JUNY							= 5;
	public final static Integer		MES_JULIOL							= 6;
	public final static Integer		MES_AGOST							= 7;
	public final static Integer		MES_SETEMBRE						= 8;
	public final static Integer		MES_OCTUBRE							= 9;
	public final static Integer		MES_NOVEMBRE						= 10;
	public final static Integer		MES_DESEMBRE						= 11;

	public final static Integer		ORDRE_BY_NOM_TABLE_FIRST_COL		= 0;
	public final static Integer		ORDRE_BY_NOM_TABLE_SECOND_COL		= 1;
	public final static Integer		ORDRE_BY_ESTAT_TABLE_THIRD_COL		= 2;
	public final static Integer		NUMBER_OF_ESTATS					= 10;

	public final static String		FACTURACIO_SERVEI					= "servei";
	public final static String		FACTURACIO_RECURS					= "recurs";

	public final static String[]	mesos								= new String[] { "ENE", "FEB", "MAR", "ABR", "MAI", "JUN", "JUL",
			"AGO", "SET", "OCT", "NOV", "DES"							};
	
	public final static Double IVA= 21.0;
	public final static Double IGIC= 15.0;

}
