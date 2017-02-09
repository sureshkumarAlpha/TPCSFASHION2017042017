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

import com.alpha.tpcsfashion.beans.DynamicFields;
import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.MailInfo;
import com.alpha.tpcsfashion.beans.MaterialMaster;
import com.alpha.tpcsfashion.beans.SalesEnquiry;
import com.alpha.tpcsfashion.beans.SalesEnquiryDetail;
import com.alpha.tpcsfashion.beans.SalesEnquiryHeader;
import com.alpha.tpcsfashion.beans.SalesInvoice;
import com.alpha.tpcsfashion.beans.SalesInvoiceHeader;
import com.alpha.tpcsfashion.beans.SalesOrderDetail;
import com.alpha.tpcsfashion.beans.SalesOrderPage;
import com.alpha.tpcsfashion.beans.SalesQuotation;
import com.alpha.tpcsfashion.beans.SalesQuotationDetail;
import com.alpha.tpcsfashion.beans.SalesQuotationHead;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;
import com.alpha.tpcsfashion.mail.SendMail;
import com.alpha.tpcsfashion.util.SQLUtil;
import com.alpha.tpcsfashion.util.Validator;

public class SalesQuotationDAO {
	public int saveSalesQuotation(Connection con, TPCSUser userInfo,SalesQuotationHead ltrIss,
			Map<String,String> map,Map<String,String> mapDyn,String editedMode,FileImport objBean) throws Exception {
		// TODO Auto-generated method stub
		CallableStatement cstmt = null;
		ResultSet rs=null;
		Statement stmt = null;
		PreparedStatement pst = null;
		PreparedStatement pstmtAttach=null;  
		int tr_id = 0; 

		try {

			SalesQuotation header = ltrIss.getHeader();
			SalesQuotationDetail detail = ltrIss.getDetail();

			float exchangeRage=header.getExchangeRate();
			
			//System.out.println("editedMode----->"+editedMode);

			//if(!editedMode.equals("e")){
				cstmt = con.prepareCall("{? = call pr_insert_salesQuotation(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				SQLXML sqlxml = con.createSQLXML();
				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.setString(2,userInfo.getDisplayUserName());
				cstmt.setInt(3, userInfo.getCompanyId());
				cstmt.setInt(4, userInfo.getLocationId());
				cstmt.setInt(5, userInfo.getYearId());
				cstmt.setString(6, ltrIss.getHeaderMode());
				cstmt.setString(7, ltrIss.getDetMode()); 
				cstmt.setInt(8, header.getQuoteId());
				cstmt.setInt(9, header.getQuoteNo());
				if(header.getTrDate()!=null && !header.getTrDate().isEmpty())
				{
					cstmt.setString(10, header.getTrDate());
				}
				else
				{
					cstmt.setNull(10, Types.DATE);
					}
				cstmt.setInt(11, header.getPartyId());
				cstmt.setString(12, header.getReferenceNo());
				cstmt.setInt(13, header.getExecutiveId());
				cstmt.setInt(14, header.getEnqId());
				cstmt.setInt(15, header.getCurrencyId());
				cstmt.setFloat(16, header.getExchangeRate());
				cstmt.setFloat(17, detail.getQuoteDetId());
				cstmt.setInt(18, detail.getMatId());
				cstmt.setString(19, detail.getUom());
				cstmt.setString(20, detail.getQty());
				cstmt.setString(21, detail.getPriceFcy());
				if(detail.getRequiredDate()==null || detail.getRequiredDate().isEmpty()){
					cstmt.setString(22, null);
				}
				else{
					cstmt.setString(22, detail.getRequiredDate());
				}
				cstmt.setString(23, detail.getDiscountPercent());
				cstmt.setString(24,detail.getRemarks());
				cstmt.setString(25,header.gethRemarks());
				cstmt.setString(26,header.getTrType());
				cstmt.setInt(27,detail.getVariantId());
				cstmt.setFloat(28, detail.getEdPercentage());
				cstmt.setFloat(29, detail.getEcessPercentage());
				cstmt.setFloat(30, detail.getShecessPercentage());
				cstmt.setFloat(31, detail.getVat());
				cstmt.setFloat(32, detail.getVat());
				cstmt.setFloat(33, detail.getService());
				cstmt.setString(34, header.getInternalMemo());
				cstmt.setString(35, header.getEmailSentTo());
				cstmt.setString(36, header.getBillToAddress());
				cstmt.setString(37, header.getShipToAddress());
				cstmt.setInt(38, detail.getTaxGroupId());
				
				cstmt.setFloat(39, header.getChargesFCY1());
				cstmt.setFloat(40, header.getChargesFCY2());
				cstmt.setFloat(41, header.getChargesFCY3());
				cstmt.setInt(42, header.getChargeId1());
				cstmt.setInt(43, header.getChargeId2());
				cstmt.setInt(44, header.getChargeId3());
//				cstmt.setInt(45, header.getRadioType());
				cstmt.setString(45, header.getPrefix());
				cstmt.setString(46, header.getPrefixQuoteNo());
//				cstmt.setInt(47, header.getLastNo());
				cstmt.setString(47, detail.getMaterialDesc());
				cstmt.setString(48, header.getPaymentTerms());
				cstmt.setInt(49, header.getCreditDays());
				sqlxml.setString(ltrIss.getSqlxmlDynamicFields());
				cstmt.setSQLXML("XmlData",sqlxml);

				cstmt.executeUpdate();
				tr_id = cstmt.getInt(1);
				
				if(editedMode.equals("e")){
				stmt = con.createStatement();
				List<Integer> detaIdList=new ArrayList<Integer>();
				Iterator it = map.entrySet().iterator();
				Iterator itDyn = mapDyn.entrySet().iterator();

				while(it.hasNext()){
					Map.Entry mCol= (Map.Entry)it.next();
					String strArray[] = ((String) mCol.getKey()).split("_S_");	
					if(strArray.length>1){
						if(!strArray[0].equals("required_date")){
							stmt.addBatch("UPDATE sales_quotation_details SET "+strArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE quote_det_id ="+strArray[1]);  
						}
						else{
							stmt.addBatch("UPDATE sales_quotation_details SET "+strArray[0]+"=convert(datetime,'"+mCol.getValue()+"',105)   WHERE quote_det_id ="+strArray[1]);
						}

						detaIdList.add(Integer.parseInt(strArray[1]));
					}
					tr_id=header.getQuoteId();
				}

				while(itDyn.hasNext()){
					Map.Entry mCol= (Map.Entry)itDyn.next();
					String strDynArray[] = ((String) mCol.getKey()).split("_S_");


					if(strDynArray.length>1){
						//  if(!strDynArray[0].equals("required_date")){
						stmt.addBatch("UPDATE Sales_Quotation_Details_Dynamic SET "+strDynArray[0]+"='"+mCol.getValue().toString().replace("'","''")+"'   WHERE quote_det_id ="+strDynArray[1]);  
						// }
						/*  else{
					    		  stmt.addBatch("UPDATE sales_quotation_details SET "+strArray[0]+"=convert(datetime,'"+mCol.getValue()+"',105)   WHERE quote_det_id ="+strArray[1]);
					    	  }
						 */
					}
					tr_id=header.getQuoteId();
				}


				stmt.executeBatch();

			}
				
				
				if(ltrIss.getHeaderMode().equals("e")){
					pst=con.prepareStatement(" update sales_quotation_details set discount_percent=? where quote_id=?");
					pst.setString(1, detail.getDiscountPercent());
					pst.setInt(2, tr_id);
					pst.executeUpdate();			
	 				
					
				}
				
				Map<String,String> locConfigMap=userInfo.getLocatonConfigMap();
				int qtyRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffQty"));
				int rateRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffRate"));
				int valRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffValue"));
				
				cstmt=con.prepareCall("{ call pr_calculate_tax_for_det (?,?,?,?,?)}");
				cstmt.setInt(1, tr_id);
				cstmt.setString(2, "SQ");
				cstmt.setInt(3,qtyRoundOff);
				cstmt.setInt(4,rateRoundOff);
				cstmt.setInt(5,valRoundOff);
				cstmt.executeUpdate();
				
			//For Attachments
			pstmtAttach = con.prepareStatement(SQLUtil.INSERT_SALES_QUOTATION_ATTACHMENT);
			pstmtAttach.setInt(1,userInfo.getCompanyId());
			pstmtAttach.setInt(2,userInfo.getYearId());
			pstmtAttach.setInt(3,userInfo.getLocationId());
			if(!editedMode.equals("e")){
				pstmtAttach.setInt(4,tr_id);
			}else{
				pstmtAttach.setInt(4,header.getQuoteId());		
			}



			List list= objBean.getFileItems();
			String parentFolder="quotation/";
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
					System.out.println("fieldName---->"+fieldName);
					String source = item.getName();
					System.out.println("source ---->"+source );
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
							strPrefix=fileActualName+"_"+header.getQuoteId()+"_"+idx+".";
						}
	
						file=new File(folderPath+strPrefix+strSuffix);
						item.write(file);
						idx++;
//						String [] arFolder=folderPath.split("/");
//						String parentFolder=arFolder[arFolder.length-1]+"/";
						pstmtAttach.setString(5, strSuffix);
	
						/*System.out.println("parentFolder+strPrefix+strSuffix--->"+parentFolder+strPrefix+strSuffix);*/
						pstmtAttach.setString(6, parentFolder+strPrefix+strSuffix);
	
						pstmtAttach.addBatch();
					}
				}
			}
			pstmtAttach.executeBatch();


		} finally {
			DatabaseConnection.closeAll(cstmt, null);
		}
		return tr_id;

	}

	
	public boolean insertUser(Connection con,String[] userid,String[] userremarks,int trId,TPCSUser ui)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtInsertSQUser=null;   
		int insertuserid=0;
		String insertuserremarks=null;
		boolean bFlag = false; 
		try{ 


			pstmtInsertSQUser = con.prepareStatement(SQLUtil.INSERT_SQ_USER);
			pstmtInsertSQUser.setInt(2,trId);    
			pstmtInsertSQUser.setInt(3,ui.getCompanyId());  
			pstmtInsertSQUser.setInt(4,ui.getYearId());
			pstmtInsertSQUser.setInt(5,ui.getLocationId());
			pstmtInsertSQUser.setInt(6,trId);    
			for(int k=0;k<userid.length;k++)
			{
				insertuserid=Integer.parseInt(userid[k]);
				
				pstmtInsertSQUser.setInt(1, insertuserid);
				pstmtInsertSQUser.setInt(7, insertuserid);
				pstmtInsertSQUser.addBatch();
			}
			int[] iCount =pstmtInsertSQUser.executeBatch();
			bFlag=(iCount.length == userid.length)?true:false;
		}finally{     
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtInsertSQUser;
			ArrayOfResultSet = new ResultSet[0];
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return bFlag;
	}
	
	/*public void insertDefaultFollowers(Connection con,int iUserId,String[] userid,int trId,TPCSUser ui)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmtDeleteExistingStyleDefUser=null;   
		PreparedStatement pstmtInsertSQUser=null;
		int insertuserid=0;

		try{ 
			pstmtInsertSQUser = con.prepareStatement(SQLUtil.INSERT_DEF_SALES_QUOTATION_USER);
			pstmtInsertSQUser.setInt(1,ui.getCompanyId());  
			pstmtInsertSQUser.setInt(2,ui.getYearId());
			pstmtInsertSQUser.setInt(3,ui.getLocationId());
			pstmtInsertSQUser.setInt(4,trId);
			for(int k=0;k<userid.length;k++)
			{
				insertuserid=Integer.parseInt(userid[k]);

				pstmtInsertSQUser.setInt(5, insertuserid);

				pstmtInsertSQUser.addBatch();
			}
			int[] iCount =pstmtInsertSQUser.executeBatch();
		}finally{     
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtDeleteExistingStyleDefUser;
			ArrayOfResultSet = new ResultSet[0];
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}

	}*/
	 
	 
	public void sendMail(Connection con,List<String> sqDet,int loginId,List<String> toIds,String loginName,String strContactImagePath,String strContactImage,String clientId,int type)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		CallableStatement csInsertUser=null;    
		PreparedStatement pstmtGetServer=null;
		ResultSet rsGetServer = null; 
		PreparedStatement pstmtGetUserName=null;
		ResultSet rsGetUserName = null; 

		try{ 		

			String[] strSQDet=sqDet.toArray(new String[0]);
			String subject="";
			String messageBody="";
			if(type==1)
			{
				subject="New Sales Quotation Created";
				messageBody ="<html><body><table><tr><td><img src=\"cid:the-img-1\" height=\"50px\" width=\"50px\"/></td><td><font color=\"#193678\"><b>"+loginName+"</b></font><b> Created New Sales Quotation</b><br/><b> Quotation No:</b>"+strSQDet[1].toString()+"<br/><b> Customer:</b>"+strSQDet[2].toString()+"<br/><b> Product:</b>"+strSQDet[3].toString()+"</td><tr></table></body></html>";
			}
			else if(type==2)
			{
				subject="Sales Quotation Updated";
				//messageBody=loginName+" Added Sample Order Details "+objOrder.getOrderNo()+"\n Customer:"+objOrder.getCustomerName()+"\n Factory:"+objOrder.getFactoryName()+"\n Style:"+objOrder.getOrderDetail().getStyleNo();
				messageBody ="<html><body><table><tr><td><img src=\"cid:the-img-1\" height=\"50px\" width=\"50px\"/></td><td><font color=\"#193678\"><b>"+loginName+"</b></font><b> Updated Sales Quotation</b><br/><b> Quotation No:</b>"+strSQDet[1].toString()+"<br/><b> Customer:</b>"+strSQDet[2].toString()+"<br/><b> Product:</b>"+strSQDet[3].toString()+"</td><tr></table></body></html>";
			}
			String[] attachments=null;     	
			SendMail objSendMail=new SendMail();      

			try
			{
				if(strContactImage.length()==0){
					strContactImagePath=strContactImagePath+"/images/default.JPG";
				}
				else{
					strContactImagePath=strContactImagePath+"/images/uploadimages/"+clientId+"/contacts/"+strContactImage;
				}
				pstmtGetUserName = con.prepareStatement(SQLUtil.GET_CLIENT_SERVER);
				pstmtGetUserName.setInt(1, loginId);
				rsGetUserName = pstmtGetUserName.executeQuery();
				MailInfo mailInfo=new MailInfo();
				if(rsGetUserName.next()){
					System.out.println("toIds --->:"+toIds);
					System.out.println("subject --->:"+subject);
					System.out.println("messageBody --->:"+messageBody);
					System.out.println("strContactImagePath --->:"+strContactImagePath);
					objSendMail.sendMail(rsGetUserName.getString(1),toIds,rsGetUserName.getString(2),rsGetUserName.getString(3),rsGetUserName.getString(4),subject,messageBody,strContactImagePath,mailInfo);
					// objSendMail.sendMail(rsGetUserName.getString(1),toIds,rsGetUserName.getString(2),rsGetUserName.getString(3),rsGetUserName.getString(4),subject,messageBody,"");
				}

			}
			catch(Exception e) 
			{ 
				e.printStackTrace(System.out); 
			} 



		}finally{     
			if(csInsertUser!=null)
				csInsertUser.close();
			ArrayOfPreparedStatement = new PreparedStatement[2];   
			ArrayOfPreparedStatement[0]=pstmtGetServer;
			ArrayOfPreparedStatement[1]=pstmtGetUserName;
			ArrayOfResultSet = new ResultSet[2];
			ArrayOfResultSet[0]=rsGetServer;
			ArrayOfResultSet[1]=rsGetUserName;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}

	}
	public List<String> getEmailSQDetails(Connection con,int iQuoteId)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;    
		PreparedStatement pstmtGetSQDetails=null;
		ResultSet rsGetSQDetails = null; 


		List<String> sq_Details=new ArrayList<String>();		     
		try{ 


			pstmtGetSQDetails = con.prepareStatement(SQLUtil.GET_SQDETAILS);
			pstmtGetSQDetails.setInt(1, iQuoteId);	 

			rsGetSQDetails = pstmtGetSQDetails.executeQuery();				       
			if(rsGetSQDetails.next());
			{
				sq_Details.add(rsGetSQDetails.getString(1)!=null?rsGetSQDetails.getString(1):"");
				sq_Details.add(rsGetSQDetails.getString(2)!=null?rsGetSQDetails.getString(2):"");
				sq_Details.add(rsGetSQDetails.getString(3)!=null?rsGetSQDetails.getString(3):"");
				sq_Details.add(rsGetSQDetails.getString(4)!=null?rsGetSQDetails.getString(4):"");
			}


		}
		finally{

			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0]=pstmtGetSQDetails;		      
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfResultSet[0]=rsGetSQDetails;

			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return sq_Details;
	}

     
	public List<String> getSQFollowers(Connection con,int iQuoteId)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;    
		ResultSet rsGetUsers = null;   
		PreparedStatement pstmtGetUsers = null;

		List<String> tpcs_users=new ArrayList<String>();		     
		try{ 

			pstmtGetUsers = con.prepareStatement(SQLUtil.GET_SQ_FOLLOWERS);
			pstmtGetUsers.setInt(1, iQuoteId);
			rsGetUsers = pstmtGetUsers.executeQuery();

			while(rsGetUsers.next()){

				tpcs_users.add(rsGetUsers.getString(1)); 
			}


		}finally{

			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtGetUsers;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rsGetUsers;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return tpcs_users;
	}

	public String getLoggedInUser(Connection con,int iUserId)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;    
		ResultSet rsGetUserName = null;   
		PreparedStatement pstmtGetUserName = null;

		String name=null;		     
		try{ 

			pstmtGetUserName = con.prepareStatement(SQLUtil.GET_USER_NAME);
			pstmtGetUserName.setInt(1, iUserId);
			rsGetUserName = pstmtGetUserName.executeQuery();

			while(rsGetUserName.next()){
				name=rsGetUserName.getString(2)+" "+rsGetUserName.getString(3); 
			}


		}finally{

			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtGetUserName;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rsGetUserName;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return name;
	}
     
	public String getContactImage(Connection con,int iUserId)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;    
		ResultSet rsGetContactImage = null;   
		PreparedStatement pstmtGetContactImage = null;
		int iCount=0;

		String name=null;		     
		try{ 

			pstmtGetContactImage = con.prepareStatement(SQLUtil.GET_CONTACT_IMAGE);
			pstmtGetContactImage.setInt(1, iUserId);
			rsGetContactImage = pstmtGetContactImage.executeQuery();

			while(rsGetContactImage.next()){
				iCount++;
				name=rsGetContactImage.getString(1); 
			}
			if(iCount==0)
			{

				name="";
			}


		}finally{

			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmtGetContactImage;
			ArrayOfResultSet = new ResultSet[1];      
			ArrayOfResultSet[0] = rsGetContactImage;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return name;
	}
/*	 public List<SalesQuotationHead> getSQDefUsers(Connection con,int iQuoteId,int iUserId,String strPath,String strClient)throws SQLException,Exception{
         PreparedStatement ArrayOfPreparedStatement[] = null;
           ResultSet ArrayOfResultSet[] = null;
           PreparedStatement pstmt=null;
           ResultSet rs= null;  
          
         List<SalesQuotationHead> SQUsers=new ArrayList<SalesQuotationHead>();
         try{      
        	 pstmt = con.prepareCall(SQLUtil.GET_SQ_USER);
        	 rs = pstmt.executeQuery();
           while(rs.next()){
        	   SalesQuotationHead objSQ = new SalesQuotationHead();
        	   objSQ.setUserId(rs.getInt(1));
        	   objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));            
                      
        	   SQUsers.add(objSQ);             
           }
         
         }finally{
            ArrayOfPreparedStatement = new PreparedStatement[1];
             ArrayOfPreparedStatement[0] = pstmt;
             ArrayOfResultSet = new ResultSet[1];
             ArrayOfResultSet[0] = rs;
             DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
         }
         return SQUsers;
       }*/
     
     
	public List<SalesQuotationHead> getSQDefUsers(Connection con,int iQuoteId,int iUserId,String strPath,String strClient)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;  
		int iCount=0;
		List<SalesQuotationHead> SQUsers=new ArrayList<SalesQuotationHead>();
		try{      

		//	System.out.println("iQuoteId---->"+iQuoteId);
		//	System.out.println("iUserId--->"+iUserId);
			if(iQuoteId>0){

				pstmt = con.prepareCall(SQLUtil.GET_NOT_SAVED_USER_SQ);
				pstmt.setInt(1, iQuoteId);
				//pstmt.setInt(2, iUserId);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SalesQuotationHead objSQ = new SalesQuotationHead();
					objSQ.setUserId(rs.getInt(1));
					objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));
					//objSQ.setUserRemarkDet(rsGetComponents.getString(4));
				
					String delFollower="<img id=\"del_"+iCount+1+"\" class=\"delete_follower_icon\" style=\"display:block;\" src=\""+strPath+"/images/close_icon.png\" title=\"Delete\" onclick=\"deleteFollower('sales_quotaion','flwr_"+iQuoteId+"_"+rs.getInt(1)+"','"+iQuoteId+"','"+rs.getInt(1)+"')\" />";
					objSQ.setContactImage(rs.getString(4)!=null?"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/uploadimages/"+strClient+"/contacts/"+rs.getString(4)+"\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>" :"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/default.JPG\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>");
					
					SQUsers.add(objSQ);
					iCount++;
					
					
				}

			}
			else{

				pstmt = con.prepareCall(SQLUtil.GET_ALL_USER);
				pstmt.setInt(1, iUserId);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SalesQuotationHead objSQ = new SalesQuotationHead();
					objSQ.setUserId(rs.getInt(1));
					objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));            

					SQUsers.add(objSQ);             
				}
			}

		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmt;
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return SQUsers;
	}
	
	
	public List<SalesQuotationHead> getSQFollowers(Connection con,int iQuoteId,int iUserId,String strPath,String strClient)throws SQLException,Exception{
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;  
		int iCount=0;
		List<SalesQuotationHead> SQUsers=new ArrayList<SalesQuotationHead>();
		try{      

		//	System.out.println("iQuoteId---->"+iQuoteId);
		//	System.out.println("iUserId--->"+iUserId);
			if(iQuoteId>0){

				pstmt = con.prepareCall(SQLUtil.GET_SQ_SAVED_FOLLOWERS);
				pstmt.setInt(1, iQuoteId);
				pstmt.setInt(2, iUserId);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SalesQuotationHead objSQ = new SalesQuotationHead();
					objSQ.setUserId(rs.getInt(1));
					objSQ.setUserFullName(rs.getString(2)+" "+rs.getString(3));
					objSQ.setQuoteId(iQuoteId);
					
					String url="images/default.JPG";
					if(rs.getString(4)!=null && !rs.getString(4).equals("null")){
						url="images/uploadimages/"+strClient+"/contacts/"+rs.getString(4);
					}
					objSQ.setUrl(url);
					objSQ.setLoginName(rs.getString(5));
					
					//objSQ.setUserRemarkDet(rsGetComponents.getString(4));
						
					//String delFollower="<img id=\"del_"+iCount+1+"\" class=\"delete_follower_icon\" style=\"display:block;\" src=\""+strPath+"/images/close_icon.png\" title=\"Delete\" onclick=\"deleteFollower('sales_quotaion','flwr_"+iQuoteId+"_"+rs.getInt(1)+"','"+iQuoteId+"','"+rs.getInt(1)+"')\" />";
					
					
					//System.out.println(rs.getString(4)!=null?"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/uploadimages/"+strClient+"/contacts/"+rs.getString(4)+"\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>" :"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/default.JPG\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>");
					//objSQ.setContactImage(rs.getString(4)!=null?"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/uploadimages/"+strClient+"/contacts/"+rs.getString(4)+"\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>" :"<td id=\"flwr_"+iQuoteId+"_"+rs.getInt(1)+"\" >"+delFollower+"<img src=\""+strPath+"/images/default.JPG\" width=\"50\" title=\""+rs.getString(5)+"\"  /></td>");
					
					SQUsers.add(objSQ);
				//	iCount++;
					
					
				}

			}
			
		}finally{
			ArrayOfPreparedStatement = new PreparedStatement[1];
			ArrayOfPreparedStatement[0] = pstmt;
			ArrayOfResultSet = new ResultSet[1];
			ArrayOfResultSet[0] = rs;
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return SQUsers;
	}
	
	 public SalesQuotation getSalesQuotationPrefixData(Connection con,TPCSUser ui,SalesQuotation sq)throws SQLException,Exception{
		    PreparedStatement pstmt=null;
		    ResultSet rs = null;   
		    CallableStatement cstmt = null;
		    ResultSet rs1 = null; 
		    try{      
		      
//		      pstmt = con.prepareStatement("select isnull(charge_name1,'Frieght / Transport Charges'),isnull(charge_name2,'Packing Charges'),isnull(charge_name3,'Other Charges') from sales_quotation where quote_id=(select max(quote_id) from sales_quotation where company_id=? and location_id=? and year_id=?)");
//		      pstmt.setInt(1, ui.getCompanyId());
//		      pstmt.setInt(2, ui.getLocationId());
//		      pstmt.setInt(3, ui.getYearId());
//		      rs = pstmt.executeQuery();
//		      if(rs.next()){
//		    	  sq.setChargeName1(rs.getString(1));
//		    	  sq.setChargeName2(rs.getString(2));
//		    	  sq.setChargeName3(rs.getString(3));
//		      }
//		      if(sq.getChargeName1()==null){
//		    	  sq.setChargeName1("Frieght / Transport Charges");
//		      }
//		      if(sq.getChargeName2()==null){
//		    	  sq.setChargeName2("Packing Charges");
//		      }
//		      if(sq.getChargeName3()==null){
//		    	  sq.setChargeName3("Other Charges");
//		      }
		      
		      int prefixCount=0;
		    	pstmt=con.prepareStatement(SQLUtil.GET_PREFIX_COUNT.replace("?autno_table","set_autono_sales_quotation"));
		    	pstmt.setInt(1, ui.getCompanyId());
		    	pstmt.setInt(2, ui.getLocationId());
		    	pstmt.setInt(3, ui.getYearId());
		    	rs= pstmt.executeQuery();
		    	if(rs.next()){
		    		prefixCount=rs.getInt(1);
				}
		      if(prefixCount==1){
		    	  pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_sales_quotation"));
			      pstmt.setInt(1, ui.getCompanyId());
			      pstmt.setInt(2, ui.getLocationId());
			      pstmt.setInt(3, ui.getYearId());
			      rs = pstmt.executeQuery();
			      if(rs.next()){
			    	  sq.setPrefix(rs.getString(1));
			    	  sq.setQuoteNo(rs.getInt(2));
			      }
		      }
		     
		      List<String> autoPrefixList=new ArrayList<String>();
			  
			  pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_sales_quotation"));
			  pstmt.setInt(1, ui.getCompanyId());
			  pstmt.setInt(2, ui.getLocationId());
			  pstmt.setInt(3, ui.getYearId());
		      rs = pstmt.executeQuery();
		      while(rs.next()){
		    	  autoPrefixList.add(rs.getString(1));
		      }
		      sq.setAutoNoPrefixList(autoPrefixList);
		  	String checkType="";
	        pstmt=con.prepareStatement(SQLUtil.GET_AUTO_NO_TYPE.replace("?autno_table","set_autono_sales_quotation"));
			pstmt.setInt(1, ui.getCompanyId());
			pstmt.setInt(2, ui.getLocationId());
			pstmt.setInt(3, ui.getYearId());
			rs=pstmt.executeQuery();
			if(rs.next()){
				checkType=rs.getString(1);
			}
			sq.setCheckAutoNoType(checkType);
//		      pstmt = con.prepareStatement("select distinct prefix1,prefix2,prefix3,autono from set_autono_sales_quotation  where  company_id=? and location_id=? and year_id=? ");
//		      pstmt.setInt(1, ui.getCompanyId());
//		      pstmt.setInt(2, ui.getLocationId());
//		      pstmt.setInt(3, ui.getYearId());
//		      rs = pstmt.executeQuery();
//		      if(rs.next()){
//		    	  sq.setPrefix1(rs.getString(1));
//		    	  sq.setPrefix2(rs.getString(2));
//		    	  sq.setPrefix3(rs.getString(3));
//		    	  sq.setRadioType(rs.getInt(4));
//		      }
//		      
//		      cstmt = con.prepareCall("{call pr_get_auto_trNo(?,?,?,?,?,?)}");
//		      cstmt.setInt(1, ui.getCompanyId());
//		      cstmt.setInt(2, ui.getYearId());
//		      cstmt.setInt(3, ui.getLocationId());
//		      cstmt.setString(4, "SQ");
//		      cstmt.setString(5, "quote_no");
//		      cstmt.setString(6, "quote_id");
//		      rs1 = cstmt.executeQuery();
//		      if(rs1.next()){
//		    	  sq.setLastprefix(rs1.getString(1));
//		    	  sq.setLastNo(rs1.getInt(2));
//		      }
		      
		      
		    }finally{     
		      DatabaseConnection.closeAll(pstmt,rs);
		      DatabaseConnection.closeAll(cstmt,rs1);
		    }
		    return sq;
		  }
	public SalesQuotationHead getSalesQuotationHeader(Connection con, TPCSUser userInfo,
			int quoteId,int iScreenId) throws SQLException,Exception {
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		SalesQuotationHead objSQH=new SalesQuotationHead();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;
		ResultSet rs1=null;
		PreparedStatement pstmtGetInfoDynamic = null;
		PreparedStatement pstmt1=null;
		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListQuotationHeader = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 6);
		try{ 
			
		
			

			StringBuffer query = new StringBuffer(SQLUtil.GET_SALES_QUOTATION_HEADER);
			//System.out.println("query.toString()---->"+query.toString());
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,userInfo.getCompanyId());
			pstmt.setInt(2,userInfo.getYearId());
			pstmt.setInt(3,userInfo.getLocationId());
			pstmt.setInt(4,quoteId);
			rs=pstmt.executeQuery(); 

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_SALES_QUOTATION_HEADER_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,quoteId);
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

//			  pstmt1 = con.prepareStatement("select distinct prefix1,prefix2,prefix3,autono from set_autono_sales_quotation  where  company_id=? and location_id=? and year_id=? ");
//		      pstmt1.setInt(1, userInfo.getCompanyId());
//		      pstmt1.setInt(2, userInfo.getLocationId());
//		      pstmt1.setInt(3, userInfo.getYearId());
//		      rs1 = pstmt1.executeQuery();
		      
			SalesQuotation sq=new SalesQuotation();
			if(rs.next())
			{
				//				System.out.println(" date result set has row in if");
				
				
				sq.setQuoteId(rs.getInt(1));
				sq.setPrefix(rs.getString(2));
				sq.setQuoteNo(rs.getInt(3));
				sq.setQuoteDate(rs.getString(4));
				sq.setReferenceNo(rs.getString(5));
				sq.setPartyId(rs.getInt(6));
				sq.setPartyName(rs.getString(7));
				sq.setCurrencyId(rs.getInt(8));
				sq.setExecutiveId(rs.getInt(9));
				sq.setExecutiveName(rs.getString(10));
				sq.setExchangeRate(rs.getFloat(11));
				sq.sethRemarks(rs.getString(12));
				sq.setVersionNo(rs.getInt(13));
				sq.setTrType(rs.getString(14));
				sq.setEnqId(rs.getInt(15));
				sq.setEnquiryNo(rs.getString(16));
				sq.setEmailSentTo(rs.getString(17));
				sq.setBillToAddress(rs.getString(18));
				sq.setShipToAddress(rs.getString(19));
				sq.setInternalMemo(rs.getString(20));
				sq.setCurrencyName(rs.getString(21));
				sq.setChargesFCY1(rs.getFloat(22));
				sq.setChargesFCY2(rs.getFloat(23));
				sq.setChargesFCY3(rs.getFloat(24));
				sq.setChargeId1(rs.getInt(25));
				sq.setChargeName1(rs.getString(26));
				sq.setChargeId2(rs.getInt(27));
				sq.setChargeName2(rs.getString(28));
				sq.setChargeId3(rs.getInt(29));
				sq.setChargeName3(rs.getString(30));
				sq.setPaymentTerms(rs.getString(31));
				sq.setCreditDays(rs.getInt(32));
//				  if(rs1.next()){
//						// System.out.println("rs1.getInt(4)==setRadioType====>"+rs1.getInt(4));
//					  sq.setRadioType(rs1.getInt(4));
//			      }
				  
				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListQuotationHeader) {
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

				

			} 
			objSQH.setQuotationDynList(dynamicFieldsListQuotationHeader);
			
		  	
	    	
			List<String> autoPrefixList=new ArrayList<String>();
			  
			  pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_sales_quotation"));
			  pstmt.setInt(1, userInfo.getCompanyId());
			  pstmt.setInt(2, userInfo.getLocationId());
			  pstmt.setInt(3, userInfo.getYearId());
		      rs = pstmt.executeQuery();
		      while(rs.next()){
		    	  autoPrefixList.add(rs.getString(1));
		      }
		      sq.setAutoNoPrefixList(autoPrefixList);
		      objSQH.setHeader(sq);

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
		return objSQH;


	}
	
	public SalesQuotation getAllSalesQuotationDetails(Connection con,TPCSUser userInfo, int trId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		SalesQuotation sq=new SalesQuotation();
		List<SalesQuotationDetail> detList=new ArrayList<SalesQuotationDetail>();		
		PreparedStatement pstmt=null;
		PreparedStatement pstmtGetInfoDynamic=null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic=null;
		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListQuotationDetails = dynamicFieldsDAO.getDynamicFields(con,3, 8);
		try{

			StringBuffer query = new StringBuffer(SQLUtil.GET_SALES_QUOTATION_DET_LIST);
		//	System.out.println("query.toString()-------->"+query.toString());
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,userInfo.getCompanyId());
			pstmt.setInt(2,userInfo.getYearId());
			pstmt.setInt(3,userInfo.getLocationId());
			pstmt.setInt(4,trId);
			rs=pstmt.executeQuery();

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_SALES_QUOTATION_DETAIL_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,trId);
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();

			Map<String,String> detMap=new LinkedHashMap<String ,String>();

			while(rs.next())
			{
				SalesQuotationDetail det=new SalesQuotationDetail();
				det.setQuoteId(rs.getInt(1));
				det.setQuoteDetId(rs.getInt(2));
				det.setQuoteDate(rs.getString(3));
				det.setGroupId(rs.getInt(4));
				det.setGroupName(rs.getString(5));
				det.setMatId(rs.getInt(6));
				det.setMaterialName(rs.getString(7));
				det.setUom(rs.getString(8));
				det.setQty(rs.getString(9));
				det.setPriceFcy(rs.getString(10));
				det.setDiscountPercent(rs.getString(11));
				det.setRemarks(rs.getString(12));
				det.setValueFcy(rs.getString(13));
				det.setDisVal(rs.getFloat(14));
				det.setValAftDis(rs.getFloat(15));
				det.setRequiredDate(rs.getString(16));
				det.setVariantId(rs.getInt(17));
				det.setVariantName(rs.getString(18));
				det.setEdPercentage(rs.getFloat(19));
				det.setEcessPercentage(rs.getFloat(20));
				det.setShecessPercentage(rs.getFloat(21));
				det.setVat(rs.getFloat(22));
				det.setCst(rs.getFloat(23));
				det.setService(rs.getFloat(24));
				det.setMaterialDesc(rs.getString(25));
				det.setTaxGroupId(rs.getInt(26));
				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListQuotationDetails) {
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
							//					        	det.setFieldValue(fieldValue);
							detMap.put(dynamicFields.getDbFieldName()+rs.getInt(2), fieldValue);
						}
					}
				}


				detList.add(det);
			} 
			sq.setSalesQuotationDetList(detList);
			sq.setSqDetMap(detMap);
			// TODO Auto-generated method stub
		} 
		finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return sq; 
	}	
	
	
	public List<SalesQuotationDetail> getAllSalesQuotationDetailsForPrint(Connection con,TPCSUser userInfo, int trId,String materialAttachPath) throws SQLException, Exception {
		// TODO Auto-generated method stub
		List<SalesQuotationDetail> detList=new ArrayList<SalesQuotationDetail>();		
		CallableStatement cstmt=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ResultSet rs2=null;

		try{

			cstmt=con.prepareCall("{ call pr_getSQDetail_withTaxForPrint(?,?,?,?) }");
			cstmt.setInt(1,userInfo.getCompanyId());
			cstmt.setInt(2,userInfo.getYearId());
			cstmt.setInt(3,userInfo.getLocationId());
			cstmt.setInt(4,trId);
			System.out.println("trId===>"+trId);
			rs=cstmt.executeQuery();
			while(rs.next())
			{
				SalesQuotationDetail det=new SalesQuotationDetail();
				det.setSlNo(rs.getInt(1));
				det.setMatId(rs.getInt(2));
				det.setMaterialName(rs.getString(3));
				det.setMaterialDesc(rs.getString(4));
				det.setQty(rs.getString(5));
				det.setUom(rs.getString(6));
				det.setPriceFcy(rs.getString(7));
				det.setValBefDis(rs.getFloat(8));
				det.setDiscountPercent(rs.getString(9));
				det.setEdPercentage(rs.getFloat(10));
				det.setEcessPercentage(rs.getFloat(11));
				det.setShecessPercentage(rs.getFloat(12));
				det.setVat(rs.getFloat(13));
				det.setCst(rs.getFloat(14));
				det.setService(rs.getFloat(15));
				det.setDiscountFcy(rs.getString(16));
				det.setValAftDis(rs.getFloat(17));
				det.setEdFcy(rs.getFloat(18));
				det.setEcessFcy(rs.getFloat(19));
				det.setShecessFcy(rs.getFloat(20));
				det.setVatFcy(rs.getFloat(21));
				det.setCstFcy(rs.getFloat(22));
				det.setServiceFcy(rs.getFloat(23));
				det.setValueFcy(rs.getString(24));
				det.setTaxGroupId(rs.getInt(25));
				//System.out.println("rs.getString(9)--->"+rs.getString(24));
				
//				 pst = con.prepareStatement(SQLUtil.SELECT_MATERIAL_PRIMARY_IMAGE);
//			        pst.setInt(1,  rs.getInt(2));
//			        rs2=pst.executeQuery();
//			        if(rs2.next()){
//			        	det.setMaterialImage("<img src=\""+materialAttachPath.concat(rs2.getString(3))+"\" class=\"mat-image\" />");	
//			        }

				detList.add(det);
			} 


			// TODO Auto-generated method stub
		} 
		finally {
			DatabaseConnection.closeAll(cstmt, rs);
			DatabaseConnection.closeAll(pst, rs2);
		}
		return detList; 
	}

//	public List<SalesQuotationHead> getAllSalesQuotation(Connection con,
//			TPCSUser userInfo, int start, int end, String strSearhQuery) throws SQLException {
//		// TODO Auto-generated method stub
//		List<SalesQuotationHead> laList=null;		
//		CallableStatement cstmt=null;
//		ResultSet rs=null;
//		try{ 
//
//			StringBuffer query = new StringBuffer(SQLUtil.GET_SALES_QUOTATION_LIST);
//			query.append(" where  sq.company_Id=" + userInfo.getCompanyId());
//			query.append(" and sq.year_id=" + userInfo.getYearId());
//			query.append(" and sq.location_id=" + userInfo.getLocationId());
//
//			if(!strSearhQuery.isEmpty()){query.append(strSearhQuery);}
//
//			cstmt = con.prepareCall("{call sp_paging(?,?,?)}");
//			cstmt.setString(1, query.toString());
//			cstmt.setInt(2, start);
//			cstmt.setInt(3, end);
//			rs=cstmt.executeQuery();
//			laList=new ArrayList<SalesQuotationHead>();
//			while(rs.next())
//			{
//				SalesQuotationHead  sqH=new SalesQuotationHead();
//				SalesQuotation header=new SalesQuotation();
//				header.setQuoteId(rs.getInt(2));
//				header.setQuoteNo(rs.getString(3));
//				SalesQuotationDetail detail=new SalesQuotationDetail();
//				detail.setGroupId(rs.getInt(4));
//				detail.setGroupName(rs.getString(5));
//				detail.setMatId(rs.getInt(6));
//				detail.setMaterialCode(rs.getString(7));
//				detail.setMaterialName(rs.getString(8));
//				detail.setQty(rs.getFloat(9));
//				detail.setUom(rs.getString(10));
//				detail.setPriceFcy(rs.getFloat(11));
//				detail.setRequiredDate(rs.getString(12));
//				detail.setDiscountPercent(rs.getFloat(13));
//				detail.setRemarks(rs.getString(14));
//				detail.setValBefDis(rs.getFloat(15));
//				detail.setDisVal(rs.getFloat(16));
//				detail.setValAftDis(rs.getFloat(17));
//				header.setQuoteDate(rs.getString(18));
//				header.setClosedTag(rs.getInt(19));
//
//				sqH.setHeader(header);
//				sqH.setDetail(detail);
//				laList.add(sqH);
//			}
//			// TODO Auto-generated method stub
//		} finally {
//			DatabaseConnection.closeAll(cstmt, rs); 
//		}
//		return laList;
//	}
	public int getTotalPages(Connection con,TPCSUser userInfo,PageConfig pc,String strSearhQuery) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pageCount = 0;
		try {

			StringBuffer query = new StringBuffer(SQLUtil.GET_SALES_QUOTATION_COUNT);
			query.append(" where  sq.company_Id=" + userInfo.getCompanyId());
			query.append(" and sq.year_id=" + userInfo.getYearId());
			query.append(" and sq.location_id=" + userInfo.getLocationId());
			if(!strSearhQuery.isEmpty())
				query.append(strSearhQuery);
			pstmt = con.prepareStatement(query.toString());

			rs = pstmt.executeQuery();
			if(rs.next()){
				pageCount = rs.getInt(1);
			}
		} finally {

			DatabaseConnection.closeAll(pstmt,rs);
		}
		return pageCount;

	}
	public boolean deleteSalesQuotation(Connection con, int quoteId,int quoteDetId) throws SQLException {
		// TODO Auto-generated method stub
		Statement cstmt = null;
		boolean isDeleted = false;
		try { 
			cstmt = con.createStatement();
			cstmt.addBatch("DELETE FROM sales_quotation  WHERE quote_id ="+quoteId);
			cstmt.addBatch("DELETE FROM sales_quotation_details  WHERE quote_id="+quoteId);
			cstmt.addBatch("DELETE FROM Sales_Quotation_URL  WHERE quote_id="+quoteId);
			cstmt.addBatch("DELETE FROM Sales_Quotation_Followers  WHERE quote_id="+quoteId);

			cstmt.addBatch("DELETE FROM sales_quotation_dynamic WHERE quote_id="+quoteId);
			cstmt.addBatch("DELETE FROM Sales_Quotation_Details_Dynamic  WHERE quote_id="+quoteDetId);

			cstmt.executeBatch();
			isDeleted=true;
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return isDeleted;
	}
	
	public SalesEnquiry getEnqNoDetailList(Connection con,int custId) throws SQLException, Exception {
		// TODO Auto-generated method stub
		SalesEnquiry se=new SalesEnquiry();
		List<SalesEnquiryDetail> sedList=new ArrayList<SalesEnquiryDetail>();;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{
			
			String searchStr="";
			if(custId>0)
			{
				searchStr=" having se.party_id="+custId;
			}
			cstmt=con.prepareCall("{call pr_getSEDet_ForQuotation(?)}");
			cstmt.setString(1, searchStr);
			rs = cstmt.executeQuery();
			
			while (rs.next()) {
				SalesEnquiryDetail sed=new SalesEnquiryDetail();
				sed.setEnqId(rs.getInt(1));
				sed.setEnqNo(rs.getString(2));
				sed.setMatName(rs.getString(3));
				sed.setUom(rs.getString(4));
				sed.setVariantName(rs.getString(5));
				sed.setQuantity(rs.getString(8));
				sedList.add(sed);
			}
			se.setSalesEnquiryDetList(sedList);
		}
		finally
		{
			DatabaseConnection.closeAll(cstmt, rs);
		}
		return se;
	}
	
	public int postSalesOrder(Connection con,TPCSUser userInfo, int quoteId,int noType,int soNo,String prefix) throws SQLException {
		// TODO Auto-generated method stub
		 PreparedStatement ArrayOfPreparedStatement[] = null;
		 ResultSet ArrayOfResultSet[] = null;	   
		CallableStatement cstmt = null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		int soId = 0;
		try { 
			
			
			System.out.println("1  :"+userInfo.getCompanyId());
			System.out.println("2  :"+userInfo.getLocationId());
			System.out.println("3  :"+userInfo.getYearId());
			System.out.println("4  :"+quoteId);
			System.out.println("5  :"+userInfo.getDisplayUserName());
			System.out.println("6  :"+soNo);
			System.out.println("7  :"+prefix);
			
			cstmt = con.prepareCall("{?=call pr_postSalesOrder(?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, userInfo.getCompanyId());
			cstmt.setInt(3, userInfo.getLocationId());
			cstmt.setInt(4, userInfo.getYearId());
			//System.out.println("quoteId---->"+quoteId);
			cstmt.setInt(5, quoteId);
			cstmt.setString(6, userInfo.getDisplayUserName());
			cstmt.setInt(7, soNo);
			cstmt.setString(8, prefix);
			cstmt.executeUpdate();
			soId = cstmt.getInt(1);
			
			Map<String,String> locConfigMap=userInfo.getLocatonConfigMap();
			int qtyRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffQty"));
			int rateRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffRate"));
			int valRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffValue"));
				
			cstmt=con.prepareCall("{ call pr_calculate_tax_for_det (?,?,?,?,?)}");
			cstmt.setInt(1, soId);
			cstmt.setString(2, "SO");
			cstmt.setInt(3,qtyRoundOff);
			cstmt.setInt(4,rateRoundOff);
			cstmt.setInt(5,valRoundOff);
			cstmt.executeUpdate();
			
			//System.out.println("soId------DAO-------->"+soId);
			//For DynamicFields
			
//			pstmt = con.prepareStatement(" SELECT quote_det_id FROM sales_quotation_details WHERE quote_id=? ");
//			pstmt.setInt(1,quoteId);
//		    rs=pstmt.executeQuery();
//		    List<Integer> quotationDetailIdList=new ArrayList<Integer>();
//		    
//		    while(rs.next()){
//		    	quotationDetailIdList.add(rs.getInt(1));
//		    }
//		    
//		    pstmt = con.prepareStatement(" SELECT so_det_id FROM sales_order_details WHERE so_id=? ");
//		    pstmt.setInt(1,soId);
//		    rs=pstmt.executeQuery();
//		    List<Integer> orderDetailIdList=new ArrayList<Integer>();
//		    
//		    while(rs.next()){
//		    	orderDetailIdList.add(rs.getInt(1));
//		    }
		    
		    cstmt=null;
		    cstmt=con.prepareCall(" { call pr_insert_sq_header_dynamicfield_to_so(?,?)} ");
		    cstmt.setInt(1, quoteId);
	    	cstmt.setInt(2, soId);
		    cstmt.executeUpdate();
		    
		    
		    /*int i=0;
		    cstmt=con.prepareCall(" { call pr_insert_sq_dynamicfield_to_so(?,?,?)} ");
		    for(int quoteDetId:quotationDetailIdList){
		    	
		    	cstmt.setInt(1, quoteDetId);
		    	cstmt.setInt(2, orderDetailIdList.get(i));
		    	cstmt.setInt(3, soId);
		    	cstmt.addBatch();
		    	i++;
		    }
		    cstmt.executeBatch();*/
		    
		  
		    
			//System.out.println("SO ID-----------DAO--->"+soId);
		
			
		} finally {
			
		    ArrayOfPreparedStatement = new PreparedStatement[3];
		      ArrayOfPreparedStatement[0] = pstmt;
		      ArrayOfPreparedStatement[1] = cstmt;
		      
		      
		      ArrayOfResultSet = new ResultSet[2];	
		      ArrayOfResultSet[0]=rs;
		      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		      

		}
		return soId;
	}
	public boolean doAuthorizeSQ(Connection con,TPCSUser userInfo, int quoteId,int iUserId) throws SQLException {
		// TODO Auto-generated method stub
		CallableStatement cstmt = null;
		boolean isAuthorized = false;
		try { 
			
			cstmt = con.prepareCall("{call pr_SalesQuotation_Authorization(?,?,?,?,?)}");
			cstmt.setInt(1, userInfo.getCompanyId());
			cstmt.setInt(2, userInfo.getLocationId());
			cstmt.setInt(3, userInfo.getYearId());
			cstmt.setInt(4, quoteId);
			cstmt.setInt(5, iUserId);
			int columnAffected= cstmt.executeUpdate();
			
//			if(columnAffected>0)
//			{
				isAuthorized=true;
		    //}
		
		
			
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return isAuthorized;
	}
	
	
	public boolean doCancelSQ(Connection con,TPCSUser userInfo, int quoteId,int iUserId) throws SQLException {
		// TODO Auto-generated method stub
		CallableStatement cstmt = null;
		boolean isCanceled = false;
		try { 
			
			cstmt = con.prepareCall("{call pr_SalesQuotation_Cancel(?,?,?,?,?)}");
			cstmt.setInt(1, userInfo.getCompanyId());
			cstmt.setInt(2, userInfo.getLocationId());
			cstmt.setInt(3, userInfo.getYearId());
			cstmt.setInt(4, quoteId);
			cstmt.setInt(5, iUserId);
			int columnAffected= cstmt.executeUpdate();
			
//			if(columnAffected>0)
//			{
			isCanceled=true;
		    //}
		
		
			
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return isCanceled;
	}
	
	public boolean dopreCloseSalesQuotation(Connection con,TPCSUser userInfo, int quoteId,int iUserId) throws SQLException {
		// TODO Auto-generated method stub
		CallableStatement cstmt = null;
		boolean isClosed= false;
		try { 
			
			cstmt = con.prepareCall("{call pr_SalesQuotation_PreClose(?,?,?,?,?)}");
			cstmt.setInt(1, userInfo.getCompanyId());
			cstmt.setInt(2, userInfo.getLocationId());
			cstmt.setInt(3, userInfo.getYearId());
			cstmt.setInt(4, quoteId);
			cstmt.setInt(5, iUserId);
			int columnAffected= cstmt.executeUpdate();
			
//			if(columnAffected>0)
//			{
			isClosed=true;
		    //}
		
		
			
		} finally {

			DatabaseConnection.closeAll(cstmt,null);
		}
		return isClosed;
	}
	
	public SalesQuotationDetail getSalesQuotationDetInfo(Connection con,
			TPCSUser userInfo, int quoteDetId) throws SQLException {
		// TODO Auto-generated method stub
		SalesQuotationDetail detail=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{ 

			StringBuffer query = new StringBuffer(SQLUtil.GET_SALES_QUOTATION_DET_INFO);
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,userInfo.getCompanyId());
			pstmt.setInt(2,userInfo.getYearId());
			pstmt.setInt(3,userInfo.getLocationId());
			pstmt.setInt(4,quoteDetId);
			rs=pstmt.executeQuery(); 
			if(rs.next())
			{
				detail=new SalesQuotationDetail();
				detail.setQuoteDetId(rs.getInt(1));
				detail.setQuoteId(rs.getInt(2));
				detail.setGroupId(rs.getInt(3));
				detail.setGroupName(rs.getString(4));
				detail.setMatId(rs.getInt(5));
				detail.setMaterialName(rs.getString(6));
				detail.setQty(rs.getString(7));
				detail.setUom(rs.getString(8));
				detail.setPriceFcy(rs.getString(9));
				detail.setQuoteDate(rs.getString(10));
				detail.setDiscountPercent(rs.getString(11));
				detail.setRemarks(rs.getString(12));
				detail.setValBefDis(rs.getFloat(13));
				detail.setDisVal(rs.getFloat(14));
				detail.setValAftDis(rs.getFloat(15));
				detail.setVariantId(rs.getInt(16));
				detail.setVariantName(rs.getString(17));
				detail.setRequiredDate(rs.getString(18));


			} 
			// TODO Auto-generated method stub
		} finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return detail;


	}
	public boolean deleteSalesQuotationDetail(Connection con, TPCSUser userInfo,
			int quoteDetId,int quoteId) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ArrayOfPreparedStatement[] = null;
		ResultSet ArrayOfResultSet[] = null;
		PreparedStatement pstmt=null;
		CallableStatement cstmt=null;
		boolean isDeleted=false;		
		PreparedStatement pstmtDeleteSQDetDynamic=null;
		try{ 
			pstmt = con.prepareStatement(SQLUtil.DELETE_SALES_QUOTATION_DETAIL);
			pstmt.setInt(1, quoteDetId);
			int iCount = pstmt.executeUpdate();

			pstmtDeleteSQDetDynamic = con.prepareStatement(SQLUtil.DELETE_SALES_QUOTATION_DETAIL_DYNAMIC);
			pstmtDeleteSQDetDynamic.setInt(1, quoteDetId);
			int iDynCount = pstmtDeleteSQDetDynamic.executeUpdate();

			if (iCount > 0)
			{
				isDeleted = true;
				
				Map<String,String> locConfigMap=userInfo.getLocatonConfigMap();
 				int qtyRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffQty"));
 				int rateRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffRate"));
 				int valRoundOff=Validator.convertToInteger(locConfigMap.get("RoundoffValue"));
 				
 				cstmt=con.prepareCall("{ call pr_calculate_tax_for_det (?,?,?,?,?)}");
 				cstmt.setInt(1, quoteId);
 				cstmt.setString(2, "SQ");
 				cstmt.setInt(3,qtyRoundOff);
 				cstmt.setInt(4,rateRoundOff);
 				cstmt.setInt(5,valRoundOff);
 				cstmt.executeUpdate();
				
//				cstmt=con.prepareCall("{ call pr_calculate_tax_for_det (?,?)}");
//				cstmt.setInt(1, quoteId);
//				cstmt.setString(2, "SQ");
//				cstmt.executeUpdate();
			}
				

		} finally {
			ArrayOfPreparedStatement = new PreparedStatement[2];    
			ArrayOfPreparedStatement[0] = pstmt;
			ArrayOfPreparedStatement[1] = pstmtDeleteSQDetDynamic;
			ArrayOfResultSet = new ResultSet[0];
			DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
		}
		return isDeleted;
	}
	public boolean createAmendment(Connection con, TPCSUser userInfo,SalesQuotationHead objSQ,int quoteId) throws SQLException {
		// TODO Auto-generated method stub
		boolean isAmend=false;		
		CallableStatement cstmt=null;
		try{ 
			//			System.out.println("Create Amendment -head mode----------->"+objSQ.getHeaderMode());
			cstmt = con.prepareCall("{? = call tpcs_amendSalesQuotation(?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, quoteId);
			cstmt.setString(3, objSQ.getHeaderMode());

			int iCount = cstmt.executeUpdate();
			if (iCount > 0)
				isAmend = true;

		} finally {
			DatabaseConnection.closeAll(cstmt, null);
		}
		return isAmend;
	}
	
	
	
	public SalesQuotation getQuotationConfig(Connection con, TPCSUser userInfo) throws SQLException, Exception {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		SalesQuotation sq=new SalesQuotation();

		try{ 
			
			pstmt=con.prepareStatement("select config_option,config_name from set_location_config where heading='Sales Quotation' and config_name='Allow to change payment terms and credit period'");
			rs=pstmt.executeQuery(); 
			if(rs.next())
			{
				sq.setPaymentConfig(rs.getString(1));
			} 

			pstmt=con.prepareStatement("select config_option,config_name from set_location_config where heading='Sales Quotation' and config_name='Allow to change selling price'");
			rs=pstmt.executeQuery(); 
			if(rs.next())
			{
				sq.setPriceConfig(rs.getString(1));
			} 
			// TODO Auto-generated method stub
		} finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return sq;

	}
	
	public SalesQuotationHead getSalesPrintHeader(Connection con, TPCSUser userInfo,
			int quoteId,int iScreenId) throws SQLException, Exception {
		SalesQuotationHead objSQH=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rsGetInfoDynamic = null;
		PreparedStatement pstmtGetInfoDynamic = null;

		//Added for Dynamic Fields
		DynamicFieldsDAO dynamicFieldsDAO = new DynamicFieldsDAO();
		List<DynamicFields> dynamicFieldsListQuotationHeader = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 6);

		try{ 

			StringBuffer query = new StringBuffer(SQLUtil.GET_SALES_PRINT_HEADER);
			pstmt=con.prepareStatement(query.toString());
			pstmt.setInt(1,userInfo.getCompanyId());
			pstmt.setInt(2,userInfo.getYearId());
			pstmt.setInt(3,userInfo.getLocationId());
			pstmt.setInt(4,quoteId);
			rs=pstmt.executeQuery(); 

			pstmtGetInfoDynamic = con.prepareStatement(SQLUtil.SELECT_SALES_QUOTATION_HEADER_DYNAMIC);
			pstmtGetInfoDynamic.setInt(1,quoteId);
			rsGetInfoDynamic = pstmtGetInfoDynamic.executeQuery();
			if(rs.next())
			{
				//				System.out.println(" date result set has row in if");
				objSQH=new SalesQuotationHead();
				SalesQuotation sq=new SalesQuotation();
				sq.setQuoteId(rs.getInt(1));
				sq.setPrefixQuoteNo(rs.getString(2));
				sq.setQuoteDate(rs.getString(3));
				sq.setReferenceNo(rs.getString(4));
				sq.setPartyId(rs.getInt(5));
				sq.setPartyName(rs.getString(6));
				sq.setCurrencyId(rs.getInt(7));
				sq.setCurrencyName(rs.getString(8));
				sq.setExecutiveId(rs.getInt(9));
				sq.setExecutiveName(rs.getString(10));
				sq.setExchangeRate(rs.getFloat(11));
				sq.sethRemarks(rs.getString(12));
				sq.setVersionNo(rs.getInt(13));
				sq.setEnquiryNo(rs.getString(14));
				sq.setTrType(rs.getString(15));
				sq.setChargesFCY1(rs.getFloat(16));
				sq.setChargesFCY2(rs.getFloat(17));
				sq.setChargesFCY3(rs.getFloat(18));
				sq.setChargeName1(rs.getString(19));
				sq.setChargeName2(rs.getString(20));
				sq.setChargeName3(rs.getString(21));
				sq.setPaymentTerms(rs.getString(22));
				sq.setCreditDays(rs.getInt(23));
				
				if(rsGetInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListQuotationHeader) {
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

				objSQH.setHeader(sq);

			} 
			objSQH.setQuotationDynList(dynamicFieldsListQuotationHeader);

			//			System.out.println("objSQH ===>:"+objSQH);
			// TODO Auto-generated method stub
		} finally {
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return objSQH;


	}
	
	
	public String doChkAvailableQty(TPCSUser userInfo,Connection con,int enqId,int matId,int varId,String uom)throws SQLException,Exception{
	    PreparedStatement pstmt=null;
	    ResultSet rs = null;

	    StringBuffer strBuffer=new StringBuffer();
	    try{   
	    	float seQty=0.0f;
	    	float sqQty=0.0f;
	    	float balQty=0.0f;
	    	
	    	String variant="";
	    	if(varId>0){
	    		variant=" and variant_id="+varId;
	    	}
	    	
	    	pstmt=con.prepareStatement(" SELECT Sum(Quantity) FROM sales_enquiry_details WHERE material_id="+matId+" and sku='"+uom+"' "+variant+" and enq_id="+enqId);
	    	rs= pstmt.executeQuery();
			while(rs.next())
			{
				seQty=rs.getFloat(1);
			}
			
			pstmt=con.prepareCall(" SELECT Sum(sqd.Quantity) FROM sales_quotation sq inner join sales_quotation_details sqd on sq.quote_id=sqd.quote_id WHERE sqd.Material_id="+matId+" and sqd.sku='"+uom+"'  "+variant+" and sq.enq_id="+enqId);
			rs= pstmt.executeQuery();
			while(rs.next())
			{
				sqQty=rs.getFloat(1);
			}
			
		//	System.out.println("soQty---DAO--"+soQty);
		//	System.out.println("siQty---DAO--"+siQty);
			balQty=seQty-sqQty;
	    	
			strBuffer.append(balQty);   
			
	     }
	    catch (Exception e) {
			e.printStackTrace();
		}
	    finally{      
	      DatabaseConnection.closeAll(pstmt,rs);
	    }
	    return strBuffer.toString();
	  }
	
	public List<Map<String,String>> getAllSalesQuotationOnColumnMapping(Connection con, PageConfig pc,TPCSUser userInfo,String strSearchCriteria,List<String> orderBy,int iScreenId,String quotationAttachPath)throws SQLException,Exception{
		CallableStatement cstmt=null;
		ResultSet rs = null; 
		List<Map<String,String>> assortList=new ArrayList<Map<String,String>>();
		Map<String,String> map=null;
		String strbuffer="";
		PreparedStatement pstmtGetQuotationDetailInfoDynamic =null;
		ResultSet rsGetQuotationDetailInfoDynamic=null;
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
			List<DynamicFields> dynamicFieldsListOrderHeader = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 6);
			List<DynamicFields> dynamicFieldsListOrderDetail = dynamicFieldsDAO.getDynamicFields(con,iScreenId, 8);

			Map<String,String> colPreMap=getColPreMap(con,1);
			StringBuffer objBuffer = new StringBuffer(SQLUtil.GET_SALES_QUOTATION_LIST);
			objBuffer.append(" where  sq.company_Id=" + userInfo.getCompanyId());
			objBuffer.append(" and sq.year_id=" + userInfo.getYearId());
			objBuffer.append(" and sq.location_id=" + userInfo.getLocationId());
			if(strSearchCriteria!=null || strSearchCriteria=="")
			{
				objBuffer.append(strSearchCriteria);
			}
			
			
			objBuffer.append(" order by sq.quote_id desc ," );
			
			if(orderBy!=null && !orderBy.isEmpty())
			{
				for(String strOrderBy:orderBy){
					if(strOrderBy!=null)
					{
						objBuffer.append(strOrderBy+",");  
					} 

				}

			}
			
			
			  strbuffer=objBuffer.toString();
	    	  strbuffer=strbuffer.substring(0, strbuffer.length() - 1);
	    	  
				


			//System.out.println("objBuffer.toString()===>"+objBuffer.toString());
			cstmt=con.prepareCall("{call sp_Paging(?,?,?)}");      
			cstmt.setString(1,strbuffer.toString());
			cstmt.setInt(2,pc.iStart);
			cstmt.setInt(3,pc.iEnd);
			rs=cstmt.executeQuery();
			while(rs.next()){  

				map = new HashMap<String, String>();

				map.put("SalesQuotation.QuotationId", rs.getString("quote_id"));
				map.put("SalesQuotation.QuotationNo", rs.getString("quote_no"));
			//	map.put(colPreMap.get("quote_no"), rs.getString("quote_no"));
				map.put(colPreMap.get("quote_date"), rs.getString("quote_date"));
				map.put(colPreMap.get("party_id"), rs.getString("party_name"));
				map.put(colPreMap.get("group_id"), rs.getString("group_name"));
				map.put(colPreMap.get("Bill_to_address"), rs.getString("Bill_to_address"));
				map.put(colPreMap.get("Ship_to_address"), rs.getString("Ship_to_address"));
				map.put(colPreMap.get("Internal_Memo"), rs.getString("Internal_Memo"));
				map.put(colPreMap.get("mat_code"), rs.getString("Material_code"));
				map.put(colPreMap.get("material_id"), rs.getString("Material_name"));
				map.put(colPreMap.get("material_description"), rs.getString("desc"));
				map.put(colPreMap.get("quantity"), rs.getString("quantity"));
				map.put(colPreMap.get("sku"), rs.getString("sku"));
				map.put(colPreMap.get("price_fcy"), rs.getString("price_fcy"));
				map.put(colPreMap.get("required_date"), rs.getString("required_date"));
				//map.put(colPreMap.get("valBefDis"), rs.getString("valBefDis"));
				map.put(colPreMap.get("value_before_tax_fcy"), rs.getString("value_before_tax_fcy"));
				map.put(colPreMap.get("remarks"), rs.getString("remarks"));
				map.put(colPreMap.get("variant_id"), rs.getString("variant_name"));
				map.put(colPreMap.get("email_sent_to"), rs.getString("email_sent_to"));
				map.put(colPreMap.get("discount_percent"), rs.getString("discount_percent"));
				map.put(colPreMap.get("discount_fcy"), rs.getString("discount_fcy"));
				map.put(colPreMap.get("ed_percentage"), rs.getString("ed_percentage"));
				map.put(colPreMap.get("ecess_percentage"), rs.getString("ecess_percentage"));
				map.put(colPreMap.get("shecess_percentage"), rs.getString("shecess_percentage"));
				map.put(colPreMap.get("vat_percentage"), rs.getString("vat_percentage"));
				map.put(colPreMap.get("gst_percentage"), rs.getString("gst_percentage"));
				map.put(colPreMap.get("cst_percentage"), rs.getString("cst_percentage"));
				map.put(colPreMap.get("service_tax_percentage"), rs.getString("service_tax_percentage"));
				map.put(colPreMap.get("ed_fcy"), rs.getString("ed_fcy"));
				map.put(colPreMap.get("ecess_fcy"), rs.getString("ecess_fcy"));
				map.put(colPreMap.get("shecess_fcy"), rs.getString("shecess_fcy"));
				map.put(colPreMap.get("vat_fcy"), rs.getString("vat_fcy"));
				map.put(colPreMap.get("cst_fcy"), rs.getString("cst_fcy"));
				map.put(colPreMap.get("gst_fcy"), rs.getString("gst_fcy"));
				map.put(colPreMap.get("service_tax_fcy"), rs.getString("service_tax_fcy"));
				map.put(colPreMap.get("value_after_tax_fcy"), rs.getString("value_after_tax_fcy"));
				map.put(colPreMap.get("enq_id"), rs.getString("enq_no"));
				map.put("SalesQuotation.SalesQuotationDetId", String.valueOf(rs.getString("quote_det_id")));

				pstmt = con.prepareStatement("select top 1 tax_group from tax_group where tax_group_id=(select tax_group_id from sales_quotation_details  where quote_det_id=?)");
				pstmt.setInt(1, rs.getInt("quote_det_id"));
				rsAttach=pstmt.executeQuery();
				if(rsAttach.next()){
					map.put(colPreMap.get("tax_group"), rsAttach.getString(1));
				}
				else{
					map.put(colPreMap.get("tax_group"), "");
				}
				pstmt = con.prepareStatement(SQLUtil.SELECT_QUOTATION_ATTACHMENT);
				pstmt.setInt(1, Validator.convertToInteger(rs.getString("quote_id")));
				rsAttach=pstmt.executeQuery();

				StringBuffer fileNames=new StringBuffer();
				while(rsAttach.next()){
					if(imageTypeList.contains(rsAttach.getString(2).toLowerCase())){
						fileNames.append("<div class=\"grid-image\" ><a target=\"_blank\" href=\""+quotationAttachPath.concat(rsAttach.getString(3))+"\"  title=\"view\"><img src=\""+quotationAttachPath.concat(rsAttach.getString(3))+"\" width=\"30\"></a></div>");
					}
					else{
						fileNames.append("<a target=\"_blank\" href=\""+quotationAttachPath.concat(rsAttach.getString(3))+"\"  title=\"view\">"+rsAttach.getString(3)+"</a><br/>");
					}
				}
				map.put(colPreMap.get("Quotation.Attachment"), fileNames.toString());


				//Start - Added for dynamic fields
				pstmtGetQuotationDetailInfoDynamic = con.prepareStatement(SQLUtil.SELECT_SALES_QUOTATION_DETAIL_DYNAMIC_BY_ID);
				pstmtGetQuotationDetailInfoDynamic.setInt(1, Validator.convertToInteger(rs.getString("quote_id")));  
				pstmtGetQuotationDetailInfoDynamic.setInt(2, Validator.convertToInteger(rs.getString("quote_det_id"))); 
				rsGetQuotationDetailInfoDynamic = pstmtGetQuotationDetailInfoDynamic.executeQuery();
				if(rsGetQuotationDetailInfoDynamic.next()){
					for(DynamicFields dynamicFields : dynamicFieldsListOrderHeader) {
						if(!dynamicFields.isFixed()){
							String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							String fieldValue = rsGetQuotationDetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetQuotationDetailInfoDynamic.getString(dynamicFields.getDbFieldName());
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
					for(DynamicFields dynamicFields : dynamicFieldsListOrderDetail) {
						if(!dynamicFields.isFixed()){
							String labelName=dynamicFields.getDisplayName()==null?"":dynamicFields.getDisplayName();
							String fieldValue = rsGetQuotationDetailInfoDynamic.getString(dynamicFields.getDbFieldName())==null?"":rsGetQuotationDetailInfoDynamic.getString(dynamicFields.getDbFieldName());
							//  dynamicFields.setFieldValue(fieldValue);  
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
							map.put(labelName,fieldValue);
						}
					}
				}

				//End - Added for dynamic fields
				assortList.add(map);
			}

		}
		finally{

			DatabaseConnection.closeAll(cstmt,rs);
		}
		return assortList;
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


	public List<SalesQuotation> getAttachFiles(Connection con, int matId) throws SQLException {
		PreparedStatement pstmt=null;    
		ResultSet rs = null;    
		List<SalesQuotation> attachFiles=new ArrayList<SalesQuotation>();
		try{

			pstmt = con.prepareStatement(SQLUtil.SELECT_SALES_QUOTATION_ATTACHMENT);
			pstmt.setInt(1, matId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				SalesQuotation objUrl=new SalesQuotation();
				objUrl.setQuoteId(rs.getInt(1));
				objUrl.setUrlType(rs.getString(2));
				objUrl.setUrl(rs.getString(3));
				objUrl.setFileName(rs.getString(4));
				attachFiles.add(objUrl);
			}
		}finally{     

			DatabaseConnection.closeAll(pstmt,rs);
		}
		return attachFiles;
	}
	public boolean deleteAttachment(Connection con, int quoteId,String fileName) throws SQLException {
		PreparedStatement pstmt=null;    
		ResultSet rs = null;    
		boolean bFlag=false;
		try{
			int count=0;
			pstmt = con.prepareStatement(SQLUtil.DELETE_SALES_QUOTATION_ATTACH);
			pstmt.setInt(1, quoteId);
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
		
	public String doGetMatData(TPCSUser userInfo,Connection con,int matId,int id,String trTag)throws SQLException,Exception{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String desc="";
		String uom="";
		float rate=0.0f;
		StringBuffer buffer=new StringBuffer();
		try{      
			
			if(id>0)
			{
				if(trTag.equals("INV"))//SO data
				{
					pstmt=con.prepareStatement(" SELECT  sod.material_description,sod.sku,sod.price_fcy  FROM sales_order_details sod "
							+ " inner join material m on m.material_id=sod.material_id WHERE sod.material_id=? and sod.so_id= ?");
					pstmt.setInt(1,matId);
					pstmt.setInt(2,id);
				}
				else if(trTag.equals("RET"))//INV data
				{
					pstmt=con.prepareStatement(" SELECT  sid.material_description,sid.sku,sid.price_fcy  FROM sales_invoice_details sid "
							+ " inner join material m on m.material_id=sid.material_id WHERE sid.material_id=? and sid.invoice_id= ? ");
					pstmt.setInt(1,matId);
					pstmt.setInt(2,id);
				}
				
			}
			else{
				pstmt=con.prepareStatement(SQLUtil.GET_MAT_DATA);
				pstmt.setInt(1,matId);
			   }
			
			rs=pstmt.executeQuery();

			if(rs.next()){
				desc=rs.getString(1);
				uom=rs.getString(2);
				rate=rs.getFloat(3);
			}
			buffer.append("<material_datas>");
			buffer.append("<material_data>");
			buffer.append("<desc>");
			buffer.append(StringEscapeUtils.escapeXml(desc));
			buffer.append("</desc>");
			buffer.append("<uom>");
			buffer.append(uom);
			buffer.append("</uom>");
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

	public boolean deleteFollower(Connection con, int quoteId, int userId, TPCSUser ui) throws SQLException {
		boolean bFlag=false;
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement(" DELETE  sqf  from Sales_Quotation_Followers sqf "+
					" left outer join  tpcs_user t WITH(NOLOCK) on  sqf.user_id=t.tpcs_user_id "+ 
					" left outer join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+
					" WHERE sqf.quote_id=? AND t.tpcs_user_id!=? and sqf.user_id=? ");
			pst.setInt(1, quoteId);
			pst.setInt(2, ui.getUserId());
			pst.setInt(3, userId);
			int count=pst.executeUpdate();
			bFlag=count>0?true:false;
		}
		finally{
			DatabaseConnection.closeAll(pst,null);
		}
		return bFlag;
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
	
	public String doChkOrderLastTransaction(TPCSUser userInfo,Connection con)throws SQLException,Exception{
	    PreparedStatement pstmt=null;
	    ResultSet rs = null;

	    StringBuffer buffer=new StringBuffer();
	    try{   

	    	
	    	StringBuffer prefixBuffer=new StringBuffer();
	    	
	    	int autono = 0;
	    	int count=0;
	    	pstmt=con.prepareStatement(" SELECT DISTINCT count(*) FROM set_autono_sales_order WHERE company_id="+userInfo.getCompanyId()+" AND year_id="+userInfo.getYearId()+"  AND location_id="+userInfo.getLocationId());
	    	rs= pstmt.executeQuery();
	    	if(rs.next())
			{
	    		count=rs.getInt(1);
			}
	    	
	    	pstmt=con.prepareStatement(" SELECT DISTINCT  autono FROM set_autono_sales_order WHERE company_id="+userInfo.getCompanyId()+" AND year_id="+userInfo.getYearId()+"  AND location_id="+userInfo.getLocationId());
	    	rs= pstmt.executeQuery();
			while(rs.next())
			{
				autono=rs.getInt(1);
			}


			String msg="";
			if(count>1)
			{
				if(autono==1)
				{
					msg="1";
				}
				else{
					msg="0";
				}
			}
			else if(count==1)
			{
				if(autono==1)
				{
					msg="3";
				}
				else{
					msg="2";
				}
			}
			
			  pstmt = con.prepareStatement(SQLUtil.GET_ALL_PREFIX.replace("?autno_table","set_autono_sales_order"));
			  pstmt.setInt(1, userInfo.getCompanyId());
			  pstmt.setInt(2, userInfo.getLocationId());
			  pstmt.setInt(3, userInfo.getYearId());
		      rs = pstmt.executeQuery();
		      while(rs.next()){
		    	  prefixBuffer.append("<prefix_datas>");
		    	  prefixBuffer.append("<prefix_data>");
		    	  prefixBuffer.append(rs.getString(1));
		    	  prefixBuffer.append("</prefix_data>");
		    	  prefixBuffer.append("</prefix_datas>");
		      }
		      
			System.out.println(" prefix :"+prefixBuffer.toString());
			buffer.append("<chk_autonos>");
			buffer.append("<chk_autono>");
			buffer.append("<msg>");
			buffer.append(msg);
			buffer.append("</msg>");
			buffer.append("</chk_autono>");
			buffer.append(prefixBuffer.toString());
			buffer.append("</chk_autonos>");
	    	
//			strBuffer.append(autono);   
			
	     }
	    catch (Exception e) {
			e.printStackTrace();
		}
	    finally{      
	      DatabaseConnection.closeAll(pstmt,rs);
	    }
	    return buffer.toString();
	  }
	
	public boolean chkSalesQuotationAndSalesOrderQty(Connection con, int quoteId) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		 ResultSet rs = null;  
		boolean isEqualQty=false;
		try{
			
			float sqQty=0;
			float soQty=0;
			pstmt=con.prepareStatement("select isnull(sum(quantity),0) from sales_quotation sq"
					+ " inner join sales_quotation_details sqd with(nolock) on sq.quote_id=sqd.quote_id"
					+ " where sq.quote_id="+quoteId);
			rs= pstmt.executeQuery();
			if(rs.next()){
				sqQty=rs.getFloat(1);
			}
			
			pstmt=con.prepareStatement("select isnull(sum(quantity),0) from sales_order so"
					+ " inner join sales_order_details sod with(nolock) on so.so_id=sod.so_id"
					+ " where so.quote_id="+quoteId);
			rs= pstmt.executeQuery();
			if(rs.next()){
				soQty=rs.getFloat(1);
			}
			if(sqQty==soQty){
				isEqualQty=true;
			}
//			System.out.println("isEqualQty--->"+isEqualQty);
//			System.out.println("soQty--->"+soQty);
//			System.out.println("siQty--->"+siQty);
		}
		
		finally
		{
			DatabaseConnection.closeAll(pstmt, rs);
		}
		return isEqualQty;
	}

	public boolean checkSQExistense(Connection con, TPCSUser userInfo,SalesQuotationHead objSI) throws SQLException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		boolean isExists=true;
		try{
			pst=con.prepareStatement(SQLUtil.CHECK_SQ_AUTO_NUMBER_EXISTENCE);
			pst.setInt(1, objSI.getHeader().getQuoteNo());
			pst.setString(2, objSI.getHeader().getPrefix());
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

	
	
//	public String doGetQuotationSlNo(TPCSUser ui,Connection con,String prefix)throws SQLException,Exception{
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		StringBuffer buffer=new StringBuffer();
//		try{      
//			pstmt=con.prepareStatement(" SELECT DISTINCT  last_no1,last_no2,last_no3 FROM set_autono_sales_quotation where company_id=? and location_id=? and year_id=?");
//			 pstmt.setInt(1, ui.getCompanyId());
//		      pstmt.setInt(2, ui.getLocationId());
//		      pstmt.setInt(3, ui.getYearId());
//			rs=pstmt.executeQuery();
//
//			int lastNo1=0;
//			int lastNo2=0;
//			int lastNo3=0;
//			if(rs.next()){
//				lastNo1=rs.getInt(1);
//				lastNo2=rs.getInt(2);
//				lastNo3=rs.getInt(3);
//				
//			}
//			buffer.append("<sl_nos>");
//			buffer.append("<sl_no>");
//			buffer.append("<lastno1>");
//			buffer.append(lastNo1);
//			buffer.append("</lastno1>");
//			buffer.append("<lastno2>");
//			buffer.append(lastNo2);
//			buffer.append("</lastno2>");
//			buffer.append("<lastno3>");
//			buffer.append(lastNo3);
//			buffer.append("</lastno3>");
//			buffer.append("</sl_no>");
//			buffer.append("</sl_nos>");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally{    
//			DatabaseConnection.closeAll(pstmt,rs);
//		}
//		return buffer.toString();
//	}
	
	
	
//	public SalesQuotation saveQuotationAutoNum(Connection con, SalesQuotation obj,TPCSUser ui) throws SQLException {
//		 PreparedStatement ArrayOfPreparedStatement[] = null;
//		 ResultSet ArrayOfResultSet[] = null;	   
//		 PreparedStatement pstmt=null;
//			ResultSet rs=null;
//		    PreparedStatement pstmt1=null;
//			ResultSet rs1=null;
//			boolean bFlag=false;
//			Statement cstmt = null;
//			try {
//				
//				    pstmt=con.prepareStatement(" DELETE FROM set_autono_sales_quotation  WHERE company_id=? AND location_id=? AND year_id=? ");
//				 	pstmt.setInt(1, ui.getCompanyId());
//					pstmt.setInt(2, ui.getLocationId());
//					pstmt.setInt(3, ui.getYearId());
//					pstmt.executeUpdate();
//					
//					pstmt1=con.prepareStatement(" INSERT INTO set_autono_sales_quotation(company_id,location_id,year_id,autono,common_slno,prefix1,prefix2,prefix3,last_no1,last_no2,last_no3)  VALUES (?,?,?,?,?,?,?,?,?,?,?) ");
//					pstmt1.setInt(1, ui.getCompanyId());
//					pstmt1.setInt(2, ui.getLocationId());
//					pstmt1.setInt(3, ui.getYearId());
//					pstmt1.setInt(4, obj.getRadioType());
//					pstmt1.setInt(5, obj.getCommonType());
//					pstmt1.setString(6, obj.getPrefix1());
//					pstmt1.setString(7, obj.getPrefix2());
//					pstmt1.setString(8, obj.getPrefix3());
//					pstmt1.setInt(9, obj.getNextNo1());
//					pstmt1.setInt(10, obj.getNextNo2());
//					pstmt1.setInt(11, obj.getNextNo3());
//					pstmt1.executeUpdate();
//					
//				      int iCount = pstmt1.executeUpdate();
//				      bFlag=(iCount>0)?true:false;
//				      obj.setInserted(bFlag);
//				
//			}finally{
//				ArrayOfPreparedStatement = new PreparedStatement[3];
//			      ArrayOfPreparedStatement[0] = pstmt;
//			      ArrayOfPreparedStatement[1] = pstmt1;
//			      ArrayOfResultSet = new ResultSet[2];	
//			      ArrayOfResultSet[0]=rs;
//			      ArrayOfResultSet[0]=rs1;
//			      DatabaseConnection.closeAll(ArrayOfPreparedStatement,ArrayOfResultSet);
//			      DatabaseConnection.closeAll(cstmt,rs);
//			      }
//			return obj;
//		}
	
}