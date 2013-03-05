package es.intos.gdscso.actions.excelPagades;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.util.MessageResources;

import es.intos.excels.AMidaExcel;
import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasFormDTO;
import es.intos.gdscso.forms.control.GestionFacturasGeneraExcelForm;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.ln.LNImportsPactats;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.FactorCrecimientoFactura;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.utils.Constants;
import es.intos.gdscso.utils.ListWithNoDuplicates;
import es.intos.gdscso.utils.Utils;
import es.intos.util.CalendarIntos;
import es.intos.util.Format;
import es.intos.util.Usuario;

public class GeneraFacturasPagadasExcel extends AMidaExcel{

	private Map<String, Double>				mapOfFacturaImports		= new HashMap<String, Double>();
	private List<String>					codeListInExcel			= new ListWithNoDuplicates<String>();
	private List<String>					codeList				= new ListWithNoDuplicates<String>();
	private BusquedaGestionFacturasFormDTO	form					= new BusquedaGestionFacturasFormDTO();
	private DecimalFormat					twoDecimalForm			= createDecimalFormat();
	private String[]						mesos					= null;
	private int								nfila					= -1;
	private int								count					= 0;
	private int								numOfMonth				= 1;
	private HSSFCellStyle					estilRowTableGRIS		= null;
	private HSSFCellStyle					estilRowTableTOTAL		= null;
	private HSSFCellStyle					estilRowTable			= null;
	private HSSFCellStyle					estilRowTableGRISESQ	= null;
	private HSSFCellStyle					estilRowTableESQ		= null;
	private boolean							estilGris				= false;
	private Vector<Factura>					listaFactures			= new Vector<Factura>(1, 1);

	public GeneraFacturasPagadasExcel( String str, Usuario user, MessageResources messages, Locale locale ) {

		super(str, messages, locale);
		this.mesos = Utils.getMonths(messages, locale);

	}

	public HSSFWorkbook generaExcel( GestionFacturasGeneraExcelForm frm ) throws NumberFormatException, Exception{

		getForm(frm);

		searchInfoAndCreateHash();

		creaExcelEstilos();
		setColumnsWidth();

		createFirstRows(frm);
		createTableOfExcel();

		// second table
		searchInfoForResumeTableAndFillHash(frm);
		createTableOfResme();

		return wb;
	}

	private void createTableOfResme(){

		createHeaderOfResumeExcelTable();
		createRowInExcelForResume();
	}

	private void createRowInExcelForResume(){

		count = 1;
		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(this.mapOfFacturaImports.get("ImportPactat") + "€");
		row.getCell(count++).setCellStyle(estilRowTable);
		row.createCell(count).setCellValue(this.twoDecimalForm.format(this.mapOfFacturaImports.get("ImportTotal")) + "€");
		row.getCell(count++).setCellStyle(estilRowTable);
		row.createCell(count).setCellValue(this.twoDecimalForm.format(this.mapOfFacturaImports.get("ImportEstimat")) + "€");
		row.getCell(count++).setCellStyle(estilRowTable);
		row.createCell(count).setCellValue(this.mapOfFacturaImports.get("valoracio").equals(0.0) ? "No Disp." :this.twoDecimalForm.format(this.mapOfFacturaImports.get("valoracio")) + "%");
		row.getCell(count++).setCellStyle(estilRowTable);

	}

	private void searchInfoForResumeTableAndFillHash( GestionFacturasGeneraExcelForm frm ) throws NumberFormatException, Exception{

		Basic bs = LNImportsPactats.getImportPactat(((frm.getYear() == null || frm.getYear().equals("")) ? Calendar.getInstance().get(
				Calendar.YEAR) : Integer.parseInt(frm.getYear())));
		Double importPactat = (bs != null && bs.getDescripcio() != null) ? Double.parseDouble(bs.getDescripcio()) : 0.0;
		this.mapOfFacturaImports.put("ImportPactat", importPactat);
		Double importEstimat = calculImportEstimatGlobal();
		this.mapOfFacturaImports.put("ImportEstimat", importEstimat);
		Double valoracio = 0.0;
		if (importPactat.equals(0.0))
			valoracio = 0.0;
		else if(importEstimat.equals( 0.0))
			valoracio = 0.0;
		else
			valoracio = ((importEstimat / importPactat)-1) * 100;

		this.mapOfFacturaImports.put("valoracio", valoracio);
	}

	private Double calculImportEstimatGlobal() throws Exception{

		Double importEstimat = 0.0;
		Double fcEstimat = 0.0;

		for (String codefactura : this.codeList) {
			String codeTotalFacturaYearInHash = "TY_" + codefactura;
			List<FactorCrecimientoFactura> listFactorsOfFactura = LNFacturas.getFactoresCrecimientoFacturas(codefactura);
			for (FactorCrecimientoFactura fc : listFactorsOfFactura) {
				fcEstimat = fcEstimat + fc.getFactor();
			}
			fcEstimat = fcEstimat / 12;
			if (this.mapOfFacturaImports.get(codeTotalFacturaYearInHash) != null) {
				importEstimat = importEstimat + this.mapOfFacturaImports.get(codeTotalFacturaYearInHash) * fcEstimat;
			}
		}
		return importEstimat;
	}

	private void createFirstRows( GestionFacturasGeneraExcelForm frm ){

		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(getMessage("txt.excel.title.excel"));
		row.getCell(count++).setCellStyle(this.estiloH1);

		row = sheet.createRow(++nfila);
		count = 1;
		row.createCell(count).setCellValue(getMessage("txt.excel.any.form"));
		row.getCell(count++).setCellStyle(estiloH2Bold);

		row.createCell(count).setCellValue(((frm.getYear() == null || frm.getYear().equals("")) ? "" : frm.getYear()));
		row.getCell(count++).setCellStyle(estiloH2);

		setDateInExcel();

		count = 0;

		row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue("");
		row.getCell(count++).setCellStyle(estiloH2);
	}

	private void getForm( GestionFacturasGeneraExcelForm frm ){

		this.form.setF_any(frm.getYear());
	}

	private void searchInfoAndCreateHash() throws Exception{

		form.setF_estado(String.valueOf(Constants.ESTAT_FIN));
		this.listaFactures = LNFacturas.getFacturas(null, form, null, 1, 9999999);

		for (Factura factura : listaFactures) {
			if (factura.getCode() == null || factura.getCode().equals(""))
				continue;
			this.codeListInExcel.add(factura.getNomCso() + " " + factura.getCode());
			this.codeList.add(factura.getNomCso() + " " + factura.getCode());
			fillHash(factura);
		}
	}

	private void creaExcelEstilos(){

		creaEstilos(wb);
		asignaBordeCabecera(estilCapcelera, (short) 1);
		asignaBordeCabecera(estilCapceleraESQ, (short) 1);
		asignaBordeCabecera(estilCapceleraTOTAL, (short) 1);
		asignaBordeCabecera(estilCapceleraTOTAL_DRE, (short) 1);
		asignaBordeCabecera(estilTotal, (short) 1);
		asignaBordeCabecera(estilTotalNumero_0, (short) 1);
		asignaBordeCabecera(estilTotalNumero_2, (short) 1);
		asignaBordeCabecera(estilCapceleraBold, (short) 1);

		estilRowTableGRIS = wb.createCellStyle();
		estilRowTableGRIS.setFont(this.fontNormal);
		estilRowTableGRIS.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		estilRowTableGRIS.setWrapText(true);
		estilRowTableGRIS.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estilRowTableGRIS.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());

		estilRowTableGRISESQ = wb.createCellStyle();
		estilRowTableGRISESQ.setFont(this.fontNormal);
		estilRowTableGRISESQ.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		estilRowTableGRISESQ.setWrapText(true);
		estilRowTableGRISESQ.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estilRowTableGRISESQ.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());

		estilRowTableTOTAL = wb.createCellStyle();
		estilRowTableTOTAL.setFont(this.fontNormal);
		estilRowTableTOTAL.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		estilRowTableTOTAL.setWrapText(true);
		estilRowTableTOTAL.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estilRowTableTOTAL.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());

		estilRowTable = wb.createCellStyle();
		estilRowTable.setFont(this.fontNormal);
		estilRowTable.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		estilRowTable.setWrapText(true);

		estilRowTableESQ = wb.createCellStyle();
		estilRowTableESQ.setFont(this.fontNormal);
		estilRowTableESQ.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		estilRowTableESQ.setWrapText(true);
	}

	private void fillHash( Factura factura ){

		String codeImporteFacturaMonthInHash = factura.getNomCso() + " " + factura.getCode() + "_" + factura.getMonth();
		this.mapOfFacturaImports.put(codeImporteFacturaMonthInHash, factura.getImporte());
		String codeTotalMonthInhash = "T_" + factura.getMonth();
		String codeTotalFacturaYearInHash = "TY_" + factura.getCode();
		if (this.mapOfFacturaImports.get(codeTotalMonthInhash) != null) {
			Double importTotalOfMonth = this.mapOfFacturaImports.get(codeTotalMonthInhash);
			importTotalOfMonth = importTotalOfMonth + factura.getImporte();
			this.mapOfFacturaImports.remove(codeTotalMonthInhash);
			this.mapOfFacturaImports.put(codeTotalMonthInhash, importTotalOfMonth);
		} else {
			this.mapOfFacturaImports.put(codeTotalMonthInhash, factura.getImporte());
		}

		if (this.mapOfFacturaImports.get(codeTotalFacturaYearInHash) != null) {
			Double importTotalFacturaYear = this.mapOfFacturaImports.get(codeTotalFacturaYearInHash);
			importTotalFacturaYear = importTotalFacturaYear + factura.getImporte();
			this.mapOfFacturaImports.remove(codeTotalFacturaYearInHash);
			this.mapOfFacturaImports.put(codeTotalFacturaYearInHash, importTotalFacturaYear);
		} else {
			this.mapOfFacturaImports.put(codeTotalFacturaYearInHash, factura.getImporte());
		}
	}

	private DecimalFormat createDecimalFormat(){

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		return new DecimalFormat("#.##", symbols);
	}

	private void createTableOfExcel(){

		createHeaderOfExcelTable();

		for (String facturaCode : this.codeList) {
			createRowInExcelForOneFactura(facturaCode);
		}

		createRowInExcelForTotals();
	}

	private void createRowInExcelForOneFactura( String facturaCode ){

		int count = 1;
		Double importtotal = 0.0;
		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(facturaCode);
		if (this.estilGris == true) {
			row.getCell(count++).setCellStyle(estilRowTableGRISESQ);
		} else {
			row.getCell(count++).setCellStyle(estilRowTableESQ);
		}
		numOfMonth = 1;
		for (numOfMonth = 1; numOfMonth < 13; numOfMonth++) {
			if (this.mapOfFacturaImports.get(facturaCode + "_" + numOfMonth) != null) {
				importtotal = importtotal + this.mapOfFacturaImports.get(facturaCode + "_" + numOfMonth);
			}
			row.createCell(count).setCellValue(
					(this.mapOfFacturaImports.get(facturaCode + "_" + numOfMonth) == null) ? "0.0€" : this.twoDecimalForm
							.format(this.mapOfFacturaImports.get(facturaCode + "_" + numOfMonth)) + "€");
			if (this.estilGris == true) {
				row.getCell(count++).setCellStyle(estilRowTableGRIS);

			} else {
				row.getCell(count++).setCellStyle(estilRowTable);
			}
		}

		row.createCell(count + 1).setCellValue(this.twoDecimalForm.format(importtotal) + "€");

		if (this.estilGris == true) {
			row.getCell(count + 1).setCellStyle(this.estilRowTableGRIS);
			estilGris = false;
		} else {
			row.getCell(count + 1).setCellStyle(this.estilRowTable);
			estilGris = true;
		}
		count++;
	}

	private void setDateInExcel(){

		HSSFRow row = sheet.createRow(++nfila);

		CalendarIntos hoy = new CalendarIntos();
		String minuto = new Format(hoy.get(CalendarIntos.MINUTE)).format("00");
		String ara = hoy.get(CalendarIntos.HOUR_OF_DAY) + ":" + minuto + " del " + hoy.get(CalendarIntos.DAY_OF_MONTH) + "/"
				+ (1 + hoy.get(CalendarIntos.MONTH)) + "/" + hoy.get(CalendarIntos.YEAR);
		String lgenerat = getMessage("generado.Listado") + " " + ara;
		row = sheet.createRow(++nfila);
		sheet.addMergedRegion(new CellRangeAddress(nfila, nfila, 0, 15));
		row.createCell(0).setCellValue(lgenerat);
		row.getCell(0).setCellStyle(estiloH2_DRETA);
	}

	private void createRowInExcelForTotals(){

		HSSFRow row = sheet.createRow(++nfila);
		double importTotal = 0.0;
		row = sheet.createRow(++nfila);
		count = 1;
		row.createCell(count++).setCellValue("TOTALS");
		for (numOfMonth = 1; numOfMonth < 13; numOfMonth++) {
			row.createCell(count).setCellValue(
					(this.mapOfFacturaImports.get("T_" + numOfMonth) == null) ? "0.0€" : this.twoDecimalForm
							.format(this.mapOfFacturaImports.get("T_" + numOfMonth)) + "€");
			row.getCell(count++).setCellStyle(this.estilRegistre_DRETA);
			if (this.mapOfFacturaImports.get("T_" + numOfMonth) != null) {
				importTotal = importTotal + this.mapOfFacturaImports.get("T_" + numOfMonth);
			}
		}
		this.mapOfFacturaImports.put("ImportTotal", importTotal);
		row.createCell(count + 1).setCellValue(this.twoDecimalForm.format(importTotal) + "€");
		row.getCell(count + 1).setCellStyle(this.estilRowTableTOTAL);
		count++;
	}

	private void createHeaderOfExcelTable(){

		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(getMessage("txt.excel.header.code.servei"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		for (numOfMonth = 1; numOfMonth < 13; numOfMonth++) {
			row.createCell(count).setCellValue(mesos[numOfMonth - 1]);
			row.getCell(count++).setCellStyle(estilCapcelera);
		}

		row.createCell(count + 1).setCellValue(getMessage("txt.excel.header.total"));
		row.getCell(count + 1).setCellStyle(this.estilCapceleraBLAUBold);
		count++;
	}

	private void createHeaderOfResumeExcelTable(){

		nfila = nfila + 2;
		count = 1;
		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(getMessage("txt.excel.header.totalPactat"));
		row.getCell(count++).setCellStyle(estilCapcelera);
		row.createCell(count).setCellValue(getMessage("txt.excel.header.totalConsumido"));
		row.getCell(count++).setCellStyle(estilCapcelera);
		row.createCell(count).setCellValue(getMessage("txt.excel.header.totalEstimado"));
		row.getCell(count++).setCellStyle(estilCapcelera);
		row.createCell(count).setCellValue(getMessage("txt.excel.header.valoracio"));
		row.getCell(count++).setCellStyle(estilCapcelera);
	}

	private void setColumnsWidth(){

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
		sheet.setColumnWidth(countColumn++, 2000);
		sheet.setColumnWidth(countColumn++, 5000);
	}

}
