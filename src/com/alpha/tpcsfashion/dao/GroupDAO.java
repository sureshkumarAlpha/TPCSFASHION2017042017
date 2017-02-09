package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.Group;
import com.alpha.tpcsfashion.beans.GroupType;
import com.alpha.tpcsfashion.beans.RawMaterial;
import com.alpha.tpcsfashion.beans.Selection;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class GroupDAO {

	public int getGroupsCount(Connection con, int iPageSize)
			throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGroupCount = null;
		ResultSet rsGroupCount = null;
		int iCount = 0;
		try {
			pstmtGroupCount = con.prepareStatement(SQLUtil.GROUP_COUNT);
			rsGroupCount = pstmtGroupCount.executeQuery();
			rsGroupCount.next();
			iCount = rsGroupCount.getInt(1);
			iCount = (iCount / iPageSize) + (iCount % iPageSize > 0 ? 1 : 0);
		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtGroupCount;
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfResultSet[0] = rsGroupCount;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return iCount;
	}

	public List<Group> getGroups(Connection con, int iStart, int iPageSize)
			throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		CallableStatement cstmtGetGroups = null;
		ResultSet rsGetGroups = null;
		List<Group> groups = new ArrayList<Group>();
		try {
			cstmtGetGroups = con
					.prepareCall("{call pr_groupmasterpaging(?,?)}");
			cstmtGetGroups.setInt(1, iStart);
			cstmtGetGroups.setInt(2, iPageSize);
			rsGetGroups = cstmtGetGroups.executeQuery();
			while (rsGetGroups.next()) {
				Group objGroup = new Group();
				objGroup.setGroupId(rsGetGroups.getInt(1));
				objGroup.setGroupCode(rsGetGroups.getString(2));
				objGroup.setGroupName(rsGetGroups.getString(3));
				objGroup.setShortName(rsGetGroups.getString(4));
				objGroup.setGroupUnder(rsGetGroups.getInt(5));
				objGroup.setGroupedUnderName(rsGetGroups.getString(6));
				objGroup.setGroupTypeId(rsGetGroups.getInt(7));
				objGroup.setGroupTypeName(rsGetGroups.getString(8));
				groups.add(objGroup);

			}
		} finally {
			if (cstmtGetGroups != null)
				cstmtGetGroups.close();

			ArrayOfPreparedStatement = new PreparedStatement[0];
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfResultSet[0] = rsGetGroups;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return groups;
	}

	public boolean insertGroup(Connection con, Group group, int iLocationId,
			int iYearId) throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtInsertGroup = null;
		ResultSet rsGereratedKey = null;
		CallableStatement csInsertGroupLink = null;
		boolean bFlag = false;
		try {
			pstmtInsertGroup = con.prepareStatement(SQLUtil.INSERT_GROUP,
					Statement.RETURN_GENERATED_KEYS);
			pstmtInsertGroup.setString(1, group.getGroupName());
			pstmtInsertGroup.setString(2, group.getGroupCode());
			pstmtInsertGroup.setString(3, group.getShortName());
			//pstmtInsertGroup.setInt(4, group.getIsBarCodeRequired());
			//pstmtInsertGroup.setInt(5, group.getIsTrackRequired());
			pstmtInsertGroup.setInt(4, group.getGroupTypeId());
			pstmtInsertGroup.setInt(5, group.getGroupUnder());
			pstmtInsertGroup.setInt(6, group.getPrimaryGroup());
			pstmtInsertGroup.setInt(7, group.getGroupLevel());
			pstmtInsertGroup.setInt(8, group.getCompanyId());
			pstmtInsertGroup.setInt(9, group.getEnteredBy());
			pstmtInsertGroup.setInt(10, iLocationId);
			int iCount = pstmtInsertGroup.executeUpdate();
			if (iCount > 0) {
				rsGereratedKey = pstmtInsertGroup.getGeneratedKeys();
				while (rsGereratedKey.next()) {
					csInsertGroupLink = con
							.prepareCall("{? = call pr_insertgrouplink(?,?,?,?,?)}");
					csInsertGroupLink.registerOutParameter(1, Types.INTEGER);
					csInsertGroupLink.setInt(2, rsGereratedKey.getInt(1));
					csInsertGroupLink.setInt(3, group.getGroupUnder());
					csInsertGroupLink.setInt(4, group.getCompanyId());
					csInsertGroupLink.setInt(5, iYearId);
					csInsertGroupLink.setInt(6, iLocationId);
					csInsertGroupLink.executeUpdate();
					int iResult = csInsertGroupLink.getInt(1);
					bFlag = (iResult > 0) ? true : false;
				}
			}

			// bFlag=(iCount>0)?true:false;
		} finally {
			if (csInsertGroupLink != null)
				csInsertGroupLink.close();
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtInsertGroup;
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfResultSet[0] = rsGereratedKey;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return bFlag;
	}

	// public boolean insertGroup(Connection con,Group group)throws
	// SQLException,Exception{
	// PreparedStatement ArrayOfPreparedStatement[] = null;
	// ResultSet ArrayOfResultSet[] = null;
	// CallableStatement csInsertGroup = null;
	// //PreparedStatement pstmtInsertGroup=null;
	// boolean bFlag = false;
	// try{
	// csInsertGroup =
	// con.prepareCall("{? = call pr_insertitemgroup(?,?,?,?,?,?,?,?,?,?,?)}");
	// csInsertGroup.registerOutParameter(1, Types.INTEGER);
	// csInsertGroup.setString(2,group.getGroupName());
	// csInsertGroup.setString(3,group.getGroupCode());
	// csInsertGroup.setString(4,group.getShortName());
	// csInsertGroup.setInt(5,group.getIsBarCodeRequired());
	// csInsertGroup.setInt(6,group.getIsTrackRequired());
	// csInsertGroup.setInt(7,group.getGroupTypeId());
	// csInsertGroup.setInt(8,group.getGroupUnder());
	// csInsertGroup.setInt(9,group.getPrimaryGroup());
	// csInsertGroup.setInt(10,group.getGroupLevel());
	// csInsertGroup.setInt(11,group.getCompanyId());
	// csInsertGroup.setInt(12,group.getEnteredBy());
	// csInsertGroup.executeUpdate();
	// int iCount = csInsertGroup.getInt(1);
	// bFlag=(iCount>0)?true:false;
	// }finally{
	// ArrayOfPreparedStatement = new PreparedStatement[0];
	// ArrayOfResultSet = new ResultSet[0];
	// DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	// }
	// return bFlag;
	// }

	public List<Group> getGroupType(Connection con, int iGroupTypeId)
			throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetGroupType = null;
		ResultSet rsGetGroupType = null;
		List<Group> groups = new ArrayList<Group>();
		try {
//			pstmtGetGroupType = con.prepareStatement(SQLUtil.GET_SUB_GROUP);
			pstmtGetGroupType.setInt(1, iGroupTypeId);
			rsGetGroupType = pstmtGetGroupType.executeQuery();

			while (rsGetGroupType.next()) {
				Group objGroup = new Group();

				objGroup.setGroupId(rsGetGroupType.getInt(1));
				objGroup.setGroupName(rsGetGroupType.getString(2));
				objGroup.setGroupCode(rsGetGroupType.getString(3));
				objGroup.setShortName(rsGetGroupType.getString(4));
				objGroup.setGroupTypeName(rsGetGroupType.getString(5));
				objGroup.setGroupTypeId(rsGetGroupType.getInt(6));
				objGroup.setGroupLevel(rsGetGroupType.getInt(7));
				groups.add(objGroup);
			}
		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfPreparedStatement[0] = pstmtGetGroupType;
			ArrayOfResultSet[0] = rsGetGroupType;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return groups;
	}

	public Group getGroupInfo(Connection con, int iGroupId)
	throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetGroupInfo = null;
		ResultSet rsGetGroupInfo = null;
		Group objGroup = null;
		try {
			pstmtGetGroupInfo = con.prepareStatement(SQLUtil.GET_GROUP_INFO);
			pstmtGetGroupInfo.setInt(1, iGroupId);
			rsGetGroupInfo = pstmtGetGroupInfo.executeQuery();
			while (rsGetGroupInfo.next()) {
				objGroup = new Group();

				objGroup.setGroupTypeId(rsGetGroupInfo.getInt(1));
				objGroup.setGroupCode(rsGetGroupInfo.getString(2));
				objGroup.setGroupName(rsGetGroupInfo.getString(3));
				objGroup.setShortName(rsGetGroupInfo.getString(4));
				objGroup.setGroupUnder(rsGetGroupInfo.getInt(5));
				objGroup.setGroupedUnderName(rsGetGroupInfo.getString(6));
				//objGroup.setIsBarCodeRequired(rsGetGroupInfo.getInt(7));
				//objGroup.setIsTrackRequired(rsGetGroupInfo.getInt(8));
				objGroup.setGroupLevel((rsGetGroupInfo.getInt(7) - 1));
				objGroup.setGroupId(rsGetGroupInfo.getInt(8));

			}
		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtGetGroupInfo;
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfResultSet[0] = rsGetGroupInfo;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return objGroup;
	}
	public boolean updateGroup(Connection con, Group group, int iCompanyId,
			int iLocationId, int iYearId) throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtUpdateGroup = null;
		CallableStatement csUpdateGroupLink = null;
		boolean bFlag = false;
		try {
			pstmtUpdateGroup = con.prepareStatement(SQLUtil.UPDATE_GROUP);
			pstmtUpdateGroup.setInt(1, group.getGroupTypeId());
			pstmtUpdateGroup.setString(2, group.getGroupCode());
			pstmtUpdateGroup.setString(3, group.getGroupName());
			pstmtUpdateGroup.setString(4, group.getShortName());
			pstmtUpdateGroup.setInt(5, group.getGroupUnder());
			pstmtUpdateGroup.setInt(6, group.getPrimaryGroup());
			pstmtUpdateGroup.setInt(7, group.getGroupLevel());
			//pstmtUpdateGroup.setInt(8, group.getIsTrackRequired());
			//pstmtUpdateGroup.setInt(9, group.getIsBarCodeRequired());
			pstmtUpdateGroup.setInt(8, group.getUpdatedBy());
			pstmtUpdateGroup.setInt(9, group.getGroupId());
			int iCount = pstmtUpdateGroup.executeUpdate();

			if (iCount > 0) {
				csUpdateGroupLink = con
						.prepareCall("{? = call pr_insertgrouplink(?,?,?,?,?)}");
				csUpdateGroupLink.registerOutParameter(1, Types.INTEGER);
				csUpdateGroupLink.setInt(2, group.getGroupId());
				csUpdateGroupLink.setInt(3, group.getGroupUnder());
				csUpdateGroupLink.setInt(4, iCompanyId);
				csUpdateGroupLink.setInt(5, iYearId);
				csUpdateGroupLink.setInt(6, iLocationId);
				csUpdateGroupLink.executeUpdate();
				int iResult = csUpdateGroupLink.getInt(1);
				bFlag = (iResult > 0) ? true : false;

			}
			// bFlag=(iCount>0)?true:false;
		} finally {
			if (csUpdateGroupLink != null)
				csUpdateGroupLink.close();
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtUpdateGroup;
			ArrayOfResultSet = new ResultSet[0];
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return bFlag;
	}

	public boolean deleteGroup(Connection con, int iGroupId)
			throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtDeleteGroup = null, pstmCheckGroup = null, pstmCheckMaterial = null;
		ResultSet rsCheckGroup = null, rsCheckMaterial = null;
		int iCount = 0, iGroupCount = 0, iMaterialCount = 0;
		boolean bFlag = false;
		try {

			pstmCheckGroup = con.prepareStatement(SQLUtil.EXIST_IN_GROUP_UNDER);
			pstmCheckGroup.setInt(1, iGroupId);
			rsCheckGroup = pstmCheckGroup.executeQuery();
			while (rsCheckGroup.next()) {
				iGroupCount = rsCheckGroup.getInt(1);
				if (iGroupCount > 0)
					return false;
			}

			pstmCheckMaterial = con.prepareStatement(SQLUtil.EXIST_IN_MATERIAL);
			pstmCheckMaterial.setInt(1, iGroupId);
			rsCheckMaterial = pstmCheckMaterial.executeQuery();
			while (rsCheckMaterial.next()) {
				iMaterialCount = rsCheckMaterial.getInt(1);
				if (iMaterialCount > 0)
					return false;
			}

			pstmtDeleteGroup = con.prepareStatement(SQLUtil.DELETE_GROUP);
			pstmtDeleteGroup.setInt(1, iGroupId);
			iCount = pstmtDeleteGroup.executeUpdate();
			bFlag = (iCount > 0) ? true : false;
		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[3];
			ArrayOfPreparedStatement[0] = pstmtDeleteGroup;
			ArrayOfPreparedStatement[1] = pstmCheckGroup;
			ArrayOfPreparedStatement[2] = pstmCheckMaterial;
			ArrayOfResultSet = new ResultSet[2];
			ArrayOfResultSet[0] = rsCheckGroup;
			ArrayOfResultSet[1] = rsCheckMaterial;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return bFlag;
	}

	public List<GroupType> getGroupTypeList(Connection con)
			throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetGroupType = null;
		ResultSet rsGetGroupType = null;
		List<GroupType> groups = new ArrayList<GroupType>();
		try {
			pstmtGetGroupType = con
					.prepareStatement(SQLUtil.GET_GROUP_TYPE_LIST);
			rsGetGroupType = pstmtGetGroupType.executeQuery();

			while (rsGetGroupType.next()) {
				GroupType objGroup = new GroupType();
				objGroup.setGroupTypeId(rsGetGroupType.getInt(1));
				objGroup.setGroupType(rsGetGroupType.getString(2));
				groups.add(objGroup);
			}
		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfPreparedStatement[0] = pstmtGetGroupType;
			ArrayOfResultSet[0] = rsGetGroupType;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return groups;
	}

	public boolean isItemGroupExist(Connection con, Group objGroup)
			throws SQLException, Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtIsGroupExist = null;
		ResultSet rsIsGroupExist = null;
		boolean bFlag = false;
		try {
			pstmtIsGroupExist = con.prepareStatement(SQLUtil.IS_GROUP_EXIST);
			pstmtIsGroupExist.setString(1, objGroup.getGroupName());
			pstmtIsGroupExist.setString(2, objGroup.getGroupCode());
			pstmtIsGroupExist.setInt(3, objGroup.getGroupId());
			rsIsGroupExist = pstmtIsGroupExist.executeQuery();
			while (rsIsGroupExist.next()) {
				bFlag = (rsIsGroupExist.getInt(1) > 0) ? true : false;
			}
		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtIsGroupExist;
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfResultSet[0] = rsIsGroupExist;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return bFlag;
	}

	public int getGroupCodeLength(Connection con) throws SQLException,
			Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGrpCodeLength = null;
		ResultSet rsGrpCodeLength = null;
		int iLength = 0;
		try {
			pstmtGrpCodeLength = con
					.prepareStatement(SQLUtil.GROUP_CODE_LENGTH);
			rsGrpCodeLength = pstmtGrpCodeLength.executeQuery();
			rsGrpCodeLength.next();
			iLength = rsGrpCodeLength.getInt(1);
		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtGrpCodeLength;
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfResultSet[0] = rsGrpCodeLength;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,
					ArrayOfResultSet);
		}
		return iLength;
	}

	
	public List<String> getUomFromTable(Connection con)
	throws SQLException, Exception {
PreparedStatement ArrayOfPreparedStatement[] = null;
ResultSet ArrayOfResultSet[] = null;
PreparedStatement pstmtGroupCount = null;
ResultSet rsGroupCount = null;
String areaUom=null;
String weightUom=null;
List<String> uom = new ArrayList<String>();
int iCount = 0;
try {
//	pstmtGroupCount = con.prepareStatement(SQLUtil.SELECT_AREA_UOM);
	rsGroupCount = pstmtGroupCount.executeQuery();
	rsGroupCount.next();
	 areaUom=rsGroupCount.getString(1);
	
	 uom.add(areaUom);
	 
//	 pstmtGroupCount = con.prepareStatement(SQLUtil.SELECT_WEIGHT_UOM);
		rsGroupCount = pstmtGroupCount.executeQuery();
		rsGroupCount.next();
		 weightUom=rsGroupCount.getString(1);
		 uom.add(weightUom);
	 
} finally {
	ArrayOfPreparedStatement = new PreparedStatement[1];
	ArrayOfPreparedStatement[0] = pstmtGroupCount;
	ArrayOfResultSet = new ResultSet[1];
	ArrayOfResultSet[0] = rsGroupCount;
	DatabaseConnection.closeAll(ArrayOfPreparedStatement,
			ArrayOfResultSet);
}
return uom;
}
	
	
	
	
	public String getGroupMasterForPrint(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		List<Group> groups = new ArrayList<Group>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer strBuffer=new StringBuffer();
		RawMaterial objRawMaterial=null;
		try{
//		   StringBuffer query = new StringBuffer(SQLUtil.GET_ALL_GROUP_PRINT);
//		   pstmt=con.prepareCall(query.toString());
		   rs=pstmt.executeQuery();
			while (rs.next()) {
				Group objGroup = new Group();
				objGroup.setGroupId(rs.getInt(1));
				objGroup.setGroupCode(rs.getString(2));
				objGroup.setGroupName(rs.getString(3));
				objGroup.setShortName(rs.getString(4));
				objGroup.setGroupUnder(rs.getInt(5));
				objGroup.setGroupedUnderName(rs.getString(6));
				objGroup.setGroupTypeId(rs.getInt(7));
				objGroup.setGroupTypeName(rs.getString(8));
				groups.add(objGroup);

			}
		   strBuffer.append("<table class=\"rowline\" cellspacing=\"1\" cellpadding=\"4\" width=\"100%\" style=\"border-top:4px solid #193678;border-right:1px solid #193678;border-bottom:1px solid #193678;\" >");                                
		   strBuffer.append("<tr>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Item Group Code </th>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Group Name</th>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Short Name</th>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Sub Group</th>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Group Type</th>");
	    	  strBuffer.append("</tr>");
	    	  
	    	  
	    	  for(int i=0;i<groups.size();i++)
			    {
			      strBuffer.append("<tr>");  
			
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+groups.get(i).getGroupCode()+"</td>");
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+groups.get(i).getGroupName()+"</td>");
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+groups.get(i).getShortName()+"</td>");
				   String s1=groups.get(i).getGroupedUnderName()==null?"":groups.get(i).getGroupedUnderName();
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+s1+"</td>");
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+groups.get(i).getGroupTypeName()+"</td>");
			   
			   strBuffer.append("</tr>");
			    }
	    	     strBuffer.append("</table>");
		   
		}
		finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return strBuffer.toString();
	}
	
	
}
