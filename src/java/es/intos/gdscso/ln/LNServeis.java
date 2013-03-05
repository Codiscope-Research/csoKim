package es.intos.gdscso.ln;

import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

import es.intos.gdscso.bd.BDServeis;
import es.intos.gdscso.forms.manteniments.BusquedaComparativaServeisFormDTO;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.ComparativaSrvTable;
import es.intos.gdscso.utils.Recursos;
import es.intos.util.sql.ConexionBD;

public class LNServeis{

	public static Logger	log	= Logger.getLogger(LNServeis.class);

	private LNServeis() {

	}

	public static String[] getOrderBy_ASC(){

		String[] asc = { "nameServ ASC", "nameServ ASC", "comaparacionState ASC" };
		return asc;
	}

	public static String[] getOrderBy_DESC(){

		String[] desc = { "nameServ DESC", "nameServ DESC", "comaparacionState DESC" };
		return desc;
	}

	public static String[] getOrderBy_ASC_Manteniment(){

		String[] asc = { "namecso ASC", "namecso ASC", "comaparacionState ASC" };
		return asc;
	}

	public static String[] getOrderBy_DESC_Manteniment(){

		String[] desc = { "namecso DESC", "namecso DESC", "comaparacionState DESC" };
		return desc;
	}

	public static Vector<Basic> getServeis() throws Exception{

		log.debug("begin :: LNServeis->getServeis ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<Basic> basicSrvs = BDServeis.getServeis(con);

			con.commit();

			return basicSrvs;
		} catch (Exception e) {
			log.debug("LNServeis.getServeis", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNServeis->getServeis ");
		}
	}

	public static Vector<ComparativaSrvTable> getComparativaServeis( BusquedaComparativaServeisFormDTO frm, MessageResources	missatges ) throws Exception{

		log.debug("begin :: LNServeis->getServeis ");
		ConexionBD con = null;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			Vector<ComparativaSrvTable> comparativaSrvs = BDServeis.getComparativaServeis(con, frm, missatges);

			con.commit();

			return comparativaSrvs;
		} catch (Exception e) {
			log.debug("LNServeis.getServeis", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNServeis->getServeis ");
		}
	}

	public static int getComparativaServeisNumReg( BusquedaComparativaServeisFormDTO frm ) throws Exception{

		log.debug("begin :: LNServeis->getServeis ");
		ConexionBD con = null;
		int rows = 0;
		try {

			con = Recursos.gbd.getConexionBD(Recursos.nombd, false);
			con.beginTrans();

			rows = BDServeis.getComparativaServeisNumReg(con, frm);

			con.commit();

			return rows;
		} catch (Exception e) {
			log.debug("LNServeis.getServeis", e);
			if (null != con)
				con.rollback();
			throw e;
		} finally {
			if (null != con)
				con.close();
			log.debug("end ::LNServeis->getServeis ");
		}
	}

}
