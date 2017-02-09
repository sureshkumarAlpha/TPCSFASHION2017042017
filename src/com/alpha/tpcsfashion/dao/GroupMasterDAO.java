package com.alpha.tpcsfashion.dao;

import java.io.IOException;
import java.net.UnknownHostException;
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

import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.GroupMaster;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.Validator;
public class GroupMasterDAO
{

	public GroupMaster getAllItemGroupsOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,String strSearchCriteria,List<String> orderBy,int screenId,int tableId,String materialAttachPath)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		CallableStatement cstmt=null;
		ResultSet rs = null; 
		ResultSet rsAttach = null; 
		PreparedStatement pstmtGetMatInfoDynamic =null;
		ResultSet rsGetMatInfoDynamic=null;
		GroupMaster itemgroup=new GroupMaster();

		String strbuffer="";
		try{
			List<Map<String,String>> itemList=new ArrayList<Map<String,String>>();	
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();  
			List<DynamicFields> dynamicFieldsListMaterial = dynamicFieldsDAO.getDynamicFields(con,screenId, tableId);

			Map<String,String> colPreMap=getColPreMap(con,SubdocumentId.ITEM_GROUPS);
			StringBuffer query = new StringBuffer(SQLUtil.GET_GROUP_MASTER);


			if(strSearchCriteria!=null && !strSearchCriteria.isEmpty()){
				query.append("WHERE "+strSearchCriteria);
			}

			String order=" order by ";
			if(orderBy!=null && !orderBy.isEmpty()){

				for(String strOrderBy:orderBy){
					if(strOrderBy!=null){
						strbuffer+=strOrderBy+",";  
					}
					if(strbuffer.length()>0){
						strbuffer=strbuffer.substring(0, strbuffer.length() - 1);
						query.append(order+strbuffer+" desc");
					}
				}

			}

//			System.out.println("query.toString() :"+query.toString());

			cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
			cstmt.setString(1,query.toString());
			cstmt.setInt(2,pc.iStart);
			cstmt.setInt(3,pc.iEnd);
			rs=cstmt.executeQuery();
			while(rs.next()){
				Map<String,String> map = new HashMap<String,String>();

				map.put("ItemGroup.groupId", rs.getString("group_id"));
				map.put(colPreMap.get("group_code"), rs.getString("group_code"));
				map.put(colPreMap.get("group_name"), rs.getString("group_name"));
				map.put(colPreMap.get("parent_group_id"), rs.getString("groupunder_name"));
				map.put(colPreMap.get("group_type_id"), rs.getString("group_type"));
				map.put(colPreMap.get("status"), rs.getString("status"));


				//Start - Added for dynamic fields
				pstmtGetMatInfoDynamic = con.prepareStatement(SQLUtil.SELECT_ITEMGROUPS_DYNAMIC_BY_ID);
				pstmtGetMatInfoDynamic.setInt(1,Validator.convertToInteger(rs.getString("group_id")));  
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


				itemList.add(map);
				map=null;
			}
			itemgroup.setItemGroupMapList(itemList);
			itemList=null;
			dynamicFieldsDAO=null;
			dynamicFieldsListMaterial=null;
			colPreMap=null;
			query=null;

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

		return itemgroup;
	}
	public int getPageCount(Connection con, TPCSUser ui,String strSearhQuery) throws SQLException {
		// TODO Auto-generated method stub
		int pageCount=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer buffer=new StringBuffer(SQLUtil.GROUP_MASTER_PAGE_COUNT);
			buffer.append(" where g1.company_id="+ui.getCompanyId()+" and g1.location_id="+ui.getLocationId()+"  ");
			if(strSearhQuery!=null && strSearhQuery!="")
			{
				buffer.append("and "+strSearhQuery.toString());
				}
//			System.out.println("count=="+buffer.toString());
			pstmt=con.prepareStatement(buffer.toString());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				pageCount=rs.getInt(1);
			}
		}
		finally
		{
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return pageCount;
	}
	public int insertGroupMaster(Connection con, GroupMaster gm,TPCSUser ui) throws SQLException {

		CallableStatement cstmt=null;
		int groupId;
		try
		{

			String mode=gm.getEditmode();
			int id=gm.getGroupId();

			System.out.println("mode-->"+mode);
			System.out.println("id-->"+id);
			cstmt=con.prepareCall("{? = call pr_insertGroupMaster(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			SQLXML sqlxml = con.createSQLXML();
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, gm.getSubgroupid());

			cstmt.setString(3,ui.getDisplayUserName());
			cstmt.setInt(4,ui.getCompanyId());
			cstmt.setLong(5,ui.getLocationId());

			cstmt.setInt(6, gm.getGroupTypeId());
			cstmt.setString(7, gm.getGroupcode());
			cstmt.setString(8, gm.getGroupname());
			/*cstmt.setString(9, gm.getShortnmae());*/

			cstmt.setInt(9, gm.getItemtracking());
			cstmt.setInt(10, gm.getBarcodetracking());
			cstmt.setString(11, gm.getEditmode());
			cstmt.setInt(12, gm.getGroupId());
			cstmt.setInt(13, gm.getStatus());
			cstmt.setInt(14,gm.getLotNoTracking());
			cstmt.setInt(15, gm.getDateExpiry());
			cstmt.setInt(16, gm.getNegativeStock());
			cstmt.setInt(17,gm.getIssueWithIO());
			cstmt.setString(18, gm.getTaxCategory());
			cstmt.setInt(19, gm.getSizeApplicable());
			cstmt.setInt(20,gm.getSizeApplicable());
			cstmt.setInt(21, gm.getStockReservation());
			cstmt.setInt(22, gm.getInspectionRequired());
			cstmt.setInt(23, gm.getBarcodeRequired());
			sqlxml.setString(gm.getSqlxmlDynamicFields());
			cstmt.setSQLXML("XmlData",sqlxml);
			int rowsInserted=cstmt.executeUpdate();
			boolean bflag=rowsInserted>0?true:false;
			groupId = cstmt.getInt(1);

		}
		finally
		{

			DatabaseConnection.closeAll(cstmt, null);

		}
		return groupId;
	}
	public List<GroupMaster> getGroupMaster(Connection con, TPCSUser ui,PageConfig pc,String strSearhQuery) throws SQLException, IOException {
		// TODO Auto-generated method stub
		List<GroupMaster> objList=new ArrayList<GroupMaster>();
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append(SQLUtil.GET_GROUP_MASTER);
			buffer.append(" where g1.company_id="+ui.getCompanyId()+" and g1.location_id="+ui.getLocationId()+" ");
			if(strSearhQuery!=null && strSearhQuery!="")
			{
				buffer.append(strSearhQuery.toString());
			}
			buffer.append(" ORDER BY g1.group_id desc ");
			cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");
			cstmt.setString(1,buffer.toString());
			cstmt.setInt(2,pc.iStart);
			cstmt.setInt(3,pc.iEnd);

			rs = cstmt.executeQuery();
			while (rs.next()) {
				GroupMaster obj= new GroupMaster();
				obj.setGroupId(rs.getInt(2));
				obj.setGroupcode(rs.getString(3));
				obj.setGroupname(rs.getString(4));
				obj.setGroupUnderId(rs.getInt(5));
				obj.setGroupUnderName(rs.getString(6));
				obj.setGroupTypeId(rs.getInt(7));
				obj.setGrouptype(rs.getString(8));
				obj.setStatusName(rs.getString(9));
				objList.add(obj);
				obj=null;
			}
		}
		finally
		{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return objList;
	}
	//to insert the value in text box while edit....
	public GroupMaster getGroupMasterInfo(Connection con,TPCSUser ui,int groupId,int screenId,int tableId) throws SQLException,Exception{ 
		// TODO Auto-generated method stub
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;
		PreparedStatement pstmtGetInfoDynamic = null;
		GroupMaster obj= new GroupMaster();
		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListOrderHeader = dynamicFieldsDAO.getDynamicFields(con,screenId, tableId);
		try
		{
			pstmt=con.prepareStatement(SQLUtil.GET_GROUP_MASTER_INFO);
			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_GROUP_HEADER_DYNAMIC);
			pstmt.setInt(1,groupId);
			pstmtGetInfoDynamic.setInt(1,groupId);
			rs = pstmt.executeQuery();
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();
			while (rs.next()) {
				obj.setGroupname(rs.getString(1));
				obj.setGroupTypeId(rs.getInt(2));
				obj.setGroupcode(rs.getString(3));
				obj.setGroupUnderId(rs.getInt(4));
				obj.setGroupUnderName(rs.getString(5));
				obj.setGroupId(rs.getInt(6));
				obj.setItemtracking(rs.getInt(7));
				obj.setBarcodetracking(rs.getInt(8));
				obj.setStatus(rs.getInt(9));
				obj.setGrouptype(rs.getString(10));
				obj.setLotNoTracking(rs.getInt(11));
				obj.setDateExpiry(rs.getInt(12));
				obj.setNegativeStock(rs.getInt(13));
				obj.setIssueWithIO(rs.getInt(14));
				obj.setTaxCategory(rs.getString(15));
				obj.setSizeApplicable(rs.getInt(16));
				obj.setColorApplicable(rs.getInt(17));
				obj.setStockReservation(rs.getInt(18));
				obj.setInspectionRequired(rs.getInt(19));
				obj.setBarcodeRequired(rs.getInt(20));
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
								}
							}
							dynamicFields.setFieldValue(fieldValue);
						}
					}
				}
				obj.setOrderDynList(dynamicFieldsListOrderHeader);


			}

		}finally{     
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


	public boolean deleteGroupMaster(Connection con, int soId) throws SQLException {
		// TODO Auto-generated method stub
		CallableStatement cstmt=null;
		boolean isDeleted=false;
		int columnAffected;
		try
		{

			cstmt=con.prepareCall("{ call pr_deleteGroupMaster(?)}");
			cstmt.setInt(1,soId);
			columnAffected= cstmt.executeUpdate();
			if(columnAffected>0)
			{
				isDeleted=true;
			}
		}

		finally
		{
			DatabaseConnection.closeAll(cstmt, null);
		}
		return isDeleted;
	}	

	public GroupMaster saveGroup(Connection con, GroupMaster objG,TPCSUser ui) throws SQLException {

		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean bFlag=false;
		try {


			pstmt=con.prepareStatement(SQLUtil.INSERT_INTO_GROUPS);
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setString(3, objG.getGroupcode());
			pstmt.setString(4, objG.getGroupname());
			pstmt.setInt(5, objG.getGroupTypeId());
			pstmt.setInt(6, objG.getSubgroupid());
			pstmt.setString(7, ui.getDisplayUserName());
			pstmt.setInt(8, objG.getStatus());
			int count=pstmt.executeUpdate();

			pstmt=con.prepareStatement(SQLUtil.GET_GROUP_ID);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pstmt=con.prepareStatement(SQLUtil.INSERT_INTO_GROUPCONFIG);
				pstmt.setInt(1, ui.getCompanyId());
				pstmt.setInt(2, ui.getLocationId());
				pstmt.setInt(3, rs.getInt(1));
				pstmt.setInt(4, objG.getItemtracking());
				pstmt.setInt(5, objG.getBarcodetracking());
				pstmt.setInt(6, objG.getStatus());
			}

			int iCount = pstmt.executeUpdate();
			bFlag=(iCount>0)?true:false;
			objG.setInserted(bFlag);


		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[5];
			ArrayOfPreparedStatement[0] = pstmt;
			ArrayOfResultSet = new ResultSet[5];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return objG;
	}

	public boolean isMasterGroupCodeNameExist(Connection con,GroupMaster gm,TPCSUser userInfo) throws Exception
	{  
		PreparedStatement pstmt[]=new PreparedStatement[2];
		boolean codeFlag=false,nameFlag=false,bFlag=false;
		ResultSet rs[]=new ResultSet[2];
		try
		{
			StringBuffer codeQuery=new StringBuffer(SQLUtil.GROUP_CODE_EXIST);
			if(gm.getEditmode().equals("e"))
			{
				codeQuery.append(" and group_id<>"+gm.getGroupId());	
			}
			pstmt[0]=con.prepareStatement(codeQuery.toString());
			pstmt[0].setString(1,gm.getGroupcode());
			rs[0]= pstmt[0].executeQuery();
			while(rs[0].next())
			{ 
				codeFlag=(Validator.convertToInteger(rs[0].getString(1)))>0?true:false;
			}
			StringBuffer nameQuery=new StringBuffer(SQLUtil.GROUP_NAME_EXIST);
			if(gm.getEditmode().equals("e"))
			{
				nameQuery.append(" and group_id<>"+gm.getGroupId());	
			}
			pstmt[1]=con.prepareStatement(nameQuery.toString());
			pstmt[1].setString(1,gm.getGroupname());
			rs[1]= pstmt[1].executeQuery();
			while(rs[1].next())
			{ 
				nameFlag=(Validator.convertToInteger(rs[1].getString(1)))>0?true:false;
			}
			if (nameFlag || codeFlag)
			{
				bFlag=true;
			}

		}
		finally
		{
			DatabaseConnection.closeAll(pstmt,rs);

		}
		return bFlag;

	}

	public String getGroupType(Connection con) throws SQLException{
		PreparedStatement pst=null;
		ResultSet rs=null;
		StringBuffer buffer=new StringBuffer("");
		try {
			String query="SELECT group_type_id,group_type FROM group_type ";

			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
			buffer.append("<grouptypes>");
			while(rs.next()){
				buffer.append("<grouptype>");

				buffer.append("<grouptypeid>");
				buffer.append(rs.getInt(1));
				buffer.append("</grouptypeid>");
				buffer.append("<grouptypename>");
				buffer.append(rs.getString(2));
				buffer.append("</grouptypename>");

				buffer.append("</grouptype>");
			}
			buffer.append("</grouptypes>");
		} 
		finally {
			DatabaseConnection.closeAll(pst,rs);
		}
		return buffer.toString();
	}

	public GroupMaster savegrouptype(Connection con, GroupMaster objM,TPCSUser ui) throws SQLException, UnknownHostException {

		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean bFlag=false;
		try {

			pstmt= con.prepareStatement(SQLUtil.IS_GROUPTYPE_EXIST);
			pstmt.setString(1, objM.getGrouptype());
			pstmt.setInt(2, objM.getSeqNo());
			rs= pstmt.executeQuery();
			while(rs.next()){
				bFlag = (rs.getInt(1)>0)?true:false;
				System.out.println("bFlag="+bFlag);
			}
			objM.setGrouptypeExists(bFlag);

			if(!objM.isGrouptypeExists()){

				pstmt=con.prepareStatement(SQLUtil.INSERT_INTO_GROUPTYPE);

				pstmt.setString(1,objM.getGrouptype());
				pstmt.setInt(2,objM.getParantGroupTypeId());
				pstmt.setInt(3,objM.getSeqNo());
				int iCount = pstmt.executeUpdate();
				bFlag=(iCount>0)?true:false;
				objM.setInserted(bFlag);
			}


		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[5];
			ArrayOfPreparedStatement[0] = pstmt;
			ArrayOfResultSet = new ResultSet[5];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return objM;
	}

	public String getGroupTypeSlNo(Connection con) throws SQLException{
		PreparedStatement pst=null;
		ResultSet rs=null;
		StringBuffer buffer=new StringBuffer("");
		try {
			String query="SELECT MAX(sl_no)+1 AS Sl_no FROM group_type";
			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
			buffer.append("<grouptypesslnos>");
			while(rs.next()){
				buffer.append("<grouptypeslno>");

				buffer.append("<grouptypeiddisplayslno>");
				buffer.append(rs.getInt(1));
				buffer.append("</grouptypeiddisplayslno>");

				buffer.append("</grouptypeslno>");
			}
			buffer.append("</grouptypesslnos>");
		} 
		finally {
			DatabaseConnection.closeAll(pst,rs);
		}
		return buffer.toString();
	}


	public boolean bulkActiveGroupMaster(Connection con, String[] groupid,String action_mode) throws SQLException {
		// TODO Auto-generated method stub
		Statement pstmt=null;
		boolean isBulkAction=false;
		try
		{
			for(int i=0;i<groupid.length;i++)
			{
				if(action_mode.equals("1"))
				{
					pstmt = con.createStatement();
					pstmt.addBatch("update groups set Status=1 where group_id="+groupid[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
				else
				{
					pstmt = con.createStatement();
					pstmt.addBatch("update groups set Status=0 where group_id="+groupid[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
			}
		}

		finally
		{
			DatabaseConnection.closeAll(pstmt, null);
		}
		return isBulkAction;
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

	public List<GroupMaster> getDynamicFields(Connection con,String tableIds,int fixed)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetStatus=null;
		ResultSet rsGetStatus = null;  
		StringBuffer objBuffer = new StringBuffer();
		List<GroupMaster> listStatus=new ArrayList<GroupMaster>();
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
				GroupMaster objSO = new GroupMaster();
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
