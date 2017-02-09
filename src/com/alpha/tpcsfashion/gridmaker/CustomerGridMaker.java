package com.alpha.tpcsfashion.gridmaker;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;

public class CustomerGridMaker extends GridMaker{

	 
	public String formCustomertGrid(Map<String, String> visColMap,List<String> listSelectedColumns, List<Map<String,String>> data, int ipageno, ResourceBundle bundle,UserRights custRights,PageConfig pc) throws IOException{
	    StringBuffer strBuffer = new StringBuffer(); 
	    try{  
	    	
	    	strBuffer.append("<div class=\"\">");
//	    	strBuffer.append("<div class=\"panel panel-success\">");
//	        strBuffer.append("<div class=\"panel-heading\">");
//	    	strBuffer.append("<h3 class=\"panel-title\">Tasks</h3>");
//	    	strBuffer.append("<div class=\"pull-right\">");
//	    	strBuffer.append("<span class=\"clickable filter\" data-toggle=\"tooltip\" title=\"Toggle table filter\" data-container=\"body\">");
//	    	strBuffer.append("<i class=\"glyphicon glyphicon-filter\"></i>");
//	    	strBuffer.append("</span>");
//	    	strBuffer.append("</div>");
//	    	strBuffer.append("</div>");
//	    	strBuffer.append("<div class=\"panel-body\" style=\"display: none;\"> ");
//	        strBuffer.append("<input type=\"text\" class=\"form-control\" id=\"task-table-filter\" data-action=\"filter\" data-filters=\"#task-table\" placeholder=\"Filter Tasks\" />");
//	    	strBuffer.append("</div>");
			
	    	strBuffer.append("<table class=\"table table-bordered table-condensed table-hover\" id=\"task-table\">");
	    	strBuffer.append("<thead>");
	    	strBuffer.append("<tr class=\"header\">");
	    	strBuffer.append("<th style=\"width: 107px;\">"+bundle.getString("Common.Action")+"</th>");
	    	  
	    	for(String column:listSelectedColumns){
  		    	  strBuffer.append("<th>"+bundle.getString(column)+"</th>");
       }
	    	 strBuffer.append("<th ><div class=\"checkbox\"  ><input class=\"checkbox_1\" type=\"checkbox\" id=\"toggle_check_all\" name=\"toggle_check_all\"   /><label for=\"toggle_check_all\" class=\"checkbox_1\" ></label></div></th>");
		      strBuffer.append("</tr>");
		      strBuffer.append(" </thead>");
		      strBuffer.append("<tbody>");
		      
		      int k=1;
		      for(int i=0,size=data.size();i<size;i++){ 
		          
		        Map<String, String> map = data.get(i);
	        strBuffer.append("<tr class=\"datarow\" > <td >" );
	        strBuffer.append(getActionCellForCustomer(map,ipageno,bundle,custRights));
	        strBuffer.append("</td>");
           
		        for(String column:listSelectedColumns)
		        {
		        	strBuffer.append("<td align=\"left\" valign=\"middle\">"+map.get(column)+"</td>");	
		        }
		        strBuffer.append(getToggleCheckCellForCustomer(map,ipageno,bundle,custRights));
  	        strBuffer.append("</tr>"); 
  	       
  	       k++;
  	      } 
  	      strBuffer.append("</tbody>");
  	    int iRowCount=PageConfig.iPageSize;
//  	      if(k<=iRowCount){
//  	    	  k=iRowCount-k;
//  	    	  for(int i=1;i<=k;i++){
//  	    		  strBuffer.append("<tr>");
//  	    		  for(int j=0;j<=listSelectedColumns.size();j++){
//  	    			  strBuffer.append("<td>&nbsp;</td>");
//  	    		  }
//  	    		  
//  	    		  strBuffer.append("</tr>");
//  	    	  }
//  	      }
  	       
  	     
  	      strBuffer.append("</table>");
           
	  	strBuffer.append("</div>");

 
			 }catch(Exception e){
			 e.printStackTrace();
			 }
			 return strBuffer.toString();
			}
	
	private String getToggleCheckCellForCustomer(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights custRights) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("<td><div class=\"checkbox\"  > <input class=\"checkbox_1\" type=\"checkbox\" id=\"cust_check_"+map.get("Customer.partyId")+"\" name=\"cust_check_"+map.get("Customer.partyId")+"\" value=\"\"   /><label for=\"cust_check_"+map.get("Customer.partyId")+"\" class=\"checkbox_1\"  ></label></div></td>");
		return buffer.toString(); 
	}

	private String getActionCellForCustomer(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights custRights) {
		 int permitted=1;
		 StringBuffer buffer=new StringBuffer();
			
		 buffer.append("<select style=\" align:left;width: 97px; \"  class=\"form-control\" id=\"select_action\" name=\"select_action\" onchange=\"displayCustomerActionList(this.value,'"+ipageno+"','"+map.get("Customer.partyId")+"')\">");
		 buffer.append("<option selected=\"selected\" value=\"-1\">"+bundle.getString("Common.SelectAction")+"</option>");
		 
			 if(custRights.getDeletePermission()==permitted){
		 buffer.append("<option value=\"1\">"+bundle.getString("Button.Delete")+"</option>");
			 }
			 if(custRights.getEditPermission()==permitted){
		 buffer.append("<option value=\"2\">"+bundle.getString("Button.Edit")+"</option>");
			 }
			 buffer.append("</select>");
		
		 
		return buffer.toString(); 
	}




}

		 