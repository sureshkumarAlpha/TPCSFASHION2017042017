package com.alpha.tpcsfashion.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.alpha.tpcsfashion.beans.BOMHeader;
import com.alpha.tpcsfashion.beans.BuyerOrder;
import com.alpha.tpcsfashion.beans.BuyerOrderDetail;
import com.alpha.tpcsfashion.beans.BuyerOrderHeader;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.Size;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.servlet.BuyerOrderServlet;
import com.alpha.tpcsfashion.util.ExportToPdfTool;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class BuyerOrderDAO {

	public boolean checkBOExistense(Connection con, TPCSUser userInfo,BuyerOrder objBO) throws SQLException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		boolean isExists=true;
		try{
			pst=con.prepareStatement(SQLUtil.CHECK_BO_AUTO_NUMBER_EXISTENCE);
			pst.setInt(1, objBO.getHeader().getBoNo());
			pst.setString(2, objBO.getHeader().getBoPrefix());
			pst.setInt(3, userInfo.getCompanyId());
			pst.setInt(4, userInfo.getLocationId());
			pst.setInt(5, userInfo.getYearId());

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
	public BuyerOrder saveBuyerOrder(Connection con, TPCSUser ui,BuyerOrder objBO,FileImport objBean) throws Exception {
		CallableStatement cstmt = null;
		Statement stmt = null;
		PreparedStatement pstmtAttach=null;
		int trId = 0; 

		try {

			Map<String,String> locConfigMap=ui.getLocatonConfigMap();
			int qtyRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffQty"));
			int rateRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffRate"));
			int valRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffValue"));
			locConfigMap=null;
			
			BuyerOrderHeader header = objBO.getHeader();
			BuyerOrderDetail detail = objBO.getDetail();

			SQLXML sqlxml = con.createSQLXML();
			SQLXML sizeXML = con.createSQLXML();
			sizeXML.setString(detail.getSizeData());
			
			SQLXML tcXML = con.createSQLXML();
			tcXML.setString(header.getTcValues());
			
			SQLXML psXML = con.createSQLXML();
			psXML.setString(header.getPackingScheduleData());
			
			SQLXML cortanXML = con.createSQLXML();
			cortanXML.setString(detail.getCartonData());

			cstmt = con.prepareCall("{? = call pr_insert_buyer_order(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2,ui.getDisplayUserName());
			cstmt.setInt(3, ui.getCompanyId());
			cstmt.setInt(4, ui.getLocationId());
			cstmt.setInt(5, ui.getYearId());
			cstmt.setString(6, objBO.getHeaderMode());
			cstmt.setString(7, objBO.getDetailMode()); 
			cstmt.setInt(8, header.getBoId());
			cstmt.setString(9, header.getBoPrefix());
			cstmt.setInt(10, header.getBoNo());
			if(header.getBoDate()!=null && !header.getBoDate().isEmpty()) {
				cstmt.setString(11, header.getBoDate());
			}
			else{
				cstmt.setNull(11, Types.DATE);
			}
			cstmt.setInt(12, header.getCustomerId());
			cstmt.setString(13, header.getBuyerPoNo());
			cstmt.setInt(14, header.getCurrencyId());
			cstmt.setString(15, header.getExRate());
			cstmt.setString(16, header.getModeOfShip());
			cstmt.setInt(17, header.getSeasonId());
			cstmt.setString(18, header.getPiNo());
			cstmt.setString(19, header.getPiDate());
			cstmt.setInt(20, header.getAgentId());
			cstmt.setString(21, header.getCustomerPlanNo());
			cstmt.setString(22, header.getPaymentTerms());
			cstmt.setString(23, header.getInsurenceTerms());
			cstmt.setString(24, header.getDeliveryTerms());
			cstmt.setString(25, header.getDeliveryTo());
			cstmt.setString(26, header.getSpecialInstruction());
			cstmt.setString(27, header.getPackingLabeling());
			
			cstmt.setString(28, detail.getLineNo());
			cstmt.setInt(29, detail.getItemId());
			cstmt.setInt(30, detail.getVariantId());
			cstmt.setString(31, detail.getBuyerStyleNo());
			cstmt.setInt(32, detail.getSizeRangeId());
			cstmt.setString(33, detail.getQty());
			cstmt.setString(34, detail.getRate());
			if(detail.getRequiredDate()!=null && !detail.getRequiredDate().isEmpty()) {
				cstmt.setString(35, detail.getRequiredDate());
			}
			else{
				cstmt.setNull(35, Types.DATE);
			}
			if(detail.getPossibleDate()!=null && !detail.getPossibleDate().isEmpty()) {
				cstmt.setString(36, detail.getPossibleDate());
			}
			else{
				cstmt.setNull(36, Types.DATE);
			}
			cstmt.setString(37, detail.getMrp());
			cstmt.setString(38, detail.getBarcode());
			cstmt.setString(39, detail.getRemark());
			
			cstmt.setInt(40, qtyRoundOff);
			cstmt.setInt(41, rateRoundOff);
			cstmt.setInt(42, valRoundOff);
			cstmt.setSQLXML("detailSizeXML",sizeXML);
			sizeXML=null;
			cstmt.setSQLXML("TCData",tcXML);
			tcXML=null;
			cstmt.setSQLXML("PSData",psXML);
			psXML=null;
			cstmt.setSQLXML("CortanData",cortanXML);
			cortanXML=null;
			
			sqlxml.setString(objBO.getSqlxmlDynamicFields());
			cstmt.setSQLXML("XmlData",sqlxml);
			sqlxml=null;

			cstmt.executeUpdate();
			
			trId = cstmt.getInt(1);
			objBO.getHeader().setBoId(trId);

			if(objBO.getEditMode().equals("e")){
				stmt = con.createStatement();
				List<Integer> detaIdList=new ArrayList<Integer>();
				Iterator it = objBO.getEditDataMap().entrySet().iterator();
				Iterator itDyn = objBO.getEditDynamicDataMap().entrySet().iterator();

				while(it.hasNext()){
					Map.Entry mCol= (Map.Entry)it.next();
					String strArray[] = ((String) mCol.getKey()).split("_S_");	
					if(strArray.length>1){
						if(!strArray[0].equals("required_date") && !strArray[0].equals("possible_date")){
							stmt.addBatch("UPDATE Sales_Order_details SET "+strArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE so_det_id ="+strArray[1]);  
						}
						else{
							stmt.addBatch("UPDATE Sales_Order_details SET "+strArray[0]+"=convert(datetime,'"+mCol.getValue()+"',105)   WHERE so_det_id ="+strArray[1]);
						}

						detaIdList.add(Integer.parseInt(strArray[1]));
					}
					trId=header.getBoId();
					objBO.getHeader().setBoId(trId);
				}

				while(itDyn.hasNext()){
					Map.Entry mCol= (Map.Entry)itDyn.next();
					String strDynArray[] = ((String) mCol.getKey()).split("_S_");


					if(strDynArray.length>1){
						
 						stmt.addBatch("UPDATE Sales_Order_Details_Dynamic SET "+strDynArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE bo_det_id ="+strDynArray[1]);  
 					}
					trId=header.getBoId();
					objBO.getHeader().setBoId(trId);
				}


				stmt.executeBatch();
				detaIdList=null;
			}
			
			saveAttachment(con,pstmtAttach,ui,objBO,objBean);
			
			header=null;
			detail=null;

		} finally {
			DatabaseConnection.closeAll(cstmt, null);
			DatabaseConnection.closeAll(pstmtAttach, null);
		}
		return objBO;

	}
	
	private void saveAttachment(Connection con,PreparedStatement pstmtAttach,TPCSUser ui,BuyerOrder objBO,FileImport objBean) throws Exception{
		//For Attachments
		
		int trId=objBO.getHeader().getBoId();
		
		pstmtAttach = con.prepareStatement(SQLUtil.INSERT_BO_ATTACHMENT);
		pstmtAttach.setInt(1,ui.getCompanyId());
		pstmtAttach.setInt(2,ui.getYearId());
		pstmtAttach.setInt(3,ui.getLocationId());
		pstmtAttach.setInt(4,trId);

		List list= objBean.getFileItems();
		String parentFolder="BuyerOrder/";
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

					if( source.lastIndexOf("\\") >= 0 ){

						strSuffix = source.substring(source.lastIndexOf(".")+1,source.length());
						String text=source.substring(source.lastIndexOf("\\") + 1,source.length());
						fileActualName=text.substring(0,text.lastIndexOf("."));
						text=null;
					}
					else{
						filename = source.substring(source.lastIndexOf("\\") + 1,source.length());
						fileActualName=filename.substring(0, filename.lastIndexOf("."));
						strSuffix = filename.substring(filename.lastIndexOf(".")+1,filename.length());

					}
					if(!objBO.getEditMode().equals("e")){
						strPrefix=fileActualName+"_"+trId+"_"+idx+".";
					}else{
						strPrefix=fileActualName+"_"+trId+"_"+idx+".";
					}

					file=new File(folderPath+strPrefix+strSuffix);
					item.write(file);
					idx++;
					pstmtAttach.setString(5, strSuffix);

					pstmtAttach.setString(6, parentFolder+strPrefix+strSuffix);

					pstmtAttach.addBatch();
				}
				fieldName=null;
				source=null;
			}
		}
		pstmtAttach.executeBatch();
		
		parentFolder=null;
		folderPath=null;
		file=null;
	}
	public BuyerOrder getBuyerOrderDefaultData(Connection con,TPCSUser ui,BuyerOrder objBO)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs = null;   
		CallableStatement cstmt = null;
		ResultSet rs1 = null; 
		try{      
 
			BuyerOrderHeader header=objBO.getHeader();

			
			header=getTotalTableWidth(con,ui,header);
			
			getDetailVisFieldCount(con,objBO);
			
			int prefixCount=0;
			pstmt=con.prepareStatement(SQLUtil.GET_PREFIX_COUNT.replace("?autno_table","set_autono_sales_order"));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs= pstmt.executeQuery();
			if(rs.next()){
				prefixCount=rs.getInt(1);
			}
			if(prefixCount==1){
				pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_sales_order"));
				pstmt.setInt(1, ui.getCompanyId());
				pstmt.setInt(2, ui.getLocationId());
				pstmt.setInt(3, ui.getYearId());
				rs = pstmt.executeQuery();
				if(rs.next()){
					header.setBoPrefix(rs.getString(1));
					header.setBoNo(rs.getInt(2));
				}
			}

			List<String> autoPrefixList=new ArrayList<String>();

			pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_sales_order"));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs = pstmt.executeQuery();
			while(rs.next()){
				autoPrefixList.add(rs.getString(1));
			}
			header.setAutoNoPrefixList(autoPrefixList);
			autoPrefixList=null;
			
			String checkType="";
			pstmt=con.prepareStatement(SQLUtil.GET_AUTO_NO_TYPE.replace("?autno_table","set_autono_sales_order"));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs=pstmt.executeQuery();
			if(rs.next()){
				checkType=rs.getString(1);
			}
			header.setCheckAutoNoType(checkType);
			checkType=null;
			
//			//Terms & conditions
			StringBuffer tcBuf=new StringBuffer();
//			BuyerOrderHeader sqtc=null;
			List<BuyerOrderHeader> tcList=new ArrayList<BuyerOrderHeader>();
//			pstmt=con.prepareStatement(SQLUtil.SELECT_BO_DEFAULT_TC);
//			rs=pstmt.executeQuery();
			int idx=1;
//			while(rs.next()){
//				sqtc=new BuyerOrderHeader();
//				sqtc.setSlno(rs.getString(1));
//				sqtc.setTerm(rs.getString(2));
//				sqtc.setSl(idx);
//				tcList.add(sqtc);
//				tcBuf.append(idx+",");
//				idx++;
//				sqtc=null;
//			}
			header.setTcList(tcList);
			header.setTcIds(tcBuf.toString());
			header.setTcCnt((idx-1));
			if(tcList.size()==0){
				header.setTcIds("1,");
				header.setTcCnt(1);
			}
			tcBuf=null;
			tcList=null;
			autoPrefixList=null;
			
			objBO.setHeader(header);
			
			
			
			header=null;

		}finally{     
			DatabaseConnection.closeAll(pstmt,rs);
			DatabaseConnection.closeAll(cstmt,rs1);
		}
		return objBO;
	}
	
	public BuyerOrder getBOSizeRangeSizeGrid(Connection con,TPCSUser ui, BuyerOrder objBO) throws SQLException{
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			
			
			boolean isSizeExists=false;
			boolean boSizeExists=false;
			String totalSizeQty="0";
			StringBuffer buffer =new StringBuffer();
			buffer.append("select isnull(size_applicable,0) size_applicable"
					+ " from item it with(nolock)"
					+ " inner join Group_Details gd with(nolock) on it.group_id=gd.group_id"
					+ " where it.item_id=? ");
			pst=con.prepareStatement(buffer.toString());
			pst.setInt(1, objBO.getDetail().getItemId());
			rs=pst.executeQuery();
			
			if(rs.next()){
				isSizeExists=rs.getInt(1)==1?true:false;
			}
			objBO.getDetail().setSizeExists(isSizeExists);
			
			buffer=null;
			buffer=new StringBuffer();
			
			if(objBO.getDetail().isSizeExists()){
				
				List<Integer> sizeIdList=new ArrayList<Integer>();
			
				List<Size> sizeList=new ArrayList<Size>();
			
			
			
			if(objBO.getDetail().getBoDetId()>0){
			 
				pst=null;
				pst=con.prepareStatement("select count(*) from sales_order_size where so_id=? and so_det_id=?");
				pst.setInt(1, objBO.getHeader().getBoId());
				pst.setInt(2, objBO.getDetail().getBoDetId());
				rs=pst.executeQuery();
				if(rs.next()){
					boSizeExists=rs.getInt(1)>0?true:false;
				}
				pst=null;
				
					pst=con.prepareStatement("select isnull(sum(sos.qty),0) qty"
							+ " from size_master sm with(nolock) "
							+ " inner join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
							+ " left join Sales_Order_Size sos with(nolock) on sos.size_id=srd.size_id and sos.So_Id=? and sos.so_Det_Id=? "
							+ " where srd.size_range_id=?");
					pst.setInt(1, objBO.getHeader().getBoId());
					pst.setInt(2, objBO.getDetail().getBoDetId());
					pst.setInt(3, objBO.getDetail().getSizeRangeId());
					
					rs=pst.executeQuery();
					if(rs.next()){
						totalSizeQty=rs.getString(1);
					}
					
					
					buffer.append("select sm.size_id,sm.size_name,isnull(sos.qty,0) qty,isnull(sos.rate,0) rate,isnull(srd.sl_no,0) sl_no "
							+ " from size_master sm with(nolock) "
							+ " inner join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
							+ " left join Sales_Order_Size sos with(nolock) on sos.size_id=srd.size_id and sos.So_Id=? and sos.so_Det_Id=? "
							+ " where srd.size_range_id=?"
							+ " order by srd.sl_no");
					pst=null;
					pst=con.prepareStatement(buffer.toString());
					pst.setInt(1, objBO.getHeader().getBoId());
					pst.setInt(2, objBO.getDetail().getBoDetId());
					pst.setInt(3, objBO.getDetail().getSizeRangeId());
			
			}
			else{
				buffer.append("select sm.size_id,sm.size_name,0.0 qty,0.0 rate,isnull(srd.sl_no,0) sl_no "
						+ " from size_master sm with(nolock) "
						+ " left join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
						+ " where srd.size_range_id=?"
						+ " order by srd.sl_no");
				pst=null;
				pst=con.prepareStatement(buffer.toString());
				pst.setInt(1, objBO.getDetail().getSizeRangeId());	
			}
			
//			System.out.println("objBO.getHeader().getBoId() :"+objBO.getHeader().getBoId());
//			System.out.println("objBO.getDetail().getBoDetId() :"+objBO.getDetail().getBoDetId());
//			System.out.println("objBO.getDetail().getSizeRangeId() :"+objBO.getDetail().getSizeRangeId());
			
			
//			System.out.println("buffer :"+buffer.toString());
			
			rs=pst.executeQuery();
			
			
			while(rs.next()){
				Size size=new Size();
				size.setSizeId(rs.getInt(1));
				size.setSizeName(rs.getString(2));
				size.setQty(rs.getString(3));
				size.setRate(rs.getString(4));
				size.setSlNo(rs.getInt(5));
				sizeList.add(size);
				sizeIdList.add(size.getSizeId());
				size=null;
			}
			
			objBO.getDetail().setBoSizeExists(boSizeExists);
			objBO.getDetail().setSizeTotalQty(totalSizeQty);
			objBO.getDetail().setSizeList(sizeList);
			sizeList=null;
			objBO.getDetail().setSizeIdList(sizeIdList);
			sizeIdList=null;
			
			buffer=null;
			}
			
		}
		finally{     
			DatabaseConnection.closeAll(pst,rs);
		}
		
		return objBO;
	}
	
	public BuyerOrder getSizeRangeSizeGrid(Connection con,TPCSUser ui, BuyerOrder objBO) throws SQLException{
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			
			boolean isSizeExists=false;
			StringBuffer buffer=new StringBuffer();

			List<Integer> sizeIdList=new ArrayList<Integer>();

			List<Size> sizeList=new ArrayList<Size>();

			buffer.append("select sm.size_id,sm.size_name,0.0 qty,0.0 rate,isnull(srd.sl_no,0) sl_no "
					+ " from size_master sm with(nolock) "
					+ " left join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
					+ " where srd.size_range_id=?"
					+ " order by srd.sl_no");
			pst=con.prepareStatement(buffer.toString());
			pst.setInt(1, objBO.getDetail().getSizeRangeId());	

//			System.out.println("buffer :"+buffer.toString());
			
			rs=pst.executeQuery();
			while(rs.next()){
				isSizeExists=true;
				Size size=new Size();
				size.setSizeId(rs.getInt(1));
				size.setSizeName(rs.getString(2));
				size.setQty(rs.getString(3));
				size.setRate(rs.getString(4));
				size.setSlNo(rs.getInt(5));
				sizeList.add(size);
				sizeIdList.add(size.getSizeId());
				size=null;
			}
			objBO.getDetail().setSizeExists(isSizeExists);
			objBO.getDetail().setSizeList(sizeList);
			sizeList=null;
			objBO.getDetail().setSizeIdList(sizeIdList);
			sizeIdList=null;
			
			buffer=null;
			
		}
		finally{     
			DatabaseConnection.closeAll(pst,rs);
		}
		
		return objBO;
	}
	public BuyerOrder getDetailPackingSizeData(Connection con,TPCSUser ui, BuyerOrder objBO) throws SQLException{
		PreparedStatement pst=null,pst2=null;
		ResultSet rs=null,rs2=null;
		try{
			
			int cortanLocRowId=0;
			boolean cortanExists=false;
			
			List<String> cortanLocRowIdList=new ArrayList<String>();
			
			StringBuffer parentBuffer=new StringBuffer();
			StringBuffer headerBuffer=new StringBuffer();
			StringBuffer qtyBuffer=new StringBuffer();
			
			headerBuffer.append("<tr class=\"header-det\">");
			headerBuffer.append("<th id=\"corton_loc_th\">Destination</th>");

			int packingId=0;
			pst=con.prepareStatement("select distinct pack_type from Sales_Order_Pack_Qty where so_id=? and so_det_id=?");
			pst.setInt(1, objBO.getHeader().getBoId());
			pst.setInt(2, objBO.getDetail().getBoDetId());
			rs=pst.executeQuery();
			while(rs.next()){
				headerBuffer.append("<th id=\"cortan_pack_th_"+objBO.getDetail().getBoDetId()+"_"+packingId+"\">"+rs.getString(1)+"</th>");
				cortanExists=true;
				packingId++;
			}

			headerBuffer.append("<th id=\"cortan_tot_th_"+objBO.getDetail().getBoDetId()+"\">Total</th>");
			headerBuffer.append("<th>&nbsp;</th>");
			headerBuffer.append("</tr>");
			
			
			if(cortanExists){
				pst=con.prepareStatement("select distinct sopq.Destination"
						+ " from Sales_Order_Pack_Qty sopq"
						+ " where sopq.so_id=? and sopq.so_det_id=?");
				pst.setInt(1, objBO.getHeader().getBoId());
				pst.setInt(2, objBO.getDetail().getBoDetId());
				rs=pst.executeQuery();
				while(rs.next()){
					String location=rs.getString(1);

					qtyBuffer.append("<tr id=\"corton_loc_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\">");

					qtyBuffer.append("<td id=\"corton_loc_td_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\">");
					qtyBuffer.append("<div class=\"form-group\" >");
					qtyBuffer.append("<input type=\"text\" class=\"form-control\" id=\"cortan_location_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\" name=\"cortan_location_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\" value=\""+location+"\" maxlength=\"50\" placeholder=\"Enter Location\">");
					qtyBuffer.append("</div>");
					qtyBuffer.append("</td>");

					packingId=0;
					List<String> packingIdList=new ArrayList<String>(); 

					String locTotQty="0";
					String locQty="0";
					pst2=con.prepareStatement("select sopq.Pack_type,isnull(Qty,0) as Qty"
							+ " from Sales_Order_Pack_Qty sopq"
							+ " where sopq.so_id=? and sopq.so_det_id=? and sopq.destination=?");
					pst2.setInt(1, objBO.getHeader().getBoId());
					pst2.setInt(2, objBO.getDetail().getBoDetId());
					pst2.setString(3, location);
					rs2=pst2.executeQuery();
					while(rs2.next()){
						locQty=rs2.getString(2);
						locTotQty=String.valueOf(Validator.convertToDouble(locTotQty)+Validator.convertToDouble(locQty));

						qtyBuffer.append("<td id=\"cortan_pack_td_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"_"+packingId+"\">");
						qtyBuffer.append("<div class=\"form-group\" >");
						qtyBuffer.append("<input type=\"text\" class=\"form-control\" id=\"cortan_qty_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"_"+packingId+"\" name=\"cortan_qty_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"_"+packingId+"\" value=\""+locQty+"\" onkeyup=\"calculateLocationTotal("+objBO.getDetail().getBoDetId()+","+cortanLocRowId+","+packingId+");\" maxlength=\"8\" placeholder=\"Enter qty\">");
						qtyBuffer.append("</div>");
						qtyBuffer.append("</td>");

						packingIdList.add(String.valueOf(packingId));
						packingId++;
						locQty=null;
					}
					packingIdList=null;

					qtyBuffer.append("<td  id=\"cortan_tot_td_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\">");
					qtyBuffer.append("<div class=\"form-group\" >");
					qtyBuffer.append("<input type=\"text\" class=\"form-control\" id=\"cortan_tot_qty_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\" name=\"cortan_tot_qty_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\" value=\""+locTotQty+"\" tabindex=\"-1\" readonly  />");
					qtyBuffer.append("</div>");
					qtyBuffer.append("</td>");

					qtyBuffer.append("<td>"
							+ "<a href=\"javascript:addLocationRow("+objBO.getDetail().getBoDetId()+")\" data-toggle=\"tooltip\" title=\"Add\" data-original-title=\"Add\"><span class=\"span-icon glyphicon glyphicon-plus text-primary\"></span></a>"
							+ "<a href=\"javascript:deleteLocationRow("+objBO.getDetail().getBoDetId()+","+cortanLocRowId+")\" data-toggle=\"tooltip\" title=\"Remove\" data-original-title=\"Remove\"><span class=\"span-icon glyphicon glyphicon-remove icon-delete\"></span></a>"
							+ "</td>");

					qtyBuffer.append("</tr>");


					cortanLocRowIdList.add(String.valueOf(cortanLocRowId));
					cortanLocRowId++;

					location=null;	
					locTotQty=null;
				}
			}
			
			
			if(!cortanExists){
				
				headerBuffer=null;
				headerBuffer=new StringBuffer();
				
				headerBuffer.append("<tr class=\"header-det\">");
				headerBuffer.append("<th id=\"corton_loc_th\">Destination</th>");
				
				packingId=0;
				pst=con.prepareStatement("select distinct packing_type,0 as Qty From sales_order_pack where so_id=?");
				pst.setInt(1, objBO.getHeader().getBoId());
				rs=pst.executeQuery();
				while(rs.next()){
					
					headerBuffer.append("<th id=\"cortan_pack_th_"+objBO.getDetail().getBoDetId()+"_"+packingId+"\">"+rs.getString(1)+"</th>");
					packingId++;
					
				}
				headerBuffer.append("<th id=\"cortan_tot_th_"+objBO.getDetail().getBoDetId()+"\">Total</th>");
				headerBuffer.append("<th>&nbsp;</th>");
				headerBuffer.append("</tr>");
					

				qtyBuffer=null;
				qtyBuffer=new StringBuffer();
				
				cortanLocRowId=0;
				
				cortanLocRowIdList=null;
				cortanLocRowIdList=new ArrayList<String>();
				
				for(int i=0;i<2;i++){
				
				
				qtyBuffer.append("<tr id=\"corton_loc_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\">");
				
				qtyBuffer.append("<td id=\"corton_loc_td_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\">");
				qtyBuffer.append("<div class=\"form-group\">");
				qtyBuffer.append("<input type=\"text\" class=\"form-control\" id=\"cortan_location_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\" name=\"cortan_location_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\" maxlength=\"8\" placeholder=\"Enter Location\">");
				qtyBuffer.append("</div>");
				qtyBuffer.append("</td> ");
				
				String locQty="0";
				packingId=0;
				pst=con.prepareStatement("select distinct packing_type,0 as Qty From sales_order_pack where so_id=?");
				pst.setInt(1, objBO.getHeader().getBoId());
				rs=pst.executeQuery();
				while(rs.next()){

					locQty=rs.getString(2);

					qtyBuffer.append("<td id=\"cortan_pack_td_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"_"+packingId+"\">");
					qtyBuffer.append("<div class=\"form-group\" >");
					qtyBuffer.append("<input type=\"text\" class=\"form-control\" id=\"cortan_qty_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"_"+packingId+"\" name=\"cortan_qty_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"_"+packingId+"\" value=\""+locQty+"\" onkeyup=\"calculateLocationTotal("+objBO.getDetail().getBoDetId()+","+cortanLocRowId+","+packingId+");\" maxlength=\"8\" placeholder=\"Enter qty\">");
					qtyBuffer.append("</div>");
					qtyBuffer.append("</td>");

					packingId++;

					locQty=null;
				}

				qtyBuffer.append("<td  id=\"cortan_tot_td_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\">");
				qtyBuffer.append("<div class=\"form-group\" >");
				qtyBuffer.append("<input type=\"text\" class=\"form-control\" id=\"cortan_tot_qty_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\" name=\"cortan_tot_qty_"+objBO.getDetail().getBoDetId()+"_"+cortanLocRowId+"\" tabindex=\"-1\" readonly  />");
				qtyBuffer.append("</div>");
				qtyBuffer.append("</td>");

				qtyBuffer.append("<td>");
				qtyBuffer.append("<a href=\"javascript:addLocationRow("+objBO.getDetail().getBoDetId()+")\" data-toggle=\"tooltip\" title=\"Add\" data-original-title=\"Add\">");
				qtyBuffer.append("<span class=\"span-icon glyphicon glyphicon-plus text-primary\"></span>");
				qtyBuffer.append("</a>");
				qtyBuffer.append("<a href=\"javascript:deleteLocationRow("+objBO.getDetail().getBoDetId()+","+cortanLocRowId+")\" data-toggle=\"tooltip\" title=\"Remove\" data-original-title=\"Remove\">");
				qtyBuffer.append("<span class=\"span-icon glyphicon glyphicon-remove icon-delete\"></span>");
				qtyBuffer.append("</a>");
				qtyBuffer.append("</td>");
				qtyBuffer.append("</tr>");
					
				
				
				cortanLocRowIdList.add(String.valueOf(cortanLocRowId));
				cortanLocRowId++;
				}
			}
			
			objBO.getDetail().setCortanLocIds(cortanLocRowIdList.toString().replace("[", "").replace("]", "").replace(" ", ""));
			cortanLocRowIdList=null;
			objBO.getDetail().setCortanExists(cortanExists);
			objBO.getDetail().setCortanLocCnt(cortanLocRowId);
			
			parentBuffer=null;
			parentBuffer=new StringBuffer();
			parentBuffer.append("<table class=\"table-bordered table-condensed table-hover cortan-grid cortan_table_"+objBO.getDetail().getBoDetId()+"\" id=\"grid_table\">");
			parentBuffer.append("<head>");
			parentBuffer.append(headerBuffer.toString());
			parentBuffer.append("</head>");
			
			parentBuffer.append("<body>");
			parentBuffer.append(qtyBuffer.toString());
			parentBuffer.append("</body>");
			parentBuffer.append("</table>");
			
			parentBuffer.append("<input type=\"hidden\" id=\"cortan_loc_ids_"+objBO.getDetail().getBoDetId()+"\" name=\"cortan_loc_ids_"+objBO.getDetail().getBoDetId()+"\" value=\""+objBO.getDetail().getCortanLocIds().toString().replace("[", "").replace("]", "").replace(" ", "")+"\"/>");
			parentBuffer.append("<input type=\"hidden\" id=\"cortan_loc_cnt_"+objBO.getDetail().getBoDetId()+"\" name=\"cortan_loc_cnt_"+objBO.getDetail().getBoDetId()+"\" value=\""+objBO.getDetail().getCortanLocCnt()+"\"/>");
			
			objBO.getDetail().setCartonData(parentBuffer.toString());
			parentBuffer=null;
			
			
		}
		finally{     
			DatabaseConnection.closeAll(pst,rs);
			DatabaseConnection.closeAll(pst2,rs2);
		}
		
		return objBO;
	}
	public BuyerOrderHeader getPackingScheduleData(Connection con,TPCSUser ui, BuyerOrderHeader header) throws SQLException{
		PreparedStatement pst=null,pst2=null;
		ResultSet rs=null,rs2=null;
		try{
			
			
			BuyerOrder objBO=new BuyerOrder();
			BuyerOrderDetail detail=new BuyerOrderDetail();
			objBO.setDetail(detail);
			
			StringBuffer buffer=new StringBuffer();
			
			int rowCnt=0;
			String rowIds="0";
			List<String> packingRowIdList=new ArrayList<String>();
			
			pst=con.prepareStatement("select distinct sop.packing_type,sop.size_range_id,sr.size_range"
					+ " From Sales_Order_Pack sop"
					+ " inner join size_range sr on sop.size_range_id=sr.size_range_id"
					+ " where sop.so_id=?");
			pst.setInt(1, header.getBoId());
			rs=pst.executeQuery();
			while(rs.next()){
				
				String packingType=rs.getString(1);
				int sizeRangeId=rs.getInt(2);
				String sizeRange=rs.getString(3);
				
				detail.setBoDetId(rowCnt);
				buffer.append("<div class=\"row\">");
				buffer.append("<div class=\"packing_row_"+rowCnt+"\" id=\"packing_row\">");
				
				buffer.append("<div class=\"col-sm-3\">");
				buffer.append("<div class=\"col-sm-12\">");
				buffer.append("<div class=\"form-group\">");
				buffer.append("<input type=\"text\" name=\"packing_type_"+rowCnt+"\" id=\"packing_type_"+rowCnt+"\" value=\""+packingType+"\" class=\"form-control\" placeholder=\"Enter Packing Type\"/>");
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				
				buffer.append("<div class=\"col-sm-3\">");
				buffer.append("<div class=\"col-sm-12\">");
				buffer.append("<div class=\"form-group\">");
				buffer.append("<input type=\"text\" name=\"packing_size_range_"+rowCnt+"\" id=\"packing_size_range_"+rowCnt+"\" value=\""+sizeRange+"\"  onblur=\"getSizeRangeSizeData("+rowCnt+")\" class=\"form-control\" placeholder=\"Type & Select Size Range\"/>");
				buffer.append("<input type=\"hidden\" name=\"packing_size_range_id_"+rowCnt+"\" id=\"packing_size_range_id_"+rowCnt+"\" value=\""+sizeRangeId+"\" />");
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				
				buffer.append("<div class=\"col-sm-3\">");
				buffer.append("<div class=\"col-sm-3 \" >");
//				if(isRowAdd){
//					buffer.append("<a href=\"javascript:addPackingType("+rowCnt+")\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Add\"><span class=\"glyphicon glyphicon-plus text-primary\"></span></a>&nbsp;");
//				}
//				if(isRowDelete){	
					buffer.append("<a href=\"javascript:deletePackingType("+rowCnt+")\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Remove\" ><span class=\"span-icon glyphicon glyphicon-remove icon-delete\"></span></a>");
//				}
				buffer.append("</div>");
				buffer.append("</div>");
				
				buffer.append("</div>");
				buffer.append("</div>");
				
				buffer.append("<div class=\"row\">");
				buffer.append("<div class=\"col-sm-12\">");
				buffer.append("<div class=\"size_row\" id=\"size_row_"+rowCnt+"\">");
				
			
				List<Integer> sizeIdList=new ArrayList<Integer>();

				List<Size> sizeList=new ArrayList<Size>();

				
				pst2=con.prepareStatement("select sop.size_id,sm.size_name,sop.qty "
						+ " From Sales_Order_Pack sop"
						+ " inner join size_range sr on sop.size_range_id=sr.size_range_id"
						+ " inner join size_master sm on sop.size_id=sm.size_id"
						+ " where sop.so_id=? and sop.packing_type=? and sr.size_range_id=?");
				pst2.setInt(1, header.getBoId());
				pst2.setString(2,packingType);
				pst2.setInt(3,sizeRangeId);
				
				rs2=pst2.executeQuery();
				while(rs2.next()){

					Size size=new Size();
					size.setSizeId(rs2.getInt(1));
					size.setSizeName(rs2.getString(2));
					size.setQty(rs2.getString(3));
					sizeList.add(size);
					sizeIdList.add(size.getSizeId());
					size=null;

				}
				
				objBO.getDetail().setSizeList(sizeList);
				sizeList=null;
				objBO.getDetail().setSizeIdList(sizeIdList);
				sizeIdList=null;
				
				new BuyerOrderServlet().formPackingSizeData(objBO.getDetail());
				
				buffer.append(objBO.getDetail().getSizeData());
				
				buffer.append("</div>");
				buffer.append("</div>");
				buffer.append("</div>");
				if(rowCnt>0){
					
					rowIds=rowIds+","+rowCnt;
				}		
				
				packingRowIdList.add(String.valueOf(rowCnt));
				rowCnt++;
			}
			
			new BuyerOrderServlet().getPackingRow(buffer,rowCnt,true,false);
			
			rowIds=rowIds+","+rowCnt;
			packingRowIdList.add(String.valueOf(rowCnt));
			rowCnt++;

			header.setPackingRowIds(rowIds);
			header.setPackingRowCount(rowCnt);
			header.setPackingRowIdList(packingRowIdList);
			
			System.out.println("packingRowIdList :"+packingRowIdList.toString());
			
			header.setPackingScheduleData(buffer.toString());
			
			buffer=null;
			
			
		}
		finally{     
			DatabaseConnection.closeAll(pst,rs);
			DatabaseConnection.closeAll(pst2,rs2);
		}
		
		return header;
	}
	
	public BuyerOrderHeader getTotalTableWidth(Connection con,TPCSUser userInfo,BuyerOrderHeader boHeader) throws SQLException,Exception {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{ 
			
			StringBuffer query = new StringBuffer(" select (isnull(SUM(columnwidth),0)*12)+40 columnwidth from DynamicFields df "
	                + " inner join DynamicRefScreenFields drf on df.DynamicFieldId=drf.DynamicFieldId where drf.Active=1  and df.TableId=?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,7);
			rs=pstmt.executeQuery(); 
			if(rs.next()){
				boHeader.setDetTableWidth((rs.getInt(1)));
			}
			 
			query=null;
		  	
		} finally {
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return boHeader;
	}
	
	public BuyerOrder getBuyerOrderInfo(Connection con, TPCSUser ui,BuyerOrder objBO) throws SQLException,Exception {
		PreparedStatement pst=null;
		PreparedStatement pstmtGetInfoDynamic = null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;

		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListBOHeader = dynamicFieldsDAO.getDynamicFields(con,objBO.getScreenId(), objBO.getHeaderTableId());
		List<DynamicFields> dynamicFieldsListBODetail = dynamicFieldsDAO.getDynamicFields(con,objBO.getScreenId(), objBO.getDetailTableId());
		try{ 

			
			StringBuffer query = new StringBuffer(SQLUtil.BUYER_ORDER_HEADER_INFO);
			pst=con.prepareStatement(query.toString());
			pst.setInt(1,objBO.getHeader().getBoId());
			pst.setInt(2,ui.getCompanyId());
			pst.setInt(3,ui.getYearId());
			pst.setInt(4,ui.getLocationId());
			rs=pst.executeQuery(); 

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.BUYER_ORDER_HEADER_INFO_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,objBO.getHeader().getBoId());
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

			BuyerOrderHeader header=objBO.getHeader();
			
			
			
			header=getTotalTableWidth(con,ui,header);
			
			
			if(rs.next()){

				header.setBoId(rs.getInt(1));
				header.setBoNo(rs.getInt(2));
				header.setBoPrefix(rs.getString(3));
				header.setBoDate(rs.getString(4));
				header.setCustomerId(rs.getInt(5));
				header.setCustomerName(rs.getString(6));
				header.setBuyerPoNo(rs.getString(7));
				header.setCurrencyId(rs.getInt(8));
				header.setCurrencyName(rs.getString(9));
				header.setExRate(rs.getString(10));
				header.setSeasonId(rs.getInt(11));
				header.setSeasonName(rs.getString(12));
				header.setAgentId(rs.getInt(13));
				header.setAgentName(rs.getString(14));
				header.setCustomerPlanNo(rs.getString(15));
				header.setPaymentTerms(rs.getString(16));
				header.setInsurenceTerms(rs.getString(17));
				header.setDeliveryTerms(rs.getString(18));
				header.setSpecialInstruction(rs.getString(19));
				header.setPackingLabeling(rs.getString(20));


				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListBOHeader) {
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
			objBO.setBoDynList(dynamicFieldsListBOHeader);
			
			getDetailVisFieldCount(con,objBO);
			
//			System.out.println("objBO.getHeader().getBoId() dao:"+objBO.getHeader().getBoId());
			
			//Terms & conditions
			StringBuffer tcBuf=new StringBuffer();
			BuyerOrderHeader sitc=new BuyerOrderHeader();
			List<BuyerOrderHeader> tcList=new ArrayList<BuyerOrderHeader>();
			pst=con.prepareStatement(SQLUtil.SELECT_BO_TC);
			pst.setInt(1, objBO.getHeader().getBoId());
			rs=pst.executeQuery();
			int idx=1;
			while(rs.next()){
				sitc=new BuyerOrderHeader();
				sitc.setSlno(rs.getString(1));
				sitc.setTerm(rs.getString(2));
				sitc.setParticular(rs.getString(3));
				sitc.setSl(idx);
				tcList.add(sitc);
				sitc=null;
				tcBuf.append(idx+",");
				idx++;
			}
			header.setTcList(tcList);
			header.setTcIds(tcBuf.toString());
			header.setTcCnt((idx-1));
			if(tcList.size()==0){
				header.setTcIds("1,");
				header.setTcCnt(1);
			}
			
			getPackingScheduleData(con,ui, header);

			List<String> autoPrefixList=new ArrayList<String>();

			pst = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_sales_order"));
			pst.setInt(1, ui.getCompanyId());
			pst.setInt(2, ui.getLocationId());
			pst.setInt(3, ui.getYearId());
			rs = pst.executeQuery();
			while(rs.next()){
				autoPrefixList.add(rs.getString(1));
			}
			header.setAutoNoPrefixList(autoPrefixList);
			



			List<BuyerOrderDetail> detList=new ArrayList<BuyerOrderDetail>();

			query = new StringBuffer(SQLUtil.BUYER_ORDER_DETAIL_LIST);
			pst=con.prepareStatement(query.toString());
			pst.setInt(1,objBO.getHeader().getBoId());
			pst.setInt(2,ui.getCompanyId());
			pst.setInt(3,ui.getLocationId());
			pst.setInt(4,ui.getYearId());
			rs=pst.executeQuery();

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.BUYER_ORDER_DETAIL_LIST_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,objBO.getHeader().getBoId());
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

			Map<String,String> detMap=new LinkedHashMap<String ,String>();

			
			List<Integer> detIdList=new ArrayList<Integer>();
			while(rs.next()){
				BuyerOrderDetail det=new BuyerOrderDetail();
				//det.setBoId(rs.getInt(1));
				det.setBoDetId(rs.getInt(2));
				detIdList.add(det.getBoDetId());
				det.setLineNo(rs.getString(3));
				det.setItemId(rs.getInt(4));
				det.setItemName(rs.getString(5));
				det.setVariantId(rs.getInt(6));
				det.setVariantName(rs.getString(7));
				det.setSizeRangeId(rs.getInt(8));
				det.setSizeRange(rs.getString(9));
				det.setQty(ExportToPdfTool.qtyDf.format(rs.getDouble(10)));
				det.setShippedQty(String.valueOf(ExportToPdfTool.qtyDf.format(rs.getDouble(11))));
				det.setRate(ExportToPdfTool.priceDf.format(rs.getDouble(12)));
				det.setRequiredDate(rs.getString(13));
				det.setPossibleDate(rs.getString(14));
				det.setMrp(rs.getString(15));
				det.setBarcode(rs.getString(16));
				det.setRemark(rs.getString(17));

				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListBODetail) {
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
							//det.setFieldValue(fieldValue);
							detMap.put(dynamicFields.getDbFieldName()+rs.getInt(2), fieldValue);
						}
					}
				}

				objBO.setDetail(det);
				
//				getSizeRangeSizeGrid(con,ui, objBO);
				getBOSizeRangeSizeGrid(con,ui, objBO);
				
				if(objBO.getDetail().isSizeExists()){
					new BuyerOrderServlet().formSizeData(objBO.getDetail());
				}

				getDetailPackingSizeData(con,ui, objBO);
				
				detList.add(det);
				det=null;
			} 
			objBO.setBoDetList(detList);
			objBO.setBoDetMap(detMap);
			objBO.setDetIdList(detIdList);
			
			List<BuyerOrderHeader> attachFiles=new ArrayList<BuyerOrderHeader>();
			
			pst= con.prepareStatement(SQLUtil.BUYER_ORDER_ATTACHMENT);
			pst.setInt(1, objBO.getHeader().getBoId());
			rs = pst.executeQuery();
			while(rs.next()){
				BuyerOrderHeader objUrl=new BuyerOrderHeader();
				objUrl.setBoId(rs.getInt(1));
				objUrl.setUrlType(rs.getString(2));
				objUrl.setUrl(rs.getString(3));
				objUrl.setFileName(rs.getString(4));
				attachFiles.add(objUrl);
				objUrl=null;
			}
			
			header.setAttachFiles(attachFiles);
			
			
//			List<BuyerOrderHeader> boUsers=new ArrayList<BuyerOrderHeader>();
//			if(objBO.getHeader().getBoId()>0){
//
//				pst = con.prepareCall(SQLUtil.GET_SQ_SAVED_FOLLOWERS);
//				pst.setInt(1, objBO.getHeader().getBoId());
//				pst.setInt(2, ui.getUserId());
//				rs = pst.executeQuery();
//				while(rs.next()){
//					BuyerOrderHeader objSQ=new BuyerOrderHeader();
//					objSQ.setUserId(rs.getInt(1));
//					objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));
//					objSQ.setBoId(objBO.getHeader().getBoId());
//
//					String url="images/default.jpg";
//					if(rs.getString(4)!=null && !rs.getString(4).equals("null")){
//						url="images/uploadimages/"+ui.getClientId()+"/contacts/"+rs.getString(4);
//					}
//					objSQ.setUrl(url);
//					objSQ.setLoginName(rs.getString(5));
//
//					//objSQ.setUserRemarkDet(rsGetComponents.getString(4));
//
//					//String delFollower="<img id=\"del_"+iCount+1+"\" class=\"delete_follower_icon\" style=\"display:block;\" src=\""+strPath+"/images/close_icon.png\" title=\"Delete\" onclick=\"deleteFollower('sales_quotaion','flwr_"+iQuoteId+"_"+rs.getInt(1)+"','"+iQuoteId+"','"+rs.getInt(1)+"')\" />";
//
//
//					//objSQ.setContactImage(rs.getString(4)!=null?"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/uploadimages/"+strClient+"/contacts/"+rs.getString(4)+"\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>" :"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/default.jpg\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>");
//
//					boUsers.add(objSQ);
//					//	iCount++;
//					objSQ=null;
//
//				}
//
//			}
//			header.setBoUsers(boUsers);
			
//			List<BuyerOrderHeader> defUsers=new ArrayList<BuyerOrderHeader>();
//			int iCount=0;
//			String strPath=objBO.getClientPath();
//			String strClient=String.valueOf(ui.getClientId());
//			if(objBO.getHeader().getBoId()>0){
//
//				pst= con.prepareCall(SQLUtil.GET_NOT_SAVED_USER_SQ);
//				pst.setInt(1, objBO.getHeader().getBoId());
//				//pstmt.setInt(2, iUserId);
//				rs = pst.executeQuery();
//				while(rs.next()){
//					BuyerOrderHeader objSQ=new BuyerOrderHeader();
//					objSQ.setUserId(rs.getInt(1));
//					objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));
//					//objSQ.setUserRemarkDet(rsGetComponents.getString(4));
//
//					String delFollower="<img id=\"del_"+iCount+1+"\" class=\"delete_follower_icon\" style=\"display:block;\" src=\""+strPath+"/images/close_icon.png\" title=\"Delete\" onclick=\"deleteFollower('sales_quotaion','flwr_"+objBO.getHeader().getBoId()+"_"+rs.getInt(1)+"','"+objBO.getHeader().getBoId()+"','"+rs.getInt(1)+"')\" />";
//					objSQ.setContactImage(rs.getString(4)!=null?"<td id=\"flwr_"+objBO.getHeader().getBoId()+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/uploadimages/"+strClient+"/contacts/"+rs.getString(4)+"\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>" :"<td id=\"flwr_"+objBO.getHeader().getBoId()+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/default.jpg\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>");
//
//					defUsers.add(objSQ);
//					iCount++;
//					objSQ=null;
//
//				}
//
//			}
//			else{

//				pst= con.repareCall(SQLUtil.GET_ALL_USER);
//				pst.setInt(1, ui.getUserId());
//				rs = pst.executeQuery();
//				while(rs.next()){
//					BuyerOrderHeader objSQ=new BuyerOrderHeader();
//					objSQ.setUserId(rs.getInt(1));
//					objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));            
//
//					defUsers.add(objSQ); 
//					objSQ=null;
//				}
////			}
//			header.setDefUsers(defpUsers);
			objBO.setHeader(header);
			
			dynamicFieldsDAO=null;
			dynamicFieldsListBODetail=null;
			query=null;
			detMap=null;
			detList=null;
			query=null;

			dynamicFieldsDAO=null;
			dynamicFieldsListBOHeader=null;
			autoPrefixList=null;
			query=null;
			header=null;
		} finally {
			DatabaseConnection.closeAll(pst,rs);
			DatabaseConnection.closeAll(pstmtGetInfoDynamic,rsGetInfoDynamic);
		}
		return objBO;


	}
	
	public void getDetailVisFieldCount(Connection con,BuyerOrder objBO) throws SQLException{
		PreparedStatement pst= null;
		ResultSet rs = null;
		try {
			
//			System.out.println("objBO.getDetailTableId() :"+objBO.getDetailTableId());
			
			objBO.getHeader().setDetailVisFieldCnt(0);
			pst=con.prepareStatement(SQLUtil.DETAIL_VISIBLE_FIELDS_COUNT);
			pst.setInt(1, objBO.getDetailTableId());
			rs=pst.executeQuery();
			while(rs.next()){
				objBO.getHeader().setDetailVisFieldCnt(rs.getInt(1)+1);
			}
		}
		finally {
		}

		DatabaseConnection.closeAll(pst,rs);
	}
	
	public int getTotalPages(Connection con,TPCSUser userInfo,PageConfig pc,String strSearhQuery) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pageCount = 0;
		try {

			StringBuffer query = new StringBuffer(SQLUtil.BUYER_ORDER_COUNT);
			if(!strSearhQuery.isEmpty()){
				query.append(strSearhQuery);
			}
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, userInfo.getCompanyId());
			pstmt.setInt(2, userInfo.getLocationId());
			pstmt.setInt(3, userInfo.getYearId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				pageCount = rs.getInt(1);
			}
			query=null;
		} finally {

			DatabaseConnection.closeAll(pstmt,rs);
		}
		return pageCount;

	}
	public BuyerOrder getAllBuyerOrderOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,BuyerOrder objBO)throws SQLException,Exception{
		CallableStatement cstmt=null;
		ResultSet rs = null; 
		List<Map<String,String>> assortList=new ArrayList<Map<String,String>>();
		Map<String,String> map=null;
		PreparedStatement pstmtGetBODetailInfoDynamic =null;
		ResultSet rsGetBODetailInfoDynamic=null;
		PreparedStatement pstmt =null;
		ResultSet rsAttach=null;
		try{
			List<String> imageTypeList=new ArrayList<String>();
			imageTypeList.add("png");
			imageTypeList.add("jpg");
			imageTypeList.add("jpeg");
			imageTypeList.add("gif");
			imageTypeList.add("bmp");
			DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();  
			List<DynamicFields> dynamicFieldsListBOHeader = dynamicFieldsDAO.getDynamicFields(con,objBO.getScreenId(), objBO.getHeaderTableId());
			List<DynamicFields> dynamicFieldsListBODetail = dynamicFieldsDAO.getDynamicFields(con,objBO.getScreenId(), objBO.getDetailTableId());
			
			
			
			Map<String,String> colPreMap=new CommonUtilDAO().getColPreMap(con,objBO.getSubdocumentId());
			StringBuffer objBuffer = new StringBuffer(SQLUtil.BUYER_ORDER_LIST);
			objBuffer.append(" where so.company_id="+userInfo.getCompanyId()+" and so.location_id="+userInfo.getLocationId()+" and so.year_id="+userInfo.getYearId()+" ");
			if(objBO.getSearchCriteria()!=null || !objBO.getSearchCriteria().isEmpty()){
				objBuffer.append(objBO.getSearchCriteria());
			}
//			objBuffer.append(" group by so.so_id ");
	         objBuffer.append(" order by convert(int,so.so_no) desc,so.prefix " );
		
	         System.out.println("objBuffer.toString() :"+objBuffer.toString());
	         
			cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
			cstmt.setString(1,objBuffer.toString());
			cstmt.setInt(2,pc.iStart);
			cstmt.setInt(3,pc.iEnd);
			rs=cstmt.executeQuery();
			while(rs.next()){  

				map = new HashMap<String, String>();

				map.put("BuyerOrder.BoId", rs.getString("so_id"));
				map.put("BuyerOrder.SoDetId", rs.getString("so_det_id"));
				map.put("BuyerOrder.BoNo", rs.getString("prefix")+rs.getString("so_no"));
				map.put(colPreMap.get("so_date"), rs.getString("so_date"));
				map.put(colPreMap.get("party_id"), rs.getString("buyer_name"));
				map.put(colPreMap.get("buyer_po"), rs.getString("buyer_po"));
				map.put(colPreMap.get("currency_id"), rs.getString("currency_name"));
				map.put(colPreMap.get("ex_rate"), rs.getString("ex_rate"));
				map.put(colPreMap.get("season_id"), rs.getString("season_name"));
				map.put(colPreMap.get("agent_id"), rs.getString("agent_name"));
				map.put(colPreMap.get("customer_plan_no"), rs.getString("customer_plan_no"));
				map.put(colPreMap.get("payment_terms"), rs.getString("payment_terms"));
				
				map.put(colPreMap.get("insurance_terms"), rs.getString("insurance_terms"));
				map.put(colPreMap.get("delivery_terms"), rs.getString("delivery_terms"));
				map.put(colPreMap.get("special_instruction"), rs.getString("special_instruction"));
				map.put(colPreMap.get("packing_labeling"), rs.getString("packing_labeling"));
				map.put(colPreMap.get("line_no"), rs.getString("line_no"));
				map.put(colPreMap.get("item_id"), rs.getString("item_name"));
				map.put(colPreMap.get("colour_id"), rs.getString("variant_name"));
				map.put(colPreMap.get("size_range_id"), rs.getString("size_range"));
				map.put(colPreMap.get("qty"), ExportToPdfTool.qtyDf.format(rs.getDouble("qty")));
				map.put(colPreMap.get("shipped_qty"), ExportToPdfTool.qtyDf.format(rs.getDouble("shipped_qty")));
				map.put(colPreMap.get("rate_fcy"), ExportToPdfTool.priceDf.format(rs.getDouble("rate_fcy")));
				
				map.put(colPreMap.get("required_date"), rs.getString("required_date"));
				map.put(colPreMap.get("passible_date"), rs.getString("possible_date"));
				map.put(colPreMap.get("mrp"), rs.getString("mrp"));
				map.put(colPreMap.get("barcode"), rs.getString("barcode"));
				map.put(colPreMap.get("remark"), rs.getString("remark"));
				map.put("BuyerOrder.CancelTag", rs.getString("cancel_tag"));
				map.put("BuyerOrder.CloseTag", rs.getString("close_tag"));
				
 				pstmt = con.prepareStatement(SQLUtil.SELECT_BUYER_ORDER_ATTACHMENT);
				pstmt.setInt(1, Validator.convertToInteger(rs.getString("so_id")));
				rsAttach=pstmt.executeQuery();

				StringBuffer fileNames=new StringBuffer();
				while(rsAttach.next()){
					if(imageTypeList.contains(rsAttach.getString(2).toLowerCase())){
						fileNames.append("<div class=\"grid-image\" ><a target=\"_blank\" href=\""+objBO.getAttachPath().concat(rsAttach.getString(3))+"\"  title=\"view\"><img src=\""+objBO.getAttachPath().concat(rsAttach.getString(3))+"\" width=\"30\"></a></div>");
					}
					else{
						fileNames.append("<a target=\"_blank\" href=\""+objBO.getAttachPath().concat(rsAttach.getString(3))+"\"  title=\"view\">"+rsAttach.getString(3)+"</a><br/>");
					}
				}
				map.put(colPreMap.get("BO.Attachment"), fileNames.toString());
				fileNames=null;

				//Start - Added for dynamic fields
				pstmtGetBODetailInfoDynamic = con.prepareStatement(SQLUtil.SELECT_BUYER_ORDER_DETAIL_DYNAMIC_BY_ID);
				pstmtGetBODetailInfoDynamic.setInt(1, Validator.convertToInteger(rs.getString("so_id")));  
				pstmtGetBODetailInfoDynamic.setInt(2, Validator.convertToInteger(rs.getString("so_det_id"))); 
				rsGetBODetailInfoDynamic = pstmtGetBODetailInfoDynamic.executeQuery();
				if(rsGetBODetailInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListBOHeader) {
						if(!dynamicFields.isFixed()){
							String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							String fieldValue = rsGetBODetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetBODetailInfoDynamic.getString(dynamicFields.getDbFieldName());
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
					for(DynamicFields dynamicFields : dynamicFieldsListBODetail) {
						if(!dynamicFields.isFixed()){
							String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							String fieldValue = rsGetBODetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetBODetailInfoDynamic.getString(dynamicFields.getDbFieldName());
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
				assortList.add(map);
				map=null;
			}

			objBO.setBoMapList(assortList);
			
			imageTypeList=null;
			dynamicFieldsDAO=null;
			dynamicFieldsListBOHeader=null;
			dynamicFieldsListBODetail=null;
			colPreMap=null;
			objBuffer=null;
		}
		finally{

			DatabaseConnection.closeAll(cstmt,rs);
		}
		return objBO;
	}
	public BuyerOrder deleteBuyerOrderDetail(Connection con, TPCSUser userInfo,BuyerOrder objBO) throws SQLException {
		CallableStatement cstmt = null;
		ResultSet rs = null;    
		try{ 
			
			objBO.setDeletedCount(0);
			cstmt = con.prepareCall("{call pr_delete_bo_detail(?,?,?,?,?)}");
			cstmt.setInt(1, userInfo.getCompanyId());
			cstmt.setInt(2, userInfo.getLocationId());
			cstmt.setInt(3, userInfo.getYearId());
			cstmt.setInt(4, objBO.getHeader().getBoId());
			cstmt.setInt(5, objBO.getDetail().getBoDetId());
			rs=cstmt.executeQuery();
			if(rs.next()){
				objBO.setDeletedCount(rs.getInt(1));
			}
		

		} finally {
			DatabaseConnection.closeAll(cstmt,rs);
		}
		return objBO;
	}
	
	public BuyerOrder deleteBuyerOrder(Connection con,TPCSUser userInfo,BuyerOrder objBO) throws SQLException {
		CallableStatement cstmt = null;
		try { 
			
			objBO.setDeletedCount(0);
			cstmt = con.prepareCall("{call pr_delete_bo(?,?,?,?)}");
			cstmt.setInt(1, userInfo.getCompanyId());
			cstmt.setInt(2, userInfo.getLocationId());
			cstmt.setInt(3, userInfo.getYearId());
			cstmt.setInt(4, objBO.getHeader().getBoId());
			int deletedCnt=cstmt.executeUpdate();
			objBO.setDeletedCount(deletedCnt);
			objBO.setDeleted(true);
			
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return objBO;
	}
	public BuyerOrder cancelBuyerOrder(Connection con,TPCSUser userInfo,BuyerOrder objBO) throws SQLException {
		CallableStatement cstmt = null;
		try { 
			
			objBO.setDeletedCount(0);
			cstmt = con.prepareCall("{call pr_cancel_bo(?,?,?,?,?)}");
			cstmt.setString(1, userInfo.getDisplayUserName());
			cstmt.setInt(2, userInfo.getCompanyId());
			cstmt.setInt(3, userInfo.getLocationId());
			cstmt.setInt(4, userInfo.getYearId());
			cstmt.setInt(5, objBO.getHeader().getBoId());
			int deletedCnt=cstmt.executeUpdate();
			objBO.setCancelled(deletedCnt>0?true:false);
			
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return objBO;
	}
	
	public BuyerOrder closeBuyerOrder(Connection con,TPCSUser userInfo,BuyerOrder objBO) throws SQLException {
		CallableStatement cstmt = null;
		try { 
			
			objBO.setDeletedCount(0);
			cstmt = con.prepareCall("{call pr_close_bo(?,?,?,?,?)}");
			cstmt.setString(1, userInfo.getDisplayUserName());
			cstmt.setInt(2, userInfo.getCompanyId());
			cstmt.setInt(3, userInfo.getLocationId());
			cstmt.setInt(4, userInfo.getYearId());
			cstmt.setInt(5, objBO.getHeader().getBoId());
			int deletedCnt=cstmt.executeUpdate();
			objBO.setCancelled(deletedCnt>0?true:false);
			
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return objBO;
	}
}
