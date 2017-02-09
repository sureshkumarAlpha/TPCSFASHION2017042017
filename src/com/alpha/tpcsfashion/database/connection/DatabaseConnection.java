package com.alpha.tpcsfashion.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.alpha.tpcsfashion.beans.TPCSUser;

public class DatabaseConnection {
	
	public Connection getConnection(String strDataSource,String strDataBase,String strServerIP,String strSqlusername,String strSqlpassword){
	    try{
	    	String m_Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";    	
	    	String m_Url = "jdbc:sqlserver://"+strServerIP+";databaseName="+strDataBase+";user="+strSqlusername+";password="+strSqlpassword+";";
	    	//String m_Url = "jdbc:sqlserver://IE3BLTFBKTLV1\\SQLEXPRESS:1433;databaseName=DetailsDB;user=sa;password=Password1;";
	    	//System.out.println("SQL URL:\t\t"+m_Url);
	    	Class.forName(m_Driver);    	
	    	Connection  conn = DriverManager.getConnection(m_Url);
	    	return conn;
	    }catch(SQLException sqe){
	      sqe.printStackTrace();
	      return null;
	    }catch(Exception e){
	      e.printStackTrace();
	      return null;
	    }
	  } 

	public Connection getConnection(TPCSUser userInfo){
		Connection  conn=null;
		try{
			conn =getConnection(userInfo.getDatasource(),userInfo.getDatabase(),userInfo.getServerip() ,userInfo.getSqlusername(), userInfo.getSqlpassword());
		}
		catch(NullPointerException npe){
			npe.printStackTrace();
		}
    	return conn;
  } 

	
	public Connection getCommonConnection(){
	    try{
	    Context initContext = new InitialContext();
	    Context envContext  = (Context)initContext.lookup("java:/comp/env");
	    DataSource ds = (DataSource)envContext.lookup("jdbc/COMMON");
	    Connection conn = ds.getConnection();
	    return conn;
	    }catch(SQLException sqe){
	      sqe.printStackTrace();
	      return null;
	    }catch(Exception e){
	      e.printStackTrace();
	      return null;
	    }
	  }  
	
	
	 public static void closeAll(PreparedStatement pstmt[],ResultSet rs[]){
		    try {
		      if(pstmt != null) {
		          for ( int j = 0 ; j< pstmt.length; j++) {
		              if(pstmt[j] != null)
		                pstmt[j].close();
		          }
		      }
		      if(rs != null) {
		          for ( int i = 0 ; i< rs.length; i++) {
		              if(rs[i] != null)
		                rs[i].close();
		          }
		      }
		  } catch(Exception e) {
		      e.printStackTrace();
		  }
		  }
	 
	 public static void closeAll(Statement pstmt[],ResultSet rs[]){
		    try {
		      if(pstmt != null) {
		          for ( int j = 0 ; j< pstmt.length; j++) {
		              if(pstmt[j] != null)
		                pstmt[j].close();
		          }
		      }
		      if(rs != null) {
		          for ( int i = 0 ; i< rs.length; i++) {
		              if(rs[i] != null)
		                rs[i].close();
		          }
		      }
		  } catch(Exception e) {
		      e.printStackTrace();
		  }
		  }
	 
	 public static void closeAll(Statement pstmt,ResultSet rs)
	 {
		 try {
		      if(pstmt != null) {
		         
		                pstmt.close();
		          }
		      
		      if(rs != null) {
		          
		                rs.close();
		          }
		      
		  } catch(Exception e) {
		      e.printStackTrace();
		  }
	 }
	 public static void connectionRollBack(Connection con){
		    try{
		    	if(con!=null){
		    		con.rollback();
		    	}
		      }catch(SQLException se){
		        se.printStackTrace();
		      }
		  }
	 
	 public static void connectionClose(Connection con){
		    try{
		      if(con!=null){
		    	  con.close();
		      }
		      }catch(SQLException se){
		        se.printStackTrace();
		      }
		  }
	
}
