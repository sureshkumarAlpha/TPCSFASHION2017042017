<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery/mousetrap.js"></script> 
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/common/moment.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/order/buyer-order.js"></script>

 <script>

$(function(){
	$('[data-toggle="tooltip"]').tooltip();
});
$(document).ready(function(){
    $('#filter-dropdown').click(function (e) {
        e.stopPropagation();
    });
    
});


</script>


<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>

<script language="javascript">
var localizedStrings = {
		
		    ATTACHMENT_DELETED: "<fmt:message bundle="${bundle}"   key="Attachment.AttachmentDeleted"/>",
	    	ATTACHMENT_NOT_DELETED: "<fmt:message bundle="${bundle}" key="Attachment.AttachmentNotDeleted"/>",
	    	DELETE_ATTACHMENT: "<fmt:message bundle="${bundle}" key="Attachment.DeleteAttachment"/>",
		    FOLLOWER_DELETE_FAILED: "<fmt:message bundle="${bundle}"  key="Follower.DeleteFollowerFailed"/>",
	    	FOLLOWER_DELETED: "<fmt:message bundle="${bundle}"  key="Follower.FollowerDeleted"/>",
	    	DELETE_FOLLOWER: "<fmt:message bundle="${bundle}"  key="Follower.DeleteFollower"/>",
	    	AUTO_NO_SAVED: "<fmt:message  bundle="${bundle}" key="AutoNo.Saved"/>",
	    	AUTO_NO_NOT_SAVED: "<fmt:message  bundle="${bundle}" key="AutoNo.NotSaved"/>"
	    	
	};	
</script>

<script type="text/javascript">
function showhidebutton(values){
	if(values==1){
	  $("#tableshow").hide();
	  $("#texboxshow").show();
	}else{
		  $("#tableshow").show();
		  $("#texboxshow").hide();
	}
}
function showhidebutton1(values){
	if(values==1){
	  $("#tableshow1").hide();
	  $("#texboxshow1").show();
	}else{
		  $("#tableshow1").show();
		  $("#texboxshow1").hide();
	}
}
	</script>
	
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
.size-table tr th{
	width:100px;
}
/* .page-inner{
    margin: 15px;
}
.has-feedback .form-control {
    padding-right: 25px;
}
.form-control {
    height: 30px;
    border-radius: 2px;
    font-size: 12px;
    border-color: #eaeaea;
    padding-right: 22px;
}
.form-group {
    margin-bottom: 12px;
}
.form-group label {
    margin-bottom: 0px;
}
label {
    font-size: 12px;
}

#grid_table>thead>tr>th, #grid_table>tbody>tr>td {
    padding: 2px;
}
 

#grid_table>tbody>tr>td>.form-group {
    margin-bottom: 9px;
}
 
#grid_table>tbody>tr>td>.form-group>small {
    margin: 0px;
    font-size: 10px;
}
.help-block{
	margin-top: 0px;
}
.form-control-feedback{
    width: 30px;
    height: 30px;
    line-height: 40px;
} 
.auto-prefix {
    width: 35%;
    height: 30px;
    padding: 0px 6px;
    border: 1px solid #eee;
    border-radius: 2px;
    display: inline;
}
.auto-no {
    width: 52%;
    height: 30px;
    padding: 0px 12px;
    border: 1px solid #eee;
    border-radius: 2px;
    display: inline;
}
.header-row {
    margin-top: 10px;
    margin-bottom: 30px;
}
.required-field-block .required-icon {
    display: inline-block;
    vertical-align: middle;
    margin: -0.25em 0.25em 0em;
    background-color: #ffe8e8;
    border-color: #fff6f6;
    padding: 0.5em 0.8em;
    color: rgba(0, 0, 0, 0.65);
    text-transform: uppercase;
    font-weight: normal;
    border-radius: 0.325em;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    -ms-box-sizing: border-box;
    box-sizing: border-box;
    -webkit-transition: background 0.1s linear;
    -moz-transition: background 0.1s linear;
    transition: background 0.1s linear;
    font-size: 75%;
}
.required-field-block .required-icon {
    background-color: transparent;
    position: absolute;
    top: 0em;
    right: 0em;
    z-index: 1;
    margin: 0em;
    width: 25px;
    height: 25px;
    padding: 0em;
    text-align: center;
    -webkit-transition: color 0.2s ease;
    -moz-transition: color 0.2s ease;
    transition: color 0.2s ease;
}
.required-field-block .required-icon:after {
    position: absolute;
    content: "";
    right: 1px;
    top: 1px;
    z-index: -1;
    width: 0em;
    height: 0em;
    border-top: 0em solid transparent;
    border-right: 25px solid transparent;
    border-bottom: 25px solid transparent;
    border-left: 0em solid transparent;
    border-right-color: inherit;
    -webkit-transition: border-color 0.2s ease;
    -moz-transition: border-color 0.2s ease;
    transition: border-color 0.2s ease;
}
.input-group-addon{
    background-color: #f5f5f5;
    border: 1px solid #eaeaea;
    }
 
.required-field-block .required-icon .text {
    color: #B80000;
    font-size: 17px;
    margin: 0px 0 0 10px;
    font-weight: 600;
}
.cortan-grid>tbody>tr>th:first-child {
    width: 200px;
}
.cortan-grid>tbody>tr>th:last-child {
    width: 50px;
}
.cortan-grid>tbody>tr>td:first-child {
    text-align:center;
}

.cortan-grid>tbody>tr>th {
    width: 100px;
    padding:2px;
    text-align:center;
}
.size-grid>tbody>tr>th:first-child {
    width: 50px;
}
.size-grid>tbody>tr>td:first-child {
    text-align:center;
}
.size-grid>tbody>tr>th {
    width: 100px;
    padding:3px;
    text-align:center;
}
 
.btn{
    border-radius: 2px;
    padding: 4px 8px;
	font-size: 13px;
}
.btn-group>.dropdown-toggle {
    height: 28px;
}
.dropdown-menu>li>a{
	font-size: 13px;
}
#packing_row {
    margin-top: 20px;
}
#packing_row .form-group {
    margin-bottom: 13px;
}
.glyphicon {
    font-size: 11px;
}
.attach-table{
	border-collapse: collapse;
	text-align:center;
}
#grid_table{
	margin:0px;
}
.cortan-grid .form-control {
    text-align: right;
}
.size-grid .form-control {
    text-align: right;
}
.talign-right{
	text-align:right;
}
.a-icon{
    padding: 8.5px;
    border: 1px solid #eaeaea;
    margin: 0px;
    border-radius: 1px;
}
.span-icon{
    padding: 8px;
    border: 1px solid #eaeaea;
    margin: 0px;
    border-radius: 1px;
}
.abs{
	position: absolute;
	margin-left: 2px;
}  
.auto-no-modal {
    text-decoration: none;
    height: 340px;
    overflow-y: scroll;
} */
</style>	

</head>


<jsp:include page="../common/ValidateUser.jsp" />
<body>
	<form id="abo_form" method="post" role="form">

		<div class="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name" />
			</jsp:include>

			<div class="page-wrapper">
				<div class="page-inner">

					<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Buyer Order</h3>
							</div>
						</div>
					</div>
					
					<div class='row'>
					
							<div class='col-sm-3'>
						
							<c:forEach items="${dynamicFieldsListBOHeader}" var="dynamicHeaderfield">
								<c:if test="${dynamicHeaderfield.alignment=='L'}">
								
									<%@ include file="../order/AddBuyerOrderHeaderDynamic.jsp" %> 
								
								</c:if>
							</c:forEach>
						
							</div>
						
							<div class='col-sm-3'>
							
							<c:forEach items="${dynamicFieldsListBOHeader}" var="dynamicHeaderfield">
								<c:if test="${dynamicHeaderfield.alignment=='M'}">
								
									<%@ include file="../order/AddBuyerOrderHeaderDynamic.jsp" %> 
								
								</c:if>
							</c:forEach>
							
							</div>
						
							<div class='col-sm-3'>
							
							<c:forEach items="${dynamicFieldsListBOHeader}" var="dynamicHeaderfield">
								<c:if test="${dynamicHeaderfield.alignment=='R'}">
								
									<%@ include file="../order/AddBuyerOrderHeaderDynamic.jsp" %> 
								
								</c:if>
							</c:forEach>
							
							</div>
					
					</div>
					
					<div class='row sub-title-row'>
					
								<div class='col-sm-12'>
								<div class='col-sm-8'>
								<!-- <div><label>Packing Type<span class="glyphicon    text-primary glyphicon-minus-sign" id="packing_panel_togg"></span></label></div> -->
								<h4>
									<label class="text-primary">Packing Type&nbsp;<span class="span-icon glyphicon text-primary glyphicon-minus-sign" id="packing_panel_togg"></span></label>
								</h4>
								</div>
								</div>
					</div>
					<!-- <div class='row '> -->
								<div id="packing_panel">
								${ header_info.packingScheduleData}
								</div>
							
					<!-- </div> -->
						
						<input type="hidden" name="data_table_width" id="data_table_width" value="${header_info.detTableWidth}"/>
						
					 <%@ include file="../order/AddBuyerOrderDetailDynamic.jsp" %> 


					<div class="row sub-title-row">
						<div class="col-sm-12">
							<div class="col-sm-12">
								<h5>
									<label class="text-primary">Instructions & Terms </label>
								</h5>
							</div>
						</div>
					</div>

					<div class='row' >
						
						<div class='col-sm-3'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label for="payment_terms">Payment Terms</label>
									<textarea class="form-control" rows="6" id="payment_terms" name="payment_terms" maxlength="250" placeholder="Enter Payment Terms"></textarea>
								</div>
							</div>
						</div>	
						<div class='col-sm-3'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label for="insurance_terms">Insurance Details</label>
									<textarea class="form-control" rows="6" id="insurance_terms" name="insurance_terms" maxlength="250" placeholder="Enter Insurance Details"></textarea>
								</div>
							</div>
						</div>
					</div>





					<div class="row ">
						<div class='col-sm-3'>
							<div class="col-sm-12">
							
								<div class='form-group'>
									<label for="delivery_terms">Delivery Terms</label>
									<textarea class="form-control" rows="6" id="delivery_terms" name="delivery_terms" maxlength="250" placeholder="Enter Delivery Terms"></textarea>
								</div>
							</div>
						</div>
	
						<div class='col-sm-3'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label for="delivery_to">Delivery To</label>
									<textarea class="form-control" rows="6" id="delivery_to" name="delivery_to" maxlength="250" placeholder="Enter Delivery To"></textarea>
								</div>
							</div>
						</div>
					</div>


					<div class="row ">
						<div class='col-sm-3'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label for="special_instruction">Special Instruction</label>
									<textarea class="form-control" rows="6" id="special_instruction" name="special_instruction" maxlength="250" placeholder="Enter Special Instruction"></textarea>
								</div>
							</div>
						</div>
	
						<div class='col-sm-3'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label for="packing_labeling">Packing & labeling Instruction</label>
									<textarea class="form-control" rows="6" id="packing_labeling" name="packing_labeling" maxlength="250" placeholder="Enter Packing & labeling Instruction"></textarea>
								</div>
							</div>
						</div>
					</div>


					

					<div class='row'>
						<div class="col-sm-12">
							<div class="col-sm-12">
								<span class="btn btn-primary fileinput-button" onclick="addAttachment()"> 
								<i class="glyphicon glyphicon-plus"></i> <span>Add files...</span>
								</span> 
								<img id="ajax-loader" src="images/ajax-loader.gif" class="ajax-loader">
							</div>
						</div>
					</div>
					
					<div class='row attach-area'>
						<div class="col-sm-10">
							<div class="col-sm-12">
							<c:set var="idx" value="1" />
							<c:forEach var="filename" items="${attached_files}">
								 
								 <div class=" col-sm-4 notice notice-info" id="attach_file${idx}" >
								 <div class="attach-delete">
        							<a class="attach-link-delete" href="#" name="del_attach${idx}" id="del_attach${idx}" onclick="deleteBOAttachment('${idx}','${filename.boId}','${filename.url}')">
											<span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
								</div>
								<div >
											<a class="attach-link" target="_blank" href="${boAttachPath}${filename.url}">${filename.fileName}</a>
								</div>
    							</div> 
							
								<c:set var="idx" value="${idx+1}" />
							</c:forEach>
							</div>
						</div>
					</div>

					<div class='row'>
						<div class="col-sm-12">
							<div class="col-sm-12">
								<table id="attachments" class="attach-table" >
								</table>
							</div>
						</div>
					</div>
					 
					<div class='row sub-title-row' id="tc_area">
						<div class="col-sm-12 col-xs-12">
							<div class="col-sm-12">
							<div class="col-sm-9 col-xs-12">
								<h4>
									<label class="text-primary">Terms & Conditions </label>
								</h4>

								<c:choose>
									<c:when test="${not empty header_info.tcList}">
										<c:forEach var="tcVal" items="${header_info.tcList}">
											<div class="row " id="tc_row_${tcVal.sl}">
												<div class="col-sm-1-5  col-xs-3">
													<div class="form-group">
														<input type="text" class="form-control text-center" id="tc_slno_${tcVal.sl}" name="tc_slno_${tcVal.sl}" <c:if test="${tcVal.slno gt 0}">value="${tcVal.slno}"</c:if> />
													</div>
												</div>
												<div class="col-sm-3  col-xs-7">
													<div class="form-group">
														<input type="text" class="form-control" id="tc_text_${tcVal.sl}" name="tc_text_${tcVal.sl}" value="<c:out value="${tcVal.term}"/>" maxlength="50" />
													</div>
												</div>
												
												<div class="col-sm-6  col-xs-7">
													<div class="form-group">
														<input type="text" class="form-control" id="tc_particular_${tcVal.sl}" name="tc_particular_${tcVal.sl}" value="<c:out value="${tcVal.particular}"/>" maxlength="250" />
													</div>
												</div>

												<div class="col-sm-1">
													<a href="javascript:addTCRow(${tcVal.sl},'bo_tc_ids','bo_tc_cnt');" id="tc_add_${tcVal.sl}" style="display:none;"><span class="span-icon glyphicon glyphicon-plus text-primary"></span></a>
												</div>

											</div>
										<script>
										jQuery('#tc_particular_${tcVal.sl}').focus(function(e) {
									    	jQuery('#tc_add_${tcVal.sl}').show();
									    	jQuery('#tc_add_${tcVal.sl+1}').hide();
									    	jQuery('#tc_add_${tcVal.sl-1}').hide();
										}); 
									    jQuery('#tc_slno_${tcVal.sl}').focus(function(e) {
									    	jQuery('#tc_add_${tcVal.sl}').hide();
									    	jQuery('#tc_add_${tcVal.sl+1}').hide();
									    	jQuery('#tc_add_${tcVal.sl-1}').hide();
									    	
										}); 
										</script>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<div class="row " id="tc_row_1">
											<div class="col-sm-1-5  col-xs-3">
												<div class="form-group">
													<input type="text" class="form-control text-center" id="tc_slno_1" name="tc_slno_1" />
												</div>
											</div>
											<div class="col-sm-3  col-xs-7">
												<div class="form-group">
													<input type="text" class="form-control" id="tc_text_1" name="tc_text_1" maxlength="50" />
												</div>
											</div>
											
											<div class="col-sm-6  col-xs-7">
												<div class="form-group">
													<input type="text" class="form-control" id="tc_particular_1" name="tc_particular_1" maxlength="250" />
												</div>
											</div>

											<div class="col-sm-1">
												<a href="javascript:addTCRow(1,'bo_tc_ids','bo_tc_cnt');" id="tc_add_1" data-toggle="tooltip"  title="Add" style="display: none;">
													<span class="span-icon glyphicon glyphicon-plus text-primary"></span>
												</a>
											</div>
											<script>jQuery('#bo_tc_ids').val("1,");</script>
										</div>
										<script>
										jQuery('#tc_particular_1').focus(function(e) {
									    	jQuery('#tc_add_1').show();
									    	jQuery('#tc_add_2').hide();
										}); 
									    jQuery('#tc_slno_1').focus(function(e) {
									    	jQuery('#tc_add_1').hide();
									    	jQuery('#tc_add_2').hide();
									    	
										}); 
										</script>
									</c:otherwise>
								</c:choose>


								<!-- <div class="row ">
									<div class="col-sm-12">
										<div class="checkbox">
											<input class="checkbox_1" type="checkbox" id="bo_default_tc" name="bo_default_tc" value="1">
											<label for="bo_default_tc" class="checkbox_1">Make this as my default Terms & Conditions</label>
										</div>
									</div>
								</div> -->
							</div>
							</div>
						</div>
					</div>

					<div class='row '>
						<div class='col-sm-12 col-xs-12 '>
							<div class='pull-right centered save-view'>
								<c:if test="${view_mode ne 'yes' }">
								<div class="btn-group dropup">
									<button name="abo_form_save" id="abo_form_save" onclick="validateBO('${buyer_order.headerMode}','1')" class="btn btn-success ladda-button" data-style="expand-right"> <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save</button>
									<button class="btn btn-success dropdown-toggle" data-toggle="dropdown" ><span class="caret"></span></button>
									<ul class="dropdown-menu">
										<li><a id="abo_form_save_and_send" href="javascript:validateBO('${buyer_order.headerMode}','2')" >Save & Send</a></li>
										<li><a id="abo_form_save_and_new" href="javascript:validateBO('${buyer_order.headerMode}','3')">Save & New</a></li>
										<li class="divider"></li>
										<li><a id="abo_form_save_and_close" href="javascript:validateBO('${buyer_order.headerMode}','4')">Save & Close</a></li>
									</ul>
								</div>
								</c:if>
								<button type="button" id="abo_form_back" class="btn btn-primary" onclick="showBuyerOrder()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>
							</div>
						</div>
					</div>

					<div class='row' style="margin-top: 20px;"></div>

				</div>
			</div>
		</div>

		<jsp:include page="../common/Footer.jsp" />


 		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" /> 
		<input type="hidden" name="pageno" id="pageno" /> 
		<input type="hidden" name="request_type" id="request_type" />
		<input type="hidden" name="screen" id="screen" value="BO" />
		
		<input type="hidden" name="screen_tag" id="screen_tag" value="ABO"/>
		<input type="hidden" name="header_mode" id="header_mode" value="${buyer_order.headerMode}" />
		<input type="hidden" name="detail_mode" id="detail_mode"  value="${buyer_order.detailMode}"/>
		
		<input type="hidden" name="bo_id" id="bo_id" value="${header_info.boId}" />  
		<input type="hidden" name="bo_det_id" id="bo_det_id" value="${detail_info.boDetId}" />
		
		<input type="hidden" name="packing_row_ids" id="packing_row_ids" value="${header_info.packingRowIds}"/>
		<input type="hidden" name="packing_row_count" id="packing_row_count" value="${header_info.packingRowCount}"/>
		
		<input type="hidden" name="currency_defaultid" id="currency_defaultid" value="${sessionScope.user_info.currencyId}" />
		<input type="hidden" name="edited_ids" id="edited_ids" value=""/>
		<input type="hidden" name="dynedited_ids" id="dynedited_ids" value=""/>
		<input type="hidden" name="edited_mode" id="edited_mode" value=""/>
		<input type="hidden" name="invoke_method" id="invoke_method" />
		<input type="hidden" name="invoke_class" id="invoke_class" />
		<input type="hidden" name="selected_users" id="selected_users"/>
		<input type="hidden" name="selected_userremarks" id="selected_userremarks"/>
		<input type="hidden" name="selected_remarks" id="selected_remarks"/>
		<input type="hidden" name="cust_cur_id" id="cust_cur_id" value="${header_info.currencyId}"/>
		<input type="hidden" name="cust_cur_sym" id="cust_cur_sym" value="${cust_cur_sym}"/>
		<input type="hidden" name="cust_cur_name" id="cust_cur_name" value="<c:out value="${header_info.currencyName}"/>"/>
		
		<input type="hidden" name="def_cur_id" id="def_cur_id" value="${user_info.currencyId}"/>
		<input type="hidden" name="def_cur_name" id="def_cur_name" value="<c:out value="${user_info.currencyName}"/>"/>
		<input type="hidden" name="page" id="page" />
		<input type="hidden" name="save_type" id="save_type" />
		<input type="hidden" name="new_det_mode" id="new_det_mode"/>
		<input type="hidden" name="mode" id="mode"/>
		<input type="hidden" name="prefix_name" id="prefix_name"/>
		
		<input type="hidden" name="edited_size_det_ids" id="edited_size_det_ids" />
		
		<%-- <c:set var="det_ids" value=""/>
		<c:forEach var="detId" items="${det_id_list}">
		<c:set var="det_ids" value="${det_ids},${detId}"/>
		</c:forEach> --%>
		<input type="hidden" name="det_ids" id="det_ids" value="${det_ids}"/>
		
		<input type="hidden" name="bo_tc_ids" id="bo_tc_ids" value="${header_info.tcIds}"/>
		<input type="hidden" name="bo_tc_cnt" id="bo_tc_cnt" value="${header_info.tcCnt}"/>
		 
		<input type="submit" name="abo_validation_btn" id="abo_validation_btn" style="display:none;"/>

<script>
<c:forEach var="li_row_id" items="${header_info.packingRowIdList}" >

	if(jQuery('#packing_size_range_id_${li_row_id}').val()>0 && jQuery('#packing_type_${li_row_id}').val()!=''){
		addPackingToCortan(${li_row_id});
	}

</c:forEach>
</script>

		<script>
 
 
 $('#abo_form').bootstrapValidator({
		
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	/*   excluded: ':disabled', */
	  fields: {
		  buyer_name: {
	            validators: {
	                notEmpty: {
	                    message: 'This field is required'
	                },
	                callback: {
	                    message: 'This field is required',
	                    callback: function(value, validator, $field) {
	                    	if ($("#buyer_id").val()==0) {
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
	        bo_date: {
	              validators: {
	                  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  date: {
	                      format: 'DD-MM-YYYY',
	                      message: 'The date is not a valid'
	                  }
	              }
	          },
	          currency_name: {
	              validators: {
	                  notEmpty: {
	                      message: 'This field is required'
	                  },
	                    callback: {
	                        message: 'This field is required',
	                        callback: function(value, validator, $field) {
	                        	if ($("#currency_id").val()==0) {
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
	          bo_prefix: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if (value<=0) {
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
	          bo_no: {
	              validators: {
	            	  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if (value.length==0){
	                      		 return {
		                                    valid: false,
		                                    message: 'This field is required'
		                                };
	                      		}
	                      	else if (value<=0) {
	                              return {
	                                  valid: false,
	                                  message: 'This field must be greater than zero'
	                              };
	                          }
	                      	return true;
	                      }
	                  } ,
	                  numeric: {
		                      message: 'This field must be a number'
		                            } 
	                    }
	          },
	          ex_rate: {
	              validators: {
	                
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          item_name_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#item_id_0").val()==0) {
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
	          size_range_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#size_range__id_0").val()==0) {
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
	          
	          qty_0: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          rate_fcy_0: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          required_date_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  date: {
	                      format: 'DD-MM-YYYY',
	                      message: 'The date is not a valid'
	                  },
	                  callback: {
	                        message: 'Select after Order date',
	                        callback: function(value, validator) {
	                            //var m = new moment(value, 'YYYY/MM/DD');
	                            //if (!m.isValid()) {
	                               // return false;
	                            //}
	                            // Check if the date in our range
	                            var dateAr=jQuery('#bo_date').val().split('-');
	                            var reqDateAr=jQuery('#required_date_0').val().split('-');
	                            
	                            var trDate=moment(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0],'YYYY/MM/DD',true);
	                            var reqDate=moment(reqDateAr[2]+'/'+reqDateAr[1]+'/'+reqDateAr[0],'YYYY/MM/DD',true);
	                            
	                            //return m.isAfter(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0]) && m.isBefore(posDateAr[2]+'/'+posDateAr[1]+'/'+posDateAr[0]);
	                            return (reqDate >=trDate);
	                        }
	                    }
	              }
	          },
	          possible_date_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  date: {
	                      format: 'DD-MM-YYYY',
	                      message: 'The date is not a valid'
	                  },
	                  callback: {
	                        message: 'Select after Order date',
	                        callback: function(value, validator) {
	                            //var m = new moment(value, 'YYYY/MM/DD');
	                            //if (!m.isValid()) {
	                               // return false;
	                            //}
	                            // Check if the date in our range
	                            var dateAr=jQuery('#bo_date').val().split('-');
	                            var posDateAr=jQuery('#possible_date_0').val().split('-');
	                            
	                            var trDate=moment(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0],'YYYY/MM/DD',true);
	                            var posDate=moment(posDateAr[2]+'/'+posDateAr[1]+'/'+posDateAr[0],'YYYY/MM/DD',true);
	                            
	                            //return m.isAfter(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0]) && m.isBefore(posDateAr[2]+'/'+posDateAr[1]+'/'+posDateAr[0]);
	                            return (posDate >=trDate);
	                        }
	                    }
	              }
	          },
	          mrp_0:{
	        	  validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          <c:forEach var="detId" items="${det_id_list}">
	          item_name_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#item_id_S_${detId}").val()==0) {
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
	          size_range_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#size_range_id_S_${detId}").val()==0) {
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
	          qty_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          rate_fcy_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          required_date_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  date: {
	                      format: 'DD-MM-YYYY',
	                      message: 'The date is not a valid'
	                  },
	                  callback: {
	                        message: 'Select after Order date',
	                        callback: function(value, validator) {
	                            //var m = new moment(value, 'YYYY/MM/DD');
	                            //if (!m.isValid()) {
	                               // return false;
	                            //}
	                            // Check if the date in our range
	                            var dateAr=jQuery('#bo_date').val().split('-');
	                            var reqDateAr=jQuery('#required_date_S_${detId}').val().split('-');
	                            
	                            var trDate=moment(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0],'YYYY/MM/DD',true);
	                            var reqDate=moment(reqDateAr[2]+'/'+reqDateAr[1]+'/'+reqDateAr[0],'YYYY/MM/DD',true);
	                            
	                            //return m.isAfter(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0]) && m.isBefore(posDateAr[2]+'/'+posDateAr[1]+'/'+posDateAr[0]);
	                            return (reqDate >=trDate);
	                        }
	                    }
	              }
	          },
	          possible_date_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  date: {
	                      format: 'DD-MM-YYYY',
	                      message: 'The date is not a valid'
	                  },
	                  callback: {
	                        message: 'Select after Order date',
	                        callback: function(value, validator) {
	                            //var m = new moment(value, 'YYYY/MM/DD');
	                            //if (!m.isValid()) {
	                               // return false;
	                            //}
	                            // Check if the date in our range
	                            var dateAr=jQuery('#bo_date').val().split('-');
	                            var posDateAr=jQuery('#possible_date_S_${detId}').val().split('-');
	                            
	                            var trDate=moment(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0],'YYYY/MM/DD',true);
	                            var posDate=moment(posDateAr[2]+'/'+posDateAr[1]+'/'+posDateAr[0],'YYYY/MM/DD',true);
	                            
	                            //return m.isAfter(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0]) && m.isBefore(posDateAr[2]+'/'+posDateAr[1]+'/'+posDateAr[0]);
	                            return (posDate >=trDate);
	                        }
	                    }
	              }
	          },
			//for detail edit row dynamic
	          <c:forEach items="${dynamicFieldsListBODetail}" var="dynamicDetailfield">
	          <c:choose>
	          <c:when test="${not dynamicDetailfield.fixed and dynamicDetailfield.required}">
		          <c:choose>
		          <c:when test="${dynamicDetailfield.fieldTypeName == 'textbox' }">
		          		<c:choose>
		          		<c:when test="${dynamicDetailfield.dataTypeName == 'Date'}">
		          	
		          		${dynamicDetailfield.pageFieldName}_S_${detId}: {
			                 validators: {
			                     notEmpty: {
			                   	   message: 'This field is required'
			                     },
			                     date: {
			                         format: 'DD-MM-YYYY',
			                         message: 'The date is not a valid'
			                     }
			                 }
			             },
		          		</c:when>
		          		<c:otherwise>
		          		${dynamicDetailfield.pageFieldName}_S_${detId}: {
			                validators: {
			                    notEmpty: {
			                  	   message: 'This field is required'
			                    }
			                }
			            },
		          		</c:otherwise>
		          		</c:choose>
		          </c:when>
		          <c:otherwise>
			          <c:when test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
			          ${dynamicDetailfield.pageFieldName}_S_${detId}: {
			              validators: {
			                  notEmpty: {
			                	   message: 'This field is required'
			                  }
			              }
			          },
			          </c:when>
		          </c:otherwise>
		          </c:choose>
	          </c:when>
	          </c:choose>
	          </c:forEach>
	          
	          
	          </c:forEach>
	          
	          //For header dynamic
	          <c:forEach items="${dynamicFieldsListBOHeader}" var="dynamicHeaderfield">
	          <c:choose>
	          <c:when test="${not dynamicHeaderfield.fixed and dynamicHeaderfield.required}">
		          <c:choose>
		          <c:when test="${dynamicHeaderfield.fieldTypeName == 'textbox' }">
		          		<c:choose>
		          		<c:when test="${dynamicHeaderfield.dataTypeName == 'Date'}">
		          	
			          	${dynamicHeaderfield.pageFieldName}: {
			                 validators: {
			                     notEmpty: {
			                   	   message: 'This field is required'
			                     },
			                     date: {
			                         format: 'DD-MM-YYYY',
			                         message: 'The date is not a valid'
			                     }
			                 }
			             },
		          		</c:when>
		          		<c:otherwise>
			          	${dynamicHeaderfield.pageFieldName}: {
			                validators: {
			                    notEmpty: {
			                  	   message: 'This field is required'
			                    }
			                }
			            },
		          		</c:otherwise>
		          		</c:choose>
		          </c:when>
		          <c:otherwise>
			          <c:when test="${dynamicHeaderfield.fieldTypeName == 'textarea'}">
			          ${dynamicHeaderfield.pageFieldName}: {
			              validators: {
			                  notEmpty: {
			                	   message: 'This field is required'
			                  }
			              }
			          },
			          </c:when>
		          </c:otherwise>
		          </c:choose>
	          </c:when>
	          </c:choose>
	          </c:forEach>
	          
	          //For detail new row dynamic
	          <c:forEach items="${dynamicFieldsListBODetail}" var="dynamicDetailfield">
	          <c:choose>
	          <c:when test="${not dynamicDetailfield.fixed and dynamicDetailfield.required}">
		          <c:choose>
		          <c:when test="${dynamicDetailfield.fieldTypeName == 'textbox' }">
		          		<c:choose>
		          		<c:when test="${dynamicDetailfield.dataTypeName == 'Date'}">
		          		${dynamicDetailfield.pageFieldName}_0: {
			                 validators: {
			                     notEmpty: {
			                   	   message: 'This field is required'
			                     },
			                     date: {
			                         format: 'DD-MM-YYYY',
			                         message: 'The date is not a valid'
			                     }
			                 }
			             },
		          		</c:when>
		          		<c:otherwise>
		          		${dynamicDetailfield.pageFieldName}_0: {
			                validators: {
			                    notEmpty: {
			                  	   message: 'This field is required'
			                    }
			                }
			            },
		          		</c:otherwise>
		          		</c:choose>
		          </c:when>
		          <c:otherwise>
		          	<c:choose>
			          <c:when test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
			          ${dynamicDetailfield.pageFieldName}_0: {
			              validators: {
			                  notEmpty: {
			                	   message: 'This field is required'
			                  }
			              }
			          },
			          </c:when>
		          	</c:choose>
		          </c:otherwise>
		          </c:choose>
	          </c:when>
	          </c:choose>
	          </c:forEach>
	          
	          <c:forEach var="li_row_id" items="${header_info.packingRowIdList}" >
	         
	          packing_size_range_${li_row_id}: {
	              validators: {
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                    	//  jQuery('#abo_form').bootstrapValidator('revalidateField', 'packing_type_${li_row_id}');
	                      	if (($("#packing_size_range_id_${li_row_id}").val()==0 && $("#packing_size_range_${li_row_id}").val()!='') || (jQuery('#packing_type_${li_row_id}').val()!='' && $("#packing_size_range_id_${li_row_id}").val()==0)) {
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
	          packing_type_${li_row_id}:{
	        	  validators: {
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                    	  jQuery('#abo_form').bootstrapValidator('revalidateField', 'packing_size_range_${li_row_id}');
	                      	if ($("#packing_size_range_id_${li_row_id}").val()!=0 && $("#packing_type_${li_row_id}").val()=='') {
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
	          </c:forEach>
	          
	          mrp_S_${detId}:{
	        	  validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          }
	         
	        
	      
	  }
});
 
 
 
 </script>
 <script>
//Terms and conditions validation
 <c:choose>
 	<c:when test="${not empty header_info.tcList}">
 		<c:forEach var="tcVal" items="${header_info.tcList}">
 		$('#abo_form').bootstrapValidator('addField', 'tc_slno_${tcVal.sl}', {
 			 validators: {
 		       numeric: {
 		           message: 'Enter number'
 		       }
 		   }
 		});
 		</c:forEach>
 		</c:when>
 		
 		<c:otherwise>
 		
 		$('#abo_form').bootstrapValidator('addField', 'tc_slno_1', {
 			 validators: {
 		        numeric: {
 		            message: 'Enter number'
 		        }
 		    }
 		  });
 		</c:otherwise>
 </c:choose>
 </script>
 <script>
 
 
//---------------------------FIXED HEDER TABLE WITH AUTO RESIZE COLUMN

 var resizeCol = function() {
   var tiw = $('.table-body .table').width();
   $('.table-head').width(tiw);
   $('#data-table tr:first').children().each(function(index, element) {
     var w = $(this).width(),i = $(this).index();
   
  
   // w=w+2;

     $('#head-table th:eq(' + i + ')').width(w);
    // alert('w :'+w+' i:'+i);
   });
 }
 //resizeCol();
 $(window).on("resize", function() {
   resizeCol();
 });

 var scrollTarget = function() {
   var target = $(".table-header");
   $(".table-body").scroll(function() {
     target.prop("scrollLeft", this.scrollLeft);
   });
 }



 $tableInner = $('.table-body');
 $tableInner.attr('style', 'overflow: auto; width: auto; max-height: 460px');

 resizeCol();
 scrollTarget();

 //---------------------------FIXED HEDER TABLE WITH AUTO RESIZE COLUMN END
 
 
 !function ($) {
	 
		$(function(){
			
			$('#currency_name').listCurrency({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCurrency&create_new=1",
			  nameField :'#currency_name',
			  idField:'#currency_id',
			  formId:'#abo_form',
			  validateId:'currency_name'
		  	});
			
			$('#buyer_name').listCustomer({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=1",
				  nameField :'#buyer_name',
				  idField:'#buyer_id',
				  formId:'#abo_form',
				  validateId:'buyer_name'
			  });
			
			$('#agent_name').listAgent({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetAgent&create_new=1",
				  nameField :'#agent_name',
				  idField:'#agent_id',
				  formId:'#abo_form',
				  validateId:'agent_name'
			  });	
			
			$('#season_name').listSeason({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSeason&create_new=1",
				  nameField :'#season_name',
				  idField:'#season_id',
				  formId:'#abo_form',
				  validateId:'season_name'
			  });	
			
			<c:forEach var="detId" items="${det_id_list}">
			  $('#item_name_S_'+${detId}).listMaterial({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterialFG&create_new=1",
				  nameField :'#item_name_S_'+${detId},
				  idField:'#item_id_S_'+${detId},
				  formId:'#abo_form',
				  validateId:'item_name_S_'+${detId}
			  }); 
			  
			  $('#variant_name_S_'+${detId}).listVariant({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
				  nameField :'#variant_name_S_'+${detId},
				  idField:'#variant_id_S_'+${detId},
				  formId:'#abo_form',
				  validateId:'variant_name_S_'+${detId}
			  });
			  
			  $('#size_range_S_'+${detId}).listSizeRange({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
				  nameField :'#size_range_S_'+${detId},
				  idField:'#size_range_id_S_'+${detId},
				  formId:'#abo_form',
				  validateId:'size_range_S_'+${detId}
			  });
			  </c:forEach>
			  
			  $('#item_name_0').listMaterial({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterialFG&create_new=1",
				  nameField :'#item_name_0',
				  idField:'#item_id_0',
				  formId:'#abo_form',
				  validateId:'item_name_0'
			  }); 
			  
			  $('#variant_name_0').listVariant({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
				  nameField :'#variant_name_0',
				  idField:'#variant_id_0',
				  formId:'#abo_form',
				  validateId:'variant_name_0'
			  });
			  
			  $('#size_range_0').listSizeRange({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
				  nameField :'#size_range_0',
				  idField:'#size_range_id_0',
				  formId:'#abo_form',
				  validateId:'size_range_0'
			  });
			  
			  <c:forEach var="li_row_id" items="${header_info.packingRowIdList}" >
			  $('#packing_size_range_${li_row_id}').listSizeRange({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
				  nameField :'#packing_size_range_${li_row_id}',
				  idField:'#packing_size_range_id_${li_row_id}',
				  formId:'#abo_form',
				  validateId:'packing_size_range_${li_row_id}'
			  });
			  </c:forEach>
		 });
		   
		
}(window.jQuery);	
 
$(document).ready(function () {
	jQuery('#packing_panel_togg').click(function () {
		jQuery('#packing_panel').toggle();
		if(jQuery('#packing_panel').is(":visible")){
			jQuery('#packing_panel_togg').removeClass("glyphicon-plus-sign").addClass("glyphicon-minus-sign");	
		} 
		else{
			jQuery('#packing_panel_togg').removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
			
		}
	});
	
	jQuery('#carton_panel_0_togg').click(function () {
		jQuery('#cortan_panel_0').toggle();
		if(jQuery('#cortan_panel_0').is(":visible")){
			jQuery('#carton_panel_0_togg').removeClass("glyphicon-plus-sign").addClass("glyphicon-minus-sign");	
			jQuery('#size_grid_0').hide();
		} 
		else{
			jQuery('#carton_panel_0_togg').removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
			jQuery('#size_grid_0').show();
			
		}
	});
	
	<c:forEach var="detId" items="${det_id_list}">
	
	jQuery('#carton_panel_${detId}_togg').click(function () {
		jQuery('#cortan_panel_${detId}').toggle();
		if(jQuery('#cortan_panel_${detId}').is(":visible")){
			jQuery('#carton_panel_${detId}_togg').removeClass("glyphicon-plus-sign").addClass("glyphicon-minus-sign");
			jQuery('#size_grid_${detId}').hide();
		} 
		else{
			jQuery('#carton_panel_${detId}_togg').removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
			jQuery('#size_grid_${detId}').show();
			
		}
	});
	
	</c:forEach>
});
 
 </script>
 
 <script>
 /* var thisFormId= $(this).closest("form").attr('id');
Mousetrap.bind('alt+b', function(e, combo) {
	alert('#'+thisFormId+"_back");
	jQuery('#'+thisFormId+"_back").click();
    console.log(combo); // logs 'ctrl+shift+up'
}); */
	runShortKey('abo_form');
</script>
 

	</form>
	
	<jsp:include page="../common/AutoNoGeneration.jsp" >
		<jsp:param value="Sales Order" name="auto_modal_label"/>
		<jsp:param value="so_no" name="auto_header_no_column"/>
		<jsp:param value="bo_prefix" name="page_prefix_id"/>
		<jsp:param value="bo_no" name="page_auton_no_id"/>
		<jsp:param value="so_id" name="auto_header_id_column"/>
		<jsp:param value="sales_order" name="auto_header_table_name"/>
		<jsp:param value="set_autono_sales_order" name="auto_autono_table_name"/>
	</jsp:include>
	
	
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>	
</body>
</html>