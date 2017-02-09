package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.Variant;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.Validator;

public class VariantDAO {

	public int insertVariant(Connection con, Variant coObj,TPCSUser userInfo) throws SQLException {

		CallableStatement cstmt=null;
		int groupId;
		try	{
			cstmt=con.prepareCall("{? = call pr_insert_Variant(?,?,?,?,?,?,?,?)}");
			SQLXML sqlxml = con.createSQLXML();
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, coObj.getVariantCode());
			System.out.println("Variant code=="+coObj.getVariantCode());
			cstmt.setInt(3,coObj.getVariantId());
			System.out.println("coObj.getVariantId()===="+coObj.getVariantId());
			cstmt.setString(4,coObj.getVariantName());
			System.out.println("coObj.getVariantName()==="+coObj.getVariantName());
			cstmt.setString(5, coObj.getRemarks());
			System.out.println("coObj.getRemarks()==="+coObj.getRemarks());
			cstmt.setInt(6,coObj.getStatus());
			System.out.println("coObj.getStatus()==="+coObj.getStatus());
			cstmt.setInt(7,userInfo.getUserId());
			System.out.println("userInfo.getDisplayUserName()==="+userInfo.getDisplayUserName());
			cstmt.setString(8, coObj.getMode());
			System.out.println("coObj.getMode()===="+coObj.getMode());
			sqlxml.setString(coObj.getSqlxmlDynamicFields());
			System.out.println("coObj.getSqlxmlDynamicFields()=="+coObj.getSqlxmlDynamicFields());
			cstmt.setSQLXML("XmlData",sqlxml);

			int rowsInserted=cstmt.executeUpdate();
			boolean bflag=rowsInserted>0?true:false;

			groupId = cstmt.getInt(1);
		}
		finally	{

			DatabaseConnection.closeAll(cstmt, null);

		}
		return groupId;
	}
	public Variant isMasterVariantExist(Connection con,Variant coObj,TPCSUser userInfo) throws Exception {  
		PreparedStatement pstmt=null;
		boolean codeFlag=false,descFlag=false;
		ResultSet rs=null;
		try	{
			StringBuffer codeQuery=new StringBuffer(SQLUtil.VARIANT_CODE_EXIST);
			if(coObj.getMode().equals("e")){
				codeQuery.append(" and variant_Id<>"+coObj.getVariantId());	
			}
			pstmt=con.prepareStatement(codeQuery.toString());
			pstmt.setString(1,coObj.getVariantCode());
			rs= pstmt.executeQuery();
			while(rs.next()){ 
				codeFlag=(Validator.convertToInteger(rs.getString(1)))>0?true:false;
			}

			StringBuffer nameQuery=new StringBuffer(SQLUtil.VARIANT_DESC_EXIST);
			if(coObj.getMode().equals("e")){
				nameQuery.append(" and variant_Id<>"+coObj.getVariantId());	
			}
			pstmt=con.prepareStatement(nameQuery.toString());
			pstmt.setString(1,coObj.getVariantName());
			rs= pstmt.executeQuery();
			while(rs.next()){ 
				descFlag=(Validator.convertToInteger(rs.getString(1)))>0?true:false;

			}
			coObj.setCodeExists(codeFlag);
			coObj.setDescExists(descFlag);
		}
		finally{
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return coObj;

	}
	public Variant getVariantInfo(Connection con,TPCSUser ui,int coId,int iscreenId, int tableId) throws SQLException,Exception{ 

		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;
		PreparedStatement pstmtGetInfoDynamic = null;
		Variant obj= new Variant();
		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListOrderHeader = dynamicFieldsDAO.getDynamicFields(con,iscreenId, tableId);
		try	{

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_VARIANT_HEADER_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,coId);
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

			pstmt=con.prepareStatement(SQLUtil.GET_VARIANT_MASTER_INFO);
			pstmt.setInt(1,coId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				obj.setVariantCode(rs.getString(1));
				obj.setVariantId(rs.getInt(2));
				obj.setVariantName(rs.getString(3));
				obj.setRemarks(rs.getString(4));
				obj.setStatus(rs.getInt(5));
				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListOrderHeader) {
						if(!dynamicFields.isFixed()) {
							String fieldValue = rsGetInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetInfoDynamic.getString(dynamicFields.getDbFieldName());
							if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {

								if(!fieldValue.equals(""))
								{
									SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
									SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MM-yyyy");
									fieldValue = outFormatter.format(inFormatter.parse(fieldValue)).toString();
									if(fieldValue.equals("01-01-1900"))
										fieldValue="";
									inFormatter=null;
									outFormatter=null;
								}


							}
							dynamicFields.setFieldValue(fieldValue);
						}
					}
				}
				obj.setVariantDynList(dynamicFieldsListOrderHeader);

			}
			dynamicFieldsDAO=null;
			dynamicFieldsListOrderHeader=null;
		}
		finally{     
			ArrayOfPreparedStatement = new PreparedStatement[2];
			ArrayOfPreparedStatement[0] = pstmt;
			ArrayOfPreparedStatement[1] = pstmtGetInfoDynamic;
			ArrayOfResultSet = new ResultSet[2];
			ArrayOfResultSet[0] = rs;
			ArrayOfResultSet[0] = rsGetInfoDynamic;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return obj;
	}	
	public boolean deleteVariant(Connection con, int coId) throws SQLException {

		Statement pstmt=null;
		boolean isDeleted=false;
		try {
			pstmt = con.createStatement();
			pstmt.addBatch("delete from Variant_Master where Variant_Id="+coId);
			int count[]=pstmt.executeBatch();

			isDeleted=count.length>0 && count[0]>0?true:false;
		}

		finally {
			DatabaseConnection.closeAll(pstmt, null);
		}
		return isDeleted;
	}
	public int getPageCount(Connection con, TPCSUser ui,String strSearhQuery) throws SQLException {

		int pageCount=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			StringBuffer buffer=new StringBuffer(SQLUtil.VARIANT_MASTER_PAGE_COUNT);

			if(strSearhQuery!=null && strSearhQuery!=""){
				strSearhQuery=" where "+strSearhQuery;
				buffer.append(strSearhQuery.toString());
			}

			pstmt=con.prepareStatement(buffer.toString());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				pageCount=rs.getInt(1);
			}
		}
		finally	{
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return pageCount;
	}
	public boolean bulkActionVariant(Connection con, String[] coId,String action_mode) throws SQLException {

		Statement pstmt=null;
		boolean isBulkAction=false;
		try	{
			for(int i=0;i<coId.length;i++){
				if(action_mode.equals("1"))	{
					pstmt = con.createStatement();
					pstmt.addBatch("update Variant_Master set status=1 where Variant_Id="+coId[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
				else{
					pstmt = con.createStatement();
					pstmt.addBatch("update Variant_Master set Status=0 where Variant_Id="+coId[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
			}
		}

		finally	{
			DatabaseConnection.closeAll(pstmt, null);
		}
		return isBulkAction;
	}
	public Variant getAllVariantOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo, String strSearhQuery, List<String> orderBy,int iScreenId,int tableId, String variantAttachPath)throws SQLException,Exception {
		// TODO Auto-generated method stub
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		CallableStatement cstmt=null;
		ResultSet rs = null; 
		ResultSet rsAttach = null; 
		PreparedStatement pstmtGetMatInfoDynamic =null;
		ResultSet rsGetMatInfoDynamic=null;
		PreparedStatement pstmt=null;
		Variant col=new Variant();

		//	    String strbuffer="";
		try{
			List<Map<String,String>> matList=new ArrayList<Map<String,String>>();	
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();  
			List<DynamicFields> dynamicFieldsListMaterial = dynamicFieldsDAO.getDynamicFields(con,iScreenId, tableId);

			Map<String,String> colPreMap=getColPreMap(con,SubdocumentId.VARIANT_MASTER);
			//Search Query
			if(strSearhQuery!=null && strSearhQuery!=""){
				strSearhQuery=" and "+ strSearhQuery;
			}

			pstmt = con.prepareStatement("{call VariantMasterpaging(?,?,?)}");
			pstmt.setInt(1,pc.iStart);
			pstmt.setInt(2,pc.iEnd);
			pstmt.setString(3,strSearhQuery!=null && !strSearhQuery.isEmpty()?strSearhQuery:"");
			rs=pstmt.executeQuery();

			while(rs.next()){
				Map<String,String> map = new HashMap<String,String>();

				map.put("Variant.VariantId", rs.getString("variant_id"));
				map.put(colPreMap.get("Variant_Code"), rs.getString("variant_code"));
				map.put(colPreMap.get("Variant_Name"), rs.getString("variant_name"));
				map.put(colPreMap.get("Remarks"), rs.getString("remarks"));
				map.put(colPreMap.get("Status"), rs.getString("Status"));

				pstmt = con.prepareStatement(SQLUtil.SELECT_VARIANT);
				pstmt.setInt(1, Validator.convertToInteger(rs.getString("Variant_id")));
				rsAttach=pstmt.executeQuery();

				//Start - Added for dynamic fields

				pstmtGetMatInfoDynamic = con.prepareStatement(SQLUtil.SELECT_VARIANT_DYNAMIC_BY_ID);
				pstmtGetMatInfoDynamic.setInt(1,Validator.convertToInteger(rs.getString("variant_id")));  
				rsGetMatInfoDynamic = pstmtGetMatInfoDynamic.executeQuery();
				if(rsGetMatInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListMaterial) {
						if(!dynamicFields.isFixed()){
							String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							String fieldValue = rsGetMatInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetMatInfoDynamic.getString(dynamicFields.getDbFieldName());
							if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {
								if(!fieldValue.equals(""))
								{
									SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
									SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MM-yyyy");
									fieldValue = outFormatter.format(inFormatter.parse(fieldValue)).toString();
									if(fieldValue.equals("01-01-1900"))
										fieldValue="";
								}
							}
							map.put(labelName,fieldValue);

						}
					}

				}

				//End - Added for dynamic fields


				matList.add(map);
				map=null;
			}
			col.setVariantMapList(matList);
			matList=null;
			dynamicFieldsDAO=null;
			dynamicFieldsListMaterial=null;
			colPreMap=null;
			//	        query=null;
		}
		finally{
			if(cstmt!=null)
				cstmt.close();

			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0]=pstmtGetMatInfoDynamic;
			ArrayOfResultSet = new ResultSet[2];      
			ArrayOfResultSet[0] = rs;
			ArrayOfResultSet[1] = rsAttach;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}

		return col;
	}
	public Map<String,String> getColPreMap(Connection con,int documentId){

		PreparedStatement pstmt=null;
		ResultSet rs= null;

		Map<String,String> colPreMap=new LinkedHashMap<String, String>();
		try {

			String colPreQuery="select dbfieldname ,column_display_name from column_preferences where subdocument_id="+documentId;

			pstmt=con.prepareStatement(colPreQuery);
			rs=pstmt.executeQuery();//.executeQuery();
			while(rs.next()){


				colPreMap.put(rs.getString(1),rs.getString(2));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return colPreMap;
	}
	public List<Variant> getDynamicFields(Connection con,String tableIds,int fixed)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetStatus=null;
		ResultSet rsGetStatus = null;  
		StringBuffer objBuffer = new StringBuffer();
		List<Variant> listStatus=new ArrayList<Variant>();
		try{ 
			if(fixed==1){
				objBuffer.append(SQLUtil.GET_DYNAMICFIELDS);
				objBuffer.append("("+tableIds+") AND fixed="+fixed+"  ORDER BY df.tableId");
			}
			else{
				objBuffer.append(SQLUtil.GET_DYNAMICFIELDS);
				objBuffer.append("("+tableIds+") AND fixed="+fixed+"  ORDER BY df.tableId");
			}
			pstmtGetStatus = con.prepareStatement(objBuffer.toString());      
			rsGetStatus = pstmtGetStatus.executeQuery();
			while(rsGetStatus.next()){
				Variant objSO = new Variant();
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


}
