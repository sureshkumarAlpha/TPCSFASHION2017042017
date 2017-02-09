package com.alpha.tpcsfashion.beans;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfCell {
	private PdfPTable table;
	private String cellVal;
	private int hAlign;
	private int vAlign;
	private int rowSpan;
	private int colSpan;
	private Font font;
	private int border1;
	private int border2;
	private int border3;
	private float borderWidth;
	private int cellHeight;
	
	public PdfPTable getTable() {
		return table;
	}
	public void setTable(PdfPTable table) {
		this.table = table;
	}
	public String getCellVal() {
		return cellVal;
	}
	public void setCellVal(String cellVal) {
		this.cellVal = cellVal;
	}
	public int gethAlign() {
		return hAlign;
	}
	public void sethAlign(int hAlign) {
		this.hAlign = hAlign;
	}
	public int getvAlign() {
		return vAlign;
	}
	public void setvAlign(int vAlign) {
		this.vAlign = vAlign;
	}
	public int getRowSpan() {
		return rowSpan;
	}
	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}
	public int getColSpan() {
		return colSpan;
	}
	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	
	public int getCellHeight() {
		return cellHeight;
	}
	public void setCellHeight(int cellHeight) {
		this.cellHeight = cellHeight;
	}
	public float getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}
	public int getBorder1() {
		return border1;
	}
	public void setBorder1(int border1) {
		this.border1 = border1;
	}
	public int getBorder2() {
		return border2;
	}
	public void setBorder2(int border2) {
		this.border2 = border2;
	}
	public int getBorder3() {
		return border3;
	}
	public void setBorder3(int border3) {
		this.border3 = border3;
	}

	
}
