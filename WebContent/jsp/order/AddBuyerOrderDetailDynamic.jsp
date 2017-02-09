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
	<c:forEach items="${dynamicFieldsListBODetail}" var="dynamicDetailfield">
	<c:choose>
	 <c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		
		<c:choose>
		<c:when test="${ControlName=='line_no'}">		
			<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='item_id'}">
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='variant_id'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='buyer_style_no'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range'}">
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='rate_fcy'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='tax_group_id'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col"   style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_date'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='possible_date'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='mrp'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='barcode'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
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

<th style="width:40px;">
	 &nbsp;
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


<c:forEach items="${dynamicFieldsListBODetail}" var="dynamicDetailfield">
<c:choose>
	<c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		<c:choose>
		<c:when test="${ControlName=='line_no'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				 <div class='form-group'>
					<input type="text" class="form-control" id="line_no_S_${det.boDetId}" name="line_no_S_${det.boDetId}" value="${det.lineNo}" onblur="setEditedId('line_no_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				
				                                    	
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','line_no_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>		
			
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='item_id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="item_name_S_${det.boDetId}" name="item_name_S_${det.boDetId}" value="${det.itemName}" onblur="setEditedId('item_name_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
						<input type="hidden" id="item_id_S_${det.boDetId}" name="item_id_S_${det.boDetId}" value="${det.itemId}" >
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','item_name_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='variant_id'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="variant_name_S_${det.boDetId}" name="variant_name_S_${det.boDetId}" value="${det.variantName}" onblur="setEditedId('variant_name_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="variant_id_S_${det.boDetId}" name="variant_id_S_${det.boDetId}" value="${det.variantId}" >
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','variant_name_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='buyer_style_no'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="buyer_style_no_S_${det.boDetId}" name="buyer_style_no_S_${det.boDetId}" value="${det.buyerStyleNo}" onblur="setEditedId('buyer_style_no_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','buyer_style_no_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="size_range_S_${det.boDetId}" name="size_range_S_${det.boDetId}" value="${det.sizeRange}" onblur="getSizeRangeSizeGrid(${det.boDetId},'item_id_S_${det.boDetId}','size_range_id_S_${det.boDetId}');setEditedId('size_range_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
						<input type="hidden" id="size_range_id_S_${det.boDetId}" name="size_range_id_S_${det.boDetId}" value="${det.sizeRangeId}" >
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','size_range_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control talign-right" id="qty_S_${det.boDetId}" name="qty_S_${det.boDetId}" value="${det.qty}"onblur="setEditedId('qty_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				<!-- <script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','qty_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='rate_fcy'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control talign-right" id="rate_fcy_S_${det.boDetId}" name="rate_fcy_S_${det.boDetId}" value="${det.rate}" onblur="setEditedId('rate_fcy_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
			<!-- 	<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','rate_fcy_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='tax_group_id'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control talign-right" id="tax_group_S_${det.boDetId}" name="tax_group_S_${det.boDetId}" value="${det.taxGroupName}" onblur="setEditedId('tax_group_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
						<input type="hidden" id="tax_group_id_S_${det.boDetId}" name="tax_group_id_S_${det.boDetId}" value="${det.taxGroupId}" >
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','tax_group_S_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_date'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
					 	<input type="text" class="form-control" id="required_date_S_${det.boDetId}" name="required_date_S_${det.boDetId}" value="${det.requiredDate}" onblur="setEditedId('required_date_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
					 	<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
					
				</div>
				
				<!-- <script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','required_date_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
				
				<script type="text/javascript">
				 jQuery('#required_date_S_${det.boDetId}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#abo_form').bootstrapValidator('revalidateField', 'required_date_S_${det.boDetId}');
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
			<c:when test="${ControlName=='possible_date'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="possible_date_S_${det.boDetId}" name="possible_date_S_${det.boDetId}" value="${det.possibleDate}" onblur="setEditedId('possible_date_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				<!-- <script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','possible_date_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
				
				<script type="text/javascript">
				 jQuery('#possible_date_S_${det.boDetId}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#abo_form').bootstrapValidator('revalidateField', 'possible_date_S_${det.boDetId}');
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
			<c:when test="${ControlName=='mrp'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="mrp_S_${det.boDetId}" name="mrp_S_${det.boDetId}" value="${det.mrp}" onblur="setEditedId('mrp_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
			<!-- 	<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','mrp_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='barcode'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="barcode_S_${det.boDetId}" name="barcode_S_${det.boDetId}" value="${det.barcode}" onblur="setEditedId('barcode_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','barcode_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark_S_${det.boDetId}" name="remark_S_${det.boDetId}" value="${det.remark}" onblur="setEditedId('remark_S_${det.boDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','remark_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
			</c:otherwise>
			</c:choose>
			</c:otherwise>
			</c:choose>
			</c:otherwise>
			</c:choose>
		
	</c:when>
			<c:otherwise>
			
			<c:set var="f_name" value="${dynamicDetailfield.dbFieldName}${det.boDetId}" />
				<%
					String fieldName=(String) pageContext.getAttribute("f_name");
					String value=dtMap.get(fieldName);

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
										<div class="input-daterange " id='${dynamicDetailfield.pageFieldName}_S_${det.boDetId}'>
											<c:choose>
												<c:when test="${dynamicDetailfield.required}">
													<div class="required-field-block">
														<input 
															class="form-control"
															name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
															id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
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
														name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														maxlength="${dynamicDetailfield.length}" 
														type="text"
														value="<%=value %>"
														placeholder="dd-mm-yyyy" ${strEvent} />
													<span class="glyphicon glyphicon-calendar form-control-feedback"></span>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									
									<script>
										setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
					             	</script>
									<script type="text/javascript">

								      jQuery('#${dynamicDetailfield.pageFieldName}_S_${det.boDetId}').datepicker({
										    format: "dd-mm-yyyy"
										}).on({
											changeDate: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						             		// Revalidate the date field
						             			 jQuery('#abo_form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');
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
														id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														value="<c:out value="${dynamicDetailfield.fieldValue}"/>"
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
													id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
													name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
													value="<c:out value="${dynamicDetailfield.fieldValue}"/>"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													maxlength="${dynamicDetailfield.length}" 
													type="text"
													value="<%=value %>"
													class="form-control" ${strEvent} />
											</c:otherwise>
										</c:choose>
									</div>
									<script>
										setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
														id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
														type="text"
														value="<%=value %>"
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
													value="<%=value %>"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													class="form-control" 
													rows="2" 
													cols="18" ${strEvent}
													onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.boDetId},${dynamicDetailfield.length});" />
											</c:otherwise>
										</c:choose>
									</div>
									<script>
										setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
																		id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																		name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" ${strEvent}
																		onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');">
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
																		name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" ${strEvent}
																		onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');">
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
																	id="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																	name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}"
																	${strEvent}
																	onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');">
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
																	name="${dynamicDetailfield.pageFieldName}_S_${det.boDetId}" ${strEvent}
																	onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.boDetId}');">
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
											<script>
												setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.boDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
</c:forEach>

<c:if test="${view_mode ne 'yes' }">
<td style="width:40px;">
	<div class='form-group'>
		<a  name="delete_${det.boDetId}" id="delete_${det.boDetId}" onclick="deleteBuyerOrderDet('${header_info.boId}','${det.boDetId}')"><span class="span-icon glyphicon glyphicon-trash text-danger "></span></a>
	</div>
</td>
</c:if>
</tr>

<tr>
<td colspan="${header_info.detailVisFieldCnt}" id="size_grid_${det.boDetId}" <c:if test="${det.boSizeExists ne true}"> style="display:none;" </c:if>>
${det.sizeData}
</td>
</tr>

<tr class="sub-title-row">
<td colspan="${header_info.detailVisFieldCnt}" id="carton_grid_${det.boDetId}">
<%-- <div><label >Cortan Details<span class="glyphicon text-primary glyphicon-plus-sign" id="carton_panel_${det.boDetId}_togg"></span></label></div> --%>
<div class="col-sm-12">
<h3>
	<label class="text-primary">Cortan Details&nbsp;<span class="span-icon glyphicon text-primary glyphicon-minus-sign" id="carton_panel_${det.boDetId}_togg"></span></label>
</h3>
<div id="cortan_panel_${det.boDetId}"  <c:if test="${det.cortanExists ne true}"> style="display:none;" </c:if>>
${det.cartonData}
<%-- <input type="hidden" id="cortan_loc_ids_${det.boDetId}" name="cortan_loc_ids_${det.boDetId}" value="${det.cortanLocIds}" />
<input type="hidden" id="cortan_loc_cnt_${det.boDetId}" name="cortan_loc_cnt_${det.boDetId}" value="${det.cortanLocCnt}" />
<table class="table-bordered table-condensed table-hover cortan-grid cortan_table_${det.boDetId}" id="grid_table">${det.cartonData}</table> --%>
</div>
</div>
</td>
</tr>

<c:set var="k" value="${k+1}" />
</c:forEach>



<!-------------------------------------------------------  Detail Edit Row End  ------------------------------------------------------->











<!-------------------------------------------------------  Detail New Row Start  ------------------------------------------------------->

<c:if test="${view_mode ne 'yes' }">
<tr>
<c:set var="tmp_det_id" value="0"/>
<c:forEach items="${dynamicFieldsListBODetail}" var="dynamicDetailfield">
<c:choose>
	<c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		<c:choose>
		<c:when test="${ControlName=='line_no'}">		
		
				<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<div class='form-group'>
						<input type="text" class="form-control" id="line_no_${tmp_det_id}" name="line_no_${tmp_det_id}"  maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
					</div>
					<script>
						setFocusWidth('${dynamicDetailfield.pageFieldName}','line_no_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
	             	</script>
				</td>
							
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='item_id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="item_name_${tmp_det_id}" name="item_name_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
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
					 <input type="text" class="form-control" id="variant_name_${tmp_det_id}" name="variant_name_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="variant_id_${tmp_det_id}" name="variant_id_${tmp_det_id}" >
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','variant_name_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='buyer_style_no'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="buyer_style_no_${tmp_det_id}" name="buyer_style_no_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','buyer_style_no_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="size_range_${tmp_det_id}" name="size_range_${tmp_det_id}" onblur="getSizeRangeSizeGrid(0,'item_id_${tmp_det_id}','size_range_id_${tmp_det_id}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
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
			<c:when test="${ControlName=='qty'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control talign-right" id="qty_${tmp_det_id}" name="qty_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
			<!-- 	<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','qty_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='rate_fcy'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control talign-right" id="rate_fcy_${tmp_det_id}" name="rate_fcy_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				<!-- <script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','rate_fcy_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='tax_group_id'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control talign-right" id="tax_group_${tmp_det_id}" name="tax_group_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
						<input type="hidden" id="tax_group_id_${tmp_det_id}" name="tax_group_id_${tmp_det_id}" >
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','tax_group_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_date'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="required_date_${tmp_det_id}" name="required_date_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				
				<!-- <script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','required_date_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
             	
				<script type="text/javascript">
				 jQuery('#required_date_${tmp_det_id}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#abo_form').bootstrapValidator('revalidateField', 'required_date_${tmp_det_id}');
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
			<c:when test="${ControlName=='possible_date'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="possible_date_${tmp_det_id}" name="possible_date_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				
			<!-- 	<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','possible_date_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
				
				<script type="text/javascript">
				 jQuery('#possible_date_${tmp_det_id}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#abo_form').bootstrapValidator('revalidateField', 'possible_date_${tmp_det_id}');
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
			<c:when test="${ControlName=='mrp'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="mrp_${tmp_det_id}" name="mrp_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				
		<!-- 		<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','mrp_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
             	</script> -->
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='barcode'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="barcode_${tmp_det_id}" name="barcode_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				
				<script>
					setFocusWidth('${dynamicDetailfield.pageFieldName}','barcode_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
										<div class="input-daterange " id='${dynamicDetailfield.pageFieldName}_${tmp_det_id}'>
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
									
									<script>
										setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
					             	</script>
									<script type="text/javascript">

								      jQuery('#${dynamicDetailfield.pageFieldName}_${tmp_det_id}').datepicker({
										    format: "dd-mm-yyyy"
										}).on({
											changeDate: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						             		// Revalidate the date field
						             			 jQuery('#abo_form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_${tmp_det_id}');
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
											
											<script>
												setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
</c:forEach>

<td style="width:40px;">
	<a href="#" name="clear_${tmp_det_id}" id="clear_${tmp_det_id}" data-toggle="tooltip" title="Clear" onclick="clearNewBODetail('${tmp_det_id}');"><span class="span-icon glyphicon glyphicon-trash text-danger "></span></a>
</td>

</tr>

<tr>
<td colspan="${header_info.detailVisFieldCnt}" id="size_grid_${tmp_det_id}">
</td>
</tr>

<tr class="sub-title-row">
<td colspan="${header_info.detailVisFieldCnt}"id="carton_grid_${tmp_det_id}">
<div class="col-sm-12">
<%-- <div><label onclick="loadCortanDetail(${tmp_det_id})">Cortan Details<span class="glyphicon text-primary glyphicon-plus-sign" id="carton_panel_${tmp_det_id}_togg"></span></label></div> --%>
<h3>
	<label onclick="loadCortanDetail(${tmp_det_id})" class="text-primary">Cortan Details&nbsp;<span class="span-icon glyphicon text-primary glyphicon-minus-sign" id="carton_panel_${tmp_det_id}_togg"></span></label>
</h3>

<div id="cortan_panel_${tmp_det_id}">
<input type="hidden" id="cortan_loc_ids_${tmp_det_id}" name="cortan_loc_ids_${tmp_det_id}" value="0,1"/>
<input type="hidden" id="cortan_loc_cnt_${tmp_det_id}" name="cortan_loc_cnt_${tmp_det_id}" value="2"/>
<table class="table-bordered table-condensed table-hover cortan-grid cortan_table_${tmp_det_id}" id="grid_table">
<thead>
<tr class="header-det">
<th id="corton_loc_th">Destination</th> 
<th id="cortan_tot_th_0">Total</th>
<th>&nbsp;</th>
</tr>
</thead>
<tbody>
<tr id="corton_loc_0_0">
<td id="corton_loc_td_0_0">
<div class="form-group">
<input type="text" class="form-control" id="cortan_location_0_0" name="cortan_location_0_0" maxlength="8" placeholder="Enter Location">
</div>
</td> 
<td id="cortan_tot_td_0_0">
<div class="form-group">
<input type="text" class="form-control" id="cortan_tot_qty_0_0" name="cortan_tot_qty_0_0" readonly tabindex="-1">
</div>
</td>
<td>
<a href="javascript:addLocationRow(0)" data-toggle="tooltip" title="Add" data-original-title="Add">
<span class="span-icon glyphicon glyphicon-plus text-primary"></span>
</a>
<a href="javascript:deleteLocationRow(0,0)" data-toggle="tooltip" title="Remove" data-original-title="Remove">
<span class="span-icon glyphicon glyphicon-remove icon-delete"></span>
</a>
</td>
</tr>
<tr id="corton_loc_0_1">
<td id="corton_loc_td_0_1">
<div class="form-group">
<input type="text" class="form-control" id="cortan_location_0_1" name="cortan_location_0_1" maxlength="8" placeholder="Enter Location">
</div>
</td> 
<td id="cortan_tot_td_0_1">
<div class="form-group">
<input type="text" class="form-control" id="cortan_tot_qty_0_1" name="cortan_tot_qty_0_1" readonly tabindex="-1"></div>
</td>
<td>
<a href="javascript:addLocationRow(0)" data-toggle="tooltip" title="Add" data-original-title="Add">
<span class="span-icon glyphicon glyphicon-plus text-primary">
</span>
</a>
<a href="javascript:deleteLocationRow(0,1)" data-toggle="tooltip" title="Remove" data-original-title="Remove">
<span class="span-icon glyphicon glyphicon-remove icon-delete">
</span>
</a>
</td>
</tr>
</tbody>
</table>
</div>
</div>
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