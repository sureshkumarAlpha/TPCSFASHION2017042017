<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 <c:choose>
	<c:when test="${dynamicHeaderfield.fixed}">
		<c:set var="ControlName" value="${dynamicHeaderfield.pageFieldName}" />
		<c:choose>
		<c:when test="${ControlName=='po_no'}">		
			<div class='col-sm-12'>
				<div class='form-group'>
					<label for="po_prefix">${dynamicHeaderfield.displayName}</label>
					<div class="form-group has-feedback">
						<select name="po_prefix" id="po_prefix" class="auto-prefix" <c:if test="${purchase_order.headerMode ne 'n' }">disabled="disabled" </c:if> <c:if test="${purchase_order.headerMode eq 'n'}">  autofocus </c:if> onchange="getAutoSlNo(this.value);" >
						<option value="-1">Prefix</option>
						<c:forEach var="autoPrefix" items="${header_info.autoNoPrefixList }">
						<c:choose>
						<c:when test="${header_info.prefix eq autoPrefix}">
						<option value="${autoPrefix}" selected >${autoPrefix}</option>
						</c:when>
						<c:otherwise>
						<option value="${autoPrefix}">${autoPrefix}</option>
						</c:otherwise>
						</c:choose>
						</c:forEach>
						</select> 
						<input type="text"  id="po_no" name="po_no" value="${header_info.poNo}" maxlength="7" placeholder="${dynamicHeaderfield.displayName}" class="auto-no" <c:if test="${purchase_order.headerMode ne 'n' or ((header_info.checkAutoNoType eq 'common') or (header_info.checkAutoNoType eq 'seperate'))}"> readonly="readonly" </c:if>  /> 
						
						<c:if test="${purchase_order.headerMode eq 'n'}">
						<span  data-toggle="modal" data-target="#autoNoModal" onclick="getAutoNoData();"> 
							<a href="#" data-toggle="tooltip" class="a-icon abs" title="Click here to enable or disable autogeneration of Order number">
								<i class="glyphicon glyphicon-cog"></i>
							</a>
						</span>
						</c:if>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='po_date'}">
			<div class='col-sm-12'>
				<div class='form-group'>
					<label for="po_date">${dynamicHeaderfield.displayName}</label>
					<div class='input-group date' id='datepicker'>
						<div class="required-field-block">
							<input type='text' class="form-control" id="po_date" name="po_date" maxlength="${dynamicHeaderfield.length}" placeholder="Select ${dynamicHeaderfield.displayName}" <c:choose><c:when test="${new_page=='yes'}">value="${po_date}"</c:when><c:otherwise>value="${header_info.poDate}"</c:otherwise></c:choose> />
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
				 jQuery('#apo_date').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#apo_form').bootstrapValidator('revalidateField', 'po_date');
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
			<c:when test="${ControlName=='party_id'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="buyer_name">${dynamicHeaderfield.displayName}</label>
					<div class="required-field-block">
						<input type="text" class="form-control" id="buyer_name" name="buyer_name" value="${header_info.partyName}" maxlength="${dynamicHeaderfield.length}" placeholder="Type & Select ${dynamicHeaderfield.displayName}" />
						<input type="hidden" class="form-control" id="party_id" name="party_id" value="${header_info.partyId}" />
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
			</div>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='currency_id'}">
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="currency_name">${dynamicHeaderfield.displayName}</label>
					<div class="required-field-block">
						<input type="text" class="form-control" id="currency_name" name="currency_name" value="${header_info.currencyName}" maxlength="${dynamicHeaderfield.length}" placeholder="Type & Select ${dynamicHeaderfield.displayName}" />
						<input type="hidden" id="currency_id" name="currency_id" value="${header_info.currencyId}" />
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='ex_rate'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="ex_rate">${dynamicHeaderfield.displayName}</label>
					<div class="required-field-block">
						<input type="text" class="form-control" id="ex_rate" name="ex_rate" value="${header_info.exRate}" maxlength="${dynamicHeaderfield.length}" placeholder="Enter ${dynamicHeaderfield.displayName}" />
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='season_id'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="season_name">${dynamicHeaderfield.displayName}</label>
					<input type="text" class="form-control" id="season_name" name="season_name" value="${header_info.seasonName}" maxlength="${dynamicHeaderfield.length}" placeholder="Enter ${dynamicHeaderfield.displayName}" />
					<input type="hidden" id="season_id" name="season_id" value="${header_info.seasonId}" />
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='other_ref'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="other_ref">${dynamicHeaderfield.displayName}</label>
					<input type="text" class="form-control" id="other_ref" name="other_ref" value="${header_info.otherRef}" maxlength="${dynamicHeaderfield.length}" placeholder="Enter ${dynamicHeaderfield.displayName}" />
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='purchase_type'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="purchase_type">${dynamicHeaderfield.displayName}</label>
					
					<select class="form-control" id="purchase_type" name="purchase_type" >
					<option value="" >&lt;--Select--&gt;</option>
					<c:forEach var="pt" items="${header_info.purchaseTypeList}">
					<option value="${pt.purchaseType}" <c:if test="${header_info.purchaseType eq pt.purchaseType}"> selected </c:if> >${pt.purchaseType}</option>
					</c:forEach>
					</select>
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='po_valid_till'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="po_valid_till">${dynamicHeaderfield.displayName}</label>
					<div class="required-field-block">
						<input type="text" class="form-control" id="po_valid_till" name="po_valid_till" value="${header_info.poValidTill}" maxlength="${dynamicHeaderfield.length}" placeholder="Enter ${dynamicHeaderfield.displayName}" />
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				
				<script type="text/javascript">
				 jQuery('#po_valid_till').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#apo_form').bootstrapValidator('revalidateField', 'po_valid_till');
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
				             			 jQuery('#apo_form').bootstrapValidator('revalidateField', '${dynamicHeaderfield.pageFieldName}');
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
