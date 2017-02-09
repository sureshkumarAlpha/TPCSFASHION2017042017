package com.alpha.tpcsfashion.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.BOM;
import com.alpha.tpcsfashion.beans.BOMDetail;
import com.alpha.tpcsfashion.beans.BOMHeader;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.Season;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.model.DynamicFieldManager;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.Validator;


public class BOMDAO {
	

	public int getPageCount(Connection con, TPCSUser ui, String searchCriteria) throws SQLException {
		// TODO Auto-generated method stub
		int pageCount=0;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer query=new StringBuffer(SQLUtil.BOM_PAGE_COUNT);
			
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
	
	
	
	
	
	 public String getBOMDetailsgrid(Connection con, TPCSUser userInfo,int bomId) throws SQLException, ParseException {
			PreparedStatement pst=null;
			ResultSet rs=null;
		    ResultSet rsGetOrders= null;
		    CallableStatement cstmt = null;
			 ResultSet reGetOrderListDynamic = null;
			StringBuffer strBuffer = new StringBuffer(); 
			List<BOMDetail> bomDetList=new ArrayList<BOMDetail>();
			  PreparedStatement pstmtGetOrderListDynamic=null;
			  Map<String,String> detMap=new LinkedHashMap<String ,String>();
			try{
				
				  DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
				  List<DynamicFields> dynamicFieldsListBOMDetails = dynamicFieldsDAO.getDynamicFields(con,14, 30);
				  
				  
				  DynamicFieldManager dynamicFieldManager = new DynamicFieldManager();
				  DynamicFields objDynDetFieldInfo = dynamicFieldManager.getDynamicFieldsInfo(14, 30,userInfo);
				  
				pst=con.prepareStatement(SQLUtil.GET_BOM_DETAIL_LIST);
				pst.setInt(1,bomId);
				rs=pst.executeQuery();
			
				
				 pstmtGetOrderListDynamic = con.prepareStatement(SQLUtil.SELECT_BOM_DETAIL_DYNAMIC);
				  pstmtGetOrderListDynamic.setInt(1, bomId);
				  reGetOrderListDynamic = pstmtGetOrderListDynamic.executeQuery();
				while(rs.next()){
					
					BOMDetail det=new BOMDetail();
					det.setBomId(rs.getInt(1));
					det.setBomDetailId(rs.getInt(2));
					det.setMaterialId(rs.getInt(3));
					det.setMaterial(rs.getString(4));
					det.setVariantId(rs.getInt(5));
					det.setVariant(rs.getString(6));
					det.setUOM(rs.getString(7));
					det.setUomId(rs.getInt(8));
					det.setOperationId(rs.getInt(9));
					det.setOperation(rs.getString(10));
					det.setRequiredQty(rs.getString(11));
					det.setWastagePer(rs.getInt(12));
					det.setCostingPer(rs.getInt(13));
					det.setPurchasePer(rs.getInt(14));
					det.setIssuePer(rs.getInt(15));
					det.setSupplierId(rs.getInt(16));
					det.setSupplier(rs.getString(17));
					det.setUsageArea(rs.getString(18));
					det.setComponentId(rs.getInt(19));
					det.setComponent(rs.getString(20)!=null&&!rs.getString(20).isEmpty()?rs.getString(20):"");
					det.setSizeSheduleId(rs.getInt(21));
					det.setSizeShedule(rs.getString(22));
					det.setPrice(rs.getFloat(23));
					det.setValue(rs.getFloat(24));
					det.setGroupId(rs.getInt(25));
					det.setGroupName(rs.getString(26));
					
					
					
					 while(reGetOrderListDynamic.next()){
						  for(DynamicFields dynamicFields : dynamicFieldsListBOMDetails) {
							  String fieldValue="";
							  if(!dynamicFields.isFixed() ) {

								  fieldValue = reGetOrderListDynamic.getString(dynamicFields.getDbFieldName())==null?"":reGetOrderListDynamic.getString(dynamicFields.getDbFieldName());
								  if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {
									  if(!fieldValue.equals(""))
									  {
										  SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
										  SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MM-yyyy");
										  fieldValue = outFormatter.format(inFormatter.parse(fieldValue)).toString();
										  if(fieldValue.equals("01-01-1900"))
											  fieldValue="";
										  
											inFormatter=null;
							    			outFormatter=null;
									  }
								  }
								  
								  
								  detMap.put(dynamicFields.getDbFieldName()+rs.getInt(2), fieldValue!=null&&!fieldValue.isEmpty()?fieldValue:"");
							  }
						  }
						  break;
					  }
					
					det.setBomDetDetMap(detMap);
					bomDetList.add(det);
					 
					det=null;
					
				}
					 
					
					dynamicFieldsListBOMDetails=null;
				dynamicFieldManager=null;
				detMap=null;
				strBuffer.append("<tr id=\"add_row_"+bomId+"\" >");
				strBuffer.append("<td colspan=\"13\">");
					strBuffer.append("<div class=\"\">");

					strBuffer.append("<table width=\"100%\" class=\"table table-bordered table-condensed table-hover\"  id =\"task-table\">");
					strBuffer.append("<thead>");
					strBuffer.append("<tr class=\"header\">");
					strBuffer.append("<th >Component</th>");
					  if(objDynDetFieldInfo.getDbFieldMap().containsKey("heading")){
						  strBuffer.append("<th >"+objDynDetFieldInfo.getDbFieldMap().get("heading")+"</th>");
						  }
					strBuffer.append("<th >Group</th>");
					strBuffer.append("<th >Material</th>");
					strBuffer.append("<th >Color</th>");
					strBuffer.append("<th >Uom</th>");
					strBuffer.append("<th >Operation</th>");
					
				    if(objDynDetFieldInfo.getDbFieldMap().containsKey("part")){
					  strBuffer.append("<th >"+objDynDetFieldInfo.getDbFieldMap().get("part")+"</th>");
					  }
					strBuffer.append("<th class=\"text-left\">Size Shedule</th>");
	
					 strBuffer.append("<th >Sample Norms</th>");
					 strBuffer.append("<th >Wastage %</th>");
					 strBuffer.append("<th >Costing %</th>");
					 strBuffer.append("<th >Issue %</th>");
					 strBuffer.append("<th >Supplier</th>");
					 strBuffer.append("<th >Purchase %</th>");

					strBuffer.append("</tr>");
					strBuffer.append(" </thead>");
					strBuffer.append("<tbody>");


					
					for(BOMDetail det:bomDetList){ 
						
						strBuffer.append("<tr> " );
						strBuffer.append("<td>"+det.getComponent()+"</td>");
						if(objDynDetFieldInfo.getDbFieldMap().containsKey("heading")){
							strBuffer.append("<td>"+det.getBomDetDetMap().get("heading"+det.getBomDetailId())+"</td>");
						}
						
						strBuffer.append("<td>"+det.getGroupName()+"</td>");
						strBuffer.append("<td >"+det.getMaterial()+"</td>");
						strBuffer.append("<td >"+det.getVariant()+"</td>");
						strBuffer.append("<td>"+det.getUOM()+"</td>");
						strBuffer.append("<td>"+det.getOperation()+"</td>");
						if(objDynDetFieldInfo.getDbFieldMap().containsKey("part")){
							strBuffer.append("<td>"+det.getBomDetDetMap().get("part"+det.getBomDetailId())+"</td>");
						}
						strBuffer.append("<td>"+det.getSizeShedule()+"</td>");
						strBuffer.append("<td>"+det.getRequiredQty()+"</td>");
						strBuffer.append("<td>"+det.getWastagePer()+"</td>");
						strBuffer.append("<td>"+det.getCostingPer()+"</td>");
						strBuffer.append("<td>"+det.getIssuePer()+"</td>");
						strBuffer.append("<td>"+det.getSupplier()+"</td>");
						strBuffer.append("<td>"+det.getPurchasePer()+"</td>");
						
						
						strBuffer.append("</tr>");
					}
						strBuffer.append("</tbody>");
						
						

					strBuffer.append("</table>");
					strBuffer.append("</div>");
					strBuffer.append("</td>");
					strBuffer.append("</tr>");
				
			}finally{
				DatabaseConnection.closeAll(pst,rs);
				DatabaseConnection.closeAll(cstmt,rs);
				DatabaseConnection.closeAll(pstmtGetOrderListDynamic,reGetOrderListDynamic);
			}
			return strBuffer.toString();
			
		}
	 
	public List<Map<String,String>>  getAllBomColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,String strSearchCriteria,List<String> orderBy,int iScreenId,String bomAttachPath)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
		CallableStatement cstmt=null;
	    ResultSet rs = null; 
	    ResultSet rsAttach = null; 
	    PreparedStatement pstmtGetBOMDetailInfoDynamic =null;
	    ResultSet rsGetBOMDetailInfoDynamic=null;
	    PreparedStatement pstmt=null;
	    List<Map<String,String>> bomList=new ArrayList<Map<String,String>>();	
	    
	    String strbuffer="";
	    try{
	    	
	    	DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();  
	    	   List<DynamicFields> dynamicFieldsListOrderHeader = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 29);
		        List<DynamicFields> dynamicFieldsListOrderDetail = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 30);
	        
	        Map<String,String> colPreMap=getColPreMap(con,SubdocumentId.BOM_PRODUCTION);
	    	StringBuffer query = new StringBuffer(SQLUtil.GET_PRODUCTION_BOM);
	 
	 
	    	if(strSearchCriteria!=null && !strSearchCriteria.isEmpty()){
	        	 query.append("WHERE "+strSearchCriteria);
	         }
	    	
	    	query.append(" Group by b.bom_no,b.bom_id,b.product_id,prod.item_Name,isnull(b.version_no,0),b.customer_id,p.party_name,pb.bom_no, "+
             " b.remarks,convert(nvarchar,b.bom_date,105),b.Status,isnull(b.lock_status,0)  ");
	    	
	    	query.append(" order by b.bom_id desc  ");
	    	

	    	
	    	
	    //	System.out.println("query.toString() :"+query.toString());
	         cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
	         cstmt.setString(1,query.toString());
	         cstmt.setInt(2,pc.iStart);
	         cstmt.setInt(3,pc.iEnd);
	         rs=cstmt.executeQuery();
	        while(rs.next()){
	    	    Map<String,String> map = new HashMap<String,String>();
		    	  
	    	    map.put("BOM.BOMId", rs.getString("bom_id"));
	    	    map.put("BOM.BOMNo", rs.getString("bom_no"));
	    	    map.put("count", rs.getString("count"));
		        map.put(colPreMap.get("product_id"), rs.getString("product"));
		        map.put(colPreMap.get("version_no"), rs.getString("version_no"));
		        map.put(colPreMap.get("customer_id"), rs.getString("customer"));
		        map.put(colPreMap.get("parent_bom_id"), rs.getString("parent_bom_no"));
		        map.put(colPreMap.get("remarks"), rs.getString("remarks"));
		        map.put(colPreMap.get("bom_date"), rs.getString("bom_date"));
		        map.put(colPreMap.get("Status"), rs.getString("Status"));
//		        map.put(colPreMap.get("material_id"), rs.getString("mat_name"));
//		        map.put(colPreMap.get("variant_id"), rs.getString("Variant_Name"));
//		        map.put(colPreMap.get("UOM"), rs.getString("UOM"));
//		        map.put(colPreMap.get("operation_id"), rs.getString("operation"));
		        map.put(colPreMap.get("required_qty"), rs.getString("required_qty"));
//		        map.put(colPreMap.get("wastage_per"), rs.getString("wastage_per"));
//		        map.put(colPreMap.get("costing_per"), rs.getString("costing_per"));
//		        map.put(colPreMap.get("purchase_per"), rs.getString("purchase_per"));
//		        map.put(colPreMap.get("issue_per"), rs.getString("issue_per"));
//		        map.put(colPreMap.get("supplier_id"), rs.getString("supplier"));
//		        map.put(colPreMap.get("usage_area"), rs.getString("usage_area"));
//		        map.put("BOM.BOMDetId", String.valueOf(rs.getString("bom_detail_id")));
//		        map.put(colPreMap.get("component_id"), rs.getString("component_name"));
//		        map.put(colPreMap.get("group_id"), rs.getString("group_name"));
		        map.put("BOM.LockStatus", rs.getString("lock_status"));
		        map.put("BOM.AmendStatus", rs.getString("version_no"));
		        
		        //Start - Added for dynamic fields
		        pstmtGetBOMDetailInfoDynamic = con.prepareStatement(SQLUtil.SELECT_BOM_DETAIL_DYNAMIC_BY_ID);
		        pstmtGetBOMDetailInfoDynamic.setInt(1, Validator.convertToInteger(rs.getString("bom_id")));  
		        rsGetBOMDetailInfoDynamic = pstmtGetBOMDetailInfoDynamic.executeQuery();
		        if(rsGetBOMDetailInfoDynamic.next()){
				      for(DynamicFields dynamicFields : dynamicFieldsListOrderHeader) {
				    	  if(!dynamicFields.isFixed()){
				    		  String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
				    		  String fieldValue = rsGetBOMDetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetBOMDetailInfoDynamic.getString(dynamicFields.getDbFieldName());
				    		  if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {
				    			  if(!fieldValue.equals(""))
				    			  {
			        				SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
			        				SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MM-yyyy");
					    			fieldValue = outFormatter.format(inFormatter.parse(fieldValue)).toString();
					    			if(fieldValue.equals("01-01-1900"))
					    				fieldValue="";
					    			inFormatter=null;
					    			outFormatter=null;
				    			  }
			        			}
				        	//dynamicFields.setFieldValue(fieldValue);
				    		  
				    		  map.put(labelName,fieldValue);
				    		  
				    	  }
					  }
					 for(DynamicFields dynamicFields : dynamicFieldsListOrderDetail) {
						  if(!dynamicFields.isFixed()){
							  String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							  String fieldValue = rsGetBOMDetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetBOMDetailInfoDynamic.getString(dynamicFields.getDbFieldName());
					    	//  dynamicFields.setFieldValue(fieldValue);  
							  if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {
								  if(!fieldValue.equals(""))
				    			  {
			        				SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
			        				SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MM-yyyy");
					    			fieldValue = outFormatter.format(inFormatter.parse(fieldValue)).toString();
					    			if(fieldValue.equals("01-01-1900"))
					    				fieldValue="";
					    			
					    			inFormatter=null;
					    			outFormatter=null;
				    			  }
			        			}
							  map.put(labelName,fieldValue);
						  }
					 }
				 }
		        
		        //End - Added for dynamic fields
		        
		   

		    	  bomList.add(map);
		    	  map=null;
	      }
	        dynamicFieldsDAO=null;
	        colPreMap=null;
	        query=null;
	        dynamicFieldsListOrderHeader=null;
		      dynamicFieldsListOrderDetail=null;
		      query=null;
	    }
	    finally{
		      if(cstmt!=null)
		    	  cstmt.close();
		    
		      ArrayOfPreparedStatement = new PreparedStatement[3];
		      ArrayOfPreparedStatement[0]=pstmtGetBOMDetailInfoDynamic;
		      ArrayOfResultSet = new ResultSet[3];      
		      ArrayOfResultSet[0] = rs;
		      ArrayOfResultSet[1] = rsAttach;
		      ArrayOfResultSet[2] = rsGetBOMDetailInfoDynamic;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }

	    return bomList;
	  }
	public boolean deleteBOM(Connection con, int bomId,TPCSUser userInfo) throws SQLException {
		Statement cstmt = null;
		boolean isDeleted = false;
		boolean flag=false;
		ResultSet rs=null;
		try { 
			

				cstmt = con.createStatement();

				cstmt.addBatch("DELETE FROM bom_details_dynamic  WHERE bom_id="+bomId);
				cstmt.addBatch("DELETE FROM bom_details  WHERE bom_id="+bomId);
				cstmt.addBatch("DELETE FROM bom_dynamic  WHERE bom_id="+bomId);
				cstmt.addBatch("DELETE FROM bom  WHERE bom_id="+bomId);

				int count[]=cstmt.executeBatch();
				isDeleted=count.length>0?true:false;
		} finally {
	
			DatabaseConnection.closeAll(cstmt,rs);
		}
		return isDeleted;
	}
public Map<String,String> getColPreMap(Connection con,int documentId){
		
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		Map<String,String> colPreMap=new LinkedHashMap<String, String>();
		try {
			
			 String colPreQuery="select dbfieldname ,column_display_name from column_preferences where subdocument_id="+documentId;
		        
		        pstmt=con.prepareStatement(colPreQuery);
		        rs=pstmt.executeQuery();//.executeQuery();
		        while(rs.next()){
		        	
		        
		        		colPreMap.put(rs.getString(1),rs.getString(2));
		       
		        	
		        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colPreMap;
	}
	
	
	
public boolean checkBOMExistense(Connection con, TPCSUser userInfo,BOM objB) throws SQLException {
	PreparedStatement pst=null;
	ResultSet rs=null;
	boolean isExists=true;
	try{
		
		String srchQuery="";
		if(objB.getHeader().getBomId()>0){
			srchQuery=" bom_id<>"+objB.getHeader().getBomId()+" and ";
		}
		String query=" select count(*) from bom where "+srchQuery+" bom_no=? ";
		pst=con.prepareStatement(query);
		pst.setString(1, objB.getHeader().getBomNo());
		rs=pst.executeQuery();
		if(rs.next()){
			isExists=rs.getInt(1)>0?true:false;
		}
	}
	finally{
		DatabaseConnection.closeAll(pst,rs);
	}
	return isExists;
	
}
	
public BOM saveBOM(Connection con, TPCSUser userInfo,BOM objB,
		Map<String,String> map,Map<String,String> mapDyn,String editedMode,FileImport objBean) throws Exception {
	// TODO Auto-generated method stub
	CallableStatement cstmt = null;
	ResultSet rs=null;
	Statement stmt = null;
	PreparedStatement pst = null;
	PreparedStatement pstmtAttach=null;  
	int tr_id = 0; 

	try {
		
		BOMHeader header = objB.getHeader();
		BOMDetail detail = objB.getDetail();

		//if(!editedMode.equals("e")){
			cstmt = con.prepareCall("{call pr_insert_bom(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			SQLXML sqlxml = con.createSQLXML();
			cstmt.setInt(1,userInfo.getUserId());
			cstmt.setString(2, objB.getHeaderMode());
			cstmt.setString(3, objB.getDetMode()); 
			cstmt.setInt(4, header.getBomId());
			cstmt.setInt(5, detail.getBomDetailId());
			cstmt.setString(6, header.getBomNo());
			if(header.getBomDate()!=null && !header.getBomDate().isEmpty())
			{
				cstmt.setString(7, header.getBomDate());
			}
			else
			{
				cstmt.setNull(7, Types.DATE);
				}
			cstmt.setInt(8, header.getCustomerId());
			cstmt.setInt(9, header.getParentBomId());
			cstmt.setInt(10, header.getProductId());
			cstmt.setString(11, header.getRemarks());
			cstmt.setInt(12, detail.getMaterialId());
			cstmt.setInt(13, detail.getVariantId());
			cstmt.setString(14, detail.getUOM());
			cstmt.setInt(15, detail.getOperationId());
			cstmt.setString(16, detail.getRequiredQty());
			cstmt.setInt(17, detail.getWastagePer());
			cstmt.setInt(18,detail.getCostingPer());
			cstmt.setInt(19,detail.getPurchasePer());
			cstmt.setInt(20,detail.getIssuePer());
			cstmt.setInt(21,detail.getSupplierId());
			cstmt.setString(22, detail.getUsageArea());
			cstmt.setInt(23, detail.getComponentId());
			cstmt.setInt(24, detail.getSizeSheduleId());
			sqlxml.setString(objB.getSqlxmlDynamicFields());
		//	System.out.println("saEnq.getSqlxmlDynamicFields() :"+objB.getSqlxmlDynamicFields());
			cstmt.setSQLXML("XmlData",sqlxml);

			
		/*	System.out.println("1 :"+userInfo.getUserId());
			System.out.println("2 :"+objB.getHeaderMode());
			System.out.println("3 :"+objB.getDetMode());
			System.out.println("4 :"+header.getBomId());
			System.out.println("5 :"+detail.getBomDetailId());
			System.out.println("6 :"+header.getBomNo());
			System.out.println("7 :"+header.getBomDate());
			System.out.println("8 :"+header.getCustomerId());
			System.out.println("9 :"+header.getParentBomId());
			System.out.println("10 :"+header.getProductId());
			System.out.println("11 :"+header.getRemarks());
			System.out.println("12 :"+detail.getMaterialId());
			System.out.println("13 :"+detail.getVariantId());
			System.out.println("14 :"+detail.getUOM());
			System.out.println("15 :"+detail.getOperationId());
			System.out.println("16 :"+detail.getRequiredQty());
			System.out.println("17 :"+detail.getWastagePer());
			System.out.println("18 :"+detail.getCostingPer());
			System.out.println("19 :"+detail.getPurchasePer());
			System.out.println("20 :"+detail.getIssuePer());
			System.out.println("21 :"+detail.getSupplierId());
			System.out.println("22 :"+detail.getUsageArea());
			System.out.println("23 :"+ detail.getComponentId());
			System.out.println("24 :"+objB.getSqlxmlDynamicFields());*/
			
			
			rs=cstmt.executeQuery();
			if(rs.next()){
				tr_id = rs.getInt(1);
				
				objB.getHeader().setBomId(rs.getInt(1));
				objB.getDetail().setBomDetailId(rs.getInt(2));
			}
			
			
			if(editedMode.equals("e") || editedMode.equals("a")){
			stmt = con.createStatement();
			List<Integer> detaIdList=new ArrayList<Integer>();
			Iterator it = map.entrySet().iterator();
			Iterator itDyn = mapDyn.entrySet().iterator();

			while(it.hasNext()){
				Map.Entry mCol= (Map.Entry)it.next();
				String strArray[] = ((String) mCol.getKey()).split("_S_");	
				if(strArray.length>1){
				
						stmt.addBatch("UPDATE bom_details SET "+strArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE bom_detail_id ="+strArray[1]);  
					
				

					detaIdList.add(Integer.parseInt(strArray[1]));
				}
				tr_id=header.getBomId();
				
			}

			while(itDyn.hasNext()){
				Map.Entry mCol= (Map.Entry)itDyn.next();
				String strDynArray[] = ((String) mCol.getKey()).split("_S_");


				if(strDynArray.length>1){
					//  if(!strDynArray[0].equals("required_date")){
					stmt.addBatch("UPDATE bom_details_dynamic SET "+strDynArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE bom_detail_id ="+strDynArray[1]);  
					// }
					/*  else{
				    		  stmt.addBatch("UPDATE sales_quotation_details SET "+strArray[0]+"=convert(datetime,'"+mCol.getValue()+"',105)   WHERE quote_det_id ="+strArray[1]);
				    	  }
					 */
				}
				tr_id=header.getBomId();
			}
			stmt.executeBatch();
			
			
			
			//---------------------------------
			
			if(editedMode.equals("e")){
				
				updateAmendment(con,userInfo,header.getBomId(),detail.getBomDetailId());
				
			}
			detaIdList=null;
		}
			//For Attachments
			pstmtAttach = con.prepareStatement(SQLUtil.INSERT_BOM_ATTACHMENT);
			if(!editedMode.equals("e")){
				pstmtAttach.setInt(1,tr_id);
			}else{
				pstmtAttach.setInt(1,header.getBomId());		
			}



			List list= objBean.getFileItems();
			String parentFolder="bom/";
			String folderPath=objBean.getFolderPath().concat(parentFolder);

			File file = new File( folderPath) ;
			if(!file.exists()){
				file.mkdirs();
			}

			FileItem item=null;
			Iterator i = list.iterator();
			String filename=null;
			String fileActualName=null;
			String strSuffix = null,strPrefix=null;
			int idx=1;
			while ( i.hasNext () ){ 
				item = (FileItem)i.next();

				if ( !item.isFormField () ){
					String fieldName=item.getFieldName();
					String source = item.getName();
					if(source.lastIndexOf(".")!=-1){
						fieldName=fieldName.substring(fieldName.length()-1);
						//int sharable=Validator.convertToInteger(fieldName);
						
						if( source.lastIndexOf("\\") >= 0 ){
	
							strSuffix = source.substring(source.lastIndexOf(".")+1,source.length());
							String text=source.substring(source.lastIndexOf("\\") + 1,source.length());
							fileActualName=text.substring(0,text.lastIndexOf("."));
						}
						else{
							filename = source.substring(source.lastIndexOf("\\") + 1,source.length());
							fileActualName=filename.substring(0, filename.lastIndexOf("."));
							strSuffix = filename.substring(filename.lastIndexOf(".")+1,filename.length());
	
						}
						/*strPrefix="mat_"+fileActualName+"_"+matId+"_"+idx+".";*/
						if(!editedMode.equals("e")){
							strPrefix=fileActualName+"_"+tr_id+"_"+idx+".";
						}else{
							strPrefix=fileActualName+"_"+header.getBomId()+"_"+idx+".";
						}
	
						file=new File(folderPath+strPrefix+strSuffix);
						item.write(file);
						idx++;
//						String [] arFolder=folderPath.split("/");
//						String parentFolder=arFolder[arFolder.length-1]+"/";
						pstmtAttach.setString(2, strSuffix);
	
						pstmtAttach.setString(3, parentFolder+strPrefix+strSuffix);
	
						pstmtAttach.addBatch();
					}
				}
			}
			pstmtAttach.executeBatch();
			file=null;

			
			pst=con.prepareStatement("  delete from bom_size_schedule where bom_id="+objB.getHeader().getBomId()+" "
					                + " insert  into bom_size_schedule(bom_id,size_schedule_id) "
					                + "  select distinct bom_id,isnull(size_schedule_id,0) size_schedule_id from bom_details where bom_id="+objB.getHeader().getBomId());
			pst.executeUpdate();

	} finally {
		DatabaseConnection.closeAll(cstmt, null);
	}
	return objB;

}


public int updateAmendment(Connection con,TPCSUser ui,int bomId,int bomDetId)throws SQLException,Exception{
	CallableStatement cst=null;
	int cnt=0;
	try{
		
		cst=con.prepareCall("{ call pr_update_bom_amend (?,?) }");
		cst.setInt(1,bomId);
		cst.setInt(2,bomDetId);
		 cnt=cst.executeUpdate();
	}
	finally{
		DatabaseConnection.closeAll(cst, null);
	}
	return cnt;
}


public BOMHeader getTotalTableWidth(Connection con,TPCSUser userInfo,BOMHeader bomHeader) throws SQLException,Exception {
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try{ 
		
		StringBuffer query = new StringBuffer(" select (isnull(SUM(columnwidth),0)*12)+130+90+20 columnwidth from DynamicFields df "
                + " inner join DynamicRefScreenFields drf on df.DynamicFieldId=drf.DynamicFieldId where drf.Active=1  and df.TableId=?");
		pstmt=con.prepareStatement(query.toString());
		pstmt.setInt(1,30);
		rs=pstmt.executeQuery(); 
		if(rs.next()){
			bomHeader.setDetTableWidth((rs.getInt(1)));
		}
		 
		query=null;
	  	
	} finally {
		DatabaseConnection.closeAll(pstmt,rs);
	}
	return bomHeader;
}



public BOMHeader getBOMInfo(Connection con,TPCSUser userInfo,BOMHeader bomHeader) throws SQLException,Exception {
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try{ 
		
		
		bomHeader=getTotalTableWidth(con,userInfo,bomHeader);
		 
	  	
	} finally {
		DatabaseConnection.closeAll(pstmt,rs);
	}
	return bomHeader;
}


public BOM getBOMHeader(Connection con, TPCSUser userInfo,
		int bomId,int iScreenId) throws SQLException,Exception {
	PreparedStatement ArrayOfPreparedStatement[] = null;
	ResultSet ArrayOfResultSet[] = null;
	BOM objBH=new BOM();
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	ResultSet rsGetInfoDynamic = null;
	PreparedStatement pstmtGetInfoDynamic = null;
	PreparedStatement pstmt1=null;
	//Added for Dynamic Fields
	DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
	List<DynamicFields> dynamicFieldsListBOMHeader = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 29);
	try{ 

		BOMHeader seH=new BOMHeader();
		seH=getTotalTableWidth(con,userInfo,seH);
		
		
		StringBuffer query = new StringBuffer(SQLUtil.GET_BOM_HEADER);
		pstmt=con.prepareStatement(query.toString());
		pstmt.setInt(1,bomId);
		rs=pstmt.executeQuery(); 

		
	//	System.out.println(query.toString()+bomId);
		pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_BOM_HEADER_DYNAMIC);
		pstmtGetInfoDynamic.setInt(1,bomId);
		rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

		
		if(rs.next())
		{
			
			
			seH.setBomId(rs.getInt(1));
			seH.setBomNo(rs.getString(2));
			seH.setBomDate(rs.getString(3));
			seH.setProductId(rs.getInt(4));
			seH.setProduct(rs.getString(5));
			seH.setCustomerId(rs.getInt(6));
			seH.setCustomer(rs.getString(7));
			seH.setParentBomId(rs.getInt(8));
			seH.setParentBomNo(rs.getString(9));
			seH.setRemarks(rs.getString(10));
			
			
				
			if(rsGetInfoDynamic.next()){
				for(DynamicFields dynamicFields : dynamicFieldsListBOMHeader) {
					if(!dynamicFields.isFixed()) {
						String fieldValue = rsGetInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetInfoDynamic.getString(dynamicFields.getDbFieldName());
						if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {

							if(!fieldValue.equals(""))
							{
								SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
								SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MM-yyyy");
								fieldValue = outFormatter.format(inFormatter.parse(fieldValue)).toString();
								if(fieldValue.equals("01-01-1900"))
									fieldValue="";
								
								inFormatter=null;
								outFormatter=null;
							}
						}
						dynamicFields.setFieldValue(fieldValue);
					}
				}
			}

		

		} 
		objBH.setBomDynList(dynamicFieldsListBOMHeader);

	      
		
		 pstmt = con.prepareStatement("select top 1 url from bom_url where bom_id=? order by auto_id");
		 pstmt.setInt(1, bomId);
		 rs = pstmt.executeQuery();
		 if(rs.next()){
			 seH.setPrimaryImageUrl(rs.getString(1));
		 }
		 
	  	objBH.setHeader(seH);

	  	
	  	dynamicFieldsDAO=null;
	  	seH=null;
	  	dynamicFieldsListBOMHeader=null;
	  	query=null;
		// TODO Auto-generated method stub
	} finally {
		ArrayOfPreparedStatement = new PreparedStatement[3];
		ArrayOfPreparedStatement[0] = pstmt;
		ArrayOfPreparedStatement[1] = pstmt1;
		ArrayOfPreparedStatement[2] = pstmtGetInfoDynamic;
		ArrayOfResultSet = new ResultSet[3];
		ArrayOfResultSet[0] = rs;
		ArrayOfResultSet[1] = rs1;
		ArrayOfResultSet[2] = rsGetInfoDynamic;
		DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	}
	return objBH;
}




public BOM getBOMPrintInfo(Connection con, TPCSUser userInfo,int bomId,BOM objB) throws SQLException,Exception {
	PreparedStatement ArrayOfPreparedStatement[] = null;
	ResultSet ArrayOfResultSet[] = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	ResultSet rsGetInfoDynamic = null;
	PreparedStatement pstmtGetInfoDynamic = null;
	PreparedStatement pstmt1=null;
	//Added for Dynamic Fields
	DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
	Map<String,String> dynHeadeDataMap=new LinkedHashMap<String ,String>();
	List<DynamicFields> dynamicFieldsListBOMHeader = dynamicFieldsDAO.getDynamicFields(con,14, 29);
	try{ 

		StringBuffer query = new StringBuffer(SQLUtil.GET_BOM_HEADER_IN_PRINT);
		pstmt=con.prepareStatement(query.toString());
		pstmt.setInt(1,bomId);
		rs=pstmt.executeQuery(); 

		
	//	System.out.println(query.toString()+bomId);
		pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_BOM_HEADER_DYNAMIC);
		pstmtGetInfoDynamic.setInt(1,bomId);
		rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

		BOMHeader seH=new BOMHeader();
		if(rs.next())
		{
			
			
			seH.setBomId(rs.getInt(1));
			seH.setBomNo(rs.getString(2));
			seH.setCustomerId(rs.getInt(3));
			seH.setCustomer(rs.getString(4));
			seH.setRemarks(rs.getString(5));
			
			
				
			if(rsGetInfoDynamic.next()){
				for(DynamicFields dynamicFields : dynamicFieldsListBOMHeader) {
					if(!dynamicFields.isFixed()) {
						String fieldValue = rsGetInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetInfoDynamic.getString(dynamicFields.getDbFieldName());
						if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {

							if(!fieldValue.equals(""))
							{
								SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
								SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MM-yyyy");
								fieldValue = outFormatter.format(inFormatter.parse(fieldValue)).toString();
								if(fieldValue.equals("01-01-1900"))
									fieldValue="";
								
								inFormatter=null;
								outFormatter=null;
							}
						}
						dynamicFields.setFieldValue(fieldValue);
						dynHeadeDataMap.put(dynamicFields.getDbFieldName(), fieldValue);
					}
				}
			}

		

		} 
		objB.setBomDynList(dynamicFieldsListBOMHeader);
		objB.setDynHeadeDataMap(dynHeadeDataMap);
	      
		objB.setHeader(seH);
       //------------------------------Compnent List
		List<BOMDetail> compList=new ArrayList<BOMDetail>();	
	  	
		pstmt=con.prepareStatement(SQLUtil.GET_BOM_COMPONENT_LIST);
		pstmt.setInt(1,bomId);
		rs=pstmt.executeQuery();
		while(rs.next())
		{
			
			BOMDetail det=new BOMDetail();
			det.setComponent(rs.getString(1));
			det.setMaterial(rs.getString(2));


			compList.add(det);
			det=null;
		} 
		objB.setCompList(compList);
	  	
	  	
	  	
		 //------------------------------Detail List
		List<BOMDetail> detList=new ArrayList<BOMDetail>();	
	  	
		pstmt=con.prepareStatement(SQLUtil.GET_BOM_DETAIL_PRINT_LIST);
		pstmt.setInt(1,bomId);
		rs=pstmt.executeQuery();
		while(rs.next())
		{
			
			BOMDetail det=new BOMDetail();
			det.setHeading(rs.getString(1));
			det.setGroupName(rs.getString(2));
			det.setMaterial(rs.getString(3));
			det.setSupplier(rs.getString(4));
			det.setRequiredQty(rs.getString(5));
			det.setUOM(rs.getString(6));
			det.setPart(rs.getString(7));

			detList.add(det);
			det=null;
		} 
		objB.setBomDetList(detList);
	  	
	  	dynamicFieldsDAO=null;
	  	seH=null;
	  	dynamicFieldsListBOMHeader=null;
	  	query=null;
		// TODO Auto-generated method stub
	} finally {
		ArrayOfPreparedStatement = new PreparedStatement[3];
		ArrayOfPreparedStatement[0] = pstmt;
		ArrayOfPreparedStatement[1] = pstmt1;
		ArrayOfPreparedStatement[2] = pstmtGetInfoDynamic;
		ArrayOfResultSet = new ResultSet[3];
		ArrayOfResultSet[0] = rs;
		ArrayOfResultSet[1] = rs1;
		ArrayOfResultSet[2] = rsGetInfoDynamic;
		DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	}
	return objB;
}



public List<BOM> getAttachFiles(Connection con, int enqId) throws SQLException {
	PreparedStatement pstmt=null;    
	ResultSet rs = null;    
	List<BOM> attachFiles=new ArrayList<BOM>();
	try{
		pstmt = con.prepareStatement(SQLUtil.SELECT_BOM_ATTACHMENT);
		pstmt.setInt(1, enqId);
		rs = pstmt.executeQuery();
		while(rs.next()){
			BOM objUrl=new BOM();
			objUrl.setBomId(rs.getInt(1));
			objUrl.setUrlType(rs.getString(2));
			objUrl.setUrl(rs.getString(3));
			objUrl.setFileName(rs.getString(4));
			attachFiles.add(objUrl);
			
			objUrl=null;
		}
	}finally{     

		DatabaseConnection.closeAll(pstmt,rs);
	}
	return attachFiles;
}

public BOM getFirstAttachFile(Connection con, int bomId) throws SQLException {
	PreparedStatement pstmt=null;    
	ResultSet rs = null;    
	BOM objUrl=new BOM();
	try{
		pstmt = con.prepareStatement(SQLUtil.SELECT_BOM_TOP_ATTACHMENT);
		pstmt.setInt(1, bomId);
		rs = pstmt.executeQuery();
		while(rs.next()){
			objUrl.setBomId(rs.getInt(1));
			objUrl.setUrlType(rs.getString(2));
			objUrl.setUrl(rs.getString(3));
			objUrl.setFileName(rs.getString(4));
		}
	}finally{     

		DatabaseConnection.closeAll(pstmt,rs);
	}
	return objUrl;
}
public BOM getBOMDetailList(Connection con,TPCSUser userInfo, int trId) throws SQLException, Exception {
	// TODO Auto-generated method stub
	BOM objB=new BOM();
	List<BOMDetail> detList=new ArrayList<BOMDetail>();		
	PreparedStatement pstmt=null;
	PreparedStatement pstmtGetInfoDynamic=null;
	ResultSet rs=null;
	ResultSet rsGetInfoDynamic=null;
	//Added for Dynamic Fields
	DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
	List<DynamicFields> dynamicFieldsListBOMDetails = dynamicFieldsDAO.getDynamicFields(con,14, 30);
	try{

		StringBuffer query = new StringBuffer(SQLUtil.GET_BOM_DETAIL_LIST);
		pstmt=con.prepareStatement(query.toString());
		pstmt.setInt(1,trId);
		rs=pstmt.executeQuery();

		
		
//		System.out.println(query.toString()+trId);
		pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_BOM_DETAIL_DYNAMIC);
		pstmtGetInfoDynamic.setInt(1,trId);
		rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

		Map<String,String> detMap=new LinkedHashMap<String ,String>();

		while(rs.next())
		{
			
			BOMDetail det=new BOMDetail();
			det.setBomId(rs.getInt(1));
			det.setBomDetailId(rs.getInt(2));
			det.setMaterialId(rs.getInt(3));
			det.setMaterial(rs.getString(4));
			det.setVariantId(rs.getInt(5));
			det.setVariant(rs.getString(6));
			det.setUOM(rs.getString(7));
			det.setUomId(rs.getInt(8));
			det.setOperationId(rs.getInt(9));
			det.setOperation(rs.getString(10));
			det.setRequiredQty(rs.getString(11));
			det.setWastagePer(rs.getInt(12));
			det.setCostingPer(rs.getInt(13));
			det.setPurchasePer(rs.getInt(14));
			det.setIssuePer(rs.getInt(15));
			det.setSupplierId(rs.getInt(16));
			det.setSupplier(rs.getString(17));
			det.setUsageArea(rs.getString(18));
			det.setComponentId(rs.getInt(19));
			det.setComponent(rs.getString(20));
			det.setSizeSheduleId(rs.getInt(21));
			det.setSizeShedule(rs.getString(22));
			det.setPrice(rs.getFloat(23));
			det.setValue(rs.getFloat(24));
			det.setGroupId(rs.getInt(25));
			det.setGroupName(rs.getString(26));
			
			if(rsGetInfoDynamic.next()){
				for(DynamicFields dynamicFields : dynamicFieldsListBOMDetails) {
					if(!dynamicFields.isFixed()) {
						String fieldValue = rsGetInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetInfoDynamic.getString(dynamicFields.getDbFieldName());
						if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {

							if(!fieldValue.equals(""))
							{
								SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
								SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MM-yyyy");
								fieldValue = outFormatter.format(inFormatter.parse(fieldValue)).toString();
								if(fieldValue.equals("01-01-1900"))
									fieldValue="";
								inFormatter=null;
								outFormatter=null;
							}
						}
						//					        	det.setFieldValue(fieldValue);
						detMap.put(dynamicFields.getDbFieldName()+rs.getInt(2), fieldValue);
					}
				}
			}


			detList.add(det);
			det=null;
		} 
		objB.setBomDetList(detList);
		objB.setBomDetMap(detMap);
		
		
		dynamicFieldsListBOMDetails=null;
		dynamicFieldsDAO=null;
		detList=null;
		detMap=null;
		query=null;
		// TODO Auto-generated method stub
	} 
	finally {
		DatabaseConnection.closeAll(pstmt, rs);
	}
	return objB; 
}


public int deleteBOMDetail(Connection con, TPCSUser userInfo,int bomId,
		int bomDetId) throws SQLException {
	CallableStatement cstmt = null;
	ResultSet rs = null;    
	 int iCount=0;
	try{ 
		
		cstmt = con.prepareCall("{call pr_delete_bom_details(?,?)}");
		cstmt.setInt(1, bomId);
		cstmt.setInt(2, bomDetId);
		
//		System.out.println("enqId  :"+enqId);
//		System.out.println("enqDetId  :"+enqDetId);
//		System.out.println("iCount  :"+iCount);
		
	    rs=cstmt.executeQuery();
		if(rs.next())
		{
			iCount=rs.getInt(1);
		}

			
		
	} finally {
		DatabaseConnection.closeAll(cstmt,rs);
	}
	return iCount;
}

public boolean createAmendment(Connection con, TPCSUser userInfo,BOM objB,int bomId) throws SQLException {
	// TODO Auto-generated method stub
	boolean isAmend=false;		
	CallableStatement cstmt=null;
	try{ 
		cstmt = con.prepareCall("{? = call pr_amend_bom(?,?)}");
		cstmt.registerOutParameter(1, Types.INTEGER);
		cstmt.setInt(2, bomId);
		cstmt.setString(3, objB.getHeaderMode());

		int iCount = cstmt.executeUpdate();
		if (iCount > 0)
			isAmend = true;

	} finally {
		DatabaseConnection.closeAll(cstmt, null);
	}
	return isAmend;
}


public String getAltMaterialModalData(Connection con,TPCSUser ui,int bomDetId) throws SQLException{
	PreparedStatement pst=null;
	ResultSet rs=null;
	StringBuffer bufferOut=new StringBuffer();
	try {

		bufferOut.append("<alt_material>");
		StringBuffer autoCompleteIdName=new StringBuffer();

		autoCompleteIdName.append("<material_auto_complete_field>");
		StringBuffer bufferHead=new StringBuffer();
		bufferHead.append("<table class=\"table table-bordered table-condensed\"  id=\"addrow_table\">");
		bufferHead.append("<thead>");
		bufferHead.append("<tr class=\"header\">");
		bufferHead.append("<th   >ALTERNATE MATERIAL</th>");
		bufferHead.append("<th   >SUBSTITUTE % (OVER ALL % THIS MATERIAL CAN BE USED FOR AN ORDER)</th>");
		bufferHead.append("<th   >&nbsp;</th>");
		bufferHead.append("</tr>");
		bufferHead.append("</thead>");
		bufferHead.append("<tbody>");


		StringBuffer buffer;
		int matIdx=0;
		String matRowIds="";
        
        
		
		int matCnt=0;
		pst=con.prepareStatement(SQLUtil.GET_ALT_MAT_COUNT);
		pst.setInt(1, bomDetId);
		rs=pst.executeQuery();
		if(rs.next()){
			matCnt=rs.getInt(1);
		}
		
		
		if(matCnt>0){

			pst=con.prepareStatement(SQLUtil.GET_ALT_MAT_DATA);
			pst.setInt(1, bomDetId);
			rs=pst.executeQuery();
			buffer=new StringBuffer();
			while(rs.next()){
				
				buffer.append("<tr id=\"n_table_row"+matIdx+"\" class=\"datarow\">");
				buffer.append("<td>");
				buffer.append("<input class=\"form-control\" type=\"text\" name=\"alt_mat_"+matIdx+"\" id=\"alt_mat_"+matIdx+"\"  value=\""+rs.getString(3)+"\"   placeholder=\"Alternate Material\"/>");
				buffer.append("<input  type=\"hidden\" name=\"alt_mat_id_"+matIdx+"\" id=\"alt_mat_id_"+matIdx+"\"  value=\""+rs.getInt(2)+"\"  />");
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append("<input class=\"form-control\" type=\"text\" name=\"alt_per_"+matIdx+"\" id=\"alt_per_"+matIdx+"\"   value=\""+rs.getFloat(4)+"\"  placeholder=\"Alternate Per\"/>");
				buffer.append("</td>");
				
				buffer.append("<td  ><a href=\"javascript:addAnotherLineInAltMat("+matIdx+")\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Add\"><span class=\"glyphicon glyphicon-plus text-primary\"></span></a>&nbsp;");
				 buffer.append(" <a name=\"delete_0\" id=\"delete_0\" onclick=\"deleteAltMaterial(document.getElementById('alt_mat_id_"+matIdx+"').value,'"+matIdx+"',"+bomDetId+")\"><span class=\"glyphicon glyphicon-trash text-danger grid-icon-alone\"  data-toggle=\"tooltip\" title=\"Delete\" ></span></a></td>");
			
				 
				 buffer.append("</tr>");
				
				autoCompleteIdName.append("<auto_complete_field>");

				autoCompleteIdName.append("<name>");
				autoCompleteIdName.append("alt_mat_"+matIdx);
				autoCompleteIdName.append("</name>");
				autoCompleteIdName.append("<id>");
				autoCompleteIdName.append("alt_mat_id_"+matIdx);
				autoCompleteIdName.append("</id>");
				autoCompleteIdName.append("<method>");
				autoCompleteIdName.append("doGetAltMaterial");
				autoCompleteIdName.append("</method>");
				
				System.out.println("matIdx=="+matIdx);
				System.out.println("ok");
				
				autoCompleteIdName.append("</auto_complete_field>");
			
				matRowIds=matRowIds+matIdx+",";
				matIdx++;
			}

		
		}
		else{
			buffer=new StringBuffer();

			buffer.append("<tr  id=\"n_table_row0\" class=\"datarow\">");
			buffer.append("<td>");
			buffer.append("<input class=\"form-control\" type=\"text\" name=\"alt_mat_0\" id=\"alt_mat_0\"    placeholder=\"Alternate Material\"/>");
			buffer.append("<input  type=\"hidden\" name=\"alt_mat_id_0\" id=\"alt_mat_id_0\"   />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input class=\"form-control\" type=\"text\" name=\"alt_per_0\" id=\"alt_per_0\"    placeholder=\"Alternate Per\"/>");
			buffer.append("</td>");
			
			buffer.append("<td  ><a href=\"javascript:addAnotherLineInAltMat(0)\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Add\"><span class=\"glyphicon glyphicon-plus text-primary\"></span></a>&nbsp;");
			 buffer.append(" <a name=\"delete_0\" id=\"delete_0\" onclick=\"deleteAltMaterial(document.getElementById('alt_mat_id_0').value,0,"+bomDetId+")\"><span class=\"glyphicon glyphicon-trash text-danger grid-icon-alone\"  data-toggle=\"tooltip\" title=\"Delete\" ></span></a></td>");
			buffer.append("</tr>");

			autoCompleteIdName.append("<auto_complete_field>");

			autoCompleteIdName.append("<name>");
			autoCompleteIdName.append("alt_mat_"+0);
			autoCompleteIdName.append("</name>");
			autoCompleteIdName.append("<id>");
			autoCompleteIdName.append("alt_mat_id_"+0);
			autoCompleteIdName.append("</id>");
			autoCompleteIdName.append("<method>");
			autoCompleteIdName.append("doGetAltMaterial");
			autoCompleteIdName.append("</method>");
			
			
			autoCompleteIdName.append("</auto_complete_field>");


			matRowIds=matRowIds+matIdx+",";
			matIdx=1;

		}
			
			autoCompleteIdName.append("</material_auto_complete_field>");
		StringBuffer bufferFooter=new StringBuffer();
		bufferFooter.append("</tbody>");
		bufferFooter.append("</table>");

		bufferOut.append("<mat_data>");	
		bufferOut.append("<mat_table>");
		bufferOut.append(StringEscapeUtils.escapeXml(bufferHead.toString()));
		bufferOut.append(StringEscapeUtils.escapeXml(buffer.toString()));
		bufferOut.append(StringEscapeUtils.escapeXml(bufferFooter.toString()));
		bufferOut.append("</mat_table>");	
		bufferOut.append("</mat_data>");

		bufferOut.append("<mat_rows>");	
		bufferOut.append("<mat_rowcnt>");
		bufferOut.append(matIdx);
		bufferOut.append("</mat_rowcnt>");	
		bufferOut.append("<mat_rowIds>");
		bufferOut.append(matRowIds);
		bufferOut.append("</mat_rowIds>");	
		bufferOut.append("</mat_rows>");

		bufferOut.append(autoCompleteIdName.toString());

		
		bufferOut.append("</alt_material>");
		
		bufferFooter=null;
		bufferHead=null;
		buffer=null;
		autoCompleteIdName=null;
	} 
	finally {
		DatabaseConnection.closeAll(pst,rs);
	}
	return bufferOut.toString();
}


public String getBOMCompModalData(Connection con,TPCSUser ui,int bomDetId) throws SQLException{
	PreparedStatement pst=null;
	ResultSet rs=null;
	StringBuffer bufferOut=new StringBuffer();
	try {

		bufferOut.append("<bom_comp>");
		StringBuffer compautoCompleteIdName=new StringBuffer();
		StringBuffer uomautoCompleteIdName=new StringBuffer();

		compautoCompleteIdName.append("<bom_comp_auto_complete_field>");
		uomautoCompleteIdName.append("<bom_uom_auto_complete_field>");
		StringBuffer bufferHead=new StringBuffer();
		bufferHead.append("<table class=\"table table-bordered table-condensed\"  id=\"addrow_table\">");
		bufferHead.append("<thead>");
		bufferHead.append("<tr class=\"header\">");
		bufferHead.append("<th>Component</th>");
		bufferHead.append("<th>Length</th>");
		bufferHead.append("<th>Width</th>");
		bufferHead.append("<th>NOS</th>");
		bufferHead.append("<th>Meas UOM</th>");
		bufferHead.append("<th>Norms</th>");
		bufferHead.append("<th style=\"width: 8%;\">&nbsp;</th>");
		bufferHead.append("</tr>");
		bufferHead.append("</thead>");
		bufferHead.append("<tbody>");


		StringBuffer buffer;
		int compIdx=0;
		String compRowIds="";
        
        
		
		int compCnt=0;
		pst=con.prepareStatement(SQLUtil.GET_BOM_COM_COUNT);
		pst.setInt(1, bomDetId);
		rs=pst.executeQuery();
		if(rs.next()){
			compCnt=rs.getInt(1);
		}
		
		
		if(compCnt>0){

			pst=con.prepareStatement(SQLUtil.GET_BOM_COMP_DATA);
			pst.setInt(1, bomDetId);
			rs=pst.executeQuery();
			buffer=new StringBuffer();
			while(rs.next()){
				
				buffer.append("<tr id=\"n_table_row"+compIdx+"\" class=\"datarow\">");
				buffer.append("<td>");
				buffer.append("<input class=\"form-control\" type=\"text\" name=\"bom_comp_"+compIdx+"\" id=\"bom_comp_"+compIdx+"\"  value=\""+rs.getString(2)+"\"   placeholder=\"Component\"/>");
				buffer.append("<input  type=\"hidden\" name=\"bom_comp_id_"+compIdx+"\" id=\"bom_comp_id_"+compIdx+"\" value=\""+rs.getString(8)+"\"   />");
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append("<input class=\"form-control\" type=\"text\" name=\"comp_length_"+compIdx+"\" id=\"comp_length_"+compIdx+"\"  onblur=\"calBOMCompReqQty('"+compIdx+"');\" value=\""+rs.getString(3)+"\"  placeholder=\"Length\"/>");
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append("<input class=\"form-control\" type=\"text\" name=\"comp_width_"+compIdx+"\" id=\"comp_width_"+compIdx+"\"    onblur=\"calBOMCompReqQty('"+compIdx+"');\" value=\""+rs.getString(4)+"\"  placeholder=\"Width\"/>");
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append("<input class=\"form-control\" type=\"text\" name=\"no_of_parts_"+compIdx+"\" id=\"no_of_parts_"+compIdx+"\"  onblur=\"calBOMCompReqQty('"+compIdx+"');\"  value=\""+rs.getString(5)+"\"  placeholder=\"No. of parts\"/>");
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append("<input class=\"form-control\" type=\"text\" name=\"meas_uom_"+compIdx+"\" id=\"meas_uom_"+compIdx+"\"   value=\""+rs.getString(7)+"\"  placeholder=\"UOM\"/>");
				buffer.append("<input  type=\"hidden\" name=\"meas_uom_id_"+compIdx+"\" id=\"meas_uom_id_"+compIdx+"\"  />");
				buffer.append("</td>");
				buffer.append("<td>");
				buffer.append("<input class=\"form-control\" type=\"text\" name=\"reqd_qty_"+compIdx+"\" id=\"reqd_qty_"+compIdx+"\"   value=\""+rs.getString(6)+"\"  placeholder=\"Reqd Qty\"/>");
				buffer.append("</td>");
				
				buffer.append("<td  > <a href=\"javascript:saveBOMCopmponent(document.getElementById('bom_comp_id_"+compIdx+"').value,"+compIdx+")\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Save\"><span class=\"glyphicon glyphicon-save text-primary\"></span></a>&nbsp;"
						+ "<a href=\"javascript:addAnotherLineInBOMComp("+compIdx+")\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Add\"><span class=\"glyphicon glyphicon-plus text-primary\"></span></a>&nbsp;");
				 buffer.append(" <a name=\"delete_0\" id=\"delete_0\" onclick=\"deleteBOMComponents(document.getElementById('bom_comp_id_"+compIdx+"').value,'"+compIdx+"',"+bomDetId+")\"><span class=\"glyphicon glyphicon-trash text-danger grid-icon-alone\"  data-toggle=\"tooltip\" title=\"Delete\" ></span></a></td>");
			
				 
				 buffer.append("</tr>");
				
				 compautoCompleteIdName.append("<comp_auto_complete_field>");

				 compautoCompleteIdName.append("<name>");
				 compautoCompleteIdName.append("bom_comp_"+compIdx);
				 compautoCompleteIdName.append("</name>");
				 compautoCompleteIdName.append("<method>");
				 compautoCompleteIdName.append("doGetBOMCOMP");
				 compautoCompleteIdName.append("</method>");
				 compautoCompleteIdName.append("</comp_auto_complete_field>");
				
				
				 uomautoCompleteIdName.append("<uom_auto_complete_field>");

				 uomautoCompleteIdName.append("<name>");
				 uomautoCompleteIdName.append("meas_uom_"+compIdx);
				 uomautoCompleteIdName.append("</name>");
				 uomautoCompleteIdName.append("<id>");
				 uomautoCompleteIdName.append("meas_uom_id_"+compIdx);
				uomautoCompleteIdName.append("</id>");
				uomautoCompleteIdName.append("<method>");
				uomautoCompleteIdName.append("doGetUOM");
				uomautoCompleteIdName.append("</method>");
				
				
				uomautoCompleteIdName.append("</uom_auto_complete_field>");
			
				compRowIds=compRowIds+compIdx+",";
				compIdx++;
			}

		
		}
		else{
			buffer=new StringBuffer();

			buffer.append("<tr  id=\"n_table_row0\" class=\"datarow\">");
			buffer.append("<td>");
			buffer.append("<input class=\"form-control\" type=\"text\" name=\"bom_comp_0\" id=\"bom_comp_0\"    placeholder=\"Component\"/>");
			buffer.append("<input  type=\"hidden\" name=\"bom_comp_id_0\" id=\"bom_comp_id_0\"   />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input class=\"form-control\" type=\"text\" name=\"comp_length_0\" id=\"comp_length_0\"  onblur=\"calBOMCompReqQty('0');\" placeholder=\"Length\"/>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input class=\"form-control\" type=\"text\" name=\"comp_width_0\" id=\"comp_width_0\"   onblur=\"calBOMCompReqQty('0');\"   placeholder=\"Width\"/>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input class=\"form-control\" type=\"text\" name=\"no_of_parts_0\" id=\"no_of_parts_0\"   onblur=\"calBOMCompReqQty('0');\" placeholder=\"No. of parts\"/>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input class=\"form-control\" type=\"text\" name=\"meas_uom_0\" id=\"meas_uom_0\"  placeholder=\"UOM\"/>");
			buffer.append("<input  type=\"hidden\" name=\"meas_uom_id_0\" id=\"meas_uom_id_0\"  />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input class=\"form-control\" type=\"text\" name=\"reqd_qty_0\" id=\"reqd_qty_0\"    placeholder=\"Reqd Qty\"/>");
			buffer.append("</td>");
			
			
			buffer.append("<td  ><a href=\"javascript:saveBOMCopmponent(document.getElementById('bom_comp_id_0').value,0)\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Save\"><span class=\"glyphicon glyphicon-save text-primary\"></span></a>&nbsp;"
					+ "<a href=\"javascript:addAnotherLineInBOMComp(0)\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Add Row\"><span class=\"glyphicon glyphicon-plus text-primary\"></span></a>&nbsp;");
			 buffer.append(" <a name=\"delete_0\" id=\"delete_0\" onclick=\"deleteBOMComponents(document.getElementById('bom_comp_id_0').value,0,"+bomDetId+")\"><span class=\"glyphicon glyphicon-trash text-danger grid-icon-alone\"  data-toggle=\"tooltip\" title=\"Delete\" ></span></a></td>");
			buffer.append("</tr>");

			
			compautoCompleteIdName.append("<comp_auto_complete_field>");

			compautoCompleteIdName.append("<name>");
			compautoCompleteIdName.append("bom_comp_0");
			compautoCompleteIdName.append("</name>");
			compautoCompleteIdName.append("<method>");
			compautoCompleteIdName.append("doGetBOMCOMP");
			compautoCompleteIdName.append("</method>");
			
			
			compautoCompleteIdName.append("</comp_auto_complete_field>");
			
			
			uomautoCompleteIdName.append("<uom_auto_complete_field>");

			uomautoCompleteIdName.append("<name>");
			uomautoCompleteIdName.append("meas_uom_0");
			uomautoCompleteIdName.append("</name>");
			uomautoCompleteIdName.append("<id>");
			uomautoCompleteIdName.append("meas_uom_id_0");
			uomautoCompleteIdName.append("</id>");
			uomautoCompleteIdName.append("<method>");
			uomautoCompleteIdName.append("doGetUOM");
			uomautoCompleteIdName.append("</method>");
			
			
			uomautoCompleteIdName.append("</uom_auto_complete_field>");


			compRowIds=compRowIds+compIdx+",";
			compIdx=1;

		}
		uomautoCompleteIdName.append("</bom_uom_auto_complete_field>");
		compautoCompleteIdName.append("</bom_comp_auto_complete_field>");
		StringBuffer bufferFooter=new StringBuffer();
		bufferFooter.append("</tbody>");
		bufferFooter.append("</table>");

		bufferOut.append("<comp_data>");	
		bufferOut.append("<comp_table>");
		bufferOut.append(StringEscapeUtils.escapeXml(bufferHead.toString()));
		bufferOut.append(StringEscapeUtils.escapeXml(buffer.toString()));
		bufferOut.append(StringEscapeUtils.escapeXml(bufferFooter.toString()));
		bufferOut.append("</comp_table>");	
		bufferOut.append("</comp_data>");

		bufferOut.append("<comp_rows>");	
		bufferOut.append("<com_rowcnt>");
		bufferOut.append(compIdx);
		bufferOut.append("</com_rowcnt>");	
		bufferOut.append("<comp_rowIds>");
		bufferOut.append(compRowIds);
		bufferOut.append("</comp_rowIds>");	
		bufferOut.append("</comp_rows>");

		bufferOut.append(compautoCompleteIdName.toString());
		bufferOut.append(uomautoCompleteIdName.toString());
		
		bufferOut.append("</bom_comp>");
		
		bufferFooter=null;
		bufferHead=null;
		buffer=null;
		compautoCompleteIdName=null;
		uomautoCompleteIdName=null;
	} 
	finally {
		DatabaseConnection.closeAll(pst,rs);
	}
	return bufferOut.toString();
}
public BOM saveAlternateMaterials(Connection con, TPCSUser ui,BOM obj)throws SQLException,Exception{
	CallableStatement cstmt=null;
	ResultSet rs=null;
	try{
		int count=0;
		SQLXML matSqlxml = con.createSQLXML();
		obj.setInserted(false);
//		System.out.println("obj.getBomDetId() :"+obj.getBomDetId());
//	System.out.println("obj.getBillDetails() :"+obj.getAltMatDetails());
		cstmt=con.prepareCall("{call pr_insert_alt_material(?,?)}");
		cstmt.setInt(1, obj.getBomDetId());
		matSqlxml.setString(obj.getAltMatDetails());
		cstmt.setSQLXML(2,matSqlxml);
		rs=cstmt.executeQuery();
		if(rs.next()){
			count=rs.getInt(1);
		}
//		System.out.println("count :"+count);
		if(count>0){
			obj.setInserted(true);
		}

		matSqlxml=null;

	}
	finally{
		DatabaseConnection.closeAll(cstmt, rs);
	}
	return obj;
}	



public BOM deleteAltMaterial(Connection con,TPCSUser ui,BOM obj) throws SQLException {
	// TODO Auto-generated method stub
      PreparedStatement pstmt=null;
	Statement stmt = null;
	  ResultSet rs=null;
	try
	{
		stmt = con.createStatement();
		
		 obj.setDeleted(false);
		 
				
					stmt.addBatch("DELETE FROM bom_alt_details  WHERE bom_detail_id="+obj.getBomDetId()+" and item_id="+obj.getAltMatId());
					stmt.executeBatch();
					
					obj.setDeleted(true);
					
					
	     
	}
	
	finally{          
	       
		DatabaseConnection.closeAll(stmt, null);
		DatabaseConnection.closeAll(pstmt, rs);
      }
	return obj;
}




public BOMDetail deleteBOMComponet(Connection con,TPCSUser ui,BOMDetail obj) throws SQLException {
	// TODO Auto-generated method stub
      PreparedStatement pstmt=null;
	Statement stmt = null;
	  ResultSet rs=null;
	try
	{
		stmt = con.createStatement();
		
		 obj.setDeleted(false);
		 
				
					stmt.addBatch("DELETE FROM bom_comp_details  WHERE bom_comp_id="+obj.getComponentId());
					stmt.executeBatch();
					
					obj.setDeleted(true);
					
					
	     
	}
	
	finally{          
	       
		DatabaseConnection.closeAll(stmt, null);
		DatabaseConnection.closeAll(pstmt, rs);
      }
	return obj;
}

public String doGetMatData(TPCSUser userInfo,Connection con,int matId)throws SQLException,Exception{
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String desc="";
	String uom="";
	int uomId=0;
	float rate=0.0f;
	StringBuffer buffer=new StringBuffer();
	try{      

	
			pstmt=con.prepareStatement(SQLUtil.GET_MAT_DATA);
			pstmt.setInt(1,matId);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
//				desc=rs.getString(1);
				uom=rs.getString(1);
//				rate=rs.getFloat(3);
				uomId=rs.getInt(2);
			}
			
//			pstmt=con.prepareStatement("select isnull(final_product_cost_lcy,0) from sales_cost_work where isnull(final_product_cost_lcy,0)>0 and sales_mat_id=?");
//			pstmt.setInt(1,matId);
//			rs=pstmt.executeQuery();
//			if(rs.next()){
//				rate=rs.getFloat(1);
//			}
			
		
			

		
		buffer.append("<material_datas>");
		buffer.append("<material_data>");
		buffer.append("<desc>");
		buffer.append(StringEscapeUtils.escapeXml(desc));
		buffer.append("</desc>");
		buffer.append("<uom>");
		buffer.append(uom);
		buffer.append("</uom>");
		buffer.append("<uom_id>");
		buffer.append(uomId);
		buffer.append("</uom_id>");
		buffer.append("<rate>");
		buffer.append(rate);
		buffer.append("</rate>");
		
		buffer.append("</material_data>");
		buffer.append("</material_datas>");
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally{    
		DatabaseConnection.closeAll(pstmt,rs);
	}
	/*return desc+"&1"+uom;*/
	return buffer.toString();//desc+"&1"+uom+"&1"+rate;
}


public boolean deleteAttachment(Connection con, int bomId,String fileName) throws SQLException {
	PreparedStatement pstmt=null;    
	ResultSet rs = null;    
	boolean bFlag=false;
	try{
		int count=0;
		pstmt = con.prepareStatement(SQLUtil.DELETE_BOM_ATTACH);
		pstmt.setInt(1, bomId);
		pstmt.setString(2, fileName);
		count=pstmt.executeUpdate();
		if(count>0){
			bFlag=true;
		}


	}finally{     

		DatabaseConnection.closeAll(pstmt,rs);
	}
	return bFlag;
}

public int saveBOMLock(Connection con, BOM obj,TPCSUser ui) throws SQLException {

	CallableStatement cstmt=null;
	int count=0;
	try	{
		cstmt=con.prepareCall("{ call pr_insert_bom_lock(?,?,?,?,?)}");
		
//		System.out.println(obj.getBomId());
//		System.out.println(obj.getLockStatus());
//		System.out.println(obj.getLockRemarks());
//		System.out.println(ui.getUserId());
//		System.out.println(ui.getDisplayUserName());
		
		cstmt.setInt(1,obj.getBomId());
		cstmt.setInt(2,obj.getLockStatus());
		cstmt.setString(3,obj.getLockRemarks());
		cstmt.setInt(4, ui.getUserId());
		cstmt.setString(5,ui.getDisplayUserName());

		int rowsInserted=cstmt.executeUpdate();
		count=rowsInserted>0?1:0;


	}
	finally	{

		DatabaseConnection.closeAll(cstmt, null);

	}
	return count;
}

public BOMDetail saveBOMComponets(Connection con,BOMDetail objB,TPCSUser ui) throws SQLException {
	
	  PreparedStatement ArrayOfPreparedStatement[] = null;
	  ResultSet ArrayOfResultSet[] = null;
	  PreparedStatement pstmt=null;
	  ResultSet rs=null;
	  String reqQty="0";
	  float conversionVal=0.0f;
	  int compCount=0;
//	  List<FAGroup> idList=new ArrayList<FAGroup>();
		try {
			
				pstmt=con.prepareStatement("select distinct count(*) from bom_comp_details where bom_detail_id="+objB.getBomDetailId()+" and bom_comp_id="+objB.getComponentId());
				rs=pstmt.executeQuery();
				if(rs.next()){
					compCount=rs.getInt(1);
				}
				if(compCount==0){
					
					
//				System.out.println(objB.getBomDetailId());
//				System.out.println(objB.getComponent());
//				System.out.println(objB.getCompLength());
//				System.out.println(objB.getCompWidth());
//				System.out.println(objB.getNoOfParts());
//				System.out.println(objB.getRequiredQty());
//				System.out.println(objB.getUOM());
				
				pstmt=con.prepareStatement(SQLUtil.INSERT_INTO_BOM_COMPONENTS);
				pstmt.setInt(1, objB.getBomDetailId());
				pstmt.setString(2, objB.getComponent());
				pstmt.setString(3, objB.getCompLength());
				pstmt.setString(4, objB.getCompWidth());
				pstmt.setString(5, objB.getNoOfParts());
				pstmt.setString(6, objB.getRequiredQty());
				pstmt.setString(7, objB.getUOM());
				
			}
			else{
				pstmt=con.prepareStatement(SQLUtil.UPDATE_BOM_COMPONENTS);
				
				pstmt.setString(1, objB.getComponent());
				pstmt.setString(2, objB.getCompLength());
				pstmt.setString(3, objB.getCompWidth());
				pstmt.setString(4, objB.getNoOfParts());
				pstmt.setString(5, objB.getRequiredQty());
				pstmt.setString(6, objB.getUOM());
				pstmt.setInt(7, objB.getBomDetailId());
				pstmt.setInt(8, objB.getComponentId());
			
			}
		
				

				int count=pstmt.executeUpdate();
				objB.setInserted(false);
				objB.setReqQty("0");
				int compId=0;
				if(count>0){
					int bomDetId=objB.getBomDetailId();//For edit case
					objB.setInserted(true);
					
					objB.setBomDetailId(bomDetId);

					
					if(compCount==0){
						pstmt=con.prepareStatement("select top 1 bom_comp_id from bom_comp_details where bom_detail_id="+objB.getBomDetailId()+" order by bom_comp_id desc ");
						rs=pstmt.executeQuery();
						if(rs.next()){
							compId=rs.getInt(1);
						}
						objB.setComponentId(compId);
					}
				
					
				
					
					
					pstmt=con.prepareStatement("select sum(isnull(Reqd_qty,0)) Reqd_qty from bom_comp_details where bom_detail_id="+objB.getBomDetailId());
					rs=pstmt.executeQuery();
					if(rs.next()){
						reqQty=rs.getString(1);
					}
					
					conversionVal=1;
					if(objB.getUOM()!=null && !objB.getUOM().isEmpty()){
						
						pstmt=con.prepareStatement("select isnull(conversion_value,0) conversion_value from Uom_conversion where basic_unit='"+objB.getMainUom()+"'  and Alternate_unit='"+objB.getUOM()+"' ");
						rs=pstmt.executeQuery();
						if(rs.next()){
							conversionVal=rs.getFloat(1);
						}
						
					}
				
					reqQty=String.valueOf(Double.parseDouble(reqQty)/conversionVal);
					
					objB.setReqQty(reqQty);
				
				}
			
		}finally{
		      
			ArrayOfPreparedStatement = new PreparedStatement[5];
			ArrayOfPreparedStatement[0] = pstmt;
		      
		      
		      ArrayOfResultSet = new ResultSet[5];      
		      ArrayOfResultSet[0] = rs;
		        DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		      }
		return objB;
	}


public BOMDetail getBOMNoDetailList(Connection con,int custId) throws SQLException, Exception {
	// TODO Auto-generated method stub
	BOMDetail objB=new BOMDetail();
	List<BOMDetail> bodList=new ArrayList<BOMDetail>();;
	CallableStatement cstmt=null;
	ResultSet rs=null;
	try
	{
		
		String searchStr="";
		/*if(custId>0)
		{
			searchStr=" having so.party_id="+custId;
		}*/
		cstmt=con.prepareCall(SQLUtil.GET_PARENT_BOM_NO_LIST);
		rs = cstmt.executeQuery();
		
		while (rs.next()) {
			BOMDetail bd=new BOMDetail();
			bd.setBomId(rs.getInt(1));
			bd.setBomNo(rs.getString(2));
			bd.setProduct(rs.getString(3));
			bd.setCustomer(rs.getString(4));
			bd.setMaterial(rs.getString(5));
			bd.setVariant(rs.getString(6));
			bd.setUOM(rs.getString(7));
			bd.setOperation(rs.getString(8));
			bd.setSupplier(rs.getString(9));
			bd.setComponent(rs.getString(10));
			bd.setRequiredQty(rs.getString(11));
			bd.setCustomerId(rs.getInt(12));
			bodList.add(bd);
			bd=null;
		}
		objB.setBomDetList(bodList);
		bodList=null;
	}
	finally
	{
		DatabaseConnection.closeAll(cstmt, rs);
	}
	return objB;
}
public int insertBOMFromParentBOM(Connection con,TPCSUser ui,BOM objB) throws Exception {
		// TODO Auto-generated method stub
		CallableStatement cstmt = null;
		ResultSet rs=null;
   int trId=0;
		try {

			BOMHeader bomH=objB.getHeader();
		
 		cstmt = con.prepareCall("{? = call pr_post_bom_from_parentBOM(?,?,?,?,?,?,?)}");
	//	SQLXML sqlxml = con.createSQLXML();
		cstmt.registerOutParameter(1, Types.INTEGER);
 		cstmt.setString(2, ui.getUserName());
 		cstmt.setInt(3, ui.getUserId());
		cstmt.setString(4, objB.getHeaderMode());
		cstmt.setString(5,bomH.getBomNo());
		if(bomH.getBomDate()!=null && !bomH.getBomDate().isEmpty())
		{
			cstmt.setString(6, bomH.getBomDate());
		}
		else
		{
			cstmt.setNull(6, Types.DATE);
		}
		cstmt.setInt(7,bomH.getCustomerId());
		cstmt.setInt(8,bomH.getParentBomId());
		
		//sqlxml.setString(objG.getSqlxmlDynamicFields());
	//	cstmt.setSQLXML("XmlData",sqlxml);
		cstmt.executeUpdate();
		trId = cstmt.getInt(1);
	
		} finally {
			DatabaseConnection.closeAll(cstmt, null);
		}
		return trId;

	}




}
