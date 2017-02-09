
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

  <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title><fmt:message bundle="${bundle}"   key="Title.Title"/></title>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/BOM.js"></script>
<jsp:include page="../common/Header.jsp"/>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/checkbox/checkbox.css" /> --%>

<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}


label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0px;
    font-weight: 700;
}

.form-group{
margin-bottom:4px;
}
 
.form-control{
	height:25px;
	font-size: 11px;
}
.input-group-addon {
    padding: 4px 8px;
    font-size: 12px;
}
.required-field-block .required-icon:after{
    border-bottom: 25px solid transparent;
}
    
</style>
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
<!-- <style type="text/css">
#header label {
    padding: 3px 0px;
}
</style> -->
</head>
<jsp:include page="../common/ValidateUser.jsp"/>

<body >
<form action=""  role="form" id="validate-form" method="post"  class="trans-form" autocomplete="off">



<div id="wrapper">




 <jsp:include page="../common/MainMenu.jsp"> 
 <jsp:param value="2" name="screen_type"/>
		<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name"/>
		</jsp:include>
	
	


 
<div class="top-bar">
 <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" >BOM</h2>
                </div>
           </div>
<div id="page-wrapper" class="add-top-wrapper"> 
   <div id="page-inner">   
                
<div class="row" >

<div class="col-sm-3">
		 <div class="form-group">
                    <label for="customer">BOM No</label>
		    <div class="required-field-block">
                    <input class="form-control" id="bom_no" name="bom_no" size="30" type="text" value="${header_info.bomNo}" placeholder="Type and select BOM No">
		    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
		</div>


<div class='col-sm-3'  >
					<div class='form-group'>
				<label for="tr_date">BOM Date</label>
					                 <div class='input-group date'  >
							        <div class="required-field-block">
								    <input type='text' name="tr_date" id="tr_date"   class="form-control" <c:choose><c:when test="${new_page=='yes'}">value="${tr_date}"</c:when><c:otherwise>value="${header_info.bomDate}"</c:otherwise></c:choose>   placeholder="Select BOM Date" />
								    <div class="required-icon">
								    <div class="text">*</div>
							        </div>
							        </div>
								    <span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								    </span>
					                </div>
					                
					                   <script type="text/javascript">
				
									      jQuery('#tr_date').datepicker({
												    format: "dd-mm-yyyy"
												}).on({
													changeDate: function(event) {
								             			event.preventDefault();
								             			event.stopPropagation();
								             		// Revalidate the date field
								             			 jQuery('#validate-form').bootstrapValidator('revalidateField', 'tr_date');
									        		},
									        		changeMonth: function(event) {
								             			event.preventDefault();
								             			event.stopPropagation();
								        			},
								        			changeYear: function(e) {
								             			event.preventDefault();
								             			event.stopPropagation();
								        			}
							        			});
						
					        		</script>
					        		</div>
					        		</div>
					        		
					        	<%-- 	<c:if test="${header_info.isMultipleBranch eq 'Yes' }">
					        		<div class='col-sm-3'  >
					<div class='form-group'>
				<label for="branch">Branch</label>
				  <div class="required-field-block">
             <input class="form-control" id="branch_name"  name="branch_name" maxlength="100" size="30" type="text" value="<c:out value="${header_info.branchName}"/>" placeholder="Type and select Branch"/>
			 <input type="hidden" class="form-control" name="branch_id" id="branch_id" value="${header_info.branchId}"/>	
			     <div class="required-icon">
								    <div class="text">*</div>
							        </div>
							        </div>
			        </div>
			        
			         <div class="checkbox">  
			           <input class="checkbox_1" type="checkbox" id="make_as_default" name="make_as_default" value="1"  <c:if test="${header_info.defaultBranch eq '1'}">checked</c:if>  ><label for="make_as_default" class="checkbox_1">Make this as my default branch</label>
			           </div>
			           
			        </div>
			        
			           </c:if> --%>
<!-- <div class='col-sm-3 pull-right' style="position: absolute;top: 53px;right: 0;">
	<div class='form-group'>
		<div style="text-align:right;font-weight:bold;">Balance due</div>
		<div class="corner-label" id="bal_due"></div>
	</div>
</div> -->
									        		
</div> 
</div>
</div>
</div>
    
<div id="page-wrapper" style="margin-top: 0;"> 
        <div id="page-inner">   
<div class="row">                
<div class='col-sm-3'  >
					<div class='form-group'>
				<label for="customer">Customer</label>
             <input class="form-control" id="customer" name="customer"  size="30" maxlength="100" type="text" value="<c:out value="${header_info.customer}"/>" placeholder="Type and select Customer"/>
			 <input type="hidden" class="form-control" name="customer_id" id="customer_id" value="${header_info.customerId}"/>	
			        </div>
			        </div>
			        
			        <div class='col-sm-3'  >
					<div class='form-group'>
				<label >Parent BOM</label>
             	<input class="form-control" id="parent_bom_no" name="parent_bom_no" maxlength="250"  size="30" type="text" value="${header_info.parentBomNo}"  placeholder="Type and select Parent BOM"/>
             	 <input type="hidden" class="form-control" name="parent_bom_id" id="parent_bom_id" value="${header_info.parentBomId}"/>	
			        </div>
			        </div>      
			     
			        </div>   
			        
			        


<div class="row">
<div class='col-sm-3'  >
<div class='form-group'>
<label >Product</label>
	  <div class="required-field-block">
             <input class="form-control" id="product" name="product"  size="30" maxlength="100" type="text" value="<c:out value="${header_info.product}"/>"  placeholder="Type and select Product"/>
			 <input type="hidden" class="form-control" name="product_id" id="product_id" value="${header_info.productId}"/>	
			    <div class="required-icon">
				    <div class="text">*</div>
			        </div>
			        </div>
</div>            
</div>




</div>


       

   
<!--  --------------------------------------------------Header Part Starts with Dynamic Fields-------------------------------------------------------------------------------------------- -->
		<div class='row' >
			<%-- <c:forEach items="${dynamicFieldsListBOMHeader}" var="dynamicHeaderfield" >
				<div class='col-sm-3'  >
					<div class='form-group'>
			         	<%@ include file="../dynamic/EnquiryHeaderControls.jsp" %> 
					</div>
				</div>
				</c:forEach> --%>

						<c:forEach items="${dynamicFieldsListBOMHeader}"
							var="dynamicHeaderfield">
							<c:choose>
								<c:when test="${dynamicHeaderfield.fixed}">
									<c:set var="ControlName"
										value="${dynamicHeaderfield.pageFieldName}" />
									<c:choose>
										<c:when test="${ControlName=='reference_no'}">
											<div class='col-sm-3'>
												<div class='form-group'>
													<label for="ref_no">${dynamicHeaderfield.displayName}</label>
													<input class="form-control" id="ref_no" name="ref_no"
														size="30" maxlength="${dynamicHeaderfield.length}"
														type="text" value="${header_info.referenceNo}"
														placeholder="Enter ${dynamicHeaderfield.displayName}" />
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<c:choose>

												<c:when test="${ControlName=='version_no'}">
													<div class='col-sm-3'>
														<div class='form-group'>
															<label for="ver_no">${dynamicHeaderfield.displayName}</label>
															<input class="form-control" id="version_no"
																name="version_no" size="30"
																maxlength="${dynamicHeaderfield.length}" type="text"
																value="${header_info.versionNo}"
																placeholder="Enter ${dynamicHeaderfield.displayName}" />
														</div>
													</div>
												</c:when>


											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:when>
							<c:otherwise>

								<c:set var="strEvent" value="" />
								<c:set var="FieldId"
									value="${dynamicHeaderfield.dynamicFieldId}" />

								<c:forEach var="events" items="${dynamicHeaderFieldEvents}">
									<c:if test="${FieldId==events.dynamicFieldId}">
										<c:choose>
											<c:when test="${events.eventName=='onkeypress'}">
												<c:set var="strEvent"
													value=" ${strEvent}${events.eventName}='return ${events.functionName};'" />
											</c:when>
											<c:otherwise>
												<c:set var="strEvent"
													value=" ${strEvent}${events.eventName}='${events.functionName};'" />
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>

								<c:set var="picklistValueExists" value="false" />
								<c:if test="${not empty dynamicHeaderpickListOptions}">
									<c:forEach var="pickListOptions"
										items="${dynamicHeaderpickListOptions}">
										<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
											<c:set var="picklistValueExists" value="true" />
										</c:if>
									</c:forEach>
								</c:if>


								<c:choose>
									<c:when test="${dynamicHeaderfield.fieldTypeName == 'textbox'}">
										<c:choose>
											<c:when test="${dynamicHeaderfield.dataTypeName == 'Date'}">
												<div class='col-sm-3'>
													<div class='form-group'>
														<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
														<div class='input-group date'
															id='${dynamicHeaderfield.pageFieldName}'>
															<c:choose>
																<c:when test="${dynamicHeaderfield.required}">
																	<div class="required-field-block">
																		<input type='text'
																			maxlength="${dynamicHeaderfield.length}"
																			name="${dynamicHeaderfield.pageFieldName}"
																			id="${dynamicHeaderfield.pageFieldName}"
																			value="${dynamicHeaderfield.fieldValue}"
																			class="form-control"
																			placeholder="Select ${dynamicHeaderfield.displayName}"
																			${strEvent} />
																		<div class="required-icon">
																			<div class="text">*</div>
																		</div>
																	</div>
																	<span class="input-group-addon"> <span
																		class="glyphicon glyphicon-calendar"></span>
																	</span>
																</c:when>
																<c:otherwise>
																	<input type='text'
																		maxlength="${dynamicHeaderfield.length}"
																		name="${dynamicHeaderfield.pageFieldName}"
																		id="${dynamicHeaderfield.pageFieldName}"
																		value="${dynamicHeaderfield.fieldValue}"
																		class="form-control"
																		placeholder="Select ${dynamicHeaderfield.displayName}"
																		${strEvent} />
																	<span class="input-group-addon"> <span
																		class="glyphicon glyphicon-calendar"></span>
																	</span>
																</c:otherwise>
															</c:choose>


														</div>
													</div>
												</div>
												<script type="text/javascript">
				
									      jQuery('#${dynamicHeaderfield.pageFieldName}').datepicker({
												    format: "dd-mm-yyyy"
												});  
						
					        		</script>
											</c:when>
											<c:otherwise>
												<div class='col-sm-3'>
													<div class='form-group'>
														<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
														<c:choose>
															<c:when test="${dynamicHeaderfield.required}">
																<div class="required-field-block">
																	<input class="form-control"
																		id="${dynamicHeaderfield.pageFieldName}"
																		name="${dynamicHeaderfield.pageFieldName}"
																		value="${dynamicHeaderfield.fieldValue}"
																		placeholder="Enter ${dynamicHeaderfield.displayName}"
																		maxlength="${dynamicHeaderfield.length}" type="text"
																		size="30" ${strEvent} />
																	<div class="required-icon">
																		<div class="text">*</div>
																	</div>
																</div>
															</c:when>
															<c:otherwise>
																<input class="form-control"
																	id="${dynamicHeaderfield.pageFieldName}"
																	name="${dynamicHeaderfield.pageFieldName}"
																	value="${dynamicHeaderfield.fieldValue}"
																	placeholder="Enter ${dynamicHeaderfield.displayName}"
																	maxlength="${dynamicHeaderfield.length}" type="text"
																	size="30" ${strEvent} />
															</c:otherwise>
														</c:choose>
													</div>
												</div>

											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when
										test="${dynamicHeaderfield.fieldTypeName == 'textarea'}">
										<div class='col-sm-3'>
											<div class='form-group'>
												<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
												<c:choose>
													<c:when test="${dynamicHeaderfield.required}">
														<div class="required-field-block">
															<textarea id="${dynamicHeaderfield.pageFieldName}"
																name="${dynamicHeaderfield.pageFieldName}" type="text"
																placeholder="Enter ${dynamicHeaderfield.displayName}"
																class="form-control" rows="2" cols="18" ${strEvent}
																onKeyUp="limitText(this.form.${dynamicHeaderfield.pageFieldName},${dynamicHeaderfield.length});">${dynamicHeaderfield.fieldValue}</textarea>
															<div class="required-icon">
																<div class="text">*</div>
															</div>
														</div>
													</c:when>
													<c:otherwise>
														<textarea id="${dynamicHeaderfield.pageFieldName}"
															name="${dynamicHeaderfield.pageFieldName}" type="text"
															placeholder="Enter ${dynamicHeaderfield.displayName}"
															class="form-control" rows="2" cols="18" ${strEvent}
															onKeyUp="limitText(this.form.${dynamicHeaderfield.pageFieldName},${dynamicHeaderfield.length});">${dynamicHeaderfield.fieldValue}</textarea>
													</c:otherwise>
												</c:choose>
											</div>
										</div>


									</c:when>
									<c:when
										test="${dynamicHeaderfield.fieldTypeName == 'combobox'}">
										<div class='col-sm-3'>
											<div class='form-group'>
												<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
												<c:choose>
													<c:when test="${dynamicHeaderfield.required}">
														<div class="required-field-block">
															<c:choose>
																<c:when test="${picklistValueExists=='false'}">
																	<select class="form-control"
																		id="${dynamicHeaderfield.pageFieldName}"
																		name="${dynamicHeaderfield.pageFieldName}" ${strEvent}>
																		<c:choose>
																			<c:when test="${dynamicHeaderfield.fieldValue eq ''}">
																				<option value="Select">----Select----</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${dynamicHeaderfield.fieldValue}">${dynamicHeaderfield.fieldValue}</option>
																			</c:otherwise>
																		</c:choose>
																	</select>
																</c:when>
																<c:otherwise>
																	<select class="form-control"
																		id="${dynamicHeaderfield.pageFieldName}"
																		name="${dynamicHeaderfield.pageFieldName}" ${strEvent}>
																		<c:set var="SelectedValue"
																			value="${dynamicHeaderfield.fieldValue}" />
																		<c:forEach var="pickListOptions"
																			items="${dynamicHeaderpickListOptions}">
																			<c:if
																				test="${FieldId==pickListOptions.dynamicFieldId}">
																				<c:choose>
																					<c:when
																						test="${SelectedValue==pickListOptions.pickListOption}">
																						<option value="${pickListOptions.pickListOption}"
																							selected="selected"><c:out
																								value="${pickListOptions.pickListOption}" /></option>
																					</c:when>
																					<c:otherwise>
																						<option value="${pickListOptions.pickListOption}"><c:out
																								value="${pickListOptions.pickListOption}" /></option>
																					</c:otherwise>
																				</c:choose>
																			</c:if>
																		</c:forEach>
																	</select>
																</c:otherwise>
															</c:choose>
															<div class="required-icon">
																<div class="text">*</div>
															</div>
														</div>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${picklistValueExists=='false'}">
																<select class="form-control"
																	id="${dynamicHeaderfield.pageFieldName}"
																	name="${dynamicHeaderfield.pageFieldName}" ${strEvent}>
																	<c:choose>
																		<c:when test="${dynamicHeaderfield.fieldValue eq ''}">
																			<option value="Select">----Select----</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${dynamicHeaderfield.fieldValue}">${dynamicHeaderfield.fieldValue}</option>
																		</c:otherwise>
																	</c:choose>
																</select>
															</c:when>
															<c:otherwise>
																<select class="form-control"
																	id="${dynamicHeaderfield.pageFieldName}"
																	name="${dynamicHeaderfield.pageFieldName}" ${strEvent}>
																	<c:set var="SelectedValue"
																		value="${dynamicHeaderfield.fieldValue}" />
																	<c:forEach var="pickListOptions"
																		items="${dynamicHeaderpickListOptions}">
																		<c:if
																			test="${FieldId==pickListOptions.dynamicFieldId}">
																			<c:choose>
																				<c:when
																					test="${SelectedValue==pickListOptions.pickListOption}">
																					<option value="${pickListOptions.pickListOption}"
																						selected="selected"><c:out
																							value="${pickListOptions.pickListOption}" /></option>
																				</c:when>
																				<c:otherwise>
																					<option value="${pickListOptions.pickListOption}"><c:out
																							value="${pickListOptions.pickListOption}" /></option>
																				</c:otherwise>
																			</c:choose>
																		</c:if>
																	</c:forEach>
																</select>
															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose>
											</div>
										</div>

									</c:when>
									<c:when
										test="${dynamicHeaderfield.fieldTypeName == 'radio button'}">
										<div class='col-sm-3'>
											<div class='form-group'>
												<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
												<div class="radio">
													<c:choose>
														<c:when test="${picklistValueExists=='false'}">
															<div id="${dynamicHeaderfield.pageFieldName}" ${strEvent}></div>
														</c:when>
														<c:otherwise>
															<c:set var="SelectedValue"
																value="${dynamicHeaderfield.fieldValue}" />
															<c:forEach var="pickListOptions"
																items="${dynamicHeaderpickListOptions}">
																<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																	<c:choose>
																		<c:when
																			test="${fn:contains(SelectedValue,pickListOptions.pickListOption)==true}">
																			<input id="${dynamicHeaderfield.pageFieldName}"
																				name="${dynamicHeaderfield.pageFieldName}"
																				value="${pickListOptions.pickListOption}"
																				type="radio" checked="checked" ${strEvent}>
																			<c:out value="${pickListOptions.pickListOption}" />
																			</input>
																		</c:when>
																		<c:otherwise>
																			<input id="${dynamicHeaderfield.pageFieldName}"
																				name="${dynamicHeaderfield.pageFieldName}"
																				value="${pickListOptions.pickListOption}"
																				type="radio" ${strEvent}>
																			<c:out value="${pickListOptions.pickListOption}" />
																			</input>
																		</c:otherwise>
																	</c:choose>
																</c:if>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>

									</c:when>
									<c:when
										test="${dynamicHeaderfield.fieldTypeName == 'checkbox'}">
										<div class='col-sm-3'>
											<div class='form-group'>
												<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
												<div class="checkbox">
													<c:choose>
														<c:when test="${picklistValueExists=='false'}">
															<div id="${dynamicHeaderfield.pageFieldName}" ${strEvent}></div>
														</c:when>
														<c:otherwise>
															<c:set var="SelectedValue"
																value="${dynamicHeaderfield.fieldValue}" />
															<c:forEach var="pickListOptions"
																items="${dynamicHeaderpickListOptions}">
																<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																	<c:choose>
																		<c:when
																			test="${fn:contains(SelectedValue,pickListOptions.pickListOption)==true}">
																			<input id="${dynamicHeaderfield.pageFieldName}"
																				name="${dynamicHeaderfield.pageFieldName}"
																				value="${pickListOptions.pickListOption}"
																				type="checkbox" checked="checked" ${strEvent}>
																			<c:out value="${pickListOptions.pickListOption}" />
																			</input>
																		</c:when>
																		<c:otherwise>
																			<input id="${dynamicHeaderfield.pageFieldName}"
																				name="${dynamicHeaderfield.pageFieldName}"
																				value="${pickListOptions.pickListOption}"
																				type="checkbox" ${strEvent}>
																			<c:out value="${pickListOptions.pickListOption}" />
																			</input>
																		</c:otherwise>
																	</c:choose>
																</c:if>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
<!--  --------------------------------------------------Header Part End-------------------------------------------------------------------------------------------- -->
       
       
       
    <!--  --------------------------------------------------Detail Part Starts with Dynamic Fields-------------------------------------------------------------------------------------------- -->   
          	<div class='row trans-det-row'  >
          	<div class='col-md-0-5 col-xs-12' id="header">
			<label >#</label>
			</div>
	<c:forEach items="${dynamicFieldsListBOMDetails}" var="dynamicDetailfield" >
				<c:choose>
					<c:when test="${dynamicDetailfield.fixed}">
						<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
							<c:choose>
									<c:when test="${ControlName eq 'component_id'}">
										<div class='col-md-2 col-xs-12' id="header">
											<label for="customer">${dynamicDetailfield.displayName}</label>
										</div>
									</c:when>
									<c:otherwise>
								<c:choose>
									<c:when test="${ControlName eq 'material_id'}">
										<div class='col-md-2 col-xs-12' id="header">
											<label for="customer">${dynamicDetailfield.displayName}</label>
										</div>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${ControlName=='variant_id'}">
												<div class='col-md-1-5 col-xs-12' id="header">
													<label for="customer">${dynamicDetailfield.displayName}</label>
												</div>
											</c:when>

											<c:otherwise>
												<c:choose>
													<c:when test="${ControlName=='UOM'}">
														<div class='col-md-1 col-xs-12' id="header">
															<label for="customer">${dynamicDetailfield.displayName}</label>
														</div>
													</c:when>

													<c:otherwise>
														<c:choose>
															<c:when test="${ControlName=='operation_id'}">
																<div class='col-md-1-5 col-xs-12' id="header">
																	<label for="customer">${dynamicDetailfield.displayName}</label>
																</div>
															</c:when>

															<c:otherwise>
																<c:choose>
																	<c:when test="${ControlName=='required_qty'}">
																		<div class='col-md-2 col-xs-12' id="header">
																			<label for="customer">${dynamicDetailfield.displayName}</label>
																		</div>
																	</c:when>

																	<c:otherwise>
																		<c:choose>
																			<c:when test="${ControlName=='wastage_per'}">
																				<div class='col-md-1 col-xs-12' id="header">
																					<label for="customer">${dynamicDetailfield.displayName}</label>
																				</div>
																			</c:when>

																			<c:otherwise>
																					<c:choose>
																						<c:when
																							test="${ControlName=='costing_per'}">
																							<div class='col-md-1 col-xs-12'
																								id="header">
																								<label for="customer">${dynamicDetailfield.displayName}</label>
																							</div>
																						</c:when>
																						<c:otherwise>
																							<c:choose>
																								<c:when
																									test="${ControlName=='purchase_per'}">
																									<div
																										class='col-md-1 col-xs-12'
																										id="header">
																										<label for="customer">${dynamicDetailfield.displayName}</label>
																									</div>
																								</c:when>
																									<c:otherwise>
																							<c:choose>
																								<c:when
																									test="${ControlName=='issue_per'}">
																									<div
																										class='col-md-1 col-xs-12'
																										id="header">
																										<label >${dynamicDetailfield.displayName}</label>
																									</div>
																								</c:when>
																									<c:otherwise>
																							<c:choose>
																								<c:when
																									test="${ControlName=='supplier_id'}">
																									<div
																										class='col-md-1-5 col-xs-12'
																										id="header">
																										<label >${dynamicDetailfield.displayName}</label>
																									</div>
																								</c:when>
																								<c:otherwise>
																							<c:choose>
																								<c:when
																									test="${ControlName=='usage_area'}">
																									<div
																										class='col-md-1 col-xs-12'
																										id="header">
																										<label >${dynamicDetailfield.displayName}</label>
																									</div>
																								</c:when>
																									<c:otherwise>
																							<c:choose>
																								<c:when
																									test="${ControlName=='size_schedule_id'}">
																									<div
																										class='col-md-2 col-xs-12'
																										id="header">
																										<label >${dynamicDetailfield.displayName}</label>
																									</div>
																								</c:when>
																								
																							</c:choose>
																						</c:otherwise>
																							</c:choose>
																						</c:otherwise>
																							</c:choose>
																						</c:otherwise>
																							</c:choose>
																						</c:otherwise>
																							</c:choose>
																						</c:otherwise>
																					</c:choose>
																				</c:otherwise>
																				</c:choose>
																			</c:otherwise>
																		</c:choose>
																	</c:otherwise>
																</c:choose>
															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
											</c:choose>
											</c:otherwise>
										</c:choose>
									<%-- </c:otherwise>
								</c:choose> --%>
					</c:when> 
								<c:otherwise>
									
									<!-- Header for the dynamic controls -->
									<div class='col-md-2 col-xs-12' id="header">
										<label for="customer">${dynamicDetailfield.displayName}</label>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				
				<div class='col-md-0-5 col-xs-12' id="header">
								 <label >&nbsp;</label>
			    </div>  
				
            </div>
        
        
               <!-- ----------------------------------------- -->
                 <c:set var="det_map" value="${detMapList}"/>
               <%
						Map<String,String> dtMap=(Map<String,String>)pageContext.getAttribute("det_map");
               %>
              <c:set var="k" value="${1}"/>
					<c:forEach var="det" items="${detList}">
					
					
						<div class='row linerow'>
 									<div class='col-xs-0-5 col-xs-12' >
									 <div class='form-group grid-slno' >${k}</div>
			    					</div>
							<c:forEach items="${dynamicFieldsListBOMDetails}"
								var="dynamicDetailfield">
				<%-- 				--%>
								<c:choose>
									<c:when test="${dynamicDetailfield.fixed}">
										<c:set var="ControlName"
											value="${dynamicDetailfield.pageFieldName}" />
											
											<c:choose>
															<c:when test="${ControlName=='component_id'}">
															<div class='col-md-2 col-xs-12'>
																<div class='form-group'>
																  
												                 <input class="form-control "
																		name="component_S_${det.bomDetailId}"
																		id="component_S_${det.bomDetailId}"
																		onblur="setEditedId('component_id_S_${det.bomDetailId}');"
																		value="${det.component}" type="text"
																		placeholder="${dynamicDetailfield.displayName}" />
																		<input type="hidden" name="component_id_S_${det.bomDetailId}" id="component_id_S_${det.bomDetailId}" value="${det.componentId}" />
																    
																</div>
																</div>
															</c:when>

															<c:otherwise> 
												<c:choose>
													<c:when test="${ControlName eq 'material_id'}">
														<div class='col-md-2 col-xs-12'>
														<div class='form-group'>
														   <div class="required-field-block">
														   			<input class="form-control"
																name="material_S_${det.bomDetailId}"
																id="material_S_${det.bomDetailId}"
																value="<c:out value="${det.material}"/>" maxlength="${dynamicDetailfield.length}" 
																onblur="getBOMMaterialData(document.getElementById('material_id_S_${det.bomDetailId}').value,${det.bomDetailId});setEditedId('mat_id_S_${det.bomDetailId}');" 
																
																type="text"
																placeholder="Type and select ${dynamicDetailfield.displayName}" />
															<input type="hidden" name="mat_id_S_${det.bomDetailId}"
																id="mat_id_S_${det.bomDetailId}" value="${det.materialId}" />
														     <div class="required-icon">
															<div class="text">*</div>
														     </div>
														     </div>
														     </div>
														</div>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${ControlName=='variant_id'}">
															<div class='col-md-1-5 col-xs-12'>
																<div class='form-group'>
																  
												                 <input class="form-control "
																		name="variant_S_${det.bomDetailId}"
																		id="variant_S_${det.bomDetailId}"
																		onblur="setEditedId('variant_id_S_${det.bomDetailId}');"
																		value="${det.variant}" type="text"
																		placeholder="${dynamicDetailfield.displayName}" />
																		<input type="hidden" name="variant_id_S_${det.bomDetailId}" id="variant_id_S_${det.bomDetailId}" value="${det.variantId}" />
																    
																</div>
																</div>
															</c:when>

															<c:otherwise>
																<c:choose>
																	<c:when test="${ControlName=='UOM'}">
																		<div class='col-md-1 col-xs-12'>
																		<div class='form-group'>
																		<div class="required-field-block">
																			<input type="text" class="form-control"
																				name="uom_S_${det.bomDetailId}" placeholder="Type and select ${dynamicDetailfield.displayName}"
																				id="uom_S_${det.bomDetailId}" maxlength="${dynamicDetailfield.length}" 
																				onblur="setEditedId('uom_id_S_${det.bomDetailId}');"
																				value="<c:out value="${det.UOM}"/>" /> <input type="hidden"
																				name="uom_id_S_${det.bomDetailId}"
																				id="uom_id_S_${det.bomDetailId}" 
																				value="${det.uomId}" placeholder="Type and select ${dynamicDetailfield.displayName}" />
																		 <div class="required-icon">
																	<div class="text">*</div>
																     </div>
																     </div>
																		</div>
																		</div>
																	</c:when>
																		<c:otherwise>
																			<c:choose>
																				<c:when test="${ControlName=='operation_id'}">
																				<div class='col-md-1-5 col-xs-12'>
																					<div class='form-group'>
																		      <div class="required-field-block">
															                   <input type="text" class="form-control"
																						 name="operation_S_${det.bomDetailId}"
																							id="operation_S_${det.bomDetailId}"  maxlength="${dynamicDetailfield.length}" 
																							onblur="setEditedId('operation_id_S_${det.bomDetailId}');"
																							value="<c:out value="${det.operation}"/>"  placeholder="${dynamicDetailfield.displayName}" />
																			     <input type="hidden" name="operation_id_S_${det.bomDetailId}" id="operation_id_S_${det.bomDetailId}" value="${det.operationId}"/>
																			     <div class="required-icon">
																				<div class="text">*</div>
																			     </div>
																			     </div>
																					</div>
																					</div>
																				</c:when>

																				<c:otherwise>
																					<c:choose>
																						<c:when test="${ControlName=='required_qty'}">
																							<div class='col-md-2 col-xs-12'>
																							<div class='form-group'>
																	                    <input type="text" class="form-control text-right"
																									name="required_qty_S_${det.bomDetailId}"
																									id="required_qty_S_${det.bomDetailId}"
																									onblur="setEditedId('required_qty_S_${det.bomDetailId}');"
																									value="${det.requiredQty}" placeholder="${dynamicDetailfield.displayName}" />
																								</div>
																							</div>
																						</c:when>

																						<c:otherwise>
																							<c:choose>
																								<c:when test="${ControlName=='wastage_per'}">
																									<div class='col-md-1 col-xs-12'>
																									<div class='form-group'>
																											<input class="form-control text-right"
																												name="wastage_per_S_${det.bomDetailId}"
																												id="wastage_per_S_${det.bomDetailId}"
																												onblur="setEditedId('wastage_per_S_${det.bomDetailId}');"  type="text"
																												value="${det.wastagePer}"
																												placeholder="${dynamicDetailfield.displayName}" /> 
																										</div>
																						
																					        			
																									</div>
																									</c:when>
																													<c:otherwise>
																														<c:choose>
																															<c:when test="${ControlName=='costing_per'}">
																																<div class='col-md-1 col-xs-12'>
																																<div class='form-group'>
																																	<input type="text" class="form-control text-right"
																																		name="costing_per_S_${det.bomDetailId}" maxlength="${dynamicDetailfield.length}" 
																																		id="costing_per_S_${det.bomDetailId}" value="<c:out value="${det.costingPer}"/>"
																																		placeholder="Enter ${dynamicDetailfield.displayName}" 
																																		onblur="setEditedId('costing_per_S_${det.bomDetailId}');" />
																																</div>
																																</div>
																															</c:when>
																															<c:otherwise>
																															
																															<c:choose>
																																<c:when
																																	test="${ControlName=='purchase_per'}">
																																	<div class='col-md-1 col-xs-12'>
																																<div class='form-group'>
																																	<input type="text" class="form-control text-right"
																																		name="purchase_per_S_${det.bomDetailId}" maxlength="${dynamicDetailfield.length}" 
																																		id="purchase_per_S_${det.bomDetailId}" value="<c:out value="${det.purchasePer}"/>"
																																		placeholder="Enter ${dynamicDetailfield.displayName}" 
																																		onblur="setEditedId('purchase_per_S_${det.bomDetailId}');" />
																																</div>
																																</div>
																																</c:when>
																																<c:otherwise>
																															<c:choose>
																																<c:when
																																	test="${ControlName=='issue_per'}">
																																	<div
																																		class='col-md-1 col-xs-12'>
																																		<div class='form-group'>
																																		<input type="text" 
																																			class="form-control text-right"
																																			value="${det.issuePer}"
																																			name="issue_per_S_${det.bomDetailId}"
																																			 onblur="setEditedId('issue_per_S_${det.bomDetailId}');"
																																			placeholder="${dynamicDetailfield.displayName}" 
																																			id="issue_per_S_${det.bomDetailId}" />
																																			
																																	</div>
																																	</div>
																																</c:when>
																																<c:otherwise>
																															<c:choose>
																																<c:when
																																	test="${ControlName=='supplier_id'}">
																																	<div
																																		class='col-md-1-5 col-xs-12'>
																																		<div class='form-group'>
																																		<input type="text" 
																																			class="form-control "
																																			value="${det.supplier}"
																																			name="supplier_S_${det.bomDetailId}"
																																			 onblur="setEditedId('supplier_id_S_${det.bomDetailId}');"
																																			placeholder="${dynamicDetailfield.displayName}" 
																																			id="supplier_S_${det.bomDetailId}" />
																																			 <input type="hidden" name="supplier_id_S_${det.bomDetailId}" id="supplier_id_S_${det.bomDetailId}" value="${det.supplierId}"/>
																																			
																																	</div>
																																	</div>
																																</c:when>
																																<c:otherwise>
																															<c:choose>
																																<c:when
																																	test="${ControlName=='usage_area'}">
																																	<div
																																		class='col-md-1 col-xs-12'>
																																		<div class='form-group'>
																																		<input type="text" 
																																			class="form-control text-right"
																																			value="${det.usageArea}"
																																			name="usage_area_S_${det.bomDetailId}"
																																			onblur="setEditedId('usage_area_S_${det.bomDetailId}');"
																																			placeholder="${dynamicDetailfield.displayName}" 
																																			id="usage_area_S_${det.bomDetailId}" />
																																			
																																	</div>
																																	</div>
																																</c:when>
																																	<c:otherwise>
																															<c:choose>
																																<c:when
																																	test="${ControlName=='size_schedule_id'}">
																																	<div
																																		class='col-md-2 col-xs-12'>
																																		<div class='form-group'>
																																		<input type="text" 
																																			class="form-control "
																																			value="${det.sizeShedule}"
																																			name="size_schedule_S_${det.bomDetailId}"
																																			onblur="setEditedId('size_schedule_id_S_${det.bomDetailId}');"
																																			placeholder="${dynamicDetailfield.displayName}" 
																																			id="size_schedule_S_${det.bomDetailId}" />
																																			
																																					<input type="hidden" value="${det.sizeSheduleId}" name="size_schedule_id_S_${det.bomDetailId}" id="size_schedule_id_S_${det.bomDetailId}" />
																																			
																																	</div>
																																	</div>
																																</c:when>
																															</c:choose> 
																														</c:otherwise>
																															</c:choose> 
																														</c:otherwise>
																															</c:choose> 
																														</c:otherwise>
																															</c:choose> 
																														</c:otherwise>
																															</c:choose>
																															</c:otherwise>
																														</c:choose>
																													</c:otherwise>
																												</c:choose>
																											</c:otherwise>
																										</c:choose>
																									</c:otherwise>
																							</c:choose>
																						</c:otherwise>
																					</c:choose>
																				</c:otherwise>
																			</c:choose>
																		</c:otherwise>
																				</c:choose>
																		</c:otherwise>
																</c:choose>
													<%-- 		</c:otherwise> --%>
										<%-- </c:choose> --%>

									</c:when>
									<c:otherwise>
									
									<c:set var="f_name" value="${dynamicDetailfield.dbFieldName}${det.bomDetailId}" />
													<%
														String fieldName=(String) pageContext.getAttribute("f_name");
														String value=dtMap.get(fieldName);
									
													%>
									
										<c:set var="strEvent" value="" />
										<c:set var="FieldId"
											value="${dynamicDetailfield.dynamicFieldId}" />

										<c:forEach var="events" items="${dynamicDetailFieldEvents}">
											<c:if test="${FieldId==events.dynamicFieldId}">
												<c:choose>
													<c:when test="${events.eventName=='onkeypress'}">
														<c:set var="strEvent"
															value=" ${strEvent}${events.eventName}='return ${events.functionName};'" />
													</c:when>
													<c:otherwise>
														<c:set var="strEvent"
															value=" ${strEvent}${events.eventName}='${events.functionName};'" />
													</c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach>

										<c:set var="picklistValueExists" value="false" />
										<c:if test="${not empty dynamicDetailpickListOptions}">
											<c:forEach var="pickListOptions"
												items="${dynamicDetailpickListOptions}">
												<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
													<c:set var="picklistValueExists" value="true" />
												</c:if>
											</c:forEach>
										</c:if>
										<!-- TEXTBOX for the dynamic controls -->
										
										<c:choose>
											<c:when test="${dynamicDetailfield.fieldTypeName eq 'textbox'}">
											
												<c:choose>
													<c:when test="${dynamicDetailfield.dataTypeName == 'Date'}">
														<div class='col-md-2 col-xs-12'>
														<div class='form-group'>
														<div class="input-daterange has-feedback"
															id='${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}'>
															<c:choose>
												               <c:when test="${dynamicDetailfield.required}">
														        <div class="required-field-block">
															 <input class="form-control"
																name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}" maxlength="${dynamicDetailfield.length}" 
																value="<%=value %>" 
																onblur="setEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');"  type="text"
																placeholder="dd-mm-yyyy" ${strEvent}/> <span
																class="glyphicon glyphicon-calendar form-control-feedback"></span>
															    <div class="required-icon">
															    <div class="text">*</div>
														        </div>
														        </div>
												               </c:when>
												               <c:otherwise>
												               <input class="form-control"
																name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}" maxlength="${dynamicDetailfield.length}" 
																value="<%=value %>" 
																onblur="setEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');"  type="text"
																placeholder="dd-mm-yyyy" ${strEvent}/> <span
																class="glyphicon glyphicon-calendar form-control-feedback"></span>
												               </c:otherwise>
												               </c:choose>
														
														</div>
														</div>
														<script type="text/javascript">
		
													      jQuery('#${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}').datepicker({
																    format: "dd-mm-yyyy"
																});  
										
									        			</script>
																					        			
														</div>
													</c:when>
													<c:otherwise>
														
														<div class='col-md-2 col-xs-12'>
														<div class='form-group'>
													           <c:choose>
											               <c:when test="${dynamicDetailfield.required}">
									               		   <div class="required-field-block">
										                  					<input
																id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																value="<%=value %>"  placeholder="Enter ${dynamicDetailfield.displayName}" 
																onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');"
																maxlength="${dynamicDetailfield.length}" type="text"
																class="form-control" ${strEvent} />
														     <div class="required-icon">
															<div class="text">*</div>
														     </div>
														     </div>
											               </c:when>
											               <c:otherwise>
											           					<input
																id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																value="<%=value %>"  placeholder="Enter ${dynamicDetailfield.displayName}" 
																onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');"
																maxlength="${dynamicDetailfield.length}" type="text"
																class="form-control" ${strEvent} />
											               </c:otherwise>
											               </c:choose>
										
														</div>
														</div>


													</c:otherwise>
												</c:choose>
											</c:when>
											<c:when
											test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
											<div class='col-md-2 col-xs-12'>
											<div class='form-group'>
											 <c:choose>
											               <c:when test="${dynamicDetailfield.required}">
									               		   <div class="required-field-block">
										                  					<input id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}" type="text" onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');"
														class="form-control" rows="2" cols="18" ${strEvent} placeholder="Enter ${dynamicDetailfield.displayName}" 
														value="<%=value %>" 
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId},${dynamicDetailfield.length});" />
														     <div class="required-icon">
															<div class="text">*</div>
														     </div>
														     </div>
											               </c:when>
											               <c:otherwise>
											           					<input id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}" type="text" onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');"
														class="form-control" rows="2" cols="18" ${strEvent} placeholder="Enter ${dynamicDetailfield.displayName}" 
														value="<%=value %>" 
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId},${dynamicDetailfield.length});" />
											               </c:otherwise>
											               </c:choose>
											
												</div>
												</div>
												</c:when>
										<c:when
											test="${dynamicDetailfield.fieldTypeName == 'combobox'}">
											
											<div class='col-md-2 col-xs-12'>
											<div class='form-group'>
											 <c:choose>
											               <c:when test="${dynamicDetailfield.required}">
									               		   <div class="required-field-block">
										                  					<c:choose>
														<c:when test="${picklistValueExists=='false'}">
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}" ${strEvent} onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');">
																<c:choose>
																	<c:when test="${dynamicDetailfield.fieldValue eq ''}">
																		<option value="Select">----Select----</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${dynamicDetailfield.fieldValue}">${dynamicDetailfield.fieldValue}</option>
																	</c:otherwise>
																</c:choose>
															</select>
														</c:when>
														<c:otherwise>
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}" ${strEvent} onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');">
																<c:set var="SelectedValue"
																	value="<%=value %>"  />
																<c:forEach var="pickListOptions"
																	items="${dynamicDetailpickListOptions}">
																	
																	<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																		<c:choose>
																			<c:when
																				test="${SelectedValue==pickListOptions.pickListOption}">
																				<option value="${pickListOptions.pickListOption}"
																					selected="selected"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:when>
																			<c:otherwise>
																				<option value="${pickListOptions.pickListOption}"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:otherwise>
																		</c:choose>
																	</c:if>
																</c:forEach>
															</select>
														</c:otherwise>
													</c:choose>
														     <div class="required-icon">
															<div class="text">*</div>
														     </div>
														     </div>
											               </c:when>
											               <c:otherwise> 
											           					
											           					<c:choose>
														<c:when test="${picklistValueExists=='false'}">
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}" ${strEvent} onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');">
																<c:choose>
																	<c:when test="${dynamicDetailfield.fieldValue eq ''}">
																		<option value="Select">----Select----</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${dynamicDetailfield.fieldValue}">${dynamicDetailfield.fieldValue}</option>
																	</c:otherwise>
																</c:choose>
															</select>
														</c:when>
														<c:otherwise>
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}" ${strEvent} onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');">
																<c:set var="SelectedValue"
																	value="<%=value %>"  />
																<c:forEach var="pickListOptions"
																	items="${dynamicDetailpickListOptions}">
																	<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																		<c:choose>
																			<c:when
																				test="${SelectedValue==pickListOptions.pickListOption}">
																				<option value="${pickListOptions.pickListOption}"
																					selected="selected"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:when>
																			<c:otherwise>
																				<option value="${pickListOptions.pickListOption}"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:otherwise>
																		</c:choose>
																	</c:if>
																</c:forEach>
															</select>
														</c:otherwise>
													<%-- </c:choose>
											               </c:otherwise> --%>
													</c:choose>
											               </c:otherwise>
											               </c:choose> 
												</div>
												</div>
										</c:when>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						
						<c:if test="${view_mode ne 'v' }">
						
						              <div class="col-md-1-5 col-xs-12">
		<div class="box-group ">
		<div class="form-group">
		<button type="button" class="btn btn-primary" id="alt_mat_${det.bomDetailId}"  onclick="validateBOM('${bom.headerMode}','altMat','${det.bomDetailId}');"><span>Alternate Material</span></button>
		</div>
		</div>
		</div>
		
						<div class='col-md-0-5 col-xs-12'>
           <div class='form-group'>
               <a  name="delete_${det.bomDetailId}" id="delete_${det.bomDetailId}" onclick="deleteBOMDet('${det.bomId}','${det.bomDetailId}')"><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
               
						</div>
						</div>
						</c:if>
						
						
						<c:set var="k" value="${k+1}" />
						
						</div>
					</c:forEach>

					<!-- ---------------------------------- -->
               <c:set var="t_det_id" value="0"/>
             
	<c:if test="${view_mode ne 'v' }">

<div class='row linerow'>
<div class='col-md-0-5 col-xs-12' >
<div class='form-group grid-slno'>${k}</div>
</div>

						<c:forEach items="${dynamicFieldsListBOMDetails}"
							var="dynamicDetailfield">
							<c:choose>
								<c:when test="${dynamicDetailfield.fixed}">
									<c:set var="ControlName"
										value="${dynamicDetailfield.pageFieldName}" />
											<c:choose>
														<c:when test="${ControlName=='component_id'}">
																<div class='col-md-2 col-xs-12'>
															<div class='form-group'>
															  
												                  	<input type="text" class="form-control " placeholder="${dynamicDetailfield.displayName}" 
																	name="component_${t_det_id}" id="component_${t_det_id}"
																	 />
																     	<input type="hidden" name="component_id_${t_det_id}" id="component_id_${t_det_id}" />
																     </div>
															</div>
														</c:when>

														<c:otherwise>
											<c:choose>
												<c:when test="${ControlName eq 'material_id'}">
													<div class='col-md-2 col-xs-12'>
														<div class='form-group'>
													   <div class="required-field-block">
												            <input type="text" class="form-control" 
															name="material_${t_det_id}" id="material_${t_det_id}" placeholder="Type and select ${dynamicDetailfield.displayName}" 
															onkeyup="clearId(event,'material_${t_det_id}','material_id_${t_det_id}')" maxlength="${dynamicDetailfield.length}" 
															onblur="getBOMMaterialData(document.getElementById('material_id_${t_det_id}').value,${t_det_id});" />
														<input type="hidden" name="material_id_${t_det_id}"
															id="material_id_${t_det_id}" />
																     <div class="required-icon">
																	<div class="text">*</div>
																     </div>
																     </div>
																     </div>
										

													</div>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${ControlName=='variant_id'}">
																<div class='col-md-1-5 col-xs-12'>
															<div class='form-group'>
															  
												                  	<input type="text" class="form-control " placeholder="${dynamicDetailfield.displayName}" 
																	name="variant_${t_det_id}" id="variant_${t_det_id}"
																	 />
																     	<input type="hidden" name="variant_id_${t_det_id}" id="variant_id_${t_det_id}" />
																     </div>
															</div>
														</c:when>

														<c:otherwise>
															<c:choose>
																<c:when test="${ControlName=='UOM'}">
																	<div class='col-md-1 col-xs-12'>
																	<div class='form-group'>
																	 <div class="required-field-block">
																		<input type="text" name="uom_${t_det_id}" placeholder="Type and select ${dynamicDetailfield.displayName}" 
																			class="form-control" id="uom_${t_det_id}" maxlength="${dynamicDetailfield.length}" 
																			value="${detail.variantName}" /> <input
																			type="hidden" name="uom_id_${t_det_id}"
																			id="uom_id_${t_det_id}" class="g-txt"
																			value="${detail.variantId}" />
																			 <div class="required-icon">
																	<div class="text">*</div>
																     </div>
																     </div>
																	</div>
																	</div>
																</c:when>

																<c:otherwise>
																	<c:choose>
																		<c:when test="${ControlName=='operation_id'}">
																			<div class='col-md-1-5 col-xs-12'>
																			<div class='form-group'>
																			   <div class="required-field-block">
												                    			<input type="text"  class="form-control" placeholder="${dynamicDetailfield.displayName}" 
																				 maxlength="${dynamicDetailfield.length}" name="operation_${t_det_id}" id="operation_${t_det_id}" />
																			 <input type="hidden" name="operation_id_${t_det_id}" id="operation_id_${t_det_id}" />
																						   
																     <div class="required-icon">
																	<div class="text">*</div>
																     </div>
																     </div>
																			</div>	
																			</div>
																		</c:when>

																		<c:otherwise>
																			<c:choose>
																				<c:when test="${ControlName=='required_qty'}">
																				<div class='col-md-2 col-xs-12'>
																					<div class='form-group'>
																                  <input type="text" class="form-control text-right" name="required_qty_${t_det_id}" id="required_qty_${t_det_id}" placeholder="${dynamicDetailfield.displayName}"   />
																					</div>
																					</div>
																				</c:when>

																				<c:otherwise>
																					<c:choose>
																						<c:when test="${ControlName=='wastage_per'}">
																							<div class='col-md-1 col-xs-12'>
																							<div class='form-group'>
																								   <input type="text" class="form-control text-right" name="wastage_per_${t_det_id}" id="wastage_per_${t_det_id}" placeholder="${dynamicDetailfield.displayName}"   />
																								</div>
																								
																							</div>
																						</c:when>
																						<c:otherwise>
																							<c:choose>
																								<c:when test="${ControlName=='costing_per'}">
																									<div class='col-md-1 col-xs-12'>
																									<div class='form-group'>
																										<input type="text" class="form-control text-right" maxlength="${dynamicDetailfield.length}" 
																											name="costing_per_${t_det_id}" placeholder="Enter ${dynamicDetailfield.displayName}"
																											id="costing_per_${t_det_id}" />
																											</div>
																									</div>
																								</c:when>
																								<c:otherwise>
																								<c:choose>
																									<c:when
																										test="${ControlName=='purchase_per'}">
																										<div class='col-md-1 col-xs-12'>
																									<div class='form-group'>
																										<input type="text" class="form-control text-right" maxlength="${dynamicDetailfield.length}" 
																											name="purchase_per_${t_det_id}" placeholder="Enter ${dynamicDetailfield.displayName}"
																											id="purchase_per_${t_det_id}" />
																											</div>
																									</div>
																									</c:when>
																								<c:otherwise>
																								<c:choose>
																									<c:when
																										test="${ControlName=='issue_per'}">
																										<div
																											class='col-md-1 col-xs-12'>
																											<div class='form-group'>
																												<input type="text" class="form-control text-right" placeholder="${dynamicDetailfield.displayName}" name="issue_per_${t_det_id}"  id="issue_per_${t_det_id}" >
																												</div>
																										</div>
																									</c:when>
																									<c:otherwise>
																								<c:choose>
																									<c:when
																										test="${ControlName=='supplier_id'}">
																										<div
																											class='col-md-1-5 col-xs-12'>
																											<div class='form-group'>
																												<input type="text"  class="form-control" placeholder="${dynamicDetailfield.displayName}" 
																				 maxlength="${dynamicDetailfield.length}" name="supplier_${t_det_id}" id="supplier_${t_det_id}" />
																			 <input type="hidden" name="supplier_id_${t_det_id}" id="supplier_id_${t_det_id}" />
																												</div>
																										</div>
																									</c:when>
																									<c:otherwise>
																								<c:choose>
																									<c:when
																										test="${ControlName=='usage_area'}">
																										<div
																											class='col-md-1 col-xs-12'>
																											<div class='form-group'>
																												<input type="text" class="form-control " placeholder="${dynamicDetailfield.displayName}" name="usage_area_${t_det_id}"   id="usage_area_${t_det_id}"  >
																												</div>
																										</div>
																									</c:when>
																												<c:otherwise>
																								<c:choose>
																									<c:when
																										test="${ControlName=='size_schedule_id'}">
																										<div
																											class='col-md-2 col-xs-12'>
																											<div class='form-group'>
																												<input type="text" class="form-control " placeholder="${dynamicDetailfield.displayName}" name="size_schedule_${t_det_id}"   id="size_schedule_${t_det_id}"  >
																													<input type="hidden" name="size_schedule_id_${t_det_id}"   id="size_schedule_id_${t_det_id}"  >
																												</div>
																										</div>
																									</c:when>
																								</c:choose>
																							</c:otherwise>
																								</c:choose>
																							</c:otherwise>
																								</c:choose>
																							</c:otherwise>
																								</c:choose>
																							</c:otherwise>
																								</c:choose>
																							</c:otherwise>
																							</c:choose>
																						</c:otherwise>
																									</c:choose>
																								</c:otherwise>
																							</c:choose>
																						</c:otherwise>
																					</c:choose>
																				</c:otherwise>
																				</c:choose>
																				</c:otherwise>
																			</c:choose>
																		</c:otherwise>
																	</c:choose>
																</c:otherwise>
															</c:choose>
								</c:when>
								<c:otherwise>
									<c:set var="strEvent" value="" />
									<c:set var="FieldId"
										value="${dynamicDetailfield.dynamicFieldId}" />

									<c:forEach var="events" items="${dynamicDetailFieldEvents}">
										<c:if test="${FieldId==events.dynamicFieldId}">
											<c:choose>
												<c:when test="${events.eventName=='onkeypress'}">
													<c:set var="strEvent"
														value=" ${strEvent}${events.eventName}='return ${events.functionName};'" />
												</c:when>
												<c:otherwise>
													<c:set var="strEvent"
														value=" ${strEvent}${events.eventName}='${events.functionName};'" />
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>

									<c:set var="picklistValueExists" value="false" />
									<c:if test="${not empty dynamicDetailpickListOptions}">
										<c:forEach var="pickListOptions"
											items="${dynamicDetailpickListOptions}">
											<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
												<c:set var="picklistValueExists" value="true" />
											</c:if>
										</c:forEach>
									</c:if>
									<!-- TEXTBOX for the dynamic controls -->
									<c:choose>
										<c:when
											test="${dynamicDetailfield.fieldTypeName eq 'textbox'}">
											<c:choose>
												<c:when test="${dynamicDetailfield.dataTypeName == 'Date'}">
													<div class='col-md-2 col-xs-12'>
													<div class='form-group'>
													<div class="input-daterange has-feedback"
															id='${dynamicDetailfield.pageFieldName}_${t_det_id}'>
															<c:choose>
												               <c:when test="${dynamicDetailfield.required}">
														        <div class="required-field-block">
															 <input class="form-control"
																name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																id="${dynamicDetailfield.pageFieldName}_${t_det_id}" maxlength="${dynamicDetailfield.length}" 
																  type="text"
																placeholder="dd-mm-yyyy" ${strEvent}/> <span
																class="glyphicon glyphicon-calendar form-control-feedback"></span>
															    <div class="required-icon">
															    <div class="text">*</div>
														        </div>
														        </div>
															    <span class="input-group-addon">
																<span class="glyphicon glyphicon-calendar"></span>
															    </span>
												               </c:when>
												               <c:otherwise>
												               <input class="form-control"
																name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																id="${dynamicDetailfield.pageFieldName}_${t_det_id}" maxlength="${dynamicDetailfield.length}" 
																  type="text"
																placeholder="dd-mm-yyyy" ${strEvent}/> <span
																class="glyphicon glyphicon-calendar form-control-feedback"></span>
												               </c:otherwise>
												               </c:choose>
														</div>
														</div>
														<script type="text/javascript">
		
													      jQuery('#${dynamicDetailfield.pageFieldName}_${t_det_id}').datepicker({
																    format: "dd-mm-yyyy"
																});  
										
									        			</script>
													</div>
												</c:when>
												<c:otherwise>
													<div class='col-md-2 col-xs-12'>
													<div class='form-group'>
													<c:choose>
											               <c:when test="${dynamicDetailfield.required}">
									               		   <div class="required-field-block">
										                  					<input
															id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
															name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
															value="${dynamicDetailfield.fieldValue}" placeholder="Enter ${dynamicDetailfield.displayName}"
															maxlength="${dynamicDetailfield.length}" type="text"
															class="form-control" ${strEvent} />
														     <div class="required-icon">
															<div class="text">*</div>
														     </div>
														     </div>
											               </c:when>
											               <c:otherwise>
											           					<input
															id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
															name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
															value="${dynamicDetailfield.fieldValue}" placeholder="Enter ${dynamicDetailfield.displayName}"
															maxlength="${dynamicDetailfield.length}" type="text"
															class="form-control" ${strEvent} />
											               </c:otherwise>
											               </c:choose>
														</div>
													</div>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:when
											test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
											<div class='col-md-2 col-xs-12'>
											<div class='form-group'>
											<c:choose>
											               <c:when test="${dynamicDetailfield.required}">
									               		   <div class="required-field-block">
										                  					<input id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
														name="${dynamicDetailfield.pageFieldName}_${t_det_id}" type="text" placeholder="Enter ${dynamicDetailfield.displayName}"
														class="form-control" rows="2" cols="18" ${strEvent}
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_${t_det_id},${dynamicDetailfield.length});" />
														     <div class="required-icon">
															<div class="text">*</div>
														     </div>
														     </div>
											               </c:when>
											               <c:otherwise>
											           					<input id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
														name="${dynamicDetailfield.pageFieldName}_${t_det_id}" type="text" placeholder="Enter ${dynamicDetailfield.displayName}"
														class="form-control" rows="2" cols="18" ${strEvent}
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_${t_det_id},${dynamicDetailfield.length});" />
											               </c:otherwise>
											               </c:choose>
											</div>
												</div>
												</c:when>
										<c:when
											test="${dynamicDetailfield.fieldTypeName == 'combobox'}">
											<div class='col-md-2 col-xs-12'>
											<div class='form-group'>
											<c:choose>
											               <c:when test="${dynamicDetailfield.required}">
									               		   <div class="required-field-block">
										                  				<c:choose>
														<c:when test="${picklistValueExists=='false'}">
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																name="${dynamicDetailfield.pageFieldName}_${t_det_id}" ${strEvent}>
																<c:choose>
																	<c:when test="${dynamicDetailfield.fieldValue eq ''}">
																		<option value="Select">----Select----</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${dynamicDetailfield.fieldValue}">${dynamicDetailfield.fieldValue}</option>
																	</c:otherwise>
																</c:choose>
															</select>
														</c:when>
														<c:otherwise>
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																name="${dynamicDetailfield.pageFieldName}_${t_det_id}" ${strEvent}>
																<c:set var="SelectedValue"
																	value="${dynamicDetailfield.fieldValue}" />
																<c:forEach var="pickListOptions"
																	items="${dynamicDetailpickListOptions}">
																	
																	<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																		<c:choose>
																			<c:when
																				test="${SelectedValue==pickListOptions.pickListOption}">
																				<option value="${pickListOptions.pickListOption}"
																					selected="selected"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:when>
																			<c:otherwise>
																				<option value="${pickListOptions.pickListOption}"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:otherwise>
																		</c:choose>
																	</c:if>
																</c:forEach>
															</select>
														</c:otherwise>
													</c:choose>
														     <div class="required-icon">
															<div class="text">*</div>
														     </div>
														     </div>
											               </c:when>
											               <c:otherwise>
											           					<c:choose>
														<c:when test="${picklistValueExists=='false'}">
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																name="${dynamicDetailfield.pageFieldName}_${t_det_id}" ${strEvent}>
																<c:choose>
																	<c:when test="${dynamicDetailfield.fieldValue eq ''}">
																		<option value="Select">----Select----</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${dynamicDetailfield.fieldValue}">${dynamicDetailfield.fieldValue}</option>
																	</c:otherwise>
																</c:choose>
															</select>
														</c:when>
														<c:otherwise>
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																name="${dynamicDetailfield.pageFieldName}_${t_det_id}" ${strEvent}>
																<c:set var="SelectedValue"
																	value="${dynamicDetailfield.fieldValue}" />
																<c:forEach var="pickListOptions"
																	items="${dynamicDetailpickListOptions}">
																	
																	<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																		<c:choose>
																			<c:when
																				test="${SelectedValue==pickListOptions.pickListOption}">
																				<option value="${pickListOptions.pickListOption}"
																					selected="selected"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:when>
																			<c:otherwise>
																				<option value="${pickListOptions.pickListOption}"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:otherwise>
																		</c:choose>
																	</c:if>
																</c:forEach>
															</select>
														</c:otherwise>
													</c:choose>
											               </c:otherwise>
											               </c:choose>
											</div>
												</div>
										</c:when>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:forEach>

                      <div class="col-md-1-5 col-xs-12">
		<div class="box-group ">
		<div class="form-group">
		<button type="button" class="btn btn-primary" onclick="validateBOM('${bom.headerMode}','altMat',0);"><span>Alternate Material</span></button>
		</div>
		</div>
		</div>
		
						
                 <div class='col-md-0-5 col-xs-12'>
                     <a href="javascript:clearNewBOMDetail('${t_det_id}')" name="clear_${t_det_id}" id="clear_${t_det_id}" title="Clear"  ><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
						</div>
             </div>
			<div class="row">
			<div class="col-sm-12">
			
			<a href="javascript:validateBOM('${bom.headerMode}','1',0);" ><span class="glyphicon glyphicon-plus" class="text-primary"></span>&nbsp;&nbsp;Add Another Line</a>
			
			</div>
              </div>
              </c:if>
              <div class="row row-no-margin trans-footer">             
<div class="tax-cal col-sm-6 col-md-push-6">

</div>


<div class="col-sm-6 col-md-pull-6">

<div class="row row-no-margin">  
 <div class='col-sm-6'  >
<div class='form-group'>
<label >Message displayed in BOM</label>
<textarea class="form-control" rows="6"  id="bom_msg" name="bom_msg" maxlength="250"  placeholder="Enter BOM Message">${header_info.remarks}</textarea>
</div>            
</div>
</div>
<%-- 
<div class="row row-no-margin">
<div class='col-sm-6'  >
<div class='form-group'>
<label >Memo for internal use</label>
<textarea class="form-control" rows="6"  id="internal_memo" name="internal_memo" maxlength="250"  placeholder="Enter Internal Memo" >${header_info.internalMemo}</textarea>
</div>            
</div>
</div> --%>
  
</div>  




</div>

<c:if test="${view_mode ne 'v' }">
<div class='row'>
 <div class="col-sm-12">
<button class="btn btn-primary fileinput-button" onclick="addAttachment()" > <i class="glyphicon glyphicon-plus"></i> <span>Add files...</span></button>
<img id="ajax-loader" src="images/ajax-loader.gif" class="ajax-loader">
</div>
</div>

</c:if>


				
					
					<div class='row row-no-margin attach-area' >
					<div class="col-sm-10">

							<c:set var="idx" value="1" />
							<c:forEach var="filename" items="${attached_files}">
								
								
								 
								 <div class=" col-sm-4 notice notice-info" id="attach_file${idx}" >
								<%--  <c:if test="${filename.urlType=='png' || filename.urlType=='jpg' || filename.urlType=='jpeg' || filename.urlType=='gif' || filename.urlType=='bmp' }">
								 <div style="    width: 10%;float: left;height: auto;overflow-y: visible;"><a target="_blank" href="${enquiryAttachPath}${fileNV}" title="view"><img src="${enquiryAttachPath}${fileNV}" width="100%" /></a></div>
								 </c:if> --%>
								 <c:if test="${view_mode ne 'v' }">
								 <div class="attach-delete">
        							<a class="attach-link-delete" href="#" name="del_attach${idx}" id="del_attach${idx}" onclick="deleteBOMAttachment('${idx}','${filename.bomId}','${filename.url}')">
											<span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
								</div>
								</c:if>
								<div >
											<a class="attach-link" target="_blank" href="${bomAttachPath}${filename.url}">${filename.fileName}</a>
								</div>
    							</div> 
							
								<c:set var="idx" value="${idx+1}" />
							</c:forEach>
						</div>
					</div>
					
					<div class='row'>
					<div class="col-sm-12">
						<table id="attachments" class="attach-table"
							style="border-collapse: collapse;">

						</table>
						</div>
					</div>
					


					<!-- Remember Followers <input type="checkbox" name="remember_followers" id="remember_followers" /> -->
  
</div>
              

	
	
	

                  
              
                <div class='row '>
    <div class='col-sm-10 col-xs-12 col-sm-offset-1'>
    <div class='pull-right centered text-save-view'>   
                   
                     <c:choose>
                <c:when test="${view_mode ne 'v' }">
                   	<c:if test="${bom_rights.addPermission==1 or bom_rights.editPermission==1 }">
                   	<div class="btn-group dropup">
                  	
                <button name="save" id="save"  onclick="validateBOM('${bom.headerMode}','1',0)"  class="btn btn-success ladda-button"  data-style="expand-right">
										     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
										    </button>
                <button    class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
        <%--     <li><a href="javascript:validateBOM('${bom.headerMode}','2')" id="save_send" >Save & Send</a></li> --%>
            <c:if test="${bom_rights.addPermission==1}" >
             <li><a href="javascript:validateBOM('${bom.headerMode}','3',0)" id="save_new">Save & New</a></li>
           	</c:if>
           	<c:if test="${bom_rights.viewPermission==1 }">
           	       <!--  <li class="divider"></li> -->
                  <li><a  href="javascript:validateBOM('${bom.headerMode}','4',0)" id="save_close">Save & Close</a></li>
           	</c:if>
          
                </ul>
              </div>
                   	</c:if>
                  	
              
              <div class="btn-group dropup">
      <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-circle-arrow-up"></i>&nbsp;Action&nbsp;<span class="caret"></span></button>
      <ul class="dropdown-menu dropdown-menu-right">
      <%-- 	<c:if test="${bom_rights.printPermission==1 }">
      	  <li><a  href="javascript:exportSalesEnquiryToPDF(${header_info.bomId});" ><fmt:message bundle="${bundle}"   key="Button.Print"/></a></li>
      	</c:if>
      	<c:if test="${sq_rights.addPermission==1 }">
      	 <li><a  href="javascript:postSalesQuotation(${header_info.bomId},1)" >Prepare Sales Quotation</a></li>
      	</c:if> --%>
       	<c:if test="${bom_rights.deletePermission==1 }">
          <li><a href="javascript:deleteBOM(1,${header_info.bomId},1)" ><fmt:message bundle="${bundle}"   key="Button.Delete"/></a></li>
       	</c:if>
         
      </ul>
    </div>
    
   
                 				             
  	<c:if test="${bom_rights.viewPermission==1 }">
  	 <button type="button" class="btn btn-primary"  onclick="showBOM()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>
  	</c:if>
                  	
            </c:when>  
       <c:otherwise>
     <c:if test="${bom_rights.viewPermission==1 }">
  	 <button type="button" class="btn btn-primary"  onclick="showBOM()"><i class="glyphicon glyphicon-chevron-left"></i><span>&nbsp;Back</span></button>
  	</c:if>
                </c:otherwise>
                
                </c:choose>     				             
            </div>
    </div>
            </div>
                
              
    
     
            	         	 
    
    <div class='row' style="margin-top: 10px;" >        	         	 
           
    </div>
    
   </div>
  </div> 
<jsp:include page="../common/Footer.jsp"/>

<input type="hidden" name="servlet_name" id="servlet_name" />
 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
 <input type="hidden" name="pageno" id="pageno" />
 <input type="hidden" name="request_type" id="request_type" />
<input type="hidden" name="head_mode" id="head_mode" value="${bom.headerMode}" /> 
<input type="hidden" name="det_mode" id="det_mode"  value="${bom.detMode}"/>  
<input type="hidden" name="bom_det_id" id="bom_det_id" value="${bom_det_id}" />
<input type="hidden" name="bom_id" id="bom_id" value="${header_info.bomId}" />
<input type="hidden" name="edited_ids" id="edited_ids" value=""/>
<input type="hidden" name="dynedited_ids" id="dynedited_ids" value=""/>
<input type="hidden" name="edited_mode" id="edited_mode" value=""/>
 <input type="hidden" name="invoke_method" id="invoke_method" />
<input type="hidden" name="invoke_class" id="invoke_class" />
<input type="hidden" name="selected_users" id="selected_users"/>
<input type="hidden" name="selected_userremarks" id="selected_userremarks"/>
<input type="hidden" name="selected_remarks" id="selected_remarks"/>
<input type="hidden" name="material_id" id="material_id"/>
<input type="hidden" name="new_det_mode" id="new_det_mode"/>
<input type="hidden" name="prefix_name" id="prefix_name"/>
<input type="hidden" name="page" id="page"/>

<input type="hidden" name="def_cur_id" id="def_cur_id" value="${user_info.currencyId}"/>
<input type="hidden" name="def_cur_name" id="def_cur_name" value="${user_info.currencyName}"/>

<input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
<input type="hidden" id="add_new_mat" data-toggle="modal" data-target="#materialModal"   />
<input type="hidden" id="add_new_group" data-toggle="modal" data-target="#groupMasterModal"   /><!-- onclick="loadDataToGroupMasterModal()" -->
<input type="hidden" id="add_new_customer" data-toggle="modal" data-target="#customerMasterModal"   /> <!-- onclick="loadDataToCustomerMasterModal()" -->
<input type="hidden" id="add_new_uom" data-toggle="modal" data-target="#uomMasterModal"  />
<input type="hidden" id="add_new_account" data-toggle="modal" data-target="#accountModal" /><!-- onclick="loadDataToAccountModal()" -->
<input type="hidden" id="add_new_currency" data-toggle="modal" data-target="#currencyMasterModal"   />
<input type="hidden" id="add_new_accountgroup" data-toggle="modal" data-target="#accountGroupModal"  onclick="loadDataToGroupModal();"  />
<input type="hidden" id="add_new_branch" data-toggle="modal" data-target="#branchMasterModal"   />
<input type="hidden" id="add_new_variant" data-toggle="modal" data-target="#VariantModal"   />
<input type="hidden" id="trno_quotation" data-toggle="modal" data-target="#trNoModal"   />

 <input type="hidden" id="alt_mat_modal" data-toggle="modal" data-target="#alternateMaterialModal"  onclick="loadDataToAltMaterialModal()"  />


<input type="hidden" id="autono_table_name" name="autono_table_name" value="set_autono_sales_quotation"  />
<input type="hidden" name="tr_tag" id="tr_tag"  />
<input type="hidden" name="view_mode" id="view_mode" value="${view_mode}" />
     
<input type="hidden" name="row_count" id="row_count" value="${row_count}"/>
<input type="hidden" name="save_type" id="save_type" value="${save_type}" />
<input type="hidden" name="det_mat_id" id="det_mat_id"  value="${det_mat_id}" />
<input type="hidden" name="det_comp_id" id="det_comp_id"  value="${det_comp_id}" />

       
<input type="submit" name="valid_det" id="valid_det" style="display:none;"/>

<c:set var="det_ids" value=""/>
<c:forEach var="detId" items="${det_id_list}">
<c:set var="det_ids" value="${det_ids}${detId},"/>
</c:forEach>
<input type="hidden" name="det_ids" id="det_ids" value="${det_ids}"/>

  
       
     
  
   
     
<script language="javascript">

$(function(){
	$('[data-toggle="tooltip"]').tooltip();
	});




</script>
<script>
$('#validate-form').bootstrapValidator({
	
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	/*   excluded: ':disabled', */
	  fields: {
		 /*  customer: {
	            validators: {
	                notEmpty: {
	                    message: 'This field is required'
	                },
	                callback: {
	                    message: 'This field is required',
	                    callback: function(value, validator, $field) {
	                    	if ($("#customer_id").val()==0) {
	                            return {
	                                valid: false,
	                                message: 'This field is required'
	                            };
	                        }
	                    	return true;
	                    }
	                }
	          
	            }
	        },  */
	        product: {
	            validators: {
	                notEmpty: {
	                    message: 'This field is required'
	                },
	                callback: {
	                    message: 'This field is required',
	                    callback: function(value, validator, $field) {
	                    	if ($("#product_id").val()==0) {
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
          tr_date: {
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
        
          material_0: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if ($("#material_id_0").val()==0) {
                              return {
                                  valid: false,
                                  message: 'This field is required'
                              };
                          }
                      	else if ($("#component_id_0").val()>0 && $("#material_id_0").val()==$("#component_id_0").val()) {
                            return {
                                valid: false,
                                message: 'Material,component should not equal'
                            };
                        }
                      	return true;
                      }
                  }
              }
          }, component_0: {
              validators: {
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                         if ($("#component_id_0").val()>0 && $("#material_id_0").val()==$("#component_id_0").val()) {
                                  return {
                                      valid: false,
                                      message: 'Material,component should not equal'
                                  };
                              }
                      	return true;
                      }
                  }
              }
          },
          operation_0: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if ($("#operation_id_0").val()==0) {
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
          uom_0: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if ($("#uom_id_0").val()==0) {
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
 
        
     
          <c:forEach var="detId" items="${det_id_list}">
          material_S_${detId}: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if ($("#mat_id_S_${detId}").val()==0) {
                              return {
                                  valid: false,
                                  message: 'This field is required'
                              };
                          }
                      	else   if ($("#component_id_S_${detId}").val()>0 && $("#mat_id_S_${detId}").val()==$("#component_id_S_${detId}").val()) {
                            return {
                                valid: false,
                                message: 'Material,component should not equal'
                            };
                        }
                      	return true;
                      }
                  }
          
              }
          }, component_S_${detId}: {
              validators: {
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                         if ($("#component_id_S_${detId}").val()>0 && $("#material_id_S_${detId}").val()==$("#component_id_S_${detId}").val()) {
                                  return {
                                      valid: false,
                                      message: 'Material,component should not equal'
                                  };
                              }
                      	return true;
                      }
                  }
              }
          },
          operation_S_${detId}: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if ($("#operation_id_S_${detId}").val()==0) {
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
          uom_S_${detId}: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if ($("#uom_id_S_${detId}").val()==0) {
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
          quantity_S_${detId}: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          },
          required_qty_S_${detId}: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  }
              }
          },
     
          </c:forEach>
          required_qty_0: {
              validators: {
            	  notEmpty: {
                      message: 'This field is required'
                  },
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          }
          
	  }
	});

 
</script>
<script>
!function ($) {
	 
	$(function(){
	
		
		
		
		  $('#customer').listCustomer({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=1",
			  nameField :'#customer',
			  idField:'#customer_id',
			  formId:'#validate-form',
			  validateId:'customer'
		  });
		  
		  $('#product').listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetFGMaterial&create_new=1",
			  nameField :'#product',
			  idField:'#product_id',
			  formId:'#validate-form',
			  validateId:'product'
		  });
	
		  $('#parent_bom_no').listBOM({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetBOM&create_new=1",
			  nameField :'#parent_bom_no',
			  idField:'#parent_bom_id',
			  formId:'#validate-form',
			  validateId:'parent_bom_no'
		  });
	
		  $('#material_0').listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=1",
			  nameField :'#material_0',
			  idField:'#material_id_0',
			  formId:'#validate-form',
			  validateId:'material_0'
		  });
		  
		  $('#component_0').listMaterialComponent({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterialComponent&create_new=1",
			  nameField :'#component_0',
			  idField:'#component_id_0',
			  formId:'#validate-form',
			  validateId:'component_0'
		  });
		  
		  $('#uom_0').listUOM({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=1",
			  nameField :'#uom_0',
			  idField:'#uom_id_0',
			  formId:'#validate-form',
			  validateId:'uom_0'
		  });
		  
		  $('#variant_0').listVariant({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
			  nameField :'#variant_0',
			  idField:'#variant_id_0'
			  //formId:'#validate-form',
			  //validateId:'variant_0'
		  });
		  
		  $('#supplier_0').listSupplier({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSupplier&create_new=1",
			  nameField :'#supplier_0',
			  idField:'#supplier_id_0',
			  formId:'#validate-form',
			  validateId:'supplier_0'
		 });
		  
		  $('#operation_0').listOperation({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetOperation&create_new=1",
			  nameField :'#operation_0',
			  idField:'#operation_id_0',
			  formId:'#validate-form',
			  validateId:'operation_0'
		 });
		  
		  $('#size_schedule_0').listSizeShedule({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeShedule&create_new=1",
			  nameField :'#size_schedule_0',
			  idField:'#size_schedule_id_0'
		 });
		  
		  
		  <c:forEach var="detId" items="${det_id_list}">
		  $('#material_S_'+${detId}).listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=1",
			  nameField :'#material_S_'+${detId},
			  idField:'#mat_id_S_'+${detId},
			  formId:'#validate-form',
			  validateId:'material_S_'+${detId}
		  });
		  
		  $('#component_S_'+${detId}).listMaterialComponent({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterialComponent&create_new=1",
			  nameField :'#component_S_'+${detId},
			  idField:'#component_id_S_'+${detId},
			  formId:'#validate-form',
			  validateId:'component_S_'+${detId}
		  });
		  
		  
		  $('#operation_S_'+${detId}).listOperation({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetOperation&create_new=1",
			  nameField :'#operation_S_'+${detId},
			  idField:'#operation_id_S_'+${detId},
			  formId:'#validate-form',
			  validateId:'operation_S_'+${detId}
		 });
		  $('#uom_S_'+${detId}).listUOM({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=1",
			  nameField :'#uom_S_'+${detId},
			  idField:'#uom_id_S_'+${detId},
			  formId:'#validate-form',
			  validateId:'uom_S_'+${detId}
		  });
		  
		  $('#supplier_S_'+${detId}).listSupplier({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSupplier&create_new=1",
			  nameField :'#supplier_S_'+${detId},
			  idField:'#supplier_id_S_'+${detId},
			  formId:'#validate-form',
			  validateId:'supplier_S_'+${detId}
		 });
		  
		  $('#variant_S_'+${detId}).listVariant({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
			  nameField :'#variant_S_'+${detId},
			  idField:'#variant_id_S_'+${detId}
			  //formId:'#validate-form',
			  //validateId:'variant_0'
		  });
		  
		  
		  $('#size_schedule_S_'+${detId}).listSizeShedule({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeShedule&create_new=1",
			  nameField :'#size_schedule_S_'+${detId},
			  idField:'#size_schedule_id_S_'+${detId}
		 });
		  
		  </c:forEach>
		  
		  
		
		  
	 });
}(window.jQuery);
	 

</script>	


      </form>

      
       
            <jsp:include page="../masters/AddAlternateMaterial.jsp" />
            
              <script>    
   <c:if test="${save_type eq 'altMat'}" >

getAltMaterial();

</c:if>         
  </script>          
      <jsp:include page="../masters/AddGroupFromPage.jsp" />
        <jsp:include page="../masters/AddMaterialFromPage.jsp" />
          <jsp:include page="../masters/AddCustomerFromPage.jsp" />
          <%-- <jsp:include page="../sales/AutoGenerationNoForSalesEnquiry.jsp" /> --%>
            <jsp:include page="../masters/AddUOMFromPage.jsp" />
             <jsp:include page="../masters/AddCurrencyFromPage.jsp" />
            
            
            
			
             <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script> 
      </body>
      </html>