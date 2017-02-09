package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.alpha.tpcsfashion.beans.EntityRights;
import com.alpha.tpcsfashion.beans.Generic;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.EntityDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class EntityManager {
	  public int getTotalPages(String strSearchCriteria,TPCSUser ui){
		    Connection con=null;
		    int iCount = 0;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      EntityDAO objDAO= new EntityDAO();
		      iCount = objDAO.getEntityRightsCount(con,strSearchCriteria);    
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
	  public List<EntityRights> getEntityRights(PageConfig pc,String strSearchCriteria,TPCSUser ui,ResourceBundle bundle ){
		    Connection con=null;
		    List<EntityRights> entityrights = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      EntityDAO objDAO= new EntityDAO();        
		      entityrights = objDAO.getEntityRights(con, pc,strSearchCriteria,bundle );
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
		    return entityrights;
		  }
	  public List<Generic> getEntity(TPCSUser ui){
		    Connection con=null;
		    List<Generic> entities = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      EntityDAO objDAO = new EntityDAO();         
		      entities = objDAO.getEntity(con);
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
		    return entities;
		  }
	  public List<Generic> getModules(TPCSUser ui){
		    Connection con=null;
		    List<Generic> modules = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      EntityDAO objDAO = new EntityDAO();         
		      modules = objDAO.getModules(con);
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
		    return modules;
		  }
	  public List<Generic> getDocuments(TPCSUser ui,EntityRights rights){
		    Connection con=null;
		    List<Generic> documents = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      EntityDAO objDAO = new EntityDAO();         
		      documents = objDAO.getDocuments(con,rights);
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
		    return documents;
		  }
	  public boolean isEntityRightExist(EntityRights objEntityRights,TPCSUser ui){
		    Connection con=null;
		    boolean bFlag = false;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      EntityDAO objDAO = new EntityDAO();         
		      bFlag = objDAO.isEntityRightExist(con,objEntityRights);
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
	  public String getSubDocuments(EntityRights objEntityRights,List<Integer> list,TPCSUser ui,ResourceBundle bundle){
		    Connection con=null;
		    String strDocument = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      EntityDAO objDAO = new EntityDAO();         
		      strDocument = objDAO.getSubDocuments(con,objEntityRights,list,bundle);
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
	  public EntityRights insertEntityRights(EntityRights objEntityRights,TPCSUser ui){
		    Connection con=null;
		    EntityRights retVal = new EntityRights();
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      con.setAutoCommit(false);     
		      EntityDAO objDAO = new EntityDAO();           
		      retVal = objDAO.insertEntityRights(con,objEntityRights);
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
	  public String getSelectedDocuments(EntityRights rights,List<Integer> list,TPCSUser ui,ResourceBundle bundle){
		    Connection con=null;
		    String strDocument = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      EntityDAO objDAO = new EntityDAO();         
		      strDocument = objDAO.getSelectedDocuments(con,rights,list,bundle);
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
	  public EntityRights updateEntityRights(EntityRights objEntityRights,TPCSUser ui){
		    Connection con=null;
		    EntityRights retVal = new EntityRights();
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      con.setAutoCommit(false);     
		      EntityDAO objDAO = new EntityDAO();           
		      retVal = objDAO.updateEntityRights(con,objEntityRights);
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
	  public boolean updateProfileAtEntityUpdate(int iEntityId,TPCSUser ui){
		    Connection con=null;
		    boolean bFlag = false;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      con.setAutoCommit(false);     
		      EntityDAO objDAO = new EntityDAO();           
		      bFlag = objDAO.updateProfileAtEntityUpdate(con,iEntityId);
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
		    return bFlag;    
		  }
	  public boolean updateUserAtEntityUpdate(int iEntityId,TPCSUser ui){
		    Connection con=null;
		    boolean bFlag = false;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      con.setAutoCommit(false);     
		      EntityDAO objDAO = new EntityDAO();           
		      bFlag = objDAO.updateUserAtEntityUpdate(con,iEntityId);
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
		    return bFlag;    
		  }
}
