package es.intos.gdscso.bd;

import org.apache.log4j.Logger;

import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDImportPactat{

	public static Logger	log	= Logger.getLogger(LNPartidas.class);

	public static void saveOrUpdateImportPactat( ConexionBD con, Integer year, Double importe ) throws Exception{

		Basic basic = getImportPactat(con, year);
		PreparedStatement ps = null;
		int count = 1;
		if (importe == null)
			importe = 0.0;
		if (year == null)
			return;
		try {
			if (basic == null) {
				String sqlForSave = "insert into CSO_IMPORTS_PACTATS (importe,year) values (?,?)";
				ps = con.getPreparedStatement(sqlForSave);

			} else {

				String sqlForUpdate = "update CSO_IMPORTS_PACTATS set importe = ? where year=?";
				ps = con.getPreparedStatement(sqlForUpdate);
			}

			ps.setDouble(count++, importe);
			ps.setInt(count++, year);
			con.executeUpdate(ps);

		} catch (Exception e) {
			log.error("BDImportPactat.saveOrUpdateImportPactat", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}

	}

	public static Basic getImportPactat( ConexionBD con, Integer year ) throws Exception{

		Basic basic = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (year == null)
			return basic;

		try {
			String sql = "select importe, year from CSO_IMPORTS_PACTATS where year=" + year;
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				basic = new Basic();
				basic.setId(rs.getInt("year"));
				basic.setDescripcio(rs.getString("importe"));
			}
		} catch (Exception e) {
			log.error("BDImportPactat.getImportPactat", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return basic;
	}

}
