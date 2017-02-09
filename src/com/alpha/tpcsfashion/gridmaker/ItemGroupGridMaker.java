package com.alpha.tpcsfashion.gridmaker;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.GridMaker;

public class ItemGroupGridMaker  extends GridMaker{

	
	public String formItemGroupGrid(Map<String, String> visColMap,List<String> listSelectedColumns, List<Map<String,String>> data, int ipageno, ResourceBundle bundle,UserRights matRights,PageConfig pc) throws IOException{
		StringBuffer strBuffer = new StringBuffer(); 
		try{  



			strBuffer.append("<div class=\"\">");
			strBuffer.append("<table class=\"table table-bordered table-condensed table-hover\" id=\"task-table\">");
			strBuffer.append("<thead>");
			strBuffer.append("<tr class=\"header\">");
			strBuffer.append("<th style=\"width: 100px;\">"+bundle.getString("Common.Action")+"</th>");

			Iterator it = visColMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry mVisCol= (Map.Entry)it.next();

				if(mVisCol.getKey()==null ){
					continue;
				}

				strBuffer.append("<th >"+mVisCol.getValue()+"</th>");
			}
			strBuffer.append("<th ><div class=\"checkbox\"><input class=\"checkbox_1\"  type=\"checkbox\" id=\"toggle_check_all\" name=\"toggle_check_all\" /><label for=\"toggle_check_all\" class=\"checkbox_1\"  ></label></div></th>");
			strBuffer.append("</tr>");
			strBuffer.append(" </thead>");
			strBuffer.append("<tbody>");

			String previous_no = "" ; 
			int k=1;
			for(int i=0,size=data.size();i<size;i++){ 

				Map<String, String> map = data.get(i);
				strBuffer.append("<tr class=\"datarow\" > " );

				boolean equal = previous_no.equals(map.get("ItemGroup.groupId"));
				if(!equal)
				{
					strBuffer.append("<td width=\"10%\">");
					strBuffer.append(getActionCell(map,ipageno,bundle,matRights));
				}else{
					strBuffer.append("<td >");
					strBuffer.append("&nbsp;");
				}
				strBuffer.append("</td>");

				Iterator it2 = visColMap.entrySet().iterator();
				while(it2.hasNext()){
					Map.Entry mVisCol= (Map.Entry)it2.next();
					if(mVisCol.getKey()==null ){
						continue;
					}
					strBuffer.append("<td align=\"left\" valign=\"middle\">");
					if(map.get(mVisCol.getValue())==null || "null".equalsIgnoreCase(map.get(mVisCol.getValue()))){          
						strBuffer.append("&nbsp;");
					}else{
						strBuffer.append(map.get(mVisCol.getValue()));
					}
					strBuffer.append("</td>");
				}
				if(!equal)
				{

					strBuffer.append(getToggleCheckCell(map,ipageno,bundle,matRights));
				}else{
					strBuffer.append("<td>&nbsp;</td>");
				}
				strBuffer.append("</tr>"); 
				previous_no=map.get("ItemGroup.groupId"); 

				k++;
			} 
			strBuffer.append("</tbody>");
			int iRowCount=PageConfig.iPageSize;
			if(k<=iRowCount){
				k=iRowCount-k;
				for(int i=1;i<=k;i++){
					strBuffer.append("<tr>");
					for(int j=0;j<listSelectedColumns.size();j++){
						strBuffer.append("<td>&nbsp;</td>");
					}
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
	private String getToggleCheckCell(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights matRights) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("<td><div class=\"checkbox\">  <input class=\"checkbox_1\" type=\"checkbox\" id=\"itemgroup_check_"+map.get("ItemGroup.groupId")+"\" name=\"groupmaster_id\" value="+map.get("ItemGroup.groupId")+" /><label for=\"itemgroup_check_"+map.get("ItemGroup.groupId")+"\" class=\"checkbox_1\"  ></label></div></td>");
		return buffer.toString(); 
	}

	private String getActionCell(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights matRights) {
		int permitted=1;
		StringBuffer buffer=new StringBuffer();

		buffer.append("<select style=\" align:left;width: 97px; \"  class=\"form-control\" id=\"select_action\" name=\"select_action\" onchange=\"groupActionList(this.value,'"+map.get("ItemGroup.groupId")+"','"+ipageno+"')\">");
		buffer.append("<option selected=\"selected\" value=\"-1\">"+bundle.getString("Common.SelectAction")+"</option>");

		if(matRights.getDeletePermission()==permitted){
			buffer.append("<option value=\"1\">"+bundle.getString("Button.Delete")+"</option>");
		}
		if(matRights.getEditPermission()==permitted){
			buffer.append("<option value=\"2\">"+bundle.getString("Button.Edit")+"</option>");
		}
		/* if(rights.getPrintPermission()==permitted){
			 buffer.append("<option value=\"4\">"+bundle.getString("Button.Print")+"</option>");
				 }*/

		buffer.append("</select>");


		return buffer.toString(); 
	}

}
