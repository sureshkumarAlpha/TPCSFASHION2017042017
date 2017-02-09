package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.DynamicDataType;
import com.alpha.tpcsfashion.beans.DynamicDefaultValue;
import com.alpha.tpcsfashion.beans.DynamicEventType;
import com.alpha.tpcsfashion.beans.DynamicField;
import com.alpha.tpcsfashion.beans.DynamicFieldType;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicForm;
import com.alpha.tpcsfashion.beans.DynamicFormScreens;
import com.alpha.tpcsfashion.beans.DynamicJavaScript;
import com.alpha.tpcsfashion.beans.DynamicRefTable;
import com.alpha.tpcsfashion.beans.DynamicTable;
import com.alpha.tpcsfashion.beans.MenuItem;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class DynamicOrderDAO {

	public DynamicFormScreens getOrderStructure(Connection con, DynamicFormScreens dynamicForm, String strType)
			throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetOrderStructList = null;
		PreparedStatement pstmtGetFormScriptList = null;
		PreparedStatement pstmtGetOrderEventList = null;
		PreparedStatement pstmtGetDefaultValuesList = null;
		PreparedStatement pstmtGetFormData = null;
		ResultSet rsGetOrderStructList = null;
		ResultSet rsGetFormScriptList = null;
		ResultSet rsGetJavaScriptEventList = null;
		ResultSet rsGetDefaultVaulesList = null;
		ResultSet rsGetFormData = null;
		try {
			// Form Structure Values
			pstmtGetFormData = con
					.prepareCall("{ call sp_GetFormStructure(?) }");
			pstmtGetFormData.setInt(1, dynamicForm.getScreenId());
			rsGetFormData = pstmtGetFormData.executeQuery();
			while (rsGetFormData.next()) {
				dynamicForm.setNumOfSections(rsGetFormData
						.getInt("NoOfSections"));
				dynamicForm.setAlignment(rsGetFormData.getString("Alignment"));
			}

			// Form Script
			DynamicJavaScript formEvent = null;
			List<DynamicJavaScript> formScriptList = new ArrayList<DynamicJavaScript>();
			pstmtGetFormScriptList = con
					.prepareCall("{ call sp_GetDynamicRefScreenEvents  (?)  }");
			pstmtGetFormScriptList.setInt(1, dynamicForm.getScreenId());
			rsGetFormScriptList = pstmtGetFormScriptList.executeQuery();
			while (rsGetFormScriptList.next()) {
				formEvent = new DynamicJavaScript();
				formEvent.setFieldId(-1);
				formEvent.setJavaScript(rsGetFormScriptList
						.getString("AttachedJS"));
				formEvent.setJavaScriptEvent(rsGetFormScriptList
						.getString("EventName"));
				formEvent.setJavaScriptEventId(rsGetFormScriptList
						.getInt("EventId"));
				formEvent.setMethodName(rsGetFormScriptList
						.getString("FunctionName"));
				// formEvent.setPageFieldName(dynamicForm.getTableName());
				formScriptList.add(formEvent);
				formEvent=null;
			}
			dynamicForm.setFormScript(formScriptList);
			formScriptList=null;
			List<Integer> dynaTables = getAllTablesForScreenId(con,dynamicForm.getScreenId());
			DynamicTable dynaTable;
			List<DynamicTable> dynaTablesList = new ArrayList<DynamicTable>();
			for (Integer dynaTableId : dynaTables) {
				dynaTable = new DynamicTable();
				dynaTable.setTableId(dynaTableId);
				// Field JavaScripts
				pstmtGetOrderEventList = con
						.prepareCall("{ call sp_GetDynamicFieldEvents (?, ?) }");
				pstmtGetOrderEventList.setInt(1, dynamicForm.getScreenId());
				pstmtGetOrderEventList.setInt(2, dynaTableId);
				rsGetJavaScriptEventList = pstmtGetOrderEventList
						.executeQuery();
				DynamicJavaScript objEvent = null;
				List<DynamicJavaScript> orderJavaScriptList = new ArrayList<DynamicJavaScript>();

				while (rsGetJavaScriptEventList.next()) {
					objEvent = new DynamicJavaScript();
					objEvent.setFieldId(rsGetJavaScriptEventList
							.getInt("DynamicFieldId"));
					objEvent.setJavaScript(rsGetJavaScriptEventList
							.getString("AttachedJS"));
					objEvent.setJavaScriptEvent(rsGetJavaScriptEventList
							.getString("EventName"));
					objEvent.setJavaScriptEventId(rsGetJavaScriptEventList
							.getInt("EventId"));
					objEvent.setMethodName(rsGetJavaScriptEventList
							.getString("FunctionName"));
					objEvent.setPageFieldName(rsGetJavaScriptEventList
							.getString("pagefieldname"));
					orderJavaScriptList.add(objEvent);
					objEvent=null;
				}

				// Field Default Values
				pstmtGetDefaultValuesList = con
						.prepareCall("{ call sp_GetDynamicFieldsPickList (?) }");
				pstmtGetDefaultValuesList.setInt(1, dynaTableId);
				rsGetDefaultVaulesList = pstmtGetDefaultValuesList
						.executeQuery();
				DynamicDefaultValue objDValue = null;
				List<DynamicDefaultValue> orderdefaultValuesList = new ArrayList<DynamicDefaultValue>();

				/*while (rsGetDefaultVaulesList.next()) {
					objDValue = new DynamicDefaultValue();
					objDValue.setFieldId(rsGetDefaultVaulesList
							.getInt("DynamicFieldId"));
					objDValue.setDefaultValue(rsGetDefaultVaulesList
							.getString("DisplayText"));
					objDValue.setSortOrder(rsGetDefaultVaulesList
							.getInt("SortOrder"));
					orderdefaultValuesList.add(objDValue);
				}*/
				
				while (rsGetDefaultVaulesList.next()) {
					objDValue = new DynamicDefaultValue();
					objDValue.setFieldId(rsGetDefaultVaulesList.getInt("DynamicFieldId"));
//					objDValue.setDefaultValue(rsGetFieldDefaultVaulesList.getString("DisplayText"));
					objDValue.setDefaultValue((rsGetDefaultVaulesList.getString("DisplayText")+"&1&"+String.valueOf(rsGetDefaultVaulesList.getInt("SortOrder"))));
					objDValue.setSortOrder(rsGetDefaultVaulesList.getInt("SortOrder"));
					orderdefaultValuesList.add(objDValue);
					objDValue=null;
				}
				

				// Field Values
				pstmtGetOrderStructList = con
						.prepareCall("{ call sp_GetDynamicConfig(?, ?,?,?,?) }");
				pstmtGetOrderStructList.setInt(1, dynamicForm.getScreenId());
				pstmtGetOrderStructList.setInt(2, dynaTableId);
				pstmtGetOrderStructList.setInt(3, 0);
				pstmtGetOrderStructList.setInt(4,1);
				pstmtGetOrderStructList.setString(5, strType);
				rsGetOrderStructList = pstmtGetOrderStructList.executeQuery();
				DynamicField objDetail = null;
				dynaTable.getStructureList().clear();
				while (rsGetOrderStructList.next()) {
					objDetail = new DynamicField();
					objDetail.setFieldId(rsGetOrderStructList
							.getInt("DynamicFieldId"));
					objDetail.setFieldName(rsGetOrderStructList
							.getString("pagefieldname"));
					objDetail.setLabelName(rsGetOrderStructList
							.getString("DisplayName"));
					objDetail.setDbfieldName(rsGetOrderStructList
							.getString("dbfieldname"));
					objDetail.setDataTypeId(rsGetOrderStructList
							.getInt("DataTypeId"));
					objDetail.setDataType(rsGetOrderStructList
							.getString("DataTypeName"));
					objDetail.setControlTypeId(rsGetOrderStructList
							.getInt("FieldTypeId"));
					objDetail.setControlType(rsGetOrderStructList
							.getString("FieldTypeName"));
					objDetail.setFloatPosition(rsGetOrderStructList
							.getFloat("SortOrder"));
					objDetail.setLength(rsGetOrderStructList
							.getString("length"));
					objDetail.setColumnWidth(rsGetOrderStructList
							.getString("columnWidth"));
					objDetail.setRequired(rsGetOrderStructList
							.getBoolean("IsRequired"));
					objDetail.setActive(rsGetOrderStructList
							.getBoolean("Active"));
					objDetail
							.setFixed(rsGetOrderStructList.getBoolean("Fixed"));
					objDetail.setFieldAlignment(rsGetOrderStructList
							.getString("Alignment"));

					objDetail.setDynamicJavaScriptList(getJavaScriptList(
							objDetail.getFieldId(), orderJavaScriptList));
					objDetail.setDefaultValuesList(getDefaultValuesList(
							objDetail.getFieldId(), orderdefaultValuesList));
					objDetail.setFieldValue(" "); // to avoid showing null value
													// on page

					dynaTable.getStructureList().add(objDetail);
					objDetail=null;
				}
				dynaTablesList.add(dynaTable);
				dynaTable=null;
				dynamicForm.setTables(dynaTablesList);
				orderdefaultValuesList=null;
			} // end of Dynamic Table for loop
			
			dynaTablesList=null;
			
			

		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[5];
			ArrayOfPreparedStatement[0] = pstmtGetOrderStructList;
			ArrayOfPreparedStatement[1] = pstmtGetFormScriptList;
			ArrayOfPreparedStatement[2] = pstmtGetOrderEventList;
			ArrayOfPreparedStatement[3] = pstmtGetFormData;
			ArrayOfPreparedStatement[4] = pstmtGetDefaultValuesList;
			ArrayOfResultSet = new ResultSet[5];
			ArrayOfResultSet[0] = rsGetOrderStructList;
			ArrayOfResultSet[1] = rsGetJavaScriptEventList;
			ArrayOfResultSet[2] = rsGetFormData;
			ArrayOfResultSet[3] = rsGetFormScriptList;
			ArrayOfResultSet[4] = rsGetDefaultVaulesList;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return dynamicForm;
	}

	private List<DynamicJavaScript> getJavaScriptList(int id,
			List<DynamicJavaScript> orderJavaScriptList) {
		List<DynamicJavaScript> result = new ArrayList<DynamicJavaScript>();
		for (DynamicJavaScript dyna : orderJavaScriptList) {
			if (id == dyna.getFieldId()) {
				result.add(dyna);
				dyna=null;
			}
		}
		return result;
	}

	private List<String> getDefaultValuesList(int id,List<DynamicDefaultValue> defaultValuesList) {
		List<String> result = new ArrayList<String>();
		for (DynamicDefaultValue dyna : defaultValuesList) {
			if (id == dyna.getFieldId()) {
				result.add(dyna.getDefaultValue());
				dyna=null;
			}
		}
		return result;
	}

	public List<DynamicFieldType> getAllFieldTypes(Connection con)
			throws SQLException, Exception {
		List<DynamicFieldType> result = new ArrayList<DynamicFieldType>();
		PreparedStatement pstmtGetAllFieldTypes = null;
		ResultSet rsGetAllFieldTypes = null;
		try {
			pstmtGetAllFieldTypes = con.prepareCall("{ call sp_GetAllFieldtypes }");
			rsGetAllFieldTypes = pstmtGetAllFieldTypes.executeQuery();
			DynamicFieldType fieldType;
			while (rsGetAllFieldTypes.next()) {
				fieldType = new DynamicFieldType();
				fieldType.setFieldTypeId(rsGetAllFieldTypes.getInt("FieldTypeId"));
				fieldType.setFieldTypeName(rsGetAllFieldTypes.getString("FieldTypeName"));
				result.add(fieldType);
				fieldType=null;
			}

		} finally {
			if (pstmtGetAllFieldTypes != null)
				pstmtGetAllFieldTypes.close();
			if (rsGetAllFieldTypes != null)
				rsGetAllFieldTypes.close();
		}
		return result;

	}

	public List<DynamicDataType> getAllDataTypes(Connection con)
			throws SQLException, Exception {
		List<DynamicDataType> result = new ArrayList<DynamicDataType>();
		PreparedStatement pstmtGetAllDataTypes = null;
		ResultSet rsGetAllDataTypes = null;
		try {
			pstmtGetAllDataTypes = con
					.prepareCall("{ call sp_GetAllDatatypes }");
			rsGetAllDataTypes = pstmtGetAllDataTypes.executeQuery();
			DynamicDataType dataType;
			while (rsGetAllDataTypes.next()) {
				dataType = new DynamicDataType();
				dataType.setDataTypeId(rsGetAllDataTypes.getInt("DataTypeId"));
				dataType.setDataTypeName(rsGetAllDataTypes
						.getString("DataTypeName"));
				result.add(dataType);
				dataType=null;
			}

		} finally {
			if (pstmtGetAllDataTypes != null)
				pstmtGetAllDataTypes.close();
			if (rsGetAllDataTypes != null)
				rsGetAllDataTypes.close();
		}
		return result;

	}

	/**
	 * sp_GetAllEventTypes
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<DynamicEventType> getAllEventTypes(Connection con, int formLevel)
			throws SQLException, Exception {
		List<DynamicEventType> result = new ArrayList<DynamicEventType>();
		PreparedStatement pstmtGetAllEventTypes = null;
		ResultSet rsGetAllEventTypes = null;
		try {
			pstmtGetAllEventTypes = con
					.prepareCall("{ call sp_GetAllEventTypes (?) }");
			pstmtGetAllEventTypes.setInt(1, formLevel);
			rsGetAllEventTypes = pstmtGetAllEventTypes.executeQuery();
			DynamicEventType dataType;
			while (rsGetAllEventTypes.next()) {
				dataType = new DynamicEventType();
				dataType.setEventId(rsGetAllEventTypes.getInt("EventId"));
				dataType.setEventName(rsGetAllEventTypes.getString("EventName"));
				result.add(dataType);
				dataType=null;
			}

		} finally {
			if (pstmtGetAllEventTypes != null)
				pstmtGetAllEventTypes.close();
			if (rsGetAllEventTypes != null)
				rsGetAllEventTypes.close();
		}
		return result;

	}

	/**
	 * sp_GetAllEventTypes
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<MenuItem> getAllScreenNames(Connection con, String type)
			throws SQLException, Exception {
		List<MenuItem> result = new ArrayList<MenuItem>();
		PreparedStatement pstmtGetAllEventTypes = null;
		ResultSet rsGetAllEventTypes = null;
		try {
			pstmtGetAllEventTypes = con.prepareCall("select * from DynamicRefScreens where Type = ?");
			pstmtGetAllEventTypes.setString(1, type);
			rsGetAllEventTypes = pstmtGetAllEventTypes.executeQuery();
			MenuItem menuItem;
			while (rsGetAllEventTypes.next()) {
				menuItem = new MenuItem();
				int screenId = rsGetAllEventTypes.getInt("ScreenId");
				
				System.out.println("screenId :"+screenId);
				
				menuItem.setMenuId(screenId);
				menuItem.setMenuName(rsGetAllEventTypes.getString("ScreenName"));
				if(type.equalsIgnoreCase("I")){
				menuItem.setMenuURL("showConfigScreensForId(" + screenId + ")");
				}
				else{
				menuItem.setMenuURL("showReportConfigForId(" + screenId + ")");	
				}
				result.add(menuItem);
				menuItem=null;
			}

		} finally {
			if (pstmtGetAllEventTypes != null)
				pstmtGetAllEventTypes.close();
			if (rsGetAllEventTypes != null)
				rsGetAllEventTypes.close();
		}
		return result;

	}

	/**
	 * *
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<Integer> getAllTablesForScreenId(Connection con, int screenId)
			throws SQLException, Exception {
		List<Integer> result = new ArrayList<Integer>();
		PreparedStatement pstmtGetAllEventTypes = null;
		ResultSet rsGetAllEventTypes = null;
		try {
			pstmtGetAllEventTypes = con
					.prepareCall("select * from DynamicRefScreenRefTable where ScreenId = ?");
			pstmtGetAllEventTypes.setInt(1, screenId);
			rsGetAllEventTypes = pstmtGetAllEventTypes.executeQuery();
			while (rsGetAllEventTypes.next()) {
				result.add(rsGetAllEventTypes.getInt("TableId"));
			}

		} finally {
			if (pstmtGetAllEventTypes != null)
				pstmtGetAllEventTypes.close();
			if (rsGetAllEventTypes != null)
				rsGetAllEventTypes.close();
		}
		return result;

	}

	public List<DynamicRefTable> getAllTables(Connection con)
			throws SQLException, Exception {
		List<DynamicRefTable> result = new ArrayList<DynamicRefTable>();
		PreparedStatement pstmtGetAllEventTypes = null;
		ResultSet rsGetAllEventTypes = null;
		try {
			pstmtGetAllEventTypes = con.prepareCall("select * from DynamicRefTables");
			rsGetAllEventTypes = pstmtGetAllEventTypes.executeQuery();
			while (rsGetAllEventTypes.next()) {
				DynamicRefTable dynamicRefTable = new DynamicRefTable();
				dynamicRefTable.setTableId(rsGetAllEventTypes.getInt("TableId"));
				dynamicRefTable.setTableName(rsGetAllEventTypes.getString("TableName"));
				result.add(dynamicRefTable);
				dynamicRefTable=null;
			}

		} finally {
			if (pstmtGetAllEventTypes != null)
				pstmtGetAllEventTypes.close();
			if (rsGetAllEventTypes != null)
				rsGetAllEventTypes.close();
		}
		return result;

	}
	
	public DynamicTable getTableDetailsForTableId(Connection con, int screenId, int tableId, String strType)
		throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetOrderStructList = null;
		PreparedStatement pstmtGetFormScriptList = null;
		PreparedStatement pstmtGetOrderEventList = null;
		PreparedStatement pstmtGetDefaultValuesList = null;
		PreparedStatement pstmtGetFormData = null;
		ResultSet rsGetOrderStructList = null;
		ResultSet rsGetFormScriptList = null;
		ResultSet rsGetJavaScriptEventList = null;
		ResultSet rsGetDefaultVaulesList = null;
		ResultSet rsGetFormData = null;
		DynamicTable dynaTable = new DynamicTable();
		try {			
		dynaTable.setTableId(tableId);
		// Field JavaScripts
		/*pstmtGetOrderEventList = con
				.prepareCall("{ call   (?, ?) }");
		pstmtGetOrderEventList.setInt(1, screenId);
		pstmtGetOrderEventList.setInt(2, tableId);
		rsGetJavaScriptEventList = pstmtGetOrderEventList
				.executeQuery();
		DynamicJavaScript objEvent = null;
		List<DynamicJavaScript> orderJavaScriptList = new ArrayList<DynamicJavaScript>();

		while (rsGetJavaScriptEventList.next()) {
			objEvent = new DynamicJavaScript();
			objEvent.setFieldId(rsGetJavaScriptEventList
					.getInt("DynamicFieldId"));
			objEvent.setJavaScript(rsGetJavaScriptEventList
					.getString("AttachedJS"));
			objEvent.setJavaScriptEvent(rsGetJavaScriptEventList
					.getString("EventName"));
			objEvent.setJavaScriptEventId(rsGetJavaScriptEventList
					.getInt("EventId"));
			objEvent.setMethodName(rsGetJavaScriptEventList
					.getString("FunctionName"));
			objEvent.setPageFieldName(rsGetJavaScriptEventList
					.getString("pagefieldname"));
			orderJavaScriptList.add(objEvent);
		}*/

		// Field Default Values
		pstmtGetDefaultValuesList = con
				.prepareCall("{ call sp_GetDynamicFieldsPickList (?) }");
		pstmtGetDefaultValuesList.setInt(1, tableId);
		rsGetDefaultVaulesList = pstmtGetDefaultValuesList
				.executeQuery();
		DynamicDefaultValue objDValue = null;
		List<DynamicDefaultValue> orderdefaultValuesList = new ArrayList<DynamicDefaultValue>();

		
		while (rsGetDefaultVaulesList.next()) {
			objDValue = new DynamicDefaultValue();
			objDValue.setFieldId(rsGetDefaultVaulesList.getInt("DynamicFieldId"));
			objDValue.setDefaultValue((rsGetDefaultVaulesList.getString("DisplayText")+"&1&"+String.valueOf(rsGetDefaultVaulesList.getInt("SortOrder"))));
			objDValue.setSortOrder(rsGetDefaultVaulesList.getInt("SortOrder"));
			orderdefaultValuesList.add(objDValue);
		}
		
		
		/*while (rsGetDefaultVaulesList.next()) {
			objDValue = new DynamicDefaultValue();
			objDValue.setFieldId(rsGetDefaultVaulesList
					.getInt("DynamicFieldId"));
			objDValue.setDefaultValue(rsGetDefaultVaulesList
					.getString("DisplayText"));
			objDValue.setSortOrder(rsGetDefaultVaulesList
					.getInt("SortOrder"));
			orderdefaultValuesList.add(objDValue);
		}*/

		// Field Values
		pstmtGetOrderStructList = con
				.prepareCall("{ call sp_GetDynamicConfig(?, ?, ?, ?) }");
		pstmtGetOrderStructList.setInt(1, screenId);
		pstmtGetOrderStructList.setInt(2, tableId);
		pstmtGetOrderStructList.setInt(3, 0);
		pstmtGetOrderStructList.setString(4, strType);
		rsGetOrderStructList = pstmtGetOrderStructList.executeQuery();
		DynamicField objDetail = null;
		dynaTable.getStructureList().clear();
		while (rsGetOrderStructList.next()) {
			objDetail = new DynamicField();
			objDetail.setFieldId(rsGetOrderStructList
					.getInt("DynamicFieldId"));
			objDetail.setFieldName(rsGetOrderStructList
					.getString("pagefieldname"));
			objDetail.setLabelName(rsGetOrderStructList
					.getString("DisplayName"));
			objDetail.setDbfieldName(rsGetOrderStructList
					.getString("dbfieldname"));
			objDetail.setDataTypeId(rsGetOrderStructList
					.getInt("DataTypeId"));
			objDetail.setDataType(rsGetOrderStructList
					.getString("DataTypeName"));
			objDetail.setControlTypeId(rsGetOrderStructList
					.getInt("FieldTypeId"));
			objDetail.setControlType(rsGetOrderStructList
					.getString("FieldTypeName"));
			objDetail.setFloatPosition(rsGetOrderStructList
					.getFloat("SortOrder"));
			objDetail.setLength(rsGetOrderStructList
					.getString("length"));
			objDetail.setColumnWidth(rsGetOrderStructList
					.getString("columnWidth"));
			objDetail.setRequired(rsGetOrderStructList
					.getBoolean("IsRequired"));
			objDetail.setActive(rsGetOrderStructList
					.getBoolean("Active"));
			objDetail
					.setFixed(rsGetOrderStructList.getBoolean("Fixed"));
			objDetail.setFieldAlignment(rsGetOrderStructList
					.getString("Alignment"));

			//objDetail.setDynamicJavaScriptList(getJavaScriptList(
					//objDetail.getFieldId(), orderJavaScriptList));
			objDetail.setDefaultValuesList(getDefaultValuesList(
					objDetail.getFieldId(), orderdefaultValuesList));
			objDetail.setFieldValue(" "); // to avoid showing null value
											// on page

			dynaTable.getStructureList().add(objDetail);
			objDetail=null;
		}		
	
	}finally {
		ArrayOfPreparedStatement = new PreparedStatement[5];
		ArrayOfPreparedStatement[0] = pstmtGetOrderStructList;
		ArrayOfPreparedStatement[1] = pstmtGetFormScriptList;
		ArrayOfPreparedStatement[2] = pstmtGetOrderEventList;
		ArrayOfPreparedStatement[3] = pstmtGetFormData;
		ArrayOfPreparedStatement[4] = pstmtGetDefaultValuesList;
		ArrayOfResultSet = new ResultSet[5];
		ArrayOfResultSet[0] = rsGetOrderStructList;
		ArrayOfResultSet[1] = rsGetJavaScriptEventList;
		ArrayOfResultSet[2] = rsGetFormData;
		ArrayOfResultSet[3] = rsGetFormScriptList;
		ArrayOfResultSet[4] = rsGetDefaultVaulesList;
		DatabaseConnection.closeAll(ArrayOfPreparedStatement,
				ArrayOfResultSet);
	}
	return dynaTable;
	}
	
	public boolean saveFormFieldStructureForScreens(Connection con, String xmlStructure,int fieldId){
		try {			
				PreparedStatement pstmtGetAllEventTypes = null;
				
				try {					
					pstmtGetAllEventTypes = con.prepareCall("{ call sp_CreateDynamicConfig (?,?) }");
					System.out.println("xmlStructure--screen--->"+xmlStructure);
					
					pstmtGetAllEventTypes.setNString(1, xmlStructure);
					pstmtGetAllEventTypes.setInt(2, fieldId);
					
					pstmtGetAllEventTypes.execute();
					
					
					return true;

				} finally {
					if (pstmtGetAllEventTypes != null)
						pstmtGetAllEventTypes.close();
				
				}							
										
		} catch (Exception sqe) {
			sqe.printStackTrace();			
			return false;
		} finally {
//			try {
////				if (con != null)
////					con.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
		}
	}

	public void updateColumnPreferencesVisibility(Connection con, int userId,int screenId){
		try {			
				CallableStatement cstmt=null;
				try {					
					
					cstmt=con.prepareCall("{ call pr_update_column_preferences_visibility(?,?) }");
					cstmt.setInt(1, userId);
					cstmt.setInt(2, screenId);
					cstmt.executeUpdate();
					
				

				} finally {
					if (cstmt != null)
						cstmt.close();
				}							
										
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
	}

	
	/**
	 * function for field definition
	 */
	public DynamicForm getDynamicFieldStructure(Connection con,
			DynamicForm dynamicForm) throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetFieldStructList = null;
		PreparedStatement pstmtGetFieldDefaultValuesList = null;
		ResultSet rsGetFieldsStructList = null;
		ResultSet rsGetFieldDefaultVaulesList = null;
		try {

			// Field Default Values
			pstmtGetFieldDefaultValuesList = con
					.prepareCall("{ call sp_GetDynamicFieldsPickList(?) }");
			pstmtGetFieldDefaultValuesList.setInt(1, dynamicForm.getTableId());
			rsGetFieldDefaultVaulesList = pstmtGetFieldDefaultValuesList
					.executeQuery();
			DynamicDefaultValue objDValue = null;
			List<DynamicDefaultValue> orderdefaultValuesList = new ArrayList<DynamicDefaultValue>();

			while (rsGetFieldDefaultVaulesList.next()) {
				objDValue = new DynamicDefaultValue();
				objDValue.setFieldId(rsGetFieldDefaultVaulesList.getInt("DynamicFieldId"));
//				objDValue.setDefaultValue(rsGetFieldDefaultVaulesList.getString("DisplayText"));
				objDValue.setDefaultValue((rsGetFieldDefaultVaulesList.getString("DisplayText")+"&1&"+String.valueOf(rsGetFieldDefaultVaulesList.getInt("SortOrder"))));
				objDValue.setSortOrder(rsGetFieldDefaultVaulesList.getInt("SortOrder"));
				orderdefaultValuesList.add(objDValue);
				objDValue=null;
			}
			
			// Field Values
			pstmtGetFieldStructList = con
					.prepareCall("{ call sp_GetDynamicFieldsConfig(?) }");
			pstmtGetFieldStructList.setInt(1, dynamicForm.getTableId());
			rsGetFieldsStructList = pstmtGetFieldStructList.executeQuery();
			DynamicField objDetail = null;
			dynamicForm.getStructureList().clear();
			while (rsGetFieldsStructList.next()) {
				objDetail = new DynamicField();
				objDetail.setFieldId(rsGetFieldsStructList
						.getInt("DynamicFieldId"));
				objDetail.setFieldName(rsGetFieldsStructList
						.getString("pagefieldname"));
				objDetail.setLabelName(rsGetFieldsStructList
						.getString("LabelName"));
				objDetail.setDbfieldName(rsGetFieldsStructList
						.getString("dbfieldname"));
				objDetail.setDataTypeId(rsGetFieldsStructList
						.getInt("DataTypeId"));
				objDetail.setDataType(rsGetFieldsStructList
						.getString("DataTypeName"));
				objDetail.setControlTypeId(rsGetFieldsStructList
						.getInt("FieldTypeId"));
				objDetail.setControlType(rsGetFieldsStructList
						.getString("FieldTypeName"));
				objDetail.setLength(rsGetFieldsStructList.getString("length"));
				objDetail.setColumnWidth(rsGetFieldsStructList.getString("columnWidth"));
				objDetail.setRequired(rsGetFieldsStructList.getBoolean("IsRequired"));
				objDetail.setFixed(rsGetFieldsStructList.getBoolean("Fixed"));
				objDetail.setDefaultValuesList(getDefaultValuesList(objDetail.getFieldId(), orderdefaultValuesList));
				objDetail.setFieldValue(" "); // to avoid showing null value on
												// page
				dynamicForm.getStructureList().add(objDetail);
				objDetail=null;
			}

		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[2];
			ArrayOfPreparedStatement[0] = pstmtGetFieldStructList;
			ArrayOfPreparedStatement[1] = pstmtGetFieldDefaultValuesList;
			ArrayOfResultSet = new ResultSet[2];
			ArrayOfResultSet[0] = rsGetFieldsStructList;
			ArrayOfResultSet[1] = rsGetFieldDefaultVaulesList;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return dynamicForm;
	}
	
	 public List<DynamicFields> getDynamicFields(Connection con,String tableIds,int fixed)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtGetStatus=null;
		    ResultSet rsGetStatus = null;  
		    StringBuffer objBuffer = new StringBuffer();
		    List<DynamicFields> listStatus=new ArrayList<DynamicFields>();
		    try{ 
		    	if(fixed==1){
		    	 	objBuffer.append(SQLUtil.GET_DYNAMICFIELDS);
	 		    	objBuffer.append("("+tableIds+") AND df.fixed="+fixed+" AND dr.Active=1  ORDER BY df.tableId ,dr.SortOrder");
		    	}
		    	else{
		    		objBuffer.append(SQLUtil.GET_DYNAMICFIELDS);
		    		objBuffer.append("("+tableIds+") AND df.fixed="+fixed+" AND dr.Active=1  ORDER BY df.tableId,dr.SortOrder");
		    	}
		    	
		    	
		      pstmtGetStatus = con.prepareStatement(objBuffer.toString());      
		      rsGetStatus = pstmtGetStatus.executeQuery();
		      while(rsGetStatus.next()){
		    	  DynamicFields objSO = new DynamicFields();
		    	objSO.setId(rsGetStatus.getInt(1));
		    	objSO.setName(rsGetStatus.getString(2));
		    	objSO.setPageFieldName(rsGetStatus.getString(3));
		        listStatus.add(objSO);
		       objSO=null;
		      }
		      
		     objBuffer=null;
		   
		    }finally{      
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] =  pstmtGetStatus;
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rsGetStatus;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return listStatus;
		  }
	 
		public String getDynamicDbName(Connection con,int dynamicfieldId)throws SQLException,Exception{
			PreparedStatement ArrayOfPreparedStatement[] = null;
			ResultSet ArrayOfResultSet[] = null;
			PreparedStatement pstmtGetDynamicDbname=null;
			ResultSet rsGetDynamicDbname = null;   
			String dynamicDbName=null;
			try{      

				pstmtGetDynamicDbname = con.prepareStatement(SQLUtil.GET_DYNAMIC_DBNAME); 
				pstmtGetDynamicDbname.setInt(1, dynamicfieldId);
				rsGetDynamicDbname = pstmtGetDynamicDbname.executeQuery();
				if(rsGetDynamicDbname.next()){        
					dynamicDbName = rsGetDynamicDbname.getString(1);
				}

			}finally{
				ArrayOfPreparedStatement = new PreparedStatement[1];
				ArrayOfPreparedStatement[0] = pstmtGetDynamicDbname;
				ArrayOfResultSet = new ResultSet[1];
				ArrayOfResultSet[0] = rsGetDynamicDbname;
				DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
			}
			return dynamicDbName;
		}


		public int getDynamicTableId(Connection con,int dynamicfieldId)throws SQLException,Exception{
			PreparedStatement ArrayOfPreparedStatement[] = null;
			ResultSet ArrayOfResultSet[] = null;
			PreparedStatement pstmtGetDynamicDbname=null;
			ResultSet rsGetDynamicDbname = null;   
			int dynamicTableId=0;
			try{      

				pstmtGetDynamicDbname = con.prepareStatement(SQLUtil.GET_DYNAMIC_TABLEID); 
				pstmtGetDynamicDbname.setInt(1, dynamicfieldId);
				rsGetDynamicDbname = pstmtGetDynamicDbname.executeQuery();
				if(rsGetDynamicDbname.next()){        
					dynamicTableId = rsGetDynamicDbname.getInt(1);
				}

			}finally{
				ArrayOfPreparedStatement = new PreparedStatement[1];
				ArrayOfPreparedStatement[0] = pstmtGetDynamicDbname;
				ArrayOfResultSet = new ResultSet[1];
				ArrayOfResultSet[0] = rsGetDynamicDbname;
				DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
			}
			return dynamicTableId;
		}

}
