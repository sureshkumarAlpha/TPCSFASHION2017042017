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

import com.alpha.tpcsfashion.beans.Currency;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class CurrencyDAO {
	/*public List<Currency> getAllCurrency(Connection con)throws SQLException,Exception{
		    PreparedStatement pstmt=null;
		    ResultSet rs = null;   
		    List<Currency> objList=new ArrayList<Currency>();
		    try{      

		      pstmt = con.prepareStatement(SQLUtil.GET_ALL_CURRENCY);  
		      rs = pstmt.executeQuery();
		      while(rs.next()){
		    	Currency item = new Currency();
		        item.setCurrencyId(rs.getInt(1));
		        item.setCurrencyName(rs.getString(2));
		        objList.add(item);
		      }

		    }finally{     
		      DatabaseConnection.closeAll(pstmt,rs);
		    }
		    return objList;
		  }*/
	public List<Currency> getCurrencyList(Connection con, TPCSUser userInfo,PageConfig pc,String strSearhQuery) throws SQLException, IOException {
		List<Currency> objList=new ArrayList<Currency>();
		PreparedStatement pstmt=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try {
			if(strSearhQuery!=null && strSearhQuery!=""){
				strSearhQuery=" and "+ strSearhQuery;
			}

			pstmt = con.prepareStatement("{call CurrencyMasterpaging(?,?,?)}");
			pstmt.setInt(1,pc.iStart);
			pstmt.setInt(2,pc.iEnd);
			pstmt.setString(3,strSearhQuery);
			rs=pstmt.executeQuery();
			System.out.println("strSearhQuery=="+strSearhQuery);

			while (rs.next()) {

				Currency obj= new Currency();
				obj.setCurrencyId(rs.getInt(1));
				obj.setCurrencyName(rs.getString(2));
				obj.setCoinName(rs.getString(3));
				obj.setSymbol(rs.getString(4));
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
	public int insertCurrency(Connection con, Currency cuObj,TPCSUser userInfo) throws SQLException {

		CallableStatement cstmt=null;
		int groupId;
		try	{
			cstmt=con.prepareCall("{? = call pr_insert_Currency(?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2,cuObj.getCurrencyId());
			cstmt.setString(3, cuObj.getCurrencyName());
			cstmt.setString(4,cuObj.getCoinName());
			cstmt.setString(5, cuObj.getSymbol());
			cstmt.setInt(6,cuObj.getIsActive());
			cstmt.setString(7,userInfo.getDisplayUserName());
			cstmt.setString(8, cuObj.getMode());
			int rowsInserted=cstmt.executeUpdate();
			boolean bflag=rowsInserted>0?true:false;

			groupId = cstmt.getInt(1);

		}
		finally	{

			DatabaseConnection.closeAll(cstmt, null);

		}
		return groupId;
	}

	public Currency isMasterCurrencyExist(Connection con,Currency cuObj,TPCSUser userInfo) throws Exception {  
		PreparedStatement pstmt=null;
		boolean nameFlag=false,coinFlag=false;
		ResultSet rs=null;
		try	{
			StringBuffer codeQuery=new StringBuffer(SQLUtil.CURRENCY_NAME_EXIST);
			if(cuObj.getMode().equals("e")){
				codeQuery.append(" and Currency_Id<>"+cuObj.getCurrencyId());	
			}
			System.out.println("code query==="+codeQuery);
			pstmt=con.prepareStatement(codeQuery.toString());
			pstmt.setString(1,cuObj.getCurrencyName());
			rs= pstmt.executeQuery();
			while(rs.next()){ 
				nameFlag=(Validator.convertToInteger(rs.getString(1)))>0?true:false;
			}

			StringBuffer nameQuery=new StringBuffer(SQLUtil.CURRENCY_COIN_EXIST);
			if(cuObj.getMode().equals("e")){
				nameQuery.append(" and Currency_Id<>"+cuObj.getCurrencyId());	
			}
			System.out.println("name query==="+nameQuery);
			pstmt=con.prepareStatement(nameQuery.toString());
			pstmt.setString(1,cuObj.getCoinName());
			rs= pstmt.executeQuery();
			while(rs.next()){ 
				coinFlag=(Validator.convertToInteger(rs.getString(1)))>0?true:false;

			}
			cuObj.setNameExists(nameFlag);
			cuObj.setCoinExists(coinFlag);
		}
		finally{
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return cuObj;

	}

	public Currency getCurrencyInfo(Connection con,TPCSUser ui,int cuId,int screenId) throws SQLException,Exception{ 

		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;
		PreparedStatement pstmtGetInfoDynamic = null;
		Currency obj= new Currency();
		//Added for Dynamic Fields
		try	{
			pstmt=con.prepareStatement(SQLUtil.GET_CURRENCY_MASTER_INFO);
			pstmt.setInt(1,cuId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				obj.setCurrencyId(rs.getInt(1));
				obj.setCurrencyName(rs.getString(2));
				obj.setCoinName(rs.getString(3));
				obj.setSymbol(rs.getString(4));
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
	public boolean deleteCurrency(Connection con, int cuId) throws SQLException {

		Statement pstmt=null;
		boolean isDeleted=false;
		try {
			pstmt = con.createStatement();
			pstmt.addBatch("delete from Currency_Master where Currency_Id="+cuId);
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
			StringBuffer buffer=new StringBuffer(SQLUtil.CURRENCY_MASTER_PAGE_COUNT);
			//			System.out.println("buffer==="+buffer);
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
	public boolean bulkActionCurrency(Connection con, String[] cuId,String action_mode) throws SQLException {

		Statement pstmt=null;
		boolean isBulkAction=false;
		try	{
			for(int i=0;i<cuId.length;i++){
				if(action_mode.equals("1"))	{
					pstmt = con.createStatement();
					pstmt.addBatch("update Currency_Master set Is_Active=1 where Currency_Id="+cuId[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
				else{
					pstmt = con.createStatement();
					pstmt.addBatch("update Currency_Master set Is_Active=0 where Currency_Id="+cuId[i]);
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
	public Currency deleteBulkCurrency(Connection con, Currency cu) throws SQLException {
		PreparedStatement pstmt=null;   
		try{
			int iCount=0;
			pstmt = con.prepareStatement(SQLUtil.DELETE_CURRENCY);
			pstmt.setInt(1, cu.getCurrencyId());
			iCount = pstmt.executeUpdate();
			//System.out.println("SQL=="+SQLUtil.DELETE_CURRENCY);
			if(iCount>0){
				cu.setDeleted(true);
			}
		}
		finally{          
			DatabaseConnection.closeAll(pstmt,null);
		}
		return cu;
	}
}
