package es.intos.gdscso.bd;

import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.Cso;
import es.intos.gdscso.utils.Constants;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDCso{

	public static Logger	log	= Logger.getLogger(LNPartidas.class);

	public static Vector<Basic> getCSOs( ConexionBD con ) throws Exception{

		Vector<Basic> list = new Vector<Basic>(1, 1);

		String sql = "SELECT  ID, NAME from  CSO_CENTROS order by NAME ASC";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Basic bc = new Basic();
				bc.setId(rs.getInt("ID"));
				bc.setDescripcio(rs.getString("NAME"));

				list.add(bc);
			}
		} catch (Exception e) {
			log.error("BDCso.getEstats", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return list;
	}

	public static Cso getCSOFromManteniment( ConexionBD con, Integer idCSO ) throws Exception{

		

		String sql = "SELECT  ID, IVA, IGIC, DESCUENTO, IMPUESTO, FACTURACIO, NRECURSOS, PRECIO_RECURSO from  CSO where ID="+idCSO;

		PreparedStatement ps = null;
		ResultSet rs = null;
		Cso cso = new Cso();
		try {
			Vector<Basic> csolist = getCSO(con, idCSO);
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				cso.setId(rs.getInt("ID"));
				cso.setIva(rs.getBoolean("IVA"));
				cso.setIgic(rs.getBoolean("IGIC"));
				cso.setDescuento(rs.getDouble("DESCUENTO"));
				cso.setImpuesto(rs.getDouble("IMPUESTO"));
				cso.setServicioRecurso(rs.getString("FACTURACIO"));	
				cso.setPreuunitari(rs.getDouble("PRECIO_RECURSO"));
				cso.setNrecursos(rs.getDouble("NRECURSOS"));
				
			}
			if(!csolist.isEmpty()){
				Basic bs = csolist.get(0);
				cso.setDescripcio(bs.getDescripcio());
			}
		} catch (Exception e) {
			log.error("BDCso.getEstats", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return cso;
	}
	
public static void saveCSOInManteniment( ConexionBD con, Cso cso ) throws Exception{

		String sql = "INSERT INTO CSO  (ID, IVA, IGIC, DESCUENTO, IMPUESTO, FACTURACIO, NRECURSOS, PRECIO_RECURSO) values (?,?,?,?,?,?,?,?)";
		String sql_update = "UPDATE CSO  set  IVA=?, IGIC=?, DESCUENTO=?, IMPUESTO=?, FACTURACIO=?, NRECURSOS=? , PRECIO_RECURSO=?  where ID=?";

		PreparedStatement ps = null;
		
		try {
			
			if(cso.getId()==null) return;
				
				int count =1;
							
				Cso csoInDB = getCSOFromManteniment(con,cso.getId());
				
				if(csoInDB!=null && csoInDB.getId()!=null){
					//fem update
					ps = con.getPreparedStatement(sql_update);
					ps.setBoolean(count++, cso.isIva());
					ps.setBoolean(count++, cso.isIgic());
					ps.setDouble(count++, cso.getDescuento()==null? 0.0 : cso.getDescuento());
					ps.setDouble(count++, cso.getImpuesto()==null? 0.0 : cso.getImpuesto());
					ps.setString(count++, cso.getServicioRecurso()==null? Constants.FACTURACIO_SERVEI : cso.getServicioRecurso());
					ps.setDouble(count++, cso.getNrecursos()==null? 0.0 : cso.getNrecursos());
					ps.setDouble(count++, cso.getPreuunitari()==null? 0.0 : cso.getPreuunitari());
					ps.setInt(count++, cso.getId());
					
				}else{
					ps = con.getPreparedStatement(sql);
					
					ps.setInt(count++,cso.getId());
					ps.setBoolean(count++, cso.isIva());
					ps.setBoolean(count++, cso.isIgic());
					ps.setDouble(count++, cso.getDescuento()==null? 0.0 : cso.getDescuento());
					ps.setDouble(count++, cso.getImpuesto()==null? 0.0 : cso.getImpuesto());
					ps.setString(count++, cso.getServicioRecurso()==null? Constants.FACTURACIO_SERVEI : cso.getServicioRecurso());
					ps.setDouble(count++, cso.getNrecursos()==null? 0.0 : cso.getNrecursos());
					ps.setDouble(count++, cso.getPreuunitari()==null? 0.0 : cso.getPreuunitari());
					
				}
				
				ps.executeQuery();
				
	
			
		} catch (Exception e) {
			log.error("BDCso.getEstats", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
	}
	
	public static Vector<Basic> getCSO( ConexionBD con, Integer idcso ) throws Exception{

		Vector<Basic> list = new Vector<Basic>(1, 1);

		String sql = "SELECT  ID, NAME from  CSO_CENTROS where ID=" + idcso;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Basic bc = new Basic();
				bc.setId(rs.getInt("ID"));
				bc.setDescripcio(rs.getString("NAME"));

				list.add(bc);
			}
		} catch (Exception e) {
			log.error("BDCso.getEstats", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return list;
	}

}
