<%@ page import="java.util.Map"%>
<!-------------------------------------------------------  Detail Header Start  ------------------------------------------------------->

<div class="row" style="margin-top: 50px;">
	<table class="table table-bordered table-condensed table-hover">
	<tr>

	<!-- <div class="col-md-0-5 col-xs-12" id="header">
		<label>#</label>
	</div> -->
	<c:forEach items="${dynamicFieldsListBODetail}" var="dynamicDetailfield">
	<c:choose>
	 <c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		
		<c:choose>
		<c:when test="${ControlName=='line_no'}">		
			<div class="col-md-0-5 col-xs-12" id="header">
				<label >${dynamicDetailfield.displayName}</label>
			</div>
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='item_id'}">
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='colour_id'}">
			
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='buyer_style_no'}">
			
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range'}">
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty'}">
			
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='rate'}">
			
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_date'}">
			
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='client_delivery_date'}">
			
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='mrp'}">
			
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='barcode'}">
			
				<div class="col-sm-2" id="header">
					<label >${dynamicDetailfield.displayName}</label>
				</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
				<div class="col-sm-2" id="header">
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
	</c:when>
	<c:otherwise>
		<c:set var="strEvent" value="" />
		<c:set var="FieldId" value="${dynamicDetailfield.dynamicFieldId}" />

		<c:forEach var="events" items="${dynamicDetailfieldEvents}">
			<c:if test="${FieldId==events.dynamicFieldId}">
				<c:choose>
					<c:when test="${events.eventName=='onkeypress'}">
						<c:set var="strEvent"
							value="${strEvent}${events.eventName}='return ${events.functionName};'" />
					</c:when>
					<c:otherwise>
						<c:set var="strEvent"
							value="${strEvent}${events.eventName}='${events.functionName};'" />
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>

		<c:set var="picklistValueExists" value="false" />
		<c:if test="${not empty dynamicDetailfieldpickListOptions}">
			<c:forEach var="pickListOptions" items="${dynamicDetailfieldpickListOptions}">
				<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
					<c:set var="picklistValueExists" value="true" />
				</c:if>
			</c:forEach>
		</c:if>

		<!-- Header for the dynamic controls -->
		<div class='col-md-2 col-xs-12' id="header">
			<label for="customer">${dynamicDetailfield.displayName}</label>
		</div>
	</c:otherwise>
	</c:choose>
</c:forEach>
<div id="header">
	 <label >&nbsp;</label>
</div>
</div>


<!-------------------------------------------------------  Detail Header End  ------------------------------------------------------->







<!-------------------------------------------------------  Detail Edit Row Start  ------------------------------------------------------->


<c:set var="det_map" value="${detMapList}"/>
<%
	Map<String,String> dtMap=(Map<String,String>)pageContext.getAttribute("det_map");
%>
<c:set var="k" value="${1}"/>
<c:forEach var="det" items="${detList}">

<div class='row linerow'>
						

<c:forEach items="${dynamicFieldsListBODetail}" var="dynamicDetailfield">
<c:choose>
	<c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		<c:choose>
		<c:when test="${ControlName=='line_no'}">
			<div class='col-xs-0-5 col-xs-12' >
				 <div class='form-group grid-slno' >${k}</div>
			</div>		
			<%-- <div class='col-sm-2'>
				<div class='form-group'>
					<input type="text" class="form-control" id="line_no_S_${det.boDetId}" name="line_no_S_${det.boDetId}" onblur="setEditedId('line_no_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="${dynamicDetailfield.displayName}">
				</div>
			</div> --%>
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='item_id'}">
			<div class='col-sm-2'>
				<div class='form-group'>
					<input type="text" class="form-control" id="item_name_S_${det.boDetId}" name="item_name_S_${det.boDetId}" onblur="setEditedId('item_name_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="item_id_S_${det.boDetId}" name="item_id_S_${det.boDetId}" >
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='colour_id'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="colour_name_S_${det.boDetId}" name="colour_name_S_${det.boDetId}" onblur="setEditedId('colour_name_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="colour_id_S_${det.boDetId}" name="colour_id_S_${det.boDetId}" >
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='buyer_style_no'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="buyer_style_no_S_${det.boDetId}" name="buyer_style_no_S_${det.boDetId}" onblur="setEditedId('buyer_style_no_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range'}">
			<div class="col-sm-2">
				<div class='form-group'>
					<input type="text" class="form-control" id="size_range_S_${det.boDetId}" name="size_range_S_${det.boDetId}" onblur="setEditedId('size_range_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="size_range_id_S_${det.boDetId}" name="size_range_id_S_${det.boDetId}" >
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					<input type="text" class="form-control" id="qty_S_${det.boDetId}" name="qty_S_${det.boDetId}" onblur="setEditedId('qty_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='rate'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					<input type="text" class="form-control" id="rate_S_${det.boDetId}" name="rate_S_${det.boDetId}" onblur="setEditedId('rate_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_date'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="required_date_S_${det.boDetId}" name="required_date_S_${det.boDetId}" onblur="setEditedId('required_date_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
				</div>
				
				<script type="text/javascript">
				 jQuery('#required_date_S_${det.boDetId}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#validate-form').bootstrapValidator('revalidateField', 'required_date_S_${det.boDetId}');
		        		},
		        		changeMonth: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	        			},
	        			changeYear: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	        			}
     			});
				</script>
				
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='client_delivery_date'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="client_delivery_date_S_${det.boDetId}" name="client_delivery_date_S_${det.boDetId}" onblur="setEditedId('client_delivery_date_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
				</div>
				
				<script type="text/javascript">
				 jQuery('#client_delivery_date_S_${det.boDetId}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#validate-form').bootstrapValidator('revalidateField', 'client_delivery_date_S_${det.boDetId}');
		        		},
		        		changeMonth: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	        			},
	        			changeYear: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	        			}
     			});
				</script>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='mrp'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="mrp_S_${det.boDetId}" name="mrp_S_${det.boDetId}" onblur="setEditedId('mrp_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='barcode'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="barcode_S_${det.boDetId}" name="barcode_S_${det.boDetId}" onblur="setEditedId('barcode_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark_S_${det.boDetId}" name="remark_S_${det.boDetId}" onblur="setEditedId('remark_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
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
	</c:when>
			<c:otherwise>
				<c:set var="strEvent" value="" />
				<c:set var="FieldId" value="${dynamicDetailfield.dynamicFieldId}" />

				<c:forEach var="events" items="${dynamicDetailFieldEvents}">
					<c:if test="${FieldId==events.dynamicFieldId}">
						<c:choose>
							<c:when test="${events.eventName=='onkeypress'}">
								<c:set var="strEvent" value=" ${strEvent}${events.eventName}='return ${events.functionName};'" />
							</c:when>
							<c:otherwise>
								<c:set var="strEvent" value=" ${strEvent}${events.eventName}='${events.functionName};'" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>

				<c:set var="picklistValueExists" value="false" />
				<c:if test="${not empty dynamicDetailpickListOptions}">
					<c:forEach var="pickListOptions" items="${dynamicDetailpickListOptions}">
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
										<div class="input-daterange has-feedback" id='${dynamicDetailfield.pageFieldName}_S_${det.boDetId}'>
											<c:choose>
												<c:when test="${dynamicDetailfield.required}">
													<div class="required-field-block">
														<input 
															class="form-control"
															name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
															id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
															maxlength="${dynamicDetailfield.length}" 
															type="text"
															placeholder="dd-mm-yyyy" ${strEvent} /> 
															<span class="glyphicon glyphicon-calendar form-control-feedback"></span>
														<div class="required-icon">
															<div class="text">*</div>
														</div>
													</div>
													<span class="input-group-addon"> 
														<span class="glyphicon glyphicon-calendar"></span>
													</span>
												</c:when>
												<c:otherwise>
													<input 
														class="form-control"
														name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														maxlength="${dynamicDetailfield.length}" 
														type="text"
														placeholder="dd-mm-yyyy" ${strEvent} />
													<span class="glyphicon glyphicon-calendar form-control-feedback"></span>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<script type="text/javascript">

								      jQuery('#${dynamicDetailfield.pageFieldName}_S_${det.boDetId}').datepicker({
										    format: "dd-mm-yyyy"
										}).on({
											changeDate: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						             		// Revalidate the date field
						             			 jQuery('#validate-form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');
							        		},
							        		changeMonth: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						        			},
						        			changeYear: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						        			}
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
														id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														value="<c:out value="${dynamicDetailfield.fieldValue}"/>"
														placeholder="Enter ${dynamicDetailfield.displayName}"
														maxlength="${dynamicDetailfield.length}" 
														type="text"
														class="form-control" ${strEvent} />
													<div class="required-icon">
														<div class="text">*</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<input
													id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
													name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
													value="<c:out value="${dynamicDetailfield.fieldValue}"/>"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													maxlength="${dynamicDetailfield.length}" 
													type="text"
													class="form-control" ${strEvent} />
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
								<div class='col-md-2 col-xs-12'>
									<div class='form-group'>
										<c:choose>
											<c:when test="${dynamicDetailfield.required}">
												<div class="required-field-block">
													<input
														id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														type="text"
														placeholder="Enter ${dynamicDetailfield.displayName}"
														class="form-control" 
														rows="2" 
														cols="18" ${strEvent}
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.boDetId},${dynamicDetailfield.length});" />
													<div class="required-icon">
														<div class="text">*</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<input
													id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
													name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
													type="text"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													class="form-control" 
													rows="2" 
													cols="18" ${strEvent}
													onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.boDetId},${dynamicDetailfield.length});" />
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:when>
							 <c:otherwise>
									
									<c:set var="f_name" value="${dynamicDetailfield.dbFieldName}${det.boDetId}" />
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
															id='${dynamicDetailfield.pageFieldName}_S_${det.boDetId}'>
															<c:choose>
												               <c:when test="${dynamicDetailfield.required}">
														        <div class="required-field-block">
															 <input class="form-control"
																name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" maxlength="${dynamicDetailfield.length}" 
																value="<%=value %>" 
																onblur="setEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');"  type="text"
																placeholder="dd-mm-yyyy" ${strEvent}/> <span
																class="glyphicon glyphicon-calendar form-control-feedback"></span>
															    <div class="required-icon">
															    <div class="text">*</div>
														        </div>
														        </div>
												               </c:when>
												               <c:otherwise>
												               <input class="form-control"
																name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" maxlength="${dynamicDetailfield.length}" 
																value="<%=value %>" 
																onblur="setEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');"  type="text"
																placeholder="dd-mm-yyyy" ${strEvent}/> <span
																class="glyphicon glyphicon-calendar form-control-feedback"></span>
												               </c:otherwise>
												               </c:choose>
														
														</div>
														</div>
														<script type="text/javascript">
		
													      jQuery('#${dynamicDetailfield.pageFieldName}_S_${det.boDetId}').datepicker({
															    format: "dd-mm-yyyy"
															}).on({
																changeDate: function(event) {
											             			event.preventDefault();
											             			event.stopPropagation();
											             		// Revalidate the date field
											             			 jQuery('#validate-form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');
												        		},
												        		changeMonth: function(event) {
											             			event.preventDefault();
											             			event.stopPropagation();
											        			},
											        			changeYear: function(event) {
											             			event.preventDefault();
											             			event.stopPropagation();
											        			}
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
																id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																value="<%=value %>"  placeholder="Enter ${dynamicDetailfield.displayName}" 
																onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');"
																maxlength="${dynamicDetailfield.length}" type="text"
																class="form-control" ${strEvent} />
														     <div class="required-icon">
															<div class="text">*</div>
														     </div>
														     </div>
											               </c:when>
											               <c:otherwise>
											           					<input
																id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																value="<%=value %>"  placeholder="Enter ${dynamicDetailfield.displayName}" 
																onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');"
																maxlength="${dynamicDetailfield.length}" type="text"
																class="form-control" ${strEvent} />
											               </c:otherwise>
											               </c:choose>
										
														</div>
														</div>


													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
											<c:choose>
											<c:when
											test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
											<div class='col-md-2 col-xs-12'>
											<div class='form-group'>
											 <c:choose>
											               <c:when test="${dynamicDetailfield.required}">
									               		   <div class="required-field-block">
										                  					<input id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" type="text" onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');"
														class="form-control" rows="2" cols="18" ${strEvent} placeholder="Enter ${dynamicDetailfield.displayName}" 
														value="<%=value %>" 
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.boDetId},${dynamicDetailfield.length});" />
														     <div class="required-icon">
															<div class="text">*</div>
														     </div>
														     </div>
											               </c:when>
											               <c:otherwise>
											           					<input id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" type="text" onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');"
														class="form-control" rows="2" cols="18" ${strEvent} placeholder="Enter ${dynamicDetailfield.displayName}" 
														value="<%=value %>" 
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.boDetId},${dynamicDetailfield.length});" />
											               </c:otherwise>
											               </c:choose>
											
												</div>
												</div>
												</c:when>
												<c:otherwise>
											<c:choose>
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
																id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" ${strEvent} onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');">
																<c:choose>
																	<c:when test="${dynamicDetailfield.fieldValue eq ''}">
																		<option value="Select">----Select----</option>
																	</c:when>
																	<c:otherwise>
																		<option value="<c:out value="${dynamicDetailfield.fieldValue}"/>">${dynamicDetailfield.fieldValue}</option>
																	</c:otherwise>
																</c:choose>
															</select>
														</c:when>
														<c:otherwise>
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" ${strEvent} onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');">
																<c:set var="SelectedValue"
																	value="<%=value %>"  />
																<c:forEach var="pickListOptions"
																	items="${dynamicDetailpickListOptions}">
																	
																	<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																		<c:choose>
																			<c:when
																				test="${SelectedValue==pickListOptions.pickListOption}">
																				<option value="<c:out value="${pickListOptions.pickListOption}"/>"
																					selected="selected"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:when>
																			<c:otherwise>
																				<option value="<c:out value="${pickListOptions.pickListOption}"/>"><c:out
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
																id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" ${strEvent} onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');">
																<c:choose>
																	<c:when test="${dynamicDetailfield.fieldValue eq ''}">
																		<option value="Select">----Select----</option>
																	</c:when>
																	<c:otherwise>
																		<option value="<c:out value="${dynamicDetailfield.fieldValue}"/>">${dynamicDetailfield.fieldValue}</option>
																	</c:otherwise>
																</c:choose>
															</select>
														</c:when>
														<c:otherwise>
															<select class="form-control"
																id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" ${strEvent} onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');">
																<c:set var="SelectedValue"
																	value="<%=value %>"  />
																<c:forEach var="pickListOptions"
																	items="${dynamicDetailpickListOptions}">
																	<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																		<c:choose>
																			<c:when
																				test="${SelectedValue==pickListOptions.pickListOption}">
																				<option value="<c:out value="${pickListOptions.pickListOption}"/>"
																					selected="selected"><c:out
																						value="${pickListOptions.pickListOption}" /></option>
																			</c:when>
																			<c:otherwise>
																				<option value="<c:out value="${pickListOptions.pickListOption}"/>"><c:out
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
										</c:otherwise>
										</c:choose>
									</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
</c:forEach>

<c:if test="${view_mode ne 'v' }">
<div class='col-md-0-5 col-xs-12'>
	<div class='form-group'>
		<a  name="delete_${det.boDetId}" id="delete_${det.boDetId}" onclick="deleteBuyerOrderDet('${det.boId}','${det.boDetId}')"><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
	</div>
</div>
</c:if>
<c:set var="k" value="${k+1}" />
</div>
</c:forEach>
<!-------------------------------------------------------  Detail Edit Row End  ------------------------------------------------------->











<!-------------------------------------------------------  Detail New Row Start  ------------------------------------------------------->
<div class="row linerow">


<c:set var="tmp_det_id" value="0"/>

<c:forEach items="${dynamicFieldsListBODetail}" var="dynamicDetailfield">
<c:choose>
	<c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		<c:choose>
		<c:when test="${ControlName=='line_no'}">		
			<%-- <div class='col-sm-2'>
				<div class='form-group'>
					<input type="text" class="form-control" id="line_no_${tmp_det_id}" name="line_no_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="${dynamicDetailfield.displayName}">
				</div>
			</div> --%>
				<div class='col-md-0-5 col-xs-12' >
					<div class='form-group grid-slno'>${k}</div>
				</div>
							
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='item_id'}">
			<div class='col-sm-2'>
				<div class='form-group'>
					<input type="text" class="form-control" id="item_name_${tmp_det_id}" name="item_name_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="item_id_${tmp_det_id}" name="item_id_${tmp_det_id}" >
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='colour_id'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="colour_name_${tmp_det_id}" name="colour_name_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="colour_id_${tmp_det_id}" name="colour_id_${tmp_det_id}" >
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='buyer_style_no'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="buyer_style_no_${tmp_det_id}" name="buyer_style_no_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range'}">
			<div class="col-sm-2">
				<div class='form-group'>
					<input type="text" class="form-control" id="size_range_${tmp_det_id}" name="size_range_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="size_range_id_${tmp_det_id}" name="size_range_id_${tmp_det_id}" >
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					<input type="text" class="form-control" id="qty_${tmp_det_id}" name="qty_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='rate'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					<input type="text" class="form-control" id="rate_${tmp_det_id}" name="rate_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_date'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="required_date_${tmp_det_id}" name="required_date_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
				</div>
				
				<script type="text/javascript">
				 jQuery('#required_date_${tmp_det_id}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#validate-form').bootstrapValidator('revalidateField', 'required_date_${tmp_det_id}');
		        		},
		        		changeMonth: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	        			},
	        			changeYear: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	        			}
     			});
				</script>
				
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='client_delivery_date'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="client_delivery_date_${tmp_det_id}" name="client_delivery_date_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
				</div>
				
				<script type="text/javascript">
				 jQuery('#client_delivery_date_${tmp_det_id}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#validate-form').bootstrapValidator('revalidateField', 'client_delivery_date_${tmp_det_id}');
		        		},
		        		changeMonth: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	        			},
	        			changeYear: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	        			}
     			});
				</script>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='mrp'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="mrp_${tmp_det_id}" name="mrp_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='barcode'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="barcode_${tmp_det_id}" name="barcode_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
			
			<div class="col-sm-2">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark_${tmp_det_id}" name="remark_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
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
	</c:when>
			<c:otherwise>
				<c:set var="strEvent" value="" />
				<c:set var="FieldId" value="${dynamicDetailfield.dynamicFieldId}" />

				<c:forEach var="events" items="${dynamicDetailFieldEvents}">
					<c:if test="${FieldId==events.dynamicFieldId}">
						<c:choose>
							<c:when test="${events.eventName=='onkeypress'}">
								<c:set var="strEvent" value=" ${strEvent}${events.eventName}='return ${events.functionName};'" />
							</c:when>
							<c:otherwise>
								<c:set var="strEvent" value=" ${strEvent}${events.eventName}='${events.functionName};'" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>

				<c:set var="picklistValueExists" value="false" />
				<c:if test="${not empty dynamicDetailpickListOptions}">
					<c:forEach var="pickListOptions" items="${dynamicDetailpickListOptions}">
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
										<div class="input-daterange has-feedback" id='${dynamicDetailfield.pageFieldName}_${tmp_det_id}'>
											<c:choose>
												<c:when test="${dynamicDetailfield.required}">
													<div class="required-field-block">
														<input 
															class="form-control"
															name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
															id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
															maxlength="${dynamicDetailfield.length}" 
															type="text"
															placeholder="dd-mm-yyyy" ${strEvent} /> 
															<span class="glyphicon glyphicon-calendar form-control-feedback"></span>
														<div class="required-icon">
															<div class="text">*</div>
														</div>
													</div>
													<span class="input-group-addon"> 
														<span class="glyphicon glyphicon-calendar"></span>
													</span>
												</c:when>
												<c:otherwise>
													<input 
														class="form-control"
														name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
														id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
														maxlength="${dynamicDetailfield.length}" 
														type="text"
														placeholder="dd-mm-yyyy" ${strEvent} />
													<span class="glyphicon glyphicon-calendar form-control-feedback"></span>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<script type="text/javascript">

								      jQuery('#${dynamicDetailfield.pageFieldName}_${tmp_det_id}').datepicker({
										    format: "dd-mm-yyyy"
										}).on({
											changeDate: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						             		// Revalidate the date field
						             			 jQuery('#validate-form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_${tmp_det_id}');
							        		},
							        		changeMonth: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						        			},
						        			changeYear: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						        			}
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
														id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
														name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
														value="<c:out value="${dynamicDetailfield.fieldValue}"/>"
														placeholder="Enter ${dynamicDetailfield.displayName}"
														maxlength="${dynamicDetailfield.length}" 
														type="text"
														class="form-control" ${strEvent} />
													<div class="required-icon">
														<div class="text">*</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<input
													id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
													name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
													value="<c:out value="${dynamicDetailfield.fieldValue}"/>"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													maxlength="${dynamicDetailfield.length}" 
													type="text"
													class="form-control" ${strEvent} />
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
								<div class='col-md-2 col-xs-12'>
									<div class='form-group'>
										<c:choose>
											<c:when test="${dynamicDetailfield.required}">
												<div class="required-field-block">
													<input
														id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
														name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
														type="text"
														placeholder="Enter ${dynamicDetailfield.displayName}"
														class="form-control" 
														rows="2" 
														cols="18" ${strEvent}
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_${tmp_det_id},${dynamicDetailfield.length});" />
													<div class="required-icon">
														<div class="text">*</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<input
													id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
													name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
													type="text"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													class="form-control" 
													rows="2" 
													cols="18" ${strEvent}
													onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_${tmp_det_id},${dynamicDetailfield.length});" />
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when
										test="${dynamicDetailfield.fieldTypeName == 'combobox'}">
										<div class='col-md-2 col-xs-12'>
											<div class='form-group'>
												<c:choose>
													<c:when test="${dynamicDetailfield.required}">
														<div class="required-field-block">
															<c:choose>
																<c:when test="${picklistValueExists=='false'}">
																	<select 
																		class="form-control"
																		id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
																		name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
																		${strEvent}>
																		<c:choose>
																			<c:when test="${dynamicDetailfield.fieldValue eq ''}">
																				<option value="Select">----Select----</option>
																			</c:when>
																			<c:otherwise>
																				<option value="<c:out value="${dynamicDetailfield.fieldValue}"/>">${dynamicDetailfield.fieldValue}</option>
																			</c:otherwise>
																		</c:choose>
																	</select>
																</c:when>
																<c:otherwise>
																	<select 
																		class="form-control"
																		id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
																		name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
																		${strEvent}>
																		<c:set var="SelectedValue" value="${dynamicDetailfield.fieldValue}" />
																		<c:forEach var="pickListOptions" items="${dynamicDetailpickListOptions}">

																			<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																				<c:choose>
																					<c:when test="${SelectedValue==pickListOptions.pickListOption}">
																						<option value="<c:out value="${pickListOptions.pickListOption}"/>" selected="selected"><c:out value="${pickListOptions.pickListOption}" /></option>
																					</c:when>
																					<c:otherwise>
																						<option value="<c:out value="${pickListOptions.pickListOption}"/>"><c:out value="${pickListOptions.pickListOption}" /></option>
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
																<select 
																	class="form-control"
																	id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
																	name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
																	${strEvent}>
																	<c:choose>
																		<c:when test="${dynamicDetailfield.fieldValue eq ''}">
																			<option value="Select">----Select----</option>
																		</c:when>
																		<c:otherwise>
																			<option value="<c:out value="${dynamicDetailfield.fieldValue}"/>">${dynamicDetailfield.fieldValue}</option>
																		</c:otherwise>
																	</c:choose>
																</select>
															</c:when>
															<c:otherwise>
																<select 
																	class="form-control"
																	id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
																	name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
																	${strEvent}>
																	<c:set var="SelectedValue" value="${dynamicDetailfield.fieldValue}" />
																	<c:forEach var="pickListOptions" items="${dynamicDetailpickListOptions}">

																		<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																			<c:choose>
																				<c:when test="${SelectedValue==pickListOptions.pickListOption}">
																					<option value="<c:out value="${pickListOptions.pickListOption}"/>" selected="selected"><c:out value="${pickListOptions.pickListOption}" /></option>
																				</c:when>
																				<c:otherwise>
																					<option value="<c:out value="${pickListOptions.pickListOption}"/>"><c:out value="${pickListOptions.pickListOption}" /></option>
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
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
</c:forEach>

<div class='col-md-0-5 col-xs-12'>
	<a href="#" name="clear_${tmp_det_id}" id="clear_${tmp_det_id}" title="Clear"  onclick="clearNewDetail('${tmp_det_id}');"><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
</div>

</div>

<!-------------------------------------------------------  Detail New Row End  ------------------------------------------------------->