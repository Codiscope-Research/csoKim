package es.intos.gdscso.ln;

import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.bd.BDCso;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.Cso;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.sql.ConexionBD;

public class LNCso{

	public static Logger	log	= Logger.getLogger(LNCso.class);

	private LNCso() {

	}

	public static Cso getCSOFromManteniment(Integer idCSO) throws Exception{

		log.debug("begin :: LNEstats->getCSOs ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Cso cso  = BDCso.getCSOFromManteniment(con, idCSO);

			con.commit();

			return cso;
		} catch (Exception e) {
			log.debug("LNCso.getCSOs", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNCso->getCSOs ");
		}
	}
	
	public static Cso saveCSOInManteniment(Cso cso) throws Exception{

		log.debug("begin :: LNEstats->getCSOs ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			BDCso.saveCSOInManteniment(con,cso);

			con.commit();

			return cso;
		} catch (Exception e) {
			log.debug("LNCso.getCSOs", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNCso->getCSOs ");
		}
	}
	
	public static Vector<Basic> getCSOs() throws Exception{

		log.debug("begin :: LNEstats->getCSOs ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<Basic> basicCSOs = BDCso.getCSOs(con);

			con.commit();

			return basicCSOs;
		} catch (Exception e) {
			log.debug("LNCso.getCSOs", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNCso->getCSOs ");
		}
	}

	public static Vector<Basic> getCSO( Integer idcso ) throws Exception{

		log.debug("begin :: LNEstats->getCSO ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<Basic> basicCSO = BDCso.getCSO(con, idcso);

			con.commit();

			return basicCSO;
		} catch (Exception e) {
			log.debug("LNCso.getCSO", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNCso->getCSO ");
		}
	}

}
