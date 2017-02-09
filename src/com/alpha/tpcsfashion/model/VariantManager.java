package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.Variant;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.VariantDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class VariantManager {
	public Variant getAllVariantOnColumnMapping(PageConfig pc, TPCSUser userInfo,String strSearhQuery, List<String> orderBy, int iScreenId,int tableId,String variantAttachPath) {

		Connection con = null;
		Variant orders = null;
		try
		{ 
			con =new DatabaseConnection().getConnection(userInfo);

			orders = objList.getAllVariantOnColumnMapping(con, pc,userInfo,strSearhQuery,orderBy,iScreenId,tableId,variantAttachPath);
		}
		catch (Exception sqe) 
		{
			sqe.printStackTrace();
		}
		finally
		{
			try
			{
				if (con != null)
					con.close();
			}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
		}
		return orders;
	}
	public List<Variant> getDynamicFields(String tableIds,TPCSUser userInfo,int fixed) {
		// TODO Auto-generated method stub
		Connection con=null;
		List<Variant> status = null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			status = objList.getDynamicFields(con,tableIds,fixed);

		}catch(Exception sqe){
			sqe.printStackTrace();
		}finally{
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return status;
	}
	public int insertVariant(Variant coObj,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		Connection con = null;
		int CoId=0;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);

			CoId =objList.insertVariant(con,coObj,userInfo); 
			con.commit();
			//objList=null;
		}
		catch (Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		}
		finally	{
			DatabaseConnection.connectionClose(con);
		}
		return CoId;
	}
	public Variant isMasterVariantExist(TPCSUser userInfo,Variant coObj){
		Connection con=null;

		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);      
			coObj = objList.isMasterVariantExist(con,coObj,userInfo);      
			con.commit();
			//objList=null;
		}
		catch(Exception sqe){
			sqe.printStackTrace();   
			return coObj;
		}
		finally	{

			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}
		return coObj;
	}	
	public Variant getVariantInfo (int iScreenId,int tableId,TPCSUser userInfo,int coid){
		Connection con=null; 
		Variant clorConfigurationList=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 

			clorConfigurationList= objList.getVariantInfo(con,userInfo,coid,iScreenId,tableId);
			con.commit();
		}
		catch(Exception sqe){
			sqe.printStackTrace();
		}
		finally{
			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}
		return clorConfigurationList;
	}	
	public int getPageCount(TPCSUser userInfo,String strSearhQuery) {
		// TODO Auto-generated method stub
		Connection con=null; 
		int pageCount=0;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			pageCount= objList.getPageCount(con,userInfo,strSearhQuery);
			con.commit();
		}
		catch(Exception sqe){
			sqe.printStackTrace();
		}
		finally{
			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}
		return pageCount;
	}
	public boolean deleteVariant(TPCSUser userInfo, int coId) {
		// TODO Auto-generated method stub
		boolean isDeleted =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isDeleted= objList.deleteVariant(con,coId); 
			con.commit();
		}
		catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return isDeleted;
	}	
	public boolean bulkActionVariant(TPCSUser userInfo, String[] coId,String action_mode) {
		// TODO Auto-generated method stub
		boolean isBulkAction =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isBulkAction= objList.bulkActionVariant(con,coId,action_mode); 
			con.commit();
		}
		catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return isBulkAction;
	}

	VariantDAO objList = new VariantDAO();
}
