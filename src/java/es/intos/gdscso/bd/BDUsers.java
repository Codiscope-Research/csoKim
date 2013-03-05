package es.intos.gdscso.bd;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDUsers{

	private static Logger	log	= Logger.getLogger(BDUsers.class);

	private BDUsers() {// CONSTRUCTOR

	}

	public static Usuario selectUser( ConexionBD con, String sUsuari, String sPass, ActionMessages errors ) throws Exception{

		Usuario oo = null;
		int rows = 0;
		String sql = " select U_USUARIO, " + " U_PWD," + " U_NOMBRE_USUARIO, " + " U_PAPELLIDO_USUARIO, " + " U_SAPELLIDO_USUARIO "
				+
				// " , NVL(PERFIL,"+ConstantsDB.PERFIL_USUARIO+") PERFIL " +
				" from SYN_USERS_USUARIOS,ACT_MTUSUARIS " + " where UPPER(U_USUARIO)=UPPER(?)  and U_USUARIO=MATRICULA(+) "
				+ " and TRUNC(U_FECHA_CADUCIDAD)>=TRUNC(SYSDATE) ";

		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			stmt = con.getPreparedStatement(sql.toString());
			int count = 1;
			stmt.setString(count++, sUsuari);
			log.info(stmt.toString());
			rs = stmt.executeQuery();

			if (rs.next()) {
				if (sPass.equalsIgnoreCase(rs.getString("U_PWD"))) {
					oo = new Usuario();
					oo.setEEmpCen("001");
					oo.setENumCen("5316");
					oo.setPerfil(Recursos.PROCEDENCIA_GDS);
					oo.setCentro_con("5316");
					oo.setENomCen("Barcelona");
					oo.setNumEmp(rs.getString("U_USUARIO"));
					oo.setNomEmp(rs.getString("U_NOMBRE_USUARIO"));
					oo.setCog1Emp(rs.getString("U_PAPELLIDO_USUARIO"));
					oo.setCog2Emp(rs.getString("U_SAPELLIDO_USUARIO"));
					oo.setNomApell(rs.getString("U_NOMBRE_USUARIO") + " " + rs.getString("U_PAPELLIDO_USUARIO") + " "
							+ rs.getString("U_SAPELLIDO_USUARIO"));

				} else {
					log.debug("Password incorrecto >>" + sUsuari);
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("txt.password.incorrecto"));
				}
				rows++;
			} else {
				log.debug("Imposible obtener usuario el registro no existe >>" + sUsuari);
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("txt.usuario.no.existente", sUsuari));
			}
		} catch (Exception e) {
			log.error("BDUsers. selectUser", e);
			throw e;
		} finally {
			log.info(rows + " count rows selected.");
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return oo;
	}// end selectUser

}