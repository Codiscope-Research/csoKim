package es.intos.gdscso.ln;

import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.bd.BDVolum;
import es.intos.gdscso.on.CsoSrvTable;
import es.intos.gdscso.on.ServicioFact;
import es.intos.gdscso.on.SrvCSOTable;
import es.intos.gdscso.on.SrvDispTable;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.sql.ConexionBD;

public class LNVolum{

	public static Logger	log	= Logger.getLogger(LNVolum.class);

	private LNVolum() {

	}

	public static int getNumSrvWithoutFact( Integer idcso, Integer year, Integer month ) throws Exception{

		log.debug("begin :: LNVolum->getNumSrvWithoutFact ");
		ConexionBD con = null;
		int rows = 0;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			rows = BDVolum.getNumSrvWithoutFact(con, idcso, year, month);

			con.commit();

			return rows;
		} catch (Exception e) {
			log.debug("LNVolum.getNumSrvWithoutFact", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->getNumSrvWithoutFact ");
		}
	}

	public static Vector<SrvCSOTable> getSrvWithVol( Integer idcso, Integer year, Integer month, String locale, String sort, String context )
			throws Exception{

		log.debug("begin :: LNVolum->getSrvWithVol ");
		ConexionBD con = null;
		Vector<SrvCSOTable> srvs = new Vector<SrvCSOTable>(1, 1);
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			srvs = BDVolum.getSrvWithVol(con, idcso, year, month, locale, sort, context);

			con.commit();
			return srvs;
		} catch (Exception e) {
			log.debug("LNVolum.getSrvWithVol", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->getSrvWithVol ");
		}
	}

	public static Vector<SrvCSOTable> getSrvWithVolExcel( Integer idcso, Integer year, Integer month, String locale ) throws Exception{

		log.debug("begin :: LNVolum->getSrvWithVol ");
		ConexionBD con = null;
		Vector<SrvCSOTable> srvs = new Vector<SrvCSOTable>(1, 1);
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			srvs = BDVolum.getSrvWithVolExcel(con, idcso, year, month, locale);

			con.commit();
			return srvs;
		} catch (Exception e) {
			log.debug("LNVolum.getSrvWithVol", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->getSrvWithVol ");
		}
	}

	public static Vector<CsoSrvTable> getSrvCsoFacts( Integer year, Integer month, String locale, Integer inici, Integer lenght,
			String sortDir,String context ) throws Exception{

		log.debug("begin :: LNVolum->getSrvCsoFacts ");
		ConexionBD con = null;
		Vector<CsoSrvTable> srvs = new Vector<CsoSrvTable>(1, 1); 
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			srvs = BDVolum.getSrvCsoFacts(con, year, month, inici, lenght, sortDir,context);

			con.commit();
			return srvs;
		} catch (Exception e) {
			log.debug("LNVolum.getSrvCsoFacts", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->getSrvCsoFacts ");
		}
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param locale
	 * @param inici
	 * @param lenght
	 * @param sortDir
	 * @return tots els serveis per un cso, mes i any junt amb el seu estat i la
	 *         factura
	 * @throws Exception
	 */
	public static Vector<SrvDispTable> getSrvCso( Integer idcso, Integer year, Integer month, String locale, Integer inici, Integer lenght,
			String sortDir ) throws Exception{

		log.debug("begin :: LNVolum->getSrvCso ");
		ConexionBD con = null;
		Vector<SrvDispTable> srvs = new Vector<SrvDispTable>(1, 1);
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			srvs = BDVolum.getSrvFacts(con, idcso, year, month, locale, inici, lenght, sortDir);

			con.commit();
			return srvs;
		} catch (Exception e) {
			log.debug("LNVolum.getSrvCso", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->getSrvCso ");
		}
	}

	public static Vector<SrvDispTable> getSrvCsoForHomonima( String code, Integer idcso, Integer year, Integer month, String locale,
			Integer inici, Integer lenght, String sortDir ) throws Exception{

		log.debug("begin :: LNVolum->getSrvCso ");
		ConexionBD con = null;
		Vector<SrvDispTable> srvs = new Vector<SrvDispTable>(1, 1);
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			srvs = BDVolum.getSrvFactsForHomonima(con, code, idcso, year, month, locale, inici, lenght, sortDir);

			con.commit();
			return srvs;
		} catch (Exception e) {
			log.debug("LNVolum.getSrvCso", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->getSrvCso ");
		}
	}

	public static Vector<SrvDispTable> getSrvCsoModif( Integer idfactura, Integer idcso, Integer year, Integer month, String locale,
			Integer inici, Integer lenght, String sortDir ) throws Exception{

		log.debug("begin :: LNVolum->getSrvCso ");
		ConexionBD con = null;
		Vector<SrvDispTable> srvs = new Vector<SrvDispTable>(1, 1);
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			srvs = BDVolum.getSrvFactsModif(con, idfactura, idcso, year, month, locale, inici, lenght, sortDir);

			con.commit();
			return srvs;
		} catch (Exception e) {
			log.debug("LNVolum.getSrvCso", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->getSrvCso ");
		}
	}

	public static int getSrvCsoCount( Integer idcso, Integer year, Integer month, String locale, Integer inici, Integer lenght,
			String sortDir ) throws Exception{

		log.debug("begin :: LNVolum->getSrvCsoCount ");
		ConexionBD con = null;
		int numreg = 0;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			numreg = BDVolum.getSrvFactsCount(con, idcso, year, month, locale, inici, lenght, sortDir);
			con.commit();
			return numreg;

		} catch (Exception e) {
			log.debug("LNVolum.getSrvCsoCount", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->getSrvCsoCount ");
		}
	}

	public static Vector<ServicioFact> getSrv( Integer idsrv, Integer idcso, Integer month, Integer year ) throws Exception{

		log.debug("begin :: LNVolum->getSrv");
		ConexionBD con = null;
		Vector<ServicioFact> srvs = new Vector<ServicioFact>(1, 1);
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			srvs = BDVolum.getSrv(con, idsrv, idcso, month, year);

			con.commit();
			return srvs;
		} catch (Exception e) {
			log.debug("LNVolum.getSrv", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->getSrv ");
		}
	}

	public static boolean ckeckNewData() throws Exception{

		log.debug("begin :: LNVolum->ckeckNewData");
		ConexionBD con = null;
		boolean existNewData = false;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			existNewData = BDVolum.ckeckNewData(con);

			con.commit();
			return existNewData;
		} catch (Exception e) {
			log.debug("LNVolum.ckeckNewData", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNVolum->ckeckNewData ");
		}

	}

}