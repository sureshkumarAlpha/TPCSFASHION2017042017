package com.alpha.tpcsfashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.CommonUtil;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.TaxGroup;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.util.NumericFormat;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class CommonUtilDAO {
	public String doGetAutoSlNo(TPCSUser ui,Connection con,String prefix,CommonUtil comUtil)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer buffer=new StringBuffer();
		try{      
			pstmt=con.prepareStatement(SQLUtil.GET_LAST_NO_FOR_PREFIX.replace("?autno_table",comUtil.getAutoNoTableName()));
			pstmt.setString(1, prefix);
			pstmt.setInt(2, ui.getCompanyId());
			pstmt.setInt(3, ui.getLocationId());
			pstmt.setInt(4, ui.getYearId());
			rs=pstmt.executeQuery();

			int lastNo1=0;
			int autono=0;
			if(rs.next()){
				lastNo1=rs.getInt(1);
				autono=rs.getInt(2);
				
			}
			buffer.append("<auto_last_no>");
			buffer.append("<last_no>");
			buffer.append(lastNo1==0?"":lastNo1);
			buffer.append("</last_no>");
			buffer.append("<auto_no>");
			buffer.append(autono);
			buffer.append("</auto_no>");
			buffer.append("</auto_last_no>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{    
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return buffer.toString();
	}
	
	public String getAutoNoData(TPCSUser ui,Connection con,CommonUtil sih)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer buffer=new StringBuffer();
		List<CommonUtil> autoList=new ArrayList<CommonUtil>();
		try{      
			String whereCond="";
			if(sih.getHeaderTypeColumn()!=null && !sih.getHeaderTypeColumn().equals("null") && !sih.getHeaderTypeColumn().isEmpty() && sih.getHeaderTypeValue()!=null && !sih.getHeaderTypeValue().equals("null") && !sih.getHeaderTypeValue().isEmpty()){
				whereCond=" and "+sih.getHeaderTypeColumn()+"= '"+sih.getHeaderTypeValue()+"'";	
			}
			
			
			List<String> existsPrefixList=new ArrayList<String>();
			
			pstmt=con.prepareStatement(SQLUtil.GET_PO_TRANSACTION_CREATED_PREFIX.replace("?header_table",sih.getHeaderTableName()).concat(whereCond));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs=pstmt.executeQuery();
			while(rs.next()){
				existsPrefixList.add(rs.getString(1));
			}
			pstmt=con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table",sih.getAutoNoTableName()));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs=pstmt.executeQuery();
			while(rs.next()){
				CommonUtil obj=new CommonUtil();
			obj.setPrefix(rs.getString(1));
			obj.setStartingNo(rs.getInt(3));
			autoList.add(obj);
			obj=null;
			}
			
			buffer.append("<auto_details>");
			buffer.append("<auto_detail>");
			buffer.append("<all_prefix>");		
	        buffer.append(StringEscapeUtils.escapeXml("<div class=\"row table-responsive \" id=\"common-prefix\" >"));
	        
	        buffer.append(StringEscapeUtils.escapeXml("<div class=\"col-sm-1\">&nbsp;</div>"));
	        buffer.append(StringEscapeUtils.escapeXml("<div class=\"col-sm-10\">"));
			buffer.append(StringEscapeUtils.escapeXml("<table   >"));
			buffer.append(StringEscapeUtils.escapeXml("<tbody ><tr>"));
			buffer.append(StringEscapeUtils.escapeXml("<td>"));
			buffer.append(StringEscapeUtils.escapeXml("<table class=\"table-condensed\" id=\"auto_addrow_table\"  >"));
			buffer.append(StringEscapeUtils.escapeXml("<tbody>"));
			
			String rowIds="";
			int rowCnt=0;
			int i=0;
			for(CommonUtil obj:autoList)
			{
				buffer.append(StringEscapeUtils.escapeXml("<tr id=\"prefix_row_"+i+"\">"));
				buffer.append(StringEscapeUtils.escapeXml("<td>"));
				buffer.append(StringEscapeUtils.escapeXml( "<input class=\"form-control\" id=\"autono_prefix_"+i+"\" name=\"autono_prefix_"+i+"\" maxlength=\"15\" type=\"text\"  readonly value=\""+obj.getPrefix()+"\" placeholder=\"Enter Prefix\">"));
				buffer.append(StringEscapeUtils.escapeXml("</td>"));
				buffer.append(StringEscapeUtils.escapeXml("<td style=\"width: 100px;\"><a href=\"javascript:addAnotherLineAutoNo();\" data-toggle=\"tooltip\" title=\"Add\" ><span class=\"span-icon glyphicon glyphicon-plus text-primary\"></span></a>&nbsp;"
						+ "<a href=\"javascript:deletePrefixFromTable('"+i+"','"+obj.getPrefix()+"')\" data-toggle=\"tooltip\" title=\"Remove\" ><span class=\"span-icon glyphicon glyphicon-remove icon-delete\"></span></a></td>"));
				buffer.append(StringEscapeUtils.escapeXml("</tr>"));
				
				if(!existsPrefixList.contains(obj.getPrefix())){
					rowIds=rowIds+""+i+",";
				}
				rowCnt++;
				i++;
			}
			
			
			buffer.append(StringEscapeUtils.escapeXml("</tbody>"));
			buffer.append(StringEscapeUtils.escapeXml("</table>"));
			buffer.append(StringEscapeUtils.escapeXml("</div>"));
			buffer.append(StringEscapeUtils.escapeXml("</div>"));
			buffer.append(StringEscapeUtils.escapeXml("</td>"));
			buffer.append(StringEscapeUtils.escapeXml("</tr>"));
			buffer.append(StringEscapeUtils.escapeXml("</tbody></table>"));
			buffer.append(StringEscapeUtils.escapeXml("</div>"));
			buffer.append("</all_prefix>");
			
			buffer.append("<auto_prefix>");	
		    buffer.append(StringEscapeUtils.escapeXml("<div class=\"row table-responsive\" style=\" overflow-y:auto;\" >"));
		    buffer.append(StringEscapeUtils.escapeXml("<div class=\"col-sm-2\">&nbsp;</div>"));
	        buffer.append(StringEscapeUtils.escapeXml("<div class=\"col-sm-10\">"));
		    buffer.append(StringEscapeUtils.escapeXml("<table  >"));
			buffer.append(StringEscapeUtils.escapeXml("<tbody ><tr>"));
			buffer.append(StringEscapeUtils.escapeXml("<td>"));
			buffer.append(StringEscapeUtils.escapeXml("<table class=\"table-bordered table-condensed\" id=\"auto_getrow_table\"  >"));
			buffer.append(StringEscapeUtils.escapeXml("<thead>"));
			buffer.append(StringEscapeUtils.escapeXml("<tr class=\"header\">"));
			buffer.append(StringEscapeUtils.escapeXml("<th>Prefix</th>"));
			buffer.append(StringEscapeUtils.escapeXml(" <th>Starting Number</th>"));
		    
			buffer.append(StringEscapeUtils.escapeXml(" </tr>"));
			buffer.append(StringEscapeUtils.escapeXml("</thead>"));
			buffer.append(StringEscapeUtils.escapeXml("<tbody>"));
				int j=0;
				for(CommonUtil obj:autoList)
				{
					buffer.append(StringEscapeUtils.escapeXml("<tr class=\"datarow\"  id=\"sep_prefix_row_"+j+"\">"));
					buffer.append(StringEscapeUtils.escapeXml("<td id=\"seperate_prefix_"+j+"\">"));
					buffer.append(StringEscapeUtils.escapeXml(obj.getPrefix()));
					buffer.append(StringEscapeUtils.escapeXml("</td>"));
					buffer.append(StringEscapeUtils.escapeXml("<td>"));
					buffer.append(StringEscapeUtils.escapeXml("<input class=\"form-control\" id=\"autono_sep_start_no_"+j+"\" name=\"autono_sep_start_no_"+j+"\" max=\"999999\" maxlength=\"6\" type=\"text\" "+(existsPrefixList.contains(obj.getPrefix())?" readonly ":"")+" value=\""+obj.getStartingNo()+"\"  onkeyup=\"validateAutoSlno(this)\" onblur=\"setAutoSlnoIsEmpty(this)\" placeholder=\"Starting No.\">"));
					buffer.append(StringEscapeUtils.escapeXml("</td>"));
					buffer.append(StringEscapeUtils.escapeXml("</tr>"));	
					j++;
				}
			
				buffer.append(StringEscapeUtils.escapeXml("</tbody>"));
				buffer.append(StringEscapeUtils.escapeXml("</table>"));
				buffer.append(StringEscapeUtils.escapeXml("</td>"));
				buffer.append(StringEscapeUtils.escapeXml("</tr>"));
				buffer.append(StringEscapeUtils.escapeXml("</tbody></table>"));
				buffer.append(StringEscapeUtils.escapeXml("</div>"));
				buffer.append(StringEscapeUtils.escapeXml("</div>"));
				buffer.append(StringEscapeUtils.escapeXml("</div>"));
				
			buffer.append("</auto_prefix>");
			
			buffer.append("<row_ids>");
			buffer.append(rowIds);
			buffer.append("</row_ids>");
			
			buffer.append("<row_cnt>");
			buffer.append(rowCnt);
			buffer.append("</row_cnt>");
			
			buffer.append("<checked_type>");
			String checkType=""; //for default
			pstmt=con.prepareStatement(SQLUtil.GET_AUTO_NO_TYPE.replace("?autno_table",sih.getAutoNoTableName()));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs=pstmt.executeQuery();
			if(rs.next()){
				checkType=rs.getString(1);
			}
			buffer.append(checkType);
			buffer.append("</checked_type>");		
			
			int commonNo=1;
			pstmt=con.prepareStatement(SQLUtil.GET_STARTRING_NO.replace("?autno_table",sih.getAutoNoTableName()));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs=pstmt.executeQuery();
			if(rs.next()){
				commonNo=rs.getInt(1);
			}
			buffer.append("<common_start_no>");
			buffer.append(commonNo);
			buffer.append("</common_start_no>");
			buffer.append("<initial_stage>");
			buffer.append(existsPrefixList.size()>0?"0":"1");
			buffer.append("</initial_stage>");
			
			
			
			buffer.append("</auto_detail>");			
			buffer.append("</auto_details>");
			existsPrefixList=null;
			autoList=null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{    
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return buffer.toString();
	}
	
	public CommonUtil saveAutoNum(Connection con, CommonUtil sih,TPCSUser ui) throws SQLException {
		 	PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				
				if(sih.getDeletePrefixList().size()>0){
					pstmt=con.prepareStatement(SQLUtil.DELETE_AUTONO_ROW.replace("?autno_table",sih.getAutoNoTableName()));
					pstmt.setInt(2, ui.getCompanyId());
					pstmt.setInt(3, ui.getLocationId());
					pstmt.setInt(4, ui.getYearId());	
					for(String deletePrefix:sih.getDeletePrefixList()){
						pstmt.setString(1, deletePrefix);
						pstmt.addBatch();
					}
					pstmt.executeBatch();
				}
				
				StringBuffer buffer=new StringBuffer();
				
				if(sih.getPrefixList()!=null){
				
				int lastNoForCommon=0;
				pstmt=con.prepareStatement(SQLUtil.GET_STARTRING_NO.replace("?autno_table",sih.getAutoNoTableName()));
				pstmt.setInt(1, ui.getCompanyId());
				pstmt.setInt(2, ui.getLocationId());
				pstmt.setInt(3, ui.getYearId());
				rs=pstmt.executeQuery();
				if(rs.next()){
					lastNoForCommon=rs.getInt(2);
				}
				
				
				if(sih.isManSlno()==false && sih.isCommonSlno() ==false){//Seperate slno
					  buffer=new StringBuffer();
					buffer.append("if not exists(select * from "+sih.getAutoNoTableName()+" where prefix =?)"
							+ " begin"
							+ " insert into "+sih.getAutoNoTableName()+"(company_id,location_id,year_id,autono,common_slno,prefix,last_no,starting_no) values(?,?,?,?,?,?,?,?)"
							+ " end"
							+ " else "
							+ " begin"
							+ " update "+sih.getAutoNoTableName()+" set autono=?,common_slno=?,last_no=?,starting_no=? where prefix=? and company_id=? and location_id=? and year_id=?"
							+ " end");
					
					pstmt=con.prepareStatement(buffer.toString());
					pstmt.setInt(2, ui.getCompanyId());
					pstmt.setInt(3, ui.getLocationId());
					pstmt.setInt(4, ui.getYearId());
					
					int i=0;
					for(String prefix:sih.getPrefixList()){
						
						 if(!prefix.isEmpty()){
								pstmt.setString(1, prefix); //prefix
								pstmt.setInt(5, 1); //autono
								pstmt.setInt(6, 0); //common_slno
								pstmt.setString(7, prefix); //prefix
								pstmt.setInt(8,  Validator.convertToInteger(sih.getSlnoList().get(i))); //last no
								pstmt.setInt(9, Validator.convertToInteger(sih.getSlnoList().get(i))); //starting_no
								pstmt.setInt(10, 1); //auto no
								pstmt.setInt(11,0); //common slno
								
								pstmt.setInt(12, Validator.convertToInteger(sih.getSlnoList().get(i))); //last no
								pstmt.setInt(13, Validator.convertToInteger(sih.getSlnoList().get(i))); //starting no
								pstmt.setString(14, prefix); //Prefix 
								pstmt.setInt(15, ui.getCompanyId()); 
								pstmt.setInt(16, ui.getLocationId());
								pstmt.setInt(17, ui.getYearId());
							
							
							pstmt.addBatch();
						 }
						 i++;
					}
					pstmt.executeBatch();
				}
				else if(sih.isManSlno()){ //Manual slno
					
					
					 buffer=new StringBuffer();
					  buffer.append("if not exists(select * from "+sih.getAutoNoTableName()+" where prefix =?)"
							+ " begin"
							+ " insert into "+sih.getAutoNoTableName()+"(company_id,location_id,year_id,autono,common_slno,prefix,last_no,starting_no) values(?,?,?,?,?,?,?,?)"
							+ " end"
							+ " else "
							+ " begin"
							+ " update "+sih.getAutoNoTableName()+" set autono=?,common_slno=?,last_no=?,starting_no=? where company_id=? and location_id=? and year_id=?"
							+ " end");
					
					pstmt=con.prepareStatement(buffer.toString());
					pstmt.setInt(2, ui.getCompanyId());
					pstmt.setInt(3, ui.getLocationId());
					pstmt.setInt(4, ui.getYearId());
					
					int i=0;
					for(String prefix:sih.getPrefixList()){
						
						if(!prefix.isEmpty()){
							pstmt.setString(1, prefix); //prefix
							pstmt.setInt(5, 0); //auto no
							pstmt.setInt(6, 0); //common slno
							pstmt.setString(7, prefix); //prefix
							pstmt.setInt(8, 1); // last no
							pstmt.setInt(9, 1); //starting no
							
							pstmt.setInt(10, 0); //auto no
							pstmt.setInt(11, 0); //common slno
							
							pstmt.setInt(12, 1); // last no
							pstmt.setInt(13, 1); //starting no
							
							pstmt.setInt(14, ui.getCompanyId()); 
							pstmt.setInt(15, ui.getLocationId());
							pstmt.setInt(16, ui.getYearId());
						
						
						pstmt.addBatch();
						}
						i++;
					}
					pstmt.executeBatch();
				}
				else{ //common slno
					
					int lastNo=1;
					int startingNo=1;
					pstmt=con.prepareStatement(SQLUtil.GET_STARTRING_NO.replace("?autno_table",sih.getAutoNoTableName()));
					pstmt.setInt(1, ui.getCompanyId());
					pstmt.setInt(2, ui.getLocationId());
					pstmt.setInt(3, ui.getYearId());
					rs=pstmt.executeQuery();
					while(rs.next()){
						startingNo=rs.getInt(1);
						lastNo=rs.getInt(2);
					}
					
					
					String whereCond="";
					if(sih.getHeaderTypeColumn()!=null && !sih.getHeaderTypeColumn().isEmpty() && !sih.getHeaderTypeColumn().equalsIgnoreCase("null") && sih.getHeaderTypeValue()!=null && !sih.getHeaderTypeValue().isEmpty() && !sih.getHeaderTypeValue().equalsIgnoreCase("null")){
						whereCond=" and "+sih.getHeaderTypeColumn()+"= '"+sih.getHeaderTypeValue()+"'";	
					}
					
					
					
					List<String> existsPrefixList=new ArrayList<String>();
					pstmt=con.prepareStatement(SQLUtil.GET_SI_TRANSACTION_CREATED_PREFIX.replace("?header_table", sih.getHeaderTableName()).concat(whereCond));
					pstmt.setInt(1, ui.getCompanyId());
					pstmt.setInt(2, ui.getLocationId());
					pstmt.setInt(3, ui.getYearId());
					rs=pstmt.executeQuery();
					while(rs.next()){
						existsPrefixList.add(rs.getString(1));
					}
					
					
				  buffer=new StringBuffer();
				  buffer.append("if not exists(select * from "+sih.getAutoNoTableName()+" where prefix =?)"
						+ " begin"
						+ " insert into "+sih.getAutoNoTableName()+"(company_id,location_id,year_id,autono,common_slno,prefix,last_no,starting_no) values(?,?,?,?,?,?,?,?)"
						+ " end"
						+ " else "
						+ " begin"
						+ " update "+sih.getAutoNoTableName()+" set autono=?,common_slno=? where  company_id=? and location_id=? and year_id=?"
						+ " end");
				
				pstmt=con.prepareStatement(buffer.toString());
				pstmt.setInt(2, ui.getCompanyId());
				pstmt.setInt(3, ui.getLocationId());
				pstmt.setInt(4, ui.getYearId());
				
				int i=0;
				for(String prefix:sih.getPrefixList()){
					 
					if(!prefix.isEmpty()){
						pstmt.setString(1, prefix);
						pstmt.setInt(5, 1);
						pstmt.setInt(6, (sih.isCommonSlno()==true?1:0));
						pstmt.setString(7, prefix);
						pstmt.setInt(8, (sih.isCommonSlno()==true ?(lastNoForCommon!=0?lastNoForCommon:sih.getCommonNo()):Validator.convertToInteger(sih.getSlnoList().get(i))));
						pstmt.setInt(9, (sih.isCommonSlno()==true?sih.getCommonNo():Validator.convertToInteger(sih.getSlnoList().get(i))));
						pstmt.setInt(10, 1);
						pstmt.setInt(11,(sih.isCommonSlno()==true?1:0));
						pstmt.setInt(12, ui.getCompanyId());
						pstmt.setInt(13, ui.getLocationId());
						pstmt.setInt(14, ui.getYearId());
					
					
					pstmt.addBatch();
					}
					i++;
				}
				pstmt.executeBatch();
				
				if(existsPrefixList.size()>0){
					pstmt=con.prepareStatement("update "+sih.getAutoNoTableName()+" set last_no=?,starting_no=? where company_id=? and location_id=? and year_id=?");
					pstmt.setInt(1, lastNo);
					pstmt.setInt(2, startingNo);
					pstmt.setInt(3, ui.getCompanyId());
					pstmt.setInt(4, ui.getLocationId());
					pstmt.setInt(5, ui.getYearId());
					pstmt.executeUpdate();
				}
				else{
					pstmt=con.prepareStatement("update "+sih.getAutoNoTableName()+" set last_no=?,starting_no=? where company_id=? and location_id=? and year_id=?");
					pstmt.setInt(1, sih.getCommonNo());
					pstmt.setInt(2, sih.getCommonNo());
					pstmt.setInt(3, ui.getCompanyId());
					pstmt.setInt(4, ui.getLocationId());
					pstmt.setInt(5, ui.getYearId());
					pstmt.executeUpdate();
				}
				existsPrefixList=null;
				}
				}
				
				 buffer=new StringBuffer();
				buffer.append("<auto_number_generations>");
				pstmt=con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table",sih.getAutoNoTableName()));
				pstmt.setInt(1, ui.getCompanyId());
				pstmt.setInt(2, ui.getLocationId());
				pstmt.setInt(3, ui.getYearId());
				rs=pstmt.executeQuery();
				while(rs.next()){
					buffer.append("<auto_number_prefix>");
					buffer.append("<auto_prefix>");
					buffer.append(rs.getString(1));
					buffer.append("</auto_prefix>");
					
					buffer.append("<auto_lastno>");
					buffer.append(rs.getString(2));
					buffer.append("</auto_lastno>");
					
					buffer.append("</auto_number_prefix>");
				}
				buffer.append("</auto_number_generations>");
				sih.setAutoNoString(buffer.toString());
				
				buffer=null;
				
			}
			finally{
		      DatabaseConnection.closeAll(pstmt,rs);
		      }
		return sih;
	}
	public CommonUtil getDynamicFields(Connection con,CommonUtil comUtil)throws SQLException,Exception{
		PreparedStatement pst=null;
		ResultSet rs = null;  
		
		List<CommonUtil> comUtilList=new ArrayList<CommonUtil>();
		try{
			StringBuffer objBuffer = new StringBuffer();
			objBuffer.append(SQLUtil.GET_DYNAMICFIELDS);
			objBuffer.append("("+comUtil.getTableIds()+") AND isnull(df.fixed,0)="+comUtil.getFixed()+" AND isnull(dr.Active,0)=1  ORDER BY df.tableId ,dr.SortOrder");
			pst = con.prepareStatement(objBuffer.toString());      
			rs = pst.executeQuery();
			while(rs.next()){
				CommonUtil objSO = new CommonUtil();
				objSO.setDynamicFieldId(rs.getInt(1));
				objSO.setLabelName(rs.getString(2));
				objSO.setPageFieldName(rs.getString(3));
				objSO.setDbFieldName(rs.getString(4));
				objSO.setTableId(rs.getInt(5));
				comUtilList.add(objSO);
				objSO=null;
			}

			comUtil.setComUtilList(comUtilList);
			comUtilList=null;
			objBuffer=null;

		}finally{      
			DatabaseConnection.closeAll(pst,rs);
		}
		return comUtil;
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
	
	public String getTaxDetail(TPCSUser userInfo,Connection con,CommonUtil comUtil)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer buffer=new StringBuffer();
		try{      
			
			List<CommonUtil> comList =comUtil.getComUtilList();
			float discount=Validator.convertToFloat(comUtil.getDiscountPercent());
			
			CommonUtil sid=getTotalTaxForAllDet(con,comList,discount,userInfo);
			
			buffer.append("<tax_details>");
			
			String taxGroupId=sid.getStrTaxGroupIds();
			pstmt=con.prepareStatement(" select distinct tg.tax_id,tm.tax_name,tax_display_name, tm.display_order  "
					+ " from tax_group tg inner join tax_master tm with(nolock) on tg.tax_id=tm.tax_id"
					+ " where tax_group_id  in ("+taxGroupId+") ");
			rs=pstmt.executeQuery();
			while(rs.next()){
				buffer.append("<tax_detail>");	
				buffer.append("<tax_id>");
				buffer.append(rs.getInt(1));
				buffer.append("</tax_id>");
				buffer.append("<tax_name>");
				buffer.append(StringEscapeUtils.escapeXml(rs.getString(2)));
				buffer.append("</tax_name>");
				
				buffer.append("<tax_disp_name>");
				buffer.append(StringEscapeUtils.escapeXml(rs.getString(3)) );
				buffer.append("</tax_disp_name>");
				
				buffer.append("<tax_val>");
				double taxVal=0.00f;
				if(rs.getInt(1)==1){
					taxVal=Math.abs(sid.getTotEDVal());
				}
				else if(rs.getInt(1)==2){
					taxVal=Math.abs(sid.getTotECESSVal());
				}
				else if(rs.getInt(1)==3){
					taxVal=Math.abs(sid.getTotSHECESSVal());
				}
				else if(rs.getInt(1)==4){
					taxVal=Math.abs(sid.getTotVATVal());
				}
				else if(rs.getInt(1)==5){
					taxVal=Math.abs(sid.getTotCSTVal());
				}
				else if(rs.getInt(1)==6){
					taxVal=Math.abs(sid.getTotServiceTaxVal());
				}
				else if(rs.getInt(1)==7){
					taxVal=Math.abs(sid.getTotGSTVal());
				}
				buffer.append(NumericFormat.valDf.format(Math.round(taxVal*NumericFormat.valDbRoundoff)/NumericFormat.valDbRoundoff));
				buffer.append("</tax_val>");
				
				buffer.append("</tax_detail>");
				
			}
			
			buffer.append("</tax_details>");
			
			sid=null;
		}
		finally{    
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return buffer.toString();
	}
	public CommonUtil getTotalTaxForAllDet(Connection con,List<CommonUtil> sidList,float discount,TPCSUser ui) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CommonUtil si =new CommonUtil();
		try{

			Map<String,String> locConfigMap=ui.getLocatonConfigMap();
			String isDiscount=(locConfigMap.get("PODiscount")!=null?locConfigMap.get("PODiscount"):"");
			double totValAftDis=0.0f;
			double totEDVal=0.0f;
			double totECESSVal=0.0f;
			double totSHECESSVal=0.0f;
			double totVATVal=0.0f;
			double totCSTVal=0.0f;
			double totGSTVal=0.0f;
			double totServiceTaxVal=0.0f;
			String taxGroupId="";	


			for(CommonUtil sid:sidList){

				double edVal=0.0f;
				double ecessVal=0.0f;
				double shecessVal=0.0f;

				double valueBefDiscount=(Validator.convertToDouble(NumericFormat.qtyDf.format(Math.round(Validator.convertToDouble(sid.getQuantity())*NumericFormat.qtyDbRoundoff)/NumericFormat.qtyDbRoundoff))*Validator.convertToDouble(NumericFormat.priceDf.format(Math.round(Validator.convertToDouble(sid.getPriceFcy())*NumericFormat.priceDbRoundoff)/NumericFormat.priceDbRoundoff)));

				double discountValue=valueBefDiscount*(discount/100);
				if(isDiscount.equalsIgnoreCase("Yes")){
					discountValue=valueBefDiscount*(Validator.convertToFloat(sid.getDiscountPercent())/100);
				}
				double valAftDis=valueBefDiscount-discountValue;

				totValAftDis=totValAftDis+valAftDis;

				taxGroupId=taxGroupId+sid.getTaxGroupId()+",";
				pstmt=con.prepareStatement(" select tg.tax_group_id,tg.tax_group,tg.tax_id,tm.tax_name,tax_display_name, tax_percentage,tg.display_order,tm.display_order "
						+ " from tax_group tg inner join tax_master tm with(nolock) on tg.tax_id=tm.tax_id"
						+ " where tax_group_id =? order by tm.display_order");
				pstmt.setInt(1,sid.getTaxGroupId());
				rs=pstmt.executeQuery();

				while(rs.next()){
					double taxVal=0;
					if(rs.getInt(3)==1){
						taxVal=getEDValue(valAftDis,rs.getDouble(6));
						edVal=taxVal;
						totEDVal=totEDVal+edVal;
					}
					else if(rs.getInt(3)==2){
						taxVal=getECESSValue(rs.getDouble(6),edVal);
						ecessVal=taxVal;
						totECESSVal=totECESSVal+ecessVal;
					}
					else if(rs.getInt(3)==3){
						taxVal=getSHECESSValue(rs.getDouble(6),edVal);
						shecessVal=taxVal;
						totSHECESSVal=totSHECESSVal+shecessVal;
					}
					else if(rs.getInt(3)==4){
						taxVal=getVATValue(valAftDis,rs.getDouble(6),edVal,ecessVal,shecessVal);
						totVATVal=totVATVal+taxVal;
					}
					else if(rs.getInt(3)==5){
						taxVal=getCSTValue(valAftDis,rs.getDouble(6),edVal,ecessVal,shecessVal);
						totCSTVal=totCSTVal+taxVal;
					}
					else if(rs.getInt(3)==6){
						taxVal=(valAftDis)*(rs.getDouble(6)/100);
						totServiceTaxVal=totServiceTaxVal+taxVal;
					}
					else if(rs.getInt(3)==7){
						taxVal=getGSTValue(valAftDis,rs.getDouble(6),edVal,ecessVal,shecessVal);
						totGSTVal=totGSTVal+taxVal;
					}

				}

			}
			taxGroupId=taxGroupId.substring(0,taxGroupId.length()-1);
			si.setTotValAftDis(Validator.convertToDouble(NumericFormat.valDf.format(Math.round(totValAftDis*NumericFormat.valDbRoundoff)/NumericFormat.valDbRoundoff)));
			si.setTotEDVal(totEDVal);
			si.setTotECESSVal(totECESSVal);
			si.setTotSHECESSVal(totSHECESSVal);
			si.setTotVATVal(totVATVal);
			si.setTotCSTVal(totCSTVal);
			si.setTotGSTVal(totGSTVal);
			si.setTotServiceTaxVal(totServiceTaxVal);
			si.setStrTaxGroupIds(taxGroupId);
		}
		finally{    
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return si;
	}
	public double getEDValue(double valAftDis,double taxPercent){
		double edValue=0.0f;
		edValue=(valAftDis)*(taxPercent/100);
		return edValue;
	}
	public double getECESSValue(double taxPercent,double edVal){
		double ecessValue=0.0f;
		ecessValue=edVal*(taxPercent/100);
		return ecessValue;
	}
	public double getSHECESSValue(double taxPercent,double edVal){
		double shecessValue=0.0f;
		shecessValue=edVal*(taxPercent/100);
		return shecessValue;
	}
	public double getVATValue(double valAftDis,double taxPercent,double edVal, double ecessVal, double shecessVal){
		double vatValue=0.0f;
		vatValue=((valAftDis)+edVal+ecessVal+shecessVal)*(taxPercent/100);
		return vatValue;
	}
	public double getCSTValue(double valAftDis,double taxPercent,double edVal, double ecessVal, double shecessVal){
		double cstValue=0.0f;
		cstValue=((valAftDis)+edVal+ecessVal+shecessVal)*(taxPercent/100);
		return cstValue;
	}
	public double getGSTValue(double valAftDis,double taxPercent,double edVal, double ecessVal, double shecessVal){
		double gstValue=0.0f;
		gstValue=((valAftDis)+edVal+ecessVal+shecessVal)*(taxPercent/100);
		return gstValue;
	}
	public double getServiceTaxValue(double valAftDis,double taxPercent){
		double serviceTaxValue=0.0f;
		serviceTaxValue=(valAftDis)*(taxPercent/100);
		return serviceTaxValue;
	}

	public List<TaxGroup> getTaxGroupList(Connection con,TPCSUser ui)throws SQLException,Exception{
	    PreparedStatement pstmt=null;
	    ResultSet rs = null;   
	    List<TaxGroup> objList=new ArrayList<TaxGroup>();
	    try{      
	      
	      pstmt = con.prepareStatement("select distinct tax_group_id,tax_group From tax_group");
	      rs = pstmt.executeQuery();
	      while(rs.next()){
	    	TaxGroup item = new TaxGroup();
	    	item.setTaxGroupId(rs.getInt(1));
	        item.setTaxGroup(rs.getString(2));
	        objList.add(item);
	        item=null;
	      }
	      
	    }finally{     
	      DatabaseConnection.closeAll(pstmt,rs);
	    }
	    return objList;
	  }
	
	public List<CommonUtil> getPurchaseTypeList(Connection con,TPCSUser ui)throws SQLException,Exception{
	    PreparedStatement pstmt=null;
	    ResultSet rs = null;   
	    List<CommonUtil> objList=new ArrayList<CommonUtil>();
	    try{      
	      
	      pstmt = con.prepareStatement("select purchase_type_id,purchase_type from def_purchase_types");
	      rs = pstmt.executeQuery();
	      while(rs.next()){
	    	CommonUtil item = new CommonUtil();
	    	item.setPurchaseTypeId(rs.getInt(1));
	        item.setPurchaseType(rs.getString(2));
	        objList.add(item);
	        item=null;
	      }
	      
	    }finally{     
	      DatabaseConnection.closeAll(pstmt,rs);
	    }
	    return objList;
	  }
}
