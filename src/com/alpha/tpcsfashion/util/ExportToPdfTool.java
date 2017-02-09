package com.alpha.tpcsfashion.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.PdfCell;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class ExportToPdfTool {
	
public static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

		
		//create a new cell with the specified Text and Font
		PdfPCell cell=null;
	
		if(text!=null){
			cell = new PdfPCell(new Phrase(text.trim(), font));
		}else{
			cell = new PdfPCell(new Phrase("", font));
		}

		//set the cell alignment
		cell.setHorizontalAlignment(align);
		//set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		//in case there is no text and you wan to create an empty row

		cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);

		cell.setMinimumHeight(17);
		
		//cell.setBorder(Rectangle.LEFT|Rectangle.RIGHT|Rectangle.TOP|Rectangle.BOTTOM);
		/*	  if(text.trim().equalsIgnoreCase("")){
			   cell.setMinimumHeight(10f);
			  }*/
		//add the call to the table
		 cell.setBorderWidth(0.5f); 
		table.addCell(cell);
		
		cell=null;

	}
	public static void insertNoBorderCell(PdfPTable table, String text, int align, int colspan, Font font){

		
		//create a new cell with the specified Text and Font
		PdfPCell cell=null;
	
		if(text!=null){
			cell = new PdfPCell(new Phrase(text.trim(), font));
		}else{
			cell = new PdfPCell(new Phrase("", font));
		}

		//set the cell alignment
		cell.setHorizontalAlignment(align);
		//set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		//in case there is no text and you wan to create an empty row

		cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);

		cell.setMinimumHeight(15);
		/*	  if(text.trim().equalsIgnoreCase("")){
			   cell.setMinimumHeight(10f);
			  }*/
		//add the call to the table
		cell.setBorder(Rectangle.NO_BORDER);
		
		table.addCell(cell);
		cell=null;

	}
	
public static void insertBorderCell(PdfPTable table, String text, int align, int colspan, Font font,int borderAlign1,int borderAlign2,int borderAlign3){

		
		//create a new cell with the specified Text and Font
		PdfPCell cell=null;
		 
		if(text!=null){
			cell = new PdfPCell(new Phrase(text.trim(), font));
		}else{
			cell = new PdfPCell(new Phrase("", font));
		}

		//set the cell alignment
		cell.setHorizontalAlignment(align);
		//set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		//in case there is no text and you wan to create an empty row

		cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);

		cell.setMinimumHeight(17);
		/*	  if(text.trim().equalsIgnoreCase("")){
			   cell.setMinimumHeight(10f);
			  }*/
		//add the call to the table
		//cell.setBorder(borderAlign1);
//		if(borderAlign2>0 || borderAlign3>0)
//		{
			cell.setBorder(borderAlign1|borderAlign2|borderAlign3);
//		}
//		 cell.setBorderWidth(1f); 
		table.addCell(cell);
		cell=null;

	}

public static void insertBorderCellAlign(PdfPTable table, String text, int align,int Valign, int colspan, Font font,int borderAlign1,int borderAlign2,int borderAlign3){

	
	//create a new cell with the specified Text and Font
	PdfPCell cell=null;

	if(text!=null){
		cell = new PdfPCell(new Phrase(text.trim(), font));
	}else{
		cell = new PdfPCell(new Phrase("", font));
	}

	//set the cell alignment
	cell.setHorizontalAlignment(align);
	//set the cell column span in case you want to merge two or more cells
	cell.setColspan(colspan);
	//in case there is no text and you wan to create an empty row

	cell.setVerticalAlignment (Valign);

	cell.setMinimumHeight(17);
	/*	  if(text.trim().equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }*/
	//add the call to the table
	//cell.setBorder(borderAlign1);
//	if(borderAlign2>0 || borderAlign3>0)
//	{
		cell.setBorder(borderAlign1|borderAlign2|borderAlign3);
//	}
	 cell.setBorderWidth(1f); 
	table.addCell(cell);
	cell=null;

}

public static void insertBorderCellHight(PdfPTable table, String text, int align, int rowSpan,int colspan, Font font,int borderAlign1,int borderAlign2,int borderAlign3){

	
	//create a new cell with the specified Text and Font
	PdfPCell cell=null;

	if(text!=null){
		cell = new PdfPCell(new Phrase(text.trim(), font));
	}else{
		cell = new PdfPCell(new Phrase("", font));
	}

	//set the cell alignment
	cell.setHorizontalAlignment(align);
	//set the cell column span in case you want to merge two or more cells
	cell.setRowspan(rowSpan);
	cell.setColspan(colspan);
	//in case there is no text and you wan to create an empty row

	cell.setVerticalAlignment (Element.ALIGN_BOTTOM);

	cell.setMinimumHeight(34);
	/*	  if(text.trim().equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }*/
	//add the call to the table
	//cell.setBorder(borderAlign1);
//	if(borderAlign2>0 || borderAlign3>0)
//	{
		cell.setBorder(borderAlign1|borderAlign2|borderAlign3);
//	}
	 cell.setBorderWidth(1f); 
	table.addCell(cell);
	cell=null;

}

public static void insertCell(PdfCell pdfCell){

	
	PdfPCell cell=null;
	cell = new PdfPCell(new Phrase(pdfCell.getCellVal()!=null?pdfCell.getCellVal().trim():"", pdfCell.getFont()));
//	if(pdfCell.gethAlign()>0){
		cell.setHorizontalAlignment(pdfCell.gethAlign());
//	}
//	if(pdfCell.getvAlign()>0){
		cell.setVerticalAlignment (pdfCell.getvAlign());
//	}
//	if(pdfCell.getRowSpan()>0){
		cell.setRowspan(pdfCell.getRowSpan());
//	}
//	if(pdfCell.getColSpan()>0){
		cell.setColspan(pdfCell.getColSpan());
//	}
//	if(pdfCell.getCellHeight()>0){
		cell.setMinimumHeight(pdfCell.getCellHeight());
//	}
	cell.setBorder(pdfCell.getBorder1()|pdfCell.getBorder2()|pdfCell.getBorder3());
	cell.setBorderWidth(pdfCell.getBorderWidth()); 
	cell.setNoWrap(false);
	pdfCell.getTable().addCell(cell);
	cell=null;

}

public static void insertCellWithRowSpan(PdfPTable table, String text, int align, int colspan,int rowspan, Font font,int borderAlign1,int borderAlign2){

		
		//create a new cell with the specified Text and Font
		PdfPCell cell=null;
	
		if(text!=null){
			cell = new PdfPCell(new Phrase(text.trim(), font));
		}else{
			cell = new PdfPCell(new Phrase("", font));
		}

		//set the cell alignment
		cell.setHorizontalAlignment(align);
		//set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);
		//in case there is no text and you wan to create an empty row

		cell.setVerticalAlignment (Element.ALIGN_TOP	);

		cell.setMinimumHeight(15);
		/*	  if(text.trim().equalsIgnoreCase("")){
			   cell.setMinimumHeight(10f);
			  }*/
		//add the call to the table
		cell.setBorder(borderAlign1);
		if(borderAlign2>0)
		{
			cell.setBorder(borderAlign1|borderAlign2);
		}
		 cell.setBorderWidth(1f); 
       
		table.addCell(cell);
		cell=null;

	}
	public static void insertTotCell(PdfPTable table, String text, int align, int colspan, Font font,String tot){

		//create a new cell with the specified Text and Font
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		//set the cell alignment
		cell.setHorizontalAlignment(align);
		//set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		//in case there is no text and you wan to create an empty row

		cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
		cell.setMinimumHeight(15);
		if(text.trim().equalsIgnoreCase("")){
			cell.setMinimumHeight(10f);
		}
		if(tot.trim().equalsIgnoreCase("Total")){
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		}
		else if(tot.trim().equalsIgnoreCase("Sub total")){
			cell.setBackgroundColor(BaseColor.CYAN);
		}
		else if(tot.trim().equalsIgnoreCase("Total amount")){
			cell.setBackgroundColor(BaseColor.ORANGE);
		}
		//add the call to the table
		table.addCell(cell);
		cell=null;

	}
	
	
	public static void insertPDFTotColorCell(PdfPTable table, String text, int align, int colspan, Font font,BaseColor objCol){

			//cell.setBackgroundColor(BaseColor.ORANGE);
		
		PdfPCell cell=null;
	
		if(text!=null){
			cell = new PdfPCell(new Phrase(text.trim(), font));
		}else{
			cell = new PdfPCell(new Phrase("", font));
		}

		cell.setBackgroundColor(objCol);
		
		//set the cell alignment
		cell.setHorizontalAlignment(align);
		//set the cell column span in case you want to merge two or more cells
		cell.setColspan(colspan);
		//in case there is no text and you wan to create an empty row

		cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);

		cell.setMinimumHeight(17);
		/*	  if(text.trim().equalsIgnoreCase("")){
			   cell.setMinimumHeight(10f);
			  }*/
		//add the call to the table
		cell.setBorderWidth(1f); 
		table.addCell(cell);
		cell=null;

	}
	public static void writePdfFooter(TPCSUser userInfo,ResourceBundle bundle,PdfPTable footTable,PdfPCell cell,String currencyName,String headerRemarks,String paymentTerms,int creditDays, Font detTableDataFont,float grandTot) throws DocumentException{
		long num = (long) grandTot;

		EnglishNumberToWordsIndianRupees objEngNum = new EnglishNumberToWordsIndianRupees();
		String grandTotInWords = objEngNum.convert(num);
		
		String fileName=String.valueOf(grandTot);
	 	String extension = "0";
		int i = fileName.lastIndexOf('.');
		extension=i>0?fileName.substring(i+1):"0";
		int WholeFractNum=Validator.convertToInteger(extension);
		String fractWords="";
		if(WholeFractNum>0){
			fractWords=bundle.getString("ExportToPdf.And")+objEngNum.convert(WholeFractNum)+bundle.getString("ExportToPdf.Paise");
		}
					
			if(grandTot>0)
			{
				cell = new PdfPCell(new Paragraph("( "+currencyName+" "+grandTotInWords+" "+fractWords+")", detTableDataFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(6);
				cell.setMinimumHeight(22);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
				cell.setBorderWidth(1f); 
				footTable.addCell(cell);
				cell=null;
			}
		
		
			
			//-----------------------
			
			if((paymentTerms!=null && !paymentTerms.isEmpty()) || creditDays>0 ) 
			{
				ExportToPdfTool.insertCell(footTable,"Payment Terms :"+paymentTerms, Element.ALIGN_LEFT, 4, detTableDataFont);
				 ExportToPdfTool.insertCell(footTable, "Credit Days :", Element.ALIGN_LEFT, 1, detTableDataFont);
				 ExportToPdfTool.insertCell(footTable, String.valueOf(creditDays), Element.ALIGN_RIGHT, 1, detTableDataFont);
			}
			 
			 
			 
		//Footer Autorized Area

//			document.add(detailTable);
//			
//			PdfPTable footTable=new PdfPTable(6);
//			footTable.setWidthPercentage(100);
//			System.out.println("Table total height:"+detailTable.getTotalHeight());
//			
//			if(detailTable.getTotalHeight()<660){
//				cell = new PdfPCell(new Paragraph("", detTableDataFont));
//				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell.setColspan(6);
//				cell.setMinimumHeight(660-detailTable.getTotalHeight());
//				cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
//				cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT );
//				cell.setBorderWidth(1f); 
//				footTable.addCell(cell);
//			}
			
//				cell = new PdfPCell(new Paragraph(headerRemarks, detTableDataFont));
				cell = new PdfPCell(new Paragraph("", detTableDataFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setColspan(3);
				cell.setMinimumHeight(22);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
				cell.setBorder(Rectangle.LEFT );
				cell.setBorderWidth(1f); 
				footTable.addCell(cell);
				cell=null;
				
				cell = new PdfPCell(new Paragraph("For "+userInfo.getCompanyName(), detTableDataFont));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setColspan(3);
				cell.setMinimumHeight(22);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
				cell.setBorder(Rectangle.RIGHT);
				cell.setBorderWidth(1f); 
				footTable.addCell(cell);
				cell=null;
				
				//for space
				
				cell = new PdfPCell(new Paragraph("", detTableDataFont));
				cell.setColspan(6);
				cell.setMinimumHeight(22);
				cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT); 
				cell.setBorderWidth(1f); 
				footTable.addCell(cell);
				cell=null;
				
				cell = new PdfPCell(new Paragraph(bundle.getString("Common.PreparedBy"), detTableDataFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setColspan(3);
				cell.setMinimumHeight(22);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
				cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
				cell.setBorderWidth(1f); 
				footTable.addCell(cell);
				cell=null;
				
				
				cell = new PdfPCell(new Paragraph(bundle.getString("Common.AutorisedSignatory"), detTableDataFont));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setColspan(3);
				cell.setMinimumHeight(22);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
				cell.setBorder( Rectangle.RIGHT| Rectangle.BOTTOM);
				cell.setBorderWidth(1f); 
				footTable.addCell(cell);
				cell=null;
				
//				document.add(footTable);
				
				
		//Footer area

		/*Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm aa");
		String dateNow = formatter.format(currentDate.getTime());
		
		cell = new PdfPCell(new Paragraph(bundle.getString("ExportToPdf.Date")+dateNow, detTableDataFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(3);
		cell.setMinimumHeight(22);
		cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		detailTable.addCell(cell);
		
		cell = new PdfPCell(new Paragraph(bundle.getString("Common.ReportGenerated"), detTableDataFont));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setColspan(3);
		cell.setMinimumHeight(22);
		cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		detailTable.addCell(cell);*/
	}
	
	
	public static void writePdfFooterInReports(TPCSUser userInfo,ResourceBundle bundle,PdfPTable detailTable,PdfPCell cell, Font detTableDataFont,int noOfColumn){
		
		
		//Footer area

		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm aa");
		String dateNow = formatter.format(currentDate.getTime());
		
		
		
		cell = new PdfPCell(new Paragraph(bundle.getString("ExportToPdf.Date")+dateNow, detTableDataFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan((int)Math.floor(noOfColumn/2));
		cell.setMinimumHeight(22);
		cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		detailTable.addCell(cell);
		cell=null;
		
		//cell = new PdfPCell(new Paragraph(bundle.getString("ExportToPdf.ReportGenBy") + userInfo.getDisplayUserName(), detTableDataFont));
		cell = new PdfPCell(new Paragraph(bundle.getString("Common.ReportGenerated"), detTableDataFont));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setColspan((int)Math.ceil(noOfColumn/2)+1);
		cell.setMinimumHeight(22);
		cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		detailTable.addCell(cell);
		cell=null;
	}
	
	public static BaseColor hex2Rgb(String colorStr) {
	    return new BaseColor(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
	
	public static void setRoundOff(TPCSUser userInfo){
		int valueRoundOff=2;
		int priceRoundOff=2;
		int qtyRoundOff=2;
//		if(userInfo.getLocatonConfigMap()!=null){
//			valueRoundOff=userInfo.getLocatonConfigMap().get("RoundoffValue")!=null?Validator.convertToInteger(userInfo.getLocatonConfigMap().get("RoundoffValue")):2;
//			priceRoundOff=userInfo.getLocatonConfigMap().get("RoundoffRate")!=null?Validator.convertToInteger(userInfo.getLocatonConfigMap().get("RoundoffRate")):2;
//			qtyRoundOff=userInfo.getLocatonConfigMap().get("RoundoffQty")!=null?Validator.convertToInteger(userInfo.getLocatonConfigMap().get("RoundoffQty")):2;
//		}
		valDbRoundoff=Math.pow(10.00, valueRoundOff);
		priceDbRoundoff=Math.pow(10.00, priceRoundOff);
		qtyDbRoundoff=Math.pow(10.00, priceRoundOff);

		//System.out.println("INR"+userInfo.getCurrencyName().equalsIgnoreCase("INR"));
		String sepStr="#,##,###";
//		if(!userInfo.getCurrencyName().equalsIgnoreCase("INR")){
//			sepStr="###,###";
//		}
		
		String sepFractStr="#,##,#";
//		if(!userInfo.getCurrencyName().equalsIgnoreCase("INR")){
//			sepFractStr="###,#";
//		}

		StringBuffer buf=new StringBuffer("#0.00");
		StringBuffer emptyValbuf=new StringBuffer("0.0");
//		for(int i=2;i<=valueRoundOff;i++){
//			buf.append("0");
//			emptyValbuf.append("0");
//		}
		valSepFormat=sepStr;
		valSepFractFormat=sepFractStr+buf.toString();
		valFormat=buf.toString();
		valEmptyFormat=emptyValbuf.toString();
		valDf = new DecimalFormat(buf.toString());
		valSepFracDf = new DecimalFormat(sepFractStr+buf.toString());
		
		buf=null;
		buf=new StringBuffer("#0.00");
//		for(int i=2;i<=priceRoundOff;i++){
//			buf.append("0");
//		}
		priceSepFormat=sepStr+buf.toString();
		priceFormat=buf.toString();
		priceDf = new DecimalFormat(buf.toString()); 
		priceSepFracDf = new DecimalFormat(sepFractStr+buf.toString());

		buf=null;
		buf=new StringBuffer("#0");
//		for(int i=2;i<=qtyRoundOff;i++){
//			buf.append("0");
//		}
		qtySepFormat=sepStr+buf.toString();
		qtyFormat=buf.toString();
		qtyDf = new DecimalFormat(buf.toString()); 
		qtySepFracDf= new DecimalFormat(sepFractStr+buf.toString()); 
		buf=null;
	}
	 
	 
	
	//Left Bottom Top Right
	public static void setRectangleMargin(float left,float right,float top,float bottom,int brdrLft,int brdrRigt,int brdrTp,int brdrBttm){
		 pageLeft=left;
		 pageRight=right;
		 pageTop=top;
		 pageBottom=bottom;
		 
		 borderLeft=brdrLft;
		 borderRight=brdrRigt;
		 borderBottom=brdrTp;
		 borderTop=brdrBttm;
	}
	
	public static void writeGroupTotalPDF(ResourceBundle bundle ,PdfPTable table, ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data,Font detTableDataFont){

		PdfPCell cell=null;
		int o=1;

		BaseColor objColor=ExportToPdfTool.hex2Rgb("#FFFFFF");
		for(ColumnPreference cp:DataList.getCpList()){
			ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
			if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
				ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
			}
			else if(cp.getDbFieldName().equalsIgnoreCase("price_fcy")){
				ExportToPdfTool.tempDf=ExportToPdfTool.priceDf;
			}
			
			if(quantityCol.contains(cp.getDbFieldName())){

				for(String qCol:quantityCol){

					if(cp.getDbFieldName().equals(qCol)){
						ExportToPdfTool.insertPDFTotColorCell(table, tempDf.format(totalColumnMap.get(cp.getDbFieldName()+m)), Element.ALIGN_LEFT, 1, detTableDataFont,objColor);


						break;
					}
				}
			}
			else{

				if(o==m){

					objColor=ExportToPdfTool.hex2Rgb(totCol.get(m-1));
					ExportToPdfTool.insertPDFTotColorCell(table, data.get(i).get(DataList.getCpList().get(m-1).getDbFieldName())+" Total ", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);


				}
				else{
					ExportToPdfTool.insertPDFTotColorCell(table, "", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);
				}
			}
			o++;
		}
		for(String qCol:quantityCol){
			totalColumnMap.put(qCol+m, (double)0.00f);
		}
	}


	public static void writeGroupTotalQtyPDF(ResourceBundle bundle ,PdfPTable table, ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data,Font detTableDataFont){

		PdfPCell cell=null;
		int o=1;

		BaseColor objColor=ExportToPdfTool.hex2Rgb("#FFFFFF");
		for(ColumnPreference cp:DataList.getCpList()){
			
				ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
			
			if(quantityCol.contains(cp.getDbFieldName())){

				for(String qCol:quantityCol){

					if(cp.getDbFieldName().equals(qCol)){
						ExportToPdfTool.insertPDFTotColorCell(table, tempDf.format(totalColumnMap.get(cp.getDbFieldName()+"_qty"+m)), Element.ALIGN_RIGHT, 1, detTableDataFont,objColor);

						break;
					}
				}
			}
			else{

				if(o==m){

					objColor=ExportToPdfTool.hex2Rgb(totCol.get(m-1));
					ExportToPdfTool.insertPDFTotColorCell(table, data.get(i).get(DataList.getCpList().get(m-1).getDbFieldName())+" Total Qty ", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);


				}
				else{
					ExportToPdfTool.insertPDFTotColorCell(table, "", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);
				}
			}
			o++;
		}
		for(String qCol:quantityCol){
			totalColumnMap.put(qCol+"_qty"+m, (double)0f);
		}
	}


	public static void writeGroupTotalValPDF(ResourceBundle bundle ,PdfPTable table,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data,Font detTableDataFont){

		PdfPCell cell=null;

		int o=1;

		BaseColor objColor=ExportToPdfTool.hex2Rgb("#FFFFFF");
		for(ColumnPreference cp:DataList.getCpList()){
			ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
 			if(quantityCol.contains(cp.getDbFieldName())){
				for(String qCol:quantityCol){
					if(cp.getDbFieldName().equals(qCol)){

						ExportToPdfTool.insertPDFTotColorCell(table, tempDf.format(totalColumnMap.get(cp.getDbFieldName()+"_val"+m)), Element.ALIGN_RIGHT, 1, detTableDataFont,objColor);

						break;
					}
				}
			}
			else{
				if(o==m){

					objColor=ExportToPdfTool.hex2Rgb(totCol.get(m-1));
					ExportToPdfTool.insertPDFTotColorCell(table, data.get(i).get(DataList.getCpList().get(m-1).getDbFieldName())+" Total Value ", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);

				}
				else{
					ExportToPdfTool.insertPDFTotColorCell(table, "", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);
				}
			}
			o++;
		}
		for(String qCol:quantityCol){
			totalColumnMap.put(qCol+"_val"+m, (double)0f);
		}
	}
	public static void writeGrandGroupTotalPDF(ResourceBundle bundle ,PdfPTable table,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> grandTotalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data,Font detTableDataFont){

		PdfPCell cell=null;

		int o=1;
		BaseColor objColor=ExportToPdfTool.hex2Rgb(totCol.get(m+2));
		for(ColumnPreference cp:DataList.getCpList()){
				ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
			if(quantityCol.contains(cp.getDbFieldName())){

				for(String qCol:quantityCol){

					if(cp.getDbFieldName().equals(qCol)){

						ExportToPdfTool.insertPDFTotColorCell(table, tempDf.format(grandTotalColumnMap.get(cp.getDbFieldName())), Element.ALIGN_RIGHT, 1, detTableDataFont,objColor);

						break;
					}
				}
			}
			else{

				if(o==m){

					ExportToPdfTool.insertPDFTotColorCell(table, "Grand Total ", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);

				}
				else{
					ExportToPdfTool.insertPDFTotColorCell(table, "", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);
				}
			}
			o++;
		}
	}

	public static void writeGrandGroupTotalQtyPDF(ResourceBundle bundle ,PdfPTable table,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data,Font detTableDataFont){

		PdfPCell cell=null;

		int o=1;


		BaseColor objColor=ExportToPdfTool.hex2Rgb(totCol.get(m+2));

		for(ColumnPreference cp:DataList.getCpList()){
				ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
			if(quantityCol.contains(cp.getDbFieldName())){
				for(String qCol:quantityCol){
					if(cp.getDbFieldName().equals(qCol)){

						ExportToPdfTool.insertPDFTotColorCell(table, ExportToPdfTool.tempDf.format(Math.round(totalColumnMap.get(cp.getDbFieldName()+"_qty")*valDbRoundoff)/valDbRoundoff), Element.ALIGN_RIGHT, 1, detTableDataFont,objColor);

						break;
					}
				}
			}
			else{
				if(o==m){
					ExportToPdfTool.insertPDFTotColorCell(table, "Grand Total Qty ", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);
				}
				else{
					ExportToPdfTool.insertPDFTotColorCell(table, "", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);
				}
			}
			o++;
		}
	}
	public static void writeGroupGrandTotalValPDF(ResourceBundle bundle ,PdfPTable table,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,DecimalFormat df,int m,int i,List<Map<String,String>> data,Font detTableDataFont){

		PdfPCell cell=null;

		int o=1;


		BaseColor objColor=ExportToPdfTool.hex2Rgb(totCol.get(m+2));

		for(ColumnPreference cp:DataList.getCpList()){
			ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
			if(quantityCol.contains(cp.getDbFieldName())){
				for(String qCol:quantityCol){
					if(cp.getDbFieldName().equals(qCol)){

						ExportToPdfTool.insertPDFTotColorCell(table, ExportToPdfTool.tempDf.format(Math.round(totalColumnMap.get(cp.getDbFieldName()+"_val")*valDbRoundoff)/valDbRoundoff), Element.ALIGN_RIGHT, 1, detTableDataFont,objColor);

						break;
					}
				}
			}
			else{
				if(o==m){
					ExportToPdfTool.insertPDFTotColorCell(table, "Grand Total Value ", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);
				}
				else{
					ExportToPdfTool.insertPDFTotColorCell(table, "", Element.ALIGN_LEFT, 1, detTableDataFont,objColor);
				}
			}
			o++;
		}
	}
	public String getNumberInWords(String custCurrencyName,ResourceBundle bundle,Double grandTot){
		
		 String gT=valDf.format(Math.round(grandTot*valDbRoundoff)/valDbRoundoff);
		 int i = gT.lastIndexOf('.');
		 if(i!=-1){
			 gT=gT.substring(0,(i));
		 }
		 long num = (long) new Long(gT);
		 
		 EnglishNumberToWords objEngNum = new EnglishNumberToWords();
		 
		 String grandTotInWords = objEngNum.convertNotINR(num);
		 
		 String fractWords="";
		 if(custCurrencyName!=null && custCurrencyName.equalsIgnoreCase("INR")){
			 
			 DecimalFormat valDf = new DecimalFormat("#0.00");
			 
			 grandTotInWords = objEngNum.convertINR(num);

			 String fileName=String.valueOf(valDf.format(Math.round(grandTot*100.00)/100.00));
			 String extension = "0";
			 i = fileName.lastIndexOf('.');
			 extension=i>0?fileName.substring(i+1):"0";
			 int WholeFractNum=Validator.convertToInteger(extension);
		
			 if(WholeFractNum>0){
				 fractWords=bundle.getString("ExportToPdf.And")+objEngNum.convertINR(WholeFractNum)+bundle.getString("ExportToPdf.Paise");
			 }
		 }
		 
		 return grandTotInWords+" "+fractWords;
	}
	
	
	
	
	public static float pageLeft=30;
	public static float pageRight=40;
	public static float pageBottom=565;
	public static float pageTop=800;
	
	public static int borderLeft=0;
	public static int borderRight=0;
	public static int borderBottom=Rectangle.BOTTOM;
	public static int borderTop=0;
	
	public static int valueRoundOff;
	public static int priceRoundOff;
	public static int qtyRoundOff;
	
	public static String valFormat;
	public static String priceFormat;
	public static String qtyFormat;
	
	public static String valSepFractFormat;
	
	public static String valSepFormat;
	public static String priceSepFormat;
	public static String qtySepFormat;
	
	public static String valEmptyFormat;
	public static DecimalFormat valDf;//= new DecimalFormat("#.0");
	public static DecimalFormat priceDf;//= new DecimalFormat("#.0");
	public static DecimalFormat qtyDf;//= new DecimalFormat("#.0");
	public static DecimalFormat tempDf;
	public static DecimalFormat valSepFracDf;
	public static DecimalFormat qtySepFracDf;
	public static DecimalFormat priceSepFracDf;

	public static Double valDbRoundoff;//=Math.pow(10.00, 2);
	public static Double priceDbRoundoff;//=Math.pow(10.00, 2);
	public static Double qtyDbRoundoff;//=Math.pow(10.00, 2);
	
	 
 
	 
}
