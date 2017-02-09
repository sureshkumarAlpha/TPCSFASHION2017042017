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

import com.alpha.tpcsfashion.beans.CommonUtil;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.IndentHeader;
import com.alpha.tpcsfashion.beans.PurchaseOrder;
import com.alpha.tpcsfashion.beans.PurchaseOrderDetail;
import com.alpha.tpcsfashion.beans.PurchaseOrderHeader;
import com.alpha.tpcsfashion.beans.Size;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.TaxGroup;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.servlet.PurchaseOrderServlet;
import com.alpha.tpcsfashion.util.ExportToPdfTool;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class PurchaseOrderDAO
{
	public boolean checkPOExistense(Connection con, TPCSUser userInfo,PurchaseOrder objPO) throws SQLException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		boolean isExists=true;
		try{

			pst=con.prepareStatement(SQLUtil.CHECK_PO_AUTO_NUMBER_EXISTENCE);
			pst.setString(1, objPO.getHeader().getPoNo());
			pst.setString(2, objPO.getHeader().getPrefix());
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
	public PurchaseOrder savePurchaseOrder(Connection con, TPCSUser ui,PurchaseOrder objPO,FileImport objBean) throws Exception {
		CallableStatement cstmt = null;
		PreparedStatement pst=null;
		int trId = 0; 

		try {

			PurchaseOrderHeader header = objPO.getHeader();
			PurchaseOrderDetail detail = objPO.getDetail();
			Map<String,String> locConfigMap=ui.getLocatonConfigMap();
			int qtyRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffQty"));
			int rateRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffRate"));
			int valRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffValue"));

			SQLXML sizeXML = con.createSQLXML();
			sizeXML.setString(detail.getSizeData());
			SQLXML tcXML = con.createSQLXML();
			tcXML.setString(header.getTcValues());

			SQLXML sqlxml = con.createSQLXML();

			cstmt = con.prepareCall("{? = call pr_insert_purchase_order(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER); 
			cstmt.setInt(2,ui.getUserId());
			cstmt.setInt(3, ui.getCompanyId());
			cstmt.setInt(4, ui.getLocationId());
			cstmt.setInt(5, ui.getYearId());
			cstmt.setString(6, objPO.getHeaderMode());
			cstmt.setString(7, objPO.getDetailMode()); 
			cstmt.setInt(8, header.getPoId());
			cstmt.setString(9, header.getPrefix());

			cstmt.setString(10, header.getPoNo());

			if(header.getPoDate()!=null && !header.getPoDate().isEmpty()) {
				cstmt.setString(11, header.getPoDate());
			}
			else{
				cstmt.setNull(11, Types.DATE);
			}
			cstmt.setInt(12, header.getPartyId());
			cstmt.setString(13,header.getOtherRef());
			cstmt.setInt(14, header.getCurrencyId());
			cstmt.setString(15, header.getPrefix());
			cstmt.setString(16, header.getExRate());
			cstmt.setString(17, header.getRemark());
			cstmt.setString(18, header.getPurchaseType());
			cstmt.setString(19, header.getPoValidTill());
			cstmt.setString(20, header.getPaymentTerms());
			cstmt.setString(21, header.getDeliveryTerms());
			cstmt.setString(22, header.getShipToAddress());
			cstmt.setString(23, header.getInternalMemo());
			cstmt.setInt(24, detail.getItemId());
			cstmt.setInt(25, detail.getVariantId());
			cstmt.setInt(26, detail.getSizeRangeId());
			cstmt.setString(27, detail.getOtherSpec());
			cstmt.setString(28, detail.getUom());
			cstmt.setString(29, detail.getQty());
			cstmt.setString(30, detail.getQtyUom());
			cstmt.setString(31, detail.getPriceFcy());
			cstmt.setString(32, detail.getRemark1());
			cstmt.setString(33, detail.getRemark2());
			cstmt.setString(34, detail.getRemark3());


			if(detail.getRequiredDate()!=null && !detail.getRequiredDate().isEmpty()) {
				cstmt.setString(35, detail.getRequiredDate());
			}
			else{
				cstmt.setNull(35, Types.DATE);
			}
			cstmt.setString(36, detail.getRemark());
			cstmt.setInt(37, header.getSeasonId());
			cstmt.setInt(38, detail.getTaxGroupId());
			cstmt.setString(39, detail.getDiscountPercent());
			
		
			cstmt.setInt(40, qtyRoundOff);
			cstmt.setInt(41, rateRoundOff);
			cstmt.setInt(42, valRoundOff);
			cstmt.setSQLXML("detailSizeXML",sizeXML);
			
			
			cstmt.setSQLXML("TCData",tcXML);
			sqlxml.setString(objPO.getSqlxmlDynamicFields());
		
			cstmt.setSQLXML("XmlData",sqlxml);

			cstmt.executeUpdate();
			trId = cstmt.getInt(1);
			objPO.getHeader().setPoId(trId);

			updatePO(objPO,con,locConfigMap,pst);


			cstmt=con.prepareCall("{ call pr_calculate_tax_for_det(?,?,?,?,?)}");
			cstmt.setInt(1, objPO.getHeader().getPoId());
			cstmt.setString(2, "PO");
			cstmt.setInt(3,qtyRoundOff);
			cstmt.setInt(4,rateRoundOff);
			cstmt.setInt(5,valRoundOff);
			cstmt.executeUpdate();

			saveAttachment(con,pst,ui,objPO,objBean);

		} 
		finally {
			DatabaseConnection.closeAll(cstmt, null);
			DatabaseConnection.closeAll(pst, null);
		}
		return objPO;

	}
	
	//Update 
	
	private void updatePO(PurchaseOrder objPO,Connection con,Map<String,String> locConfigMap,PreparedStatement pst) throws SQLException{
		if(objPO.getEditMode().equals("e")){
			
			Statement stmt = null;
			
			stmt = con.createStatement();
			List<Integer> detaIdList=new ArrayList<Integer>();
			Iterator it = objPO.getEditDataMap().entrySet().iterator();
		
			Iterator itDyn = objPO.getEditDynamicDataMap().entrySet().iterator();

			while(it.hasNext()){
				
				Map.Entry mCol= (Map.Entry)it.next();
				
				String strArray[] = ((String) mCol.getKey()).split("_S_");	
				if(strArray.length>1){
					if(!strArray[0].equals("required_date")){
						
						stmt.addBatch("UPDATE PO_details SET "+strArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'  WHERE po_det_id ="+strArray[1]);
						
					}
					else{
						stmt.addBatch("UPDATE PO_details SET "+strArray[0]+"=convert(datetime,'"+mCol.getValue()+"',105)   WHERE po_det_id ="+strArray[1]);
						
						
					}

					detaIdList.add(Integer.parseInt(strArray[1]));
				}
			}

			while(itDyn.hasNext()){
				Map.Entry mCol= (Map.Entry)itDyn.next();
				String strDynArray[] = ((String) mCol.getKey()).split("_S_");


				if(strDynArray.length>1){

					stmt.addBatch("UPDATE PO_Details_Dynamic SET "+strDynArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE po_det_id ="+strDynArray[1]);  
				}
			}


			stmt.executeBatch();
			detaIdList=null;
			
			String isDiscount=(locConfigMap.get("PODiscount")!=null?locConfigMap.get("PODiscount"):"");
			if(objPO.getHeaderMode().equals("e") && isDiscount.equalsIgnoreCase("No")){
				pst=null;
				pst=con.prepareStatement(" update po_details set discount_percent=? where po_id=?");
				pst.setString(1, objPO.getDetail().getDiscountPercent());
				pst.setInt(2, objPO.getHeader().getPoId());
				pst.executeUpdate();			
				
			}
			
			DatabaseConnection.closeAll(stmt, null);
		}
		
		
	}
	
	//For Attachments
	private void saveAttachment(Connection con,PreparedStatement pstmtAttach,TPCSUser ui,PurchaseOrder objPO,FileImport objBean) throws Exception{
		
		int trId=objPO.getHeader().getPoId();

		pstmtAttach = con.prepareStatement(SQLUtil.INSERT_PO_ATTACHMENT);
		pstmtAttach.setInt(1,ui.getCompanyId());
		pstmtAttach.setInt(2,ui.getYearId());
		pstmtAttach.setInt(3,ui.getLocationId());
		pstmtAttach.setInt(4,trId);

		List list= objBean.getFileItems();
		String parentFolder="PurchaseOrder/";
		String folderPath=objBean.getFolderPath().concat(parentFolder);

		File file = new File( folderPath) ;
		if(!file.exists()){
			file.mkdirs();
		}

		FileItem item=null;
		Iterator i = list.iterator();
		String filename=null;
		String fileActualName=null;
		String strSuffix = null,
				strPrefix=null;
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
					}
					else{
						filename = source.substring(source.lastIndexOf("\\") + 1,source.length());
						fileActualName=filename.substring(0, filename.lastIndexOf("."));
						strSuffix = filename.substring(filename.lastIndexOf(".")+1,filename.length());

					}
					if(!objPO.getEditMode().equals("e")){
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
			}
		}
		pstmtAttach.executeBatch();
	}

	public PurchaseOrder getPurchaseOrderDefaultData(Connection con,TPCSUser ui,PurchaseOrder objPO)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs = null;   
		CallableStatement cstmt = null;
		ResultSet rs1 = null; 
		try{      

			PurchaseOrderHeader header=objPO.getHeader();

			getDetailVisFieldCount(con,objPO);

			int prefixCount=0;
			pstmt=con.prepareStatement(SQLUtil.GET_PREFIX_COUNT.replace("?autno_table","set_autono_purchase_order"));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs= pstmt.executeQuery();
			if(rs.next()){
				prefixCount=rs.getInt(1);
			}
			if(prefixCount==1){
				pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_purchase_order"));
				pstmt.setInt(1, ui.getCompanyId());
				pstmt.setInt(2, ui.getLocationId());
				pstmt.setInt(3, ui.getYearId());
				rs = pstmt.executeQuery();
				if(rs.next()){
					header.setPoPrefix(rs.getString(1));
					header.setPoNo(rs.getString(2));
				}
			}

			List<String> autoPrefixList=new ArrayList<String>();

			pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_purchase_order"));
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
			pstmt=con.prepareStatement(SQLUtil.GET_AUTO_NO_TYPE.replace("?autno_table","set_autono_purchase_order"));
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
			PurchaseOrderHeader sqtc=null;
			List<PurchaseOrderHeader> tcList=new ArrayList<PurchaseOrderHeader>();
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
			//	header.setTcList(tcList);
			header.setTcIds(tcBuf.toString());
			header.setTcCnt((idx-1));
			if(tcList.size()==0){
				header.setTcIds("1,");
				header.setTcCnt(1);
			}
			tcBuf=null;
			tcList=null;
			autoPrefixList=null;
			
			
			List<TaxGroup> taxGrouList=new CommonUtilDAO().getTaxGroupList(con,ui);
			header.setTaxGroupList(taxGrouList);
			taxGrouList=null;
			
			List<CommonUtil> purchaseTypeList=new CommonUtilDAO().getPurchaseTypeList(con,ui);
			header.setPurchaseTypeList(purchaseTypeList);
			purchaseTypeList=null;
			
			getTotalTableWidth(con,ui,objPO);
			
			
			objPO.setHeader(header);
			header=null;

		}finally{     
			DatabaseConnection.closeAll(pstmt,rs);
			DatabaseConnection.closeAll(cstmt,rs1);
		}
		return objPO;
	}
	public PurchaseOrder getTotalTableWidth(Connection con,TPCSUser userInfo,PurchaseOrder objPO) throws SQLException,Exception {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{ 
			
			StringBuffer query = new StringBuffer(" select (isnull(SUM(columnwidth),0)*12)+20 columnwidth from DynamicFields df "
	                + " inner join DynamicRefScreenFields drf on df.DynamicFieldId=drf.DynamicFieldId  where drf.Active=1  and df.TableId=?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,objPO.getDetailTableId());
			rs=pstmt.executeQuery(); 
			if(rs.next()){
				objPO.getHeader().setDetTableWidth((rs.getInt(1)));
			}
			 
			query=null;
		  	
		} finally {
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return objPO;
	}
	public PurchaseOrder getPOSizeRangeSizeGrid(Connection con,TPCSUser ui, PurchaseOrder objPO) throws SQLException{
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{


			boolean isSizeExists=false;
			boolean poSizeExists=false;
			String totalSizeQty="0";
			String totalSizePrice="0";
			StringBuffer buffer =new StringBuffer();
			buffer.append("select isnull(size_applicable,0) size_applicable"
					+ " from item it with(nolock)"
					+ " inner join Group_Details gd with(nolock) on it.group_id=gd.group_id"
					+ " where it.item_id=? ");


			pst=con.prepareStatement(buffer.toString());
			pst.setInt(1, objPO.getDetail().getItemId());
			rs=pst.executeQuery();

			if(rs.next()){
				isSizeExists=rs.getInt(1)==1?true:false;
			}
			objPO.getDetail().setSizeExists(isSizeExists);

			buffer=null;
			buffer=new StringBuffer();

			if(objPO.getDetail().isSizeExists()){

				List<Integer> sizeIdList=new ArrayList<Integer>();

				List<Size> sizeList=new ArrayList<Size>();



				if(objPO.getDetail().getPoDetId()>0){

					pst=null;
					pst=con.prepareStatement("select count(*) from po_size where po_id=? and po_det_id=?");
					pst.setInt(1, objPO.getHeader().getPoId());
					pst.setInt(2, objPO.getDetail().getPoDetId());
					rs=pst.executeQuery();
					if(rs.next()){
						poSizeExists=rs.getInt(1)>0?true:false;
					}
					pst=null;

					pst=con.prepareStatement("select isnull(sum(sos.qty),0) qty,isnull(sum(sos.rate),0) rate"
							+ " from size_master sm with(nolock) "
							+ " inner join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
							+ " left join po_size sos with(nolock) on sos.size_id=srd.size_id and sos.Po_Id=? and sos.po_det_Id=? "
							+ " where srd.size_range_id=?");
					pst.setInt(1, objPO.getHeader().getPoId());
					pst.setInt(2, objPO.getDetail().getPoDetId());
					pst.setInt(3, objPO.getDetail().getSizeRangeId());

					rs=pst.executeQuery();
					if(rs.next()){
						totalSizeQty=rs.getString(1);
						totalSizePrice=rs.getString(2);
					}


					buffer.append("select sm.size_id,sm.size_name,isnull(sos.qty,0) qty,isnull(sos.rate,0) rate,isnull(srd.sl_no,0) sl_no "
							+ " from size_master sm with(nolock) "
							+ " inner join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
							+ " left join po_size sos with(nolock) on sos.size_id=srd.size_id and sos.po_Id=? and sos.po_Det_Id=? "
							+ " where srd.size_range_id=?"
							+ " order by srd.sl_no");
					pst=null;
					pst=con.prepareStatement(buffer.toString());
					pst.setInt(1, objPO.getHeader().getPoId());
					pst.setInt(2, objPO.getDetail().getPoDetId());
					pst.setInt(3, objPO.getDetail().getSizeRangeId());

				}
				else{
					buffer.append("select sm.size_id,sm.size_name,0.0 qty,0.0 rate,isnull(srd.sl_no,0) sl_no "
							+ " from size_master sm with(nolock) "
							+ " left join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
							+ " where srd.size_range_id=? ");


					pst=null;

					pst=con.prepareStatement(buffer.toString());
					pst.setInt(1, objPO.getDetail().getSizeRangeId());	
				}

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

				objPO.getDetail().setPoSizeExists(poSizeExists);
				objPO.getDetail().setSizeTotalQty(totalSizeQty);
				objPO.getDetail().setSizeTotalPrice(totalSizePrice);
				objPO.getDetail().setSizeList(sizeList);
				sizeList=null;
				objPO.getDetail().setSizeIdList(sizeIdList);
				sizeIdList=null;

				buffer=null;
			}

		}
		finally{     
			DatabaseConnection.closeAll(pst,rs);
		}

		return objPO;
	}
	public PurchaseOrder getPurchaseOrderInfo(Connection con, TPCSUser ui, PurchaseOrder objPO) throws SQLException,Exception {

		PreparedStatement pst=null;
		PreparedStatement pstmtGetInfoDynamic = null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;

		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListPOHeader = dynamicFieldsDAO.getDynamicFields(con,objPO.getScreenId(), objPO.getHeaderTableId());
		List<DynamicFields> dynamicFieldsListPODetail = dynamicFieldsDAO.getDynamicFields(con,objPO.getScreenId(), objPO.getDetailTableId());
		try{ 


			Map<String,String> locConfigMap=ui.getLocatonConfigMap();

			StringBuffer query = new StringBuffer(SQLUtil.PURCHASE_ORDER_HEADER_INFO);
			pst=con.prepareStatement(query.toString());
			pst.setInt(1,objPO.getHeader().getPoId());
			pst.setInt(2,ui.getCompanyId());
			pst.setInt(3,ui.getLocationId());
			pst.setInt(4,ui.getYearId());
			rs=pst.executeQuery(); 

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.PURCHASE_ORDER_HEADER_INFO_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,objPO.getHeader().getPoId());
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();
			PurchaseOrderHeader header=objPO.getHeader();
			if(rs.next()){

				header.setPoNo(rs.getString(2));
				header.setPrefix(rs.getString(3));
				header.setPoDate(rs.getString(4));
				header.setPartyId(rs.getInt(5));
				header.setPartyName(rs.getString(6));
				header.setCurrencyId(rs.getInt(7));
				header.setCurrencyName(rs.getString(8));
				header.setExRate(rs.getString(9));
				header.setOtherRef(rs.getString(10));
				header.setRemark(rs.getString(11));
				header.setPurchaseType(rs.getString(12));
				header.setPaymentTerms(rs.getString(13));
				header.setDeliveryTerms(rs.getString(14));
				header.setShipToAddress(rs.getString(15));
				header.setInternalMemo(rs.getString(16));
				header.setPoValidTill(rs.getString(17));
				header.setSeasonId(rs.getInt(18));
				header.setSeasonName(rs.getString(19));

				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListPOHeader) {
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
			objPO.setPoDynList(dynamicFieldsListPOHeader);

			getDetailVisFieldCount(con,objPO);


			//Terms & conditions
			StringBuffer tcBuf=new StringBuffer();
			PurchaseOrderHeader sitc=new PurchaseOrderHeader();
			List<PurchaseOrderHeader> tcList=new ArrayList<PurchaseOrderHeader>();
			pst=con.prepareStatement(SQLUtil.SELECT_PO_TC);
			pst.setInt(1, objPO.getHeader().getPoId());
			rs=pst.executeQuery();
			int idx=1;
			while(rs.next()){
				sitc=new PurchaseOrderHeader();
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

			List<String> autoPrefixList=new ArrayList<String>();

			pst = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_purchase_order"));

			pst.setInt(1, ui.getCompanyId());
			pst.setInt(2, ui.getLocationId());
			pst.setInt(3, ui.getYearId());
			rs = pst.executeQuery();
			while(rs.next()){
				autoPrefixList.add(rs.getString(1));
			}
			header.setAutoNoPrefixList(autoPrefixList);
			List<PurchaseOrderDetail> detList=new ArrayList<PurchaseOrderDetail>();

			query = new StringBuffer(SQLUtil.PURCHASE_ORDER_DETAIL_LIST);
			pst=con.prepareStatement(query.toString());

			pst.setInt(1,objPO.getHeader().getPoId());
			pst.setInt(2,ui.getCompanyId());
			pst.setInt(3,ui.getLocationId());
			pst.setInt(4,ui.getYearId());
			rs=pst.executeQuery();

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.PURCHASE_ORDER_DETAIL_LIST_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,objPO.getHeader().getPoId());
		
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

			Map<String,String> detMap=new LinkedHashMap<String ,String>();


			double totValueFcy=0.0d;
			List<Integer> detIdList=new ArrayList<Integer>();
			while(rs.next()){
				PurchaseOrderDetail det=new PurchaseOrderDetail();
				//det.setPoId(rs.getInt(1));

				det.setPoDetId(rs.getInt(2));
				detIdList.add(det.getPoDetId());
//				det.setSlNo(rs.getInt(3));
				det.setItemId(rs.getInt(3));
				det.setItemName(rs.getString(4));
				det.setVariantId(rs.getInt(5));
				det.setVariantName(rs.getString(6));
				det.setSizeRangeId(rs.getInt(7));
				det.setSizeRangeName(rs.getString(8));
				det.setOtherSpec(rs.getString(9));
				det.setUom(rs.getString(10));
				det.setQty(ExportToPdfTool.qtyDf.format(rs.getFloat(11)));
//				det.setPrice(rs.getFloat(13));
				det.setQtyUom(rs.getString(12));
				det.setPriceFcy(ExportToPdfTool.priceDf.format(rs.getFloat(13)));
				det.setRemark1(rs.getString(14));
				det.setRemark2(rs.getString(15));
				det.setRemark3(rs.getString(16));
				det.setRequiredDate(rs.getString(17));
				det.setRemark(rs.getString(18));
				det.setTaxGroupId(rs.getInt(19));
				det.setDiscountPercent(rs.getString(20));
				if(locConfigMap.get("PODiscount")!=null && locConfigMap.get("PODiscount").equalsIgnoreCase("No")){
					det.setValueFcy(ExportToPdfTool.valDf.format(rs.getDouble(21))); //value before discount
				}else{
					det.setValueFcy(ExportToPdfTool.valDf.format(rs.getDouble(22))); //value after discount	
				}
				
				totValueFcy=totValueFcy+Validator.convertToDouble(det.getValueFcy());
				
				header.setDiscountPercent(det.getDiscountPercent()); //for common discount 
				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListPODetail) {
						if(!dynamicFields.isFixed()) {
							String fieldValue = rsGetInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetInfoDynamic.getString(dynamicFields.getDbFieldName());

							if(dynamicFields.getDataTypeName().equalsIgnoreCase("date"))
							{
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
							detMap.put(dynamicFields.getDbFieldName()+det.getPoDetId(), fieldValue);
						}
					}
				}

				objPO.setDetail(det);

				getPOSizeRangeSizeGrid(con,ui, objPO);

				if(objPO.getDetail().isSizeExists()){
					new PurchaseOrderServlet().formSizeData(objPO.getDetail(),ui);
				}
				//new PurchaseOrderServlet.formSizeData(det);

				//getDetailPackingSizeData(con,ui, objPO);

				detList.add(det);
				det=null;
			} 
			
			System.out.println("totValueFcy:"+totValueFcy);
			header.setTotalValueFcy(ExportToPdfTool.valDf.format(totValueFcy));
			objPO.setPoDetList(detList);
			objPO.setPoDetMap(detMap);
			objPO.setDetIdList(detIdList);

			List<PurchaseOrderHeader> attachFiles=new ArrayList<PurchaseOrderHeader>();

			pst= con.prepareStatement(SQLUtil.PURCHASE_ORDER_ATTACHMENT);
			pst.setInt(1, objPO.getHeader().getPoId());
			rs = pst.executeQuery();
			while(rs.next()){
				PurchaseOrderHeader objUrl=new PurchaseOrderHeader();
				objUrl.setPoId(rs.getInt(1));
				objUrl.setUrlType(rs.getString(2));
				objUrl.setUrl(rs.getString(3));
				objUrl.setFileName(rs.getString(4));
				attachFiles.add(objUrl);
				objUrl=null;
			}

			header.setAttachFiles(attachFiles);

			/*List<PurchaseOrderHeader> poUsers=new ArrayList<PurchaseOrderHeader>();
			if(objPO.getHeader().getPoId()>0){

				pst = con.prepareCall(SQLUtil.GET_SQ_SAVED_FOLLOWERS);
				pst.setInt(1, objPO.getHeader().getPoId());
				pst.setInt(2, ui.getUserId());
				rs = pst.executeQuery();
				while(rs.next()){
					PurchaseOrderHeader objSQ=new PurchaseOrderHeader();
				objSQ.setUserId(rs.getInt(1));
					objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));
					objSQ.setPoId(objPO.getHeader().getPoId());

					String url="images/default.jpg";
					if(rs.getString(4)!=null && !rs.getString(4).equals("null")){
						url="images/uploadimages/"+ui.getClientId()+"/contacts/"+rs.getString(4);
					}
					objSQ.setUrl(url);
					objSQ.setLoginName(rs.getString(5));
			 */			 
			//objSQ.setUserRemarkDet(rsGetComponents.getString(4));

			//String delFollower="<img id=\"del_"+iCount+1+"\" class=\"delete_follower_icon\" style=\"display:block;\" src=\""+strPath+"/images/close_icon.png\" title=\"Delete\" onclick=\"deleteFollower('sales_quotaion','flwr_"+iQuoteId+"_"+rs.getInt(1)+"','"+iQuoteId+"','"+rs.getInt(1)+"')\" />";


			//objSQ.setContactImage(rs.getString(4)!=null?"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/uploadimages/"+strClient+"/contacts/"+rs.getString(4)+"\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>" :"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/default.jpg\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>");

			//boUsers.add(objSQ);
			//	iCount++;
			//objSQ=null;

			//}
			//	}
			//			header.setPoUsers(poUsers);
			//			
			//			List<PurchaseOrderHeader> defUsers=new ArrayList<PurchaseOrderHeader>();
			//			int iCount=0;
			//			String strPath=objPO.getClientPath();
			//			String strClient=String.valueOf(ui.getClientId());
			//			if(objPO.getHeader().getPoId()>0){
			//				pst= con.prepareCall(SQLUtil.GET_NOT_SAVED_USER_SQ);
			//				pst.setInt(1, objPO.getHeader().getPoId());
			//			//	pstmt.setInt(2, iUserId);
			//				rs = pst.executeQuery();
			//				while(rs.next()){
			//					PurchaseOrderHeader objSQ=new PurchaseOrderHeader();
			//					objSQ.setUserId(rs.getInt(1));
			//					objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));
			//					//objSQ.setUserRemarkDet(rsGetComponents.getString(4));
			//
			//				//	String delFollower="<img id=\"del_"+iCount+1+"\" class=\"delete_follower_icon\" style=\"display:block;\" src=\""+strPath+"/images/close_icon.png\" title=\"Delete\" onclick=\"deleteFollower('sales_quotaion','flwr_"+objBO.getHeader().getBoId()+"_"+rs.getInt(1)+"','"+objBO.getHeader().getBoId()+"','"+rs.getInt(1)+"')\" />";
			//				//	objSQ.setContactImage(rs.getString(4)!=null?"<td id=\"flwr_"+objBO.getHeader().getBoId()+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/uploadimages/"+strClient+"/contacts/"+rs.getString(4)+"\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>" :"<td id=\"flwr_"+objBO.getHeader().getBoId()+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/default.jpg\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>");
			//
			//					defUsers.add(objSQ);
			//				iCount++;
			//				objSQ=null;
			//			}
			//			
			//			else{
			//
			//				pst= con.repareCall(SQLUtil.GET_ALL_USER);
			//				pst.setInt(1, ui.getUserId());
			//				rs = pst.executeQuery();
			//				while(rs.next()){
			//					BuyerOrderHeader objSQ=new BuyerOrderHeader();
			//				objSQ.setUserId(rs.getInt(1));
			//				objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));            
			//
			//			defUsers.add(objSQ); 
			//					objSQ=null;
			//			}
			//			}
			// header.setDefUsers(defpUsers);
			
			List<TaxGroup> taxGrouList=new CommonUtilDAO().getTaxGroupList(con,ui);
			header.setTaxGroupList(taxGrouList);
			taxGrouList=null;
			
			List<CommonUtil> purchaseTypeList=new CommonUtilDAO().getPurchaseTypeList(con,ui);
			header.setPurchaseTypeList(purchaseTypeList);
			purchaseTypeList=null;
			
			getTotalTableWidth(con,ui,objPO);
			
			objPO.setHeader(header);

			dynamicFieldsDAO=null;
			dynamicFieldsListPODetail=null;
			//query=null;
			//detMap=null;
			//detList=null;
			query=null;

			dynamicFieldsDAO=null;
			dynamicFieldsListPOHeader=null;
			autoPrefixList=null;
			query=null;
			header=null;
		}
		finally {
			DatabaseConnection.closeAll(pst,rs);
			DatabaseConnection.closeAll(pstmtGetInfoDynamic,rsGetInfoDynamic);
		}
		return objPO;


	}
	public void getDetailVisFieldCount(Connection con,PurchaseOrder objPO) throws SQLException{
		PreparedStatement pst= null;
		ResultSet rs = null;
		try {



			objPO.getHeader().setDetailVisFieldCnt(0);
			pst=con.prepareStatement(SQLUtil.DETAIL_VISIBLE_FIELDS_COUNT);
			pst.setInt(1, objPO.getDetailTableId());
			rs=pst.executeQuery();
			while(rs.next()){
				objPO.getHeader().setDetailVisFieldCnt(rs.getInt(1)+1);
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

			StringBuffer query = new StringBuffer(SQLUtil.PURCHASE_ORDER_COUNT);
			query.append(" where po.company_id="+userInfo.getCompanyId()+" and po.location_id="+userInfo.getLocationId()+" and po.year_id="+userInfo.getYearId()+" ");

			if(strSearhQuery!=null && !strSearhQuery.isEmpty()){

				query.append(strSearhQuery);
			}
			pstmt = con.prepareStatement(query.toString());
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
	public PurchaseOrder getAllPurchaseOrderOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,PurchaseOrder objPO)throws SQLException,Exception{
		CallableStatement cstmt=null;
		ResultSet rs = null; 
		List<Map<String,String>> assortList=new ArrayList<Map<String,String>>();
		Map<String,String> map=null;
		PreparedStatement pstmtGetPODetailInfoDynamic =null;
		ResultSet rsGetPODetailInfoDynamic=null;
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
			List<DynamicFields> dynamicFieldsListPOHeader = dynamicFieldsDAO.getDynamicFields(con,objPO.getScreenId(), objPO.getHeaderTableId());
			List<DynamicFields> dynamicFieldsListPODetail = dynamicFieldsDAO.getDynamicFields(con,objPO.getScreenId(), objPO.getDetailTableId());
		
			Map<String,String> colPreMap=new CommonUtilDAO().getColPreMap(con,objPO.getSubdocumentId());

			StringBuffer objBuffer = new StringBuffer(SQLUtil.PURCHASE_ORDER_LIST);

			objBuffer.append(" where po.company_id="+userInfo.getCompanyId()+" and po.location_id="+userInfo.getLocationId()+" and po.year_id="+userInfo.getYearId()+" ");

			if(objPO.getSearchCriteria()!=null && !objPO.getSearchCriteria().isEmpty()){

				objBuffer.append(objPO.getSearchCriteria());
			}
			//objBuffer.append(" group by so.po_id ");
			objBuffer.append(" order by po.po_no ,po.po_prefix " );
			cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
			cstmt.setString(1,objBuffer.toString());
			cstmt.setInt(2,pc.iStart);
			cstmt.setInt(3,pc.iEnd);
			rs=cstmt.executeQuery();
			while(rs.next()){  

				map = new HashMap<String, String>();

				map.put("PurchaseOrder.POId", rs.getString("po_id"));
				map.put("PurchaseOrder.PONo", rs.getString("po_prefix")+rs.getString("po_no"));
				map.put(colPreMap.get("po_date"), rs.getString("po_date"));
				map.put("PurchaseOrder.PODetId", rs.getString("po_det_id"));
				map.put(colPreMap.get("party_id"), rs.getString("buyer_name"));
				map.put(colPreMap.get("other_ref"), rs.getString("other_ref"));
				map.put(colPreMap.get("currency_id"), rs.getString("currency_name"));
				map.put(colPreMap.get("ex_rate"), rs.getString("ex_rate"));
				map.put(colPreMap.get("other_ref"), rs.getString("other_ref"));
				map.put(colPreMap.get("payment_terms"), rs.getString("payment_terms"));
				map.put(colPreMap.get("delivery_terms"), rs.getString("delivery_terms"));
				map.put(colPreMap.get("ship_to_address"), rs.getString("ship_to_address"));
				map.put(colPreMap.get("internal_memo"), rs.getString("internal_memo"));
				map.put(colPreMap.get("hremark"), rs.getString("hremark"));
				map.put(colPreMap.get("item_id"), rs.getString("item_name"));
				map.put(colPreMap.get("variant_id"), rs.getString("variant_name"));
				map.put(colPreMap.get("size_range_id"), rs.getString("size_range"));
				map.put(colPreMap.get("other_spec"), rs.getString("other_spec"));
				map.put(colPreMap.get("uom"), rs.getString("uom"));
				map.put(colPreMap.get("qty"), ExportToPdfTool.qtyDf.format(rs.getDouble("qty")));
//				map.put(colPreMap.get("price"), ExportToPdfTool.priceDf.format(rs.getDouble("price")));
				map.put(colPreMap.get("qty_uom"), rs.getString("qty_uom"));
				map.put(colPreMap.get("price_fcy"), ExportToPdfTool.priceDf.format(rs.getDouble("price_fcy")));
				map.put(colPreMap.get("remark1"), rs.getString("remark1"));
				map.put(colPreMap.get("remark2"), rs.getString("remark2"));
				map.put(colPreMap.get("remark3"), rs.getString("remark3"));
				map.put(colPreMap.get("required_date"), rs.getString("required_date"));
				map.put(colPreMap.get("remark"), rs.getString("remark"));
				map.put("PurchaseOrder.CancelTag", rs.getString("cancel_tag"));
				map.put("PurchaseOrder.CloseTag", rs.getString("close_tag"));

				//Start - Added for dynamic fields
				
				pstmtGetPODetailInfoDynamic = con.prepareStatement(SQLUtil.SELECT_PURCHASE_ORDER_DETAIL_DYNAMIC_BY_ID);
				
				pstmtGetPODetailInfoDynamic.setInt(1, Validator.convertToInteger(rs.getString("po_id")));  
				pstmtGetPODetailInfoDynamic.setInt(2, Validator.convertToInteger(rs.getString("po_det_id"))); 
				
				rsGetPODetailInfoDynamic = pstmtGetPODetailInfoDynamic.executeQuery();
				if(rsGetPODetailInfoDynamic.next()){

					for(DynamicFields dynamicFields : dynamicFieldsListPOHeader) {
					
						if(!dynamicFields.isFixed()){
							
							String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							
							String fieldValue = rsGetPODetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetPODetailInfoDynamic.getString(dynamicFields.getDbFieldName());
						
							if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {
								if(!fieldValue.equals(""))
								{
									SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S "); 
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
					for(DynamicFields dynamicFields : dynamicFieldsListPODetail) {
					
						if(!dynamicFields.isFixed()){
							String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							String fieldValue = rsGetPODetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetPODetailInfoDynamic.getString(dynamicFields.getDbFieldName());
							//  dynamicFields.setFieldValue(fieldValue);  
							if(dynamicFields.getDataTypeName().equalsIgnoreCase("date")) {
								if(!fieldValue.equals(""))
								{
									SimpleDateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S "); 
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
			objPO.setPoMapList(assortList);
	
			imageTypeList=null;
			dynamicFieldsDAO=null;
			dynamicFieldsListPOHeader=null;
			dynamicFieldsListPODetail=null;
			colPreMap=null;
			//strbuffer=null;
			objBuffer=null;
		}
		finally{

			DatabaseConnection.closeAll(cstmt,rs);
		}
		return objPO;
	}
	public PurchaseOrder deletePurchaseOrderDetail(Connection con, TPCSUser userInfo,PurchaseOrder objPO) throws SQLException {
		CallableStatement cstmt = null;
		ResultSet rs = null;    
		try{ 

			objPO.setDeletedCount(0);
			cstmt = con.prepareCall("{call pr_delete_po_detail(?,?,?,?,?)}");
			cstmt.setInt(1, userInfo.getCompanyId());
			cstmt.setInt(2, userInfo.getLocationId());
			cstmt.setInt(3, userInfo.getYearId());
			cstmt.setInt(4, objPO.getHeader().getPoId());
			cstmt.setInt(5, objPO.getDetail().getPoDetId());
			rs=cstmt.executeQuery();
			if(rs.next()){
				objPO.setDeletedCount(rs.getInt(1));
			}


		} finally {
			DatabaseConnection.closeAll(cstmt,rs);
		}
		return objPO;
	}
	public PurchaseOrder deletePurchaseOrder(Connection con,TPCSUser userInfo,PurchaseOrder objPO) throws SQLException {
		CallableStatement cstmt = null;
		try { 

			objPO.setDeletedCount(0);
			cstmt = con.prepareCall("{call pr_delete_po(?,?,?,?)}");
			cstmt.setInt(1, userInfo.getCompanyId());
			cstmt.setInt(2, userInfo.getLocationId());
			cstmt.setInt(3, userInfo.getYearId());
			cstmt.setInt(4, objPO.getHeader().getPoId());
			int deletedCnt=cstmt.executeUpdate();
			objPO.setDeletedCount(deletedCnt);
			objPO.setDeleted(true);

		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return objPO;
	}
	public PurchaseOrder cancelPurchaseOrder(Connection con,TPCSUser userInfo,PurchaseOrder objPO) throws SQLException {
		CallableStatement cstmt = null;
		boolean isCancelled=false;
		try { 

			objPO.setDeletedCount(0);


			cstmt = con.prepareCall("{call pr_cancel_po(?,?,?,?,?)}");
			cstmt.setString(1, userInfo.getDisplayUserName());
			cstmt.setInt(2, userInfo.getCompanyId());
			cstmt.setInt(3, userInfo.getLocationId());
			cstmt.setInt(4, userInfo.getYearId());
			cstmt.setInt(5, objPO.getHeader().getPoId());

			int deletedCnt=cstmt.executeUpdate();
			objPO.setCancelled(deletedCnt>0?true:false);
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return objPO;
	}


	public PurchaseOrder closePurchaseOrder(Connection con,TPCSUser userInfo,PurchaseOrder objPO) throws SQLException {
		CallableStatement cstmt = null;
		try { 

			objPO.setDeletedCount(0);
			cstmt = con.prepareCall("{call pr_close_po(?,?,?,?,?)}");
			cstmt.setString(1, userInfo.getDisplayUserName());
			cstmt.setInt(2, userInfo.getCompanyId());
			cstmt.setInt(3, userInfo.getLocationId());
			cstmt.setInt(4, userInfo.getYearId());
			cstmt.setInt(5, objPO.getHeader().getPoId());
			int deletedCnt=cstmt.executeUpdate();
			objPO.setCancelled(deletedCnt>0?true:false);

		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return objPO;
	}



}
