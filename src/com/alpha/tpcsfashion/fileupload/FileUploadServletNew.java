package com.alpha.tpcsfashion.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alpha.tpcsfashion.beans.FileImport;

public class FileUploadServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FileUploadServletNew() {
        super();       
    }
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			  
            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
            if (!isMultipartContent)
            {
              return;
            }
        
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try
            {
              List<FileItem> fields = upload.parseRequest(request);
              Iterator<FileItem> it = fields.iterator();
              if (!it.hasNext())
              {
                return;
              }
              String path = getServletContext().getRealPath("/");
              path = path.concat("uploadedimages");
//              System.out.println("PATH..........\t"+path);
              while (it.hasNext())
              {
                FileItem fileItem = it.next();
//                System.out.println("FieldName=" + fileItem.getFieldName());
//                System.out.println("Size in bytes=" + fileItem.getSize());
//                System.out.println("Resquest URI" + request.getRequestURI());
                if (fileItem.isFormField())
                {
//                  System.out.println(fileItem.getFieldName() + "\t\t\t*********\t\t\t" +fileItem.getString());
                }
                else
                {
//                  System.out.println("FileName=" + fileItem.getName());
//                  System.out.println("ContentType=" + fileItem.getContentType());
                  File file = new File(path+ File.separator + fileItem.getName());
                  fileItem.write(file);
                  file=null;
//                  System.out.println("Absolute Path at server=" + file.getAbsolutePath());
                }
        
              }
              
              factory=null;
              upload=null;
              response.getWriter().write("Success");
              
            
              
            }
            catch (Exception e)
            {
              e.printStackTrace();
            }
	}
	public void invokeMethod(HttpServletRequest request,HttpServletResponse response,FileImport objBean)
	{
	/*	Class[] paramString = new Class[3];	
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
		}*/
	}
	
}


