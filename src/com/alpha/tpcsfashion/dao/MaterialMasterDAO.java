package com.alpha.tpcsfashion.dao;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.Currency;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.MaterialMaster;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class MaterialMasterDAO {
	
	public MaterialMaster getAllMaterialOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,String strSearchCriteria,List<String> orderBy,int iScreenId,String materialAttachPath)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
		CallableStatement cstmt=null;
	    ResultSet rs = null; 
	    ResultSet rsAttach = null; 
	    PreparedStatement pstmtGetMatInfoDynamic =null;
	    ResultSet rsGetMatInfoDynamic=null;
	    PreparedStatement pstmt=null;
	    MaterialMaster mat=new MaterialMaster();
	    
	    String strbuffer="";
	    try{
	    	List<Map<String,String>> matList=new ArrayList<Map<String,String>>();	
	    	DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();  
	        List<DynamicFields> dynamicFieldsListMaterial = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 2);
	        
	        Map<String,String> colPreMap=getColPreMap(con,23);
	    	StringBuffer query = new StringBuffer(SQLUtil.GET_MATERIAL_MASTER);
	 
	    	if(strSearchCriteria!=null && !strSearchCriteria.isEmpty()){
	        	 query.append(" WHERE "+strSearchCriteria);
	         }
	    	
	    	String order=" order by ";
	         if(orderBy!=null && !orderBy.isEmpty()){
	        	 
		    	  for(String strOrderBy:orderBy){
		    		 if(strOrderBy!=null){
		    			 strbuffer+=strOrderBy+",";  
		    		 }
		    	  }
//			    	   strbuffer=query.toString();
		    		 if(strbuffer.length()>0){
		    			 strbuffer=strbuffer.substring(0, strbuffer.length() - 1);
		    			 query.append(order+strbuffer);
		    		 }
		    		 
		    	  
		      }
//	         System.out.println("mat_query "+query.toString());
	         cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
	         cstmt.setString(1,query.toString());
	         cstmt.setInt(2,pc.iStart);
	         cstmt.setInt(3,pc.iEnd);
	         rs=cstmt.executeQuery();
	        while(rs.next()){
	    	    Map<String,String> map = new HashMap<String,String>();
//		    	  System.out.println("group_name"+rs.getString("group_name"));
	    	    map.put("Material.MatId", rs.getString("item_id"));
		        map.put(colPreMap.get("group_id"), rs.getString("group_name"));
		        map.put(colPreMap.get("item_code"), rs.getString("item_code"));
		        map.put(colPreMap.get("item_id"), rs.getString("item_name"));
		        map.put(colPreMap.get("Standard_Cost"), rs.getString("Standard_Cost"));
		        map.put(colPreMap.get("Standard_Cost_currency"), rs.getString("Standard_Cost_currency"));
		        map.put(colPreMap.get("Stock_Uom"), rs.getString("Stock_Uom"));
		        map.put(colPreMap.get("BOM_Uom"), rs.getString("BOM_Uom"));
		        map.put(colPreMap.get("Purchase_Uom"), rs.getString("Purchase_Uom"));
		        map.put(colPreMap.get("spec1"), rs.getString("spec1"));
		        map.put(colPreMap.get("spec2"), rs.getString("spec2"));
		        map.put(colPreMap.get("spec3"), rs.getString("spec3"));
		        map.put(colPreMap.get("spec4"), rs.getString("spec4"));
		        map.put(colPreMap.get("spec5"), rs.getString("spec5"));
		        map.put(colPreMap.get("spec6"), rs.getString("spec6"));
		        map.put(colPreMap.get("spec7"), rs.getString("spec7"));
		        map.put(colPreMap.get("spec8"), rs.getString("spec8"));
		        map.put(colPreMap.get("spec9"), rs.getString("spec9"));
		        map.put(colPreMap.get("spec10"), rs.getString("spec10"));
		        map.put(colPreMap.get("BOM_Number"), rs.getString("BOM_Number"));
		        map.put(colPreMap.get("min_level"), rs.getString("min_level"));
		        map.put(colPreMap.get("max_level"), rs.getString("max_level"));
		        map.put(colPreMap.get("reorder_level"), rs.getString("reorder_level"));
		        map.put(colPreMap.get("reorder_qty"), rs.getString("reorder_qty"));
		        map.put(colPreMap.get("min_order_qty"), rs.getString("min_order_qty"));
		        map.put(colPreMap.get("lead_time"), rs.getString("lead_time"));
//		        map.put(colPreMap.get("excess_allowance"), rs.getString("excess_allowance"));
		        map.put(colPreMap.get("default_size_range"), rs.getString("default_size_range"));
		        map.put(colPreMap.get("default_leather_id"), rs.getString("default_leather_id"));
		        map.put(colPreMap.get("default_colour_id"), rs.getString("Variant_Code"));
		        map.put(colPreMap.get("buyer_style_no"), rs.getString("buyer_style_no"));
		        map.put(colPreMap.get("sample_style_no"), rs.getString("sample_style_no"));
		        map.put(colPreMap.get("old_item_code"), rs.getString("old_item_code"));
		        map.put(colPreMap.get("abc_class"), rs.getString("abc_class"));
		        map.put(colPreMap.get("xyz_class"), rs.getString("xyz_class"));
		        map.put(colPreMap.get("route_ref"), rs.getString("route_ref"));
		        map.put(colPreMap.get("imported_item"), rs.getString("imported_item"));
		        map.put(colPreMap.get("item_tracking_applicable"), rs.getString("item_tracking_applicable"));
		        map.put(colPreMap.get("expiry_applicable"), rs.getString("expiry_applicable"));
		        map.put(colPreMap.get("excess_allowance"), rs.getString("excess_allowance"));
		        map.put(colPreMap.get("allow_negative_stock"), rs.getString("allow_negative_stock"));
		        map.put(colPreMap.get("issue_with_IO"), rs.getString("issue_with_IO"));
		        map.put(colPreMap.get("tax_category"), rs.getString("tax_category")); 
		        map.put(colPreMap.get("status"), rs.getString("is_active"));
		        //pstmt = con.prepareStatement(SQLUtil.SELECT_MATERIAL_ATTACHMENT);
		        //pstmt.setInt(1, Validator.convertToInteger(rs.getString("mat_id")));
		       // rsAttach=pstmt.executeQuery();
		       
		        String fileNames="";
//		        while(rsAttach.next()){
//		        	
//		        	fileNames=fileNames+"<a target=\"_blank\" href=\""+materialAttachPath.concat(rsAttach.getString(3))+"\"  title=\"view\">"+rsAttach.getString(3)+"</a><br/>";
//		        	/*fileNames=fileNames+"<a target=\"_blank\" href=\""+materialAttachPath.concat(rsAttach.getString(3))+"\">"+rsAttach.getString(3).substring(rs.getString(3).lastIndexOf("/")+1,rsAttach.getString(3).length())+"</a><br/>";*/
//		        }
		        map.put(colPreMap.get("Material.Attachment"), fileNames);
		        
		        //pstmt = con.prepareStatement(SQLUtil.SELECT_MATERIAL_PRIMARY_IMAGE);
		        //pstmt.setInt(1, Validator.convertToInteger(rs.getString("mat_id")));
//		        //rsAttach=pstmt.executeQuery();
//		        if(rsAttach.next()){
//		        	map.put(colPreMap.get("Material.Image"), "<div class=\"grid-image\" ><img src=\""+materialAttachPath.concat(rsAttach.getString(3)+"\" height=\"30px\" ></div>"));	
//		        }
		        
//		      //Start - Added for dynamic fields
		        pstmtGetMatInfoDynamic = con.prepareStatement(SQLUtil.SELECT_ITEM_DYNAMIC_BY_ID);
		        pstmtGetMatInfoDynamic.setInt(1,Validator.convertToInteger(rs.getString("item_id")));  
		        rsGetMatInfoDynamic = pstmtGetMatInfoDynamic.executeQuery();
		        if(rsGetMatInfoDynamic.next()){
				      for(DynamicFields dynamicFields : dynamicFieldsListMaterial) {
				    	  if(!dynamicFields.isFixed()){
				    		  String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
				    		  String fieldValue = rsGetMatInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetMatInfoDynamic.getString(dynamicFields.getDbFieldName());
				    		  if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {
				    			  if(!fieldValue.equals(""))
				    			  {
			        				SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
			        				SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MM-yyyy");
					    			fieldValue = outFormatter.format(inFormatter.parse(fieldValue)).toString();
					    			if(fieldValue.equals("01-01-1900"))
					    				fieldValue="";
				    			  }
			        			}
				        	//dynamicFields.setFieldValue(fieldValue);
				    		  
				    		  map.put(labelName,fieldValue);
				    		  
				    	  }
					  }
					 
				 }
		        
		        //End - Added for dynamic fields
		        
	     
		        matList.add(map);
	      }
	        mat.setMaterialMapList(matList);
	        
	    }
	    finally{
		      if(cstmt!=null)
		    	  cstmt.close();
		    
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0]=pstmtGetMatInfoDynamic;
		      ArrayOfResultSet = new ResultSet[2];      
		      ArrayOfResultSet[0] = rs;
		      ArrayOfResultSet[1] = rsAttach;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }

	    return mat;
	  }
	public String getDynamicDbName(Connection con,int dynamicfieldId)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGetDynamicDbname=null;
	    ResultSet rsGetDynamicDbname = null;   
	   String dynamicDbName=null;
	    try{      
	    	 	      
	    	pstmtGetDynamicDbname = con.prepareStatement(SQLUtil.GET_DYNAMIC_DBNAME); 
	    	pstmtGetDynamicDbname.setInt(1, dynamicfieldId);
	    	rsGetDynamicDbname = pstmtGetDynamicDbname.executeQuery();
	        if(rsGetDynamicDbname.next()){        
	        	dynamicDbName = rsGetDynamicDbname.getString(1);
	        }
	    	
	    }finally{
	    	ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtGetDynamicDbname;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsGetDynamicDbname;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return dynamicDbName;
	  }


	public int getDynamicTableId(Connection con,int dynamicfieldId)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGetDynamicDbname=null;
	    ResultSet rsGetDynamicDbname = null;   
	   int dynamicTableId=0;
	    try{      
	    	 	      
	    	pstmtGetDynamicDbname = con.prepareStatement(SQLUtil.GET_DYNAMIC_TABLEID); 
	    	pstmtGetDynamicDbname.setInt(1, dynamicfieldId);
	    	rsGetDynamicDbname = pstmtGetDynamicDbname.executeQuery();
	        if(rsGetDynamicDbname.next()){        
	        	dynamicTableId = rsGetDynamicDbname.getInt(1);
	        }
	    	
	    }finally{
	    	ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] = pstmtGetDynamicDbname;
		      ArrayOfResultSet = new ResultSet[1];
		      ArrayOfResultSet[0] = rsGetDynamicDbname;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return dynamicTableId;
	  }
	
	public int getPageCount(Connection con, TPCSUser ui, String searchCriteria) throws SQLException {
		// TODO Auto-generated method stub
		int pageCount=0;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer query=new StringBuffer(SQLUtil.MATERIAL_PAGE_COUNT);
			
			if(searchCriteria!=null && !searchCriteria.isEmpty())
			{
			 query.append("WHERE " +searchCriteria); 	
			}
		System.out.println("query in count:"+query);
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
	
	public MaterialMaster getAttachFiles(Connection con, int matId) throws SQLException {
	    PreparedStatement pstmt=null;    
	    ResultSet rs = null;    
	    MaterialMaster mm=new MaterialMaster();
	    try{

//	    	List<MaterialMaster> attachFiles=new ArrayList<MaterialMaster>();
		    List<MaterialMaster> images=new ArrayList<MaterialMaster>();
		    
//	    	pstmt = con.prepareStatement(SQLUtil.SELECT_MATERIAL_ATTACHMENT);
//	    	pstmt.setInt(1, matId);
//	        rs = pstmt.executeQuery();
//	      while(rs.next()){
//	    	  MaterialMaster objUrl=new MaterialMaster();
//	    	  objUrl.setMatId(rs.getInt(1));
//	    	  objUrl.setUrlType(rs.getString(2));
//	    	  objUrl.setUrl(rs.getString(3));
//	    	  attachFiles.add(objUrl);
//	      }
	      pstmt = con.prepareStatement(SQLUtil.SELECT_MATERIAL_IMAGES);
	    	pstmt.setInt(1, matId);
	        rs = pstmt.executeQuery();
	      while(rs.next()){
	    	  MaterialMaster objUrl=new MaterialMaster();
	    	  objUrl.setMatId(rs.getInt(1));
	    	  objUrl.setPicPath(rs.getString(2));
	    	  //objUrl.setUrl(rs.getString(3));
	    	  images.add(objUrl);
	      }
//	      mm.setAttachments(attachFiles);
	      mm.setImages(images);
	      
	      
	    }finally{     
	      
	      DatabaseConnection.closeAll(pstmt,rs);
	    }
	    return mm;
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
	public List<MaterialMaster> getDynamicFields(Connection con,String tableIds,int fixed)throws SQLException,Exception{
		    PreparedStatement ArrayOfPreparedStatement[] = null;
		    ResultSet ArrayOfResultSet[] = null;
		    PreparedStatement pstmtGetStatus=null;
		    ResultSet rsGetStatus = null;  
		    StringBuffer objBuffer = new StringBuffer();
		    List<MaterialMaster> listStatus=new ArrayList<MaterialMaster>();
		    try{ 
		    	if(fixed==1){
		    	 	objBuffer.append(SQLUtil.GET_DYNAMICFIELDS);
	 		    	objBuffer.append("("+tableIds+") AND fixed="+fixed+"  ORDER BY df.tableId");
		    	}
		    	else{
		    		objBuffer.append(SQLUtil.GET_DYNAMICFIELDS);
	 		    	objBuffer.append("("+tableIds+") AND fixed="+fixed+"  ORDER BY df.tableId");
		    	}
		      pstmtGetStatus = con.prepareStatement(objBuffer.toString());      
		      rsGetStatus = pstmtGetStatus.executeQuery();
		      while(rsGetStatus.next()){
		    	  MaterialMaster objSO = new MaterialMaster();
		    	objSO.setId(rsGetStatus.getInt(1));
		    	objSO.setName(rsGetStatus.getString(2));
		    	objSO.setPageFieldName(rsGetStatus.getString(3));
		       
		        listStatus.add(objSO);
		      }
		    }finally{      
		      ArrayOfPreparedStatement = new PreparedStatement[1];
		      ArrayOfPreparedStatement[0] =  pstmtGetStatus;
		      ArrayOfResultSet = new ResultSet[1];      
		      ArrayOfResultSet[0] = rsGetStatus;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		    }
		    return listStatus;
		  }

	
	public MaterialMaster insertMaterialMaster(Connection con, TPCSUser ui,
			MaterialMaster objMatP,FileImport objBean)throws SQLException,Exception{
		// TODO Auto-generated method stub
		boolean isInserted=false;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;   
		MaterialMaster mat=new MaterialMaster();
		try
		{
			
			
			StringBuffer query=new StringBuffer(SQLUtil.IS_MATERIAL_EXIST);
			
			if(objMatP.getMode().equals("e")){
				query.append(" and Mat_id<>"+objMatP.getMatId());	
			}
			
			pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1,objMatP.getMatCode());
			pstmt.setString(2,objMatP.getShortName());
			/*pstmt.setString(3,objMatP.getSpec1());
			pstmt.setString(4,objMatP.getSpec2());
			pstmt.setString(5,objMatP.getSpec3());
			pstmt.setString(6,objMatP.getSpec4());
			pstmt.setString(7,objMatP.getSpec5());
			pstmt.setString(8,objMatP.getSpec6());
			pstmt.setString(9,objMatP.getSpec7());
			pstmt.setString(10,objMatP.getSpec8());
			pstmt.setString(11,objMatP.getSpec9());
			pstmt.setString(12,objMatP.getSpec10());
			System.out.println("Spec1=="+objMatP.getSpec1());
			System.out.println("getMatCode=="+objMatP.getMatCode());
			System.out.println("getShortName=="+objMatP.getShortName());*/
			System.out.println("query.toString()=="+query.toString());
			rs=pstmt.executeQuery();
			
			int icount=0;
			while(rs.next()){ 
				icount=Integer.parseInt(rs.getString(1));
				System.out.println("count="+rs.getString(1));
			}
			mat.setMaterialExists(icount>0?true:false);

		if(!mat.isMaterialExists()){	
			cstmt=con.prepareCall("{call pr_insert_itemmaster(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			SQLXML sqlxml = con.createSQLXML();
			
			cstmt.setInt(1,ui.getUserId());
			cstmt.setInt(2,ui.getCompanyId());
			cstmt.setInt(3,ui.getLocationId());
			cstmt.setInt(4,ui.getYearId());
			cstmt.setString(5,objMatP.getMatCode());
			cstmt.setString(6,objMatP.getShortName());
			cstmt.setFloat(7, objMatP.getStdCost());
			cstmt.setString(8, objMatP.getCostCurrency());
		
			cstmt.setString(9,objMatP.getSpec1());
			cstmt.setString(10,objMatP.getSpec2());
			cstmt.setString(11,objMatP.getSpec3());
			cstmt.setString(12,objMatP.getSpec4());
			cstmt.setString(13,objMatP.getSpec5());
			cstmt.setString(14,objMatP.getSpec6());
			cstmt.setString(15,objMatP.getSpec7());
			cstmt.setString(16,objMatP.getSpec8());
			cstmt.setString(17,objMatP.getSpec9());
			cstmt.setString(18,objMatP.getSpec10());
			cstmt.setString(19, objMatP.getStockKeepingUnit());
			cstmt.setString(20, objMatP.getBomUom());
			cstmt.setString(21,objMatP.getPurchaseUOM());
			cstmt.setString(22, objMatP.getBomUnit());
			cstmt.setString(23, objMatP.getPurchaseUnit());
			
			cstmt.setInt(24, objMatP.getLeadTime());
			cstmt.setFloat(25, objMatP.getMinLevel());
			cstmt.setFloat(26, objMatP.getMaxLevel());
			cstmt.setFloat(27, objMatP.getMinOrderQty());
			cstmt.setFloat(28, objMatP.getExcessAllowance());
			cstmt.setInt(29, objMatP.getNegativeStock());
			cstmt.setInt(30, objMatP.getIssueWithIO());
			cstmt.setString(31, objMatP.getTaxCategory());
			cstmt.setInt(32, objMatP.getItemTrackingApplicable());
			cstmt.setInt(33, objMatP.getExpiryApplicable());
			cstmt.setString(34, objMatP.getBomNumber());
			cstmt.setString(35, objMatP.getDefaultSizeRange());
			cstmt.setInt(36,objMatP.getDefaultLeatherId());
			cstmt.setInt(37, objMatP.getDefaultColourId());
			cstmt.setString(38, objMatP.getBuyerStyleNo());
			cstmt.setString(39, objMatP.getSampleStyleNo());
			cstmt.setString(40, objMatP.getAbcClass());
			cstmt.setString(41, objMatP.getXyzClass());
			cstmt.setString(42, objMatP.getRouteRef());
			cstmt.setInt(43, objMatP.getImportMat());
			
			cstmt.setString(44,objMatP.getBasicUnit());
			cstmt.setInt(45,objMatP.getGroupId());
			cstmt.setString(46,objMatP.getStockLocation());
			cstmt.setString(47,objMatP.getBinNumber());
			cstmt.setInt(48, objMatP.getTrackInventory());
			cstmt.setInt(49, objMatP.getInventoryAccountId());
			cstmt.setInt(50, objMatP.getSellMaterial());
			cstmt.setString(51, objMatP.getSalesDesc());
			cstmt.setFloat(52,objMatP.getSellingPrice());
			cstmt.setInt(53, objMatP.getIncludeTax());
			cstmt.setInt(54, objMatP.getPurchaseMaterial());
			cstmt.setString(55,objMatP.getPurchaseDesc());
			cstmt.setInt(56, objMatP.getPurchaseAccountId());
			cstmt.setFloat(57, objMatP.getCostPrice());
			cstmt.setFloat(58,objMatP.getReorderLevel());
			cstmt.setFloat(59, objMatP.getReorderQty());
			cstmt.setInt(60, objMatP.getStatus());
			cstmt.setString(61,objMatP.getMode());
			cstmt.setInt(62,objMatP.getMatId());
			cstmt.setString(63,ui.getUserName());
			cstmt.setInt(64, objMatP.getSizeApplicable());
			cstmt.setInt(65,objMatP.getSizeApplicable());
			cstmt.setInt(66, objMatP.getStockReservation());
			cstmt.setInt(67, objMatP.getInspectionRequired());
			cstmt.setInt(68, objMatP.getBarcodeRequired());
			sqlxml.setString(objMatP.getSqlxmlDynamicFields());
		    cstmt.setSQLXML(69,sqlxml);
		    
			rs=cstmt.executeQuery();
			if(rs.next()){
				mat.setMatId(rs.getInt(1));
			}
			
			if(mat.getMatId()>0){
			pstmt = con.prepareStatement(SQLUtil.INSERT_ITEM_ATTACHMENT);
			pstmt.setInt(1,mat.getMatId());

	          
	          	List list= objBean.getFileItems();
	          	String parentFolder="material/";
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
				String fileType="attachment";
				  int idx=1;
	        while ( i.hasNext () ){ 
	        	item = (FileItem)i.next();
	        	
	        	if ( !item.isFormField () ){
	        		String fieldName=item.getFieldName();
	        		System.out.println("fieldName :"+fieldName);
//	        		fieldName=fieldName.substring(fieldName.length()-1);
	        		
	        		fileType="attachment";
	        		if(fieldName.equals("image_file")){
	        			fileType="image";
	        		}
//	            	int sharable=Validator.convertToInteger(fieldName);
	        		String source = item.getName();
	        		System.out.println("item.getName() :"+item.getName());
	        		if(source.lastIndexOf(".")==-1){
	        			continue;
	        		}
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
	        		strPrefix=fileActualName+"_"+mat.getMatId()+"_"+idx+".";
	        		System.out.println("folderPath+strPrefix+strSuffix :"+folderPath+strPrefix+strSuffix);
	        		file=new File(folderPath+strPrefix+strSuffix);
	  			item.write(file);
	  			idx++;
//	  			String [] arFolder=folderPath.split("/");
//				String parentFolder=arFolder[arFolder.length-1]+"/";
				//pstmt.setString(2, strSuffix);
				
				/*System.out.println("parentFolder+strPrefix+strSuffix--->"+parentFolder+strPrefix+strSuffix);*/
				pstmt.setString(2, parentFolder+strPrefix+strSuffix);
				//pstmt.setString(4, fileType);
				
				pstmt.addBatch();
	        	}
	        }
	        pstmt.executeBatch();
			}
			//System.out.println("matId DAO----> in insert"+matId);
			//				    bFlag=(icount)>0?true:false;	
		}
		}
		finally
		{
			DatabaseConnection.closeAll(cstmt, null);
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return mat;
	}


	public MaterialMaster saveMaterial(Connection con, MaterialMaster objM,TPCSUser ui) throws SQLException, UnknownHostException {

		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean bFlag=false;
		try {

			pstmt= con.prepareStatement(SQLUtil.IS_MATERIAL_EXIST);
			pstmt.setString(1, objM.getShortName());
			pstmt.setString(2, objM.getMatCode());
			rs= pstmt.executeQuery();
			while(rs.next()){
				bFlag = (rs.getInt(1)>0)?true:false;
			}
			objM.setMaterialExists(bFlag);

			if(!objM.isMaterialExists()){

				pstmt=con.prepareStatement(SQLUtil.INSERT_INTO_MATERIAL);

				pstmt.setString(1,objM.getMatCode());
				pstmt.setString(2,objM.getShortName());
				//					pstmt.setString(3,objM.getSpec1());
				//					pstmt.setString(4,objM.getSpec2());
				//					pstmt.setString(5,objM.getSpec3());
				//					pstmt.setString(6,objM.getSpec4());
				//					pstmt.setString(7,objM.getSpec5());
				//					pstmt.setString(8,objM.getSpec6());
				//					pstmt.setString(9,objM.getSpec7());
				pstmt.setString(3,objM.getBasicUnit());
				pstmt.setInt(4,objM.getGroupId());
				pstmt.setString(5,objM.getStockLocation());
				pstmt.setString(6,objM.getBinNumber());
				pstmt.setFloat(7,objM.getReorderLevel());
				pstmt.setFloat(8,objM.getSellingPrice());
				pstmt.setFloat(9,objM.getCostPrice());

				//					pstmt.setFloat(16,objM.getExcessAllowance());

				pstmt.setString(10,objM.getSalesDesc());
				pstmt.setString(11,objM.getPurchaseDesc());
				pstmt.setInt(12,objM.getStatus());
				pstmt.setInt(13,objM.getTrackInventory());
				pstmt.setInt(14,objM.getPurchaseMaterial());
				pstmt.setInt(15,objM.getSellMaterial());
				pstmt.setInt(16,objM.getInventoryAccountId());
				pstmt.setInt(17,objM.getPurchaseAccountId());
				pstmt.setString(18,String.valueOf(InetAddress.getLocalHost().getHostAddress()));
				pstmt.setInt(19,ui.getUserId());

				int iCount = pstmt.executeUpdate();
				bFlag=(iCount>0)?true:false;
				objM.setInserted(bFlag);
				//   System.out.println("bFlag-------save----------->"+bFlag);
			}


		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[5];
			ArrayOfPreparedStatement[0] = pstmt;
			ArrayOfResultSet = new ResultSet[5];      
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return objM;
	}
	public MaterialMaster saveUOM(Connection con, MaterialMaster objM,TPCSUser ui) throws SQLException {

		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean bFlag=false;
		try {
			pstmt= con.prepareStatement(SQLUtil.IS_UOM_EXIST);
			pstmt.setString(1, objM.getBasicUnit());
			rs= pstmt.executeQuery();
			while(rs.next()){
				bFlag = (rs.getInt(1)>0)?true:false;
			}
			objM.setUOMExists(bFlag);

			if(!objM.isUOMExists()){
				pstmt=con.prepareStatement(SQLUtil.INSERT_INTO_UOM);	
				pstmt.setString(1, objM.getBasicUnit());
				int iCount = pstmt.executeUpdate();
				bFlag=(iCount>0)?true:false;
				objM.setInserted(bFlag);
			}
		}
			finally{
				ArrayOfPreparedStatement = new PreparedStatement[1];
				ArrayOfPreparedStatement[0] = pstmt;
				ArrayOfResultSet = new ResultSet[1];      
				ArrayOfResultSet[0] = rs;
				DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
			}
			return objM;
		}
	 public String doGetMaterialModalData(Connection con) throws SQLException{
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  StringBuffer buffer=new StringBuffer("");
		  try {
			  String query="SELECT group_type_id,group_type FROM group_type ";
		
			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
			buffer.append("<grouptypes>");
			while(rs.next()){
				buffer.append("<grouptype>");
	  	  	  	
	  	  	  	buffer.append("<grouptypeid>");
	  	  	  	buffer.append(rs.getInt(1));
	  	  	  	buffer.append("</grouptypeid>");
	  	  	  	buffer.append("<grouptypename>");
	  	  	  	buffer.append(rs.getString(2));
	  	  	  	buffer.append("</grouptypename>");
	  	  	  	
	  	  	  	buffer.append("</grouptype>");
			}
			buffer.append("</grouptypes>");
		  } 
		  finally {
			  DatabaseConnection.closeAll(pst,rs);
		}
		  return buffer.toString();
	  }
	 
	 
	public boolean deleteMaterialMaster(Connection con, int matId) throws SQLException {
		
		
		
		// TODO Auto-generated method stub
		  PreparedStatement ArrayOfPreparedStatement[] = null;
	      ResultSet ArrayOfResultSet[] = null;
		  PreparedStatement pstmt=null;   
	      PreparedStatement pstmtDeleteMaterialDynamic=null;
	      PreparedStatement pstmtDeleteMaterialAttach=null;
		boolean isDeleted=false;
		try
		{
			int iCount=0;
		     pstmt = con.prepareStatement(SQLUtil.DELETE_ITEM);
		     pstmt.setInt(1, matId);
		     int iMatCount = pstmt.executeUpdate();
		     
		     pstmt = con.prepareStatement(SQLUtil.DELETE_ITEM_DETAILS);
		     pstmt.setInt(1, matId);
		     //rs=pstmt.executeUpdate();
		     int iMCount = pstmt.executeUpdate();
//		     isDeleted=iCount.length>0?true:false;
			pstmtDeleteMaterialDynamic = con.prepareStatement(SQLUtil.DELETE_ITEM_DYNAMIC);
			pstmtDeleteMaterialDynamic.setInt(1, matId);
		    iCount = pstmtDeleteMaterialDynamic.executeUpdate();
		        
		    pstmtDeleteMaterialAttach = con.prepareStatement(SQLUtil.DELETE_MATERIAL_IMAGES);
		    pstmtDeleteMaterialAttach.setInt(1, matId);
		    iCount = pstmtDeleteMaterialAttach.executeUpdate();
	
		    System.out.println("iCount------"+iMatCount);
			if(iMatCount>0)
			{
				isDeleted=true;
			}
		}
		
		finally{          
		       
	        ArrayOfPreparedStatement = new PreparedStatement[3];    
	        ArrayOfPreparedStatement[0] = pstmt;
	        ArrayOfPreparedStatement[1] = pstmtDeleteMaterialDynamic;
	        ArrayOfPreparedStatement[2] = pstmtDeleteMaterialAttach;
	        ArrayOfResultSet = new ResultSet[0];
	        DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	      }
		return isDeleted;
	}
	
	
	public MaterialMaster getMaterialMasterInfo(Connection con, TPCSUser ui,int matId,int iScreenId)throws SQLException,Exception {
		// TODO Auto-generated method stub
		MaterialMaster objMat=new MaterialMaster(); 
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		 ResultSet rsGetInfoDynamic = null;
		 PreparedStatement pstmtGetInfoDynamic = null;
		 
		 //Added for Dynamic Fields
		 DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		 List<DynamicFields> dynamicFieldsListMaterial = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 3);
		try
		{
			pstmt = con.prepareStatement(SQLUtil.GET_MATERIAL_INFO);
			pstmt.setInt(1,matId);
			//System.out.println("Edit is:"+pstmt.toString());
			rs = pstmt.executeQuery();
			
			
			//System.out.println("Result :"+pstmt.toString());
			
			 pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_ITEM_DYNAMIC);
			 pstmtGetInfoDynamic.setInt(1,matId);
			 rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();
//				 System.out.println("Material id :"+matId);
			while (rs.next()) {
				objMat.setMatId(rs.getInt(1));
				objMat.setGroupName(rs.getString(2));
				objMat.setMatCode(rs.getString(3));
				objMat.setShortName(rs.getString(4));
				objMat.setStdCost(rs.getFloat(5));
				objMat.setCostCurrency(rs.getString(6));
				objMat.setStockKeepingUnit(rs.getString(7));
				objMat.setBomUom(rs.getString(8));
				objMat.setPurchaseUOM(rs.getString(9));
				objMat.setSpec1(rs.getString(10));
				objMat.setSpec2(rs.getString(11));
				objMat.setSpec3(rs.getString(12));
				objMat.setSpec4(rs.getString(13));
				objMat.setSpec5(rs.getString(14));
				objMat.setSpec6(rs.getString(15));
				objMat.setSpec7(rs.getString(16));
				objMat.setSpec8(rs.getString(17));
				objMat.setSpec9(rs.getString(18));
				objMat.setSpec10(rs.getString(19));
				objMat.setBomNumber(rs.getString(20));
				objMat.setIsActive(rs.getInt(21));
				objMat.setMinLevel(rs.getFloat(22));
				objMat.setMaxLevel(rs.getFloat(23));
				objMat.setReorderLevel(rs.getFloat(24));
				objMat.setReorderQty(rs.getFloat(25));
				objMat.setMinOrderQty(rs.getFloat(26));
				objMat.setLeadTime(rs.getInt(27));
				objMat.setDefaultSizeRange(rs.getString(28));
				objMat.setDefaultLeatherId(rs.getInt(29));
				objMat.setDefaultColourId(rs.getInt(30));
				objMat.setBuyerStyleNo(rs.getString(31));
				objMat.setSampleStyleNo(rs.getString(32));
				objMat.setAbcClass(rs.getString(33));
				objMat.setXyzClass(rs.getString(34));
				objMat.setRouteRef(rs.getString(35));
				objMat.setImportMat(rs.getInt(36));
				objMat.setItemTrackingApplicable(rs.getInt(37));
				objMat.setExpiryApplicable(rs.getInt(38));
				objMat.setExcessAllowance(rs.getFloat(39));
				objMat.setNegativeStock(rs.getInt(40));
				objMat.setIssueWithIO(rs.getInt(41));
				objMat.setTaxCategory(rs.getString(42));
				objMat.setDefaultColourName(rs.getString(43));
				objMat.setGroupId(rs.getInt(44));
				objMat.setGroupType(rs.getString(45));
				objMat.setSizeApplicable(rs.getInt(46));
				objMat.setColorApplicable(rs.getInt(47));
				objMat.setStockReservation(rs.getInt(48));
				objMat.setInspectionRequired(rs.getInt(49));
				objMat.setBarcodeRequired(rs.getInt(50));

			 if(rsGetInfoDynamic.next()){
			        	for(DynamicFields dynamicFields : dynamicFieldsListMaterial) {
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
					    			  }
			        			}
					        	dynamicFields.setFieldValue(fieldValue);
			        		}
				        }
			        }
			
			 objMat.setMatDynList(dynamicFieldsListMaterial);
			 
//			 
			  
			 pstmt = con.prepareStatement("select top 1 Pic_Path from Item_image where item_Id=? order by item_Id");
			 pstmt.setInt(1, matId);
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				 objMat.setPrimaryImageUrl(rs.getString(1));
			 }
		}
		}
		finally
		{
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return objMat;
	}	
	
	
	public boolean isMaterialExist(Connection con, MaterialMaster objMat) throws SQLException,Exception {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean bflag=false;
		try
		{
			
			  StringBuffer query=new StringBuffer(SQLUtil.IS_MATERIAL_EXIST);
			
			if(objMat.getMode().equals("e")){
				query.append(" and item_Id<>"+objMat.getMatId());	
			}
		//System.out.println("query.toString()-IS_MATERIAL_EXIST-----"+query.toString());
		pstmt=con.prepareStatement(query.toString());
		pstmt.setString(1,objMat.getShortName());
		pstmt.setString(2,objMat.getMatCode());
		rs=pstmt.executeQuery();
		int icount=0;
		while(rs.next())
		{ 
			 icount=Integer.parseInt(rs.getString(1));
					}
		bflag=(icount>0)?true:false;
		}
		finally{
		DatabaseConnection.closeAll(pstmt,rs);
		}
		return bflag;
	}
	public boolean deleteAttachment(Connection con, int matId,String fileName) throws SQLException {
		PreparedStatement pstmt=null;    
	    ResultSet rs = null;    
	    boolean bFlag=false;
	    try{
	    	int count=0;
	    	pstmt = con.prepareStatement(SQLUtil.DELETE_MAT_ATTACH);
	    	pstmt.setInt(1, matId);
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
	public List<Currency> getCurrencyList(Connection con, TPCSUser userInfo) throws SQLException {
		PreparedStatement pstmt=null;    
	    ResultSet rs = null;
	    List<Currency> curList=new ArrayList<Currency>();
		try {
			
			
			pstmt = con.prepareStatement(" select currency_id,currency_name,currency_symbol from currency_master where company_id=? and location_id=? ");
	    	pstmt.setInt(1, userInfo.getCompanyId());
	    	pstmt.setInt(2, userInfo.getLocationId());
	    	rs=pstmt.executeQuery();
	    	while(rs.next()){
	    		Currency cur=new Currency();
	    		cur.setCurrencyId(rs.getInt(1));
	    		cur.setCurrencyName(rs.getString(2));
	    		cur.setCurrencySymbol(rs.getString(3));
	    		curList.add(cur);
	    	}
		} finally{     
		      
		      DatabaseConnection.closeAll(pstmt,rs);
		    }
		return curList;
	}
	
	
	
	
	
	public MaterialMaster deleteBulkMaterialMaster(Connection con, MaterialMaster mat) throws SQLException {
		
		 PreparedStatement ArrayOfPreparedStatement[] = null;
	      ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null; 
		PreparedStatement pstmtDeleteMaterialMasterDynamic=null;
		try{
			int iCount=0;
			pstmt = con.prepareStatement(SQLUtil.DELETE_ITEM_ROW);
			pstmt.setInt(1, mat.getMatId());
			iCount = pstmt.executeUpdate();
			
		    // isDeleted=iCount.length>0?true:false;
			pstmtDeleteMaterialMasterDynamic = con.prepareStatement(SQLUtil.DELETE_ITEM_DYNAMIC);
			pstmtDeleteMaterialMasterDynamic.setInt(1, mat.getMatId());
			    iCount = pstmtDeleteMaterialMasterDynamic.executeUpdate();

			if(iCount>0){
				mat.setDeleted(true);
			}
		}
		finally{  
			
			 ArrayOfPreparedStatement = new PreparedStatement[2];    
		        ArrayOfPreparedStatement[0] = pstmt;
		        ArrayOfPreparedStatement[1] = pstmtDeleteMaterialMasterDynamic;
		        ArrayOfResultSet = new ResultSet[0];
			DatabaseConnection.closeAll(pstmt,null);
		}
		return mat;
	}

	
	
	public boolean bulkActionMaterialMaster(Connection con, String[] matId,String action_mode) throws SQLException {

		Statement pstmt=null;
		boolean isBulkAction=false;
		try	{
			//System.out.println("sizerangeId.length="+sizerangeId.length);
			for(int i=0;i<matId.length;i++){
				System.out.println("Mat ID: "+matId[i]);
				if(action_mode.equals("1"))	{
					pstmt = con.createStatement();
					pstmt.addBatch("update item set Is_Active=1 where Mat_Id="+matId[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
				else{
					pstmt = con.createStatement();
					pstmt.addBatch("update item set Is_Active=0 where Mat_Id="+matId[i]);
					int count[]=pstmt.executeBatch();

					isBulkAction=count.length>0?true:false;
				}
			}
		}

		finally	{
			DatabaseConnection.closeAll(pstmt, null);
		}
		return isBulkAction;
	}
	public String doGetItemGroupData(TPCSUser userInfo,Connection con,int groupId)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int getgroupId=0;
		String groupType="";
		int itemTracking=0;
		int expiryApplicable=0;
		int allowNegativeStock=0;
		int issueWithIo=0;
		String taxCategory="";
		int sizeApplicable=0;
		int colourApplicable=0;
		int reservationApplicable=0;
		int inspectionRequired=0;
		int barcodeRequired=0;
		StringBuffer buffer=new StringBuffer();
		try{     
			
				pstmt=con.prepareStatement(SQLUtil.GET_ITEMGROUP_DATA);
				pstmt.setInt(1,groupId);
				rs=pstmt.executeQuery();

			if(rs.next()){
				getgroupId=rs.getInt(1);
				groupType=rs.getString(2);
				itemTracking=rs.getInt(3);
				expiryApplicable=rs.getInt(4);
				allowNegativeStock=rs.getInt(5);
				issueWithIo=rs.getInt(6);
				taxCategory=rs.getString(7);
				sizeApplicable=rs.getInt(8);
				colourApplicable=rs.getInt(9);
				reservationApplicable=rs.getInt(10);
				inspectionRequired=rs.getInt(11);
				barcodeRequired=rs.getInt(12);
			}
			buffer.append("<itemgroup_datas>");
			buffer.append("<itemgroup_data>");
			buffer.append("<groupType>");
			buffer.append(StringEscapeUtils.escapeXml(groupType));
			buffer.append("</groupType>");
			buffer.append("<itemTracking>");
			buffer.append(itemTracking);
			buffer.append("</itemTracking>");
			buffer.append("<expiryApplicable>");
			buffer.append(expiryApplicable);
			buffer.append("</expiryApplicable>");
			buffer.append("<allowNegativeStock>");
			buffer.append(allowNegativeStock);
			buffer.append("</allowNegativeStock>");
			buffer.append("<issueWithIo>");
			buffer.append(issueWithIo);
			buffer.append("</issueWithIo>");
			buffer.append("<taxCategory>");
			buffer.append(taxCategory);
			buffer.append("</taxCategory>");
			buffer.append("<sizeApplicable>");
			buffer.append(sizeApplicable);
			buffer.append("</sizeApplicable>");
			buffer.append("<colourApplicable>");
			buffer.append(colourApplicable);
			buffer.append("</colourApplicable>");
			buffer.append("<reservationApplicable>");
			buffer.append(reservationApplicable);
			buffer.append("</reservationApplicable>");
			buffer.append("<inspectionRequired>");
			buffer.append(inspectionRequired);
			buffer.append("</inspectionRequired>");
			buffer.append("<barcodeRequired>");
			buffer.append(barcodeRequired);
			buffer.append("</barcodeRequired>");
			buffer.append("</itemgroup_data>");
			buffer.append("</itemgroup_datas>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{    
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return buffer.toString();//desc+"&1"+uom+"&1"+rate;
	}	
	
	
}
	
	
	
	
	
	



