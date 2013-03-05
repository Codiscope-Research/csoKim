package es.intos.gdscso.actions.partes;

import java.util.Locale;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.util.MessageResources;

import es.intos.excels.AMidaExcel;
import es.intos.gdscso.forms.partes.BusquedaNoConformidadesFormDTO;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.FacturaTableNC;
import es.intos.gdscso.utils.Utils;
import es.intos.util.CalendarIntos;
import es.intos.util.Format;
import es.intos.util.Usuario;

public class GeneraExcelParte extends AMidaExcel{

	private int			nfila	= -1;
	private int			count	= 1;
	private String[]	mesos	= null;

	public GeneraExcelParte( String str, Usuario user, MessageResources messages, Locale locale ) throws Exception {

		super(str, messages, locale);
		this.mesos = Utils.getMonths(messages, locale);

	}

	public HSSFWorkbook generaExcel( Vector<FacturaTableNC> listaNC, BusquedaNoConformidadesFormDTO facturaNoConformidadesDTO )
			throws NumberFormatException, Exception{

		estils();
		setTitle();
		setHeader(facturaNoConformidadesDTO);

		if (!listaNC.isEmpty())
			creaTable(listaNC);

		return wb;
	}

	private void estils() throws Exception{

		creaEstilos(wb);
		asignaBordeCabecera(estilCapcelera, (short) 1);
		asignaBordeCabecera(estilCapceleraESQ, (short) 1);
		asignaBordeCabecera(estilCapceleraTOTAL, (short) 1);
		asignaBordeCabecera(estilCapceleraTOTAL_DRE, (short) 1);
		asignaBordeCabecera(estilTotal, (short) 1);
		asignaBordeCabecera(estilTotalNumero_0, (short) 1);
		asignaBordeCabecera(estilTotalNumero_2, (short) 1);
		asignaBordeCabecera(estilCapceleraBold, (short) 1);

		int countColumn = 0;

		sheet.setColumnWidth(countColumn++, 9000);
		sheet.setColumnWidth(countColumn++, 9000);
		sheet.setColumnWidth(countColumn++, 9000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
	}

	private void setTitle() throws Exception{

		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue(getMessage("txt.nocon.title.jsp"));
		row.getCell(0).setCellStyle(estiloH1);

		row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue("");
		row.getCell(0).setCellStyle(estiloH2);
	}

	private void setHeader( BusquedaNoConformidadesFormDTO facturaNoConformidadesDTO ) throws Exception{

		HSSFRow row = sheet.createRow(++nfila);

		row.createCell(count).setCellValue(getMessage("txt.excel.code"));
		row.getCell(count++).setCellStyle(estiloH2Bold);

		row.createCell(count).setCellValue(
				(facturaNoConformidadesDTO.getF_idfact() == null || facturaNoConformidadesDTO.getF_idfact().equals("")) ? "-"
						: facturaNoConformidadesDTO.getF_idfact());
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;

		row.createCell(count).setCellValue(getMessage("txt.nocon.form.cso"));
		row.getCell(count++).setCellStyle(estiloH2Bold);

		String csoDescripcio = "";
		if (facturaNoConformidadesDTO.getF_cso() != null && !facturaNoConformidadesDTO.getF_cso().equals("")) {
			Vector<Basic> csos = LNCso.getCSO(Integer.parseInt(facturaNoConformidadesDTO.getF_cso()));
			if (!csos.isEmpty())
				csoDescripcio = csos.get(0).getDescripcio();
		}
		row.createCell(count).setCellValue(csoDescripcio.equals("") ? getMessage("consulta.gestServ.todos") : csoDescripcio);
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;

		row.createCell(count).setCellValue(getMessage("txt.excel.year"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count)
				.setCellValue(
						(facturaNoConformidadesDTO.getF_any() == null || facturaNoConformidadesDTO.getF_any().equals("")) ? getMessage("consulta.gestServ.todos")
								: facturaNoConformidadesDTO.getF_any());
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;

		row.createCell(count).setCellValue(getMessage("txt.excel.month"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(
				(facturaNoConformidadesDTO.getF_mes() != null && !facturaNoConformidadesDTO.getF_mes().equals("")) ? mesos[Integer
						.parseInt(facturaNoConformidadesDTO.getF_mes()) - 1] : getMessage("consulta.gestServ.todos"));
		row.getCell(count++).setCellStyle(estiloH2);

		setGenerationDate();

		setEmptyRow();
	}

	private void setEmptyRow(){

		count = 0;

		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue("");
		row.getCell(count++).setCellStyle(estiloH2);
	}

	private void setGenerationDate() throws Exception{

		HSSFRow row = sheet.createRow(++nfila);
		count = 1;
		CalendarIntos hoy = new CalendarIntos();
		String minuto = new Format(hoy.get(CalendarIntos.MINUTE)).format("00");
		String ara = hoy.get(CalendarIntos.HOUR_OF_DAY) + ":" + minuto + " del " + hoy.get(CalendarIntos.DAY_OF_MONTH) + "/"
				+ (1 + hoy.get(CalendarIntos.MONTH)) + "/" + hoy.get(CalendarIntos.YEAR);
		String lgenerat = getMessage("generado.Listado") + " " + ara;
		row = sheet.createRow(++nfila);
		sheet.addMergedRegion(new CellRangeAddress(nfila, nfila, 0, 5));
		row.createCell(0).setCellValue(lgenerat);
		row.getCell(0).setCellStyle(estiloH2_DRETA);
	}

	private void creaTable( Vector<FacturaTableNC> listaNC ) throws Exception{

		creaTableHeader();
		fillTable(listaNC);
	}

	private void creaTableHeader() throws Exception{

		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(getMessage("txt.nocon.form.fact"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.nocon.form.cso"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.nocon.form.any"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.nocon.form.mes"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.serv.rejilla.th5"));
		row.getCell(count++).setCellStyle(estilCapcelera);
	}

	private void fillTable( Vector<FacturaTableNC> listaNC ) throws Exception{

		for (FacturaTableNC nc : listaNC) {

			count = 1;

			HSSFRow row = sheet.createRow(++nfila);
			row.createCell(count).setCellValue(nc.getDescripcio());
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(nc.getCso());
			row.getCell(count++).setCellStyle(this.estilRegistreMultiline);

			row.createCell(count).setCellValue(nc.getYear());
			row.getCell(count++).setCellStyle(this.estilRegistre_CENTRE);

			row.createCell(count).setCellValue(
					(nc.getMonth() != null && nc.getMonth().equals("")) ? getMessage("consulta.gestServ.todos") : mesos[Integer.parseInt(nc
							.getMonth()) - 1]);
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(nc.getnNoConformitats());
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

		}
	}
}
