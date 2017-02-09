package com.alpha.tpcsfashion.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import com.alpha.tpcsfashion.beans.ColumnPreference;

public class ExcelExporter {

public void addPictureToExcel(String imgUrl,HSSFWorkbook hwb,HSSFSheet sheet,HSSFPatriarch patriarch,int row,int col) throws IOException
{
try {
	InputStream inputStream = new FileInputStream(imgUrl);
	byte[] bytes = IOUtils.toByteArray(inputStream);
	int pictureIdx = hwb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
	inputStream.close();
	CreationHelper helper = ((Workbook) hwb).getCreationHelper();
	HSSFPatriarch drawing = sheet.createDrawingPatriarch();
	HSSFClientAnchor anchor = (HSSFClientAnchor) helper.createClientAnchor();
	anchor.setRow1(row);
	anchor.setCol1((short) col);
	HSSFPicture pict1 = drawing.createPicture(anchor, pictureIdx);
	pict1.resize();
}
catch(Exception e)
{
	e.printStackTrace();
	
}
}


public static void writeGroupTotal(ResourceBundle bundle ,HSSFWorkbook hwb,HSSFCellStyle dataStyle,  HSSFRow row ,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data){

	 HSSFCell cell;
	 //HSSFCellStyle dataNumberStyle=dataStyle;
	 //HSSFDataFormat cdf =hwb.createDataFormat();

	 int o=1;
	 
	 short fontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TotalRowFontSize"));
	 HSSFCellStyle colStyle=hwb.createCellStyle();//=dataNumberStyle;

	 HSSFFont font = hwb.createFont();

	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 colStyle.setFont(font);

	 DataFormat format = hwb.createDataFormat();
	 HSSFCellStyle numberQtyStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberQtyStyle.setDataFormat(format.getFormat(ExportToPdfTool.qtyFormat));
	 numberQtyStyle.setFont(font);
	 
	 HSSFCellStyle numberPriceStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberPriceStyle.setDataFormat(format.getFormat(ExportToPdfTool.priceFormat));
	 numberPriceStyle.setFont(font);
	 
	 HSSFCellStyle numberValStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberValStyle.setDataFormat(format.getFormat(ExportToPdfTool.valFormat));
	 numberValStyle.setFont(font);

	 HSSFCellStyle tmpStyle=hwb.createCellStyle();//=dataNumberStyle;
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 tmpStyle.setFont(font);

	 HSSFCellStyle tmp2Style = hwb.createCellStyle();
	 
	 int cellNo=0;
	 for(ColumnPreference cp:DataList.getCpList()){
			 
		 	ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
			if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
				ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
				tmp2Style=numberQtyStyle;
			}
			else{
				 tmp2Style=numberValStyle;
			}
			
		 if(quantityCol.contains(cp.getDbFieldName())){
			 for(String qCol:quantityCol){

				 if(cp.getDbFieldName().equals(qCol)){

					 cell=row.createCell(cellNo++);
					 cell.setCellValue(new Double(ExportToPdfTool.tempDf.format(totalColumnMap.get(cp.getDbFieldName()+m))));
					 cell.setCellStyle(tmp2Style);

					 break;
				 }
			 }
		 }
		 else{
			 if(o==m){
				 colStyle=ExportToExcelTool.setStyleColor(hwb,colStyle,totCol.get(m-1));
				 numberQtyStyle=ExportToExcelTool.setStyleColor(hwb,numberQtyStyle,totCol.get(m-1));
				 numberValStyle=ExportToExcelTool.setStyleColor(hwb,numberValStyle,totCol.get(m-1));

				 tmpStyle=colStyle;

				 cell=row.createCell(cellNo++);
				 cell.setCellValue(new HSSFRichTextString(data.get(i).get(DataList.getCpList().get(m-1).getDbFieldName())+" Total "));
				 cell.setCellStyle(tmpStyle);

			 }
			 else{
				 cell=row.createCell(cellNo++);
				 cell.setCellStyle(tmpStyle);
			 }
		 }
		 o++;
	 }
	 for(String qCol:quantityCol){
		 totalColumnMap.put(qCol+m, (double) 0.0f);
	 }
}

public static void writeGrandGroupTotal(ResourceBundle bundle ,HSSFWorkbook hwb,HSSFCellStyle dataStyle, HSSFRow row ,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> grandTotalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data){

	 HSSFCell cell;
	 //HSSFCellStyle dataNumberStyle=dataStyle;
	 //HSSFDataFormat cdf =hwb.createDataFormat();

	 int o=1;
	 short fontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TotalRowFontSize"));
	 HSSFCellStyle colStyle=hwb.createCellStyle();

	 HSSFFont font = hwb.createFont();

	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 colStyle.setFont(font);
	 colStyle=ExportToExcelTool.setStyleColor(hwb,colStyle,totCol.get(m+2));

	 DataFormat format = hwb.createDataFormat();
	 HSSFCellStyle numberQtyStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberQtyStyle.setDataFormat(format.getFormat(ExportToPdfTool.qtyFormat));
	 numberQtyStyle.setFont(font);
	 
	 HSSFCellStyle numberPriceStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberPriceStyle.setDataFormat(format.getFormat(ExportToPdfTool.priceFormat));
	 numberPriceStyle.setFont(font);
	 
	 HSSFCellStyle numberValStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberValStyle.setDataFormat(format.getFormat(ExportToPdfTool.valFormat));
	 numberValStyle.setFont(font);
	 
	 HSSFCellStyle tmp2Style = hwb.createCellStyle();

	 int cellNo=0;
	 for(ColumnPreference cp:DataList.getCpList()){
		 	
		 	ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
			if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
				ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
				tmp2Style=numberQtyStyle;
			}
			else{
				 tmp2Style=numberValStyle;
			}
		 if(quantityCol.contains(cp.getDbFieldName())){

			 for(String qCol:quantityCol){

				 if(cp.getDbFieldName().equals(qCol)){

					 cell=row.createCell(cellNo++);
					 cell.setCellValue(new Double(ExportToPdfTool.tempDf.format(grandTotalColumnMap.get(cp.getDbFieldName()))));
					 cell.setCellStyle(tmp2Style);

					 break;
				 }
			 }
		 }
		 else{

			 if(o==m){

				 numberQtyStyle=ExportToExcelTool.setStyleColor(hwb,numberQtyStyle,totCol.get(m+2));
				 numberValStyle=ExportToExcelTool.setStyleColor(hwb,numberValStyle,totCol.get(m+2));

				 cell=row.createCell(cellNo++);
				 cell.setCellValue(new HSSFRichTextString("Grand Total "));
				 cell.setCellStyle(colStyle);

			 }
			 else{
				 cell=row.createCell(cellNo++);
				 cell.setCellStyle(colStyle);
			 }
		 }
		 o++;
	 }
	 //		 for(String qCol:quantityCol){
	 //			 grandTotalColumnMap.put(qCol+m, 0f);
	 //		 }
}
public static void writeGroupCalTotal(ResourceBundle bundle ,HSSFWorkbook hwb,HSSFCellStyle dataStyle,  HSSFRow row ,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data){
//	 strBuffer.append("<tr class=\"rept-tot-row\">");
//	int o=1;
//	String color="";
	 
	 HSSFCell cell;
	 //HSSFCellStyle dataNumberStyle=dataStyle;
	 //HSSFDataFormat cdf =hwb.createDataFormat();

	 int o=1;
	 
	 short fontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TotalRowFontSize"));
	 HSSFCellStyle colStyle=hwb.createCellStyle();//=dataNumberStyle;

	 HSSFFont font = hwb.createFont();

	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 colStyle.setFont(font);


	 DataFormat format = hwb.createDataFormat();
	 HSSFCellStyle numberQtyStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberQtyStyle.setDataFormat(format.getFormat(ExportToPdfTool.qtyFormat));
	 numberQtyStyle.setFont(font);
	 
	 HSSFCellStyle numberPriceStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberPriceStyle.setDataFormat(format.getFormat(ExportToPdfTool.priceFormat));
	 numberPriceStyle.setFont(font);
	 
	 HSSFCellStyle numberValStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberValStyle.setDataFormat(format.getFormat(ExportToPdfTool.valFormat));
	 numberValStyle.setFont(font);

	 HSSFCellStyle tmpStyle=hwb.createCellStyle();//=dataNumberStyle;
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 tmpStyle.setFont(font);
	 
	 int cellNo=0;
	 HSSFCellStyle tmp2Style = hwb.createCellStyle();
	for(ColumnPreference cp:DataList.getCpList()){
		
	 	ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
		if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
			ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
			tmp2Style=numberQtyStyle;
		}
		else{
			 tmp2Style=numberValStyle;
		}
		
		
		if(quantityCol.contains(cp.getDbFieldName())){
			for(String qCol:quantityCol){
				if(cp.getDbFieldName().equals(qCol)){
//					strBuffer.append("<td "+color+" align=\"right\">"+df.format(Math.round(totalColumnMap.get(cp.getDbFieldName()+"_qty"+m)*100.00)/100.00)+"</td>");
					 cell=row.createCell(cellNo++);
					 cell.setCellValue(new Double(ExportToPdfTool.tempDf.format(totalColumnMap.get(cp.getDbFieldName()+"_qty"+m))));
					 cell.setCellStyle(tmp2Style);
					
					break;
				}
			}
		}
		else{
			if(o==m){
//				color="style=\"background-color:"+totCol.get(m-1)+";\"";
//				strBuffer.append("<td "+color+">"+data.get(i).get(DataList.getCpList().get(n-1).getDbFieldName())+" Total Qty:</td>");
				colStyle=ExportToExcelTool.setStyleColor(hwb,colStyle,totCol.get(m-1));
				 numberQtyStyle=ExportToExcelTool.setStyleColor(hwb,numberQtyStyle,totCol.get(m-1));
				 numberValStyle=ExportToExcelTool.setStyleColor(hwb,numberValStyle,totCol.get(m-1));

				 tmpStyle=colStyle;	

				 cell=row.createCell(cellNo++);
				 cell.setCellValue(new HSSFRichTextString(data.get(i).get(DataList.getCpList().get(m-1).getDbFieldName())+" Total Qty:"));
				 cell.setCellStyle(tmpStyle);
			}
			else{
//			strBuffer.append("<td "+color+" >&nbsp;</td>");
				 cell=row.createCell(cellNo++);
				 cell.setCellStyle(tmpStyle);
			}
		}
		o++;
	}
//	strBuffer.append("</tr>");
	for(String qCol:quantityCol){
		totalColumnMap.put(qCol+"_qty"+m, (double)0f);
	}
}



public static void writeGroupTotalCalVal(ResourceBundle bundle ,HSSFWorkbook hwb,HSSFCellStyle dataStyle,  HSSFRow row ,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data){
//	 strBuffer.append("<tr  class=\"rept-tot-row\">");
//	int o=1;
//	String color="";
	 
	 HSSFCell cell;
	 //HSSFCellStyle dataNumberStyle=dataStyle;
	 //HSSFDataFormat cdf =hwb.createDataFormat();

	 int o=1;
	 
	 short fontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TotalRowFontSize"));
	 HSSFCellStyle colStyle=hwb.createCellStyle();//=dataNumberStyle;

	 HSSFFont font = hwb.createFont();


	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 colStyle.setFont(font);

	 DataFormat format = hwb.createDataFormat();
	 HSSFCellStyle numberQtyStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberQtyStyle.setDataFormat(format.getFormat(ExportToPdfTool.qtyFormat));
	 numberQtyStyle.setFont(font);
	 
	 HSSFCellStyle numberPriceStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberPriceStyle.setDataFormat(format.getFormat(ExportToPdfTool.priceFormat));
	 numberPriceStyle.setFont(font);
	 
	 HSSFCellStyle numberValStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberValStyle.setDataFormat(format.getFormat(ExportToPdfTool.valFormat));
	 numberValStyle.setFont(font);

	 HSSFCellStyle tmpStyle=hwb.createCellStyle();//=dataNumberStyle;
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 tmpStyle.setFont(font);

	 HSSFCellStyle tmp2Style = hwb.createCellStyle();
	 

	 int cellNo=0;
	 
	for(ColumnPreference cp:DataList.getCpList()){
		ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
		if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
			ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
			tmp2Style=numberQtyStyle;
		}
		else{
			 tmp2Style=numberValStyle;
		}
		if(quantityCol.contains(cp.getDbFieldName())){
			for(String qCol:quantityCol){
				if(cp.getDbFieldName().equals(qCol)){
//					strBuffer.append("<td "+color+" align=\"right\">"+df.format(Math.round(totalColumnMap.get(cp.getDbFieldName()+"_val"+m)*100.00)/100.00)+"</td>");
					 cell=row.createCell(cellNo++);
					 
					 cell.setCellValue(new Double(ExportToPdfTool.tempDf.format(totalColumnMap.get(cp.getDbFieldName()+"_val"+m))));
					 cell.setCellStyle(tmp2Style);
					break;
				}
			}
		}
		else{
			if(o==m){
//				color="style=\"background-color:"+totCol.get(m-1)+";\"";
//				strBuffer.append("<td "+color+" align=\"right\">Total Value :</td>");
				colStyle=ExportToExcelTool.setStyleColor(hwb,colStyle,totCol.get(m-1));
				 numberQtyStyle=ExportToExcelTool.setStyleColor(hwb,numberQtyStyle,totCol.get(m-1));
				 numberValStyle=ExportToExcelTool.setStyleColor(hwb,numberValStyle,totCol.get(m-1));	

				 tmpStyle=colStyle;

				 cell=row.createCell(cellNo++);
				 cell.setCellValue(new HSSFRichTextString(data.get(i).get(DataList.getCpList().get(m-1).getDbFieldName())+" Total Value :"));
				 cell.setCellStyle(tmpStyle);
			}
			else{
//			strBuffer.append("<td "+color+" >&nbsp;</td>");
				 cell=row.createCell(cellNo++);
				 cell.setCellStyle(tmpStyle);
			}
		}
		o++;
	}
//	strBuffer.append("</tr>");
	for(String qCol:quantityCol){
		totalColumnMap.put(qCol+"_val"+m, (double)0f);
	}
}






public static void writeGroupGrandCalTotal(ResourceBundle bundle ,HSSFWorkbook hwb,HSSFCellStyle dataStyle,  HSSFRow row ,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data){
//	 strBuffer.append("<tr class=\"rept-tot-row\">");
//	int o=1;
//	String color="";
	 
	 HSSFCell cell;
	 //HSSFCellStyle dataNumberStyle=dataStyle;
	 //HSSFDataFormat cdf =hwb.createDataFormat();

	 int o=1;
	 
	 short fontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TotalRowFontSize"));
	 HSSFCellStyle colStyle=hwb.createCellStyle();//=dataNumberStyle;

	 HSSFFont font = hwb.createFont();

	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 colStyle.setFont(font);
	 colStyle=ExportToExcelTool.setStyleColor(hwb,colStyle,totCol.get(m+2));

	 DataFormat format = hwb.createDataFormat();
	 HSSFCellStyle numberQtyStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberQtyStyle.setDataFormat(format.getFormat(ExportToPdfTool.qtyFormat));
	 numberQtyStyle.setFont(font);
	 
	 HSSFCellStyle numberPriceStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberPriceStyle.setDataFormat(format.getFormat(ExportToPdfTool.priceFormat));
	 numberPriceStyle.setFont(font);
	 
	 HSSFCellStyle numberValStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberValStyle.setDataFormat(format.getFormat(ExportToPdfTool.valFormat));
	 numberValStyle.setFont(font);
	 
	 HSSFCellStyle tmp2Style = hwb.createCellStyle();

	 int cellNo=0;
	 
	for(ColumnPreference cp:DataList.getCpList()){
		
		ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
		if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
			ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
			tmp2Style=numberQtyStyle;
		}
		else{
			 tmp2Style=numberValStyle;
		}
		
		if(quantityCol.contains(cp.getDbFieldName())){
			for(String qCol:quantityCol){
				if(cp.getDbFieldName().equals(qCol)){
//					strBuffer.append("<td "+color+" align=\"right\">"+df.format(Math.round(totalColumnMap.get(cp.getDbFieldName()+"_qty"+m)*100.00)/100.00)+"</td>");
					 cell=row.createCell(cellNo++);
					 cell.setCellValue(new Double(ExportToPdfTool.tempDf.format(totalColumnMap.get(cp.getDbFieldName()+"_qty"))));
					 cell.setCellStyle(tmp2Style);
					break;
				}
			}
		}
		else{
			if(o==m){
//				color="style=\"background-color:"+totCol.get(m-1)+";\"";
//				strBuffer.append("<td "+color+">"+data.get(i).get(DataList.getCpList().get(n-1).getDbFieldName())+" Total Qty:</td>");
				
//				 numberColStyle=ExportToExcelTool.setStyleColor(hwb,numberColStyle,totCol.get(m-1));		

				
				 numberQtyStyle=ExportToExcelTool.setStyleColor(hwb,numberQtyStyle,totCol.get(m+2));
				 numberValStyle=ExportToExcelTool.setStyleColor(hwb,numberValStyle,totCol.get(m+2));

				 cell=row.createCell(cellNo++);
				 cell.setCellValue(new HSSFRichTextString("Grand Total Qty:"));
				 cell.setCellStyle(colStyle);
			}
			else{
//			strBuffer.append("<td "+color+" >&nbsp;</td>");
				 cell=row.createCell(cellNo++);
				 cell.setCellStyle(colStyle);
			}
		}
		o++;
	}
//	strBuffer.append("</tr>");
//	for(String qCol:quantityCol){
//		totalColumnMap.put(qCol+"_qty"+m, 0f);
//	}
}
public static void writeGroupGrandTotalCalVal(ResourceBundle bundle ,HSSFWorkbook hwb,HSSFCellStyle dataStyle,  HSSFRow row ,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data){
//	 strBuffer.append("<tr  class=\"rept-tot-row\">");
//	int o=1;
//	String color="";
	 
	 HSSFCell cell;
	 //HSSFCellStyle dataNumberStyle=dataStyle;
	 //HSSFDataFormat cdf =hwb.createDataFormat();

	 int o=1;
	 
	 short fontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TotalRowFontSize"));
	 HSSFCellStyle colStyle=hwb.createCellStyle();//=dataNumberStyle;

	 HSSFFont font = hwb.createFont();

	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 colStyle.setFont(font);
	 colStyle=ExportToExcelTool.setStyleColor(hwb,colStyle,totCol.get(m+2));

	 DataFormat format = hwb.createDataFormat();
	 HSSFCellStyle numberQtyStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberQtyStyle.setDataFormat(format.getFormat(ExportToPdfTool.qtyFormat));
	 numberQtyStyle.setFont(font);
	 
	 HSSFCellStyle numberPriceStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberPriceStyle.setDataFormat(format.getFormat(ExportToPdfTool.priceFormat));
	 numberPriceStyle.setFont(font);
	 
	 HSSFCellStyle numberValStyle=hwb.createCellStyle();
	 font.setFontHeightInPoints((short)fontSize);
	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
	 numberValStyle.setDataFormat(format.getFormat(ExportToPdfTool.valFormat));
	 numberValStyle.setFont(font);
	 
	 HSSFCellStyle tmp2Style = hwb.createCellStyle();
	 
//	 HSSFCellStyle numberColStyle=hwb.createCellStyle();
//	 font.setFontHeightInPoints((short)fontSize);
//	 font.setFontName(bundle.getString("ExportToExcel.FontName"));
//	 numberColStyle.setFont(font);

	 int cellNo=0;
	 
	for(ColumnPreference cp:DataList.getCpList()){
		
		ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
		if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
			ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
			tmp2Style=numberQtyStyle;
		}
		else{
			 tmp2Style=numberValStyle;
		}
		
		if(quantityCol.contains(cp.getDbFieldName())){
			for(String qCol:quantityCol){
				if(cp.getDbFieldName().equals(qCol)){
//					strBuffer.append("<td "+color+" align=\"right\">"+df.format(Math.round(totalColumnMap.get(cp.getDbFieldName()+"_val"+m)*100.00)/100.00)+"</td>");
					 cell=row.createCell(cellNo++);
					 cell.setCellValue(new Double(ExportToPdfTool.tempDf.format(totalColumnMap.get(cp.getDbFieldName()+"_val"))));
					 cell.setCellStyle(tmp2Style);
					break;
				}
			}
		}
		else{
			if(o==m){
//				color="style=\"background-color:"+totCol.get(m-1)+";\"";
//				strBuffer.append("<td "+color+" align=\"right\">Total Value :</td>");
				
//				 numberColStyle=ExportToExcelTool.setStyleColor(hwb,numberColStyle,totCol.get(m-1));		

				 numberQtyStyle=ExportToExcelTool.setStyleColor(hwb,numberQtyStyle,totCol.get(m+2));
				 numberValStyle=ExportToExcelTool.setStyleColor(hwb,numberValStyle,totCol.get(m+2));
				 
				 cell=row.createCell(cellNo++);
				 cell.setCellValue(new HSSFRichTextString("Grand Total Value :"));
				 cell.setCellStyle(colStyle);
			}
			else{
//			strBuffer.append("<td "+color+" >&nbsp;</td>");
				 cell=row.createCell(cellNo++);
				 cell.setCellStyle(colStyle);
			}
		}
		o++;
	}
//	strBuffer.append("</tr>");
//	for(String qCol:quantityCol){
//		totalColumnMap.put(qCol+"_val"+m, 0f);
//	}
}

}
