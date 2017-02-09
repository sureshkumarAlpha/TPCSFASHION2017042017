package com.alpha.tpcsfashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.Company;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.Year;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class CompanyAndYearDAO {
	
	public List<Company> getCompany(Connection con,int iClientId)throws SQLException,Exception{    
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGetCompany=null;
	    ResultSet rsGetCompany = null;    
	    List<Company> company=new ArrayList<Company>();
	    try{      
	      
	    	pstmtGetCompany = con.prepareStatement(SQLUtil.SELECT_COMPANY);   
	    	pstmtGetCompany.setInt(1, iClientId);
	    	rsGetCompany = pstmtGetCompany.executeQuery();
	      while(rsGetCompany.next()){
	    	  Company objCompany = new Company();
	    	  objCompany.setCompanyId(rsGetCompany.getInt(2));            
	    	  objCompany.setCompanyName(rsGetCompany.getString(4));
	    	  objCompany.setPartyId(rsGetCompany.getInt(6));
	    	  objCompany.setCurrencyId(rsGetCompany.getInt(7));
	    	  company.add(objCompany);
	      }
	    }finally{
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtGetCompany;
	      ArrayOfResultSet = new ResultSet[1];      
	      ArrayOfResultSet[0] = rsGetCompany;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return company;
	  }
	
	public List<Year> getYear(Connection con,int iClientId)throws SQLException,Exception{    
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGetYear=null;
	    ResultSet rsGetYear = null;    
	    List<Year> year=new ArrayList<Year>();
	    try{      
	      
	    	pstmtGetYear = con.prepareStatement(SQLUtil.SELECT_YEAR);
	    	pstmtGetYear.setInt(1, iClientId);
	    	rsGetYear = pstmtGetYear.executeQuery();
	      while(rsGetYear.next()){
	    	  Year objyear = new Year();
	    	  objyear.setYearId(rsGetYear.getInt(1));            
	    	  objyear.setStartDate(rsGetYear.getString(2));
	    	  objyear.setEndDate(rsGetYear.getString(3));
	    	  year.add(objyear);
	      }
	    }finally{
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtGetYear;
	      ArrayOfResultSet = new ResultSet[1];
	      ArrayOfResultSet[0] = rsGetYear;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return year;
	  }
	
	
	public Company getCompanyInfo(Connection con,TPCSUser userInfo)throws SQLException,Exception{    
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGetCompany=null;
	    ResultSet rsGetCompany = null;    
	    Company objCompany =  new Company();
	    try{      
	      
	    	
	    	pstmtGetCompany = con.prepareStatement(SQLUtil.GET_COMPANY);   
	    	pstmtGetCompany.setInt(1, userInfo.getCompanyId());
	    	pstmtGetCompany.setInt(2, userInfo.getclientId());
	    	rsGetCompany = pstmtGetCompany.executeQuery();
	      while(rsGetCompany.next()){
	    	  objCompany.setCompanyId(rsGetCompany.getInt(1));  
	    	  objCompany.setCompanyName(rsGetCompany.getString(2));
	    	  objCompany.setAddress1(rsGetCompany.getString(3));
	    	  objCompany.setAddress2(rsGetCompany.getString(4));
	    	  objCompany.setAddress3(rsGetCompany.getString(5));
	    	  objCompany.setCountry(rsGetCompany.getString(6));
	    	  objCompany.setPhoneNo(rsGetCompany.getString(7));
	    	  objCompany.setFaxNo(rsGetCompany.getString(8));
	    	  objCompany.setTaxRegNo1(rsGetCompany.getString(9));
	    	  objCompany.setTaxRegNo2(rsGetCompany.getString(10));
	    	  objCompany.setEmailId(rsGetCompany.getString(11));
	    }
	    }finally{
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] = pstmtGetCompany;
	      ArrayOfResultSet = new ResultSet[1];      
	      ArrayOfResultSet[0] = rsGetCompany;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return objCompany;
	  }
	
	
	
}
