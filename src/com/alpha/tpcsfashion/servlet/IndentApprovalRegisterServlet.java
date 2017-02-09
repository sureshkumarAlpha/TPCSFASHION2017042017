package com.alpha.tpcsfashion.servlet;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alpha.tpcsfashion.beans.ColumnPreference;
import com.alpha.tpcsfashion.beans.Indent;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.beans.UserRights;
import com.alpha.tpcsfashion.core.PageConfig;
import com.alpha.tpcsfashion.gridmaker.IndentApprovalRegisterGridMaker;
import com.alpha.tpcsfashion.model.ColumnPreferenceManager;
import com.alpha.tpcsfashion.model.IndentApprovalRegisterManager;
import com.alpha.tpcsfashion.model.IndentManager;
import com.alpha.tpcsfashion.model.UserRightsManager;
import com.alpha.tpcsfashion.util.SubdocumentId;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;
import com.alpha.tpcsfashion.util.TPCSRedirectPage;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

public class IndentApprovalRegisterServlet {

	public void doDisplayIndentApprovalRegister(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  
			HttpSession session=request.getSession();
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");

			int subdocumentId=Validator.convertToInteger(request.getParameter("subdocument_id"));
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);  
			String strSearchCriteria="";
			String reqType=request.getParameter("request_type");
			
			if("Search".equalsIgnoreCase(reqType)){	       
				strSearchCriteria=getSearchCriteria(request);
				getAndSetAttributes(request);
			}
			
			if(request.getSession().getAttribute("ser_rights")!=null){
				rights =(UserRights)request.getSession().getAttribute("ser_rights");
			}
			else{
				rights =objRight.getUserRights(SubdocumentId.INDENT_APPROVAL, userInfo);
				session.setAttribute("ser_rights",rights);		
			}
			
			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(subdocumentId, userInfo);
			ColumnPreference cp= objManager.getTotalPages(userInfo,strSearchCriteria,DataList,subdocumentId);
			
			PageConfig pc=null;
			if(cp.isGroupPaging()){
				pc=new PageConfig(request.getParameter("pageno"),1);
				pc.setPageCount(cp.getPageCnt(),1);
				request.setAttribute("pc", pc);
			}
			else{
				
				pc=new PageConfig(request.getParameter("pageno"));
				pc.setPageCount(cp.getPageCnt());
				request.setAttribute("pc", pc);
			}
			
			DataList.setPaging(cp);
			
			IndentApprovalRegisterGridMaker objUtil=new IndentApprovalRegisterGridMaker();

			List<Map<String,String>> orders = objManager.getAllIndentApprovalRegisterOnColumnMapping(pc,userInfo,strSearchCriteria,DataList,subdocumentId);
			String Grid = objUtil.formIndentApprovalRegisterGrid(userInfo,DataList, orders, bundle,  rights);
			request.setAttribute("se_register_grid", Grid); 
			request.setAttribute("subdocument_id", subdocumentId);  
			
			if("Search".equalsIgnoreCase(reqType)){	       
				getAndSetAttributes(request);
			}
			
			dynamicfields = soMan.getDynamicFields("8,9",userInfo,1);
			session.setAttribute("fixedfields", dynamicfields);
			request.setAttribute("invoke_servlet", "com.alpha.tpcsfashion.servlet.IndentApprovalRegisterServlet");
			request.setAttribute("invoke_method", "doDisplayIndentApprovalRegister");
			
			request.setAttribute("is_multi_branch_app", userInfo.getLocatonConfigMap()!=null?userInfo.getLocatonConfigMap().get("MultiBranch"):"");
			
			
			rights=null;
			cpm=null;
			DataList=null;
			cp=null;
			pc=null;
			objUtil=null;
			orders=null;
			Grid=null;
			dynamicfields=null;
			
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.DISPLAY_INDENT_APPROVAL_REGISTER,request,response);
		}catch(Exception e){
			e.printStackTrace();
			//NovarCommonUtils.RedirectURL(NovarRedirectPage.ERROR_PAGE,request,response);
		}
	}
/*	public void doPrintSalesEnquiryRegister(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		try{  
			HttpSession session=request.getSession();
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");

			int subdocumentId=Validator.convertToInteger(request.getParameter("subdocument_id"));
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);  
			String strSearchCriteria="";
			strSearchCriteria=getSearchCriteria(request);
			
			if(request.getSession().getAttribute("ser_rights")!=null){
				rights =(UserRights)request.getSession().getAttribute("ser_rights");
			}
			else{
				rights =objRight.getUserRights(SubdocumentId.INDENT_APPROVAL, userInfo);
				session.setAttribute("ser_rights",rights);		
			}
			
			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(subdocumentId, userInfo);
			
			PageConfig pc=null;
			pc=new PageConfig(request.getParameter("pageno"));
			pc.setPageCount(9999999);
			pc.setIEnd(9999999);
			request.setAttribute("pc", pc);
			
			ColumnPreference cp=new ColumnPreference();
			cp.setGroupPaging(false);
			DataList.setPaging(cp);
			
			
			IndentApprovalRegisterGridMaker objUtil=new IndentApprovalRegisterGridMaker();

			List<Map<String,String>> orders = objManager.getAllSalesEnquiryRegisterOnColumnMapping(pc,userInfo,strSearchCriteria,DataList,subdocumentId);
			String Grid = objUtil.formSalesEnquiryRegisterGrid(userInfo,DataList, orders, bundle,  rights);
			
			Map<String, String> objMap = new HashMap<String,String>();
		
			dynamicfields = soMan.getDynamicFields("3,4",userInfo,1);
			
			
			for(SalesOrder fields : dynamicfields){
				if("enq_no".equalsIgnoreCase(fields.getPageFieldName())){
					objMap.put(fields.getName(),request.getParameter("enq_no"));
				}
				if("party_id".equalsIgnoreCase(fields.getPageFieldName())){
					objMap.put(fields.getName(),request.getParameter("se_customer"));
				}
				if("group_id".equalsIgnoreCase(fields.getPageFieldName())){
					objMap.put(fields.getName(),request.getParameter("se_group"));
				}
				if("material_id".equalsIgnoreCase(fields.getPageFieldName())){
					objMap.put(fields.getName(),request.getParameter("se_material"));
				}
			}
			
			
			objMap.put("Sales Executive",request.getParameter("executive")!=null?request.getParameter("executive"):"");
			objMap.put("Message displayed in Enquiry",request.getParameter("se_remarks"));
			objMap.put("Memo for internal use",request.getParameter("se_internal_memo"));
			
			request.setAttribute("from_date", request.getParameter("se_from_date")); 
			request.setAttribute("to_date", request.getParameter("se_to_date")); 
			
			request.setAttribute("filter_list", objMap);
			
			request.setAttribute("print_grid", Grid); 

			request.setAttribute("subdocument_id", subdocumentId);  
			rights=null;
			cpm=null;
			DataList=null;
			pc=null;
			cp=null;
			objUtil=null;
			orders=null;
			Grid=null;
			objMap=null;
			dynamicfields=null;
			Grid=null;
			TPCSCommonUtil.RedirectURL(TPCSRedirectPage.PRINT_REPORT,request,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	

	
	
	public void doExportSalesEnquiryRegisterPdf(HttpServletRequest request,HttpServletResponse response){
		try
		{
			ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 
			HttpSession session=request.getSession();
			TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
			
			ExportToPdfTool.setRoundOff(userInfo);
			
		  	Map<Integer,String> subdocument=(Map<Integer,String>)session.getAttribute("subdocuments");
		  	int subdocumentId=Validator.convertToInteger(request.getParameter("subdocument_id"));
   			String screenName=subdocument.get(subdocumentId); 
			
			String strSearchCriteria="";
			strSearchCriteria=getSearchCriteria(request);
			
			if(request.getSession().getAttribute("ser_rights")!=null){
				rights =(UserRights)request.getSession().getAttribute("ser_rights");
			}
			else{
				rights =objRight.getUserRights(SubdocumentId.SALES_ENQUIRY, userInfo);
				session.setAttribute("ser_rights",rights);		
			}
			
			ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			ColumnPreference DataList=cpm.getVisibleColumnNames(subdocumentId, userInfo);
			
			PageConfig pc=null;
			pc=new PageConfig(request.getParameter("pageno"));
			pc.iStart=0;
			pc.setPageCount(9999999);
			pc.setIEnd(9999999);
			request.setAttribute("pc", pc);
			
			ColumnPreference objcp=new ColumnPreference();
			objcp.setGroupPaging(false);
			DataList.setPaging(objcp);
			List<Map<String,String>> data = objManager.getAllSalesEnquiryRegisterOnColumnMapping(pc,userInfo,strSearchCriteria,DataList,subdocumentId);
//			CompanyAndYearSelectionManager objCompany=new CompanyAndYearSelectionManager();
//			Company objCompanyDet = objCompany.getCompanyInfo(userInfo);




			Document document = new Document(PageSize.A4, 36, 36, 54, 36);
			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, pdfReport);
			TableHeader event = new TableHeader();
			writer.setPageEvent(event);
			
			float pageMarginLeft=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginLeft"));
			float pageMarginRight=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginRight"));
			float pageMarginTop=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginTop"));
			float pageMarginBottom=Validator.convertToFloat(bundle.getString("ExportToPdf.MarginBottom"));
			
//			Document document = new Document(PageSize.A4, pageMarginLeft, pageMarginRight, pageMarginTop, pageMarginBottom);
			Document document = new Document(PageSize.A4, pageMarginLeft, pageMarginRight, pageMarginTop, pageMarginBottom);
			ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, pdfReport);
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
			writer.setPageEvent(event);
	        
			document.open();

		        
			String fontName=bundle.getString("ExportToPdf.FontName");
			short compNameFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.CompanyNameFontSize"));
			short addFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.AddressFontSize"));
			short titleFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.TitleFontSize"));
			short headTableLblFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeadTableLblFontSize"));
			short headTableDataFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeadTableDataFontSize"));
			short headerFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.HeaderFontSize"));
			short dataFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.DataFontSize"));
			short tableWidthPer=(short)Validator.convertToInteger(bundle.getString("ExportToPdf.TableWidthPer"));

			FontFactory.registerDirectories();
			BaseColor baseColor=new BaseColor(0,0,0);
			Font compNameFont= FontFactory.getFont(fontName,compNameFontSize,Font.BOLD, baseColor);
			Font addFont= FontFactory.getFont(fontName,addFontSize,Font.NORMAL, baseColor);
			Font titleFont= FontFactory.getFont(fontName,titleFontSize,Font.BOLD, baseColor);

			Font headTableLblFont= FontFactory.getFont(fontName,headTableLblFontSize,Font.BOLD, baseColor);
			Font headTableDataFont= FontFactory.getFont(fontName,headTableDataFontSize,Font.NORMAL, baseColor);

			Font detTableHeaderFont= FontFactory.getFont(fontName,headerFontSize,Font.BOLD, baseColor);
			Font detTableDataFont= FontFactory.getFont(fontName,dataFontSize,Font.NORMAL, baseColor);

			PdfPTable companyTable=new PdfPTable(1);
			companyTable.setHeaderRows(1); //To repeat table header in every page
			//			 companyTable.setSpacingBefore(5f);
			//			 companyTable.setSpacingAfter(5f);
			companyTable.setWidthPercentage(tableWidthPer); 
			companyTable.getDefaultCell().setUseAscender(true);
			companyTable.getDefaultCell().setUseDescender(true);

			PdfPCell cell = new PdfPCell(new Phrase(userInfo.getCompanyName(), compNameFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setColspan(1);
			cell.setVerticalAlignment (Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.NO_BORDER);
			companyTable.addCell(cell);
			cell=null;
			
			String companyAddress=!userInfo.getCompanyAddress().isEmpty() && userInfo.getCompanyAddress()!=null ?userInfo.getCompanyAddress():""; 
			
			if(!companyAddress.isEmpty() ){
				cell = new PdfPCell(new Phrase(companyAddress, addFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setColspan(1);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
				cell.setBorder(Rectangle.NO_BORDER);
				companyTable.addCell(cell);
				cell=null;
			}

//			String address1=!objCompanyDet.getAddress1().isEmpty()?objCompanyDet.getAddress1():"";
//			String address2=!objCompanyDet.getAddress2().isEmpty()?objCompanyDet.getAddress2():"";
//			String address3=!objCompanyDet.getAddress3().isEmpty()?objCompanyDet.getAddress3():"";
//			String country=!objCompanyDet.getCountry().isEmpty()?objCompanyDet.getCountry():"";
//			String phoneNo=!objCompanyDet.getPhoneNo().isEmpty()?objCompanyDet.getPhoneNo():"";
//			String faxNo=!objCompanyDet.getFaxNo().isEmpty()?objCompanyDet.getFaxNo():"";
//			String emailId=!objCompanyDet.getEmailId().isEmpty()?objCompanyDet.getEmailId():"";
//			String taxRegNo1=!objCompanyDet.getTaxRegNo1().isEmpty()?objCompanyDet.getTaxRegNo1():"";
//			String taxRegNo2=!objCompanyDet.getTaxRegNo2().isEmpty()?objCompanyDet.getTaxRegNo2():"";
//
//			if(!address1.isEmpty() || !address2.isEmpty() || !address1.isEmpty() ){
//				cell = new PdfPCell(new Phrase(address1+" "+address2+" "+address3+" "+country+" "+phoneNo, addFont));
//				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				cell.setColspan(1);
//				cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
//				cell.setBorder(Rectangle.NO_BORDER);
//				companyTable.addCell(cell);
//			}
//
//			if(!faxNo.isEmpty() || !emailId.isEmpty()){
//				cell = new PdfPCell(new Phrase((!faxNo.isEmpty()?bundle.getString("Address.FaxNo")+faxNo:"")+(!emailId.isEmpty()?bundle.getString("Address.Email")+emailId:""), addFont));
//				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				cell.setColspan(1);
//				cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
//				cell.setBorder(Rectangle.NO_BORDER);
//				companyTable.addCell(cell);
//			}
//			if(!taxRegNo1.isEmpty() || !taxRegNo2.isEmpty()){
//				cell = new PdfPCell(new Phrase((!taxRegNo1.isEmpty()?bundle.getString("Address.TaxRegNo1")+taxRegNo1:"")+(!taxRegNo2.isEmpty()?bundle.getString("Address.TaxRegNo2")+taxRegNo2:""), addFont));
//				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				cell.setColspan(1);
//				cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
//				cell.setBorder(Rectangle.NO_BORDER);
//				companyTable.addCell(cell);
//			}

			
			String webLogo=new UserInfo().getSchemeSever(request)+userInfo.getLogoPath();
			
			File logoFile=new File(userInfo.getLogoFilePath());
			
			Image img=null;
			
			if(logoFile.isFile() && !userInfo.getLogoPath().isEmpty()){
				
				img = Image.getInstance(webLogo);
				img.setAlignment(Image.RIGHT);
			}
			
			webLogo=null;
			
			PdfPTable filterTable=new PdfPTable(6);
			//			 headerTable.setSpacingBefore(5f);
			//			 companyTable.setSpacingAfter(5f);
			filterTable.setWidthPercentage(tableWidthPer);
			filterTable.getDefaultCell().setUseAscender(true);
			filterTable.getDefaultCell().setUseDescender(true);

			
			dynamicfields = soMan.getDynamicFields("3,4",userInfo,1);
			
			int noOfCells=0;
			for(SalesOrder fields : dynamicfields){
				if("enq_no".equalsIgnoreCase(fields.getPageFieldName()) && !request.getParameter("enq_no").isEmpty() ){
					ExportToPdfTool.insertNoBorderCell(filterTable,fields.getName(), Element.ALIGN_LEFT, 1, headTableLblFont);
					ExportToPdfTool.insertNoBorderCell(filterTable,": "+request.getParameter("enq_no"), Element.ALIGN_LEFT, 1, headTableDataFont);
					noOfCells++;
				}
				if("party_id".equalsIgnoreCase(fields.getPageFieldName()) && !request.getParameter("se_customer").isEmpty() ){
					ExportToPdfTool.insertNoBorderCell(filterTable,fields.getName(), Element.ALIGN_LEFT, 1, headTableLblFont);
					ExportToPdfTool.insertNoBorderCell(filterTable,": "+request.getParameter("se_customer"), Element.ALIGN_LEFT, 1, headTableDataFont);
					noOfCells++;
				}
				if("group_id".equalsIgnoreCase(fields.getPageFieldName()) && !request.getParameter("se_group").isEmpty() ){
					ExportToPdfTool.insertNoBorderCell(filterTable,fields.getName(), Element.ALIGN_LEFT, 1, headTableLblFont);
					ExportToPdfTool.insertNoBorderCell(filterTable,": "+request.getParameter("se_group"), Element.ALIGN_LEFT, 1, headTableDataFont);
					noOfCells++;
				}
				if("material_id".equalsIgnoreCase(fields.getPageFieldName()) && !request.getParameter("se_material").isEmpty() ){
					ExportToPdfTool.insertNoBorderCell(filterTable,fields.getName(), Element.ALIGN_LEFT, 1, headTableLblFont);
					ExportToPdfTool.insertNoBorderCell(filterTable,": "+request.getParameter("se_material"), Element.ALIGN_LEFT, 1, headTableDataFont);
					noOfCells++;
				}
			}
			
			if(request.getParameter("executive")!=null && !request.getParameter("executive").isEmpty()) {
				ExportToPdfTool.insertNoBorderCell(filterTable,"Sales Executive", Element.ALIGN_LEFT, 1, headTableLblFont);
				ExportToPdfTool.insertNoBorderCell(filterTable,": "+request.getParameter("executive"), Element.ALIGN_LEFT, 1, headTableDataFont);	
				noOfCells++;
			}
			if(request.getParameter("se_remarks")!=null && !request.getParameter("se_remarks").isEmpty()) {
				ExportToPdfTool.insertNoBorderCell(filterTable,"Message displayed in Enquiry", Element.ALIGN_LEFT, 1, headTableLblFont);
				ExportToPdfTool.insertNoBorderCell(filterTable,": "+request.getParameter("se_remarks"), Element.ALIGN_LEFT, 1, headTableDataFont);
				noOfCells++;
			}
			if(request.getParameter("se_internal_memo")!=null && !request.getParameter("se_internal_memo").isEmpty()) {
				ExportToPdfTool.insertNoBorderCell(filterTable,"Memo for internal use", Element.ALIGN_LEFT, 1, headTableLblFont);
				ExportToPdfTool.insertNoBorderCell(filterTable,": "+request.getParameter("se_internal_memo"), Element.ALIGN_LEFT, 1, headTableDataFont);
				noOfCells++;
			}
			
			for(int n=noOfCells;n<=3;n++) {
				ExportToPdfTool.insertNoBorderCell(filterTable,"", Element.ALIGN_LEFT, 1, headTableLblFont);
				ExportToPdfTool.insertNoBorderCell(filterTable,"", Element.ALIGN_LEFT, 1, headTableDataFont);
			}
//			request.setAttribute("from_date", request.getParameter("se_from_date")); 
//			request.setAttribute("to_date", request.getParameter("se_to_date")); 
			
			
	
		//	document.add(headerTable);

			
			   int noOfColumn=0;
	       	  for(String detCol:DataList.getVisibleColumns()){
		    	   noOfColumn++;
		      }
	       	  
			
	       	int repeatHeaderRows=3;
	       	if(noOfCells>0)
			{
	       		repeatHeaderRows=4;
			}

			PdfPTable detailTable=new PdfPTable(noOfColumn);
			detailTable.setHeaderRows(repeatHeaderRows); //To repeat table header in every page
			//			table2.setSpacingBefore(10f);
			detailTable.setWidthPercentage(tableWidthPer);
			detailTable.getDefaultCell().setUseAscender(true);
			detailTable.getDefaultCell().setUseDescender(true);
		
			//detailTable.setWidthPercentage(100);
//			float[] columnWidths = new float[] {10f, 10f, 10f, 10f,10f,10f};
//			detailTable.setWidths(columnWidths);
			
			cell = new PdfPCell(); 
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			int logoColspan=3;
			if(noOfColumn<=7){
				logoColspan=2;
			}
			else if(noOfColumn>12){
				logoColspan=4;
			}
			
			if(logoFile.isFile() && img!=null){
				cell.setBorder(Rectangle.TOP | Rectangle.LEFT| Rectangle.BOTTOM);
				cell.setColspan(noOfColumn-logoColspan);	
				 cell.setBorderWidth(1f); 
			}
			else{
				
				cell.setColspan(noOfColumn);
			}
			
			cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
			cell.addElement(companyTable);
			cell.setBorderWidth(1f); 
			detailTable.addCell(cell);
			cell=null;
			
			
			if(logoFile.isFile() && img!=null){
				cell = new PdfPCell(); 
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
				cell.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);
				cell.addElement(img);
				cell.setColspan(logoColspan);
				 cell.setBorderWidth(1f); 
				detailTable.addCell(cell);
				cell=null;
			}
			logoFile=null;
			
			String fromDate=request.getParameter("se_from_date");
			String toDate=request.getParameter("se_to_date");
			if(!fromDate.isEmpty() || !toDate.isEmpty())
			{
				screenName=screenName+" between "+fromDate+" and "+toDate;
			}
			
			cell = new PdfPCell(new Phrase(screenName, titleFont)); 
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(noOfColumn);
			cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
			 cell.setBorderWidth(1f); 
			detailTable.addCell(cell);
			cell=null;
			
			
			if(noOfCells>0)
			{
				cell = new PdfPCell(); 
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment (Element.ALIGN_MIDDLE	);
				cell.setColspan(noOfColumn);
				cell.addElement(filterTable);
				 cell.setBorderWidth(1f); 
				detailTable.addCell(cell);
				cell=null;
			}
	
			
			  for(String column:DataList.getVisibleColumns()){
		    	   ExportToPdfTool.insertCell(detailTable, column, Element.ALIGN_LEFT, 1, detTableHeaderFont);	
		      }
			
			  
			  
			  
			  
			  //Data area




				 int groupCount=DataList.getGroupCnt();
				 List<String> totColor=new ArrayList<String>();

				 totColor.add("#"+bundle.getString("ExportToExcel.FistGroupRowColor"));
				 totColor.add("#"+bundle.getString("ExportToExcel.SecondGroupRowColor"));
				 totColor.add("#"+bundle.getString("ExportToExcel.ThirdGroupRowColor"));

				 totColor.add("#"+bundle.getString("ExportToExcel.FourthGroupRowColor"));

				 List<String> quantityCol=new ArrayList<String>();
				 quantityCol=DataList.getColumnsToTotal();

				 Map<String,Double> totalColumnMap=new LinkedHashMap<String,Double>();
				 Map<String,Double> grandTotalColumnMap=new LinkedHashMap<String,Double>();
				 Map<String,String> groupMap=new HashMap<String,String>();
				 for(String qCol:quantityCol){
					 for(int m=1;m<=groupCount;m++){
					 
						 totalColumnMap.put(qCol+m, (double)0.0f);
						 groupMap.put("group"+m, "");
					 }
					 grandTotalColumnMap.put(qCol, (double)0.0f);
					 
				 }
				 Map<Integer,Integer> groupTotalIndex=new LinkedHashMap<Integer,Integer>();
				 groupTotalIndex.put(1, 0);
				 groupTotalIndex.put(2, 0);
				 groupTotalIndex.put(3, 0);
				 DecimalFormat df = new DecimalFormat("#.##");

				 int noOfLines=1;
				 
				 for(int i=0,size=data.size();i<size;i++){ 


					 Map<String, String> map = data.get(i);

					 int l=1;
					 int cellNo=0;

					 for(ColumnPreference cp:DataList.getCpList()){
						 	ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
							if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
								ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
							}
							else if(cp.getDbFieldName().equalsIgnoreCase("price_fcy")){
								ExportToPdfTool.tempDf=ExportToPdfTool.priceDf;
							}
						 if(l<=groupCount+1){

							 cellNo=0;
							 for(int m=1;m<=groupCount;m++){
								
								 if(l==m ){

									 if(!groupMap.get("group"+m).equals(map.get(cp.getDbFieldName()))){

										 for(int c=1;c<=cellNo;c++){
									 		 ExportToPdfTool.insertCell(detailTable, "", Element.ALIGN_LEFT, 1, detTableDataFont);
									 	}
											ExportToPdfTool.insertCell(detailTable, map.get(cp.getDbFieldName()), Element.ALIGN_LEFT, (DataList.getCpList().size()-(m-1)), detTableDataFont);
									 }
									 groupMap.put("group"+m, map.get(cp.getDbFieldName()));
								 }
								 cellNo++;

							 }
							 if(l==groupCount+1){
							 	for(int c=1;c<=cellNo;c++){
							 		 ExportToPdfTool.insertCell(detailTable, "", Element.ALIGN_LEFT, 1, detTableDataFont);
							 	}
								

								 ExportToPdfTool.insertCell(detailTable, map.get(cp.getDbFieldName()), Element.ALIGN_LEFT, 1, detTableDataFont);
								 

							 }
						 }
						 else{
							 
							 if(quantityCol.contains(cp.getDbFieldName())){

								 for(String qCol:quantityCol){

									 if(cp.getDbFieldName().equals(qCol)){

										 for(int m=1;m<=groupCount;m++){
											 totalColumnMap.put(qCol+m, (totalColumnMap.get(qCol+m)+Validator.convertToDouble(map.get(cp.getDbFieldName()))));
										 }
										 grandTotalColumnMap.put(qCol, (grandTotalColumnMap.get(qCol)+Validator.convertToDouble(map.get(cp.getDbFieldName()))));
										 break;
									 }	  
								 }

								 ExportToPdfTool.insertCell(detailTable, ExportToPdfTool.tempDf.format(Validator.convertToDouble(map.get(cp.getDbFieldName()))), Element.ALIGN_LEFT, 1, detTableDataFont);
								 

							 }
							 else if(cp.getDataType()==6 ||cp.getDataType()==7  ){
									
								 ExportToPdfTool.insertCell(detailTable, ExportToPdfTool.tempDf.format(Validator.convertToDouble(map.get(cp.getDbFieldName()))), Element.ALIGN_LEFT, 1, detTableDataFont);
							}
							 else{

								 ExportToPdfTool.insertCell(detailTable, map.get(cp.getDbFieldName()), Element.ALIGN_LEFT, 1, detTableDataFont);

							 }
						 }
						 l++;
					 }

					
					 
					 
					 if(quantityCol.size()>0){
						 for(int m=groupCount;m>=1;m--){

							 if(data.size()==i+1){

								 ExportToPdfTool.writeGroupTotalPDF(bundle ,detailTable,DataList,quantityCol,totalColumnMap,totColor,df,m,i,data,detTableDataFont);

							 }
							 else if(!data.get(i+1).get(DataList.getCpList().get(m-1).getDbFieldName()).equals(data.get(i).get(DataList.getCpList().get(m-1).getDbFieldName()))){

								 groupTotalIndex.put(m, 1);

								 if(m<groupCount){

									 if(groupTotalIndex.get(m+1)==0){

										 ExportToPdfTool.writeGroupTotalPDF(bundle ,detailTable,DataList,quantityCol,totalColumnMap,totColor,df,m+1,i,data,detTableDataFont);
										 groupMap.put("group"+(m+1), "");

										 ExportToPdfTool.writeGroupTotalPDF(bundle ,detailTable,DataList,quantityCol,totalColumnMap,totColor,df,m,i,data,detTableDataFont);
									 }
									 else{

										 ExportToPdfTool.writeGroupTotalPDF(bundle ,detailTable,DataList,quantityCol,totalColumnMap,totColor,df,m,i,data,detTableDataFont);
									 }
								 }
								 else{

									 ExportToPdfTool.writeGroupTotalPDF(bundle ,detailTable,DataList,quantityCol,totalColumnMap,totColor,df,m,i,data,detTableDataFont);
								 }
							 }
							 if(m==1){

								 groupTotalIndex.put(1, 0);
								 groupTotalIndex.put(2, 0);
								 groupTotalIndex.put(3, 0);
							 }
						 }
						 if(data.size()==i+1){// && groupCount > 0){
							 
							 //Grand total row
							 int m=1;
							 ExportToPdfTool.writeGrandGroupTotalPDF(bundle ,detailTable,DataList,quantityCol,grandTotalColumnMap,totColor,df,m,i,data,detTableDataFont);
						 }

					 }

				 }

		   // ExportToPdfTool.writePdfFooterInReports(userInfo,bundle,detailTable,cell,detTableDataFont,noOfColumn);
			document.add(detailTable);

			document.close();

			byte[] pdfBytes = pdfReport.toByteArray();
			
			userInfo=null;
			subdocument=null;
			rights=null;
			cpm=null;
			DataList=null;
			pc=null;
			objcp=null;
			data=null;
			document=null;
			baseColor=null;
			companyTable=null;
			filterTable=null;
			dynamicfields=null;
			detailTable=null;
			totColor=null;
			quantityCol=null;
			totalColumnMap=null;
			grandTotalColumnMap=null;
			groupMap=null;
			groupTotalIndex=null;
			df=null;
			pdfReport=null;
			writer=null;
			event=null;
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\""+screenName+ new java.util.Date()+".pdf" + "\"");
			response.setContentLength(pdfBytes.length);
			response.getOutputStream().write(pdfBytes);
			response.getOutputStream().flush(); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	 public void doExportSalesEnquiryRegisterExcel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		 try{

			 HttpSession session=request.getSession();
			 TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");

			 int subdocumentId=Validator.convertToInteger(request.getParameter("subdocument_id"));

			 String strSearchCriteria="";
			 String reqType=request.getParameter("request_type");

			 if("Search".equalsIgnoreCase(reqType)){	       
				 strSearchCriteria=getSearchCriteria(request);
				 getAndSetAttributes(request);
			 }

			 ColumnPreferenceManager cpm=new ColumnPreferenceManager(); 
			 ColumnPreference DataList=cpm.getVisibleColumnNames(subdocumentId, userInfo);

			 PageConfig pc=null;
			 pc=new PageConfig(request.getParameter("pageno"));
			 pc.iStart=0;
			 pc.setPageCount(9999999);
			 pc.setIEnd(9999999);

			 ColumnPreference cp=new ColumnPreference();
			 cp.setGroupPaging(false);
			 DataList.setPaging(cp);


			 List<Map<String,String>> orders = objManager.getAllSalesEnquiryRegisterOnColumnMapping(pc,userInfo,strSearchCriteria,DataList,subdocumentId);

//			 CompanyAndYearSelectionManager objCompany = new CompanyAndYearSelectionManager();
//			 Company objCompanyDet = objCompany.getCompanyInfo(userInfo);

			 Map<Integer,String> subdocument=(Map<Integer,String>)session.getAttribute("subdocuments");
			 String screenName=subdocument.get(subdocumentId); 

			 

			 Map<String, String> objMap = new HashMap<String,String>();

			 dynamicfields = soMan.getDynamicFields("3,4",userInfo,1);


			 for(Indent fields : dynamicfields){
				 if("enq_no".equalsIgnoreCase(fields.getPageFieldName())){
					 objMap.put(fields.getName(),request.getParameter("enq_no"));
				 }
				 if("party_id".equalsIgnoreCase(fields.getPageFieldName())){
					 objMap.put(fields.getName(),request.getParameter("se_customer"));
				 }
				 if("group_id".equalsIgnoreCase(fields.getPageFieldName())){
					 objMap.put(fields.getName(),request.getParameter("se_group"));
				 }
				 if("material_id".equalsIgnoreCase(fields.getPageFieldName())){
					 objMap.put(fields.getName(),request.getParameter("se_material"));
				 }
			 }


			 objMap.put("Sales Executive",request.getParameter("executive"));
			 objMap.put("Message displayed in Enquiry",request.getParameter("se_remarks"));
			 objMap.put("Memo for internal use",request.getParameter("se_internal_memo"));
			 
			 String fromDate=request.getParameter("se_from_date");
			 String toDate=request.getParameter("se_to_date");
			 if(!fromDate.isEmpty() || !toDate.isEmpty() )
			 {
				 screenName=screenName+" between "+fromDate+" and "+toDate; 
			 }
				

			 createSalesEnquiryRegisterExcel(DataList, orders,screenName,objMap,request,response);

			 cpm=null;
			 DataList=null;
			 pc=null;
			 cp=null;
			 orders=null;
			 objMap=null;
			 dynamicfields=null;
		 }
		 catch (Exception ex){
			 ex.printStackTrace();
		 }
	}
	 @SuppressWarnings("deprecation")
	 public void createSalesEnquiryRegisterExcel(ColumnPreference DataList,List<Map<String,String>> data,String title, Map<String, String> objMap,HttpServletRequest request, HttpServletResponse response)throws IOException{

		 TPCSUser ui=new UserInfo().getUserInfo(request);
		 
		 ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request);

		 ServletOutputStream out = null;
		 response.setContentType("application/vnd.ms-excel");    
		 response.setHeader("Content-Disposition", "attachment; filename=\""+title+" "+ new java.util.Date()+".xls" + "\"");
		 out = response.getOutputStream();
		 try{

			 ExportToPdfTool.setRoundOff(ui);
			 
			 HSSFWorkbook hwb = new HSSFWorkbook();
			 HSSFSheet sheet = hwb.createSheet(title);

			 sheet.setDefaultRowHeight((short)(Validator.convertToInteger(bundle.getString("ExportToExcel.DefaultRowHeight"))));

			 String fontName=bundle.getString("ExportToExcel.FontName");
			 short compNameFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.CompanyNameFontSize"));
			 short addFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.AddressFontSize"));
			 short titleFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TitleFontSize"));
			 short filterFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.FilterFontSize"));
			 short headerFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.HeaderFontSize"));
			 short dataFontSize=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.DataFontSize"));
			 
			 HSSFCellStyle companyNameStyle =ExportToExcelTool.getBorderCompanyNameStyle(hwb,fontName,compNameFontSize);
			 HSSFCellStyle addressStyle =ExportToExcelTool.getBorderAddressStyle(hwb,fontName,addFontSize);
			 HSSFCellStyle titleStyle =ExportToExcelTool.getBorderTitleStyle(hwb,fontName,titleFontSize);
			 HSSFCellStyle filterStyle =ExportToExcelTool.getBorderFilterStyle(hwb,fontName,filterFontSize);
			 HSSFCellStyle filterValStyle =ExportToExcelTool.getBorderFilterValStyle(hwb,fontName,filterFontSize);
			 HSSFCellStyle headerStyle =ExportToExcelTool.getBorderHeaderStyle(hwb,fontName,headerFontSize);
			 DataFormat format = hwb.createDataFormat();
			 HSSFCellStyle dataStyle =ExportToExcelTool.getBorderDataStyle(hwb,fontName,dataFontSize);
			
			 HSSFCellStyle dataValStyle =ExportToExcelTool.getBorderDataStyle(hwb,fontName,dataFontSize);
			 dataValStyle.setDataFormat(format.getFormat(ExportToPdfTool.valFormat));
			 
			 HSSFCellStyle dataQtyStyle =ExportToExcelTool.getBorderDataStyle(hwb,fontName,dataFontSize);
			 dataQtyStyle.setDataFormat(format.getFormat(ExportToPdfTool.qtyFormat));
			 
			 HSSFCellStyle dataPriceStyle =ExportToExcelTool.getBorderDataStyle(hwb,fontName,dataFontSize);
			 dataPriceStyle.setDataFormat(format.getFormat(ExportToPdfTool.priceFormat));
			 
			 HSSFCellStyle dataWrapStyle =ExportToExcelTool.getBorderDataWrapStyle(hwb,fontName,dataFontSize);
//			 HSSFCellStyle dataNumberStyle =ExportToExcelTool.getBorderDataNumberStyle(hwb,fontName,dataFontSize);

			 int companyNameRow=Validator.convertToInteger(bundle.getString("ExportToExcel.CompanyNameRow"));
			 int addRow=Validator.convertToInteger(bundle.getString("ExportToExcel.AddressRow"));
			 int titleRow=Validator.convertToInteger(bundle.getString("ExportToExcel.TitleRow"));//address end row plus title row
			 int filterRow=Validator.convertToInteger(bundle.getString("ExportToExcel.FilterRow"));//title end row plus filter row
			 int headerRow=Validator.convertToInteger(bundle.getString("ExportToExcel.HeaderRow"));//filter end row plus header row
			 int dataRow=Validator.convertToInteger(bundle.getString("ExportToExcel.DataRow"));//header end row plus data row

			 int defRowHeightPoitsSubt=Validator.convertToInteger(bundle.getString("ExportToExcel.DefaultRowHeightPointsSubtract"));//subtract value for default row height points
			 
			 short wrapLineHeight=(short)(sheet.getDefaultRowHeightInPoints()-defRowHeightPoitsSubt);
			 short wrapCharLength=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.WrapCharLength"));
			 short wrapColWidth=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.WrapColWidth"));

			 short cNameRowHeight=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.CompNameRowHeight"));
			 short addRowHeight=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.AddressRowHeight"));
			 short titleRowHeight=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TitleRowHeight"));
			 short filterRowHeight=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.FilterRowHeight"));
			 short headerRowHeight=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.HeaderRowHeight"));

			 short compMergeStartCell=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.CompMergeStartCell"));
			 short compMergeEndCell=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.CompMergeEndCell"));
			 
			 short compLogoStartRow=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.LogoStartRow"));
			 short compLogoStartCell=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.LogoStartCell"));

			 short addMergeStartRow=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.AddressMergeStartRow"));
			 short addMergeEndRow=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.AddressMergeEndRow"));
			 
			 short addMergeStartCell=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.AddressMergeStartCell"));
			 short addMergeEndCell=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.AddressMergeEndCell"));

			 short titleMergeStartCell=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TitleMergeStartCell"));
			 short titleMergeEndCell=(short)Validator.convertToInteger(bundle.getString("ExportToExcel.TitleMergeEndCell"));

			 //Company name area

			 int rowNo=companyNameRow;

			 sheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,compMergeStartCell,compMergeEndCell));
			 HSSFRow row = sheet.createRow((short)rowNo++);
			 row.setHeight(cNameRowHeight);
			 HSSFCell cell = row.createCell(0);
			 cell.setCellValue(new HSSFRichTextString(ui.getCompanyName()));
			 cell.setCellStyle(companyNameStyle);
			 
			 

				
			 
			 //Address area

			 rowNo=rowNo+addRow;
			 
			 
			 
			 if(ui.getCompanyAddress()!=null && !ui.getCompanyAddress().isEmpty()){
				 sheet.addMergedRegion(new CellRangeAddress(addMergeStartRow,addMergeEndRow,addMergeStartCell,addMergeEndCell));			 
				 row = sheet.createRow((short)rowNo++);
				 row.setHeight(addRowHeight);
				 cell= row.createCell(0);
				 cell.setCellValue(new HSSFRichTextString(ui.getCompanyAddress()));
				 addressStyle.setWrapText(true);
				 cell.setCellStyle(addressStyle);
				 
				 rowNo++;
				 rowNo++;
				 rowNo++;
				 rowNo++;
			 }

//			 if(!objCompany.getAddress1().isEmpty()){
//				 sheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,addMergeStartCell,addMergeEndCell));			 
//				 row = sheet.createRow((short)rowNo++);
//				 row.setHeight(addRowHeight);
//				 cell= row.createCell(0);
//				 cell.setCellValue(new HSSFRichTextString(objCompany.getAddress1()));
//				 cell.setCellStyle(addressStyle);
//			 }
//
//			 if(!objCompany.getAddress2().isEmpty()){
//				 sheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,addMergeStartCell,addMergeEndCell));
//				 row = sheet.createRow((short)rowNo++);
//				 row.setHeight(addRowHeight);
//				 cell= row.createCell(0);
//				 cell.setCellValue(new HSSFRichTextString(objCompany.getAddress2()));
//				 cell.setCellStyle(addressStyle);
//			 }
//
//			 if(!objCompany.getAddress3().isEmpty()){
//				 sheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,addMergeStartCell,addMergeEndCell));
//				 row = sheet.createRow((short)rowNo++);
//				 row.setHeight(addRowHeight);
//				 cell= row.createCell(0);
//				 cell.setCellValue(new HSSFRichTextString(objCompany.getAddress3()));
//				 cell.setCellStyle(addressStyle);
//			 }
//
//			 if(!objCompany.getCountry().isEmpty()){
//				 sheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,addMergeStartCell,addMergeEndCell));
//				 row = sheet.createRow((short)rowNo++);
//				 row.setHeight(addRowHeight);
//				 cell= row.createCell(0);
//				 cell.setCellValue(new HSSFRichTextString(objCompany.getCountry()));
//				 cell.setCellStyle(addressStyle);
//			 }
//
//			 if(!objCompany.getPhoneNo().isEmpty()){
//				 sheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,addMergeStartCell,addMergeEndCell));
//				 row = sheet.createRow((short)rowNo++);
//				 row.setHeight(addRowHeight);
//				 cell= row.createCell(0);
//				 cell.setCellValue(new HSSFRichTextString("Phone: "+objCompany.getPhoneNo()));
//				 cell.setCellStyle(addressStyle);
//			 }
//			 if(!objCompany.getFaxNo().isEmpty() || !objCompany.getEmailId().isEmpty()){
//				 sheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,addMergeStartCell,addMergeEndCell));
//				 row = sheet.createRow((short)rowNo++);
//				 row.setHeight(addRowHeight);
//				 cell= row.createCell(0);
//				 String addRowText=!objCompany.getFaxNo().isEmpty()?"TIN No: "+objCompany.getFaxNo()+",":"";
//				 addRowText=!objCompany.getEmailId().isEmpty()?addRowText+" CST: "+objCompany.getEmailId():"";
//				 cell.setCellValue(new HSSFRichTextString(addRowText));
//				 cell.setCellStyle(addressStyle);
//			 }
//			 if(!objCompany.getTaxRegNo1().isEmpty() || !objCompany.getTaxRegNo2().isEmpty()){
//				 sheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,addMergeStartCell,addMergeEndCell));
//				 row = sheet.createRow((short)rowNo++);	 
//				 row.setHeight(addRowHeight);
//				 cell= row.createCell(0);
//				 String addRowText=!objCompany.getTaxRegNo1().isEmpty()?"TIN No: "+objCompany.getTaxRegNo1()+",":"";
//				 addRowText=!objCompany.getTaxRegNo2().isEmpty()?addRowText+" CST: "+objCompany.getTaxRegNo2():"";
//				 cell.setCellValue(new HSSFRichTextString(addRowText));
//				 cell.setCellStyle(addressStyle);
//			 }

			 //Title area

			 rowNo=rowNo+titleRow;

			 sheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,titleMergeStartCell,titleMergeEndCell));
			 row= sheet.createRow((short)rowNo++); 
			 row.setHeight(titleRowHeight);
			 cell= row.createCell(0);
			 cell.setCellValue(new HSSFRichTextString(title));
			 cell.setCellStyle(titleStyle);

			 
			 //Filter Area
			 rowNo=rowNo+filterRow;
			 row= sheet.createRow((short)rowNo++); 
			 row.setHeight(filterRowHeight);
		       int z=0;
		       int s=1;

		       		      for (Map.Entry<String, String> entry : objMap.entrySet())
		       		      {
		       		    	  if(entry.getValue()!=null && entry.getValue()!="" && !entry.getValue().equals("-1")){
		       		    	  cell= row.createCell(z);
		       			      cell.setCellValue(new HSSFRichTextString(entry.getKey()));//.setCellValue(title);
		       			      cell.setCellStyle(filterStyle);
		       			      cell= row.createCell(s);
		       			      cell.setCellValue(new HSSFRichTextString(entry.getValue()));//.setCellValue(title);
		       			      cell.setCellStyle(filterValStyle);
		       			      z=z+2;
		       			      s=s+2;
		       		    	  }
		       		      }

		       		      
			 //Header area 

			 rowNo=rowNo+headerRow;

			 int k=0;
			 row= sheet.createRow((short)rowNo++); 
			 row.setHeight(headerRowHeight);


			 for(String column:DataList.getVisibleColumns()){

				 cell=row.createCell(k++);
				 cell.setCellValue(new HSSFRichTextString(column));
				 cell.setCellStyle(headerStyle); 

			 }

			 //Data area

			 rowNo=rowNo+dataRow;


			 List<Short> resizedColumnList=new ArrayList<Short>();
			 Map<Integer,Integer> resizedRowList=new HashMap<Integer,Integer>();

			 int groupCount=DataList.getGroupCnt();
			 List<String> totColor=new ArrayList<String>();

			 totColor.add("#"+bundle.getString("ExportToExcel.FistGroupRowColor"));
			 totColor.add("#"+bundle.getString("ExportToExcel.SecondGroupRowColor"));
			 totColor.add("#"+bundle.getString("ExportToExcel.ThirdGroupRowColor"));

			 totColor.add("#"+bundle.getString("ExportToExcel.FourthGroupRowColor"));

			 List<String> quantityCol=new ArrayList<String>();
			 quantityCol=DataList.getColumnsToTotal();

			 Map<String,Double> totalColumnMap=new LinkedHashMap<String,Double>();
			 Map<String,Double> grandTotalColumnMap=new LinkedHashMap<String,Double>();
			 //Map<String,Float> avgColumnMap=new LinkedHashMap<String,Float>();
			 //Map<String,Integer> avgColumnCountMap=new LinkedHashMap<String,Integer>();
			 Map<String,String> groupMap=new HashMap<String,String>();
			 for(String qCol:quantityCol){
				 for(int m=1;m<=groupCount;m++){
					 totalColumnMap.put(qCol+m, (double) 0.0f);
					 groupMap.put("group"+m, "");
				 }
				 grandTotalColumnMap.put(qCol, (double) 0.0f);

				 
			 }
			 Map<Integer,Integer> groupTotalIndex=new LinkedHashMap<Integer,Integer>();
			 groupTotalIndex.put(1, 0);
			 groupTotalIndex.put(2, 0);
			 groupTotalIndex.put(3, 0);
			 DecimalFormat df = new DecimalFormat("#.##");

			 int noOfLines=1;
			 
			 for(int i=0,size=data.size();i<size;i++){ 

				 row = sheet.createRow((short)rowNo++);
				 //row.setHeight((short)dataRowHeight);
				 //row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

				 Map<String, String> map = data.get(i);

				 int l=1;
				 int cellNo=0;

				 for(ColumnPreference cp:DataList.getCpList()){
					 HSSFCellStyle tmpStyle = hwb.createCellStyle();
					 tmpStyle=dataValStyle;
					 
					 ExportToPdfTool.tempDf=ExportToPdfTool.valDf;
						if(cp.getDbFieldName().equalsIgnoreCase("quantity")){
							ExportToPdfTool.tempDf=ExportToPdfTool.qtyDf;
							tmpStyle=dataQtyStyle;
						}
						else if(cp.getDbFieldName().equalsIgnoreCase("price_fcy")){
							ExportToPdfTool.tempDf=ExportToPdfTool.priceDf;
							tmpStyle=dataPriceStyle;
						}
					 if(l<=groupCount+1){

						 cellNo=0;

						 for(int m=1;m<=groupCount;m++){

							 if(l==m ){

								 if(!groupMap.get("group"+m).equals(map.get(cp.getDbFieldName()))){
									 sheet.addMergedRegion(new CellRangeAddress((rowNo-1),(rowNo-1),(m-1),(DataList.getCpList().size()-1) ));

									 cell=row.createCell(cellNo++);
									 cell.setCellValue(new HSSFRichTextString(map.get(cp.getDbFieldName())));
									 //cell.setCellStyle(dataStyle); 
									 if(map.get(cp.getDbFieldName()).length()>wrapCharLength){

										 cell.setCellStyle(dataWrapStyle);
										 
										 noOfLines=(map.get(cp.getDbFieldName()).length()/wrapCharLength) < TPCSCommonUtil.countLines(map.get(cp.getDbFieldName())) ? TPCSCommonUtil.countLines(map.get(cp.getDbFieldName())) : (map.get(cp.getDbFieldName()).length()/wrapCharLength);
										 
										 if(resizedRowList.get(rowNo-1)==null || resizedRowList.get(rowNo-1)< noOfLines){
											 row.setHeightInPoints((short)(noOfLines*wrapLineHeight));
											 resizedRowList.put((rowNo-1),noOfLines);
										 }
										 sheet.setColumnWidth(cellNo-1, ((short)wrapCharLength*wrapColWidth));
										 resizedColumnList.add((short) ((short)cellNo-1));
										 //resizedRowList.add((short)rowNo);
									 }
									 else{

										 cell.setCellStyle(dataStyle);
										 //sheet.autoSizeColumn((short)s-1);
									 }

									 row = sheet.createRow((short)rowNo++);
									 //row.setHeight((short)dataRowHeight);
									 //row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));
								 }
								 groupMap.put("group"+m, map.get(cp.getDbFieldName()));

							 }

							 cell=row.createCell(cellNo++);
							 cell.setCellStyle(dataStyle); 

						 }
						 if(l==groupCount+1){


							 cell=row.createCell(cellNo++);
							 cell.setCellValue(new HSSFRichTextString(map.get(cp.getDbFieldName())));

							 if(map.get(cp.getDbFieldName()).length()>wrapCharLength){
								 cell.setCellStyle(dataWrapStyle);

								 noOfLines=(map.get(cp.getDbFieldName()).length()/wrapCharLength) < TPCSCommonUtil.countLines(map.get(cp.getDbFieldName())) ? TPCSCommonUtil.countLines(map.get(cp.getDbFieldName())) : (map.get(cp.getDbFieldName()).length()/wrapCharLength);
								 
								 if(resizedRowList.get(rowNo-1)==null || resizedRowList.get(rowNo-1)< noOfLines){
									 row.setHeightInPoints((short)(noOfLines*wrapLineHeight));
									 resizedRowList.put((rowNo-1),noOfLines);
								 }
								 sheet.setColumnWidth(cellNo-1, ((short)wrapCharLength*wrapColWidth));
								 resizedColumnList.add((short) ((short)cellNo-1));
								 //resizedRowList.add((short)rowNo);
							 }
							 else{
								 cell.setCellStyle(dataStyle);
								 //sheet.autoSizeColumn((short)s-1);
							 }

						 }
					 }
					 else{
						 if(quantityCol.contains(cp.getDbFieldName())){

							 for(String qCol:quantityCol){

								 if(cp.getDbFieldName().equals(qCol)){

									 for(int m=1;m<=groupCount;m++){
										 totalColumnMap.put(qCol+m, (totalColumnMap.get(qCol+m)+Validator.convertToDouble(map.get(cp.getDbFieldName()))));
									 }
									 grandTotalColumnMap.put(qCol, (grandTotalColumnMap.get(qCol)+Validator.convertToDouble(map.get(cp.getDbFieldName()))));
									 break;
								 }	  
							 }

							 cell=row.createCell(cellNo++);
							 cell.setCellValue(new Double(ExportToPdfTool.tempDf.format(Validator.convertToDouble(map.get(cp.getDbFieldName())))));
							 cell.setCellStyle(tmpStyle);

						 }
						 else if(cp.getDataType()==6 ||cp.getDataType()==7  ){
							 
							 cell=row.createCell(cellNo++);
							 cell.setCellValue(new Double(ExportToPdfTool.tempDf.format(Validator.convertToDouble(map.get(cp.getDbFieldName())))));
							 cell.setCellStyle(tmpStyle);

						 }
						 else{


							 cell=row.createCell(cellNo++);
							 cell.setCellValue(new HSSFRichTextString(map.get(cp.getDbFieldName())));
							 if(map.get(cp.getDbFieldName()).length()>wrapCharLength){

								 cell.setCellStyle(dataWrapStyle);
								 
								 noOfLines=(map.get(cp.getDbFieldName()).length()/wrapCharLength) < TPCSCommonUtil.countLines(map.get(cp.getDbFieldName())) ? TPCSCommonUtil.countLines(map.get(cp.getDbFieldName())) : (map.get(cp.getDbFieldName()).length()/wrapCharLength);
								 
								 if(resizedRowList.get(rowNo-1)==null || resizedRowList.get(rowNo-1)< noOfLines){
									 row.setHeightInPoints((short)(noOfLines*wrapLineHeight));
									 resizedRowList.put((rowNo-1),noOfLines);
								 }
								 sheet.setColumnWidth(cellNo-1, ((short)wrapCharLength*wrapColWidth));
								
								 resizedColumnList.add((short) ((short)cellNo-1));
								 //resizedRowList.add((short)rowNo);
							 }
							 else{

								 cell.setCellStyle(dataStyle);
								 //sheet.autoSizeColumn((short)s-1);
							 }

						 }
					 }
					 l++;
				 }

				 if(quantityCol.size()>0){
					 for(int m=groupCount;m>=1;m--){

						 if(data.size()==i+1){

							 row = sheet.createRow((short)rowNo++);
							 //row.setHeight((short)dataRowHeight);
							 //row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));
							 new ExcelExporter().writeGroupTotal(bundle ,hwb,dataStyle,row,DataList,quantityCol,totalColumnMap,totColor,df,m,i,data);

						 }
						 else if(!data.get(i+1).get(DataList.getCpList().get(m-1).getDbFieldName()).equals(data.get(i).get(DataList.getCpList().get(m-1).getDbFieldName()))){

							 groupTotalIndex.put(m, 1);

							 if(m<groupCount){

								 if(groupTotalIndex.get(m+1)==0){

									 row = sheet.createRow((short)rowNo++);
									 //row.setHeight((short)dataRowHeight);
									 //row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

									 ExcelExporter.writeGroupTotal(bundle ,hwb,dataStyle,row,DataList,quantityCol,totalColumnMap,totColor,df,m+1,i,data);
									 groupMap.put("group"+(m+1), "");

									 row = sheet.createRow((short)rowNo++);
									 //row.setHeight((short)dataRowHeight);
									 //row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

									 ExcelExporter.writeGroupTotal(bundle ,hwb,dataStyle,row,DataList,quantityCol,totalColumnMap,totColor,df,m,i,data);
								 }
								 else{

									 row = sheet.createRow((short)rowNo++);
									 //row.setHeight((short)dataRowHeight);
									 //row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));
									 ExcelExporter.writeGroupTotal(bundle ,hwb,dataStyle,row,DataList,quantityCol,totalColumnMap,totColor,df,m,i,data);
								 }
							 }
							 else{

								 row = sheet.createRow((short)rowNo++);
								 //row.setHeight((short)dataRowHeight);
								 //row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));
								 ExcelExporter.writeGroupTotal(bundle ,hwb,dataStyle,row,DataList,quantityCol,totalColumnMap,totColor,df,m,i,data);
							 }
						 }
						 if(m==1){

							 groupTotalIndex.put(1, 0);
							 groupTotalIndex.put(2, 0);
							 groupTotalIndex.put(3, 0);
						 }
					 }
					 if(data.size()==i+1){// && groupCount > 0){
						 
						 //Grand total row
						 row = sheet.createRow((short)rowNo++);
						 //row.setHeight((short)dataRowHeight);
						 //row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));
						 int m=1;
						 ExcelExporter.writeGrandGroupTotal(bundle ,hwb,dataStyle,row,DataList,quantityCol,grandTotalColumnMap,totColor,df,m,i,data);
					 }

				 }

			 }

			 for(int re=0;data.size()>0 && re<data.get(0).size();re++){

				 if(!resizedColumnList.contains((short)re)){

					 sheet.autoSizeColumn((short)re);
				 }
			 }
			 
			//logo area

//			 sheet.addMergedRegion(new CellRangeAddress(compLogoStartRow,4,compLogoStartCell,8));

			 HSSFPatriarch patriarch=sheet.createDrawingPatriarch();

			 ExcelExporter exporter=new ExcelExporter();
			 
			 if(ManageFile.isfileExist(ui.getLogoFilePath())){
				 
				 exporter.addPictureToExcel(ui.getLogoFilePath(), hwb,sheet, patriarch, compLogoStartRow, compLogoStartCell);
			 }
			 
			 //			 for(int re=0;re<rowNo;re++){
			 ////				 if(!resizedRowList.contains((short)re)){
			 ////					 sheet.setDefaultRowHeightInPoints(sheet.getRow(re));
			 //					 row=sheet.getRow(re);
			 //					 row.setHeight((short)(sheet.getRow(re).getHeight()+75));
			 ////				 }
			 //			 }


			 hwb.write(out);
			 
			 ui=null;
			 hwb=null;
			 resizedColumnList=null;
			 resizedRowList=null;
			 totColor=null;
			 quantityCol=null;
			 totalColumnMap=null;
			 grandTotalColumnMap=null;
			 groupMap=null;
			 groupTotalIndex=null;
			 df=null;
			 exporter=null;
		 }catch(FileNotFoundException fnfe){
			 fnfe.printStackTrace();
		 }catch (Exception ex)
		 {ex.printStackTrace();}finally{}
	 }
	 
	
	 
	 */
	
	 
	  
	public void getAndSetAttributes(HttpServletRequest request){
		request.setAttribute("page_size", PageConfig.iPageSize);
		request.setAttribute("supplier_name",request.getParameter("supplier_name"));
		request.setAttribute("supplier_id",request.getParameter("supplier_id"));
		request.setAttribute("variant_name",request.getParameter("variant_name"));
		request.setAttribute("variant_id",request.getParameter("variant_id"));
		request.setAttribute("uom",request.getParameter("uom"));

		
	}
	public String getSearchCriteria(HttpServletRequest request){

		TPCSUser ui=new UserInfo().getUserInfo(request);
		StringBuffer  strQuery=new StringBuffer("");
//		String supplierName= request.getParameter("supplier_name");
		int supplierId=Validator.convertToInteger(request.getParameter("supplier_id"));
		int variantId=Validator.convertToInteger(request.getParameter("variant_id"));
//		String variantName=request.getParameter("variant_name");
		String uom= request.getParameter("uom");
			
		
		if(supplierId>0){
			strQuery.append(" and p.party_id="+supplierId);
		}
		if(variantId>0){
			strQuery.append(" and inddet.variant_id="+variantId);
		}
		if(uom!=null && uom.length()>0){
			strQuery.append(" and inddet.uom LIKE '%"+uom.replace("'","''")+"%'");
		}
			
		ui=null;
		return strQuery.toString();

	}			  

	private UserRights rights = null;
	private UserRightsManager objRight = new UserRightsManager();	
	public static IndentApprovalRegisterManager objManager=new IndentApprovalRegisterManager();
	private IndentManager soMan=new IndentManager();
	private List<Indent> dynamicfields = null;
	
 

}