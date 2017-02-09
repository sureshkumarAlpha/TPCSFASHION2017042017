package com.alpha.tpcsfashion.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.TaxGroup;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;

public class TaxGroupDAO {

	public List<TaxGroup> getAllTaxGroup(Connection con, TPCSUser ui,PageConfig pc,String strSearhQuery) throws SQLException, IOException {
		// TODO Auto-generated method stub
		List<TaxGroup> objList=new ArrayList<TaxGroup>();
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer buffer=new StringBuffer();
			buffer.append(SQLUtil.GET_ALL_TAX_GROUP);
			if(strSearhQuery!=null && strSearhQuery!="")
			{
				buffer.append("WHERE "+strSearhQuery.toString());
			}
			buffer.append("  order by tg.display_order  desc ");
			cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");
			cstmt.setString(1,buffer.toString());
			cstmt.setInt(2,pc.iStart);
			cstmt.setInt(3,pc.iEnd);

			rs = cstmt.executeQuery();
			while (rs.next()) {
				TaxGroup obj= new TaxGroup();

				obj.setTaxGroupId(rs.getInt(2));
				obj.setTaxGroup(rs.getString(3));
				obj.setTaxId(rs.getInt(4));
				obj.setTaxPercent(rs.getFloat(5));
				obj.setTaxName(rs.getString(6));
				obj.setStatus(rs.getInt(7));
				objList.add(obj);
				obj=null;
			}
			
			buffer=null;
		}
		finally
		{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return objList;
	}
	public List<TaxGroup> getAllTax(Connection con,TPCSUser ui)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs = null;   
		List<TaxGroup> objList=new ArrayList<TaxGroup>();
		try{      

			pstmt = con.prepareStatement(SQLUtil.GET_ED_TAX_IF_APPLICABLES);  
			//pstmt.setInt(1, ui.getLocationId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				TaxGroup item = new TaxGroup();
				item.setTaxId(rs.getInt(1));
				item.setTaxName(rs.getString(2));
				objList.add(item);
				item=null;
			}

			pstmt = con.prepareStatement(SQLUtil.GET_VAT_TAX_IF_APPLICABLES);
			//pstmt.setInt(1, ui.getLocationId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				TaxGroup item = new TaxGroup();
				item.setTaxId(rs.getInt(1));
				item.setTaxName(rs.getString(2));
				objList.add(item);
				item=null;
			}

			pstmt = con.prepareStatement(SQLUtil.GET_CST_TAX_IF_APPLICABLES);
			//pstmt.setInt(1, ui.getLocationId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				TaxGroup item = new TaxGroup();
				item.setTaxId(rs.getInt(1));
				item.setTaxName(rs.getString(2));
				objList.add(item);
				item=null;
			}

			pstmt = con.prepareStatement(SQLUtil.GET_GST_TAX_IF_APPLICABLES);
		//	pstmt.setInt(1, ui.getLocationId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				TaxGroup item = new TaxGroup();
				item.setTaxId(rs.getInt(1));
				item.setTaxName(rs.getString(2));
				objList.add(item);
				item=null;
			}

			pstmt = con.prepareStatement(SQLUtil.GET_SERVICE_TAX_IF_APPLICABLES);  
			//pstmt.setInt(1, ui.getLocationId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				TaxGroup item = new TaxGroup();
				item.setTaxId(rs.getInt(1));
				item.setTaxName(rs.getString(2));
				objList.add(item);
				item=null;
			}

		}
		finally{     
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return objList;
	}

	public int getPageCount(Connection con, TPCSUser ui, String searchCriteria) throws SQLException {
		// TODO Auto-generated method stub
		int pageCount=0;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer query=new StringBuffer(SQLUtil.GET_TAX_GROUP_COUNT);

			if(searchCriteria!=null && !searchCriteria.isEmpty())
			{
				query.append("WHERE " +searchCriteria); 	
			}
			cstmt=con.prepareCall(query.toString());

			rs = cstmt.executeQuery();
			while (rs.next()) {
				pageCount=rs.getInt(1);
			}
			query=null;
		}
		finally
		{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return pageCount;
	}


	public int saveTaxGroup(Connection con, TPCSUser ui, TaxGroup tg,String mode,int taxGroupId)throws SQLException,Exception{
		CallableStatement cstmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Statement stmt = null;
		try{


			if(mode.equalsIgnoreCase("n")){

				pstmt = con.prepareStatement(" SELECT isnull(max(tax_group_id)+1,1)  from tax_group");  
				rs = pstmt.executeQuery();
				if(rs.next()){
					taxGroupId=rs.getInt(1);
				}
			}
			else{

				pstmt = con.prepareStatement(SQLUtil.DELETE_TAX_GROUP);
				pstmt.setInt(1, taxGroupId);
				pstmt.executeUpdate();
			}

			stmt = con.createStatement();

			List<TaxGroup> objList=tg.getTaxGroupList();

			if(objList.size()>0){

				for(TaxGroup obj:objList){
					if(obj.getTaxId()!=-1 && obj.getTaxPercent()>0){
						stmt.addBatch(" INSERT INTO tax_group (tax_group_id,tax_group,Tax_id,Tax_percentage,Status) "
								+" VALUES("+taxGroupId+" ,'"+obj.getTaxGroup()+"',"+obj.getTaxId()+","+obj.getTaxPercent()+","+tg.getStatus()+")");
					}
				}
			}

			int id[]=stmt.executeBatch();

			if(id.length==0){

				stmt.addBatch(" INSERT INTO tax_group (tax_group_id,tax_group,Tax_id,Tax_percentage,status) VALUES("+taxGroupId+" ,'"+tg.getTaxGroup()+"',0,0,"+tg.getStatus()+")");

				id=stmt.executeBatch();
			}




			int tmpTaxGroupId=0;
			if(id.length>0){
				tmpTaxGroupId=taxGroupId;
			}
			taxGroupId=tmpTaxGroupId;
			objList=null;
		}
		finally{
			DatabaseConnection.closeAll(cstmt, null);
		}
		return taxGroupId;
	}
	public List<TaxGroup> getTaxGroupInfo(Connection con, TPCSUser ui,int taxGroupId)throws SQLException,Exception {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<TaxGroup> objList=new ArrayList<TaxGroup>();
		try
		{
			pstmt = con.prepareStatement(SQLUtil.GET_TAX_GROUP_INFO);
			pstmt.setInt(1, taxGroupId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TaxGroup obj=new TaxGroup();

				obj.setTaxGroupId(rs.getInt(1));
				obj.setTaxGroup(rs.getString(2));
				obj.setTaxId(rs.getInt(3));
				obj.setTaxPercent(rs.getFloat(4));
//				obj.setTaxSlno(rs.getInt(6));
				obj.setStatus(rs.getInt(6));
				objList.add(obj);
				obj=null;
			}
			return objList;
			
		}
		finally{	
			objList=null;
			DatabaseConnection.closeAll(pstmt, rs);
		}
		
		
	}


	/*public boolean checkTaxGroupExistence(Connection con, TaxGroup objT,TPCSUser ui) throws SQLException{
		PreparedStatement pst=null;
		ResultSet rs=null;
		boolean flag=false;
		try {

			pst=con.prepareStatement(" select distinct count(*) from sales_quotation sq inner join sales_quotation_details sqd on sq.quote_id=sqd.quote_id and"
					+ " sq.company_id=sqd.company_id and sq.location_id=sqd.location_id and sq.year_id=sqd.year_id where sqd.tax_group_id=?");
			pst.setInt(1, objT.getTaxGroupId());

			rs=pst.executeQuery();
			if(rs.next()){
				flag=rs.getInt(1)>0?true:false;//true already exists.
			}

			if(!flag)
			{
				pst=con.prepareStatement(" select distinct count(*) from sales_order so inner join sales_order_details sod on so.so_id=sod.so_id and"
						+ " so.company_id=sod.company_id and so.location_id=sod.location_id and so.year_id=sod.year_id where sod.tax_group_id=?");
				pst.setInt(1, objT.getTaxGroupId());

				rs=pst.executeQuery();
				if(rs.next()){
					flag=rs.getInt(1)>0?true:false;//true already exists.
				}
			}
			if(!flag)
			{

				pst=con.prepareStatement(" select distinct count(*) from sales_invoice si inner join sales_invoice_details sid on si.invoice_id=sid.invoice_id and"
						+ " si.company_id=sid.company_id and si.location_id=sid.location_id and si.year_id=sid.year_id where sid.tax_group_id=?");
				pst.setInt(1, objT.getTaxGroupId());

				rs=pst.executeQuery();
				if(rs.next()){
					flag=rs.getInt(1)>0?true:false;//true already exists.
				}
			}
			if(!flag)
			{
				pst=con.prepareStatement(" select distinct count(*) from po po inner join po_details pod on po.po_id=pod.po_id and"
						+ " po.company_id=pod.company_id and po.location_id=pod.location_id and po.year_id=pod.year_id where pod.tax_group_id=?");
				pst.setInt(1, objT.getTaxGroupId());

				rs=pst.executeQuery();
				if(rs.next()){
					flag=rs.getInt(1)>0?true:false;//true already exists.
				}
			}
			if(!flag)
			{
				pst=con.prepareStatement(" select distinct count(*) from purchase_invoice pi inner join purchase_invoice_details pid on pi.invoice_id=pid.invoice_id and"
						+ " pi.company_id=pid.company_id and pi.location_id=pid.location_id and pi.year_id=pid.year_id where pid.tax_group_id=?");
				pst.setInt(1, objT.getTaxGroupId());

				rs=pst.executeQuery();
				if(rs.next()){
					flag=rs.getInt(1)>0?true:false;//true already exists.
				}
			}
		} finally{
			DatabaseConnection.closeAll(pst, rs);
		}
		return flag;
	}*/

	public TaxGroup deleteTaxGroup(Connection con, TaxGroup objT) throws SQLException {
		PreparedStatement pstmt=null;   
		try{
			int iCount=0;
			pstmt = con.prepareStatement(SQLUtil.DELETE_TAX_GROUP);
			pstmt.setInt(1, objT.getTaxGroupId());
			iCount = pstmt.executeUpdate();

			if(iCount>0){
				objT.setDeleted(true);
			}
		}

		finally{          

			DatabaseConnection.closeAll(pstmt,null);
		}
		return objT;
	}

	public boolean deleteTaxGroupRow(Connection con, int taxGroupId,int taxId) throws SQLException {
		PreparedStatement pstmt=null;   
		boolean isDeleted=false;
		try{
			int iCount=0;
			pstmt = con.prepareStatement(SQLUtil.DELETE_TAX_GROUP_ROW);
			pstmt.setInt(1, taxGroupId);
			pstmt.setInt(2, taxId);
			iCount = pstmt.executeUpdate();

			if(iCount>0){
				isDeleted=true;
			}
		}

		finally{          
			DatabaseConnection.closeAll(pstmt,null);
		}
		return isDeleted;
	}
	public boolean bulkActiveTaxgroup(Connection con, String[] Taxgroupid,String action_mode) throws SQLException {
		// TODO Auto-generated method stub
	     Statement pstmt=null;
		boolean isBulkAction=false;
		try
		{
			for(int i=0;i<Taxgroupid.length;i++)
			{
				if(action_mode.equals("1"))
				{
					pstmt = con.createStatement();
					pstmt.addBatch("update tax_group set Status=1 where tax_group_id="+Taxgroupid[i]);
					int count[]=pstmt.executeBatch();
					
					isBulkAction=count.length>0?true:false;
				}
				else
				{
					pstmt = con.createStatement();
					pstmt.addBatch("update tax_group set Status=0 where tax_group_id="+Taxgroupid[i]);
					int count[]=pstmt.executeBatch();
					
					isBulkAction=count.length>0?true:false;
				}
			}
	}

	finally
	{
		DatabaseConnection.closeAll(pstmt, null);
	}
	return isBulkAction;
	}	
	
}
