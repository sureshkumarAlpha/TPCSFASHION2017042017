package com.alpha.tpcsfashion.gridmaker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.util.ExportToPdfTool;
import com.alpha.tpcsfashion.util.ReportGrid;
import com.alpha.tpcsfashion.util.Validator;
 
public class IndentApprovalRegisterGridMaker extends GridMaker {
	 
	public String formIndentApprovalRegisterGrid(TPCSUser userInfo,ColumnPreference DataList, List<Map<String,String>> data, ResourceBundle bundle,UserRights objRights) throws IOException{
		StringBuffer strBuffer = new StringBuffer(); 
		try{  
			
			
			ExportToPdfTool.setRoundOff(userInfo);
//			String cssClass="subpageheader";  
			
			strBuffer.append("<div style=\"overflow-y:hidden; \"> ");
			
            
			strBuffer.append("<table    class=\"table table-bordered table-condensed\" >");                                
			strBuffer.append(" <thead>");
			strBuffer.append("<tr  class=\"header\">");

			for(String column:DataList.getVisibleColumns()){
				strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">"+column+"</th>");
			}

			strBuffer.append("</tr>");
			strBuffer.append(" </thead>");
			strBuffer.append("<tbody>");

			int groupCount=DataList.getGroupCnt();

			List<String> totColor=new ArrayList<String>();
			totColor.add("#eee2f4");
			totColor.add("#b3e1e9");
			totColor.add("#f7dcd8");

			totColor.add("#f7d8dc");
			totColor.add("#e2f4ee");
			totColor.add("#d8f7dc");
			totColor.add("#e2eef4");
			totColor.add("#d8dcf7");
			totColor.add("#eee2f4");


			List<String> quantityCol=new ArrayList<String>();
			quantityCol=DataList.getColumnsToTotal();

			Map<String, Double> totalColumnMap=new LinkedHashMap<String,Double>();
//			Map<String,Double> avgColumnMap=new LinkedHashMap<String,Double>();
//			Map<String,Integer> avgColumnCountMap=new LinkedHashMap<String,Integer>();
			Map<String,String> groupMap=new HashMap<String,String>();
			for(int m=1;m<=groupCount;m++){
				for(String qCol:quantityCol){
					totalColumnMap.put(qCol+m, (double) 0.0f);  
				}

				groupMap.put("group"+m, "");
			}
			Map<Integer,Integer> groupTotalIndex=new LinkedHashMap<Integer,Integer>();
			groupTotalIndex.put(1, 0);
			groupTotalIndex.put(2, 0);
			groupTotalIndex.put(3, 0);
//			DecimalFormat df = new DecimalFormat("#.##");

			String style=null; 
			int k=1;
			for(int i=0,j=1,size=data.size();i<size;i++,j++){ 
				if(j%2==0)
					style="eventabledata";
				else
					style="oddtabledata"; 
				Map<String, String> map = data.get(i);
				strBuffer.append("<tr class="+style+"> " );

				int l=1;
				for(ColumnPreference cp:DataList.getCpList()){
					
					ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
					if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
						ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
					}
					else if(cp.getDbFieldName().equalsIgnoreCase("price_fcy")){
						ExportToPdfTool.tempDf=ExportToPdfTool.priceDf;
					}

					if(l<=groupCount+1){
						StringBuffer space=new StringBuffer();
						for(int m=1;m<=groupCount;m++){
							if(l==m ){
								if(!groupMap.get("group"+m).equals(map.get(cp.getDbFieldName()))){
									strBuffer.append(space+"<td colspan=\""+(DataList.getCpList().size()-(m-1))+"\" >");
									strBuffer.append(map.get(cp.getDbFieldName()));
									strBuffer.append(""+"</td>");
									strBuffer.append("</tr><tr>"); 
								}
								groupMap.put("group"+m, map.get(cp.getDbFieldName()));
								
							}
							space.append("<td >&nbsp;</td>");
						}
						if(l==groupCount+1){

							strBuffer.append(space+"<td  >"+map.get(cp.getDbFieldName())+"</td>");
						}
						space=null;
					}
					else{
						if(quantityCol.contains(cp.getDbFieldName())){
							for(String qCol:quantityCol){
								if(cp.getDbFieldName().equals(qCol)){

									for(int m=1;m<=groupCount;m++){
										totalColumnMap.put(qCol+m, (totalColumnMap.get(qCol+m)+Validator.convertToDouble(map.get(cp.getDbFieldName()))));
									}
									break;
								}	  
							}
							
							strBuffer.append("<td align=\"right\" >"+ExportToPdfTool.tempDf.format(Validator.convertToDouble(map.get(cp.getDbFieldName())))+"</td>");
						}
						else if(cp.getDataType()==6 ||cp.getDataType()==7  ){
							
							strBuffer.append("<td align=\"right\" >"+ExportToPdfTool.tempDf.format(Validator.convertToDouble(map.get(cp.getDbFieldName())))+"</td>");
						}
						else{
							strBuffer.append("<td >"+map.get(cp.getDbFieldName())+"</td>");
						}
					}
					l++;
				}
				strBuffer.append("</tr>"); 

				
				if(quantityCol.size()>0){
				for(int m=groupCount;m>=1;m--){
					if(data.size()==i+1){
						ReportGrid.writeGroupTotal(strBuffer,DataList,quantityCol,totalColumnMap,totColor,m,m,i,data);
					}
					else if(!data.get(i+1).get(DataList.getCpList().get(m-1).getDbFieldName()).equals(data.get(i).get(DataList.getCpList().get(m-1).getDbFieldName()))){
						groupTotalIndex.put(m, 1);
						if(m<groupCount){	
						if(groupTotalIndex.get(m+1)==0){
							ReportGrid.writeGroupTotal(strBuffer,DataList,quantityCol,totalColumnMap,totColor,m+1,m+1,i,data);
								groupMap.put("group"+(m+1), "");
								ReportGrid.writeGroupTotal(strBuffer,DataList,quantityCol,totalColumnMap,totColor,m,m,i,data);
							}
							else{
								ReportGrid.writeGroupTotal(strBuffer,DataList,quantityCol,totalColumnMap,totColor,m,m,i,data);
							}
						}
						else{
							ReportGrid.writeGroupTotal(strBuffer,DataList,quantityCol,totalColumnMap,totColor,m,m,i,data);
						}
					}
					if(m==1){
						groupTotalIndex.put(1, 0);
						groupTotalIndex.put(2, 0);
						groupTotalIndex.put(3, 0);
					}
				}
				}
				
				k++;
			} 
			
			strBuffer.append("</tbody>");
			
			List<String> listSelectedColumns=DataList.getVisibleColumns(); 
			strBuffer.append("</tbody>");
			int iRowCount=PageConfig.iPageSize;
			if(k==1 && k<=iRowCount){
				k=iRowCount-k;
				for(int i=1;i<=k;i++){
					strBuffer.append("<tr>");
					for(int j=1;j<=listSelectedColumns.size();j++){
						strBuffer.append("<td>&nbsp;</td>");
					}
					strBuffer.append("</tr>");
				}
			}
			strBuffer.append("</table>");
			strBuffer.append("</div>");
			totColor=null;
			quantityCol=null;
			totalColumnMap=null;
//			avgColumnMap=null;
//			avgColumnCountMap=null;
			groupMap=null;
			groupTotalIndex=null;
		}catch(Exception e){
			e.printStackTrace();
		}



		return strBuffer.toString();
	}


		    
	
	 
}
