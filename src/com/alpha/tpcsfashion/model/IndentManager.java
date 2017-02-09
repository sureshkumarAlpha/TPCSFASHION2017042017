package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.Indent;
import com.alpha.tpcsfashion.beans.IndentDetail;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.IndentDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;



public class IndentManager {

	public Indent getIndentInfo(Indent objIND, TPCSUser userInfo) {
		Connection con = null;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			IndentDAO objDAO=new IndentDAO();
			objIND = objDAO.getIndentInfo(con,userInfo,objIND);
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

		return objIND;
	}
	
	
	
	public Indent saveIndent(TPCSUser userInfo,Indent objIND, FileImport objBean) {
		Connection con = null;
		int trId=0;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			IndentDAO objDAO=new IndentDAO();
			con.setAutoCommit(false);

			synchronized(this){

				boolean isExists=false;

				if(objIND.getHeaderMode().equalsIgnoreCase("n")){

					isExists=objDAO.checkINDExistense(con,userInfo,objIND);
				}

				if(!isExists){

					objIND= objDAO.saveIndent(con,userInfo,objIND, objBean);
					
				}
				else{
//					trId=-1; 
					objIND.getHeader().setIndentId(-1);//invoice number and prefix already exists
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
		return objIND;

	}
	
	
	
	public int getTotalPages(TPCSUser userInfo,PageConfig pc,String strSearhQuery) {

		Connection con = null;
		int iCount = 0;
		try {
			con = new DatabaseConnection().getConnection(userInfo);
			IndentDAO objDAO=new IndentDAO();
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
	
	
	
	public Indent getAllIndentOnColumnMapping (PageConfig pc, TPCSUser userInfo,Indent objIND){
		Connection con = null;
		try{ 
			con =new DatabaseConnection().getConnection(userInfo);
			IndentDAO objDAO=new IndentDAO();
			objIND= objDAO.getAllIndentOnColumnMapping(con, pc,userInfo,objIND);
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
		return objIND;
	  }
	
	
	
	public Indent getIndentDefaultData(TPCSUser ui,Indent objIND){
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false); 
			IndentDAO objDAO=new IndentDAO(); 
			objIND= objDAO.getIndentDefaultData(con,ui,objIND); 
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
	   return objIND;
	}
	public Indent getSoNoDetailList(TPCSUser ui){
		Connection con=null; 
		Indent indNodList=null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	      con.setAutoCommit(false); 
	      IndentDAO objDAO = new IndentDAO();
	      indNodList = objDAO.getSoNoDetailList(con); 
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
	   return indNodList;
	}
	
	
	public Indent deleteIndentDetail(TPCSUser userInfo, Indent objIND) {
		Connection con = null;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			IndentDAO objDAO=new IndentDAO();
			objIND= objDAO.deleteIndentDetail(con,userInfo,objIND);
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

		return objIND;

	}
	
	public Indent deleteIndent(TPCSUser userInfo, Indent objIND) {
		Connection con = null;
		try {
			con = new DatabaseConnection().getConnection(userInfo);

			IndentDAO objDAO=new IndentDAO();
			con.setAutoCommit(false);
			objIND = objDAO.deleteIndent(con,userInfo,objIND); 
			con.commit();
			objDAO=null;
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return objIND;
	}
	
	
	public Indent cancelIndent(TPCSUser userInfo, Indent objIND) {
		Connection con = null;
		try {
			con = new DatabaseConnection().getConnection(userInfo);

			IndentDAO objDAO=new IndentDAO();
			con.setAutoCommit(false);
			objIND = objDAO.cancelIndent(con,userInfo,objIND); 
			con.commit();
			objDAO=null;
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return objIND;
	}
	
	
	public Indent closeIndent(TPCSUser userInfo, Indent objIND) {
		Connection con = null;
		try {
			con = new DatabaseConnection().getConnection(userInfo);

			IndentDAO objDAO=new IndentDAO();
			con.setAutoCommit(false);
			objIND = objDAO.closeIndent(con,userInfo,objIND); 
			con.commit();
			objDAO=null;
		} catch (Exception sqe) {
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace();
		} finally {
			DatabaseConnection.connectionClose(con);
		}
		return objIND;
	}
	
	
	public Indent getINDSizeRangeSizeGrid(TPCSUser ui, Indent objIND){
		Connection con=null; 
	    try{
	    	con = new DatabaseConnection().getConnection(ui);
	    	IndentDAO objDAO=new IndentDAO();
	    	objIND= objDAO.getINDSizeRangeSizeGrid(con,ui,objIND);
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
	   return objIND;
	}
	
	
	public Indent IndentPrintHeader(int iScreenId,int trId, TPCSUser userInfo) {
		// TODO Auto-generated method stub
		Indent header=null;

		Connection con = null;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			IndentDAO objDAO=new IndentDAO();
			con.setAutoCommit(false); 
			header = objDAO.getIndentPrintHeader(con,userInfo,trId,iScreenId);
			con.commit();
			objDAO=null;
		}
		catch (Exception sqe){
			sqe.printStackTrace();
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		
		return header;
	}	
	
	public String doGetItemData(TPCSUser userInfo,int matId,String trTag){
		String strlthrDetails="";
		Connection con=null;
		try{
			con=new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false);
			IndentDAO objDAO=new IndentDAO();
			 strlthrDetails=objDAO.doGetItemData(userInfo,con,matId,trTag);
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
	public List<IndentDetail> getIndentDetailsForPrint(int indentId,TPCSUser userInfo,String materialAttachPath) {
		// TODO Auto-generated method stub
		List<IndentDetail> detList=null;
		Connection con = null;
		try{
			con =new DatabaseConnection().getConnection(userInfo);
			IndentDAO objDAO=new IndentDAO();
			con.setAutoCommit(false); 
			detList = objDAO.getAllIndentDetailsForPrint(con,userInfo,indentId,materialAttachPath);
			con.commit();
			objDAO=null;
		}
		catch (Exception sqe){
			sqe.printStackTrace();
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		
		return detList;
	}	
	
	public List<Indent> getDynamicFields(String tableIds,TPCSUser userInfo,int fixed){
	    Connection con=null;
	    List<Indent> status = null;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	      IndentDAO soDAO=new IndentDAO(); 
	      status = soDAO.getDynamicFields(con,tableIds,fixed);
	      
	      soDAO=null;
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
	
	
	
}
