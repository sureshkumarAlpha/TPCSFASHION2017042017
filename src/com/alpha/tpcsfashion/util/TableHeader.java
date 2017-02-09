package com.alpha.tpcsfashion.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class TableHeader extends PdfPageEventHelper {
    /** The header text. */
    String header;
    /** The template with the total number of pages. */
    PdfTemplate total;
    BaseColor baseColor=new BaseColor(0,0,0);
	Font addFont= FontFactory.getFont("Calibri",8,Font.ITALIC, baseColor);
    /**
     * Allows us to change the content of the header.
     * @param header The new header String
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Creates the PdfTemplate that will hold the total number of pages.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }

    /**
     * Adds a header to every page
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(2);
        try {
        	
        	
           	PdfContentByte canvas = writer.getDirectContent();
//        	Rectangle layout = new Rectangle(36, 48, 559, 806); //559
//        	Rectangle layout = new Rectangle(30, 40, 565, 800); //559
        	
//        	Rectangle layout = new Rectangle(30, 40, 565, 800); //559
        	
           	
//           	System.out.println("ExportToPdfTool.pageLeft :"+ExportToPdfTool.pageLeft);
//           	System.out.println("ExportToPdfTool.pageRight :"+ExportToPdfTool.pageRight);
//           	System.out.println("ExportToPdfTool.pageBottom :"+ExportToPdfTool.pageBottom);
//           	System.out.println("ExportToPdfTool.pageTop :"+ExportToPdfTool.pageTop);
           	
        	Rectangle layout = new Rectangle(ExportToPdfTool.pageLeft, ExportToPdfTool.pageRight, ExportToPdfTool.pageBottom, ExportToPdfTool.pageTop); //559
        	
        	
//    	    layout.setBackgroundColor(new BaseColor(100, 200, 180)); //Background color
    	    layout.setBorderColor(BaseColor.BLACK);  //Border color
    	    layout.setBorderWidth(0.5f);      //Border width
    	    layout.setBorder(ExportToPdfTool.borderLeft | ExportToPdfTool.borderRight  | ExportToPdfTool.borderTop | ExportToPdfTool.borderBottom);  //Border on 4 sides
    	    canvas.rectangle(layout);
    	 
    	    
            table.setWidths(new int[]{50,50});
            table.setTotalWidth(527);
            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(20);
            table.getDefaultCell().setBorder(Rectangle.TOP);
//            table.addCell(header);
//            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell cell = new PdfPCell(new Phrase(String.format("Page %d of", writer.getPageNumber()),addFont));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
           
            cell = new PdfPCell(Image.getInstance(total));
//            cell.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            table.writeSelectedRows(0, -1, 34, 40, writer.getDirectContent());
            
//            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Page " + document.getPageNumber(),addFont), 300, 25, 0);
            
        }
        catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    /**
     * Fills out the total number of pages before the document is closed.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_RIGHT,new Phrase(String.valueOf(writer.getPageNumber() - 1),addFont),4, 6, 0);
    }
}