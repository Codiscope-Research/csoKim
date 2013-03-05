package es.intos.gdscso.ln;

import org.apache.log4j.Logger;

import es.intos.gdscso.bd.BDImportPactat;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.sql.ConexionBD;

public class LNImportsPactats{

	public static Logger	log	= Logger.getLogger(LNImportsPactats.class);

	private LNImportsPactats() {

	}

	public static void saveOrUpdateImportPactat( Integer year, Double importe ) throws Exception{

		log.debug("begin :: LNImportsPactats->saveOrUpdateImportPactat ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			BDImportPactat.saveOrUpdateImportPactat(con, year, importe);

			con.commit();

		} catch (Exception e) {
			log.debug("LNImportsPactats.saveOrUpdateImportPactat", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNImportsPactats->saveOrUpdateImportPactat ");
		}
	}

	public static Basic getImportPactat( Integer year ) throws Exception{

		log.debug("begin :: LNImportsPactats->getImportPactat ");
		ConexionBD con = null;
		Basic basic = new Basic();
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			basic = BDImportPactat.getImportPactat(con, year);

			con.commit();

		} catch (Exception e) {
			log.debug("LNImportsPactats.getImportPactat", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNImportsPactats->getImportPactat ");
		}
		return basic;
	}

}