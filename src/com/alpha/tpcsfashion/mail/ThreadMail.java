package com.alpha.tpcsfashion.mail;

import com.alpha.tpcsfashion.beans.MailInfo;


public class ThreadMail implements Runnable
{

  private String fromAcc=null;
  private String fromPwd=null;
  private String smtpAcc=null;
  private String smptPort=null;
  private String starttls=null;
  private String authenticate=null;
  private boolean debug=false;
  private String socketFactoryClass=null;
  private String fallback=null;
  private String[] toAcc=null;
  private String[] ccAcc=null;
  private String[] bccAcc=null;
  private String subject=null;
  private String body=null;
  private String file=null;
  private MailInfo mailInfo=null;
  public ThreadMail(String userName,String passWord,String host,String port,String auth,String[] to,String[] cc, String[] bcc,String subject,String text,String file,MailInfo mailInfo){
    this.fromAcc=userName;
    this.fromPwd=passWord;
    this.smtpAcc=host;
    this.smptPort=port;
    this.authenticate=auth;
    /*this.starttls=starttls;    
    this.debug=debug;
    this.socketFactoryClass=socketFactoryClass;
    this.fallback=fallback;*/
    this.toAcc=to;
    this.ccAcc=cc;
    this.bccAcc=bcc;
    this.subject=subject;
    this.body=text;
    this.file=file;
    this.mailInfo=mailInfo;
    
  }
  public void run()
  {
     try {
    	 Mail mail=new Mail();
        mail.sendMail(fromAcc, fromPwd, smtpAcc, smptPort, authenticate,toAcc, ccAcc, bccAcc, subject, body,file,mailInfo);        
     } catch (Throwable t) { }
  }
}
