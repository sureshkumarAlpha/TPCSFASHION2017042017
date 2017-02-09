package com.alpha.tpcsfashion.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.ColumnPreference;

public class ReportGrid {

	
	 
	 public static void writeGroupTotal(StringBuffer strBuffer,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,int m,int n,int i,List<Map<String,String>> data){
		 strBuffer.append("<tr class=\"rept-tot-row\">");
 		int o=1;
 		String color="";
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
     					strBuffer.append("<td "+color+" align=\"right\">"+ExportToPdfTool.tempDf.format(totalColumnMap.get(cp.getDbFieldName()+m))+"</td>");
     					break;
     				}
     			}
     		}
     		else{
     			if(o==m){
     				color="style=\"background-color:"+totCol.get(m-1)+";\"";
     				strBuffer.append("<td "+color+" >"+data.get(i).get(DataList.getCpList().get(n-1).getDbFieldName())+" Total </td>");	
     			}
     			else{
     			strBuffer.append("<td "+color+" >&nbsp;</td>");
     			}
     		}
     		o++;
     	}
     	strBuffer.append("</tr>");
     	for(String qCol:quantityCol){
     		totalColumnMap.put(qCol+m, (double) 0.0f);
     	}
	 }
	 
	 public static void writeGroupTotalVal(StringBuffer strBuffer,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,int m,int n,int i,List<Map<String,String>> data){
		 strBuffer.append("<tr  class=\"rept-tot-row\">");
 		int o=1;
 		String color="";
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
     					strBuffer.append("<td "+color+" align=\"right\">"+ExportToPdfTool.tempDf.format(totalColumnMap.get(cp.getDbFieldName()+m))+"</td>");
     					break;
     				}
     			}
     		}
     		else{
     			if(o==m){
     				color="style=\"background-color:"+totCol.get(m-1)+";\"";
     				strBuffer.append("<td "+color+" align=\"right\">Total Value :</td>");	
     			}
     			else{
     			strBuffer.append("<td "+color+" >&nbsp;</td>");
     			}
     		}
     		o++;
     	}
     	strBuffer.append("</tr>");
     	for(String qCol:quantityCol){
     		totalColumnMap.put(qCol+"_val"+m, (double) 0f);
     	}
	 }
	 
	 public static void writeGroupCalTotal(StringBuffer strBuffer,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,int m,int n,int i,List<Map<String,String>> data){
		 strBuffer.append("<tr class=\"rept-tot-row\">");
 		int o=1;
 		String color="";
     	for(ColumnPreference cp:DataList.getCpList()){
     		
     		ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
//			if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
//				ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
//			}
//			else if(cp.getDbFieldName().equalsIgnoreCase("price_fcy")){
//				ExportToPdfTool.tempDf=ExportToPdfTool.priceDf;
//			}
     		if(quantityCol.contains(cp.getDbFieldName())){
     			for(String qCol:quantityCol){
     				if(cp.getDbFieldName().equals(qCol)){
     					strBuffer.append("<td "+color+" align=\"right\">"+ExportToPdfTool.tempDf.format(totalColumnMap.get(cp.getDbFieldName()+"_qty"+m))+"</td>");
     					break;
     				}
     			}
     		}
     		else{
     			if(o==m){
     				color="style=\"background-color:"+totCol.get(m-1)+";\"";
     				strBuffer.append("<td "+color+">"+data.get(i).get(DataList.getCpList().get(n-1).getDbFieldName())+" Total Qty:</td>");	
     			}
     			else{
     			strBuffer.append("<td "+color+" >&nbsp;</td>");
     			}
     		}
     		o++;
     	}
     	strBuffer.append("</tr>");
     	for(String qCol:quantityCol){
     		totalColumnMap.put(qCol+"_qty"+m, (double) 0.0f);
     	}
	 }
	 public static void writeGroupTotalCalVal(StringBuffer strBuffer,ColumnPreference DataList,List<String> quantityCol,Map<String,Double> totalColumnMap,List<String> totCol,int m,int n,int i,List<Map<String,String>> data){
		 strBuffer.append("<tr  class=\"rept-tot-row\">");
 		int o=1;
 		String color="";
     	for(ColumnPreference cp:DataList.getCpList()){
     		
     		ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
//			if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
//				ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
//			}
//			else if(cp.getDbFieldName().equalsIgnoreCase("price_fcy")){
//				ExportToPdfTool.tempDf=ExportToPdfTool.priceDf;
//			}
			
     		if(quantityCol.contains(cp.getDbFieldName())){
     			for(String qCol:quantityCol){
     				if(cp.getDbFieldName().equals(qCol)){
     					strBuffer.append("<td "+color+" align=\"right\">"+ExportToPdfTool.tempDf.format(totalColumnMap.get(cp.getDbFieldName()+"_val"+m))+"</td>");
     					break;
     				}
     			}
     		}
     		else{
     			if(o==m){
     				color="style=\"background-color:"+totCol.get(m-1)+";\"";
     				strBuffer.append("<td "+color+" >Total Value :</td>");	
     			}
     			else{
     			strBuffer.append("<td "+color+" >&nbsp;</td>");
     			}
     		}
     		o++;
     	}
     	strBuffer.append("</tr>");
     	for(String qCol:quantityCol){
     		totalColumnMap.put(qCol+"_val"+m, (double) 0f);
     	}
	 }
	 public void writeGroupAvg(StringBuffer strBuffer,ColumnPreference DataList,List<String> avgCol,Map<String,Float> avgColumnMap,Map<String,Integer> avgColumnCountMap,List<String> totCol,DecimalFormat df,int m,int n,int i,List<Map<String,String>> data){
		 strBuffer.append("<tr>");
 		int o=1;
 		String color="";
     	for(ColumnPreference cp:DataList.getCpList()){
     	
     		ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
			if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
				ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
			}
			else if(cp.getDbFieldName().equalsIgnoreCase("price_fcy")){
				ExportToPdfTool.tempDf=ExportToPdfTool.priceDf;
			}

			
     		if(avgCol.contains(cp.getDbFieldName())){
     			for(String aCol:avgCol){
     				if(cp.getDbFieldName().equals(aCol)){
     					strBuffer.append("<td "+color+" align=\"right\">"+ExportToPdfTool.tempDf.format(avgColumnMap.get(cp.getDbFieldName()+m)/(avgColumnCountMap.get(aCol+m)-1))+"</td>");
     					break;
     				}
     			}
     		}
     		else{
     			if(o==m){
     				color="style=\"background-color:"+totCol.get(m-1)+";\"";
     				strBuffer.append("<td "+color+">"+data.get(i).get(DataList.getCpList().get(n-1).getDbFieldName())+" Avg </td>");	
     			}
     			else{
     				strBuffer.append("<td "+color+" >&nbsp;</td>");
     			}
     		}
     		o++;
     	}
     	strBuffer.append("</tr>");
     	for(String aCol:avgCol){
     		avgColumnMap.put(aCol+m, 0f);
     		avgColumnCountMap.put(aCol+m, 1);
     	}
	 }
}
