package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.DynamicFieldEvents;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.DynamicFormStructure;
import com.alpha.tpcsfashion.beans.DynamicRefTable;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class DynamicFieldsDAO {

	public List<DynamicFields> getDynamicFields(Connection con, int screenId, int tableId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CallableStatement callableStatement = null;
		List<DynamicFields> dynamicFieldsList = null;
		DynamicFields dynamicFields = null;
		try {
//			System.out.println("screenId :" +screenId+" tableid :" +tableId);
			callableStatement = con.prepareCall("{ call sp_GetDynamicConfig(?,?,?,?) }");
			callableStatement.setInt("screenid", screenId);
			callableStatement.setInt("tableid", tableId);
			callableStatement.setBoolean("active",true);
			callableStatement.setInt("multiplyWidth", 12);
			resultSet = callableStatement.executeQuery();
			dynamicFieldsList = new ArrayList<DynamicFields>();
			while (resultSet.next()) {
				dynamicFields = new DynamicFields();
				dynamicFields.setDynamicFieldId(resultSet.getInt("DynamicFieldId"));
				dynamicFields.setPageFieldName(resultSet.getString("pagefieldname"));
				dynamicFields.setDbFieldName(resultSet.getString("dbfieldname"));
				dynamicFields.setDisplayName(resultSet.getString("DisplayName"));
				dynamicFields.setFieldTypeName(resultSet.getString("FieldTypeName"));
				dynamicFields.setDataTypeName(resultSet.getString("DataTypeName"));
				dynamicFields.setSortOrder(resultSet.getInt("SortOrder"));
				dynamicFields.setLength(resultSet.getInt("length"));
				dynamicFields.setColumnWidth(resultSet.getString("columnWidth"));
				dynamicFields.setRequired(resultSet.getBoolean("IsRequired"));
				dynamicFields.setFixed(resultSet.getBoolean("Fixed"));
				dynamicFields.setDefaultValue(resultSet.getString("defaultvalue")==null?"":resultSet.getString("defaultvalue"));
				dynamicFields.setAlignment(resultSet.getString("alignment"));
				dynamicFieldsList.add(dynamicFields);
				dynamicFields=null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dynamicFieldsList;
	}
	
	public DynamicFields getDynamicFieldsInfo(Connection con, int screenId, int tableId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CallableStatement callableStatement = null;
		List<DynamicFields> dynamicFieldsList = null;
		DynamicFields objD=new DynamicFields();
		Map<String,String> dbFieldMap=new LinkedHashMap<String,String>();
//		DynamicFields dynamicFields = null;
		try {
//			System.out.println("screenId :" +screenId+" tableid :" +tableId);
			callableStatement = con.prepareCall("{ call sp_GetDynamicConfig(?,?,?,?) }");
			callableStatement.setInt("screenid", screenId);
			callableStatement.setInt("tableid", tableId);
			callableStatement.setBoolean("active",true);
			callableStatement.setInt("multiplyWidth",1);
			resultSet = callableStatement.executeQuery();
			dynamicFieldsList = new ArrayList<DynamicFields>();
			while (resultSet.next()) {
//				dynamicFields = new DynamicFields();
//				dynamicFields.setDynamicFieldId(resultSet.getInt("DynamicFieldId"));
//				dynamicFields.setPageFieldName(resultSet.getString("pagefieldname"));
//				dynamicFields.setDbFieldName(resultSet.getString("dbfieldname"));
//				dynamicFields.setDisplayName(resultSet.getString("DisplayName"));
//				dynamicFields.setFieldTypeName(resultSet.getString("FieldTypeName"));
//				dynamicFields.setDataTypeName(resultSet.getString("DataTypeName"));
//				dynamicFields.setSortOrder(resultSet.getInt("SortOrder"));
//				dynamicFields.setLength(resultSet.getInt("length"));
//				dynamicFields.setRequired(resultSet.getBoolean("IsRequired"));
//				dynamicFields.setFixed(resultSet.getBoolean("Fixed"));
//				dynamicFields.setDefaultValue(resultSet.getString("defaultvalue")==null?"":resultSet.getString("defaultvalue"));
//				dynamicFields.setAlignment(resultSet.getString("alignment"));
//				dynamicFieldsList.add(dynamicFields);
//				dynamicFields=null;
				
				dbFieldMap.put(resultSet.getString("dbfieldname"), resultSet.getString("DisplayName"));
			}

			objD.setDbFieldMap(dbFieldMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objD;
	}

	public DynamicFormStructure getDynamicFormStructure(Connection con,
			int screenId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CallableStatement callableStatement = null;
		DynamicFormStructure dynamicFormStructure = null;
		try {
			callableStatement = con
					.prepareCall("{ call sp_GetFormStructure(?) }");
			callableStatement.setInt("screenid", screenId);
			resultSet = callableStatement.executeQuery();
			dynamicFormStructure = new DynamicFormStructure();
			while (resultSet.next()) {
				dynamicFormStructure.setTableName(resultSet.getString("ScreenName"));
				dynamicFormStructure.setNoOfSection(resultSet.getInt("NoOfSections"));
				dynamicFormStructure.setAlignment(resultSet.getString("Alignment"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dynamicFormStructure;
	}
	
	
	public List<DynamicFieldEvents> getDynamicFieldEvents(Connection con,int screenId, int tableId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CallableStatement callableStatement = null;
		List<DynamicFieldEvents> dynamicFieldEventList = null;
		DynamicFieldEvents dynamicFieldEvents = null;
		try {
			callableStatement = con
					.prepareCall("{ call sp_GetDynamicFieldEvents(?,?,?) }");
			callableStatement.setInt("screenid", screenId);
			callableStatement.setInt("tableid", tableId);
			callableStatement.setInt("active", 1);
			resultSet = callableStatement.executeQuery();
			dynamicFieldEventList = new ArrayList<DynamicFieldEvents>();
			while (resultSet.next()) {
				dynamicFieldEvents = new DynamicFieldEvents();
				dynamicFieldEvents.setDynamicFieldId(resultSet.getInt("DynamicFieldId"));
				dynamicFieldEvents.setPagefieldname(resultSet.getString("pagefieldname"));
				dynamicFieldEvents.setFunctionName(resultSet.getString("FunctionName"));
				dynamicFieldEvents.setAttachedJS(resultSet.getString("AttachedJS"));
				dynamicFieldEvents.setEventName(resultSet.getString("EventName"));
				dynamicFieldEventList.add(dynamicFieldEvents);
				dynamicFieldEvents=null;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return dynamicFieldEventList;
	}
	
	public List<DynamicFieldEvents> getDynamicFormEvents(Connection con, int screenId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CallableStatement callableStatement = null;
		List<DynamicFieldEvents> dynamicFieldEventList = null;
		DynamicFieldEvents dynamicFieldEvents = null;
		try {
			callableStatement = con
					.prepareCall("{ call sp_GetDynamicRefScreenEvents(?) }");
			callableStatement.setInt("screenid", screenId);
			resultSet = callableStatement.executeQuery();
			dynamicFieldEventList = new ArrayList<DynamicFieldEvents>();
			while (resultSet.next()) {
				dynamicFieldEvents = new DynamicFieldEvents();
//				dynamicFieldEvents.setDynamicFieldId(resultSet.getInt("DynamicFieldId"));
//				dynamicFieldEvents.setPagefieldname(resultSet.getString("pagefieldname"));
				dynamicFieldEvents.setFunctionName(resultSet.getString("FunctionName"));
				dynamicFieldEvents.setAttachedJS(resultSet.getString("AttachedJS"));
				dynamicFieldEvents.setEventName(resultSet.getString("EventName"));
				dynamicFieldEventList.add(dynamicFieldEvents);
				
				dynamicFieldEvents=null;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return dynamicFieldEventList;
	}
	
	
	public List<DynamicFieldEvents> getPickListOptions(Connection con, int tableId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CallableStatement callableStatement = null;
		List<DynamicFieldEvents> pickListOptionsList = null;
		DynamicFieldEvents dynamicFieldEvents = null;
		try {
			callableStatement = con.prepareCall("{ call sp_GetDynamicFieldsPickList(?) }");
			callableStatement.setInt("tableid", tableId);
			resultSet = callableStatement.executeQuery();
			pickListOptionsList = new ArrayList<DynamicFieldEvents>();
			while (resultSet.next()) {
				dynamicFieldEvents = new DynamicFieldEvents();
				dynamicFieldEvents.setDynamicFieldId(resultSet.getInt("DynamicFieldId"));
				dynamicFieldEvents.setPickListOption(resultSet.getString("DisplayText"));
				dynamicFieldEvents.setPickListSortOrder(resultSet.getInt("SortOrder"));
				pickListOptionsList.add(dynamicFieldEvents);
				
				dynamicFieldEvents=null;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return pickListOptionsList;
	}
	
	/*public int getDynamicFieldType(Connection con, String pageFiledName) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	
		int dynamicFieldtype = 0;
		
		try {
			preparedStatement =con.prepareStatement(SQLUtil.SELECT_DYNAMIC_FIELD_TYPE);
			preparedStatement.setString(1, pageFiledName);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				dynamicFieldtype=resultSet.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return dynamicFieldtype;
	}*/
	public List<DynamicRefTable> getAlldynamicPanelIds(Connection con,String tableIds,int screenid)
			throws SQLException, Exception {
		List<DynamicRefTable> result = new ArrayList<DynamicRefTable>();
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareCall("SELECT tableId FROM DynamicRefScreenRefTable WHERE screenid="+screenid+" AND tableId NOT IN("+tableIds+") ");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DynamicRefTable dynamicRefTable = new DynamicRefTable();
				dynamicRefTable.setTableId(rs.getInt(1));
				result.add(dynamicRefTable);
			}

		} finally {
			 DatabaseConnection.closeAll(pstmt,rs);
		}
		return result;

	}
	

}
