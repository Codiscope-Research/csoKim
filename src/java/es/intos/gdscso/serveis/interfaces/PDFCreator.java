package es.intos.gdscso.serveis.interfaces;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import es.intos.gdscso.exceptions.CsoPDFException;
import es.intos.gdscso.serveis.pojos.CsoPDFCell;

public abstract class PDFCreator{

	public static final int			ALIGN_LEFT			= 0;

	public static final int			ALIGN_CENTER		= 1;

	public static final int			ALIGN_RIGHT			= 2;

	public static final int			ALIGN_JUSTIFIED		= 3;

	public static final int			ALIGN_TOP			= 4;

	public static final int			ALIGN_MIDDLE		= 5;

	public static final int			ALIGN_BOTTOM		= 6;

	public static final int			NO_BORDER			= 0;

	public static final int			BORDER_LEFT			= 1;

	public static final int			BORDER_RIGHT		= 2;

	public static final int			BORDER_TOP			= 3;

	public static final int			BORDER_BOTTOM		= 4;

	public static final int			BORDER_BOX			= 5;

	public static final Font		font08BOLD_Black	= new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(Color.BLACK));

	public static final Font		font08_Black		= new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(Color.BLACK));

	public static final Font		font10BOLD_Black	= new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(Color.BLACK));

	public static final Font		font10_Black		= new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(Color.BLACK));

	public static final Font		font12BOLD_Black	= new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(Color.BLACK));

	public static final Font		font12_Black		= new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(Color.BLACK));

	public static final Font		font14BOLD_Black	= new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(Color.BLACK));

	public static final Font		font14_Black		= new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL, new BaseColor(Color.BLACK));

	public ByteArrayOutputStream	baos				= new ByteArrayOutputStream();
	public PdfWriter				writer				= null;
	public Document					document			= null;

	public abstract void initPDF() throws CsoPDFException;

	public abstract ByteArrayOutputStream closePDF() throws CsoPDFException;

	protected abstract PdfPCell createCell( String info, Font font, int[] borders, int align, int colspan, int rowspan );

	public abstract void createTable( List<CsoPDFCell> cells, int numCols, Float withpercentatge, Float spacingAfter, int alignTable );
	
	public abstract void setMarca(String ruta) throws MalformedURLException, IOException, DocumentException;
	

	public PdfPCell setBorders( PdfPCell cell, int[] borders ){

		cell.setBorder(Rectangle.BOX);
		boolean removeTOP = true;
		boolean removeBOTTOM = true;
		boolean removeLEFT = true;
		boolean removeRIGHT = true;
		for (int border : borders) {
			if (border == PDFCreator.NO_BORDER) {
				cell.setBorder(Rectangle.NO_BORDER);
				break;
			} else if (border == PDFCreator.BORDER_LEFT) {
				removeLEFT = false;
			} else if (border == PDFCreator.BORDER_RIGHT) {
				removeRIGHT = false;
			} else if (border == PDFCreator.BORDER_TOP) {
				removeTOP = false;
			} else if (border == PDFCreator.BORDER_BOTTOM) {
				removeBOTTOM = false;
			} else if (border == PDFCreator.BORDER_BOX) {
				removeLEFT = false;
				removeTOP = false;
				removeRIGHT = false;
				removeBOTTOM = false;
				break;
			}
		}

		if (removeLEFT)
			cell.setBorderWidthLeft(0);

		if (removeBOTTOM)
			cell.setBorderWidthBottom(0);

		if (removeRIGHT)
			cell.setBorderWidthRight(0);

		if (removeTOP)
			cell.setBorderWidthTop(0);

		return cell;
	}

}
