package com.alpha.tpcsfashion.gridmaker;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;

public class SizeMappingGridMaker {
	public String formSizeMappingGrid(TPCSUser userInfo,Map<String, String> visColMap,List<String> listSelectedColumns, List<Map<String,String>> data, int ipageno, ResourceBundle bundle,UserRights seRights,UserRights sqRights,PageConfig pc) throws IOException{
		StringBuffer strBuffer = new StringBuffer(); 
		try{  

			strBuffer.append("<div class=\"\">");

			strBuffer.append("<table class=\"table table-bordered table-condensed table-hover\" id=\"task-table\">");
			strBuffer.append("<thead>");
			strBuffer.append("<tr class=\"header\">");
			strBuffer.append("<th style=\"width: 107px;\">"+bundle.getString("Common.Action")+"</th>");


			Iterator it = visColMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry mVisCol= (Map.Entry)it.next();

				if(mVisCol.getKey()==null ){
					continue;
				}

				strBuffer.append("<th >"+mVisCol.getValue()+"</th>");
			}
			strBuffer.append("<th ><div class=\"checkbox\"  ><input class=\"checkbox_1\" type=\"checkbox\" id=\"toggle_check_all\" name=\"toggle_check_all\"   /><label for=\"toggle_check_all\" class=\"checkbox_1\" ></label></div></th>");
			strBuffer.append("</tr>");
			strBuffer.append(" </thead>");
			strBuffer.append("<tbody>");
			String previous_order_no = "" ; 
			int k=1;
			for(int i=0,size=data.size();i<size;i++){ 

				Map<String, String> map = data.get(i);


				strBuffer.append("<tr class=\"datarow\" id=\"enq_row_"+map.get("SizeMapping.SizeScheduleId")+"\"  > <td  >" );
//				strBuffer.append("<tr class=\"datarow\"> <td  >" );
				boolean equal = previous_order_no.equals(map.get("SizeMapping.SizeScheduleId"));
				if(!equal)
				{

					strBuffer.append(getActionCellForSalesEnquiry(map,ipageno,bundle,seRights,sqRights));
				}else{
					strBuffer.append("&nbsp;");
				}
				strBuffer.append("</td>");

//				strBuffer.append("<td align=\"left\" valign=\"middle\" onclick=\"showEnquiryDetailgrid("+map.get("ApprovedPriceList.PurchasePriceId")+");\">"+map.get("SalesEnquiry.EnquiryNo")+"</td>");

				Iterator it2 = visColMap.entrySet().iterator();
				while(it2.hasNext()){
					Map.Entry mVisCol= (Map.Entry)it2.next();
					if(mVisCol.getKey()==null ){
						continue;
					}

					strBuffer.append("<td align=\"left\" valign=\"middle\" onclick=\"showEnquiryDetailgrid("+map.get("SizeMapping.SizeScheduleId")+");\">");
					if(map.get(mVisCol.getValue())==null || "null".equalsIgnoreCase(map.get(mVisCol.getValue()))){          
						strBuffer.append("&nbsp;");
					}else{
						strBuffer.append(map.get(mVisCol.getValue()));
					}
					strBuffer.append("</td>");
				}
				if(!equal)
				{

					strBuffer.append(getToggleCheckCellForSalesEnquiry(map,ipageno,bundle,seRights,sqRights));
				}else{
					strBuffer.append("<td>&nbsp;</td>");
				}

				strBuffer.append("</tr>"); 
				previous_order_no=map.get("SizeMapping.SizeScheduleId"); 

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



	private String getActionCellForSalesEnquiry(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights seRights,UserRights sqRights) {
		int permitted=1;
		
		StringBuffer buffer=new StringBuffer();
		

		buffer.append("<select style=\" align:left;width: 97px; \" id=\"select_action\" class=\"form-control\" name=\"select_action\" onchange=\"displaySizeMappingActionList(this.value,'"+ipageno+"','"+map.get("SizeMapping.SizeScheduleId")+"','"+map.get("SizeMapping.SizeScheduleDetId")+"')\">");
		buffer.append("<option selected=\"selected\" value=\"-1\">"+bundle.getString("Common.SelectAction")+"</option>");

			if(seRights.getEditPermission()==permitted){
				buffer.append("<option value=\"1\">"+bundle.getString("Button.Edit")+"</option>");
			}
			if(seRights.getDeletePermission()==permitted){
				buffer.append("<option value=\"2\">"+bundle.getString("Button.Delete")+"</option>");
			}


		buffer.append("</select>");


		return buffer.toString(); 
	}
	private String getToggleCheckCellForSalesEnquiry(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights seRights,UserRights sqRights) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("<td><div class=\"checkbox\"  > <input class=\"checkbox_1\" type=\"checkbox\" id=\"se_check_"+map.get("SizeMapping.SizeScheduleId")+"\" name=\"SizeSchedule_Id\" value="+map.get("SizeMapping.SizeScheduleId")+"   /><label for=\"se_check_"+map.get("SizeMapping.SizeScheduleId")+"\" class=\"checkbox_1\"  ></label></div></td>");
		//buffer.append("<td><div class=\"checkbox\"  > <input class=\"checkbox_1\" type=\"checkbox\" id=\"se_check_"+map.get("ApprovedPriceList.SupplierId")+"\" name=\"purchasePrice_Id\" value="+map.get("ApprovedPriceList.SupplierId")+"   /><label for=\"se_check_"+map.get("ApprovedPriceList.SupplierId")+"\" class=\"checkbox_1\"  ></label></div></td>");
		return buffer.toString(); 
	}
}
