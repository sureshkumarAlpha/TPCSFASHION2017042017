<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.alpha.tpcsfashion.beans.MenuItem"%>
<%@page import="com.alpha.tpcsfashion.beans.TPCSUser"%>
<%@ page import="com.alpha.tpcsfashion.util.UserInfo"%>
<%@ page import="com.alpha.tpcsfashion.util.TPCSCommonUtil"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.alpha.tpcsfashion.beans.MenuHolder"%>

<fmt:setLocale value="${sessionScope.login_user_locale}"/>
<fmt:bundle basename="${sessionScope.appBundleName}">

<!-- Top Menu Starts Here-->

<!-- <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Jenix Cloud</a>
          <img src="images/jenixerp.png" class="img-rounded" alt="Cinque Terre" width="200" height="50">
        </div>

<div class="navbar-collapse collapse">
	 	  <ul class="nav navbar-nav navbar-right" id="top_menu"> -->
<style>
/* @import url(http://fonts.googleapis.com/css?family=Open+Sans:400,700); */
/* body {
  font-family: 'Open Sans', 'sans-serif';
background:#f0f0f0; 
} */
.navbar-nav>li>.dropdown-menu {
	/* margin-top:10px; */
	/* border-top-left-radius: 4px;
	border-top-right-radius: 4px; */
	border-radius:0px;
	border: 1px solid #ccc;
	background-color: #fff;
	box-shadow: 0 2px 10px rgba(0, 0, 0, .2);
	border-top: 5px solid #0097CF;
}

.navbar-default .navbar-nav>li>a {
	/*  width:200px;  */
	font-weight: bold;
}

.mega-dropdown {
	position: static !important;
	/* width:100%; */
}

.mega-dropdown-menu {
	padding: 20px 0px;
	/* width: 99%; */
	box-shadow: none;
	-webkit-box-shadow: none;
	/*   margin: 0px 8px; */
}
/* .navbar .open .dropdown-toggle:before {
    content: "";
    border-bottom: 10px solid #fff;
    border-right: 12px solid transparent;
    border-left: 12px solid transparent;
    position: absolute;
 	top: 41px;
  	left: 20px;
  	z-index: 1500;
}
.navbar .open .dropdown-toggle:after {
    content: "";
    border-bottom: 12px solid #ccc;
    border-right: 14px solid transparent;
    border-left: 14px solid transparent;
    position: absolute;
  	top: 40px;
  	left: 18px;
  	z-index: 8;
}  */
.mega-dropdown-menu>li>ul {
	padding: 0;
	margin: 0;
}

.mega-dropdown-menu>li>ul>li {
	list-style: none;
}

.mega-dropdown-menu>li>ul>li>a {
	display: block;
	padding: 5px 20px;
	clear: both;
	font-weight: normal;
	line-height: 1.428571429;
	color: #999;
	white-space: normal;
}

.mega-dropdown-menu>li ul>li>a:hover,.mega-dropdown-menu>li ul>li>a:focus
	{
	/* text-decoration: underline; */
	text-decoration: none;
	color: #fff;
	background-color: #0097CF;
}

.mega-dropdown-menu .dropdown-header {
	color: #428bca;
	font-size: 18px;
	font-weight: bold;
    text-decoration: underline;
}

.mega-dropdown-menu form {
	margin: 3px 20px;
}

.mega-dropdown-menu .form-group {
	margin-bottom: 3px;
}

.mega-dropdown-menu>li>ul>li>a active {
	text-decoration: underline;
}
</style>	 	  


	 	  
<div class="navbar">
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="navbar-header">
		<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<!-- <a class="navbar-brand" href="#">MegaMenu</a> -->
		<img src="${pageContext.request.contextPath}/images/tpcsfashion.png" class="img-rounded" alt="Jenix Books" height="50" onclick="showHome()">
	</div>
	
	<div class="collapse navbar-collapse js-navbar-collapse">
		<ul class="nav navbar-nav">
			 	  
	 	  <%
/*  int iSubDocumentType = Integer.parseInt(request.getParameter("sub_document_type"));
	 	  
	 	  
	 	  System.out.println("iSubDocumentType---------->"+iSubDocumentType); */
	 	  
	 	 String left="style=\"\"";
	 	  String col="class=\"col-sm-3\"";	 	  
	 	  
TPCSUser ui=new UserInfo().getUserInfo(request);
MenuHolder holder = (MenuHolder) session.getAttribute("menu_items");
Map<Integer, List<MenuItem>> mapDocuments = holder.getDocuments();
Map<Integer, List<MenuItem>> mapSubDocuments = holder.getSubDocuments();
ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 
try{
List<MenuItem> items = (List<MenuItem>)session.getAttribute("main_menu");
MenuItem mainMenu=null;
Iterator<MenuItem> iterator = items.iterator();

	 	  while(iterator.hasNext()){
	 	    mainMenu = (MenuItem)iterator.next();
	 	    
	 	  
	 	   %>
	 	   <li class="dropdown mega-dropdown">		
	 	   <a href="#" onclick="<%=mainMenu.getMenuURL()%>" <%if(mainMenu.getMenuId()!=6){ %> class="dropdown-toggle" data-toggle="dropdown" <%} %>><fmt:message bundle="${bundle}" ><%=mainMenu.getMenuName()%></fmt:message>&nbsp;
	 	   <span class="caret"></span></a>
	 	  <%-- <li class="dropdown"><a  href="#" onclick="<%=mainMenu.getMenuURL()%>"><fmt:message bundle="${bundle}" ><%=mainMenu.getMenuName()%></fmt:message></a>
	 	  <ul class="dropdown-menu"> --%>
	 	  
	 	  <%
	 	  
			if(mainMenu.getMenuId()!=6){	 	  
	 	  	 	  	
				List<MenuItem> documents = mapDocuments.get(mainMenu.getMenuId());
			 	  if(documents!=null ){
			 		  
			 	  int docSize=documents.size();
			 	  
			 	 left="";
			 	  if(docSize>=4){
			 		 left="style=\"right:0;\"";
			 	  }
			 	  col=docSize==5?"class=\"col-sm-5ths\"":"class=\"col-sm-"+12/docSize+"\"";
			 	
			 	  //System.out.println("docSize :"+docSize);
			 	  
			 	 /* if (mainMenu.getMenuName().equals("Menu.Home")) {
						//left = "style=\"left:134px;\"";
						col = "class=\"col-sm-"+12/docSize+"\"";
					} else if (mainMenu.getMenuName().equals("Menu.Sales")) {
						//left = "style=\"left: 108px;width: 240px;\"";
						col = "class=\"col-sm-"+12/docSize+"\"";
					} else if (mainMenu.getMenuName().equals("Menu.Purchase")) {
						//left = "style=\"left:184px;\"";
						col = "class=\"col-sm-"+12/docSize+"\"";
					} else if (mainMenu.getMenuName().equals("Menu.Inventory")) {
						//left = "style=\"left:165px;\"";
						col = "class=\"col-sm-"+12/docSize+"\"";
					} else if (mainMenu.getMenuName().equals("Menu.Production")) {
						//left = "style=\"left:453px;\"";
						col = "class=\"col-sm-"+12/docSize+"\"";
					} else if (mainMenu.getMenuName().equals("Menu.Finance")) {
						//left = "style=\"left:415px;\"";
						col = "class=\"col-sm-"+12/docSize+"\"";
					} else if (mainMenu.getMenuName().equals("Menu.MasterData")) {
						//left = "style=\"left:383px;\"";
						col = "class=\"col-sm-"+12/docSize+"\"";
					} else if (mainMenu.getMenuName().equals("Menu.Other")) {
						//left = "style=\"left:803px;\"";
						col = "class=\"col-sm-"+12/docSize+"\"";
					}
					else if (mainMenu.getMenuName().equals("Menu.Admin")) {
						//left = "style=\"left:860px;\"";
						col = "class=\"col-sm-"+12/docSize+"\"";
					} */
			 	  
	 	 
	 	  	 	  %>
	 	  <%-- <%=left %>   --%>
	 	  <ul class="dropdown-menu mega-dropdown-menu row"  >
	 	  <%
	 	  
	 	  int i=1;
	 	  
	 	  String  docClass="";
	 			  
	 	  for (MenuItem document : documents)
	 	  {
	 		  %>
	 		  
	 		  	<!-- class="col-sm-3" -->
					<li <%=col%>>
					<ul>
					<!--  || mainMenu.getMenuId()==5 -->
					<%
					if(mainMenu.getMenuId()==4 || mainMenu.getMenuId()==5){
					%>
							<li class="dropdown-header"><fmt:message bundle="${bundle}" ><%=document.getMenuName()%></fmt:message></li>
							
							<%
							}
					%>
	 		  
	 	  <%-- <li><a href="#"><i class="<%=docClass%>"></i>&nbsp;&nbsp;<fmt:message bundle="${bundle}" ><%=document.getMenuName()%></fmt:message></a> 
	 	   <ul class="dropdown-menu">--%>
	 	  <%
	 	  List<MenuItem> subdocuments = mapSubDocuments.get(document.getMenuId());
	 	  if(subdocuments!=null){
	 	  //int subSize=subdocuments.size();
	 	  int j=1;
	 	  
	 	  
	 	    for (MenuItem subDocument : subdocuments)
	 	    {
	 	    	
			%>
			<li><a href="#" onclick="<%=subDocument.getMenuURL()%>"><fmt:message bundle="${bundle}" ><%=subDocument.getMenuName()%></fmt:message></a></li>

			<%
	 	    	//if(subSize!=j){
	 	    	   %>
	 	    		 <!-- <li class="divider"></li> -->	
	 	    	  <%
	 	    //	  }
	 	    	
	 	    	
	 	    	j++;
	 	    }
	 	  }
	 	   %>
	 	   </ul>
	 	     </li>
	 	    
	 	       <%	
	 	  //  if(docSize!=i){
	 	    	 %>
 	    		<!--  <li class="divider"></li> -->	
 	    	  <%
	     	//}
	     	i++;	
	 	  }
	 	  }
			}
	 	 
	 	 if(mainMenu.getMenuId()!=6){
	 	 %>
	 	 
	 	 
	 	  </ul> 
	 	  <%
	 	 }
	 	  %>
	 	 </li>
	 	   <!-- </li> -->
	 	    <%
	 	  }
	 	 %>
	 	 <li class="">
				<a href="javascript:showAllReports()" class="dropdown-toggle" >Reports</a>
				<!-- style="left:865px;" -->
				
			</li>
	 	 <%-- <li class="dropdown mega-dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="glyphicon glyphicon-user fa-2x"></i><span class="caret"></span></a>
				<!-- style="left:865px;" -->
				<ul class="dropdown-menu mega-dropdown-menu row" >
				<li class="col-sm-12">
						<ul>
							<li class="dropdown-header">Welcome <%=ui.getDisplayUserName()%></li>
							<li><a href="javascript:changeCompanyYear()">Change Company / Year</a></li>
							<li><a href="javascript:logout();">Logout</a></li>
				</ul>
			</li>
			</ul>
			</li> --%>
	 	 
	 	 
	 	 </ul>
	 	 <ul  class="nav navbar-nav pull-right">
	 	 <li class="dropdown mega-dropdown" >
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">&nbsp;<i class="glyphicon glyphicon-user fa-2x"></i><span class="caret"></span></a>
				<!-- style="left:865px;" -->
				<ul class="dropdown-menu mega-dropdown-menu row" style="right:0">
				<li class="col-sm-12">
						<ul>
							<li class="dropdown-header">Welcome <%=ui.getDisplayUserName()%></li>
							<li><a href="javascript:changeCompanyYear()">Change Company / Year</a></li>
								<li><a href="javascript:companyConfiguartion()">Company & Configuration</a></li>
							<li><a href="javascript:logout();">Logout</a></li>
				</ul>
			</li>
			</ul>
			</li>
			</ul>
	 	 </div>
	 	 
	 	 <!-- </nav> -->
	 	  <%-- <li class="dropdown">
	 	  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	 	   <i class="fa fa-user fa-fw"></i> <%=ui.getDisplayUserName()%> </a>
	 	  <ul class="dropdown-menu dropdown-user">
	 	  <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
	 	  </li>
	 	  <li class="divider"></li>
	 	  <li><a href="#"><i class="fa fa-gear fa-fw"></i> User Settings</a>
	 	  </li>
	 	  <li class="divider"></li>
	 	  <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
	 	  </li>
	 	  </ul>                 
	 	  </li>  --%>
	 	   <!-- </ul> -->
	 	   </nav>
	 	   </div>      
<%
	 	     }catch(ClassCastException cce){
  cce.printStackTrace();
}catch(Exception e){
  e.printStackTrace();
}
%>
<!-- Top Menu End Here-->
	<div id="errmsg">
		<font class="warning-msg">
			<div id="warningmessage">
				<font class="err-msg">
					<div id="errormessage">
						<font class="succ-msg">
							<div id="successmessage">

								<font id="err-msg" class="err-msg" style="display: none">${error_message}</font>
								<font id="succ-msg" class="succ-msg" style="display: none">${success_message}</font>
								<font id="warning-msg" class="warning-msg" style="display: none">${warning_message}</font>

							</div>
						</font>
					</div>
				</font>
			</div>
		</font>
	</div>
	<script language="javascript">
/* showResponseMsg('err-msg','succ-msg');
function showResponseMsg(errMsg,sussMsg){
	errorMsg=document.getElementById(errMsg).innerHTML;
	succMsg=document.getElementById(sussMsg).innerHTML;

	if(errorMsg!='' ){
		toastr["error"](errorMsg);
	}
	else if(succMsg!=''){
		toastr["success"](succMsg);
	}

} */

showResponseMsg('err-msg','succ-msg');
function showResponseMsg(errMsg,sussMsg){
	errorMsg=document.getElementById(errMsg);
	succMsg=document.getElementById(sussMsg);
	if(errorMsg.innerHTML!='' ){
		toastr["error"](errorMsg.innerHTML);
		errorMsg.innerHTML='';
	}
	else if(succMsg.innerHTML!=''){
		toastr["success"](succMsg.innerHTML);
		succMsg.innerHTML='';
	}

}


showWarningMsg('warning-msg');
function showWarningMsg(warningMsg){
	warningMsg=document.getElementById(warningMsg);
	 if(warningMsg.innerHTML!=''){
		toastr["warning"](warningMsg.innerHTML);
		warningMsg.innerHTML='';
	}

}



$(document).ready(function(){
/*      $(".mega-dropdown").hover(            
        function(event) {
            $(this).toggleClass('open');     
            event.stopPropagation();
        } 
       
    ); 
     $(".mega-dropdown").click(            
             function(event) {
                 $(this).toggleClass('open');        
                // event.stopPropagation();
             }
         );  */
/*     $(".mega-dropdown").click(            
            function() {
                $(this).toggleClass('open');        
            },
            function() {
                $(this).toggleClass('open');       
            }
        ); */
   /*  $(".mega-dropdown").focusout(            
            function() {
                $(this).removeClass('open');        
            },
            function() {
                $(this).removeClass('open');       
            }
        );
    $(".mega-dropdown > a").focus(            
            function() {
                $(this).parent().toggleClass('open');        
            },
            function() {
                $(this).parent().toggleClass('open');       
            }
        ); */
});

</script>

</fmt:bundle> 

