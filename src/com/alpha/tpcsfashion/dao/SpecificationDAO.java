package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.Address;
import com.alpha.tpcsfashion.beans.ContactPerson;
import com.alpha.tpcsfashion.beans.GroupType;
import com.alpha.tpcsfashion.beans.Material;
import com.alpha.tpcsfashion.beans.Order;
import com.alpha.tpcsfashion.beans.Party;
import com.alpha.tpcsfashion.beans.RawMaterial;
import com.alpha.tpcsfashion.beans.Spec;
import com.alpha.tpcsfashion.beans.UnitMaster;
import com.alpha.tpcsfashion.beans.Warehouse;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;

public class SpecificationDAO {

	public List<Spec> getSpecification(Connection con,int iStart,int iEnd)throws SQLException,Exception {
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    CallableStatement cstmtGetSpecification=null;
	    PreparedStatement psmtGetSpecification=null;
	    ResultSet rsGetSpecification = null;
	    List<Spec> specification=new ArrayList<Spec>();
	    try{	    	
	    	cstmtGetSpecification=con.prepareCall("{call sp_Paging(?,?,?)}");
	    	cstmtGetSpecification.setString(1,SQLUtil.SELECT_SPECIFICATION);
	    	cstmtGetSpecification.setInt(2,iStart);
	    	cstmtGetSpecification.setInt(3,iEnd);
	    	rsGetSpecification=cstmtGetSpecification.executeQuery();
	    	
	    	while(rsGetSpecification.next()){
	    		GroupType objGroupType=new GroupType();	    		
	    		objGroupType.setGroupTypeId(rsGetSpecification.getInt(2));
	    		objGroupType.setGroupType(rsGetSpecification.getString(3));
	    		
	    		Spec objSpec=new Spec();
	    		objSpec.setSpecification(rsGetSpecification.getString(4));
	    		objSpec.setSpecValue(rsGetSpecification.getString(5));
	    		objSpec.setSpecCode(rsGetSpecification.getString(6));
	    	
	    		objSpec.setGroupType(objGroupType);
	    		specification.add(objSpec);
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
	
	public int getSpecificationsCount(Connection con,int iPageSize)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtSpecificationCount=null;
	    ResultSet rsSpecificationCount = null;
	    int iCount = 0;
	    try{ 
	    	pstmtSpecificationCount = con.prepareStatement(SQLUtil.SPECIFICATION_COUNT);      
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
	
	public boolean insertSpecification(Connection con,Spec specification,int iLoginUserId,int iCompanyId,int iLocationId)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    PreparedStatement psInsertSpecification=null;
	    ResultSet ArrayOfResultSet[] = null; 
	    boolean bFlag = false;    
	    try{
	    	psInsertSpecification=con.prepareStatement(SQLUtil.INSERT_SPECIFICATION);
	    	psInsertSpecification.setInt(1,specification.getGroupType().getGroupTypeId());
	    	psInsertSpecification.setString(2,specification.getSpecification());
	    	psInsertSpecification.setString(3,specification.getSpecValue());
	    	psInsertSpecification.setString(4,specification.getSpecCode());
	    	psInsertSpecification.setInt(5,iLoginUserId);
	    	psInsertSpecification.setInt(6,iCompanyId);
	    	psInsertSpecification.setInt(7,iLocationId);
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
	
	
	
	public boolean isSpecificationExist(Connection con,Spec objSpec)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtIsCustomerExist=null;    
	    ResultSet rsIsCustomerExist = null;    
	    boolean bFlag = false;
	    try{
	    	pstmtIsCustomerExist = con.prepareStatement(SQLUtil.IS_SPECIFICATION_EXIST);    
	    	pstmtIsCustomerExist.setString(1,objSpec.getSpecValue());
	    	pstmtIsCustomerExist.setString(2,objSpec.getSpecCode());
	    rsIsCustomerExist = pstmtIsCustomerExist.executeQuery();
	      while(rsIsCustomerExist.next()){
	      bFlag = (rsIsCustomerExist.getInt(1)>0)?true:false;      
	      }
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtIsCustomerExist;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] = rsIsCustomerExist;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return bFlag;
	  }	
	
	
	
		public	boolean deleteMasterSpecification(Connection con,Spec specification ,String strSpecification,String strSpecificationValue,String strSpecificationCode) throws SQLException,Exception{
		
		PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtdeleteMasterSpecification=null;
	    boolean bFlag = false;
	    try{     
	    	
	    	pstmtdeleteMasterSpecification=con.prepareStatement(SQLUtil.DELETE_SPECIFICATION);
    		
	    	pstmtdeleteMasterSpecification.setInt(1,specification.getGroupType().getGroupTypeId());
            pstmtdeleteMasterSpecification.setString(2, strSpecification);     
	    	pstmtdeleteMasterSpecification.setString(3, strSpecificationValue);     
	    	pstmtdeleteMasterSpecification.setString(4, strSpecificationCode);     

	    	
	    															
	        int iCount = pstmtdeleteMasterSpecification.executeUpdate(); 
	        bFlag = (iCount>0)?true:false;     
	    
		
	
		}finally{ 
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtdeleteMasterSpecification;
	      ArrayOfResultSet = new ResultSet[0];
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return bFlag;
	  }
	
		
			 
		public boolean updateSpecification(Connection con,Spec specification)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtupdateSpecification=null;  
		    boolean bFlag = false;    
		    try{ 
		    	pstmtupdateSpecification = con.prepareStatement(SQLUtil.UPDATE_SPECMASTER);
		    	pstmtupdateSpecification.setString(1,specification.getSpecValue());
		    	pstmtupdateSpecification.setString(2,specification.getSpecCode());
		    	pstmtupdateSpecification.setInt(3,specification.getGroupType().getGroupTypeId());
		    	pstmtupdateSpecification.setString(4,specification.getSpecification());
		    	int iCount = pstmtupdateSpecification.executeUpdate();
			    	bFlag=(iCount>0)?true:false;
		    }finally{
			      ArrayOfPreparedStatement = new PreparedStatement[1];
	             ArrayOfPreparedStatement[0] = pstmtupdateSpecification;
				      ArrayOfResultSet = new ResultSet[0];
				      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
				    }
				    return bFlag;
				  }
		 
	 	  
	

	public Spec getSpecInfo(Connection con,Spec specification,String strSpecification,String strSpecificationValue,String strSpecificationCode)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtgetSpecMasterInfo=null;    
		    ResultSet rsGetSpecMasterInfo=null;
		    Spec objSpecification = null;
		    
		    
		    try{
		    	pstmtgetSpecMasterInfo = con.prepareStatement(SQLUtil.GET_SPECMASTER_INFO);
		    
                pstmtgetSpecMasterInfo.setInt(1,specification.getGroupType().getGroupTypeId());
		    	pstmtgetSpecMasterInfo.setString(2, strSpecification);     
		    	pstmtgetSpecMasterInfo.setString(3, strSpecificationValue);     
		    	pstmtgetSpecMasterInfo.setString(4, strSpecificationCode);     


		    	rsGetSpecMasterInfo = pstmtgetSpecMasterInfo.executeQuery();
		      while(rsGetSpecMasterInfo.next()){
		    	  

		    	  GroupType  objGroup=new GroupType();
		    	  objGroup.setGroupTypeId(rsGetSpecMasterInfo.getInt(1));
		    	  objGroup.setGroupType(rsGetSpecMasterInfo.getString(2));
		    	  
		    	  objSpecification = new Spec();
		    	  objSpecification.setSpecification(rsGetSpecMasterInfo.getString(3));
		    	  objSpecification.setSpecValue(rsGetSpecMasterInfo.getString(4));
		    	  objSpecification.setSpecCode(rsGetSpecMasterInfo.getString(5));
		    	  objSpecification.setGroupType(objGroup);

		    	 		    	  
		    	  }
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtgetSpecMasterInfo;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsGetSpecMasterInfo;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    
		    return objSpecification;
		  }
		
	public String getSpecificationForPrint(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		 List<Spec> specification=new ArrayList<Spec>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer strBuffer=new StringBuffer();
		RawMaterial objRawMaterial=null;
		try{
		   StringBuffer query = new StringBuffer(SQLUtil.SELECT_SPECIFICATION);
		   pstmt=con.prepareCall(query.toString());
		   rs=pstmt.executeQuery();
	    	
	    	while(rs.next()){
	    		GroupType objGroupType=new GroupType();	    		
	    		objGroupType.setGroupTypeId(rs.getInt(1));
	    		objGroupType.setGroupType(rs.getString(2));
	    		Spec objSpec=new Spec();
	    		objSpec.setSpecification(rs.getString(3));
	    		objSpec.setSpecValue(rs.getString(4));
	    		objSpec.setSpecCode(rs.getString(5));
	    		objSpec.setGroupType(objGroupType);
	    		specification.add(objSpec);
	    	}	    	
		   strBuffer.append("<table class=\"rowline\" cellspacing=\"1\" cellpadding=\"4\" width=\"100%\" style=\"border-top:4px solid #193678;border-right:1px solid #193678;border-bottom:1px solid #193678;\" >");                                
		   strBuffer.append("<tr>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Group Type</th>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification</th>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification Value</th>");
	    	  strBuffer.append("<th nowrap=\"nowrap\"  class=\"subpageheader\">Specification Code</th>");

	    	  strBuffer.append("</tr>");
	    	  
	    	  
	    	  for(int i=0;i<specification.size();i++)
			    {
			       strBuffer.append("<tr>");  
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getGroupType().getGroupType()+"</td>");
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecification()+"</td>");
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecValue()+"</td>");
				   strBuffer.append("<td nowrap=\"nowrap\" class=\"dataalignment\">"+specification.get(i).getSpecCode()+"</td>");
			       strBuffer.append("</tr>");
			    }
	    	     strBuffer.append("</table>");
		   
		}
		finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return strBuffer.toString();
	}

	public boolean isSpecificationExistForUpdate(Connection con,Spec objSpec)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtIsCustomerExist=null;    
	    ResultSet rsIsCustomerExist = null;    
	    boolean bFlag = false;
	    try{
	    	pstmtIsCustomerExist = con.prepareStatement("SELECT  count(*) from spec_master where spec_value=? and spec_code=?");    
	    	pstmtIsCustomerExist.setString(1,objSpec.getSpecValue());
	    	pstmtIsCustomerExist.setString(2,objSpec.getSpecCode());
	    rsIsCustomerExist = pstmtIsCustomerExist.executeQuery();
	      while(rsIsCustomerExist.next()){
	      bFlag = (rsIsCustomerExist.getInt(1)>1)?true:false;      
	      }
	    }finally{     
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtIsCustomerExist;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] = rsIsCustomerExist;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return bFlag;
	  }	
	
	
	
	
}
