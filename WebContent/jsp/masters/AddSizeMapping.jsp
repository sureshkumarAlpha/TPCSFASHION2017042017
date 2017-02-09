<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.Map"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>
<jsp:include page="../common/Header.jsp" />

<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/masters.js"></script>


<script language="javascript">
	<c:forEach var="events" items="${dynamicFormEvents}">
		<c:out value="function ${events.functionName}"/> 
		<c:out value =  "{"/>
		<c:out value="${events.attachedJS}" escapeXml="false"/>
		<c:out value =  "}"/>			
	</c:forEach>
    
	
 	
	
	
	<c:forEach var="events" items="${dynamicHeaderFieldEvents}">
		<c:out value="function ${events.functionName}"/> 
		<c:out value =  "{"/>
		<c:out value="${events.attachedJS}" escapeXml="false"/>
		<c:out value =  "}"/>			
	</c:forEach>
    
	<c:forEach var="events" items="${dynamicDetailFieldEvents}">
		<c:out value="function ${events.functionName}"/> 
		<c:out value =  "{"/>
		<c:out value="${events.attachedJS}" escapeXml="false"/>
		<c:out value =  "}"/>			
	</c:forEach>
	

	
</script>

<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style>
</head>
<jsp:include page="../common/ValidateUser.jsp" />
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
<body>
<form action=""  id="validate_sizemapping" method="post" role="form" autocomplete="off"> 
<div class="wrapper">
    <jsp:include page="../common/MainMenu.jsp"> 
 	<jsp:param value="5" name="screen_type"/>
	<jsp:param value="Admin.Products.SizeMapping" name="screen_name"/>
	</jsp:include>

<div class="top-bar">
     <div class="row-no-margin row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" style="margin:18px 0 0px 20px;">Size Mapping</h2>
                </div>
    </div>
<div class="page-wrapper">
<div class="page-inner"> 
	<div class="row">
				 <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label for="customer">Schedule No</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="schedule_no" name="schedule_no" autofocus size="30" type="text" value="${heatlst.scheduleNo}" placeholder="Enter Schedule No"/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
				
	
	 <div class='col-sm-3'  >
			<div class='form-group'>
	  		<label>Status</label>
				<select class="form-control" id="status" name="status">
				<option value="1" <c:if test="${heatlst.isActive eq 1 }">selected="selected"</c:if>>Active</option>
				<option value="0" <c:if test="${heatlst.isActive eq 0 }">selected="selected"</c:if>>Inactive</option>
				</select>
		       </div>
	</div>
	
	<div class="col-sm-3">
		 <div class='form-group'>
                    <label for="customer">Remark</label>
                    <textarea class="form-control address-text-area" rows="4" id="remark" name="remark" placeholder="Enter remark" maxlength="250" data-bv-field="ship_to_address" >${heatlst.remark}</textarea>
                </div>
		</div>
	
	</div>
</div>      
</div>
</div>

<div class="page-wrapper">
<div class="page-inner"> 

<div class="row">
 <label >&nbsp;</label>
</div>
 <div class="row">
 <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label for="customer">Product Size Range</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="size_range" name="size_range"  size="30" type="text" value="${details.prodSizeRangeName}" placeholder="Type and select Size Range" onblur="getSizeMappingDetailsGrid(${size_sched_id});"/>
		   		 <input class="form-control" id="size_range_id" name="size_range_id"  size="30" type="hidden" value="${details.prodSizeRangeId}" />
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
 </div> 
  <div class='row linerow'>
  					<div id="size_grid">
					${sizeGrid}
						</div>
  </div>
          
	
	<div class='row '>
   				 <div class='col-sm-10 col-xs-12 col-sm-offset-1'>
   				 <div class='pull-right centered save-view'>            
                 <div class="btn-group dropup">
               	 <button name="save" id="save"   onclick="saveSizeMapping('${mode}','1')" class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			   	 </button>
               	 <button    class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                 <ul class="dropdown-menu">
           		<%--  <li><a href="javascript:validateAppPriceList('${mode}','2')" >Save & Send</a></li> --%>
          		 <li><a href="javascript:saveSizeMapping('${mode}','3')" >Save & New</a></li>
                 <li class="divider"></li>
                <%--  <li><a  href="javascript:saveSizeMapping('${mode}','4')">Save & Close</a></li> --%>
                 </ul>
                 </div>
                 <button type="button" class="btn btn-primary"  onclick="showSizeMapping()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
                 </div>
           		 </div>              
    </div> 	
    <div class='row'  style="margin-top:20px;">
 	</div>

<!-- ------------------------------- -->
<div class="row table-responsive">
		
<table class="table table-bordered table-condensed table-hover"  id="task-table" >
<thead>
<tr class="header">
<th valign="middle" nowrap="nowrap" align="center" class="w-mini first small">Action</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Product Size Range</th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Product Size </th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Raw Material Size Range </th>
<th valign="middle" nowrap="nowrap" align="center" class="w-mini">Raw Material Size</th>
</tr>
</thead>
<tbody>
<c:set var="k" value="${1 }"/>
<c:set var="previous_id" value=""/>
<c:forEach var="obj" items="${detList}">
	<tr class="datarow">
	<c:choose>
	<c:when test="${previous_id ne obj.sizeScheduleDetId}">
	<td valign="middle"  nowrap="nowrap" align="left">
	<select class="form-control" id="select_action" name="select_action" onchange="ActionSizeMappingDetailList(this.value,'${obj.sizeScheduleId}','${obj.sizeScheduleDetId}',1)">
	<option selected="selected" value="-1">Action</option>
		<%-- <c:if test="${operation_rights.editPermission==1 }">	 --%>
		<option value="2">Edit</option>
		<%-- </c:if>
		<c:if test="${operation_rights.deletePermission==1 }">	 --%>
		<option value="1">Delete</option>
		<%-- </c:if> --%>
	</select>

	</td>
	 </c:when>
    <c:otherwise>
    	<td >&nbsp;</td>
    </c:otherwise>         
    </c:choose>
	<td >${obj.prodSizeRangeName}</td>
	<td >${obj.prodSizeName}</td>
	<td >${obj.rmSizeRangeName}</td>
	<td >${obj.rmSizeName}</td> 
	
	</tr>
	<c:set var="k" value="${k+1}"/>
	<c:set var="previous_id" value="${obj.sizeScheduleDetId}"/>
</c:forEach>

<c:if test="${k < 5}">
		<c:set var="k" value="${5-k}"/>
		<c:forEach begin="1" end="${k}" varStatus="loop">
    	<tr>      
        <td>&nbsp;</td>
         <td>&nbsp;</td>
          <td>&nbsp;</td>
           <td>&nbsp;</td>
             <td>&nbsp;</td>
    	</tr>
		</c:forEach>
		</c:if>
</tbody>
</table>
	    </div>


</div>
</div>

<!-- --------------------------------------------------- -->
</div>

<jsp:include page="../common/Footer.jsp"/>

   <input type="hidden" name="servlet_name" id="servlet_name" />
   <input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
   <input type="hidden" name="pageno" id="pageno" />
   <input type="hidden" name="request_type" id="request_type" />
   <input type="hidden" name="mode" id="mode" value="${mode}" />
   <input type="hidden" name="save_type" id="save_type" />
   <input type="hidden" name="edited_ids" id="edited_ids" value=""/>
   <input type="hidden" name="dynedited_ids" id="dynedited_ids" value=""/>
   <input type="hidden" name="edited_mode" id="edited_mode" value=""/>
   <input type="hidden" name="update" id="update" />
   <input type="hidden" name="size_sched_id" id="size_sched_id" value="${size_sched_id}" />
   <input type="hidden" name="size_sched_Detid" id="size_sched_Detid" value="${size_sched_Detid}" />
   <input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
    <input type="hidden" name="det_mode" id="det_mode" value="${det_mode}" />
    <input type="hidden" name="size_sched" id="size_sched" value="${size_sched}" />
<script>
$('#validate_sizemapping').bootstrapValidator({
	//  live: 'disabled',
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	/*   excluded: ':disabled', */
	  fields: {
	        
	          schedule_no:{
	              validators: {
	            	  notEmpty: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          size_range: {
		            validators: {
		                notEmpty: {
		                    message: 'This field is required'
		                },
		                callback: {
		                    message: 'This field is required',
		                    callback: function(value, validator, $field) {
		                    	if ($("#size_range_id").val()==0) {
		                            return {
		                                valid: false,
		                                message: 'This field is required'
		                            };
		                        }
		                    	return true;
		                    }
		                }
		          
		            }
		        }, 
		  
	  }
});
</script>   
   
<script>
!function ($) {
	 
	$(function(){
		
		$('#size_range').listSizeRange({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
			  nameField :'#size_range',
			  idField:'#size_range_id',
			  formId:'#validate_sizemapping',
			  validateId:'size_range' 
		  });
		 <c:forEach var="sizeId" items="${sizeIdList}">
			
		 $('#size_${size_sched_id}_${sizeId}_1').listSizeRange({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
			  nameField :'#size_${size_sched_id}_${sizeId}_1',
			  idField:'#size_id_${size_sched_id}_${sizeId}_1',
			  /* formId:'#validate_sizemapping',
			 validateId:'size_range' */
		  });
		 $('#size_${size_sched_id}_${sizeId}_2').listSizeRangeSize({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRangeSize&create_new=1",
			  nameField :'#size_${size_sched_id}_${sizeId}_2',
			  idField:'#size_id_${size_sched_id}_${sizeId}_2',
			  dependIdField:'#size_id_${size_sched_id}_${sizeId}_1'
			  /* formId:'#validate_sizemapping',
			 validateId:'size_range' */
		  });
		
		 </c:forEach>
		 
	 });
}(window.jQuery);

	</script>  
	
	
</form>

 <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>