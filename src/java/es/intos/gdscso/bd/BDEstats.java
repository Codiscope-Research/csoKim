package es.intos.gdscso.bd;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.EstatHistoric;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDEstats{

	public static Logger	log	= Logger.getLogger(LNPartidas.class);

	public static Vector<Basic> getEstats( ConexionBD con, String locale ) throws Exception{

		Vector<Basic> list = new Vector<Basic>(1, 1);

		String sql = "SELECT  ID, DESCRIPCIO, DESCRIPCIO_CA, CODI from  CSO_ESTATS";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Basic bc = new Basic();
				bc.setId(rs.getInt("ID"));
				if (locale.toUpperCase().equals("CA")) {
					bc.setDescripcio(rs.getString("CODI") + ": " + rs.getString("DESCRIPCIO_CA"));
				} else {
					bc.setDescripcio(rs.getString("CODI") + ": " + rs.getString("DESCRIPCIO"));
				}
				list.add(bc);
			}
		} catch (Exception e) {
			log.error("BDEstats.getEstats", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return list;
	}

	public static void saveHisEstats( ConexionBD con, EstatHistoric statH ) throws Exception{

		if (statH.getNewStat() == null || statH.getNewStat().equals("") || statH.getOldStat() == null || statH.getOldStat().equals(""))
			return;
		String sql = " insert into CSO_HIS_ESTATS(  " + " ID," + " IDFACTURA," + " IDESTAT," + " DATAESTAT," + " ESTAT_ANTERIOR,"
				+ " DATA_ESTAT_ANTERIOR, " + " INCIDENCIA" + "   ) values ( " + "S_CSO_HIS_ESTATS.nextval,  " + " ?," + " ?," + " sysdate,"
				+ " ?," + " sysdate," + " ?" + "   )  ";
		PreparedStatement ps = null;

		try {
			ps = con.getPreparedStatement(sql);
			int count = 1;
			ps.setInt(count++, statH.getId());
			ps.setInt(count++, Integer.parseInt(statH.getNewStat()));
			ps.setInt(count++, Integer.parseInt(statH.getOldStat()));
			ps.setString(count++, (statH.getIncidencia() == null || statH.getIncidencia().equals("")) ? "0" : statH.getIncidencia());
			con.executeUpdate(ps);
		} catch (Exception e) {
			log.error("BDEstats.insert", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}
	}

	public static List<EstatHistoric> getHisEstatsOfFactura( ConexionBD con, Integer idFactura ) throws Exception{

		List<EstatHistoric> estatHistoricList = new ArrayList<EstatHistoric>();

		if (idFactura != null) {
			String sql = "SELECT  ID, IDESTAT, ESTAT_ANTERIOR, DATAESTAT  from  CSO_HIS_ESTATS WHERE IDFACTURA=" + idFactura;

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					EstatHistoric estatHistoric = new EstatHistoric();
					estatHistoric.setNewStat(rs.getString("IDESTAT"));
					estatHistoric.setDateOfChange(rs.getDate("DATAESTAT"));
					estatHistoric.setOldStat(rs.getString("ESTAT_ANTERIOR"));
					estatHistoricList.add(estatHistoric);
				}
			} catch (Exception e) {
				log.error("BDEstats.getHisEstatsOfFactura", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return estatHistoricList;
	}

}
