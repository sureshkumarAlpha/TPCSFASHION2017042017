<%@ page import="java.util.Map"%>

<div class="row det-row-margin" >
<div class="col-sm-12">
<div class="col-sm-12 ">
<div class="table-responsive">
<!-------------------------------------------------------  Detail Header Start  ------------------------------------------------------->
	<div class="table-header" style="overflow:hidden; ">
        <table class="table table-bordered table-condensed  table-head" style="margin-bottom:-1px;min-width:${header_info.detTableWidth}px;" id="head-table" >
        <thead>
            <tr class="header-det">
	<c:forEach items="${dynamicFieldsListINDDetail}" var="dynamicDetailfield">
	<c:choose>
	 <c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		
		<c:choose>
			<c:when test="${ControlName=='item_Id'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='variant_id'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
	
	
	
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='indent_qty'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			</c:when>
			
	
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_date'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			
			

			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='uom'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='party_id'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='so_det_id'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			 	<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='group_id'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
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
	</c:when>
	
	<c:otherwise>
		<c:set var="strEvent" value="" />
		<c:set var="FieldId" value="${dynamicDetailfield.dynamicFieldId}" />

		<c:forEach var="events" items="${dynamicDetailfieldEvents}">
			<c:if test="${FieldId==events.dynamicFieldId}">
				<c:choose>
					<c:when test="${events.eventName=='onkeypress'}">
						<c:set var="strEvent" value="${strEvent}${events.eventName}='return ${events.functionName};'" />
					</c:when>
					<c:otherwise>
						<c:set var="strEvent" value="${strEvent}${events.eventName}='${events.functionName};'" />
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
		<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
			<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
		</th>
	</c:otherwise>
	
	
	</c:choose>
</c:forEach>

<th style="width:20px;">
	 <label >&nbsp;</label>
</th>
</tr>
</thead>
 </table>
  </div>

 

<!-------------------------------------------------------  Detail Header End  ------------------------------------------------------->







<!-------------------------------------------------------  Detail Edit Row Start  ------------------------------------------------------->
 <div class="table-body">
        <table class="table table-bordered table-condensed table-hover" style="overflow: auto; width: auto; min-width:${header_info.detTableWidth}px;" id="data-table" >
        <tbody id="table-body-dd" >
<tr>
<c:set var="det_map" value="${detMapList}"/>
<%
	Map<String,String> dtMap=(Map<String,String>)pageContext.getAttribute("det_map");
%>
<c:set var="k" value="${1}"/>
<c:forEach var="det" items="${detList}">


<c:forEach items="${dynamicFieldsListINDDetail}" var="dynamicDetailfield">
<c:choose>
	<c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
	
		<c:choose>
			<c:when test="${ControlName=='item_Id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class="required-field-block">
					<input type="text" class="form-control" id="item_name_S_${det.indentDetId}" name="item_name_S_${det.indentDetId}" value="${det.itemName}" onblur="setEditedId('item_id_S_${det.indentDetId}');getItemData(document.getElementById('item_id_S_${det.indentDetId}').value,'${det.indentDetId}','');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="item_id_S_${det.indentDetId}" name="item_id_S_${det.indentDetId}" value="${det.itemId}" >
					<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				
			    <script>
				  setFocusWidth('${dynamicDetailfield.pageFieldName}','item_name_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               </script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='variant_id'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class="required-field-block">
					 <input type="text" class="form-control" id="variant_name_S_${det.indentDetId}" name="variant_name_S_${det.indentDetId}" value="${det.colourName}" onblur="setEditedId('variant_id_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="variant_id_S_${det.indentDetId}" name="variant_id_S_${det.indentDetId}" value="${det.colourId}" >
					<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				
				 <script>
				  setFocusWidth('${dynamicDetailfield.pageFieldName}','variant_name_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               </script>
               
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class="required-field-block">
					<input type="text" class="form-control" id="size_range_S_${det.indentDetId}" name="size_range_S_${det.indentDetId}" value="${det.sizeRange}" onblur="getINDSizeRangeSizeGrid(${det.indentDetId},'item_id_S_${det.indentDetId}','size_range_id_S_${det.indentDetId}');setEditedId('size_range_id_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="size_range_id_S_${det.indentDetId}" name="size_range_id_S_${det.indentDetId}" value="${det.sizeRangeId}" >
					<div class="required-icon">
							<div class="text">*</div>
					</div>
				</div>
				</div>
				
				<script>
				  setFocusWidth('${dynamicDetailfield.pageFieldName}','size_range_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               </script>
               
			</td>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='indent_qty'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class="required-field-block">
					<input type="text" class="form-control" id="indent_qty_S_${det.indentDetId}" name="indent_qty_S_${det.indentDetId}" value="${det.indentQty}"onblur="setEditedId('indent_qty_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_date'}">
			
						
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="required_date_S_${det.indentDetId}" name="required_date_S_${det.indentDetId}" value="${det.requiredDate}" onblur="setEditedId('required_date_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
				</div>
				
				<script type="text/javascript">
				 jQuery('#required_date_S_${det.indentDetId}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#aind_form').bootstrapValidator('revalidateField', 'required_date_S_${det.indentDetId}');
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
				
			</td>
			
			

			
			
			</c:when>
			

			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='uom'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class="required-field-block">
					 <input type="text" class="form-control" id="uom_S_${det.indentDetId}" name="uom_S_${det.indentDetId}" value="${det.uom}" onblur="setEditedId('uom_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
					<input type="hidden" id="uom_id_S_${det.indentDetId}" name="uom_id_S_${det.indentDetId}" value="${det.uomId}" >
					<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				
				<script>
				  setFocusWidth('${dynamicDetailfield.pageFieldName}','uom_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               </script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='party_id'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="party_name_S_${det.indentDetId}" name="party_name_S_${det.indentDetId}" value="${det.partyName}" onblur="setEditedId('party_id_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
					<input type="hidden" id="party_id_S_${det.indentDetId}" name="party_id_S_${det.indentDetId}" value="${det.partyId}" >
				</div>
				
				<script>
				  setFocusWidth('${dynamicDetailfield.pageFieldName}','party_name_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               </script>
			</td>
			
			</c:when>
			
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark_S_${det.indentDetId}" name="remark_S_${det.indentDetId}" value="${det.remark}" onblur="setEditedId('remark_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				
				<script>
				  setFocusWidth('${dynamicDetailfield.pageFieldName}','remark_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               </script>
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='so_det_id'}">
			
			
				<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				
				<%-- <div class='form-group'>
					 <input type="text" class="form-control" id="so_no_S_${det.indentDetId}" name="so_no_S_${det.indentDetId}" value="${det.remark}" onblur="setEditedId('remark_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div> --%>
				
				<div class='form-group'>
				<div class='input-group date'  >
					 <input type="text" class="form-control" id="so_no_S_${det.indentDetId}" name="so_no_S_${det.indentDetId}" value="${det.soNo}" readonly onblur="setEditedId('so_det_id_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				<input type="hidden" name="so_det_id_S_${det.indentDetId}" id="so_det_id_S_${det.indentDetId}" value="${det.soDetId}">
					   <span class="input-group-addon" data-toggle="modal" data-target="#TrNoModal" onclick="selectSoNoDet(${det.indentDetId});" title="So No List" >
		   <span class="glyphicon glyphicon-list"  data-toggle="tooltip"  ></span>
		   </span>
				</div>
				</div>
				
				 <script>
				  setFocusWidth('${dynamicDetailfield.pageFieldName}','so_no_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               </script>
               
				
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='group_id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<input type="text" class="form-control" id="group_name_S_${det.indentDetId}" name="group_name_S_${det.indentDetId}" value="${det.groupName}" onblur="setEditedId('group_id_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="group_id_S_${det.indentDetId}" name="group_id_S_${det.indentDetId}" value="${det.groupId}" >
					
				</div>
				
			    <script>
				  setFocusWidth('${dynamicDetailfield.pageFieldName}','group_name_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               </script>
			</td>
			
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
	</c:when>
	
	
	
	
	
			<c:otherwise>
			
			<c:set var="f_name" value="${dynamicDetailfield.dbFieldName}${det.indentDetId}" />
				<%
					String fieldName=(String) pageContext.getAttribute("f_name");
				//System.out.println("fieldName :"+fieldName);
					String value=dtMap.get(fieldName);
					//System.out.println("value :"+value);

				%>
									
										
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
								<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
									<div class='form-group'>
										<div class="input-daterange has-feedback" id='${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}'>
											<c:choose>
												<c:when test="${dynamicDetailfield.required}">
													<div class="required-field-block">
														<input 
															class="form-control"
															name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
															id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
															maxlength="${dynamicDetailfield.length}" 
															type="text"
															value="<%=value %>"
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
														name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
														id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
														maxlength="${dynamicDetailfield.length}" 
														type="text"
														value="<%=value %>"
														placeholder="dd-mm-yyyy" ${strEvent} />
													<span class="glyphicon glyphicon-calendar form-control-feedback"></span>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<script type="text/javascript">

								      jQuery('#${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}').datepicker({
										    format: "dd-mm-yyyy"
										}).on({
											changeDate: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						             		// Revalidate the date field
						             			 jQuery('#aind_form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}');
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
								</td>
							</c:when>
							<c:otherwise>
								<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
									<div class='form-group'>
										<c:choose>
											<c:when test="${dynamicDetailfield.required}">
												<div class="required-field-block">
													<input
														id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
														placeholder="Enter ${dynamicDetailfield.displayName}"
														maxlength="${dynamicDetailfield.length}" 
														type="text"
														value="<%=value %>"
														class="form-control" ${strEvent} />
													<div class="required-icon">
														<div class="text">*</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<input
													id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
													name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													maxlength="${dynamicDetailfield.length}" 
													type="text"
													value="<%=value %>"
													class="form-control" ${strEvent} />
											</c:otherwise>
										</c:choose>
									</div>
									
							    <script>
				                      setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                               </script>
								</td>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
								<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
									<div class='form-group'>
										<c:choose>
											<c:when test="${dynamicDetailfield.required}">
												<div class="required-field-block">
													<input
														id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
														type="text"
														value="<%=value %>"
														
														
														placeholder="Enter ${dynamicDetailfield.displayName}"
														class="form-control" 
														rows="2" 
														cols="18" ${strEvent}
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.indentDetId},${dynamicDetailfield.length});" />
													<div class="required-icon">
														<div class="text">*</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<input
													id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
													name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
													type="text"
													value="<%=value %>"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													class="form-control" 
													rows="2" 
													cols="18" ${strEvent}
													onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.indentDetId},${dynamicDetailfield.length});" />
											</c:otherwise>
										</c:choose>
									</div>
									
									 <script>
				                      setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                               </script>
								</td>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${dynamicDetailfield.fieldTypeName == 'combobox'}">
										<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
											<div class='form-group'>
												<c:choose>
													<c:when test="${dynamicDetailfield.required}">
														<div class="required-field-block">
															<c:choose>
																<c:when test="${picklistValueExists=='false'}">
																	<select class="form-control"
																		id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
																		name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}" ${strEvent}
																		onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}');">
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
																		id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
																		name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}" ${strEvent}
																		onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}');">
																		<c:set var="SelectedValue" value="<%=value%>" />
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
																<select class="form-control"
																	id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
																	name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
																	${strEvent}
																	onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}');">
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
																	id="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}"
																	name="${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}" ${strEvent}
																	onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.indentDetId}');">
																	<c:set var="SelectedValue" value="<%=value%>" />
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
										</td>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
						</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
</c:forEach>

<c:if test="${view_mode ne 'yes' }">
<td style="width:20px;">
	<div class='form-group'>
		<a  name="delete_${det.indentDetId}" id="delete_${det.indentDetId}" onclick="deleteIndentDet('${header_info.indentId}','${det.indentDetId}')"><span class="glyphicon glyphicon-trash text-danger "></span></a>
	</div>
</td>
</c:if>

</tr>






<tr>
<td colspan="${header_info.detailVisFieldCnt}" id="size_grid_${det.indentDetId}" >
${det.sizeData}
</td>
</tr>



<c:set var="k" value="${k+1}" />
</c:forEach>


<!-------------------------------------------------------  Detail Edit Row End  ------------------------------------------------------->











<!-------------------------------------------------------  Detail New Row Start  ------------------------------------------------------->

<c:if test="${view_mode ne 'yes' }">
<tr>
<c:set var="tmp_det_id" value="0"/>
<c:forEach items="${dynamicFieldsListINDDetail}" var="dynamicDetailfield">
<c:choose>
	<c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		
		<c:choose>
			<c:when test="${ControlName=='item_Id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
			
				<div class='form-group'>
				<div class="required-field-block">
					<input type="text" class="form-control" id="item_name_${tmp_det_id}" name="item_name_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" onblur="getItemData(document.getElementById('item_id_${tmp_det_id}').value,0,'');" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="item_id_${tmp_det_id}" name="item_id_${tmp_det_id}" >
					<div class="required-icon">
						<div class="text">*</div>
					</div>
				</div>
					
				</div>
				
						 <script>
				           setFocusWidth('${dynamicDetailfield.pageFieldName}','item_name_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                     </script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='variant_id'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class="required-field-block">
					 <input type="text" class="form-control" id="variant_name_${tmp_det_id}" name="variant_name_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="variant_id_${tmp_det_id}" name="variant_id_${tmp_det_id}" >
					<div class="required-icon">
						<div class="text">*</div>
					</div>
				</div>
				</div>
				
				 <script>
				           setFocusWidth('${dynamicDetailfield.pageFieldName}','variant_name_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                  </script>
				
			</td>
			
			</c:when>
			
			
			
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class="required-field-block">
					<input type="text" class="form-control" id="size_range_${tmp_det_id}" name="size_range_${tmp_det_id}" onblur="getINDSizeRangeSizeGrid(0,'item_id_${tmp_det_id}','size_range_id_${tmp_det_id}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="size_range_id_${tmp_det_id}" name="size_range_id_${tmp_det_id}" >
					<div class="required-icon">
						<div class="text">*</div>
					</div>
				</div>	
				</div>
				
				<script>
				           setFocusWidth('${dynamicDetailfield.pageFieldName}','size_range_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                  </script>
				
			</td>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='indent_qty'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class="required-field-block">
					<input type="text" class="form-control" id="indent_qty_${tmp_det_id}" name="indent_qty_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
					<div class="required-icon">
						<div class="text">*</div>
					</div>
				</div>	
				</div>
				
				
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='uom'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class="required-field-block">
					<input type="text" class="form-control" id="uom_${tmp_det_id}" name="uom_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
					<input type="hidden" id="uom_id_${tmp_det_id}" name="uom_id_${tmp_det_id}" >
					<div class="required-icon">
						<div class="text">*</div>
					</div>
				</div>	
				</div>
				
				  <script>
				           setFocusWidth('${dynamicDetailfield.pageFieldName}','uom_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                  </script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_date'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
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
	             			 jQuery('#aind_form').bootstrapValidator('revalidateField', 'required_date_${tmp_det_id}');
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
				
			</td>
			

			
			
			</c:when>
			

			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='party_id'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="party_name_${tmp_det_id}" name="party_name_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="party_id_${tmp_det_id}" name="party_id_${tmp_det_id}" >
				</div>
				
				  <script>
				           setFocusWidth('${dynamicDetailfield.pageFieldName}','uom_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                  </script>
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark_${tmp_det_id}" name="remark_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				
				<script>
				           setFocusWidth('${dynamicDetailfield.pageFieldName}','remark_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                  </script>
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='so_det_id'}">
			
			
				<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
				<div class='input-group date'  data-toggle="tooltip" title="Select So No"  >
					 <input type="text" class="form-control" id="so_no_${tmp_det_id}" name="so_no_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				<input type="hidden" name="so_det_id_${tmp_det_id}" id="so_det_id_${tmp_det_id}" value="">
					   <span class="input-group-addon" data-toggle="modal" data-target="#TrNoModal" onclick="selectSoNoDet(${tmp_det_id});"  >
		   <span class="glyphicon glyphicon-list" ></span>
		   </span>
				</div>
				</div>
			<%-- 	<div class='form-group'>
					 <input type="text" class="form-control" id="so_no_${tmp_det_id}" name="so_no_S_${det.indentDetId}" value="${det.remark}" onblur="setEditedId('remark_S_${det.indentDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div> --%>
				
				 <script>
				  setFocusWidth('${dynamicDetailfield.pageFieldName}','so_no_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               </script>
               
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='group_id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
			
				<div class='form-group'>
					<input type="text" class="form-control" id="group_name_${tmp_det_id}" name="group_name_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="group_id_${tmp_det_id}" name="group_id_${tmp_det_id}" >
				</div>
				
						 <script>
				           setFocusWidth('${dynamicDetailfield.pageFieldName}','group_name_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                     </script>
			</td>
			
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
								<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
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
						             			 jQuery('#aind_form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_${tmp_det_id}');
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
								</td>
							</c:when>
							<c:otherwise>
								<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
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
									
									<script>
				         			  setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                 			    </script>
								</td>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
								<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
									<div class='form-group'>
										<c:choose>
											<c:when test="${dynamicDetailfield.required}">
												<div class="required-field-block">
													<input
														id="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
														name="${dynamicDetailfield.pageFieldName}_${tmp_det_id}"
														type="text"
														value="${dynamicDetailfield.fieldValue}"
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
													value="${dynamicDetailfield.fieldValue}"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													class="form-control" 
													rows="2" 
													cols="18" ${strEvent}
													onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_${tmp_det_id},${dynamicDetailfield.length});" />
											</c:otherwise>
										</c:choose>
									</div>
									<script>
				         			  setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
                 			    </script>
								</td>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${dynamicDetailfield.fieldTypeName == 'combobox'}">
										<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
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
										</td>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
</c:forEach>

<td style="width:20px;">
	<a href="#" name="clear_${tmp_det_id}" id="clear_${tmp_det_id}" title="Clear"  onclick="clearNewINDDetail('${tmp_det_id}');"><span class="glyphicon glyphicon-trash text-danger "></span></a>
</td>

</tr>




 <tr>
<td colspan="${header_info.detailVisFieldCnt}"id="size_grid_${tmp_det_id}">
</td>
</tr>


</c:if>

</tbody>
				</table>  
				</div>

<!-------------------------------------------------------  Detail New Row End  ------------------------------------------------------->
</div>
</div>
</div>
</div>