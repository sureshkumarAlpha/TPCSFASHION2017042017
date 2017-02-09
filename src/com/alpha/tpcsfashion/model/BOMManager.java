package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.BOM;
import com.alpha.tpcsfashion.beans.BOMDetail;
import com.alpha.tpcsfashion.beans.BOMHeader;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.BOMDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;


public class BOMManager {

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
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	   return pageCount;
	}
	public List<Map<String,String>> getAllBomColumnMapping (PageConfig pc, TPCSUser userInfo,String strSearchCriteria,List<String> orderBy,int iScreenId,String bomAttachPath)
	  {  
	    BOMDAO ObjDAO=new BOMDAO();
	    Connection con = null;
	    List<Map<String,String>> orders = null;
	    try
	    { 
	      con =new DatabaseConnection().getConnection(userInfo);
	      
	      orders = objDAO.getAllBomColumnMapping(con, pc,userInfo,strSearchCriteria,orderBy,iScreenId,bomAttachPath);
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
	
	 public String getBOMDetailsgrid(TPCSUser userinfo,int bomId) throws SQLException{
		    Connection con=null;
		    String buffer="";		    
		    try{
		      con = new DatabaseConnection().getConnection(userinfo);
		      con.setAutoCommit(false);
		      buffer= objDAO.getBOMDetailsgrid(con,userinfo,bomId);
		      con.commit();
		    }catch(Exception sqe){
		      sqe.printStackTrace();
		    }finally{
		    	con.close();
		    }
		    return buffer;
		  }
	 
	 
	 

	 
	public boolean deleteBOM(TPCSUser userInfo, int bomId) {
		Connection con = null;
		boolean isDeleted = false;
		try {
			con = new DatabaseConnection().getConnection(userInfo);
			
			con.setAutoCommit(false);
			isDeleted = objDAO.deleteBOM(con, bomId,userInfo); 
			con.commit();
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return isDeleted;
	}
	
	
	public BOM saveBOM(TPCSUser userInfo,BOM objB,Map<String,String> map,Map<String,String> mapDyn,String editedMode,
			FileImport objBean,String[] userid, String[] userremarks,int iUserId) {
		// TODO Auto-generated method stub
		Connection con = null;
		try
		{
			con =new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);

			synchronized(this){		
				boolean isExists=false;

				if(objB.getHeaderMode().equalsIgnoreCase("n")){

					isExists=objDAO.checkBOMExistense(con,userInfo,objB);
				}

				if(!isExists){
					
					objB = objDAO.saveBOM(con,userInfo,objB,map,mapDyn,editedMode,objBean);
			/*		if((userid.length>=1)&&(!(userid[0].equals("")))) 
					{

						if(!Arrays.asList(userid).contains(String.valueOf(iUserId))){
							String[] addLoginUser=new String[userid.length+1];
							System.arraycopy(userid, 0, addLoginUser, 0, userid.length);
							addLoginUser[addLoginUser.length-1]=String.valueOf(iUserId); 
							//						   userremarks[userremarks.length]="";
							userid=new String[addLoginUser.length];
							userid=addLoginUser;
							
							addLoginUser=null;
						}

						boolean isUserInserted = objDAO.insertUser(con,userid,userremarks,trId,userInfo);//inserting in selectedfollowers
						
						userid=null;
					}
					else{//Default Login UserId Insert
						String[] tmpuserId={String.valueOf(iUserId)}; 
						boolean isUserInserted = objDAO.insertUser(con,tmpuserId,userremarks,trId,userInfo);//inserting login user in in follower tables
						//			         	
					}*/
				}
				else{
					objB.getHeader().setBomId(-1);//invoice number and prefix already exists 
				}
			}
			con.commit();
		}
		catch (Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return objB;

	}
	
	public BOM getBOMHeader(int iScreenId,int trId, TPCSUser userInfo) {
		// TODO Auto-generated method stub
		BOM header=null;
		
		 Connection con = null;
		    try
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
				header = objDAO.getBOMHeader(con,userInfo,trId,iScreenId);
				con.commit();
		    }
		    catch (Exception sqe)
		    {
		      sqe.printStackTrace();
		    }
		    finally
		    {
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return header;
	}	
	
	public BOMHeader getBOMInfo(TPCSUser userInfo,BOMHeader bomHeader) {
		// TODO Auto-generated method stub
		 Connection con = null;
		    try
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
		      bomHeader = objDAO.getBOMInfo(con,userInfo,bomHeader);
				con.commit();
		    }
		    catch (Exception sqe)
		    {
		      sqe.printStackTrace();
		    }
		    finally
		    {
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return bomHeader;
	}
	public BOM getBOMPrintInfo(int trId,BOM objB,TPCSUser userInfo) {
		// TODO Auto-generated method stub
		 Connection con = null;
		    try
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
		      objB = objDAO.getBOMPrintInfo(con,userInfo,trId,objB);
				con.commit();
		    }
		    catch (Exception sqe)
		    {
		      sqe.printStackTrace();
		    }
		    finally
		    {
		    	DatabaseConnection.connectionClose(con);
		    }
		
		return objB;
	}	
	
	public BOM getBOMDetailList(int trId,
			TPCSUser userInfo) {
		// TODO Auto-generated method stub
		BOM det=null;
		 Connection con = null;
		    try 
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
				con.setAutoCommit(false); 
				det = objDAO.getBOMDetailList(con,userInfo,trId);
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
	
	 public List<BOM> getAttachFiles(int enqId, TPCSUser ui) {
			Connection con=null;
			List<BOM> attachFiles=null;
			try{
			      con = new DatabaseConnection().getConnection(ui);
			      con.setAutoCommit(false);
			      attachFiles= objDAO.getAttachFiles(con,enqId);  
			      con.commit();
			      objDAO=null;
			    }catch(Exception sqe){
			    	 DatabaseConnection.connectionRollBack(con);
			      sqe.printStackTrace();
			     
			    }finally{
			    	DatabaseConnection.connectionClose(con);
			    }
			    return attachFiles;
			    }
	 
	 
	 public BOM getFirstAttachFile(int bomId, TPCSUser ui) {
			Connection con=null;
			BOM attachFiles=null;
			try{
			      con = new DatabaseConnection().getConnection(ui);
			      con.setAutoCommit(false);
			      attachFiles= objDAO.getFirstAttachFile(con,bomId);  
			      con.commit();
			      objDAO=null;
			    }catch(Exception sqe){
			    	 DatabaseConnection.connectionRollBack(con);
			      sqe.printStackTrace();
			     
			    }finally{
			    	DatabaseConnection.connectionClose(con);
			    }
			    return attachFiles;
			    }
	public boolean createAmendment(TPCSUser userInfo, BOM objB,int bomId) {
		// TODO Auto-generated method stub
		boolean isAmend=false;
		
		 Connection con = null;
		    try
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
		      isAmend = objDAO.createAmendment(con,userInfo,objB,bomId);
		      con.commit();
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
		
		return isAmend;

	}	
	public int deleteBOMDetail(TPCSUser userInfo, int bomId,int bomDetId) {
		int iCount=0;
		
		 Connection con = null;
		    try
		    {
		      con =new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false); 
		      iCount = objDAO.deleteBOMDetail(con,userInfo,bomId,bomDetId);
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
	
	
	public String getAltMaterialModalData(TPCSUser ui,int bomDetId) {
		Connection con=null;
		String str="";
		try {
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false); 
			str= objDAO.getAltMaterialModalData(con,ui,bomDetId);
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return str;
	}
	
	public String getBOMCompModalData(TPCSUser ui,int bomDetId) {
		Connection con=null;
		String str="";
		try {
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false); 
			str= objDAO.getBOMCompModalData(con,ui,bomDetId);
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return str;
	}
	public BOM saveAlternateMaterials(TPCSUser ui,BOM obj) {
		Connection con=null;

		try {
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false); 
			obj= objDAO.saveAlternateMaterials(con,ui,obj);
			con.commit();
		} catch (Exception e) {
			DatabaseConnection.connectionRollBack(con);
			e.printStackTrace();
		}
		finally {
			DatabaseConnection.connectionClose(con);
		}
		return obj;
	}
	
	
	public BOM deleteAltMaterial(TPCSUser userInfo, BOM obj) {
		// TODO Auto-generated method stub
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      obj= objDAO.deleteAltMaterial(con,userInfo,obj); 
	     
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return obj;
	}
	
	
	public BOMDetail deleteBOMComponet(TPCSUser userInfo, BOMDetail obj) {
		// TODO Auto-generated method stub
		Connection con=null; 
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      con.setAutoCommit(false); 
	      obj= objDAO.deleteBOMComponet(con,userInfo,obj); 
	     
	      con.commit();
	    }catch(Exception sqe){
	    	DatabaseConnection.connectionRollBack(con);
	      sqe.printStackTrace(); 
	    }finally{
	    	DatabaseConnection.connectionClose(con);
	    }
	   return obj;
	}
	public String doGetMatData(TPCSUser userInfo,int matId){
		String strlthrDetails="";
		Connection con=null;
		try{
			con=new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);
			 strlthrDetails=objDAO.doGetMatData(userInfo,con,matId);
			 objDAO=null;
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
	
	
	 public boolean deleteAttachment(int enqId, String fileName,TPCSUser ui) {
			Connection con=null;
			boolean bFlag=false;
			try{
			      con = new DatabaseConnection().getConnection(ui);
			      con.setAutoCommit(false);
			      bFlag= objDAO.deleteAttachment(con,enqId,fileName);  
			      con.commit();
			      objDAO=null;
			    }catch(Exception sqe){
			    	DatabaseConnection.connectionRollBack(con);
			      sqe.printStackTrace();
			      
			    }finally{
			    	DatabaseConnection.connectionClose(con);
			    }
			    return bFlag;
			    }
	 
	 public int saveBomLock(BOM obj,TPCSUser userInfo) {
			// TODO Auto-generated method stub
			Connection con = null;
			int id=0;
			try{
				con =new DatabaseConnection().getConnection(userInfo);
				con.setAutoCommit(false);

				id =objDAO.saveBOMLock(con,obj,userInfo); 
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
			return id;
		}
	 
	 public BOMDetail saveBOMComponets(BOMDetail objB,TPCSUser ui) {
			Connection con=null;
			try {
				con = new DatabaseConnection().getConnection(ui);
			    	  con.setAutoCommit(false);
			    	  objB= objDAO.saveBOMComponets(con,objB,ui);
			    	  con.commit();
			      
				
			} catch (Exception e) {
				DatabaseConnection.connectionRollBack(con);
				e.printStackTrace();
				
			}finally{
				DatabaseConnection.connectionClose(con);
			}
			return objB;
		}
	 
	 public BOMDetail getBOMNoDetailList(TPCSUser ui,int custId){
			Connection con=null; 
			BOMDetail bomNodList=null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      con.setAutoCommit(false); 
		      bomNodList = objDAO.getBOMNoDetailList(con,custId); 
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
		   return bomNodList;
		}
		
	 public int insertBOMFromParentBOM(TPCSUser userInfo,BOM objB) {
			// TODO Auto-generated method stub
			 Connection con = null;
			 int trId=0;
			    try
			    {
			    	con =new DatabaseConnection().getConnection(userInfo);
					con.setAutoCommit(false);
					trId = objDAO.insertBOMFromParentBOM(con,userInfo,objB);
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
			    return trId;
			
		}
		  public boolean isBOMNoExist(BOM objB,TPCSUser userInfo){
		    Connection con=null;
		    boolean bFlag = false;
		    try{
		      con = new DatabaseConnection().getConnection(userInfo);
		      con.setAutoCommit(false);
		      bFlag = objDAO.checkBOMExistense(con,userInfo,objB);
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
BOMDAO objDAO =new BOMDAO();
}
