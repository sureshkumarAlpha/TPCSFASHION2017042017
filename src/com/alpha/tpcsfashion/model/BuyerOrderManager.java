package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;

import com.alpha.tpcsfashion.beans.BuyerOrder;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.BuyerOrderDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class BuyerOrderManager {

	public BuyerOrder saveBuyerOrder(TPCSUser userInfo,BuyerOrder objBO, FileImport objBean) {
		Connection con = null;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			BuyerOrderDAO objDAO=new BuyerOrderDAO();
			con.setAutoCommit(false);

			synchronized(this){

				boolean isExists=false;

				if(objBO.getHeaderMode().equalsIgnoreCase("n")){

					isExists=objDAO.checkBOExistense(con,userInfo,objBO);
				}

				if(!isExists){

					objBO= objDAO.saveBuyerOrder(con,userInfo,objBO, objBean);
					
//					if((userid.length>=1)&&(!(userid[0].equals("")))){
//
//						if(!Arrays.asList(userid).contains(String.valueOf(iUserId))){
//							String[] addLoginUser=new String[userid.length+1];
//							System.arraycopy(userid, 0, addLoginUser, 0, userid.length);
//							addLoginUser[addLoginUser.length-1]=String.valueOf(iUserId); 
//							//						   userremarks[userremarks.length]="";
//							userid=new String[addLoginUser.length];
//							userid=addLoginUser;
//							addLoginUser=null;
//						}
//
//						boolean isUserInserted = objDAO.insertUser(con,userid,userremarks,trId,userInfo);//inserting in selectedfollowers
//					}
//					else{
//						//Default Login UserId Insert
//						String[] tmpuserId={String.valueOf(iUserId)}; 
//						boolean isUserInserted = objDAO.insertUser(con,tmpuserId,userremarks,trId,userInfo);//inserting login user in in follower tables
//						tmpuserId=null;			         	
//					}
				}
				else{
//					trId=-1; 
					objBO.getHeader().setBoId(-1);//invoice number and prefix already exists
				}
			}
			con.commit();
			objDAO=null;
		}
		catch (Exception sqe){
			sqe.printStackTrace();
			DatabaseConnection.connectionRollBack(con);
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return objBO;

	}
	public BuyerOrder getBuyerOrderDefaultData(TPCSUser ui,BuyerOrder objBO){
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false); 
	      BuyerOrderDAO objDAO=new BuyerOrderDAO(); 
	      objBO= objDAO.getBuyerOrderDefaultData(con,ui,objBO); 
	      con.commit();
	      objDAO=null;
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
	   return objBO;
	}
	public BuyerOrder getBOSizeRangeSizeGrid(TPCSUser ui, BuyerOrder objBO){
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      BuyerOrderDAO objDAO=new BuyerOrderDAO();
	      objBO= objDAO.getBOSizeRangeSizeGrid(con,ui,objBO);
	      objDAO=null;
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
	   return objBO;
	}
	public BuyerOrder getSizeRangeSizeGrid(TPCSUser ui, BuyerOrder objBO){
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      BuyerOrderDAO objDAO=new BuyerOrderDAO();
	      objBO= objDAO.getSizeRangeSizeGrid(con,ui,objBO);
	      objDAO=null;
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
	   return objBO;
	}
	
	public BuyerOrder getBuyerOrderInfo(BuyerOrder objBO, TPCSUser userInfo) {
		Connection con = null;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			BuyerOrderDAO objDAO=new BuyerOrderDAO();
			objBO = objDAO.getBuyerOrderInfo(con,userInfo,objBO);
			objDAO=null;
		}
		catch (Exception sqe){
			sqe.printStackTrace();
		}
		finally{
			try{
				if (con != null){
					con.close();
				}
			}
			catch (SQLException se){
				se.printStackTrace();
			}
		}

		return objBO;
	}
	public int getTotalPages(TPCSUser userInfo,PageConfig pc,String strSearhQuery) {

		Connection con = null;
		int iCount = 0;
		try {
			con = new DatabaseConnection().getConnection(userInfo);
			BuyerOrderDAO objDAO=new BuyerOrderDAO();
			iCount = objDAO.getTotalPages(con, userInfo,pc,strSearhQuery);
			objDAO=null;
		} catch (Exception sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return iCount;

	}
	public BuyerOrder getAllBuyerOrderOnColumnMapping (PageConfig pc, TPCSUser userInfo,BuyerOrder objBO){
		Connection con = null;
		try{ 
			con =new DatabaseConnection().getConnection(userInfo);
			BuyerOrderDAO objDAO=new BuyerOrderDAO();
			objBO= objDAO.getAllBuyerOrderOnColumnMapping(con, pc,userInfo,objBO);
			objDAO=null;
		}
		catch (Exception sqe) {
			sqe.printStackTrace();
		}
		finally{
			try{
				if (con != null)
					con.close();
			}
			catch (SQLException se){
				se.printStackTrace();
			}
		}
		return objBO;
	}
	public BuyerOrder deleteBuyerOrderDetail(TPCSUser userInfo, BuyerOrder objBO) {
		Connection con = null;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			BuyerOrderDAO objDAO=new BuyerOrderDAO();
			objBO= objDAO.deleteBuyerOrderDetail(con,userInfo,objBO);
			con.commit();
			objDAO=null;
		}
		catch (Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}

		return objBO;

	}
	
	public BuyerOrder deleteBuyerOrder(TPCSUser userInfo, BuyerOrder objBO) {
		Connection con = null;
		try {
			con = new DatabaseConnection().getConnection(userInfo);
			
			BuyerOrderDAO objDAO=new BuyerOrderDAO();
			con.setAutoCommit(false);
			objBO = objDAO.deleteBuyerOrder(con,userInfo,objBO); 
			con.commit();
			objDAO=null;
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return objBO;
	}
	
	public BuyerOrder cancelBuyerOrder(TPCSUser userInfo, BuyerOrder objBO) {
		Connection con = null;
		try {
			con = new DatabaseConnection().getConnection(userInfo);
			
			BuyerOrderDAO objDAO=new BuyerOrderDAO();
			con.setAutoCommit(false);
			objBO = objDAO.cancelBuyerOrder(con,userInfo,objBO); 
			con.commit();
			objDAO=null;
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return objBO;
	}
	public BuyerOrder closeBuyerOrder(TPCSUser userInfo, BuyerOrder objBO) {
		Connection con = null;
		try {
			con = new DatabaseConnection().getConnection(userInfo);
			
			BuyerOrderDAO objDAO=new BuyerOrderDAO();
			con.setAutoCommit(false);
			objBO = objDAO.closeBuyerOrder(con,userInfo,objBO); 
			con.commit();
			objDAO=null;
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return objBO;
	}
}
