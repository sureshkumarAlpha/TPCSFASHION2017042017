package com.alpha.tpcsfashion.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alpha.tpcsfashion.beans.CommonUtil;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.model.CommonUtilManager;
import com.alpha.tpcsfashion.util.NumericFormat;
import com.alpha.tpcsfashion.util.UserInfo;
import com.alpha.tpcsfashion.util.Validator;

public class CommonUtilServlet {

	public void doGetAutoSlNo(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try{
			TPCSUser ui=new UserInfo().getUserInfo(request);

			String pagePrefixId=request.getParameter("page_prefix_id");
			String pageAutoNoId=request.getParameter("page_auton_no_id");
			String headerNoColumn=request.getParameter("auto_header_no_column");
			String headerIdColumn=request.getParameter("auto_header_id_column");
			String headerTableName=request.getParameter("auto_header_table_name");
			String autoNoTableName=request.getParameter("auto_autono_table_name");

			CommonUtil comUtil=new CommonUtil();
			comUtil.setPagePrefixId(pagePrefixId);
			comUtil.setPageAutoNoId(pageAutoNoId);
			comUtil.setHeaderNoColumn(headerNoColumn);
			comUtil.setHeaderIdColumn(headerIdColumn);
			comUtil.setHeaderTableName(headerTableName);
			comUtil.setAutoNoTableName(autoNoTableName);

			String prefix=request.getParameter("prefix");
			String strTechDetails=comMan.doGetAutoSlNo(ui,prefix,comUtil);
			response.setContentType("text/xml");
			response.getWriter().write(strTechDetails);
			
			prefix=null;
			strTechDetails=null;
			
			pagePrefixId=null;
			pageAutoNoId=null;
			headerNoColumn=null;
			headerIdColumn=null;
			headerTableName=null;
			autoNoTableName=null;
			
			comUtil=null;
			ui=null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doGetAutoNoData(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try{

			TPCSUser ui=new UserInfo().getUserInfo(request);

			String pagePrefixId=request.getParameter("page_prefix_id");
			String pageAutoNoId=request.getParameter("page_auton_no_id");
			String headerNoColumn=request.getParameter("auto_header_no_column");
			String headerIdColumn=request.getParameter("auto_header_id_column");
			String headerTypeValue=request.getParameter("auto_header_type_value");
			String headerTypeColumn=request.getParameter("auto_header_type_column");
			String headerTableName=request.getParameter("auto_header_table_name");
			String autoNoTableName=request.getParameter("auto_autono_table_name");

			CommonUtil comUtil=new CommonUtil();
			comUtil.setPagePrefixId(pagePrefixId);
			comUtil.setPageAutoNoId(pageAutoNoId);
			comUtil.setHeaderNoColumn(headerNoColumn);
			comUtil.setHeaderIdColumn(headerIdColumn);
			comUtil.setHeaderTypeColumn(headerTypeColumn);
			comUtil.setHeaderTypeValue(headerTypeValue);
			comUtil.setHeaderTableName(headerTableName);
			comUtil.setAutoNoTableName(autoNoTableName);


			String strTechDetails=comMan.getAutoNoData(ui,comUtil);
			response.setContentType("text/xml");
			response.getWriter().write(strTechDetails);
			
			pagePrefixId=null;
			pageAutoNoId=null;
			headerNoColumn=null;
			headerIdColumn=null;
			headerTableName=null;
			autoNoTableName=null;
			headerTypeColumn=null;
			headerTypeValue=null;
			
			ui=null;
			strTechDetails=null;
			comUtil=null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doSaveAutoNo(HttpServletRequest request , HttpServletResponse response){
		try {
			TPCSUser ui=new UserInfo().getUserInfo(request);


			String pagePrefixId=request.getParameter("page_prefix_id");
			String pageAutoNoId=request.getParameter("page_auton_no_id");
			String headerNoColumn=request.getParameter("auto_header_no_column");
			String headerIdColumn=request.getParameter("auto_header_id_column");
			String headerTypeValue=request.getParameter("auto_header_type_value");
			String headerTypeColumn=request.getParameter("auto_header_type_column");
			String headerTableName=request.getParameter("auto_header_table_name");
			String autoNoTableName=request.getParameter("auto_autono_table_name");

			CommonUtil comUtil=new CommonUtil();
			comUtil.setPagePrefixId(pagePrefixId);
			comUtil.setPageAutoNoId(pageAutoNoId);
			comUtil.setHeaderNoColumn(headerNoColumn);
			comUtil.setHeaderIdColumn(headerIdColumn);
			comUtil.setHeaderTypeColumn(headerTypeColumn);
			comUtil.setHeaderTypeValue(headerTypeValue);
			comUtil.setHeaderTableName(headerTableName);
			comUtil.setAutoNoTableName(autoNoTableName);


			String deletePrefix=request.getParameter("delete_prefix");
//			System.out.println("deletePrefix :"+deletePrefix);
			List<String> deletePrefixList=new ArrayList<String>();
			if(!deletePrefix.isEmpty()){
				String[] arDeletePrefix=deletePrefix.split(",");
				deletePrefixList=Arrays.asList(arDeletePrefix);
			}


			int commonSlno=Validator.convertToInteger(request.getParameter("common_slno"));


			String[] arAllPrefix=request.getParameterValues("all_prefix[]");


			List<String> prefixList=null;
			if(arAllPrefix!=null){
				prefixList=new ArrayList<String>(Arrays.asList(arAllPrefix));
				//					prefixList.removeAll(Collections.singleton(""));
			}

			String[] arSepSlno=request.getParameterValues("all_sep_slno[]");
			List<String> slnoList=null;
			if(arSepSlno!=null){
				slnoList=new ArrayList<String>(Arrays.asList(arSepSlno));	
			}


			boolean isCommonSlno=request.getParameter("is_common").equals("1")?true:false;
			boolean isSepSlno=request.getParameter("is_seperate").equals("1")?true:false;
			boolean isManSlno=request.getParameter("is_manual").equals("1")?true:false;

			comUtil.setPrefixList(prefixList);
			comUtil.setCommonNo(commonSlno);
			comUtil.setSlnoList(slnoList);
			comUtil.setCommonSlno(isCommonSlno);
			comUtil.setSepSlno(isSepSlno);
			comUtil.setManSlno(isManSlno);
			comUtil.setDeletePrefixList(deletePrefixList);
			deletePrefixList=null;
			
			comUtil=comMan.saveAutoNo(comUtil,ui);

			response.setContentType("text/xml");
			response.getWriter().write(comUtil.getAutoNoString());

			pagePrefixId=null;
			pageAutoNoId=null;
			headerNoColumn=null;
			headerIdColumn=null;
			headerTableName=null;
			autoNoTableName=null;
			headerTypeColumn=null;
			headerTypeValue=null;
			
			ui=null;
			slnoList=null;
			prefixList=null;
			arAllPrefix=null;
			comUtil=null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doCalculateTax(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try{

			TPCSUser ui=new UserInfo().getUserInfo(request);
			NumericFormat.setRoundOff(ui);

			CommonUtil comUtil=new CommonUtil();
			
			String discount=Validator.convertToFloat(request.getParameter("discount_percent"))>0?request.getParameter("discount_percent"):"0";

			List<CommonUtil> comUtilList=new ArrayList<CommonUtil>();

			CommonUtil cu=new CommonUtil();
			cu.setTaxGroupId(Validator.convertToInteger(request.getParameter("tax_group_id_0")));
			cu.setQuantity(request.getParameter("qty_0"));
			cu.setPriceFcy(request.getParameter("price_fcy_0"));
			cu.setDiscountPercent(request.getParameter("discount_percent"));
			comUtilList.add(cu);
			cu=null;

			String detIds=request.getParameter("det_ids");
			if(!detIds.isEmpty()){
//				detIds=detIds.substring(0,detIds.length()-1);
				String[] arDetId=detIds.split(",");
				List<String> detIdList=Arrays.asList(arDetId);
				arDetId=null;
				

				for(String detId:detIdList){
					cu=new CommonUtil();

					cu.setTaxGroupId(Validator.convertToInteger(request.getParameter("tax_group_id_"+detId)));
					cu.setQuantity(request.getParameter("qty_"+detId));
					cu.setPriceFcy(request.getParameter("price_fcy_"+detId));
					cu.setDiscountPercent(request.getParameter("discount_percent_"+detId));
					comUtilList.add(cu);
					
					cu=null;
				}
			}

			comUtil.setComUtilList(comUtilList);
			comUtil.setDiscountPercent(discount);
			
			String strTechDetails=comMan.getTaxDetail(ui,comUtil);
			response.setContentType("text/xml");
			response.getWriter().write(strTechDetails);
			ui=null;
			strTechDetails=null;
			comUtilList=null;
			comUtil=null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private CommonUtilManager comMan=new CommonUtilManager();
}
