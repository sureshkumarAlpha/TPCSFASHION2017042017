package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.PurchaseOrder;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.PurchaseOrderDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class PurchaseOrderManager
{
	
	public PurchaseOrder savePurchaseOrder(TPCSUser userInfo,PurchaseOrder objPO, FileImport objBean) { 

		Connection con = null;
		int trId=0;
		try{
			con =new DatabaseConnection().getConnection(userInfo);

			PurchaseOrderDAO objDAO=new PurchaseOrderDAO();
			con.setAutoCommit(false);

			synchronized(this){

				boolean isExists=false;

				if(objPO.getHeaderMode().equalsIgnoreCase("n")){

					isExists=objDAO.checkPOExistense(con,userInfo,objPO);
				}

				if(!isExists){

					objPO= objDAO.savePurchaseOrder(con,userInfo,objPO, objBean);

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
					objPO.getHeader().setPoId(-1);//invoice number and prefix already exists
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
		return objPO;

	}
	public PurchaseOrder getPurchaseOrderDefaultData(TPCSUser ui,PurchaseOrder objPO){
		Connection con=null; 
		try{

			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false); 
			PurchaseOrderDAO objDAO=new PurchaseOrderDAO(); 
			objPO= objDAO.getPurchaseOrderDefaultData(con,ui,objPO); 
			con.commit();
			//objDAO=null;
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
		return objPO;
	}
	public PurchaseOrder getPOSizeRangeSizeGrid(TPCSUser ui, PurchaseOrder objPO){
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      PurchaseOrderDAO objDAO=new PurchaseOrderDAO();
	      objPO= objDAO.getPOSizeRangeSizeGrid(con,ui,objPO);
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
	   return objPO;
	}
	
	public PurchaseOrder getPurchaseOrderInfo(PurchaseOrder objPO, TPCSUser userInfo) {
		Connection con = null;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			PurchaseOrderDAO objDAO=new PurchaseOrderDAO();
			objPO = objDAO.getPurchaseOrderInfo(con,userInfo,objPO);
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

		return objPO;
	}
	
	public int getTotalPages(TPCSUser userInfo,PageConfig pc,String strSearhQuery) {

		Connection con = null;
		int iCount = 0;
		try {
			con = new DatabaseConnection().getConnection(userInfo);
			PurchaseOrderDAO objDAO=new PurchaseOrderDAO();
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

	public PurchaseOrder deletePurchaseOrderDetail(TPCSUser userInfo, PurchaseOrder objPO) {
		Connection con = null;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			PurchaseOrderDAO objDAO=new PurchaseOrderDAO();
			objPO= objDAO.deletePurchaseOrderDetail(con,userInfo,objPO);
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

		return objPO;

	}
	public PurchaseOrder deletePurchaseOrder(TPCSUser userInfo, PurchaseOrder objPO) {
		Connection con = null;
		try {
			con = new DatabaseConnection().getConnection(userInfo);

			PurchaseOrderDAO objDAO=new PurchaseOrderDAO();
			con.setAutoCommit(false);
			objPO = objDAO.deletePurchaseOrder(con,userInfo,objPO); 
			con.commit();
			objDAO=null;
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return objPO;
	}

	public PurchaseOrder cancelPurchaseOrder(TPCSUser userInfo, PurchaseOrder objPO) {
		Connection con = null;
		try {
			con = new DatabaseConnection().getConnection(userInfo);

			PurchaseOrderDAO objDAO=new PurchaseOrderDAO();
			con.setAutoCommit(false);
			objPO = objDAO.cancelPurchaseOrder(con,userInfo,objPO); 

			con.commit();
			objDAO=null;
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return objPO;
	}
	public PurchaseOrder getAllPurchaseOrderOnColumnMapping (PageConfig pc, TPCSUser userInfo,PurchaseOrder objPO){
		Connection con = null;
		try{ 
			con =new DatabaseConnection().getConnection(userInfo);
			PurchaseOrderDAO objDAO=new PurchaseOrderDAO();
			objPO= objDAO.getAllPurchaseOrderOnColumnMapping(con, pc,userInfo,objPO);
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
		return objPO;
	}


	public PurchaseOrder closePurchaseOrder(TPCSUser userInfo, PurchaseOrder objPO) {
		Connection con = null;
		try {
			con = new DatabaseConnection().getConnection(userInfo);

			PurchaseOrderDAO objDAO=new PurchaseOrderDAO();
			con.setAutoCommit(false);
			objPO = objDAO.closePurchaseOrder(con,userInfo,objPO); 
			con.commit();
			objDAO=null;
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return objPO;
	}

	
}