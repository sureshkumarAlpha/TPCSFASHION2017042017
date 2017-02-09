package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.GroupSetting;
import com.alpha.tpcsfashion.beans.GroupType;
import com.alpha.tpcsfashion.beans.Material;
import com.alpha.tpcsfashion.beans.RawMaterial;
import com.alpha.tpcsfashion.beans.Spec;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class SpecificationSettingDAO {

	public List<Material> getSpecificationSetting(Connection con,PageConfig pc,String strSearhQuery)throws SQLException,Exception {
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    CallableStatement cstmtGetSpecification=null;
	    PreparedStatement psmtGetSpecification=null;
	    ResultSet rsGetSpecification = null;
	    List<Material> specification=new ArrayList<Material>();
	    try{	    	
	    	StringBuffer buffer=new StringBuffer();
	    	buffer.append(SQLUtil.SELECT_SPECIFICATION_SETTING);
	    	if(strSearhQuery!=null && strSearhQuery!="")
			{
				buffer.append(strSearhQuery.toString());
			}
	    	
	    	cstmtGetSpecification=con.prepareCall("{call sp_Paging(?,?,?)}");
	    	cstmtGetSpecification.setString(1,buffer.toString());
	    	cstmtGetSpecification.setInt(2,pc.iStart);
	    	cstmtGetSpecification.setInt(3,pc.iEnd);
	    	rsGetSpecification=cstmtGetSpecification.executeQuery();
	    	
	    	while(rsGetSpecification.next()){
	    		GroupType objGroupType=new GroupType();	    		
	    		objGroupType.setGroupTypeId(rsGetSpecification.getInt(2));
	    		objGroupType.setGroupType(rsGetSpecification.getString(3));
	    		
	    		Material objMaterial=new Material();
	    		objMaterial.setSpec1(rsGetSpecification.getString(4));
	    		objMaterial.setSpecLen1(rsGetSpecification.getInt(5));
	    		objMaterial.setSpec2(rsGetSpecification.getString(6));
	    		objMaterial.setSpecLen2(rsGetSpecification.getInt(7));
	    		objMaterial.setSpec3(rsGetSpecification.getString(8));
	    		objMaterial.setSpecLen3(rsGetSpecification.getInt(9));
	    		objMaterial.setSpec4(rsGetSpecification.getString(10));
	    		objMaterial.setSpecLen4(rsGetSpecification.getInt(11));
	    		objMaterial.setSpec5(rsGetSpecification.getString(12));
	    		objMaterial.setSpecLen5(rsGetSpecification.getInt(13));
	    		objMaterial.setSpec6(rsGetSpecification.getString(14));
	    		objMaterial.setSpecLen6(rsGetSpecification.getInt(15));
	    		objMaterial.setSpec7(rsGetSpecification.getString(16));
	    		objMaterial.setSpecLen7(rsGetSpecification.getInt(17));
	    		objMaterial.setGroupType(objGroupType);
	    		
	    		objMaterial.setGroupType(objGroupType);
	    		specification.add(objMaterial);
	    		objGroupType=null;
	    		objMaterial=null;
	    		
	    	}	    	
	    	return specification;
	    }
	    finally{
	        if(cstmtGetSpecification!=null)
	        	cstmtGetSpecification.close();
	      
	        ArrayOfPreparedStatement = new PreparedStatement[1];
	        ArrayOfPreparedStatement[0]=psmtGetSpecification;
	        ArrayOfResultSet = new ResultSet[1];      
	        ArrayOfResultSet[0] = rsGetSpecification;
	        DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	      }
	}
	
	public int getSpecificationSettingsCount(Connection con,int iPageSize)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtSpecificationCount=null;
	    ResultSet rsSpecificationCount = null;
	    int iCount = 0;
	    try{ 
	    	pstmtSpecificationCount = con.prepareStatement(SQLUtil.SPECIFICATION_SETTING_COUNT);      
	    	rsSpecificationCount = pstmtSpecificationCount.executeQuery();
	    	rsSpecificationCount.next();
	        iCount = rsSpecificationCount.getInt(1);
	        iCount = (iCount/iPageSize)+(iCount%iPageSize>0?1:0);
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtSpecificationCount;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] =  rsSpecificationCount;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return iCount;
	  }
	
	public boolean insertSpecificationSetting(Connection con,Material specification,TPCSUser userInfo)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    PreparedStatement psInsertSpecification=null;
	    ResultSet ArrayOfResultSet[] = null; 
	    boolean bFlag = false;    
	    try{
	    	psInsertSpecification=con.prepareStatement(SQLUtil.INSERT_SPECIFICATION_SETTING);
	    	psInsertSpecification.setInt(1,specification.getGroupType().getGroupTypeId());
	    	psInsertSpecification.setString(2,specification.getSpec1());
	    	psInsertSpecification.setInt(3,specification.getSpecLen1());
	    	psInsertSpecification.setString(4,specification.getSpec2());
	    	psInsertSpecification.setInt(5,specification.getSpecLen2());
	    	psInsertSpecification.setString(6,specification.getSpec3());
	    	psInsertSpecification.setInt(7,specification.getSpecLen3());
	    	psInsertSpecification.setString(8,specification.getSpec4());
	    	psInsertSpecification.setInt(9,specification.getSpecLen4());
	    	psInsertSpecification.setString(10,specification.getSpec5());
	    	psInsertSpecification.setInt(11,specification.getSpecLen5());
	    	psInsertSpecification.setString(12,specification.getSpec6());
	    	psInsertSpecification.setInt(13,specification.getSpecLen6());
	    	psInsertSpecification.setString(14,specification.getSpec7());
	    	psInsertSpecification.setInt(15,specification.getSpecLen7());	    	
	    	psInsertSpecification.setInt(16,0);
	    	psInsertSpecification.setInt(17,userInfo.getCompanyId());
	    	psInsertSpecification.setInt(18,userInfo.getLocationId());	    	
	    	psInsertSpecification.setInt(19,userInfo.getUserId());
	    	psInsertSpecification.setString(20,specification.getIpAddress());
	    	psInsertSpecification.setString(21,specification.getHostName());

	    	int iCount =psInsertSpecification.executeUpdate();
	    	bFlag=(iCount>0)?true:false;
	    }finally{       
	      ArrayOfPreparedStatement = new PreparedStatement[1];   
	      ArrayOfPreparedStatement[0] = psInsertSpecification;
	      ArrayOfResultSet = new ResultSet[0];
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return bFlag;
	  }
	
	public	boolean deleteSpecificationSetting(Connection con,Material material) throws SQLException,Exception{
	
	PreparedStatement ArrayOfPreparedStatement[] = null;
    ResultSet ArrayOfResultSet[] = null;
    PreparedStatement pstmtdeleteSpecificationSetting=null;
    boolean bFlag = false;
    try{     
    	
    	pstmtdeleteSpecificationSetting=con.prepareStatement(SQLUtil.DELETE_SPECIFICATION_SETTING);
		
    	pstmtdeleteSpecificationSetting.setInt(1,material.getGroupType().getGroupTypeId());
    	pstmtdeleteSpecificationSetting.setString(2,material.getSpec1());
    	pstmtdeleteSpecificationSetting.setInt(3,material.getSpecLen1());
    	pstmtdeleteSpecificationSetting.setString(4,material.getSpec2());
    	pstmtdeleteSpecificationSetting.setInt(5,material.getSpecLen2());
    	pstmtdeleteSpecificationSetting.setString(6,material.getSpec3());
    	pstmtdeleteSpecificationSetting.setInt(7,material.getSpecLen3());
    	pstmtdeleteSpecificationSetting.setString(8,material.getSpec4());
    	pstmtdeleteSpecificationSetting.setInt(9,material.getSpecLen4());
    	pstmtdeleteSpecificationSetting.setString(10,material.getSpec5());
    	pstmtdeleteSpecificationSetting.setInt(11,material.getSpecLen5());
    	pstmtdeleteSpecificationSetting.setString(12,material.getSpec6());
    	pstmtdeleteSpecificationSetting.setInt(13,material.getSpecLen6());
    	pstmtdeleteSpecificationSetting.setString(14,material.getSpec7());
    	pstmtdeleteSpecificationSetting.setInt(15,material.getSpecLen7());

            int iCount = pstmtdeleteSpecificationSetting.executeUpdate(); 
        bFlag = (iCount>0)?true:false;     
    	}finally{ 
      ArrayOfPreparedStatement = new PreparedStatement[1];
      ArrayOfPreparedStatement[0] = pstmtdeleteSpecificationSetting;
      ArrayOfResultSet = new ResultSet[0];
      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
    }
    return bFlag;
  }

	
	public boolean updateSpecificationSetting(Connection con,Material mat ,Material material,TPCSUser userinfo )throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtupdateSpecificationSetting=null;  


		boolean bFlag = false;    

		try{ 
			pstmtupdateSpecificationSetting = con.prepareStatement(SQLUtil.UPDATE_SPECMASTER_SETTING);

			pstmtupdateSpecificationSetting.setString(1,mat.getSpec1());
			pstmtupdateSpecificationSetting.setInt(2,mat.getSpecLen1());
			pstmtupdateSpecificationSetting.setString(3,mat.getSpec2());
			pstmtupdateSpecificationSetting.setInt(4,mat.getSpecLen2());
			pstmtupdateSpecificationSetting.setString(5,mat.getSpec3());
	    	pstmtupdateSpecificationSetting.setInt(6,mat.getSpecLen3());
	    	pstmtupdateSpecificationSetting.setString(7,mat.getSpec4());
	    	pstmtupdateSpecificationSetting.setInt(8,mat.getSpecLen4());
	    	pstmtupdateSpecificationSetting.setString(9,mat.getSpec5());
	    	pstmtupdateSpecificationSetting.setInt(10,mat.getSpecLen5());
	    	pstmtupdateSpecificationSetting.setString(11,mat.getSpec6());
	    	pstmtupdateSpecificationSetting.setInt(12,mat.getSpecLen6());
	    	pstmtupdateSpecificationSetting.setString(13,mat.getSpec7());
	    	pstmtupdateSpecificationSetting.setInt(14,mat.getSpecLen7());
	    	pstmtupdateSpecificationSetting.setInt(15,userinfo.getUserId());
	    	pstmtupdateSpecificationSetting.setInt(16,material.getGroupType().getGroupTypeId());
	    	System.out.println("material.getGroupType().getGroupTypeId()="+material.getGroupType().getGroupTypeId());
	    	pstmtupdateSpecificationSetting.setString(17,material.getSpec1());
	    	pstmtupdateSpecificationSetting.setInt(18,material.getSpecLen1());
	    	pstmtupdateSpecificationSetting.setString(19,material.getSpec2());
	    	pstmtupdateSpecificationSetting.setInt(20,material.getSpecLen2());
	    	pstmtupdateSpecificationSetting.setString(21,material.getSpec3());
	    	pstmtupdateSpecificationSetting.setInt(22,material.getSpecLen3());
	    	pstmtupdateSpecificationSetting.setString(23,material.getSpec4());
	    	pstmtupdateSpecificationSetting.setInt(24,material.getSpecLen4());
	    	pstmtupdateSpecificationSetting.setString(25,material.getSpec5());
	    	pstmtupdateSpecificationSetting.setInt(26,material.getSpecLen5());
	    	pstmtupdateSpecificationSetting.setString(27,material.getSpec6());
	    	pstmtupdateSpecificationSetting.setInt(28,material.getSpecLen6());
	    	pstmtupdateSpecificationSetting.setString(29,material.getSpec7());
	    	pstmtupdateSpecificationSetting.setInt(30,material.getSpecLen7());
	    	
	    	int iCount = pstmtupdateSpecificationSetting.executeUpdate();

	    	bFlag=(iCount>0)?true:false;
		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtupdateSpecificationSetting;
			ArrayOfResultSet = new ResultSet[0];
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return bFlag;
		}
	 
 	 	

public Material getSpecsettingInfo(Connection con,Material material)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtgetSpecSettingInfo=null;    
	    ResultSet rsGetSpecSettingInfo=null;
	    Material objMaterial = null;

	    
	    try{
	    	pstmtgetSpecSettingInfo = con.prepareStatement(SQLUtil.SPECSETTING_INFO);
	    

	    	pstmtgetSpecSettingInfo.setInt(1,material.getGroupType().getGroupTypeId());    	//pstmtgetSpecMasterInfo.setString(2,specification.getSpecification());
	    	pstmtgetSpecSettingInfo.setString(2,material.getSpec1());     
	    	pstmtgetSpecSettingInfo.setInt(3,material.getSpecLen1());     
	    	pstmtgetSpecSettingInfo.setString(4,material.getSpec2());     
	    	pstmtgetSpecSettingInfo.setInt(5,material.getSpecLen2());     
	    	pstmtgetSpecSettingInfo.setString(6,material.getSpec3());     
	    	pstmtgetSpecSettingInfo.setInt(7,material.getSpecLen3());     
	    	pstmtgetSpecSettingInfo.setString(8,material.getSpec4());     
	    	pstmtgetSpecSettingInfo.setInt(9,material.getSpecLen4());     
	    	pstmtgetSpecSettingInfo.setString(10,material.getSpec5());     
	    	pstmtgetSpecSettingInfo.setInt(11,material.getSpecLen5());     
	    	pstmtgetSpecSettingInfo.setString(12,material.getSpec6());     
	    	pstmtgetSpecSettingInfo.setInt(13,material.getSpecLen6());     
	    	pstmtgetSpecSettingInfo.setString(14,material.getSpec7());     
	    	pstmtgetSpecSettingInfo.setInt(15,material.getSpecLen7());     


	    	rsGetSpecSettingInfo = pstmtgetSpecSettingInfo.executeQuery();
	      while(rsGetSpecSettingInfo.next()){
	    	  

	    	  GroupType  objGroup=new GroupType();
	    	  objGroup.setGroupTypeId(rsGetSpecSettingInfo.getInt(1));
	    	  objGroup.setGroupType(rsGetSpecSettingInfo.getString(2));
	    	  
	    	  objMaterial = new  Material();
	    	  objMaterial.setSpec1(rsGetSpecSettingInfo.getString(3));
	    	  objMaterial.setSpecLen1(rsGetSpecSettingInfo.getInt(4));

	    	  objMaterial.setSpec2(rsGetSpecSettingInfo.getString(5));
	    	  objMaterial.setSpecLen2(rsGetSpecSettingInfo.getInt(6));

	    	  objMaterial.setSpec3(rsGetSpecSettingInfo.getString(7));
	    	  objMaterial.setSpecLen3(rsGetSpecSettingInfo.getInt(8));

	    	  objMaterial.setSpec4(rsGetSpecSettingInfo.getString(9));
	    	  objMaterial.setSpecLen4(rsGetSpecSettingInfo.getInt(10));

	    	  objMaterial.setSpec5(rsGetSpecSettingInfo.getString(11));
	    	  objMaterial.setSpecLen5(rsGetSpecSettingInfo.getInt(12));

	    	  objMaterial.setSpec6(rsGetSpecSettingInfo.getString(13));
	    	  objMaterial.setSpecLen6(rsGetSpecSettingInfo.getInt(14));

	    	  objMaterial.setSpec7(rsGetSpecSettingInfo.getString(15));
	    	  objMaterial.setSpecLen7(rsGetSpecSettingInfo.getInt(16));

	    	 objMaterial.setGroupType(objGroup);
	    	 objGroup=null;

	    	 		    	  
	    	  }
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtgetSpecSettingInfo;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] = rsGetSpecSettingInfo;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    
	    return objMaterial;
	  }
	
		

public String getSpecSettingForPrint(Connection con) throws SQLException {
	// TODO Auto-generated method stub
	List<Material> specification=new ArrayList<Material>();
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	StringBuffer strBuffer=new StringBuffer();
	RawMaterial objRawMaterial=null;
	try{
	   StringBuffer query = new StringBuffer(SQLUtil.SELECT_SPECIFICATION_SETTING);
	   pstmt=con.prepareCall(query.toString());
	   rs=pstmt.executeQuery();
	 	while(rs.next()){
    		GroupType objGroupType=new GroupType();	    		
    		objGroupType.setGroupTypeId(rs.getInt(1));
    		objGroupType.setGroupType(rs.getString(2));
    		
    		Material objMaterial=new Material();
    		objMaterial.setSpec1(rs.getString(3));
    		objMaterial.setSpecLen1(rs.getInt(4));
    		objMaterial.setSpec2(rs.getString(5));
    		objMaterial.setSpecLen2(rs.getInt(6));
    		objMaterial.setSpec3(rs.getString(7));
    		objMaterial.setSpecLen3(rs.getInt(8));
    		objMaterial.setSpec4(rs.getString(9));
    		objMaterial.setSpecLen4(rs.getInt(10));
    		objMaterial.setSpec5(rs.getString(11));
    		objMaterial.setSpecLen5(rs.getInt(12));
    		objMaterial.setSpec6(rs.getString(13));
    		objMaterial.setSpecLen6(rs.getInt(14));
    		objMaterial.setSpec7(rs.getString(15));
    		objMaterial.setSpecLen7(rs.getInt(16));
    		objMaterial.setGroupType(objGroupType);
    		
    		objMaterial.setGroupType(objGroupType);
    		specification.add(objMaterial);
    		objGroupType=null;
    		objMaterial=null;
    		
    	}	    	
	   strBuffer.append("<table class=\"rowline\" cellspacing=\"1\" cellpadding=\"4\" width=\"100%\" style=\"border-top:4px solid #193678;border-right:1px solid #193678;border-bottom:1px solid #193678;\" >");                                
	   strBuffer.append("<tr>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Group Type</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 1</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 1 Length</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 2</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 2 Length</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 3</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 3 Length</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 4</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 4 Length</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 5</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 5 Length</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 6</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 6 Length</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 7</th>");
    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification 7 Length</th>");
    	  strBuffer.append("</tr>");
    	  
    	  
    	  for(int i=0;i<specification.size();i++)
		    {
		       strBuffer.append("<tr>");  
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getGroupType().getGroupType()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpec1()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecLen1()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpec2()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecLen2()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpec3()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecLen3()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpec4()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecLen4()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpec5()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecLen5()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpec6()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecLen6()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpec7()+"</td>");
			   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecLen7()+"</td>");
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
