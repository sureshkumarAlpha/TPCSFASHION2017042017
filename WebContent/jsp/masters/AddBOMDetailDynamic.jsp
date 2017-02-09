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
	<c:forEach items="${dynamicFieldsListBOMDetails}" var="dynamicDetailfield">
	<c:choose>
	 <c:when test="${dynamicDetailfield.fixed}">
		<c:set var="ControlName" value="${dynamicDetailfield.pageFieldName}" />
		
		<c:choose>
		<c:when test="${ControlName=='component_id'}">		
			<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
			<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${ControlName=='item_id'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='variant_id'}">
				<th  class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='UOM'}">
				<th  class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='operation_id'}">
					<th  class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='required_qty'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='wastage_per'}">
				<th class="${dynamicDetailfield.pageFieldName}_col_h" style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='costing_per'}">
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='purchase_per'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='issue_per'}">
				<th class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='supplier_id'}">
				<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
				<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
			<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='usage_area'}">
					<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
					<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='size_schedule_id'}">
					<th class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
					<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th>
			</c:when>
					<c:otherwise>
			<c:choose>
			<c:when test="${ControlName=='group_id'}">
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
			<th  class="${dynamicDetailfield.pageFieldName}_col"  style="width:${dynamicDetailfield.columnWidth}px;">
			<label class="${dynamicDetailfield.pageFieldName}_lbl">${dynamicDetailfield.displayName}</label>
			</th> 
	</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${view_mode ne 'v' }">

<th style="width:130px;"  >&nbsp;</th>
<th style="width:90px;"  >&nbsp;</th>
<th style="width:20px;"  >&nbsp;</th>   

</c:if>
 
</tr>
</thead>
 </table>
  </div>

<!-----------------------------------------------------  Detail Header End  ------------------------------------------------------->







<!-------------------------------------------------------  Detail Edit Row Start  ------------------------------------------------------->
 <div class="table-body">
        <table class="table table-bordered table-condensed table-hover" style="overflow: auto; width: auto; min-width:${header_info.detTableWidth}px;" id="data-table" >
        <tbody id="table-body-dd" >
						<tr>
							<c:set var="det_map" value="${detMapList}" />
							<%
	Map<String,String> dtMap=(Map<String,String>)pageContext.getAttribute("det_map");
%>
							<c:set var="k" value="${1}" />
							<c:forEach var="det" items="${detList}">


								<c:forEach items="${dynamicFieldsListBOMDetails}"
									var="dynamicDetailfield">
									<c:choose>
										<c:when test="${dynamicDetailfield.fixed}">
											<c:set var="ControlName"
												value="${dynamicDetailfield.pageFieldName}" />
											<c:choose>
												<c:when test="${ControlName=='component_id'}">
													<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
														<div class='form-group'>
															<input class="form-control "
																name="component_S_${det.bomDetailId}"
																id="component_S_${det.bomDetailId}"
																onblur="setEditedId('component_id_S_${det.bomDetailId}');"
																value="${det.component}" type="text"
																maxlength="${dynamicDetailfield.length}"
																placeholder="${dynamicDetailfield.displayName}" /> 
																<input
																type="hidden" name="component_id_S_${det.bomDetailId}"
																id="component_id_S_${det.bomDetailId}"
																value="${det.componentId}" />
														</div> <script>
				                                               	setFocusWidth('${dynamicDetailfield.pageFieldName}','component_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
				                                    	</script>


													</td>

												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${ControlName=='item_id'}">
															<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																<div class="form-group required-field-block">
																	<input class="form-control"
																		name="material_S_${det.bomDetailId}"
																		id="material_S_${det.bomDetailId}"
																		value="<c:out value="${det.material}"/>"
																		maxlength="${dynamicDetailfield.length}"
																		onblur="getBOMMaterialData(document.getElementById('item_id_S_${det.bomDetailId}').value,${det.bomDetailId});setEditedId('item_id_S_${det.bomDetailId}');"
																		type="text"
																		placeholder="Type and select ${dynamicDetailfield.displayName}" />
																	<input type="hidden" name="item_id_S_${det.bomDetailId}"
																		id="item_id_S_${det.bomDetailId}"
																		value="${det.materialId}" />
																	<div class="required-icon">
																		<div class="text">*</div>
																	</div>
																</div> <script>
				                                               	setFocusWidth('${dynamicDetailfield.pageFieldName}','material_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
				                                    	</script>

															</td>

														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${ControlName=='variant_id'}">

																	<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																		<div class='form-group'>
																			<input class="form-control "
																				name="variant_S_${det.bomDetailId}"
																				id="variant_S_${det.bomDetailId}"
																				onblur="setEditedId('variant_id_S_${det.bomDetailId}');"
																				value="${det.variant}" type="text"
																				maxlength="${dynamicDetailfield.length}"
																				placeholder="${dynamicDetailfield.displayName}" />
																			<input type="hidden"
																				name="variant_id_S_${det.bomDetailId}"
																				id="variant_id_S_${det.bomDetailId}"
																				value="${det.variantId}" />
																		</div> <script>
				                                               	setFocusWidth('${dynamicDetailfield.pageFieldName}','variant_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
				                                    	</script>

																	</td >

																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${ControlName=='UOM'}">

																			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																				<div class="form-group required-field-block">
																					<input type="text" class="form-control"
																						name="uom_S_${det.bomDetailId}"
																						placeholder="Type and select ${dynamicDetailfield.displayName}"
																						id="uom_S_${det.bomDetailId}"
																						maxlength="${dynamicDetailfield.length}"
																						onblur="setEditedId('uom_id_S_${det.bomDetailId}');"
																						value="<c:out value="${det.UOM}"/>" /> <input
																						type="hidden" name="uom_id_S_${det.bomDetailId}"
																						id="uom_id_S_${det.bomDetailId}"
																						value="${det.uomId}"
																						placeholder="Type and select ${dynamicDetailfield.displayName}" />
																					<div class="required-icon">
																						<div class="text">*</div>
																					</div>
																				</div> <script>
				                                               	setFocusWidth('${dynamicDetailfield.pageFieldName}','uom_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
				                                    				</script>

																			</td>

																		</c:when>
																		<c:otherwise>
																			<c:choose>
																				<c:when test="${ControlName=='operation_id'}">
																					<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																						<div class='form-group'>
																							<!--      <div class="required-field-block"> -->
																							<input type="text" class="form-control"
																								name="operation_S_${det.bomDetailId}"
																								id="operation_S_${det.bomDetailId}"
																								maxlength="${dynamicDetailfield.length}"
																								onblur="setEditedId('operation_id_S_${det.bomDetailId}');"
																								value="<c:out value="${det.operation}"/>"
																								placeholder="${dynamicDetailfield.displayName}" />
																							<input type="hidden"
																								name="operation_id_S_${det.bomDetailId}"
																								id="operation_id_S_${det.bomDetailId}"
																								value="${det.operationId}" />
																							<!--      <div class="required-icon">
																				<div class="text">*</div>
																			     </div>
																			     </div> -->
																						</div> <script>
				                                               				setFocusWidth('${dynamicDetailfield.pageFieldName}','operation_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
				                                    				</script>

																					</td>
																				</c:when>
																				<c:otherwise>
																					<c:choose>
																						<c:when test="${ControlName=='required_qty'}">

																							<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																								<div class="form-group required-field-block">
																									<input type="text"
																										class="form-control text-right"
																										name="required_qty_S_${det.bomDetailId}"
																										id="required_qty_S_${det.bomDetailId}"
																										maxlength="${dynamicDetailfield.length}"
																										onblur="setEditedId('required_qty_S_${det.bomDetailId}');"
																										value="${det.requiredQty}"
																										placeholder="${dynamicDetailfield.displayName}" />
																									<div class="required-icon">
																										<div class="text">*</div>
																									</div>
																								</div> <!--   <script>
											                                               				setFocusWidth('required_qty_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
											                                    				</script> -->

																							</td>

																						</c:when>
																						<c:otherwise>
																							<c:choose>
																								<c:when test="${ControlName=='wastage_per'}">

																									<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																										<div class='form-group'>
																											<input class="form-control text-right"
																												name="wastage_per_S_${det.bomDetailId}"
																												maxlength="${dynamicDetailfield.length}"
																												id="wastage_per_S_${det.bomDetailId}"
																												onblur="setEditedId('wastage_per_S_${det.bomDetailId}');"
																												type="text" value="${det.wastagePer}"
																												placeholder="${dynamicDetailfield.displayName}" />
																										</div> <!--  <script>
											                                               				setFocusWidth('wastage_per_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
											                                    				</script> -->

																									</td>

																								</c:when>
																								<c:otherwise>
																									<c:choose>
																										<c:when test="${ControlName=='costing_per'}">

																											<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																												<div class='form-group'>
																													<input type="text"
																														class="form-control text-right"
																														name="costing_per_S_${det.bomDetailId}"
																														maxlength="${dynamicDetailfield.length}"
																														id="costing_per_S_${det.bomDetailId}"
																														value="<c:out value="${det.costingPer}"/>"
																														placeholder="Enter ${dynamicDetailfield.displayName}"
																														onblur="setEditedId('costing_per_S_${det.bomDetailId}');" />
																												</div> <!-- 	 <script>
											                                               			                 	setFocusWidth('costing_per_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
											                                    				                      </script> -->

																											</td>

																										</c:when>
																										<c:otherwise>
																											<c:choose>
																												<c:when
																													test="${ControlName=='purchase_per'}">

																													<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																														<div class='form-group'>
																															<input type="text"
																																class="form-control text-right"
																																name="purchase_per_S_${det.bomDetailId}"
																																maxlength="${dynamicDetailfield.length}"
																																id="purchase_per_S_${det.bomDetailId}"
																																value="<c:out value="${det.purchasePer}"/>"
																																placeholder="Enter ${dynamicDetailfield.displayName}"
																																onblur="setEditedId('purchase_per_S_${det.bomDetailId}');" />
																														</div> <!-- 			<script>
											                                               			                 	setFocusWidth('purchase_per_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
											                                    				                      </script> -->


																													</td>

																												</c:when>
																												<c:otherwise>
																													<c:choose>
																														<c:when test="${ControlName=='issue_per'}">

																															<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																<div class='form-group'>
																																	<input type="text"
																																		class="form-control text-right"
																																		value="${det.issuePer}"
																																		name="issue_per_S_${det.bomDetailId}"
																																		maxlength="${dynamicDetailfield.length}"
																																		onblur="setEditedId('issue_per_S_${det.bomDetailId}');"
																																		placeholder="${dynamicDetailfield.displayName}"
																																		id="issue_per_S_${det.bomDetailId}" />
																																</div> <!-- 		<script>
											                                               			                 	setFocusWidth('issue_per_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
											                                    				                      </script> -->

																															</td>

																														</c:when>
																														<c:otherwise>
																															<c:choose>
																																<c:when
																																	test="${ControlName=='supplier_id'}">

																																	<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																		<div class='form-group'>
																																			<input type="text"
																																				class="form-control "
																																				value="${det.supplier}"
																																				name="supplier_S_${det.bomDetailId}"
																																				maxlength="${dynamicDetailfield.length}"
																																				onblur="setEditedId('supplier_id_S_${det.bomDetailId}');"
																																				placeholder="${dynamicDetailfield.displayName}"
																																				id="supplier_S_${det.bomDetailId}" />
																																			<input type="hidden"
																																				name="supplier_id_S_${det.bomDetailId}"
																																				id="supplier_id_S_${det.bomDetailId}"
																																				value="${det.supplierId}" />

																																		</div> <script>
											                                               			                 	setFocusWidth('${dynamicDetailfield.pageFieldName}','supplier_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
											                                    				                      </script>

																																	</td>

																																</c:when>
																																<c:otherwise>
																																	<c:choose>
																																		<c:when
																																			test="${ControlName=='usage_area'}">

																																			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																				<div class='form-group'>
																																					<input type="text"
																																						class="form-control text-right"
																																						value="${det.usageArea}"
																																						maxlength="${dynamicDetailfield.length}"
																																						name="usage_area_S_${det.bomDetailId}"
																																						onblur="setEditedId('usage_area_S_${det.bomDetailId}');"
																																						placeholder="${dynamicDetailfield.displayName}"
																																						id="usage_area_S_${det.bomDetailId}" />
																																				</div> <!-- 	<script>
															                                               			                 	setFocusWidth('usage_area_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
															                                    				                      </script>	 -->
																																			</td>

																																		</c:when>
																																		<c:otherwise>
																																			<c:choose>
																																				<c:when
																																					test="${ControlName=='size_schedule_id'}">

																																					<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																						<div class='form-group'>
																																							<input type="text"
																																								class="form-control "
																																								value="${det.sizeShedule}"
																																								name="size_schedule_S_${det.bomDetailId}"
																																								onblur="setEditedId('size_schedule_id_S_${det.bomDetailId}');"
																																								placeholder="${dynamicDetailfield.displayName}"
																																								id="size_schedule_S_${det.bomDetailId}"
																																								maxlength="${dynamicDetailfield.length}" />

																																							<input type="hidden"
																																								value="${det.sizeSheduleId}"
																																								name="size_schedule_id_S_${det.bomDetailId}"
																																								id="size_schedule_id_S_${det.bomDetailId}" />

																																						</div> <script>
															                                               			                 	setFocusWidth('${dynamicDetailfield.pageFieldName}','size_schedule_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
															                                    				                      </script>

																																					</td>

																																				</c:when>
																																				<c:otherwise>
																																					<c:choose>
																																						<c:when
																																							test="${ControlName=='group_id'}">
																																							<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																								<div class='form-group'>
																																									<input type="text"
																																										class="form-control "
																																										value="${det.groupName}"
																																										name="group_name_S_${det.bomDetailId}"
																																										placeholder="${dynamicDetailfield.displayName}"
																																										id="group_name_S_${det.bomDetailId}"
																																										maxlength="${dynamicDetailfield.length}" />
																																									<input type="hidden"
																																										value="${det.groupId}"
																																										name="group_id_S_${det.bomDetailId}"
																																										id="group_id_S_${det.bomDetailId}" />

																																								</div> <script>
															                                               			                 	setFocusWidth('${dynamicDetailfield.pageFieldName}','group_name_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
										</c:when>
										<c:otherwise>

											<c:set var="f_name"
												value="${dynamicDetailfield.dbFieldName}${det.bomDetailId}" />
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
												<c:when
													test="${dynamicDetailfield.fieldTypeName eq 'textbox'}">
													<c:choose>
														<c:when
															test="${dynamicDetailfield.dataTypeName == 'Date'}">
															<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																<div class='form-group'>
																	<div class="input-daterange "
																		id='${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}'>
																		<c:choose>
																			<c:when test="${dynamicDetailfield.required}">
																				<div class="required-field-block">
																					<input class="form-control"
																						name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																						id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																						maxlength="${dynamicDetailfield.length}"
																						type="text" value="<%=value %>"
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
																					name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																					id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																					maxlength="${dynamicDetailfield.length}"
																					type="text" value="<%=value %>"
																					placeholder="dd-mm-yyyy" ${strEvent} />
																				<span
																					class="glyphicon glyphicon-calendar form-control-feedback"></span>
																			</c:otherwise>
																		</c:choose>
																	</div>
																</div> <script type="text/javascript">

								      jQuery('#${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}').datepicker({
										    format: "dd-mm-yyyy"
										}).on({
											changeDate: function(event) {
						             			event.preventDefault();
						             			event.stopPropagation();
						             		// Revalidate the date field
						             			 jQuery('#abo_form').bootstrapValidator('revalidateField', '${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');
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
		        					</script> <script>
															 setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
																					id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																					name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																					value="<c:out value="${dynamicDetailfield.fieldValue}"/>"
																					placeholder="Enter ${dynamicDetailfield.displayName}"
																					maxlength="${dynamicDetailfield.length}"
																					type="text" value="<%=value %>"
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
																				value="<c:out value="${dynamicDetailfield.fieldValue}"/>"
																				placeholder="Enter ${dynamicDetailfield.displayName}"
																				maxlength="${dynamicDetailfield.length}" type="text"
																				value="<%=value %>" class="form-control" ${strEvent} />
																		</c:otherwise>
																	</c:choose>
																</div> <script>
															 setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
															</script>
															</td>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when
															test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
															<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																<div class='form-group'>
																	<c:choose>
																		<c:when test="${dynamicDetailfield.required}">
																			<div class="required-field-block">
																				<input
																					id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																					name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																					type="text" value="<%=value %>"
																					placeholder="Enter ${dynamicDetailfield.displayName}"
																					class="form-control" rows="2" cols="18" ${strEvent}
																					onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId},${dynamicDetailfield.length});" />
																				<div class="required-icon">
																					<div class="text">*</div>
																				</div>
																			</div>
																		</c:when>
																		<c:otherwise>
																			<input
																				id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																				name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																				type="text" value="<%=value %>"
																				placeholder="Enter ${dynamicDetailfield.displayName}"
																				class="form-control" rows="2" cols="18" ${strEvent}
																				onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId},${dynamicDetailfield.length});" />
																		</c:otherwise>
																	</c:choose>
																</div> <script>
															 setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
															</script>
															</td>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when
																	test="${dynamicDetailfield.fieldTypeName == 'combobox'}">
																	<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																		<div class='form-group'>
																			<c:choose>
																				<c:when test="${dynamicDetailfield.required}">
																					<div class="required-field-block">
																						<c:choose>
																							<c:when test="${picklistValueExists=='false'}">
																								<select class="form-control"
																									id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																									name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																									${strEvent}
																									onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');">
																									<c:choose>
																										<c:when
																											test="${dynamicDetailfield.fieldValue eq ''}">
																											<option value="Select">----Select----</option>
																										</c:when>
																										<c:otherwise>
																											<option
																												value="<c:out value="${dynamicDetailfield.fieldValue}"/>">${dynamicDetailfield.fieldValue}</option>
																										</c:otherwise>
																									</c:choose>
																								</select>
																							</c:when>
																							<c:otherwise>
																								<select class="form-control"
																									id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																									name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																									${strEvent}
																									onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');">
																									<c:set var="SelectedValue" value="<%=value%>" />
																									<c:forEach var="pickListOptions"
																										items="${dynamicDetailpickListOptions}">

																										<c:if
																											test="${FieldId==pickListOptions.dynamicFieldId}">
																											<c:choose>
																												<c:when
																													test="${SelectedValue==pickListOptions.pickListOption}">
																													<option
																														value="<c:out value="${pickListOptions.pickListOption}"/>"
																														selected="selected"><c:out
																															value="${pickListOptions.pickListOption}" /></option>
																												</c:when>
																												<c:otherwise>
																													<option
																														value="<c:out value="${pickListOptions.pickListOption}"/>"><c:out
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
																								name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																								${strEvent}
																								onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');">
																								<c:choose>
																									<c:when
																										test="${dynamicDetailfield.fieldValue eq ''}">
																										<option value="Select">----Select----</option>
																									</c:when>
																									<c:otherwise>
																										<option
																											value="<c:out value="${dynamicDetailfield.fieldValue}"/>">${dynamicDetailfield.fieldValue}</option>
																									</c:otherwise>
																								</c:choose>
																							</select>
																						</c:when>
																						<c:otherwise>
																							<select class="form-control"
																								id="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																								name="${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}"
																								${strEvent}
																								onblur="setDynEditedId('${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}');">
																								<c:set var="SelectedValue" value="<%=value%>" />
																								<c:forEach var="pickListOptions"
																									items="${dynamicDetailpickListOptions}">
																									<c:if
																										test="${FieldId==pickListOptions.dynamicFieldId}">
																										<c:choose>
																											<c:when
																												test="${SelectedValue==pickListOptions.pickListOption}">
																												<option
																													value="<c:out value="${pickListOptions.pickListOption}"/>"
																													selected="selected"><c:out
																														value="${pickListOptions.pickListOption}" /></option>
																											</c:when>
																											<c:otherwise>
																												<option
																													value="<c:out value="${pickListOptions.pickListOption}"/>"><c:out
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
																		</div> <script>
															 setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_S_${det.bomDetailId}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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


<%-- onclick="validateBOM('${bom.headerMode}','altMat','${det.bomDetailId}');" --%>
							<c:if test="${view_mode ne 'v' }">
                                          <td style="width:130px;">

										<div class="box-group ">
											<div class="form-group">
												<button type="button" class="btn btn-primary"
													data-toggle="tooltip" data-original-title="BOM Components"
													onclick="getBOMComponents('${bom.headerMode}','${det.bomDetailId}');">
													&nbsp;<i class="glyphicon glyphicon-th-list"></i><span>&nbsp;Components</span>
												</button>
											</div>
										</div>
									</td>
									
									 <td style="width:90px;">

										<div class="box-group ">
											<div class="form-group">
												<button type="button" class="btn btn-primary"
													data-toggle="tooltip"
													data-original-title="Alternate Material"
													id="alt_mat_${det.bomDetailId}" onclick="getAltMaterial('${bom.headerMode}','${det.bomDetailId}');">
													<i class="glyphicon glyphicon-list-alt"></i><span>&nbsp;Alt
														Mat</span>
												</button>
											</div>
										</div> 
									</td>
									
									 <td style="width:20px;">
									<a name="delete_${det.bomDetailId}"
										id="delete_${det.bomDetailId}"
										onclick="deleteBOMDet('${det.bomId}','${det.bomDetailId}')"><span
											class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
									</td>
									
									</c:if> 
								

								<c:set var="k" value="${k+1}" />
						</tr>


						</c:forEach>



						<!-------------------------------------------------------  Detail Edit Row End  ------------------------------------------------------->











						<!-------------------------------------------------------  Detail New Row Start  ------------------------------------------------------->
                   <c:set var="col" value="${0}" />
						<c:if test="${view_mode ne 'yes' }">
							<tr>
								<c:set var="t_det_id" value="0" />
								<c:forEach items="${dynamicFieldsListBOMDetails}"
									var="dynamicDetailfield">
									<c:choose>
										<c:when test="${dynamicDetailfield.fixed}">
											<c:set var="ControlName"
												value="${dynamicDetailfield.pageFieldName}" />
											<c:choose>
												<c:when test="${ControlName=='component_id'}">
													<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
														<div class='form-group'>
															<input type="text" class="form-control "
																placeholder="${dynamicDetailfield.displayName}"
																name="component_${t_det_id}" id="component_${t_det_id}"
																maxlength="${dynamicDetailfield.length}" /> <input
																type="hidden" name="component_id_${t_det_id}"
																id="component_id_${t_det_id}" />
														</div> <script>
																	setFocusWidth('${dynamicDetailfield.pageFieldName}','component_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
															</script>
													</td>
												</c:when>

												<c:otherwise>
													<c:choose>
														<c:when test="${ControlName eq 'item_id'}">
															<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																<div class="form-group required-field-block">
																	<input type="text" class="form-control"
																		name="material_${t_det_id}" id="material_${t_det_id}"
																		placeholder="Type and select ${dynamicDetailfield.displayName}"
																		onkeyup="clearId(event,'material_${t_det_id}','material_id_${t_det_id}')"
																		maxlength="${dynamicDetailfield.length}"
																		onblur="getBOMMaterialData(document.getElementById('material_id_${t_det_id}').value,${t_det_id});" />
																	<input type="hidden" name="material_id_${t_det_id}"
																		id="material_id_${t_det_id}" />
																	<div class="required-icon">
																		<div class="text">*</div>
																	</div>
																</div> <script>
																	setFocusWidth('${dynamicDetailfield.pageFieldName}','material_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																	</script>
															</td>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${ControlName=='variant_id'}">
																	<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																		<div class='form-group'>
																			<input type="text" class="form-control"
																				maxlength="${dynamicDetailfield.length}"
																				placeholder="${dynamicDetailfield.displayName}"
																				name="variant_${t_det_id}" id="variant_${t_det_id}" />
																			<input type="hidden" name="variant_id_${t_det_id}"
																				id="variant_id_${t_det_id}" />
																		</div> <script>
																	setFocusWidth('${dynamicDetailfield.pageFieldName}','variant_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																	</script>
																	</td>
																</c:when>

																<c:otherwise>
																	<c:choose>
																		<c:when test="${ControlName=='UOM'}">
																			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																				<div class="form-group required-field-block">
																					<input type="text" name="uom_${t_det_id}"
																						placeholder="Type and select ${dynamicDetailfield.displayName}"
																						class="form-control" id="uom_${t_det_id}"
																						maxlength="${dynamicDetailfield.length}"
																						value="${detail.variantName}" /> <input
																						type="hidden" name="uom_id_${t_det_id}"
																						id="uom_id_${t_det_id}" class="g-txt"
																						value="${detail.variantId}" />
																					<div class="required-icon">
																						<div class="text">*</div>
																					</div>
																				</div> <script>
																		setFocusWidth('${dynamicDetailfield.pageFieldName}','uom_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																	</script>
																			</td>
																		</c:when>

																		<c:otherwise>
																			<c:choose>
																				<c:when test="${ControlName=='operation_id'}">
																					<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																						<div class='form-group'>
																							<!--    <div class="required-field-block"> -->
																							<input type="text" class="form-control"
																								placeholder="${dynamicDetailfield.displayName}"
																								maxlength="${dynamicDetailfield.length}"
																								name="operation_${t_det_id}"
																								id="operation_${t_det_id}" /> <input
																								type="hidden" name="operation_id_${t_det_id}"
																								id="operation_id_${t_det_id}" />

																							<!--     <div class="required-icon">
																	<div class="text">*</div>
																     </div>
																     </div> -->
																						</div> <script>
																		setFocusWidth('${dynamicDetailfield.pageFieldName}','operation_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																	</script>
																					</td>
																				</c:when>

																				<c:otherwise>
																					<c:choose>
																						<c:when test="${ControlName=='required_qty'}">
																							<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																								<div class="form-group required-field-block">
																									<input type="text"
																										class="form-control text-right"
																										name="required_qty_${t_det_id}"
																										id="required_qty_${t_det_id}"
																										placeholder="${dynamicDetailfield.displayName}" />
																									<div class="required-icon">
																										<div class="text">*</div>
																									</div>
																								</div> <!-- 					         <script>
																		setFocusWidth('required_qty_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																	</script> -->

																							</td>
																						</c:when>

																						<c:otherwise>
																							<c:choose>
																								<c:when test="${ControlName=='wastage_per'}">
																									<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																										<div class='form-group'>
																											<input type="text"
																												class="form-control text-right"
																												maxlength="${dynamicDetailfield.length}"
																												name="wastage_per_${t_det_id}"
																												placeholder="Enter ${dynamicDetailfield.displayName}"
																												id="wastage_per_${t_det_id}" />
																										</div> <!-- <script>
																								setFocusWidth('wastage_per_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																							</script> -->
																									</td>
																								</c:when>
																								<c:otherwise>
																									<c:choose>
																										<c:when test="${ControlName=='costing_per'}">
																											<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																												<div class='form-group'>
																													<input type="text"
																														class="form-control text-right"
																														maxlength="${dynamicDetailfield.length}"
																														name="costing_per_${t_det_id}"
																														placeholder="Enter ${dynamicDetailfield.displayName}"
																														id="costing_per_${t_det_id}" />
																												</div> <!--    <script>
																									setFocusWidth('costing_per_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																								</script> -->
																											</td>
																										</c:when>
																										<c:otherwise>
																											<c:choose>
																												<c:when
																													test="${ControlName=='purchase_per'}">
																													<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																														<div class='form-group'>
																															<input type="text"
																																class="form-control text-right"
																																maxlength="${dynamicDetailfield.length}"
																																name="purchase_per_${t_det_id}"
																																placeholder="Enter ${dynamicDetailfield.displayName}"
																																id="purchase_per_${t_det_id}" />
																														</div> <!-- 		<script>
																									setFocusWidth('purchase_per_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																								</script> -->
																													</td>
																												</c:when>
																												<c:otherwise>
																													<c:choose>
																														<c:when test="${ControlName=='issue_per'}">

																															<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																<div class='form-group'>
																																	<input type="text"
																																		class="form-control text-right"
																																		maxlength="${dynamicDetailfield.length}"
																																		name="issue_per_${t_det_id}"
																																		placeholder="Enter ${dynamicDetailfield.displayName}"
																																		id="issue_per_${t_det_id}" />
																																</div> <!-- <script>
																									setFocusWidth('issue_per_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																								</script> -->
																															</td>

																														</c:when>
																														<c:otherwise>
																															<c:choose>
																																<c:when
																																	test="${ControlName=='supplier_id'}">
																																	<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																		<div class='form-group'>
																																			<input type="text"
																																				class="form-control"
																																				placeholder="${dynamicDetailfield.displayName}"
																																				maxlength="${dynamicDetailfield.length}"
																																				name="supplier_${t_det_id}"
																																				id="supplier_${t_det_id}" /> <input
																																				type="hidden"
																																				name="supplier_id_${t_det_id}"
																																				id="supplier_id_${t_det_id}" />
																																		</div> <script>
																									setFocusWidth('${dynamicDetailfield.pageFieldName}','supplier_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																								</script>
																																	</td>
																																</c:when>
																																<c:otherwise>
																																	<c:choose>
																																		<c:when
																																			test="${ControlName=='usage_area'}">
																																			<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																				<div class='form-group'>
																																					<input type="text"
																																						class="form-control "
																																						placeholder="${dynamicDetailfield.displayName}"
																																						name="usage_area_${t_det_id}"
																																						id="usage_area_${t_det_id}">
																																				</div> <!-- <script>
																									setFocusWidth('usage_area_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																								</script> -->
																																			</td>
																																		</c:when>
																																		<c:otherwise>
																																			<c:choose>
																																				<c:when
																																					test="${ControlName=='size_schedule_id'}">
																																					<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																						<div class='form-group'>
																																							<input type="text"
																																								class="form-control "
																																								maxlength="${dynamicDetailfield.length}"
																																								placeholder="${dynamicDetailfield.displayName}"
																																								name="size_schedule_${t_det_id}"
																																								id="size_schedule_${t_det_id}">
																																							<input type="hidden"
																																								name="size_schedule_id_${t_det_id}"
																																								id="size_schedule_id_${t_det_id}">
																																						</div> <script>
																									setFocusWidth('${dynamicDetailfield.pageFieldName}','size_schedule_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
																								</script>

																																					</td>
																																				</c:when>
																																				<c:otherwise>
																																					<c:choose>
																																						<c:when
																																							test="${ControlName=='group_id'}">
																																							<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																																								<div class='form-group'>
																																									<input type="text"
																																										class="form-control "
																																										name="group_name_${t_det_id}"
																																										placeholder="${dynamicDetailfield.displayName}"
																																										id="group_name_${t_det_id}"
																																										maxlength="${dynamicDetailfield.length}" />
																																									<input type="hidden"
																																										name="group_id_${t_det_id}"
																																										id="group_id_${t_det_id}" />

																																								</div> <script>
																								                     	setFocusWidth('${dynamicDetailfield.pageFieldName}','group_name_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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


											<c:set var="count" value="${count+1}" />
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
														<c:when
															test="${dynamicDetailfield.dataTypeName == 'Date'}">
															<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
																<div class='form-group'>
																	<div class="input-daterange has-feedback"
																		id='${dynamicDetailfield.pageFieldName}_${t_det_id}'>
																		<c:choose>
																			<c:when test="${dynamicDetailfield.required}">
																				<div class="required-field-block">
																					<input class="form-control"
																						name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																						id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																						maxlength="${dynamicDetailfield.length}"
																						type="text" placeholder="dd-mm-yyyy" ${strEvent} />
																					<span
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
																					maxlength="${dynamicDetailfield.length}"
																					type="text" placeholder="dd-mm-yyyy" ${strEvent} />
																				<span
																					class="glyphicon glyphicon-calendar form-control-feedback"></span>
																			</c:otherwise>
																		</c:choose>
																	</div>
																	<script type="text/javascript">
		
													      jQuery('#${dynamicDetailfield.pageFieldName}_${t_det_id}').datepicker({
																    format: "dd-mm-yyyy"
																});  
										
									        			</script>
																</div> <script>
				                                               	setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
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
																					id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																					name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																					value="${dynamicDetailfield.fieldValue}"
																					placeholder="Enter ${dynamicDetailfield.displayName}"
																					maxlength="${dynamicDetailfield.length}"
																					type="text" class="form-control" ${strEvent} />
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
																</div> <script>
				                                               	setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
				                                    	</script>
															</td>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:when
													test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
													<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
														<div class='form-group'>
															<c:choose>
																<c:when test="${dynamicDetailfield.required}">
																	<div class="required-field-block">
																		<input
																			id="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																			name="${dynamicDetailfield.pageFieldName}_${t_det_id}"
																			onfocus="this.rows=5;" onblur="this.rows=1;"
																			type="text"
																			placeholder="Enter ${dynamicDetailfield.displayName}"
																			class="form-control" rows="1" ${strEvent}
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
																		onfocus="this.rows=5;" onblur="this.rows=1;"
																		type="text"
																		placeholder="Enter ${dynamicDetailfield.displayName}"
																		class="form-control" rows="1" ${strEvent}
																		onKeyUp="limitText(this.form.${dynamicDetailfield.pageFieldName}_${t_det_id},${dynamicDetailfield.length});" />
																</c:otherwise>
															</c:choose>
														</div> <script>
				                                               	setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
				                                    	</script>
													</td>
												</c:when>
												<c:when
													test="${dynamicDetailfield.fieldTypeName == 'combobox'}">
													<td class="${dynamicDetailfield.pageFieldName}_col" style="width:${dynamicDetailfield.columnWidth}px;">
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
																</c:otherwise>
															</c:choose>
														</div> <script>
				                                               	setFocusWidth('${dynamicDetailfield.pageFieldName}','${dynamicDetailfield.pageFieldName}_${t_det_id}','${dynamicDetailfield.length}','${dynamicDetailfield.columnWidth}');
				                                    	</script>

													</td>
												</c:when>
											</c:choose>
											<c:set var="count" value="${count+1}" />
										</c:otherwise>
									</c:choose>
								</c:forEach>

					<c:if test="${view_mode ne 'v' }">
                               <td style="width:130px;">
									<div class="box-group ">
										<div class="form-group">
											<button type="button" class="btn btn-primary"
												data-toggle="tooltip" data-original-title="BOM Components"
												onclick="validateBOM('${bom.headerMode}','bomComp',0);">
												&nbsp;<i class="glyphicon glyphicon-th-list"></i><span>&nbsp;Components</span>
											</button>
										</div>
									</div>
								</td>

								 <td style="width:90px;">
									<div class="box-group ">
										<div class="form-group">
											<button type="button" class="btn btn-primary"
												data-toggle="tooltip"
												data-original-title="Alternate Material" id="alt_mat_0"
												onclick="validateBOM('${bom.headerMode}','altMat',0);">
												<i class="glyphicon glyphicon-list-alt"></i><span>&nbsp;Alt
													Mat</span>
											</button>
										</div>
									</div> 
								</td>
								
							 <td style="width:20px;">
							<a href="javascript:clearNewBOMDetail('${t_det_id}')"
									name="clear_${t_det_id}" id="clear_${t_det_id}" title="Clear"><span
										class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
								</td>
						
								</c:if>
								
								<c:set var="col" value="${3}" />
							</tr>
							
							
							<tr class="datarow">
								<td colspan="${count+col}"><a
									href="javascript:validateBOM('${bom.headerMode}','1',0);"><span
										class="glyphicon glyphicon-plus" class="text-primary"></span>&nbsp;&nbsp;Add
										Another Line</a></td>
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