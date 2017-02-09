package com.alpha.tpcsfashion.util;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperReportPrinter {
	
 public  byte[] readJasperReport(String jasperUrl,Connection con,Map params) throws Exception  //here pass the jrxml url,connection,parameteters to be sent to jasper report
 {
     JasperDesign jasperDesign = JRXmlLoader.load(jasperUrl);
	  JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	  ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
	  
	  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,con);
	  JasperExportManager.exportReportToPdfStream(jasperPrint, byteOutStream);
	  byteOutStream.flush();
	  byte buffer[]=byteOutStream.toByteArray();
	  return buffer;
 }
 public  void printJasperReport(HttpServletRequest request,HttpServletResponse response,TPCSUser userInfo,String reportPath, Map params,String screenName) throws Exception  //here pass the jrxml url,connection,parameteters to be sent to jasper report
 {
	 Connection con = new DatabaseConnection().getConnection(userInfo);
	 try
		{
	 String jrxmlUrl=request.getSession().getServletContext().getRealPath("/report/"+reportPath);
	 
     JasperDesign jasperDesign = JRXmlLoader.load(jrxmlUrl);
	  JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	  ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
	  
	  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,con);
	  JasperExportManager.exportReportToPdfStream(jasperPrint, byteOutStream);
	  byteOutStream.flush();
	  byte buffer[]=byteOutStream.toByteArray();
	  
//	  response.setContentLength(buffer.length);
//	    ServletOutputStream servletOS=response.getOutputStream();
//	    servletOS.write(buffer);
//	    servletOS.flush();
//	    servletOS.close();
	  
	  
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\""+screenName+" "+ new java.util.Date()+".pdf" + "\"");
		response.setContentLength(buffer.length);
		response.getOutputStream().write(buffer);
		response.getOutputStream().flush();
		}
	 	
		catch (Exception e) {
		e.printStackTrace();
		}
	 	finally{
	 		DatabaseConnection.connectionClose(con);
		 
	 	}
 }
	
}
