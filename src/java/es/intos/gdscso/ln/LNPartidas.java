package es.intos.gdscso.ln;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.bd.BDPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.DetallPartidaTable;
import es.intos.gdscso.on.FactorCrecimiento;
import es.intos.gdscso.on.FactorsCorreccio;
import es.intos.gdscso.on.FacturacioDetallePartida;
import es.intos.gdscso.on.FacturacioPartidasFirstTable;
import es.intos.gdscso.on.FacturacioPartidasSecondTable;
import es.intos.gdscso.on.Partida;
import es.intos.gdscso.on.Servicio;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.Usuario;
import es.intos.util.sql.ConexionBD;

public class LNPartidas{

	public static Logger	log	= Logger.getLogger(LNPartidas.class);

	private LNPartidas() {

	}

	public static int getMaxIdPartida( Usuario user, Locale locale ) throws Exception{

		log.debug("begin :: LNPartidas->getMaxIdPartida ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			int alt = BDPartidas.selectMaxIdPartida(con);

			con.commit();

			return alt;
		} catch (Exception e) {
			log.debug("LNPartidas.getMaxIdPartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getMaxIdPartida ");
		}
	}

	public static int savePartida( Usuario user, Locale locale, Partida part ) throws Exception{

		log.debug("begin :: LNPartidas->savePartida ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			int alt = BDPartidas.insertPartida(con, part);

			con.commit();

			return alt;
		} catch (Exception e) {
			log.debug("LNPartidas.savePartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->savePartida ");
		}
	}

	public static int deletePartida( Usuario user, Locale locale, Partida part ) throws Exception{

		log.debug("begin :: LNPartidas->deletePartida ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			int alt = BDPartidas.deletePartida(con, part);

			con.commit();

			return alt;
		} catch (Exception e) {
			log.debug("LNPartidas.deletePartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->deletePartida ");
		}
	}

	public static int updateNomPartida( Usuario user, Locale locale, Partida part ) throws Exception{

		log.debug("begin :: LNPartidas->updateNomPartida ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			int alt = BDPartidas.updateNomPartida(con, part);

			con.commit();

			return alt;
		} catch (Exception e) {
			log.debug("LNPartidas.updateNomPartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->updateNomPartida ");
		}
	}

	public synchronized static List<Servicio> getAllSrv( Locale locale, boolean partida, Integer idPartida, Integer inici, Integer lenght,
			String sortDir ) throws Exception{

		log.debug("begin :: LNPartidas->getAllSrv ");
		ConexionBD con = null;
		List<Servicio> list = new ArrayList<Servicio>();
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			if (idPartida == null) {
				list = BDPartidas.getAllSrvPartida(con, false, null, inici, lenght, sortDir);

			} else {

				list = BDPartidas.getAllSrvPartida(con, partida, idPartida, inici, lenght, sortDir);

			}
			con.commit();
			return list;
		} catch (Exception e) {
			log.debug("LNPartidas.getAllSrv", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getAllSrv ");
		}
	}

	public synchronized static int getAllSrvCount( Locale locale, boolean partida, Integer idPartida ) throws Exception{

		log.debug("begin :: LNPartidas->savePartida ");
		ConexionBD con = null;

		try {
			int rows = 0;
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			if (idPartida == null) {
				rows = BDPartidas.getAllSrvPartidaCount(con, false, null);

			} else {

				rows = BDPartidas.getAllSrvPartidaCount(con, partida, idPartida);

			}
			con.commit();
			return rows;
		} catch (Exception e) {
			log.debug("LNPartidas.getAllSrvCount", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getAllSrvCount ");
		}
	}

	public static int deleteSrvPartida( Usuario user, Locale locale, Integer idPartida, Integer idSrv ) throws Exception{

		log.debug("begin :: LNPartidas->deleteSrvPartida ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			int part = BDPartidas.deleteSrvPartida(con, idSrv, idPartida);

			con.commit();

			return part;
		} catch (Exception e) {
			log.debug("LNPartidas.deleteSrvPartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->deleteSrvPartida ");
		}
	}

	public static int addSrvPartida( Usuario user, Locale locale, Integer idPartida, Integer idSrv, String nomSrv ) throws Exception{

		log.debug("begin :: LNPartidas->addSrvPartida ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			int part = BDPartidas.insertSrvPartida(con, idSrv, idPartida, nomSrv);

			con.commit();

			return part;
		} catch (Exception e) {
			log.debug("LNPartidas.addSrvPartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->addSrvPartida ");
		}
	}

	public static int getNumPartides() throws Exception{

		log.debug("begin :: LNPartidas->getNumPartides ");
		ConexionBD con = null;
		int numRows = 0;
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			numRows = BDPartidas.getNumPartides(con);

			con.commit();
			return numRows;
		} catch (Exception e) {
			log.debug("LNPartidas.getNumPartides", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getNumPartides ");
		}
	}

	public static List<Partida> getAllPartidas() throws Exception{

		log.debug("begin :: LNPartidas->getAllPartidas ");
		ConexionBD con = null;
		List<Partida> list = new ArrayList<Partida>();
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			list = BDPartidas.getAllPartida(con);
			con.commit();

			return list;
		} catch (Exception e) {
			log.debug("LNPartidas.getAllPartidas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getAllPartidas ");
		}
	}

	public static List<FacturacioPartidasSecondTable> getInfoSecondTableOfFacturacionPartidas( Integer yearOfConsult, Integer inici,
			Integer lenght, String order ) throws Exception{

		log.debug("begin :: LNPartidas->getInfoTableOfFacturacionPartidas ");
		ConexionBD con = null;
		List<FacturacioPartidasSecondTable> list = new ArrayList<FacturacioPartidasSecondTable>();
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			list = BDPartidas.getInfoSecondTableOfFacturacionPartidas(con, yearOfConsult, inici, lenght, order);
			con.commit();
			return list;
		} catch (Exception e) {
			log.debug("LNPartidas.getInfoTableOfFacturacionPartidas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getInfoTableOfFacturacionPartidas ");
		}
	}

	public static List<FacturacioPartidasFirstTable> getInfoTableOfFacturacionPartidas( Integer yearOfConsult, Integer inici,
			Integer lenght, String order ) throws Exception{

		log.debug("begin :: LNPartidas->getInfoTableOfFacturacionPartidas ");
		ConexionBD con = null;
		List<FacturacioPartidasFirstTable> list = new ArrayList<FacturacioPartidasFirstTable>();
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			list = BDPartidas.getInfoTableOfFacturacionPartidas(con, yearOfConsult, inici, lenght, order);
			con.commit();
			return list;
		} catch (Exception e) {
			log.debug("LNPartidas.getInfoTableOfFacturacionPartidas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getInfoTableOfFacturacionPartidas ");
		}
	}

	public static List<FactorCrecimiento> getFactoresCrecimientoPartidas( Integer yearOfConsult, Integer partida ) throws Exception{

		log.debug("begin :: LNPartidas->getFactoresCrecimientoPartidas ");
		ConexionBD con = null;
		List<FactorCrecimiento> list = new ArrayList<FactorCrecimiento>();
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			list = BDPartidas.getFactoresCrecimientoPartidas(con, yearOfConsult, partida);
			con.commit();
			return list;
		} catch (Exception e) {
			log.debug("LNPartidas.getFactoresCrecimientoPartidas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getFactoresCrecimientoPartidas ");
		}
	}

	public static void saveFactoresCrecimientoPartidas( FactorsCorreccio factorsCorreccio ) throws Exception{

		log.debug("begin :: LNPartidas->saveFactoresCrecimientoPartidas ");
		ConexionBD con = null;
		List<FactorCrecimiento> listFactors = new ArrayList<FactorCrecimiento>();
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			int numeroDeMes = 1;
			for (numeroDeMes = 1; numeroDeMes <= 12; numeroDeMes++) {
				FactorCrecimiento factor = factorsCorreccio.getFactor(numeroDeMes);
				listFactors.add(factor);
			}
			BDPartidas.insertFactoresCrecimientoPartidas(con, listFactors);
			con.commit();

		} catch (Exception e) {
			log.debug("LNPartidas.saveFactoresCrecimientoPartidas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->saveFactoresCrecimientoPartidas ");
		}
	}

	public static void saveImportPactatPartida( FactorsCorreccio factorsCorreccio ) throws Exception{

		log.debug("begin :: LNPartidas->saveImportPactatPartida ");
		ConexionBD con = null;
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			BDPartidas.saveImportPactatPartida(con, factorsCorreccio);
			con.commit();

		} catch (Exception e) {
			log.debug("LNPartidas.saveImportPactatPartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->saveImportPactatPartida ");
		}
	}

	public static Double getImportPactatPartida( Integer yearOfConsult, Integer partida ) throws Exception{

		log.debug("begin :: LNPartidas->saveImportPactatPartida ");
		ConexionBD con = null;
		Double importe = null;
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			importe = BDPartidas.getImportPactatPartida(con, yearOfConsult, partida);
			con.commit();
		} catch (Exception e) {
			log.debug("LNPartidas.saveImportPactatPartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->saveImportPactatPartida ");
		}
		return importe;
	}

	public static List<FacturacioDetallePartida> getInfoOfFacturacionPartida( Vector<Basic> yearsOfConsult, Integer idPartida )
			throws Exception{

		log.debug("begin :: LNPartidas->getInfoOfFacturacionPartida ");
		ConexionBD con = null;
		List<FacturacioDetallePartida> list = new ArrayList<FacturacioDetallePartida>();
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			list = BDPartidas.getInfoOfFacturacionPartida(con, yearsOfConsult, idPartida);
			con.commit();
			return list;
		} catch (Exception e) {
			log.debug("LNPartidas.getInfoOfFacturacionPartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getInfoOfFacturacionPartida ");
		}
	}

	public static String getNomPartida( Integer idPartida ) throws Exception{

		log.debug("begin :: LNPartidas->getNomPartida ");
		ConexionBD con = null;
		String nomPartida = "";
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			nomPartida = BDPartidas.getNomPartida(con, idPartida);
			con.commit();
			return nomPartida;
		} catch (Exception e) {
			log.debug("LNPartidas.getNomPartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getNomPartida ");
		}
	}

	public static List<DetallPartidaTable> getInfoTableDetallPartida( Integer yearOfConsult, Integer idPartida, Integer inici,
			Integer lenght, String order ) throws Exception{

		log.debug("begin :: LNPartidas->getInfoTableDetallPartida ");
		ConexionBD con = null;
		List<DetallPartidaTable> list = new ArrayList<DetallPartidaTable>();
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			list = BDPartidas.getInfoTableDetallPartida(con, yearOfConsult, idPartida, inici, lenght, order);
			con.commit();
			return list;
		} catch (Exception e) {
			log.debug("LNPartidas.getInfoTableDetallPartida", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getInfoTableDetallPartida ");
		}
	}

	public static int getNumSrvPartides( Integer yearOfConsult, Integer idPartida ) throws Exception{

		int numSrvPartida = 0;
		log.debug("begin :: LNPartidas->getNumSrvPartides ");
		ConexionBD con = null;
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			numSrvPartida = BDPartidas.getNumSrvPartides(con, yearOfConsult, idPartida);
		} catch (Exception e) {
			log.debug("LNPartidas.getNumSrvPartides", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->getNumSrvPartides ");
		}
		return numSrvPartida;

	}
}
