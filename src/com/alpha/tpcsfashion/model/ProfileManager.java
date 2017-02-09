package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.Profiles;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.ProfileDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class ProfileManager {
	public int getProfileCount(String strSearchCriteria,TPCSUser ui){
	    Connection con=null;
	    int iCount = 0;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      ProfileDAO objDAO= new ProfileDAO();
	      iCount = objDAO.getProfileCount(con,strSearchCriteria);    
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
	    return iCount;
	  }
	  public List<Profiles> getProfiles(PageConfig pc,String strSearchCriteria,TPCSUser ui,ResourceBundle bundle ){
		    Connection con=null;
		    List<Profiles> profiles = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      ProfileDAO objDAO = new ProfileDAO();        
		      profiles = objDAO.getProfiles(con, pc,strSearchCriteria,bundle );
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
		    return profiles;
		  }
	  public String getEntitySelectedDocuments(ResourceBundle bundle,int entityId,int profileId,List<Integer> list,TPCSUser ui){
		    Connection con=null;
		    String strDocument = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      ProfileDAO objDAO = new ProfileDAO();         
		      strDocument = objDAO.getEntitySelectedDocuments(bundle,con,entityId,profileId,list);
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
		    return strDocument;
		    
		  }
	  public boolean isProfileExist(Profiles objProfiles,TPCSUser ui){
		    Connection con=null;
		    boolean bFlag = false;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      ProfileDAO objDAO = new ProfileDAO();         
		      bFlag = objDAO.isProfileExist(con,objProfiles);
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
		    return bFlag;    
		  }
	  public Profiles insertProfile(Profiles objProfile,TPCSUser ui){
		    Connection con=null;
		    Profiles retVal = new Profiles();
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      con.setAutoCommit(false);     
		      ProfileDAO objDAO = new ProfileDAO();           
		      retVal = objDAO.insertProfile(con,objProfile);
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
		    return retVal;    
		  }
	  public boolean deleteProfile(int iProfileId,TPCSUser ui)
	  {
	    Connection con=null;
	    boolean bFlag = false;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      ProfileDAO objDAO = new ProfileDAO();
	      con.setAutoCommit(false);      
	      bFlag = objDAO.deleteProfile(con,iProfileId);      
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();    
	      DatabaseConnection.connectionRollBack(con);
	      return bFlag;
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return bFlag;
	  }
	  public String getEntitySelectedDocumentsList(ResourceBundle bundle,int entityId,int profileId,List<Integer> list,TPCSUser ui){
		    Connection con=null;
		    String strDocument = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      ProfileDAO objDAO = new ProfileDAO();         
		      strDocument = objDAO.getEntitySelectedDocumentsList(bundle,con,entityId,profileId,list);
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
		    return strDocument;
		    
		  }
	  public Profiles getProfileInfo(int iProfileId,TPCSUser ui)
	  {
	    Connection con=null;
	    Profiles objProfiles = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      ProfileDAO objDAO = new ProfileDAO();
	      con.setAutoCommit(false);      
	      objProfiles = objDAO.getProfileInfo(con,iProfileId);      
	      con.commit();
	    }catch(Exception sqe){
	      sqe.printStackTrace();    
	      DatabaseConnection.connectionRollBack(con);
	      return null;
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return objProfiles;
	  }
	  public boolean isEntityProfileExist(Profiles objProfiles,TPCSUser ui){
		    Connection con=null;
		    boolean bFlag = false;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      ProfileDAO objDAO = new ProfileDAO();         
		      bFlag = objDAO.isEntityProfileExist(con,objProfiles);
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
		    return bFlag;    
		  }
	  public Profiles updateProfile(Profiles objProfile,TPCSUser ui){
		    Connection con=null;
		    Profiles retVal = new Profiles();
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      con.setAutoCommit(false);     
		      ProfileDAO objDAO = new ProfileDAO();           
		      retVal = objDAO.updateProfile(con,objProfile);
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
		    return retVal;    
		  }
}
