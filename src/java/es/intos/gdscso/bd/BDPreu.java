package es.intos.gdscso.bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.Preu;
import es.intos.gdscso.on.Tram;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDPreu{

	public static Logger	log	= Logger.getLogger(BDPreu.class);

	public static void deletePreu( ConexionBD con, Integer id ) throws Exception{

		String sql = " delete from CSO_MT_PRECIOS where ID=" + id;
		PreparedStatement ps = null;

		try {
			ps = con.getPreparedStatement(sql);
			con.executeUpdate(ps);
		} catch (Exception e) {
			log.error("BDPreu.insertPreu", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}

	}

	public static int insertPreu( ConexionBD con, Preu oo, String totscso ) throws Exception{

		String sql = "   insert into CSO_MT_PRECIOS(ID ,IDCSO,IDSRV,TIPO ) values ( S_CSO_PREUS.NEXTVAL, ?, ?, ? )  ";

		PreparedStatement ps = null;
		int rows = 0;
		int id = 0;
		if (oo != null && oo.getIdCSO() != null && oo.getIdSRV() != null) {
			try {
				if (totscso.equals("false")) {
					// Guardem preu per un cso
					HashMap idPHashMap = selectIdPreu(con, oo.getIdCSO(), oo.getIdSRV());					
					Integer idP = (Integer) idPHashMap.get("id");
					List<Tram> pr = getPreu(con, oo.getIdCSO(), oo.getIdSRV());
					if (!pr.isEmpty()) {
						// borrem trams
						Preu ooo = new Preu(idP, "", oo.getIdCSO(), oo.getIdSRV(), null,null);
						deleteTrams(con, ooo);
					}
					if (idP != null) {
						// borrem el preu
						deletePreu(con, idP);
					}
					// Ara fem insert
					ps = con.getPreparedStatement(sql);
					int count = 1;
					ps.setDouble(count++, oo.getIdCSO());
					ps.setDouble(count++, oo.getIdSRV());
					
					if (oo.getTrams() != null && oo.getTrams().size() == 1) {
						ps.setInt(count++, 0);
					} else if (oo.getTrams() != null && oo.getTrams().size() > 1){
						ps.setInt(count++, 1);
					}else{
						ps.setInt(count++, 2);
					}
					
					
					rows = con.executeUpdate(ps);
					if (0 == rows)
						throw new SQLException("no rows inserted");

					id = selectMaxIdPreu(con);
					if (oo.getTrams() != null && !oo.getTrams().isEmpty()) {
						// insertem els trams
						for (Tram tr : oo.getTrams()) {
							insertTram(con, tr, id);
						}
					}else if(oo.getMensual()!=null){
						insertTram(con,oo.getMensual(),id);
					}
					
				} else if (totscso.equals("true")) {
					// Guardem per tots els cso
					Vector<Basic> csos = BDCso.getCSOs(con);
					for (Basic b : csos) {
						HashMap idPHashMap = selectIdPreu(con, b.getId(), oo.getIdSRV());
						Integer idP = (Integer) idPHashMap.get("id");
						List<Tram> pr = getPreu(con, b.getId(), oo.getIdSRV());
						if (!pr.isEmpty()) {
							// borrem trams
							Preu ooo = new Preu(idP, "", b.getId(), oo.getIdSRV(), null,null);
							deleteTrams(con, ooo);
						}
						if (idP != null) {
							// borrem el preu
							deletePreu(con, idP);
						}
						// Ara fem insert
						ps = con.getPreparedStatement(sql);
						int count = 1;
						ps.setDouble(count++, b.getId());
						ps.setDouble(count++, oo.getIdSRV());
						if (oo.getTrams() != null && oo.getTrams().size() == 1) {
							ps.setInt(count++, 0);
						} else {
							ps.setInt(count++, 1);
						}									
						
						rows = con.executeUpdate(ps);
						if (0 == rows)
							throw new SQLException("no rows inserted");

						id = selectMaxIdPreu(con);
						if (oo.getTrams() != null && !oo.getTrams().isEmpty()) {
							// insertem els trams
							for (Tram tr : oo.getTrams()) {
								insertTram(con, tr, id);
							}
						}else if(oo.getMensual()!=null){
							insertTram(con,oo.getMensual(),id);
						}
					}
				}
			} catch (Exception e) {
				log.error("BDPreu.insertPreu", e);
				throw e;
			} finally {
				log.info(rows + " rows inserted.");
				if (null != ps)
					ps.close();
			}
		}
		return id;
	}

	public static int insertTram( ConexionBD con, Double mes, Integer idPreu ) throws Exception{

		String sql = "   insert into CSO_MT_PRECIOS_TR(	IDPRECIO, PRECIO           "
				+ "   ) values (   ?, ?   )  ";
		PreparedStatement ps = null;
		int rows = 0;

		try {
			ps = con.getPreparedStatement(sql);
			int count = 1;
			ps.setInt(count++, idPreu);
			ps.setDouble(count++, mes);

			rows = con.executeUpdate(ps);
			if (0 == rows)
				throw new SQLException("no rows inserted");

		} catch (Exception e) {
			log.error("BDPreu.insertTram", e);
			throw e;
		} finally {
			log.info(rows + " rows inserted.");
			if (null != ps)
				ps.close();
		}
		return rows;
	} // end insert

	public static void deleteTrams( ConexionBD con, Preu oo ) throws Exception{

		if (oo != null && oo.getId() != null) {
			String sql = "delete from CSO_MT_PRECIOS_TR where IDPRECIO=" + oo.getId();

			PreparedStatement ps = null;
			try {
				ps = con.getPreparedStatement(sql);
				con.executeUpdate(ps);
			} catch (Exception e) {
				log.error("BDPreu.deleteTrams", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
	}

	public static void updatePreu( ConexionBD con, Preu oo ) throws Exception{

		PreparedStatement ps = null;
		try {
			
			// primer borrem els trams
			deleteTrams(con, oo);
			if (oo.getTrams() != null && !oo.getTrams().isEmpty()) {
				// insertem els trams
				for (Tram tr : oo.getTrams()) {
					insertTram(con, tr, oo.getId());
				}
			}
			
		} catch (Exception e) {
			log.error("BDPreu.updatePreu", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
	} // end insert

	public static int insertTram( ConexionBD con, Tram oo, Integer idPreu ) throws Exception{

		String sql = "   insert into CSO_MT_PRECIOS_TR(	IDPRECIO, NUMTRAM, VOLINI, VOLFIN, PRECIO           "
				+ "   ) values (   ?, 	?,	?,	?, 	?   )  ";
		PreparedStatement ps = null;
		int rows = 0;

		try {
			ps = con.getPreparedStatement(sql);
			int count = 1;
			ps.setInt(count++, idPreu);
			ps.setInt(count++, oo.getNumTram());
			ps.setDouble(count++, oo.getDesde());
			if (oo.getHasta() != null) {
				ps.setDouble(count++, oo.getHasta());
			} else {
				ps.setNull(count++, java.sql.Types.DOUBLE);
			}

			ps.setDouble(count++, oo.getPreu());

			rows = con.executeUpdate(ps);
			if (0 == rows)
				throw new SQLException("no rows inserted");

		} catch (Exception e) {
			log.error("BDPreu.insertTram", e);
			throw e;
		} finally {
			log.info(rows + " rows inserted.");
			if (null != ps)
				ps.close();
		}
		return rows;
	} // end insert

	public static List<Tram> getPreu( ConexionBD con, Integer cso, Integer srv ) throws Exception{

		List<Tram> list = new ArrayList<Tram>();
		if (cso != null && srv != null) {
			StringBuffer sqlSB = new StringBuffer("select IDPRECIO,NUMTRAM,VOLINI,VOLFIN,PRECIO  from CSO_MT_PRECIOS_TR tr "
					+ "LEFT JOIN  CSO_MT_PRECIOS pr ON(tr.IDPRECIO= pr.ID AND pr.IDCSO=" + cso + " and pr.IDSRV=" + srv
					+ ") where tr.IDPRECIO= pr.ID order by NUMTRAM asc");

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sqlSB.toString());
				rs = ps.executeQuery();
				while (rs.next()) {
					Tram tr = new Tram();
					tr.setNumTram(rs.getInt("NUMTRAM"));
					tr.setDesde(rs.getDouble("VOLINI"));
					tr.setHasta(rs.getDouble("VOLFIN"));
					tr.setPreu(rs.getDouble("PRECIO"));
					list.add(tr);
				}
			} catch (Exception e) {
				log.error("BDPreu.getPreu", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return list;
	}

	public static int selectMaxIdPreu( ConexionBD con ) throws Exception{

		StringBuffer sql = new StringBuffer("SELECT MAX(ID) AS MAX  from CSO_MT_PRECIOS");

		PreparedStatement ps = null;
		ResultSet rs = null;
		int rows = 0;

		try {
			ps = con.getPreparedStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				rows = rs.getInt("MAX");
			}
		} catch (Exception e) {
			log.error("BDPreu.selectMaxIdPreu", e);
			throw e;
		} finally {
			log.info(rows + " rows selectMaxIdPreu.");
			if (null != ps)
				ps.close();
		}

		return rows;
	}

	public static HashMap<String,Integer> selectIdPreu( ConexionBD con, Integer cso, Integer srv ) throws Exception{

		Integer id = null;
		Integer tipo = null;
		HashMap<String, Integer> intHash = new HashMap<String,Integer>();
		
		if (cso != null && srv != null) {

			StringBuffer sql = new StringBuffer("SELECT ID, TIPO from CSO_MT_PRECIOS where IDCSO=" + cso + " AND IDSRV=" + srv);
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql.toString());
				rs = ps.executeQuery();
				while (rs.next()) {
					id = rs.getInt("ID");
					tipo = rs.getInt("TIPO");
					intHash.put("id", id);
					intHash.put("tipo", tipo);	
				}
				
			} catch (Exception e) {
				log.error("BDPreu.selectIdPreu", e);
				throw e;
			} finally {

				if (null != ps)
					ps.close();
			}
		}
		return intHash;
	}

}
