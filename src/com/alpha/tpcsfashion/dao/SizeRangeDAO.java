package com.alpha.tpcsfashion.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.SizeRange;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
//import com.alpha.tpcsfashion.util.Validator;
import com.alpha.tpcsfashion.util.Validator;

public class SizeRangeDAO {
	public List<SizeRange> getAllSizeRange(Connection con, TPCSUser userInfo,PageConfig pc,String strSearhQuery) throws SQLException, IOException, ParseException {
		List<SizeRange> objList=new ArrayList<SizeRange>();
		PreparedStatement pstmt=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		try {
			if(strSearhQuery!=null && strSearhQuery!=""){
				strSearhQuery=" and "+ strSearhQuery;
			}

			pstmt = con.prepareStatement("{call pr_Size_Range_paging(?,?,?)}");
			pstmt.setInt(1,pc.iStart);
			pstmt.setInt(2,pc.iEnd);
			pstmt.setString(3,strSearhQuery);
			rs=pstmt.executeQuery();
			
			String sizeNameSl="";
			while (rs.next()) {
				 sizeNameSl="";
				SizeRange obj= new SizeRange();

				obj.setSizeRangeId(rs.getInt(1));
	        		cstmt = con.prepareCall("{call pr_get_size_det(?)}");
			    	cstmt.setInt(1,rs.getInt(1));
			    	rs1 = cstmt.executeQuery();
			       	ResultSetMetaData rsmd = rs1.getMetaData();

			    	int columnsNumber = rsmd.getColumnCount();
			    	
				if(rs1.next()){
					  for(int i=1;i<=columnsNumber;i++){
						  String[] ar=rs1.getString(i).split("&sp#");
						  String sizeName=ar[1];//--SizeName
		    			  String slNo=ar[0];//--SizeQty
		    			  
		    			  
		    			  sizeNameSl=sizeNameSl+sizeName+"/"+slNo+",";
					  }
					  	if(!sizeNameSl.isEmpty()){
					  		sizeNameSl=sizeNameSl.substring(0,sizeNameSl.length()-1);
				    	}
	    			  
				}
				obj.setSizeRange(rs.getString(2));
				obj.setApplicableTo(rs.getInt(3));
				obj.setIsActive(rs.getInt(4));
				//obj.setSlNo(rs.getInt(5));
				obj.setSizeName(sizeNameSl);
				obj.setIsActivedet(rs.getInt(5));
				obj.setEnteredBy(rs.getInt(6));
				obj.setEnteredOn(rs.getString(7));
				obj.setUpdatedBy(rs.getInt(8));
				obj.setUpdatedOn(rs.getString(9));
				objList.add(obj);
				obj=null;
		      }
		}
		finally{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return objList;
	}

	public SizeRange getSizeRangeInfo(Connection con, TPCSUser ui,SizeRange sr)throws SQLException,Exception {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<SizeRange> sizeRangeList=new ArrayList<SizeRange>();
		try
		{
			String rowIds="";
			int idx=0;
			pstmt = con.prepareStatement(SQLUtil.GET_SIZE_RANGE_INFO);
			pstmt.setInt(1, sr.getSizeRangeId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SizeRange obj=new SizeRange();

				obj.setSizeRangeId(rs.getInt(1));
				obj.setSizeRange(rs.getString(2));
				obj.setApplicableTo(rs.getInt(3));
				obj.setIsActive(rs.getInt(4));
				obj.setSlNo(rs.getInt(5));
				obj.setSizeId(rs.getInt(6));
				obj.setSizeName(rs.getString(7));
				obj.setIsActivedet(rs.getInt(8));
				sizeRangeList.add(obj);
				obj=null;
				rowIds=rowIds+idx+",";
				idx++;
			}
			sr.setRowIds(rowIds);
			sr.setRowCount(sizeRangeList.size());

			sr.setSizeRangeList(sizeRangeList);
			sizeRangeList=null;
			return sr;

		}
		finally{	
			DatabaseConnection.closeAll(pstmt, rs);
		}


	}
	public SizeRange isMasterColorExist(Connection con, SizeRange si,TPCSUser userInfo) throws Exception {  
		PreparedStatement pstmt=null;
		boolean codeFlag=false;
		ResultSet rs=null;
		try	{
			StringBuffer codeQuery=new StringBuffer(SQLUtil.SIZE_RANGE_EXIST);
			if(si.getMode().equals("e")){
				codeQuery.append(" and Size_Range_Id <>"+si.getSizeRangeId());
			}
			pstmt=con.prepareStatement(codeQuery.toString());
			pstmt.setString(1,si.getSizeRange());
			rs= pstmt.executeQuery();
			while(rs.next()){ 
				codeFlag=(Validator.convertToInteger(rs.getString(1)))>0?true:false;
			}
			si.setSizeRangeExists(codeFlag);
		}
		finally{
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return si;
	}
	public int saveSizeRange(Connection con, TPCSUser ui, SizeRange si,String mode,int sizeRangeId)throws SQLException,Exception{
			CallableStatement cstmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Statement stmt = null;
		try{
			if(mode.equalsIgnoreCase("n")){
				pstmt = con.prepareStatement(" SELECT isnull(max(size_range_id)+1,1)  from size_range");  
				rs = pstmt.executeQuery();
				if(rs.next()){
					sizeRangeId=rs.getInt(1);
				}
			}
			else{
				pstmt = con.prepareStatement(SQLUtil.DELETE_SIZE_RANGE_ROW);
				pstmt.setInt(1, sizeRangeId);
				pstmt.executeUpdate();
			}
			stmt = con.createStatement();
			List<SizeRange> objList=si.getSizeRangeList();
			if(objList.size()>0){
				for(SizeRange obj:objList){
					if(obj.getSlNo()!=-1 && obj.getSizeId()>0){
						stmt.addBatch(" INSERT INTO Size_range (size_range_id,size_range,applicable_to,is_active,sl_no,size_id,is_active_det,Entered_By,Entered_On) "
								+" VALUES("+sizeRangeId+" ,'"+obj.getSizeRange()+"',"+obj.getApplicableTo()+","+obj.getIsactive()+","+obj.getSlNo()+","+obj.getSizeId()+","+obj.getIsActivedet()+","+ui.getUserId()+",getdate())");
					}
				}
			}
			int id[]=stmt.executeBatch();
			if(id.length==0){
				stmt.addBatch(" INSERT INTO size_range (size_range_id,size_range,applicable_to,is_active,sl_no,size_id,is_active_det,Updated_By,Updated_On) VALUES("+sizeRangeId+" ,'"+si.getSizeRange()+"',"+si.getApplicableTo()+","+si.getIsactive()+","+ui.getUserId()+",getdate(),0,0,0)");
				id=stmt.executeBatch();
			}
			int tmpsizeRangeId=0;
			if(id.length>0){
				tmpsizeRangeId=sizeRangeId;
			}
			sizeRangeId=tmpsizeRangeId;
			objList=null;
		}
		finally{
			DatabaseConnection.closeAll(cstmt, null);
		}
		return sizeRangeId;
	}
	public boolean deleteSize(Connection con, int sizeRangeId) throws SQLException {
		Statement pstmt=null;
		boolean isDeleted=false;
		try {
			pstmt = con.createStatement();
			pstmt.addBatch("delete from Size_range where Size_range_id="+sizeRangeId);
			int count[]=pstmt.executeBatch();
			isDeleted=count.length>0 && count[0]>0?true:false;
		}
		finally {
			DatabaseConnection.closeAll(pstmt, null);
		}
		return isDeleted;
	}
	public int getPageCount(Connection con, TPCSUser ui,String strSearhQuery) throws SQLException {
		int pageCount=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			StringBuffer buffer=new StringBuffer(SQLUtil.SIZE_RANGE_PAGE_COUNT);
			if(strSearhQuery!=null && strSearhQuery!=""){
				strSearhQuery=" where "+strSearhQuery;
				buffer.append(strSearhQuery.toString());
			}
			pstmt=con.prepareStatement(buffer.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pageCount=rs.getInt(1);
			}
		}
		finally	{
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return pageCount;
	}
	public SizeRange deleteSizeRange(Connection con, SizeRange sr) throws SQLException {
		PreparedStatement pstmt=null;   
		try{
			int iCount=0;
			pstmt = con.prepareStatement(SQLUtil.DELETE_SIZE_RANGE_ROW);
			pstmt.setInt(1, sr.getSizeRangeId());
			iCount = pstmt.executeUpdate();
			if(iCount>0){
				sr.setDeleted(true);
			}
		}
		finally{          

			DatabaseConnection.closeAll(pstmt,null);
		}
		return sr;
	}
	public boolean bulkActionSizeRange(Connection con, String[] sizerangeId,String action_mode) throws SQLException {
		Statement pstmt=null;
		boolean isBulkAction=false;
		try	{
			System.out.println("sizerangeId.length="+sizerangeId.length);
			for(int i=0;i<sizerangeId.length;i++){
				//System.out.println("SIZE RANGE ID: "+sizerangeId[i]);
				if(action_mode.equals("1"))	{
					pstmt = con.createStatement();
					pstmt.addBatch("update Size_range set Is_Active=1 where Size_Range_Id="+sizerangeId[i]);
					int count[]=pstmt.executeBatch();
					isBulkAction=count.length>0?true:false;
				}
				else{
					pstmt = con.createStatement();
					pstmt.addBatch("update Size_range set Is_Active=0 where Size_Range_Id="+sizerangeId[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
			}
		}
		finally	{
			DatabaseConnection.closeAll(pstmt, null);
		}
		return isBulkAction;
	}
	public SizeRange saveSizeMaster(Connection con, SizeRange objsi,TPCSUser ui) throws SQLException {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean bFlag=false;
		try {
			pstmt= con.prepareStatement(SQLUtil.SIZE_CODE_EXIST);
			pstmt.setString(1, objsi.getSizeName());
			rs= pstmt.executeQuery();
			while(rs.next()){
				bFlag = (rs.getInt(1)>0)?true:false;
			}
			objsi.setSizeExists(bFlag);
			if(!objsi.isSizeExists()){
							pstmt=con.prepareStatement(SQLUtil.INSERT_SIZE_MASTER);	
//							pstmt.setInt(1, objsi.getSizeId());
				pstmt.setString(1, objsi.getSizeName());
				pstmt.setInt(2, objsi.getIsactive());
				int iCount = pstmt.executeUpdate();
				bFlag=(iCount>0)?true:false;
				objsi.setInserted(bFlag);
			}
		}
			finally{
				ArrayOfPreparedStatement = new PreparedStatement[2];
				ArrayOfPreparedStatement[0] = pstmt;
				ArrayOfResultSet = new ResultSet[2];      
				ArrayOfResultSet[0] = rs;
				DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
			}
			return objsi;
		}
	
	
}