package es.intos.gdscso.serveis.pojos;

import java.awt.Color;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public class CsoPDFCell{

	private String	info	= "";
	private int[]	borders	= new int[] { 0 };
	private Font	font	= new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(Color.BLACK));
	private int		align	= 0;
	private int		colspan	= 0;
	private int		rowspan	= 0;

	// CONSTRUCTORS

	public CsoPDFCell() {

	}

	public CsoPDFCell( String info, int[] borders, Font font, int align ) {

		super();
		this.info = info;
		this.borders = borders;
		this.font = font;
		this.align = align;
	}

	// GETTERS i SETTERS
	public String getInfo(){

		return info;
	}

	public void setInfo( String info ){

		this.info = info;
	}

	public int[] getBorders(){

		return borders;
	}

	public void setBorder( int[] borders ){

		this.borders = borders;
	}

	public Font getFont(){

		return font;
	}

	public void setFont( Font font ){

		this.font = font;
	}

	public int getAlign(){

		return align;
	}

	public void setAlign( int align ){

		this.align = align;
	}

	public int getColspan(){

		return colspan;
	}

	public void setColspan( int colspan ){

		this.colspan = colspan;
	}

	public int getRowspan(){

		return rowspan;
	}

	public void setRowspan( int rowspan ){

		this.rowspan = rowspan;
	}

}
