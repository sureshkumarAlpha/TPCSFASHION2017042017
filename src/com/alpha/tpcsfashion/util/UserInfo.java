package com.alpha.tpcsfashion.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alpha.tpcsfashion.beans.TPCSUser;


public class UserInfo {
	  public TPCSUser getUserInfo(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	
	    	return userInfo;
	    }
	  public String getClientPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";
	    	session=null;
	    	userInfo=null;	    	
	    	return folderPath;
	    }
	  public String getMaterialAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//material/";
	    	return folderPath;
	    }
	  public String getEnquiryAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//enquiry/";
	    	return folderPath;
	    }
	  public String getVariantAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//variant/";
	    	return folderPath;
	    }
	  public String getBOMAttachPath(HttpServletRequest request){
			TPCSUser userInfo=getUserInfo(request);
			String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//enquiry/";
			return folderPath;
		}	 
	  
	  public String getQuotationAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//quotation/";
	    	return folderPath;
	    }
	  public String getOrderAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//order/";
	    	return folderPath;
	    }
	  public String getInvoiceAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//salesinvoice/";
	    	return folderPath;
	    }
	  public String getReturnAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//salesreturn/";
	    	return folderPath;
	    }
	  public String getBankReceiptAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//bankreceipt/";
	    	return folderPath;
	    }
	  public String getBankPaymentAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//bankpayment/";
	    	return folderPath;
	    }
	  public String getJournalVoucherAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//journalvoucher/";
	    	return folderPath;
	    }
	  public String getContraAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//journalvoucher/";
	    	return folderPath;
	    }
	  public String getPurchaseInvoiceAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//purchaseinvoice/";
	    	return folderPath;
	    }
	  public String getPurchaseReturnAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//purchasereturn/";
	    	return folderPath;
	    }
	  public String getPurchaseAttachPath(HttpServletRequest request){
	    	HttpSession session=request.getSession();
	    	TPCSUser userInfo=(TPCSUser)session.getAttribute("user_info");
	    	String folderPath="images/uploadimages/"+userInfo.getclientId()+"/";//purchaseorder/";
	    	return folderPath;
	    }
	  
}
