package es.intos.gdscso.actions.control;

import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.util.MessageResources;

import es.intos.excels.AMidaExcel;
import es.intos.gdscso.forms.control.GestionFacturasGeneraExcelForm;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.ln.LNVolum;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.SrvCSOTable;
import es.intos.gdscso.utils.Utils;
import es.intos.util.CalendarIntos;
import es.intos.util.Format;
import es.intos.util.Usuario;

public class GeneraExcelServiciosCso extends AMidaExcel{

	private Integer	idcso	= null;
	private Integer	year	= null;
	private Integer	month	= null;

	public GeneraExcelServiciosCso( String str, Usuario user, MessageResources messages, Locale locale ) {

		super(str, messages, locale);

	}

	private void inicializeParams( GestionFacturasGeneraExcelForm frm ){

		if (frm != null) {
			try {
				this.idcso = (frm.getIdCso() != null && !frm.getIdCso().equals("") && !frm.getIdCso().equals("null")) ? Integer
						.parseInt(frm.getIdCso()) : null;
			} catch (NumberFormatException e) {
				this.idcso = null;
			}

			try {
				this.year = (frm.getYear() != null && !frm.getYear().equals("") && !frm.getYear().equals("null")) ? Integer.parseInt(frm
						.getYear()) : Calendar.getInstance().get(Calendar.YEAR);
			} catch (NumberFormatException e) {
				this.year = Calendar.getInstance().get(Calendar.YEAR);
			}

			try {
				this.month = (frm.getMonth() != null && !frm.getMonth().equals("") && !frm.getMonth().equals("null")) ? Integer
						.parseInt(frm.getMonth()) : 0;
			} catch (NumberFormatException e) {
				this.month = 0;
			}

		}
	}

	public HSSFWorkbook generaExcel( GestionFacturasGeneraExcelForm frm ) throws NumberFormatException, Exception{

		inicializeParams(frm);

		Vector<SrvCSOTable> serveiCSOList = LNVolum.getSrvWithVolExcel(this.idcso, this.year, this.month, locale.getLanguage());
		Vector<Basic> csoVector = LNCso.getCSO(this.idcso);
		Basic cso = new Basic();
		if (!csoVector.isEmpty()) {
			cso = csoVector.get(0);
		}

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

		int numCols = 5;
		int countColumn = 0;

		sheet.setColumnWidth(countColumn++, 9000);
		sheet.setColumnWidth(countColumn++, 9000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 8000);
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
		row.createCell(count).setCellValue(getMessage("txt.control.excel.cso"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(cso.getDescripcio());
		row.getCell(count++).setCellStyle(estiloH2);

		row.createCell(count).setCellValue(getMessage("txt.control.excel.month"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(mesos[this.month - 1]);
		row.getCell(count++).setCellStyle(estiloH2);

		row.createCell(count).setCellValue(getMessage("txt.control.excel.year"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(this.year);
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

		row.createCell(count).setCellValue(getMessage("txt.control.excel.th2"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.control.excel.th3"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.control.excel.th4"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.control.excel.th5"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		// ////////////////////

		for (SrvCSOTable serveiCSO : serveiCSOList) {
			count = 1;
			row = sheet.createRow(++nfila);
			row.createCell(count).setCellValue(serveiCSO.getServei());
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(serveiCSO.getFactura());
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(serveiCSO.getVolum());
			row.getCell(count++).setCellStyle(this.estilRegistre_DRETA);

			row.createCell(count).setCellValue(serveiCSO.getEstatFactura());
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(serveiCSO.getPreu());
			row.getCell(count++).setCellStyle(this.estilRegistre_DRETA);
		}

		return wb;
	}

}
