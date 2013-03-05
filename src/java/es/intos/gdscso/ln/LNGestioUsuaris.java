package es.intos.gdscso.ln;

import java.util.Locale;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.bd.BDGestioUsuaris;
import es.intos.gdscso.forms.admin.GestioUsuarisForm;
import es.intos.gdscso.on.GdsactivosPerfil;
import es.intos.gdscso.on.GdsactivosUsuari;
import es.intos.gdscso.utils.ConstantsGU;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;
import es.intos.util.sql.ConexionBD;

public class LNGestioUsuaris{

	public static Logger	log	= Logger.getLogger(LNGestioUsuaris.class);

	private LNGestioUsuaris() {

	}

	public static String[] getOrderBy_ASC(){

		String[] asc = { "SYN_USERS_USUARIOS.U_USUARIO ASC", "SYN_USERS_USUARIOS.U_NOMBRE_USUARIO ASC",
				"SYN_USERS_USUARIOS.U_PAPELLIDO_USUARIO ASC", "SYN_USERS_USUARIOS.U_SAPELLIDO_USUARIO ASC",
				"NVL(ACT_MTUSUARIS.PERFIL," + ConstantsGU.PERFIL_USUARIO + ") ASC" };
		return asc;
	}

	public static String[] getOrderBy_DESC(){

		String[] desc = { "SYN_USERS_USUARIOS.U_USUARIO DESC", "SYN_USERS_USUARIOS.U_NOMBRE_USUARIO DESC",
				"SYN_USERS_USUARIOS.U_PAPELLIDO_USUARIO DESC", "SYN_USERS_USUARIOS.U_SAPELLIDO_USUARIO DESC",
				"NVL(ACT_MTUSUARIS.PERFIL," + ConstantsGU.PERFIL_USUARIO + ") DESC" };
		return desc;
	}

	public static Vector<GdsactivosUsuari> consultaTots( Locale locale ) throws Exception{

		log.debug("begin :: consultaList ");
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			Vector<GdsactivosUsuari> list = BDGestioUsuaris.selecList(con, null, null, 1, Integer.MAX_VALUE, locale);

			con.commit();

			return list;

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris.consultaList", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: consultaList ");
		}
	}

	public static Vector<GdsactivosUsuari> consultaList( GestioUsuarisForm frm, Usuario user, Locale locale ) throws Exception{

		log.debug("begin :: consultaList ");
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<GdsactivosUsuari> list = BDGestioUsuaris.selecList(con, frm.getF_perfil(), frm.getOrder_by(), frm.getPagina(),
					frm.getRpp(), locale);

			con.commit();

			return list;

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris.consultaList", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: consultaList ");
		}
	}

	public static int consultaNumReg( Usuario user, Locale locale ) throws Exception{

		log.debug("begin :: consultaNumReg ");
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			int numReg = BDGestioUsuaris.selecCount(con);

			con.commit();

			return numReg;

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris.consultaNumReg", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: consultaNumReg ");
		}
	}

	public static void insert( GestioUsuarisForm frm, Usuario user, ActionMessages errors, ActionMessages messages ) throws Exception{

		log.debug("begin :: insert >>" + frm.getF_matricula() + ">>");
		ConexionBD con = null;
		try {
			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			if (BDGestioUsuaris.selecPK(con, frm.getF_matricula()) > 0)
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("manteniments.insert.ya.existe", frm.getF_matricula()));
			else
				BDGestioUsuaris.insert(con, frm.getF_matricula(), user, frm.getF_perfil());
			messages.add("INFO", new ActionMessage("manteniments.insert.ok"));
			con.commit();

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris. insert", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: insert >>" + frm.getF_matricula());
		}
	}// end insert

	public static int update( GestioUsuarisForm frm, Usuario user, ActionMessages errors, ActionMessages messages ) throws Exception{

		log.debug("begin :: update >>" + frm.getF_matricula() + ">>");
		ConexionBD con = null;
		int rows = 0;
		try {
			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			rows = BDGestioUsuaris.update(con, frm.getF_matricula(), user, frm.getF_perfil());
			if (rows > 0)
				messages.add("INFO", new ActionMessage("manteniments.update.ok"));
			con.commit();

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris. update", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: update >>" + frm.getF_matricula());
		}
		return rows;
	}// end update

	public static String getPerfil( Usuario user ) throws Exception{

		log.debug("begin :: getPerfil ");
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			String perfil = BDGestioUsuaris.getPerfil(con, user.getNumEmp());

			con.commit();

			return perfil;

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris. getPerfil", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: getPerfil ");
		}
	}

	public static Vector<GdsactivosPerfil> consultaPerfils( Usuario user, Locale locale ) throws Exception{

		log.debug("begin :: consultaPerfils ");
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<GdsactivosPerfil> list = BDGestioUsuaris.selecPerfils(con, locale);

			con.commit();

			return list;

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris. consultaPerfils", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: consultaPerfils ");
		}
	}// consultaPerfils

	public static GdsactivosUsuari getUsuarioByMatricula( String matricula ) throws Exception{

		log.debug("begin :: getPerfil ");
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			GdsactivosUsuari oo = BDGestioUsuaris.getUsuarioByMatricula(con, matricula);

			con.commit();

			return oo;

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris. getUsuarioByMatricula", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: getUsuarioByMatricula ");
		}
	}

	public static GdsactivosUsuari getUsuarioMailByMatricula( String matricula ) throws Exception{

		log.debug("begin :: getUsuarioMailByMatricula ");
		GdsactivosUsuari usuarioMail = null;
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			usuarioMail = BDGestioUsuaris.consultaUserByMatricula(con, matricula);

			con.commit();

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris. getUsuarioMailByMatricula", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: getUsuarioMailByMatricula ");
		}
		return usuarioMail;
	}

	public static Vector<Usuario> getUsuarioByArea( String idArea ) throws Exception{

		log.debug("begin :: getUsuarioMailByMatricula ");
		Vector<Usuario> llistaUsuaris;
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			llistaUsuaris = BDGestioUsuaris.getUsuarioByArea(con, idArea);

			con.commit();

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris. getUsuarioMailByMatricula", e);
			if (null != con)
				con.rollback();
			throw e;

		} finally {
			if (null != con)
				con.close();
			log.debug("end :: getUsuarioMailByMatricula ");
		}
		return llistaUsuaris;
	}

	public static Usuario getUsuariByMatricula( String idMatricula ) throws Exception{

		log.debug("begin :: getUsuariByMatricula ");
		Usuario usuario;
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			usuario = BDGestioUsuaris.getUsuariByMatricula(con, idMatricula);

			con.commit();

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris. getUsuariByMatricula", e);
			if (null != con)
				con.rollback();
			throw e;

		} finally {
			if (null != con)
				con.close();
			log.debug("end :: getUsuariByMatricula ");
		}
		return usuario;
	}

	public static Vector<GdsactivosUsuari> consultaEmpleadosByCentro( Usuario user ) throws Exception{

		log.debug("begin :: consultaEmpleados ");
		Vector<GdsactivosUsuari> usuarios = new Vector<GdsactivosUsuari>();
		ConexionBD con = null;
		try {

			con = es.intos.gdscso.utils.Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			String jerarquia = BDGestioUsuaris.getJerarquia(con, user.getCentro_con());

			if (StringUtils.isNotBlank(jerarquia)) {
				Vector<String> listaCentros = BDGestioUsuaris.getCentrosByJerarquia(con, user.getCentro_con(), jerarquia);

				usuarios = BDGestioUsuaris.getEmpleadosByCentro(con, user.getCentro_con(), listaCentros);
			}

			con.commit();

		} catch (Exception e) {
			log.debug("LNGdsactivosGestioUsuaris. getUsuariByMatricula", e);
			if (null != con)
				con.rollback();
			throw e;

		} finally {
			if (null != con)
				con.close();
			log.debug("end :: getUsuariByMatricula ");
		}
		return usuarios;
	}
}
