package com.alpha.tpcsfashion.util;

import java.awt.Color;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExportToExcelTool {
	public static HSSFCellStyle getStyleBoldFont(HSSFWorkbook hwb,short heightPoints,short fontType,short borderType,short alignType,String fontName) {
       	HSSFFont boldFont = hwb.createFont();
       	boldFont.setFontName(fontName);
       	
	    boldFont.setFontHeightInPoints((short)heightPoints);
	    boldFont.setBoldweight(fontType); //Setting Bold font
	    
	    HSSFCellStyle boldStyle = hwb.createCellStyle();
	    boldStyle.setFont(boldFont); //Attaching the font to the Style
	    boldStyle.setBorderBottom(borderType);
	    boldStyle.setBorderTop(borderType);
	    boldStyle.setBorderRight(borderType);
	    boldStyle.setBorderLeft(borderType);
	    boldStyle.setAlignment(alignType);
	    
		
		return boldStyle;
	}
	public static HSSFCellStyle getValBorderStyle(HSSFWorkbook hwb, short s,short boldweightBold, short borderThin, String perFormat,String fontName) {
		HSSFCellStyle valStyle = hwb.createCellStyle();
		HSSFFont font= hwb.createFont();
		font.setFontName(fontName);
		valStyle.setFont(font);
		valStyle.setDataFormat(hwb.createDataFormat().getFormat(perFormat));
		valStyle.setBorderBottom(borderThin);
		valStyle.setBorderTop(borderThin);
		valStyle.setBorderRight(borderThin);
		valStyle.setBorderLeft(borderThin);
		return valStyle;
	}
	public static HSSFCellStyle getPerBorderStyle(HSSFWorkbook hwb, short s,short boldweightBold, short borderThin, String perFormat,String fontName) {

		HSSFCellStyle perStyle = hwb.createCellStyle();
		HSSFFont font= hwb.createFont();
		font.setFontName(fontName);
		perStyle.setFont(font);
		perStyle.setDataFormat(hwb.createDataFormat().getFormat(perFormat));
		perStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		perStyle.setBorderBottom(borderThin);
		perStyle.setBorderTop(borderThin);
		perStyle.setBorderRight(borderThin);
		perStyle.setBorderLeft(borderThin);
		return perStyle;
	}
	
	public static HSSFCellStyle getPerDifBorderStyle(HSSFWorkbook hwb, short s,short boldweightBold, short borderTop,short borderRt, short borderBtm,short borderLft, String perFormat,String fontName) {

		HSSFCellStyle perStyle = hwb.createCellStyle();
		HSSFFont font= hwb.createFont();
		font.setFontName(fontName);
		perStyle.setFont(font);
		perStyle.setDataFormat(hwb.createDataFormat().getFormat(perFormat));
		perStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		perStyle.setBorderTop(borderTop);
		perStyle.setBorderRight(borderRt);
		perStyle.setBorderBottom(borderBtm);
		perStyle.setBorderLeft(borderLft);
		return perStyle;
	}

	
	public static HSSFCellStyle getBorderStyle(HSSFWorkbook hwb, short s,short boldweightBold, short borderThin,String fontName) {
	    HSSFCellStyle borderStyle=hwb.createCellStyle();
	    HSSFFont font= hwb.createFont();
		font.setFontName(fontName);
		borderStyle.setFont(font);
		borderStyle.setBorderBottom(borderThin);
		borderStyle.setBorderTop(borderThin);
		borderStyle.setBorderRight(borderThin);
		borderStyle.setBorderLeft(borderThin);
		return borderStyle;
	}
	public static HSSFCellStyle getBorderStyle(HSSFWorkbook hwb, short s,short boldweightBold,short borderTop,short borderRt, short borderBtm,short borderLft,String fontName) {
	    HSSFCellStyle borderStyle=hwb.createCellStyle();
	    HSSFFont font= hwb.createFont();
		font.setFontName(fontName);
		borderStyle.setFont(font);
	    borderStyle.setBorderTop(borderTop);
	    borderStyle.setBorderRight(borderRt);
	    borderStyle.setBorderBottom(borderBtm);
		borderStyle.setBorderLeft(borderLft);
		return borderStyle;
	}

	public static HSSFCellStyle getBorderRightStyle(HSSFWorkbook hwb, short s,short boldweightBold, short borderThin, short align,String fontName) {
	    HSSFCellStyle borderRStyle=hwb.createCellStyle();
	    HSSFFont font= hwb.createFont();
		font.setFontName(fontName);
		borderRStyle.setFont(font);
	    borderRStyle.setAlignment(align);
		borderRStyle.setBorderBottom(borderThin);
		borderRStyle.setBorderTop(borderThin);
		borderRStyle.setBorderRight(borderThin);
		borderRStyle.setBorderLeft(borderThin);
		return borderRStyle;
	}
	public static HSSFCellStyle getBorderCompanyNameStyle(HSSFWorkbook hwb ,String fontName,short fontSize) {
//		short fontSize=12;
		short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
		return style;
	}
	
	public static HSSFCellStyle getBorderAddressStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
		
//		short fontSize=10;
		short fontWeight=HSSFFont.BOLDWEIGHT_NORMAL;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
		return style;
	}
	

public static HSSFCellStyle getBorderFilterStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
		
	
//	short fontSize=10;
	short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
	short border=HSSFCellStyle.BORDER_NONE;
	
	HSSFFont font = hwb.createFont();
    font.setFontHeightInPoints((short)fontSize);
    font.setFontName(fontName);
    font.setBoldweight(fontWeight);
	HSSFCellStyle style = hwb.createCellStyle();
    style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    style.setFont(font);
    style.setBorderBottom(border);
    style.setBorderTop(border);
    style.setBorderRight(border);
    style.setBorderLeft(border);
	return style;
	
	}
	
public static HSSFCellStyle getBorderFilterValStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {

//	short fontSize=10;
	short fontWeight=HSSFFont.BOLDWEIGHT_NORMAL;
	short border=HSSFCellStyle.BORDER_NONE;
	
	HSSFFont font = hwb.createFont();
    font.setFontHeightInPoints((short)fontSize);
    font.setFontName(fontName);
    font.setBoldweight(fontWeight);
	HSSFCellStyle style = hwb.createCellStyle();
    style.setFont(font);
    style.setBorderBottom(border);
    style.setBorderTop(border);
    style.setBorderRight(border);
    style.setBorderLeft(border);
	return style;

	}

	
	public static HSSFCellStyle getBorderTitleStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
		
//		short fontSize=12;
		short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
		return style;
	}
	
	public static HSSFCellStyle getBorderDataStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
		
//		short fontSize=10;
		short fontWeight=HSSFFont.BOLDWEIGHT_NORMAL;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
//	    style.setWrapText(true);
		return style;
	}
	
public static HSSFCellStyle getBorderDataBoldStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
		
//		short fontSize=10;
		short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
//	    style.setWrapText(true);
		return style;
	}


public static HSSFCellStyle getBorderTopDataBoldStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
	
//	short fontSize=10;
	short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
	short border=HSSFCellStyle.BORDER_NONE;
	
	HSSFFont font = hwb.createFont();
    font.setFontHeightInPoints((short)fontSize);
    font.setFontName(fontName);
    font.setBoldweight(fontWeight);
	HSSFCellStyle style = hwb.createCellStyle();
    style.setFont(font);
    style.setBorderBottom(border);
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style.setBorderRight(border);
    style.setBorderLeft(border);
//    style.setWrapText(true);
	return style;
}


public static HSSFCellStyle getBorderTopBottomDataBoldStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
	
//	short fontSize=10;
	short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
	short border=HSSFCellStyle.BORDER_NONE;
	
	HSSFFont font = hwb.createFont();
    font.setFontHeightInPoints((short)fontSize);
    font.setFontName(fontName);
    font.setBoldweight(fontWeight);
	HSSFCellStyle style = hwb.createCellStyle();
    style.setFont(font);
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style.setBorderRight(border);
    style.setBorderLeft(border);
//    style.setWrapText(true);
	return style;
}

	public static HSSFCellStyle getBorderDataWrapStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
		
//		short fontSize=10;
		short fontWeight=HSSFFont.BOLDWEIGHT_NORMAL;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
	    style.setWrapText(true);
		return style;
	}
	
public static HSSFCellStyle getBorderDataNumberStyle(HSSFWorkbook hwb,  String fontName,short fontSize ) {
		
//		short fontSize=10;
		short fontWeight=HSSFFont.BOLDWEIGHT_NORMAL;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
		HSSFDataFormat cdf =hwb.createDataFormat();
		
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
//		style.setDataFormat(hwb.createDataFormat().getFormat(perFormat));
		 style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
//	    style.setDataFormat(cdf.getFormat("#,##0.00"));
		return style;
	}
	
	
	public static HSSFCellStyle getBorderHeaderStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
		
//		short fontSize=10;
		short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
		return style;
	}
	
	
public static HSSFCellStyle getDataBoldStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
		
//		short fontSize=10;
		short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
	    style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
		return style;
	}

	public static HSSFCellStyle getDataBoldNumStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
	//short fontSize=10;
	short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
	short border=HSSFCellStyle.BORDER_NONE;
	
	HSSFFont font = hwb.createFont();
	font.setFontHeightInPoints((short)fontSize);
	font.setFontName(fontName);
	font.setBoldweight(fontWeight);
	HSSFCellStyle style = hwb.createCellStyle();
	style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	style.setFont(font);
	style.setBorderBottom(border);
	style.setBorderTop(border);
	style.setBorderRight(border);
	style.setBorderLeft(border);
	return style;
	}

	/*
public static HSSFCellStyle getBorderGroupStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
		
//		short fontSize=10;
		short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
		short border=HSSFCellStyle.BORDER_NONE;
		
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
		HSSFCellStyle style = hwb.createCellStyle();
	    style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
		return style;
	}

	
public static HSSFCellStyle getBorderGroupDataStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
//	short fontSize=10;
	short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
	short border=HSSFCellStyle.BORDER_NONE;
	
	HSSFFont font = hwb.createFont();
    font.setFontHeightInPoints((short)fontSize);
    font.setFontName(fontName);
    font.setBoldweight(fontWeight);
	HSSFCellStyle style = hwb.createCellStyle();
    style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
    style.setFont(font);
    style.setBorderBottom(border);
    style.setBorderTop(border);
    style.setBorderRight(border);
    style.setBorderLeft(border);
	return style;
}


public static HSSFCellStyle getBorderAccountDataStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
	
//	short fontSize=10;
	short fontWeight=HSSFFont.BOLDWEIGHT_NORMAL;
	short border=HSSFCellStyle.BORDER_NONE;
	
	HSSFFont font = hwb.createFont();
    font.setFontHeightInPoints((short)fontSize);
    font.setFontName(fontName);
    font.setBoldweight(fontWeight);
	HSSFCellStyle style = hwb.createCellStyle();
    style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
    style.setFont(font);
    style.setBorderBottom(border);
    style.setBorderTop(border);
    style.setBorderRight(border);
    style.setBorderLeft(border);
	return style;
}*/


/*public static HSSFCellStyle getBorderDataValStyle(HSSFWorkbook hwb,  String fontName,short fontSize) {
	
//	short fontSize=10;
	short fontWeight=HSSFFont.BOLDWEIGHT_NORMAL;
	short border=HSSFCellStyle.BORDER_NONE;
	
	HSSFFont font = hwb.createFont();
    font.setFontHeightInPoints((short)fontSize);
    font.setFontName(fontName);
    font.setBoldweight(fontWeight);
	HSSFCellStyle style = hwb.createCellStyle();
    style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
    style.setFont(font);
    style.setBorderBottom(border);
    style.setBorderTop(border);
    style.setBorderRight(border);
    style.setBorderLeft(border);
	return style;
}*/



	public static HSSFCellStyle setStyleColor(HSSFWorkbook hwb,HSSFCellStyle style,String colorHexCode){
		HSSFPalette palette = hwb.getCustomPalette();
		String color=hex2Rgb(colorHexCode);
		String arC[]=color.split(",");
		
		HSSFColor myColor = palette.findSimilarColor(Validator.convertToInteger(arC[0]),Validator.convertToInteger(arC[1]),Validator.convertToInteger(arC[2]));
		short palIndex = myColor.getIndex();
		style.setFillForegroundColor((short)palIndex);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return style;
	}
	
	public static HSSFCellStyle getBorderDataColorStyle(HSSFWorkbook hwb,String fontName,short fontSize,String colorHexCode) {
    	HSSFCellStyle style=hwb.createCellStyle();
    	try{
    		
		short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
		short border=HSSFCellStyle.BORDER_NONE;
    		
    	HSSFPalette palette = hwb.getCustomPalette();
		String color=hex2Rgb(colorHexCode);
		String arC[]=color.split(",");
		
		HSSFColor myColor = palette.findSimilarColor(Validator.convertToInteger(arC[0]),Validator.convertToInteger(arC[1]),Validator.convertToInteger(arC[2]));
		short palIndex = myColor.getIndex();
		
		style.setFillForegroundColor(palIndex);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFFont font = hwb.createFont();
		font.setFontHeightInPoints((short)fontSize);
		font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
	    
		style.setFont(font);
		style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
		return style;
	}
	
	public static HSSFCellStyle getBorderColorDataNumStyle(HSSFWorkbook hwb,  String fontName,short fontSize,String colorHexCode) {
		HSSFCellStyle style=hwb.createCellStyle();
		try{
//		short fontSize=10;
		short fontWeight=HSSFFont.BOLDWEIGHT_BOLD;
		short border=HSSFCellStyle.BORDER_NONE;
		
	 	  HSSFPalette palette = hwb.getCustomPalette();
			String color=hex2Rgb(colorHexCode);
			String arC[]=color.split(",");
			
			HSSFColor myColor = palette.findSimilarColor(Validator.convertToInteger(arC[0]),Validator.convertToInteger(arC[1]),Validator.convertToInteger(arC[2]));
			short palIndex = myColor.getIndex();
			
			style.setFillForegroundColor(palIndex);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
		HSSFFont font = hwb.createFont();
	    font.setFontHeightInPoints((short)fontSize);
	    font.setFontName(fontName);
	    font.setBoldweight(fontWeight);
	    style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	    style.setFont(font);
	    style.setBorderBottom(border);
	    style.setBorderTop(border);
	    style.setBorderRight(border);
	    style.setBorderLeft(border);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return style;
}

	
	public static HSSFCellStyle getBorderRightBoldStyle(HSSFWorkbook hwb,
			short s, short boldweightBold, short borderThin,String fontName) {
		HSSFFont boldFont = hwb.createFont();
		boldFont.setFontName(fontName);
	    boldFont.setFontHeightInPoints((short)s);
	    boldFont.setBoldweight(boldweightBold);
		HSSFCellStyle boldStyle = hwb.createCellStyle();
	    boldStyle.setFont(boldFont);
	    boldStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	    boldStyle.setBorderBottom(borderThin);
	    boldStyle.setBorderTop(borderThin);
	    boldStyle.setBorderRight(borderThin);
	    boldStyle.setBorderLeft(borderThin);
		return boldStyle;

	}
	public static HSSFCellStyle getPerBorderBoldStyle(HSSFWorkbook hwb,short s, short boldweightBold, short borderThin, String perFormat,String fontName) {
		HSSFFont boldFont = hwb.createFont();
		boldFont.setFontName(fontName);
	    boldFont.setFontHeightInPoints((short)s);
	    boldFont.setBoldweight(boldweightBold);
	    
		HSSFCellStyle perStyle = hwb.createCellStyle();
		perStyle.setFont(boldFont);
		perStyle.setDataFormat(hwb.createDataFormat().getFormat(perFormat));
		perStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		perStyle.setBorderBottom(borderThin);
		perStyle.setBorderTop(borderThin);
		perStyle.setBorderRight(borderThin);
		perStyle.setBorderLeft(borderThin);
		return perStyle;
	}
	public static HSSFCellStyle getPerDifBorderBoldStyle(HSSFWorkbook hwb,short s, short boldweightBold,short borderTop,short borderRt, short borderBtm,short borderLft, String perFormat,String fontName) {
		HSSFFont boldFont = hwb.createFont();
		boldFont.setFontName(fontName);
	    boldFont.setFontHeightInPoints((short)s);
	    boldFont.setBoldweight(boldweightBold);
	    
		HSSFCellStyle perStyle = hwb.createCellStyle();
		perStyle.setFont(boldFont);
		perStyle.setDataFormat(hwb.createDataFormat().getFormat(perFormat));
		perStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		perStyle.setBorderTop(borderTop);
		perStyle.setBorderRight(borderRt);
		perStyle.setBorderBottom(borderBtm);
		perStyle.setBorderLeft(borderLft);
		return perStyle;
	}
	public static String hex2Rgb(String colorStr) {
		/**
		* 
		* @param colorStr e.g. "#FFFFFF"
		* @return String - formatted "rgb(0,0,0)"
		*/

	    Color c = new Color(
	    		Integer.valueOf(colorStr.substring(1, 3), 16), 
	    		Integer.valueOf(colorStr.substring(3, 5), 16), 
	    		Integer.valueOf(colorStr.substring(5, 7), 16)
	    		);

	    StringBuffer sb = new StringBuffer();
	    sb.append(c.getRed());
	    sb.append(",");
	    sb.append(c.getGreen());
	    sb.append(",");
	    sb.append(c.getBlue());
	    return sb.toString();
	}
	public static HSSFCellStyle getColorStyle(HSSFWorkbook hwb,HSSFFont font ,String fontName,String colorHexCode) {
    	HSSFCellStyle style=hwb.createCellStyle();
    	try{
    	HSSFPalette palette = hwb.getCustomPalette();
		String color=hex2Rgb(colorHexCode);
		String arC[]=color.split(",");
		
		HSSFColor myColor = palette.findSimilarColor(Validator.convertToInteger(arC[0]),Validator.convertToInteger(arC[1]),Validator.convertToInteger(arC[2]));
		short palIndex = myColor.getIndex();
		
		style.setFillForegroundColor(palIndex);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		font= hwb.createFont();
		font.setFontName(fontName);
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
		return style;
	}
}
