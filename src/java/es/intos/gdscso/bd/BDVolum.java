package es.intos.gdscso.bd;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.CsoSrvTable;
import es.intos.gdscso.on.ServicioFact;
import es.intos.gdscso.on.SrvCSOTable;
import es.intos.gdscso.on.SrvDispTable;
import es.intos.gdscso.utils.Constants;
import es.intos.gdscso.utils.Utils;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDVolum{

	public static Logger	log	= Logger.getLogger(LNPartidas.class);

	public static int getNumSrvWithoutFact( ConexionBD con, Integer idcso, Integer year, Integer month ) throws Exception{

		int rows = 0;
		if (idcso != null && year != null && month != null) {
			String sql = "SELECT  ID from  CSO_SRV_VOL srvol  where srvol.IDSRV " + "NOT IN( select IDSRV from CSO_FACT_SRV factsrv "
					+ "where factsrv.IDFACTURA IN (select ID from CSO_FACTURAS  where IDCSO=" + idcso + " and ANY_FACT=" + year
					+ " and MES_FACT=" + month + " and IDESTADO !=7)) AND srvol.MONTH=" + month + " AND srvol.YEAR=" + year + " AND srvol.IDCSO=" + idcso;

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {

					rows++;
				}
			} catch (Exception e) {
				log.error("BDVolum.getNumSrvWithoutFact", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return rows;
	}

	public static Vector<SrvCSOTable> getSrvWithVol( ConexionBD con, Integer idcso, Integer year, Integer month, String locale, String sort,String context )
			throws Exception{

		Vector<SrvCSOTable> srvs = new Vector<SrvCSOTable>(1, 1);
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat twoDecimalForm = new DecimalFormat("#,##0.00", symbols);

		if (idcso != null && year != null && month != null) {

			String sql = "SELECT  srvol.VOLUM, srvol.IDSRV, srv.DESCRIP, fac.ID,fac.code,est.descripcio, est.descripcio_ca, prtr.precio, srvol.importe as importe, "
					+ " ROW_NUMBER() OVER (ORDER BY "
					+ sort
					+ ") as RN "
					+ " from CSO_SRV_VOL srvol,"
					+ " SYN_SRV srv left join CSO_FACT_SRV factsrv  ON factsrv.IDSRV=srv.idsrv and factsrv.idfactura IN (select ID from cso_facturas where IDCSO="
					+ idcso
					+ "and CSO_FACTURAS.MES_FACT = "
					+ month
					+ " and CSO_FACTURAS.ANY_FACT = "
					+ year
					+ ") "
					+ "left join cso_facturas fac"
					+ " ON (factsrv.idfactura=fac.id and  fac.mes_fact="
					+ month
					+ " and  fac.any_fact="
					+ year
					+ " and  fac.idcso="
					+ idcso
					+ " and  factsrv.idfactura=fac.id )"
					+ " left join  CSO_MT_PRECIOS pr ON pr.idCSO ="
					+ idcso
					+ " AND pr.idsrv=srv.idsrv"
					+ " left join  CSO_MT_PRECIOS_TR prtr ON prtr.idprecio = pr.id left join cso_estats est ON(est.ID= fac.IDESTADO)"
					+ " where  srvol.MONTH="
					+ month
					+ " AND srvol.YEAR="
					+ year
					+ " AND srvol.IDCSO="
					+ idcso
					+ " AND srv.IDSRV=srvol.IDSRV";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					SrvCSOTable srvT = new SrvCSOTable();
					srvT.setServei(Utils.changeEncode(rs.getString("DESCRIP")));
					if (rs.getInt("ID") != 0) {
						srvT.setFactura("<a href=\"/"+context+"/GestioFacturaAction.do?id=" + rs.getInt("ID") + "\" >  " + rs.getInt("ID") + "_"
								+ rs.getString("code") + "</a>");
					} else {
						if (locale != null && locale.toUpperCase().equals("CA")) {
							srvT.setEstatFactura("Pendent");
							srvT.setFactura("");
						} else {
							srvT.setEstatFactura("Pendiente");
							srvT.setFactura("");
						}
					}
					srvT.setVolum(rs.getString("VOLUM"));
					if (locale != null && locale.toUpperCase().equals("CA") && rs.getInt("ID") != 0) {
						srvT.setEstatFactura(Utils.changeEncode(rs.getString("DESCRIPCIO_CA")));
					} else if (rs.getInt("ID") != 0) {
						srvT.setEstatFactura(Utils.changeEncode(rs.getString("DESCRIPCIO")));
					}
					srvT.setPreu(twoDecimalForm.format(rs.getDouble("IMPORTE"))+ "&#8364;");
					srvs.add(srvT);
				}
			} catch (Exception e) {
				log.error("BDVolum.getSrvWithVol", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return srvs;
	}

	public static Vector<CsoSrvTable> getSrvCsoFacts( ConexionBD con, Integer year, Integer month, Integer inici, Integer lenght,
			String sortDir, String context ) throws Exception{

		Vector<CsoSrvTable> srvs = new Vector<CsoSrvTable>(1, 1);

		if (year != null && month != null) {
			if (inici == null)
				inici = 0;
			if (lenght == null)
				lenght = 20;
			if (sortDir == null || sortDir.equals(""))
				sortDir = "cso.name ASC ";
			String sql = "SELECT * from (SELECT   cso.name,cso.id,sr.srvt as TSRV,fac.fat as TFAC,  ROW_NUMBER() OVER (ORDER BY " + sortDir
					+ ") as RN " + "from CSO_CENTROS cso " + "left join "
					+ "(select CSO_SRV_VOL.IDCSO, count(cso_srv_vol.idsrv) as srvt from CSO_SRV_VOL where cso_srv_vol.month=" + month
					+ " and cso_srv_vol.year=" + year + "  group by IDCSO) sr " + "ON sr.IDCSO=cso.id " + "left join "
					+ "(select cso_facturas.idcso,count(cso_facturas.id) as fat from cso_facturas where cso_facturas.any_fact=" + year
					+ " and cso_facturas.mes_fact=" + month + " group by IDCSO) fac " + "ON fac.IDCSO=cso.id"
					+ ")  where RN BETWEEN ? AND ?";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				ps.setInt(1, inici);
				ps.setInt(2, inici + lenght - 1);
				rs = ps.executeQuery();
				while (rs.next()) {
					CsoSrvTable srvT = new CsoSrvTable();
					boolean rojo = false;
					srvT.setCso("<a href=\"#\" id=\"g_" + rs.getString("ID") + "\" onclick=\"openfactSrv('" + rs.getString("ID") + "')\" >"
							+ rs.getString("NAME") + "</a>");
					srvT.settFactures("<a href=\"#\" id=\"g_" + rs.getString("ID") + "\" onclick=\"openfactSrv('" + rs.getString("ID")
							+ "')\" >" + rs.getString("TFAC") + "</a>");
					srvT.settServeis("<a href=\"#\" id=\"g_" + rs.getString("ID") + "\" onclick=\"openfactSrv('" + rs.getString("ID")
							+ "')\" >" + rs.getString("TSRV") + "</a>");
					int srvsWithOut = getNumSrvWithoutFact(con, rs.getInt("ID"), year, month);
					if (srvsWithOut != 0) {
						rojo = true;
						srvT.setEstat("<img id=\"g_" + rs.getString("ID")
								+ "\"  src=\"/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/16x16/rojo.png\">");
					}

					if (rojo == false) {
						Integer[] estats = new Integer[] { 2, 3, 4, 5, 8, 9 };
						int rows = BDFacturas.getNumRegControl(con, rs.getInt("ID"), year, month, estats);
						if (rows != 0) {
							// ambar
							srvT.setEstat("<img id=\"g_" + rs.getString("ID")
									+ "\" src=\"/"+context+"/web/img/naranja.png\">");
						} else {
							// verd
							Vector<SrvCSOTable>  srvsCSO = getSrvWithVol(con,Integer.parseInt(rs.getString("ID")) , year, month, "CA", null, context);
							if(srvsCSO.isEmpty()){
								srvT.setEstat("<img id=\"g_" + rs.getString("ID")
										+ "\" src=\"/"+context+"/web/img/grey.png\">");
								
							}else{
								srvT.setEstat("<img id=\"g_" + rs.getString("ID")
										+ "\" src=\"/ParticipadasIntosWeb/web/img/symbolize-icons-set/png/16x16/verde.png\">");
							}
						}
					}

					srvs.add(srvT);
				}
			} catch (Exception e) {
				log.error("BDVolum.getSrvCsoFacts", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return srvs;
	} // end insert

	/**
	 * 
	 * @param con
	 * @param cso
	 * @param year
	 * @param month
	 * @param locale
	 * @param inici
	 * @param lenght
	 * @param sortDir
	 * @return busca serveis donat un cso, un any, un mes
	 * @throws Exception
	 */
	public static Vector<SrvDispTable> getSrvFacts( ConexionBD con, Integer cso, Integer year, Integer month, String locale, Integer inici,
			Integer lenght, String sortDir ) throws Exception{

		Vector<SrvDispTable> srvs = new Vector<SrvDispTable>(1, 1);
		Map<String, Integer> checkBoxControl = new HashMap<String, Integer>();

		if (cso != null && year != null && month != null) {
			if (inici == null)
				inici = 0;
			if (lenght == null)
				lenght = 20;
			if (sortDir == null || sortDir.equals(""))
				sortDir = " sr.DESCRIP ASC ";

			String sql = "SELECT * from "
					+ " (SELECT   srvol.IDSRV, sr.DESCRIP,srvfac.ID, srvfac.IDESTADO,srvfac.code, est.DESCRIPCIO, est.DESCRIPCIO_CA, ROW_NUMBER() OVER (ORDER BY "
					+ sortDir
					+ ") as RN "
					+ " from CSO_SRV_VOL srvol left join SYN_SRV sr ON sr.IDSRV=srvol.IDSRV "
					+ " left join "
					+ "(select CSO_FACT_SRV.IDSRV,fac.IDESTADO, fac.id,fac.code, fac.idcso  from"
					+ " CSO_FACT_SRV left join"
					+ " (select cso_facturas.idcso,cso_facturas.id,cso_facturas.code, cso_facturas.IDESTADO from cso_facturas where cso_facturas.any_fact="
					+ year
					+ " and cso_facturas.mes_fact="
					+ month
					+ " and cso_facturas.idcso="
					+ cso
					+ ") fac "
					+ " ON cso_fact_srv.idfactura= fac.id ) srvfac "
					+ " ON srvfac.IDSRV=srvol.IDSRV and srvfac.idcso = "
					+ cso
					+ " left join  CSO_ESTATS  est ON est.ID= srvfac.IDESTADO"
					+ " where srvol.MONTH="
					+ month
					+ " AND srvol.YEAR="
					+ year
					+ " AND srvol.IDCSO=" + cso + ")" + " where RN BETWEEN ? AND ?";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				ps.setInt(1, inici);
				ps.setInt(2, inici + lenght - 1);
				rs = ps.executeQuery();
				int i = 0;
				while (rs.next()) {
					SrvDispTable srvT = new SrvDispTable();
					if (rs.getString("ID") == null || rs.getString("ID").equals("") || rs.getInt("IDESTADO") == Constants.ESTAT_CANCEL) {
						if (checkBoxControl.get(rs.getString("IDSRV")) == null && checkBoxControl.get(rs.getString("IDSRV") + "_c") == null) {
							srvT.setServei("<input type=\"checkbox\" id=\"s_" + rs.getString("IDSRV") + "\" onclick=\"saveSrv(this)\" > "
									+ Utils.changeEncode(rs.getString("DESCRIP")));
							checkBoxControl.put(rs.getString("IDSRV") + "_c", i);
						} else {
							srvT.setServei(Utils.changeEncode(rs.getString("DESCRIP")));
						}
					} else {
						srvT.setServei(Utils.changeEncode(rs.getString("DESCRIP")));
						checkBoxControl.put(rs.getString("IDSRV"), i);
						if (checkBoxControl.get(rs.getString("IDSRV") + "_c") != null) {
							srvs.get(checkBoxControl.get(rs.getString("IDSRV") + "_c")).setServei(
									Utils.changeEncode(rs.getString("DESCRIP")));
						}
					}
					srvT.setId(rs.getInt("IDSRV"));
					srvT.setFactura("Codi Factura  " + rs.getString("CODE"));
					if (locale != null && locale.toUpperCase().equals("CA")) {
						srvT.setEstatFactura(Utils.changeEncode(rs.getString("DESCRIPCIO_CA")));
					} else {
						srvT.setEstatFactura(Utils.changeEncode(rs.getString("DESCRIPCIO")));
					}

					srvs.add(srvT);
					i = i + 1;
				}
			} catch (Exception e) {
				log.error("BDVolum.getSrvFacts", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return srvs;
	}

	private static List<Basic> getSrvOfHomonima( ConexionBD con, String code ) throws Exception{

		List<Basic> basicVector = new LinkedList<Basic>();
		if (code == null)
			return basicVector;
		String sql = "select distinct(idsrv) from CSO_FACT_SRV where idfactura IN (select id from CSO_FACTURAS where code='" + code + "' )";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Basic bs = new Basic(rs.getInt("idsrv"), rs.getString("idsrv"));
				basicVector.add(bs);
			}
		} catch (Exception e) {
			log.error("BDVolum.getSrvFacts", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return basicVector;

	}

	public static Vector<SrvDispTable> getSrvFactsForHomonima( ConexionBD con, String code, Integer cso, Integer year, Integer month,
			String locale, Integer inici, Integer lenght, String sortDir ) throws Exception{

		Vector<SrvDispTable> srvs = new Vector<SrvDispTable>(1, 1);

		if (cso != null && year != null && month != null && code != null) {
			if (inici == null)
				inici = 0;
			if (lenght == null)
				lenght = 20;
			if (sortDir == null || sortDir.equals(""))
				sortDir = " sr.DESCRIP ASC ";

			List<Basic> serveisHomonims = getSrvOfHomonima(con, code);
			List<Basic> serveisHomonimsNoChecked = new LinkedList<Basic>(serveisHomonims);
			List<Basic> serveisHomonimsCheckedList = new LinkedList<Basic>();
			String sql = "SELECT * from "
					+ " (SELECT   srvol.IDSRV, sr.DESCRIP,srvfac.ID, srvfac.IDESTADO, est.DESCRIPCIO, est.DESCRIPCIO_CA, ROW_NUMBER() OVER (ORDER BY "
					+ sortDir
					+ ") as RN "
					+ " from CSO_SRV_VOL srvol left join SYN_SRV sr ON sr.IDSRV=srvol.IDSRV "
					+ " left join "
					+ "(select CSO_FACT_SRV.IDSRV,fac.IDESTADO, fac.id, fac.idcso  from"
					+ " CSO_FACT_SRV left join"
					+ " (select cso_facturas.idcso,cso_facturas.id, cso_facturas.IDESTADO from cso_facturas where cso_facturas.any_fact="
					+ year
					+ " and cso_facturas.mes_fact="
					+ month
					+ " and cso_facturas.idcso="
					+ cso
					+ ") fac "
					+ " ON cso_fact_srv.idfactura= fac.id ) srvfac "
					+ " ON srvfac.IDSRV=srvol.IDSRV and srvfac.idcso = "
					+ cso
					+ " left join  CSO_ESTATS  est ON est.ID= srvfac.IDESTADO"
					+ " where srvol.MONTH="
					+ month
					+ " AND srvol.YEAR="
					+ year
					+ " AND srvol.IDCSO=" + cso + ")" + " where RN BETWEEN ? AND ?";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				ps.setInt(1, inici);
				ps.setInt(2, inici + lenght - 1);
				rs = ps.executeQuery();
				while (rs.next()) {
					SrvDispTable srvT = new SrvDispTable();
					if (rs.getString("ID") == null || rs.getString("ID").equals("")) {
						boolean serveiHomonim = false;
						for (Basic srv : serveisHomonims) {
							if (rs.getString("IDSRV").equals(srv.getDescripcio())) {
								srvT.setServei("<input type=\"checkbox\" id=\"s_" + rs.getString("IDSRV")
										+ "\" onclick=\"\" checked=checked  disabled > " + Utils.changeEncode(rs.getString("DESCRIP")));
								serveiHomonim = true;
								serveisHomonimsCheckedList.add(srv);
								serveisHomonimsNoChecked.remove(srv);
							}
						}
						if (!serveiHomonim) {
							srvT.setServei("<input type=\"checkbox\" id=\"s_" + rs.getString("IDSRV") + "\" onclick=\"\" disabled > "
									+ Utils.changeEncode(rs.getString("DESCRIP")));
						}
					} else {
						boolean serveiNotInHomonim = true;
						for (Basic srv : serveisHomonimsNoChecked) {
							if (rs.getString("IDSRV").equals(srv.getDescripcio())) {
								srvT.setServei("<font color='red'>" + Utils.changeEncode(rs.getString("DESCRIP")) + "</font>");
								serveiNotInHomonim = false;
							}
						}
						if (serveiNotInHomonim) {
							srvT.setServei(Utils.changeEncode(rs.getString("DESCRIP")));
						}
					}
					srvT.setId(rs.getInt("IDSRV"));
					srvT.setFactura("Factura num. " + rs.getString("ID"));
					if (locale != null && locale.toUpperCase().equals("CA")) {
						srvT.setEstatFactura(Utils.changeEncode(rs.getString("DESCRIPCIO_CA")));
					} else {
						srvT.setEstatFactura(Utils.changeEncode(rs.getString("DESCRIPCIO")));
					}

					srvs.add(srvT);
				}
				if (serveisHomonims.size() > serveisHomonimsCheckedList.size()) {
					srvs.add(null);
				}
			} catch (Exception e) {
				log.error("BDVolum.getSrvFacts", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return srvs;
	}

	/**
	 * 
	 * @param con
	 * @param cso
	 * @param year
	 * @param month
	 * @param locale
	 * @param inici
	 * @param lenght
	 * @param sortDir
	 * @return busca serveis donat un cso, un any, un mes
	 * @throws Exception
	 */
	public static Vector<SrvDispTable> getSrvFactsModif( ConexionBD con, Integer idfactura, Integer cso, Integer year, Integer month,
			String locale, Integer inici, Integer lenght, String sortDir ) throws Exception{

		Vector<SrvDispTable> srvs = new Vector<SrvDispTable>(1, 1);

		if (cso != null && year != null && month != null) {
			if (inici == null)
				inici = 0;
			if (lenght == null)
				lenght = 20;
			if (sortDir == null || sortDir.equals(""))
				sortDir = " sr.DESCRIP ASC ";

			String sql = "SELECT * from "
					+ " (SELECT   srvol.IDSRV, sr.DESCRIP,srvfac.ID, srvfac.IDESTADO, est.DESCRIPCIO, est.DESCRIPCIO_CA, ROW_NUMBER() OVER (ORDER BY "
					+ sortDir
					+ ") as RN "
					+ " from CSO_SRV_VOL srvol left join SYN_SRV sr ON sr.IDSRV=srvol.IDSRV "
					+ " left join "
					+ "(select CSO_FACT_SRV.IDSRV,fac.IDESTADO, fac.id, fac.idcso  from"
					+ " CSO_FACT_SRV left join"
					+ " (select cso_facturas.idcso,cso_facturas.id, cso_facturas.IDESTADO from cso_facturas where cso_facturas.any_fact="
					+ year
					+ " and cso_facturas.mes_fact="
					+ month
					+ " and cso_facturas.idcso="
					+ cso
					+ ") fac "
					+ " ON cso_fact_srv.idfactura= fac.id ) srvfac "
					+ " ON srvfac.IDSRV=srvol.IDSRV and srvfac.idcso = "
					+ cso
					+ " left join  CSO_ESTATS  est ON est.ID= srvfac.IDESTADO"
					+ " where srvol.MONTH="
					+ month
					+ " AND srvol.YEAR="
					+ year
					+ " AND srvol.IDCSO=" + cso + ")" + " where RN BETWEEN ? AND ?";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				ps.setInt(1, inici);
				ps.setInt(2, inici + lenght - 1);
				rs = ps.executeQuery();
				while (rs.next()) {
					SrvDispTable srvT = new SrvDispTable();
					if (rs.getString("ID") == null || rs.getString("ID").equals("")) {
						srvT.setServei("<input type=\"checkbox\" id=\"s_" + rs.getString("IDSRV")
								+ "\" onclick=\"saveSrvIntoFacturaObject(this)\" > " + Utils.changeEncode(rs.getString("DESCRIP")));
					} else if (rs.getString("ID") != null && rs.getString("ID").equals(idfactura.toString())) {
						srvT.setServei("<input type=\"checkbox\" id=\"s_" + rs.getString("IDSRV")
								+ "\" onclick=\"saveSrvIntoFacturaObject(this)\" checked=\"checked\" > "
								+ Utils.changeEncode(rs.getString("DESCRIP")));
					} else {
						srvT.setServei(Utils.changeEncode(rs.getString("DESCRIP")));
					}
					srvT.setId(rs.getInt("IDSRV"));
					srvT.setFactura("Factura num. " + rs.getString("ID"));
					if (locale != null && locale.toUpperCase().equals("CA")) {
						srvT.setEstatFactura(Utils.changeEncode(rs.getString("DESCRIPCIO_CA")));
					} else {
						srvT.setEstatFactura(Utils.changeEncode(rs.getString("DESCRIPCIO")));
					}

					srvs.add(srvT);
				}
			} catch (Exception e) {
				log.error("BDVolum.getSrvFacts", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return srvs;
	}

	public static Integer getSrvFactsCount( ConexionBD con, Integer cso, Integer year, Integer month, String locale, Integer inici,
			Integer lenght, String sortDir ) throws Exception{

		int numreg = 0;

		if (cso != null && year != null && month != null) {
			if (inici == null)
				inici = 0;
			if (lenght == null)
				lenght = 20;

			String sql = "SELECT  count(*) AS NUMREG " + " from CSO_SRV_VOL srvol left join SYN_SRV sr ON sr.IDSRV=srvol.IDSRV "
					+ " left join " + "(select CSO_FACT_SRV.IDSRV,fac.IDESTADO, fac.id, fac.idcso  from" + " CSO_FACT_SRV left join"
					+ " (select cso_facturas.idcso,cso_facturas.id, cso_facturas.IDESTADO from cso_facturas where cso_facturas.any_fact="
					+ year + " and cso_facturas.mes_fact=" + month + " and cso_facturas.idcso=" + cso + ") fac "
					+ " ON cso_fact_srv.idfactura= fac.id ) srvfac " + " ON srvfac.IDSRV=srvol.IDSRV and srvfac.idcso = " + cso
					+ " left join  CSO_ESTATS  est ON est.ID= srvfac.IDESTADO" + " where srvol.MONTH=" + month + " AND srvol.YEAR=" + year
					+ " AND srvol.IDCSO=" + cso;

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					numreg = rs.getInt("numreg");
				}
			} catch (Exception e) {
				log.error("BDVolum.getSrvFactsCount", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return numreg;
	}

	public static Vector<ServicioFact> getSrv( ConexionBD con, Integer idsrv, Integer cso, Integer month, Integer year ) throws Exception{

		Vector<ServicioFact> srvs = new Vector<ServicioFact>(1, 1);

		if (idsrv != null && cso != null && month != null && year != null) {
			String sql = " SELECT   srvol.IDSRV, srvol.VOLUM " + " from CSO_SRV_VOL srvol " + " where srvol.MONTH=" + month
					+ " AND srvol.YEAR=" + year + " AND srvol.IDCSO=" + cso + " and srvol.idsrv=" + idsrv;

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);

				rs = ps.executeQuery();
				while (rs.next()) {
					ServicioFact srvT = new ServicioFact();
					srvT.setIdServicio(idsrv);
					srvT.setVolumetria(rs.getDouble("VOLUM"));
					srvs.add(srvT);
				}
			} catch (Exception e) {
				log.error("BDVolum.getSrv", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return srvs;
	}

	public static boolean ckeckNewData( ConexionBD con ) throws Exception{

		boolean existNewData = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		if(month==0){
			
			year = year-1;
			month=12;
			
		}
		String sql = "SELECT  ID from  CSO_SRV_VOL srvol  where srvol.IDSRV NOT IN( select IDSRV from CSO_FACT_SRV factsrv) AND srvol.MONTH="
				+ month + " AND srvol.YEAR=" + year;
		try {
			ps = con.getPreparedStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				existNewData = true;
				break;
			}
			return existNewData;
		} catch (Exception e) {
			log.error("BDVolum.getSrvCsoFacts", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
	}

	public static Vector<SrvCSOTable> getSrvWithVolExcel( ConexionBD con, Integer idcso, Integer year, Integer month, String locale )
			throws Exception{

		Vector<SrvCSOTable> srvs = new Vector<SrvCSOTable>(1, 1);

		if (idcso != null && year != null && month != null) {

			String sql = "SELECT  srvol.VOLUM, srvol.IDSRV, srv.DESCRIP, fac.ID,fac.code,est.descripcio, est.descripcio_ca, prtr.precio "
					+ " from CSO_SRV_VOL srvol,"
					+ " SYN_SRV srv left join CSO_FACT_SRV factsrv  ON factsrv.IDSRV=srv.idsrv and factsrv.idfactura IN (select ID from cso_facturas where IDCSO="
					+ idcso
					+ "and CSO_FACTURAS.MES_FACT = "
					+ month
					+ " and CSO_FACTURAS.ANY_FACT = "
					+ year
					+ ") "
					+ "left join cso_facturas fac"
					+ " ON (factsrv.idfactura=fac.id and  fac.mes_fact="
					+ month
					+ " and  fac.any_fact="
					+ year
					+ " and  fac.idcso="
					+ idcso
					+ " and  factsrv.idfactura=fac.id )"
					+ " left join  CSO_MT_PRECIOS pr ON pr.idCSO ="
					+ idcso
					+ " AND pr.idsrv=srv.idsrv"
					+ " left join  CSO_MT_PRECIOS_TR prtr ON prtr.idprecio = pr.id left join cso_estats est ON(est.ID= fac.IDESTADO)"
					+ " where  srvol.MONTH="
					+ month
					+ " AND srvol.YEAR="
					+ year
					+ " AND srvol.IDCSO="
					+ idcso
					+ " AND srv.IDSRV=srvol.IDSRV ";

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					SrvCSOTable srvT = new SrvCSOTable();
					srvT.setServei(rs.getString("DESCRIP"));
					if (rs.getInt("ID") != 0) {
						srvT.setFactura(rs.getInt("ID") + "_" + rs.getString("CODE"));
					} else {
						if (locale.equals("CA")) {
							srvT.setFactura("");
							srvT.setEstatFactura("Pendent");
						} else {
							srvT.setFactura("");
							srvT.setEstatFactura("Pendiente");

						}
					}
					srvT.setVolum(rs.getString("VOLUM"));
					if (locale != null && locale.toUpperCase().equals("CA") && rs.getInt("ID") != 0) {
						srvT.setEstatFactura(rs.getString("DESCRIPCIO_CA"));
					} else if (rs.getInt("ID") != 0) {
						srvT.setEstatFactura(rs.getString("DESCRIPCIO"));
					}
					if (rs.getString("PRECIO").equals("")) {
						srvT.setPreu(rs.getString("PRECIO"));
					} else {
						srvT.setPreu(rs.getString("PRECIO") + "€");
					}
					srvs.add(srvT);
				}
			} catch (Exception e) {
				log.error("BDVolum.getSrvWithVol", e);
				throw e;
			} finally {
				if (null != ps)
					ps.close();
			}
		}
		return srvs;
	}

}
