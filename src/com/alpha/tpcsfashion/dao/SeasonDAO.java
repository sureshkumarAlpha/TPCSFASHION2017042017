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

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.beans.Season;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class SeasonDAO {
	public List<Season> getSeasonList(Connection con, TPCSUser userInfo,PageConfig pc,String strSearhQuery) throws SQLException, IOException {
		List<Season> objList=new ArrayList<Season>();
		PreparedStatement pstmt=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try {
			if(strSearhQuery!=null && strSearhQuery!=""){
				strSearhQuery=" and "+ strSearhQuery;
			}

			pstmt = con.prepareStatement("{call SeasonMasterpaging(?,?,?)}");
			pstmt.setInt(1,pc.iStart);
			pstmt.setInt(2,pc.iEnd);
			pstmt.setString(3,strSearhQuery);
			rs=pstmt.executeQuery();

			while (rs.next()) {

				Season obj= new Season();
				obj.setSeasonId(rs.getInt(1));
				obj.setSeasonName(rs.getString(2));
				obj.setFromDate(rs.getString(3));
				obj.setToDate(rs.getString(4));
				obj.setIsActive(rs.getInt(5));
				objList.add(obj);
				obj=null;

			}
		}
		finally{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return objList;
	}

	public int insertSeason(Connection con, Season seObj,TPCSUser userInfo) throws SQLException {

		CallableStatement cstmt=null;
		int groupId;
		try	{
			cstmt=con.prepareCall("{? = call pr_insert_Season(?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2,seObj.getSeasonId());
			cstmt.setString(3, seObj.getSeasonName());
			cstmt.setString(4,seObj.getFromDate());
			cstmt.setString(5, seObj.getToDate());
			cstmt.setInt(6,seObj.getIsActive());
			cstmt.setString(7,userInfo.getDisplayUserName());

			cstmt.setString(8, seObj.getMode());
			System.out.println("MODE===="+seObj.getMode());
			int rowsInserted=cstmt.executeUpdate();
			boolean bflag=rowsInserted>0?true:false;

			groupId = cstmt.getInt(1);

		}
		finally	{

			DatabaseConnection.closeAll(cstmt, null);

		}
		return groupId;
	}

	public Season isMasterSeasonExist(Connection con,Season seObj,TPCSUser userInfo) throws Exception {  
		PreparedStatement pstmt=null;
		boolean seasonFlag=false;
		ResultSet rs=null;
		try	{
			StringBuffer codeQuery=new StringBuffer(SQLUtil.SEASON_EXIST);
			if(seObj.getMode().equals("e")){
				codeQuery.append(" and Season_Id<>"+seObj.getSeasonId());	
			}
			pstmt=con.prepareStatement(codeQuery.toString());
			pstmt.setString(1,seObj.getSeasonName());
			rs= pstmt.executeQuery();
			while(rs.next()){ 
				seasonFlag=(Validator.convertToInteger(rs.getString(1)))>0?true:false;

			}
			seObj.setSeasonExists(seasonFlag);

		}
		finally{
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return seObj;

	}

	public Season getSeasonInfo(Connection con,TPCSUser ui,int seId,int screenId) throws SQLException,Exception{ 

		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;
		PreparedStatement pstmtGetInfoDynamic = null;
		Season obj= new Season();
		//Added for Dynamic Fields
		try	{
			pstmt=con.prepareStatement(SQLUtil.GET_SEASON_MASTER_INFO);
			pstmt.setInt(1,seId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				obj.setSeasonId(rs.getInt(1));
				obj.setSeasonName(rs.getString(2));
				obj.setFromDate(rs.getString(3));
				obj.setToDate(rs.getString(4));
				obj.setIsActive(rs.getInt(5));
			}

		}
		finally{     
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

	public boolean deleteSeason(Connection con, int seId) throws SQLException {

		Statement pstmt=null;
		boolean isDeleted=false;
		try {
			pstmt = con.createStatement();
			pstmt.addBatch("delete from Season where Season_Id="+seId);
			int count[]=pstmt.executeBatch();

			isDeleted=count.length>0 && count[0]>0?true:false;
		}

		finally {
			DatabaseConnection.closeAll(pstmt, null);
		}
		return isDeleted;
	}

	public int getPageCount(Connection con, TPCSUser userInfo,String strSearhQuery) throws SQLException {

		int pageCount=0;

		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			StringBuffer buffer=new StringBuffer(SQLUtil.SEASON_MASTER_PAGE_COUNT);

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
	public boolean bulkActionSeason(Connection con, String[] seId,String action_mode) throws SQLException {

		Statement pstmt=null;
		boolean isBulkAction=false;
		try	{
			for(int i=0;i<seId.length;i++){
				if(action_mode.equals("1"))	{
					pstmt = con.createStatement();
					pstmt.addBatch("update Season set Is_Active=1 where Season_Id="+seId[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
				else{
					pstmt = con.createStatement();
					pstmt.addBatch("update Season set Is_Active=2 where Season_Id="+seId[i]);
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

	public Season deleteBulkSeason(Connection con, Season se) throws SQLException {
		PreparedStatement pstmt=null;   
		try{
			int iCount=0;
			pstmt = con.prepareStatement(SQLUtil.DELETE_SEASON);
			pstmt.setInt(1, se.getSeasonId());
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
}
