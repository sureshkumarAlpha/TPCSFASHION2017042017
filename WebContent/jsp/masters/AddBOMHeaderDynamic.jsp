<c:choose>
	<c:when test="${dynamicHeaderfield.fixed}">
		<c:set var="ControlName" value="${dynamicHeaderfield.pageFieldName}" />
		<c:choose>
		<c:when test="${ControlName=='bom_no'}">
				<div class='col-sm-12'>

					<div class="form-group">
						<label for="customer">${dynamicHeaderfield.displayName}</label>
						<div class="required-field-block">
							<input class="form-control" id="bom_no" name="bom_no" size="30" maxlength="${dynamicHeaderfield.length}"
								<c:if test="${bom.headerMode eq 'n'}">autofocus</c:if>
								type="text" value="${header_info.bomNo}"
								onblur="chkBomNoExist();" placeholder="Enter BOM No">
							<div class="required-icon">
								<div class="text">*</div>
							</div>
						</div>
					</div>
				</div>
			</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='bom_date'}">
			<div class='col-sm-12'>
				<div class='form-group'>
					<label for="bo_date">${dynamicHeaderfield.displayName}</label>
					<div class='input-group date' id='datepicker'>
						<div class="required-field-block">
							<input type='text' class="form-control" id="tr_date" name="tr_date" maxlength="${dynamicHeaderfield.length}" placeholder="Select ${dynamicHeaderfield.displayName}" <c:choose><c:when test="${new_page=='yes'}">value="${tr_date}"</c:when><c:otherwise>value="${header_info.bomDate}"</c:otherwise></c:choose> />
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
				 jQuery('#tr_date').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#abo_form').bootstrapValidator('revalidateField', 'tr_date');
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
			<c:when test="${ControlName=='customer_id'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="buyer_name">${dynamicHeaderfield.displayName}</label>
					<div class="required-field-block">
						<input type="text" class="form-control" id="customer" name="customer" value="${header_info.customer}" maxlength="${dynamicHeaderfield.length}" placeholder="Type & Select ${dynamicHeaderfield.displayName}" />
						<input type="hidden" class="form-control" id="customer_id" name="customer_id" value="${header_info.customerId}" />
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
			</div>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='parent_bom_id'}">
				<div class="box-group ">
			<div class="col-sm-10">
				<div class='form-group'>
					<label for="buyer_po_no">${dynamicHeaderfield.displayName}</label>
														<div class='input-group date'  >
   <input type='text' name="parent_bom_no" id="parent_bom_no"  class="form-control"  readonly   placeholder="Select BOM No" value="${header_info.parentBomNo}"/>
   <input type="hidden" name="parent_bom_id" id="parent_bom_id" value="${header_info.parentBomId}">
   <span class="input-group-addon" data-toggle="modal" data-target="#TrNoModal" onclick="selectBOMNoDet();"  title="BOM No List" >
   <span class="glyphicon glyphicon-list"   ></span>
   </span>
            </div>
				</div>
			</div>
				<c:if test="${(view_mode ne 'v') and  (bom.headerMode eq 'n')}">
								<div class="form-group col-sm-2">
							<label >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<button type="button" class="btn btn-primary" onclick="addBOMDetailFrmParent('${bom.headerMode}')"><span>&nbsp;Add Details</span></button>
							</div>
							</c:if>
							</div>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='product_id'}">
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="currency_name">${dynamicHeaderfield.displayName}</label>
				    <input class="form-control" id="product" name="product"  size="30" maxlength="100" type="text" value="<c:out value="${header_info.product}"/>"  placeholder="Type and select Product"/>
			 <input type="hidden" class="form-control" name="product_id" id="product_id" value="${header_info.productId}"/>	
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remarks'}">
			
			<div class="col-sm-12">
				<div class='form-group'>
					<label for="ex_rate">${dynamicHeaderfield.displayName}</label>
				<textarea class="form-control" rows="6"  id="bom_msg" name="bom_msg" maxlength="250"  placeholder="Enter Remarks">${header_info.remarks}</textarea>
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
				             			 jQuery('#abo_form').bootstrapValidator('revalidateField', '${dynamicHeaderfield.pageFieldName}');
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
