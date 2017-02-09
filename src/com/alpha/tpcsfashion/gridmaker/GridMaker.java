package com.alpha.tpcsfashion.gridmaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GridMaker {
	public List<String> getVisibleColumns(String selectedColumns,Map<Integer, String> allColumns){
	    List<String> listColumns = new ArrayList<String>();
	    try{      
	      String[] sColumns = selectedColumns.split(",");      
	      for(String column:sColumns){
	    	  
	        listColumns.add(allColumns.get(Integer.parseInt(column)));
	      }
	      
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    return listColumns;
	  }
}
