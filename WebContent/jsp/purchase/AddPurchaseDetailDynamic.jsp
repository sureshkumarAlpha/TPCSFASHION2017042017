<%@ page import="java.util.Map"%>
<div class="row det-row-margin" >
<div class="col-sm-12">
<div class="col-sm-12 ">
<div class="row table-responsive" style="margin-top:50px;" >

<!-------------------------------------------------------  Detail Header Start  ------------------------------------------------------->
	<div class="table-header" style="overflow:hidden; ">
        <table class="table table-bordered table-condensed  table-head" style="margin-bottom:-1px;min-width:${header_info.detTableWidth}px;" id="head-table" >
        <thead>
		<tr class="header-det">
	
	<c:forEach items="${dynamicFieldsListPODetail}" var="dynamicDetailfield">
	<c:choose>
	 <c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		
		<c:choose>
			<c:when test="${ControlName=='item_id'}">
			<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
				${dynamicDetailfield.displayName}
			</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='variant_id'}">
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range_id'}">
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='uom'}">
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			</c:when>
			
	
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty_uom'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
		
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='price_fcy'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='discount_percent' }">
			
				<c:if test="${sessionScope.user_info.locatonConfigMap['PODiscount'] eq 'Yes'}">
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
				</c:if>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='value_before_tax_fcy'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='tax_group_id'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
			
			
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='other_spec'}">
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark1'}">
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark2'}">
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark3'}">
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
				</th>
			
			</c:when>
			
			
			<c:otherwise>
			<c:choose>
			
			<c:when test="${ControlName=='required_date'}">
			
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
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
				<th class="${dynamicDetailfield.pageFieldName}_col ${dynamicDetailfield.pageFieldName}_lbl" style="width:${dynamicDetailfield.columnWidth}px;">
					${dynamicDetailfield.displayName}
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


<c:set var="det_map" value="${detMapList}"/>
<%
	Map<String,String> dtMap=(Map<String,String>)pageContext.getAttribute("det_map");
%>
<c:set var="k" value="${1}"/>
 <div class="table-body">
        <table class="table table-bordered table-condensed table-hover" style="overflow: auto; width: auto; min-width:${header_info.detTableWidth}px;" id="data-table" >
        <tbody id="table-body-dd" >
<c:forEach var="det" items="${detList}">



<tr id="det_row_${det.poDetId}">

<script>
$('#det_row_${det.poDetId}').on('click', function (e) {
    e.preventDefault();
    var elem = $('#det_size_grid_row_${det.poDetId}').toggle('slow');
});
</script>

<c:forEach items="${dynamicFieldsListPODetail}" var="dynamicDetailfield">
<c:choose>
	<c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		<c:choose>
		 
			<c:when test="${ControlName=='item_id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="item_name_S_${det.poDetId}" name="item_name_S_${det.poDetId}" value="${det.itemName}" onblur="setEditedId('item_id_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
						<input type="hidden" id="item_id_S_${det.poDetId}" name="item_id_S_${det.poDetId}"  value="${det.itemId}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				
			 	<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','item_name_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
				
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='variant_id'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="variant_name_S_${det.poDetId}" name="variant_name_S_${det.poDetId}" value="${det.variantName}" onblur="setEditedId('variant_id_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="variant_id_S_${det.poDetId}" name="variant_id_S_${det.poDetId}" value="${det.variantId}" >
				</div>
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','variant_name_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_range_id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="size_range_S_${det.poDetId}" name="size_range_${det.poDetId}" value="${det.sizeRangeName}" onblur="getSizeRangePOSizeGrid(${det.poDetId},'item_id_S_${det.poDetId}','size_range_id_S_${det.poDetId}'); setEditedId('size_range_id_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
						<input type="hidden" id="size_range_id_S_${det.poDetId}" name="size_range_id_S_${det.poDetId}" value="${det.sizeRangeId}" >
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','size_range_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
			</td>
			</c:when>	
			<c:otherwise>
			<c:choose>
			
			<c:when test="${ControlName=='other_spec'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<input type="text" class="form-control" id="other_spec_S_${det.poDetId}" name="other_spec_S_${det.poDetId}" value="${det.otherSpec}"  onblur="setEditedId('other_spec_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','other_spec_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>	
			</td>
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='uom'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<input type="text" class="form-control" id="uom_S_${det.poDetId}" name="uom_S_${det.poDetId}" value="${det.uom}" onblur="setEditedId('uom_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
					<input type="hidden" id="uom_id_S_${det.poDetId}" name="uom_id_S_${det.poDetId}" value="${det.uomId}" >
				</div>
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','uom_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="qty_S_${det.poDetId}" name="qty_S_${det.poDetId}" value="${det.qty}"onblur="setEditedId('qty_S_${det.poDetId}');calculateAmt('${det.poDetId}',${header_info.roundOffQty},${header_info.roundOffRate},${header_info.roundOffValue});" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				 
				
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='price_fcy'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="price_fcy_S_${det.poDetId}" name="price_fcy_S_${det.poDetId}" value="${det.priceFcy}" onblur="setEditedId('price_fcy_S_${det.poDetId}');calculateAmt('${det.poDetId}',${header_info.roundOffQty},${header_info.roundOffRate},${header_info.roundOffValue});" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				 
				
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='discount_percent'}">
			<c:if test="${sessionScope.user_info.locatonConfigMap['PODiscount'] eq 'Yes'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="discount_percent_S_${det.poDetId}" name="discount_percent_S_${det.poDetId}" value="${det.discountPercent}" onblur="setEditedId('discount_percent_S_${det.poDetId}');calculateAmt('${det.poDetId}',${header_info.roundOffQty},${header_info.roundOffRate},${header_info.roundOffValue});" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				
				 
			</td>
			</c:if>
			</c:when>
			<c:otherwise>
			<c:choose>
				<c:when test="${ControlName=='value_before_tax_fcy'}">
				<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<div class='form-group'>
						<input type="text" class="form-control text-right"
							id="value_before_tax_fcy_S_${det.poDetId}" 
							name="value_before_tax_fcy_S_${det.poDetId}" readonly tabindex="-1"
							value="${det.valueFcy}" 
							onblur="setEditedId('value_before_tax_fcy_S_${det.poDetId}');calculateAmt('${det.poDetId}',${header_info.roundOffQty},${header_info.roundOffRate},${header_info.roundOffValue});"
							placeholder="${dynamicDetailfield.displayName}" 
							/>
					</div>
					</td>
				</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='tax_group_id'}">
				<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<select class="form-control" id="tax_group_id_S_${det.poDetId}" name="tax_group_id_S_${det.poDetId}" onchange="calculateTax();setEditedId('tax_group_id_S_${det.poDetId}');" >
						<option value="-1" >&lt;--Select--&gt;</option>
						<c:forEach var="objTG" items="${header_info.taxGroupList}">
						<c:choose>
						<c:when test="${objTG.taxGroupId eq det.taxGroupId}">
						<option value="<c:out value="${objTG.taxGroupId}" />" selected ><c:out value="${objTG.taxGroup}" /></option>
						</c:when>
						<c:otherwise>
						<option value="<c:out value="${objTG.taxGroupId}" />" ><c:out value="${objTG.taxGroup}" /></option>
						</c:otherwise>
						</c:choose>
						
						</c:forEach>
						</select>
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','tax_group_id_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
				
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty_uom'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<input type="text" class="form-control" id="qty_uom_S_${det.poDetId}" name="qty_uom_S_${det.poDetId}" value="${det.qtyUom}" onblur="setEditedId('qty_uom_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','qty_uom_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark_S_${det.poDetId}" name="remark_S_${det.poDetId}" value="${det.remark}" onblur="setEditedId('remark_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','remark_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
				
			</td>
			
			</c:when>
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark1'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark1_S_${det.poDetId}" name="remark1_S_${det.poDetId}" value="${det.remark1}" onblur="setEditedId('remark1_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','remark1_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark2'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark2_S_${det.poDetId}" name="remark2_S_${det.poDetId}" value="${det.remark2}" onblur="setEditedId('remark2_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','remark2_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
				
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark3'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark3_S_${det.poDetId}" name="remark3_S_${det.poDetId}" value="${det.remark3}" onblur="setEditedId('remark3_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
				  	setFocusWidth('${dynamicDetailfield.pageFieldName}','remark3_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
               	</script>
			</td>
			
			</c:when>
			
			<c:when test="${ControlName=='required_date'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
					 	<input type="text" class="form-control" id="required_date_S_${det.poDetId}" name="required_date_S_${det.poDetId}" value="${det.requiredDate}" onblur="setEditedId('required_date_S_${det.poDetId}');" maxlength="${dynamicDetailfield.length}" placeholder="Select ${dynamicDetailfield.displayName}">
					 	<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
					 
				</div>
				
				
				<script type="text/javascript">
				 jQuery('#required_date_S_${det.poDetId}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#apo_form').bootstrapValidator('revalidateField', 'required_date_S_${det.poDetId}');
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
			</c:otherwise>
			</c:choose>
			</c:otherwise>
			</c:choose>
			</c:when>
	 
			<c:otherwise>
			
			<c:set var="f_name" value="${dynamicDetailfield.dbFieldName}${det.poDetId}" />
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
										<div class="input-daterange has-feedback" id='${dynamicDetailfield.pageFieldName}_S_${det.poDetId}'>
											<c:choose>
												<c:when test="${dynamicDetailfield.required}">
													<div class="required-field-block">
														<input 
															class="form-control"
															name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
															id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
															maxlength="${dynamicDetailfield.length}" 
															type="text"
															value="<%=value %>"
															placeholder="dd-mm-yyyy" ${strEvent} 
															onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');"/> 
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
														name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
														id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
														maxlength="${dynamicDetailfield.length}" 
														type="text"
														value="<%=value %>"
														placeholder="dd-mm-yyyy" ${strEvent} 
														onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');"/>
													<span class="glyphicon glyphicon-calendar form-control-feedback"></span>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<script type="text/javascript">

								      jQuery('#${dynamicDetailfield.pageFieldName}_S_${det.potDetId}').datepicker({
										    format: "dd-mm-yyyy"
										}).on({
											changeDate: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						             		// Revalidate the date field
						             			 jQuery('#apo_form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');
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
		        					
		        					<script>
								  		setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
														id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
														placeholder="Enter ${dynamicDetailfield.displayName}"
														maxlength="${dynamicDetailfield.length}" 
														type="text"
														value="<%=value %>"
														class="form-control" ${strEvent} 
														onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');"/>
													<div class="required-icon">
														<div class="text">*</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<input
													id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
													name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													maxlength="${dynamicDetailfield.length}" 
													type="text"
													value="<%=value %>"
													class="form-control" ${strEvent} 
													onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');"/>
											</c:otherwise>
										</c:choose>
									</div>
									
									<script>
								  		setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
														id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
														name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
														type="text"
														value="<%=value %>"
														placeholder="Enter ${dynamicDetailfield.displayName}"
														class="form-control" 
														rows="2" 
														cols="18" ${strEvent}
														onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.poDetId},${dynamicDetailfield.length});" 
														onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');"/>
													<div class="required-icon">
														<div class="text">*</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<input
													id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
													name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
													type="text"
													value="<%=value %>"
													placeholder="Enter ${dynamicDetailfield.displayName}"
													class="form-control" 
													rows="2" 
													cols="18" ${strEvent}
													onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.poDetId},${dynamicDetailfield.length});" 
													onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');"/>
											</c:otherwise>
										</c:choose>
									</div>
									<script>
								  		setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
																		id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
																		name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}" ${strEvent}
																		onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');">
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
																		id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
																		name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}" ${strEvent}
																		onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');">
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
																	id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
																	name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
																	${strEvent}
																	onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');">
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
																	id="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}"
																	name="${dynamicDetailfield.pageFieldName}_S_${det.poDetId}" ${strEvent}
																	onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.poDetId}');">
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
										  		setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.poDetId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
<td style="width:20px;">
	<div class='form-group'>
		<a  name="delete_${det.poDetId}" id="delete_${det.poDetId}" onclick="deletePurchaseOrderDet('${header_info.poId}','${det.poDetId}')"><span class="glyphicon glyphicon-trash text-danger "></span></a>
	</div>
</td>
</c:if>
</tr>



<tr id="det_size_grid_row_${det.poDetId}" style="display:none;" >
<td colspan="${header_info.detailVisFieldCnt}" id="size_grid_${det.poDetId}" <c:if test="${det.poSizeExists ne true}"> style="display:none;" </c:if>>
${det.sizeData}
</td>
</tr>

<c:set var="k" value="${k+1}" />
</c:forEach>



<!-------------------------------------------------------  Detail Edit Row End  ------------------------------------------------------->











<!-------------------------------------------------------  Detail New Row Start  ------------------------------------------------------->

<c:if test="${view_mode ne 'yes' }">

<c:set var="tmp_det_id" value="0"/>

<tr id="det_row_${tmp_det_id}" >
<c:forEach items="${dynamicFieldsListPODetail}" var="dynamicDetailfield">
<c:choose>
	<c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		 
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
			
			<c:when test="${ControlName=='size_range_id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="size_range_${tmp_det_id}" name="size_range_${tmp_det_id}"   onblur="getSizeRangePOSizeGrid(0,'item_id_${tmp_det_id}','size_range_id_${tmp_det_id}');" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
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
			<c:when test="${ControlName=='uom'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<input type="text" class="form-control" id="uom_${tmp_det_id}" name="uom_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Type & Select ${dynamicDetailfield.displayName}">
					<input type="hidden" id="uom_id_${tmp_det_id}" name="uom_id_${tmp_det_id}" >
				
				</div>
				<script>
			  		setFocusWidth('${dynamicDetailfield.pageFieldName}','uom_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
           		</script>
			</td>
			
			</c:when>
			
			
			
			
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="qty_${tmp_det_id}" name="qty_${tmp_det_id}" onblur="calculateAmt('${tmp_det_id}',${header_info.roundOffQty},${header_info.roundOffRate},${header_info.roundOffValue});" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
						<div class="required-icon">
				    		<div class="text">*</div>
						</div>
					</div>
				</div>
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='qty_uom'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
		    		<input type="text" class="form-control" id="qty_uom_${tmp_det_id}" name="qty_uom_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
			  		setFocusWidth('${dynamicDetailfield.pageFieldName}','qty_uom_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
           		</script>
			</td>
      		</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='price_fcy'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="price_fcy_${tmp_det_id}" name="price_fcy_${tmp_det_id}" onblur="calculateAmt('${tmp_det_id}',${header_info.roundOffQty},${header_info.roundOffRate},${header_info.roundOffValue});" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				 		<div class="required-icon">
				    		<div class="text">*</div>
						</div>
			   		</div>
		   		</div>
		   		
			</td>
			
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='discount_percent'}">
			<c:if test="${sessionScope.user_info.locatonConfigMap['PODiscount'] eq 'Yes'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<input type="text" class="form-control" id="discount_percent_${tmp_det_id}" name="discount_percent_${tmp_det_id}" onblur="calculateAmt('${tmp_det_id}',${header_info.roundOffQty},${header_info.roundOffRate},${header_info.roundOffValue});" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				 		<div class="required-icon">
				    		<div class="text">*</div>
						</div>
			   		</div>
		   		</div>
			</td>
			</c:if>
			</c:when>
			<c:otherwise>
			<c:choose>
				<c:when test="${ControlName=='value_before_tax_fcy'}">
				<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
					<div class='form-group'>
						<input type="text" class="form-control text-right"
							id="value_before_tax_fcy_${tmp_det_id}" 
							name="value_before_tax_fcy_${tmp_det_id}" readonly tabindex="-1"
							onblur="setEditedId('value_before_tax_fcy_${tmp_det_id}');calculateAmt('${tmp_det_id}',${header_info.roundOffQty},${header_info.roundOffRate},${header_info.roundOffValue});"
							placeholder="${dynamicDetailfield.displayName}" 
							/>
					</div>
					</td>
				</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='tax_group_id'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<div class="required-field-block">
						<select class="form-control" id="tax_group_id_${tmp_det_id}" name="tax_group_id_${tmp_det_id}" onchange="calculateTax();" >
						<option value="-1" >&lt;--Select--&gt;</option>
						<c:forEach var="objTG" items="${header_info.taxGroupList}">
						<option value="<c:out value="${objTG.taxGroupId}" />" ><c:out value="${objTG.taxGroup}" /></option>
						</c:forEach>
						</select>
						<div class="required-icon">
							<div class="text">*</div>
						</div>
					</div>
				</div>
				<script>
			  		setFocusWidth('${dynamicDetailfield.pageFieldName}','tax_group_id_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
				
				
				<script type="text/javascript">
				 jQuery('#required_date_${tmp_det_id}').datepicker({
					    format: "dd-mm-yyyy"
					}).on({
						changeDate: function(event) {
	             			event.preventDefault();
	             			event.stopPropagation();
	             		// Revalidate the date field
	             			 jQuery('#apo_form').bootstrapValidator('revalidateField', 'required_date_${tmp_det_id}');
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
			
			<c:when test="${ControlName=='other_spec'}">
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					<input type="text" class="form-control" id="other_spec_${tmp_det_id}" name="other_spec_${tmp_det_id}"  maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>	
				<script>
			  		setFocusWidth('${dynamicDetailfield.pageFieldName}','other_spec_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
			<c:when test="${ControlName=='remark1'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark1_${tmp_det_id}" name="remark1_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
			  		setFocusWidth('${dynamicDetailfield.pageFieldName}','remark1_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
           		</script>
			</td>
			
			</c:when>
				<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark2'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark2_${tmp_det_id}" name="remark2_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
			  		setFocusWidth('${dynamicDetailfield.pageFieldName}','remark2_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
           		</script>
			</td>
			
			</c:when>
				<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='remark3'}">
			
			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<div class='form-group'>
					 <input type="text" class="form-control" id="remark3_${tmp_det_id}" name="remark3_${tmp_det_id}" maxlength="${dynamicDetailfield.length}" placeholder="Enter ${dynamicDetailfield.displayName}">
				</div>
				<script>
			  		setFocusWidth('${dynamicDetailfield.pageFieldName}','remark3_${tmp_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
						             			 jQuery('#apo_form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_${tmp_det_id}');
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
		        					
		        					<script>
								  		setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${tmp_det_id}','${dynamicDetailfield.columnWidth}');
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
								  		setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${tmp_det_id}','${dynamicDetailfield.columnWidth}');
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
								  		setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${tmp_det_id}','${dynamicDetailfield.columnWidth}');
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
										  		setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${tmp_det_id}','${dynamicDetailfield.columnWidth}');
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

<td style="width:20px;">
	<a href="#" name="clear_${tmp_det_id}" id="clear_${tmp_det_id}" title="Clear"  onclick="clearNewPODetail('${tmp_det_id}');"><span class="glyphicon glyphicon-trash text-danger "></span></a>
</td>

</tr>

<tr id="det_size_grid_row_${tmp_det_id}">
<td colspan="${header_info.detailVisFieldCnt}" id="size_grid_${tmp_det_id}">
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