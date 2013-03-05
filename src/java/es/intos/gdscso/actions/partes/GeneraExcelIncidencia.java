package es.intos.gdscso.actions.partes;

import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.util.MessageResources;

import es.intos.excels.AMidaExcel;
import es.intos.gdscso.on.FacturaDialogNC;
import es.intos.gdscso.utils.Utils;
import es.intos.util.CalendarIntos;
import es.intos.util.Format;
import es.intos.util.Usuario;

public class GeneraExcelIncidencia extends AMidaExcel{

	private int			nfila	= -1;
	private int			count	= 1;
	private String[]	mesos	= null;

	public GeneraExcelIncidencia( String str, Usuario user, MessageResources messages, Locale locale ) {

		super(str, messages, locale);
		this.mesos = Utils.getMonths(messages, locale);

	}

	public HSSFWorkbook generaExcel( FacturaDialogNC factInfoN ) throws NumberFormatException, Exception{

		estils();
		setTitle();
		setHeader(factInfoN);

		if (factInfoN != null && factInfoN.getIncidencia() != null)
			creaTable(factInfoN.getIncidencia());

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
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 9000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
	}

	private void setTitle() throws Exception{

		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue(getMessage("txt.title.excel.incidencies"));
		row.getCell(0).setCellStyle(estiloH1);

		row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue("");
		row.getCell(0).setCellStyle(estiloH2);
	}

	private void setHeader( FacturaDialogNC factInfoN ) throws Exception{

		HSSFRow row = sheet.createRow(++nfila);

		row.createCell(count).setCellValue(getMessage("txt.excel.code"));
		row.getCell(count++).setCellStyle(estiloH2Bold);

		row.createCell(count).setCellValue(factInfoN.getCode());
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;

		row.createCell(count).setCellValue(getMessage("txt.excel.estat"));
		row.getCell(count++).setCellStyle(estiloH2Bold);

		row.createCell(count).setCellValue(
				(factInfoN.getEstat() == null || factInfoN.getEstat().equals("")) ? getMessage("consulta.gestServ.todos") : factInfoN
						.getEstat());
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;

		row.createCell(count).setCellValue(getMessage("txt.excel.year"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(factInfoN.getYear());
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;

		row.createCell(count).setCellValue(getMessage("txt.excel.month"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(
				(factInfoN.getMonth() != null && !factInfoN.getMonth().equals("")) ? mesos[Integer.parseInt(factInfoN.getMonth()) - 1]
						: getMessage("consulta.gestServ.todos"));
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;

		row.createCell(count).setCellValue(getMessage("txt.excel.cso"));
		row.getCell(count++).setCellStyle(estiloH2Bold);

		row.createCell(count).setCellValue(factInfoN.getCso());
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
		sheet.addMergedRegion(new CellRangeAddress(nfila, nfila, 0, 3));
		row.createCell(0).setCellValue(lgenerat);
		row.getCell(0).setCellStyle(estiloH2_DRETA);
	}

	private void creaTable( String[] incs ) throws Exception{

		creaTableHeader();
		fillTable(incs);
	}

	private void creaTableHeader() throws Exception{

		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(getMessage("txt.excel.table.estat"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.excel.table.date"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.excel.table.incidencies"));
		row.getCell(count++).setCellStyle(estilCapcelera);
	}

	private void fillTable( String[] incs ) throws Exception{

		for (String inc : incs) {
			String[] infotable = inc.split("_");
			count = 1;
			if (infotable.length == 3) {
				HSSFRow row = sheet.createRow(++nfila);
				row.createCell(count).setCellValue(Utils.decode(infotable[0]));
				row.getCell(count++).setCellStyle(estilRegistreMultiline);

				row.createCell(count).setCellValue(infotable[1]);
				row.getCell(count++).setCellStyle(this.estilRegistre_CENTRE);

				row.createCell(count).setCellValue(Utils.decode(infotable[2]));
				row.getCell(count++).setCellStyle(estilRegistreMultiline);
			}

		}
	}
}
