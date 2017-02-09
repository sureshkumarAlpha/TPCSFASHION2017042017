
package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.UserInfo;

public class ColumnPreferenceServlet {


	  public void doDisplayColumnPreferencePage(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	    	
	    	try {
	    		
	    		HttpSession session=request.getSession();
	    		TPCSUser userInfo = new UserInfo().getUserInfo(request);
	    		StringBuffer buffer = new StringBuffer();
	    		JSONObject main = new JSONObject();
	    		int iDocumentId = Integer.parseInt(request.getParameter("document_id"));
	    		ColumnPreferenceManager cpm=new ColumnPreferenceManager();
	    		Map<Integer, String> columns = null; 
	    		List<Integer> visibleColumns = cpm.getVisibleColumns(iDocumentId,userInfo);
	    			columns = cpm.getAllColumns(iDocumentId,userInfo);	    
	    		//	request.setAttribute("all_columns",columns);	
	    			//request.setAttribute("visible_columns" , visibleColumns);	 
	    		ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);
	    		String orgPopup = buildOrganizePopup(visibleColumns,columns,iDocumentId,bundle,userInfo);
	    		response.setContentType("text/html");
				response.getWriter().write(orgPopup);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(); 
			}
	    
			} 

	  public String buildOrganizePopup(List<Integer> visColumns,Map<Integer, String> columns,int iDocumentId,ResourceBundle bundle,TPCSUser userInfo)
		{
		  
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<table width=\"100%\">");
		
		buffer.append("<tr>");
		buffer.append("<td style=\"height:25px;\" colspan=\"2\" align=\"center\">&nbsp;<font color=\"red\"><div id=\"error_message\"></div></font></td>");
		buffer.append("</tr>");
		
		buffer.append("<tr>");
		buffer.append("<td width=\"50%\">");
		buffer.append("<table width=\"100%\">");
		buffer.append("<tr>");
		buffer.append("<td width=\"80%\">");
		buffer.append("<select name=\"total_columns\" id=\"total_columns\" size=\"15\" multiple=\"multiple\" style=\"width:100%;\">");
					
					 Map<Integer,String> allColumns=columns; 
					 List<Integer> visibleColumns=visColumns;
					  
					 Set<Integer> columnIds = allColumns.keySet(); 
			    	Iterator idItr = columnIds.iterator();   
			    	int key=0;			     	
			    	while(idItr.hasNext()){
			    	  key=(Integer)idItr.next();	
			    	  if(!visibleColumns.contains(key)){
			    		  System.out.print("iDocumentId :"+iDocumentId);
			    	  if(iDocumentId!=93){
			    		  buffer.append("<option value="+key+">"+allColumns.get(key)+"</option>");
			    	  }
			    	  else{
			    		  buffer.append("<option value="+key+">"+bundle.getString(allColumns.get(key))+"</option>");
			    	  }
			    		  
					
			    	  }}
					
					
			    	buffer.append("</select>");
			    	buffer.append("</td>");
			    	buffer.append("<td width=\"20%\" style=\"text-align:center;\">");
			    	buffer.append("<table width=\"100%\">");
			    	buffer.append("<tr height=\"40\"><td ><a class=\"btn btn-primary\" style=\"padding: 5px 10px;line-height: 1;\" href=\"javascript:addToVisible()\" ><span class=\"glyphicon glyphicon-circle-arrow-right\" style=\"font-size: 20px;\" aria-hidden=\"true\"  ></span></a></td></tr>");
			    	buffer.append("<tr><td><a class=\"btn btn-primary\" style=\"padding: 5px 10px;line-height: 1;\" href=\"javascript:addToHidden()\" ><span class=\"glyphicon glyphicon-circle-arrow-left\" style=\"font-size: 20px;\" aria-hidden=\"true\"  ></span></a></td></tr>");
			    	buffer.append("</table>");
			    	buffer.append("</td>");
			    	buffer.append("</tr>");
			    	buffer.append("</table>");
			    	buffer.append("</td>");
			    	buffer.append("<td width=\"50%\">");
			    	buffer.append("<table width=\"100%\">"); 
			    	buffer.append("<tr>");
			    	buffer.append("<td width=\"80%\">");
			    	buffer.append("<select name=\"visible_columns\" id=\"visible_columns\" size=\"15\" multiple=\"multiple\" style=\"width:100%;\">");
			    	
					Iterator visible = columnIds.iterator();
			    	while(visible.hasNext()){
			    	  key=(Integer)visible.next();			
			    	  if(visibleColumns.contains(key)){    
				
			    		  if(iDocumentId!=93){
				    		  buffer.append("<option value="+key+">"+allColumns.get(key)+"</option>");
				    	  }
				    	  else{
				    		  buffer.append("<option value="+key+">"+bundle.getString(allColumns.get(key))+"</option>");
				    	  }
			    	  }}
			    	buffer.append("</select>");
			    	buffer.append("</td>");
			    	buffer.append("<td width=\"20%\" style=\"text-align:center;\">");
			    	buffer.append("<table width=\"100%\">");
		    	    buffer.append("<tr height=\"40\"><td><a class=\"btn btn-primary\" style=\"padding: 5px 10px;line-height: 1;\" href=\"javascript:moveToUp()\" ><span class=\"glyphicon glyphicon-circle-arrow-up\" style=\"font-size: 20px;\" aria-hidden=\"true\" ></span></a></td></tr>");
			    	buffer.append("<tr><td><a class=\"btn btn-primary\" style=\"padding: 5px 10px;line-height: 1;\" href=\"javascript:moveToDown()\" ><span class=\"glyphicon glyphicon-circle-arrow-down\" style=\"font-size: 20px;\" aria-hidden=\"true\" ></span></a></td></tr>");
						
			    	buffer.append("</table>");
			    	buffer.append("</td>");
			    	buffer.append("</tr>");
			    	buffer.append("</table>");
			    	buffer.append("</td>");
			    	buffer.append("</tr>");
			    	
			   
			    	buffer.append("</table> ");
		 return buffer.toString();
	
}
}
