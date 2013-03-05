package es.intos.gdscso.ln;

import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.bd.BDIncidencias;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.sql.ConexionBD;

public class LNIncidencias{

	public static Logger	log	= Logger.getLogger(LNIncidencias.class);

	private LNIncidencias() {

	}

	public static Vector<Basic> getIncidencias( String locale, String type ) throws Exception{

		log.debug("begin :: LNIncidencias->getIncidencias ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<Basic> basicInc = BDIncidencias.getIncidencias(con, locale, type);

			con.commit();

			return basicInc;
		} catch (Exception e) {
			log.debug("LNIncidencias.getEstats", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNIncidencias->getEstats ");
		}
	}

	public static String getIncidenciaActual( String locale, Integer idFact ) throws Exception{

		log.debug("begin :: LNIncidencias->getIncidenciaActual ");
		ConexionBD con = null;
		String incidencia = "";
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			incidencia = BDIncidencias.getIncidenciaActual(con, locale, idFact);

			con.commit();

			return incidencia;
		} catch (Exception e) {
			log.debug("LNIncidencias.getIncidenciaActual", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNIncidencias->getIncidenciaActual ");
		}
	}

	public static String getIncidencia( String locale, Integer idInc ) throws Exception{

		log.debug("begin :: LNIncidencias->getIncidenciaActual ");
		ConexionBD con = null;
		String incidencia = "";
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			incidencia = BDIncidencias.getIncidencia(con, locale, idInc);

			con.commit();

			return incidencia;
		} catch (Exception e) {
			log.debug("LNIncidencias.getIncidenciaActual", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNIncidencias->getIncidenciaActual ");
		}
	}

}
