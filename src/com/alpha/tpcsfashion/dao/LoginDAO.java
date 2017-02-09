package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alpha.tpcsfashion.beans.Entity;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.User;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;


public class LoginDAO
{
	
	public TPCSUser validateCredentials(Connection con,TPCSUser user)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtCheckLogin=null;
	    ResultSet rsCheckLogin = null;
	    TPCSUser objLocalUser=new TPCSUser();
	    try{ 
	      pstmtCheckLogin = con.prepareStatement(SQLUtil.VALIDATE_LOGIN);
	      pstmtCheckLogin.setString(1, user.getUserName());
	      pstmtCheckLogin.setString(2, user.getPassword());
	      rsCheckLogin = pstmtCheckLogin.executeQuery();
	      objLocalUser.setUserValid(false);
	      while(rsCheckLogin.next()){
	    	objLocalUser.setUserValid(true);
	        objLocalUser.setDisplayUserName(rsCheckLogin.getString(1)+" "+(rsCheckLogin.getString(2)!=null?rsCheckLogin.getString(2):""));
	        objLocalUser.setUserId(rsCheckLogin.getInt(3));
	        objLocalUser.setDatasource(rsCheckLogin.getString(4));
	        objLocalUser.setDatabase(rsCheckLogin.getString(5));
	        objLocalUser.setServerip(rsCheckLogin.getString(6));
	        objLocalUser.setSqlusername(rsCheckLogin.getString(7));
	        objLocalUser.setSqlpassword(rsCheckLogin.getString(8));
	        objLocalUser.setclientId(rsCheckLogin.getInt(9));
	        objLocalUser.setUserLocale(rsCheckLogin.getString(11));
	        objLocalUser.setPasswordExpired(isPasswordExpired(con,rsCheckLogin.getInt(9),rsCheckLogin.getInt(3)));
	        objLocalUser.setCompanyName(rsCheckLogin.getString(10));
	        objLocalUser.setClientName(rsCheckLogin.getString(10));
	        
	        
	        System.out.println("rsCheckLogin.getString(12) :"+rsCheckLogin.getString(12));
	        objLocalUser.setPropertyFileName(rsCheckLogin.getString(12));
	      
	      }
	      
	      return objLocalUser;
	    }finally{
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfPreparedStatement[0]=pstmtCheckLogin;   
	      ArrayOfResultSet[0] = rsCheckLogin;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	  }
	public TPCSUser setLocationConfig(Connection con,TPCSUser user) throws SQLException{
		PreparedStatement pst=null;
	    ResultSet rs= null;
		try{
			
			System.out.println("user.getLocationId() :"+user.getLocationId());
			
			Map<String,String> locationConfigMap=new LinkedHashMap<String,String>();
			pst= con.prepareStatement(" select config_code,config_option from Set_Location_config where location_id=?  ");
			pst.setInt(1, user.getLocationId());
			rs=pst.executeQuery();
			while(rs.next()){
				locationConfigMap.put(rs.getString(1), rs.getString(2));
			}
			user.setLocatonConfigMap(locationConfigMap);
		}finally{
			
		}
		return user;		
	}
	public TPCSUser getUserCompanyAndYearDet(Connection con,TPCSUser user) throws SQLException{
		PreparedStatement pst=null;
		ResultSet rs= null;
		try{

			pst= con.prepareStatement(SQLUtil.GET_USER_COMPANY_YEAR);
			pst.setInt(1, user.getUserId());
			rs= pst.executeQuery();
			if(rs.next()){
				user.setCompanyId(rs.getInt(1));
				user.setYearId(rs.getInt(2));
			}

			/*  System.out.println(" User Company Id :"+user.getCompanyId());
		      System.out.println(" User Year Id :"+user.getYearId());*/

			if(user.getCompanyId()>0){
				
				pst= con.prepareStatement(SQLUtil.GET_COMPANY);   
				pst.setInt(1, user.getCompanyId());
				pst.setInt(2, user.getclientId());
				
				rs= pst.executeQuery();
				if(rs.next()){
					
					user.setCompanyId(rs.getInt(1));  
					user.setCompanyName(rs.getString(2));
					user.setAddress1(rs.getString(3));
					user.setAddress2(rs.getString(4));
					user.setAddress3(rs.getString(5));
					user.setCountry(rs.getString(6));
					user.setPhoneNo(rs.getString(7));
					user.setFaxNo(rs.getString(8));
					user.setTaxRegNo1(rs.getString(9));
					user.setTaxRegNo2(rs.getString(10));
					user.setEmailId(rs.getString(11));
					user.setLocationId(rs.getInt(12));
					user.setCurrencyId(rs.getInt(13));
				}
				setDefaultCurrencyData(con, user);

			}
			if(user.getYearId()>0){
				
				pst=con.prepareStatement(SQLUtil.SELECT_USER_YEAR);
				pst.setInt(1, user.getYearId());
				rs= pst.executeQuery();
				
				if(rs.next()){
					user.setStartDate(rs.getString(2));
					user.setEndDate(rs.getString(3));
				}
			}
			return user;			
		}
		finally{
			DatabaseConnection.closeAll(pst,rs);
		}
	}
	 public boolean isPasswordExpired(Connection con,int iClientId,int iUserId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csIsPasswordExixts=null;    
		    //EntityRights retVal = new EntityRights();
		    boolean bFlag = false;    
		    try{     
		    	csIsPasswordExixts = con.prepareCall("{? = call pr_ispasswordexpired(?,?)}");
		    	csIsPasswordExixts.registerOutParameter(1, Types.INTEGER);
		    	csIsPasswordExixts.setInt(2, iClientId);  
		    	csIsPasswordExixts.setInt(3, iUserId);      
		    	csIsPasswordExixts.executeUpdate();
		    	int iCount = csIsPasswordExixts.getInt(1);
		    	bFlag=(iCount!=0)?true:false;
		    	//retVal.setSuccess(bFlag);
		     
		    }finally{     
		      if(csIsPasswordExixts!=null)
		    	  csIsPasswordExixts.close();
		      ArrayOfPreparedStatement = new PreparedStatement[0];      
		      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return bFlag;
		  }
	 
	 
	 public void updateLoginTime(Connection con,int iclientId,int iUserId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtUpdateLoginTime=null;    
		    PreparedStatement pstmtUpdateLoginInfo=null;    
		       
		    try{ 
		    	pstmtUpdateLoginTime = con.prepareStatement(SQLUtil.UPDATE_LOGIN_TIME);
		    	pstmtUpdateLoginTime.setInt(1,iclientId);      
		    	pstmtUpdateLoginTime.setInt(2,iUserId);
		    	pstmtUpdateLoginTime.executeUpdate();
		    	pstmtUpdateLoginInfo = con.prepareStatement(SQLUtil.INSERT_LOGIN_INFO);
		    	pstmtUpdateLoginInfo.setInt(1,iUserId);
		    	pstmtUpdateLoginInfo.executeUpdate();
		   
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[2];
		      ArrayOfPreparedStatement[0] = pstmtUpdateLoginTime;
		      ArrayOfPreparedStatement[1] = pstmtUpdateLoginInfo;
		      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    
		  }
	 
	 
	 
	 
	
	 
	 
	 
	 public void updateLogoutTime(Connection con,int iclientId,int iUserId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtUpdateLogoutTime=null;   
		    PreparedStatement pstmtUpdateLogoutInfo=null;   
		       
		    try{ 
		    	pstmtUpdateLogoutTime = con.prepareStatement(SQLUtil.UPDATE_LOGOUT_TIME);
		    	pstmtUpdateLogoutTime.setInt(1,iclientId);      
		    	pstmtUpdateLogoutTime.setInt(2,iUserId);
		    	pstmtUpdateLogoutTime.executeUpdate();
		    	pstmtUpdateLogoutInfo = con.prepareStatement(SQLUtil.INSERT_LOGOUT_INFO);
		    	pstmtUpdateLogoutInfo.setInt(1,iUserId);
		    	pstmtUpdateLogoutInfo.executeUpdate();
		   
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[2];
		      ArrayOfPreparedStatement[0] = pstmtUpdateLogoutTime;
		      ArrayOfPreparedStatement[1] = pstmtUpdateLogoutInfo;
		      ArrayOfResultSet = new ResultSet[0];
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    
		  }
	 
	 public void updateUserCompanyAndYear(Connection con,TPCSUser userInfo)throws SQLException,Exception{
		 PreparedStatement pst=null;
		 ResultSet rs=null;
		 try {
			 pst= con.prepareStatement(SQLUtil.UPDATE_USER_COMPANY_YEAR);
			 pst.setInt(1, userInfo.getCompanyId());
			 pst.setInt(2, userInfo.getYearId());
			 pst.setInt(3, userInfo.getUserId());
			 pst.executeUpdate();
			 
		} finally{
			 DatabaseConnection.closeAll(pst,rs);
		}
	 }
	 
	 public void setDefaultCurrencyData(Connection con,TPCSUser userInfo) throws SQLException{
		 PreparedStatement pst=null;
		 ResultSet rs=null;
		 try{
			 pst= con.prepareStatement(SQLUtil.GET_CURRENCY_DATA);
			 pst.setInt(1, userInfo.getCurrencyId());
//			 pst.setInt(2, userInfo.getCompanyId());
//			 pst.setInt(2, userInfo.getLocationId());
			 rs=pst.executeQuery();
			 if(rs.next()){
				 userInfo.setCurrencyName(rs.getString(2));
				 userInfo.setCurrencySymbol(rs.getString(3));
			 }
		 }
		 finally{
			 DatabaseConnection.closeAll(pst,rs);
		 }
	 }
	 
	 public Entity getEntityDetails(Connection con,int iUserId)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;	    
		    PreparedStatement pstmtGetEntityDetails=null;
		    ResultSet rsGetEntityDetails = null; 
		    Entity objEntity= new Entity();	     
		    
		   
		    try{     
		 		
		    	pstmtGetEntityDetails = con.prepareStatement(SQLUtil.GET_ENTITY_DETAILS);
		    	pstmtGetEntityDetails.setInt(1,iUserId);   
		    	rsGetEntityDetails = pstmtGetEntityDetails.executeQuery();
		         while(rsGetEntityDetails.next()){
		        	 objEntity.setEntityHOId(rsGetEntityDetails.getInt(1));
		        	 objEntity.setEntityCustomerId(rsGetEntityDetails.getInt(2));
		        	 objEntity.setEntityFactoryId(rsGetEntityDetails.getInt(3));
//		        	 objEntity.setFactoryName(rsGetEntityDetails.getString(4));
//		        	 objEntity.setCustomerName(rsGetEntityDetails.getString(5));
		        	
		         }
		    }finally{     
		     
		      ArrayOfPreparedStatement = new PreparedStatement[1];   
		      ArrayOfPreparedStatement[0]=pstmtGetEntityDetails;	     
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0]=rsGetEntityDetails;
		    
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return objEntity;
		  }
	 public User passwordreset(Connection con,String strLoginName)throws SQLException,Exception{
		  
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    CallableStatement csInsertEntityRights=null;    
		    PreparedStatement pstmtGetUserName=null;
		    ResultSet rsGetUserName = null; 
		    PreparedStatement pstmtGetPasswordDetails=null;    
		    ResultSet rsGetPasswordDetails = null;
		    boolean bFlag = false;    
		    User retVal = new User();
		    try{     
		     System.out.println("strLoginName :"+strLoginName);
		    	csInsertEntityRights = con.prepareCall("{? = call pr_passwordreset(?)}");
		    	csInsertEntityRights.registerOutParameter(1, Types.INTEGER);
		    	csInsertEntityRights.setString(2, strLoginName);      
		    	      		      
		    	csInsertEntityRights.executeUpdate();
		    	
		      int iCount = csInsertEntityRights.getInt(1);
		      
		      bFlag=(iCount!=0)?true:false;
		      retVal.setSuccess(bFlag);
		      
		        pstmtGetUserName = con.prepareStatement(SQLUtil.GET_CLIENT_EMAIL);
		         pstmtGetUserName.setString(1, strLoginName);   
		         rsGetUserName = pstmtGetUserName.executeQuery();
		         while(rsGetUserName.next()){	       	  
		        retVal.setClientUserName(rsGetUserName.getString(1));
		      	retVal.setUserId(rsGetUserName.getInt(2));
		         }
		         
		     	pstmtGetPasswordDetails=con.prepareStatement(SQLUtil.GET_PASSWORD);
		    	pstmtGetPasswordDetails.setString(1, strLoginName);
		    
		    	rsGetPasswordDetails=pstmtGetPasswordDetails.executeQuery();
		    	
		    	while(rsGetPasswordDetails.next()){	
		    	
		    		retVal.setUserName(strLoginName);
		    		retVal.setPassword(rsGetPasswordDetails.getString(2));
		    	}
		     
		      
		     
		    }finally{     
		      if(csInsertEntityRights!=null)
		    	  csInsertEntityRights.close();
		      ArrayOfPreparedStatement = new PreparedStatement[2];  
		      ArrayOfPreparedStatement[0]=pstmtGetUserName;
		      ArrayOfPreparedStatement[1]=pstmtGetPasswordDetails;
		      ArrayOfResultSet = new ResultSet[2];
		      ArrayOfResultSet[0]=rsGetPasswordDetails;
		      ArrayOfResultSet[1]=rsGetUserName;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return retVal;
		  }
	 public TPCSUser getUserId(Connection con,TPCSUser uInfo) {
			
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
					pstmt=con.prepareStatement("select user_id,client_id,user_login_name from users_info where user_login_name=?");
					pstmt.setString(1, uInfo.getUserName());
					rs=pstmt.executeQuery();
					
					if(rs.next()){
						uInfo.setUserId(rs.getInt(1));
						uInfo.setclientId(rs.getInt(2));
						uInfo.setUserName(rs.getString(3));
					}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{     
			     
			      DatabaseConnection.closeAll(pstmt,rs);
			    }
			return uInfo;
		}
}
