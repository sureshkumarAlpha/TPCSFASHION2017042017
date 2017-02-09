package com.alpha.tpcsfashion.gridmaker;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.util.Validator;


public class BOMGridMaker {

	
	public String formBomGrid(TPCSUser userInfo,Map<String, String> visColMap,List<String> listSelectedColumns, List<Map<String,String>> data, int ipageno, ResourceBundle bundle,UserRights bomRights,PageConfig pc) throws IOException{
		StringBuffer strBuffer = new StringBuffer(); 
		try{  

			
			String onclickFun="";
			strBuffer.append("<div class=\"\">");

			strBuffer.append("<table class=\"table table-bordered table-condensed table-hover\" id=\"task-table\">");
			strBuffer.append("<thead>");
			strBuffer.append("<tr class=\"header\">");
			strBuffer.append("<th style=\"width: 107px;\">"+bundle.getString("Common.Action")+"</th>");
			strBuffer.append("<th >"+bundle.getString("BOM.BOMNo")+"</th>");


			Iterator it = visColMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry mVisCol= (Map.Entry)it.next();

				if(mVisCol.getKey()==null ){
					continue;
				}

				strBuffer.append("<th >"+mVisCol.getValue()+"</th>");
			}
			strBuffer.append("<th >Count</th>");
			strBuffer.append("<th ><div class=\"checkbox\"  ><input class=\"checkbox_1\" type=\"checkbox\" id=\"toggle_check_all\" name=\"toggle_check_all\"   /><label for=\"toggle_check_all\" class=\"checkbox_1\" ></label></div></th>");
			strBuffer.append("</tr>");
			strBuffer.append(" </thead>");
			strBuffer.append("<tbody>");
			String previous_order_no = "" ; 
			int k=1;
			for(int i=0,size=data.size();i<size;i++){ 

				Map<String, String> map = data.get(i);

				onclickFun="onclick=\"showBOMDetailgrid("+map.get("BOM.BOMId")+");\"";
				
				strBuffer.append("<tr class=\"datarow\" id=\"bom_row_"+map.get("BOM.BOMId")+"\"  > <td  >" );

				boolean equal = previous_order_no.equals(map.get("BOM.BOMId"));
				if(!equal)
				{

					strBuffer.append(getActionCellForBOM(map,ipageno,bundle,bomRights));
				}else{
					strBuffer.append("&nbsp;");
				}
				strBuffer.append("</td>");

				strBuffer.append("<td align=\"left\" "+onclickFun+"  valign=\"middle\">"+map.get("BOM.BOMNo")+"</td>");

				Iterator it2 = visColMap.entrySet().iterator();
				while(it2.hasNext()){
					Map.Entry mVisCol= (Map.Entry)it2.next();
					if(mVisCol.getKey()==null ){
						continue;
					}

					strBuffer.append("<td align=\"left\" valign=\"middle\" "+onclickFun+"  >");
					if(map.get(mVisCol.getValue())==null || "null".equalsIgnoreCase(map.get(mVisCol.getValue()))){          
						strBuffer.append("&nbsp;");
					}else{
						strBuffer.append(map.get(mVisCol.getValue()));
					}
					strBuffer.append("</td>");
				}
				strBuffer.append("<td align=\"left\" "+onclickFun+"  valign=\"middle\">"+map.get("count")+"</td>");
				if(!equal)
				{

					strBuffer.append(getToggleCheckCellForBOM(map,ipageno,bundle,bomRights));
				}else{
					strBuffer.append("<td>&nbsp;</td>");
				}

				strBuffer.append("</tr>"); 
				previous_order_no=map.get("BOM.BOMId"); 

				k++;
			} 

			strBuffer.append("</tbody>");
			int iRowCount=PageConfig.iPageSize;
			if(k<=iRowCount){
				k=iRowCount-k;
				for(int i=1;i<=k;i++){
					strBuffer.append("<tr>");
					for(int j=0;j<=listSelectedColumns.size();j++){
						strBuffer.append("<td>&nbsp;</td>");
					}
					strBuffer.append("<td>&nbsp;</td>");
					strBuffer.append("<td>&nbsp;</td>");
					strBuffer.append("<td>&nbsp;</td>");
					strBuffer.append("</tr>");
				}
			}


			strBuffer.append("</table>");
			strBuffer.append("</div>");


		}catch(Exception e){
			e.printStackTrace();
		}
		return strBuffer.toString();
	}



	private String getActionCellForBOM(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights bomRights) {
		int permitted=1;
		StringBuffer buffer=new StringBuffer();

		buffer.append("<select style=\" align:left;width: 97px; \" id=\"select_action\" class=\"form-control\" name=\"select_action\" onchange=\"displayBOMActionList(this.value,'"+ipageno+"','"+map.get("BOM.BOMId")+"','"+map.get("BOM.BOMNo")+"')\">");
		buffer.append("<option selected=\"selected\" value=\"-1\">"+bundle.getString("Common.SelectAction")+"</option>");

		
		
		if(bomRights.getViewPermission()==permitted){
			buffer.append("<option value=\"1\">"+bundle.getString("Button.Print")+"</option>");
		}
		if(bomRights.getViewPermission()==permitted){
			buffer.append("<option value=\"2\">"+bundle.getString("Button.View")+"</option>");
		}

		if(bomRights.getEditPermission()==permitted){
			buffer.append("<option value=\"3\">"+bundle.getString("Button.Edit")+"</option>");
			
		   if(map.get("BOM.LockStatus").equalsIgnoreCase("0")){
				buffer.append("<option value=\"6\">"+bundle.getString("Button.Lock")+"</option>");	
			}
		   else if(map.get("BOM.LockStatus").equalsIgnoreCase("1") ){
				buffer.append("<option value=\"5\">"+bundle.getString("Button.Amendment")+"</option>");		
				buffer.append("<option value=\"7\">"+bundle.getString("Button.UnLock")+"</option>");		
			} 

		}
		
		if(bomRights.getDeletePermission()==permitted && !(map.get("BOM.LockStatus").equalsIgnoreCase("1") || Validator.convertToInteger(map.get("BOM.AmendStatus"))>0  ) ){
			buffer.append("<option value=\"4\">"+bundle.getString("Button.Delete")+"</option>");
		}
		
		buffer.append("</select>");


		return buffer.toString(); 
	}
	private String getToggleCheckCellForBOM(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights seRights) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("<td><div class=\"checkbox\"  > <input class=\"checkbox_1\" type=\"checkbox\" id=\"se_check_"+map.get("BOM.BOMId")+"\" name=\"bom_id\" value="+map.get("BOM.BOMId")+"   /><label for=\"se_check_"+map.get("BOM.BOMId")+"\" class=\"checkbox_1\"  ></label></div></td>");
		return buffer.toString(); 
	}
	
	
}
		 
