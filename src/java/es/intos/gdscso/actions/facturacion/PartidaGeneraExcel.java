package es.intos.gdscso.actions.facturacion;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.util.MessageResources;

import es.intos.excels.AMidaExcel;
import es.intos.gdscso.forms.partides.PartidaGeneraExcelForm;
import es.intos.gdscso.ln.LNPartidas;
import es.intos.gdscso.on.DetallPartidaTable;
import es.intos.gdscso.utils.Utils;
import es.intos.util.CalendarIntos;
import es.intos.util.Format;
import es.intos.util.Usuario;

public class PartidaGeneraExcel extends AMidaExcel{

	private Integer	idpartida	= null;
	private Integer	year		= null;

	public PartidaGeneraExcel( String str, Usuario user, MessageResources messages, Locale locale ) {

		super(str, messages, locale);

	}

	public HSSFWorkbook generaExcel( PartidaGeneraExcelForm frm ) throws NumberFormatException, Exception{

		if (frm == null) {
			this.year = Calendar.getInstance().get(Calendar.YEAR);
		} else {
			this.year = (frm.getYear() != null && !frm.getYear().equals("")) ? Integer.parseInt(frm.getYear()) : null;
			this.idpartida = (frm.getIdpartida() != null && !frm.getIdpartida().equals("")) ? Integer.parseInt(frm.getIdpartida()) : null;
		}

		List<DetallPartidaTable> facturacioPartidasFirstTableList = LNPartidas.getInfoTableDetallPartida(this.year, this.idpartida, 0, 999,
				"srv.descrip asc");

		String[] mesos = Utils.getMonths(messages, locale);

		creaEstilos(wb);
		asignaBordeCabecera(estilCapcelera, (short) 1);
		asignaBordeCabecera(estilCapceleraESQ, (short) 1);
		asignaBordeCabecera(estilCapceleraTOTAL, (short) 1);
		asignaBordeCabecera(estilCapceleraTOTAL_DRE, (short) 1);
		asignaBordeCabecera(estilTotal, (short) 1);
		asignaBordeCabecera(estilTotalNumero_0, (short) 1);
		asignaBordeCabecera(estilTotalNumero_2, (short) 1);
		asignaBordeCabecera(estilCapceleraBold, (short) 1);

		int numCols = 13;
		int countColumn = 0;

		sheet.setColumnWidth(countColumn++, 9000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);

		int nfila = -1;
		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue(getMessage("txt.title.excel.srv.control"));
		row.getCell(0).setCellStyle(estiloH1);

		row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue("");
		row.getCell(0).setCellStyle(estiloH2);

		int count = 1;
		row = sheet.createRow(++nfila);

		row.createCell(count).setCellValue(getMessage("txt.control.excel.year"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(frm.getYear());
		row.getCell(count++).setCellStyle(estiloH2);

		CalendarIntos hoy = new CalendarIntos();
		String minuto = new Format(hoy.get(CalendarIntos.MINUTE)).format("00");
		String ara = hoy.get(CalendarIntos.HOUR_OF_DAY) + ":" + minuto + " del " + hoy.get(CalendarIntos.DAY_OF_MONTH) + "/"
				+ (1 + hoy.get(CalendarIntos.MONTH)) + "/" + hoy.get(CalendarIntos.YEAR);
		String lgenerat = getMessage("generado.Listado") + " " + ara;
		row = sheet.createRow(++nfila);
		sheet.addMergedRegion(new CellRangeAddress(nfila, nfila, 0, numCols));
		row.createCell(0).setCellValue(lgenerat);
		row.getCell(0).setCellStyle(estiloH2_DRETA);

		count = 0;

		row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue("");
		row.getCell(count++).setCellStyle(estiloH2);

		// ////////////////////COLUMNAT ************************
		row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(getMessage("txt.control.excel.th1"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[0]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[1]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[2]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[3]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[4]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[5]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[6]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[7]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[8]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[9]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[10]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(mesos[11]);
		row.getCell(count++).setCellStyle(estilCapcelera);

		// ////////////////////

		for (DetallPartidaTable detallPartidaTable : facturacioPartidasFirstTableList) {
			count = 1;
			row = sheet.createRow(++nfila);
			row.createCell(count).setCellValue(Utils.decode(detallPartidaTable.getServei()));
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(detallPartidaTable.getEne().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(this.estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getFeb().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getMar().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getAbr().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getMai().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getJun().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getJul().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getAgo().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getSet().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getOct().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getNov().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);

			row.createCell(count).setCellValue(detallPartidaTable.getDes().replaceAll("&euro;", "€"));
			row.getCell(count++).setCellStyle(estilRegistre_DRETA);
		}

		return wb;
	}

}
