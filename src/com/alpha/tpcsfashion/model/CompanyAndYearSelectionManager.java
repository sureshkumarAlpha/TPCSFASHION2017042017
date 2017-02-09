package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.Company;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.Year;
import com.alpha.tpcsfashion.dao.CompanyAndYearDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class CompanyAndYearSelectionManager {

	  public List<Company> getCompany(TPCSUser ui){
		    Connection con=null;
		    List<Company> company = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      CompanyAndYearDAO objCompanyAndYearDAO = new CompanyAndYearDAO();       
		      company = objCompanyAndYearDAO.getCompany(con,ui.getclientId());
		    }catch(Exception sqe){
		      sqe.printStackTrace();
		    }finally{
		      try{
		        if(con!=null)
		           con.close();
		      }catch(SQLException se){
		        se.printStackTrace();
		      }
		    }
		    return company;
		  }
	
	  public List<Year> getYear(TPCSUser ui){
		    Connection con=null;
		    List<Year> year = null;
		    try{
		      con = new DatabaseConnection().getConnection(ui);
		      CompanyAndYearDAO objCompanyAndYearDAO = new CompanyAndYearDAO();       
		      year = objCompanyAndYearDAO.getYear(con,ui.getclientId());
		    }catch(Exception sqe){
		      sqe.printStackTrace();
		    }finally{
		      try{
		        if(con!=null)
		           con.close();
		      }catch(SQLException se){
		        se.printStackTrace();
		      }
		    }
		    return year;
		  }
	  
	  public Company getCompanyInfo(TPCSUser userInfo){
		    Connection con=null;
		    Company objCompany = null;
		    
		    try{
		      con = new DatabaseConnection().getConnection(userInfo.getDatasource(),userInfo.getDatabase(),userInfo.getServerip(),userInfo.getSqlusername(),userInfo.getSqlpassword());
		      CompanyAndYearDAO objCompanyAndYearDAO = new CompanyAndYearDAO();       
		      objCompany = objCompanyAndYearDAO.getCompanyInfo(con,userInfo);
		    }catch(Exception sqe){
		      sqe.printStackTrace();
		    }finally{
		      try{
		        if(con!=null)
		           con.close();
		      }catch(SQLException se){
		        se.printStackTrace();
		      }
		    }
		    return objCompany;
		  }
}
