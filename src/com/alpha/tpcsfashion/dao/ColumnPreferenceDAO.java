package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class ColumnPreferenceDAO
{

  public Map<Integer, String> getAllColumns(Connection con,int iDocumentId,TPCSUser userInfo)throws SQLException,Exception{
    PreparedStatement ArrayOfPreparedStatement[] = null;
    ResultSet ArrayOfResultSet[] = null;
    CallableStatement cstmtGetAllColumns=null;
    ResultSet rsGetAllColoumns = null;   
    Map<Integer, String> columns=new LinkedHashMap<Integer, String>();
    try{      
      
      cstmtGetAllColumns = con.prepareCall("{call pr_getallcolumns(?,?)}");
      cstmtGetAllColumns.setInt(1,iDocumentId);  
      cstmtGetAllColumns.setInt(2,userInfo.getUserId());     
      rsGetAllColoumns = cstmtGetAllColumns.executeQuery(); 
      while(rsGetAllColoumns.next()){
        columns.put(rsGetAllColoumns.getInt(1),rsGetAllColoumns.getString(2));
      }
     
    }finally{     
      if(cstmtGetAllColumns!=null)
        cstmtGetAllColumns.close();
      ArrayOfPreparedStatement = new PreparedStatement[0];     
      ArrayOfResultSet = new ResultSet[1];      
      ArrayOfResultSet[0] = rsGetAllColoumns;
      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
    }
    return columns;
  }
  
  
  public List<Integer> getVisibleColumns(Connection con,int iDocumentId, TPCSUser userInfo)throws SQLException,Exception{
    PreparedStatement ArrayOfPreparedStatement[] = null;
    ResultSet ArrayOfResultSet[] = null;
    CallableStatement cstmtGetVisibleColumns=null;
    ResultSet rsGetVisibleColumns = null;   
    List<Integer> visibleColumns=new ArrayList<Integer>();
    try{      
      cstmtGetVisibleColumns = con.prepareCall("{call pr_getvisiblecolumns(?,?,?)}");
      cstmtGetVisibleColumns.setInt(1,iDocumentId);
      cstmtGetVisibleColumns.setInt(2,userInfo.getUserId());
      cstmtGetVisibleColumns.setInt(3,1);
      rsGetVisibleColumns = cstmtGetVisibleColumns.executeQuery();
      while(rsGetVisibleColumns.next()){
        visibleColumns.add(rsGetVisibleColumns.getInt(1));
      }
    }finally{
      if(cstmtGetVisibleColumns!=null)
        cstmtGetVisibleColumns.close(); 
    
      ArrayOfPreparedStatement = new PreparedStatement[0];
      ArrayOfResultSet = new ResultSet[1];      
      ArrayOfResultSet[0] = rsGetVisibleColumns;
      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet); 
    } 
    return visibleColumns; 
  } 
  
   
  public ColumnPreference getVisibleColumnNames(Connection con,int iDocumentId, TPCSUser userInfo)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    CallableStatement cst=null;
	    ResultSet rs = null;    
	    List<String> visibleColumns=new ArrayList<String>();
	    List<String> columnName=new ArrayList<String>();
	    List<String> queryColumn=new ArrayList<String>();
	    List<String> dbFieldName=new ArrayList<String>();
	    List<String> orderBy=new ArrayList<String>();
	    List<String> groupBy=new ArrayList<String>();
	    ColumnPreference List=new ColumnPreference();
	    List<ColumnPreference> cpList=new ArrayList<ColumnPreference>();
	    try{      
	      cst = con.prepareCall("{call pr_getvisiblecolumns(?,?,?)}");
	      cst.setInt(1,iDocumentId);
	      cst.setInt(2,userInfo.getUserId());  
	      cst.setInt(3,2);
	      rs = cst.executeQuery();
	      while(rs.next()){
	    	  ColumnPreference cp=new ColumnPreference();
	    	  cp.setColumnDisplayName(rs.getString(1));
	    	  cp.setOrderByColumn(rs.getString(2));
	    	  cp.setDbFieldName(rs.getString(3));
	    	  cp.setColumnName(rs.getString(4));
	    	  cp.setGroupBy(rs.getString(5));
	    	  cp.setGroupByOrder(rs.getString(6));
	    	  cp.setQueryColumn(rs.getString(7));
	    	  cpList.add(cp);
	    	  
	    	  visibleColumns.add(rs.getString(1));
	    	  if(rs.getString(2)!=null && !rs.getString(2).equals("null")){
	    		  orderBy.add(rs.getString(2));
	    	  }
	    	  if(rs.getString(5)!=null && !rs.getString(5).equals("null")){
	    		  groupBy.add(rs.getString(5));
	    	  }
	    	  dbFieldName.add(rs.getString(3));
	    	  columnName.add(rs.getString(4));
	    	  if(rs.getString(8)!=null && !rs.getString(8).equals("null")){
	    		  queryColumn.add(rs.getString(8));//!=null?rs.getString(7).substring(rs.getString(7).indexOf(".")+1):rs.getString(7));
	    	  }
	    	  
//	    	  fieldName.add(rsGetVisibleColumns.getString(5));
	      }
	      int groupCount=0;
	      cst=con.prepareCall(" SELECT distinct count(group_by_order) over()-1  FROM column_preferences WITH(NOLOCK) WHERE subdocument_id="+iDocumentId+" AND column_visibility_id=1 group by group_by_order ");
	      rs = cst.executeQuery();
	      if(rs.next()){
	    	  groupCount=rs.getInt(1);
	      }
	      List<String> columnsToTotal=new ArrayList<String>();
	      cst=con.prepareCall(" select dbfieldname from column_preferences where column_id in(select column_id from column_to_total where total=1 and subdocument_id="+iDocumentId+") and subdocument_id="+iDocumentId);
	      rs = cst.executeQuery();
	      while(rs.next()){
	    	  columnsToTotal.add(rs.getString(1));
	      }
	      
	      List.setVisibleColumns(visibleColumns);
	      List.setColumnNameList(columnName);
	      List.setDbFieldNameList(dbFieldName);
	      List.setOrderBy(orderBy);
	      List.setGroupByList(groupBy);
	      List.setCpList(cpList);
	      List.setGroupCnt(groupCount);
	      List.setColumnsToTotal(columnsToTotal);
	      List.setQueryColumnList(queryColumn);
	    }finally{ 
	      if(cst!=null)
	        cst.close();
	    
	      ArrayOfPreparedStatement = new PreparedStatement[0];
	      ArrayOfResultSet = new ResultSet[1];      
	      ArrayOfResultSet[0] = rs;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return List;
	  }
  
  
  public Map<String,String> getVisibleColumnNamesMap(Connection con,int iDocumentId, int iUserId)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    CallableStatement cstmtGetVisibleColumns=null;
	    ResultSet rsGetVisibleColumns = null;   
	    Map<String,String> visColMap=new LinkedHashMap<String,String>();
	    try{      
	      cstmtGetVisibleColumns = con.prepareCall("{call pr_getvisiblecolumns(?,?,?)}");
	      cstmtGetVisibleColumns.setInt(1,iDocumentId);
	      cstmtGetVisibleColumns.setInt(2,iUserId);
	      cstmtGetVisibleColumns.setInt(3,2);
	      rsGetVisibleColumns = cstmtGetVisibleColumns.executeQuery();
	      while(rsGetVisibleColumns.next()){
	    		  visColMap.put(rsGetVisibleColumns.getString(3),rsGetVisibleColumns.getString(1));//dbfieldname,displayname
	    	
	      }
	    }finally{
	      if(cstmtGetVisibleColumns!=null)
	        cstmtGetVisibleColumns.close();
	    
	      ArrayOfPreparedStatement = new PreparedStatement[0];
	      ArrayOfResultSet = new ResultSet[1];      
	      ArrayOfResultSet[0] = rsGetVisibleColumns;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return visColMap;
	  }
  public boolean updateColumnPreferences(Connection con, String strTotalColumns,int iDocumentId, int iUserId)throws SQLException,Exception{
    PreparedStatement ArrayOfPreparedStatement[] = null;
    ResultSet ArrayOfResultSet[] = null;   
    PreparedStatement pst=null;
    boolean bFlag = false;
    int iTotal = 0;
    try{      
    	
    	
    	String[] visCol=strTotalColumns.split(",");
    	List<String> visColList=Arrays.asList(visCol);
    	System.out.println("Columns :"+visColList.toString());
    	pst = con.prepareStatement("update column_preferences set column_visibility_id=1,display_order=? where column_id =? and subdocument_id=?");
    	int idx=1;
    	for(String colId:visColList){
    		pst.setInt(1,idx);
    		pst.setInt(2,Integer.parseInt(colId));
    		pst.setInt(3,iDocumentId);
	    	pst.addBatch();
	    	idx++;
    	}
    	pst.executeBatch();
    	
    	pst = con.prepareStatement("update column_preferences set column_visibility_id=2 where column_id not in("+strTotalColumns+") and subdocument_id=?");
    	pst.setInt(1,iDocumentId);
    	pst.executeUpdate();
    	System.out.println(1);
// 
//      pstmtDeleteColumnPreferences = con.prepareStatement(SQLUtil.DELETE_COLUMN_PREFERENCES);
//      pstmtDeleteColumnPreferences.setInt(1, iDocumentId);
//      pstmtDeleteColumnPreferences.setInt(2, iUserId);
//      int iDeleteCount = pstmtDeleteColumnPreferences.executeUpdate();
//      String[] columns = strTotalColumns.split(",");
//      for(String column:columns){
//        String[] values = column.split("C");
//        pstmtUpdateColumnPreferences = con.prepareStatement(SQLUtil.INSERT_COLUMN_PREFERENCES);
//        pstmtUpdateColumnPreferences.setInt(1, Integer.parseInt(values[0]));
//        pstmtUpdateColumnPreferences.setInt(2, iDocumentId);
//        pstmtUpdateColumnPreferences.setInt(3, iUserId);
//        pstmtUpdateColumnPreferences.setInt(4, Integer.parseInt(values[1]));
//        int iCount = pstmtUpdateColumnPreferences.executeUpdate();
//        iTotal = iTotal+iCount;
//      }
////      System.out.println("iTotal--->"+iTotal);
////      System.out.println("columns.length--->"+columns.length);
////      System.out.println("iDeleteCount--->"+iDeleteCount);
//       if(iTotal==columns.length && iDeleteCount == columns.length){
//    	   bFlag=true;
//       }
       
    }finally{
      DatabaseConnection.closeAll(pst,null);
    }
    return bFlag;
  }
  
  public ColumnPreference updateColumnPreferences(Connection con, ColumnPreference cp,int iDocumentId, int iUserId,int type)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;   
	    PreparedStatement pst=null;
	    CallableStatement cst=null;
	    ResultSet rs=null;
	    Statement st=null;
	    int iTotal = 0;
	    try{    
	    	
	    	int oldDocumentId=iDocumentId;
	    	int newDocumentId=0;
	    	
	    	Map<Integer,String> groupByOrder=cp.getMapGroupByOrder();
	    	Map<String,String> mapColumnToTotal=cp.getMapColumnToTotal();
	    	List<String> criteriaIds=cp.getCriteriaIds();
	    	
	    	System.out.println("groupByOrder :"+groupByOrder.toString());
	    	
	    	st=con.createStatement();
	    	System.out.println("type :"+type);
	    	if(type==1){
	    		cst=con.prepareCall("{ ? = call pr_create_new_customize_report(?,?,?)}");
	    		cst.registerOutParameter(1, Types.INTEGER);
	    		cst.setInt(2, iDocumentId);
	    		cst.setString(3, cp.getReportName());
	    		cst.setString(4, cp.getReportDesc());
	    		cst.executeUpdate();
	    		newDocumentId= cst.getInt(1);
 				System.out.println("newDocumentId :"+newDocumentId);
 				
 				System.out.println("cp.getVisColumns():"+cp.getVisColumns());
 				
 				//Visibility
 				List<String> newCpColId=new ArrayList<String>();
 				pst=con.prepareStatement("select column_id from column_preferences where subdocument_id="+newDocumentId+" and dbfieldname in(select dbfieldname from column_preferences where column_id in("+cp.getVisColumns()+") and subdocument_id="+oldDocumentId+")");
 				rs=pst.executeQuery();
 				while(rs.next()){
 					newCpColId.add(rs.getString(1));
 				}
 				
 				pst = con.prepareStatement("update column_preferences set column_visibility_id=1,display_order=? where column_id=? and subdocument_id=?");
 				int ix=1;
 				for(String colId:newCpColId){
 					pst.setInt(1, ix);
 					pst.setString(2, colId);
 					pst.setInt(3, newDocumentId);
 					pst.addBatch();
 			    	ix++;
 				}
 				if(newCpColId.size()>0){
 					pst.executeBatch();
 					
 					pst = con.prepareStatement("update column_preferences set column_visibility_id=2 where column_id not in("+newCpColId.toString().replace("[", "").replace("]", "")+") and subdocument_id=?");
 	 		    	pst.setInt(1,newDocumentId);
 	 		    	pst.executeUpdate();
 				}
 				
 				
 		    	
 		    	//Group by Order
 		    	
// 		    	if(groupByOrder.size()>0){
 		    		pst = con.prepareStatement("update column_preferences set group_by_order =null where subdocument_id=?");
 			    	pst.setInt(1,newDocumentId);
 			    	pst.executeUpdate();
 			    	
 			    	pst = con.prepareStatement("update column_preferences set orderby_column=group_by where subdocument_id=?");
 			    	pst.setInt(1,newDocumentId);
 			    	pst.executeUpdate();
// 		    	}
 			    	Set<Integer> keys=groupByOrder.keySet();
 		    	List<Integer> oldGroupByOrderColId=new ArrayList<Integer>();
 		    	oldGroupByOrderColId.addAll(keys);

 		    	List<Integer> newGroupByOrderColId=new ArrayList<Integer>();
 		    	if(oldGroupByOrderColId.size()>0){
	 				pst=con.prepareStatement("select column_id from column_preferences where subdocument_id="+newDocumentId+" and dbfieldname in(select dbfieldname from column_preferences where column_id in("+oldGroupByOrderColId.toString().replace("[","").replace("]", "")+") and subdocument_id="+oldDocumentId+")");
	 				rs=pst.executeQuery();
	 				while(rs.next()){
	 					newGroupByOrderColId.add(rs.getInt(1));
	 				}
 		    	}
 		    	
// 		    	Iterator it= groupByOrder.entrySet().iterator();
// 		    	while (it.hasNext()) {
//		    		 Map.Entry gbo= (Map.Entry)it.next();
//		    		 newGroupByOrderColId.add((Integer)gbo.getKey());
//		    			st.addBatch("update column_preferences set orderby_column=group_by+' "+gbo.getValue()+"' where column_id="+gbo.getKey()+" and subdocument_id="+newDocumentId);
// 		    	}
// 		    	if(groupByOrder.size()>0){
// 		    	 st.executeBatch();
// 		    	}
 		    	
 		    	int gi=1;
 		    	pst = con.prepareStatement("update column_preferences set group_by_order =? where column_id=? and subdocument_id=?");
 		    	Iterator git= groupByOrder.entrySet().iterator();
 		    	while (git.hasNext()) {
		    		 Map.Entry gbo= (Map.Entry)git.next();
 		    		pst.setInt(1,gi);
 		    		pst.setInt(2,newGroupByOrderColId.get(gi-1));
 		 	    	pst.setInt(3,newDocumentId);
 		 	    	pst.addBatch();
 		 	    	st.addBatch("update column_preferences set orderby_column=group_by+' "+gbo.getValue()+"' where column_id="+newGroupByOrderColId.get(gi-1)+" and subdocument_id="+newDocumentId);
 		 	    	st.addBatch("update column_preferences set order_by='"+gbo.getValue()+"' where column_id="+newGroupByOrderColId.get(gi-1)+" and subdocument_id="+newDocumentId);
 		 	    	gi++;
 		    	 }
 		    	 if(gi>1){
 		    		 pst.executeBatch();
 		    		st.executeBatch();
 		    	 }
 		    	 
 		    	pst = con.prepareStatement("update column_preferences set group_by_order=(select max(isnull(group_by_order,0))+1 from column_preferences where subdocument_id=?) where group_by_order is null and subdocument_id=?");
 		    	pst.setInt(1,newDocumentId);
 		    	pst.setInt(2,newDocumentId);
 		    	pst.executeUpdate();
 		    	
 		    	
 		    	List<Integer> columnToTotalColId=cp.getColumnToTotalColId();
 		    	
 		    	List<Integer> newCpColTotTotalColId=new ArrayList<Integer>();
 		    	
 		    	if(columnToTotalColId.size()>0){
 		    		
	 		    	System.out.println("select column_id from column_preferences where subdocument_id="+newDocumentId+" and dbfieldname in(select dbfieldname from column_preferences where column_id in("+columnToTotalColId.toString().replace("[", "").replace("]", "")+") and subdocument_id="+oldDocumentId+")");
	 				pst=con.prepareStatement("select column_id from column_preferences where subdocument_id="+newDocumentId+" and dbfieldname in(select dbfieldname from column_preferences where column_id in("+columnToTotalColId.toString().replace("[", "").replace("]", "")+") and subdocument_id="+oldDocumentId+")");
	 				rs=pst.executeQuery();
	 				while(rs.next()){
	 					newCpColTotTotalColId.add(rs.getInt(1));
	 				}
	 				
	 				int k=0;
	 		    	for(int colId:columnToTotalColId){
	 		    		String total=mapColumnToTotal.get("chk_"+colId+"_1")!=null&&!mapColumnToTotal.get("chk_"+colId+"_1").equals("null")?mapColumnToTotal.get("chk_"+colId+"_1"):"0";
	 		    		String avg=mapColumnToTotal.get("chk_"+colId+"_2")!=null&&!mapColumnToTotal.get("chk_"+colId+"_2").equals("null")?mapColumnToTotal.get("chk_"+colId+"_2"):"0";
	 		    		String min=mapColumnToTotal.get("chk_"+colId+"_3")!=null&&!mapColumnToTotal.get("chk_"+colId+"_3").equals("null")?mapColumnToTotal.get("chk_"+colId+"_3"):"0";
	 		    		String max=mapColumnToTotal.get("chk_"+colId+"_4")!=null&&!mapColumnToTotal.get("chk_"+colId+"_4").equals("null")?mapColumnToTotal.get("chk_"+colId+"_4"):"0";
	 		    		st.addBatch("update column_to_total set total="+total+" ,column_id="+newCpColTotTotalColId.get(k)+" where subdocument_id="+newDocumentId+" and column_id="+colId);
	 		    		st.addBatch("update column_to_total set average="+avg+" ,column_id="+newCpColTotTotalColId.get(k)+" where subdocument_id="+newDocumentId+" and column_id="+colId);
	 		    		st.addBatch("update column_to_total set minimum="+min+" ,column_id="+newCpColTotTotalColId.get(k)+" where subdocument_id="+newDocumentId+" and column_id="+colId);
	 		    		st.addBatch("update column_to_total set maximum="+max+" ,column_id="+newCpColTotTotalColId.get(k)+" where subdocument_id="+newDocumentId+" and column_id="+colId);
	 		    		k++;
	 		    	}
	 		    	if(columnToTotalColId.size()>0){
	 		    		st.executeBatch();
	 		    	}
 		    	}
 		    	
 		    	
 		    	
 		    	
 		    	if(criteriaIds.size()>0){
 			    	pst = con.prepareStatement("delete from column_for_criteria where subdocument_id=?");
 			    	pst.setInt(1,newDocumentId);
 			    	pst.executeUpdate();
 		    	}
 		    	
 		    	Map<String,String> mapCriteriaQuery=cp.getMapCriteriaQuery();
 		    	if(mapCriteriaQuery.size()>0){
 		    		for(String ids:criteriaIds){
 		    			pst = con.prepareStatement("insert into column_for_criteria(Criteria_id,Subdocument_id,Report_id,Criteria_Display,Criteria_Db,criteria_db_having) values(?,?,?,?,?,?)");
 		    			pst.setString(1, ids);
 		    			pst.setInt(2, newDocumentId);
 		    			pst.setInt(3, 1);
 		    			pst.setString(4, mapCriteriaQuery.get("query_"+ids));
 		    			pst.setString(5, mapCriteriaQuery.get("db_query_"+ids));
 		    			pst.setString(6, mapCriteriaQuery.get("db_having_query_"+ids));
 		    			pst.executeUpdate();
 		    		}
 		    	}
	    	}
	    	else{
	    	
	    	String[] visCol=cp.getVisColumns().split(",");
	    	List<String> visColList=Arrays.asList(visCol);
	    	pst = con.prepareStatement("update column_preferences set column_visibility_id=1,display_order=? where column_id =? and subdocument_id=?");
	    	int idx=1;
	    	for(String colId:visColList){
	    		pst.setInt(1,idx);
	    		pst.setInt(2,Integer.parseInt(colId));
	    		pst.setInt(3,iDocumentId);
		    	pst.addBatch();
		    	idx++;
	    	}
	    	pst.executeBatch();
	    	
	    	pst = con.prepareStatement("update column_preferences set column_visibility_id=2 where column_id not in("+cp.getVisColumns()+") and subdocument_id=?");
	    	pst.setInt(1,iDocumentId);
	    	pst.executeUpdate();
	    	
	    
	    	
//	    	if(groupByOrder.size()>0){
	    		pst = con.prepareStatement("update column_preferences set group_by_order =null where subdocument_id=?");
		    	pst.setInt(1,iDocumentId);
		    	pst.executeUpdate();
		    	
		    	pst = con.prepareStatement("update column_preferences set orderby_column=group_by where subdocument_id=?");
		    	pst.setInt(1,iDocumentId);
		    	pst.executeUpdate();
//	    	}
	    	pst = con.prepareStatement("update column_preferences set group_by_order =? where column_id=? and subdocument_id=?");
	    	
	    	int i=1;
	    	Iterator it= groupByOrder.entrySet().iterator();
	    	 while (it.hasNext()) {
	    		 Map.Entry gbo= (Map.Entry)it.next();
	    		pst.setInt(1,i);
	    		pst.setInt(2,(Integer)gbo.getKey());
	 	    	pst.setInt(3,iDocumentId);
	 	    	pst.addBatch();
	 	    	
	 	    	st.addBatch("update column_preferences set orderby_column=group_by+' "+gbo.getValue()+"' where column_id="+gbo.getKey()+" and subdocument_id="+iDocumentId);
	 	    	st.addBatch("update column_preferences set order_by='"+gbo.getValue()+"' where column_id="+gbo.getKey()+" and subdocument_id="+iDocumentId);
	    				 
	    		 i++;
	    	 }
	    	 if(i>1){
	    		 pst.executeBatch();
	    		 st.executeBatch();
	    	 }
	    	 
	    	pst = con.prepareStatement("update column_preferences set group_by_order=(select max(isnull(group_by_order,0))+1 from column_preferences where subdocument_id=?) where group_by_order is null and subdocument_id=?");
	    	pst.setInt(1,iDocumentId);
	    	pst.setInt(2,iDocumentId);
	    	pst.executeUpdate();
	    	
	    	List<Integer> columnToTotalColId=cp.getColumnToTotalColId();
//	    	Map<String,String> mapColumnToTotal=cp.getMapColumnToTotal();
	    	for(int colId:columnToTotalColId){
	    		String total=mapColumnToTotal.get("chk_"+colId+"_1")!=null&&!mapColumnToTotal.get("chk_"+colId+"_1").equals("null")?mapColumnToTotal.get("chk_"+colId+"_1"):"0";
	    		String avg=mapColumnToTotal.get("chk_"+colId+"_2")!=null&&!mapColumnToTotal.get("chk_"+colId+"_2").equals("null")?mapColumnToTotal.get("chk_"+colId+"_2"):"0";
	    		String min=mapColumnToTotal.get("chk_"+colId+"_3")!=null&&!mapColumnToTotal.get("chk_"+colId+"_3").equals("null")?mapColumnToTotal.get("chk_"+colId+"_3"):"0";
	    		String max=mapColumnToTotal.get("chk_"+colId+"_4")!=null&&!mapColumnToTotal.get("chk_"+colId+"_4").equals("null")?mapColumnToTotal.get("chk_"+colId+"_4"):"0";
	    				st.addBatch("update column_to_total set total="+total+" where subdocument_id="+iDocumentId+" and column_id="+colId);
	    				st.addBatch("update column_to_total set average="+avg+" where subdocument_id="+iDocumentId+" and column_id="+colId);
	    				st.addBatch("update column_to_total set minimum="+min+" where subdocument_id="+iDocumentId+" and column_id="+colId);
	    				st.addBatch("update column_to_total set maximum="+max+" where subdocument_id="+iDocumentId+" and column_id="+colId);
	    	}
	    	if(columnToTotalColId.size()>0){
	    		st.executeBatch();
	    	}
	    	
//	    	List<String> criteriaIds=cp.getCriteriaIds();
	    	if(criteriaIds.size()>0){
		    	pst = con.prepareStatement("delete from column_for_criteria where subdocument_id=?");
		    	pst.setInt(1,iDocumentId);
		    	pst.executeUpdate();
	    	}
	    	
	    	Map<String,String> mapCriteriaQuery=cp.getMapCriteriaQuery();
	    	if(mapCriteriaQuery.size()>0){
	    		for(String ids:criteriaIds){
	    			pst = con.prepareStatement("insert into column_for_criteria(Criteria_id,Subdocument_id,Report_id,Criteria_Display,Criteria_Db,criteria_db_having) values(?,?,?,?,?,?)");
	    			pst.setString(1, ids);
	    			pst.setInt(2, iDocumentId);
	    			pst.setInt(3, 1);
	    			pst.setString(4, mapCriteriaQuery.get("query_"+ids));
	    			pst.setString(5, mapCriteriaQuery.get("db_query_"+ids));
	    			pst.setString(6, mapCriteriaQuery.get("db_having_query_"+ids));
	    			pst.executeUpdate();
	    		}
	    	}
	    	}
//	    	Iterator itr= mapCriteriaQuery.entrySet().iterator();
//	    	 while (itr.hasNext()) {
//	    		 Map.Entry gbo= (Map.Entry)itr.next();
//	    		 pst = con.prepareStatement("insert into column_for_criteria(Criteria_id,Subdocument_id,Report_id,Criteria_Display,Criteria_Db,criteria_db_having) values(?,?,?,?,?,?)");
//	    		 String key=(String) gbo.getKey();
//	    		 pst.setString(1, key.substring(key.length() - 1));
//	    		 pst.setInt(2, iDocumentId);
//	    		 pst.setInt(3, 1);
//	    		 pst.setInt(4, gbo.get);
//	    	 }
//	    	if(!cp.getFinalQuery().isEmpty()){
//		    	pst = con.prepareStatement("update column_for_criteria set criteria_display=? ,criteria_db=?,criteria_db_having=? where subdocument_id=? and report_id=?");
//		    	pst.setString(1,cp.getFinalQuery());
//		    	pst.setString(2,cp.getFinalDbQuery());
//		    	pst.setString(3,cp.getFinalDbHavingQuery());
//		    	pst.setInt(4, iDocumentId);
//		    	pst.setInt(5, 1);
//		    	pst.executeUpdate();
//	    	}
	    	
	    	
//	      pstmtDeleteColumnPreferences = con.prepareStatement(SQLUtil.DELETE_COLUMN_PREFERENCES);
//	      pstmtDeleteColumnPreferences.setInt(1, iDocumentId);
//	      pstmtDeleteColumnPreferences.setInt(2, iUserId);
//	      int iDeleteCount = pstmtDeleteColumnPreferences.executeUpdate();
//	      String[] columns = cp.getVisColumns().split(",");
//	      for(String column:columns){
//	        String[] values = column.split("C");
//	        pstmtUpdateColumnPreferences = con.prepareStatement(SQLUtil.INSERT_COLUMN_PREFERENCES);
//	        pstmtUpdateColumnPreferences.setInt(1, Integer.parseInt(values[0]));
//	        pstmtUpdateColumnPreferences.setInt(2, iDocumentId);
//	        pstmtUpdateColumnPreferences.setInt(3, iUserId);
//	        pstmtUpdateColumnPreferences.setInt(4, Integer.parseInt(values[1]));
//	        int iCount = pstmtUpdateColumnPreferences.executeUpdate();
//	        iTotal = iTotal+iCount;
//	      }
////	      System.out.println("iTotal--->"+iTotal);
////	      System.out.println("columns.length--->"+columns.length);
////	      System.out.println("iDeleteCount--->"+iDeleteCount);
//	       if(iTotal==columns.length && iDeleteCount == columns.length){
//	    	   bFlag=true;
//	       }
	       
	    }finally{
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pst;
	      ArrayOfResultSet = new ResultSet[0];      
	      //ArrayOfResultSet[0] = rsInsertColumns;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return cp;
	  }
  
  public ColumnPreference getDataForReportCustomize(Connection con,int iDocumentId, TPCSUser userInfo)throws SQLException,Exception{
	    PreparedStatement pst=null;
	    ResultSet rs= null;   
	    ColumnPreference colPre=new ColumnPreference();
	    
	    try{
	    	List<ColumnPreference> groupByColumnList=new ArrayList<ColumnPreference>();
	      pst= con.prepareStatement("select column_id,column_display_name,group_by_order from column_preferences where subdocument_id=? and group_by_column=1");
	      pst.setInt(1,iDocumentId);
	      rs= pst.executeQuery();
	      while(rs.next()){
	    	  ColumnPreference cp=new ColumnPreference();
	    	  cp.setColumnId(rs.getString(1));
	    	  cp.setColumnDisplayName(rs.getString(2));
	    	  cp.setGroupByOrder(rs.getString(3));
	    	  groupByColumnList.add(cp);
	      }
	      colPre.setGroupByColumnList(groupByColumnList);
	      
	      colPre.setFirstOrderBy("asc");
	      colPre.setSecondOrderBy("asc");
	      colPre.setThirdOrderBy("asc");
	      
	      pst= con.prepareStatement("select isnull(order_by,'asc'),group_by_order from column_preferences where subdocument_id=? and group_by_order < (select max(group_by_order) from column_preferences where subdocument_id=?)");
	      pst.setInt(1,iDocumentId);
	      pst.setInt(2,iDocumentId);
	      rs= pst.executeQuery();
	      while(rs.next()){
	    	  if(rs.getInt(2)==1){
	    		  colPre.setFirstOrderBy(rs.getString(1));
	    	  }
	    	  else if(rs.getInt(2)==2){
	    		  colPre.setSecondOrderBy(rs.getString(1));
	    	  }
	    	  else if(rs.getInt(2)==3){
	    		  colPre.setThirdOrderBy(rs.getString(1));
	    	  }
	      }
	      
	      System.out.println("col pre");
	      System.out.println(1);
	      List<Integer> columnTotalColIds=new ArrayList<Integer>();
		  List<ColumnPreference> columnToToalList=new ArrayList<ColumnPreference>();
	      pst= con.prepareStatement("select cp.column_id,cp.column_display_name,ctt.total,ctt.average,ctt.minimum,ctt.maximum from column_preferences cp "
	      		+ " inner join column_to_total ctt with(nolock) on cp.column_id=ctt.column_id and cp.subdocument_id=ctt.subdocument_id where cp.subdocument_id=?");
	      pst.setInt(1,iDocumentId);
	      rs= pst.executeQuery();
	      while(rs.next()){
	    	  ColumnPreference cp=new ColumnPreference();
	    	  cp.setColumnId(rs.getString(1));
	    	  cp.setColumnDisplayName(rs.getString(2));
	    	  cp.setTotal(rs.getInt(3));
	    	  cp.setAverage(rs.getInt(4));
	    	  cp.setMinimum(rs.getInt(5));
	    	  cp.setMaximum(rs.getInt(6));
	    	  columnToToalList.add(cp);
	    	  columnTotalColIds.add(rs.getInt(1));
	      }
	      colPre.setColumnToTotalList(columnToToalList);
	      colPre.setColumnToTotalColId(columnTotalColIds);
	      
	      List<ColumnPreference> operatorList=new ArrayList<ColumnPreference>();
	      pst= con.prepareStatement("select data_type,operator_id,operator,operator_db from column_operator order by data_type,operator_id");
	      rs= pst.executeQuery();
	      while(rs.next()){
	    	  ColumnPreference cp=new ColumnPreference();
	    	  cp.setDataType(rs.getInt(1));
	    	  cp.setOperatorId(rs.getInt(2));
	    	  cp.setOperator(rs.getString(3));
	    	  cp.setOperatorDb(rs.getString(4));
	    	  operatorList.add(cp);
	      }
	      colPre.setOperatorList(operatorList);
	      
	      List<ColumnPreference> columnList=new ArrayList<ColumnPreference>();
	      pst= con.prepareStatement("select column_id,column_display_name,query_column,datatypeid from column_preferences where subdocument_id=?");
	      pst.setInt(1,iDocumentId);
	      rs= pst.executeQuery();
	      while(rs.next()){
	    	  ColumnPreference cp=new ColumnPreference();
	    	  cp.setColumnId(rs.getString(1));
	    	  cp.setColumnDisplayName(rs.getString(2));
	    	  cp.setColumnName(rs.getString(3));
	    	  cp.setDataType(rs.getInt(4));
	    	  columnList.add(cp);
	      }
	      colPre.setColumnList(columnList);
	      
	      List<ColumnPreference > criteriaQuery=new ArrayList<ColumnPreference >();
	      pst= con.prepareStatement("select criteria_id,criteria_display,criteria_db,criteria_db_having from column_for_criteria where subdocument_id=? and report_id=1");
	      pst.setInt(1,iDocumentId);
	      rs= pst.executeQuery();
	      while(rs.next()){
	    	  ColumnPreference cp=new ColumnPreference();
	    	  cp.setCriteriaId(rs.getInt(1));
	    	  cp.setFinalQuery(rs.getString(2));
	    	  cp.setFinalDbQuery(rs.getString(3));
	    	  cp.setFinalDbHavingQuery(rs.getString(4));
	    	  criteriaQuery.add(cp);
	      }
	      colPre.setCriteriaQuery(criteriaQuery);
	      
	      
	      pst= con.prepareStatement("select max(isnull(group_by_order,0)) from column_preferences where subdocument_id=?");
	      pst.setInt(1,iDocumentId);
	      rs= pst.executeQuery();
	      if(rs.next()){
	    	  colPre.setGroupCnt(rs.getInt(1));
	      }
	      
	      pst= con.prepareStatement("select max(isnull(criteria_id,0))+1 from column_for_criteria where subdocument_id=?");
	      pst.setInt(1,iDocumentId);
	      rs= pst.executeQuery();
	      if(rs.next()){
	    	  colPre.setCriteriaRowCnt(rs.getInt(1));
	      }
	      
	      pst= con.prepareStatement("select subdocument_name,description,url from subdocuments where subdocument_id=?");
	      pst.setInt(1,iDocumentId);
	      rs= pst.executeQuery();
	      if(rs.next()){
	    	  colPre.setReportName(rs.getString(1));
	    	  colPre.setReportDesc(rs.getString(2));
	    	  colPre.setReportUrl(rs.getString(3));
	      }
	      
	      
	    }finally{
	      DatabaseConnection.closeAll(pst,rs); 
	    } 
	    return colPre; 
	  }
  public ColumnPreference saveReportName(Connection con,ColumnPreference cp ,int iDocumentId, TPCSUser userInfo)throws SQLException,Exception{
	  	PreparedStatement pst=null;
	    try{      
	    		pst= con.prepareStatement("update subdocuments set subdocument_name=?,description=? where subdocument_id=?");
	    		pst.setString(1,cp.getReportName());
	    		pst.setString(2,cp.getReportDesc());
	    		pst.setInt(3,iDocumentId);
	    		int cnt=pst.executeUpdate();
	    		cp.setSaved(cnt>0?true:false);
	    }finally{
	      DatabaseConnection.closeAll(pst,null); 
	    } 
	    return cp; 
	  } 
  public ColumnPreference deleteReport(Connection con,int iDocumentId)throws SQLException,Exception{
	  	PreparedStatement pst=null;
	  	ColumnPreference cp=new ColumnPreference();
	    try{      
	    		pst= con.prepareStatement("delete from subdocuments where subdocument_id=?");
	    		pst.setInt(1,iDocumentId);
	    		pst.executeUpdate();
	    		
	    		pst= con.prepareStatement("delete from tpcs_user_rights where subdocument_id=?");
	    		pst.setInt(1,iDocumentId);
	    		pst.executeUpdate();
	    		
	    		pst= con.prepareStatement("delete from column_preferences where subdocument_id=?");
	    		pst.setInt(1,iDocumentId);
	    		pst.executeUpdate();
	    		
	    		pst= con.prepareStatement("delete from entity_rights where subdocument_id=?");
	    		pst.setInt(1,iDocumentId);
	    		pst.executeUpdate();
	    		
	    		pst= con.prepareStatement("delete from profile_detail where subdocument_id=?");
	    		pst.setInt(1,iDocumentId);
	    		pst.executeUpdate();
	    		
	    		pst= con.prepareStatement("delete from column_to_total where subdocument_id=?");
	    		pst.setInt(1,iDocumentId);
	    		pst.executeUpdate();
	    		
	    		pst= con.prepareStatement("delete from column_for_criteria where subdocument_id=?");
	    		pst.setInt(1,iDocumentId);
	    		pst.executeUpdate();
	    		
	    		cp.setSaved(true);
	    }finally{
	      DatabaseConnection.closeAll(pst,null); 
	    } 
	    return cp; 
	  } 
}
