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
import org.apache.commons.lang.StringEscapeUtils;

import com.alpha.tpcsfashion.beans.BOMHeader;
import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.Indent;
import com.alpha.tpcsfashion.beans.IndentDetail;
import com.alpha.tpcsfashion.beans.IndentHeader;
import com.alpha.tpcsfashion.beans.Size;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.servlet.BuyerOrderServlet;
import com.alpha.tpcsfashion.servlet.IndentServlet;
import com.alpha.tpcsfashion.util.ExportToPdfTool;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class IndentDAO {
	
	
	public Indent getIndentInfo(Connection con, TPCSUser ui,Indent objIND) throws SQLException,Exception {
		PreparedStatement pst=null;
		PreparedStatement pstmtGetInfoDynamic = null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rsGetInfoDynamic = null;

		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListINDHeader = dynamicFieldsDAO.getDynamicFields(con,objIND.getScreenId(), objIND.getHeaderTableId());
		List<DynamicFields> dynamicFieldsListINDDetail = dynamicFieldsDAO.getDynamicFields(con,objIND.getScreenId(), objIND.getDetailTableId());
		try{ 

	
			
			StringBuffer query = new StringBuffer(SQLUtil.INDENT_HEADER_INFO);
			
//			System.out.println("getIndentInfo Query ==== " +query);
			
			pst=con.prepareStatement(query.toString());
			pst.setInt(1,objIND.getHeader().getIndentId());
			pst.setInt(2,ui.getCompanyId());
			pst.setInt(3,ui.getYearId());
			pst.setInt(4,ui.getLocationId());
			rs=pst.executeQuery(); 
			

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.INDENT_HEADER_INFO_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,objIND.getHeader().getIndentId());
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

			IndentHeader header=objIND.getHeader();
			if(rs.next()){

		header.setIndentId(rs.getInt(1));
		header.setIndentNo(rs.getInt(2));
		header.setIndentPrefix(rs.getString(3));
		header.setIndentDate(rs.getString(4));
		header.setIndentType(rs.getString(5));
		header.setDept(rs.getString(6));
		header.setRemarks(rs.getString(7));
		header.setOtherRef(rs.getString(8));
		
		
//		System.out.println("getIndentInfo=====setRemarks "+rs.getString(7));
//		System.out.println("getIndentInfo=====setOtherRef "+rs.getString(8));
			

				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListINDHeader) {
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
			objIND.setIndentDynList(dynamicFieldsListINDHeader);           
			
			getDetailVisFieldCount(con,objIND);
			
//			System.out.println("objIND.getHeader().getIndentId() dao:"+objIND.getHeader().getIndentId());
			
			//Terms & conditions
			StringBuffer tcBuf=new StringBuffer();
			IndentHeader sitc=new IndentHeader();
			List<IndentHeader> tcList=new ArrayList<IndentHeader>();
			pst=con.prepareStatement(SQLUtil.SELECT_INDENT_TC);
			pst.setInt(1, objIND.getHeader().getIndentId());
			rs=pst.executeQuery();
			int idx=1;
			while(rs.next()){
				sitc=new IndentHeader();
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
		


			header=getTotalTableWidth(con,ui,header);
			
			List<String> autoPrefixList=new ArrayList<String>();

			pst = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_indent"));
			pst.setInt(1, ui.getCompanyId());
			pst.setInt(2, ui.getLocationId());
			pst.setInt(3, ui.getYearId());
			rs = pst.executeQuery();
			while(rs.next()){
				autoPrefixList.add(rs.getString(1));
			}
			header.setAutoNoPrefixList(autoPrefixList);
			



			List<IndentDetail> detList=new ArrayList<IndentDetail>();

			query = new StringBuffer(SQLUtil.INDENT_DETAIL_LIST);
			pst=con.prepareStatement(query.toString());
			pst.setInt(1,objIND.getHeader().getIndentId());
			pst.setInt(2,ui.getCompanyId());
			pst.setInt(3,ui.getLocationId());
			pst.setInt(4,ui.getYearId());
			rs=pst.executeQuery(); 

			
			
			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.INDENT_DETAIL_LIST_DYNAMIC);
			
			pstmtGetInfoDynamic.setInt(1,objIND.getHeader().getIndentId());
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();
			
//			System.out.println("getIndentInfo==== INDENT_DETAIL_LIST_DYNAMIC=== " +pstmtGetInfoDynamic);
			

			Map<String,String> detMap=new LinkedHashMap<String ,String>();

			
			List<Integer> detIdList=new ArrayList<Integer>();
			while(rs.next()){
				
				IndentDetail det=new IndentDetail();
				det.setIndentId(rs.getInt(1));
				det.setIndentDetId(rs.getInt(2));
				detIdList.add(det.getIndentDetId());
				//det.setIndentLineNo(rs.getString(3));
				det.setItemId(rs.getInt(3));
				det.setItemName(rs.getString(4));
				det.setColourId(rs.getInt(5));
				det.setColourName(rs.getString(6));
				det.setSizeRangeId(rs.getInt(7));
				det.setSizeRange(rs.getString(8));
				det.setIndentQty(ExportToPdfTool.qtyDf.format(rs.getDouble(9)));
				det.setUom(rs.getString(10));
				det.setPartyId(rs.getString(11));
				det.setPartyName(rs.getString(12));
				det.setRequiredDate(rs.getString(13));
				det.setRemark(rs.getString(14));
				det.setUomId(rs.getString(15));
				det.setSoDetId(rs.getInt(16));
				det.setGroupId(rs.getInt(17));
				det.setGroupName(rs.getString(18));
				
				//So Ref--------------------
				pst=con.prepareCall(" select so.prefix+CONVERT(nvarchar,so_no) soNo,isnull(it.item_Name,'') item_Name,isnull(var.variant_name,'') variant_name,isnull(sr.size_range,'') size_range,convert(nvarchar,sod.Required_Date,105) Required_Date, "+  
					" sod.so_det_id from Sales_Order so "+   
					" Inner join Sales_Order_Details sod on so.SO_Id=sod.SO_Id and so.Company_Id=sod.Company_Id "+  
					" and so.location_id=sod.Location_Id and so.Year_Id=sod.Year_Id   "+
					" inner join item it on it.Item_Id=sod.Item_Id  "+
					" left join Variant_Master var on var.Variant_Id=sod.Variant_Id "+  
					" inner join size_range sr with(nolock) on sr.size_range_id=sod.size_range_id   ");
				rs1 = pst.executeQuery();
				if (rs1.next()) {
					det.setSoNo(rs1.getString(1)+" / "+rs1.getString(2)+" / "+rs1.getString(3)+" / "+rs1.getString(4)+" / "+rs1.getString(5));
				}

				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListINDDetail) {
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
							detMap.put(dynamicFields.getDbFieldName()+det.getIndentDetId(), fieldValue);
						}
					}
				}

				objIND.setDetail(det);
				
				
				
				getINDSizeRangeSizeGrid(con,ui, objIND);
				if(objIND.getDetail().isSizeExists()){
					new IndentServlet().formSizeData(objIND.getDetail());
				}
				
				detList.add(det);
				//System.out.println("detList in:"+detList.size());
				det=null;
			} 
			
			//System.out.println("detList :"+detList.size());
			objIND.setIndentDetList(detList);
			objIND.setIndentDetMap(detMap);
			objIND.setDetIdList(detIdList);
			
			List<IndentHeader> attachFiles=new ArrayList<IndentHeader>();
			
			pst= con.prepareStatement(SQLUtil.INDENT_ATTACHMENT);
			pst.setInt(1, objIND.getHeader().getIndentId());
			rs = pst.executeQuery();
			while(rs.next()){
				IndentHeader objUrl=new IndentHeader();
				objUrl.setIndentId(rs.getInt(1));
				objUrl.setUrlType(rs.getString(2));
				objUrl.setUrl(rs.getString(3));
				objUrl.setFileName(rs.getString(4));
				attachFiles.add(objUrl);
				objUrl=null;
			}
			
			header.setAttachFiles(attachFiles);
			
			objIND.setHeader(header);
			
			dynamicFieldsDAO=null;
			dynamicFieldsListINDDetail=null;
			query=null;
			detMap=null;
			detList=null;
			query=null;

			dynamicFieldsDAO=null;
			dynamicFieldsListINDHeader=null;
			autoPrefixList=null;
			query=null;
			header=null;
		} finally {
			DatabaseConnection.closeAll(pst,rs);
			DatabaseConnection.closeAll(null,rs1);
			DatabaseConnection.closeAll(pstmtGetInfoDynamic,rsGetInfoDynamic);
		}
		return objIND;


	}
	

	
	
	
	public void getDetailVisFieldCount(Connection con,Indent objIND) throws SQLException{
		PreparedStatement pst= null;
		ResultSet rs = null;
		try {
			
//			System.out.println("objIND.getDetailTableId() :"+objIND.getDetailTableId());
			
			objIND.getHeader().setDetailVisFieldCnt(0);
			pst=con.prepareStatement(SQLUtil.DETAIL_VISIBLE_FIELDS_COUNT);
			pst.setInt(1, objIND.getDetailTableId());
			rs=pst.executeQuery();
			while(rs.next()){
			objIND.getHeader().setDetailVisFieldCnt(rs.getInt(1)+1);
				
				
			}
		}
		finally {
		}

		DatabaseConnection.closeAll(pst,rs);
	}
	
	
	
	
	
	public boolean checkINDExistense(Connection con, TPCSUser userInfo,Indent objIND) throws SQLException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		boolean isExists=true;
		try{
			pst=con.prepareStatement(SQLUtil.CHECK_IND_AUTO_NUMBER_EXISTENCE);
			pst.setInt(1, objIND.getHeader().getIndentNo());
			pst.setString(2, objIND.getHeader().getIndentPrefix());
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
	
	
	
	public Indent saveIndent(Connection con, TPCSUser ui,Indent objIND,FileImport objBean) throws Exception {
		CallableStatement cstmt = null;
		Statement stmt = null;
		PreparedStatement pstmtAttach=null;
		int trId = 0; 

		try {

			Map<String,String> locConfigMap=ui.getLocatonConfigMap();
//			int qtyRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffQty"));
//			int rateRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffRate"));
//			int valRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffValue"));
			
			IndentHeader header = objIND.getHeader();
			IndentDetail detail = objIND.getDetail();

			SQLXML sqlxml = con.createSQLXML();
			
			
			SQLXML sizeXML = con.createSQLXML();
			sizeXML.setString(detail.getSizeData());
			
//			System.out.println("saveIndent==== " +detail.getSizeData().toString());
			
			SQLXML tcXML = con.createSQLXML();
			tcXML.setString(header.getTcValues());
			


			cstmt = con.prepareCall("{? = call pr_insert_indent(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2,ui.getDisplayUserName());
			cstmt.setInt(3, ui.getCompanyId());
			cstmt.setInt(4, ui.getLocationId());
			cstmt.setInt(5, ui.getYearId());
			cstmt.setString(6, objIND.getHeaderMode());
			cstmt.setString(7, objIND.getDetailMode()); 
			cstmt.setInt(8, header.getIndentId());
			cstmt.setString(9, header.getIndentPrefix());
			cstmt.setInt(10, header.getIndentNo());
			if(header.getIndentDate()!=null && !header.getIndentDate().isEmpty()) {
				cstmt.setString(11, header.getIndentDate());
			}
			else{
				cstmt.setNull(11, Types.DATE);
			}
			cstmt.setString(12, header.getIndentType());
			cstmt.setString(13, header.getDept());
			cstmt.setString(14, header.getRemarks());
			
//			System.out.println("in save dao procedure== getRemarks=== " + header.getRemarks());
			
			cstmt.setString(15, header.getOtherRef());

			
			cstmt.setString(16, detail.getIndentLineNo());
			cstmt.setInt(17, detail.getItemId());
			cstmt.setInt(18, detail.getColourId());
			cstmt.setInt(19, detail.getSizeRangeId());
			cstmt.setString(20, detail.getIndentQty());
			cstmt.setString(21, detail.getUom());
			cstmt.setString(22, detail.getPartyId());
			
			if(detail.getRequiredDate()!=null && !detail.getRequiredDate().isEmpty()) {
				cstmt.setString(23, detail.getRequiredDate());
			}
			else{
				cstmt.setNull(23, Types.DATE);
			}
			
			cstmt.setString(24, detail.getRemark());
			cstmt.setInt(25, detail.getSoDetId());
			

			cstmt.setSQLXML("detailSizeXML",sizeXML);
			cstmt.setSQLXML("TCData",tcXML);
			sqlxml.setString(objIND.getSqlxmlDynamicFields());
			
			cstmt.setSQLXML("XmlData",sqlxml);

			cstmt.executeUpdate();
			
			
			trId = cstmt.getInt(1);
			objIND.getHeader().setIndentId(trId);

			if(objIND.getEditMode().equals("e")){
				stmt = con.createStatement();
				List<Integer> detaIdList=new ArrayList<Integer>();
				Iterator it = objIND.getEditDataMap().entrySet().iterator();
				Iterator itDyn = objIND.getEditDynamicDataMap().entrySet().iterator();

				while(it.hasNext()){
					Map.Entry mCol= (Map.Entry)it.next();
					String strArray[] = ((String) mCol.getKey()).split("_S_");	
				
					System.out.println("id"+strArray[1]);
					
					if(strArray.length>1){
						if(!strArray[0].equals("required_date")){
							
							
							stmt.addBatch("UPDATE Indent_details SET "+strArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE indent_det_id ="+strArray[1]);  
					
						}
						else{
							stmt.addBatch("UPDATE Indent_details SET "+strArray[0]+"=convert(datetime,'"+mCol.getValue()+"',105)   WHERE indent_det_id ="+strArray[1]);
						}

						detaIdList.add(Integer.parseInt(strArray[1]));
					}
					trId=header.getIndentId();
					objIND.getHeader().setIndentId(trId);
				}

				while(itDyn.hasNext()){
					Map.Entry mCol= (Map.Entry)itDyn.next();
					String strDynArray[] = ((String) mCol.getKey()).split("_S_");


					if(strDynArray.length>1){
						
 						stmt.addBatch("UPDATE Indent_Details_Dynamic SET "+strDynArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE indent_det_id ="+strDynArray[1]);  
 					}
					trId=header.getIndentId();
					objIND.getHeader().setIndentId(trId);
				}


				stmt.executeBatch();
				detaIdList=null;
			}
			
			saveAttachment(con,pstmtAttach,ui,objIND,objBean);

		} finally {
			DatabaseConnection.closeAll(cstmt, null);
			DatabaseConnection.closeAll(pstmtAttach, null);
		}
		return objIND;

	}
	
	
	
	
	
	private void saveAttachment(Connection con,PreparedStatement pstmtAttach,TPCSUser ui,Indent objIND,FileImport objBean) throws Exception{
		//For Attachments
		
		int trId=objIND.getHeader().getIndentId();
		
		pstmtAttach = con.prepareStatement(SQLUtil.INSERT_IND_ATTACHMENT);
		pstmtAttach.setInt(1,ui.getCompanyId());
		pstmtAttach.setInt(2,ui.getYearId());
		pstmtAttach.setInt(3,ui.getLocationId());
		pstmtAttach.setInt(4,trId);

		List list= objBean.getFileItems();
		String parentFolder="Indent/";
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
					}
					else{
						filename = source.substring(source.lastIndexOf("\\") + 1,source.length());
						fileActualName=filename.substring(0, filename.lastIndexOf("."));
						strSuffix = filename.substring(filename.lastIndexOf(".")+1,filename.length());

					}
					if(!objIND.getEditMode().equals("e")){
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
	
	
	public Indent getSoNoDetailList(Connection con) throws SQLException, Exception {
		// TODO Auto-generated method stub
		Indent obj=new Indent();
		List<IndentDetail> indList=new ArrayList<IndentDetail>();;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{

			
			cstmt=con.prepareCall("{call pr_getSODet_ForIndent()}");
			rs = cstmt.executeQuery();

			while (rs.next()) {
				IndentDetail ind=new IndentDetail();
				ind.setSoNo(rs.getString(1));
				ind.setSoDate(rs.getString(2));
				ind.setSoPartyName(rs.getString(3));
				ind.setSoItemName(rs.getString(4));
				ind.setSoColourName(rs.getString(5));
				ind.setSoSizeRange(rs.getString(6));
				ind.setSoRequiredDate(rs.getString(7));
				ind.setSoDetId(rs.getInt(8));
				indList.add(ind);
				
				ind=null;
			}
			obj.setIndentDetList(indList);
		}
		finally
		{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return obj;
	}
	
	
	public IndentHeader getTotalTableWidth(Connection con,TPCSUser userInfo,IndentHeader indHeader) throws SQLException,Exception {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{ 
			
			StringBuffer query = new StringBuffer(" select (isnull(SUM(columnwidth),0)*12)+20 columnwidth from DynamicFields df "
	                + " inner join DynamicRefScreenFields drf on df.DynamicFieldId=drf.DynamicFieldId  where drf.Active=1  and df.TableId=?");
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,9);
			rs=pstmt.executeQuery(); 
			if(rs.next()){
				indHeader.setDetTableWidth((rs.getInt(1)));
			}
			 
			query=null;
		  	
		} finally {
			DatabaseConnection.closeAll(pstmt,rs);
		}
		return indHeader;
	}

	
	public Indent getIndentDefaultData(Connection con,TPCSUser ui,Indent objIND)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs = null;   
		CallableStatement cstmt = null;
		ResultSet rs1 = null; 
		try{      
 
			IndentHeader header=objIND.getHeader();

			
			header=getTotalTableWidth(con,ui,header);
			getDetailVisFieldCount(con,objIND);
			
			int prefixCount=0;
			pstmt=con.prepareStatement(SQLUtil.GET_PREFIX_COUNT.replace("?autno_table","set_autono_indent"));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs= pstmt.executeQuery();
			if(rs.next()){
				prefixCount=rs.getInt(1);
			}
			if(prefixCount==1){
				pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_indent"));
				pstmt.setInt(1, ui.getCompanyId());
				pstmt.setInt(2, ui.getLocationId());
				pstmt.setInt(3, ui.getYearId());
				rs = pstmt.executeQuery();
				if(rs.next()){
					header.setIndentPrefix(rs.getString(1));
					header.setIndentNo(rs.getInt(2));
				}
			}

			List<String> autoPrefixList=new ArrayList<String>();

			pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_indent"));
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
			pstmt=con.prepareStatement(SQLUtil.GET_AUTO_NO_TYPE.replace("?autno_table","set_autono_indent"));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs=pstmt.executeQuery();
			if(rs.next()){
				checkType=rs.getString(1);
			}
			header.setCheckAutoNoType(checkType);
			checkType=null;
			
			//Terms & conditions
			StringBuffer tcBuf=new StringBuffer();
			IndentHeader sqtc=null;
			List<IndentHeader> tcList=new ArrayList<IndentHeader>();

			int idx=1;

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
			
			objIND.setHeader(header);
			
			
			
			header=null;

		}finally{     
			DatabaseConnection.closeAll(pstmt,rs);
			DatabaseConnection.closeAll(cstmt,rs1);
		}
		return objIND;
	}
	
	
	
	
	
	
	
	public Indent getAllIndentOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,Indent objIND)throws SQLException,Exception{
		CallableStatement cstmt=null;
		ResultSet rs = null; 
		List<Map<String,String>> assortList=new ArrayList<Map<String,String>>();
		Map<String,String> map=null;
		PreparedStatement pstmtGetINDDetailInfoDynamic =null;
		ResultSet rsGetINDDetailInfoDynamic=null;
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
			
			
			
			List<DynamicFields> dynamicFieldsListINDHeader = dynamicFieldsDAO.getDynamicFields(con,objIND.getScreenId(), objIND.getHeaderTableId());
			List<DynamicFields> dynamicFieldsListINDDetail = dynamicFieldsDAO.getDynamicFields(con,objIND.getScreenId(), objIND.getDetailTableId());
			
			
			
			Map<String,String> colPreMap=new CommonUtilDAO().getColPreMap(con,objIND.getSubdocumentId());
			StringBuffer objBuffer = new StringBuffer(SQLUtil.INDENT_LIST);
			objBuffer.append(" where ind.company_id="+userInfo.getCompanyId()+" and ind.location_id="+userInfo.getLocationId()+" and ind.year_id="+userInfo.getYearId()+" ");
			if(objIND.getSearchCriteria()!=null || !objIND.getSearchCriteria().isEmpty()){
				objBuffer.append(objIND.getSearchCriteria());
			}
	         objBuffer.append(" order by convert(int,ind.indent_no) desc,ind.prefix " );
		
	        // System.out.println("objBuffer.toString() :"+objBuffer.toString());
	         
			cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
			cstmt.setString(1,objBuffer.toString());
			cstmt.setInt(2,pc.iStart);
			cstmt.setInt(3,pc.iEnd);
			rs=cstmt.executeQuery();
			while(rs.next()){  

				map = new HashMap<String, String>();

				map.put("Indent.IndentId", rs.getString("indent_id"));
				
				map.put("Indent.IndentDetId", rs.getString("indent_det_id"));
				map.put("Indent.IndentNo", rs.getString("prefix"));
//				map.put("Indent.IndentNo", rs.getString("prefix")+rs.getString("indent_no"));
				map.put(colPreMap.get("indent_date"), rs.getString("indent_date"));
				map.put(colPreMap.get("indent_type"),rs.getString("indent_type"));
				map.put(colPreMap.get("department"),rs.getString("department"));
				map.put(colPreMap.get("remarks"),rs.getString("remarks"));
				map.put(colPreMap.get("other_ref"),rs.getString("other_ref"));
				
				map.put(colPreMap.get("indent_line_no"), rs.getString("indent_line_no"));
				map.put(colPreMap.get("item_Id"), rs.getString("item_name"));
				map.put(colPreMap.get("group_id"), rs.getString("group_name"));
				map.put(colPreMap.get("variant_id"), rs.getString("variant_name"));
				map.put(colPreMap.get("size_range"), rs.getString("size_range"));
				map.put(colPreMap.get("indent_qty"), rs.getString("indent_qty"));
				map.put(colPreMap.get("uom"), rs.getString("uom"));
				map.put(colPreMap.get("party_id"), rs.getString("buyer_name"));
				map.put(colPreMap.get("required_date"), rs.getString("required_date"));
				map.put(colPreMap.get("remark"),rs.getString("remark"));
				map.put("Indent.CancelTag", rs.getString("cancel_tag"));
				map.put("Indent.CloseTag", rs.getString("close_tag"));
			
 				pstmt = con.prepareStatement(SQLUtil.SELECT_INDENT_ATTACHMENT);
				pstmt.setInt(1, Validator.convertToInteger(rs.getString("indent_id")));
				rsAttach=pstmt.executeQuery();

				StringBuffer fileNames=new StringBuffer();
				while(rsAttach.next()){
					if(imageTypeList.contains(rsAttach.getString(2).toLowerCase())){
						fileNames.append("<div class=\"grid-image\" ><a target=\"_blank\" href=\""+objIND.getAttachPath().concat(rsAttach.getString(3))+"\"  title=\"view\"><img src=\""+objIND.getAttachPath().concat(rsAttach.getString(3))+"\" width=\"30\"></a></div>");
					}
					else{
						fileNames.append("<a target=\"_blank\" href=\""+objIND.getAttachPath().concat(rsAttach.getString(3))+"\"  title=\"view\">"+rsAttach.getString(3)+"</a><br/>");
					}
				}
				map.put(colPreMap.get("IND.Attachment"), fileNames.toString());
				fileNames=null;

				
				//Start - Added for dynamic fields
				
				
				
				pstmtGetINDDetailInfoDynamic = con.prepareStatement(SQLUtil.SELECT_INDENT_DETAIL_DYNAMIC_BY_ID);
				
				pstmtGetINDDetailInfoDynamic.setInt(1, Validator.convertToInteger(rs.getString("indent_id")));  
				pstmtGetINDDetailInfoDynamic.setInt(2, Validator.convertToInteger(rs.getString("indent_det_id"))); 
				rsGetINDDetailInfoDynamic = pstmtGetINDDetailInfoDynamic.executeQuery();
				if(rsGetINDDetailInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListINDHeader) {
						
						
						if(!dynamicFields.isFixed()){
							String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							String fieldValue = rsGetINDDetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetINDDetailInfoDynamic.getString(dynamicFields.getDbFieldName());
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
					
					for(DynamicFields dynamicFields : dynamicFieldsListINDDetail) {
						if(!dynamicFields.isFixed()){
							String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							String fieldValue = rsGetINDDetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetINDDetailInfoDynamic.getString(dynamicFields.getDbFieldName());

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

			objIND.setIndentMapList(assortList);
			
			imageTypeList=null;
			dynamicFieldsDAO=null;
			dynamicFieldsListINDHeader=null;
			dynamicFieldsListINDDetail=null;
			colPreMap=null;

			objBuffer=null;
		}
		finally{

			DatabaseConnection.closeAll(cstmt,rs);
		}
		return objIND;
	}
	
	
	
	
	public int getTotalPages(Connection con,TPCSUser userInfo,PageConfig pc,String strSearhQuery) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pageCount = 0;
		try {

			StringBuffer query = new StringBuffer(SQLUtil.INDENT_COUNT);
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
	
	
	
	
	
	
	public Indent deleteIndentDetail(Connection con, TPCSUser userInfo,Indent objIND) throws SQLException {
		CallableStatement cstmt = null;
		ResultSet rs = null; 
		boolean deletedCount=false;
		int del;
		try{ 
			
			objIND.setDeletedCount(0);
			cstmt = con.prepareCall("{call pr_delete_indent_detail(?,?,?,?,?)}");
			cstmt.setInt(1, userInfo.getCompanyId());
			cstmt.setInt(2, userInfo.getLocationId());
			cstmt.setInt(3, userInfo.getYearId());
			cstmt.setInt(4, objIND.getHeader().getIndentId());
			cstmt.setInt(5, objIND.getDetail().getIndentDetId());
			rs=cstmt.executeQuery();
			if(rs.next()){
				objIND.setDeletedCount(rs.getInt(1));
			}
			
		} finally {
			DatabaseConnection.closeAll(cstmt,rs);
		}
		return objIND;
	}
	
	public Indent deleteIndent(Connection con,TPCSUser userInfo,Indent objIND) throws SQLException {
		CallableStatement cstmt = null;
		try { 
			
			objIND.setDeletedCount(0);
			cstmt = con.prepareCall("{call pr_delete_indent(?,?,?,?)}");
			cstmt.setInt(1, userInfo.getCompanyId());
			cstmt.setInt(2, userInfo.getLocationId());
			cstmt.setInt(3, userInfo.getYearId());
			cstmt.setInt(4, objIND.getHeader().getIndentId());
			int deletedCnt=cstmt.executeUpdate();
			objIND.setDeletedCount(deletedCnt);
			objIND.setDeleted(true);
			
//			System.out.println("deletedCnt=[== " +deletedCnt );
			
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return objIND;
	}
	
	public Indent cancelIndent(Connection con,TPCSUser userInfo,Indent objIND) throws SQLException {
		CallableStatement cstmt = null;
		try { 
			
//			objIND.setCancelledCount(0);
			
			cstmt = con.prepareCall("{call pr_cancel_indent(?,?,?,?,?)}");
			cstmt.setString(1, userInfo.getDisplayUserName());
			cstmt.setInt(2, userInfo.getCompanyId());
			cstmt.setInt(3, userInfo.getLocationId());
			cstmt.setInt(4, userInfo.getYearId());
			cstmt.setInt(5, objIND.getHeader().getIndentId());
			int cancelledCnt=cstmt.executeUpdate();
			objIND.setCancelled(cancelledCnt>0?true:false);
			
//			System.out.println("cancelledCnt==== " +cancelledCnt);
//			System.out.println("isCancelled==== " +objIND.isCancelled());

		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return objIND;
	}
	

	public Indent closeIndent(Connection con,TPCSUser userInfo,Indent objIND) throws SQLException {
		CallableStatement cstmt = null;
		try { 
			
		//	objIND.setClosedCount(0);
			cstmt = con.prepareCall("{call pr_close_indent(?,?,?,?,?)}");
			cstmt.setString(1, userInfo.getDisplayUserName());
			cstmt.setInt(2, userInfo.getCompanyId());
			cstmt.setInt(3, userInfo.getLocationId());
			cstmt.setInt(4, userInfo.getYearId());
			cstmt.setInt(5, objIND.getHeader().getIndentId());
			int closedCnt=cstmt.executeUpdate();
			objIND.setClosed(closedCnt>0?true:false);
			
//			System.out.println("closedCnt==== " +closedCnt);
//			System.out.println("isClosed==== " +objIND.isClosed());
			
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return objIND;
	}
	
	
	public Indent getINDSizeRangeSizeGrid(Connection con,TPCSUser ui, Indent objIND) throws SQLException{
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			
			
			boolean isSizeExists=false;
			boolean indSizeExists=false;
			String totalSizeQty="0";
			StringBuffer buffer =new StringBuffer();
			buffer.append("select isnull(size_applicable,0) size_applicable"
					+ " from item it with(nolock)"
					+ " inner join Group_Details gd with(nolock) on it.group_id=gd.group_id"
					+ " where it.item_id=? ");
			pst=con.prepareStatement(buffer.toString());
			pst.setInt(1, objIND.getDetail().getItemId());
			rs=pst.executeQuery();
			
			if(rs.next()){
				isSizeExists=rs.getInt(1)==1?true:false;
			}
			
//			System.out.println("isSizeExists=== " +isSizeExists);
			
			objIND.getDetail().setSizeExists(isSizeExists);
			
			buffer=null;
			buffer=new StringBuffer();
			
			if(objIND.getDetail().isSizeExists()){
				
				List<Integer> sizeIdList=new ArrayList<Integer>();
			
				List<Size> sizeList=new ArrayList<Size>();
			
			if(objIND.getDetail().getIndentDetId()>0){
			 
				pst=null;
				pst=con.prepareStatement("select count(*) from indent_size where indent_id=? and indent_det_id=?");
				pst.setInt(1, objIND.getHeader().getIndentId());
				pst.setInt(2, objIND.getDetail().getIndentDetId());
				rs=pst.executeQuery();
				if(rs.next()){
					indSizeExists=rs.getInt(1)>0?true:false;
				}
				pst=null;
				
					pst=con.prepareStatement("select isnull(sum(inds.qty),0) qty"
							+ " from size_master sm with(nolock) "
							+ " inner join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
							+ " left join indent_size inds with(nolock) on inds.size_id=srd.size_id and inds.indent_Id=? and inds.indent_Det_Id=? "
							+ " where srd.size_range_id=?");
					pst.setInt(1, objIND.getHeader().getIndentId());
					pst.setInt(2, objIND.getDetail().getIndentDetId());
					pst.setInt(3, objIND.getDetail().getSizeRangeId());
					
					rs=pst.executeQuery();
					if(rs.next()){
						totalSizeQty=rs.getString(1);
					}
					
					
					buffer.append("select sm.size_id,sm.size_name,isnull(inds.qty,0) qty,isnull(srd.sl_no,0) sl_no "
							+ " from size_master sm with(nolock) "
							+ " inner join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
							+ " left join indent_size inds with(nolock) on inds.size_id=srd.size_id and inds.indent_Id=? and inds.indent_Det_Id=? "
							+ " where srd.size_range_id=?"
							+ " order by srd.sl_no");
					pst=null;
					pst=con.prepareStatement(buffer.toString());
					pst.setInt(1, objIND.getHeader().getIndentId());
					pst.setInt(2, objIND.getDetail().getIndentDetId());
					pst.setInt(3, objIND.getDetail().getSizeRangeId());
					
//					System.out.println("buffer :"+buffer.toString());
			
			}
			
			else{
				buffer.append("select sm.size_id,sm.size_name,0.0 qty,isnull(srd.sl_no,0) sl_no "
						+ " from size_master sm with(nolock) "
						+ " left join size_range_det srd with(nolock) on sm.size_id=srd.size_id "
						+ " where srd.size_range_id=?"
						+ " order by srd.sl_no");
				pst=null;
				pst=con.prepareStatement(buffer.toString());
				pst.setInt(1, objIND.getDetail().getSizeRangeId());	
			}
			
//			System.out.println("objIND.getHeader().getIndentId() :"+objIND.getHeader().getIndentId());
//			System.out.println("objIND.getDetail().getIndentDetId() :"+objIND.getDetail().getIndentDetId());
//			System.out.println("objIND.getDetail().getSizeRangeId() :"+objIND.getDetail().getSizeRangeId());
			
			rs=pst.executeQuery();
			
			while(rs.next()){
				Size size=new Size();
				size.setSizeId(rs.getInt(1));
				size.setSizeName(rs.getString(2));
				size.setQty(rs.getString(3));
//				size.setRate(rs.getString(4));
				size.setSlNo(rs.getInt(4));
				sizeList.add(size);
				sizeIdList.add(size.getSizeId());
				size=null;
			}
			
			objIND.getDetail().setIndsizeExists(indSizeExists);
			objIND.getDetail().setSizeTotalQty(totalSizeQty);
			objIND.getDetail().setSizeList(sizeList);
			objIND.getDetail().setSizeIdList(sizeIdList);
			
			sizeList=null;
			sizeIdList=null;
			
			buffer=null;
			}
			
		}
		finally{     
			DatabaseConnection.closeAll(pst,rs);
		}
		
		return objIND;
	}
	
	
	public Indent getIndentPrintHeader(Connection con, TPCSUser userInfo,int indentId,int iScreenId) throws SQLException, Exception {
		Indent objINDH=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;
		PreparedStatement pstmtGetInfoDynamic = null;

		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListIndentHeader = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 8);

		try{ 

			StringBuffer query = new StringBuffer(SQLUtil.GET_INDENT_PRINT_HEADER);
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,userInfo.getCompanyId());
			pstmt.setInt(2,userInfo.getYearId());
			pstmt.setInt(3,userInfo.getLocationId());
			pstmt.setInt(4,indentId);
			rs=pstmt.executeQuery(); 

			
			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.INDENT_HEADER_INFO_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,indentId);
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();
			if(rs.next())
			{
				objINDH=new Indent();
				IndentHeader header=new IndentHeader();
				header.setIndentId(rs.getInt(1));
				header.setIndentNo(rs.getInt(2));
				header.setIndentDate(rs.getString(3));
				header.setIndentType(rs.getString(4));
				header.setDept(rs.getString(5));
				header.setRemarks(rs.getString(6));
				header.setOtherRef(rs.getString(7));


				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListIndentHeader) {
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

				objINDH.setHeader(header);
				header=null;

			} 
			objINDH.setIndentDynList(dynamicFieldsListIndentHeader);

			
			dynamicFieldsDAO=null;
			
			query=null;
			dynamicFieldsListIndentHeader=null;
			
			// TODO Auto-generated method stub
		} finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return objINDH;


	}
	
	public String doGetItemData(TPCSUser userInfo,Connection con,int matId,String trTag)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer buffer=new StringBuffer();
		try{      

			int szeApp=0;
			pstmt=con.prepareStatement(" SELECT isnull(gd.size_applicable,0) size_applicable from groups g "+
					" INNER JOIN group_details gd on g.group_id=gd.Group_Id "+
					" INNER JOIN Item it ON it.Group_Id=g.group_id "+
					" WHERE it.item_id="+matId); 
			
			System.out.println(" SELECT isnull(gd.size_applicable,0) size_applicable from groups g "+
					" INNER JOIN group_details gd on g.group_id=gd.Group_Id "+
					" INNER JOIN Item it ON it.Group_Id=g.group_id "+
					" WHERE it.item_id="+matId);
			rs=pstmt.executeQuery();

			if(rs.next()){
				szeApp=rs.getInt(1);
			}
			
			buffer.append("<item_datas>");
			buffer.append("<item_data>");
			buffer.append("<szeApp>");
			buffer.append(szeApp);
			buffer.append("</szeApp>");
			buffer.append("</item_data>");
			buffer.append("</item_datas>");
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

	public List<IndentDetail> getAllIndentDetailsForPrint(Connection con,TPCSUser userInfo, int indentId,String materialAttachPath) throws SQLException, Exception {
		// TODO Auto-generated method stub
		List<IndentDetail> detList=new ArrayList<IndentDetail>();		
		CallableStatement cstmt=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ResultSet rs2=null;

		try{

			cstmt=con.prepareCall(" select inde.item_id,it.item_name,inde.variant_id,isnull(vm.variant_name,'') as variant_name,isnull(inde.indent_qty,0) as Indent_Qty,"
								  + " inde.uom as UOM,isnull(inde.party_id,0) as Buyer_Id,isnull(p.party_name,0) as Buyer_Name "
                                  + " from indent_details inde "       
                                  + " inner join party p with(nolock) on inde.party_id=p.party_id "
                                  + " inner join item it with(nolock) on inde.item_id=it.item_id "
                                  + " left join variant_master vm with(nolock) on inde.variant_id=vm.variant_id "
                                  + " where inde.indent_id=?  order by inde.indent_det_id") ;
			
			
			cstmt.setInt(1,indentId);
			rs=cstmt.executeQuery();
			
			int slNo=0;
			while(rs.next())
			{
				IndentDetail det=new IndentDetail();
				det.setSlNo(slNo+1);
			
				det.setItemId(rs.getInt(1));
				det.setItemName(rs.getString(2));
				det.setColourId(rs.getInt(3));
				det.setColourName(rs.getString(4));
				det.setIndentQty(ExportToPdfTool.qtyDf.format(rs.getDouble(5)));
				det.setUom(rs.getString(6));
				det.setPartyId(rs.getString(7));
				det.setPartyName(rs.getString(8));
				
				/*pst = con.prepareStatement(SQLUtil.SELECT_MATERIAL_PRIMARY_IMAGE);
		        pst.setInt(1,  rs.getInt(1));
		        rs2=pst.executeQuery();
		        if(rs2.next()){
		        	det.setMaterialImage("<img src=\""+materialAttachPath.concat(rs2.getString(3))+"\" class=\"mat-image\" />");	
		        }*/


				detList.add(det);
				det=null;
			} 


			// TODO Auto-generated method stub
		} 
		finally {
			DatabaseConnection.closeAll(cstmt, rs);
			DatabaseConnection.closeAll(pst, rs2);
		}
		return detList; 
	}
	public List<Indent> getDynamicFields(Connection con,String tableIds,int fixed)throws SQLException,Exception{
	    PreparedStatement ArrayOfPreparedStatement[] = null;
	    ResultSet ArrayOfResultSet[] = null;
	    PreparedStatement pstmtGetStatus=null;
	    ResultSet rsGetStatus = null;  
	    StringBuffer objBuffer = new StringBuffer();
	    List<Indent> listStatus=new ArrayList<Indent>();
	    try{ 
	    	if(fixed==1){
	    	 	objBuffer.append(SQLUtil.GET_DYNAMICFIELDS);
 		    	objBuffer.append("("+tableIds+") AND df.fixed="+fixed+" AND dr.Active=1  ORDER BY df.tableId ,dr.SortOrder");
	    	}
	    	else{
	    		objBuffer.append(SQLUtil.GET_DYNAMICFIELDS);
	    		objBuffer.append("("+tableIds+") AND df.fixed="+fixed+" AND dr.Active=1  ORDER BY df.tableId,dr.SortOrder");
	    	}
	      pstmtGetStatus = con.prepareStatement(objBuffer.toString());      
	      rsGetStatus = pstmtGetStatus.executeQuery();
	      while(rsGetStatus.next()){
	    	 Indent objSO = new Indent();
	    	objSO.setId(rsGetStatus.getInt(1));
	    	objSO.setName(rsGetStatus.getString(2));
	    	objSO.setPageFieldName(rsGetStatus.getString(3));
	        listStatus.add(objSO);
	       objSO=null;
	      }
	      
	     objBuffer=null;
	   
	    }finally{      
	      ArrayOfPreparedStatement = new PreparedStatement[1];
	      ArrayOfPreparedStatement[0] =  pstmtGetStatus;
	      ArrayOfResultSet = new ResultSet[1];      
	      ArrayOfResultSet[0] = rsGetStatus;
	      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
	    }
	    return listStatus;
	  }
	
	
}
