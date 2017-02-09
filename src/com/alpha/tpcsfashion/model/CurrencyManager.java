package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.Currency;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.CurrencyDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class CurrencyManager {
	/* public List<Currency> getAllCurrency(TPCSUser ui){
			Connection con=null;
			List<Currency> objList = null;

			try{

				con = new DatabaseConnection().getConnection(ui);
				CurrencyDAO objDAO = new CurrencyDAO();         
				con.setAutoCommit(false);
				objList = objDAO.getAllCurrency(con);
				con.commit();

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
			return objList;
		  }
	 */
	public List<Currency> getCurrencyList(TPCSUser userInfo,PageConfig pc,String strSearhQuery){
		Connection con=null; 
		List<Currency> CurrencyConfigurationList=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			CurrencyConfigurationList= objList.getCurrencyList(con,userInfo,pc,strSearhQuery);
			con.commit();
		}
		catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);	
			sqe.printStackTrace();
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return CurrencyConfigurationList;
	}	

	public int insertCurrency(Currency cuObj,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		Connection con = null;
		int CuId=0;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);

			CuId =objList.insertCurrency(con,cuObj,userInfo); 
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
		return CuId;
	}

	public Currency isMasterCurrencyExist(TPCSUser userInfo,Currency cuObj){
		Connection con=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);      
			cuObj = objList.isMasterCurrencyExist(con,cuObj,userInfo);      
			con.commit();
			//objList=null;
		}
		catch(Exception sqe){
			sqe.printStackTrace();   
			return cuObj;
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
		return cuObj;
	}	

	public Currency getCurrencyInfo (int iScreenId,TPCSUser userInfo,int cuid){
		Connection con=null; 
		Currency currencyConfigurationList=null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 

			currencyConfigurationList= objList.getCurrencyInfo(con,userInfo,cuid,iScreenId);
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
		return currencyConfigurationList;
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

	public boolean deleteCurrency(TPCSUser userInfo, int cuId) {
		// TODO Auto-generated method stub
		boolean isDeleted =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isDeleted= objList.deleteCurrency(con,cuId); 
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

	public boolean bulkActionCurrency(TPCSUser userInfo, String[] cuId,String action_mode) {
		// TODO Auto-generated method stub
		boolean isBulkAction =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			isBulkAction= objList.bulkActionCurrency(con,cuId,action_mode); 
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

	public Currency deleteBulkCurrency(TPCSUser userInfo, Currency cu) {
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 

			cu= objList.deleteBulkCurrency(con,cu); 

			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return cu;
	}

	CurrencyDAO objList = new CurrencyDAO();  
}
