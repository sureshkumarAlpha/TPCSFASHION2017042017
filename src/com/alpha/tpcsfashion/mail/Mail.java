package com.alpha.tpcsfashion.mail;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.alpha.tpcsfashion.beans.MailInfo;
import com.alpha.tpcsfashion.util.ManageFile;



public class Mail
{

String auth_user=null,auth_pwd=null;
        public void sendMail(String userName,String passWord,String host,String port,String auth,String[] to,String[] cc, String[] bcc,String subject,String text,String file,MailInfo mailInfo){
        	
//        		text=text.concat("<br><br><br><br><br><br><a href=\"http://www.jenixcloud.com/jenixerp\" target=\"_blank\" title=\"login\" alt=\"login\" style=\"cursor:pointer;\">www.jenixcloud.com/jenixerp</a>");
        	System.out.println("userName: "+userName);
        	System.out.println("passWord: "+passWord);
        		auth_user=userName;
        		auth_pwd=passWord;
                Properties props = new Properties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.auth", auth);
                props.put("mail.smtp.starttls.enable", "true");
               // Properties props=System.getProperties();
               // props.put("mail.smtp.user", userName);
               
                
               /* if(!"".equals(port))
                   props.put("mail.smtp.port", port);
                
                if(!"".equals(starttls)){
                  props.put("mail.smtp.starttls.enable",starttls);
                   props.put("mail.smtp.socketFactory.port", port);
                }
               
                if(debug){
                   props.put("mail.smtp.debug", "true");
                }else{
                   props.put("mail.smtp.debug", "false");         
                }
               
                 
               if(!"".equals(socketFactoryClass))
                   props.put("mail.smtp.socketFactory.class",socketFactoryClass);
                if(!"".equals(fallback))
                   props.put("mail.smtp.socketFactory.fallback", fallback);*/

        try
        {
        	
            Session session = null;            
            session = Session.getInstance(props,new javax.mail.Authenticator() {
	                     protected PasswordAuthentication getPasswordAuthentication() {
	                       return new PasswordAuthentication(auth_user,auth_pwd);          
	                     }
                     });
            
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setSubject(subject);
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart htmlPart=new MimeBodyPart();
            htmlPart.setContent(text, "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);
            
            if(file.length()>0 && ManageFile.isfileExist(file) )
            {
	            BodyPart imgPart=new MimeBodyPart();
	            DataSource ds=new FileDataSource(file);
	            imgPart.setDataHandler(new DataHandler(ds));
	            imgPart.setHeader("Content-ID","<the-img-1>");
	            multipart.addBodyPart(imgPart);
            }
            
            int atchCnt=0;
            BodyPart attachPart=new MimeBodyPart();
            
            if(mailInfo.getLiMailInfo()!=null){
	            List<MailInfo> liMailInfo=mailInfo.getLiMailInfo();
	            for(MailInfo mInfo:liMailInfo){
	            	File f=new File(mInfo.getFilePath());
	            	if(f.exists()){
		            	DataSource source = new FileDataSource(mInfo.getFilePath());
			            attachPart.setDataHandler(new DataHandler(source));
			            attachPart.setFileName(mInfo.getFileName());
			            multipart.addBodyPart(attachPart);
			            atchCnt++;
	            	}
	            }
            }
            /*BodyPart attachPart=new MimeBodyPart();
            Map<String,String> attachMap=mailInfo.getAttachMap();
            Iterator it=attachMap.entrySet().iterator();
            while(it.hasNext()){
	            Map.Entry<String, String> en=(Map.Entry<String, String>)it.next();
	            DataSource source = new FileDataSource(en.getKey()+"\\"+en.getValue());
	            attachPart.setDataHandler(new DataHandler(source));
	            attachPart.setFileName(en.getValue());
	            multipart.addBodyPart(attachPart);
            }*/
            
//            if(file.length()>0 || atchCnt>0){
            	msg.setContent(multipart);
//            }
//            else
//            {
//            	msg.setText(text);
//            }
            	
            InternetAddress addressFrom = new InternetAddress(userName);
            msg.setFrom(addressFrom);

            for(int i=0;i<to.length;i++){
              msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
            }
            for(int i=0;i<cc.length;i++){
              msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
            }
            for(int i=0;i<bcc.length;i++){
              msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
            }
            msg.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(host, userName, passWord);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Mail sent successfully.........."+new java.util.Date());
            
            if(mailInfo.getFilePath()!=null){
            	ManageFile.deleteFile(mailInfo.getFilePath());
            }
           // return true;
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
                     //   return false;
        }
        }

}
