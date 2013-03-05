package es.intos.gdscso.bd;

import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDIncidencias{

	public static Logger	log	= Logger.getLogger(LNPartidas.class);

	public static Vector<Basic> getIncidencias( ConexionBD con, String locale, String type ) throws Exception{

		Vector<Basic> list = new Vector<Basic>(1, 1);

		if (locale != null && type != null) {
			String sql = "SELECT  ID, DESCRIPCIO, DESCRIPCIO_ES from  CSO_INC where TYPE_INC='" + type + "'";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Basic bc = new Basic();
					bc.setId(rs.getInt("ID"));
					if (locale.toUpperCase().equals("CA")) {
						bc.setDescripcio(rs.getString("DESCRIPCIO"));
					} else {
						bc.setDescripcio(rs.getString("DESCRIPCIO_ES"));
					}
					list.add(bc);
				}
			} catch (Exception e) {
				log.error("BDIncidencias.getIncidencias", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return list;
	}

	public static String getIncidenciaActual( ConexionBD con, String locale, Integer fact ) throws Exception{

		String incidencia = "";
		if (locale != null && fact != null) {
			String sql = "SELECT INCIDENCIA from  CSO_HIS_ESTATS where IDFACTURA=" + fact + "";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					incidencia = rs.getString("INCIDENCIA");
				}
				int incInt = Integer.parseInt(incidencia);

				incidencia = getIncidencia(con, locale, incInt);
			} catch (NumberFormatException nfe) {
				return incidencia;
			} catch (Exception e) {
				log.error("BDIncidencias.getIncidencias", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return incidencia;
	}

	public static String getIncidencia( ConexionBD con, String locale, Integer id ) throws Exception{

		String sql = "SELECT  DESCRIPCIO, DESCRIPCIO_ES from  CSO_INC where ID=" + id;
		String inc = "";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				if (locale.toUpperCase().equals("CA")) {
					inc = rs.getString("DESCRIPCIO");
				} else {
					inc = rs.getString("DESCRIPCIO_ES");
				}
			}
		} catch (Exception e) {
			log.error("BDIncidencias.getIncidencias", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return inc;
	}

}
