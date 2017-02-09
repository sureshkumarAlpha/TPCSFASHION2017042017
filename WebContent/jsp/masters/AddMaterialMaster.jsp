<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/js/masters/material_master.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/panel.css" />
<style>
.container {
	margin-top: 30px;
}

.filter-col {
	padding-left: 10px;
	padding-right: 10px;
}
</style>
<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";

	function getData(selectedFields,totalColumns){		
		getItemData(selectedFields,totalColumns);
		}
	function getItemData(selectedFields,totalColumns){
	
		setVal('request_type','Normal');
		setVal('servlet_name','MaterialMasterServlet');
		setVal('callbackmethod_name','doDisplayMaterialAfterColumnOrganized');
		document.forms[0].action=contextpath+"/RequestHandlerServlet?pageno="+1+"&selected_columns="+selectedFields+"&total_columns="+totalColumns;	
		document.forms[0].method="POST";	
		document.forms[0].submit();
	}

</script>
<script language="javascript">
	var contextpath = "${pageContext.request.contextPath}";
</script>
<script>
jQuery(document).ready(function(){
	jQuery('.collapse').on('shown.bs.collapse', function(){
		jQuery(this).parent().find(".glyphicon-plus-sign").removeClass("glyphicon-plus-sign").addClass("glyphicon-minus-sign");
		}).on('hidden.bs.collapse', function(){
	jQuery(this).parent().find(".glyphicon-minus-sign").removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
		});
	});
</script>
<script type="text/javascript">
	
		var localizedStrings = {
				
		    	ATTACHMENT_DELETED: "<fmt:message bundle="${bundle}" key="Attachment.AttachmentDeleted" />"  ,
		    	ATTACHMENT_NOT_DELETED: "<fmt:message bundle="${bundle}" key="Attachment.AttachmentNotDeleted" />"  ,
		    	DELETE_ATTACHMENT: "<fmt:message bundle="${bundle}" key="Attachment.DeleteAttachment" />",
		    	VALID_GROUP_CODE: "<fmt:message bundle="${bundle}" key="Group.ValidGroupCode"/>",
		    	VALID_GROUP_NAME: "<fmt:message bundle="${bundle}" key="Group.ValidGroupName"/>",
		    	VALID_GROUP_TYPE: "<fmt:message bundle="${bundle}" key="Group.ValidGroupType"/>",
		    	GROUP_ALREADY_EXISTS: "<fmt:message  bundle="${bundle}" key="Group.AlreadyExixts"/>",
		    	GROUP_SAVED: "<fmt:message  bundle="${bundle}" key="Group.CreateSuccessfully"/>",
		    	GROUP_SAVED_FAILED: "<fmt:message  bundle="${bundle}" key="Group.CreateGroupFailed"/>"
		};			
	
	</script>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" method="post" role="form" id="validate-form"
		class="trans-form" autocomplete="off">
		<div class="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn"
					name="screen_name" />
			</jsp:include>



			<div class="page-wrapper">
				<div class="page-inner">


			<div class="row header-row">
				<div class="col-sm-12 col-xs-12">
					<div class='col-sm-8'>
						<h3>Item</h3>
					</div>
				</div>
			</div>

			<div class="row">
				<div class='col-sm-6' style="padding: 0;">
					<div class="col-sm-12">


						<div class="row">
							<div class='col-sm-6'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label>Item Group Name</label>
									<div class="required-field-block">
										<input class="form-control" type="text" name="group_name"
											id="group_name" maxlength="50"
											value="${material_info.groupName}"
											placeholder="Type and select Group"
											onblur="getItemGroupData(document.getElementById('group_id').value);" />
										<input type="hidden" name="group_id" id="group_id"
											value="${material_info.groupId}" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
								</div>
							</div>

						</div>



						<div class="row">
							<div class='col-sm-6'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label>Item Code</label>
									<div class="required-field-block">
										<input class="form-control" type="text" name="material_code"
											id="material_code" placeholder="Enter Material Code"
											value="${material_info.matCode}" maxlength="10" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
								</div>
							</div>

							<div class='col-sm-6'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label>Item Name</label>
									<div class="required-field-block">
										<input class="form-control" type="text" name="material_name"
											id="material_name" maxlength="50"
											placeholder="Enter Material Name"
											value="${material_info.shortName}" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
							</div>
							</div>
						</div>

						<div class="row">
							<div class='col-sm-6'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label>Standard Cost</label> <input class="form-control"
										type="text" name="standard_cost" id="standard_cost"
										placeholder="Enter Standard Cost"
										value="${material_info.stdCost}" maxlength="10" />
								</div>
							</div>
							</div>
							<div class='col-sm-6'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label>Standard Cost Currency</label>
									<div class="required-field-block">
										<input class="form-control" type="text" name="currency_name"
											id="currency_name" maxlength="50"
											placeholder="Enter Cost Currency"
											value="${material_info.costCurrency}" /> <input
											type="hidden" name="currency_id" id="currency_id"
											value="${material_info.currencyId}" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>


						<div class="row">
							<div class='col-sm-6'>
							<div class="col-sm-12">
								<div class='form-group'>
									<label>Status</label> <select class="form-control"
										id="material_status" name="material_status">
										<option value="1"
											<c:if test="${material_info.isActive eq 1}"> selected="selected" </c:if>>Active</option>
										<option value="0"
											<c:if test="${material_info.isActive eq 0}"> selected="selected" </c:if>>Inactive</option>
									</select>
								</div>
							</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class='col-sm-6'>
					<div class='col-sm-3'>
						<div class="col-sm-12">
							<c:choose>
								<c:when
									test="${material_info.primaryImageUrl ne null && material_info.primaryImageUrl ne '' }">
									<img
										src="${materialAttachPath}${material_info.primaryImageUrl}"
										width="200px" />
								</c:when>
								<c:otherwise>
									<img src="images/default-image.png" width="200px" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<div class="col-sm-12">
				<div class="col-sm-12 col-sm-12">
					<h4>
						<label class="text-primary">Unit Of Measurements:</label>
					</h4>
				</div>
				</div>
			</div>


			<div class="row">
				<div class='row'>
					<div class='col-sm-2'>
					<div class="col-sm-12">
						<label></label>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<label>Unit</label>
						</div>
					</div>
				</div>

				<div class='row'>
					<div class='col-sm-2 '>
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class='form-group'>
							<label>Stock Keeping Unit</label>
						</div>
						</div>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<div class="required-field-block">
								<input class="form-control" id="stack_keepin_unit"
									name="stack_keepin_unit" maxlength="200" size="30" type="text"
									value="${material_info.stockKeepingUnit}"
									placeholder="Enter Unit" />
								<div class="required-icon">
									<div class="text">*</div>
								</div>
							</div>
						</div>
					</div>
				</div>


				</div>
				<div class='row'>
					<div class='col-sm-2'>
					<div class="col-sm-12 ">
					<div class="col-sm-12 ">
						<div class='form-group'>
							<label>Purchase Unit</label>
						</div>
						</div>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<div class="required-field-block">
								<input class="form-control" id="purchase_unit"
									name="purchase_unit" maxlength="200" size="30" type="text"
									value="${material_info.purchaseUOM}" placeholder="Enter Unit" />
								<div class="required-icon">
									<div class="text">*</div>
								</div>
							</div>
						</div>
						</div>
					</div>
					

				</div>
				<div class='row'>
					<div class='col-sm-2'>
					<div class="col-sm-12">
					<div class="col-sm-12 ">
						<div class='form-group'>
							<label>BOM Unit</label>
						</div>
						</div>
							</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<div class="required-field-block">
								<input class="form-control" id="BOM_unit" name="BOM_unit"
									maxlength="200" size="30" type="text"
									value="${material_info.bomUom}" placeholder="Enter Unit" />
								<div class="required-icon">
									<div class="text">*</div>
								</div>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-sm-12 col-sm-12">
				<div class="col-sm-12 ">
					<h4>
						<label class="text-primary">Specifications :</label>
					</h4>
				</div>
				</div>
			</div>

			<div class="row">
				<div class='row'>
					<div class='col-sm-1'></div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<label></label> <label>Name</label>
					</div>
					</div>
					<div class='col-sm-1'></div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<label></label> <label>Name</label>
						</div>
					</div>
					<!-- <div class='col-sm-2'>
                        	<label>Code</label>
                        	</div> -->
				</div>

				<div class='row'>
					<div class='col-sm-1'>
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class='form-group'>
							<label>Spec1</label>
						</div>
						</div>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec1" name="spec1"
								maxlength="200" size="30" type="text"
								value="${material_info.spec1}" placeholder="Enter Spec1" />
						</div>
					</div>
                 </div>
					<div class='col-sm-1'>
					<div class="col-sm-12">
						<div class='form-group'>
							<label>Spec6</label>
						</div>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec6" name="spec6"
								maxlength="200" size="30" type="text"
								value="${material_info.spec6}" placeholder="Enter Spec6" />
						</div>
						</div>
					</div>

				</div>
				<div class='row'>
					<div class='col-sm-1'>
					<div class="col-sm-12">
					<div class="col-sm-12">
						<label>Spec2</label>
						</div>
							</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec2" name="spec2"
								maxlength="200" size="30" type="text"
								value="${material_info.spec2}" placeholder="Enter Spec2" />
								</div>
						</div>
					</div>
					<div class='col-sm-1'>
					<div class="col-sm-12">
						<label>Spec7</label>
					</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec7" name="spec7"
								maxlength="200" size="30" type="text"
								value="${material_info.spec7}" placeholder="Enter Spec7" />
						</div>
						</div>
					</div>
					
				</div>
				<div class='row'>
					<div class='col-sm-1'>
					<div class="col-sm-12">
						<div class="col-sm-12">
						<label>Spec3</label>
						</div>
							</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec3" name="spec3"
								maxlength="200" size="30" type="text"
								value="${material_info.spec3}" placeholder="Enter Spec3" />
						</div>
						</div>
					</div>
					<!-- <div class='col-sm-2'>
                        	<div class='form-group'>   
				                    <input class="form-control" id="code5" name="code5"  maxlength="200"  size="30" type="text"  value="" placeholder="Enter Code"/>
				                </div>
                        	</div> -->
					<div class='col-sm-1'>
					<div class="col-sm-12">
						<label>Spec8</label>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec8" name="spec8"
								maxlength="200" size="30" type="text"
								value="${material_info.spec8}" placeholder="Enter Spec8" />
						</div>
						</div>
					</div>

				</div>
				<div class='row'>
					<div class='col-sm-1'>
					<div class="col-sm-12">
						<div class="col-sm-12">
						<label>Spec4</label>
						</div>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec4" name="spec4"
								maxlength="200" size="30" type="text"
								value="${material_info.spec4}" placeholder="Enter Spec4" />
						</div>
						</div>
					</div>

					<div class='col-sm-1'>
					<div class="col-sm-12">
						<label>Spec9</label>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec9" name="spec9"
								maxlength="200" size="30" type="text"
								value="${material_info.spec9}" placeholder="Enter Spec9" />
						</div>
						</div>
					</div>

				</div>

				<div class='row'>
					<div class='col-sm-1'>
					<div class="col-sm-12">
					<div class="col-sm-12">
						<label>Spec5</label>
					</div>
					</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec5" name="spec5"
								maxlength="200" size="30" type="text"
								value="${material_info.spec5}" placeholder="Enter Spec5" />
						</div>
						</div>
					</div>

					<div class='col-sm-1'>
					<div class="col-sm-12">
						<label>Spec10</label>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<input class="form-control" id="spec10" name="spec10"
								maxlength="200" size="30" type="text"
								value="${material_info.spec10}" placeholder="Enter Spec10" />
						</div>
						</div>
					</div>

				</div>


			</div>



			<div class="row">
				<div class="col-sm-12">
				<div class="col-sm-12">
					<h4>
						<label class="text-primary">Purchase :</label>
					</h4>
				</div>
					</div>
			</div>
			<div class="row">
				<div class="row ">
					<div class='col-sm-3'>
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class='form-group'>
							<label for="vat">Excess Purchase Allowance %</label> <input
								class="form-control" id="excess_allowance"
								name="excess_allowance" size="30" type="text" maxlength="25"
								value="${material_info.excessAllowance}"
								placeholder="Enter Excess Allowance %" />
						</div>
						</div>
							</div>
					</div>

					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<label for="service_no">Standard Lead Time</label> <input
								class="form-control" id="standerd_leadtime"
								name="standerd_leadtime" size="30" maxlength="25" type="text"
								value="${material_info.leadTime}"
								placeholder="Enter Standard Lead Time" />
						</div>
						</div>
					</div>
				</div>

				<div class='row'>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class="col-sm-12">
						<div class='form-group'>
							<label for="vat">Min Level</label> <input class="form-control"
								id="min_level" name="min_level" size="30" type="text"
								maxlength="25" value="${material_info.minLevel}"
								placeholder="Enter Minimum level" />
						</div>
						</div>
						</div>
					</div>
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<label for="service_no">Max Level</label> <input
								class="form-control" id="max_level" name="max_level" size="30"
								maxlength="25" type="text" value="${material_info.maxLevel}"
								placeholder="Enter Maximum level" />
						</div>
						</div>
					</div>
				</div>


				<div class='row'>


					<div class='col-sm-3'>
					<div class="col-sm-12">
				   <div class="col-sm-12">
						<div class='form-group'>
							<label for="service_no">Re-Order Point</label> <input
								class="form-control" id="reorder_point" name="reorder_point"
								size="30" maxlength="25" type="text"
								value="${material_info.reorderLevel}"
								placeholder="Enter Re-Order Point" />
						</div>
						</div>
						</div>
					</div>



					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<label for="pan_no">Re-Order Quantity</label> <input
								class="form-control" id="reorder_quantity"
								name="reorder_quantity" size="30" maxlength="25" type="text"
								value="${material_info.reorderQty}"
								placeholder="Enter Re-Order Quantity" />
						</div>
							</div>
					</div>
				</div>

				<div class='row'>
					<div class='col-sm-3'>
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class='form-group'>
							<label for="insurance">Tax Category</label> <input
								class="form-control" id="tax_category" name="tax_category"
								maxlength="100" size="30" type="text"
								value="${material_info.taxCategory}"
								placeholder="Enter Tax Category" />
						</div>
						</div>
						</div>
					</div>

					<div class='row'>
						<div class='col-sm-3'>
						<div class="col-sm-12">
							<div class='form-group'>
								<label for="pan_no">Minimum Order Quantity</label> <input
									class="form-control" id="min_order_qty" name="min_order_qty"
									size="30" maxlength="25" type="text"
									value="${material_info.minOrderQty}"
									placeholder="Enter Minimum Order Quantity" />
							</div>
							</div>
						</div>
					</div>


				</div>

			</div>


			<div class="row">
				<div class="col-sm-12">
				<div class="col-sm-12">
					<h4>
						<label class="text-primary">Inventory :</label>
					</h4>
				</div>
				</div>
			</div>

			<div class='row'>
				<div class='row'>
					<div class='col-sm-2'>
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class='form-group'>
							<label>Material Source</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="1" id="import_yes"
									name="import_mat"
									<c:if test="${material_info.importMat eq 1}">checked="checked"</c:if> />
								<label class="radio_1" for="import_yes">&nbsp;Domestic</label>
							</div>
							</div>
							</div>
						</div>
					</div>
					<div class='col-sm-2'>
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class='form-group'>
							<label>&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0" id="import_no"
									name="import_mat"
									<c:if test="${material_info.importMat eq 0 }">checked="checked"</c:if> />
								<label class="radio_1" for="import_no">&nbsp;Import</label>
							</div>
								</div>
                        </div>
						</div>
					</div>

				</div>



				<div class='row' id="allowNagative_stock" style="display: none;">
					<div class="col-sm-2">
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class="form-group">
							<label>Allow Negative Stock?</label>
							<div class="radio ">
								<input type="radio" class="radio_1" value="1"
									id="negative_stock_yes" name="negative_stock"
									<c:if test="${material_info.negativeStock eq 1  }">checked="checked"</c:if> /><label
									class="radio_1" for="negative_stock_yes">&nbsp;Yes</label>
							</div>
                        </div>
						</div>
							</div>
					</div>
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="form-group">
							<label>&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0"
									id="negative_stock_no" name="negative_stock"
									<c:if test="${material_info.negativeStock eq 0  }">checked="checked"</c:if> /><label
									class="radio_1" for="negative_stock_no">&nbsp;No</label>
							</div>
						</div>
						</div>
					</div>

				</div>



				<div class='row' id="itemTraking" style="display: none;">
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="col-sm-12">
						<div class="form-group">
							<label>Item-Tracking Applicable?</label>
							<div class="radio ">
								<input type="radio" class="radio_1" value="1"
									id="item_traking_yes" name="item_traking"
									<c:if test="${material_info.itemTrackingApplicable eq 1}">checked="checked"</c:if> /><label
									class="radio_1" for="item_traking_yes">&nbsp;Yes</label>
							</div>
                        </div>
						</div>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="form-group">
							<label>&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0"
									id="item_traking_no" name="item_traking"
									<c:if test="${material_info.itemTrackingApplicable eq 0}">checked="checked"</c:if> />
								<label class="radio_1" for="item_traking_no">&nbsp;No</label>
							</div>
						</div>
						</div>
					</div>
				</div>

				<div class='row' id="expiryDate" style="display: none;">
					<div class="col-sm-2">
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class="form-group">
							<label>Expiry date Required?</label>
							<div class="radio ">
								<input type="radio" class="radio_1" value="1" id="expiry_yes"
									name="expiry"
									<c:if test="${material_info.expiryApplicable eq 1}">checked="checked"</c:if> />
								<label class="radio_1" for="expiry_yes">&nbsp;Yes</label>
							</div>
                    </div>
						</div>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="form-group">
							<label>&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0" id="expiry_no"
									name="expiry"
									<c:if test="${material_info.expiryApplicable eq 0}">checked="checked"</c:if> />
								<label class="radio_1" for="expiry_no">&nbsp;No</label>
							</div>
						</div>
					</div>
				</div>
                  </div>
				<div class='row' id="issue_WithIo" style="display: none;">
					<div class="col-sm-2">
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class="form-group">
							<label>Allow item to be issued only with reference to
								IO.No?</label>
							<div class="radio ">
								<input type="radio" class="radio_1" value="1"
									id="issueWithIo_yes" name="issueWithIo"
									<c:if test="${material_info.issueWithIO eq 1}">checked="checked"</c:if> />
								<label class="radio_1" for="issueWithIo_yes">&nbsp;Yes</label>
							</div>
                          </div>
						</div>
						</div>
					</div>
					<div class="col-sm-0-5">
					<div class="col-sm-12">
						<div class="form-group">
							<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0"
									id="issueWithIo_no" name="issueWithIo"
									<c:if test="${material_info.issueWithIO eq 0}">checked="checked"</c:if> />
								<label class="radio_1" for="issueWithIo_no">&nbsp;No</label>
							</div>
						</div>
					</div>
				</div>
              </div>
				<div class='row' id="sizeApplicble" style="display: none;">
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="col-sm-12">
						<div class="form-group">
							<label>Size Applicable ?</label>
							<div class="radio ">
								<input type="radio" class="radio_1" value="1"
									id="sizeapplicble_yes" name="size_applicble"
									<c:if test="${material_info.sizeApplicable eq 1}">checked="checked"</c:if> />
								<label class="radio_1" for="sizeapplicble_yes">&nbsp;Yes</label>
							</div>
                      </div>
                      </div>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="form-group">
							<label>&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0"
									id="sizeapplicble_no" name="size_applicble"
									<c:if test="${material_info.sizeApplicable eq 0}">checked="checked"</c:if> />
								<label class="radio_1" for="sizeapplicble_no">&nbsp;No</label>
							</div>
						</div>
					</div>
					</div>
				</div>

				<div class='row' id="colorApplicble" style="display: none;">
					<div class="col-sm-2">
					<div class="col-sm-12">
					</div>
						<div class="form-group">
							<label>Colour Applicable ?</label>
							<div class="radio ">
								<input type="radio" class="radio_1" value="1"
									id="colorapplicble_yes" name="color_applicble"
									<c:if test="${material_info.colorApplicable eq 1}">checked="checked"</c:if> />
								<label class="radio_1" for="colorapplicble_yes">&nbsp;Yes</label>
							</div>
                         </div>
						</div>
							</div>
					</div>
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="form-group">
							<label>&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0"
									id="colorapplicble_no" name="color_applicble"
									<c:if test="${material_info.colorApplicable eq 0}">checked="checked"</c:if> />
								<label class="radio_1" for="colorapplicble_no">&nbsp;No</label>
							</div>
						</div>
					</div>
				</div>
                  </div>
				<div class='row' id="reservationApplicble" style="display: none;">
					<div class="col-sm-2">
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class="form-group">
							<label>Allow Stock Reservation for MRP ?</label>
							<div class="radio ">
								<input type="radio" class="radio_1" value="1"
									id="reservationapplicble_yes" name="reservation_applicble"
									<c:if test="${material_info.stockReservation eq 1}">checked="checked"</c:if> />
								<label class="radio_1" for="reservationapplicble_yes">&nbsp;Yes</label>
							</div>
                           </div>
						</div>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="form-group">
							<label>&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0"
									id="reservationapplicble_no" name="reservation_applicble"
									<c:if test="${material_info.stockReservation eq 0}">checked="checked"</c:if> />
								<label class="radio_1" for="reservationapplicble_no">&nbsp;No</label>
							</div>
						</div>
					</div>
					</div>
				</div>
				<div class='row' id="inspectionRequired" style="display: none;">
					<div class="col-sm-2">
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class="form-group">
							<label>Allow Inspection is Required for the Group ?</label>
							<div class="radio ">
								<input type="radio" class="radio_1" value="1"
									id="inspectionrequired_yes" name="inspection_required"
									<c:if test="${material_info.inspectionRequired eq 1}">checked="checked"</c:if> />
								<label class="radio_1" for="inspectionrequired_yes">&nbsp;Yes</label>
							</div>
                       </div>
						</div>
						</div>
					</div>
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="form-group">
							<label>&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0"
									id="inspectionrequired_no" name="inspection_required"
									<c:if test="${material_info.inspectionRequired eq 0}">checked="checked"</c:if> />
								<label class="radio_1" for="inspectionrequired_no">&nbsp;No</label>
							</div>
						</div>
					</div>
				</div>
                 </div>
				<div class='row' id="barcodeRequired" style="display: none;">
					<div class="col-sm-2">
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class="form-group">
							<label>Allow Barcode is Required for the Group ?</label>
							<div class="radio ">
								<input type="radio" class="radio_1" value="1"
									id="barcoderequired_yes" name="barcode_required"
									<c:if test="${material_info.barcodeRequired eq 1}">checked="checked"</c:if> />
								<label class="radio_1" for="barcoderequired_yes">&nbsp;Yes</label>
							</div>
                        </div>
						</div>
							</div>
					</div>
					<div class="col-sm-2">
					<div class="col-sm-12">
						<div class="form-group">
							<label>&nbsp;</label>
							<div class="radio">
								<input type="radio" class="radio_1" value="0"
									id="barcoderequired_no" name="barcode_required"
									<c:if test="${material_info.barcodeRequired eq 0}">checked="checked"</c:if> />
								<label class="radio_1" for="barcoderequired_no">&nbsp;No</label>
							</div>
						</div>
					</div>
				</div>
             </div>


				<div class='row'>
					<div class="col-sm-3">
					<div class="col-sm-12">
					<div class="col-sm-12">
						<div class="form-group">
							<label>ABC Class</label> <select class="form-control"
								id="ABC_class" name="ABC_class">
								<option value="A"
									<c:if test="${material_info.abcClass eq 'A' }">selected="selected"</c:if>>A</option>
								<option value="B"
									<c:if test="${material_info.abcClass eq 'B' }">selected="selected"</c:if>>B</option>
								<option value="C"
									<c:if test="${material_info.abcClass eq 'C' }">selected="selected"</c:if>>C</option>
							</select>
						</div>
						</div>
					</div>
					</div>


					<div class="col-sm-3">
					<div class="col-sm-12">
						<div class="form-group">
							<label>XYZ Class</label> <select class="form-control"
								id="XYZ_class" name="XYZ_class"
								value="${material_info.xyzClass}" checked="checked">
								<option value="X"
									<c:if test="${material_info.xyzClass eq 'X' }">selected="selected"</c:if>>X</option>
								<option value="Y"
									<c:if test="${material_info.xyzClass eq 'Y' }">selected="selected"</c:if>>Y</option>
								<option value="Z"
									<c:if test="${material_info.xyzClass eq 'Z' }">selected="selected"</c:if>>Z</option>
							</select>
						</div>
					</div>
				</div>
              </div>
			</div>

			<div id="productDet" style="display: none;">
				<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-12 col-sm-12">
						<h4>
							<label class="text-primary">Product Details:</label>
						</h4>
					</div>
				</div>

				<div class="row">
					<div class='row'>
						<div class='col-sm-3'>
						<div class="col-sm-12">
							<div class='form-group'>
								<label for="ProductDetails">BOM No</label> <input
									class="form-control" id="bom_number" name="bom_number"
									maxlength="200" size="30" type="text"
									value="${material_info.bomNumber}" placeholder="Enter BOM No" />
							</div>
						</div>
                          </div>


						<!--  <div class='row'> -->
						<div class='col-sm-3'>
						<div class="col-sm-12">
							<div class='form-group'>
								<label for="ProductDetails">Default Size Range</label> <input
									class="form-control" id="size_range" name="size_range"
									maxlength="200" size="30" type="text"
									value="${material_info.defaultSizeRange}"
									placeholder="Enter Size Range" /> <input type="hidden"
									name="size_range_id" id="size_range_id" />
							</div>
						</div>
						</div>
					</div>




					<div class='row'>
						<%--  <div class='col-sm-3'>    
				                <div class='form-group'>
								<label for="ProductDetails">Default Leather Id</label>
								  <input class="form-control" id="leather_id" name="leather_id"  maxlength="200"  size="30" type="text"  value="${material_info.defaultLeatherId}" placeholder="Enter Leather ID"/>
				                </div>
				            </div> --%>
						<div class='col-sm-3'>
						<div class="col-sm-12">
							<div class='form-group'>
								<label for="ProductDetails">Route Ref</label> <input
									class="form-control" id="route_ref" name="route_ref"
									maxlength="200" size="30" type="text"
									value="${material_info.routeRef}"
									placeholder="Enter Route Reference" />
							</div>
							</div>
						</div>



						<div class='col-sm-3'>
						<div class="col-sm-12">
							<div class='form-group'>
								<label for="ProductDetails">Default Variant </label> <input
									class="form-control" id="color_name" name="color_name"
									maxlength="200" size="30" type="text"
									value="${material_info.defaultColourName}"
									placeholder="Enter Colour ID" /> <input type="hidden"
									name="color_id" id="color_id"
									value="${material_info.defaultColourId}" />
							</div>
						</div>
					</div>
					</div>



					<div class='row'>
						<div class='col-sm-3'>
						<div class="col-sm-12">
							<div class='form-group'>
								<label for="ProductDetails">Buyer Style No</label> <input
									class="form-control" id="style_id" name="style_id"
									maxlength="200" size="30" type="text"
									value="${material_info.buyerStyleNo}"
									placeholder="Enter Style No" />
							</div>
						</div>
						</div>




						<div class='col-sm-3'>
						<div class="col-sm-12">
							<div class='form-group'>
								<label for="ProductDetails">Sample Style No</label> <input
									class="form-control" id="sample_id" name="sample_id"
									maxlength="200" size="30" type="text"
									value="${material_info.sampleStyleNo}"
									placeholder="Enter Sample Style No" />
							</div>
						</div>
					</div>
					</div>

					<!--  
				            <div class='row'>
				            
				            </div> -->

				</div>
			</div>
			</div>

			<div class='row'>
				<c:forEach items="${dynamicFieldsListMaterial}"
					var="dynamicHeaderfield">
					<div class='col-sm-3'>
					<div class="col-sm-12">
						<div class='form-group'>
							<%@ include file="../dynamic/ItemControls.jsp"%>
						</div>
						</div>
					</div>
				</c:forEach>
			</div>


			<div class='row'>
              <div class="col-sm-12">
               <div class="col-sm-12">
				<label for="fileupload"><span
					class="btn btn-primary fileinput-button"> <i
						class="glyphicon glyphicon-picture"></i> <span>Add
							images...</span>
				</span></label> <img id="img-ajax-loader"
					src="${pageContext.request.contextPath}/images/ajax-loader.gif"
					style="position: relative; margin-left: 160px; margin-top: -32px; vertical-align: middle; height: 35px; display: none;" />
				<input id="fileupload" name="image_file" type="file"
					multiple="multiple"
					accept="image/x-png, image/gif, image/jpeg, image/jpg, image/bmp"
					style="display: none;" />
			</div>
			</div>

			<div class="row">
			    <div class="col-sm-12">
               <div class="col-sm-12">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle " data-toggle="collapse"
									data-parent="#accordion" href="#collapseOne1"
									style="text-decoration: none;"> <span
									class="indicator glyphicon glyphicon-minus-sign"></span>
									&nbsp;&nbsp;Preview
								</a>
							</h4>

						</div>
						<div id="collapseOne1" class="panel-collapse collapse in">
							<div class="panel-body">

								<div id="dvPreview" class="row"></div>

								<div class="row">
									<c:set var="imgidx" value="1" />
									<c:forEach var="filename" items="${images}">
										<c:set var="fileV" value="${filename.picPath}" />
										<%
									String fV = (String) pageContext.getAttribute("fileV");
										if (fV != null && !fV.isEmpty()) {
											request.setAttribute("fileNV", fV);
										} else {
											request.setAttribute("fileNV", "");
										}
								%>
										<div class="col-md-2 col-sm-2 col-xs-12" id="images_${imgidx}"
											style="border: 1px solid #eee; padding: 5px;">
											<i
												class="glyphicon glyphicon-trash text-danger img-corner-del-icon"
												title="Delete"
												onclick="deleteMaterialAttachment('${imgidx}','${filename.matId}','${filename.picPath}','2')"></i>
											<img src="${materialAttachPath}${fileNV}" alt="${fileNV}"
												style="width: 100%" />
										</div>

										<c:if test="${imgidx%6==0}">
								</div>
								<div class="row">
									</c:if>
									<%-- <tr id="attach_file${idx}">
									<td width="150px">
										<input type="button" name="del_attach${idx}" id="del_attach${idx}" onclick="deleteMaterialAttachment('${idx}','${filename.quoteId}','${filename.url}')" value="x" class="btns"/>
										<button type="button" class="btn btn-danger destroy"
											name="del_attach${idx}" id="del_attach${idx}"
											onclick="deleteMaterialAttachment('${idx}','${filename.matId}','${filename.url}')">
											<i class="glyphicon glyphicon-trash"></i> <span>Delete</span>
										</button>
									</td>
									<td><a target="_blank"
										href="${materialAttachPath}${fileNV}">${fileNV}</a></td>
								</tr> 
								<c:set var="idx" value="${idx+1}" /> --%>
									<c:set var="imgidx" value="${imgidx+1}" />
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
			</div>

			<%-- <div class='row'>
 
<!-- <input type="button" value="+" onclick="addAttachment()" class="btns"/><img id="ajax-loader" src="images/ajax-loader.gif" style="position:relative ; margin-left: 160px;margin-top:-32px;vertical-align:middle;height:35px;display:none;"><br /> --> 
<span class="btn btn-primary fileinput-button" onclick="addAttachment()" 
								> <i
								class="glyphicon glyphicon-plus"></i> <span>Add files...</span>
								
							</span>
<img id="ajax-loader" src="${pageContext.request.contextPath}/images/ajax-loader.gif" style="position:relative ; margin-left: 160px;margin-top:-32px;vertical-align:middle;height:35px;display:none;"/>

</div> --%>

			<%-- <div class='row'>
						<table style="border-collapse: collapse;" class="attach-table">

							<c:set var="idx" value="1" />
							<c:forEach var="filename" items="${attached_files}">
								<c:set var="fileV" value="${filename.url}" />
								<%
									String fV = (String) pageContext.getAttribute("fileV");
										if (fV != null && !fV.isEmpty()) {
											request.setAttribute("fileNV", fV);
										} else {
											request.setAttribute("fileNV", "");
										}
								%>
								<tr id="attach_file${idx}">
									<td width="150px">
										<input type="button" name="del_attach${idx}" id="del_attach${idx}" onclick="deleteQuotationAttachment('${idx}','${filename.quoteId}','${filename.url}')" value="x" class="btns"/>
										<button type="button" class="btn btn-danger destroy"
											name="del_attach${idx}" id="del_attach${idx}"
											onclick="deleteMaterialAttachment('${idx}','${filename.matId}','${filename.url}','1')">
											<i class="glyphicon glyphicon-trash"></i> <span>Delete</span>
										</button>
									</td>
									<td><a target="_blank"
										href="${materialAttachPath}${fileNV}">${fileNV}</a></td>
								</tr>
								<c:set var="idx" value="${idx+1}" />
							</c:forEach>
						</table>
					</div> --%>
			<!-- <div class='row'>
						<table id="attachments" class="attach-table"
							style="border-collapse: collapse;">

						</table>
					</div> -->

			<div class='row pull-right' style="margin-bottom: 25px;">
				<div class='col-sm-12 col-xs-12 centered'>


					<c:if
						test="${material_rights.addPermission==1 or material_rights.editPermission==1 }">
						<div class="btn-group dropup">

							<%-- <button name="save" id="save"  onclick="saveMaterial('${mode}','1')" class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button> --%>
							<button name="save" id="save"
								class="btn btn-success ladda-button"
								onclick="saveMaterial('${mode}','1')" data-style="expand-right">
								<span class="ladda-label"> <i
									class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
							</button>

							<button class="btn btn-success dropdown-toggle"
								data-toggle="dropdown" style="height: 34px;">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">

								<c:if test="${material_rights.addPermission==1 }">
									<li><a href="javascript:saveMaterial('${mode}','2');">Save
											& New</a></li>
								</c:if>
								<c:if test="${material_rights.viewPermission==1 }">
									<li><a href="javascript:saveMaterial('${mode}','3');">Save
											& Close</a></li>
								</c:if>

							</ul>
						</div>

					</c:if>
					<c:if test="${material_rights.viewPermission==1 }">
						<button type="button" class="btn btn-primary"
							onclick="showMaterial()">
							<i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span>
						</button>
					</c:if>

				</div>
			</div>
</div>
		</div>
		</div>
		</div>


</div>
			</div>
</div>
		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
		<input type="hidden" name="total_pages" id="total_pages"
			value="${pc.pageCount}" /> <input type="hidden" name="save_type"
			id="save_type" /> <input type="hidden" name="mode" id="mode"
			value="${mode}" /> <input type="hidden" name="mat_id" id="mat_id"
			value="${mat_id}" /> <input type="hidden" name="invoke_method"
			id="invoke_method" /> <input type="hidden" name="invoke_class"
			id="invoke_class" /> <input type="submit" name="validation_btn"
			id="validation_btn" style="display: none;" /> <input type="hidden"
			id="add_new_group" data-toggle="modal"
			data-target="#groupMasterModal"
			onclick="loadDataToGroupMasterModal()" /> <input type="hidden"
			id="add_new_uom" data-toggle="modal" data-target="#uomMasterModal" />
		<input type="hidden" id="add_new_account" data-toggle="modal"
			data-target="#accountModal" />
		<!-- onclick="loadDataToAccountModal()" -->
		<input type="hidden" name="groupType" id="groupType"
			value="${groupType}" /> <input type="hidden" name="itemTracking"
			id="itemTracking" value="${material_info.itemTrackingApplicable}" />
		<input type="hidden" name="expiryApplicable" id="expiryApplicable"
			value="${material_info.expiryApplicable}" /> <input type="hidden"
			name="allowNegativeStock" id="allowNegativeStock"
			value="${material_info.negativeStock}" /> <input type="hidden"
			name="issueWithIo" id="issueWithIo"
			value="${material_info.issueWithIO}" /> <input type="hidden"
			name="sizeApplicable" id="sizeApplicable"
			value="${material_info.sizeApplicable}" /> <input type="hidden"
			name="colourApplicable" id="colourApplicable"
			value="${material_info.colorApplicable}" /> <input type="hidden"
			name="reservationApplicable" id="reservationApplicable"
			value="${material_info.stockReservation}" /> <input type="hidden"
			name="inspectionRequired" id="inspectionRequired"
			value="${material_info.inspectionRequired}" /> <input type="hidden"
			name="barcodeRequired" id="barcodeRequired"
			value="${material_info.barcodeRequired}" />


		<script>
 if(jQuery('#groupType').val()=='Finished Goods'){
	 jQuery("#productDet").show();
}
 if(jQuery('#itemTracking').val()==2){
	 jQuery("#itemTraking").show();
}
 if(jQuery('#expiryApplicable').val()==2){
	 jQuery("#expiryDate").show();
}
 if(jQuery('#allowNegativeStock').val()==2){
	 jQuery("#allowNagative_stock").show();
}
 if(jQuery('#issueWithIo').val()==2){
	 jQuery("#issue_WithIo").show();
}
 if(jQuery('#sizeApplicable').val()==2){
	 jQuery("#sizeApplicble").show();
}
 if(jQuery('#colourApplicable').val()==2){
	 jQuery("#colorApplicble").show();
}
 if(jQuery('#reservationApplicable').val()==2){
	 jQuery("#reservationApplicble").show();
}
 if(jQuery('#inspectionRequired').val()==2){
	 jQuery("#inspectionRequired").show();
}
 if(jQuery('#barcodeRequired').val()==2){
	 jQuery("#barcodeRequired").show();
}
 
 </script>
		<script>
	$('#validate-form').bootstrapValidator({
		//  live: 'disabled',
		  message: 'This value is not valid',
		  feedbackIcons: {
		      valid: 'glyphicon glyphicon-ok',
		      invalid: 'glyphicon glyphicon-remove',
		      validating: 'glyphicon glyphicon-refresh'
		  },
		  excluded: ':disabled',
		  fields: {
			  group_name: {
	              validators: {
	                  notEmpty: {
	                      message: 'This field is required'
	                  }
	              }
	          },
	          callback: {
                  message: 'This field is required',
                  callback: function(value, validator, $field) {
                  	if ($("#group_id").val()=='' && $("#group_id").val()<=0) {
                          return {
                              valid: false,
                              message: 'This field is required'
                          };
                      }
                  	return true;
                  }
              },
              currency_name: {
	              validators: {
	                  notEmpty: {
	                      message: 'This field is required'
	                  }
	              }
	          },
	          callback: {
                  message: 'This field is required',
                  callback: function(value, validator, $field) {
                  	if ($("#currency_id").val()=='' && $("#currency_id").val()<=0) {
                          return {
                              valid: false,
                              message: 'This field is required'
                          };
                      }
                  	return true;
                  }
              },
              standard_cost: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          stack_keepin_unit: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  }
	              }
	          },
	          purchase_unit: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  }
	              }
	          },
	          BOM_unit: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  }
	              }
	          },
	          excess_allowance: {
	              validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          standerd_leadtime: {
	              validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          min_level: {
	              validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          max_level: {
	              validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          reorder_point: {
	              validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          reorder_quantity: {
	              validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          min_order_qty: {
	              validators: {
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          /* group_id: {
	              validators: {
	                  notEmpty: {
	                      message: 'This field is required'
	                  }
	              }
	          }, */
	          material_code: {
	            validators: {
	                notEmpty: {
	                    message: 'This field is required'
	                }
	            }
	        },
	        
	        
	        new_currency_name : {
				validators : {
					notEmpty : {
						message : 'This field is required'
					}
				}
	        },
	        material_name: {
	              validators: {
	                  notEmpty: {
	                      message: 'This field is required'
	                  }
	              }
	          },
	        basic_unit: {
	              validators: {
	                  notEmpty: {
	                      message: 'This field is required'
	                  }
	              }
	          }
	          ,callback: {
                  message: 'This field is required',
                  callback: function(value, validator, $field) {
                  	if ($("#basic_unit_id").val()=='' && $("#basic_unit_id").val()<=0) {
                          return {
                              valid: false,
                              message: 'This field is required'
                          };
                      }
                  	return true;
                  }
              }
		  }
		});
	
 
 



	 
</script>

		<script type="text/javascript">
$(document).ready(function() {
 
 if(window.File && window.FileList && window.FileReader) {
 $("#fileupload").on("change",function(e) {
	 
	 var dvPreview = $("#dvPreview");
	 dvPreview.html("");
 var files = e.target.files ,
 filesLength = files.length ;
 for (var i = 0; i < filesLength ; i++) {
 var f = files[i]
 var fileReader = new FileReader();
 fileReader.onload = (function(e) {
 var file = e.target;
 
 var div= $("<div />");
	div.attr("class","col-md-2 col-sm-2 col-xs-12");
	div.attr("style","  border: 1px solid #eee;padding: 5px;");
 var img = $("<img />");
 img.attr("style", "width: 100%");
 img.attr("src", e.target.result);
 div.append(img);
 dvPreview.append(div);
 
 /* $("<img></img>",{
 class : "imageThumb",
 src : e.target.result,
 title : file.name
 }).insertAfter("#files"); */
 });
 fileReader.readAsDataURL(f);
 }
});
 } else { alert("Your browser doesn't support to File API") }
});
</script>

		<!-- <script language="javascript" type="text/javascript">
$(function () {
    $("#fileupload").change(function () {
        if (typeof (FileReader) != "undefined") {
            var dvPreview = $("#dvPreview");
            dvPreview.html("");
            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
            $($(this)[0].files).each(function () {
                var file = $(this);
                if (regex.test(file[0].name.toLowerCase())) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                    	var div= $("<div />");
                    	div.attr("class","col-md-2 col-sm-2 col-xs-12");
                    	div.attr("style","  border: 1px solid #CCD;padding: 8px;height: 229px;");
                        var img = $("<img />");
                        img.attr("style", "width: 100%");
                        img.attr("src", e.target.result);
                        div.append(img);
                        dvPreview.append(div);
                    }
                    reader.readAsDataURL(file[0]);
                } else {
                    alert(file[0].name + " is not a valid image file.");
                    dvPreview.html("");
                    return false;
                }
            });
        } else {
            alert("This browser does not support HTML5 FileReader.");
        }
    });
});
</script> -->
		<script>
	  !function ($) {

			 
			 $(function(){
				  $('#group_name').listGroup({
					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup&create_new=1",
					  nameField :'#group_name',
					  idField:'#group_id',
					  formId:'#validate-form',
					  validateId:'group_name'
				  });			 
			 }); 
			 
			 
			 $(function(){
				  $('#currency_name').listCurrency({
					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCurrency&create_new=1",
					  nameField :'#currency_name',
					  idField:'#currency_id',
					  formId:'#validate-form',
					  validateId:'currency_name'
				  });			 
			 }); 
			 
			 
			 
			 $(function(){
				  $('#color_name').listVariant({
					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
					  nameField :'#color_name',
					  idField:'#color_id',
					  formId:'#validate-form',
					  validateId:'color_name'
				  });			 
			 }); 
			 
			 
			 $(function(){
				  $('#size_range').listSizeRange({
					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
					  nameField :'#size_range',
					  idField:'#size_range_id',
					  formId:'#validate-form',
					  validateId:'size_range'
				  });			 
			 });
		$(function(){ 
			 $('#stack_keepin_unit').listUOM({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=0",
				  nameField :'#stack_keepin_unit',
				  formId:'#validate-form',
				  validateId:'stack_keepin_unit'
			  });
			 $('#purchase_unit').listUOM({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=0",
				  nameField :'#purchase_unit',
				  formId:'#validate-form',
				  validateId:'purchase_unit' 
			  });
			 $('#BOM_unit').listUOM({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=0",
				  nameField :'#BOM_unit',
				  formId:'#validate-form',
				  validateId:'BOM_unit'
			  });
			 
			 
			 }); 

			 
			 
			 
		
		}(window.jQuery);
	  </script>



	</form>
	<jsp:include page="../masters/AddGroupFromPage.jsp" />
	<jsp:include page="../masters/AddUOMFromPage.jsp" />



	<script type="text/javascript" language="javascript"
		src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
</body>
</html>

