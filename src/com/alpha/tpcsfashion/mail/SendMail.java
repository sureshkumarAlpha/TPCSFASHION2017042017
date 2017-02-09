package com.alpha.tpcsfashion.mail;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.alpha.tpcsfashion.beans.MailInfo;

import java.util.List;

public class SendMail 
{ 
 
 String from_id=null,to_id=null,smtp_password=null;
 
    public void sendMail(String fromId, List<String> toId, String password, String smtpserver,String portNumber,String subject,String contents,String file,MailInfo mailInfo) throws MessagingException, AddressException 
    {
    	  System.out.println("Sending mail started................."+new java.util.Date());
         
          String[] to=toId.toArray(new String[0]);
          String[] cc={};
          String[] bcc={};
       
          ThreadMail objThread = new ThreadMail(fromId,password,smtpserver,portNumber,"true",to,cc,bcc,subject,contents,file,mailInfo);
          new Thread(objThread).start();         
          
          System.out.println("Mail Sending in progress................."+new java.util.Date());
    } 
 

  
   
}