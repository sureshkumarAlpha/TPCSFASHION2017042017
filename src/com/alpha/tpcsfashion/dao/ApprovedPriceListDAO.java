package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.ApprovedPriceList;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.Validator;


public class ApprovedPriceListDAO {
	
	public List<Map<String,String>> getAllApprovedPriceListOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,String strSearchCriteria,List<String> orderBy,int iScreenId,int tableId)throws SQLException,Exception{
	    CallableStatement cstmt=null;
	    ResultSet rs = null; 
	    PreparedStatement pstmtGetInfoDynamic =null;
	    ResultSet rsGetEnquiryDetailInfoDynamic=null;
	    List<Map<String,String>> assortList=new ArrayList<Map<String,String>>();
	    PreparedStatement pstmt =null;
		ResultSet rsAttach=null;
	    String strbuffer="";
	    try{
	    	
	    	DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();  
	        List<DynamicFields> dynamicFieldsListApprovedPriceList = dynamicFieldsDAO.getDynamicFields(con,iScreenId, tableId);
	        GroupMasterDAO objDAO=new GroupMasterDAO();
	        Map<String,String> colPreMap=objDAO.getColPreMap(con,SubdocumentId.APPROVED_PRICELIST);
	        
	    	StringBuffer query = new StringBuffer(SQLUtil.GET_APPROVED_PRICE_LIST);
//	    	query.append(" where  se.company_Id=" + userInfo.getCompanyId());
//			query.append(" and se.year_id=" + userInfo.getYearId());
//			query.append(" and se.location_id=" + userInfo.getLocationId());
	 
	    	if(strSearchCriteria!=null && !strSearchCriteria.isEmpty())
			{
	        	 query.append(strSearchCriteria);
	         }
	    	 
	    	 query.append(" order by pr.supplier_id,pr.purchase_price_id desc,");
	    	 
	         if(orderBy!=null && !orderBy.isEmpty())
		      {
		    	  for(String strOrderBy:orderBy){
		    		 if(strOrderBy!=null)
		    		 {
		    			 query.append(strOrderBy+",");  
		    		 }
			    	  
			      }
		    	 
		      }
	         
	          strbuffer=query.toString();
	    	  strbuffer=strbuffer.substring(0, strbuffer.length() - 1);
	    	
	    	  System.out.println("strbuffer=="+strbuffer);
	         cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
	         cstmt.setString(1,strbuffer);
	         cstmt.setInt(2,pc.iStart);
	         cstmt.setInt(3,pc.iEnd);
	         rs=cstmt.executeQuery();
	         
	      while(rs.next()){  
	    	 
	    	  Map<String,String> map = new HashMap<String,String>();
	    	  
	    	  map.put("ApprovedPriceList.PurchasePriceId", rs.getString("Purchase_price_id"));
	    	  map.put("ApprovedPriceList.SupplierId", rs.getString("supplier_id"));
		     //   map.put(colPreMap.get("enq_no"), rs.getString("enq_no"));
		    	map.put(colPreMap.get("supplier_id"), rs.getString("party_name"));
		        map.put(colPreMap.get("item_id"), rs.getString("item_Name"));
		        map.put(colPreMap.get("color_id"), rs.getString("Variant_Code"));
		        map.put(colPreMap.get("rate"), rs.getString("Rate"));
		        map.put(colPreMap.get("uom"), rs.getString("UOM"));
		        map.put(colPreMap.get("currency_name"), rs.getString("Currency_Name"));
		        map.put(colPreMap.get("lead_time_days"), rs.getString("Lead_Time_Days"));
		        map.put(colPreMap.get("supplier_desc"), rs.getString("Supplier_Desc"));
		        map.put(colPreMap.get("moq"), rs.getString("MOQ"));
		        map.put(colPreMap.get("size_range_id"), rs.getString("Size_Range"));
		        
				
		      //Start - Added for dynamic fields
		        pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_APPROVED_PRICELIST_DETAIL_DYNAMIC);
		        
		        
		        pstmtGetInfoDynamic.setInt(1, Validator.convertToInteger(rs.getString("Purchase_price_id")));  
		        
		        rsGetEnquiryDetailInfoDynamic = pstmtGetInfoDynamic.executeQuery();
		        if(rsGetEnquiryDetailInfoDynamic.next()){
				      
					 for(DynamicFields dynamicFields : dynamicFieldsListApprovedPriceList) {
						  if(!dynamicFields.isFixed()){
							  String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							  String fieldValue = rsGetEnquiryDetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetEnquiryDetailInfoDynamic.getString(dynamicFields.getDbFieldName());
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
		        
	     
	    	  assortList.add(map);
	    	  map=null;
	      }
	      
	      dynamicFieldsListApprovedPriceList=null;
	      dynamicFieldsDAO=null;
	      objDAO=null;
	      query=null;
	    }
	    finally{
	          
	        DatabaseConnection.closeAll(cstmt,rs);
	      }
	    return assortList;
	  }

	public ApprovedPriceList saveApprovedPriceList(Connection con, ApprovedPriceList aplobj,TPCSUser userinfo,Map<String,String> map,Map<String,String> mapDyn,String editedMode,String sizeInput) throws SQLException {
		Statement stmt = null;
		CallableStatement cstmt=null;
		int purPriceID;
		try
		{

//			String mode=aplobj.getMode();
//			int purPriceid=aplobj.getPurchasePriceId();
			SQLXML sizeSqlxml= con.createSQLXML();
			sizeSqlxml.setString(sizeInput);
			
			cstmt=con.prepareCall("{? = call pr_insertApprovedPriceList(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			SQLXML sqlxml = con.createSQLXML();
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2,userinfo.getDisplayUserName());
			cstmt.setString(3,aplobj.getMode());
			cstmt.setInt(4, aplobj.getPurchasePriceId());
			cstmt.setInt(5, aplobj.getSupplierId());
			cstmt.setInt(6, aplobj.getMatId());
			cstmt.setInt(7, aplobj.getColorId());
			cstmt.setString(8,aplobj.getUom());
			cstmt.setString(9, aplobj.getRate());
			cstmt.setString(10, aplobj.getCurrencyName());
			cstmt.setInt(11, aplobj.getLeadTimeDays());
			cstmt.setString(12,aplobj.getSupplierDesc());
			cstmt.setString(13, aplobj.getMoq());
			cstmt.setInt(14,aplobj.getSizeRangeId());
			
			sqlxml.setString(aplobj.getSqlxmlDynamicFields());
			cstmt.setSQLXML("XmlData",sqlxml);
			cstmt.setSQLXML(16,sizeSqlxml);
//			System.out.println("mode="+aplobj.getMode());
//			System.out.println("id="+aplobj.getPurchasePriceId());
//			System.out.println("supplierId="+aplobj.getSupplierId());
//			System.out.println("Matid"+aplobj.getMatId());
//			System.out.println("Colorid="+aplobj.getColorId());
//			System.out.println("uom="+aplobj.getUom());
//			System.out.println("rate="+aplobj.getRate());
//			System.out.println("currecncyName="+aplobj.getCurrencyName());
//			System.out.println("leatTime"+aplobj.getLeadTimeDays());
//			System.out.println("supplierDesc="+aplobj.getSupplierDesc());
//			System.out.println("MOQ="+aplobj.getMoq());
//			System.out.println("sizeRangeI"+aplobj.getSizeRangeId());
			
			cstmt.registerOutParameter(5, Types.INTEGER);
			
//			int rowsInserted=
					cstmt.executeUpdate();
			
			
			purPriceID = cstmt.getInt(1);
		int	supplierID=cstmt.getInt(5);
		
		boolean bflag=purPriceID>0?true:false;
		
		if(aplobj.getMode().equals("n"))
		{
		aplobj.setPurchasePriceId(purPriceID);
		}
		aplobj.setSupplierId(supplierID);
		
			if(editedMode.equals("e")){
				stmt = con.createStatement();
				List<Integer> detaIdList=new ArrayList<Integer>();
				Iterator it = map.entrySet().iterator();
				Iterator itDyn = mapDyn.entrySet().iterator();

				while(it.hasNext()){
					Map.Entry mCol= (Map.Entry)it.next();
					String strArray[] = ((String) mCol.getKey()).split("_S_");	
					if(strArray.length>1){
							stmt.addBatch("UPDATE Purchase_Price SET "+strArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE purchase_price_id ="+strArray[1]);  
						
							

						detaIdList.add(Integer.parseInt(strArray[1]));
					}
					purPriceID=aplobj.getPurchasePriceId();
					aplobj.setPurchasePriceId(aplobj.getPurchasePriceId());
				}

				while(itDyn.hasNext()){
					Map.Entry mCol= (Map.Entry)itDyn.next();
					String strDynArray[] = ((String) mCol.getKey()).split("_S_");


					if(strDynArray.length>1){
						
						stmt.addBatch("UPDATE Purchase_Price_dynamic SET "+strDynArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE purchase_price_id ="+strDynArray[1]);  
					}
					purPriceID=aplobj.getPurchasePriceId();
					aplobj.setPurchasePriceId(aplobj.getPurchasePriceId());
				}
 								
 					
				stmt.executeBatch();
				
				detaIdList=null;
			}
//System.out.println("aplobj.getPurchasePriceId()=="+aplobj.getPurchasePriceId());
		}
		finally
		{

			DatabaseConnection.closeAll(cstmt, null);

		}
		return aplobj;
	}
	public int updateSizeInputGridData(Connection con, TPCSUser ui,int purPriceid,String x,String valWithId,String insertMode, List<String> sizeIdList,List<String> detailsIdList,List<String> qtylist)throws SQLException,Exception
	{
		 	Statement st = con.createStatement();
		 	Statement stIns = con.createStatement();
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		 int h=0;
		 try{
			 con.setAutoCommit(false);
			 
			 System.out.println("insertMode III MethodGrid="+insertMode);
			 sizeIdList = new ArrayList<String>(new LinkedHashSet<String>(sizeIdList));
			 
			 if(!x.isEmpty() && insertMode.equals("n")){
				 pstmt=con.prepareStatement(" insert into purchase_price_size(purchase_price_id,size_id) values(?,?)");
				 pstmt.setInt(1,purPriceid);
				 System.out.println("price_idGrid="+purPriceid);
				 for(String sizeId:sizeIdList){
					 pstmt.setInt(2,Validator.convertToInteger(sizeId));
//					 System.out.println("sizeIdGrid"+sizeId);
					 pstmt.addBatch();
				 }
				 pstmt.executeBatch();
				 

			 }
			 
	
			 if(!x.isEmpty() && insertMode.equals("e")){
				 
				 for(String detId:detailsIdList)	{
						 
				 pstmt=con.prepareStatement(" BEGIN "
					 		+ " IF NOT EXISTS (SELECT * FROM purchase_price_size "
					 		+ " WHERE  purchase_price_id=? and size_id=?)"
					 		+ " BEGIN"
					 		+ " INSERT INTO purchase_price_size (purchase_price_id,size_id)"
					 		+ " VALUES (?,?)"
					 		+ " END "
					 		+ " END ");
				 for(String sizeId:sizeIdList){
					 pstmt.setInt(1,Validator.convertToInteger(detId));
					 pstmt.setInt(2, Validator.convertToInteger(sizeId));
					 pstmt.setInt(3,Validator.convertToInteger(detId));
					 pstmt.setInt(4, Validator.convertToInteger(sizeId));
					 pstmt.addBatch();
					 System.out.println("sizeIdsizeId===="+sizeId);
				 }
				 pstmt.executeBatch();
				 
				System.out.println("detIdDAO===="+detId);
				
				 
				 }//detid loop
			 }
			 
			 
			 String  value="";
			 String  sizeId="";
			 
			 if(!x.isEmpty()){
				 x  = x.substring(0, x.length() - 1);
				 String arr1[]=x.split(",");
				 
				 for(String m:arr1){
					 
					String ar[]=m.split("&sp");//ar1[0]--value
					
					value=ar[0];//value
					sizeId=ar[1];//sizeId
					String oldVal=ar[2];
					
					String qtyType=oldVal.substring( 0, 2);
					
					
					String sqlQ=" update purchase_price_size set rate="+value+" where purchase_price_id="+purPriceid+" and  size_id="+sizeId;
										
					st.addBatch(sqlQ);
				 }
				 int  a[]=st.executeBatch();
			 }
			 con.commit();
			 sizeIdList=null;
			 detailsIdList=null;
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 finally{
			 DatabaseConnection.closeAll(stIns, null);
			 DatabaseConnection.closeAll(st, null);
			 DatabaseConnection.closeAll(null, rs);
		 }
		 
		 return h ;
	}
	
	public int getPageCount(Connection con, TPCSUser ui, String searchCriteria) throws SQLException {
		// TODO Auto-generated method stub
		int pageCount=0;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer query=new StringBuffer(SQLUtil.GET_APPROVED_PIRCE_LIST_COUNT);
			
			if(searchCriteria!=null && !searchCriteria.isEmpty())
			{
			 query.append(searchCriteria); 	
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
	public ApprovedPriceList getApprovedPriceheat(Connection con,TPCSUser userInfo, int purpriceId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ApprovedPriceList det=new ApprovedPriceList();
		try{

			StringBuffer query = new StringBuffer(SQLUtil.GET_APPROVEDPRICE_HEAD);
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,purpriceId);
			rs=pstmt.executeQuery();


			while(rs.next())
			{
				det.setSupplierId(rs.getInt(1));
				det.setSupplierName(rs.getString(2));
			} 
			
			query=null;
			// TODO Auto-generated method stub
		} 
		finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return det; 
	}
	
	public ApprovedPriceList getApprovedPriceDetailsList(Connection con,TPCSUser userInfo, int purpriceId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		ApprovedPriceList se=new ApprovedPriceList();
		List<ApprovedPriceList> detList=new ArrayList<ApprovedPriceList>();		
		PreparedStatement pstmt=null;
		PreparedStatement pstmtGetInfoDynamic=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		ResultSet rsGetInfoDynamic=null;
		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListApprovedPriceListDetails = dynamicFieldsDAO.getDynamicFields(con,2,2);
		try{
			 String detailsid="";
			StringBuffer query = new StringBuffer(SQLUtil.GET_APPROVEDPRICE_DETAIL_LIST);
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,purpriceId);
			rs=pstmt.executeQuery();

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_APPROVED_PRICELIST_DETAIL_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,purpriceId);
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

			Map<String,String> detMap=new LinkedHashMap<String ,String>();

			while(rs.next())
			{
				
				ApprovedPriceList det=new ApprovedPriceList();
				det.setPurchasePriceId(rs.getInt(1));
				det.setSupplierId(rs.getInt(2));
				det.setSupplierName(rs.getString(3));
				det.setMatId(rs.getInt(4));
				det.setMatName(rs.getString(5));
				det.setColorId(rs.getInt(6));
				det.setColorName(rs.getString(7));
				det.setRate(rs.getString(8));
				det.setUom(rs.getString(9));
				det.setCurrencyName(rs.getString(10));
				det.setLeadTimeDays(rs.getInt(11));
				det.setSupplierDesc(rs.getString(12));
				det.setMoq(rs.getString(13));
				det.setSizeRangeId(rs.getInt(14));
				det.setSizeRange(rs.getString(15));
				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListApprovedPriceListDetails) {
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
				// size grid edit start
				 ApprovedPriceList sizeGrid=new ApprovedPriceList();
//				pstmt=con.prepareStatement(" select size_range_id from purchase_price where supplier_id=? ");
//				pstmt.setInt(1, purpriceId);
//				rs=pstmt.executeQuery();
//				if(rs.next()){
//					sizeGrid.setSizeRangeGridid(rs.getInt(1));
//				}
				
				int sizerange_id=det.getSizeRangeId();
				if(sizerange_id!=0){
				 List<String> size=new ArrayList<String>();
				 pstmt = con.prepareStatement(SQLUtil.SELECT_SIZE_FOR_SIZE_RANGE_ID);
			 	    pstmt.setInt(1, sizerange_id);
			 	   rs2 = pstmt.executeQuery();
				      while(rs2.next()){
				    	  size.add(rs2.getString(2));
				      }
			 	    String mode="e";
			 	   StringBuffer buffer=new StringBuffer();
			 	  
			 	    detailsid=detailsid+det.getPurchasePriceId()+",";
			 	  
			 	  sizeGrid.setSizeRangeGridid(sizerange_id);
			 	  sizeGrid.setPurchasePriceId(det.getPurchasePriceId());
			 	  ApprovedPriceList strGrid=writeSizeInputGrid(con,userInfo,sizeGrid,size,mode);
			 	 String sizegrids= buffer.append(strGrid.getBuffer()).toString();
				      
			 	 det.setSizeGrid(sizegrids!=null&&!sizegrids.isEmpty()?sizegrids:"");
			 	sizeGrid=null;
				}
				//end
				
				detList.add(det);
				det=null;
				sizeGrid=null;
			} 
			se.setApprovedPriceDetailList(detList);
			se.setAplDetMap(detMap);
			se.setEditDetailsID(detailsid);
			
			dynamicFieldsListApprovedPriceListDetails=null;
			dynamicFieldsDAO=null;
			detList=null;
			detMap=null;
			query=null;
			// TODO Auto-generated method stub
		} 
		finally {
			DatabaseConnection.closeAll(pstmt, rs);
			DatabaseConnection.closeAll(null, rs2);
		}
		return se; 
	}	
	
	
	public List<String> getSizeNameForSizeRangeId(Connection con,ApprovedPriceList sizeRange,String mode)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmt=null;
	    ResultSet rs = null;  
	    List<String> size=new ArrayList<String>();
	    try{      
	    	if(!mode.equals("p")){
	    	    pstmt = con.prepareStatement(SQLUtil.SELECT_SIZE_FOR_SIZE_RANGE_ID);
		 	    pstmt.setInt(1, sizeRange.getSizeRangeGridid());
	    	}
	    	else{
//	    		pstmt = con.prepareStatement(SQLUtil.SELECT_SIZE_FOR_SIZE_RANGE_ID_PRINT);
//		    	pstmt.setInt(1, sizeRange.getProductionorderId());
//		    	pstmt.setInt(2, sizeRange.getProductionorderDetailId());
//		        pstmt.setInt(3, sizeRange.getSizeRangeId());	
	    	}
	      
	      rs = pstmt.executeQuery();
	      while(rs.next()){
	    	  size.add(rs.getString(2));
	      }
	    }finally{
	      if(pstmt!=null)
	        pstmt.close();
	    
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] =  pstmt;
	      ArrayOfResultSet = new ResultSet[1];      
	      ArrayOfResultSet[0] = rs;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return size;
	  }	
	
	public ApprovedPriceList writeSizeInputGrid(Connection con,TPCSUser userInfo, ApprovedPriceList sizeRangeBean,List<String> sizeDisplayList,String mode) {
		 
		ApprovedPriceList retPlan=new ApprovedPriceList();
		 try{
			 
	 
		 ApprovedPriceList ap=getDataFromTempTable(con,userInfo,sizeRangeBean,mode);
		 List<List<ApprovedPriceList>> rowList=ap.getApRowList();
		 
		 DecimalFormat df = new DecimalFormat("0.0");
		 String size_ids="";
		 StringBuffer buffer=new StringBuffer();	
			
		  //buffer.append("<div id=\"fix-grid\">");
		  buffer.append("<table  class=\"table table-bordered table-condensed\">");
		  
		  buffer.append("<thead>");
		  buffer.append("<tr class=\"header\">");
		  buffer.append("<th nowrap=\"nowrap\">SIZE</th>");
		  for(String sizeName:sizeDisplayList){
			  
			  buffer.append("<th>");
			  buffer.append(sizeName);
			  buffer.append("</th>");
		  }
		  buffer.append("</tr>");
		  buffer.append("</thead>");
		  
		  buffer.append("<tbody>");
		  int j=0;
		  for(List<ApprovedPriceList> colList:rowList)
		  {
			buffer.append("<tr >");
			
			buffer.append("<td nowrap=\"nowrap\">");
			
			buffer.append("Size Qty");
			buffer.append("</td>");
			
			for(ApprovedPriceList col:colList){
				
				buffer.append("<td id=\"td_size_"+sizeRangeBean.getPurchasePriceId()+"_"+col.getSizeId()+"\" align=\"right\" >");
				buffer.append("<input class=\"form-control\" id=\"size_"+sizeRangeBean.getPurchasePriceId()+"_"+col.getSizeId()+"\" name=\"size_"+sizeRangeBean.getPurchasePriceId()+"_"+col.getSizeId()+"\" type=\"text\" value=\""+col.getRate()+"\"  /> "); //onblur=\"setEditedSizeId(this.id)\" 
				buffer.append("</td>");
//				String strH=(colList.get(i));
				
//				if(strH!=null){
//					
//					String[] ar=strH.split("&sp");
//					
//					String sizeId=ar[0];//--SizeId
//					String sizeQty=ar[1];//--SizeQty
//					sizeQty=sizeQty.substring(2);
//					
//					String[] qt=sizeQty.split("&id");
//					
//					String qtyvalue=qt[0];//--sizeQty
//					
//						String id=strH;
//						allid=allid+id+",";
//						String value= qtyvalue;
//						buffer.append("<td id=\"td_"+id+"\" align=\"right\" >");
//						
//						Double format= Double.parseDouble(value) ;
//						buffer.append("<input id=\""+id+"\" name=\""+id+"\" type=\"text\" onblur=\"setEditedSizeId(this.id)\" value=\""+df.format(format)+"\"  /> ");
//						
//				}
//				else{
//					buffer.append("<td id=\"td_\">");
//				}
//				buffer.append("</td>");
			
				size_ids=size_ids+col.getSizeId()+",";
			
			}
			buffer.append("<input id=\"purchase_price_"+sizeRangeBean.getPurchasePriceId()+"\" name=\"purchase_price_"+sizeRangeBean.getPurchasePriceId()+"\" type=\"hidden\" value=\""+size_ids+"\"/> ");
			buffer.append("</tr>");
			
			j++;
		  }
		  	 buffer.append("</tbody>");
			 buffer.append("</table>");
			// buffer.append("</div>");
			
			 retPlan.setBuffer(buffer);
			 
			 buffer=null;
			 df=null;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		 return retPlan;
}
	
	
	
	/*public List<List<String>> getDataFromTempTable(Connection con, TPCSUser ui,ApprovedPriceList objRange,String mode){
		List<List<String>> objList=new ArrayList<List<String>>();
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try{

						
			cstmt=con.prepareCall("{ call pr_size_input_grid(?,?,?) }");
			cstmt.setString(1, mode);
			cstmt.setInt(2, objRange.getSizeRangeGridid());
			cstmt.setInt(3, objRange.getPurchasePriceId());
			
			rs = cstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns  = rsmd.getColumnCount();
			while (rs.next()) {
					
				List<String> strDatas = new ArrayList<String>();
					
				for (int i = 1; i <=numberOfColumns; i++) {
						strDatas.add(rs.getString(i));
					}
				objList.add(strDatas);
				strDatas=null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return objList;
	}*/	
	public ApprovedPriceList getDataFromTempTable(Connection con, TPCSUser ui,ApprovedPriceList objRange,String mode){
		CallableStatement cstmt=null;
		ResultSet rs=null;
		ApprovedPriceList ap=new ApprovedPriceList();
		try{
			List<List<ApprovedPriceList>> objList=new ArrayList<List<ApprovedPriceList>>();
						
			cstmt=con.prepareCall("{ call pr_size_grid(?,?,?) }");
			cstmt.setString(1, mode);
			cstmt.setInt(2, objRange.getSizeRangeGridid());
			cstmt.setInt(3, objRange.getPurchasePriceId());
			
			
			System.out.println("mode :"+mode);
			System.out.println("2 :"+objRange.getSizeRangeGridid());
			System.out.println("3 :"+objRange.getPurchasePriceId());
			
			rs = cstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns  = rsmd.getColumnCount();
			while (rs.next()) {
					
				List<ApprovedPriceList> apColList = new ArrayList<ApprovedPriceList>();
					
				for(int i=2;i<=numberOfColumns;i++){
					ApprovedPriceList apCol=new ApprovedPriceList();
					apCol.setSizeId(Validator.convertToInteger(rsmd.getColumnName(i)));
					apCol.setRate(rs.getString(i));
					apColList.add(apCol);
					apCol=null;
				}
				
//				for (int i = 1; i <=numberOfColumns; i++) {
//						strDatas.add(rs.getString(i));
//					}
				objList.add(apColList);
				apColList=null;
			}
			
			ap.setApRowList(objList);
			objList=null;
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return ap;
	}
	
	public boolean deleteApprovedPriceList(Connection con, int purchaseId,TPCSUser userInfo) throws SQLException {
		// TODO Auto-generated method stub
		Statement cstmt = null;
		boolean isDeleted = false;
		try { 
			cstmt = con.createStatement();
			
			cstmt.addBatch("DELETE FROM purchase_price_dynamic  WHERE supplier_id="+purchaseId);
			cstmt.addBatch("DELETE FROM purchase_price  WHERE supplier_id="+purchaseId);
			
			int count[]=cstmt.executeBatch();
			
			isDeleted=count.length>0?true:false;
			
		} finally {
	
			DatabaseConnection.closeAll(cstmt,null);
		}
		return isDeleted;
	}	
	public int deleteApprovedPriceListDetail(Connection con, TPCSUser userInfo,int supplierId,int pricdetId) throws SQLException {
		// TODO Auto-generated method stub
		CallableStatement cstmt = null;
		ResultSet rs = null;    
		 int iCount=0;
		try{ 
			
			cstmt = con.prepareCall("{call pr_delete_approvedpricelist_details(?,?)}");
			cstmt.setInt(1, supplierId);
			cstmt.setInt(2, pricdetId);
			
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
	
	public boolean checkExistense(Connection con, TPCSUser userInfo,ApprovedPriceList appobj) throws SQLException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		boolean isExists=true;
		try{
			pst=con.prepareStatement(SQLUtil.CHECK_APPROVED_PRICE_EXISTENCE);
			pst.setInt(1, appobj.getSupplierId());
			
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
	public boolean checkMatExistense(Connection con, TPCSUser userInfo,ApprovedPriceList appobj) throws SQLException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		boolean isExists=true;
		try{
			pst=con.prepareStatement(SQLUtil.CHECK_APPROVED_PRICE_MAT_EXISTENCE);
			pst.setInt(1, appobj.getSupplierId());
			pst.setInt(2, appobj.getMatId());
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
	
	public String doGetItemMasterData(TPCSUser userInfo,Connection con,int itemId)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String currency="";
		String uom="";
		String cost="";
		StringBuffer buffer=new StringBuffer();
		try{     
			
				pstmt=con.prepareStatement(SQLUtil.GET_ITEM_DATA);
				pstmt.setInt(1,itemId);
				rs=pstmt.executeQuery();

			if(rs.next()){
				currency=rs.getString(1);
				uom=rs.getString(2);
				cost=rs.getString(3);
				
			}
			buffer.append("<item_datas>");
			buffer.append("<item_data>");
			buffer.append("<currency>");
			buffer.append(StringEscapeUtils.escapeXml(currency));
			buffer.append("</currency>");
			buffer.append("<uom>");
			buffer.append(uom);
			buffer.append("</uom>");
			buffer.append("<cost>");
			buffer.append(cost);
			buffer.append("</cost>");
			buffer.append("</item_data>");
			buffer.append("</item_datas>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{    
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return buffer.toString();
	}
}
