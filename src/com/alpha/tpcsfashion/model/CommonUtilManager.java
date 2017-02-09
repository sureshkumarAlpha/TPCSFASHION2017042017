package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.CommonUtil;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.TaxGroup;
import com.alpha.tpcsfashion.dao.CommonUtilDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class CommonUtilManager {

	public String doGetAutoSlNo(TPCSUser userInfo,String prefix,CommonUtil comUtil){
		String strlthrDetails="";
		Connection con=null;
		try{
			con=new DatabaseConnection().getConnection(userInfo);
			 strlthrDetails=objDAO.doGetAutoSlNo(userInfo,con,prefix,comUtil);
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
	
	public String getAutoNoData(TPCSUser userInfo,CommonUtil comUtil){
		  String strlthrDetails="";
		  Connection con=null;
		  try{
			  con=new DatabaseConnection().getConnection(userInfo);
			  strlthrDetails=objDAO.getAutoNoData(userInfo,con,comUtil);
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
	
	public CommonUtil saveAutoNo(CommonUtil comUtil,TPCSUser ui) {
		Connection con=null;
		
		try {
			
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false);
			comUtil= objDAO.saveAutoNum(con,comUtil,ui);
			con.commit();
		} catch (Exception e) {
			DatabaseConnection.connectionRollBack(con);
			e.printStackTrace();
		}
		finally{
			  DatabaseConnection.connectionClose(con);
		  }
		return comUtil;
	}
	
	public CommonUtil getDynamicFields(TPCSUser userInfo,CommonUtil comUtil){
	    Connection con=null;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      comUtil = objDAO.getDynamicFields(con,comUtil);
	      
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
	    return comUtil;
	  }
	
	public String getTaxDetail(TPCSUser userInfo,CommonUtil comUtil){
		  String strlthrDetails="";
		  Connection con=null;
		  try{
			  con=new DatabaseConnection().getConnection(userInfo);
			  con.setAutoCommit(false);
			  strlthrDetails=objDAO.getTaxDetail(userInfo,con,comUtil);
			  con.commit();
		  }catch(Exception sqe){
			  sqe.printStackTrace();
		  }finally{
			  DatabaseConnection.connectionClose(con);
		  }

		  return strlthrDetails;	
	  }
	public List<TaxGroup> getTaxGroupList(TPCSUser ui ){
		Connection con=null;
		List<TaxGroup> objList = null;
		    
		try{
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false);
			objList = objDAO.getTaxGroupList(con,ui);
			con.commit();
		}catch(Exception sqe){
			sqe.printStackTrace();
		
		}finally{

			DatabaseConnection.connectionClose(con);		}
		return objList;
	  }
	private CommonUtilDAO objDAO=new CommonUtilDAO();
}
