package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.GroupMaster;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.dao.GroupMasterDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class GroupMasterManager
{
	public GroupMaster getAllItemGroupsOnColumnMapping (PageConfig pc, TPCSUser userInfo,String strSearchCriteria,List<String> orderBy,int screenId,int tableId,String materialAttachPath)
	{  
		GroupMasterDAO GMasterDAO = new GroupMasterDAO();  
		Connection con = null;
		GroupMaster itemgroup = null;
		try
		{ 
			con =new DatabaseConnection().getConnection(userInfo);

			itemgroup = GMasterDAO.getAllItemGroupsOnColumnMapping(con, pc,userInfo,strSearchCriteria,orderBy,screenId,tableId,materialAttachPath);
			GMasterDAO=null;
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
		return itemgroup;
	}
	public int insertGroupMaster(GroupMaster gm,TPCSUser ui) {
		// TODO Auto-generated method stub
		Connection con = null;
		int trId=0;
		try
		{
			con =new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false);
			GroupMasterDAO GMasterDAO = new GroupMasterDAO();  
			trId =GMasterDAO.insertGroupMaster(con,gm,ui); 
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



	public List<GroupMaster> getGroupMaster(TPCSUser ui,PageConfig pc,String strSearhQuery){
		Connection con=null; 
		List<GroupMaster> GroupMasterList=null;
		try{
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false); 
			GroupMasterDAO salesDAO=new GroupMasterDAO();

			GroupMasterList= salesDAO.getGroupMaster(con,ui,pc,strSearhQuery);
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
		return GroupMasterList;
	}
	public GroupMaster getGroupMasterInfo (int iScreenId,int tableId,TPCSUser ui,int groupid){
		Connection con=null; 
		GroupMaster GroupMasterList=null;
		try{
			con = new DatabaseConnection().getConnection(ui);
			con.setAutoCommit(false); 
			GroupMasterDAO groupDAO=new GroupMasterDAO();

			GroupMasterList= groupDAO.getGroupMasterInfo(con,ui,groupid,iScreenId,tableId);
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
		return GroupMasterList;
	}


	public GroupMaster saveGroup(GroupMaster objG,TPCSUser ui) {
		Connection con=null;

		try {
			con = new DatabaseConnection().getConnection(ui);
			GroupMasterDAO objDAO=new GroupMasterDAO();
			objG= objDAO.saveGroup(con,objG,ui);

		} catch (Exception e) {
			DatabaseConnection.connectionRollBack(con);
			e.printStackTrace();
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return objG;
	}
	public boolean deleteGroupMaster(TPCSUser userInfo, int soId) {
		// TODO Auto-generated method stub
		boolean isDeleted =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			GroupMasterDAO objDAO=new GroupMasterDAO();
			isDeleted= objDAO.deleteGroupMaster(con,soId); 
			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return isDeleted;
	}

	public int getPageCount(TPCSUser userInfo,String strSearhQuery) {
		// TODO Auto-generated method stub
		Connection con=null; 
		int pageCount=0;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			GroupMasterDAO salesDAO=new GroupMasterDAO();
			pageCount= salesDAO.getPageCount(con,userInfo,strSearhQuery);
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

	public boolean isMasterGroupCodeNameExist(TPCSUser userInfo,GroupMaster gm)
	{
		Connection con=null;
		//int soId=0;
		boolean bFlag=true;
		try{
			con = new DatabaseConnection().getConnection(userInfo);

			GroupMasterDAO GMasterDAO = new GroupMasterDAO();         

			con.setAutoCommit(false);      

			bFlag = GMasterDAO.isMasterGroupCodeNameExist(con,gm,userInfo);      
			con.commit();

		}catch(Exception sqe){
			sqe.printStackTrace();   

			return bFlag;
		}
		finally
		{
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return bFlag;

	}


	public GroupMaster savegrouptype(GroupMaster objM,TPCSUser ui) {
		Connection con=null;

		try {
			con = new DatabaseConnection().getConnection(ui);
			GroupMasterDAO objDAO = new GroupMasterDAO();   
			con.setAutoCommit(false); 
			objM= objDAO.savegrouptype(con,objM,ui);
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

	public String getGroupType(TPCSUser ui) {
		Connection con=null;
		String str="";
		try {
			con = new DatabaseConnection().getConnection(ui);
			GroupMasterDAO objDAO = new GroupMasterDAO();       
			str= objDAO.getGroupType(con);

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
	public String getGroupTypeSlno(TPCSUser ui) {
		Connection con=null;
		String str="";
		try {
			con = new DatabaseConnection().getConnection(ui);
			GroupMasterDAO objDAO = new GroupMasterDAO();  
			str= objDAO.getGroupTypeSlNo(con);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return str;
	}

	public boolean bulkActiveGroupMaster(TPCSUser userInfo, String[] groupId,String action_mode) {
		// TODO Auto-generated method stub
		boolean isBulkAction =false;
		Connection con=null; 
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			con.setAutoCommit(false); 
			GroupMasterDAO objDAO = new GroupMasterDAO();      
			isBulkAction= objDAO.bulkActiveGroupMaster(con,groupId,action_mode); 
			con.commit();
		}catch(Exception sqe){
			DatabaseConnection.connectionRollBack(con);
			sqe.printStackTrace(); 
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return isBulkAction;
	}

	public List<GroupMaster> getDynamicFields(String tableIds,TPCSUser userInfo,int fixed){
		Connection con=null;
		List<GroupMaster> status = null;
		try{
			con = new DatabaseConnection().getConnection(userInfo);
			GroupMasterDAO objDAO = new GroupMasterDAO();      
			status = objDAO.getDynamicFields(con,tableIds,fixed);

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
		return status;
	}
}


