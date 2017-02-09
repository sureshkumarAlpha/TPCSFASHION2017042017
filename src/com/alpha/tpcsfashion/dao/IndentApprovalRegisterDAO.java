package com.alpha.tpcsfashion.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
public class IndentApprovalRegisterDAO {
	 public List<Map<String,String>> getAllIndentApprovalRegisterOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,String strSearchCriteria,ColumnPreference DataList,int subdocumentId)throws SQLException,Exception{
		    CallableStatement cstmt=null;
		    PreparedStatement pst=null;
		    ResultSet rs = null; 
		    
		    List<Map<String,String>> assortList=new ArrayList<Map<String,String>>();
		    Map<String,String> ColNameValuMap=null;
//		    String strbuffer="";
		    try{
		    	List<String> orderBy=DataList.getOrderBy();
		    	List<String> groupBy=DataList.getGroupByList();
		    	
		    	StringBuffer dbQuery=new StringBuffer("");
		    	StringBuffer  dbHavingQuery=new StringBuffer("");
		    	pst= con.prepareStatement("select criteria_db,criteria_db_having from column_for_criteria where subdocument_id=?");
		    	pst.setInt(1,subdocumentId);
		    	rs= pst.executeQuery();
		    	while(rs.next()){
		    		dbQuery.append(rs.getString(1));
		    		dbHavingQuery.append(rs.getString(2));
		    	}
		    	
		    	ColumnPreference paging=DataList.getPaging();
		       	String pageCondition="";
		    	if(paging.isGroupPaging()){
		    		
		    		StringBuffer buffer=new StringBuffer();
		    		buffer.append(" select group_name from("
    				+ " select group_name , row_number() over (order by "+(paging.getFirstGroupDataTypeId()!=5?" group_name ":"convert(datetime,group_name,105)")+" "+(paging.isDescending()==true?" desc ":"")+") as rownum"
    				+ " from("
    				+ " select   distinct "+(paging.getFirstGroupDataTypeId()!=5?paging.getGroupPagingName():"convert(nvarchar,"+paging.getGroupPagingName()+",105)")+" as group_name"
	    			+ "  from indent ind "	
	    			+ " left join Indent_Details inddet on inddet.Indent_Id=ind.Indent_Id "	
	    			+ " left join party p on p.party_id=inddet.party_id "
	    			+ " left join Variant_Master v on v.variant_id=inddet.variant_id " 	
	    			+ " where ind.company_Id="+userInfo.getCompanyId()+" and ind.year_id="+userInfo.getYearId()+" and ind.location_id="+userInfo.getLocationId()+"  and isnull(ind.cancel_tag,0)<>1 ");
		    		
		    		if(strSearchCriteria!=null && !strSearchCriteria.isEmpty()){
		    			buffer.append(strSearchCriteria);
			    	}
			    	if(!dbQuery.toString().isEmpty()){
			    		buffer.append(dbQuery.toString());
			    	}
			    	if(groupBy!=null && !groupBy.isEmpty()){
			    		buffer.append(" group by "+groupBy.toString().replace("[", "").replace("]", ""));
			    		if(!dbHavingQuery.toString().isEmpty()){
			    			buffer.append(" having "+dbHavingQuery.toString().substring(4));
			    		}
			    	}
		    		buffer.append(") as item"
	    			+ " )as it"
	    			+ " where rownum ="+pc.getPageNo());
		    		
		    	pst= con.prepareStatement(buffer.toString());
		    	rs=pst.executeQuery();
		    	if(rs.next()){
		    		if(paging.getFirstGroupDataTypeId()==5){
		    			pageCondition=" and "+paging.getGroupPagingName()+"=convert(datetime,'"+rs.getString(1)+"',105)";	
		    		}
		    		else{
//		    			System.out.println("paging.getGroupPagingName()==="+paging.getGroupPagingName());
//		    			System.out.println("rs.getString(1)==="+rs.getString(1));
		    			pageCondition=" and "+paging.getGroupPagingName()+"='"+rs.getString(1).replace("'", "''")+"'";
		    		}
		    		
		    	}
		    	buffer=null;
		    	}
		    	
		    
		    	List<ColumnPreference> cpList=DataList.getCpList();
		    	StringBuffer query = new StringBuffer("select  "+DataList.getColumnNameList().toString().replace("[", "").replace("]", "")+""
		    			+ "  from indent ind "	
		    			+ " left join Indent_Details inddet on inddet.Indent_Id=ind.Indent_Id "	
		    			+ " left join party p on p.party_id=inddet.party_id "
		    			+ " left join Variant_Master v on v.variant_id=inddet.variant_id " 	
		    			+ " where ind.company_Id="+userInfo.getCompanyId()+" and ind.year_id="+userInfo.getYearId()+" and ind.location_id="+userInfo.getLocationId()+"  and isnull(ind.cancel_tag,0)<>1 ");
		
		    	
		    	query.append(pageCondition);
		    	
		    	if(strSearchCriteria!=null && !strSearchCriteria.isEmpty()){
		    		query.append(strSearchCriteria);
		    	}
		    	if(!dbQuery.toString().isEmpty()){
		    		query.append(dbQuery.toString());
		    	}
		    	
		    	//System.out.println("orderBy:"+orderBy.toString());
		    	if(groupBy!=null && !groupBy.isEmpty()){
		    		query.append(" group by "+groupBy.toString().replace("[", "").replace("]", ""));
		    		if(!dbHavingQuery.toString().isEmpty()){
		    			query.append(" having "+dbHavingQuery.toString().substring(4));
		    		}
		    	}
		    	if(orderBy!=null && !orderBy.isEmpty()){
		    		query.append(" order by "+orderBy.toString().replace("[", "").replace("]", "")); 
		    	}
		    	
		    	if(paging.isGroupPaging()){
		    		cstmt= con.prepareCall(query.toString());
		    		rs=cstmt.executeQuery();
			    	ResultSetMetaData rsmd=rs.getMetaData();
			    	//System.out.println("rsmd.getColumnCount() :"+rsmd.getColumnCount());
			    	while(rs.next()){  
			    		ColNameValuMap = new LinkedHashMap<String, String>();
			    		for(int i=1;i<=rsmd.getColumnCount();i++){
			    			ColNameValuMap.put(cpList.get(i-1).getDbFieldName(),rs.getString(i)!=null&&!rs.getString(i).equals("null")?rs.getString(i):"");
			    		}
			    		assortList.add(ColNameValuMap);
			    		ColNameValuMap=null;
			      }
		    	}
		    	else{
//		    		System.out.println("query.toString() pp :"+query.toString());
		    		cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");
			    	cstmt.setString(1,query.toString());
			    	cstmt.setInt(2,pc.iStart);
			    	cstmt.setInt(3,pc.iEnd);
			    	rs=cstmt.executeQuery();
			    	ResultSetMetaData rsmd=rs.getMetaData();
			    //	System.out.println("rsmd.getColumnCount() :"+rsmd.getColumnCount());
			    	while(rs.next()){  
			    		ColNameValuMap = new LinkedHashMap<String, String>();
			    		for(int i=1;i<=rsmd.getColumnCount()-1;i++){
			    			ColNameValuMap.put(cpList.get(i-1).getDbFieldName(),rs.getString(i+1)!=null&&!rs.getString(i+1).equals("null")?rs.getString(i+1):"");
			    		}
			    		assortList.add(ColNameValuMap);
			    		ColNameValuMap=null;
			      }
		    	}
		    	dbQuery=null;
		    	dbHavingQuery=null;
		    	
		    	query=null;
		      
		    }
		    finally{
		          
		        DatabaseConnection.closeAll(cstmt,rs);
		        DatabaseConnection.closeAll(pst,null);
		      }
		    return assortList;
		  }



public ColumnPreference getTotalPages(Connection con,TPCSUser userInfo, String strSearchCriteria,ColumnPreference DataList, int subdocumentId) throws SQLException, Exception {
	PreparedStatement pst = null;
	ResultSet rs = null;
	int iCount = 0;
	ColumnPreference cp=new ColumnPreference();
	try {
//		  List<String> orderBy=DataList.getOrderBy();
    	  List<String> groupBy=DataList.getGroupByList();
		 StringBuffer dbQuery=new StringBuffer("");
    	  StringBuffer  dbHavingQuery=new StringBuffer("");
    	  pst= con.prepareStatement("select criteria_db,criteria_db_having from column_for_criteria where subdocument_id=?");
    	  pst.setInt(1,subdocumentId);
    	  rs= pst.executeQuery();
    	  while(rs.next()){
    		  dbQuery.append(rs.getString(1));
    		  dbHavingQuery.append(rs.getString(2));
    	  }
		
		int groupCnt=0;
		String firstGroupName="";
		int groupDataTypeId=0;
		boolean descending=false;
		  pst= con.prepareStatement("select count(column_name) from column_preferences  where subdocument_id="+subdocumentId+" and column_visibility_id=1  "
					+ " and group_by_order =( select min(group_by_order) from column_preferences where subdocument_id="+subdocumentId+" and column_visibility_id=1 )");
	      rs= pst.executeQuery();
	      while(rs.next()){
	    	  groupCnt=rs.getInt(1);  
//	    	  System.out.println("group cnt :"+groupCnt);
	      }
	      
	      if(groupCnt==1){
	    	  cp.setGroupPaging(true);
	    	  pst= con.prepareStatement(" select query_column,datatypeid,isnull(order_by,'asc') as order_by from column_preferences "
	    	  		+ " where subdocument_id="+subdocumentId+" and column_visibility_id=1  and group_by_order =("
	    	  		+ " select min(group_by_order) from column_preferences where subdocument_id="+subdocumentId+" and column_visibility_id=1 ) ");
	    	  rs= pst.executeQuery();
		      while(rs.next()){
		    	  firstGroupName=rs.getString(1);  
		    	  groupDataTypeId=rs.getInt(2);
		    	  descending=rs.getString(3).equalsIgnoreCase("desc")?true:false;
		      }
		      cp.setGroupPagingName(firstGroupName);
		      cp.setFirstGroupDataTypeId(groupDataTypeId);
		      cp.setDescending(descending);
		      StringBuffer buffer=new StringBuffer();
		      buffer.append(" select count(group_name) from("
	      		+ " select group_name, row_number() over (order by group_name) as rownum "
	      		+ " from("
	      		+ " select   distinct "+(cp.getFirstGroupDataTypeId()!=5?cp.getGroupPagingName():"convert(nvarchar,"+cp.getGroupPagingName()+",105)")+" as group_name"
	      		+ "  from indent ind "	
    			+ " left join Indent_Details inddet on inddet.Indent_Id=ind.Indent_Id "	
    			+ " left join party p on p.party_id=inddet.party_id "
    			+ " left join Variant_Master v on v.variant_id=inddet.variant_id " 	
    			+ " where ind.company_Id="+userInfo.getCompanyId()+" and ind.year_id="+userInfo.getYearId()+" and ind.location_id="+userInfo.getLocationId()+"  and isnull(ind.cancel_tag,0)<>1 ");

		      if(strSearchCriteria!=null && !strSearchCriteria.isEmpty()){
	    			buffer.append(strSearchCriteria);
		    	}
		    	if(!dbQuery.toString().isEmpty()){
		    		buffer.append(dbQuery.toString());
		    	}
		    	if(groupBy!=null && !groupBy.isEmpty()){
		    		buffer.append(" group by "+groupBy.toString().replace("[", "").replace("]", ""));
		    		if(!dbHavingQuery.toString().isEmpty()){
		    			buffer.append(" having "+dbHavingQuery.toString().substring(4));
		    		}
		    	}
		      buffer.append(" ) as item"
	      		+ " ) as it ");
//		      System.out.println("buffer.toString()==="+buffer.toString());
		      pst= con.prepareStatement(buffer.toString());
		      
		      rs= pst.executeQuery();
		      while(rs.next()){
		    	  iCount=rs.getInt(1);  
		      }
		      cp.setPageCnt(iCount);
//		      System.out.println("page count :"+iCount);	
		      buffer=null;
	      }
	      else{

	    	 
//	    	  List<ColumnPreference> cpList=DataList.getCpList();
	    	  //		    	SQLUtil.GET_PENDING_SALES
	    	  StringBuffer query = new StringBuffer("select  distinct count(*) over() "
	    			  + "  from indent ind "	
		    			+ " left join Indent_Details inddet on inddet.Indent_Id=ind.Indent_Id "	
		    			+ " left join party p on p.party_id=inddet.party_id "
		    			+ " left join Variant_Master v on v.variant_id=inddet.variant_id " 	
		    			+ " where ind.company_Id="+userInfo.getCompanyId()+" and ind.year_id="+userInfo.getYearId()+" and ind.location_id="+userInfo.getLocationId()+"  and isnull(ind.cancel_tag,0)<>1 ");
		
	    	  if(strSearchCriteria!=null && !strSearchCriteria.isEmpty()){
//	    		  System.out.println("strSearchCriteria==="+strSearchCriteria);
	    		  query.append(strSearchCriteria);
	    	  }
	    	  if(!dbQuery.toString().isEmpty()){
//	    		  System.out.println("dbQuery.toString()===="+dbQuery.toString());
	    		  query.append(dbQuery.toString());
	    	  }
	    	
	    	  //System.out.println("orderBy:"+orderBy.toString());
	    	  if(groupBy!=null && !groupBy.isEmpty()){
	    		  query.append(" group by "+groupBy.toString().replace("[", "").replace("]", ""));
	    		  if(!dbHavingQuery.toString().isEmpty()){
	    			  query.append(" having "+dbHavingQuery.toString().substring(4));
	    		  }
	    	  }
	    	  pst=con.prepareStatement(query.toString());
	    	  rs = pst.executeQuery();
	    	  if(rs.next()){
	    		  iCount = rs.getInt(1);	
	    	  }
	    	  cp.setPageCnt(iCount);
	    	  
	    	 
		      query=null;
	      }
	      groupBy=null;
	      dbQuery=null;
	      dbHavingQuery=null;
	    
	} finally {
		DatabaseConnection.closeAll(pst,rs);
	}
	return cp;
}
}