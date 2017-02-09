package com.alpha.tpcsfashion.model;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.alpha.tpcsfashion.beans.DynamicDataType;
import com.alpha.tpcsfashion.beans.DynamicEventType;
import com.alpha.tpcsfashion.beans.DynamicFieldType;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicForm;
import com.alpha.tpcsfashion.beans.DynamicFormScreens;
import com.alpha.tpcsfashion.beans.DynamicRefTable;
import com.alpha.tpcsfashion.beans.DynamicTable;
import com.alpha.tpcsfashion.beans.MenuItem;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.dao.DynamicOrderDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class DynamicOrderManager {

	/**
	 * To fetch Field Structure for a table
	 * 
	 * @param tableId
	 * @param strDataSource
	 * @param strDataBase
	 * @param strServerIP
	 * @param strSqlusername
	 * @param strSqlpassword
	 * @return
	 */
	public DynamicFormScreens getOrderStructureForTable(DynamicFormScreens dynamicForm,TPCSUser ui, String strType) {
		Connection con = null;		
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicOrderDAO objOrder = new DynamicOrderDAO();
			dynamicForm = objOrder.getOrderStructure(con, dynamicForm,strType);
			objOrder=null;
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
		return dynamicForm;
	}

	public List<DynamicFieldType> getFieldTypes(TPCSUser ui) {
		Connection con = null;
		List<DynamicFieldType> fieldTypes = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicOrderDAO objOrder = new DynamicOrderDAO();
			fieldTypes = objOrder.getAllFieldTypes(con);
			objOrder=null;
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
		return fieldTypes;
	}
	
	public List<DynamicDataType> getDataTypes(TPCSUser ui) {
		Connection con = null;
		List<DynamicDataType> dataTypes = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicOrderDAO objOrder = new DynamicOrderDAO();
			dataTypes = objOrder.getAllDataTypes(con);
			objOrder=null;
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
		return dataTypes;
	}
	
	public List<DynamicEventType> getEvents(int formLevel, TPCSUser ui) {
		Connection con = null;
		List<DynamicEventType> eventTypes = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicOrderDAO objOrder = new DynamicOrderDAO();
			eventTypes = objOrder.getAllEventTypes(con, formLevel);
			objOrder=null;
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
		return eventTypes;
	}
	
	public List<MenuItem> getAllScreenNames(String type,TPCSUser ui){
		Connection con = null;
		List<MenuItem> menuItem = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicOrderDAO objOrder = new DynamicOrderDAO();
			menuItem = objOrder.getAllScreenNames(con, type);
			objOrder=null;
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
		return menuItem;
	}
	
	public List<DynamicRefTable> getAllTables(TPCSUser ui){
		Connection con = null;
		List<DynamicRefTable> menuItem = null;
		try {
	
		
			con = new DatabaseConnection().getConnection(ui);
			DynamicOrderDAO objOrder = new DynamicOrderDAO();
			menuItem = objOrder.getAllTables(con);
			objOrder=null;
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
		return menuItem;
	}
	
	public DynamicTable getTableDetailsForTableId(int screenId, int tableId, TPCSUser ui, String strType){
		Connection con = null;
		DynamicTable menuItem = null;
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicOrderDAO objOrder = new DynamicOrderDAO();
			menuItem = objOrder.getTableDetailsForTableId(con,screenId,tableId, strType);
			objOrder=null;
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
		return menuItem;
	}
	
	
	
	public boolean insertOrderStructureForTable(
			DynamicFormScreens dynamicForm, TPCSUser ui,int userId,int screenId,int fieldId) {
		
		Connection con = null;		
		boolean bFlag = false;
		try {
			try {
				
				JAXBContext jaxbContext = JAXBContext.newInstance(DynamicFormScreens.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);				
				
				StringWriter writer = new StringWriter();
				jaxbMarshaller.marshal(dynamicForm, writer);
				StringBuffer sBuffer =  writer.getBuffer().delete(0, 56);  //this is to remove xml spec at the generated string 
				con = new DatabaseConnection().getConnection(ui);
				DynamicOrderDAO objOrder = new DynamicOrderDAO();
				bFlag = objOrder.saveFormFieldStructureForScreens(con, sBuffer.toString(),fieldId);						

				writer.close();
				
				objOrder.updateColumnPreferencesVisibility(con, userId,screenId);
				
				
				writer=null;
				objOrder=null;
			} catch (JAXBException e) {
				e.printStackTrace();
			}			
		} catch (Exception sqe) {
			sqe.printStackTrace();
			//DatabaseConnection.connectionRollBack(con);
			return bFlag;
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return bFlag;
	}
	
	/**
	 * function for field definition
	 */
	public DynamicForm getDynamicFieldStructure(DynamicForm dynamicForm,TPCSUser ui) {
		Connection con = null;		
		try {
			con = new DatabaseConnection().getConnection(ui);
			DynamicOrderDAO objOrder = new DynamicOrderDAO();
			dynamicForm = objOrder.getDynamicFieldStructure(con, dynamicForm);
			
			objOrder=null;
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
		return dynamicForm;
	}
	
	public boolean insertFieldStructureForTable(DynamicForm dynamicForm, TPCSUser ui) {
		
		Connection con = null;
		boolean bFlag = false;
		ResultSet rs=null;
		try {
			//con = new DatabaseConnection().getConnection(strDataSource,
			//		strDataBase, strServerIP, strSqlusername, strSqlpassword);
			
			/*  Create a batch update to update the table, before this delete the existing structure
			 * SampleOrderDAO objSampleOrder = new SampleOrderDAO();
			 * con.setAutoCommit(false); bFlag =
			 * objSampleOrder.insertSampleOrderDetails
			 * (con,objOrder,strUserName,iUserId);
			 */
			try {
				
				JAXBContext jaxbContext = JAXBContext.newInstance(DynamicForm.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);				
				
				StringWriter writer = new StringWriter();
				jaxbMarshaller.marshal(dynamicForm, writer);
				StringBuffer sBuffer =  writer.getBuffer().delete(0, 56);
//				System.out.println(sBuffer.toString());
				PreparedStatement pstmtGetAllEventTypes = null;				
				try {
					
				//	System.out.println("save field structure xml value FIELD:"+sBuffer.toString());
					
			System.out.println(":---------------------sp_CreateDynamicFieldsConfig------FIELD-------------:"+sBuffer.toString());
					con = new DatabaseConnection().getConnection(ui);
					
					con.setAutoCommit(false);
					
					pstmtGetAllEventTypes = con.prepareCall("{ call sp_CreateDynamicFieldsConfig(?) }");
					pstmtGetAllEventTypes.setNString(1, sBuffer.toString());
					rs=pstmtGetAllEventTypes.executeQuery();
					int dynamicFiledId=0;
					if(rs.next()){
						dynamicFiledId=rs.getInt(1);
					}
//					System.out.println("dynamicFiledId inserted:"+dynamicFiledId);
					if(dynamicFiledId>0){
						bFlag = true;	
					}
				
//					writer=null;
					con.commit();
				} finally {
					if (pstmtGetAllEventTypes != null)
						pstmtGetAllEventTypes.close();					
				}
				

							
				writer.close();
				
				
			} catch (JAXBException e) {
				e.printStackTrace();
			}			
		} catch (Exception sqe) {
			sqe.printStackTrace();
			DatabaseConnection.connectionRollBack(con);
			return bFlag;
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return bFlag;
	}
	
	public List<DynamicFields> getDynamicFields(String tableIds,TPCSUser userInfo,int fixed){
	    Connection con=null;
	    List<DynamicFields> status = null;
	    try{
	      con = new DatabaseConnection().getConnection(userInfo);
	  	DynamicOrderDAO objOrder = new DynamicOrderDAO();
	      status = objOrder.getDynamicFields(con,tableIds,fixed);
	      
	      objOrder=null;
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
	
	public String getDynamicDbName(int dynamicfieldId,TPCSUser ui){
	    Connection con=null;
	    String dynaicDbName = null;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	   	DynamicOrderDAO objDAO = new DynamicOrderDAO();
	      dynaicDbName = objDAO.getDynamicDbName(con, dynamicfieldId);
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
	    return dynaicDbName;
	  }
	
	public int getDynamicTableId(int dynamicfieldId,TPCSUser ui){
	    Connection con=null;
	    int dynaicTableId = 0;
	    try{
	      con = new DatabaseConnection().getConnection(ui);
	  	DynamicOrderDAO objDAO = new DynamicOrderDAO();  
	      dynaicTableId = objDAO.getDynamicTableId(con, dynamicfieldId);
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
	    return dynaicTableId;
	  }
}
