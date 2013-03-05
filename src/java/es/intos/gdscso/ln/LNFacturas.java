package es.intos.gdscso.ln;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.bd.BDFacturas;
import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasFormDTO;
import es.intos.gdscso.forms.partes.BusquedaNoConformidadesFormDTO;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.FactSrvTable;
import es.intos.gdscso.on.FactorCrecimientoFactura;
import es.intos.gdscso.on.FactorsCorreccioFactura;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.on.FacturaDialogNC;
import es.intos.gdscso.on.FacturaTableNC;
import es.intos.gdscso.on.ServicioFact;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class LNFacturas{

	public static Logger	log	= Logger.getLogger(LNFacturas.class);

	private LNFacturas() {

	}

	public static String[] getOrderBy_ASC(){

		String[] asc = {

		"CSO_FACTURAS.IDCSO ASC", "CSO_FACTURAS.MES_FACT ASC", "CSO_FACTURAS.ANY_FACT ASC", "CSO_FACTURAS.IMPORTE ASC",
				"est.DESCRIPCIO ASC", "CSO_FACTURAS.FENTRADA ASC" };
		return asc;
	}

	public static String[] getOrderBy_DESC(){

		String[] desc = { "CSO_FACTURAS.IDCSO DESC", "CSO_FACTURAS.MES_FACT DESC", "CSO_FACTURAS.ANY_FACT DESC",
				"CSO_FACTURAS.IMPORTE DESC", "est.DESCRIPCIO DESC", "CSO_FACTURAS.FENTRADA DESC" };
		return desc;
	}

	public static String[] getOrderByNC_ASC(){

		String[] asc = {

		"CSO_FACTURAS.ID ASC", "cso.NAME ASC", "CSO_FACTURAS.MES_FACT ASC", "CSO_FACTURAS.ANY_FACT ASC", "his.nc ASC", "cso.NAME ASC" };
		return asc;
	}

	public static String[] getOrderByNC_DESC(){

		String[] desc = { "CSO_FACTURAS.ID DESC", "cso.NAME DESC", "CSO_FACTURAS.MES_FACT DESC", "CSO_FACTURAS.ANY_FACT DESC",
				"his.nc DESC", "cso.NAME DESC" };
		return desc;
	}


	public static void setFacturaPDF( Integer idFactura, String name ) throws Exception{

		log.debug("begin :: LNFacturas->getFacturas ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			 BDFacturas.setFacturaPDF(con, idFactura, name);

			con.commit();

			
		} catch (Exception e) {
			log.debug("LNFacturas.getFacturas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getFacturas ");
		}
	}

	public static List<Basic> getFacturaPDFNames( Integer idfact) throws Exception{

		log.debug("begin :: LNFacturas->getFacturas ");
		ConexionBD con = null;
		List<Basic> names = new ArrayList<Basic>();
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			names =  BDFacturas.getFacturaPDFNames(con, idfact);

			con.commit();

			
		} catch (Exception e) {
			log.debug("LNFacturas.getFacturas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getFacturas ");
		}
		return names;
		}
	
	public static void deleteFacturaPDF( Integer idFactura, String name ) throws Exception{

		log.debug("begin :: LNFacturas->getFacturas ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			BDFacturas.deleteFacturaPDF(con, idFactura, name);

			con.commit();

			
		} catch (Exception e) {
			log.debug("LNFacturas.getFacturas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getFacturas ");
		}
	}
	
	
	public static Vector<Factura> getFacturas( Integer idFactura, BusquedaGestionFacturasFormDTO facturaDTO, String sortDir, Integer pag,
			Integer lenght ) throws Exception{

		log.debug("begin :: LNFacturas->getFacturas ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<Factura> factList = BDFacturas.getFacturas(con, idFactura, facturaDTO, sortDir, pag, lenght);

			con.commit();

			return factList;
		} catch (Exception e) {
			log.debug("LNFacturas.getFacturas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getFacturas ");
		}
	}

	public static int getNumReg( BusquedaGestionFacturasFormDTO facturaDTO ) throws Exception{

		log.debug("begin :: LNFacturas->getNumReg ");
		ConexionBD con = null;
		int rows = 0;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			rows = BDFacturas.getNumReg(con, facturaDTO);

			con.commit();

			return rows;
		} catch (Exception e) {
			log.debug("LNFacturas.getNumReg", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getNumReg ");
		}
	}

	public static int getNumRegNoConformidades( BusquedaNoConformidadesFormDTO facturaDTO ) throws Exception{

		log.debug("begin :: LNFacturas->getNumReg ");
		ConexionBD con = null;
		int rows = 0;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			rows = BDFacturas.getNumRegNoConformidades(con, facturaDTO);

			con.commit();

			return rows;
		} catch (Exception e) {
			log.debug("LNFacturas.getNumReg", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getNumReg ");
		}
	}

	public static int getNumRegControl( Integer idCSO, Integer any, Integer mes, Integer[] estats ) throws Exception{

		log.debug("begin :: LNFacturas->getNumRegControl ");
		ConexionBD con = null;
		int rows = 0;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			rows = BDFacturas.getNumRegControl(con, idCSO, any, mes, estats);

			con.commit();

			return rows;
		} catch (Exception e) {
			log.debug("LNFacturas.getNumRegControl", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getNumRegControl ");
		}
	}

	public static Vector<FactSrvTable> getTableSrvFactures( Integer idFactura, String sortDir ) throws Exception{

		log.debug("begin :: LNFacturas->getNumReg ");
		ConexionBD con = null;

		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<FactSrvTable> factSrvList = BDFacturas.getTableSrvFactures(con, idFactura, sortDir);

			con.commit();

			return factSrvList;
		} catch (Exception e) {
			log.debug("LNFacturas.getNumReg", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getNumReg ");
		}
	}

	public static void changeStateFactura( Integer idFactura, Integer idNewState ) throws Exception{

		log.debug("begin :: LNFacturas->changeStateFactura ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			BDFacturas.changeStateFactura(con, idFactura, idNewState);
			con.commit();

		} catch (Exception e) {
			log.debug("LNFacturas.changeStateFactura", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->changeStateFactura ");
		}
	}

	public static void updateObservacionesFactura( Integer idFactura, String observaciones ) throws Exception{

		log.debug("begin :: LNFacturas->updateObservacionesFactura ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			BDFacturas.updateObservacionesFactura(con, idFactura, observaciones);
			con.commit();

		} catch (Exception e) {
			log.debug("LNFacturas.updateObservacionesFactura", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->updateObservacionesFactura ");
		}
	}

	public static void insertFactura( Factura factura ) throws Exception{

		log.debug("begin :: LNFacturas->insertFactura ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			BDFacturas.insertFactura(con, factura);
			con.commit();

		} catch (Exception e) {
			log.debug("LNFacturas.insertFactura", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->insertFactura ");
		}
	}

	public static void updateImportFactura( Integer idfactura ) throws Exception{

		log.debug("begin :: LNFacturas->updateImportFactura ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans(); 
			BDFacturas.updateImportFactura(con, idfactura,null);
			con.commit();

		} catch (Exception e) {
			log.debug("LNFacturas.updateImportFactura", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->updateImportFactura ");
		}
	}

	public static Vector<FacturaTableNC> getFacturasWithNoConformidades( BusquedaNoConformidadesFormDTO facturaNCDTO, String locale,
			String sortDir, Integer pag, Integer lenght ) throws Exception{

		log.debug("begin :: LNFacturas->getFacturas ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<FacturaTableNC> factList = BDFacturas.getFacturasWithNoConformidades(con, facturaNCDTO, locale, sortDir, pag, lenght);

			con.commit();

			return factList;
		} catch (Exception e) {
			log.debug("LNFacturas.getFacturas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getFacturas ");
		}
	}

	public static FacturaDialogNC getInfoFacturaNoConformidades( Integer idfact, String locale ) throws Exception{

		log.debug("begin :: LNFacturas->getInfoFacturaNC ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			FacturaDialogNC fact = BDFacturas.getInfoFacturaNoConformidades(con, idfact, locale);

			con.commit();

			return fact;
		} catch (Exception e) {
			log.debug("LNFacturas.getInfoFacturaNC", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getInfoFacturaNC ");
		}
	}

	public static void updateServeisFactura( Integer idfact, List<ServicioFact> servicios, Integer idCso ) throws Exception{

		log.debug("begin :: LNFacturas->getInfoFacturaNC ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			BDFacturas.updateServeisFactura(con, idfact, servicios,idCso);

			con.commit();

		} catch (Exception e) {
			log.debug("LNFacturas.getInfoFacturaNC", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getInfoFacturaNC ");
		}
	}

	public static List<Basic> getCodesOfFacturesHomonimes( Integer idCso ) throws Exception{

		log.debug("begin :: LNFacturas->getCodesOfFacturesHomonimes ");
		List<Basic> facturesHominesCodes = new ArrayList<Basic>();
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			facturesHominesCodes = BDFacturas.getCodesOfFacturesHomonimes(con, idCso);

			con.commit();

		} catch (Exception e) {
			log.debug("LNFacturas.getCodesOfFacturesHomonimes", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getCodesOfFacturesHomonimes ");
		}
		return facturesHominesCodes;
	}

	public static List<FactorCrecimientoFactura> getFactoresCrecimientoFacturas( String codefactura ) throws Exception{

		log.debug("begin :: LNFacturas->getFactoresCrecimientoFacturas ");
		ConexionBD con = null;
		List<FactorCrecimientoFactura> list = new ArrayList<FactorCrecimientoFactura>();
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			list = BDFacturas.getFactoresCrecimientoFactura(con, codefactura);
			con.commit();
			return list;
		} catch (Exception e) {
			log.debug("LNFacturas.getFactoresCrecimientoFacturas", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNFacturas->getFactoresCrecimientoFacturas ");
		}
	}

	public static void saveFactoresCrecimientoFactura( FactorsCorreccioFactura factorsCorreccio ) throws Exception{

		log.debug("begin :: LNPartidas->saveFactoresCrecimientoFactura ");
		ConexionBD con = null;
		List<FactorCrecimientoFactura> listFactors = new ArrayList<FactorCrecimientoFactura>();
		try {
			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();
			int numeroDeMes = 1;
			for (numeroDeMes = 1; numeroDeMes <= 12; numeroDeMes++) {
				FactorCrecimientoFactura factor = factorsCorreccio.getFactor(numeroDeMes);
				listFactors.add(factor);
			}
			BDFacturas.insertFactoresCrecimientoFactura(con, listFactors);
			con.commit();

		} catch (Exception e) {
			log.debug("LNPartidas.saveFactoresCrecimientoFactura", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNPartidas->saveFactoresCrecimientoFactura ");
		}
	}
}
