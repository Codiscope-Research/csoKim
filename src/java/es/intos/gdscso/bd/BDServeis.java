package es.intos.gdscso.bd;

import java.util.Calendar;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

import es.intos.gdscso.forms.manteniments.BusquedaComparativaServeisFormDTO;
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.ComparativaSrvTable;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDServeis{

	public static Logger	log	= Logger.getLogger(LNPartidas.class);

	public static Vector<Basic> getServeis( ConexionBD con ) throws Exception{

		Vector<Basic> list = new Vector<Basic>(1, 1);

		String sql = "SELECT  IDSRV, DESCRIP from  SYN_SRV order by DESCRIP ASC";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Basic bc = new Basic();
				bc.setId(rs.getInt("IDSRV"));
				bc.setDescripcio(rs.getInt("IDSRV")+".-"+rs.getString("DESCRIP"));

				list.add(bc);
			}
		} catch (Exception e) {
			log.error("BDServeis.getServeis", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return list;
	}

	public static Vector<ComparativaSrvTable> getComparativaServeis( ConexionBD con, BusquedaComparativaServeisFormDTO frm, MessageResources	missatges )
			throws Exception{

		Vector<ComparativaSrvTable> list = new Vector<ComparativaSrvTable>(1, 1);
		Calendar cal = Calendar.getInstance();
		Integer monthActual = cal.get(Calendar.MONTH);
		Integer yearActual = cal.get(Calendar.YEAR);

		if (frm != null && frm.getF_cso() != null && frm.getF_csoActual() != null) {

			if (frm.getF_mes() == null)
				frm.setF_mes(monthActual.toString());
			if (frm.getF_any() == null)
				frm.setF_any(yearActual.toString());

			StringBuffer sql = new StringBuffer(
					"SELECT CSO_SRV_VOL.IDSRV AS SRVACTUAL, CSO_SRV_VOL.idcso, svs.idsrv as SRVANTERIOR,cs.descrip as namecso, CSO_SRV_VOL.MONTH "
							+ " from  CSO_SRV_VOL  LEFt JOIN " + " (SELECT sv.idsrv  FROM CSO_SRV_VOL sv WHERE  sv.month=" + frm.getF_mes()
							+ " ");
			if (!frm.getF_cso().equals("")) {
				sql.append(" and sv.idcso=" + frm.getF_cso());
			}

			sql.append(" and sv.year=" + frm.getF_any() + " ) svs " + " ON svs.IDSRV= CSO_SRV_VOL.idsrv "
					+ " left join syn_srv cs ON CSO_SRV_VOL.idsrv = cs.idsrv " + " where   MONTH=" + monthActual);

			if (!frm.getF_csoActual().equals("")) {
				sql.append(" and  idcso=" + frm.getF_csoActual() + " ");
			}

			sql.append(" and year=" + yearActual + " " + " union "
					+ " SELECT  svs.idsrv as SRVACTUAL, svs.idcso, CSO_SRV_VOL.IDSRV AS SRVANTERIOR,cs.descrip as namecso, svs.MONTH "
					+ " from  CSO_SRV_VOL  LEFt JOIN " + " (SELECT sv.idsrv,sv.month,sv.idcso  FROM CSO_SRV_VOL sv WHERE sv.month="
					+ monthActual + " ");
			if (!frm.getF_csoActual().equals("")) {
				sql.append(" and sv.idcso=" + frm.getF_csoActual() + " ");
			}
			sql.append(" and sv.year=" + yearActual + " ) svs " + " ON svs.IDSRV= CSO_SRV_VOL.idsrv "
					+ " left join syn_srv cs ON CSO_SRV_VOL.idsrv = cs.idsrv " + " where CSO_SRV_VOL.MONTH=" + frm.getF_mes() + " ");
			if (!frm.getF_cso().equals("")) {
				sql.append(" and CSO_SRV_VOL.idcso=" + frm.getF_cso() + " ");
			}
			sql.append(" and CSO_SRV_VOL.year=" + frm.getF_any() + "  ");

			if (frm.getOrder_by() != null && !frm.getOrder_by().equals("") && !frm.getOrder_by().equals("comaparacionState DESC")
					&& !frm.getOrder_by().equals("comaparacionState ASC"))
				sql.append(" order by " + frm.getOrder_by());

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql.toString());
				rs = ps.executeQuery();
				while (rs.next()) {
					ComparativaSrvTable bc = new ComparativaSrvTable();
					boolean serveiComu = true;
					if (rs.getString("SRVACTUAL").equals("")) {
						bc.setServeiActual("-");
						bc.setEstat(missatges.getMessage("servei.inexistent"));
						serveiComu = false;
					} else {
						bc.setServeiActual(rs.getString("SRVACTUAL")+".-"+rs.getString("namecso"));
					}

					if (rs.getString("SRVANTERIOR").equals("")) {
						bc.setServeiAnterior("-");
						bc.setEstat("NOU");
						serveiComu = false;
					} else {
						bc.setServeiAnterior(rs.getString("SRVANTERIOR")+".-"+rs.getString("namecso"));
					}

					if (serveiComu) {
						bc.setEstat("EXISTENT");
						bc.setServeiAnterior(rs.getString("SRVANTERIOR")+".-"+rs.getString("namecso"));
						bc.setServeiActual(rs.getString("SRVACTUAL")+".-"+rs.getString("namecso"));
					}

					list.add(bc);
				}
			} catch (Exception e) {
				log.error("BDServeis.getComparativaServeis", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return list;
	}

	public static int getComparativaServeisNumReg( ConexionBD con, BusquedaComparativaServeisFormDTO frm ) throws Exception{

		Calendar cal = Calendar.getInstance();
		Integer monthActual = cal.get(Calendar.MONTH) + 1;
		Integer yearActual = cal.get(Calendar.YEAR);

		int rows = 0;

		if (frm != null && frm.getF_cso() != null && frm.getF_csoActual() != null) {

			if (frm.getF_mes() == null)
				frm.setF_mes(monthActual.toString());
			if (frm.getF_any() == null)
				frm.setF_any(yearActual.toString());

			String sql = "select count(*) as NUMREG from("
					+ "SELECT CSO_SRV_VOL.IDSRV AS SRVACTUAL, CSO_SRV_VOL.idcso, svs.idsrv as SRVANTERIOR,cs.name as namecso, CSO_SRV_VOL.MONTH "
					+ " from  CSO_SRV_VOL  LEFt JOIN " + " (SELECT sv.idsrv  FROM CSO_SRV_VOL sv WHERE sv.idcso="
					+ frm.getF_cso()
					+ " and sv.month="
					+ frm.getF_mes()
					+ " and sv.year="
					+ frm.getF_any()
					+ " ) svs "
					+ " ON svs.IDSRV= CSO_SRV_VOL.idsrv "
					+ " left join cso_centros cs ON CSO_SRV_VOL.idsrv = cs.id "
					+ " where idcso="
					+ frm.getF_csoActual()
					+ " and MONTH="
					+ monthActual
					+ " and year="
					+ yearActual
					+ " "
					+ " union "
					+ " SELECT  svs.idsrv as SRVACTUAL, svs.idcso, CSO_SRV_VOL.IDSRV AS SRVANTERIOR,cs.name as namecso, svs.MONTH "
					+ " from  CSO_SRV_VOL  LEFt JOIN "
					+ " (SELECT sv.idsrv,sv.month,sv.idcso  FROM CSO_SRV_VOL sv WHERE sv.idcso="
					+ frm.getF_csoActual()
					+ " and sv.month="
					+ monthActual
					+ " and sv.year="
					+ yearActual
					+ " ) svs "
					+ " ON svs.IDSRV= CSO_SRV_VOL.idsrv "
					+ " left join cso_centros cs ON CSO_SRV_VOL.idsrv = cs.id "
					+ " where CSO_SRV_VOL.idcso="
					+ frm.getF_cso()
					+ " and CSO_SRV_VOL.MONTH="
					+ frm.getF_mes()
					+ " and CSO_SRV_VOL.year="
					+ frm.getF_any() + ") ";

			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					rows = rs.getInt("NUMREG");
				}
			} catch (Exception e) {
				log.error("BDServeis.getComparativaServeisNumReg", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return rows;
	}
}
