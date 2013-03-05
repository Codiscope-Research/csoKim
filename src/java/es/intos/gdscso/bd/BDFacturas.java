package es.intos.gdscso.bd;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasFormDTO;
import es.intos.gdscso.forms.partes.BusquedaNoConformidadesFormDTO;
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.FactSrvTable;
import es.intos.gdscso.on.FactorCrecimientoFactura;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.on.FacturaDialogNC;
import es.intos.gdscso.on.FacturaTableNC;
import es.intos.gdscso.on.Servicio;
import es.intos.gdscso.on.ServicioFact;
import es.intos.gdscso.utils.Constants;
import es.intos.gdscso.utils.Utils;
import es.intos.util.sql.ConexionBD;
import es.intos.util.sql.PreparedStatement;
import es.intos.util.sql.ResultSet;

public class BDFacturas{

	public static Logger			log					= Logger.getLogger(LNPartidas.class);

	private static SimpleDateFormat	formatter			= new SimpleDateFormat("dd-MM-yyyy");

	private static SimpleDateFormat	formatterDateHora	= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public static Vector<Factura> getFacturas( ConexionBD con, Integer idFactura, BusquedaGestionFacturasFormDTO facturaDTO,
			String sortDir, Integer pag, Integer lenght ) throws Exception{

		Vector<Factura> list = new Vector<Factura>(1, 1);
		if (sortDir == null || sortDir.equals(""))
			sortDir = "CSO_FACTURAS.ID ASC";

		StringBuffer sqlSB = new StringBuffer("SELECT * from (" + "SELECT CSO_FACTURAS.ID,CSO_FACTURAS.OBSERVACIONS,CSO_FACTURAS.IDCSO, "
				+ "CSO_FACTURAS.IDESTADO,CSO_FACTURAS.MES_FACT,CSO_FACTURAS.ANY_FACT, "
				+ " CSO_FACTURAS.IMPORTE,CSO_FACTURAS.FENTRADA,CSO_FACTURAS.FULTIMESTAT, CSO_FACTURAS.CODE,  cso.name, "
				+ "est.DESCRIPCIO, ROW_NUMBER() OVER (ORDER BY " + sortDir + ") as RN " + "from CSO_FACTURAS "
				+ "left join CSO_CENTROS cso ON cso.id=CSO_FACTURAS.IDCSO " + "left join CSO_ESTATS est ON est.ID=CSO_FACTURAS.IDESTADO"
				+ " where 1=1 ");

		if (idFactura != null) {
			sqlSB.append(" AND CSO_FACTURAS.ID=" + idFactura);

		}

		if (facturaDTO != null && facturaDTO.getF_csoI() != null) {
			sqlSB.append(" AND ");
			sqlSB.append(" CSO_FACTURAS.IDCSO=" + facturaDTO.getF_csoI());

		}

		if (facturaDTO != null && facturaDTO.getF_estadoI() != null) {
			sqlSB.append(" AND ");
			sqlSB.append(" CSO_FACTURAS.IDESTADO=" + facturaDTO.getF_estadoI());

		}

		if (facturaDTO != null && facturaDTO.getF_mesI() != null) {
			sqlSB.append(" AND ");
			sqlSB.append(" CSO_FACTURAS.MES_FACT=" + facturaDTO.getF_mesI());

		}
		if (facturaDTO != null && facturaDTO.getF_anyI() != null) {
			sqlSB.append(" AND ");
			sqlSB.append(" CSO_FACTURAS.ANY_FACT=" + facturaDTO.getF_anyI());

		}
		if (facturaDTO != null && facturaDTO.getF_fechaFacDesdeS() != null) {
			sqlSB.append(" AND ");
			sqlSB.append(" CSO_FACTURAS.FENTRADA >= to_date('" + facturaDTO.getF_fechaFacDesdeS() + "','DD-MM-YYYY')");

		}
		if (facturaDTO != null && facturaDTO.getF_fechaFacHastaS() != null) {
			sqlSB.append(" AND ");
			sqlSB.append(" CSO_FACTURAS.FENTRADA <= to_date('" + facturaDTO.getF_fechaFacHastaS() + "','DD-MM-YYYY')");
		}

		if (facturaDTO != null && facturaDTO.getF_impdesdeF() != null) {
			sqlSB.append(" AND ");
			sqlSB.append(" IMPORTE >= ?");
		}
		if (facturaDTO != null && facturaDTO.getF_imphastaF() != null) {
			sqlSB.append(" AND ");
			sqlSB.append(" CSO_FACTURAS.IMPORTE <= ?");
		}

		sqlSB.append(")  where RN BETWEEN ? AND ?");

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sqlSB.toString());
			int count = 1;
			if (facturaDTO != null && facturaDTO.getF_impdesdeF() != null) {
				ps.setFloat(count++, facturaDTO.getF_impdesdeF());
			}
			if (facturaDTO != null && facturaDTO.getF_imphastaF() != null) {
				ps.setFloat(count++, facturaDTO.getF_imphastaF());
			}
			if (pag != null && lenght != null) {
				ps.setInt(count++, (pag - 1) * lenght);
				ps.setInt(count++, ((pag - 1) * lenght) + lenght - 1);
			} else {
				ps.setInt(count++, 0);
				ps.setInt(count++, 500);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Factura fc = new Factura();
				fc.setId(rs.getInt("ID"));
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate("FENTRADA"));
				fc.setFentrada(cal.getTime());
				fc.setImporte(rs.getDouble("IMPORTE"));
				fc.setIdEstat(rs.getInt("IDESTADO"));
				fc.setMonth(rs.getInt("MES_FACT"));
				fc.setYear(rs.getInt("ANY_FACT"));
				fc.setIdCSO(rs.getInt("IDCSO"));
				fc.setNomCso(rs.getString("NAME"));
				Calendar call = Calendar.getInstance();
				call.setTime(rs.getTimestamp("FULTIMESTAT"));
				fc.setFulltimestat(call.getTime());
				fc.setNomEstat(rs.getString("DESCRIPCIO"));
				fc.setDescripcio(rs.getString("OBSERVACIONS"));
				fc.setCode(rs.getString("CODE"));
				list.add(fc);
			}
		} catch (Exception e) {
			log.error("BDFacturas.getFacturas", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return list;
	}

	public static Vector<FacturaTableNC> getFacturasWithNoConformidades( ConexionBD con, BusquedaNoConformidadesFormDTO facturaDTO,
			String locale, String sortDir, Integer pag, Integer lenght ) throws Exception{

		Vector<FacturaTableNC> list = new Vector<FacturaTableNC>(1, 1);
		if (sortDir == null || sortDir.equals(""))
			sortDir = "CSO_FACTURAS.ID ASC";

		StringBuffer sqlSB = new StringBuffer(
				"SELECT * from ("
						+ "select cso_facturas.any_fact,cso_facturas.code, cso_facturas.mes_fact,cso_facturas.id,cso_facturas.idcso, est.DESCRIPCIO, est.DESCRIPCIO_CA, "
						+ " cso_facturas.idestado ,his.nc, cso.name, ROW_NUMBER() OVER (ORDER BY " + sortDir
						+ ") as RN from cso_facturas left join"
						+ " (select count(cso_his_estats.idestat) as nc,CSO_HIS_ESTATS.IDFACTURA as idfac  from CSO_HIS_ESTATS where "
						+ " cso_his_estats.idestat=5 group by cso_his_estats.idfactura) his " + " ON his.idfac=cso_facturas.id "
						+ " left join cso_centros cso ON cso.id=cso_facturas.idcso "
						+ " left join CSO_ESTATS est ON est.id= cso_facturas.IDESTADO where his.nc > 0 ");

		if (facturaDTO.getF_idfact() != null && !facturaDTO.getF_idfact().equals("")) {
			sqlSB.append(" AND CSO_FACTURAS.ID=" + facturaDTO.getF_idfact());
		}

		if (facturaDTO != null && facturaDTO.getF_cso() != null && !facturaDTO.getF_cso().equals("")) {
			sqlSB.append(" AND ");
			sqlSB.append(" IDCSO=" + facturaDTO.getF_cso());

		}

		if (facturaDTO != null && facturaDTO.getF_mes() != null && !facturaDTO.getF_mes().equals("")) {
			sqlSB.append(" AND ");
			sqlSB.append(" MES_FACT=" + (Integer.parseInt(facturaDTO.getF_mes()) + 1));

		}
		if (facturaDTO != null && facturaDTO.getF_any() != null && !facturaDTO.getF_any().equals("")) {
			sqlSB.append(" AND ");
			sqlSB.append(" ANY_FACT=" + facturaDTO.getF_any());

		}

		sqlSB.append(")  where RN BETWEEN ? AND ?");

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sqlSB.toString());
			int count = 1;

			if (pag != null && lenght != null) {
				ps.setInt(count++, (pag - 1) * lenght);
				ps.setInt(count++, (pag - 1) * lenght + lenght - 1);
			} else {
				ps.setInt(count++, 0);
				ps.setInt(count++, 500);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				FacturaTableNC fc = new FacturaTableNC();
				if (locale.equals("CA")) {
					fc.setEstat(rs.getString("DESCRIPCIO_CA"));
				} else {
					fc.setEstat(rs.getString("DESCRIPCIO"));
				}

				fc.setIdFactura(rs.getInt("ID"));
				fc.setDescripcio(rs.getInt("ID") + "_" + rs.getString("code"));
				String[] inc = getIncidencies(con, rs.getInt("ID"), locale);
				fc.setIncidencia(inc);
				fc.setMonth(rs.getString("MES_FACT"));
				fc.setnNoConformitats(rs.getInt("NC"));
				fc.setYear(rs.getString("ANY_FACT"));
				fc.setCso(rs.getString("NAME"));
				list.add(fc);
			}
		} catch (Exception e) {
			log.error("BDFacturas.getFacturas", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return list;
	}

	public static FacturaDialogNC getInfoFacturaNoConformidades( ConexionBD con, Integer idfact, String locale ) throws Exception{

		FacturaDialogNC fc = new FacturaDialogNC();

		StringBuffer sqlSB = new StringBuffer(
				"select cso_facturas.any_fact,cso_facturas.mes_fact,cso_facturas.id,cso_facturas.idcso, est.DESCRIPCIO, est.DESCRIPCIO_CA, cso_facturas.code,  "
						+ " cso_facturas.idestado ,his.IDESTAT AS HISIDESTAT,his.namesub_ca, his.namesub, his.DATAESTAT,his.INCIDENCIA, cso.name "
						+ " from cso_facturas "
						+ " left join "
						+ " (select CSO_HIS_ESTATS.IDESTAT, CSO_HIS_ESTATS.DATAESTAT, CSO_HIS_ESTATS.INCIDENCIA, "
						+ " ests.DESCRIPCIO_CA as namesub_ca, ests.DESCRIPCIO as namesub  from CSO_HIS_ESTATS  "
						+ " left join CSO_ESTATS ests ON CSO_HIS_ESTATS.IDESTAT= ests.id  where cso_his_estats.idestat=5 AND CSO_HIS_ESTATS.IDFACTURA="
						+ idfact
						+ ") his "
						+ " ON 1=1 left join cso_centros cso ON cso.id=cso_facturas.idcso  "
						+ " left join CSO_ESTATS est ON est.id= cso_facturas.IDESTADO where cso_facturas.ID=" + idfact);

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.getPreparedStatement(sqlSB.toString());

			rs = ps.executeQuery();
			int i = 0;
			String incs = "";
			while (rs.next()) {

				if (i == 0) {
					fc.setIdFactura(idfact);
					fc.setCode(rs.getString("code"));
					if (locale != null && locale.equals("CA")) {
						fc.setEstat(Utils.changeEncode(rs.getString("DESCRIPCIO_CA")));
					} else {
						fc.setEstat(Utils.changeEncode(rs.getString("DESCRIPCIO")));
					}
					fc.setMonth(rs.getString("MES_FACT"));
					fc.setYear(rs.getString("ANY_FACT"));
					fc.setCso(rs.getString("NAME"));
				}
				Integer idInc = null;
				String inc = "";
				try {
					idInc = Integer.parseInt(rs.getString("INCIDENCIA"));
					inc = BDIncidencias.getIncidencia(con, locale, idInc);
				} catch (Exception e) {
					inc = rs.getString("INCIDENCIA");
				}
				if (locale != null && locale.equals("CA")) {
					incs = incs + "#" + Utils.changeEncode(rs.getString("namesub_ca")) + "_"
							+ formatterDateHora.format(rs.getTimestamp("DATAESTAT")) + "_" + Utils.changeEncode(inc);
				} else {
					incs = incs + "#" + Utils.changeEncode(rs.getString("namesub")) + "_"
							+ formatterDateHora.format(rs.getTimestamp("DATAESTAT")) + "_" + Utils.changeEncode(inc);
				}

			}
			if (incs.length() > 0) {
				incs = incs.substring(1);
				String[] inc = incs.split("#");
				fc.setIncidencia(inc);
			}
		} catch (Exception e) {
			log.error("BDFacturas.getInfoFacturaNC", e);
			throw e;
		} finally {
			if (null != ps)
				ps.close();
		}
		return fc;
	} // end insert

	private static String[] getIncidencies( ConexionBD con, Integer idfact, String locale ) throws Exception{

		if (idfact == null)
			return null;
		if (locale == null || locale.equals(""))
			locale = "CA";

		StringBuffer sqlSB = new StringBuffer("SELECT CSO_HIS_ESTATS.INCIDENCIA FROM CSO_HIS_ESTATS where IDESTAT =5 and IDFACTURA="
				+ idfact);

		PreparedStatement ps = null;
		ResultSet rs = null;
		int rows = 0;
		StringBuffer incTotal = new StringBuffer();
		String[] incc = null;
		try {
			ps = con.getPreparedStatement(sqlSB.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				Integer idInc = null;
				String inc = "";
				if (rs.getString("INCIDENCIA").equals(""))
					continue;
				try {
					idInc = Integer.parseInt(rs.getString("INCIDENCIA"));
					inc = BDIncidencias.getIncidencia(con, locale, idInc);
				} catch (Exception e) {
					inc = rs.getString("INCIDENCIA");
				}
				incTotal.append("#" + inc);
				rows++;
			}
			if (incTotal.length() > 1)
				incc = incTotal.substring(1).toString().split("#");
		} catch (Exception e) {
			log.error("BDFacturas.getIncidencies", e);
			throw e;
		} finally {
			log.info(rows + " rows getIncidencies.");
			if (null != ps)
				ps.close();
		}
		return incc;
	}

	public static int getNumRegControl( ConexionBD con, Integer idcso, Integer any, Integer mes, Integer[] estats ) throws Exception{

		int rows = 0;
		if (idcso != null && any != null && mes != null && estats != null) {
			StringBuffer sqlSB = new StringBuffer("SELECT COUNT(*) AS NUMREG  FROM CSO_FACTURAS where IDCSO=" + idcso);
			sqlSB.append(" AND MES_FACT=" + mes + " AND ANY_FACT=" + any + " AND ");
			if (estats.length > 1) {
				sqlSB.append("(IDESTADO=" + estats[0]);
				for (Integer in : estats) {
					sqlSB.append(" or IDESTADO=" + in + " ");
				}
				sqlSB.append(")");
			} else if (estats.length == 1) {
				sqlSB.append(" IDESTADO=" + estats[0]);
			} else {
				sqlSB.append(" IDESTADO=" + Constants.ESTAT_GENERAT);
			}
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = con.getPreparedStatement(sqlSB.toString());

				rs = ps.executeQuery();
				while (rs.next()) {
					rows = rs.getInt("NUMREG");
				}
			} catch (Exception e) {
				log.error("BDFacturas.getNumRegControl", e);
				throw e;
			} finally {
				log.info(rows + " rows getNumRegControl.");
				if (null != ps)
					ps.close();
			}
		}
		return rows;
	}
	
	public static void setFacturaPDF( ConexionBD con, Integer idfact, String name ) throws Exception{

		
		if (idfact != null && name != null ) {
			String sql ="SELECT PDF_NAMES  FROM CSO_FACTURAS where ID=" + idfact;
			String sqlUpdate  ="UPDATE CSO_FACTURAS  SET PDF_NAMES=?  where ID=" + idfact;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.getPreparedStatement(sql);

				rs = ps.executeQuery();				
				String names ="";
				
				while (rs.next()) {
					names = rs.getString("PDF_NAMES");
				}
				names= names+";"+name;
				
				ps = con.getPreparedStatement(sqlUpdate);
				ps.setString(1, names);
				ps.executeQuery();
				
			} catch (Exception e) {
				log.error("BDFacturas.getNumRegControl", e);
				throw e;
			} finally {				
				if (null != ps)
					ps.close();
			}
		}
		
	}
	
public static List<Basic>  getFacturaPDFNames( ConexionBD con, Integer idfact) throws Exception{

		
		if (idfact != null ) {
			String sql ="SELECT PDF_NAMES  FROM CSO_FACTURAS where ID=" + idfact;
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<Basic> namesList = new ArrayList<Basic>();
			try {
				ps = con.getPreparedStatement(sql);

				rs = ps.executeQuery();				
				String[] namesVec={};
				String names="";
				while (rs.next()) {
					
					names = rs.getString("PDF_NAMES");
				}
				
				if(names!=null && !names.equals("")){
					namesVec = names.split(";");
					for(String nm : namesVec){
						if(!nm.equals("")){
							Basic bs = new Basic(idfact , nm);
							namesList.add(bs);
						}
					}
				}
				return namesList;
				
			} catch (Exception e) {
				log.error("BDFacturas.getNumRegControl", e);
				throw e;
			} finally {				
				if (null != ps)
					ps.close();
			}			
		}
		return null;
		
	}
	
	public static void deleteFacturaPDF( ConexionBD con, Integer idfact, String name ) throws Exception{

		
		if (idfact != null && name != null ) {
			String sql ="SELECT PDF_NAMES  FROM CSO_FACTURAS where ID=" + idfact;
			String sqlUpdate  ="UPDATE CSO_FACTURAS  SET PDF_NAMES=?  where ID=" + idfact;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.getPreparedStatement(sql);

				rs = ps.executeQuery();				
				String names ="";
				
				while (rs.next()) {
					names = rs.getString("PDF_NAMES");
				}
				String[] namesVec = names.split(";");
				
				StringBuffer namSB = new StringBuffer("");
				for(String nam : namesVec){
					if(nam!=null && !nam.equals(nam)){
						namSB.append(";"+nam);
					}
				}
				
				names=namSB.toString();
				
				ps = con.getPreparedStatement(sqlUpdate);
				ps.setString(1, names);
				ps.executeQuery();
				
			} catch (Exception e) {
				log.error("BDFacturas.getNumRegControl", e);
				throw e;
			} finally {				
				if (null != ps)
					ps.close();
			}
		}
		
	}
	

	public static Vector<FactSrvTable> getTableSrvFactures( ConexionBD con, Integer idFactura, String sortDir ) throws Exception{

		Vector<FactSrvTable> factSrvTable = new Vector<FactSrvTable>();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat twoDecimalForm = new DecimalFormat("#,##0.00", symbols);

		if (idFactura != null && sortDir != null) {
			if (sortDir.equals(""))
				sortDir = "synsrv.descrip desc, mtpreutr.numtram ASC";
			else
				sortDir = sortDir + ",mtpreutr.numtram ASC";
			StringBuffer sqlSB = new StringBuffer(
					"select CSO_FACT_SRV.IDSRV, synsrv.descrip  AS NOMSRV, srvol.VOLUM AS VOLUMETRIA,CSO_FACT_SRV.preu as preuf,CSO_FACT_SRV.preu_op as preuopf, srvol.importe as preu, "
							+ " mtpreu.id as idpreu, mtpreu.tipo, mtpreutr.numtram, mtpreutr.VOLINI, mtpreutr.VOLFIN,mtpreutr.PRECIO "
							+ " from CSO_FACT_SRV " + " left join cso_facturas fac on fac.id=" + idFactura
							+ " left join syn_srv synsrv on synsrv.idsrv=CSO_FACT_SRV.IDSRV "
							+ " left join CSO_SRV_VOL srvol on srvol.YEAR =fac.ANY_FACT "
							+ " AND srvol.IDSRV= CSO_FACT_SRV.IDSRV  AND srvol.MONTH =fac.MES_FACT and srvol.idcso=fac.idcso "
							+ " left join CSO_MT_PRECIOS mtpreu on mtpreu.idcso= fac.idcso and mtpreu.idsrv=CSO_FACT_SRV.IDSRV "
							+ " left join CSO_MT_PRECIOS_TR mtpreutr on mtpreutr.idprecio= mtpreu.id " + " where CSO_FACT_SRV.IDFACTURA="
							+ idFactura + " ORDER BY " + sortDir);
			PreparedStatement ps = null;
			ResultSet rs = null;
			int rows = 0;
			try {
				ps = con.getPreparedStatement(sqlSB.toString());

				rs = ps.executeQuery();
				int idSrv = 0;
				while (rs.next()) {
					FactSrvTable oo = new FactSrvTable();
					if (idSrv == rs.getInt("IDSRV")) {
						oo = factSrvTable.get(factSrvTable.size() - 1);
						oo.setImporteOp(oo.getImporteOp()
								+ "%"
								+ String.valueOf(rs.getInt("NUMTRAM")
										+ " Tram:("
										+ String.valueOf(twoDecimalForm.format(rs.getDouble("VOLINI")) + "-"
												+ twoDecimalForm.format(rs.getDouble("VOLFIN"))) + ") ->"
										+ String.valueOf(twoDecimalForm.format(rs.getDouble("PRECIO"))) + " &#8364; "));
						// CALCUL DE L'IMPORT

						//Double preu = preuSrv(rs.getDouble("VOLUMETRIA"), rs.getDouble("VOLINI"), (rs.getDouble("VOLFIN") == 0.0 ? null
						//		: rs.getDouble("VOLFIN")), rs.getDouble("PRECIO"));
						
						//if (preu != 0.0)
							oo.setPreuOp(twoDecimalForm.format(rs.getDouble("preuopf")));
						oo.setImporte(twoDecimalForm.format(rs.getDouble("preuf")));

					} else {
						idSrv = rs.getInt("IDSRV");					
						oo.setServei(rs.getString("NOMSRV"));
						oo.setIdSrv(rs.getInt("IDSRV"));
						oo.setVolumetria(String.valueOf(twoDecimalForm.format(rs.getDouble("VOLUMETRIA"))));
						oo.setImporteOp(String.valueOf(rs.getInt("NUMTRAM")
								+ " Tram:("
								+ String.valueOf(twoDecimalForm.format(rs.getDouble("VOLINI")) + "-"
										+ twoDecimalForm.format(rs.getDouble("VOLFIN"))) + ") ->"
								+ String.valueOf(twoDecimalForm.format(rs.getDouble("PRECIO"))) + " &#8364; "));
						// CALCUL DE L'IMPORT
//						Double preu = preuSrv(rs.getDouble("VOLUMETRIA"), rs.getDouble("VOLINI"), (rs.getDouble("VOLFIN") == 0.0 ? null
//								: rs.getDouble("VOLFIN")), rs.getDouble("PRECIO"));
//						if (preu != 0.0)
						
						oo.setPreuOp(twoDecimalForm.format(rs.getDouble("preuopf")));
						oo.setImporte(twoDecimalForm.format(rs.getDouble("preuf")));
						oo.setId(idFactura);
						factSrvTable.add(oo);
					}
				}
			} catch (Exception e) {
				log.error("BDFacturas.getTableSrvFactures", e);
				throw e;
			} finally {
				log.info(rows + " rows getTableSrvFactures.");
				if (null != ps)
					ps.close();
			}
		}
		return factSrvTable;

	}

	private static Double preuSrv( Double volum, Double volIni, Double volfin, Double preu ){

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.##", simbolos);
		if (volum >= volIni && volfin == null) {
			return Double.parseDouble(df.format(volum * preu));
		}
		if (volum >= volIni && volum < volfin) {
			return Double.parseDouble(df.format(volum * preu));
		}

		return 0.0;
	}

	public static void changeStateFactura( ConexionBD con, Integer idFactura, Integer newStat ) throws Exception{

		if (idFactura != null && newStat != null) {
			StringBuffer sql = new StringBuffer("update CSO_FACTURAS SET  ");
			sql.append(" IDESTADO='" + newStat + "' where ID=" + idFactura);
			PreparedStatement ps = null;

			int rows = 0;
			try {
				ps = con.getPreparedStatement(sql.toString());
				ps.executeQuery();

			} catch (Exception e) {
				log.error("BDFacturas.changeStateFactura", e);
				throw e;
			} finally {
				log.info(rows + " rows changeStateFactura.");
				if (null != ps)
					ps.close();
			}
		}
	}

	public static void updateObservacionesFactura( ConexionBD con, Integer idFactura, String obs ) throws Exception{

		if (idFactura != null && obs != null && !obs.equals("")) {
			StringBuffer sql = new StringBuffer("update CSO_FACTURAS SET  ");
			sql.append(" OBSERVACIONS='" + obs + "' where ID=" + idFactura);
			PreparedStatement ps = null;

			int rows = 0;
			try {
				ps = con.getPreparedStatement(sql.toString());
				ps.executeQuery();

			} catch (Exception e) {
				log.error("BDFacturas.updateObservacionesFactura", e);
				throw e;
			} finally {
				log.info(rows + " rows updateObservacionesFactura.");
				if (null != ps)
					ps.close();
			}
		}
	}

	public static int getNumReg( ConexionBD con, BusquedaGestionFacturasFormDTO facturaDTO ) throws Exception{

		StringBuffer sqlSB = new StringBuffer(" SELECT COUNT(*) AS NUMREG  FROM CSO_FACTURAS where ");
		boolean add = false;
		if (facturaDTO != null) {
			if (facturaDTO.getF_csoI() != null) {
				if (add)
					sqlSB.append(" AND ");
				sqlSB.append(" IDCSO=" + facturaDTO.getF_csoI());
				add = true;
			}

			if (facturaDTO.getF_estadoI() != null) {
				if (add)
					sqlSB.append(" AND ");
				sqlSB.append(" IDESTADO=" + facturaDTO.getF_estadoI());
				add = true;
			}

			if (facturaDTO.getF_mesI() != null) {
				if (add)
					sqlSB.append(" AND ");
				sqlSB.append(" MES_FACT=" + facturaDTO.getF_mesI());
				add = true;
			}
			if (facturaDTO.getF_anyI() != null) {
				if (add)
					sqlSB.append(" AND ");
				sqlSB.append(" ANY_FACT=" + facturaDTO.getF_anyI());
				add = true;
			}
			if (facturaDTO.getF_fechaFacDesdeS() != null) {
				if (add)
					sqlSB.append(" AND ");
				sqlSB.append(" FENTRADA > to_date('" + facturaDTO.getF_fechaFacDesdeS() + "','DD-MM-YYYY')");
				add = true;

			}
			if (facturaDTO.getF_fechaFacHastaS() != null) {
				if (add)
					sqlSB.append(" AND ");
				sqlSB.append(" FENTRADA < to_date('" + facturaDTO.getF_fechaFacHastaS() + "','DD-MM-YYYY')");
				add = true;
			}

			if (facturaDTO.getF_impdesdeF() != null) {
				if (add)
					sqlSB.append(" AND ");
				sqlSB.append(" IMPORTE > ?");
				add = true;
			}
			if (facturaDTO.getF_imphastaF() != null) {
				if (add)
					sqlSB.append(" AND ");
				sqlSB.append(" IMPORTE < ?");
				add = true;
			}
		}

		if (!add) {
			sqlSB.append(" 1=1");
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rows = 0;
		try {
			ps = con.getPreparedStatement(sqlSB.toString());
			int count = 1;
			if (facturaDTO != null) {
				if (facturaDTO.getF_impdesdeF() != null) {
					ps.setFloat(count++, facturaDTO.getF_impdesdeF());
				}
				if (facturaDTO.getF_imphastaF() != null) {
					ps.setFloat(count++, facturaDTO.getF_imphastaF());
				}
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				rows = rs.getInt("NUMREG");
			}
		} catch (Exception e) {
			log.error("BDFacturas.getNumReg", e);
			throw e;
		} finally {
			log.info(rows + " rows getNumReg.");
			if (null != ps)
				ps.close();
		}

		return rows;

	}

	public static int getNumRegNoConformidades( ConexionBD con, BusquedaNoConformidadesFormDTO facturaDTO ) throws Exception{

		StringBuffer sqlSB = new StringBuffer("select  " + " COUNT(*) AS NUMREG from cso_facturas left join"
				+ " (select count(cso_his_estats.idestat) as nc,CSO_HIS_ESTATS.IDFACTURA as idfac  from CSO_HIS_ESTATS where "
				+ " cso_his_estats.idestat=5 group by cso_his_estats.idfactura) his " + " ON his.idfac=cso_facturas.id "
				+ " left join cso_centros cso ON cso.id=cso_facturas.idcso "
				+ " left join CSO_ESTATS est ON est.id= cso_facturas.IDESTADO where his.nc!=0  ");

		if (facturaDTO != null) {
			if (facturaDTO.getF_idfact() != null && !facturaDTO.getF_idfact().equals("")) {
				sqlSB.append(" AND CSO_FACTURAS.ID=" + facturaDTO.getF_idfact());

			}

			if (facturaDTO.getF_cso() != null && !facturaDTO.getF_cso().equals("")) {
				sqlSB.append(" AND ");
				sqlSB.append(" IDCSO=" + facturaDTO.getF_cso());

			}

			if (facturaDTO.getF_mes() != null && !facturaDTO.getF_mes().equals("")) {
				sqlSB.append(" AND ");
				sqlSB.append(" MES_FACT=" + facturaDTO.getF_mes());

			}
			if (facturaDTO.getF_any() != null && !facturaDTO.getF_any().equals("")) {
				sqlSB.append(" AND ");
				sqlSB.append(" ANY_FACT=" + facturaDTO.getF_any());

			}
		}

		PreparedStatement ps = null;
		ResultSet rs = null;
		int rows = 0;
		try {
			ps = con.getPreparedStatement(sqlSB.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				rows = rs.getInt("NUMREG");
			}
		} catch (Exception e) {
			log.error("BDFacturas.getNumReg", e);
			throw e;
		} finally {
			log.info(rows + " rows getNumReg.");
			if (null != ps)
				ps.close();
		}

		return rows;

	}
	
	private static Double calculImportServei( ConexionBD con, ServicioFact srv, Integer idCso ) throws Exception{

		Double preu = 0.0;
		if (srv != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {

				String sql = "select CSO_MT_PRECIOS_TR.VOLINI,CSO_MT_PRECIOS_TR.VOLFIN, CSO_MT_PRECIOS_TR.PRECIO from  CSO_MT_PRECIOS_TR where CSO_MT_PRECIOS_TR.IDPRECIO IN( select CSO_MT_PRECIOS.ID "
						+ "from CSO_MT_PRECIOS where CSO_MT_PRECIOS.IDCSO="
						+ idCso
						+ " and CSO_MT_PRECIOS.IDSRV="
						+ srv.getIdServicio()
						+ ")";
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					preu = preu + preuSrvInsert(srv.getVolumetria(), rs.getDouble("VOLINI"), rs.getDouble("VOLFIN"), rs.getDouble("PRECIO"));
				}
			} catch (Exception e) {

			e.printStackTrace();
			} finally {

				if (null != ps)
					ps.close();
			}
		}

		return preu;

	}

	private static Double preuSrvInsert( Double volum, Double volIni, Double volfin, Double preu ){

		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.##", simbolos);
		if(volfin==0.0){
			return Double.parseDouble(df.format(volum * preu));
		}
		if (volum >= volIni && volum <volfin) {
			return Double.parseDouble(df.format(volum * preu));
		}

		return 0.0;
	}
		
	

	public synchronized static void insertFactura( ConexionBD con, Factura factura ) throws Exception{

		StringBuffer sql = new StringBuffer(
				"insert into CSO_FACTURAS( ID,FENTRADA,FULTIMESTAT,IDCSO,MES_FACT,ANY_FACT,IDESTADO,IMPORTE,CODE) VALUES "
						+ "(S_CSO_FACTURAS.NEXTVAL, to_date(?,'DD/MM/YYYY'),to_date(?||' '||to_char(sysdate,'HH24:mi:ss'),'DD/MM/YYYY HH24:mi:ss'),  ?,?,?,?,?,?) ");

		PreparedStatement ps = null;

		try {

			int count = 1;
			ps = con.getPreparedStatement(sql.toString());
			ps.setString(count++, formatter.format(new Date()));
			ps.setString(count++, formatter.format(new Date()));
			ps.setInt(count++, factura.getIdCSO());
			ps.setInt(count++, factura.getMonth());
			ps.setInt(count++, factura.getYear());
			ps.setInt(count++, Constants.ESTAT_GENERAT);
			ps.setDouble(count++, 0.0);
			ps.setString(count++, factura.getCode());
			con.executeUpdate(ps);

			Double preuTotal=0.0;
			// guardem la relacio amb els serveis
			if (factura.getServicios() != null) {
				for (ServicioFact srv : factura.getServicios()) {
					Double preu = calculImportServei(con,srv,factura.getIdCSO());
					preuTotal=preuTotal+preu;
					Double preuOp= srv.getVolumetria()==null ? null :  (preu/srv.getVolumetria());
					
					String sqlsrv = " insert into CSO_FACT_SRV (IDSRV,IDFACTURA,PREU,PREU_OP) VALUES (" + srv.getIdServicio()
							+ ",S_CSO_FACTURAS.currVal, "+preu+","+preuOp+")";
					ps = con.getPreparedStatement(sqlsrv);
					con.executeUpdate(ps);

				}
			}

			updateImportFactura(con, getLastIdInserted(con), preuTotal);

		} catch (Exception e) {
			log.error("BDFacturas.updateObservacionesFactura", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}
	}

	private static int getLastIdInserted( ConexionBD con ) throws Exception{

		String sql = " select MAX(ID) as maxid from cso_facturas";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int maxId = 0;

		try {
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				maxId = rs.getInt("maxid");
			}
		} catch (Exception e) {
			log.error("BDFacturas.updateObservacionesFactura", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}
		return maxId;

	}

	@SuppressWarnings("unused")
	private static Double calculImport( ConexionBD con, List<ServicioFact> srvs, Integer idcso ) throws Exception{

		Double preu = 0.0;
		if (srvs != null) {
			for (ServicioFact srv : srvs) {
				
				if (srv.getAction().equals("delete"))
					continue;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {

					preu= preu +calculImportServei(con, srv, idcso);

				} catch (Exception e) {
					log.error("BDFacturas.updateObservacionesFactura", e);
					throw e;
				} finally {

					if (null != ps)
						ps.close();
				}
			}
		}
		return preu;

	}

	private static Double calculImport( ConexionBD con, Integer idfactura ) throws Exception{

		Double preu = 0.0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String sql = "select cso_fact_srv.idsrv, cso_fact_srv.preu as preuf, cso_fact_srv.preu_op as preuopf," +
					" srvol.importe  from cso_fact_srv left join cso_facturas fac on fac.id="
					+ idfactura + " left join cso_srv_vol srvol on srvol.idsrv=cso_fact_srv.idsrv  "
					+ " and srvol.month=fac.mes_fact and srvol.year=fac.any_fact and srvol.idcso= fac.idcso "
					+ " where cso_fact_srv.idfactura=" + idfactura;

			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				preu = preu + rs.getDouble("preuf");
			}
		} catch (Exception e) {
			log.error("BDFacturas.calculImport", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}
		return preu;

	}

	public static void updateImportFactura( ConexionBD con, Integer idfactura, Double preuTotal ) throws Exception{

		PreparedStatement ps = null;
		if (idfactura == null)
			return;
		Double precio =preuTotal;
		if(preuTotal==null){
			precio = calculImport(con, idfactura);
		}
		try {

			String sql = "update cso_facturas set importe=? where id=" + idfactura;
			ps = con.getPreparedStatement(sql);
			ps.setDouble(1, precio);
			ps.executeQuery();

		} catch (Exception e) {
			log.error("BDFacturas.updateImportFactura", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}
	}

	public static Servicio getSrvFactura( ConexionBD con, Integer idfact, Integer servei ) throws Exception{

		PreparedStatement ps = null;
		ResultSet rs = null;
		Servicio srv = null;
		try {

			String sql = "select idsrv from cso_fact_srv where idfactura=" + idfact + " and idsrv=" + servei;
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				srv = new Servicio(rs.getInt("idsrv"), "");
			}

		} catch (Exception e) {
			log.error("BDFacturas.updateObservacionesFactura", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}
		return srv;
	}

	public static void updateServeisFactura( ConexionBD con, Integer idfact, List<ServicioFact> servicios, Integer idCso ) throws Exception{

		PreparedStatement ps = null;
		if (idfact == null)
			return;
		try {
			for (ServicioFact serv : servicios) {

				Servicio srv = getSrvFactura(con, idfact, serv.getId());
				
			
				
				String sql = "";
				if (srv != null && serv.getAction().equals("save")) {
					// No cal fer res
					continue;
				} else if (srv == null && serv.getAction().equals("save")) {
					// insert
					Double preu = 	calculImportServei(con, serv, idCso);
					Double preuOp = 0.0; 
					if(serv.getVolumetria()!=null)
						preuOp = preu/serv.getVolumetria();					
					sql = "insert into cso_fact_srv (idsrv,idfactura,preu,preu_op) values (?,?,"+preu+","+preuOp+")";
				} else if (srv != null && serv.getAction().equals("delete")) {
					// delete
					sql = "delete cso_fact_srv where idsrv=? and idfactura=?";
				} else {
					// no cal fer res
					continue;
				}
				ps = con.getPreparedStatement(sql);
				ps.setInt(1, serv.getId());
				ps.setInt(2, idfact);
				con.executeUpdate(ps);

			}

		} catch (Exception e) {
			log.error("BDFacturas.updateObservacionesFactura", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}

	}

	public static List<Basic> getCodesOfFacturesHomonimes( ConexionBD con, Integer idCso ) throws Exception{

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Basic> facturesHominesCodes = new ArrayList<Basic>();
		if (idCso == null)
			return facturesHominesCodes;
		facturesHominesCodes.add(new Basic(0, ""));
		try {
			String sql = "select distinct(code) from cso_facturas where idcso=" + idCso;
			ps = con.getPreparedStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Basic bs = new Basic(0, rs.getString("CODE"));
				facturesHominesCodes.add(bs);
			}
		} catch (Exception e) {
			log.error("BDFacturas.getCodesOfFacturesHomonimes", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}
		return facturesHominesCodes;
	}

	public static List<FactorCrecimientoFactura> getFactoresCrecimientoFactura( ConexionBD con, String codefactura ) throws Exception{

		List<FactorCrecimientoFactura> list = new ArrayList<FactorCrecimientoFactura>();

		if (codefactura != null) {

			String sql = " select codefactura, month, fc from cso_facturas_fc where codefactura='" + codefactura + "' order by month ASC";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.getPreparedStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					FactorCrecimientoFactura fc = new FactorCrecimientoFactura();
					fc.setCodeFactura(codefactura);
					fc.setMonth(rs.getInt("month"));
					fc.setFactor(rs.getDouble("fc"));
					list.add(fc);
				}
				if (list.isEmpty()) {
					int numMonth = 0;
					for (numMonth = 0; numMonth < Constants.mesos.length; numMonth++) {
						FactorCrecimientoFactura fc = new FactorCrecimientoFactura();
						fc.setCodeFactura(codefactura);
						fc.setMonth(numMonth + 1);
						fc.setFactor(1.0);
						list.add(fc);
					}
				}
			} catch (Exception e) {
				log.error("BDFacturas.getFactoresCrecimientoFactura", e);
				throw e;
			} finally {

				if (null != ps)
					ps.close();
			}
		}
		return list;

	}

	public static void insertFactoresCrecimientoFactura( ConexionBD con, List<FactorCrecimientoFactura> list ) throws Exception{

		if (list != null) {

			String sqlInsert = " insert into cso_facturas_fc (codefactura, month, fc) values (?,?,?)";
			String sqlUpdate = " update cso_facturas_fc set fc=? where codefactura=? and month=?";
			PreparedStatement ps = null;
			try {
				for (FactorCrecimientoFactura factorCrecimiento : list) {

					if (factorCrecimiento.getCodeFactura() != null && factorCrecimiento.getMonth() != null
							&& factorCrecimiento.getFactor() != null) {

						if (getFactorCrecimientoFactura(con, factorCrecimiento.getCodeFactura(), factorCrecimiento.getMonth()) != null) {
							ps = con.getPreparedStatement(sqlUpdate);
							int count = 1;
							ps.setDouble(count++, factorCrecimiento.getFactor());
							ps.setString(count++, factorCrecimiento.getCodeFactura());
							ps.setInt(count++, factorCrecimiento.getMonth());

						} else {

							ps = con.getPreparedStatement(sqlInsert);
							int count = 1;
							ps.setString(count++, factorCrecimiento.getCodeFactura());
							ps.setInt(count++, factorCrecimiento.getMonth());
							ps.setDouble(count, factorCrecimiento.getFactor());
						}
						con.executeUpdate(ps);
					}
				}
			} catch (Exception e) {
				log.error("BDFacturas.insertFactoresCrecimientoFactura", e);
				throw e;
			} finally {

				if (null != ps)
					ps.close();
			}
		}
	}

	private static FactorCrecimientoFactura getFactorCrecimientoFactura( ConexionBD con, String codeFactura, Integer month )
			throws Exception{

		String sql = " select codefactura, month, fc from cso_facturas_fc where codefactura= ? and month= ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		FactorCrecimientoFactura factorCrecimientoFactura = null;
		try {

			ps = con.getPreparedStatement(sql);
			int count = 1;
			ps.setString(count++, codeFactura);
			ps.setInt(count++, month);
			rs = ps.executeQuery();
			while (rs.next()) {
				factorCrecimientoFactura = new FactorCrecimientoFactura();
				factorCrecimientoFactura.setCodeFactura(rs.getString("codefactura"));
				factorCrecimientoFactura.setFactor(rs.getDouble("fc"));
				factorCrecimientoFactura.setMonth(rs.getInt("month"));
			}

		} catch (Exception e) {
			log.error("BDFacturas.getFactorCrecimientoFactura", e);
			throw e;
		} finally {

			if (null != ps)
				ps.close();
		}
		return factorCrecimientoFactura;
	}
}
