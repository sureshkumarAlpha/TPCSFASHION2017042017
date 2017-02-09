package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.Currency;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.MaterialMaster;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.MaterialMasterDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
public class MaterialMasterManager {

	public MaterialMaster getAllMaterialOnColumnMapping (PageConfig pc, TPCSUser userInfo,String strSearchCriteria,List<String> orderBy,int iScreenId,String materialAttachPath)
	  {  
	    MaterialMasterDAO materialDAO=new MaterialMasterDAO();
	    Connection con = null;
	    MaterialMaster orders = null;
	    try
	    { 
	      con =new DatabaseConnection().getConnection(userInfo);
	      
	      orders = materialDAO.getAllMaterialOnColumnMapping(con, pc,userInfo,strSearchCriteria,orderBy,iScreenId,materialAttachPath);
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
	
	public String getFilteredMatIds(String matSearchCriteria,TPCSUser ui){
	    Connection con=null;
	    String matIds = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      MaterialMasterDAO materialDAO=new MaterialMasterDAO();        
	     // matIds = materialDAO.getFilteredMatIds(con, matSearchCriteria);
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
	    return matIds;
	  }
	public String getDynamicDbName(int dynamicfieldId,TPCSUser ui){
	    Connection con=null;
	    String dynaicDbName = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      MaterialMasterDAO materialDAO=new MaterialMasterDAO();       
	      dynaicDbName = materialDAO.getDynamicDbName(con, dynamicfieldId);
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
	    return dynaicDbName;
	  }

	public int getDynamicTableId(int dynamicfieldId,TPCSUser ui){
	    Connection con=null;
	    int dynaicTableId = 0;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      MaterialMasterDAO materialDAO=new MaterialMasterDAO();        
	      dynaicTableId = materialDAO.getDynamicTableId(con, dynamicfieldId);
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
	    return dynaicTableId;
	  }
	public MaterialMaster getAttachFiles(int matId, TPCSUser ui) {
		Connection con=null;
		MaterialMaster attachFiles=null;
		try{
		      con = new DatabaseConnection().getConnection(ui);
		      MaterialMasterDAO materialDAO=new MaterialMasterDAO();
		      con.setAutoCommit(false);
		      attachFiles= materialDAO.getAttachFiles(con,matId);  
		      con.commit();
		    }catch(Exception sqe){
		      sqe.printStackTrace();
		      DatabaseConnection.connectionRollBack(con);
		    }finally{
		      try{
		        if(con!=null)
		           con.close();
		      }catch(SQLException se){
		        se.printStackTrace();
		      }
		    }
		    return attachFiles;
		    }
	public List<MaterialMaster> getDynamicFields(String tableIds,TPCSUser userInfo,int fixed){
	    Connection con=null;
	    List<MaterialMaster> status = null;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      MaterialMasterDAO materialDAO=new MaterialMasterDAO();
	      status = materialDAO.getDynamicFields(con,tableIds,fixed);
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
	public MaterialMaster getMaterialMasterInfo(TPCSUser ui,int matId,int iScreenId ){
		Connection con=null; 
		MaterialMaster matInfo=null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false); 
	      MaterialMasterDAO materialDAO=new MaterialMasterDAO();
	      matInfo= materialDAO.getMaterialMasterInfo(con,ui,matId,iScreenId);
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
	   return matInfo;
}

	public MaterialMaster insertMaterialMaster(TPCSUser ui,MaterialMaster objMatP,FileImport objBean){
		Connection con=null; 
		MaterialMaster mat=null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false); 
	      MaterialMasterDAO materialDAO=new MaterialMasterDAO();
	      mat= materialDAO.insertMaterialMaster(con,ui, objMatP,objBean);
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace();
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return mat;
}

	public MaterialMaster saveMaterial(MaterialMaster objM,TPCSUser ui) {
		Connection con=null;
		
		try {
			con = new DatabaseConnection().getConnection(ui);
			 MaterialMasterDAO materialDAO=new MaterialMasterDAO();
			 con.setAutoCommit(false); 
			 objM= materialDAO.saveMaterial(con,objM,ui);
			 con.commit();
		} catch (Exception e) {
			DatabaseConnection.connectionRollBack(con);
			e.printStackTrace();
		}
		finally {
			DatabaseConnection.connectionClose(con);
		    }
		return objM;
	}
	public MaterialMaster saveUOM(MaterialMaster objM,TPCSUser ui) {
		Connection con=null;
		
		try {
			con = new DatabaseConnection().getConnection(ui);
			 MaterialMasterDAO materialDAO=new MaterialMasterDAO();
			 con.setAutoCommit(false); 
			 objM= materialDAO.saveUOM(con,objM,ui);
			 con.commit();
		} catch (Exception e) {
			DatabaseConnection.connectionRollBack(con);
			e.printStackTrace();
		}
		finally {
			DatabaseConnection.connectionClose(con);
		    }
		return objM;
	}
	
	  public String doGetMaterialModalData(TPCSUser ui) {
			Connection con=null;
			String str="";
			try {
				con = new DatabaseConnection().getConnection(ui);
				 MaterialMasterDAO materialDAO=new MaterialMasterDAO();    
				str= materialDAO.doGetMaterialModalData(con);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			 finally {
			      try {
			        if (con != null)
			          con.close();
			      }
			      catch (SQLException se) {
			        se.printStackTrace();
			      }
			    }
			return str;
		}
	
public boolean deleteMaterialMaster(TPCSUser userInfo, int matId) {
		// TODO Auto-generated method stub
		boolean isDeleted =false;
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      MaterialMasterDAO masterDAO=new MaterialMasterDAO();
	      isDeleted= masterDAO.deleteMaterialMaster(con,matId); 
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return isDeleted;
	}

	



public boolean isMaterialExist(TPCSUser ui,MaterialMaster  objMat) throws SQLException,Exception {
	Connection con=null;
	MaterialMasterDAO materialDAO=new MaterialMasterDAO();
	boolean bflag=false;
	try{
		con=new DatabaseConnection().getConnection(ui);
		con.setAutoCommit(false);
		bflag=materialDAO.isMaterialExist(con,objMat);
		con.commit();
	}
	finally{
		try
		{
		if(con!=null)
		{
			con.close();
		}}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	return bflag;
}

	
public int getPageCount(TPCSUser userInfo, PageConfig pc, String searchCriteria) {
	// TODO Auto-generated method stub
	Connection con=null; 
	int pageCount=0;
    try{
      con = new DatabaseConnection().getConnection(userInfo);
      con.setAutoCommit(false); 
      MaterialMasterDAO materialDAO=new MaterialMasterDAO();
      pageCount= materialDAO.getPageCount(con,userInfo, searchCriteria);
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
   return pageCount;
}

	
public boolean deleteAttachment(int matId, String fileName,TPCSUser ui) {
	Connection con=null;
	boolean bFlag=false;
	try{
	      con = new DatabaseConnection().getConnection(ui);
	      MaterialMasterDAO materialDAO=new MaterialMasterDAO();
	      con.setAutoCommit(false);
	      bFlag= materialDAO.deleteAttachment(con,matId,fileName);  
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace();
	      
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	    return bFlag;
	    }

public List<Currency> getCurrencyList(TPCSUser userInfo) {
	Connection con=null;
	List<Currency> curList=new ArrayList<Currency>();
try {
	con = new DatabaseConnection().getConnection(userInfo);
    MaterialMasterDAO materialDAO=new MaterialMasterDAO();
    con.setAutoCommit(false);
    curList=materialDAO.getCurrencyList(con,userInfo);
    con.commit();
	} catch(Exception sqe){
    sqe.printStackTrace();
    DatabaseConnection.connectionRollBack(con);
  }finally{
    try{
      if(con!=null)
         con.close();
    }catch(SQLException se){
      se.printStackTrace();
    }
  }
	return curList;
}





public boolean bulkActionMaterialMaster(TPCSUser userInfo, String[] matId,String action_mode) {
	// TODO Auto-generated method stub
	boolean isBulkAction =false;
	Connection con=null; 
	try{
		con = new DatabaseConnection().getConnection(userInfo);
		 MaterialMasterDAO materialDAO=new MaterialMasterDAO();
		con.setAutoCommit(false); 
		isBulkAction= materialDAO.bulkActionMaterialMaster(con,matId,action_mode); 
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

	

public MaterialMaster deleteBulkMaterialMaster(TPCSUser userInfo, MaterialMaster mat) {
	Connection con=null; 
	try{
		con = new DatabaseConnection().getConnection(userInfo);
		MaterialMasterDAO materialDAO=new MaterialMasterDAO();
		con.setAutoCommit(false); 

		mat= materialDAO.deleteBulkMaterialMaster(con,mat); 

		con.commit();
	}catch(Exception sqe){
		DatabaseConnection.connectionRollBack(con);
		sqe.printStackTrace(); 
	}finally{
		DatabaseConnection.connectionClose(con);
	}
	return mat;
	
}
public String doGetItemGroupData(TPCSUser userInfo,int groupId){
	String strlthrDetails="";
	Connection con=null;
	try{
		con=new DatabaseConnection().getConnection(userInfo);
		MaterialMasterDAO materialDAO=new MaterialMasterDAO();
		con.setAutoCommit(false);
		 strlthrDetails=materialDAO.doGetItemGroupData(userInfo,con,groupId);
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



}
