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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />


<style>
/* .container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}
 */

.accordion-toggle:hover {
      text-decoration: none;
    }
    
    .back-to-top {
    cursor: pointer;
    position: fixed;
    bottom: 20px;
    right: 20px;
    display:none;
}
</style>
<script>

$(function(){
$('[data-toggle="tooltip"]').tooltip();
});
$(document).ready(function(){
    $('#filter-dropdown').click(function (e) {
    //	alert(e);
        e.stopPropagation();
    });
});
</script>
<script>
jQuery(document).ready(function(){
jQuery('.collapse').on('shown.bs.collapse', function(){
	jQuery(this).parent().find(".glyphicon-plus-sign").removeClass("glyphicon-plus-sign").addClass("glyphicon-minus-sign");
	}).on('hidden.bs.collapse', function(){
jQuery(this).parent().find(".glyphicon-minus-sign").removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
	});
});
</script>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-8 col-sm-12 col-xs-12">
                    <h3 class="page-header"><i class="fa fa-bar-chart-o fa-fw light-green-color" ></i>Reports</h3>
                </div>	
                
                <div class="col-md-4 col-sm-12 col-xs-12">
	                <div class="input-group stylish-input-group">
	                    <input type="text" id="search_report" name="search_report" autofocus class="form-control"  placeholder="Search report" >
	                    <span class="input-group-addon">
	                        <button id="rept_search_btn" name="rept_search_btn" >
	                            <span class="glyphicon glyphicon-search"></span>
	                        </button>  
	                    </span>
	                    
	                    <input type="hidden" id="search_report_url" name="search_report_url" />
	                </div>
                </div>
             
         </div>
     <%
	 	  
	 	  
TPCSUser ui=new UserInfo().getUserInfo(request);
MenuHolder holder = (MenuHolder) session.getAttribute("report_items");
Map<Integer, List<MenuItem>> mapDocuments = holder.getDocuments();
Map<Integer, List<MenuItem>> mapSubDocuments = holder.getSubDocuments();
ResourceBundle bundle = TPCSCommonUtil.getResourceBundle(request); 
try{
List<MenuItem> items = (List<MenuItem>)session.getAttribute("report_main_menu");
MenuItem mainMenu=null;
Iterator<MenuItem> iterator = items.iterator();

int i=1;
	 	  while(iterator.hasNext()){
	 	    mainMenu = (MenuItem)iterator.next();
	 	   %>     
         
		<div class="row">
<div class="panel-group" id="accordion">
  <div class="panel panel-default " >
    <div class="panel-heading accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne<%=i%>">
      <h4 class="panel-title">
        <span  style="cursor: pointer;" >
          <span class="indicator glyphicon glyphicon-minus-sign light-green-color" ></span>
         <fmt:message bundle="${bundle}" ><%=mainMenu.getMenuName()%></fmt:message>
        </span>
      </h4>
      <!-- <div class='pull-right' id="">
			<a href="#" onclick="editReport(22)"   data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit"></span>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash"></span>
        			</a>
		</div> -->
    </div>
    <div id="collapseOne<%=i%>" class="panel-collapse collapse in">
      <div class="panel-body">
       	<div class='row' style="border-bottom:1px solid #f5f5f5;">
       	<div class='col-md-2 col-xs-12' >
			<label for="customer">Folder</label>
		</div>
		<div class='col-md-3' id="">
			<label for="customer">Report Name</label>
		</div>
		<div class='col-md-6 col-xs-12' id="">
			<label for="customer">Description</label>
		</div>
		<div class='col-md-1 col-xs-12' id="">
			<label for="customer">Action</label>
		</div>
       	</div>
       	
       	
       	 <%
       	 
	 	  List<MenuItem> documents = mapDocuments.get(mainMenu.getMenuId());
	 	  if(documents !=null && documents.size()>0){
	 	  for (MenuItem document : documents)
	 	  {
	 		  
	 	  List<MenuItem> subdocuments = mapSubDocuments.get(document.getMenuId());
	 	  if(subdocuments!=null && subdocuments.size()>0){
	 	    for (MenuItem subDocument : subdocuments)
	 	    {
			%>
       		<div class='row' style="border-bottom:1px solid #f5f5f5;">
       	<div class='col-md-2 col-xs-12' >
			<h5></h5><!-- Inventory  -->
		</div>
		<div class='col-md-3' id="">
			<h5><a href="#" id="rept_name_<%=subDocument.getMenuId() %>" onclick="<%=subDocument.getMenuURL()%>"><%=subDocument.getMenuName()%></a></h5>
		</div>
		<div class='col-md-6 col-xs-12' id="">
			<h5 id="rept_desc_<%=subDocument.getMenuId() %>"><%=subDocument.getDesc() %></h5>
		</div>
		<div class="col-md-1 col-xs-12" id="">
		<h5>
			<a href="javascript:editReport(<%=subDocument.getMenuId() %>)" id="rept_edit" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit icon-edit"></span>
        			</a>
        				<a href="javascript:customizeReport(<%=subDocument.getMenuId() %>)" id="rept_customize"  data-toggle="tooltip" title="Customize">
          				<i class="glyphicon glyphicon-cog icon-cog"></i>
        			</a>
        			<%if(subDocument.getParentId()!=0){ %>
        			<a href="javascript:deleteReport(<%=subDocument.getMenuId() %>)" id="rept_delete" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash icon-delete"></span>
        			</a>
        			<%} %>
        			</h5>
		</div>
       	</div>
       		<%-- <!-- <li> --><a href="#" onclick="<%=subDocument.getMenuURL()%>"><fmt:message bundle="${bundle}" ><%=subDocument.getMenuName()%></fmt:message></a><!-- </li> --> --%>
       	
       		<%
       		}
	 	  }
	 	  }
	 	  }
	 	   %><!-- </ul> -->
       			<!-- </div> -->
       			
       			
       		<!-- <div class='col-md-4 col-xs-12'>
       			<label for="">desc</label>
       		</div>
       		<div class='col-md-2 col-xs-12'>
        			<a href="#" onclick="editReport(22)"   data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit"></span>
        			</a>
        				<a href="#"  onclick="customizeReport(22)" data-toggle="tooltip" title="Customize">
          				<i class="fa fa-asterisk fa-fw"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash"></span>
        			</a>

       		</div> -->
       		<!-- </div>
 -->       		
       		
       	<%-- <div class='row linerow'>
       		<div class='col-md-2 col-xs-12'>
       			<label for=""></label>
       		</div>
       			
       			<div class='col-md-4'>
       			<ul>
       	 <%
	 	  List<MenuItem> documents = mapDocuments.get(mainMenu.getMenuId());
	 	  for (MenuItem document : documents)
	 	  {
	 		  
	 	  List<MenuItem> subdocuments = mapSubDocuments.get(document.getMenuId());	
	 	    for (MenuItem subDocument : subdocuments)
	 	    {
	 	    	
			%>
       	
       		<li><a href="#" onclick="<%=subDocument.getMenuURL()%>"><fmt:message bundle="${bundle}" ><%=subDocument.getMenuName()%></fmt:message></a></li>
       	
       		<%
       		}
	 	  }
	 	   %></ul>
       			</div>
       			
       			
       		<div class='col-md-4 col-xs-12'>
       			<label for="">desc</label>
       		</div>
       		<div class='col-md-2 col-xs-12'>
        			<a href="#" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit"></span>
        			</a>
        				<a href="#" data-toggle="tooltip" title="Customize">
          				<i class="fa fa-asterisk fa-fw"></i>
        			</a>
        			<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-trash"></span>
        			</a>

       		</div>
       		</div> --%>
      </div>
    </div>
  </div>
  
</div>

<a id="back-to-top" href="#" class="btn btn-primary btn-lg back-to-top" role="button" title="Click to return on the top page" data-toggle="tooltip" data-placement="left"><span class="glyphicon glyphicon-chevron-up"></span></a>

</div>
		 <%
		 i++;
		 
	 	  }
	 	  } catch(ClassCastException cce){
  cce.printStackTrace();
}catch(Exception e){
  e.printStackTrace();
}

%>
		
       </div>           
     </div>  
    </div>

		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" /> 
		<input  type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" />
		<input type="hidden" name="report_mode" id="report_mode" value="e"/>
			<input type="hidden" name="subdocument_id" id="subdocument_id" value=""/>
			
			<input type="hidden" name="open_report_modal" id="open_report_modal" data-toggle="modal" data-target="#reptNameModal" />
			
<jsp:include page="/jsp/reports/NewReport.jsp" />

<style>
.popover{
	max-width:476px;
}
</style>
<script >



//Instance the tour


/* (function(){
 
    var tour = new Tour({
        storage : false,
        template: "<div class='popover tour'><div class='arrow'></div><h3 class='popover-title'></h3><div class='popover-content'></div><div class='popover-navigation'><div class='btn-group'><button class='btn btn-sm btn-default' data-role='prev'><i class='glyphicon glyphicon-menu-left'></i> Prev</button><button class='btn btn-sm btn-default' data-role='next'>Next <i class='glyphicon glyphicon-menu-right'></i></button></div><span data-role='separator'>&nbsp;</span><button class='btn btn-sm btn-default' data-role='end'>End tour</button></div></nav></div>"
    });
    
    tour.addSteps([
  	{
	    element: "#search_report",
	    title: "Search Report",
	    content: "Type report name to search reports",
	    placement: "bottom"
  	},
  	{
	    element: "#rept_search_btn",
	    title: "Display Report",
	    content: "Click here to go to searched report",
	    placement: "left"
  	},
  	{
	    element: "#rept_disp",
	    title: "Display Report",
	    content: "Click here to go to report",
	    placement: "bottom"
  	},
  	{
	    element: "#rept_edit",
	    title: "Edit Report",
	    content: "Click here to edit report name and its description",
	    placement: "left"
  	},
  	{
	    element: "#rept_customize",
	    title: "Customize Report",
	    content: "Click here to customize report by add/remove fields, group fields, total fields, custom filters and save as new report",
	    placement: "left"
  	},
  	{
	    element: "#rept_delete",
	    title: "Delete Report",
	    content: "Click here to delete created report",
	    placement: "left"
  	}
  	
  	
  ]); 
    
    });*/
    
   

// Initialize the tour
//tour.init();

// Start the tour
//tour.start();


$(document).ready(function(){
    $(window).scroll(function () {
           if ($(this).scrollTop() > 50) {
               $('#back-to-top').fadeIn();
           } else {
               $('#back-to-top').fadeOut();
           }
       });
       // scroll body to 0px on click
       $('#back-to-top').click(function () {
           $('#back-to-top').tooltip('hide');
           $('body,html').animate({
               scrollTop: 0
           }, 800);
           return false;
       });
       
       $('#back-to-top').tooltip('show');

});


</script>
<script>
!function ($) {
	 
	$(function(){
			
		  $('#search_report').listAllReports({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetAllReports&create_new=0",
			  nameField :'#search_report',
			  idField:'#search_report_url',
			  attrField:'#rept_search_btn',
			  attrName:'onclick',
		  });

		  
	 });
}(window.jQuery);
	 

</script>	
	</form>
	
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>	
</body>
</html>
   </fmt:bundle>         