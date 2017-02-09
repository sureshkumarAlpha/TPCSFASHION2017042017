package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.GroupType;
import com.alpha.tpcsfashion.beans.Material;
import com.alpha.tpcsfashion.beans.Spec;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.GroupDAO;
import com.alpha.tpcsfashion.dao.GroupSettingDAO;
import com.alpha.tpcsfashion.dao.SpecificationDAO;
import com.alpha.tpcsfashion.dao.SpecificationSettingDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class SpecificationSettingManager {

	public List<Material> getSpecificationSetting(PageConfig pc,TPCSUser userinfo,String strSearhQuery){
		Connection con=null;
		List<Material> specification=null;
		try {
			con=new DatabaseConnection().getConnection(userinfo);
			SpecificationSettingDAO objSpecificationSettingDAO=new SpecificationSettingDAO();
			specification=objSpecificationSettingDAO.getSpecificationSetting(con,pc,strSearhQuery);
		} catch(Exception sqe){
			sqe.printStackTrace();
		}finally{
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return specification;
	}
	
	  public int getTotalPages(int iPageSze,TPCSUser userinfo){
		    Connection con=null;
		    int iCount = 0;
		    try{
		      con = new DatabaseConnection().getConnection(userinfo);
		      SpecificationSettingDAO objSpecificationSettingDAO=new SpecificationSettingDAO();
		      iCount = objSpecificationSettingDAO.getSpecificationSettingsCount(con,iPageSze);    
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
	
		public List<GroupType> getGroupTypeList(TPCSUser userInfo){
		    Connection con=null;
		    List<GroupType> grouptypes = null;
		    try{
		      con = new DatabaseConnection().getConnection(userInfo);
		      GroupDAO objGroup = new GroupDAO();         
		      grouptypes = objGroup.getGroupTypeList(con);
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
		    return grouptypes;
		  }
		
		  public boolean insertSpecificationSetting(Material  specification,TPCSUser userInfo)
		  {
		    Connection con=null;
		    boolean bFlag = false;
		    try{
		      con = new DatabaseConnection().getConnection(userInfo);
		      SpecificationSettingDAO objSpecificationSettingDAO=new SpecificationSettingDAO();
		      con.setAutoCommit(false);      
		      bFlag = objSpecificationSettingDAO.insertSpecificationSetting(con,specification,userInfo);      
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
	  
	
		  
		  
		  
		  

		 /* public int getMaterialCount(int iPageSze,TPCSUser userinfo){
			  Connection con=null;
			    int iCount = 0;
			    String strSearchCriteria = null;
			    try{
			      con = new DatabaseConnection().getConnection(userinfo);
			      MaterialDAO objMaterialDAO = new MaterialDAO();
			      iCount = objMaterialDAO.getMaterialCount(con,iPageSze,strSearchCriteria);    
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

			  }  */
		
		  
		  
		  public boolean deleteSpecificationSetting(Material material ,TPCSUser userInfo){

			  
			  Connection con=null;
			    boolean bFlag = false;
			    try{
			      con = new DatabaseConnection().getConnection(userInfo);
			      SpecificationSettingDAO objSpecificationSettingDAO=new SpecificationSettingDAO();
			      con.setAutoCommit(false);
			      bFlag = objSpecificationSettingDAO.deleteSpecificationSetting( con,material) ;   
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
			    return bFlag;
		  }

		  
		  
		  
			   
			   

				public boolean updateSpecificationSetting(Material mat,Material material,TPCSUser userinfo)
				 
			{
				    Connection con=null;
				    boolean bFlag = false;
				    try{
				      con = new DatabaseConnection().getConnection(userinfo);
				      SpecificationSettingDAO objSpecificationSettingDAO=new SpecificationSettingDAO();
				      con.setAutoCommit(false);      
				      bFlag = objSpecificationSettingDAO.updateSpecificationSetting(con,mat,material,userinfo);      
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

				
				
				
				

				public Material getSpecsettingInfo(Material material,TPCSUser userInfo){
				    Connection con=null;
				    Material objMaterials = null;
				    try{
				      con = new DatabaseConnection().getConnection(userInfo);
				      SpecificationSettingDAO objSpecificationSettingDAO=new SpecificationSettingDAO();
				      con.setAutoCommit(false);
				     objMaterials = objSpecificationSettingDAO.getSpecsettingInfo(con,material);  
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
				    return objMaterials;
				  }


				public String getSpecSettingForPrint(TPCSUser userinfo) throws SQLException,Exception {
					Connection con=null;
					String groupList="";
					try
				{
					con=new DatabaseConnection().getConnection(userinfo);
				      SpecificationSettingDAO objSpecificationSettingDAO=new SpecificationSettingDAO();
					con.setAutoCommit(false);
					groupList=objSpecificationSettingDAO.getSpecSettingForPrint(con);
					con.commit();
				}
					finally
					{
						if(con!=null)
						{
						con.close();	
						}
					}
					
					return groupList;
				}
  
		  
		  
}
