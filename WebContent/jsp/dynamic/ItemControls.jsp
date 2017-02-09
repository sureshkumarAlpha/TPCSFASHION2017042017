<c:choose>
	<c:when test="${dynamicHeaderfield.fixed}">
		<c:set var="ControlName" value="${dynamicHeaderfield.pageFieldName}"/>
<%-- 		<c:choose> 
					 <c:when test="${ControlName=='group_type_id'}">
		        <label>${dynamicHeaderfield.displayName}</label>
		          <div class="required-field-block">
				<select name="grouptype" id="grouptype" class="form-control">
					<option value="-1">&lt;--Select--&gt;</option>
					<c:forEach var="obj" items="${grouptype_list}">
				<c:choose>
				<c:when test="${obj.groupTypeId==group_info.groupTypeId}">
				<option value="${obj.groupTypeId}" selected="selected">${obj.groupType}</option>
				</c:when>
				<c:otherwise>
				 <option value="${obj.groupTypeId}">${obj.groupType}</option>
				</c:otherwise>
				</c:choose>
					</c:forEach>
				</select>
				   <div class="required-icon">
				    <div class="text">*</div>
			        </div>
			        </div>
			</c:when> --%>
			
			<%-- <c:when test="${ControlName=='group_code'}">
			  	<label>${dynamicHeaderfield.displayName}</label>
			  	  <div class="required-field-block">
				<input class="form-control" type="text"  id="groupcode" name="groupcode" value="${group_info.groupcode}" placeholder="${dynamicHeaderfield.displayName}"/>
			    <div class="required-icon">
				    <div class="text">*</div>
			        </div>
			        </div>
			</c:when>
			<c:when test="${ControlName=='group_name'}">
			  	<label>${dynamicHeaderfield.displayName} </label>
			  	  <div class="required-field-block">
				<input class="form-control"  type="text"  id="groupname" name="groupname" value="${group_info.groupname}" placeholder="${dynamicHeaderfield.displayName}"/>
			 <div class="required-icon">
				    <div class="text">*</div>
			        </div>
			        </div>
			</c:when>
				<c:when test="${ControlName=='short_name'}">
			  	<td class="default">${dynamicHeaderfield.displayName} :</td>
				<td><input type="text"  id="shortname" name="shortname" value="${group_info.shortname}"/>
				</td>
			</c:when>
		<c:when test="${ControlName=='grouped_under'}">
			  	<label>${dynamicHeaderfield.displayName} </label>
				<input class="form-control"   type="text"  id="subgroup" name="subgroup" value="${group_info.groupUnderName}"  onkeyup="clearId(event,'subgroup','subgroup_id')" placeholder="${dynamicHeaderfield.displayName}"/>
						<input type="hidden" name="subgroup_id" id="subgroup_id" value="${group_info.groupUnderId}"/>
			</c:when>
			
					
			<c:when test="${ControlName=='is_itrack_required'}">
			
					 <div class="checkbox"> <label> <input type="checkbox" id="itemtracking" name="itemtracking"   <c:if test="${group_info.itemtracking=='1'}"> checked="checked" </c:if> /><span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span> ${dynamicHeaderfield.displayName}</label></div>
			</c:when>
			
			
					<c:when test="${ControlName=='is_barcode_required'}">
			<div class="checkbox"> <label> <input type="checkbox" id="barcodetracking" name="barcodetracking"   <c:if test="${group_info.barcodetracking=='1'}"> checked="checked" </c:if> /><span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span> ${dynamicHeaderfield.displayName}</label></div>
			</c:when> 
			
		
	  </c:choose>--%>
	</c:when>
	
	<c:otherwise>
		<c:set var="strEvent" value = ""/>
		<c:set var="FieldId" value = "${dynamicHeaderfield.dynamicFieldId}"/>
		
		<c:forEach var="events" items="${dynamicHeaderFieldEvents}">
			<c:if test="${FieldId==events.dynamicFieldId}">
				<c:choose>
					<c:when test="${events.eventName=='onkeypress'}">
						<c:set var="strEvent" value=" ${strEvent}${events.eventName}='return ${events.functionName};'"/>
					</c:when>
					<c:otherwise>
						<c:set var="strEvent" value=" ${strEvent}${events.eventName}='${events.functionName};'"/>
					</c:otherwise>
				</c:choose>
			</c:if> 
		</c:forEach>
		
		<c:set var="picklistValueExists" value="false"/>												                        					
		<c:if test="${not empty dynamicHeaderpickListOptions}">
			<c:forEach var="pickListOptions" items="${dynamicHeaderpickListOptions}">
				<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
					<c:set var="picklistValueExists" value="true"/>
				</c:if>
			</c:forEach>												                        						
		</c:if>

		<!-- Labels for the dynamic controls -->
		<label>
			<c:out value="${dynamicHeaderfield.displayName}"/>
			<c:if test="${dynamicHeaderfield.required}">
				<font color="#FF0000">*</font>
			</c:if>
		
		</label>
		
		<c:choose>
			<c:when test="${dynamicHeaderfield.fieldTypeName == 'textbox'}">
				<c:choose>
					<c:when test="${dynamicHeaderfield.dataTypeName == 'Date'}">
							<input class="form-control"  name="${dynamicHeaderfield.pageFieldName}" id="${dynamicHeaderfield.pageFieldName}" placeholder="${dynamicHeaderfield.displayName}"
							type="text" size="25" maxlength="25"
							onkeypress="enterKeyPress(event,'save_order'); "
							value="${dynamicHeaderfield.fieldValue}"
							onchange="setTmpCalendardate(this.value)"
							onfocus="Calendar.setup({
							inputField     :    '${dynamicHeaderfield.pageFieldName}',
							ifFormat       :    '%d-%m-%Y',
							date           :    document.getElementById('tmp_calendar_date').value							           
							});" ${strEvent} />
					</c:when>
					<c:otherwise>
							<input class="form-control"  id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}"  value="${dynamicHeaderfield.fieldValue}" maxlength="${dynamicHeaderfield.length}" type="text" size="25"  ${strEvent} />
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${dynamicHeaderfield.fieldTypeName == 'textarea'}">
					<textarea class="form-control"  id="${dynamicHeaderfield.pageFieldName}" placeholder="${dynamicHeaderfield.displayName}" name="${dynamicHeaderfield.pageFieldName}" type="text"  rows="3" cols="18" ${strEvent} onKeyUp="limitText(this.form.${dynamicHeaderfield.pageFieldName},${dynamicHeaderfield.length});">${dynamicHeaderfield.fieldValue}</textarea>
			</c:when>
		
			<c:when test="${dynamicHeaderfield.fieldTypeName == 'combobox'}">
					<%-- <input type="text" name="ddg" value="${picklistValueExists}"/> --%>
					<c:choose>
						<c:when test="${picklistValueExists=='false'}">
						
							<select class="form-control"  id="${dynamicHeaderfield.pageFieldName}" name = "${dynamicHeaderfield.pageFieldName}" ${strEvent}>
									<c:choose>
									<c:when test ="${dynamicHeaderfield.fieldValue eq ''}">
										<option value="Select">   ----Select----  </option>
									</c:when>
									<c:otherwise>
									
									<option value="${dynamicHeaderfield.fieldValue}">${dynamicHeaderfield.fieldValue}</option>
									</c:otherwise>
									</c:choose>
								</select>						
						</c:when>
						<c:otherwise>
							<select class="form-control"  id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}" ${strEvent} >
								<c:set var="SelectedValue" value = "${dynamicHeaderfield.fieldValue}"/>
								<c:forEach var="pickListOptions" items="${dynamicHeaderpickListOptions}">
									<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
										<c:choose>
											<c:when test="${SelectedValue==pickListOptions.pickListOption}">
												<option value="${pickListOptions.pickListOption}" selected="selected"><c:out value="${pickListOptions.pickListOption}"/></option>
											</c:when>
											<c:otherwise>
												<option value="${pickListOptions.pickListOption}"><c:out value="${pickListOptions.pickListOption}"/></option>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach>
							</select>
						</c:otherwise>
					</c:choose>
			</c:when>
			<c:when test="${dynamicHeaderfield.fieldTypeName == 'radio button'}">
				<c:choose>
					<c:when test="${picklistValueExists=='false'}">
						<div id="${dynamicHeaderfield.pageFieldName}" ${strEvent} ></div>
					</c:when>
					<c:otherwise>
						<c:set var="SelectedValue" value = "${dynamicHeaderfield.fieldValue}"/>
						<c:forEach var="pickListOptions" items="${dynamicHeaderpickListOptions}">
							<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
								<c:choose>
									<c:when test="${fn:contains(SelectedValue,pickListOptions.pickListOption)==true}">
										<input class="form-control"  id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}" value="${pickListOptions.pickListOption}" type="radio" checked="checked" ${strEvent}><c:out value="${pickListOptions.pickListOption}"/></input>	
									</c:when>
									<c:otherwise>
										<input class="form-control"  id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}" value="${pickListOptions.pickListOption}" type="radio" ${strEvent}><c:out value="${pickListOptions.pickListOption}"/></input>	
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		       </td>
			</c:when>
			<c:when test="${dynamicHeaderfield.fieldTypeName == 'checkbox'}">
				<c:choose>
					<c:when test="${picklistValueExists=='false'}">
						<div id="${dynamicHeaderfield.pageFieldName}" ${strEvent} ></div>
					</c:when>
					<c:otherwise>
						<c:set var="SelectedValue" value = "${dynamicHeaderfield.fieldValue}"/>
						<c:forEach var="pickListOptions" items="${dynamicHeaderpickListOptions}">
							<c:if test="${FieldId==pickListOptions.dynamicFieldId}">
								<c:choose>
									<c:when test="${fn:contains(SelectedValue,pickListOptions.pickListOption)==true}">
										<input class="form-control"  id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}" value="${pickListOptions.pickListOption}" type="checkbox" checked="checked" ${strEvent}><c:out value="${pickListOptions.pickListOption}"/></input>	
									</c:when>
									<c:otherwise>
										<input class="form-control"  id="${dynamicHeaderfield.pageFieldName}" name="${dynamicHeaderfield.pageFieldName}" value="${pickListOptions.pickListOption}" type="checkbox" ${strEvent}><c:out value="${pickListOptions.pickListOption}"/></input>	
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
	</c:otherwise>	
</c:choose>

