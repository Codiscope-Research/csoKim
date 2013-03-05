package es.intos.gdscso.ln;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.bd.BDEstats;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.EstatHistoric;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.sql.ConexionBD;

public class LNEstats{

	public static Logger	log	= Logger.getLogger(LNEstats.class);

	private LNEstats() {

	}

	public static Vector<Basic> getEstats( String locale ) throws Exception{

		log.debug("begin :: LNEstats->getEstats ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<Basic> basicEstats = BDEstats.getEstats(con, locale);

			con.commit();

			return basicEstats;
		} catch (Exception e) {
			log.debug("LNEstats.getEstats", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNEstats->getEstats ");
		}
	}

	public static void saveHisEstats( EstatHistoric statH ) throws Exception{

		log.debug("begin :: LNEstats->saveHisEstats ");
		ConexionBD con = null;

		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			BDEstats.saveHisEstats(con, statH);

			con.commit();

		} catch (Exception e) {
			log.debug("LNEstats.saveHisEstats", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNEstats->saveHisEstats ");
		}
	}

	public static List<EstatHistoric> getHisEstatsOfFactura( Integer idFactura ) throws Exception{

		log.debug("begin :: LNEstats->getHisEstatsOfFactura ");
		ConexionBD con = null;

		List<EstatHistoric> estatHistoricList = new ArrayList<EstatHistoric>();

		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			estatHistoricList = BDEstats.getHisEstatsOfFactura(con, idFactura);

			con.commit();

			return estatHistoricList;
		} catch (Exception e) {
			log.debug("LNEstats.getHisEstatsOfFactura", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNEstats->getHisEstatsOfFactura ");
		}
	}

}
