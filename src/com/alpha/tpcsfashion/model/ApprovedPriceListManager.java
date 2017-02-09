package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.ApprovedPriceList;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.ApprovedPriceListDAO;
import com.alpha.tpcsfashion.dao.MaterialMasterDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class ApprovedPriceListManager {
	
	public List<Map<String,String>> getAllApprovedPriceListOnColumnMapping (PageConfig pc, TPCSUser userInfo,String strSearchCriteria,List<String> orderBy,int iScreenId,int tableId)
	{ 
		
	  Connection con = null;
	  List<Map<String,String>> orders = null;
	  try
	  { 
	    con =new DatabaseConnection().getConnection(userInfo);
	    orders = objDAO.getAllApprovedPriceListOnColumnMapping(con, pc,userInfo,strSearchCriteria,orderBy,iScreenId,tableId);
	  }
	  catch (Exception sqe) 
	  {
	    sqe.printStackTrace();
	  }
	  finally
	  {
			DatabaseConnection.connectionClose(con);
	  }
	  return orders;
	}

	public ApprovedPriceList saveApprovedPriceList(ApprovedPriceList appObj,TPCSUser userinfo,Map<String,String> map,Map<String,String> mapDyn,String editedMode,String sizeInput) {
		// TODO Auto-generated method stub
		Connection con = null;
		int purPriceid=0;
		try
		{
			con =new DatabaseConnection().getConnection(userinfo);
			con.setAutoCommit(false);
			boolean isExists=false;
			boolean isMatExists=false;

			if(appObj.getMode().equalsIgnoreCase("n")){

				isExists=objDAO.checkExistense(con,userinfo,appObj);
			}
			if(!isExists){
				
			isMatExists=objDAO.checkMatExistense(con,userinfo,appObj);
			
			if(!isMatExists){
				
			appObj =objDAO.saveApprovedPriceList(con,appObj,userinfo,map,mapDyn,editedMode,sizeInput); 
			}else
			{
				purPriceid=-2;//Material  already exists 
				appObj.setPurchasePriceId(purPriceid);
			}
			}
			else{
				purPriceid=-1;//Supplier  already exists 
				appObj.setPurchasePriceId(purPriceid);
				
			}
			con.commit();
		}
		catch (Exception sqe)
		{
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		}
		finally
		{
			DatabaseConnection.connectionClose(con);
		}
		return appObj;
	}
	
	public int updateSizeInputGridData(TPCSUser userInfo,int purPriceid, String x,String valWithId,String insertMode, List<String> sizeIdList, List<String> detailIdList,List<String> qtylist)
	{
		  Connection con=null; 
		  int m=0;
		  try{
			  System.out.println("purPriceid II Method"+purPriceid);
			  con = new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
		      m=objDAO.updateSizeInputGridData(con,userInfo,purPriceid,x,valWithId,insertMode,sizeIdList,detailIdList,qtylist);
			  con.commit();
		  }
		  catch(Exception e){
			  e.printStackTrace();
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
		  return m;
	}
	public ApprovedPriceList getApprovedPriceheat(int purpriceId,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		ApprovedPriceList head=null;
		 Connection con = null;
		    try 
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
				con.setAutoCommit(false); 
				head = objDAO.getApprovedPriceheat(con,userInfo,purpriceId);
				con.commit();
		    }
		    catch (Exception sqe){
		      sqe.printStackTrace();
		    }
		    finally{
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return head;
	}
	public ApprovedPriceList getApprovedPriceDetailsList(int purpriceId,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		ApprovedPriceList det=null;
		 Connection con = null;
		    try 
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
				con.setAutoCommit(false); 
				det = objDAO.getApprovedPriceDetailsList(con,userInfo,purpriceId);
				con.commit();
		    }
		    catch (Exception sqe){
		      sqe.printStackTrace();
		    }
		    finally{
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return det;
	}
	
	
	public String getSizeGrid(TPCSUser ui,ApprovedPriceList appPrice,String sizemode) throws SQLException{
		Connection con=null;
		 StringBuffer buffer=new StringBuffer();
		try {
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false);
			
			/*List<String> sizeDisplayList=objDAO.getSizeNameForSizeRangeId(con,appPrice,sizemode);*/
			
			List<String> sizeDisplayList=objDAO.getSizeNameForSizeRangeId(con,appPrice,sizemode);
			
			ApprovedPriceList strGrid=objDAO.writeSizeInputGrid(con,ui,appPrice,sizeDisplayList,sizemode);
			buffer.append(strGrid.getBuffer());
			con.commit();
			
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}finally{
		      try{
			        if(con!=null)
			           con.close();
			      }catch(SQLException se){
			        se.printStackTrace();
			      }
			    }
		return buffer.toString();	
	}
	
	
	 public int getPageCount(TPCSUser userInfo, PageConfig pc, String searchCriteria) {
			// TODO Auto-generated method stub
			Connection con=null; 
			int pageCount=0;
		    try{
		      con = new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
		      pageCount= objDAO.getPageCount(con,userInfo, searchCriteria);
		      con.commit();
		    }catch(Exception sqe){
		      sqe.printStackTrace();
		    }finally{
		    	DatabaseConnection.connectionClose(con);
		    }
		   return pageCount;
		}	
	 
	public boolean deleteApprovedPriceList(TPCSUser userInfo, int purchaseId) {
			Connection con = null;
			boolean isDeleted = false;
			try {
				con = new DatabaseConnection().getConnection(userInfo);
				
				con.setAutoCommit(false);
				isDeleted = objDAO.deleteApprovedPriceList(con, purchaseId,userInfo); 
				con.commit();
			} catch (Exception sqe) {
				DatabaseConnection.connectionRollBack(con);
				sqe.printStackTrace();
			} finally {
				DatabaseConnection.connectionClose(con);
			}
			return isDeleted;
		}
	public int deleteApprovedPriceListDetail(TPCSUser userInfo, int supplierId,int priceDetId) {
		int iCount=0;
		
		 Connection con = null;
		    try
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
		      iCount = objDAO.deleteApprovedPriceListDetail(con,userInfo,supplierId,priceDetId);
		      con.commit();
		    }
		    catch (Exception sqe){
		    	DatabaseConnection.connectionRollBack(con);
		      sqe.printStackTrace();
		    }
		    finally{
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return iCount;

	}
	public String doGetItemMasterData(TPCSUser userInfo,int itemId){
		String strlthrDetails="";
		Connection con=null;
		try{
			con=new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);
			 strlthrDetails=objDAO.doGetItemMasterData(userInfo,con,itemId);
			con.commit();
			
		}catch(Exception sqe){
			sqe.printStackTrace();
		}finally{
			try{
				if(con!=null)
					con.close();
			}catch(Exception sqe){
				sqe.printStackTrace();
			}
		}

		return strlthrDetails;	
	}	
	
	
private ApprovedPriceListDAO objDAO=new ApprovedPriceListDAO();	
}
