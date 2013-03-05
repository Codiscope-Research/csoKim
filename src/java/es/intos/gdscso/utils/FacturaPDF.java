package es.intos.gdscso.utils;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

import es.intos.gdscso.exceptions.CsoPDFException;
import es.intos.gdscso.ln.LNCso;
import es.intos.gdscso.on.Cso;
import es.intos.gdscso.on.FactSrvTable;
import es.intos.gdscso.on.Factura;
import es.intos.gdscso.serveis.impl.PDFCreatorImpl;
import es.intos.gdscso.serveis.pojos.CsoPDFCell;

public class FacturaPDF{

	public static Logger					log				= Logger.getLogger(FacturaPDF.class);
	private PDFCreatorImpl					pDFCreatorImpl	= new PDFCreatorImpl();
	private MessageResources				mensajes		= null;
	private Factura							fct				= null;
	private static final SimpleDateFormat	df				= new SimpleDateFormat("dd-MM-yyyy");
	private static DecimalFormat			dcf				= new DecimalFormat("#.##");
	Vector<FactSrvTable>					listSrv			= null;
	private Cso								cso;
	private Double							importSenseIva	= 0.0;

	public FacturaPDF( MessageResources mensajes ) {

		this.mensajes = mensajes;
	}

	public ByteArrayOutputStream generaFacturaPDF( Vector<FactSrvTable> list, Factura fct, String marcaRuta ) throws Exception{

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			this.fct = fct;
			this.cso = LNCso.getCSOFromManteniment(fct.getIdCSO());
			this.listSrv = list;
			pDFCreatorImpl.initPDF();
			pDFCreatorImpl.setMarca(marcaRuta);
			creaTableHeader();
			creaTableHeader2();
			creaTableDadesAdicionals();
			creaTableInfoFactura();
			creaTableInfoServeis();
			creaTableSubFoot();
			creaTableFoot();
			baos = pDFCreatorImpl.closePDF();

		} catch (CsoPDFException e) {
			throw e;
		} catch (Exception e) {

			throw e;
		}
		return baos;
	}

	private void creaTableHeader(){

		List<CsoPDFCell> cells = new ArrayList<CsoPDFCell>();

		int[] borders = new int[] { PDFCreatorImpl.NO_BORDER };
		CsoPDFCell cell = new CsoPDFCell(this.mensajes.getMessage("factura.rao.social.label"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.rao.social.cti"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.rao.social.label"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.rao.social.cusa"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		cell = new CsoPDFCell(this.mensajes.getMessage("factura.nif.label"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.nif.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.address1.label"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.address1.calle.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		cell = new CsoPDFCell(this.mensajes.getMessage("factura.address2.label"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.address2.calle.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.address1.postalcode.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.address2.postalcode.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.address1.pais.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.address2.pais.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		pDFCreatorImpl.createTable(cells, 4, 100f, 35f, 0);
	}

	private void creaTableHeader2(){

		List<CsoPDFCell> cells = new ArrayList<CsoPDFCell>();

		int[] borders = new int[] { PDFCreatorImpl.NO_BORDER };

		CsoPDFCell cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.dades.client"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.rao.social.label"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.rao.social.cusa"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.nif.label"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.dades.client.nif"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		pDFCreatorImpl.createTable(cells, 4, 100f, 5f, 0);
	}

	private void creaTableDadesAdicionals(){

		List<CsoPDFCell> cells = new ArrayList<CsoPDFCell>();

		int[] borders = new int[] { PDFCreatorImpl.BORDER_BOX };

		CsoPDFCell cell = new CsoPDFCell(this.mensajes.getMessage("factura.dades.adicionals"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.dades.adicionals"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.contacto"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.contacto"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_RIGHT, PDFCreatorImpl.BORDER_BOTTOM };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.email") + "                "
				+ this.mensajes.getMessage("factura.email.value1"), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_RIGHT, PDFCreatorImpl.BORDER_BOTTOM };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.email") + "                 "
				+ this.mensajes.getMessage("factura.email.value2"), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		pDFCreatorImpl.createTable(cells, 2, 100f, 5f, 0);
	}

	private void creaTableInfoFactura(){

		List<CsoPDFCell> cells = new ArrayList<CsoPDFCell>();

		int[] borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_TOP };

		CsoPDFCell cell = new CsoPDFCell(this.mensajes.getMessage("factura.num.label"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_TOP };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.tipo"), borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.clase"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_TOP, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.ref.operacion"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_LEFT };
		cell = new CsoPDFCell(fct.getCode() + "-" + fct.getId(), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.tipo.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.clase.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell("CF12000030815", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_LEFT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.fecha"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] {};
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.periodo"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.moneda.op"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.moneda.imp"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_BOTTOM };
		cell = new CsoPDFCell(df.format(fct.getFentrada()), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("EUR - Euro", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell("EUR - Euro", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_LEFT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.num.registre"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] {};
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_BOTTOM };
		cell = new CsoPDFCell("3916_0001584404", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		pDFCreatorImpl.createTable(cells, 4, 100f, 15f, 0);
	}

	private void creaTableInfoServeis(){

		List<CsoPDFCell> cells = new ArrayList<CsoPDFCell>();

		int[] borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_TOP };

		CsoPDFCell cell = new CsoPDFCell(this.mensajes.getMessage("factura.ref.op.rec"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cell.setColspan(5);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_TOP, PDFCreatorImpl.BORDER_LEFT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.cantidad"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cell.setRowspan(2);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.unidad"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cell.setRowspan(2);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.precio.unitario"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cell.setRowspan(2);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.imp.ref"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cell.setRowspan(2);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_TOP, PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.dto"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cell.setRowspan(2);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_TOP };

		cell = new CsoPDFCell(this.mensajes.getMessage("factura.descrip"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cell.setColspan(5);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_TOP };
		cell = new CsoPDFCell("CF120000030815", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cell.setColspan(5);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_TOP };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_RIGHT, PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_TOP };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		if (listSrv != null && !listSrv.isEmpty()) {
			int numSrv = 0;
			for (FactSrvTable serv : listSrv) {

				if (numSrv == 0)
					borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_TOP, PDFCreatorImpl.BORDER_BOTTOM };
				else
					borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_BOTTOM };
				cell = new CsoPDFCell(serv.getServei(), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
				cell.setColspan(5);
				cells.add(cell);

				borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_LEFT };
				cell = new CsoPDFCell(String.valueOf(serv.getVolumetria()), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
				cells.add(cell);
				borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_LEFT };
				cell = new CsoPDFCell(this.mensajes.getMessage("factura.unidad.value"), borders, PDFCreatorImpl.font08_Black,
						PDFCreatorImpl.ALIGN_LEFT);
				cells.add(cell);
				cell = new CsoPDFCell(String.valueOf(serv.getPreuOp() == null ? "" : serv.getPreuOp()), borders,
						PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
				cells.add(cell);

				cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08_Black,
						PDFCreatorImpl.ALIGN_LEFT);
				cells.add(cell);

				borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT, PDFCreatorImpl.BORDER_LEFT };
				cell = new CsoPDFCell(String.valueOf(serv.getImporte()), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
				cells.add(cell);
				importSenseIva = importSenseIva + Double.parseDouble(Utils.getDoubleFrom(serv.getImporte()));

				numSrv = numSrv + 1;
			}
		}

		pDFCreatorImpl.createTable(cells, 10, 100f, 15f, 0);
	}

	private void creaTableSubFoot(){

		List<CsoPDFCell> cells = new ArrayList<CsoPDFCell>();

		int[] borders = new int[] { PDFCreatorImpl.NO_BORDER };

		CsoPDFCell cell = new CsoPDFCell(this.mensajes.getMessage("factura.imp.brut"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(3);
		cells.add(cell);
		cell = new CsoPDFCell(String.valueOf(fct.getImporte()), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cells.add(cell);

		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(4);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_BOX };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.total.imp.brut"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(3);
		cells.add(cell);
		cell = new CsoPDFCell(String.valueOf(fct.getImporte()), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.NO_BORDER };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(4);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_BOX };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.imp.repercutido"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cell.setColspan(4);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.concepto"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cell.setColspan(2);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.base.imp"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.cuota"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_CENTER);
		cells.add(cell);
		
		if(this.cso !=null && this.cso.isIva()==true){
			borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
			cell = new CsoPDFCell(this.mensajes.getMessage("factura.iva")+Constants.IVA+"%", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_CENTER);
			cell.setColspan(2);
			cells.add(cell);
			borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
			cell = new CsoPDFCell(String.valueOf(fct.getImporte()), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_CENTER);
			cells.add(cell);
			cell = new CsoPDFCell(String.valueOf(dcf.format((( this.importSenseIva) / 100) * Constants.IVA)), borders,
					PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_CENTER);
			cells.add(cell);
		}else if(this.cso !=null && this.cso.isIgic()==true){
			
			borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
			cell = new CsoPDFCell(this.mensajes.getMessage("factura.igic")+Constants.IGIC+"%", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_CENTER);
			cell.setColspan(2);
			cells.add(cell);
			borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
			cell = new CsoPDFCell(String.valueOf(fct.getImporte()), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_CENTER);
			cells.add(cell);
			cell = new CsoPDFCell(String.valueOf(dcf.format(((this.importSenseIva) / 100) * Constants.IGIC)), borders,
					PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_CENTER);
			cells.add(cell);
			
		}
		if(this.cso!=null && this.cso.getImpuesto()!=null){
			borders = new int[] { PDFCreatorImpl.BORDER_LEFT, PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
			cell = new CsoPDFCell(this.mensajes.getMessage("factura.impost")+this.cso.getImpuesto()+" %", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_CENTER);
			cell.setColspan(2);
			cells.add(cell);
			borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
			cell = new CsoPDFCell(String.valueOf(fct.getImporte()), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_CENTER);
			cells.add(cell);
			cell = new CsoPDFCell(String.valueOf(dcf.format(((this.importSenseIva) / 100) * this.cso.getImpuesto())), borders,
					PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_CENTER);
			cells.add(cell);
		}
		borders = new int[] { PDFCreatorImpl.NO_BORDER };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(4);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.NO_BORDER };

		cell = new CsoPDFCell(this.mensajes.getMessage("factura.imp.repercutido.total"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(3);
		cells.add(cell);
		
		cell = new CsoPDFCell(String.valueOf(dcf.format(((this.importSenseIva) / 100) * 21)), borders,
				PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.NO_BORDER };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(4);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_BOX };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.total"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(3);
		cells.add(cell);
		cell = new CsoPDFCell(String.valueOf(dcf.format(((this.importSenseIva) / 100) * 21 + fct.getImporte())),
				borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.NO_BORDER };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(4);
		cell.setRowspan(2);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_BOX };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.apagar"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_RIGHT);
		cell.setColspan(3);
		cells.add(cell);
		cell = new CsoPDFCell(String.valueOf(dcf.format(((fct.getImporte() - this.importSenseIva) / 100) * 21 + fct.getImporte())),
				borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_RIGHT);
		cells.add(cell);

		pDFCreatorImpl.createTable(cells, 4, 50f, 25f, PDFCreatorImpl.ALIGN_RIGHT);
	}

	private void creaTableFoot(){

		List<CsoPDFCell> cells = new ArrayList<CsoPDFCell>();

		int[] borders = new int[] { PDFCreatorImpl.NO_BORDER };

		CsoPDFCell cell = new CsoPDFCell(this.mensajes.getMessage("factura.datos.cobro"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cell.setColspan(5);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_TOP, PDFCreatorImpl.BORDER_LEFT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.fecha.vencimiento"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_TOP };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.imp"), borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.forma.pago"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.cuenta"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_TOP, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.codi.swift"), borders, PDFCreatorImpl.font08BOLD_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_LEFT };

		cell = new CsoPDFCell(getDateVencimiento(), borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM };
		cell = new CsoPDFCell(String.valueOf(dcf.format(((fct.getImporte() - this.importSenseIva) / 100) * 21 + fct.getImporte())),
				borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell(this.mensajes.getMessage("factura.forma.pago.value"), borders, PDFCreatorImpl.font08_Black,
				PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		cell = new CsoPDFCell("21008659110200016465", borders, PDFCreatorImpl.font08_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);
		borders = new int[] { PDFCreatorImpl.BORDER_BOTTOM, PDFCreatorImpl.BORDER_RIGHT };
		cell = new CsoPDFCell("", borders, PDFCreatorImpl.font08BOLD_Black, PDFCreatorImpl.ALIGN_LEFT);
		cells.add(cell);

		pDFCreatorImpl.createTable(cells, 5, 100f, 5f, PDFCreatorImpl.ALIGN_RIGHT);
	}

	private String getDateVencimiento(){

		Calendar calendar = Calendar.getInstance();
		calendar.set(fct.getYear(), (fct.getMonth()), 30);
		int lastDay = calendar.getMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(fct.getYear(), (fct.getMonth()), lastDay);
		return df.format(calendar.getTime());
	}

}
