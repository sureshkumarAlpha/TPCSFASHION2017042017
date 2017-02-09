package com.alpha.tpcsfashion.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.Currency;
import com.alpha.tpcsfashion.beans.Customer;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.Group;
import com.alpha.tpcsfashion.beans.GroupMaster;
import com.alpha.tpcsfashion.beans.MaterialMaster;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class CustomerDAO {

	
	
	
	public int getPageCount(Connection con, TPCSUser ui, String searchCriteria) throws SQLException {
		// TODO Auto-generated method stub
		int pageCount=0;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer query=new StringBuffer(SQLUtil.GET_CUSTOMERS_COUNT);
			
			if(searchCriteria!=null && !searchCriteria.isEmpty())
			{
			 query.append("WHERE " +searchCriteria); 	
			}
		//System.out.println("query in count:"+query);
			cstmt=con.prepareCall(query.toString());
			
			rs = cstmt.executeQuery();
			while (rs.next()) {
				pageCount=rs.getInt(1);
			}
		}
		finally
		{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return pageCount;
	}
	
	
	public Currency saveCurrency(Connection con, Currency objC,TPCSUser ui) throws SQLException {

		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean bFlag=false;
		try {
			pstmt= con.prepareStatement(SQLUtil.IS_CURRENCY_EXIST);
			pstmt.setString(1, objC.getCurrencyName());
			rs= pstmt.executeQuery();
			while(rs.next()){
				bFlag = (rs.getInt(1)>0)?true:false;
			}
			objC.setCurrencyExists(bFlag);

			if(!objC.isCurrencyExists()){
				pstmt=con.prepareStatement(SQLUtil.INSERT_INTO_CURRENCY);	
				pstmt.setInt(1,ui.getCompanyId());
				pstmt.setInt(2,ui.getLocationId());
				pstmt.setString(3, objC.getCurrencyName());
				int iCount = pstmt.executeUpdate();
				bFlag=(iCount>0)?true:false;
				objC.setInserted(bFlag);
			}
		}
			finally{
				ArrayOfPreparedStatement = new PreparedStatement[1];
				ArrayOfPreparedStatement[0] = pstmt;
				ArrayOfResultSet = new ResultSet[1];      
				ArrayOfResultSet[0] = rs;
				DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
			}
			return objC;
		}
	public List<Map<String,String>> getAllCustomerOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,String strSearchCriteria,List<String> orderBy)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
		CallableStatement cstmt=null;
	    ResultSet rs = null; 
	    ResultSet rsAttach = null; 
	    PreparedStatement pstmt=null;
	    List<Map<String,String>> custList=new ArrayList<Map<String,String>>();
	    String strbuffer="";
	    try{
	    	
	    	StringBuffer query = new StringBuffer(SQLUtil.GET_ALL_CUSTOMERS);
	    	if(strSearchCriteria!=null && !strSearchCriteria.isEmpty())
			{
	        	 query.append("WHERE "+strSearchCriteria);
	         }
	    	//System.out.println("orderBy--DAO-------->"+orderBy);
	    	String order=" ORDER BY ";
	         if(orderBy!=null && !orderBy.isEmpty())
		      {
	        	 
		    	  for(String strOrderBy:orderBy){
		    		 if(strOrderBy!=null)
		    		 {
		    			 strbuffer+=strOrderBy+",";  
		    		 }
		    		
			      }
		    	  if(strbuffer.length()>0){
		    			 strbuffer=strbuffer.substring(0, strbuffer.length() - 1);
		    			 query.append(order+strbuffer);
		    		 }
		      }
	          //System.out.println("CUST  query "+query.toString());
	         cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
	         cstmt.setString(1,query.toString());
	         cstmt.setInt(2,pc.iStart);
	         cstmt.setInt(3,pc.iEnd);
	         rs=cstmt.executeQuery();
	        while(rs.next()){  
	    	  Map<String,String> map = new HashMap<String,String>();
	    	
	    	  map.put("Customer.partyId", rs.getString("party_id"));
	    	  map.put("Customer.partyCode", rs.getString("party_code"));
	    	  map.put("Customer.partyName", rs.getString("party_name"));
	    	  map.put("Customer.displayName", rs.getString("Display_name"));
	    	  map.put("Customer.partyTag", rs.getString("party_tag"));
	    	  map.put("Customer.contactSalutation", rs.getString("Contact_salutation"));
	    	  map.put("Customer.contactFirstname", rs.getString("Contact_Firstname"));
	    	  map.put("Customer.contactLastname", rs.getString("Contact_Lastname"));
	    	  map.put("Customer.contactDesignation", rs.getString("Contact_Designation"));
	    	  map.put("Customer.contactPhone", rs.getString("Contact_Phone"));
	    	  map.put("Customer.contactEmail", rs.getString("Contact_Email"));
	    	  map.put("Customer.companyPhone", rs.getString("Company_phone"));
	    	  map.put("Customer.companyFax", rs.getString("Company_fax"));
	    	  map.put("Customer.companyEmail", rs.getString("Company_Email"));
	    	  map.put("Customer.Status", rs.getString("Status"));
	    	  map.put("Customer.creditDays", rs.getString("Credit_Days"));
	    	  map.put("Customer.billStreet", rs.getString("Bill_Street"));
	    	  map.put("Customer.billLocation", rs.getString("Bill_Location"));
	    	  map.put("Customer.billCity", rs.getString("Bill_City"));
	    	  map.put("Customer.billState", rs.getString("Bill_State"));
	    	  map.put("Customer.billCountry", rs.getString("Bill_Country"));
	    	  map.put("Customer.billZip", rs.getString("Bill_Zip"));
	    	  map.put("Customer.shipStreet", rs.getString("Ship_Street"));
	    	  map.put("Customer.shipLocation", rs.getString("Ship_Location"));
	    	  map.put("Customer.shipCity", rs.getString("Ship_City"));
	    	  map.put("Customer.shipState", rs.getString("Ship_state"));
	    	  map.put("Customer.shipCountry", rs.getString("Ship_Country"));
	    	  map.put("Customer.shipZip", rs.getString("Ship_Zip"));
	    	  map.put("Customer.shipPhone", rs.getString("Ship_Phone"));
	    	  map.put("Customer.panNO", rs.getString("PAN_NO"));
	    	  map.put("Customer.vatNo", rs.getString("VAT_No"));
	    	  map.put("Customer.servtaxNo", rs.getString("Servtax_No"));
	    	  map.put("Customer.insuranceDetails", rs.getString("Insurance_details"));
	    	  map.put("Customer.accountGroup", rs.getString("Group_Name"));
	    	  map.put("Customer.billTracking", rs.getString("Bill_tracking"));
	    	  map.put("Customer.Notes", rs.getString("Notes"));
	    	  map.put("Customer.currency", rs.getString("currency_name"));
	     
		        custList.add(map);
	      }
	      
	    }
	    finally{
		      if(cstmt!=null)
		    	  cstmt.close();
		    
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfResultSet = new ResultSet[2];      
		      ArrayOfResultSet[0] = rs;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }

	    return custList;
	  }
	public boolean checkExistence(Connection con,TPCSUser ui,Customer objC) throws SQLException{
		PreparedStatement pst=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			System.out.println("objC.getMode():"+objC.getMode());
			if(objC.getMode().equals("n")){
			StringBuffer query=new StringBuffer(" if(exists(select p.* from party p left join account acc with(nolock) on p.account_id=acc.account_id "
					+ " where (p.party_code=? or acc.account_code=?) or (p.party_name=? or acc.account_name=?)))"
					+ " begin"
					+ " select 1"
					+ " end"
					+ " else "
					+ " begin"
					+ " select 0	"
					+ " end ");
			pst=con.prepareStatement(query.toString());
			pst.setString(1, objC.getPartyCode());
			pst.setString(2, objC.getPartyCode());
			pst.setString(3, objC.getPartyName());
			pst.setString(4, objC.getPartyName());
			}
			else {
				StringBuffer query=new StringBuffer(" if(exists(select p.* from party p left join account acc with(nolock) on p.account_id=acc.account_id "
						+ " where p.party_id<>? and ((p.party_code=? or acc.account_code=?) or (p.party_name=? or acc.account_name=?))))"
						+ " begin"
						+ " select 1"
						+ " end"
						+ " else "
						+ " begin"
						+ " select 0	"
						+ " end ");	
				pst=con.prepareStatement(query.toString());
				pst.setInt(1, objC.getPartyId());
				pst.setString(2, objC.getPartyCode());
				pst.setString(3, objC.getPartyCode());
				pst.setString(4, objC.getPartyName());
				pst.setString(5, objC.getPartyName());
			}
			
		//	System.out.println("objC.getPartyCode() :"+objC.getPartyCode());
		//	System.out.println("objC.getPartyName() :"+objC.getPartyName());
			
			rs=pst.executeQuery();
			if(rs.next()){
			//	System.out.println("rs.getInt(1) :"+rs.getInt(1));
				
				flag=rs.getInt(1)>0?true:false;//true already exists.
			}
			
		} finally{
			DatabaseConnection.closeAll(pst, rs);
		}
		return flag;
	}
	public int saveCustomer(Connection con, TPCSUser ui,
			Customer objC)throws SQLException,Exception{
		// TODO Auto-generated method stub
		CallableStatement cstmt=null;
		ResultSet rs=null;
		int custId=0;
		try
		{
			cstmt=con.prepareCall("{call pr_insert_customer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			//System.out.println("ui.getUserName()------------>"+ui.getUserName());
			//System.out.println("objC.getCompanyFax()---->"+objC.getCompanyFax());
			//System.out.println("objC.getBillTracking()---->"+objC.getBillTracking());
			//System.out.println("objC.getPartyTag()--->"+objC.getPartyTag());
			cstmt.setString(1,ui.getUserName());
			cstmt.setInt(2,ui.getCompanyId());
			cstmt.setInt(3,ui.getLocationId());
			cstmt.setString(4,objC.getPartyCode());
			cstmt.setString(5,objC.getPartyName());
			cstmt.setString(6,objC.getDisplayName());
			cstmt.setString(7,objC.getPartyTag());
			cstmt.setString(8,objC.getContactSalutation());
			cstmt.setString(9,objC.getContactFirstname());
			cstmt.setString(10,objC.getContactLastname());
			cstmt.setString(11,objC.getContactDesignation());
			cstmt.setString(12,objC.getContactPhone());
			cstmt.setString(13,objC.getContactEmail());
			cstmt.setString(14,objC.getCompanyPhone());
			cstmt.setString(15,objC.getCompanyFax());
			cstmt.setString(16,objC.getCompanyEmail());
			cstmt.setInt(17,objC.getStatus());
			cstmt.setInt(18,objC.getCurrencyId());
			cstmt.setInt(19,objC.getCreditDays());
			cstmt.setString(20,objC.getBillStreet());
			cstmt.setString(21,objC.getBillLocation());
			cstmt.setString(22,objC.getBillCity());
			cstmt.setString(23,objC.getBillState());
			cstmt.setString(24,objC.getBillCountry());
			cstmt.setString(25,objC.getBillZip());
			cstmt.setString(26,objC.getShipStreet());
			cstmt.setString(27,objC.getShipLocation());
			cstmt.setString(28,objC.getShipCity());
			cstmt.setString(29,objC.getShipState());
			cstmt.setString(30,objC.getShipCountry());
			cstmt.setString(31,objC.getShipZip());
			cstmt.setString(32,objC.getShipPhone());
			cstmt.setString(33,objC.getPanNO());
			cstmt.setString(34,objC.getVatNo());
			cstmt.setString(35,objC.getServtaxNo());
			cstmt.setString(36,objC.getInsuranceDetails());
			cstmt.setInt(37,objC.getAccountGroupId());
			cstmt.setInt(38,objC.getBillTracking());
			cstmt.setString(39,objC.getNotes());
			cstmt.setInt(40,objC.getPartyId());
			cstmt.setString(41,objC.getMode());
			cstmt.setString(42,objC.getPaymentTerms());
			//System.out.println("mmd.getMode(-----)"+objC.getMode());
		    
			rs=cstmt.executeQuery();
			if(rs.next())
			{
				custId=rs.getInt(1);
			}
			
		//System.out.println("custId DAO----> in insert"+custId);
		}
		finally
		{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return custId;
	}
	public Customer getCustomerInfo(Connection con, TPCSUser ui,int custId)throws SQLException,Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Customer objC=null;
		try
		{
			pstmt = con.prepareStatement(SQLUtil.GET_CUSTOMER_INFO);
			pstmt.setInt(1, custId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				 objC=new Customer();

				objC.setPartyId(rs.getInt(1));
				objC.setPartyCode(rs.getString(2));
				objC.setPartyName(rs.getString(3));
				objC.setDisplayName(rs.getString(4));
				objC.setPartyTag(rs.getString(5));
				objC.setContactSalutation(rs.getString(6));
				objC.setContactFirstname(rs.getString(7));
				objC.setContactLastname(rs.getString(8));
				objC.setContactDesignation(rs.getString(9));
				objC.setContactPhone(rs.getString(10));
				objC.setContactEmail(rs.getString(11));
				objC.setCompanyPhone(rs.getString(12));
				objC.setCompanyFax(rs.getString(13));
				objC.setCompanyEmail(rs.getString(14));
				objC.setStatus(rs.getInt(15));
				objC.setCurrencyId(rs.getInt(16));
				objC.setCreditDays(rs.getInt(17));
				objC.setBillStreet(rs.getString(18));
				objC.setBillLocation(rs.getString(19));
				objC.setBillCity(rs.getString(20));
				objC.setBillState(rs.getString(21));
				objC.setBillCountry(rs.getString(22));
				objC.setBillZip(rs.getString(23));
				objC.setShipStreet(rs.getString(24));
				objC.setShipLocation(rs.getString(25));
				objC.setShipCity(rs.getString(26));
				objC.setShipState(rs.getString(27));
				objC.setShipCountry(rs.getString(28));
				objC.setShipZip(rs.getString(29));
				objC.setShipPhone(rs.getString(30));
				objC.setPanNO(rs.getString(31));
				objC.setVatNo(rs.getString(32));
				objC.setServtaxNo(rs.getString(33));
				objC.setInsuranceDetails(rs.getString(34));
				objC.setAccountGroupId(rs.getInt(35));
				objC.setBillTracking(rs.getInt(36));
				objC.setNotes(rs.getString(37));
				objC.setAccountGroup(rs.getString(38));
				objC.setPaymentTerms(rs.getString(39));
				objC.setGroupType(rs.getString(40));
				objC.setCurrency(rs.getString(41));
		
			}
		}
		finally
		{
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return objC;
		
	}
	  public boolean checkCustomerExistence(Connection con, Customer objC,TPCSUser ui) throws SQLException{
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  boolean flag=false;
		  try {
			  
				pst=con.prepareStatement(" select distinct count(*) from sales_enquiry where party_id=?");
				pst.setInt(1, objC.getPartyId());
				
				rs=pst.executeQuery();
				if(rs.next()){
					flag=rs.getInt(1)>0?true:false;//true already exists
					System.out.println("Cust Exist in sales_enquiry :"+flag);
				}
				
				if(!flag)
				{
					pst=con.prepareStatement(" select distinct count(*) from sales_quotation where party_id=?");
					pst.setInt(1, objC.getPartyId());
					
					rs=pst.executeQuery();
					if(rs.next()){
						flag=rs.getInt(1)>0?true:false;//true already exists.
						System.out.println("Cust Exist in sales_quotation :"+flag);
					}
					
				}
				 if(!flag)
				{
					pst=con.prepareStatement(" select distinct count(*) from sales_order where party_id=?");
					pst.setInt(1, objC.getPartyId());
					
					rs=pst.executeQuery();
					if(rs.next()){
						flag=rs.getInt(1)>0?true:false;//true already exists.
						System.out.println("Cust Exist in sales_order :"+flag);
					}
				}
				  if(!flag)
				{
					pst=con.prepareStatement(" select distinct count(*) from sales_invoice where party_id=?");
					pst.setInt(1, objC.getPartyId());
					
					rs=pst.executeQuery();
					if(rs.next()){
						flag=rs.getInt(1)>0?true:false;//true already exists.
						System.out.println("Cust Exist in sales_invoice :"+flag);
					}
				}
				  if(!flag)
				{
					pst=con.prepareStatement(" select distinct count(*) from po where party_id=?");
					pst.setInt(1, objC.getPartyId());
					
					rs=pst.executeQuery();
					if(rs.next()){
						flag=rs.getInt(1)>0?true:false;//true already exists.
						System.out.println("Cust Exist in po :"+flag);
					}
				}
				  if(!flag)
				{
					pst=con.prepareStatement(" select distinct count(*) from purchase_invoice where party_id=?");
					pst.setInt(1, objC.getPartyId());
					
					rs=pst.executeQuery();
					if(rs.next()){
						flag=rs.getInt(1)>0?true:false;//true already exists.
						System.out.println("Cust Exist in po :"+flag);
					}
				}
				  if(!flag)
				{
					pst=con.prepareStatement(" select distinct count(*) from fin_tr_details ftd inner join party p on ftd.account_id=p.account_id and ftd.fin_tr_type<>'OPBAL' where p.party_id=?");
					pst.setInt(1, objC.getPartyId());
					
					rs=pst.executeQuery();
					if(rs.next()){
						flag=rs.getInt(1)>0?true:false;//true already exists.
						System.out.println("Cust Exist in fin_tr_details :"+flag);
					}
				}
		} finally{
			DatabaseConnection.closeAll(pst, rs);
		}
		  return flag;
	  }
	  
	  
	public Customer deleteCustomer(Connection con,Customer objC) throws SQLException {
		// TODO Auto-generated method stub
		  PreparedStatement ArrayOfPreparedStatement[] = null;
	      ResultSet ArrayOfResultSet[] = null;
		  PreparedStatement pstmt=null;   
		try
		{
			int iCount=0;
		     pstmt = con.prepareStatement(SQLUtil.DELETE_CUSTOMER);
		     pstmt.setInt(1, objC.getPartyId());
		     iCount = pstmt.executeUpdate();
	
		    //System.out.println("iCount------"+iCount);
			if(iCount>0)
			{
				objC.setDeleted(true);
			}
		}
		
		finally{          
		       
	        ArrayOfPreparedStatement = new PreparedStatement[3];    
	        ArrayOfPreparedStatement[0] = pstmt;
	        ArrayOfResultSet = new ResultSet[0];
	        DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	      }
		return objC;
	}
	 public String getCurrency(Connection con) throws SQLException{
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  StringBuffer buffer=new StringBuffer("");
		  try {
			  String query="select currency_id,currency_name from currency_master ";
		
			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
			buffer.append("<currencymaster>");
			while(rs.next()){
				buffer.append("<currency>");
	  	  	  	
	  	  	  	buffer.append("<currencyid>");
	  	  	  	buffer.append(rs.getInt(1));
	  	  	  	buffer.append("</currencyid>");
	  	  	  	buffer.append("<currencyname>");
	  	  	  	buffer.append(rs.getString(2));
	  	  	  	buffer.append("</currencyname>");
	  	  	  	
	  	  	  	buffer.append("</currency>");
			}
			buffer.append("</currencymaster>");
		  } 
		  finally {
			  DatabaseConnection.closeAll(pst,rs);
		}
		  return buffer.toString();
	  }
	 
	 public Customer saveCustomer(Connection con, Customer objC,TPCSUser ui) throws SQLException {
			
		  PreparedStatement ArrayOfPreparedStatement[] = null;
		  ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement cstmt=null;
			ResultSet rs=null;
			boolean bFlag=false;
			try {
				
					
//					pstmt=con.prepareStatement(SQLUtil.INSERT_INTO_CUSTOMER);
//					pstmt.setString(1,ui.getUserName());
//					pstmt.setInt(2,ui.getCompanyId());
//					pstmt.setInt(3,ui.getLocationId());
//					pstmt.setString(4,objC.getPartyCode());
//					pstmt.setString(5,objC.getPartyName());
//					pstmt.setString(6,objC.getDisplayName());
//					pstmt.setString(7,objC.getPartyTag());
//					pstmt.setString(8,objC.getContactSalutation());
//					pstmt.setString(9,objC.getContactFirstname());
//					pstmt.setString(10,objC.getContactLastname());
//					pstmt.setString(11,objC.getContactDesignation());
//					pstmt.setString(12,objC.getContactPhone());
//					pstmt.setString(13,objC.getContactEmail());
//					pstmt.setString(14,objC.getCompanyPhone());
//					pstmt.setString(15,objC.getCompanyFax());
//					pstmt.setString(16,objC.getCompanyEmail());
//					pstmt.setInt(17,objC.getStatus());
//					pstmt.setInt(18,objC.getCurrencyId());
//					pstmt.setInt(19,objC.getCreditDays());
//					pstmt.setString(20,objC.getBillStreet());
//					pstmt.setString(21,objC.getBillLocation());
//					pstmt.setString(22,objC.getBillCity());
//					pstmt.setString(23,objC.getBillState());
//					pstmt.setString(24,objC.getBillCountry());
//					pstmt.setString(25,objC.getBillZip());
//					pstmt.setString(26,objC.getShipStreet());
//					pstmt.setString(27,objC.getShipLocation());
//					pstmt.setString(28,objC.getShipCity());
//					pstmt.setString(29,objC.getShipState());
//					pstmt.setString(30,objC.getShipCountry());
//					pstmt.setString(31,objC.getShipZip());
//					pstmt.setString(32,objC.getShipPhone());
//					pstmt.setString(33,objC.getPanNO());
//					pstmt.setString(34,objC.getVatNo());
//					pstmt.setString(35,objC.getServtaxNo());
//					pstmt.setString(36,objC.getInsuranceDetails());
//					pstmt.setInt(37,objC.getAccountGroupId());
//					pstmt.setInt(38,objC.getBillTracking());
//					pstmt.setString(39,objC.getNotes());
//					pstmt.setString(40,objC.getPaymentTerms());
//					 
//				      int iCount = pstmt.executeUpdate();
//				      bFlag=(iCount>0)?true:false;
//				      objC.setInserted(bFlag);
				
				cstmt=con.prepareCall("{call pr_insert_customer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				//System.out.println("ui.getUserName()------------>"+ui.getUserName());
				//System.out.println("objC.getCompanyFax()---->"+objC.getCompanyFax());
				//System.out.println("objC.getBillTracking()---->"+objC.getBillTracking());
				//System.out.println("objC.getPartyTag()--->"+objC.getPartyTag());
				cstmt.setString(1,ui.getUserName());
				cstmt.setInt(2,ui.getCompanyId());
				cstmt.setInt(3,ui.getLocationId());
				cstmt.setString(4,objC.getPartyCode());
				cstmt.setString(5,objC.getPartyName());
				cstmt.setString(6,objC.getDisplayName());
				cstmt.setString(7,objC.getPartyTag());
				cstmt.setString(8,objC.getContactSalutation());
				cstmt.setString(9,objC.getContactFirstname());
				cstmt.setString(10,objC.getContactLastname());
				cstmt.setString(11,objC.getContactDesignation());
				cstmt.setString(12,objC.getContactPhone());
				cstmt.setString(13,objC.getContactEmail());
				cstmt.setString(14,objC.getCompanyPhone());
				cstmt.setString(15,objC.getCompanyFax());
				cstmt.setString(16,objC.getCompanyEmail());
				cstmt.setInt(17,objC.getStatus());
				cstmt.setInt(18,objC.getCurrencyId());
				cstmt.setInt(19,objC.getCreditDays());
				cstmt.setString(20,objC.getBillStreet());
				cstmt.setString(21,objC.getBillLocation());
				cstmt.setString(22,objC.getBillCity());
				cstmt.setString(23,objC.getBillState());
				cstmt.setString(24,objC.getBillCountry());
				cstmt.setString(25,objC.getBillZip());
				cstmt.setString(26,objC.getShipStreet());
				cstmt.setString(27,objC.getShipLocation());
				cstmt.setString(28,objC.getShipCity());
				cstmt.setString(29,objC.getShipState());
				cstmt.setString(30,objC.getShipCountry());
				cstmt.setString(31,objC.getShipZip());
				cstmt.setString(32,objC.getShipPhone());
				cstmt.setString(33,objC.getPanNO());
				cstmt.setString(34,objC.getVatNo());
				cstmt.setString(35,objC.getServtaxNo());
				cstmt.setString(36,objC.getInsuranceDetails());
				cstmt.setInt(37,objC.getAccountGroupId());
				cstmt.setInt(38,objC.getBillTracking());
				cstmt.setString(39,objC.getNotes());
				cstmt.setInt(40,objC.getPartyId());
				cstmt.setString(41,objC.getMode());
				cstmt.setString(42,objC.getPaymentTerms());
				//System.out.println("mmd.getMode(-----)"+objC.getMode());
			    
				rs=cstmt.executeQuery();
				if(rs.next())
				{
					 objC.setInserted(rs.getInt(1)>0?true:false);
				}

				
			}finally{
				ArrayOfPreparedStatement = new PreparedStatement[5];
				ArrayOfPreparedStatement[0] = cstmt;
			      ArrayOfResultSet = new ResultSet[5];      
			      ArrayOfResultSet[0] = rs;
			        DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
			      }
			return objC;
		}
	 
	 
	  public boolean getAccountBillTracking(Connection con,TPCSUser ui,int groupId) throws SQLException{
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  boolean billExist=false;
		  try {
			  
			  String query="SELECT group_id,group_name,group_type from account_group where group_id="+groupId;
		
			pst=con.prepareStatement(query);
		   rs=pst.executeQuery();
		   Group obj=new Group();
			if(rs.next())
			{
				obj.setGroupId(rs.getInt(1));
				obj.setGroupName(rs.getString(2));//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//				obj.setGroupType(rs.getString(3));
//				if(obj.getGroupType().equalsIgnoreCase("Sundry Debtors") || obj.getGroupType().equalsIgnoreCase("Sundry Creditors")){
//					billExist=true;	
//				}
			}
			
		  } 
		  finally {
			  DatabaseConnection.closeAll(pst,rs);
		}
		  return billExist;
	  }
	  
}
