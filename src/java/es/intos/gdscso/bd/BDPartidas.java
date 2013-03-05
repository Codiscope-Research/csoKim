package es.intos.gdscso.bd;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.DetallPartidaTable;
import es.intos.gdscso.on.FactorCrecimiento;
import es.intos.gdscso.on.FactorsCorreccio;
import es.intos.gdscso.on.FacturacioDetallePartida;
import es.intos.gdscso.on.FacturacioPartidasFirstTable;
import es.intos.gdscso.on.FacturacioPartidasSecondTable;
import es.intos.gdscso.on.Partida;
import es.intos.gdscso.on.Servicio;
import es.intos.gdscso.utils.Constants;
import es.intos.gdscso.utils.Utils;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDPartidas{

	public static Logger	log	= Logger.getLogger(LNPartidas.class);

	public static int insertPartida( ConexionBD con, Partida oo ) throws Exception{

		String sql = "   insert into CSO_MT_PARTIDAS(  " + "	ID ,                   " + "	NOMLINEA           "
				+ "   ) values (                                         " + "   S_CSO_PARTIDAS.NEXTVAL,  " + " 	? " + // varchar2(50)
				"   )  ";

		PreparedStatement ps = null;
		int rows = 0;

		try {
			ps = con.getPreparedStatement(sql);
			int count = 1;
			ps.setString(count++, oo.getDescripcio());
			log.info("BDPartidas.insertPartida >> " + ps.toString());
			rows = con.executeUpdate(ps);
			if (0 == rows)
				throw new SQLException("no rows inserted");

			if (oo.getServicios() != null && !oo.getServicios().isEmpty()) {
				// insertem els serv.
				for (Servicio srv : oo.getServicios()) {
					insertSrvPartida(con, srv.getId(), oo.getId(), srv.getDescripcio());
				}

			}
		} catch (Exception e) {
			log.error("BDPartidas.insertPartida", e);
			throw e;
		} finally {
			log.info(rows + " rows inserted.");
			if (null != ps)
				ps.close();
		}
		return rows;
	} // end insert

	public static int updateNomPartida( ConexionBD con, Partida oo ) throws Exception{

		int rows = 0;
		if (oo != null && oo.getDescripcio() != null && oo.getId() != null) {
			String sql = " update CSO_MT_PARTIDAS SET NOMLINEA='" + oo.getDescripcio() + "' where ID=" + oo.getId();

			PreparedStatement ps = null;

			try {
				ps = con.getPreparedStatement(sql);
				log.info("BDPartidas.updateNomPartida >> " + ps.toString());
				rows = con.executeUpdate(ps);

			} catch (Exception e) {
				log.error("BDPartidas.updateNomPartida", e);
				throw e;
			} finally {
				log.info(rows + " rows inserted.");
				if (null != ps)
					ps.close();
			}
		}
		return rows;
	}

	public static int deletePartida( ConexionBD con, Partida oo ) throws Exception{

		String sql = " delete from CSO_MT_PARTIDAS  where ID=" + oo.getId();

		PreparedStatement ps = null;
		int rows = 0;

		try {
			ps = con.getPreparedStatement(sql);
			log.info("BDPartidas.deletePartida >> " + ps.toString());
			rows = con.executeUpdate(ps);

		} catch (Exception e) {
			log.error("BDPartidas.deletePartida", e);
			throw e;
		} finally {
			log.info(rows + " rows inserted.");
			if (null != ps)
				ps.close();
		}
		return rows;
	}

	public static int insertSrvPartida( ConexionBD con, Integer srv, Integer part, String nom ) throws Exception{

		int rows = 0;
		if (srv != null && part != null) {
			String sql = "   insert into CSO_PART_SRV(  " + "	IDSRV ,                   " + "	IDPARTIDA,          " + "   NOMSRV"
					+ "   ) values (                                         " + "   ?,  " + " 	?, " + " 	? " + "   )  ";

			PreparedStatement ps = null;

			try {
				ps = con.getPreparedStatement(sql);
				int count = 1;
				ps.setInt(count++, srv);
				ps.setInt(count++, part);
				ps.setString(count++, (nom == null) ? "" : "");
				log.info("BDPartidas.insertSrvPartida >> " + ps.toString());
				rows = con.executeUpdate(ps);
				if (0 == rows)
					throw new SQLException("no rows inserted");
			} catch (Exception e) {
				log.error("BDPartidas.insertSrvPartida", e);
				throw e;
			} finally {
				log.info(rows + " rows inserted.");
				if (null != ps)
					ps.close();
			}
		}
		return rows;
	} // end insert

	public static int deleteSrvPartida( ConexionBD con, Integer srv, Integer part ) throws Exception{

		String sql = "   delete from CSO_PART_SRV where  " + "	IDSRV=" + srv + " AND IDPARTIDA=" + part;

		PreparedStatement ps = null;
		int rows = 0;

		try {
			ps = con.getPreparedStatement(sql);
			rows = con.executeUpdate(ps);
			if (0 == rows)
				throw new SQLException("no rows inserted");
		} catch (Exception e) {
			log.error("BDPartidas.deleteSrvPartida", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return rows;
	}

	// El boolean a true indica si es volen els que te la partida o els que li
	// menquen
	public static List<Servicio> getAllSrvPartida( ConexionBD con, boolean partida, Integer idPartida, Integer inici, Integer lenght,
			String sortDir ) throws Exception{

		List<Servicio> list = new ArrayList<Servicio>();
		StringBuffer sqlSB = new StringBuffer("");
		if (partida == true && idPartida != null) {
			sqlSB.append("SELECT * from (select SYN_SRV.IDSRV,SYN_SRV.DESCRIP NOMSRV, ROW_NUMBER() OVER (ORDER BY SYN_SRV.DESCRIP "
					+ sortDir + ") as RN  from CSO_PART_SRV, SYN_SRV where  SYN_SRV.IDSRV=CSO_PART_SRV.IDSRV ");
			sqlSB.append(" AND	IDPARTIDA=" + idPartida + " )  where RN BETWEEN ? AND ?");
		} else if (idPartida == null) {
			return list;
		} else {
			sqlSB.append("SELECT * from (select E.IDSRV, E.DESCRIP, ROW_NUMBER() OVER (ORDER BY DESCRIP " + sortDir
					+ ") as RN from SYN_SRV E where E.IDSRV NOT IN (select IDSRV from CSO_PART_SRV O where O.IDPARTIDA=" + idPartida
					+ ") )  where RN BETWEEN ? AND ?");
		}

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sqlSB.toString());
			ps.setInt(1, inici);
			ps.setInt(2, inici + lenght - 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				Servicio srv = null;
				if (partida == true) {
					srv = new Servicio(rs.getInt("IDSRV"), rs.getInt("IDSRV")+".-"+rs.getString("NOMSRV"));
				} else {
					srv = new Servicio(rs.getInt("IDSRV"), rs.getInt("IDSRV")+".-"+rs.getString("DESCRIP"));
				}
				list.add(srv);
			}
		} catch (Exception e) {
			log.error("BDPartidas.getAllSrvPartida", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return list;
	}

	// El boolean a true indica si es volen els que te la partida o els que li
	// menquen
	public static int getAllSrvPartidaCount( ConexionBD con, boolean partida, Integer idPartida ) throws Exception{

		StringBuffer sqlSB = new StringBuffer("");
		if (idPartida == null)
			return 0;
		if (partida == true && idPartida != null) {
			sqlSB.append("select count(*) as NUMREG from CSO_PART_SRV where  ");
			sqlSB.append("	IDPARTIDA=" + idPartida);
		} else {
			sqlSB.append("select count(*) as NUMREG from SYN_SRV E where E.IDSRV NOT IN (select IDSRV from CSO_PART_SRV O where O.IDPARTIDA="
					+ idPartida + ") ");
		}

		PreparedStatement ps = null;
		int rows = 0;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sqlSB.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				rows = rs.getInt("NUMREG");
			}
		} catch (Exception e) {
			log.error("BDPartidas.getAllSrvPartidaCount", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return rows;
	}

	public static List<Partida> getAllPartida( ConexionBD con ) throws Exception{

		List<Partida> list = new ArrayList<Partida>();
		StringBuffer sqlSB = new StringBuffer("select ID,NOMLINEA  from CSO_MT_PARTIDAS ");

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sqlSB.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				Partida prt = new Partida(rs.getInt("ID"), rs.getString("NOMLINEA"), null);
				list.add(prt);
			}

		} catch (Exception e) {
			log.error("BDPartidas.getAllPartida", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return list;
	} // end insert

	public static int selectMaxIdPartida( ConexionBD con ) throws Exception{

		StringBuffer sql = new StringBuffer("SELECT MAX(ID) AS MAX  from CSO_MT_PARTIDAS");

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
			log.error("BDPartidas.selectMaxIdPartida", e);
			throw e;
		} finally {
			log.info(rows + " rows selectMaxIdPartida.");
			if (null != ps)
				ps.close();
		}

		return rows;
	}

	public static List<FacturacioPartidasSecondTable> getInfoSecondTableOfFacturacionPartidas( ConexionBD con, Integer yearOfConsult,
			Integer inici, Integer lenght, String order ) throws Exception{

		List<FacturacioPartidasSecondTable> list = new ArrayList<FacturacioPartidasSecondTable>();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat twoDecimalForm = new DecimalFormat("#,##0.00", symbols);

		Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
		Integer currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		if (yearOfConsult == null)
			return list;
		if (order == null || order.equals(""))
			order = "pt.nomlinea ASC";
		if (inici == null)
			inici = 0;
		if (lenght == null)
			lenght = 90;

		StringBuffer sqlBuffer = new StringBuffer("SELECT * from (");
		sqlBuffer
				.append("select pt.id as idpartida, pt.nomlinea as partida, impact.importe as importpactat, ptsrv.vol as importconsumit, ((NVL2(sumafc.fc,sumafc.fc/12,0)*NVL(ptsrv.vol,0))) as "
						+ "importestimat,(NVL2(impact.importe,(impact.importe-NVL(ptsrv.vol,0)),0)) as rentabilitat, ROW_NUMBER() OVER (ORDER BY "
						+ order
						+ ") as RN from cso_mt_partidas  pt "
						+ "left join "
						+ "cso_partidas_imports_pactats impact ON ( impact.idpartida=pt.id and impact.year="
						+ yearOfConsult
						+ ") "
						+ "left join "
						+ "( select SUM(prtfc.fc) as fc, prtfc.idpartida as idpartida FROM cso_mt_partida_fc prtfc where year="
						+ yearOfConsult
						+ " group by prtfc.idpartida) sumafc "
						+ "ON(sumafc.idpartida = pt.id) "
						+ "left join "
						+ "(select SUM(srvol.importe) as VOL,cso_part_srv.idpartida  from cso_part_srv "
						+ "left join CSO_SRV_VOL srvol  ON srvol.idsrv=cso_part_srv.idsrv and srvol.year="
						+ yearOfConsult
						+ " group by  cso_part_srv.idpartida " + ")ptsrv ON pt.id=ptsrv.idpartida ");

		sqlBuffer.append(")where RN BETWEEN ? AND ?");

		PreparedStatement ps = null;
		ResultSet rs = null;
		int rows = 0;

		try {
			int positionParam = 1;
			Double importPactatTotal = 0.0;
			Double importEstimatTotal = 0.0;
			Double importConsumitTotal = 0.0;

			ps = con.getPreparedStatement(sqlBuffer.toString());
			ps.setInt(positionParam++, inici);
			ps.setInt(positionParam++, lenght);
			rs = ps.executeQuery();
			while (rs.next()) {
				Double estimat=0.0;
				FacturacioPartidasSecondTable facturacioPartidasSecondTable = new FacturacioPartidasSecondTable();
				facturacioPartidasSecondTable.setPartida("<a href=\"#\" id=\"part_" + rs.getString("idpartida")
						+ "\" onclick=\"openDetallPartida(" + rs.getString("idpartida") + ")\" >" + rs.getString("partida") + "</a>");
				facturacioPartidasSecondTable.setImportpactat("<a href=\"#\" id=\"part_" + rs.getString("idpartida")
						+ "\" onclick=\"openDetallPartida(" + rs.getString("idpartida") + ")\" >"
						+ ((rs.getString("importpactat").equals("")) ? "0" : twoDecimalForm.format(rs.getDouble("importpactat")))
						+ "&euro;" + "</a>");
				importPactatTotal = importPactatTotal + ((rs.getString("importpactat").equals("")) ? 0.0 : rs.getDouble("importpactat"));

				if (currentYear.equals(yearOfConsult)) {
					if (currentMonth == 0) {

						facturacioPartidasSecondTable.setImportestimat("<a href=\"#\" id=\"part_" + rs.getString("idpartida")
								+ "\" onclick=\"openDetallPartida(" + rs.getString("idpartida") + ")\" >No Disp.</a>");

					} else {

						Double mitjanaConsumit = rs.getDouble("importconsumit") / currentMonth;
						List<FactorCrecimiento> factorsCrecimientoList = BDPartidas.getFactoresCrecimientoPartidas(con, yearOfConsult,
								rs.getInt("idpartida"));
						Double estimatCALC = 0.0;
						int numMonth = 0;
						for (FactorCrecimiento factCorrec : factorsCrecimientoList) {
							if (numMonth < currentMonth) {
								estimatCALC = estimatCALC + mitjanaConsumit;
								numMonth++;
								continue;
							}
							estimatCALC = estimatCALC + mitjanaConsumit * factCorrec.getFactor();
						}

						facturacioPartidasSecondTable.setImportestimat("<a href=\"#\" id=\"part_" + rs.getString("idpartida")
								+ "\" onclick=\"openDetallPartida(" + rs.getString("idpartida") + ")\" >"
								+ twoDecimalForm.format(estimatCALC) + "&euro;" + "</a>");
						estimat=estimatCALC;
						importEstimatTotal = importEstimatTotal + estimatCALC;

					}

				} else {
					facturacioPartidasSecondTable.setImportestimat("<a href=\"#\" id=\"part_" + rs.getString("idpartida")
							+ "\" onclick=\"openDetallPartida(" + rs.getString("idpartida") + ")\" >"
							+ twoDecimalForm.format(rs.getDouble("importestimat")) + "&euro;" + "</a>");
					estimat = ((rs.getString("importestimat").equals("")) ? 0.0 : rs.getDouble("importestimat"));
					importEstimatTotal = importEstimatTotal
							+ ((rs.getString("importestimat").equals("")) ? 0.0 : rs.getDouble("importestimat"));
				}
				facturacioPartidasSecondTable.setImportconsumit("<a href=\"#\" id=\"part_" + rs.getString("idpartida")
						+ "\" onclick=\"openDetallPartida(" + rs.getString("idpartida") + ")\" >"
						+ twoDecimalForm.format(rs.getDouble("importconsumit")) + "&euro;" + "</a>");
				importConsumitTotal = importConsumitTotal
						+ ((rs.getString("importconsumit").equals("")) ? 0.0 : rs.getDouble("importconsumit"));

				if (rs.getDouble("importpactat") == 0.0) {
					facturacioPartidasSecondTable.setRentabilitat("<a href=\"#\" id=\"part_" + rs.getString("idpartida")
							+ "\" onclick=\"openDetallPartida(" + rs.getString("idpartida") + ")\" >No Disp.</a>");
				} else if (estimat == 0.0) {
					facturacioPartidasSecondTable.setRentabilitat("<a href=\"#\" id=\"part_" + rs.getString("idpartida")
							+ "\" onclick=\"openDetallPartida(" + rs.getString("idpartida") + ")\" >" + 0 + "</a>");
				} else {
					facturacioPartidasSecondTable
							.setRentabilitat("<a href=\"#\" id=\"part_" + rs.getString("idpartida") + "\" onclick=\"openDetallPartida("
									+ rs.getString("idpartida") + ")\" >"
									+ twoDecimalForm.format(((estimat/ rs.getDouble("importpactat")) - 1) * 100)
									+ " % </a>");
				}

				list.add(facturacioPartidasSecondTable);
			}

			// TOTALS
			FacturacioPartidasSecondTable facturacioPartidasSecondTable = new FacturacioPartidasSecondTable();
			facturacioPartidasSecondTable.setPartida("TOTAL");
			facturacioPartidasSecondTable.setImportpactat(String.valueOf(importPactatTotal) + "&euro;");
			facturacioPartidasSecondTable.setImportestimat(String.valueOf(twoDecimalForm.format(importEstimatTotal)) + "&euro;");
			facturacioPartidasSecondTable.setImportconsumit(String.valueOf(importConsumitTotal) + "&euro;");
			facturacioPartidasSecondTable.setRentabilitat("");

			list.add(facturacioPartidasSecondTable);
		} catch (Exception e) {
			log.error("BDPartidas.getInfoSecondTableOfFacturacionPartidas", e);
			throw e;
		} finally {
			log.info(rows + " rows getInfoSecondTableOfFacturacionPartidas.");
			if (null != ps)
				ps.close();
		}

		return list;
	}

	public static List<FacturacioPartidasFirstTable> getInfoTableOfFacturacionPartidas( ConexionBD con, Integer yearOfConsult,
			Integer inici, Integer lenght, String order ) throws Exception{

		List<FacturacioPartidasFirstTable> list = new ArrayList<FacturacioPartidasFirstTable>();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat twoDecimalForm = new DecimalFormat("#,##0.00", symbols);
		if (yearOfConsult == null)
			return list;
		if (order == null || order.equals(""))
			order = "pt.nomlinea ASC";
		if (inici == null)
			inici = 0;
		if (lenght == null)
			lenght = 90;

		StringBuffer sqlBuffer = new StringBuffer("SELECT * from (select pt.id, pt.nomlinea, ROW_NUMBER() OVER (ORDER BY " + order
				+ ") as RN, " + "  ptsrv" + Constants.mesos[Constants.MES_GENER] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_GENER]
				+ ", ptsrv" + Constants.mesos[Constants.MES_FEBRER] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_FEBRER] + ", ptsrv"
				+ Constants.mesos[Constants.MES_MARC] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_MARC] + ", ptsrv"
				+ Constants.mesos[Constants.MES_ABRIL] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_ABRIL] + ", ptsrv"
				+ Constants.mesos[Constants.MES_MAIG] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_MAIG] + ", ptsrv"
				+ Constants.mesos[Constants.MES_JUNY] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_JUNY] + ", ptsrv"
				+ Constants.mesos[Constants.MES_JULIOL] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_JULIOL] + ", ptsrv"
				+ Constants.mesos[Constants.MES_AGOST] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_AGOST] + ", ptsrv"
				+ Constants.mesos[Constants.MES_SETEMBRE] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_SETEMBRE] + ", ptsrv"
				+ Constants.mesos[Constants.MES_OCTUBRE] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_OCTUBRE] + ", ptsrv"
				+ Constants.mesos[Constants.MES_NOVEMBRE] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_NOVEMBRE] + ", ptsrv"
				+ Constants.mesos[Constants.MES_DESEMBRE] + ".VOL as VOLUM" + Constants.mesos[Constants.MES_DESEMBRE]
				+ " from cso_mt_partidas pt ");
		int numyear = 0;
		for (numyear = 0; numyear < 12; numyear++) {
			sqlBuffer.append(" left join " + " (select SUM(srvol.importe) as VOL,cso_part_srv.idpartida " + " from cso_part_srv "
					+ " left join CSO_SRV_VOL srvol " + " ON srvol.idsrv=cso_part_srv.idsrv and srvol.month=" + (new Integer(numyear + 1))
					+ " and srvol.year=" + yearOfConsult + " group by  cso_part_srv.idpartida  )ptsrv" + Constants.mesos[numyear]
					+ " ON pt.id=ptsrv" + Constants.mesos[numyear] + ".idpartida ");
		}

		sqlBuffer.append(")where RN BETWEEN ? AND ?");

		PreparedStatement ps = null;
		ResultSet rs = null;
		int rows = 0;

		try {

			Double eneTotal = 0.0;
			Double febTotal = 0.0;
			Double marTotal = 0.0;
			Double abrTotal = 0.0;
			Double maiTotal = 0.0;
			Double junTotal = 0.0;
			Double julTotal = 0.0;
			Double agoTotal = 0.0;
			Double setTotal = 0.0;
			Double octTotal = 0.0;
			Double novTotal = 0.0;
			Double desTotal = 0.0;

			int positionParam = 1;
			ps = con.getPreparedStatement(sqlBuffer.toString());
			ps.setInt(positionParam++, inici);
			ps.setInt(positionParam++, lenght);
			rs = ps.executeQuery();
			while (rs.next()) {
				FacturacioPartidasFirstTable facturacioPartidasFirstTable = new FacturacioPartidasFirstTable();
				facturacioPartidasFirstTable.setPartida("<a href=\"#\" id=\"part_" + rs.getString("id") + "\" onclick=\"openDetallPartida("
						+ rs.getString("id") + ")\" >" + rs.getString("nomlinea") + "</a>");
				facturacioPartidasFirstTable.setEne("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_GENER]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_GENER]))) + "&euro;" + "</a>");
				eneTotal = eneTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_GENER]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_GENER]));

				facturacioPartidasFirstTable.setFeb("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_FEBRER]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_FEBRER]))) + "&euro;" + "</a>");
				febTotal = febTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_FEBRER]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_FEBRER]));

				facturacioPartidasFirstTable.setMar("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_MARC]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_MARC]))) + "&euro;" + "</a>");
				marTotal = marTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_MARC]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_MARC]));

				facturacioPartidasFirstTable.setAbr("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_ABRIL]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_ABRIL]))) + "&euro;" + "</a>");
				abrTotal = abrTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_ABRIL]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_ABRIL]));

				facturacioPartidasFirstTable.setMai("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_MAIG]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_MAIG]))) + "&euro;" + "</a>");
				maiTotal = maiTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_MAIG]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_MAIG]));

				facturacioPartidasFirstTable.setJun("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_JUNY]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_JUNY]))) + "&euro;" + "</a>");
				junTotal = junTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_JUNY]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_JUNY]));

				facturacioPartidasFirstTable.setJul("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_JULIOL]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_JULIOL]))) + "&euro;" + "</a>");
				julTotal = julTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_JULIOL]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_JULIOL]));

				facturacioPartidasFirstTable.setAgo("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_AGOST]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_AGOST]))) + "&euro;" + "</a>");
				agoTotal = agoTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_AGOST]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_AGOST]));

				facturacioPartidasFirstTable.setSet("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_SETEMBRE]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_SETEMBRE]))) + "&euro;" + "</a>");
				setTotal = setTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_SETEMBRE]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_SETEMBRE]));

				facturacioPartidasFirstTable.setOct("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_OCTUBRE]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_OCTUBRE]))) + "&euro;" + "</a>");
				octTotal = octTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_OCTUBRE]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_OCTUBRE]));

				facturacioPartidasFirstTable.setNov("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_NOVEMBRE]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_NOVEMBRE]))) + "&euro;" + "</a>");
				novTotal = novTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_NOVEMBRE]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_NOVEMBRE]));

				facturacioPartidasFirstTable.setDes("<a href=\"#\" id=\"part_"
						+ rs.getString("id")
						+ "\" onclick=\"openDetallPartida("
						+ rs.getString("id")
						+ ")\" >"
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_DESEMBRE]).equals("")) ? "0" : twoDecimalForm.format(rs
								.getDouble("VOLUM" + Constants.mesos[Constants.MES_DESEMBRE]))) + "&euro;" + "</a>");
				desTotal = desTotal
						+ ((rs.getString("VOLUM" + Constants.mesos[Constants.MES_DESEMBRE]).equals("")) ? 0.0 : rs.getDouble("VOLUM"
								+ Constants.mesos[Constants.MES_DESEMBRE]));

				list.add(facturacioPartidasFirstTable);
			}

			// Afegim un total
			FacturacioPartidasFirstTable facturacioPartidasFirstTable = new FacturacioPartidasFirstTable();
			facturacioPartidasFirstTable.setPartida("TOTAL");
			facturacioPartidasFirstTable.setEne(String.valueOf(eneTotal) + "&euro;");
			facturacioPartidasFirstTable.setFeb(String.valueOf(febTotal) + "&euro;");
			facturacioPartidasFirstTable.setMar(String.valueOf(marTotal) + "&euro;");
			facturacioPartidasFirstTable.setAbr(String.valueOf(abrTotal) + "&euro;");
			facturacioPartidasFirstTable.setMai(String.valueOf(maiTotal) + "&euro;");
			facturacioPartidasFirstTable.setJun(String.valueOf(junTotal) + "&euro;");
			facturacioPartidasFirstTable.setJul(String.valueOf(julTotal) + "&euro;");
			facturacioPartidasFirstTable.setAgo(String.valueOf(agoTotal) + "&euro;");
			facturacioPartidasFirstTable.setSet(String.valueOf(setTotal) + "&euro;");
			facturacioPartidasFirstTable.setOct(String.valueOf(octTotal) + "&euro;");
			facturacioPartidasFirstTable.setNov(String.valueOf(novTotal) + "&euro;");
			facturacioPartidasFirstTable.setDes(String.valueOf(desTotal) + "&euro;");
			list.add(facturacioPartidasFirstTable);

		} catch (Exception e) {
			log.error("BDPartidas.selectMaxIdPartida", e);
			throw e;
		} finally {
			log.info(rows + " rows selectMaxIdPartida.");
			if (null != ps)
				ps.close();
		}

		return list;
	}

	public static int getNumPartides( ConexionBD con ) throws Exception{

		String sql = "select count(*) as NUMREG from CSO_MT_PARTIDAS where 1=1";
		int numRows = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				numRows = rs.getInt("NUMREG");
			}
		} catch (Exception e) {
			log.error("BDPartidas.getNumPartides", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}
		return numRows;
	}

	public static List<FactorCrecimiento> getFactoresCrecimientoPartidas( ConexionBD con, Integer year, Integer partida ) throws Exception{

		List<FactorCrecimiento> list = new ArrayList<FactorCrecimiento>();

		if (year != null && partida != null) {

			String sql = " select idpartida, month, year, fc from cso_mt_partida_fc where year=" + year + " and idpartida=" + partida
					+ " order by month ASC";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					FactorCrecimiento fc = new FactorCrecimiento();
					fc.setIdpartida(partida);
					fc.setMonth(rs.getInt("month"));
					fc.setYear(year);
					fc.setFactor(rs.getDouble("fc"));
					list.add(fc);
				}
				if (list.isEmpty()) {
					int numMonth = 0;
					for (numMonth = 0; numMonth < Constants.mesos.length; numMonth++) {
						FactorCrecimiento fc = new FactorCrecimiento();
						fc.setIdpartida(partida);
						fc.setMonth(numMonth + 1);
						fc.setYear(year);
						fc.setFactor(1.0);
						list.add(fc);
					}
				}
			} catch (Exception e) {
				log.error("BDPartidas.getFactoresCrecimientoPartidas", e);
				throw e;
			} finally {

				if (null != ps)
					ps.close();
			}
		}
		return list;

	}

	private static boolean getFactorCreixement( ConexionBD con, Integer idpartida, Integer month, Integer year ) throws Exception{

		boolean exist = false;
		String sql = "select * from cso_mt_partida_fc where idpartida=" + idpartida + " and  month=" + month + " and  year=" + year;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				exist = true;
			}
		} catch (Exception e) {
			log.error("BDPartidas.getImportPactatPartida", e);
			throw e;
		}

		return exist;
	}

	public static void insertFactoresCrecimientoPartidas( ConexionBD con, List<FactorCrecimiento> list ) throws Exception{

		if (list != null) {

			String sql = " insert into cso_mt_partida_fc (idpartida, month, year, fc) values (?,?,?,?)";
			String sqlupdate = "update cso_mt_partida_fc set  fc=? where idpartida=? and month=? and year=? ";

			PreparedStatement ps = null;
			try {
				for (FactorCrecimiento factorCrecimiento : list) {
					if (factorCrecimiento.getIdpartida() != null && factorCrecimiento.getYear() != null
							&& factorCrecimiento.getMonth() != null && factorCrecimiento.getFactor() != null) {
						boolean exist = getFactorCreixement(con, factorCrecimiento.getIdpartida(), factorCrecimiento.getMonth(),
								factorCrecimiento.getYear());
						if (exist) {
							ps = con.getPreparedStatement(sqlupdate);
							int count = 1;
							ps.setDouble(count++, factorCrecimiento.getFactor());
							ps.setInt(count++, factorCrecimiento.getIdpartida());
							ps.setInt(count++, factorCrecimiento.getMonth());
							ps.setInt(count++, factorCrecimiento.getYear());

						} else {
							ps = con.getPreparedStatement(sql);
							int count = 1;
							ps.setInt(count++, factorCrecimiento.getIdpartida());
							ps.setInt(count++, factorCrecimiento.getMonth());
							ps.setInt(count++, factorCrecimiento.getYear());
							ps.setDouble(count, factorCrecimiento.getFactor());
						}

						con.executeUpdate(ps);
					}
				}
			} catch (Exception e) {
				log.error("BDPartidas.insertFactoresCrecimientoPartidas", e);
				throw e;
			} finally {

				if (null != ps)
					ps.close();
			}
		}
	}

	public static void saveImportPactatPartida( ConexionBD con, FactorsCorreccio factorsCorreccio ) throws Exception{

		if (factorsCorreccio != null) {

			String sql = " insert into CSO_PARTIDAS_IMPORTS_PACTATS (idpartida, year, importe) values (?,?,?)";
			PreparedStatement ps = null;
			try {

				if (factorsCorreccio.getIdPartida() != null && !factorsCorreccio.getIdPartida().equals("")
						&& factorsCorreccio.getYear() != null && !factorsCorreccio.getYear().equals("")) {
					if (factorsCorreccio.getImportpactat() == null || factorsCorreccio.getImportpactat().equals(""))
						factorsCorreccio.setImportpactat("0.0");
					deleteImportPactatPartida(con, Integer.parseInt(factorsCorreccio.getYear()),
							Integer.parseInt(factorsCorreccio.getIdPartida()));
					ps = con.getPreparedStatement(sql);
					int count = 1;
					ps.setInt(count++, Integer.parseInt(factorsCorreccio.getIdPartida()));
					ps.setInt(count++, Integer.parseInt(factorsCorreccio.getYear()));
					ps.setDouble(count++, Double.parseDouble(factorsCorreccio.getImportpactat()));

					con.executeUpdate(ps);
				}

			} catch (Exception e) {
				log.error("BDPartidas.insertFactoresCrecimientoPartidas", e);
				throw e;
			} finally {

				if (null != ps)
					ps.close();
			}
		}
	}

	public static Double getImportPactatPartida( ConexionBD con, Integer yearOfConsult, Integer partida ) throws Exception{

		Double importe = null;
		if (yearOfConsult != null && partida != null) {
			importe = 0.0;
			String sql = " select importe from CSO_PARTIDAS_IMPORTS_PACTATS where idpartida=" + partida + " and year=" + yearOfConsult;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					importe = rs.getDouble("importe");
				}
			} catch (Exception e) {
				log.error("BDPartidas.getImportPactatPartida", e);
				throw e;
			} finally {

				if (null != ps)
					ps.close();
			}

		}
		return importe;
	}

	public static void deleteImportPactatPartida( ConexionBD con, Integer yearOfConsult, Integer partida ) throws Exception{

		if (yearOfConsult != null && partida != null) {

			String sql = " delete from CSO_PARTIDAS_IMPORTS_PACTATS where idpartida=" + partida + " and year=" + yearOfConsult;
			PreparedStatement ps = null;

			try {
				ps = con.getPreparedStatement(sql);
				con.executeUpdate(ps);
			} catch (Exception e) {
				log.error("BDPartidas.deleteImportPactatPartida", e);
				throw e;
			} finally {

				if (null != ps)
					ps.close();
			}

		}
	}

	public static List<FacturacioDetallePartida> getInfoOfFacturacionPartida( ConexionBD con, Vector<Basic> yearsOfConsult,
			Integer idPartida ) throws Exception{

		List<FacturacioDetallePartida> list = new ArrayList<FacturacioDetallePartida>();

		if (yearsOfConsult == null || yearsOfConsult.size() == 0)
			return list;
		if (idPartida == null)
			return list;

		int numYear = 0;
		for (Basic basic : yearsOfConsult) {
			if (numYear > 1) {
				break;
			}
			numYear++;
			StringBuffer sqlBuffer = new StringBuffer("");
			sqlBuffer
					.append("select  pt.nomlinea as partida, impact.year as year, impact.importe as importpactat, ptsrv.vol as importconsumit, ((sumafc.fc/12)*ptsrv.vol) as "
							+ "importestimat,((impact.importe-ptsrv.vol)/ptsrv.vol) as rentabilitat "
							+ "from cso_mt_partidas  pt "
							+ "left join "
							+ "cso_partidas_imports_pactats impact ON ( impact.idpartida=pt.id and  impact.year="
							+ basic.getId()
							+ ") "
							+ "left join "
							+ "( select SUM(prtfc.fc) as fc, prtfc.idpartida as idpartida FROM cso_mt_partida_fc prtfc where prtfc.year="
							+ basic.getId()
							+ " group by prtfc.idpartida) sumafc "
							+ "ON(sumafc.idpartida = pt.id) "
							+ "left join "
							+ "(select SUM(srvol.importe) as VOL,cso_part_srv.idpartida  from cso_part_srv "
							+ "left join CSO_SRV_VOL srvol  ON srvol.idsrv=cso_part_srv.idsrv and srvol.year="
							+ basic.getId()
							+ " group by  cso_part_srv.idpartida )ptsrv ON pt.id=ptsrv.idpartida where pt.id=" + idPartida + " ");
			sqlBuffer.append("");
			PreparedStatement ps = null;
			ResultSet rs = null;
			int rows = 0;

			try {
				ps = con.getPreparedStatement(sqlBuffer.toString());
				rs = ps.executeQuery();
				while (rs.next()) {

					FacturacioDetallePartida facturacioDetallePartida = new FacturacioDetallePartida();
					facturacioDetallePartida.setPartida(rs.getString("partida"));
					facturacioDetallePartida.setImportpactat(String.valueOf(rs.getDouble("importpactat")));
					facturacioDetallePartida.setImportestimat(String.valueOf(rs.getDouble("importestimat")));
					facturacioDetallePartida.setImportconsumit(String.valueOf(rs.getDouble("importconsumit")));
					facturacioDetallePartida.setYear(basic.getId().toString());
					facturacioDetallePartida.setRentabilitat(String.valueOf(rs.getDouble("rentabilitat")));

					list.add(facturacioDetallePartida);
				}
			} catch (Exception e) {
				log.error("BDPartidas.getInfoOfFacturacionPartida", e);
				throw e;
			} finally {
				log.info(rows + " rows getInfoOfFacturacionPartida.");
				if (null != ps)
					ps.close();
			}
		}
		return list;
	}

	public static String getNomPartida( ConexionBD con, Integer idPartida ) throws Exception{

		if (idPartida == null)
			return "";

		PreparedStatement ps = null;
		ResultSet rs = null;
		String nomPartida = "";

		try {
			ps = con.getPreparedStatement(" select nomlinea from cso_mt_partidas where id=" + idPartida);
			rs = ps.executeQuery();
			while (rs.next()) {
				nomPartida = rs.getString("nomlinea");
			}
		} catch (Exception e) {
			log.error("BDPartidas.getNomPartida", e);
			throw e;
		} finally {
			log.info(nomPartida + " rows getNomPartida.");
			if (null != ps)
				ps.close();
		}
		return nomPartida;
	}

	public static List<DetallPartidaTable> getInfoTableDetallPartida( ConexionBD con, Integer yearOfConsult, Integer idPartida,
			Integer inici, Integer lenght, String order ) throws Exception{

		List<DetallPartidaTable> list = new ArrayList<DetallPartidaTable>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sqlBuffer = new StringBuffer("");
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat twoDecimalForm = new DecimalFormat("#,##0.00", symbols);
		if (yearOfConsult == null)
			return list;
		if (idPartida == null)
			return list;
		if (inici == null)
			inici = 0;
		if (lenght == null)
			lenght = 100;
		if (order == null || order.equals(""))
			order = " srv.descrip ASC ";

		try {
			sqlBuffer.append("SELECT * from (select ptsrv.idsrv, sum" + Constants.mesos[Constants.MES_GENER]
					+ ".importe as import_ene, sum" + Constants.mesos[Constants.MES_FEBRER] + ".importe as import_feb, " + "sum"
					+ Constants.mesos[Constants.MES_MARC] + ".importe as import_mar,sum" + Constants.mesos[Constants.MES_ABRIL]
					+ ".importe as import_abr,sum" + Constants.mesos[Constants.MES_MAIG] + ".importe as import_mai, " + "sum"
					+ Constants.mesos[Constants.MES_JUNY] + ".importe as import_jun,sum" + Constants.mesos[Constants.MES_JULIOL]
					+ ".importe as import_jul, sum" + Constants.mesos[Constants.MES_AGOST] + ".importe as import_ago, " + "sum"
					+ Constants.mesos[Constants.MES_SETEMBRE] + ".importe as import_set,sum" + Constants.mesos[Constants.MES_OCTUBRE]
					+ ".importe as import_oct,sum" + Constants.mesos[Constants.MES_NOVEMBRE] + ".importe as import_nov, " + "sum"
					+ Constants.mesos[Constants.MES_DESEMBRE] + ".importe as import_des, srv.descrip, ROW_NUMBER() OVER (ORDER BY " + order
					+ ") as RN from cso_part_srv ptsrv " + "left join syn_srv srv ON srv.idsrv = ptsrv.idsrv "
					+ " left join (select SUM(srvENE.importe) as importe, srvENE.idsrv as idsrv from cso_srv_vol srvENE where srvENE.year="
					+ yearOfConsult + " and srvENE.month=" + (Constants.MES_GENER + 1)
					+ " group by srvENE.idsrv) sumENE ON sumENE.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvFEB.importe) as importe, srvFEB.idsrv as idsrv from cso_srv_vol srvFEB where srvFEB.year="
					+ yearOfConsult + " and srvFEB.month=" + (Constants.MES_FEBRER + 1)
					+ " group by srvFEB.idsrv) sumFEB ON sumFEB.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvMAR.importe) as importe, srvMAR.idsrv as idsrv from cso_srv_vol srvMAR where srvMAR.year="
					+ yearOfConsult + " and srvMAR.month=" + (Constants.MES_MARC + 1)
					+ " group by srvMAR.idsrv) sumMAR ON sumMAR.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvABR.importe) as importe, srvABR.idsrv as idsrv from cso_srv_vol srvABR where srvABR.year="
					+ yearOfConsult + " and srvABR.month=" + (Constants.MES_ABRIL + 1)
					+ " group by srvABR.idsrv) sumABR ON sumABR.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvMAI.importe) as importe, srvMAI.idsrv as idsrv from cso_srv_vol srvMAI where srvMAI.year="
					+ yearOfConsult + " and srvMAI.month=" + (Constants.MES_MAIG + 1)
					+ " group by srvMAI.idsrv) sumMAI ON sumMAI.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvJUN.importe) as importe, srvJUN.idsrv as idsrv from cso_srv_vol srvJUN where srvJUN.year="
					+ yearOfConsult + " and srvJUN.month=" + (Constants.MES_JUNY + 1)
					+ " group by srvJUN.idsrv) sumJUN ON sumJUN.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvJUL.importe) as importe, srvJUL.idsrv as idsrv from cso_srv_vol srvJUL where srvJUL.year="
					+ yearOfConsult + " and srvJUL.month=" + (Constants.MES_JULIOL + 1)
					+ " group by srvJUL.idsrv) sumJUL ON sumJUL.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvAGO.importe) as importe, srvAGO.idsrv as idsrv from cso_srv_vol srvAGO where srvAGO.year="
					+ yearOfConsult + " and srvAGO.month=" + (Constants.MES_AGOST + 1)
					+ " group by srvAGO.idsrv) sumAGO ON sumAGO.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvSET.importe) as importe, srvSET.idsrv as idsrv from cso_srv_vol srvSET where srvSET.year="
					+ yearOfConsult + " and srvSET.month=" + (Constants.MES_SETEMBRE + 1)
					+ " group by srvSET.idsrv) sumSET ON sumSET.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvOCT.importe) as importe, srvOCT.idsrv as idsrv from cso_srv_vol srvOCT where srvOCT.year="
					+ yearOfConsult + " and srvOCT.month=" + (Constants.MES_OCTUBRE + 1)
					+ " group by srvOCT.idsrv) sumOCT ON sumOCT.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvNOV.importe) as importe, srvNOV.idsrv as idsrv from cso_srv_vol srvNOV where srvNOV.year="
					+ yearOfConsult + " and srvNOV.month=" + (Constants.MES_NOVEMBRE + 1)
					+ " group by srvNOV.idsrv) sumNOV ON sumNOV.idsrv=ptsrv.idsrv"
					+ " left join (select SUM(srvDES.importe) as importe, srvDES.idsrv as idsrv from cso_srv_vol srvDES where srvDES.year="
					+ yearOfConsult + " and srvDES.month=" + (Constants.MES_DESEMBRE + 1)
					+ " group by srvDES.idsrv) sumDES ON sumDES.idsrv=ptsrv.idsrv" + " where ptsrv.idpartida=" + idPartida

			);

			sqlBuffer.append(")where RN BETWEEN ? AND ?");

			ps = con.getPreparedStatement(sqlBuffer.toString());
			int positionParam = 1;

			Double eneTotal = 0.0;
			Double febTotal = 0.0;
			Double marTotal = 0.0;
			Double abrTotal = 0.0;
			Double maiTotal = 0.0;
			Double junTotal = 0.0;
			Double julTotal = 0.0;
			Double agoTotal = 0.0;
			Double setTotal = 0.0;
			Double octTotal = 0.0;
			Double novTotal = 0.0;
			Double desTotal = 0.0;

			ps.setInt(positionParam++, inici);
			ps.setInt(positionParam++, inici + lenght - 1);
			rs = ps.executeQuery();
			while (rs.next()) {

				eneTotal = eneTotal + rs.getDouble("import_ene");
				febTotal = febTotal + rs.getDouble("import_feb");
				marTotal = marTotal + rs.getDouble("import_mar");
				abrTotal = abrTotal + rs.getDouble("import_abr");
				maiTotal = maiTotal + rs.getDouble("import_mai");
				junTotal = junTotal + rs.getDouble("import_jun");
				julTotal = julTotal + rs.getDouble("import_jul");
				agoTotal = agoTotal + rs.getDouble("import_ago");
				setTotal = setTotal + rs.getDouble("import_set");
				octTotal = octTotal + rs.getDouble("import_oct");
				novTotal = novTotal + rs.getDouble("import_nov");
				desTotal = desTotal + rs.getDouble("import_des");

				DetallPartidaTable detallPartidaTable = new DetallPartidaTable();
				detallPartidaTable.setServei(Utils.changeEncode(rs.getString("descrip")));
				detallPartidaTable.setEne(twoDecimalForm.format(rs.getDouble("import_ene")) + "&euro;");
				detallPartidaTable.setFeb(twoDecimalForm.format(rs.getDouble("import_feb")) + "&euro;");
				detallPartidaTable.setMar(twoDecimalForm.format(rs.getDouble("import_mar")) + "&euro;");
				detallPartidaTable.setAbr(twoDecimalForm.format(rs.getDouble("import_abr")) + "&euro;");
				detallPartidaTable.setMai(twoDecimalForm.format(rs.getDouble("import_mai")) + "&euro;");
				detallPartidaTable.setJun(twoDecimalForm.format(rs.getDouble("import_jun")) + "&euro;");
				detallPartidaTable.setJul(twoDecimalForm.format(rs.getDouble("import_jul")) + "&euro;");
				detallPartidaTable.setAgo(twoDecimalForm.format(rs.getDouble("import_ago")) + "&euro;");
				detallPartidaTable.setSet(twoDecimalForm.format(rs.getDouble("import_set")) + "&euro;");
				detallPartidaTable.setOct(twoDecimalForm.format(rs.getDouble("import_oct")) + "&euro;");
				detallPartidaTable.setNov(twoDecimalForm.format(rs.getDouble("import_nov")) + "&euro;");
				detallPartidaTable.setDes(twoDecimalForm.format(rs.getDouble("import_des")) + "&euro;");
				list.add(detallPartidaTable);
			}
			// Afegim TOTALS
			DetallPartidaTable detallPartidaTable = new DetallPartidaTable();

			detallPartidaTable.setServei("TOTAL");
			detallPartidaTable.setEne(twoDecimalForm.format(eneTotal) + "&euro;");
			detallPartidaTable.setFeb(twoDecimalForm.format(febTotal) + "&euro;");
			detallPartidaTable.setMar(twoDecimalForm.format(marTotal) + "&euro;");
			detallPartidaTable.setAbr(twoDecimalForm.format(abrTotal) + "&euro;");
			detallPartidaTable.setMai(twoDecimalForm.format(maiTotal) + "&euro;");
			detallPartidaTable.setJun(twoDecimalForm.format(junTotal) + "&euro;");
			detallPartidaTable.setJul(twoDecimalForm.format(julTotal) + "&euro;");
			detallPartidaTable.setAgo(twoDecimalForm.format(agoTotal) + "&euro;");
			detallPartidaTable.setSet(twoDecimalForm.format(setTotal) + "&euro;");
			detallPartidaTable.setOct(twoDecimalForm.format(octTotal) + "&euro;");
			detallPartidaTable.setNov(twoDecimalForm.format(novTotal) + "&euro;");
			detallPartidaTable.setDes(twoDecimalForm.format(desTotal) + "&euro;");

			list.add(detallPartidaTable);
		} catch (Exception e) {
			log.error("BDPartidas.getInfoTableDetallPartida", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return list;

	}

	public static int getNumSrvPartides( ConexionBD con, Integer yearOfConsult, Integer idPartida ) throws Exception{

		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sqlBuffer = new StringBuffer("");
		int numSrvPartida = 0;
		if (yearOfConsult == null || idPartida == null)
			return 0;

		try {
			sqlBuffer.append("select count(*) as numreg from cso_part_srv where idpartida= " + idPartida);

			ps = con.getPreparedStatement(sqlBuffer.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				numSrvPartida = rs.getInt("numreg");
			}
		} catch (Exception e) {
			log.error("BDPartidas.getNumSrvPartides", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return numSrvPartida;
	}
}