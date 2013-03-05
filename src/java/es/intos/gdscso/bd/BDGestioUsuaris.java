package es.intos.gdscso.bd;

import java.util.Locale;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.intos.gdscso.on.GdsactivosPerfil;
import es.intos.gdscso.on.GdsactivosUsuari;

import es.intos.gdscso.utils.ConstantsGU;
import es.intos.util.Usuario;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDGestioUsuaris{

	private static Logger	log	= Logger.getLogger(BDGestioUsuaris.class);

	private BDGestioUsuaris() {

	}

	public static int selecPK( ConexionBD con, String sMatricula ) throws Exception{

		int num = 0;

		String sql = " select count(*) NUM " + " FROM  ACT_MTUSUARIS " + " WHERE ACT_MTUSUARIS.MATRICULA=? ";

		int rows = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			int count = 1;
			stmt = con.getPreparedStatement(sql);
			stmt.setString(count++, sMatricula);
			log.info("BDGestioUsuaris. selecPK >> " + stmt.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt("NUM");
				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. selecPK", e);
			throw e;
		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return num;
	}// END selecPK

	public static int selecCount( ConexionBD con ) throws Exception{

		int num = 0;

		String sql = " select count(*) NUM " + " FROM  SYN_USERS_USUARIOS,SYN_USERS_USR_APP  " + " WHERE UA_NOMBRE_APP = 'GDS_CUSAVIEJA' "
				+ "  AND SYN_USERS_USUARIOS.U_USUARIO = SYN_USERS_USR_APP.UA_USUARIO ";

		int rows = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.getPreparedStatement(sql);
			log.info("BDGestioUsuaris. selecCount >> " + stmt.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt("NUM");
				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. selecCount", e);
			throw e;
		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return num;
	}// END selecCount

	public static Vector<GdsactivosUsuari> selecList( ConexionBD con, String perfil, String sOrder, int iPagina, int iRpp, Locale locale )
			throws Exception{

		Vector<GdsactivosUsuari> list = new Vector<GdsactivosUsuari>();
		Locale CAT = new Locale("ca", "ES");

		String sql = " select  * from (select     " + " ACT_MTUSUARIS.ID,  " + " SYN_USERS_USUARIOS.U_USUARIO,  "
				+ " SYN_USERS_USUARIOS.U_NOMBRE_USUARIO," + " SYN_USERS_USUARIOS.U_PAPELLIDO_USUARIO,"
				+ " SYN_USERS_USUARIOS.U_SAPELLIDO_USUARIO," + " NVL(ACT_MTUSUARIS.PERFIL," + ConstantsGU.PERFIL_USUARIO + ") PERFIL,  ";
		if (CAT.equals(locale))
			sql += " ACT_PERFILS.NOM_PERFIL_CAT NOM_PERFIL,  ";
		else
			sql += " ACT_PERFILS.NOM_PERFIL_CAS NOM_PERFIL,  ";
		sql += " ACT_MTUSUARIS.DATAALTA,  " + " TO_CHAR(ACT_MTUSUARIS.DATAALTA,'DD-MM-YYYY') DATAALTAx,   "
				+ " ACT_MTUSUARIS.USUARIALTA,  " + " ACT_MTUSUARIS.DATAULTMODIF,  "
				+ " TO_CHAR(ACT_MTUSUARIS.DATAULTMODIF,'DD-MM-YYYY HH24:mi') DATAULTMODIFx,   " + " ACT_MTUSUARIS.USUARIMODIF,  "
				+ "row_number() over ( ";
		if (StringUtils.isNotBlank(sOrder))
			sql += " order by "
					+ sOrder
					+ ", SYN_USERS_USUARIOS.U_NOMBRE_USUARIO ASC, SYN_USERS_USUARIOS.U_PAPELLIDO_USUARIO ASC, SYN_USERS_USUARIOS.U_SAPELLIDO_USUARIO ASC ";
		else
			sql += " order by SYN_USERS_USUARIOS.U_NOMBRE_USUARIO ASC, SYN_USERS_USUARIOS.U_PAPELLIDO_USUARIO ASC, SYN_USERS_USUARIOS.U_SAPELLIDO_USUARIO ASC ";
		sql += ") rn" + " FROM  ACT_MTUSUARIS,SYN_USERS_USUARIOS,ACT_PERFILS, SYN_USERS_USR_APP  "
				+ " WHERE UA_NOMBRE_APP = 'GDS_ACTIVOS' " + "  AND SYN_USERS_USUARIOS.U_USUARIO = SYN_USERS_USR_APP.UA_USUARIO "
				+ " AND SYN_USERS_USUARIOS.U_USUARIO=ACT_MTUSUARIS.MATRICULA(+) " + " AND NVL(ACT_MTUSUARIS.PERFIL,"
				+ ConstantsGU.PERFIL_USUARIO + ")=ACT_PERFILS.ID_PERFIL(+)";
		if (perfil != null && !perfil.equals(""))
			sql += " AND PERFIL = ?";
		sql += " ) WHERE  rn between ? and ?";

		int rows = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			int count = 1;
			stmt = con.getPreparedStatement(sql);
			if (perfil != null && !perfil.equals(""))
				stmt.setInt(count++, Integer.valueOf(perfil));
			stmt.setInt(count++, ((iPagina - 1) * iRpp) + 1);
			stmt.setInt(count++, iPagina * iRpp);
			log.info("BDGestioUsuaris. selecList >> " + stmt.toString());
			rs = stmt.executeQuery();

			while (rs.next()) {
				GdsactivosUsuari oo = new GdsactivosUsuari();
				oo.setIdUsuari(rs.getInt("ID"));
				oo.setMatricula(rs.getString("U_USUARIO"));
				oo.setNomUsuari(rs.getString("U_NOMBRE_USUARIO"));
				oo.setPcognomUsuari(rs.getString("U_PAPELLIDO_USUARIO"));
				oo.setScognomUsuari(rs.getString("U_SAPELLIDO_USUARIO"));
				oo.setPerfil(rs.getString("PERFIL"));
				oo.setPerfildesc(rs.getString("NOM_PERFIL"));
				oo.setFaltaD(rs.getDate("DATAALTA"));
				oo.setFalta(rs.getString("DATAALTAx"));
				oo.setUseralta(rs.getString("USUARIALTA"));
				oo.setFmodifD(rs.getDate("DATAULTMODIF"));
				oo.setFmodif(rs.getString("DATAULTMODIFx"));
				oo.setUsermodif(rs.getString("USUARIMODIF"));
				list.add(oo);
				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. selecList", e);
			throw e;
		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return list;
	}// END selecList

	public static int insert( ConexionBD con, String sMatricula, Usuario user, String sPerfil ) throws Exception{

		String sql = " insert into ACT_MTUSUARIS(  ID,  " + " MATRICULA,  " + " PERFIL,  " + " DATAALTA,  " + " USUARIALTA,  "
				+ " DATAULTMODIF,  " + " USUARIMODIF  " + "   ) values (                                         "
				+ "   S_ACT_MTUSUARIS.nextval,  " + // ID
				"	?,  " + // U_USUARIO
				"	?,  " + // PERFIL
				"	sysdate,  " + // DATAALTA
				"	?,  " + // USUARIALTA
				"	sysdate,  " + // DATAULTMODIF
				"	?)"; // USUARIMODIF

		PreparedStatement ps = null;
		int rows = 0;

		try {
			ps = con.getPreparedStatement(sql);
			int count = 1;
			ps.setString(count++, sMatricula); // MATRICULA
			ps.setString(count++, sPerfil);// PERFIL
			ps.setString(count++, user.getNumEmp()); // USUARIALTA
			ps.setString(count++, user.getNumEmp()); // USUARIMODIF
			log.info("BDGestioUsuaris. insert >> " + ps.toString());
			rows = con.executeUpdate(ps);
		} catch (Exception e) {
			log.error("BDGestioUsuaris. insert", e);
			throw e;
		} finally {
			log.info(rows + " rows inserted.");
			if (null != ps)
				ps.close();
		}
		return rows;
	} // end insert

	public static int update( ConexionBD con, String sMatricula, Usuario user, String sPerfil ) throws Exception{

		String sql = " update ACT_MTUSUARIS set  " + " PERFIL=? , " + " USUARIMODIF=? , " + " DATAULTMODIF=sysdate "
				+ " where MATRICULA=? ";

		PreparedStatement ps = null;
		int rows = 0;

		try {
			ps = con.getPreparedStatement(sql);
			int count = 1;
			ps.setString(count++, sPerfil);// PERFIL
			ps.setString(count++, user.getNumEmp()); // USUARIMODIF
			ps.setString(count++, sMatricula);// ID
			log.info("BDGestioUsuaris. update >> " + ps.toString());
			rows = ps.executeUpdate();
		} catch (Exception e) {
			log.error("BDGestioUsuaris. update", e);
			throw e;
		} finally {
			log.info(rows + " rows updated.");
			if (null != ps)
				ps.close();
		}
		return rows;
	} // end update

	public static Vector<GdsactivosPerfil> selecPerfils( ConexionBD con, Locale locale ) throws Exception{

		Vector<GdsactivosPerfil> list = new Vector<GdsactivosPerfil>();
		Locale CAT = new Locale("ca", "ES");

		String sql = " SELECT " + " ID_PERFIL,";
		if (CAT.equals(locale))
			sql += " NOM_PERFIL_CAT NOM_PERFIL ";
		else
			sql += " NOM_PERFIL_CAS NOM_PERFIL ";
		sql += " FROM ACT_PERFILS ";

		int rows = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.getPreparedStatement(sql);
			log.info("BDGestioUsuaris. selecPerfils >> " + stmt.toString());
			rs = stmt.executeQuery();

			while (rs.next()) {
				GdsactivosPerfil oo = new GdsactivosPerfil();
				oo.setIdPerfil(rs.getString("ID_PERFIL"));
				oo.setNomPerfil(rs.getString("NOM_PERFIL"));
				list.add(oo);
				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. selecPerfils", e);
			throw e;
		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return list;
	}// END selecPerfils

	public static String getPerfil( ConexionBD con, String sMatricula ) throws Exception{

		String perfil = ConstantsGU.PERFIL_USUARIO;

		String sql = " SELECT " + " NVL(PERFIL," + ConstantsGU.PERFIL_USUARIO + ") PERFIL " + " FROM ACT_MTUSUARIS "
				+ " WHERE  MATRICULA= ?";

		int rows = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			int count = 1;
			stmt = con.getPreparedStatement(sql);
			stmt.setString(count++, sMatricula);

			log.info("BDGestioUsuaris. getPerfil >> " + stmt.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {
				perfil = rs.getString("PERFIL");
				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. getPerfil", e);
			throw e;
		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return perfil;
	}// END getPerfil

	public static GdsactivosUsuari getUsuarioByMatricula( ConexionBD con, String sMatricula ) throws Exception{

		String sql = " SELECT U_USUARIO, U_NOMBRE_USUARIO, U_PAPELLIDO_USUARIO, U_SAPELLIDO_USUARIO FROM SYN_USERS_USUARIOS "
				+ " WHERE UPPER(U_USUARIO)=? ";

		int rows = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		GdsactivosUsuari oo = null;
		try {
			int count = 1;
			stmt = con.getPreparedStatement(sql);
			stmt.setString(count++, sMatricula);

			log.info("BDGestioUsuaris. getUsuarioByMatricula >> " + stmt.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {

				oo = new GdsactivosUsuari();
				oo.setMatricula(rs.getString("U_USUARIO"));
				oo.setNomUsuari(rs.getString("U_NOMBRE_USUARIO"));
				oo.setPcognomUsuari(rs.getString("U_PAPELLIDO_USUARIO"));
				oo.setScognomUsuari(rs.getString("U_SAPELLIDO_USUARIO"));
				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. getUsuarioByMatricula", e);
			throw e;
		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return oo;
	}// END getPerfil

	/*
	 * public static Vector<GdsactivosUsuari> selectListaParticipantesReunion(
	 * ConexionBD con) throws Exception {
	 * 
	 * String sql = "select act_mtusuaris.matricula, " +
	 * " syn_users_usuarios.u_nombre_usuario, " +
	 * " syn_users_usuarios.u_papellido_usuario, " +
	 * " syn_users_usuarios.u_sapellido_usuario " +
	 * " from act_mtusuaris,syn_users_usuarios " +
	 * " where act_mtusuaris.matricula = syn_users_usuarios.u_usuario(+)";
	 * 
	 * int rows=0; Vector<GdsactivosUsuari> lista= new
	 * Vector<GdsactivosUsuari>(); ResultSet rs=null; PreparedStatement
	 * stmt=null; try { stmt = con.getPreparedStatement(sql);
	 * //stmt.setString(count++, sMatricula);
	 * 
	 * log.info("BDGestioUsuaris. selectListaParticipantesReunion >> "+stmt.toString
	 * ()); rs = stmt.executeQuery();
	 * 
	 * while(rs.next()) {
	 * 
	 * GdsactivosUsuari oo = new GdsactivosUsuari();
	 * oo.setMatricula(rs.getString("matricula"));
	 * oo.setNomUsuari(rs.getString("u_nombre_usuario"));
	 * oo.setPcognomUsuari(rs.getString("u_papellido_usuario"));
	 * oo.setScognomUsuari(rs.getString("u_sapellido_usuario"));
	 * 
	 * lista.add(oo); rows++; }
	 * 
	 * } catch (Exception e) {
	 * log.error("BDGestioUsuaris. selectListaParticipantesReunion", e); throw
	 * e; } finally { log.info(rows+" rows selected."); if (null!=rs)
	 * rs.close(); if (null!=stmt) stmt.close(); } return lista; }
	 */

	public static GdsactivosUsuari consultaUserByMatricula( ConexionBD con, String sMatricula ) throws Exception{

		/*
		 * String sql = " select act_mtusuaris.matricula, " +
		 * " syn_users_usuarios.u_nombre_usuario,  " +
		 * " syn_users_usuarios.u_papellido_usuario,  " +
		 * " syn_users_usuarios.u_sapellido_usuario, " +
		 * " syn_users_usuarios.u_email, " + " syn_users_usuarios.u_telefono " +
		 * " from act_mtusuaris,syn_users_usuarios  " +
		 * " where act_mtusuaris.matricula = ? " +
		 * " and act_mtusuaris.matricula = syn_users_usuarios.u_usuario(+) ";
		 */
		String sql = " SELECT SWE_MATRICULA_CUSA, SWE_MAIL " + " FROM SYN_SOFTWARE_EMPLEADO " + " WHERE " + " swe_matricula_cusa=? ";

		int rows = 0;
		GdsactivosUsuari oo = new GdsactivosUsuari();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.getPreparedStatement(sql);
			int count = 1;
			stmt.setString(count++, sMatricula);

			log.info("BDGestioUsuaris. consultaUserByMatricula >> " + stmt.toString());
			rs = stmt.executeQuery();

			while (rs.next()) {

				oo.setMatricula(rs.getString("SWE_MATRICULA_CUSA"));
				// oo.setNomUsuari(rs.getString("u_nombre_usuario"));
				// oo.setPcognomUsuari(rs.getString("u_papellido_usuario"));
				// oo.setScognomUsuari(rs.getString("u_sapellido_usuario"));
				oo.setEmail(rs.getString("SWE_MAIL"));
				// oo.setTelefono(rs.getString("u_telefono"));

				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. consultaUserByMatricula", e);
			throw e;
		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return oo;
	}

	public static Vector<Usuario> getUsuarioByArea( ConexionBD con, String idArea ) throws Exception{

		String sql = " SELECT "
				+ "  SWE_DNI"
				+ ", SWE_MATRICULA_CUSA"
				+ ", SWE_MATRICULA_CAIXA"
				+
				// ", SWE_MAIL" +
				// ", SWE_NIVELES_CAIXA" +
				// ", SWE_FECHA_ALTA" +
				// ", SWE_FECHA_MODIFICACION" +
				// ", SWE_FECHA_FINALIZACION" +
				" FROM" + "  SYN_SOFTWARE_EMPLEADO" + ", SYN_EMPLEADOS" + " WHERE 1=1"
				+ " AND SYN_EMPLEADOS.EMP_DNI=SYN_SOFTWARE_EMPLEADO.SWE_DNI" + " AND SYN_EMPLEADOS.EMP_ID_CENTRO = ?";// area
																														// antic,
																														// ara
																														// és
																														// centre.

		int rows = 0;
		Vector<Usuario> llistaUsuaris = new Vector<Usuario>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.getPreparedStatement(sql);
			int count = 1;
			stmt.setString(count++, idArea);

			log.info("BDGestioUsuaris. getUsuario >> " + stmt.toString());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario oo = new Usuario();
				oo.setDni(rs.getString("SWE_DNI"));
				// oo.setMatriculaCaixa("SWE_MATRICULA_CAIXA");
				oo.setMail("SWE_MAIL");
				rows++;
				llistaUsuaris.add(oo);
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. getUsuario", e);
			throw e;

		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return llistaUsuaris;
	}

	public static Usuario getUsuariByMatricula( ConexionBD con, String idMatricula ) throws Exception{

		String sql = " SELECT " + "  SWE_DNI"
				+ ", SWE_MATRICULA_CUSA"
				+ ", SWE_MATRICULA_CAIXA"
				+ ", SWE_MAIL"
				+
				// ", SWE_NIVELES_CAIXA" +
				// ", SWE_FECHA_ALTA" +
				// ", SWE_FECHA_MODIFICACION" +
				// ", SWE_FECHA_FINALIZACION" +
				", EMP_ID_CENTRO " + " FROM" + "  SYN_SOFTWARE_EMPLEADO" + ", SYN_EMPLEADOS" + " WHERE 1=1"
				+ " AND SYN_EMPLEADOS.EMP_DNI=SYN_SOFTWARE_EMPLEADO.SWE_DNI" + " AND SYN_SOFTWARE_EMPLEADO.SWE_MATRICULA_CUSA = ?";// area
																																	// antic,
																																	// ara
																																	// és
																																	// centre.

		int rows = 0;
		Usuario usuario = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.getPreparedStatement(sql);
			int count = 1;
			stmt.setString(count++, idMatricula);

			log.info("BDGestioUsuaris. getUsuario >> " + stmt.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setDni(rs.getString("SWE_DNI"));
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. getUsuario", e);
			throw e;

		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return usuario;
	}

	public static String getJerarquia( ConexionBD con, String centro_con ) throws Exception{

		String sql = " SELECT SYN_CENTROS.CEN_ID_JERARQUIA " + " FROM SYN_CENTROS  " + " WHERE SYN_CENTROS.CEN_ID = ? ";

		int rows = 0;
		String jerarquia = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.getPreparedStatement(sql);
			int count = 1;
			stmt.setString(count++, centro_con);

			log.info("BDGestioUsuaris. getJerarquia >> " + stmt.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {
				jerarquia = rs.getString("CEN_ID_JERARQUIA");
				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. getJerarquia", e);
			throw e;

		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return jerarquia;
	}

	public static Vector<String> getCentrosByJerarquia( ConexionBD con, String centro_con, String jerarquia ) throws Exception{

		String sql = " SELECT SYN_CENTROS.CEN_ID " + " FROM SYN_CENTROS " + " WHERE SYN_CENTROS.CEN_BAJA <> '" + ConstantsGU.CODI_BBDD_SI
				+ "' ";
		if (StringUtils.isNotBlank(jerarquia)) {
			if (jerarquia.equals("" + ConstantsGU.JERARQUIA_DIRECCION_GENERAL)) {
				sql += " AND (SYN_CENTROS.CEN_DG = ? OR SYN_CENTROS.CEN_ID = ? ) ";
			} else if (jerarquia.equals("" + ConstantsGU.JERARQUIA_AREA)) {
				sql += " AND (SYN_CENTROS.CEN_AREA = ? OR SYN_CENTROS.CEN_ID = ? ) ";
			} else if (jerarquia.equals("" + ConstantsGU.JERARQUIA_GERENCIA)) {
				sql += " AND (SYN_CENTROS.CEN_GERENCIA = ? OR SYN_CENTROS.CEN_ID = ? ) ";
			} else {
				sql += " AND SYN_CENTROS.CEN_ID = ? ";
			}
		}

		int rows = 0;
		Vector<String> lista = new Vector<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.getPreparedStatement(sql);
			int count = 1;

			if (StringUtils.isNotBlank(jerarquia)) {
				if (jerarquia.equals("" + ConstantsGU.JERARQUIA_DIRECCION_GENERAL) || jerarquia.equals("" + ConstantsGU.JERARQUIA_AREA)
						|| jerarquia.equals("" + ConstantsGU.JERARQUIA_GERENCIA)) {
					stmt.setString(count++, centro_con);
				}
				stmt.setString(count++, centro_con);
			}

			log.info("BDGestioUsuaris. getJerarquia >> " + stmt.toString());
			rs = stmt.executeQuery();

			while (rs.next()) {
				String centro = rs.getString("CEN_ID");

				lista.add(centro);
				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. getJerarquia", e);
			throw e;

		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}

		return lista;
	}

	public static Vector<GdsactivosUsuari> getEmpleadosByCentro( ConexionBD con, String centro_con, Vector<String> listaCentros )
			throws Exception{

		String sql = " SELECT SYN_SOFTWARE_EMPLEADO.SWE_MATRICULA_CUSA, " + " SYN_EMPLEADOS.EMP_NOMBRE, "
				+ " SYN_EMPLEADOS.EMP_APELLIDO1, " + " SYN_EMPLEADOS.EMP_APELLIDO2 " + " FROM  SYN_EMPLEADOS "
				+ " LEFT JOIN SYN_SOFTWARE_EMPLEADO on SYN_EMPLEADOS.EMP_DNI=SYN_SOFTWARE_EMPLEADO.SWE_DNI "
				+ " WHERE SYN_EMPLEADOS.EMP_ID_CENTRO in ( ? ";
		for (int i = 1; i < listaCentros.size(); i++) {
			sql += " , ? ";
		}
		sql += " )";

		int rows = 0;
		Vector<GdsactivosUsuari> lista = new Vector<GdsactivosUsuari>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = con.getPreparedStatement(sql);
			int count = 1;

			for (int i = 0; i < listaCentros.size(); i++) {
				stmt.setString(count++, listaCentros.get(i));
			}

			log.info("BDGestioUsuaris. getJerarquia >> " + stmt.toString());
			rs = stmt.executeQuery();

			while (rs.next()) {
				GdsactivosUsuari oo = new GdsactivosUsuari();
				oo.setMatricula(rs.getString("SWE_MATRICULA_CUSA"));
				oo.setNomUsuari(rs.getString("EMP_NOMBRE"));
				oo.setPcognomUsuari(rs.getString("EMP_APELLIDO1"));
				oo.setScognomUsuari(rs.getString("EMP_APELLIDO2"));

				lista.add(oo);
				rows++;
			}

		} catch (Exception e) {
			log.error("BDGestioUsuaris. getJerarquia", e);
			throw e;

		} finally {
			log.info(rows + " rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}

		return lista;
	}

}// end BDCusaViejaGestioUsuaris
