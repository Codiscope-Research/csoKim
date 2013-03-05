package es.intos.gdscso.utils;

public class ConstantsGU{

	public final static String	CODI_BBDD_SI										= "S";
	public final static String	FORMAT_DATA_I_HORA									= "DD-MM-YYYY HH24:mi";
	public final static String	FORMAT_DATA											= "DD-MM-YYYY";

	/*
	 * IDENTIFICADORS
	 */
	// TIPUS D'ESTATS
	public final static String	ESTAT_TAREAS										= "TAREAS";
	public final static String	ESTAT_PETICIO										= "PETICIONS_CUSA";
	// FASE
	public final static String	ID_FASE_1											= "F1";
	public final static String	ID_FASE_2											= "F2";
	public final static String	ID_FASE_3											= "F3";
	public final static String	ID_FASE_4											= "F4";
	public final static String	ID_FASE_5											= "F5";
	// PRIORITAT
	public final static int		ID_PRIORITAT_ALTA									= 1;
	public final static int		ID_PRIORITAT_MITJA									= 2;
	public final static int		ID_PRIORITAT_BAIXA									= 3;
	// ESTAT
	public final static String	CODI_ESTAT_PETICIO_PENDIENTE_SISTEMA_CUSA			= "E1";
	public final static String	CODI_ESTAT_PETICIO_PENDIENTE_RESPUESTA				= "E2";
	public final static String	CODI_ESTAT_PETICIO_APROBADO							= "E3";
	public final static String	CODI_ESTAT_PETICIO_PENDIENTE_JUSTIFICACION			= "E4";
	public final static String	CODI_ESTAT_PETICIO_DENEGADO							= "E5";
	public final static String	CODI_ESTAT_PETICIO_PENDIENTE_APROBACION_PROCESOS	= "E6";
	public final static String	CODI_ESTAT_PETICIO_PENDIENTE_AREA_NEGOCIOS			= "E7";
	public final static String	CODI_ESTAT_PETICIO_CANCELADO						= "E8";
	// PERFILS
	public final static String	PERFIL_USUARIO										= "0";
	public final static String	PERFIL_ADMINISTRADOR								= "1";
	public final static String	PERFIL_FINANZAS										= "2";
	public final static String	PERFIL_CALIDAD										= "3";
	public final static String	PERFIL_PRESUPUESTO									= "4";
	public final static String	PERFIL_ORGANIZACION									= "5";
	public final static String	PERFIL_AREA_NEGOCIO									= "6";
	public final static String	PERFIL_SISTEMES_CUSA								= "7";
	// JERARQUIAS
	public final static int		JERARQUIA_DIRECCION_GENERAL							= 1;
	public final static int		JERARQUIA_AREA										= 2;
	public final static int		JERARQUIA_GERENCIA									= 3;
	public final static int		JERARQUIA_DEPARTAMENTO								= 4;
	// TIPUS PETICIO
	public final static int		TIPUS_PETICIO_ACCES_A_ENTORNS_CAIXA					= 1;
	public final static int		TIPUS_PETICIO_ACCES_A_TRANSACCIONS_FITXERS_CAIXA	= 2;
	public final static int		TIPUS_PETICIO_NIVELLS_EN_APLICACIONS_CAIXA			= 3;
	public final static int		TIPUS_PETICIO_NO_ESPECIFICADES_CAIXA				= 4;
	public final static int		TIPUS_PETICIO_ACCES_A_APLICACIONS_GDS				= 5;
	public final static int		TIPUS_PETICIO_INSTALLACIO_APLICACIONS_GDS			= 6;
	public final static int		TIPUS_PETICIO_PERMISOS_DE_XARXA_GDS					= 7;
	public final static int		TIPUS_PETICIO_ALTA_USUARI_EXTERN					= 8;
	public final static int		TIPUS_PETICIO_NO_ESPECIFICADA						= 9;
	public final static int		TIPUS_PETICIO_INCIDENCIA							= 10;

	// identificadors Documents d'ajuda
	public final static int		ID_DOCUMENT_AJUDA_GESTIO_PETICIONS_SISTEMES_CUSA	= 1;
	/*
	 * TAULES
	 */
	public final static String	TAULA_ACT_PROJECTES_FASE1							= "ACT_PROJECTES_FASE1";
	public final static String	TAULA_ACT_PROJECTES_FASE3							= "ACT_PROJECTES_FASE3";
	public final static String	TAULA_ACT_TASQUESACCIONS							= "ACT_TASQUESACCIONS";
	public final static String	TAULA_ACT_PROJECTMESA								= "ACT_PROJECTMESA";
	public final static String	TAULA_USUARIS										= "SYN_USERS_USUARIOS";
	public final static String	TAULA_PETICIONS_SISTEMES_CUSA						= "AC_PETSISTEMESCUSA";
	public final static String	TAULA_HIS_ESTATS_PETSISTEMESCUSA					= "AC_HIS_ESTATS_PETSISTEMESCUSA";
	public final static String	TAULA_DADES_ESPECIFIQUES_PETICIONS_SISTEMES_CUSA	= "AC_DADESESP_PETSISTEMESCUSA";
	public final static String	TAULA_HIS_OBS_PETSISTEMESCUSA						= "AC_HIS_OBS_PETSISTEMESCUSA";
	public final static String	TAULA_CATEGORIES									= "ACT_MT_CATEGORIATIPOLOGIA";
	public final static String	TAULA_REL_AREA_TECNIC								= "ACT_MNT_REL_AREA_TECNIC";
	public final static String	TAULA_MTUSUARIS										= "ACT_MTUSUARIS";
	public final static String	TAULA_USERS_USUARIOS								= "SYN_USERS_USUARIOS";
	public final static String	TAULA_CENTROS										= "SYN_CENTROS";
	public final static String	TAULA_DOCUMENTS_AJUDA								= "ACT_DOCUMENTS_AJUDA";
	public final static String	TAULA_WORKFLOW_ESTATS								= "ACT_WORKFLOW_ESTATS";
	public final static String	TAULA_ESTATS										= "ACT_ESTATS_ENTITAT";
	public final static String	TAULA_HISTORIC_TASQUES								= "ACT_TASQUES_HIS_ESTATS";
	public final static String	TAULA_PROVEIDORS_EMPRESA							= "SYN_USERS_EMPRESAS";

}
