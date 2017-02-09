package com.alpha.tpcsfashion.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alpha.tpcsfashion.beans.GroupType;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.dao.GroupTypeDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class GroupTypeManager {
	 public List<GroupType> getAllGroupType(TPCSUser ui){
			Connection con=null;
			List<GroupType> objList = null;
			    
			try{

				con = new DatabaseConnection().getConnection(ui);
				GroupTypeDAO objDAO = new GroupTypeDAO();         
				objList = objDAO.getAllGroupType(con,ui);

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
			return objList;
		  }

}
