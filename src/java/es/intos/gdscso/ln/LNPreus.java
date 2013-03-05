package es.intos.gdscso.ln;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.intos.gdscso.bd.BDPreu;
import es.intos.gdscso.on.Preu;
import es.intos.gdscso.on.Tram;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;
import es.intos.util.sql.ConexionBD;

public class LNPreus{

	public static Logger	log	= Logger.getLogger(LNPreus.class);

	private LNPreus() {

	}

	public static int savePreu( Usuario user, Locale locale, Preu preu, String totscso ) throws Exception{

		log.debug("begin :: LNPreus->savePreu ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			int alt = BDPreu.insertPreu(con, preu, totscso);

			con.commit();

			return alt;
		} catch (Exception e) {
			log.debug("LNPreus.savePreu", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPreus->savePreu ");
		}
	}

	public static void updatePreu( Usuario user, Locale locale, Preu preu ) throws Exception{

		log.debug("begin :: LNPreus->updatePreu ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			BDPreu.updatePreu(con, preu);

			con.commit();

		} catch (Exception e) {
			log.debug("LNPreus.updatePreu", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPreus->updatePreu ");
		}
	}

	public static List<Tram> getPreu( Integer cso, Integer srv ) throws Exception{

		log.debug("begin :: LNPreus->getPreu ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			List<Tram> trams = BDPreu.getPreu(con, cso, srv);

			con.commit();

			return trams;
		} catch (Exception e) {
			log.debug("LNPreus.getPreu", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPreus->getPreu ");
		}
	}

	public static HashMap<String,Integer> getIdPreu( Integer cso, Integer srv ) throws Exception{

		log.debug("begin :: LNPreus->getIdPreu ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			HashMap<String,Integer> id = BDPreu.selectIdPreu(con, cso, srv);

			con.commit();

			return id;
		} catch (Exception e) {
			log.debug("LNPreus.getIdPreu", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPreus->getIdPreu ");
		}
	}

}
