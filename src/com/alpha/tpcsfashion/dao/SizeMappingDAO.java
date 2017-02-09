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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.alpha.tpcsfashion.beans.SizeMapping;
import com.alpha.tpcsfashion.beans.SizeRange;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.Validator;

public class SizeMappingDAO {
	
	public List<Map<String,String>> getAllSizeMappingListOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,String strSearchCriteria,List<String> orderBy)throws SQLException,Exception{
	    CallableStatement cstmt=null;
	    ResultSet rs = null; 
	    List<Map<String,String>> assortList=new ArrayList<Map<String,String>>();
	    String strbuffer="";
	    try{
	    	
	        GroupMasterDAO objDAO=new GroupMasterDAO();
	        Map<String,String> colPreMap=objDAO.getColPreMap(con,SubdocumentId.SIZE_MAPPING);
	        
	    	StringBuffer query = new StringBuffer(SQLUtil.GET_SIZEMAPPING_LIST);
//	    	query.append(" where  se.company_Id=" + userInfo.getCompanyId());
//			query.append(" and se.year_id=" + userInfo.getYearId());
//			query.append(" and se.location_id=" + userInfo.getLocationId());
	 
	    	if(strSearchCriteria!=null && !strSearchCriteria.isEmpty())
			{
	        	 query.append(strSearchCriteria);
	         }
	    	 
	    	 query.append(" order by ss.size_schedule_id desc,");
	    	 
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
	    	
//	    	  System.out.println("strbuffer=="+strbuffer);
	         cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
	         cstmt.setString(1,strbuffer);
	         cstmt.setInt(2,pc.iStart);
	         cstmt.setInt(3,pc.iEnd);
	         rs=cstmt.executeQuery();
	         
	      while(rs.next()){  
	    	 
	    	  Map<String,String> map = new HashMap<String,String>();
	    	  
	    	  map.put("SizeMapping.SizeScheduleId", rs.getString("size_schedule_id"));
	    	  map.put("SizeMapping.SizeScheduleDetId", rs.getString("size_sched_det_id"));
		     //   map.put(colPreMap.get("enq_no"), rs.getString("enq_no"));
		    	map.put(colPreMap.get("size_schedule_id"), rs.getString("size_sched_ref"));
		        map.put(colPreMap.get("remark"), rs.getString("remarks"));
		        map.put(colPreMap.get("status"), rs.getString("is_active"));
		        map.put(colPreMap.get("prod_size_range_id"), rs.getString("prod_Size_Range"));
		        map.put(colPreMap.get("prod_size_id"), rs.getString("prod_size_name"));
		        map.put(colPreMap.get("rm_size_range_id"), rs.getString("rm_Size_Range"));
		        map.put(colPreMap.get("rm_size_id"), rs.getString("rm_size_name"));
	     
	    	  assortList.add(map);
	    	  map=null;
	      }
	      
	     
	      objDAO=null;
	      query=null;
	    }
	    finally{
	          
	        DatabaseConnection.closeAll(cstmt,rs);
	      }
	    return assortList;
	  }

	
	
	public int getPageCount(Connection con, TPCSUser ui, String searchCriteria) throws SQLException {
		// TODO Auto-generated method stub
		int pageCount=0;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{
			StringBuffer query=new StringBuffer(SQLUtil.GET_SIZEMAPPING_LIST_COUNT);
			
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
	public List<String> getSizeNameForSizeRangeId(Connection con,SizeMapping sizeRange,String mode)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmt=null;
	    ResultSet rs = null;  
	    List<String> size=new ArrayList<String>();
	    try{      
	    	if(!mode.equals("p")){
	    	    pstmt = con.prepareStatement(SQLUtil.SELECT_SIZE_FOR_SIZE_RANGE_ID);
		 	    pstmt.setInt(1, sizeRange.getSizeRangeId());
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
	
	/*public SizeMapping writeSizeInputGrid(Connection con,TPCSUser userInfo, SizeMapping sizeRangeBean,List<String> sizeDisplayList,String mode) {
		 
		SizeMapping retPlan=new SizeMapping();
		 try{
			 
	 
			 SizeMapping ap=getDataFromTempTable(con,userInfo,sizeRangeBean,mode);
		 List<List<SizeMapping>> rowList=ap.getApRowList();
		 
		 DecimalFormat df = new DecimalFormat("0.0");
		 String size_ids="";
		 StringBuffer buffer=new StringBuffer();	
		 StringBuffer autoCompleteIdName=new StringBuffer();	
		  //buffer.append("<div id=\"fix-grid\">");
		  buffer.append("<table  class=\"table table-bordered table-condensed\">");
		  
		  buffer.append("<thead>");
		  buffer.append("<tr class=\"header\">");
		  buffer.append("<th nowrap=\"nowrap\">Product SIZE</th>");
		  for(String sizeName:sizeDisplayList){
			  
			  buffer.append("<th>");
			  buffer.append(sizeName);
			  buffer.append("</th>");
		  }
		  buffer.append("</tr>");
		  buffer.append("</thead>");
		  
		  buffer.append("<tbody>");
		  int j=1;
		  for(List<SizeMapping> colList:rowList)
		  {
			buffer.append("<tr >");
			
			buffer.append("<td nowrap=\"nowrap\">");
			if(j==1){
			buffer.append("Raw Mat Size Range");
			}else{
				buffer.append("Raw Mat Sizes");
			}
			buffer.append("</td>");
			
			for(SizeMapping col:colList){
				
				String rowValue=String.valueOf(col.getRmSizeRangeIds());
				String[] arr=rowValue.split(",");
				String sizeScheduId=arr[0];//sizeScheduleId
				String sizeScheduleNameId=arr[1];//sizeScheduleId
//				System.out.println("sizeScheduId="+sizeScheduId);    
//				System.out.println("sizeScheduleNameId="+sizeScheduleNameId);
 * 
				buffer.append("<td id=\"td_size_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"\" align=\"right\" >");
				buffer.append("<input id=\"size_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"\" name=\"size_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"\" type=\"text\" value=\""+col.getRmSizeRangeId()+"\"  /> "); //onblur=\"setEditedSizeId(this.id)\"
				buffer.append("<input id=\"size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"\" name=\"size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"\" type=\"hidden\" value=\""+col.getRmSizeRangeId()+"\"  /> "); //onblur=\"setEditedSizeId(this.id)\"
				buffer.append("</td>");
				
				
				autoCompleteIdName.append("<auto_complete_field>");
				autoCompleteIdName.append("<id>");
				autoCompleteIdName.append("size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"");
				autoCompleteIdName.append("</id>");
				
				autoCompleteIdName.append("<name>");
				autoCompleteIdName.append("size_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"");
				autoCompleteIdName.append("</name>");
				
				autoCompleteIdName.append("<method>");
				if(j==1){
				autoCompleteIdName.append("doGetSizeRange");
				}else{
				autoCompleteIdName.append("doGetSize");
				}
				autoCompleteIdName.append("</method>");
				autoCompleteIdName.append("</auto_complete_field>");
				
				size_ids=size_ids+col.getProdSizeId()+",";
				
			
			}
			buffer.append("<input id=\"size_sched_id_"+sizeRangeBean.getSizeScheduleId()+"\" name=\"size_sched_id_"+sizeRangeBean.getSizeScheduleId()+"\" type=\"hidden\" value=\""+size_ids+"\"/> ");
			buffer.append("</tr>");
			
			j++;
		  }
		  	 buffer.append("</tbody>");
			 buffer.append("</table>");
			// buffer.append("</div>");
			
			 retPlan.setSizeData(buffer.toString());
			 retPlan.setAutoCompleteIdNameString(autoCompleteIdName.toString());
			 
			 buffer=null;
			 df=null;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		 return retPlan;
}*/
	
//	public List<List<String>> getDataFromTempTable(Connection con, TPCSUser ui,SizeMapping objRange,String mode){
//		List<List<String>> objList=new ArrayList<List<String>>();
//		CallableStatement cstmt=null;
//		ResultSet rs=null;
//		try{
//
//						
//			cstmt=con.prepareCall("{ call pr_sizeMapping_input_grid(?,?,?,?) }");
////			cstmt.setInt(1, ui.getUserId());
////			cstmt.setString(2, screenTag);
//			cstmt.setString(1, mode);
//			cstmt.setInt(2, objRange.getSizeRangeId());
//			cstmt.setInt(3, objRange.getSizeScheduleId());
//			cstmt.setInt(4, objRange.getSizeScheduleDetId());
//			
//			rs = cstmt.executeQuery();
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int numberOfColumns  = rsmd.getColumnCount();
//			while (rs.next()) {
//					
//				List<String> strDatas = new ArrayList<String>();
//					
//				for (int i = 1; i <=numberOfColumns; i++) {
//						strDatas.add(rs.getString(i));
//					}
//				objList.add(strDatas);
//				strDatas=null;
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		finally{
//			DatabaseConnection.closeAll(cstmt, rs);
//		}
//		return objList;
//	}		
	/*public SizeMapping getDataFromTempTable(Connection con, TPCSUser ui,SizeMapping objRange,String mode){
		CallableStatement cstmt=null;
		ResultSet rs=null;
		SizeMapping ap=new SizeMapping();
		try{
			List<List<SizeMapping>> objList=new ArrayList<List<SizeMapping>>();
						
			cstmt=con.prepareCall("{ call pr_sizeMapping_input_grid(?,?,?,?) }");
			cstmt.setString(1, mode);
			cstmt.setInt(2, objRange.getSizeRangeId());
			cstmt.setInt(3, objRange.getSizeScheduleId());
			cstmt.setInt(4, objRange.getSizeScheduleDetId());
//			System.out.println("mode="+mode);
//			System.out.println("objRange.getSizeRangeId()=="+objRange.getSizeRangeId());
//			System.out.println(" objRange.getSizeScheduleId()=="+ objRange.getSizeScheduleId());
//			System.out.println("objRange.getSizeScheduleDetId()=="+objRange.getSizeScheduleDetId());
			
			rs = cstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns  = rsmd.getColumnCount();
			while (rs.next()) {
					
				List<SizeMapping> apColList = new ArrayList<SizeMapping>();
				SizeMapping apCol=null;
				for(int i=2;i<=numberOfColumns;i++){
					apCol=new SizeMapping();
					apCol.setProdSizeId(Validator.convertToInteger(rsmd.getColumnName(i)));
					apCol.setRmSizeRangeId(rs.getInt(i));
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
	}*/
	public SizeMapping writeSizeInputGrid(Connection con,TPCSUser userInfo, SizeMapping sizeRangeBean,List<String> sizeDisplayList,String mode) {
		 
//		SizeMapping retPlan=new SizeMapping();
		 try{
			 
			 List<String> sizeidList=new ArrayList<String>() ;
			 SizeMapping ap=getDataFromTempTable(con,userInfo,sizeRangeBean,mode);
			 List<SizeMapping> rowList=ap.getSizeMappingDetailList();
		 
		 DecimalFormat df = new DecimalFormat("0.0");
		 String size_ids="";
		 StringBuffer buffer=new StringBuffer();	
		 StringBuffer headerBuffer=new StringBuffer();	
		 StringBuffer rmSizeRangeBuffer=new StringBuffer();	
		 StringBuffer rmSizeBuffer=new StringBuffer();	
		 StringBuffer autoCompleteIdName=new StringBuffer();	
		 
		 for(SizeMapping data:rowList){
			 
			 
			 headerBuffer.append("<th>"+data.getProdSizeName()+"</th>");
			 
			 rmSizeRangeBuffer.append("<td id=\"td_size_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_1\" align=\"right\" >");
			 rmSizeRangeBuffer.append("<input class=\"form-control\" id=\"size_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_1\" name=\"size_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_1\" type=\"text\" value=\""+data.getRmSizeRangeName()+"\"  /> "); //onblur=\"setEditedSizeId(this.id)\"
			 rmSizeRangeBuffer.append("<input id=\"size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_1\" name=\"size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_1\" type=\"hidden\" value=\""+data.getRmSizeRangeId()+"\"  /> "); //onblur=\"setEditedSizeId(this.id)\"
			 rmSizeRangeBuffer.append("</td>");
			 
			 rmSizeBuffer.append("<td id=\"td_size_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_2\" align=\"right\" >");
			 rmSizeBuffer.append("<input class=\"form-control\" id=\"size_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_2\" name=\"size_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_2\" type=\"text\" value=\""+data.getRmSizeName()+"\"  /> "); //onblur=\"setEditedSizeId(this.id)\"
			 rmSizeBuffer.append("<input id=\"size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_2\" name=\"size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_2\" type=\"hidden\" value=\""+data.getRmSizeId()+"\"  /> "); //onblur=\"setEditedSizeId(this.id)\"
			 rmSizeBuffer.append("</td>");
			 
			 	autoCompleteIdName.append("<auto_complete_field>");
				autoCompleteIdName.append("<id>");
				autoCompleteIdName.append("size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_1");
				autoCompleteIdName.append("</id>");
				
				autoCompleteIdName.append("<name>");
				autoCompleteIdName.append("size_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_1");
				autoCompleteIdName.append("</name>");
				
				
				autoCompleteIdName.append("<dependId>");
				autoCompleteIdName.append("size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_1");
				autoCompleteIdName.append("</dependId>");
				
				
				autoCompleteIdName.append("<method>");
				autoCompleteIdName.append("doGetSizeRange");
				autoCompleteIdName.append("</method>");
				autoCompleteIdName.append("</auto_complete_field>");
				
				autoCompleteIdName.append("<auto_complete_field>");
				autoCompleteIdName.append("<id>");
				autoCompleteIdName.append("size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_2");
				autoCompleteIdName.append("</id>");
				
				autoCompleteIdName.append("<name>");
				autoCompleteIdName.append("size_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_2");
				autoCompleteIdName.append("</name>");
				
				autoCompleteIdName.append("<dependId>");
				autoCompleteIdName.append("size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+data.getProdSizeId()+"_1");
				autoCompleteIdName.append("</dependId>");
				
				autoCompleteIdName.append("<method>");
				autoCompleteIdName.append("doGetSizeRangeSize");
				autoCompleteIdName.append("</method>");
				autoCompleteIdName.append("</auto_complete_field>");
				
				size_ids=size_ids+data.getProdSizeId()+",";	
				
//				sizeId.setSizeId(String.valueOf(data.getProdSizeId()));
				sizeidList.add(String.valueOf(data.getProdSizeId()));
		 }
		 
		 
		  //buffer.append("<div id=\"fix-grid\">");
		  buffer.append("<table  class=\"table table-bordered table-condensed\">");
		  
		  buffer.append("<thead>");
		  buffer.append("<tr class=\"header\">");
		  buffer.append("<th nowrap=\"nowrap\">Product SIZE</th>");
		  buffer.append(headerBuffer.toString());
		  buffer.append("</tr>");
		  buffer.append("</thead>");
		  buffer.append("<tbody>");
		  buffer.append("<tr>");
		  buffer.append("<td nowrap=\"nowrap\">Raw Mat Size Range</td>");
		  buffer.append(rmSizeRangeBuffer.toString());
		  buffer.append("</tr>");
		  buffer.append("<tr>");
		  buffer.append("<td nowrap=\"nowrap\">Raw Mat Sizes</td>");
		  buffer.append(rmSizeBuffer.toString());
		  buffer.append("</tr>");
		  buffer.append("<input id=\"size_sched_id_"+sizeRangeBean.getSizeScheduleId()+"\" name=\"size_sched_id_"+sizeRangeBean.getSizeScheduleId()+"\" type=\"hidden\" value=\""+size_ids+"\"/> ");
		  buffer.append("</tbody>");
		  buffer.append("</table>");
		  
		  
		  /*for(String sizeName:sizeDisplayList){
			  
			  buffer.append("<th>");
			  buffer.append(sizeName);
			  buffer.append("</th>");
		  }
		  buffer.append("</tr>");
		  buffer.append("</thead>");
		  
		  buffer.append("<tbody>");
		  int j=1;
		  for(List<SizeMapping> colList:rowList)
		  {
			buffer.append("<tr >");
			
			buffer.append("<td nowrap=\"nowrap\">");
			if(j==1){
			buffer.append("Raw Mat Size Range");
			}else{
				buffer.append("Raw Mat Sizes");
			}
			buffer.append("</td>");
			
			for(SizeMapping col:colList){
				
				
				
				buffer.append("<td id=\"td_size_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"\" align=\"right\" >");
				buffer.append("<input id=\"size_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"\" name=\"size_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"\" type=\"text\" value=\""+col.getRmSizeRangeIds()+"\"  /> "); //onblur=\"setEditedSizeId(this.id)\"
				buffer.append("<input id=\"size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"\" name=\"size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"\" type=\"hidden\" value=\""+col.getRmSizeRangeIds()+"\"  /> "); //onblur=\"setEditedSizeId(this.id)\"
				buffer.append("</td>");
				
				
				autoCompleteIdName.append("<auto_complete_field>");
				autoCompleteIdName.append("<id>");
				autoCompleteIdName.append("size_id_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"");
				autoCompleteIdName.append("</id>");
				
				autoCompleteIdName.append("<name>");
				autoCompleteIdName.append("size_"+sizeRangeBean.getSizeScheduleId()+"_"+col.getProdSizeId()+"_"+j+"");
				autoCompleteIdName.append("</name>");
				
				autoCompleteIdName.append("<method>");
				if(j==1){
				autoCompleteIdName.append("doGetSizeRange");
				}else{
				autoCompleteIdName.append("doGetSize");
				}
				autoCompleteIdName.append("</method>");
				autoCompleteIdName.append("</auto_complete_field>");
				
				size_ids=size_ids+col.getProdSizeId()+",";
				
			
			}
			buffer.append("<input id=\"size_sched_id_"+sizeRangeBean.getSizeScheduleId()+"\" name=\"size_sched_id_"+sizeRangeBean.getSizeScheduleId()+"\" type=\"hidden\" value=\""+size_ids+"\"/> ");
			buffer.append("</tr>");
			
			j++;
		  }
		  	 buffer.append("</tbody>");
			 buffer.append("</table>");
			// buffer.append("</div>");
*/			
		  sizeRangeBean.setSizeData(buffer.toString());
		  sizeRangeBean.setAutoCompleteIdNameString(autoCompleteIdName.toString());
		  sizeRangeBean.setSizeIdList(sizeidList);
			 buffer=null;
			 df=null;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		 return sizeRangeBean;
}
	public SizeMapping getDataFromTempTable(Connection con, TPCSUser ui,SizeMapping objRange,String mode){
		CallableStatement cstmt=null;
		ResultSet rs=null;
		SizeMapping ap=new SizeMapping();
		try{
			List<List<SizeMapping>> objList=new ArrayList<List<SizeMapping>>();
			List<SizeMapping> apColList = new ArrayList<SizeMapping>();
						
			cstmt=con.prepareCall("{ call pr_sizeMapping_input_grids(?,?,?,?) }");
			cstmt.setString(1, mode);
			cstmt.setInt(2, objRange.getSizeRangeId());
			cstmt.setInt(3, objRange.getSizeScheduleId());
			cstmt.setInt(4, objRange.getSizeScheduleDetId());
//			System.out.println("mode="+mode);
//			System.out.println("objRange.getSizeRangeId()=="+objRange.getSizeRangeId());
//			System.out.println(" objRange.getSizeScheduleId()=="+ objRange.getSizeScheduleId());
//			System.out.println("objRange.getSizeScheduleDetId()=="+objRange.getSizeScheduleDetId());
			
			rs = cstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns  = rsmd.getColumnCount();
			while (rs.next()) {
				SizeMapping apCol=new SizeMapping();
				
				apCol.setSizeScheduleId(rs.getInt(1));
				apCol.setSizeScheduleDetId(rs.getInt(2));
				apCol.setProdSizeId(rs.getInt(3));
				apCol.setRmSizeRangeId(rs.getInt(4));
				apCol.setRmSizeId(rs.getInt(5));
				apCol.setProdSizeName(rs.getString(6));
				apCol.setRmSizeRangeName(rs.getString(7));
				apCol.setRmSizeName(rs.getString(8));
				
				apColList.add(apCol);
				
//				SizeMapping apCol=null;
//				for(int i=2;i<=numberOfColumns;i++){
//					apCol=new SizeMapping();
//					apCol.setProdSizeId(Validator.convertToInteger(rsmd.getColumnName(i)));
//					apCol.setRmSizeRangeIds(rs.getString(i));
//					apColList.add(apCol);
//					apCol=null;
//				}
//				
////				for (int i = 1; i <=numberOfColumns; i++) {
////						strDatas.add(rs.getString(i));
////					}
//				objList.add(apColList);
//				apColList=null;
			}
			
			ap.setSizeMappingDetailList(apColList);
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
	public int updateSizeInputGridData(Connection con, TPCSUser ui,SizeRange objPlan,String x,String valWithId,String insertMode, List<String> sizeIdList)throws SQLException,Exception
	{
		 	Statement st = con.createStatement();
		 	Statement stIns = con.createStatement();
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		 int h=0;
		 try{
//			 con.setAutoCommit(false);
			 
			 
			 sizeIdList = new ArrayList<String>(new LinkedHashSet<String>(sizeIdList));
			 
//			 System.out.println("x :"+x);
//			 System.out.println("insertMode :"+insertMode);
			 
			 if(!x.isEmpty() && insertMode.equals("n")){
				 pstmt=con.prepareStatement(" BEGIN "
					 		+ " IF NOT EXISTS (SELECT * FROM size_schedule_det "
					 		+ " WHERE size_schedule_id=? and prod_size_id=?)"
					 		+ " BEGIN"
					 		+ " INSERT INTO size_schedule_det(size_schedule_id,prod_size_id)"
					 		+ " VALUES (?,?)"
					 		+ " END "
					 		+ " END");
				 
				 for(String sizeId:sizeIdList){
					//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//					 pstmt.setInt(1, objPlan.getSizeScheduleId());
//					 pstmt.setInt(2,Validator.convertToInteger(sizeId));
//					 pstmt.setInt(3, objPlan.getSizeScheduleId());
					 pstmt.setInt(4,Validator.convertToInteger(sizeId));
					 pstmt.addBatch();
				 }
				 pstmt.executeBatch();
			 }
			 int sizeRangeId=0;
			 
	
			 if(!x.isEmpty() && insertMode.equals("e")){
				 
				 
				 
				 pstmt=con.prepareStatement(" BEGIN "
					 		+ " IF NOT EXISTS (SELECT * FROM size_schedule_det "
					 		+ " WHERE size_schedule_id=? and  and prod_size_id=?)"
					 		+ " BEGIN"
					 		+ " INSERT INTO size_schedule_det (size_schedule_id,prod_size_id)"
					 		+ " VALUES (?,?)"
					 		+ " END "
					 		+ " END ");
				 for(String sizeId:sizeIdList){
					//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//					 pstmt.setInt(1, objPlan.getSizeScheduleId());
//					 pstmt.setInt(2,Validator.convertToInteger(sizeId));
//					 pstmt.setInt(3, objPlan.getSizeScheduleId());
					 pstmt.setInt(4,Validator.convertToInteger(sizeId));
					 pstmt.addBatch();
				 }
				 pstmt.executeBatch();
				 
				 
				 
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
					
					//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//					String sqlQ=" update size_schedule_det set rm_size_range_id="+value+" where size_schedule_id="+objPlan.getSizeScheduleId()+" and prod_size_id="+sizeId;
//					if(qtyType.equals("#2")){
//						sqlQ=" update size_schedule_det set rm_size_id="+value+" where size_schedule_id="+objPlan.getSizeScheduleId()+"  and prod_size_id="+sizeId;
//					}
					
					
					/*	String sql="update temp_gcm_constraints set backlog_days = "+ar1[0]+" where prod_site_id= "+ar2[0]+" and prod_line_id= "+ar3[0]+" and tech_id="+ar4[0]+" and week_id="+ar5[0]+" and temp_tag='"+screenTag+"'";
					if(value.substring(0,1).equals("#")){
						sql="update temp_gcm_constraints set traffic_light_color = '"+ar1[0]+"' where prod_site_id= "+ar2[0]+" and prod_line_id= "+ar3[0]+" and tech_id="+ar4[0]+" and week_id="+ar5[0]+" and temp_tag='"+screenTag+"'";	
					} */
					
					//COMMENTED BELOW BECAUSE OF COMPILE ERROR
//					st.addBatch(sqlQ);
				 }
				 int  a[]=st.executeBatch();
				 
//				 float totSizeQty=0;
//				 pstmt=con.prepareStatement(" select SUM(isnull(size_qty,0)) as size_qty  from customerorder_size where customerorder_detail_id="+objPlan.getSizeScheduleDetId()+" having SUM(isnull(size_qty,0))>0");
//				 rs=pstmt.executeQuery();
//				 if(rs.next()){
//					 totSizeQty=rs.getFloat(1);
//				 }
//				 pstmt=con.prepareStatement(" update customerorder_detail "
//					 		+ " set quantity=(select SUM(isnull(size_qty,0))  from customerorder_size where customerorder_detail_id="+objPlan.getSizeScheduleDetId()+" having SUM(isnull(size_qty,0))>0) "
//					 		+ " where customerorder_detail_id="+objPlan.getSizeScheduleDetId());
//				 pstmt.executeUpdate();
			 }
			
			 sizeIdList=null;
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
	
	public SizeMapping saveSizeMapping(Connection con,TPCSUser userinfo,SizeMapping objheadersize,String sizeInput) throws SQLException {
		Statement stmt = null;
		CallableStatement cstmt=null;
		int SizeScheduleId;
		try
		{

			SQLXML sizeSqlxml= con.createSQLXML();
			sizeSqlxml.setString(sizeInput);
			cstmt=con.prepareCall("{? = call pr_insertSizeMapping(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2,userinfo.getUserId());
			cstmt.setInt(3,userinfo.getCompanyId());
			cstmt.setString(4,objheadersize.getMode());
			cstmt.setString(5, objheadersize.getDetailMode());
			cstmt.setString(6, objheadersize.getScheduleNo());
			cstmt.setString(7, objheadersize.getRemark());
			cstmt.setInt(8, objheadersize.getIsActive());
			
			cstmt.setInt(9,objheadersize.getSizeRangeId());
			cstmt.setString(10, objheadersize.getSizeRangeName());
			cstmt.setInt(11, objheadersize.getSizeScheduleId());
			cstmt.setInt(12, objheadersize.getSizeScheduleDetId());
			cstmt.setSQLXML(13,sizeSqlxml);
					cstmt.executeUpdate();
					SizeScheduleId = cstmt.getInt(1);
		
		boolean bflag=SizeScheduleId>0?true:false;
		
		if(objheadersize.getMode().equals("n"))
		{
			objheadersize.setSizeScheduleId(SizeScheduleId);
		}
		}
		finally
		{

			DatabaseConnection.closeAll(cstmt, null);

		}
		return objheadersize;
	}	
	
	public SizeMapping getSizeMappingheat(Connection con,TPCSUser userInfo, int sizeschedId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		SizeMapping det=new SizeMapping();
		try{

			StringBuffer query = new StringBuffer(SQLUtil.GET_SIZEMAPPING_HEAD);
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,sizeschedId);
			rs=pstmt.executeQuery();


			while(rs.next())
			{
				det.setSizeScheduleId(rs.getInt(1));
				det.setScheduleNo(rs.getString(2));
				det.setRemark(rs.getString(3));
				det.setIsActive(rs.getInt(4));
			} 
			
			query=null;
			// TODO Auto-generated method stub
		} 
		finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return det; 
	}	
	
	public SizeMapping getSizeMappingDetailsList(Connection con,TPCSUser userInfo, int sizeschedId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		SizeMapping se=new SizeMapping();
		List<SizeMapping> detList=new ArrayList<SizeMapping>();		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			StringBuffer query = new StringBuffer(SQLUtil.GET_SIZEMAPPING_DETAIL_LIST);
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,sizeschedId);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				SizeMapping det=new SizeMapping();
				det.setSizeScheduleId(rs.getInt(1));
				det.setSizeScheduleDetId(rs.getInt(2));
				det.setProdSizeRangeId(rs.getInt(3));
				det.setProdSizeId(rs.getInt(4));
				det.setRmSizeRangeId(rs.getInt(5));
				det.setRmSizeId(rs.getInt(6));
				det.setProdSizeRangeName(rs.getString(7));
				det.setProdSizeName(rs.getString(8));
				det.setRmSizeRangeName(rs.getString(9));
				det.setRmSizeName(rs.getString(10));
				detList.add(det);
				det=null;
			} 
			se.setSizeMappingDetailList(detList);
			
			detList=null;
			query=null;
			// TODO Auto-generated method stub
		} 
		finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return se; 
	}	
	
	public SizeMapping getEditSizeMappingDetailsList(Connection con,TPCSUser userInfo, SizeMapping appmode) throws SQLException, Exception {
		// TODO Auto-generated method stub
		SizeMapping se=new SizeMapping();
		List<SizeMapping> detList=new ArrayList<SizeMapping>();		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		SizeMapping det=null;
		try{
			String detailsid="";
			StringBuffer query = new StringBuffer(SQLUtil.GET_SIZEMAPPING_DETAIL);
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,appmode.getSizeScheduleId());
			pstmt.setInt(2,appmode.getSizeScheduleDetId());
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				det=new SizeMapping();
				det.setSizeScheduleId(rs.getInt(1));
				det.setSizeScheduleDetId(rs.getInt(2));
				det.setProdSizeRangeId(rs.getInt(3));
				det.setProdSizeRangeName(rs.getString(4));
			
			// size grid edit start
//			SizeMapping sizeGrid=new SizeMapping();

			
//			int sizerange_id=det.getProdSizeRangeId();
			 List<String> size=new ArrayList<String>();
			 pstmt = con.prepareStatement(SQLUtil.SELECT_SIZE_FOR_SIZE_RANGE_ID);
		 	    pstmt.setInt(1, det.getProdSizeRangeId());
		 	   rs2 = pstmt.executeQuery();
			      while(rs2.next()){
			    	  size.add(rs2.getString(2));
			      }
		 	    String mode="e";
//		 	   StringBuffer buffer=new StringBuffer();
		 	  
		 	    detailsid=detailsid+det.getSizeScheduleId()+",";
		 	    System.out.println("detailsid="+detailsid);
		 	  
//		 	  sizeGrid.setSizeRangeId(sizerange_id);
//		 	  sizeGrid.setSizeScheduleId(det.getSizeScheduleId());
//		 	  sizeGrid.setSizeScheduleDetId(det.getSizeScheduleDetId());
		 	  det=writeSizeInputGrid(con,userInfo,det,size,mode);
//		 	 String sizegrids= buffer.append(strGrid.getSizeData()).toString();
			      
//		 	 det.setSizeGrid(sizegrids);
		 	 det.setEditDetailsID(detailsid);
//		 	sizeGrid=null;
			//end
//		 	detList.add(det);
			
			
	}		
//			se.setSizeMappingDetailList(detList);
			
			detList=null;
			query=null;
			// TODO Auto-generated method stub
		} 
		finally {
			DatabaseConnection.closeAll(pstmt, rs);
			DatabaseConnection.closeAll(null, rs2);
		}
		return det; 
	}	
	public boolean deleteSizeMapping(Connection con, int sizeSchedId,TPCSUser userInfo) throws SQLException {
		// TODO Auto-generated method stub
		Statement cstmt = null;
		boolean isDeleted = false;
		try { 
			cstmt = con.createStatement();
			
			cstmt.addBatch("DELETE FROM size_schedule_det  WHERE size_schedule_id="+sizeSchedId);
			cstmt.addBatch("DELETE FROM size_schedule  WHERE size_schedule_id="+sizeSchedId);
			
			int count[]=cstmt.executeBatch();
			
			isDeleted=count.length>0?true:false;
			
		} finally {
	
			DatabaseConnection.closeAll(cstmt,null);
		}
		return isDeleted;
	}	
	
	public int deleteSizeMappingDetail(Connection con, TPCSUser userInfo,int sizeSchedId,int sizeSchedDetId) throws SQLException {
		// TODO Auto-generated method stub
		CallableStatement cstmt = null;
		ResultSet rs = null;    
		 int iCount=0;
		try{ 
			
			cstmt = con.prepareCall("{call pr_delete_SizeMapping_details(?,?)}");
			cstmt.setInt(1, sizeSchedId);
			cstmt.setInt(2, sizeSchedDetId);
			
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
}
