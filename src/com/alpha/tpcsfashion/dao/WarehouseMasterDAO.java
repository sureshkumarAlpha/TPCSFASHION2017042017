package com.alpha.tpcsfashion.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import com.alpha.tpcsfashion.beans.OperationOrStage;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.Warehouse;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class WarehouseMasterDAO {

	public int getPageCount(Connection con, TPCSUser ui,String strSearhQuery) throws SQLException {
		// TODO Auto-generated method stub
		int pageCount=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer buffer=new StringBuffer(SQLUtil.WAREHOUSE_MASTER_PAGE_COUNT);
			
			if(strSearhQuery!=null && strSearhQuery!=""){
				strSearhQuery=" where "+strSearhQuery;
				buffer.append(strSearhQuery.toString());
				}
//			System.out.println("buffer.toString()="+buffer.toString());
			pstmt=con.prepareStatement(buffer.toString());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pageCount=rs.getInt(1);
			}
		}
		finally
		{
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return pageCount;
	}	

	
	public List<Warehouse> getWarehouse(Connection con, TPCSUser userInfo,PageConfig pc,String strSearhQuery) throws SQLException, IOException {
		// TODO Auto-generated method stub
		List<Warehouse> objList=new ArrayList<Warehouse>();
		PreparedStatement pstmt=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try {
			if(strSearhQuery!=null && strSearhQuery!="")
			{
				strSearhQuery=" and "+ strSearhQuery;
			}
			pstmt = con.prepareStatement("{call storeMasterpaging(?,?,?)}");
			pstmt.setInt(1,pc.iStart);
			pstmt.setInt(2,pc.iEnd);
			pstmt.setString(3,strSearhQuery);
			System.out.println(pc.iStart);
			System.out.println(pc.iEnd);
			System.out.println(strSearhQuery);
			
			rs=pstmt.executeQuery();

			while (rs.next()) {
				Warehouse obj= new Warehouse();
				
				obj.setStoreId(rs.getInt(1));
				obj.setStoreCode(rs.getString(2));
				obj.setStoreName(rs.getString(3));
				obj.setStoreStatus(rs.getInt(4));
		
				objList.add(obj);
				obj=null;
			}
		}
		finally
		{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return objList;
	}
	
	
	public int insertWarehouse(Connection con, Warehouse opObj,TPCSUser userInfo) throws SQLException {

	    CallableStatement cstmt=null;
	    int storeId;
		try
		{
			
//			String mode=gm.getEditmode();
//			int id=gm.getGroupId();
//			
//			System.out.println("mode-->"+mode);
//			System.out.println("id-->"+id);
			cstmt=con.prepareCall("{? = call pr_insertWarehouse(?,?,?,?,?,?,?,?)}");
			System.out.println("opObj.getStoreId() :"+opObj.getStoreId());
			System.out.println("opObj.getStoreCode() :"+opObj.getStoreCode());
			System.out.println("opObj.getStoreName() :"+opObj.getStoreName());
			System.out.println("opObj.getStoreStatus() :"+opObj.getStoreStatus());
			System.out.println("userInfo.getUserId() :"+userInfo.getUserId());
			System.out.println("opObj.getMode() :"+opObj.getMode());
			System.out.println("userInfo.getCompanyId() :"+userInfo.getCompanyId());
			System.out.println("userInfo.getLocationId() :"+userInfo.getLocationId());
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, opObj.getStoreId());
			cstmt.setString(3, opObj.getStoreCode());
			cstmt.setString(4, opObj.getStoreName());
			cstmt.setInt(5, opObj.getStoreStatus());
			cstmt.setInt(6, userInfo.getUserId());
			cstmt.setString(7, opObj.getMode());
 			cstmt.setInt(8,userInfo.getCompanyId());
			cstmt.setLong(9,userInfo.getLocationId());
			
			
			

			int rowsInserted=cstmt.executeUpdate();
			boolean bflag=rowsInserted>0?true:false;
			
			storeId = cstmt.getInt(1);
		
		}
		finally
		{
	
			DatabaseConnection.closeAll(cstmt, null);
		   
		}
		return storeId;
	}
	
	
	
	
	
	
	
	
	
	public Warehouse getWarehouseInfo(Connection con,TPCSUser ui,int stId,int screenId) throws SQLException,Exception{ 
		// TODO Auto-generated method stub
	 PreparedStatement ArrayOfPreparedStatement[] = null;
	 ResultSet ArrayOfResultSet[] = null;
	 PreparedStatement pstmt=null;
	 ResultSet rs=null;
	 ResultSet rsGetInfoDynamic = null;
	 PreparedStatement pstmtGetInfoDynamic = null;
	 Warehouse obj= new Warehouse();
	 //Added for Dynamic Fields
		try
		{
			pstmt=con.prepareStatement(SQLUtil.GET_WAREHOUSE_MASTER_INFO);
			pstmt.setInt(1,stId);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				obj.setStoreId(rs.getInt(1));
				obj.setStoreCode(rs.getString(2));
				obj.setStoreName(rs.getString(3));
				obj.setStoreStatus(rs.getInt(4));
				
			}

	    }finally{     
	        ArrayOfPreparedStatement = new PreparedStatement[2];
	        ArrayOfPreparedStatement[0] = pstmt;
	        ArrayOfPreparedStatement[1] = pstmtGetInfoDynamic;
	        ArrayOfResultSet = new ResultSet[2];
	        ArrayOfResultSet[0] = rs;
	        ArrayOfResultSet[0] = rsGetInfoDynamic;
	        DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	      }
	      return obj;
	    }	
	
	
	public boolean isMasterWarehouseCodeNameExist(Connection con,Warehouse opObj,TPCSUser userInfo) throws Exception
	{  
		 PreparedStatement pstmt=null;
		 boolean codeFlag=false,nameFlag=false,bFlag=false;
		ResultSet rs=null;
		try
		{
	    StringBuffer codeQuery=new StringBuffer(SQLUtil.WAREHOUSE_CODE_NAME_EXIST);
		if(opObj.getMode().equals("e"))
		{
			codeQuery.append(" and store_id<>"+opObj.getStoreId());	
		}
		pstmt=con.prepareStatement(codeQuery.toString());
		pstmt.setString(1,opObj.getStoreName());
        rs= pstmt.executeQuery();
        while(rs.next())
        { 
		 codeFlag=(Validator.convertToInteger(rs.getString(1)))>0?true:false;
        }
	   
        if (codeFlag)
        {
        	 bFlag=true;
        }
        
	     }
	 finally
		{
			DatabaseConnection.closeAll(pstmt,rs);
			
		}
		return bFlag;
	
	}
	
	 public boolean checkWarehouseExistence(Connection con, Warehouse opObj,TPCSUser ui) throws SQLException{
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  boolean flag=false;
		  try {
			  
				pst=con.prepareStatement(" select distinct count(*) from store_master where store_id=?");
				pst.setInt(1, opObj.getStoreId());
				
				rs=pst.executeQuery();
				if(rs.next()){
					flag=rs.getInt(1)>0?true:false;//true already exists
				}
				
				
		} finally{
			DatabaseConnection.closeAll(pst, rs);
		}
		  return flag;
	  }
	
	
	
	
	public Warehouse deleteWarehouse(Connection con, Warehouse opObj) throws SQLException {
		// TODO Auto-generated method stub
		Statement cstmt=null; 
		boolean isDeleted=false;
		try
		{
			cstmt=con.createStatement();
			cstmt.addBatch("delete from store_master where store_id="+opObj.getStoreId());
			int count[]=cstmt.executeBatch();
			
			isDeleted=count.length>0?true:false;
			opObj.setDeleted(isDeleted);
	}

	finally
	{
		DatabaseConnection.closeAll(cstmt, null);
	}
	return opObj;
	}	
	
	
	public boolean deActiveWarehouse(Connection con, int stId) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		boolean isDeActive=false;
		try
		{
			pstmt=con.prepareStatement(SQLUtil.DEACTIVE_WAREHOUSE);
			pstmt.setInt(1, stId);
			
			int count=pstmt.executeUpdate();
//			System.out.println("countcount="+count);
			isDeActive=count>0?true:false;
	}

	finally
	{
		DatabaseConnection.closeAll(pstmt, null);
	}
	return isDeActive;
	}	
	
	
	public boolean bulkActionWarehouse(Connection con, String[] stId,String action_mode) throws SQLException {
		// TODO Auto-generated method stub
	     Statement pstmt=null;
		boolean isBulkAction=false;
		try
		{
			for(int i=0;i<stId.length;i++)
			{
				if(action_mode.equals("1"))
				{
					pstmt = con.createStatement();
					pstmt.addBatch("update store_master set is_active=1 where store_id="+stId[i]);
					int count[]=pstmt.executeBatch();
					
					isBulkAction=count.length>0?true:false;
				}
				else
				{
					pstmt = con.createStatement();
					pstmt.addBatch("update store_master set is_active=0 where store_id="+stId[i]);
					int count[]=pstmt.executeBatch();
					
					isBulkAction=count.length>0?true:false;
				}
			}
	}

	finally
	{
		DatabaseConnection.closeAll(pstmt, null);
	}
	return isBulkAction;
	}	
	
	
	
}