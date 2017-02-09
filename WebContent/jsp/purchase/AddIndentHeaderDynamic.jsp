<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 
 <c:choose>
	<c:when test="${dynamicHeaderfield.fixed}">
		<c:set var="ControlName" value="${dynamicHeaderfield.pageFieldName}" />
		<c:choose>
		<c:when test="${ControlName=='indent_no'}">		
			<div class='col-sm-12'>
				<div class='form-group'>
					<label for="prefix">${dynamicHeaderfield.displayName}</label>
					<div class="form-group has-feedback">
						<select name="prefix" id="prefix" class="auto-prefix" <c:if test="${indent.headerMode ne 'n' }">disabled="disabled" </c:if> <c:if test="${indent.headerMode eq 'n'}">  autofocus </c:if> onchange="getAutoSlNo(this.value);" >
						<option value="-1">Prefix</option>
						<c:forEach var="autoPrefix" items="${header_info.autoNoPrefixList }">
						<c:choose>
						<c:when test="${header_info.indentPrefix eq autoPrefix}">
						<option value="${autoPrefix}" selected >${autoPrefix}</option>
						</c:when>
						<c:otherwise>
						<option value="${autoPrefix}">${autoPrefix}</option>
						</c:otherwise>
						</c:choose>
						</c:forEach>
						</select> 
						<input type="text" id="indent_no" name="indent_no" value="${header_info.indentNo}" maxlength="7" placeholder="${dynamicHeaderfield.displayName}" class="auto-no" <c:if test="${indent.headerMode ne 'n' or ((header_info.checkAutoNoType eq 'common') or (header_info.checkAutoNoType eq 'seperate'))}"> readonly="readonly" </c:if>  /> 
						
						
						<span   <c:if test="${indent.headerMode eq 'n'}"> data-toggle="modal" data-target="#autoNoModal" onclick="getAutoNoData();" </c:if> > 
							<a href="#" class="a-icon abs" data-toggle="tooltip" class="field-icon" title="Click here to enable or disable autogeneration of Indent number">
								<i class="glyphicon glyphicon-cog"></i>
							</a>
						</span>
						
					
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='indent_date'}">
			<div class='col-sm-12'>
				<div class='form-group'>
					<label for="indent_date">${dynamicHeaderfield.displayName}</label>
					<div class='input-group date' id='datepicker'>
						<div class="required-field-block">
							<input type='text' class="form-control" id="indent_date" name="indent_date" maxlength="${dynamicHeaderfield.length}" placeholder="Select ${dynamicHeaderfield.displayName}" <c:choose><c:when test="${new_page=='yes'}">value="${indent_date}"</c:when><c:otherwise>value="${header_info.indentDate}"</c:otherwise></c:choose> />
							<div class="required-icon">
								<div class="text">*</div>
							</div>
						</div>
						<span class="input-group-addon"> 
							<span class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>


				<script type="text/javascript">
				 jQuery('#indent_date').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#aind_form').bootstrapValidator('revalidateField', 'indent_date');
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
			<c:when test="${ControlName=='indent_type'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="type">${dynamicHeaderfield.displayName}</label>
					<div class="required-field-block">
						<input type="text" class="form-control" id="indent_type" name="indent_type" value="${header_info.indentType}"  maxlength="${dynamicHeaderfield.length}" placeholder="Type & Select ${dynamicHeaderfield.displayName}" />
						<%-- <input class="form-control " id="indent_type" name="indent_type" type="hidden" value="${header_info.indentType}" /> --%>
					</div>
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='department'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="dept">${dynamicHeaderfield.displayName}</label>
					<div class="required-field-block">
						<input type="text" class="form-control" id="department" name="department" value="${header_info.dept}" maxlength="${dynamicHeaderfield.length}" placeholder="Type & Select ${dynamicHeaderfield.displayName}" />
						
					</div>
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
	</c:when>
	
	
	
	
	
	
	
	
	
	
	
	<c:otherwise>

		<c:set var="strEvent" value="" />
		<c:set var="FieldId" value="${dynamicHeaderfield.dynamicFieldId}" />

		<c:forEach var="events" items="${dynamicHeaderFieldEvents}">
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
		<c:if test="${not empty dynamicHeaderpickListOptions}">
			<c:forEach var="pickListOptions" items="${dynamicHeaderpickListOptions}">
				<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
					<c:set var="picklistValueExists" value="true" />
				</c:if>
			</c:forEach>
		</c:if>


		<c:choose>
			<c:when test="${dynamicHeaderfield.fieldTypeName == 'textbox'}">
				<c:choose>
					<c:when test="${dynamicHeaderfield.dataTypeName == 'Date'}">
						<div class='col-sm-12'>
							<div class='form-group'>
								<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
								<div class='input-group date' id='${dynamicHeaderfield.pageFieldName}'>
									<c:choose>
										<c:when test="${dynamicHeaderfield.required}">
											<div class="required-field-block">
												<input type='text' 
													maxlength="${dynamicHeaderfield.length}"
													name="${dynamicHeaderfield.pageFieldName}"
													id="${dynamicHeaderfield.pageFieldName}"
													value="<c:out value="${dynamicHeaderfield.fieldValue}"/>"
													class="form-control"
													placeholder="Select ${dynamicHeaderfield.displayName}"
													${strEvent} />
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
												type='text' 
												maxlength="${dynamicHeaderfield.length}"
												name="${dynamicHeaderfield.pageFieldName}"
												id="${dynamicHeaderfield.pageFieldName}"
												value="<c:out value="${dynamicHeaderfield.fieldValue}"/>"
												class="form-control"
												placeholder="Select ${dynamicHeaderfield.displayName}"
												${strEvent} />
											<span class="input-group-addon"> 
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</c:otherwise>
									</c:choose>


								</div>
							</div>
						</div>
						<script type="text/javascript">
				
						      jQuery('#${dynamicHeaderfield.pageFieldName}').datepicker({
								    format: "dd-mm-yyyy"
								}).on({
									changeDate: function(event) {
				             			event.preventDefault();
				             			event.stopPropagation();
				             		// Revalidate the date field
				             			 jQuery('#aind_form').bootstrapValidator('revalidateField', '${dynamicHeaderfield.pageFieldName}');
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
					</c:when>
					<c:otherwise>
						<div class='col-sm-12'>
							<div class='form-group'>
								<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
								<c:choose>
									<c:when test="${dynamicHeaderfield.required}">
										<div class="required-field-block">
											<input 
												class="form-control"
												id="${dynamicHeaderfield.pageFieldName}"
												name="${dynamicHeaderfield.pageFieldName}"
												value="<c:out value="${dynamicHeaderfield.fieldValue}"/>"
												placeholder="Enter ${dynamicHeaderfield.displayName}"
												maxlength="${dynamicHeaderfield.length}" 
												type="text"
												size="30" ${strEvent} />
											<div class="required-icon">
												<div class="text">*</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<input 
											class="form-control"
											id="${dynamicHeaderfield.pageFieldName}"
											name="${dynamicHeaderfield.pageFieldName}"
											value="<c:out value="${dynamicHeaderfield.fieldValue}"/>"
											placeholder="Enter ${dynamicHeaderfield.displayName}"
											maxlength="${dynamicHeaderfield.length}" 
											type="text"
											size="30" ${strEvent} />
									</c:otherwise>
								</c:choose>
							</div>
						</div>

					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${dynamicHeaderfield.fieldTypeName == 'textarea'}">
						<div class='col-sm-12'>
							<div class='form-group'>
								<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
								<c:choose>
									<c:when test="${dynamicHeaderfield.required}">
										<div class="required-field-block">
											<textarea 
												id="${dynamicHeaderfield.pageFieldName}"
												name="${dynamicHeaderfield.pageFieldName}" 
												type="text"
												maxlength="${dynamicHeaderfield.length}"
												placeholder="Enter ${dynamicHeaderfield.displayName}"
												class="form-control" 
												rows="2" 
												cols="18" ${strEvent}
												onKeyUp="limitText(this.form.${dynamicHeaderfield.pageFieldName},${dynamicHeaderfield.length});">${dynamicHeaderfield.fieldValue}</textarea>
											<div class="required-icon">
												<div class="text">*</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<textarea 
											id="${dynamicHeaderfield.pageFieldName}"
											name="${dynamicHeaderfield.pageFieldName}" 
											type="text"
											maxlength="${dynamicHeaderfield.length}"
											placeholder="Enter ${dynamicHeaderfield.displayName}"
											class="form-control" 
											rows="2" 
											cols="18" ${strEvent}
											onKeyUp="limitText(this.form.${dynamicHeaderfield.pageFieldName},${dynamicHeaderfield.length});">${dynamicHeaderfield.fieldValue}</textarea>
									</c:otherwise>
								</c:choose>
							</div>
						</div>


					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${dynamicHeaderfield.fieldTypeName == 'combobox'}">
								<div class='col-sm-12'>
									<div class='form-group'>
										<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
										<c:choose>
											<c:when test="${dynamicHeaderfield.required}">
												<div class="required-field-block">
													<c:choose>
														<c:when test="${picklistValueExists=='false'}">
															<select class="form-control" id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}" ${strEvent}>
																<c:choose>
																	<c:when test="${dynamicHeaderfield.fieldValue eq ''}">
																		<option value="Select">----Select----</option>
																	</c:when>
																	<c:otherwise>
																		<option value="<c:out value="${dynamicHeaderfield.fieldValue}"/>">${dynamicHeaderfield.fieldValue}</option>
																	</c:otherwise>
																</c:choose>
															</select>
														</c:when>
														<c:otherwise>
															<select  class="form-control" id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}" ${strEvent}>
																<c:set var="SelectedValue" value="${dynamicHeaderfield.fieldValue}" />
																<c:forEach var="pickListOptions" items="${dynamicHeaderpickListOptions}">
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
														<select class="form-control" id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}" ${strEvent}>
															<c:choose>
																<c:when test="${dynamicHeaderfield.fieldValue eq ''}">
																	<option value="Select">----Select----</option>
																</c:when>
																<c:otherwise>
																	<option value="<c:out value="${dynamicHeaderfield.fieldValue}"/>">${dynamicHeaderfield.fieldValue}</option>
																</c:otherwise>
															</c:choose>
														</select>
													</c:when>
													<c:otherwise>
														<select class="form-control" id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}" ${strEvent}>
															<c:set var="SelectedValue" value="${dynamicHeaderfield.fieldValue}" />
															<c:forEach var="pickListOptions" items="${dynamicHeaderpickListOptions}">
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
							<c:otherwise>
								<c:choose>
									<c:when
										test="${dynamicHeaderfield.fieldTypeName == 'radio button'}">
										<div class='col-sm-12'>
											<div class='form-group'>
												<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
												<div class="radio">
													<c:choose>
														<c:when test="${picklistValueExists=='false'}">
															<div id="${dynamicHeaderfield.pageFieldName}" ${strEvent}></div>
														</c:when>
														<c:otherwise>
															<c:set var="SelectedValue" value="${dynamicHeaderfield.fieldValue}" />
															<c:forEach var="pickListOptions" items="${dynamicHeaderpickListOptions}">
																<c:set var="inc" value="1" />
																<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																	<c:choose>
																		<c:when test="${fn:contains(SelectedValue,pickListOptions.pickListOption)==true}">
																			<input
																				id="${dynamicHeaderfield.pageFieldName}_${inc}"
																				name="${dynamicHeaderfield.pageFieldName}"
																				class="radio_1"
																				value="<c:out value="${pickListOptions.pickListOption}"/>"
																				type="radio" 
																				checked="checked" ${strEvent} />
																			<label class="radio_1" for="${dynamicHeaderfield.pageFieldName}_${inc}">&nbsp;<c:out value="${pickListOptions.pickListOption}" /></label>&nbsp;&nbsp;	
																		</c:when>
																		<c:otherwise>
																			<input
																				id="${dynamicHeaderfield.pageFieldName}_${inc}"
																				name="${dynamicHeaderfield.pageFieldName}"
																				class="radio_1"
																				value="<c:out value="${pickListOptions.pickListOption}"/>"
																				type="radio" ${strEvent} />
																			<label class="radio_1" for="${dynamicHeaderfield.pageFieldName}_${inc}">&nbsp;<c:out value="${pickListOptions.pickListOption}" /></label>&nbsp;&nbsp;	
																		</c:otherwise>
																	</c:choose>
																	<c:set var="inc" value="${inc+1}" />
																</c:if>

															</c:forEach>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>

									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when
												test="${dynamicHeaderfield.fieldTypeName == 'checkbox'}">
												<div class='col-sm-12'>
													<div class='form-group'>
														<label for="dynamicField">${dynamicHeaderfield.displayName}</label>
														<div class="checkbox">
															<c:choose>
																<c:when test="${picklistValueExists=='false'}">
																	<div id="${dynamicHeaderfield.pageFieldName}" ${strEvent}></div>
																</c:when>
																<c:otherwise>
																	<c:set var="SelectedValue" value="${dynamicHeaderfield.fieldValue}" />
																	<c:set var="inc" value="1" />
																	<c:forEach var="pickListOptions" items="${dynamicHeaderpickListOptions}">
																		<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
																			<c:choose>
																				<c:when test="${fn:contains(SelectedValue,pickListOptions.pickListOption)==true}">
																					<input
																						id="${dynamicHeaderfield.pageFieldName}_${inc}"
																						name="${dynamicHeaderfield.pageFieldName}"
																						class="checkbox_1"
																						value="<c:out value="${pickListOptions.pickListOption}"/>"
																						type="checkbox" 
																						checked="checked" ${strEvent} />
																					<label class="checkbox_1" for="${dynamicHeaderfield.pageFieldName}_${inc}">&nbsp;<c:out value="${pickListOptions.pickListOption}" /></label>&nbsp;&nbsp;	
																				</c:when>
																				<c:otherwise>
																					<input
																						id="${dynamicHeaderfield.pageFieldName}_${inc}"
																						name="${dynamicHeaderfield.pageFieldName}"
																						class="checkbox_1"
																						value="<c:out value="${pickListOptions.pickListOption}"/>"
																						type="checkbox" ${strEvent} />
																					<label class="checkbox_1" for="${dynamicHeaderfield.pageFieldName}_${inc}">&nbsp;<c:out value="${pickListOptions.pickListOption}" /></label>&nbsp;&nbsp;	
																				</c:otherwise>
																			</c:choose>
																			<c:set var="inc" value="${inc+1}" />
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
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
