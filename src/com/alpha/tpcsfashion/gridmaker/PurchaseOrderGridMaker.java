package com.alpha.tpcsfashion.gridmaker;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.BuyerOrder;
import com.alpha.tpcsfashion.beans.PurchaseOrder;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.util.Validator;

public class PurchaseOrderGridMaker 
{
	public String formPurchaseOrderGrid(PurchaseOrder pr,ResourceBundle bundle,UserRights rights, PageConfig pc) throws IOException{
		StringBuffer strBuffer = new StringBuffer(); 
		try{  

			Map<String, String> visColMap=pr.getVisColMap();
			List<String> listSelectedColumns=pr.getListSelectedColumns(); 
			List<Map<String,String>> data=pr.getPoMapList(); 
			
			strBuffer.append("<div class=\"\">");
			strBuffer.append("<table class=\"table table-bordered table-condensed table-hover\" id=\"task-table\">");
			strBuffer.append("<thead>");
			strBuffer.append("<tr class=\"header\">");
			strBuffer.append("<th >"+bundle.getString("Common.Action")+"</th>");
			strBuffer.append("<th >"+bundle.getString("PurchaseOrder.PONo")+"</th>");
			Iterator it = visColMap.entrySet().iterator();
			
			while(it.hasNext()){
				Map.Entry mVisCol= (Map.Entry)it.next();

				if(mVisCol.getKey()==null ){
					continue;
				}

				//Not a print statement
				strBuffer.append("<th >"+mVisCol.getValue()+"</th>");

			}

			strBuffer.append("<th ><div class=\"checkbox\"><label> <input class=\"checkbox_1\"  type=\"checkbox\" id=\"toggle_check_all\" name=\"toggle_check_all\" /><label for=\"toggle_check_all\" class=\"checkbox_1\"  ></label></div></th>");
			strBuffer.append("</tr>");
			strBuffer.append(" </thead>");
			strBuffer.append("<tbody>");
			String previous_order_no = "" ; 
			int k=1;
			for(int i=0,size=data.size();i<size;i++){ 

				Map<String, String> map = data.get(i);
				strBuffer.append("<tr class=\"datarow\" > <td >" );

				boolean equal = previous_order_no.equals(map.get("PurchaseOrder.POId"));
				if(!equal){

					strBuffer.append(getActionCellForPurchaseOrder(map,bundle,rights));
				}
				else{
					strBuffer.append("&nbsp;");
				}
				strBuffer.append("</td>");

				strBuffer.append("<td align=\"left\" valign=\"middle\">"+map.get("PurchaseOrder.PONo")+"</td>");


				Iterator it2 = visColMap.entrySet().iterator();
				while(it2.hasNext()){
					Map.Entry mVisCol= (Map.Entry)it2.next();
					if(mVisCol.getKey()==null ){
						continue;
					}


					boolean checkFormat=false;
					String nowrapCls="";
					if(map.get(mVisCol.getValue())!=null && !map.get(mVisCol.getValue()).isEmpty() && map.get(mVisCol.getValue()).matches("([0-9]{2})-([0-9]{2})-([0-9]{4})") ){
						checkFormat=true;
						nowrapCls=" nowrap=\"nowrap\" ";
					}


					strBuffer.append("<td align=\"left\" "+nowrapCls+" valign=\"middle\">");
					if(map.get(mVisCol.getValue())==null || "null".equalsIgnoreCase(map.get(mVisCol.getValue()))){          
						strBuffer.append("&nbsp;");
					}else{
						strBuffer.append(map.get(mVisCol.getValue()));
					}
					strBuffer.append("</td>");
				}
				if(!equal)
				{

					strBuffer.append(getToggleCheckCellForPurchaseOrder(map,bundle,rights));
				}
				else{
					strBuffer.append("<td>&nbsp;</td>");
				}

				strBuffer.append("</tr>"); 
				previous_order_no=map.get("PurchaseOrder.POId"); 

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
					strBuffer.append("</tr>");
				}
			}


			strBuffer.append("</table>");
			strBuffer.append("</div>");


		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strBuffer.toString();
	}


	private String getActionCellForPurchaseOrder(Map<String, String> map, ResourceBundle bundle, UserRights rights) {
		int permission=1;

		StringBuffer buffer=new StringBuffer();

		buffer.append("<select class=\"form-control\"  style=\" align:left;width: 97px; \" id=\"select_action\" name=\"select_action\" onchange=\"displayPOActionList(this.value,1,'"+map.get("PurchaseOrder.POId")+"','"+map.get("PurchaseOrder.PODetId")+"')\">");
		buffer.append("<option selected=\"selected\" value=\"-1\">"+bundle.getString("Common.SelectAction")+"</option>");

		if(Validator.convertToInteger(map.get("PurchaseOrder.CloseTag"))==0){
			if(rights.getViewPermission()==permission){
				buffer.append("<option value=\"1\">"+bundle.getString("Button.View")+"</option>");
			}


			if(Validator.convertToInteger(map.get("PurchaseOrder.CancelTag"))!=1){
				if(rights.getPrintPermission()==permission){
					buffer.append("<option value=\"4\">"+bundle.getString("Button.Print")+"</option>");
				}
				if(rights.getEditPermission()==permission){
					buffer.append("<option value=\"2\">"+bundle.getString("Button.Edit")+"</option>");
					buffer.append("<option value=\"6\">"+bundle.getString("Button.Amendment")+"</option>");
				}

			}
			if(rights.getDeletePermission()==permission){
				buffer.append("<option value=\"3\">"+bundle.getString("Button.Delete")+"</option>");
			}
			if(Validator.convertToInteger(map.get("PurchaseOrder.CancelTag"))!=1){
				if(rights.getDeletePermission()==permission){
					buffer.append("<option value=\"5\">"+bundle.getString("Button.Cancel")+"</option>");
					buffer.append("<option value=\"8\">"+bundle.getString("Button.Close")+"</option>");
				}
			}
			buffer.append("<option value=\"7\">"+bundle.getString("Button.ReleaseIO")+"</option>");
		}
		buffer.append("</select>");

		return buffer.toString(); 


	}

	private String getToggleCheckCellForPurchaseOrder(Map<String, String> map, ResourceBundle bundle, UserRights rights) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("<td><div class=\"checkbox\"> <label><input  class=\"checkbox_1\"  type=\"checkbox\" id=\"po_check_"+map.get("PurchaseOrder.POId")+"\" name=\"po_chk_id\" value="+map.get("PurchaseOrder.POId")+" /><label for=\"po_check_"+map.get("PurchaseOrder.POId")+"\" class=\"checkbox_1\"  ></label></div></td>");
		return buffer.toString(); 
	}




}
