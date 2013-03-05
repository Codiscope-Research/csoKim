package es.intos.gdscso.ln;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;

import es.intos.gdscso.bd.BDUsers;
import es.intos.gdscso.forms.gdsusers.LoginForm;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;
import es.intos.util.sql.ConexionBD;

public class LNUsers{

	public static Logger	log	= Logger.getLogger(LNUsers.class);

	public static Usuario consultaUser( LoginForm frm, Locale locale, ActionMessages errors ) throws Exception{

		log.debug("begin :: consultaUser >>" + frm.getF_user());
		ConexionBD con = null;
		Usuario oo = null;
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			oo = BDUsers.selectUser(con, frm.getF_user(), frm.getF_pass(), errors);
			con.commit();
		} catch (Exception e) {
			log.debug("LNUsers. consultaUser", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end :: consultaUser >>" + frm.getF_user());
		}

		return oo;
	}

}// end LNUsers
