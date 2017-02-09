package com.alpha.tpcsfashion.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alpha.tpcsfashion.autocomplete.AutoCompleteServlet;
import com.alpha.tpcsfashion.servlet.ApprovedPriceListServlet;
import com.alpha.tpcsfashion.servlet.BOMServlet;
import com.alpha.tpcsfashion.servlet.BuyerOrderServlet;
import com.alpha.tpcsfashion.servlet.ColumnPreferenceServlet;
import com.alpha.tpcsfashion.servlet.CommonUtilServlet;
import com.alpha.tpcsfashion.servlet.CompanyAndYearSelectionServlet;
import com.alpha.tpcsfashion.servlet.CurrencyServlet;
import com.alpha.tpcsfashion.servlet.CustomerServlet;
import com.alpha.tpcsfashion.servlet.CustomizeReportServlet;
import com.alpha.tpcsfashion.servlet.DynamicOrderConfig;
import com.alpha.tpcsfashion.servlet.EntityServlet;
import com.alpha.tpcsfashion.servlet.GroupMasterServlet;
import com.alpha.tpcsfashion.servlet.HomePageServlet;
import com.alpha.tpcsfashion.servlet.IndentApprovalRegisterServlet;
import com.alpha.tpcsfashion.servlet.IndentServlet;
import com.alpha.tpcsfashion.servlet.MaterialMasterServlet;
import com.alpha.tpcsfashion.servlet.OperationOrStageServlet;
import com.alpha.tpcsfashion.servlet.ProfileServlet;
import com.alpha.tpcsfashion.servlet.PurchaseOrderServlet;
import com.alpha.tpcsfashion.servlet.SeasonServlet;
import com.alpha.tpcsfashion.servlet.SizeMappingServlet;
import com.alpha.tpcsfashion.servlet.SizeRangeServlet;
import com.alpha.tpcsfashion.servlet.SpecificationSettingServlet;
import com.alpha.tpcsfashion.servlet.TaxGroupServlet;
import com.alpha.tpcsfashion.servlet.UserServlet;
import com.alpha.tpcsfashion.servlet.VariantServlet;
import com.alpha.tpcsfashion.servlet.WarehouseMasterServlet;
import com.alpha.tpcsfashion.util.TPCSCommonUtil;


public class InvokerClass {

	public void doProcess(HttpServletRequest request, HttpServletResponse response,String strControllerName, String strMethodName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException{
		try{
			
			Method  set [] =null;
			if(TPCSCommonUtil.HOME_PAGE.equalsIgnoreCase(strControllerName)){	
				HomePageServlet homePage  = new HomePageServlet();
				set = HomePageServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)homePage,strMethodName);
			}
			else if(TPCSCommonUtil.COMPANY_YEAR_SELECTION.equalsIgnoreCase(strControllerName)){	
				CompanyAndYearSelectionServlet homePage  = new CompanyAndYearSelectionServlet();
				set = CompanyAndYearSelectionServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)homePage,strMethodName);
			}
			else if(TPCSCommonUtil.ENTITY_RIGHTS_SERVLET.equalsIgnoreCase(strControllerName)){
				EntityServlet objServlet = new EntityServlet();
				set = EntityServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.PROFILE_SERVLET.equalsIgnoreCase(strControllerName)){
				ProfileServlet objServlet = new ProfileServlet();
				set = ProfileServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.USER_SERVLET.equalsIgnoreCase(strControllerName)){
				UserServlet objServlet = new UserServlet();
				set = UserServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.JENIXCLOUD_AUTOCOMPLETE_SERVLET.equalsIgnoreCase(strControllerName)){
				AutoCompleteServlet objServlet= new AutoCompleteServlet();
				set = AutoCompleteServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
				objServlet=null;
			}
			else if(TPCSCommonUtil.COLUM_PREFERENCES_SERVLET.equalsIgnoreCase(strControllerName)){
				ColumnPreferenceServlet objServlet = new ColumnPreferenceServlet();
				set = ColumnPreferenceServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.GROUP_MASTER_SERVLET.equalsIgnoreCase(strControllerName)){
				GroupMasterServlet gms= new GroupMasterServlet();
				set = GroupMasterServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)gms,strMethodName);
			}
			else if(TPCSCommonUtil.MATERIAL_MASTER_SERVLET.equalsIgnoreCase(strControllerName)){
				MaterialMasterServlet objServlet = new MaterialMasterServlet();
				set = MaterialMasterServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.TAX_GROUP_SERVLET.equalsIgnoreCase(strControllerName)){
				TaxGroupServlet objServlet= new TaxGroupServlet();
				set = TaxGroupServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.CUSTOMER_SERVLET.equalsIgnoreCase(strControllerName)){
				CustomerServlet objServlet = new CustomerServlet();
				set = CustomerServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.DYNAMIC_ORDER.equalsIgnoreCase(strControllerName)){
				DynamicOrderConfig objHome  = new DynamicOrderConfig();
				set = DynamicOrderConfig.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objHome,strMethodName);
			}
			else if(TPCSCommonUtil.SPECIFICATION_SETTING.equalsIgnoreCase(strControllerName)){
				SpecificationSettingServlet objServlet  = new SpecificationSettingServlet();
				set = SpecificationSettingServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.OPERATION_STAGE.equalsIgnoreCase(strControllerName)){
				OperationOrStageServlet objServlet  = new OperationOrStageServlet();
				set = OperationOrStageServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.APPROVED_PRICELIST_SERVLET.equalsIgnoreCase(strControllerName)){
				ApprovedPriceListServlet objServlet  = new ApprovedPriceListServlet();
				set = ApprovedPriceListServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.MASTER_PRODUCTS_VARIANT.equalsIgnoreCase(strControllerName)){
				VariantServlet objServlet  = new VariantServlet();
				set = VariantServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.WAREHOUSE.equalsIgnoreCase(strControllerName)){
				WarehouseMasterServlet objServlet  = new WarehouseMasterServlet();
				set = WarehouseMasterServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}else if(TPCSCommonUtil.BOM_PAGE.equalsIgnoreCase(strControllerName)){
				BOMServlet objServlet = new BOMServlet();
				set = BOMServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
				objServlet=null;
			}
			else if(TPCSCommonUtil.OPERATION_STAGE.equalsIgnoreCase(strControllerName)){
				OperationOrStageServlet objServlet  = new OperationOrStageServlet();
				set = OperationOrStageServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			
			else if(TPCSCommonUtil.MASTER_PRODUCTS_SIZE_RANGE.equalsIgnoreCase(strControllerName)){
				SizeRangeServlet objServlet  = new SizeRangeServlet();
				set = SizeRangeServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.MASTER_PRODUCTS_SEASON.equalsIgnoreCase(strControllerName)){
				SeasonServlet objServlet  = new SeasonServlet();
				set = SeasonServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.MASTER_PRODUCTS_CURRENCY.equalsIgnoreCase(strControllerName)){
				CurrencyServlet objServlet  = new CurrencyServlet();
				set = CurrencyServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.MASTER_SIZE_MAPPING.equalsIgnoreCase(strControllerName)){
				SizeMappingServlet objServlet  = new SizeMappingServlet();
				set = SizeMappingServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.BUYER_ORDER.equalsIgnoreCase(strControllerName)){
				BuyerOrderServlet objServlet  = new BuyerOrderServlet();
				set = BuyerOrderServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.COMMON_UTIL.equalsIgnoreCase(strControllerName)){
				CommonUtilServlet objServlet  = new CommonUtilServlet();
				set = CommonUtilServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.PURCHASE_ORDER.equalsIgnoreCase(strControllerName)){
				PurchaseOrderServlet objServlet=new PurchaseOrderServlet();
				set = PurchaseOrderServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}
			else if(TPCSCommonUtil.INDENT.equalsIgnoreCase(strControllerName)){
				IndentServlet objServlet  = new IndentServlet();
				set = IndentServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
			}else if(TPCSCommonUtil.CUSTOMIZE_REPORT.equalsIgnoreCase(strControllerName)){
				CustomizeReportServlet objServlet= new CustomizeReportServlet();
				set = CustomizeReportServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
				objServlet=null;
			}
			
			else if(TPCSCommonUtil.INDENT_APPROVAL_REGISTER.equalsIgnoreCase(strControllerName)){
				IndentApprovalRegisterServlet objServlet= new IndentApprovalRegisterServlet();
				set = IndentApprovalRegisterServlet.class.getMethods();       
				this.InvokeMethod(request,response,set,(Object)objServlet,strMethodName);
				objServlet=null;
			}
			//			  InvokeMethod(request,response,strControllerName,strMethodName);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void InvokeMethod(HttpServletRequest request,HttpServletResponse response,Method set[],Object disUser, String strMethodName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException{
		try{
			for (int i = 0; i < set.length; i++) {
				if(set[i].getName().equalsIgnoreCase(strMethodName)){
					Object args[] = new Object[2];
					args[0] = request;
					args[1] = response;
					set[i].invoke(disUser,args);
					break;				
				}
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
