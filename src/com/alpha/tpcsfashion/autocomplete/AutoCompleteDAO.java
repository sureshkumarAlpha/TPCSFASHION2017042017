package com.alpha.tpcsfashion.autocomplete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.dao.UserRightsDAO;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.SubdocumentId;
//import com.alpha.tpcsfashion.util.JenixCloudCommonUtil;
/**
 * 
 *  This class will be used to get all auto complete data from database.
 *
 * @author E361342
 * @creation Oct 6, 2013
 *
 */
public class AutoCompleteDAO
{

	/**
	 * This method will he used to get all the suppliers based on input values.
	 * @param con
	 * @param query
	 * @return
	 * @throws Exception
	 */

	/**
	 * This method will be used to get all groups from database.
	 * @param con
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public String getGroups(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetGroups = null;
		ResultSet rsGetGroups = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();

			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}


			System.out.println("query : "+query);
			//or group_code like ?
			pstmtGetGroups = con.prepareStatement("select group_id,group_code,group_name from groups where (group_name like ? ) and status=1 and company_id=? and location_id=?");
			pstmtGetGroups.setString(1, "%"+query+"%");
			//      pstmtGetGroups.setString(2, "%"+query+"%");
			pstmtGetGroups.setInt(2, ui.getCompanyId());
			pstmtGetGroups.setInt(3, ui.getLocationId());
			rsGetGroups = pstmtGetGroups.executeQuery();
			while(rsGetGroups.next()){
				obj = new JSONObject();
				//        System.out.println("rsGetGroups.getString(1) :"+rsGetGroups.getString(1));
				//        System.out.println("rsGetGroups.getString(2) :"+rsGetGroups.getString(2));
				//        System.out.println("rsGetGroups.getString(3) :"+rsGetGroups.getString(3));
				obj.put("id", rsGetGroups.getString(1));
				obj.put("name", rsGetGroups.getString(3));
				//        obj.put("name", JenixCloudCommonUtil.formatString(rsGetGroups.getString(2),rsGetGroups.getString(3)));
				//        obj.put("track", rsGetGroups.getString(4));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtGetGroups;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rsGetGroups;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	
	public String getTempGroups(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtGetGroups = null;
		ResultSet rsGetGroups = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();

			/*if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}*/


			System.out.println("getTempGroups query : "+query);
			
			pstmtGetGroups = con.prepareStatement("select group_id,group_name from temp_excel_group where (group_name like ? ) order by group_name ");
			pstmtGetGroups.setString(1, "%"+query+"%");
			//      pstmtGetGroups.setString(2, "%"+query+"%");
			rsGetGroups = pstmtGetGroups.executeQuery();
			while(rsGetGroups.next()){
				obj = new JSONObject();
				obj.put("id", rsGetGroups.getString(1));
				obj.put("name", rsGetGroups.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtGetGroups;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rsGetGroups;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	
	public String getIndentType(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();

			pst = con.prepareStatement(" SELECT isnull(Indent_Type,'') from def_indent_types where Indent_Type like ?  order by Indent_Type");
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("name", rs.getString(1));
				data.put(obj);
			}
		}
		finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	
	public String getUOM(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select uom_id,uom from uom_master where uom like ?");
			pst.setString(1, "%"+query+"%");
			//	      pstmtGetGroups.setString(2, "%"+query+"%");
			//	      pstmtGetGroups.setInt(2, ui.getCompanyId());
			//	      pstmtGetGroups.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				//	        System.out.println("rsGetGroups.getString(1) :"+rsGetGroups.getString(1));
				//	        System.out.println("rsGetGroups.getString(2) :"+rsGetGroups.getString(2));
				//	        System.out.println("rsGetGroups.getString(3) :"+rsGetGroups.getString(3));
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				//	        obj.put("name", JenixCloudCommonUtil.formatString(rsGetGroups.getString(2),rsGetGroups.getString(3)));
				//	        obj.put("track", rsGetGroups.getString(4));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}


	public String getCustomer(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select party_id,party_name from party where party_tag='customer' and party_name like ? and status=1 and company_id=? and location_id=? order by party_name");
			pst.setString(1, "%"+query+"%");
			//	      pstmtGetGroups.setString(2, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				//	        System.out.println("rsGetGroups.getString(1) :"+rsGetGroups.getString(1));
				//	        System.out.println("rsGetGroups.getString(2) :"+rsGetGroups.getString(2));
				//	        System.out.println("rsGetGroups.getString(3) :"+rsGetGroups.getString(3));
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				//	        obj.put("name", JenixCloudCommonUtil.formatString(rsGetGroups.getString(2),rsGetGroups.getString(3)));
				//	        obj.put("track", rsGetGroups.getString(4));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getAgent(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select party_id,party_name from party where party_tag='agent' and party_name like ? and status=1 and company_id=? and location_id=? order by party_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	
	public String getSeason(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select season_id,season_name from season where season_name like ? and is_active=1 order by season_name");
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getSupplier(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select party_id,party_name from party where party_tag in  ('Vendor','Both') and party_name like ? and status=1 and company_id=? and location_id=? order by party_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	public String getInventoryAccount(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			//	      obj.put("id", "-2");
			//	      obj.put("name", "+ Add New");
			//	      data.put(obj);

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select account_id,acc.account_name 	from account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " where ag.group_type ='Inventory' and acc.Active=1 and acc.account_name like ? and acc.company_id=? and acc.location_id=?");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getPurchaseAccount(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select account_id,acc.account_name 	from account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " where ag.group_type like '%purchase%'  and acc.Active=1 and acc.account_name like ? and acc.company_id=? and acc.location_id=?");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getMaterial(Connection con,String query,TPCSUser ui,int createNew,int groupId)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			String matQuery="";
			String filtrQuery="";
			
			if(groupId>0){
				filtrQuery=" group_id="+groupId+" and ";
			}
			matQuery="select item_id,item_name from item where "+filtrQuery+" item_name like ? and is_active=1 order by item_name";
			
			
			System.out.println("matQuery :"+matQuery);
			pst = con.prepareStatement(matQuery);
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	
	public String getMaterialFG(Connection con,String query,TPCSUser ui,int createNew,int groupId)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
				obj=null;
			}
			String matQuery="";
//			String filtrQuery="";
			
//			if(groupId>0){
//				filtrQuery=" group_id="+groupId+" and ";
//			}
			matQuery="select it.item_Id, it.item_Name "
					+ " from item it with(nolock) "
					+ " inner join groups g with(nolock) on it.group_id=it.group_id"
					+ " inner join group_type gt with(nolock) on g.group_type_id=gt.group_type_id"
					+ " where it.is_active=1 and it.item_Name like ? and (gt.group_type ='Finished Goods' or gt.group_type ='Components Manufactured' ) ";
			
//			System.out.println("matQuery---->"+matQuery);
			pst = con.prepareStatement(matQuery);
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
				obj=null;
			}
			
			matQuery=null;

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	
	public String getMaterialComponent(Connection con,String query,TPCSUser ui,int createNew,int trId)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			UserRights ur=new UserRightsDAO().getUserRights(con,SubdocumentId.MATERIAL_MASTER, ui);
			JSONObject obj = new JSONObject();
			if(createNew==1 && ur.getAddPermission()==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
				obj=null;
			}
			String matQuery="";
			matQuery=" select distinct bd.item_Id,m.item_Name from bom b "+
				"	inner join bom_details bd on b.bom_id=bd.bom_id "+
				"	inner join item m on  m.item_Id=bd.item_Id "+
				"	where b.bom_id=? and m.item_Name like ? and m.is_active=1 order by m.item_Name ";
			
			
			pst = con.prepareStatement(matQuery);
			pst.setInt(1, trId);
			pst.setString(2, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
				obj=null;
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	
	
	
	public String getBOMComponents(Connection con,String query,TPCSUser ui,int createNew,int detId)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
				obj=null;
			}
			String compQuery="";
			compQuery="select distinct component_name from bom_comp_details where bom_detail_id="+detId+" and component_name like ? order by component_name ";
			
			
		//	System.out.println("matQuery :"+matQuery);
			pst = con.prepareStatement(compQuery);
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("name", rs.getString(1));
				data.put(obj);
				obj=null;
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	
	public String getAltMaterial(Connection con,String query,TPCSUser ui,int createNew,int matId,int compId)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			UserRights ur=new UserRightsDAO().getUserRights(con,SubdocumentId.MATERIAL_MASTER, ui);
			JSONObject obj = new JSONObject();
			if(createNew==1 && ur.getAddPermission()==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
				obj=null;
			}
			String matQuery="";
			matQuery="select item_id,item_name from item where item_id not in("+matId+","+compId+") and item_name like ? and is_active=1 order by item_name";
			
			
		//	System.out.println("matQuery :"+matQuery);
			pst = con.prepareStatement(matQuery);
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
				obj=null;
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	
	public String getBOM(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			UserRights ur=new UserRightsDAO().getUserRights(con,SubdocumentId.MATERIAL_MASTER, ui);
			JSONObject obj = new JSONObject();
//			if(createNew==1 && ur.getAddPermission()==1){
//				obj.put("id", "-2");
//				obj.put("name", "+ Add New");
//				data.put(obj);
//				obj=null;
//			}
			String matQuery="";
			matQuery="select bom_id,bom_no from bom where bom_no like ?  order by bom_no";
			pst = con.prepareStatement(matQuery);
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
				obj=null;
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	
	
	

	public String getFGMaterial(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			UserRights ur=new UserRightsDAO().getUserRights(con,SubdocumentId.MATERIAL_MASTER, ui);
			JSONObject obj = new JSONObject();
			if(createNew==1 && ur.getAddPermission()==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
				obj=null;
			}
			String matQuery="";
			matQuery=" select m.item_Id,m.item_Name from item m "+
					"	INNER JOIN groups g ON g.group_id=m.group_id "+
					"	INNER JOIN group_type gt ON gt.group_type_id=g.group_type_id    where   gt.group_type='Finished Goods' "+
					"	and m.item_Name like ? and m.is_active=1 order by m.item_Name ";
			
			pst = con.prepareStatement(matQuery);
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
				obj=null;
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	

	public String getMaterialForInvoice(Connection con,String query,TPCSUser ui,int createNew,int soId,int poId,int invId,int enqId,int quoteId,String trTag)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			String matQuery="";
			if(enqId>0 && trTag.equalsIgnoreCase("SQ"))//se materials in salesquotation
			{
				matQuery="select material_id,material_name from material where material_id in (select distinct material_id from sales_enquiry_details where enq_id="+enqId+") and  material_name like ? order by material_name";
			}
			else if(quoteId>0 && trTag.equalsIgnoreCase("SO"))//sq materials in salesOrder
			{
				matQuery="select material_id,material_name from material where material_id in (select distinct material_id from sales_quotation_details where quote_id="+quoteId+") and  material_name like ? order by material_name";
			}
			else if(soId>0 && (trTag.equalsIgnoreCase("INV") || trTag.equalsIgnoreCase("PO")) )//so materials in salesInvoice and Po
			{
				matQuery="select material_id,material_name from material where material_id in (select distinct material_id from sales_order_details where so_id="+soId+") and  material_name like ? order by material_name";
			}
			else if(poId>0 && trTag.equalsIgnoreCase("PI") ){//po materials in purchase invoice
				matQuery="select material_id,material_name from material where material_id in (select distinct material_id from po_details where po_id="+poId+") and  material_name like ? order by material_name";
			}
			else if(invId>0 && trTag.equalsIgnoreCase("RET")){//salesinvoice materials in sales  return
				matQuery="select material_id,material_name from material where material_id in (select distinct material_id from sales_invoice_details where invoice_id="+invId+") and  material_name like ? order by material_name";
			}
			else if(invId>0 && trTag.equalsIgnoreCase("PRET")){//purchase invoice materials in purchase  return
				matQuery="select material_id,material_name from material where material_id in (select distinct material_id from purchase_invoice_details where invoice_id="+invId+") and  material_name like ? order by material_name";
			}
			else{ //All Materials
				matQuery="select Material_id,Material_name from material where material_name like ? and status=1 order by material_name";
			}
			    System.out.println("matQuery---->"+matQuery);
			pst = con.prepareStatement(matQuery);
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getAccountGroup(Connection con,String query,TPCSUser ui)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
		  obj.put("id", "-2");
	      obj.put("name", "+ Add New");
	      data.put(obj);

			System.out.println("query account group: "+query);
			//or group_code like ?
			pst = con.prepareStatement("select group_id,group_name from account_group where group_name like ? and company_id=? and location_id=? order by group_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	
	public String getCurrency(Connection con,String query,TPCSUser ui)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
//			System.out.println("dao");

		  JSONObject obj = new JSONObject();
		  obj.put("id", "-2");
	      obj.put("name", "+ Add New");
	      data.put(obj);

//			System.out.println("query currency: "+query);
			//or group_code like ?
			pst = con.prepareStatement("select currency_id,currency_name from currency_master where currency_name like ?  order by currency_name");
			pst.setString(1, "%"+query+"%");
//			pst.setInt(2, ui.getCompanyId());
//			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	public String getParentGroup(Connection con,String query,TPCSUser ui)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			/*  obj.put("id", "-2");
	      obj.put("name", "+ Add New");
	      data.put(obj);*/

			System.out.println("query parent group: "+query);
			//or group_code like ?
			pst = con.prepareStatement("select group_id,Group_Name,group_level from account_group where group_id not in (select group_id from account where active=1 and company_id=? and location_id=? ) and group_name like ? and company_id=? and location_id=? order by group_name");
			pst.setInt(1, ui.getCompanyId());
			pst.setInt(2, ui.getLocationId());
			pst.setString(3, "%"+query+"%");
			pst.setInt(4, ui.getCompanyId());
			pst.setInt(5, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				obj.put("level", rs.getString(3));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	public String getAccountReceivedFrom(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");


			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select account_id,acc.account_name,ag.group_type,agt.category "
					+ " from account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
					+ " where   (ag.group_type like '%Sundry Creditor%'  or ag.group_type  like '%Sundry Debtor%' or ag.group_type  like '%Income%' or ag.group_type  like '%Sales%'  or ag.group_type  like '%Assets%' ) and acc.Active=1 and acc.account_name like ? and acc.company_id=? and acc.location_id=? order by acc.account_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				obj.put("type", rs.getString(3));
				obj.put("category", rs.getString(4));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getAllAccount(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			// System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select account_id,acc.account_name from account acc inner join account_group ag with(nolock) on acc.group_id=ag.group_id where   acc.Active=1 and acc.account_name like ?  and acc.company_id=? and acc.location_id=? order by acc.account_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getOpeningAccount(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);  
			}

			// System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select account_id,acc.account_name from account acc inner join account_group ag with(nolock) on acc.group_id=ag.group_id "+
					" inner join account_group_type agt on ag.group_type=agt.group_type and agt.Category='BS' where   acc.Active=1 and acc.account_name like ?  and acc.company_id=? and acc.location_id=? order by acc.account_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getAccountDepositTo(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select acc.account_id,acc.account_name from account acc inner join account_group ag with(nolock) on acc.group_id=ag.group_id where (ag.group_type like '%Cash%' or ag.group_type like '%Bank%')  and acc.Active=1  and acc.account_name like ? and acc.company_id=? and acc.location_id=? order by acc.account_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getBankAccount(Connection con,String query,TPCSUser ui,int createNew )throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);  
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select acc.account_id,acc.account_name from account acc inner join account_group ag with(nolock) on acc.group_id=ag.group_id where  ag.group_type like '%Bank%'  and acc.Active=1 and acc.account_name like ? and acc.company_id=? and acc.location_id=? order by acc.account_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getCashAccount(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}

			System.out.println("query : "+query);
			//or group_code like ?
			pst = con.prepareStatement("select acc.account_id,acc.account_name from account acc inner join account_group ag with(nolock) on acc.group_id=ag.group_id where ag.group_type like '%Cash%'  and acc.Active=1  and acc.account_name like ? and acc.company_id=? and acc.location_id=? order by acc.account_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getAccountPaidTo(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);  
			}

			System.out.println("query : "+query);
			//or group_code like ?
			//  pst = con.prepareStatement("select account_id,acc.account_name from account acc inner join account_group ag with(nolock) on acc.group_id=ag.group_id where ag.group_type not like '%income%' and acc.account_name like ? and acc.company_id=? and acc.location_id=? order by acc.account_name");
			pst = con.prepareStatement("select account_id,acc.account_name,ag.group_type,agt.category from account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
					+ " where (ag.group_type  like '%Sundry Debtor%' or ag.group_type  like '%Sundry Creditor%' or ag.group_type  like '%expense%' or ag.group_type  like '%Expenditure%' or ag.group_type  like '%Liabilit%' or ag.group_type  like '%Purchase%') and acc.Active=1 and acc.account_name like ? and acc.company_id=? and acc.location_id=? order by acc.account_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				obj.put("type", rs.getString(3));
				obj.put("category", rs.getString(4));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getJournalAccount(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			/*  obj.put("id", "-2");
	      obj.put("name", "+ Add New");
	      data.put(obj);*/

			//	      System.out.println("query : "+query);
			pst = con.prepareStatement("select account_id,acc.account_name,ag.group_type,agt.category "
					+ "from account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
					+ " where (ag.group_type not like '%Sundry Debtor%'  and ag.group_type not like '%Sundry Creditor%') and acc.Active=1 and acc.account_name like ? and acc.company_id=? and acc.location_id=? order by acc.account_name");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				obj.put("type", rs.getString(3));
				obj.put("category", rs.getString(4));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	public String getGroupForAccount(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			System.out.println("dao");

			JSONObject obj = new JSONObject();
			if(createNew==1){
				  obj.put("id", "-2");
			      obj.put("name", "+ Add New");
			      data.put(obj);
			}
	

			//	      System.out.println("query : "+query);
			pst = con.prepareStatement("select group_id,group_name from account_group where group_id  not in(select parent_group_id from account_group where parent_group_id is not null) and group_name like ? order by group_name");
			pst.setString(1, "%"+query+"%");
			//	      pst.setInt(2, ui.getCompanyId());
			//	      pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}

	public String getAccountOfDutiesAndTax(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			//	      System.out.println("query : "+query);
			pst = con.prepareStatement("select  acc.account_id,acc.account_name "
					+ " from "
					+ " account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " where   acc.Active=1 and acc.account_name like ? and ag.group_type like 'Duties and Taxes' and acc.company_id=? and acc.location_id=? "
					+ " order by acc.account_name ");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getAccountOfSales(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			//	      System.out.println("query : "+query);
			pst = con.prepareStatement("select  acc.account_id,acc.account_name "
					+ " from "
					+ " account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " where   acc.Active=1 and acc.account_name like ? and ag.group_type like '%Sales%' and acc.company_id=? and acc.location_id=? "
					+ " order by acc.account_name ");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getAccountOfPurchase(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			//	      System.out.println("query : "+query);
			pst = con.prepareStatement("select  acc.account_id,acc.account_name "
					+ " from "
					+ " account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " where   acc.Active=1 and acc.account_name like ? and ag.group_type like '%Purchase%' and acc.company_id=? and acc.location_id=? "
					+ " order by acc.account_name ");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getAccountOfExpenditureDirExpense(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			//	      System.out.println("query : "+query);
			pst = con.prepareStatement("select  acc.account_id,acc.account_name "
					+ " from "
					+ " account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " where   acc.account_name like ? and (ag.group_type like '%Direct Expense%' or ag.group_type like '%Expenditure%' ) and acc.Active=1 and acc.company_id=? and acc.location_id=? "
					+ " order by acc.account_name ");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getAccountOfExpenseLiability(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			//      System.out.println("query : "+query);
			pst = con.prepareStatement("select acc.account_id,acc.account_name,ag.group_type,agt.category "
					+ " from account acc "
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id "
					+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
					+ " where (ag.group_type like '%Expense%' or ag.group_type like '%Liabilit%') and acc.active=1 and acc.account_name like ? and acc.company_id=? and acc.location_id=? order by acc.account_name ");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				obj.put("group_type", rs.getString(3));
				obj.put("category", rs.getString(4));
				data.put(obj);
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getCostCenter(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			//      System.out.println("query : "+query);
			pst = con.prepareStatement("select costcenter_id,costcenter_name from costcenter where costcenter_name like ? and company_id=? and location_id=? order by costcenter_name  ");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getProfitCenter(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
//			      System.out.println("query : "+query);
			pst = con.prepareStatement("select costcenter_id,costcenter_name from costcenter where costcenter_name like ? and is_profit_center=1 and company_id=? and location_id=? order by costcenter_name  ");
			pst.setString(1, "%"+query+"%");
			pst.setInt(2, ui.getCompanyId());
			pst.setInt(3, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getBSAccountGroupType(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
//			if(createNew==1){
//				obj.put("id", "-2");
//				obj.put("name", "+ Add New");
//				data.put(obj);
//			}
			//      System.out.println("query : "+query);
			pst = con.prepareStatement("select agt.group_type_id,agt.group_type from account_group_type agt where agt.category='BS' and agt.group_type like ? ");
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	
	public String getPLAccountGroupType(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
//			if(createNew==1){
//				obj.put("id", "-2");
//				obj.put("name", "+ Add New");
//				data.put(obj);
//			}
			//      System.out.println("query : "+query);
			pst = con.prepareStatement("select agt.group_type_id,agt.group_type from account_group_type agt where  agt.category='PL' and agt.group_type like ? ");
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getOperation(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
//
//			if(createNew==1){
//				obj.put("id", "-2");
//				obj.put("name", "+ Add New");
//				data.put(obj);
//			}
			pst = con.prepareStatement(" SELECT DISTINCT o.operation_id,o.Operation from operation o where o.Operation like ? order by o.Operation");
			pst.setString(1, "%"+query+"%");
//			pst.setInt(2, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}
		}
		finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}		
	public String getgrouptype(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();

			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			pst = con.prepareStatement(" select group_type_id,group_type from group_type where group_type like ? order by sl_no");
			pst.setString(1, "%"+query+"%");
//			pst.setInt(2, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}
		}
		finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}	
	public String getparentgrouptype(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();

//			if(createNew==1){
//				obj.put("id", "-2");
//				obj.put("name", "+ Add New");
//				data.put(obj);
//			}
			pst = con.prepareStatement(" select group_type_id,group_type from group_type where parent_group_type_id=0 and group_type like ? order by sl_no");
			pst.setString(1, "%"+query+"%");
//			pst.setInt(2, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}
		}
		finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	
	public String getSizeRange(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			
			pst = con.prepareStatement("SELECT DISTINCT si.Size_Range_Id,si.Size_Range from Size_range si where si.Size_Range like ?  order by si.Size_Range DESC ");
			pst.setString(1, "%"+query+"%");
//			pst.setInt(2, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getInt(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}
		}
		finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}	
	
	
	public String getSizeRangeSize(Connection con,String query,TPCSUser ui,int createNew,int dependId)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
			
			String dependQuery="";
			if(dependId>0){
				dependQuery=" sr.Size_Range_Id="+dependId+" and ";
			}
			pst = con.prepareStatement(" SELECT DISTINCT sz.Size_Id,sz.Size_Name from size_range sr inner join Size_Master sz on sz.Size_Id=sr.Size_Id where "+dependQuery+" sz.Size_Name like ?  order by sz.Size_Name   ");
			pst.setString(1, "%"+query+"%");
//			pst.setInt(2, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getInt(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
				obj=null;
			}
		}
		finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getSize(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();

			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			obj=null;
			
			pst = con.prepareStatement(" SELECT DISTINCT sz.Size_Id,sz.Size_Name from Size_Master sz where sz.Size_Name like ?  order by sz.Size_Name   ");
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getInt(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
				obj=null;
			}
		}
		finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getColor(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

			JSONObject obj = new JSONObject();
//
//			if(createNew==1){
//				obj.put("id", "-2");
//				obj.put("name", "+ Add New");
//				data.put(obj);
//			}
			pst = con.prepareStatement(" SELECT DISTINCT c.Color_Id,c.Color_Code from Colors c where c.Color_Code like ? order by c.Color_Code");
			pst.setString(1, "%"+query+"%");
//			pst.setInt(2, ui.getLocationId());
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}
		}
		finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	public String getVariant(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

		//	UserRights ur=new UserRightsDAO().getUserRights(con,SubdocumentId.VARIANT_MASTER, ui.getUserId());
			JSONObject obj = new JSONObject();
			if(createNew==1 ){//&& ur.getAddPermission()==1
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			String matQuery="";
			matQuery="select variant_id,variant_name from variant_master where variant_name like ? and status=1 order by variant_name";
			pst = con.prepareStatement(matQuery);
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	public String getSizeShedule(Connection con,String query,TPCSUser ui,int createNew)throws Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      

		//	UserRights ur=new UserRightsDAO().getUserRights(con,SubdocumentId.VARIANT_MASTER, ui.getUserId());
			JSONObject obj = new JSONObject();
			if(createNew==1 ){//&& ur.getAddPermission()==1
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
			}
			String szeQuery="";
			szeQuery="select size_schedule_id,size_sched_ref from size_schedule where size_sched_ref like ? and is_active=1 order by size_sched_ref";
			pst = con.prepareStatement(szeQuery);
			pst.setString(1, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pst;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return data.toString();
	}
	public String getAllReports(Connection con,String query,int createNew,TPCSUser ui)throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		JSONArray data = new JSONArray();
		try{      
			
			JSONObject obj = new JSONObject();
			if(createNew==1){
				obj.put("id", "-2");
				obj.put("name", "+ Add New");
				data.put(obj);
				obj=null;
			}
			pst = con.prepareStatement(SQLUtil.GET_REPORT_NAMES.replace("tpcs_user_id=?", "tpcs_user_id=? and subdocument_name like ? "));
//			System.out.println("ui.getUserId()==="+ui.getUserId());
			pst.setInt(1, ui.getUserId());
//			System.out.println("query==="+query);
			pst.setString(2, "%"+query+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				obj = new JSONObject();
				obj.put("id", rs.getString(1));
				obj.put("name", rs.getString(2));
				data.put(obj);
				obj=null;
			}

		}finally{
			DatabaseConnection.closeAll(pst,rs);
		}
		return data.toString();
	}
	
}
