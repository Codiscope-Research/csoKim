package es.intos.gdscso.actions.consulta;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.util.MessageResources;

import es.intos.excels.AMidaExcel;
import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasForm;
import es.intos.gdscso.forms.consulta.BusquedaGestionFacturasFormDTO;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.ln.LNEstats;
import es.intos.gdscso.ln.LNFacturas;
import es.intos.gdscso.on.Basic;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.utils.Utils;
import es.intos.util.CalendarIntos;
import es.intos.util.Format;
import es.intos.util.Usuario;

public class GeneraFacturasExcel extends AMidaExcel{

	private static final SimpleDateFormat	df				= new SimpleDateFormat("dd-MM-yyyy");
	private Vector<Basic>					estats			= null;
	private String							csoDescripcio	= "";

	public GeneraFacturasExcel( String str, Usuario user, MessageResources messages, Locale locale ) {

		super(str, messages, locale);

	}

	public HSSFWorkbook generaExcel( BusquedaGestionFacturasForm frm ) throws NumberFormatException, Exception{

		BusquedaGestionFacturasFormDTO facturaDTO = getDTO(frm);

		this.estats = LNEstats.getEstats(locale.getLanguage());
		if (frm.getF_cso() != null && !frm.getF_cso().equals("")) {
			Vector<Basic> csos = LNCso.getCSO(Integer.parseInt(frm.getF_cso()));
			if (!csos.isEmpty())
				csoDescripcio = csos.get(0).getDescripcio();
		}

		Vector<Factura> lista = LNFacturas.getFacturas(null, facturaDTO, frm.getOrder_by(), 1, 99999);

		String[] mesos = Utils.getMonths(super.messages, locale);

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
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 10000);
		sheet.setColumnWidth(countColumn++, 5000);
		sheet.setColumnWidth(countColumn++, 5000);

		int nfila = -1;
		HSSFRow row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue(getMessage("txt.title.excel.fac.consulta"));
		row.getCell(0).setCellStyle(estiloH1);

		row = sheet.createRow(++nfila);
		row.createCell(0).setCellValue("");
		row.getCell(0).setCellStyle(estiloH2);

		int count = 1;
		row = sheet.createRow(++nfila);

		row.createCell(count).setCellValue(getMessage("txt.consulta.form.cso"));
		row.getCell(count++).setCellStyle(estiloH2Bold);

		row.createCell(count).setCellValue(csoDescripcio);
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;
		row.createCell(count).setCellValue(getMessage("txt.consulta.form.ano"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue((frm.getF_any().equals("") ? getMessage("consulta.gestServ.todos") : frm.getF_any()));
		row.getCell(count++).setCellStyle(estiloH2);

		row.createCell(count).setCellValue(getMessage("txt.consulta.form.mes"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(
				frm.getF_mes().equals("") ? getMessage("consulta.gestServ.todos") : mesos[Integer.parseInt(frm.getF_mes()) - 1]);
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;
		row.createCell(count).setCellValue(getMessage("txt.consulta.form.estado"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(
				frm.getF_estado().equals("") ? getMessage("consulta.gestServ.todos") : this.estats.get(Integer.parseInt(frm.getF_estado()))
						.getDescripcio());
		row.getCell(count++).setCellStyle(estiloH2);

		row.createCell(count).setCellValue(getMessage("txt.consulta.form.fechaini"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(frm.getF_fechaFacDesde().equals("") ? " - " : frm.getF_fechaFacDesde());
		row.getCell(count++).setCellStyle(estiloH2);

		row.createCell(count).setCellValue(getMessage("txt.consulta.form.fechafin"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(frm.getF_fechaFacHasta().equals("") ? " - " : frm.getF_fechaFacHasta());
		row.getCell(count++).setCellStyle(estiloH2);

		row = sheet.createRow(++nfila);
		count = 1;
		row.createCell(count).setCellValue(getMessage("txt.consulta.form.importe"));
		row.getCell(count++).setCellStyle(estiloH2Bold);
		row.createCell(count).setCellValue(frm.getF_impdesde() + " - " + frm.getF_imphasta());
		row.getCell(count++).setCellStyle(estiloH2);

		CalendarIntos hoy = new CalendarIntos();
		String minuto = new Format(hoy.get(CalendarIntos.MINUTE)).format("00");
		String ara = hoy.get(CalendarIntos.HOUR_OF_DAY) + ":" + minuto + " del " + hoy.get(CalendarIntos.DAY_OF_MONTH) + "/"
				+ (1 + hoy.get(CalendarIntos.MONTH)) + "/" + hoy.get(CalendarIntos.YEAR);
		String lgenerat = getMessage("generado.Listado") + " " + ara;
		row = sheet.createRow(++nfila);
		sheet.addMergedRegion(new CellRangeAddress(nfila, nfila, 0, 6));
		row.createCell(0).setCellValue(lgenerat);
		row.getCell(0).setCellStyle(estiloH2_DRETA);

		count = 0;

		row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue("");
		row.getCell(count++).setCellStyle(estiloH2);

		// ////////////////////COLUMNAT ************************
		row = sheet.createRow(++nfila);
		row.createCell(count).setCellValue(getMessage("txt.consulta.th1.rejilla"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.consulta.th2.rejilla"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.consulta.th3.rejilla"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.consulta.th4.rejilla"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.consulta.th5.rejilla"));
		row.getCell(count++).setCellStyle(estilCapcelera);

		row.createCell(count).setCellValue(getMessage("txt.consulta.th6.rejilla"));
		row.getCell(count++).setCellStyle(estilCapcelera);
		// ////////////////////

		for (Factura factura : lista) {
			count = 1;
			row = sheet.createRow(++nfila);
			row.createCell(count).setCellValue(factura.getNomCso());
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(mesos[factura.getMonth() - 1]);
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(factura.getYear());
			row.getCell(count++).setCellStyle(this.estilRegistre_CENTRE);

			row.createCell(count).setCellValue(factura.getImporte() + "€");
			row.getCell(count++).setCellStyle(this.estilRegistre_DRETA);

			row.createCell(count).setCellValue(factura.getNomEstat());
			row.getCell(count++).setCellStyle(estilRegistreMultiline);

			row.createCell(count).setCellValue(df.format(factura.getFentrada()));
			row.getCell(count++).setCellStyle(this.estilRegistre_CENTRE);

		}

		return wb;
	}

	private BusquedaGestionFacturasFormDTO getDTO( BusquedaGestionFacturasForm form ) throws IllegalAccessException,
			InvocationTargetException{

		// traspas del from
		BusquedaGestionFacturasFormDTO busquedaGestionFacturasFormDTO = new BusquedaGestionFacturasFormDTO();
		BeanUtils.copyProperties(busquedaGestionFacturasFormDTO, form);
		return busquedaGestionFacturasFormDTO;

	}

}
