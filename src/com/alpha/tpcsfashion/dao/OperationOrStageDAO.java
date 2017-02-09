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
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class OperationOrStageDAO {
	public List<OperationOrStage> getOperationOrStage(Connection con, TPCSUser userInfo,PageConfig pc,String strSearhQuery) throws SQLException, IOException {
		// TODO Auto-generated method stub
		List<OperationOrStage> objList=new ArrayList<OperationOrStage>();
		PreparedStatement pstmt=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try {
			if(strSearhQuery!=null && strSearhQuery!="")
			{
				strSearhQuery=" and "+ strSearhQuery;
			}
			pstmt = con.prepareStatement("{call operationMasterpaging(?,?,?)}");
			pstmt.setInt(1,pc.iStart);
			pstmt.setInt(2,pc.iEnd);
			pstmt.setString(3,strSearhQuery);
			rs=pstmt.executeQuery();

			while (rs.next()) {
				OperationOrStage obj= new OperationOrStage();

				obj.setOperationId(rs.getInt(1));
				obj.setOperationName(rs.getString(2));
				obj.setOperationSeqNo(rs.getInt(3));
				obj.setOperationDesc(rs.getString(4));
				obj.setDisplayOrder(rs.getInt(5));
				obj.setInspectionRequired(rs.getInt(6));
				obj.setProduction(rs.getInt(7));
				obj.setMainGroup(rs.getString(8));
				obj.setSubGroup(rs.getString(9));
				obj.setOperationStatus(rs.getInt(10)); 

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
	public int insertOperationOrStage(Connection con, OperationOrStage opObj,TPCSUser userInfo) throws SQLException {

		CallableStatement cstmt=null;
		int groupId;
		try
		{

			//			String mode=gm.getEditmode();
			//			int id=gm.getGroupId();
			//			
			//			System.out.println("mode-->"+mode);
			//			System.out.println("id-->"+id);
			cstmt=con.prepareCall("{? = call pr_insert_Operation(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, opObj.getOperationId());	
			cstmt.setString(3, opObj.getOperationName());
			cstmt.setInt(4,opObj.getOperationSeqNo());
			cstmt.setString(5, opObj.getOperationDesc());
			cstmt.setInt(6,opObj.getDisplayOrder());
			cstmt.setInt(7,opObj.getInspectionRequired());
			cstmt.setInt(8,opObj.getProduction());
			cstmt.setString(9, opObj.getMainGroup());
			cstmt.setString(10, opObj.getSubGroup());
			cstmt.setInt(11, opObj.getOperationStatus());
			cstmt.setLong(12,userInfo.getLocationId());
			cstmt.setString(13,userInfo.getDisplayUserName());
			//			cstmt.setInt(4,userInfo.getCompanyId());
			cstmt.setString(14, opObj.getMode());

			int rowsInserted=cstmt.executeUpdate();
			boolean bflag=rowsInserted>0?true:false;

			groupId = cstmt.getInt(1);

		}
		finally
		{

			DatabaseConnection.closeAll(cstmt, null);

		}
		return groupId;
	}

	public boolean isMasterOperationOrStageExist(Connection con,OperationOrStage opObj,TPCSUser userInfo) throws Exception
	{  
		PreparedStatement pstmt=null;
		boolean codeFlag=false,nameFlag=false,bFlag=false;
		ResultSet rs=null;
		try
		{
			StringBuffer codeQuery=new StringBuffer(SQLUtil.OPERATION_STAGE_CODE_EXIST);
			if(opObj.getMode().equals("e"))
			{
				codeQuery.append(" and Operation_id<>"+opObj.getOperationId());	
			}
			pstmt=con.prepareStatement(codeQuery.toString());
			pstmt.setString(1,opObj.getOperationName());
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
	public OperationOrStage getOperationOrStageInfo(Connection con,TPCSUser ui,int opId,int screenId) throws SQLException,Exception{ 
		// TODO Auto-generated method stub
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;
		PreparedStatement pstmtGetInfoDynamic = null;
		OperationOrStage obj= new OperationOrStage();
		//Added for Dynamic Fields
		try
		{
			pstmt=con.prepareStatement(SQLUtil.GET_OPERATON_MASTER_INFO);
			pstmt.setInt(1,opId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				obj.setOperationId(rs.getInt(1));
				obj.setOperationName(rs.getString(2));
				obj.setOperationSeqNo(rs.getInt(3));
				obj.setOperationDesc(rs.getString(4));

				obj.setDisplayOrder(rs.getInt(5));
				obj.setInspectionRequired(rs.getInt(6));
				obj.setProduction(rs.getInt(7));
				obj.setMainGroup(rs.getString(8));
				obj.setSubGroup(rs.getString(9));
				obj.setOperationStatus(rs.getInt(10));
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

	public int getPageCount(Connection con, TPCSUser ui,String strSearhQuery) throws SQLException {
		// TODO Auto-generated method stub
		int pageCount=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer buffer=new StringBuffer(SQLUtil.OPERATION_MASTER_PAGE_COUNT);

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
	public boolean deActiveOperationOrStage(Connection con, int poId) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		boolean isDeActive=false;
		try
		{
			pstmt=con.prepareStatement(SQLUtil.DEACTIVE_OPREATION);
			pstmt.setInt(1, poId);

			int count=pstmt.executeUpdate();
			//		System.out.println("countcount="+count);
			isDeActive=count>0?true:false;
		}

		finally
		{
			DatabaseConnection.closeAll(pstmt, null);
		}
		return isDeActive;
	}	

	public boolean deleteOperationOrStageMaster(Connection con, int opId) throws SQLException {
		// TODO Auto-generated method stub
		Statement pstmt=null;
		boolean isDeleted=false;
		try
		{
			pstmt = con.createStatement();
			pstmt.addBatch("delete from Operation where operation_id="+opId);
			int count[]=pstmt.executeBatch();

			isDeleted=count.length>0?true:false;
		}

		finally
		{
			DatabaseConnection.closeAll(pstmt, null);
		}
		return isDeleted;
	}	



	public boolean bulkActionOperationOrStage(Connection con, String[] poId,String action_mode) throws SQLException {
		// TODO Auto-generated method stub
		Statement pstmt=null;
		boolean isBulkAction=false;
		try
		{
			for(int i=0;i<poId.length;i++)
			{
				if(action_mode.equals("1"))
				{
					pstmt = con.createStatement();
					pstmt.addBatch("update Operation set Is_Active=1 where operation_id="+poId[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
				else
				{
					pstmt = con.createStatement();
					pstmt.addBatch("update Operation set Is_Active=0 where operation_id="+poId[i]);
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
	public OperationOrStage deleteBulkOperation(Connection con, OperationOrStage se) throws SQLException {
		PreparedStatement pstmt=null;   
		try{
			int iCount=0;
			pstmt = con.prepareStatement(SQLUtil.DELETE_OPERATION_MASTER);
			pstmt.setInt(1, se.getOperationId());
			iCount = pstmt.executeUpdate();

			if(iCount>0){
				se.setDeleted(true);
			}
		}
		finally{          

			DatabaseConnection.closeAll(pstmt,null);
		}
		return se;
	}

	/*PreparedStatement pst=null;
Statement st=null;
ResultSet rs=null;
try {
	  int iCount=0;

	  pst = con.prepareStatement(SQLUtil.DELETE_BRANCH_TEST);
	  pst.setInt(1, objC.getPartyId());

	  iCount = pst.executeUpdate();
	  if(iCount>0) {
		  objC.setDeleted(true);
	  }

}

finally{          

	  DatabaseConnection.closeAll(pst,rs);
	  DatabaseConnection.closeAll(st,null);
}
return objC;
}
	 */

}
