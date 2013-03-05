package es.intos.gdscso.serveis.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import es.intos.gdscso.exceptions.CsoPDFException;
import es.intos.gdscso.serveis.interfaces.PDFCreator;
import es.intos.gdscso.serveis.pojos.CsoPDFCell;

public class PDFCreatorImpl extends PDFCreator{

	public ByteArrayOutputStream closePDF() throws CsoPDFException{

		try {
			PdfAction action = PdfAction.gotoLocalPage(1, new PdfDestination(PdfDestination.XYZ, -1, -1, 1.1f), writer);
			writer.setOpenAction(action);

			document.close();
			return baos;
		} catch (Exception e) {
			throw new CsoPDFException("Error closing PDF: " + e.getMessage());
		}
	}

	public void initPDF(){

		document = new Document(); // crea un A4 per defecte
									// vertical. Si passes un
									// PageSize.A4_LANDSCAPE.rotate(),
									// crea un A4 amb orientacio
									// horitzontal
		try {
			writer = PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			throw new CsoPDFException("Error en writter: " + e.getMessage());
		}
		document.open();
		
	}

	public void setMarca(String ruta) throws MalformedURLException, IOException, DocumentException{
				           

          Image watermark_image = Image.getInstance(ruta);

          watermark_image.setAbsolutePosition(0, 0);
          
          document.add(watermark_image);

	}
	
	protected PdfPCell createCell( String info, Font font, int[] borders, int align, int colspan, int rowspan ){

		PdfPCell cell = new PdfPCell();
		Paragraph par = new Paragraph();
		par.setFont(font);
		par.setAlignment(align);
		par.setSpacingAfter(2f);
		par.add(info);
		if (colspan != 0)
			cell.setColspan(colspan);
		if (rowspan != 0)
			cell.setRowspan(rowspan);
		cell.addElement(par);

		cell = setBorders(cell, borders);
		return cell;
	}

	public void createTable( List<CsoPDFCell> cells, int numCols, Float withpercentatge, Float spacingAfter, int alignTable ){

		try {
			PdfPTable table = new PdfPTable(numCols);
			if (alignTable != 0)
				table.setHorizontalAlignment(alignTable);
			table.setSpacingAfter(spacingAfter);
			table.setWidthPercentage(withpercentatge);

			for (CsoPDFCell csoCell : cells) {

				table.addCell(createCell(csoCell.getInfo(), csoCell.getFont(), csoCell.getBorders(), csoCell.getAlign(),
						csoCell.getColspan(), csoCell.getRowspan()));
			}

			document.add(table);
		} catch (Exception e) {
			throw new CsoPDFException("Error creating table: " + e.getMessage());
		}
	}

}
