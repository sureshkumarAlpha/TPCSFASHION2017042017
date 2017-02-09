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
/* .container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
} */

</style>
</head>
<jsp:include page="../common/ValidateUser.jsp" />
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
<body>
<form action=""  id="validate_approvedPriceList" method="post" role="form" autocomplete="off"> 
<div class="wrapper">
    <jsp:include page="../common/MainMenu.jsp"> 
 	<jsp:param value="2" name="screen_type"/>
	<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name"/>
	</jsp:include>

<div class="page-wrapper">
<div class="page-inner"> 


					<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Approved Price List</h3>
							</div>
						</div>
					</div>
					
	   <div class="row">
				 <div class="col-sm-3">
				 <div class='col-sm-12'>
		 		 <div class='form-group'>
                 <label for="customer">Supplier</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="supplier_name" name="supplier_name" <c:if test="${mode eq 'e'}">readonly </c:if>  <c:if test="${mode ne 'e'}">autofocus </c:if> size="30" type="text" value="${heat_List.supplierName}" placeholder="Type and select Supplier"/>
                 <input type="hidden" id="supplier_id" name="supplier_id" value="${heat_List.supplierId}">
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
				 </div>
				<!--  <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label for="customer">Item</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="material" name="material" size="30" type="text" placeholder="Type and select Material"/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
	</div>
	<div class="row">
	  			<div class="col-sm-3">
		 		 <div class='form-group'>
                 <label for="customer">Price(INR)</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="price" name="price" size="30" type="text" placeholder="Type and select Price(INR) per UOM"/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div>
				 <div class="col-sm-3">
		 		 <div class='form-group'>
                 <label for="customer">Lead Time</label>
		    	 <div class="required-field-block">
                 <input class="form-control" id="price" name="price" size="30" type="text" placeholder="Type and select Lead Time Days"/>
		   		 <div class="required-icon">
				 <div class="text">*</div>
		    	 </div>
		    	 </div>
            	 </div>
				 </div> -->
	</div>
	
<!-- ----------------------Detail Part Starts with Dynamic Fields  Grid Header --------------------------------------------------------------------------------- -->	
	
	<div class="row det-row-margin" >
<div class="col-sm-12">
<div class="col-sm-12 ">

	<div class='row trans-det-row'  >
          	<div class='col-md-0-5 col-xs-12' id="header">
			<label >#</label>
			</div>
	<c:forEach items="${dynamicFieldsListAppPriceList}" var="dynamicDetailfield" >
				<c:choose>
					<c:when test="${dynamicDetailfield.fixed}">
						<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
						<%-- <c:choose>
							<c:when test="${ControlName=='group_id'}">
								<div class='col-md-2 col-xs-12' id="header">
									<label for="customer">${dynamicDetailfield.displayName}</label>
								</div>
							</c:when>
							<c:otherwise> --%>
								<c:choose>
									<c:when test="${ControlName eq 'item_id'}">
										<div class='col-md-2 col-xs-12' id="header">
											<label for="customer">${dynamicDetailfield.displayName}</label>
										</div>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${ControlName=='color_id'}">
												<div class='col-md-1 col-xs-12' id="header">
													<label for="customer">${dynamicDetailfield.displayName}</label>
												</div>
											</c:when>

											<c:otherwise>
												<c:choose>
													<c:when test="${ControlName=='uom'}">
														<div class='col-md-1 col-xs-12' id="header">
															<label for="customer">${dynamicDetailfield.displayName}</label>
														</div>
													</c:when>

													<c:otherwise>
														<c:choose>
															<c:when test="${ControlName=='rate'}">
																<div class='col-md-1 col-xs-12' id="header">
																	<label for="customer">${dynamicDetailfield.displayName}</label>
																</div>
															</c:when>

															<c:otherwise>
																<c:choose>
																	<c:when test="${ControlName=='currency_name'}">
																		<div class='col-md-1 col-xs-12' id="header">
																			<label for="customer">${dynamicDetailfield.displayName}</label>
																		</div>
																	</c:when>

																	<c:otherwise>
																		<c:choose>
																			<c:when test="${ControlName=='lead_time_days'}">
																				<div class='col-md-1 col-xs-12' id="header">
																					<label for="customer">${dynamicDetailfield.displayName}</label>
																				</div>
																			</c:when>

																			<c:otherwise>
																					<c:choose>
																						<c:when
																							test="${ControlName=='supplier_desc'}">
																							<div class='col-md-2 col-xs-12'
																								id="header">
																								<label for="customer">${dynamicDetailfield.displayName}</label>
																							</div>
																						</c:when>
																						<c:otherwise>
																							<c:choose>
																								<c:when
																									test="${ControlName=='moq'}">
																									<div
																										class='col-md-1 col-xs-12'
																										id="header">
																										<label for="customer">${dynamicDetailfield.displayName}</label>
																									</div>
																								</c:when>
																									<c:otherwise>
																							<c:choose>
																								<c:when
																									test="${ControlName=='size_range_id'}">
																									<div
																										class='col-md-1 col-xs-12'
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
           
 <!-- ----------------------Detail Part END with Dynamic Fields  Grid Header --------------------------------------------------------------------------------- -->	
            
 <!-- ----------------------------------------------Edit Detaiks Row Start   Dynamic Fields ------------------------------------------------------------ -->
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
							<c:forEach items="${dynamicFieldsListAppPriceList}"	var="dynamicDetailfield">
								<c:choose>
									<c:when test="${dynamicDetailfield.fixed}">
										<c:set var="ControlName"
											value="${dynamicDetailfield.pageFieldName}" />

										<c:choose>
											<c:when test="${ControlName eq 'item_id'}">
												<div class='col-md-2 col-xs-12'>
													<div class='form-group'>
														<div class="required-field-block">
															<input class="form-control" name="mat_S_${det.purchasePriceId}" readonly
																id="mat_S_${det.purchasePriceId}"
																value="<c:out value="${det.matName}"/>"
																onblur="setEditedId('item_id_S_${det.purchasePriceId}');getItemGroupData(document.getElementById('item_id_S_${det.purchasePriceId}').value,${det.purchasePriceId});"
																maxlength="${dynamicDetailfield.length}"
																type="text" 
																placeholder="Type and select ${dynamicDetailfield.displayName}" />
															<input type="hidden" name="item_id_S_${det.purchasePriceId}"
																id="item_id_S_${det.purchasePriceId}" value="${det.matId}" />
															<div class="required-icon">
																<div class="text">*</div>
															</div>
														</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${ControlName=='color_id'}">
														<div class='col-md-1 col-xs-12'>
															<div class='form-group'>
																	<input class="form-control "
																		name="color_name_S_${det.purchasePriceId}"
																		id="color_name_S_${det.purchasePriceId}"
																		value="${det.colorName}" type="text"
																		onblur="setEditedId('color_id_S_${det.purchasePriceId}');"
																		placeholder="${dynamicDetailfield.displayName}" />
																		<input type="hidden" name="color_id_S_${det.purchasePriceId}" 
																		id="color_id_S_${det.purchasePriceId}" value="${det.colorId}">
																	
															</div>
														</div>
													</c:when>

													<c:otherwise>
														<c:choose>
															<c:when test="${ControlName=='uom'}">
																<div class='col-md-1 col-xs-12'>
																	<div class='form-group'>
																		<input type="text" class="form-control"
																			name="uom_${det.purchasePriceId}" id="uom_${det.purchasePriceId}"
																			maxlength="${dynamicDetailfield.length}"
																			onblur="setEditedId('uom_${det.purchasePriceId}');"
																			value="<c:out value="${det.uom}"/>" />
																	</div>
																</div>
															</c:when>
															<c:otherwise>
																<c:choose>
																	<c:when test="${ControlName=='rate'}">
																		<div class='col-md-1 col-xs-12'>
																			<div class='form-group'>
																			<div class="required-field-block">
																					<input type="text" class="form-control"
																						name="rate_S_${det.purchasePriceId}"
																						id="rate_S_${det.purchasePriceId}"
																						maxlength="${dynamicDetailfield.length}"
																						onblur="setEditedId('rate_S_${det.purchasePriceId}');"
																						value="<c:out value="${det.rate}"/>"
																						placeholder="${dynamicDetailfield.displayName}" />
																			 <div class="required-icon">
																				<div class="text">*</div>
																			</div>
																			</div>
																			</div>
																			</div>
																	</c:when>

																	<c:otherwise>
																		<c:choose>
																			<c:when test="${ControlName=='currency_name'}">
																				<div class='col-md-1 col-xs-12'>
																					<div class='form-group'>
																						<input type="text" class="form-control "
																							name="currency_name_S_${det.purchasePriceId}"
																							id="currency_name_S_${det.purchasePriceId}"
																							value="${det.currencyName}"
																							onblur="setEditedId('currency_name_S_${det.purchasePriceId}');"
																							placeholder="${dynamicDetailfield.displayName}" />
																					</div>
																				</div>
																			</c:when>

																			<c:otherwise>
																				<c:choose>
																					<c:when test="${ControlName=='lead_time_days'}">
																						<div class='col-md-1 col-xs-12'>
																							<div class='form-group'>
																								
																										<input class="form-control"
																											name="lead_time_days_S_${det.purchasePriceId}"
																											id="lead_time_days_S_${det.purchasePriceId}"
																											onblur="setEditedId('lead_time_days_S_${det.purchasePriceId}');"
																											type="text" value="${det.leadTimeDays}"
																											placeholder="${dynamicDetailfield.displayName}" />
																										
																							</div>


																						</div>
																					</c:when>
																					<c:otherwise>
																						<c:choose>
																							<c:when test="${ControlName=='supplier_desc'}">
																								<div id="desc_div_${det.purchasePriceId}"
																									class='col-md-2 col-xs-12' data-trigger="hover"
																									data-content="${det.supplierDesc}"
																									data-toggle="popover" data-placement="top"
																									title="Description">
																									<div class='form-group'>

																										<textarea class="form-control" rows="1"
																											id="supplier_desc_S_${det.purchasePriceId}"
																											name="supplier_desc_S_${det.purchasePriceId}"
																											placeholder="${dynamicDetailfield.displayName}"
																											maxlength="${dynamicDetailfield.length}"
																											onblur="setEditedId('supplier_desc_S_${det.purchasePriceId}');">${det.supplierDesc}</textarea>

																									</div>
																								</div>
																							</c:when>
																							<c:otherwise>

																								<c:choose>
																									<c:when test="${ControlName=='moq'}">
																										<div class='col-md-1 col-xs-12'>
																											<div class='form-group'>
																												<input type="text" class="form-control"
																													name="moq_S_${det.purchasePriceId}"
																													maxlength="${dynamicDetailfield.length}"
																													id="moq_S_${det.purchasePriceId}"
																													value="<c:out value="${det.moq}"/>"
																													placeholder="Enter ${dynamicDetailfield.displayName}"
																													onblur="setEditedId('moq_S_${det.purchasePriceId}');" />
																											</div>
																										</div>
																									</c:when>

																									<c:otherwise>
																										<c:choose>
																											<c:when
																												test="${ControlName=='size_range_id'}">
																												<div class='col-md-1 col-xs-12'>
																													<div class='form-group'>
																														<input type="text"
																															class="form-control text-right"
																															value="${det.sizeRange}"
																															name="size_range_${det.purchasePriceId}"
																															onblur="setEditedId('size_range_id_S_${det.purchasePriceId}');getSizeDetailsGrid(${det.purchasePriceId});"
																															placeholder="${dynamicDetailfield.displayName}"
																															id="size_range_${det.purchasePriceId}" /> <input
																															type="hidden"
																															name="size_range_id_S_${det.purchasePriceId}"
																															id="size_range_id_S_${det.purchasePriceId}"
																															value="${det.sizeRangeId}" />
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
										<%-- 		</c:otherwise> --%>
										<%-- </c:choose> --%>

									</c:when>
									<c:otherwise>

										<c:set var="f_name"
											value="${dynamicDetailfield.dbFieldName}${det.purchasePriceId}" />
										<%
											/* String fieldName=(String) pageContext.getAttribute("f_name"); */
															/* String value=dtMap.get(fieldName); */
															String value = "test";
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
											<c:when
												test="${dynamicDetailfield.fieldTypeName eq 'textbox'}">

												<c:choose>
													<c:when test="${dynamicDetailfield.dataTypeName == 'Date'}">
														<div class='col-md-2 col-xs-12'>
															<div class='form-group'>
																<div class="input-daterange has-feedback"
																	id='${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}'>
																	<c:choose>
																		<c:when test="${dynamicDetailfield.required}">
																			<div class="required-field-block">
																				<input class="form-control"
																					name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																					id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																					maxlength="${dynamicDetailfield.length}"
																					value="<%=value %>"
																					onblur="setEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');"
																					type="text" placeholder="dd-mm-yyyy" ${strEvent} />
																				<span
																					class="glyphicon glyphicon-calendar form-control-feedback"></span>
																				<div class="required-icon">
																					<div class="text">*</div>
																				</div>
																			</div>
																		</c:when>
																		<c:otherwise>
																			<input class="form-control"
																				name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																				id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																				maxlength="${dynamicDetailfield.length}"
																				value="<%=value %>"
																				onblur="setEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');"
																				type="text" placeholder="dd-mm-yyyy" ${strEvent} />
																			<span
																				class="glyphicon glyphicon-calendar form-control-feedback"></span>
																		</c:otherwise>
																	</c:choose>

																</div>
															</div>
															<script type="text/javascript">
		
													      jQuery('#${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}').datepicker({
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
																				id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																				name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																				value="<%=value %>"
																				placeholder="Enter ${dynamicDetailfield.displayName}"
																				onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');"
																				maxlength="${dynamicDetailfield.length}" type="text"
																				class="form-control" ${strEvent} />
																			<div class="required-icon">
																				<div class="text">*</div>
																			</div>
																		</div>
																	</c:when>
																	<c:otherwise>
																		<input
																			id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																			name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																			value="<%=value %>"
																			placeholder="Enter ${dynamicDetailfield.displayName}"
																			onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');"
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
																	<input
																		id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																		name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																		type="text"
																		onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');"
																		class="form-control" rows="2" cols="18" ${strEvent}
																		placeholder="Enter ${dynamicDetailfield.displayName}"
																		value="<%=value %>"
																		onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId},${dynamicDetailfield.length});" />
																	<div class="required-icon">
																		<div class="text">*</div>
																	</div>
																</div>
															</c:when>
															<c:otherwise>
																<input
																	id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																	name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																	type="text"
																	onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');"
																	class="form-control" rows="2" cols="18" ${strEvent}
																	placeholder="Enter ${dynamicDetailfield.displayName}"
																	value="<%=value %>"
																	onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId},${dynamicDetailfield.length});" />
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
																				id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																				name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																				${strEvent}
																				onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');">
																				<c:choose>
																					<c:when
																						test="${dynamicDetailfield.fieldValue eq ''}">
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
																				id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																				name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																				${strEvent}
																				onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');">
																				<c:set var="SelectedValue" value="<%=value%>" />
																				<c:forEach var="pickListOptions"
																					items="${dynamicDetailpickListOptions}">

																					<c:if
																						test="${FieldId==pickListOptions.dynamicFieldId}">
																						<c:choose>
																							<c:when
																								test="${SelectedValue==pickListOptions.pickListOption}">
																								<option
																									value="${pickListOptions.pickListOption}"
																									selected="selected"><c:out
																										value="${pickListOptions.pickListOption}" /></option>
																							</c:when>
																							<c:otherwise>
																								<option
																									value="${pickListOptions.pickListOption}"><c:out
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
																			id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																			name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																			${strEvent}
																			onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');">
																			<c:choose>
																				<c:when
																					test="${dynamicDetailfield.fieldValue eq ''}">
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
																			id="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																			name="${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}"
																			${strEvent}
																			onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.purchasePriceId}');">
																			<c:set var="SelectedValue" value="<%=value%>" />
																			<c:forEach var="pickListOptions"
																				items="${dynamicDetailpickListOptions}">
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
						<div class='col-md-0-5 col-xs-12'>
           <div class='form-group'>
               <a  name="delete_${det.purchasePriceId}" id="delete_${det.purchasePriceId}" onclick="deleteApprovedPriceListDet('${heat_List.supplierId}','${det.purchasePriceId}')"><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
               
						</div>
						</div>
						</c:if>
						
						<div id="size_grid_${det.purchasePriceId}">
						${det.sizeGrid}
						</div>
						
						<c:set var="k" value="${k+1}" />
						
						</div>
					</c:forEach>

	<!-- ------------------------------------------------- End Edit Details Row --------------------------------------------------------------- -->
              
	<!-- --------------------------------------------------- Add new Detail Row Start--------------------------------------------------------------- -->
 <c:set var="t_det_id" value="0"/>
<div class='row linerow'>
<div class='col-md-0-5 col-xs-12' >
<div class='form-group grid-slno'>${k}</div>
</div>

						<c:forEach items="${dynamicFieldsListAppPriceList}"
							var="dynamicDetailfield">
							<c:choose>
								<c:when test="${dynamicDetailfield.fixed}">
									<c:set var="ControlName"
										value="${dynamicDetailfield.pageFieldName}" />

									<c:choose>
										<c:when test="${ControlName eq 'item_id'}">
											<div class='col-md-2 col-xs-12'>
												<div class='form-group'>
													<div class="required-field-block">
														<input type="text" class="form-control"
															<c:if test="${mode ne 'n'}">autofocus</c:if>
															name="mat_${t_det_id}" id="mat_${t_det_id}"
															placeholder="Type and select ${dynamicDetailfield.displayName}"
															onkeyup="clearId(event,'mat_${t_det_id}','mat_id_${t_det_id}')"
															maxlength="${dynamicDetailfield.length}" onblur="getItemGroupData(document.getElementById('mat_id_${t_det_id}').value,${t_det_id});" />
														<input type="hidden" name="mat_id_${t_det_id}"
															id="mat_id_${t_det_id}" />
														<div class="required-icon">
															<div class="text">*</div>
														</div>
													</div>
												</div>


											</div>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${ControlName=='color_id'}">
													<div class='col-md-1 col-xs-12'>
														<div class='form-group'>
																<input type="text" class="form-control "
																	placeholder="${dynamicDetailfield.displayName}"
																	name="color_name_${t_det_id}" id="color_name_${t_det_id}" />
																	<input type="hidden" id="color_id_${t_det_id}" name="color_id_${t_det_id}" >
																
														</div>
													</div>
												</c:when>

												<c:otherwise>
													<c:choose>
														<c:when test="${ControlName=='uom'}">
															<div class='col-md-1 col-xs-12'>
																<div class='form-group'>
																	<input type="text" name="uom_${t_det_id}"
																		placeholder="Type and select ${dynamicDetailfield.displayName}"
																		class="form-control" id="uom_${t_det_id}"
																		maxlength="${dynamicDetailfield.length}"
																		value="${detail.variantName}" /> 
																</div>
															</div>
														</c:when>

														<c:otherwise>
															<c:choose>
																<c:when test="${ControlName=='rate'}">
																	<div class='col-md-1 col-xs-12'>
																		<div class='form-group'>
																		<div class="required-field-block">
																				<input type="text" class="form-control"
																					placeholder="${dynamicDetailfield.displayName}"
																					maxlength="${dynamicDetailfield.length}"
																					<c:if test="${header_info.isMultipleUom eq 'No'}"> readonly tabindex="-1"</c:if>
																					name="rate_${t_det_id}" id="rate_${t_det_id}" /> 
																					
																		 <div class="required-icon">
																				<div class="text">*</div>
																			</div>
																		 </div>

																					
																		</div>
																	</div>
																</c:when>

																<c:otherwise>
																	<c:choose>
																		<c:when test="${ControlName=='currency_name'}">
																			<div class='col-md-1 col-xs-12'>
																				<div class='form-group'>
																					<input type="text" class="form-control "
																						name="currency_name_${t_det_id}"
																						id="currency_name_${t_det_id}"
																						placeholder="${dynamicDetailfield.displayName}" />
																				</div>
																			</div>
																		</c:when>

																		<c:otherwise>
																			<c:choose>
																				<c:when test="${ControlName=='lead_time_days'}">
																					<div class='col-md-1 col-xs-12'>
																						<div class='form-group'>
																									<input class="form-control"
																										name="lead_time_days_${t_det_id}"
																										id="lead_time_days_${t_det_id}" type="text"
																										placeholder="${dynamicDetailfield.displayName}" />
																									
																						</div>

																					</div>
																				</c:when>
																				<c:otherwise>
																					<c:choose>
																						<c:when test="${ControlName=='supplier_desc'}">
																							<div id="supplier_desc_0" data-trigger="hover"
																								data-toggle="popover" data-placement="top"
																								title="Description" class='col-md-2 col-xs-12'>
																								<div class='form-group'>
																									<textarea class="form-control" rows="1"
																										id="supplier_desc_${t_det_id}"
																										name="supplier_desc_${t_det_id}"
																										placeholder="${dynamicDetailfield.displayName}"
																										maxlength="${dynamicDetailfield.length}"></textarea>
																								</div>
																							</div>
																						</c:when>
																						<c:otherwise>
																							<c:choose>

																								<c:when test="${ControlName=='moq'}">
																									<div class='col-md-1 col-xs-12'>
																										<div class='form-group'>
																											<input type="text" class="form-control"
																												maxlength="${dynamicDetailfield.length}"
																												name="moq_${t_det_id}"
																												placeholder="Enter ${dynamicDetailfield.displayName}"
																												id="moq_${t_det_id}" />
																										</div>
																									</div>
																								</c:when>
																								<c:otherwise>
																									<c:choose>
																										<c:when test="${ControlName=='size_range_id'}">
																											<div class='col-md-1 col-xs-12'>
																												<div class='form-group'>
																													<input type="text"
																														class="form-control "
																														placeholder="${dynamicDetailfield.displayName}"
																														name="size_range_${t_det_id}"
																														id="size_range_${t_det_id}"
																														onblur="getSizeDetailsGrid(${t_det_id});"> <input
																														type="hidden"
																														id="size_range_id_${t_det_id}"
																														name="size_range_id_${t_det_id}" value="">
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
									<%-- </c:otherwise>
													</c:choose> --%>
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
																				id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																				maxlength="${dynamicDetailfield.length}" type="text"
																				placeholder="dd-mm-yyyy" ${strEvent} /> <span
																				class="glyphicon glyphicon-calendar form-control-feedback"></span>
																			<div class="required-icon">
																				<div class="text">*</div>
																			</div>
																		</div>
																		<span class="input-group-addon"> <span
																			class="glyphicon glyphicon-calendar"></span>
																		</span>
																	</c:when>
																	<c:otherwise>
																		<input class="form-control"
																			name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																			id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																			maxlength="${dynamicDetailfield.length}" type="text"
																			placeholder="dd-mm-yyyy" ${strEvent} />
																		<span
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
																			value="${dynamicDetailfield.fieldValue}"
																			placeholder="Enter ${dynamicDetailfield.displayName}"
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
																		value="${dynamicDetailfield.fieldValue}"
																		placeholder="Enter ${dynamicDetailfield.displayName}"
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
																<input
																	id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																	name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																	type="text"
																	placeholder="Enter ${dynamicDetailfield.displayName}"
																	class="form-control" rows="2" cols="18" ${strEvent}
																	onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_${t_det_id},${dynamicDetailfield.length});" />
																<div class="required-icon">
																	<div class="text">*</div>
																</div>
															</div>
														</c:when>
														<c:otherwise>
															<input
																id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																type="text"
																placeholder="Enter ${dynamicDetailfield.displayName}"
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
																			name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																			${strEvent}>
																			<c:choose>
																				<c:when
																					test="${dynamicDetailfield.fieldValue eq ''}">
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
																			name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																			${strEvent}>
																			<c:set var="SelectedValue"
																				value="${dynamicDetailfield.fieldValue}" />
																			<c:forEach var="pickListOptions"
																				items="${dynamicDetailpickListOptions}">

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
																		id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																		name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																		${strEvent}>
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
																		name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																		${strEvent}>
																		<c:set var="SelectedValue"
																			value="${dynamicDetailfield.fieldValue}" />
																		<c:forEach var="pickListOptions"
																			items="${dynamicDetailpickListOptions}">

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
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						

                 <div class='col-md-0-5 col-xs-12'>
                     <a href="javascript:clearNewDetail('${t_det_id}')" name="clear_${t_det_id}" id="clear_${t_det_id}" title="Clear"  ><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
						</div>
						<div id="size_grid">
						${size_grid}
						</div>
             </div>
			<div class="row">
			<div class="col-sm-12">
			
			<a href="javascript:validateAppPriceList('${mode}','1');" ><span class="glyphicon glyphicon-plus" class="text-primary"></span>&nbsp;&nbsp;Add Another Line</a>
			
			</div>
              </div>
              </div>
              </div>
              </div>
              
         <!-- ---------------------------- Add new Detail Row END--------------------------------------------------------------- -->
            
  
          
	
	<div class='row '>
   					<div class="col-sm-12">
   				 <div class='pull-right centered save-view'>            
                 <div class="btn-group dropup">
               	 <button name="save" id="save"   onclick="validateAppPriceList('${mode}','1')" class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			   	 </button>
               	 <button    class="btn btn-success dropdown-toggle" data-toggle="dropdown" ><span class="caret"></span></button>
                 <ul class="dropdown-menu">
           		<%--  <li><a href="javascript:validateAppPriceList('${mode}','2')" >Save & Send</a></li> --%>
          		 <li><a href="javascript:validateAppPriceList('${mode}','3')" >Save & New</a></li>
                 <li class="divider"></li>
                 <li><a  href="javascript:validateAppPriceList('${mode}','4')">Save & Close</a></li>
                 </ul>
                 </div>
                 <button type="button" class="btn btn-primary"  onclick="showApprovedPriceList()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
                 </div>
           		 </div>              
    </div> 	
    <div class='row'  style="margin-top:20px;">
 	</div>


</div>
</div>
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
   <input type="hidden" name="purchase_price_id" id="purchase_price_id" value="${purchaseId}" />
   <input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
   <input type="hidden" name="purchase_price" id="purchase_price" value="${purchase_price}"/>
   
   
<script>
$('#validate_approvedPriceList').bootstrapValidator({
	//  live: 'disabled',
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	/*   excluded: ':disabled', */
	  fields: {
		  supplier_name: {
	            validators: {
	                notEmpty: {
	                    message: 'This field is required'
	                },
	                callback: {
	                    message: 'This field is required',
	                    callback: function(value, validator, $field) {
	                    	if ($("#supplier_id").val()==0) {
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
	        mat_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#mat_id_0").val()==0) {
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
	          lead_time_days_0:{
	              validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          moq_0:{
	              validators: {
	            	     numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          rate_0: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          <c:forEach var="detId" items="${det_id_list}">
	          
	          mat_S_${detId}: {
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
	          lead_time_days_S_${detId}:{
	              validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          moq_S_${detId}:{
	              validators: {
	            	     numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          rate_S_${detId}: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          
	          </c:forEach>
		  
	  }
});
</script>   
   
<script>
!function ($) {
	 
	$(function(){
		
		$('#supplier_name').listSupplier({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSupplier&create_new=1",
			  nameField :'#supplier_name',
			  idField:'#supplier_id',
			  formId:'#validate_approvedPriceList',
			  validateId:'supplier_name'
		 });
		$('#mat_0').listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=1",
			  nameField :'#mat_0',
			  idField:'#mat_id_0',
			  formId:'#validate_approvedPriceList',
			  validateId:'mat_0'
		  });
		
		
		$('#color_name_0').listVariant({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
			  nameField :'#color_name_0',
			  idField:'#color_id_0'
			  /* formId:'#validate_approvedPriceList',
			  validateId:'color_name_0' */
		  });
		
		$('#currency_name_0').listCurrency({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCurrency&create_new=1",
			  nameField :'#currency_name_0'
			  /* idField:'#currency_id_0' */
		  });
		
		$('#uom_0').listUOM({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=1",
			  nameField :'#uom_0',
			  idField:'#uom_id_0'
		  });
		$('#size_range_0').listSizeRange({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
			  nameField :'#size_range_0',
			  idField:'#size_range_id_0'
			  //formId:'#validate_approvedPriceList',
			  //validateId:'size_range_0'
		  });
		
		 <c:forEach var="detId" items="${det_id_list}">
		
		 $('#mat_S_${detId}').listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=1",
			  nameField :'#mat_S_${detId}',
			  idField:'#item_id_S_${detId}',
			  formId:'#validate_approvedPriceList',
			  validateId:'mat_S_${detId}'
		  });
		
		
		 $('#color_name_S_${detId}').listColor({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
			  nameField :'#color_name_S_${detId}',
			  idField:'#color_id_S_${detId}',
			  formId:'#validate_approvedPriceList',
			  validateId:'color_name_S_${detId}'
		  });
		 
		$('#currency_name_S_${detId}').listCurrency({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCurrency&create_new=1",
			  nameField :'#currency_name_S_${detId}'
			 /*  idField:'#currency_id_S_${detId}' */
		  });
		
		$('#uom_S_${detId}').listUOM({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=1",
			  nameField :'#uom_S_${detId}',
			  idField:'#uom_id_S_${detId}'
		  });
		$('#size_range_S_${detId}').listSizeRange({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
			  nameField :'#size_range_S_${detId}',
			  idField:'#size_range_id_S_${detId}'
			  //formId:'#validate_approvedPriceList',
			  //validateId:'size_range_0'
		  });
		 </c:forEach>
		 
	 });
}(window.jQuery);

	</script>  
</form>

 <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>