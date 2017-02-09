package com.alpha.tpcsfashion.model;

import java.sql.Connection;

import com.alpha.tpcsfashion.beans.HomePage;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.dao.HomePageDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class HomePageManager {

	public HomePage getHomePageData(TPCSUser ui,HomePage homePage) {
		Connection con=null;
		try{
			con = new DatabaseConnection().getConnection(ui);
			HomePageDAO objDAO=new HomePageDAO(); 
			homePage= objDAO.getHomePageData(con,ui,homePage);
		}catch(Exception sqe){
			sqe.printStackTrace();
		}finally{
			DatabaseConnection.connectionClose(con);
		}
		return homePage;
	}
}
