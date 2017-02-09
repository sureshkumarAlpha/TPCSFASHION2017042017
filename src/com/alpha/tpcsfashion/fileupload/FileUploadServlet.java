package com.alpha.tpcsfashion.fileupload;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alpha.tpcsfashion.beans.FileImport;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.util.UserInfo;

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			   
			String className="";
			String methodName="";
			int colCountRow=0;
			boolean extensionxlsx=false;
			boolean extensionxls=false;
			
			   File file ;
			   String path="";
			   String realPath=(String) request.getSession().getAttribute("path");
			   	
			   	StringBuffer screenTag = new StringBuffer("");
			   	TPCSUser ui=new UserInfo().getUserInfo(request);
	    		Calendar currentDate = Calendar.getInstance();
	    	    SimpleDateFormat formatter = new SimpleDateFormat("yyyymmddhhmmssaa");
	        	String dateNow = formatter.format(currentDate.getTime());
	    		screenTag.append(ui.getUserId()+"_" + dateNow);
			   
			   int maxFileSize = 1000000 * 1024;
			   int maxMemSize = 1000000 * 1024;
			   /*String folderPath=realPath+"images\\uploadimages\\"+ui.getclientId()+"\\Development Order\\";
			   File f=new File(folderPath);
			   if(!f.exists()){
				   f.mkdirs();
	            }*/
			   String contentType = request.getContentType();
			   
			   if ((contentType.indexOf("multipart/form-data") >= 0)) {
			   DiskFileItemFactory factory = new DiskFileItemFactory();
			   factory.setSizeThreshold(maxMemSize);
			   factory.setRepository(new File("C:\\WINDOWS\\Temp"));
			   ServletFileUpload upload = new ServletFileUpload(factory);
			   upload.setSizeMax( maxFileSize );
			   FileItem item=null ;
			      try{ 
						List fileItems = upload.parseRequest(request);
						Iterator i = fileItems.iterator();
						  
						  List<File> fileList=new ArrayList<File>();
			            while ( i.hasNext () ){ 
			            	item = (FileItem)i.next();
			      
			            	
			            	if(item.isFormField ()) {
			            		
			            		String fieldname = item.getFieldName();
			            		String fieldvalue = item.getString();
			            		if (fieldname.equals("invoke_class")) {
			            			className=fieldvalue;
			            		}
			            		if (fieldname.equals("invoke_method")) {
			            			methodName=fieldvalue;;
			            		}
			            	}
			            	fileList=null;
			      }
			    
		          FileImport objBean=new FileImport();
//		          System.out.println("className :"+className);
//		          System.out.println("methodName :"+methodName);
			      objBean.setClassName(className);
			      objBean.setMethodName(methodName);
			      objBean.setFileItems(fileItems);
			     
			      invokeMethod(request,response,objBean);
			      
			      screenTag=null;
			      ui=null;
			      formatter=null;
			      factory=null;
			      upload=null;
			      objBean=null;
			      }
			      catch(Exception ex){
			         ex.printStackTrace();
			         }
			      }
			     else{
			        System.out.println("Error"); 
			    	 }
		   }
	public void invokeMethod(HttpServletRequest request,HttpServletResponse response,FileImport objBean)
	{
		Class[] paramString = new Class[3];	
		paramString[0] = HttpServletRequest.class;
		paramString[1] = HttpServletResponse.class;
		paramString[2] = FileImport.class;
		
		try{
			Class objClass= Class.forName(objBean.getClassName());
			Object obj=objClass.newInstance();
			Method method = objClass.getDeclaredMethod(objBean.getMethodName(),paramString);
			method.invoke(obj,request,response,objBean);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}


