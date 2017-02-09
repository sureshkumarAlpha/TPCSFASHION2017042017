package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.beans.DynamicRefTable;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.dao.DynamicFieldsDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class DynamicFieldManager {

	public DynamicFormStructure getDynamicFormStructure(int screenId,TPCSUser ui) {
		Connection con = null;
		DynamicFormStructure dynamicFormStructure = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
			dynamicFormStructure = dynamicFieldsDAO.getDynamicFormStructure(
					con, screenId);
			dynamicFieldsDAO=null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dynamicFormStructure;
	}

	public List<DynamicFields> getDynamicFields(int screenId,int tableId,TPCSUser ui) {
		Connection con = null;
		List<DynamicFields> dynamicFieldsList = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
			dynamicFieldsList = dynamicFieldsDAO.getDynamicFields(con,screenId, tableId);
			dynamicFieldsDAO=null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dynamicFieldsList;
	}
	
	
	public DynamicFields getDynamicFieldsInfo(int screenId,int tableId,TPCSUser ui) {
		Connection con = null;
		DynamicFields objD = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
			objD = dynamicFieldsDAO.getDynamicFieldsInfo(con,screenId, tableId);
			dynamicFieldsDAO=null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objD;
	}
	
	public List<DynamicFieldEvents> getDynamicFieldEvents(int screenId,int tableId,TPCSUser ui) {
		Connection con = null;
		List<DynamicFieldEvents> dynamicFieldEventsList = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
			dynamicFieldEventsList = dynamicFieldsDAO.getDynamicFieldEvents(con,screenId, tableId);
			dynamicFieldsDAO=null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dynamicFieldEventsList;
	}
	
	
	public List<DynamicFieldEvents> getDynamicFormEvents(int screenId,TPCSUser ui) {
		Connection con = null;
		List<DynamicFieldEvents> dynamicFieldEventsList = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
			dynamicFieldEventsList = dynamicFieldsDAO.getDynamicFormEvents(con, screenId);
			dynamicFieldsDAO=null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dynamicFieldEventsList;
	}
	
	public List<DynamicFieldEvents> getPickListOptions(int tableId,TPCSUser ui) {
		Connection con = null;
		List<DynamicFieldEvents> pickListOptions = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
			pickListOptions = dynamicFieldsDAO.getPickListOptions(con, tableId);
			dynamicFieldsDAO=null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pickListOptions;
	}
	
	public List<DynamicRefTable> dynamicPanelIds(String tableIds,int screenid,TPCSUser ui){
		Connection con = null;
		List<DynamicRefTable> panelIds = null;
		try {
	
		
			con = new DatabaseConnection().getConnection(ui);
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
			panelIds = dynamicFieldsDAO.getAlldynamicPanelIds(con,tableIds,screenid);
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
		return panelIds;
	}
	
	

}
