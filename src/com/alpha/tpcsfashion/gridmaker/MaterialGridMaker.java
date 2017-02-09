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

public class MaterialGridMaker extends GridMaker{

	 
	public String formMaterialGrid(Map<String, String> visColMap,List<String> listSelectedColumns, List<Map<String,String>> data, int ipageno, ResourceBundle bundle,UserRights matRights,PageConfig pc) throws IOException{
	    StringBuffer strBuffer = new StringBuffer(); 
	    try{  
	    	  /*String cssClass="subpageheader";  
  
		      strBuffer.append("<table class=\"table table-bordered table-condensed\">");                                
		      strBuffer.append(" <thead>");
		      strBuffer.append("<tr class=\"header\">");
		      strBuffer.append("<th style=\"height:22px;\" nowrap=\"nowrap\"  class=\"subpageheader\">"+bundle.getString("Common.Action")+"</th>");

		      Iterator it = visColMap.entrySet().iterator();
		      while(it.hasNext()){
		    	  Map.Entry mVisCol= (Map.Entry)it.next();
		    	  
		    	  if(mVisCol.getKey()==null ){
		    		  continue;
		    	  }

	    	  strBuffer.append("<th nowrap=\"nowrap\" align=\"center\" valign=\"middle\" class=\""+cssClass+"\">"+mVisCol.getValue()+"</th>");
		      }
	      strBuffer.append("</tr>");
	      strBuffer.append(" </thead>");
	      strBuffer.append("<tbody>");
	      

	      
	      String previous_order_no = "" ,style=null; 
	      int k=1;
	      for(int i=0,j=1,size=data.size();i<size;i++,j++){ 
	        if(j%2==0){
	        	  style="eventabledata";
	        }
	        else{
	        	style="oddtabledata"; 
	        }
	          
	        Map<String, String> map = data.get(i);
	        strBuffer.append("<tr class="+style+"> <td class=\"dataalignment\" nowrap=\"nowrap\">" );
	        boolean equal = previous_order_no.equals(map.get("Material.MatId"));
	        if(!equal)
	        {
	        strBuffer.append(getActionCellForMaterial(map,ipageno,bundle,objRights));
	        }else{
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
	        strBuffer.append("</tr>"); 
	        previous_order_no=map.get("Material.MatId"); 
	       
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
	    		  
	    		  strBuffer.append("</tr>");
	    	  }
	      }
	       
	     
	      strBuffer.append("</table>");*/
	    	
	    	
	    	strBuffer.append("<div class=\"\">");
//	    	strBuffer.append("<div class=\"panel panel-success\">");
//	       strBuffer.append("<div class=\"panel-heading\">");
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
		      
		      String previous_order_no = "" ; 
		      int k=1;
		      for(int i=0,size=data.size();i<size;i++){ 
		          
		        Map<String, String> map = data.get(i);
	        strBuffer.append("<tr class=\"datarow\" > " );
	        
	        boolean equal = previous_order_no.equals(map.get("Material.MatId"));
	        if(!equal)
	        {
	        	strBuffer.append("<td width=\"10%\">");
	        strBuffer.append(getActionCellForMaterial(map,ipageno,bundle,matRights));
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
   
        strBuffer.append(getToggleCheckCellForMaterial(map,ipageno,bundle,matRights));
        }else{
        	  strBuffer.append("<td>&nbsp;</td>");
        }
  	        strBuffer.append("</tr>"); 
  	        previous_order_no=map.get("Material.MatId"); 
  	       
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
	private String getToggleCheckCellForMaterial(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights matRights) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("<td><div class=\"checkbox\">  <input class=\"checkbox_1\" type=\"checkbox\" id=\"mat_check_"+map.get("Material.MatId")+"\" name=\"mat_check_"+map.get("Material.MatId")+"\" value=\"\" /><label for=\"mat_check_"+map.get("Material.MatId")+"\" class=\"checkbox_1\"  ></label></div></td>");
		return buffer.toString(); 
	}
	public String formMaterialPrintGrid(Map<String, String> visColMap,List<String> listSelectedColumns, List<Map<String,String>> data, int ipageno, ResourceBundle bundle,int iDivWidth,UserRights objRights) throws IOException{
	    StringBuffer strBuffer = new StringBuffer();
	    try{
	    	 String cssClass="subpageheader";  
		      strBuffer.append("<table class=\"rowline\" cellspacing=\"1\" cellpadding=\"4\" width=\"100%\"  >");                                
		      strBuffer.append("<tr>");
		     
		      Iterator it = visColMap.entrySet().iterator();
		      while(it.hasNext()){
		    	  Map.Entry mVisCol= (Map.Entry)it.next();
		    	  
		    	  if(mVisCol.getKey()==null ){
		    		  continue;
		    	  }

	    	  strBuffer.append("<th nowrap=\"nowrap\" align=\"center\" valign=\"middle\" class=\""+cssClass+"\">"+mVisCol.getValue()+"</th>");
		      }
		    	  

	      strBuffer.append("</tr>");
	       String style=null; 
	      int k=1;
	      for(int i=0,j=1,size=data.size();i<size;i++,j++){ 
	        if(j%2==0){
	        	  style="eventabledata";
	        }
	        else{
	        	style="oddtabledata"; 
	        }
	          
	        Map<String, String> map = data.get(i);
	        strBuffer.append("<tr class="+style+"> " );
	 
   
        
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
	        strBuffer.append("</tr>"); 
	       
	       k++;
	      } 
	      strBuffer.append("</tbody>");
	       
	     
	      strBuffer.append("</table>");

	    }catch(Exception e){
	      e.printStackTrace();
	    } 
	    return strBuffer.toString(); 
	  }

	private String getActionCellForMaterial(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights matRights) {
		 int permitted=1;
		 StringBuffer buffer=new StringBuffer();
			
		 buffer.append("<select style=\" align:left;width: 97px; \"  class=\"form-control\" id=\"select_action\" name=\"select_action\" onchange=\"displayMaterialActionList(this.value,'"+ipageno+"','"+map.get("Material.MatId")+"')\">");
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
		 

/*public class MaterialGridMaker extends GridMaker{

	 
  public String formMaterialGrid(Map<String, String> visColMap,List<String> listSelectedColumns, List<Map<String,String>> data,UserRights objRights) throws IOException{
    StringBuffer buffer = new StringBuffer();
    try{  
        String cssClass="subpageheader";  
        strBuffer.append("<div   style=\"overflow-y:hidden; \"> ");

        strBuffer.append("<table class=\"liting-table\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");                                
        strBuffer.append(" <thead>");
        strBuffer.append("<tr>");
        strBuffer.append("<th style=\"height:22px;\" nowrap=\"nowrap\"  class=\"subpageheader\">"+bundle.getString("Common.Action")+"</th>");

        Iterator it = visColMap.entrySet().iterator();
        while(it.hasNext()){
          Map.Entry mVisCol= (Map.Entry)it.next();
          
          if(mVisCol.getKey()==null ){
            continue;
          }

        strBuffer.append("<th nowrap=\"nowrap\" align=\"center\" valign=\"middle\" class=\""+cssClass+"\">"+mVisCol.getValue()+"</th>");
        }
      strBuffer.append("</tr>");
      strBuffer.append(" </thead>");
      strBuffer.append("<tbody>");
      

      
      buffer.append("<table class=\"table table-bordered table-condensed\">");
      buffer.append("<thead><tr class=\"header\"><th>ACTION</th>");
      Set<Entry<String, String>> entrySet = visColMap.entrySet();
      for (Map.Entry<String, String> entry : entrySet)
      {        
        if(entry.getValue()==null || entry.getKey()==null)
          continue;
        buffer.append("<th nowrap>");
        buffer.append(entry.getValue().toUpperCase());
        buffer.append("</th>");
      }
      buffer.append("</tr></thead>");
      buffer.append("<tbody>");
      for(Map<String,String> row:data){
        buffer.append("<tr class=\"datarow\">");
        buffer.append("<td>");      
        buffer.append("&nbsp;&nbsp;<a href=\"#product/"+row.get("Material.MatId")+"/delete\" data-toggle=\"tooltip\" title=\"Delete Material\">");
        buffer.append("<span class=\"glyphicon glyphicon-remove\"></span></a>");
        buffer.append("&nbsp;&nbsp;<a href=\"#product/"+row.get("Material.MatId")+"/edit\" data-toggle=\"tooltip\" title=\"Edit Material\">");
        buffer.append("<span class=\"glyphicon glyphicon-edit\"></span></a>");
        //buffer.append(row.get("Material.MatId"));
        buffer.append("</td>");
        
        Set<Entry<String, String>> dataSet = visColMap.entrySet();
        for (Map.Entry<String, String> entry : dataSet)
        {        
          if(entry.getKey()==null)
            continue;
          buffer.append("<td>");
          //System.out.println(entry.getKey()+"\t\t"+entry.getValue());
          if(row.get(entry.getValue())==null || "null".equalsIgnoreCase(row.get(entry.getValue()))){          
            buffer.append("&nbsp;");
          }else{
            buffer.append(row.get(entry.getValue()));
          }
          buffer.append("</td>");
        }
        Set<Entry<String, String>> dataSet = row.entrySet();
        for (Map.Entry<String, String> entry : dataSet)
        {        
          if(entry.getKey()==null)
            continue;
          buffer.append("<td>");
          System.out.println(entry.getValue());
          if(entry.getValue()==null || "null".equalsIgnoreCase(entry.getValue())){          
            buffer.append("&nbsp;");
          }else{
            buffer.append(entry.getValue());
          }
          buffer.append("</td>");
        }
        buffer.append("</tr>");
      }
      buffer.append("</tbody><table>");
      
      
      String previous_order_no = "" ,style=null; 
      int k=1;
      for(int i=0,j=1,size=data.size();i<size;i++,j++){ 
        if(j%2==0){
            style="eventabledata";
        }
        else{
          style="oddtabledata"; 
        }
          
        Map<String, String> map = data.get(i);
        strBuffer.append("<tr class="+style+"> <td class=\"dataalignment\" nowrap=\"nowrap\">" );
        boolean equal = true;//previous_order_no.equals(map.get("Material.MatId"));
        if(!equal)
        {
          //strBuffer.append(getActionCellForMaterial(map,ipageno,bundle,objRights));
        }else{
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
        strBuffer.append("</tr>"); 
        previous_order_no=map.get("Material.MatId"); 
       
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
          
          strBuffer.append("</tr>");
        }
      }
       
     
      strBuffer.append("</table>");

      strBuffer.append("</div>");
      
     }catch(Exception e){
     e.printStackTrace();
     }
     return buffer.toString();
    }
	
	public String formMaterialPrintGrid(Map<String, String> visColMap,List<String> listSelectedColumns, List<Map<String,String>> data, int ipageno, ResourceBundle bundle,int iDivWidth,UserRights objRights) throws IOException{
	    StringBuffer strBuffer = new StringBuffer();
	    try{
	    	 String cssClass="subpageheader";  
		      strBuffer.append("<table class=\"rowline\" cellspacing=\"1\" cellpadding=\"4\" width=\"100%\"  >");                                
		      strBuffer.append("<tr>");
		     
		      Iterator it = visColMap.entrySet().iterator();
		      while(it.hasNext()){
		    	  Map.Entry mVisCol= (Map.Entry)it.next();
		    	  
		    	  if(mVisCol.getKey()==null ){
		    		  continue;
		    	  }

	    	  strBuffer.append("<th nowrap=\"nowrap\" align=\"center\" valign=\"middle\" class=\""+cssClass+"\">"+mVisCol.getValue()+"</th>");
		      }
		    	  

	      strBuffer.append("</tr>");
	       String style=null; 
	      int k=1;
	      for(int i=0,j=1,size=data.size();i<size;i++,j++){ 
	        if(j%2==0){
	        	  style="eventabledata";
	        }
	        else{
	        	style="oddtabledata"; 
	        }
	          
	        Map<String, String> map = data.get(i);
	        strBuffer.append("<tr class="+style+"> " );
	 
   
        
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
	        strBuffer.append("</tr>"); 
	       
	       k++;
	      } 
	      strBuffer.append("</tbody>");
	       
	     
	      strBuffer.append("</table>");

	    }catch(Exception e){
	      e.printStackTrace();
	    } 
	    return strBuffer.toString(); 
	  }

	private String getActionCellForMaterial(Map<String, String> map, int ipageno,ResourceBundle bundle, UserRights rights) {
		 int permitted=1;
		 StringBuffer buffer=new StringBuffer();
			
		 buffer.append("<select style=\" align:left;\" id=\"select_action\" name=\"select_action\" onchange=\"displayMaterialActionList(this.value,'"+ipageno+"','"+map.get("Material.MatId")+"')\">");
		 buffer.append("<option selected=\"selected\" value=\"-1\">"+bundle.getString("Common.SelectAction")+"</option>");
		 
			 if(rights.getDeletePermission()==permitted){
		 buffer.append("<option value=\"1\">"+bundle.getString("Button.Delete")+"</option>");
			 }
			 if(rights.getEditPermission()==permitted){
		 buffer.append("<option value=\"2\">"+bundle.getString("Button.Edit")+"</option>");
			 }
		 if(rights.getPrintPermission()==permitted){
			 buffer.append("<option value=\"4\">"+bundle.getString("Button.Print")+"</option>");
				 }

			 buffer.append("</select>");
		
		 
		return buffer.toString(); 
	}




}*/
		 