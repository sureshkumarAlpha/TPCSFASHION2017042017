package com.alpha.tpcsfashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.GroupType;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class GroupTypeDAO {
	 public List<GroupType> getAllGroupType(Connection con,TPCSUser ui)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmt=null;
		    ResultSet rs = null;   
		    List<GroupType> objList=new ArrayList<GroupType>();
		    try{      
		      
		      pstmt = con.prepareStatement(SQLUtil.GET_ALL_GROUP);
		      rs = pstmt.executeQuery();
		      while(rs.next()){
		    	GroupType item = new GroupType ();
		        item.setGroupTypeId(rs.getInt(1));
		        item.setGroupType(rs.getString(2));
		        objList.add(item);
		      }
		      
		    }finally{     
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmt;
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rs;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return objList;
		  }

}
