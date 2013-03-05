package es.intos.gdscso.actions.manteniments;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.util.MessageResources;

import es.intos.excels.AMidaExcel;
import es.intos.gdscso.forms.manteniments.BusquedaComparativaServeisFormDTO;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.ComparativaSrvTable;
import es.intos.gdscso.utils.Utils;
import es.intos.util.CalendarIntos;
import es.intos.util.Format;
import es.intos.util.Usuario;

public class GeneraExcelServeis extends AMidaExcel{

	private int			nfila	= -1;
	private int			count	= 1;
	private String[]	mesos	= null;

	public GeneraExcelServeis( String str, Usuario user, MessageResources messages, Locale locale ) throws Exception {

		super(str, messages, locale);
		this.mesos = Utils.getMonths(messages, locale);

	}

	public HSSFWorkbook generaExcel( BusquedaComparativaServeisFormDTO comparativaSrvFormDTO,
			List<ComparativaSrvTable> comparativaServeislist ) throws NumberFormatException, Exception{

		estils();
		setTitle();
		setHeader(comparativaSrvFormDTO);

		if (!comparativaServeislist.isEmpty())
			creaTable(comparativaServeislist);

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
		sheet.setColumnWidth(countColumn++, 10000);
		sheet.setColumnWidth(countColumn++, 10000);
		sheet.setColumnWidth(countColumn++, 9000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);

	}

	private void setTitle() throws Exception{

		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue(getMessage("txt.serv.title"));
		row.getCell(0).setCellStyle(estiloH1);

		row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue("");
		row.getCell(0).setCellStyle(estiloH2);
	}

	private void setHeader( BusquedaComparativaServeisFormDTO comparativaSrvFormDTO ) throws Exception{

		HSSFRow row = sheet.createRow(++nfila);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);

		// primer cso Actual
		row.createCell(count).setCellValue(mesos[(month - 1)] + "/" + year + "  " + getMessage("txt.serv.filtro.centro.cso"));
		row.getCell(count++).setCellStyle(estiloH2Bold);

		String csoDescripcio = "";
		if (comparativaSrvFormDTO.getF_csoActual() != null && !comparativaSrvFormDTO.getF_csoActual().equals("")) {
			Vector<Basic> csos = LNCso.getCSO(Integer.parseInt(comparativaSrvFormDTO.getF_csoActual()));
			if (!csos.isEmpty())
				csoDescripcio = csos.get(0).getDescripcio();
		}

		row.createCell(count).setCellValue(csoDescripcio.equals("") ? getMessage("consulta.gestServ.todos") : csoDescripcio);
		row.getCell(count++).setCellStyle(estiloH2);

		// cso a comparar
		row = sheet.createRow(++nfila);
		count = 1;

		row.createCell(count).setCellValue(
				mesos[Integer.parseInt(comparativaSrvFormDTO.getF_mes())] + "/" + comparativaSrvFormDTO.getF_any() + "  "
						+ getMessage("txt.serv.filtro.centro.cso"));
		row.getCell(count++).setCellStyle(estiloH2Bold);

		csoDescripcio = "";
		if (comparativaSrvFormDTO.getF_cso() != null && !comparativaSrvFormDTO.getF_cso().equals("")) {
			Vector<Basic> csos = LNCso.getCSO(Integer.parseInt(comparativaSrvFormDTO.getF_cso()));
			if (!csos.isEmpty())
				csoDescripcio = csos.get(0).getDescripcio();
		}

		row.createCell(count).setCellValue(csoDescripcio.equals("") ? getMessage("consulta.gestServ.todos") : csoDescripcio);
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

	private void creaTable( List<ComparativaSrvTable> comparativaServeislist ) throws Exception{

		creaTableHeader();
		fillTable(comparativaServeislist);
	}

	private void creaTableHeader() throws Exception{

		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(getMessage("txt.serv.actual"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.serv.ant"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.serv.compartiva"));
		row.getCell(count++).setCellStyle(estilCapcelera);

	}

	private void fillTable( List<ComparativaSrvTable> comparativaServeislist ) throws Exception{

		for (ComparativaSrvTable comparativaServeisCSO : comparativaServeislist) {

			count = 1;

			HSSFRow row = sheet.createRow(++nfila);
			row.createCell(count).setCellValue(comparativaServeisCSO.getServeiActual());
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(comparativaServeisCSO.getServeiAnterior());
			row.getCell(count++).setCellStyle(this.estilRegistreMultiline);

			row.createCell(count).setCellValue(comparativaServeisCSO.getEstat());
			row.getCell(count++).setCellStyle(this.estilRegistreMultiline);

		}
	}
}
