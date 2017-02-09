<%@page import="com.alpha.tpcsfashion.util.NumericFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/purchase/purchase-order.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/common/moment.js"></script>
 <script>

$(function(){
$('[data-toggle="tooltip"]').tooltip();
});
$(document).ready(function(){
    $('#filter-dropdown').click(function (e) {
        e.stopPropagation();
    });
    
});


</script>


<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>

<script language="javascript">
var localizedStrings = {
		
		    ATTACHMENT_DELETED: "<fmt:message bundle="${bundle}"   key="Attachment.AttachmentDeleted"/>",
	    	ATTACHMENT_NOT_DELETED: "<fmt:message bundle="${bundle}" key="Attachment.AttachmentNotDeleted"/>",
	    	DELETE_ATTACHMENT: "<fmt:message bundle="${bundle}" key="Attachment.DeleteAttachment"/>",
		    FOLLOWER_DELETE_FAILED: "<fmt:message bundle="${bundle}"  key="Follower.DeleteFollowerFailed"/>",
	    	FOLLOWER_DELETED: "<fmt:message bundle="${bundle}"  key="Follower.FollowerDeleted"/>",
	    	DELETE_FOLLOWER: "<fmt:message bundle="${bundle}"  key="Follower.DeleteFollower"/>",
	    	AUTO_NO_SAVED: "<fmt:message  bundle="${bundle}" key="AutoNo.Saved"/>",
	    	AUTO_NO_NOT_SAVED: "<fmt:message  bundle="${bundle}" key="AutoNo.NotSaved"/>"
	    	
	};	
</script>

<script type="text/javascript">
function showhidebutton(values){
	if(values==1){
	  $("#tableshow").hide();
	  $("#texboxshow").show();
	}else{
		  $("#tableshow").show();
		  $("#texboxshow").hide();
	}
}
function showhidebutton1(values){
	if(values==1){
	  $("#tableshow1").hide();
	  $("#texboxshow1").show();
	}else{
		  $("#tableshow1").show();
		  $("#texboxshow1").hide();
	}
}


	</script>
	
<script language="javascript">
	<c:forEach var="events" items="${dynamicFormEvents}">
		<c:out value="function ${events.functionName}"/> 
		<c:out value =  "{"/>
		<c:out value="${events.attachedJS}" escapeXml="false"/>
		<c:out value =  "}"/>			
	</c:forEach>

	<c:forEach var="events" items="${dynamicHeaderFieldEvents}">
		<c:out value="function ${events.functionName}"/> 
		<c:out value =  "{"/>
		<c:out value="${events.attachedJS}" escapeXml="false"/>
		<c:out value =  "}"/>			
	</c:forEach>
    
	<c:forEach var="events" items="${dynamicDetailFieldEvents}">
		<c:out value="function ${events.functionName}"/> 
		<c:out value =  "{"/>
		<c:out value="${events.attachedJS}" escapeXml="false"/>
		<c:out value =  "}"/>			
	</c:forEach>
	
</script>

</head>

<jsp:include page="../common/ValidateUser.jsp" />
<body>
	<form id="apo_form" method="post" role="form">

		<div class="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Sales.Transactions.indent" name="screen_name" />
			</jsp:include>

			<div class="page-wrapper">
				<div class="page-inner">

					<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Purchase Order</h3>
							</div>
						</div>
					</div>
					
					<div class='row'>
					
						<div class='col-sm-3'>
					
						<c:forEach items="${dynamicFieldsListPOHeader}" var="dynamicHeaderfield">
							
							<c:if test="${dynamicHeaderfield.alignment=='L'}">
								<%@ include file="../purchase/AddPurchaseHeaderDynamic.jsp" %> 
							</c:if>
						</c:forEach>
							
						</div>
					
						<div class='col-sm-3'>
						
						<c:forEach items="${dynamicFieldsListPOHeader}" var="dynamicHeaderfield">
							<c:if test="${dynamicHeaderfield.alignment=='M'}">
								<%@ include file="../purchase/AddPurchaseHeaderDynamic.jsp" %> 
							</c:if>
						</c:forEach>
						
						</div>
					
						<div class='col-sm-3'>
						
						<c:forEach items="${dynamicFieldsListPOHeader}" var="dynamicHeaderfield">
							<c:if test="${dynamicHeaderfield.alignment=='R'}">
								<%@ include file="../purchase/AddPurchaseHeaderDynamic.jsp" %> 
							</c:if>
							
						</c:forEach>
							
						</div>
					
					</div>
					
				
				<input type="hidden" name="data_table_width" id="data_table_width" value="${header_info.detTableWidth}"/>
				 <%@ include file="../purchase/AddPurchaseDetailDynamic.jsp" %> 
				
					<c:if test="${view_mode ne 'yes' }">

						<div class="row">
							<div class="col-sm-12">
								<div class="col-sm-12">

									<a href="javascript:validatePO('${purchase_order.headerMode}','1');">
									<span class="glyphicon glyphicon-plus" class="text-primary"></span>&nbsp;&nbsp;AddAnother Line</a>

								</div>
							</div>
						</div>
					</c:if>
					
					<div class="row sub-title-row">
						<div class="col-sm-12">
							<div class="col-sm-12">
								<h5>
									<label class="text-primary">Instructions & Terms </label>
								</h5>
							</div>
						</div>
					</div>
					 
					 <div class="row ">
						
						
						<div class='col-sm-6 tax-area col-md-push-6' >
							
							<div class="tax-row">
							<div class="col-sm-12">
					            <label class="col-sm-9 col-xs-6" for="group_code">Sub Total :</label>
					             <div class="col-sm-3 col-xs-6">          
					                    <label id="sub_total_lbl"><fmt:formatNumber pattern="<%=NumericFormat.valFormat%>"  value="${header_info.totalValueFcy}"/></label>
					                    <input id="sub_total" name="sub_total" type="hidden"  value="<fmt:formatNumber pattern="<%=NumericFormat.valFormat%>"   value="${header_info.totalValueFcy}"/>"/>
							     </div>
						    </div>
						    </div>
						   
					    	<c:if test="${sessionScope.user_info.locatonConfigMap['PODiscount'] eq 'No'}">
					    	<div class="tax-row">
					    	<div class="col-sm-12">
					            <div class="col-sm-3 col-sm-offset-6 col-xs-6 form-group ">
				                    <select class="form-control text-right" id="discount_type" name="discount_type" onchange="calculateDisPer();" >
				                    <option value="per">Discount %</option>
				                    <option value="val">Discount Value</option>
				                    </select>
			                    </div>
				             	<div class="col-sm-3 col-xs-6">          
				                    <input class="form-control text-right" id="discount_per" name="discount_per" type="text" value="${headerInfo.discountPercent}" onkeyup="calculateDisPer();"/>
				                    <input id="discount_0" name="discount_0" type="hidden" value="${headerInfo.discountPercent}"/>
						     	</div>
					     	</div>
							</div>
							
							<div class="tax-row">
							<div class="col-sm-12">
					            <label class="col-sm-9 col-xs-6" for="group_code">Value after discount :</label>
					             <div class="col-sm-3 col-xs-6">          
				                   <label id="tot_val_aft_dis_val_lbl"><fmt:formatNumber pattern="#0.00" value="${value_aft_dis}"/></label>
				                   <input id="tot_val_aft_dis_val" name="tot_val_aft_dis_val" type="hidden"  value="${value_aft_dis}"/>
							     </div>
							</div>
							</div>
							</c:if>
							
							<div id="tax_cal_inner">       
							</div>
							
							<div class="tax-row">
							<div  class=" col-sm-12">      
					            <label class="col-sm-9 col-xs-6" >Total :</label>
					             <div class="col-sm-3 col-xs-6">          
				                    <label id="net_total_lbl"><fmt:formatNumber pattern="#0.00" value="0"/></label>
				                    <input id="net_total" name="net_total" type="hidden" />
							     </div>
							</div>        
							</div>
							
						</div>
						
						<div class='col-sm-6 col-md-pull-6'>
							
							<div class="col-sm-6">
								<div class='form-group'>
									<label for="hremark">Remark</label>
									<textarea class="form-control" rows="6" id="hremark" name="hremark" maxlength="250" placeholder="Enter Remark">${header_info.remark}</textarea>
								</div>
							</div>
							<div class="col-sm-6">
								<div class='form-group'>
									<label for="payment_terms">Payment Terms</label>
									<textarea class="form-control" rows="6" id="payment_terms" name="payment_terms" maxlength="250" placeholder="Enter Payment Terms">${header_info.paymentTerms}</textarea>
								</div>
							</div>
					 
							<div class="col-sm-6">
								<div class='form-group'>
									<label for="delivery_terms">Delivery Terms</label>
									<textarea class="form-control" rows="6" id="delivery_terms" name="delivery_terms" maxlength="250" placeholder="Enter Delivery Terms">${header_info.deliveryTerms}</textarea>
								</div>
							</div>
							<div class="col-sm-6">
								<div class='form-group'>
									<label for="ship_to_address">Ship To Address</label>
									<textarea class="form-control" rows="6" id="ship_to_address" name="ship_to_address" maxlength="250" placeholder="Enter Ship To Address">${header_info.shipToAddress}</textarea>
								</div>
							</div>
					 
						 	<div class="col-sm-6">
								<div class='form-group'>
									<label for="internal_memo">Internal Memo</label>
									<textarea class="form-control" rows="6" id="internal_memo" name="internal_memo" maxlength="250" placeholder="Enter Internal Memo">${header_info.internalMemo}</textarea>
								</div>
							</div>
						</div>
					
					</div>

					<div class='row'>
						<div class="col-sm-12">
							<div class="col-sm-12">
								<span class="btn btn-primary fileinput-button" onclick="addAttachment()"> 
								<i class="glyphicon glyphicon-plus"></i> <span>Add files...</span>
								</span> 
								<img id="ajax-loader" src="images/ajax-loader.gif" class="ajax-loader">
							</div>
						</div>
					</div>
					
					<div class='row attach-area'>
						<div class="col-sm-10">
							<div class="col-sm-12">
								<c:set var="idx" value="1" />
								<c:forEach var="filename" items="${attached_files}">
									 
									 <div class=" col-sm-4 notice notice-info" id="attach_file${idx}" >
									 <div class="attach-delete">
	        							<a class="attach-link-delete" href="#" name="del_attach${idx}" id="del_attach${idx}" onclick="deletePOAttachment('${idx}','${filename.poId}','${filename.url}')">
												<span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
									</div>
									<div >
												<a class="attach-link" target="_blank" href="${poAttachPath}${filename.url}">${filename.fileName}</a>
									</div>
	    							</div> 
								
									<c:set var="idx" value="${idx+1}" />
								</c:forEach>
							</div>
						</div>
					</div>

					<div class='row'>
						<div class="col-sm-12">
							<div class="col-sm-12">
								<table id="attachments" class="attach-table" style="border-collapse: collapse;">
								</table>
							</div>
						</div>
					</div>
					
					<div class='row sub-title-row' id="tc_area">
						<div class="col-sm-12 col-xs-12">
						<div class="col-sm-12">
							<div class="col-sm-9 col-xs-12">
								<h4>
									<label class="text-primary">Terms & Conditions </label>
								</h4>

								<c:choose>
									<c:when test="${not empty header_info.tcList}">
										<c:forEach var="tcVal" items="${header_info.tcList}">
											<div class="row row-no-margin" id="tc_row_${tcVal.sl}">
												<div class="col-sm-1-5  col-xs-3">
													<div class="form-group">
														<input type="text" class="form-control text-center" id="tc_slno_${tcVal.sl}" name="tc_slno_${tcVal.sl}" <c:if test="${tcVal.slno gt 0}">value="${tcVal.slno}"</c:if> />
													</div>
												</div>
												<div class="col-sm-3  col-xs-7">
													<div class="form-group">
														<input type="text" class="form-control" id="tc_text_${tcVal.sl}" name="tc_text_${tcVal.sl}" value="<c:out value="${tcVal.term}"/>" maxlength="50" />
													</div>
												</div>
												
												<div class="col-sm-6  col-xs-7">
													<div class="form-group">
														<input type="text" class="form-control" id="tc_particular_${tcVal.sl}" name="tc_particular_${tcVal.sl}" value="<c:out value="${tcVal.particular}"/>" maxlength="250" />
													</div>
												</div>

												<div class="col-sm-1">
													<a href="javascript:addTCRow(${tcVal.sl},'po_tc_ids','po_tc_cnt');" id="tc_add_${tcVal.sl}" style="display:none;"><span class="glyphicon glyphicon-plus text-primary"></span></a>
												</div>

											</div>
										<script>
										jQuery('#tc_particular_${tcVal.sl}').focus(function(e) {
									    	jQuery('#tc_add_${tcVal.sl}').show();
									    	jQuery('#tc_add_${tcVal.sl+1}').hide();
									    	jQuery('#tc_add_${tcVal.sl-1}').hide();
										}); 
									    jQuery('#tc_slno_${tcVal.sl}').focus(function(e) {
									    	jQuery('#tc_add_${tcVal.sl}').hide();
									    	jQuery('#tc_add_${tcVal.sl+1}').hide();
									    	jQuery('#tc_add_${tcVal.sl-1}').hide();
									    	
										}); 
										</script>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<div class="row row-no-margin" id="tc_row_1">
											<div class="col-sm-1-5  col-xs-3">
												<div class="form-group">
													<input type="text" class="form-control text-center" id="tc_slno_1" name="tc_slno_1" />
												</div>
											</div>
											<div class="col-sm-3  col-xs-7">
												<div class="form-group">
													<input type="text" class="form-control" id="tc_text_1" name="tc_text_1" maxlength="50" />
												</div>
											</div>
											
											<div class="col-sm-6  col-xs-7">
												<div class="form-group">
													<input type="text" class="form-control" id="tc_particular_1" name="tc_particular_1" maxlength="250" />
												</div>
											</div>

											<div class="col-sm-1">
												<a href="javascript:addTCRow(1,'po_tc_ids','po_tc_cnt');" id="tc_add_1" style="display: none;">
													<span class="glyphicon glyphicon-plus text-primary"></span>
												</a>
											</div>
											<script>jQuery('#po_tc_ids').val("1,");</script>
										</div>
										<script>
										jQuery('#tc_particular_1').focus(function(e) {
									    	jQuery('#tc_add_1').show();
									    	jQuery('#tc_add_2').hide();
										}); 
									    jQuery('#tc_slno_1').focus(function(e) {
									    	jQuery('#tc_add_1').hide();
									    	jQuery('#tc_add_2').hide();
									    	
										}); 
										</script>
									</c:otherwise>
								</c:choose>


								<!-- <div class="row row-no-margin">
									<div class="col-sm-12">
										<div class="checkbox">
											<input class="checkbox_1" type="checkbox" id="bo_default_tc" name="bo_default_tc" value="1">
											<label for="bo_default_tc" class="checkbox_1">Make this as my default Terms & Conditions</label>
										</div>
									</div>
								</div> -->
							</div>
						</div>
						</div>
					</div>

					<div class='row '>
						<div class='col-sm-12 col-xs-12 '>
							<div class='pull-right centered save-view'>
								<c:if test="${view_mode ne 'yes' }">
								<div class="btn-group dropup">
									<button name="save" id="save" onclick="validatePO('${purchase_order.headerMode}','1')" class="btn btn-success ladda-button" data-style="expand-right"> <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save</button>
								 
								 	<button class="btn btn-success dropdown-toggle" data-toggle="dropdown" ><span class="caret"></span></button>
									<ul class="dropdown-menu">
										<li><a href="javascript:validatePO('${purchase_order.headerMode}','2')" >Save & Send</a></li>
										<li><a href="javascript:validatePO('${purchase_order.headerMode}','3')">Save & New</a></li>
										<li class="divider"></li>
										<li><a href="javascript:validatePO('${purchase_order.headerMode}','4')">Save & Close</a></li>
									</ul>
								</div>
								</c:if>
								<button type="button" class="btn btn-primary" onclick="showPurchaseOrder()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>
							</div>
						</div>
					</div>

					<div class='row' style="margin-top: 20px;"></div>

				</div>
			</div>
		</div>
		<jsp:include page="../common/Footer.jsp" />
 		
 		
 		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" /> 
		<input type="hidden" name="pageno" id="pageno" /> 
		<input type="hidden" name="request_type" id="request_type" />
		<input type="hidden" name="screen" id="screen" value="PO" />
		
		<input type="hidden" name="screen_tag" id="screen_tag" value="APO"/>
		<input type="hidden" name="header_mode" id="header_mode" value="${purchase_order.headerMode}" />
		<input type="hidden" name="detail_mode" id="detail_mode"  value="${purchase_order.detailMode}"/>
		
		<input type="hidden" name="po_id" id="po_id" value="${header_info.poId}" />  
		<input type="hidden" name="po_det_id" id="po_det_id" value="${detail_info.poDetId}" />
		
		<input type="hidden" name="edited_ids" id="edited_ids" value=""/>
		<input type="hidden" name="dynedited_ids" id="dynedited_ids" value=""/>
		<input type="hidden" name="edited_mode" id="edited_mode" value=""/>
		<input type="hidden" name="invoke_method" id="invoke_method" />
		<input type="hidden" name="invoke_class" id="invoke_class" />
		<input type="hidden" name="selected_users" id="selected_users"/>
		<input type="hidden" name="selected_userremarks" id="selected_userremarks"/>
		<input type="hidden" name="selected_remarks" id="selected_remarks"/>
		
		<input type="hidden" name="page" id="page" />
		<input type="hidden" name="save_type" id="save_type" />
		<input type="hidden" name="new_det_mode" id="new_det_mode"/>
		<input type="hidden" name="mode" id="mode"/>
		<input type="hidden" name="prefix_name" id="prefix_name"/>
		
		<input type="hidden" name="edited_size_det_ids" id="edited_size_det_ids" />
		
		<%-- <c:set var="det_ids" value=""/>
		<c:forEach var="detId" items="${det_id_list}">
		<c:set var="det_ids" value="${det_ids}${detId},"/>
		</c:forEach> --%>
		<input type="hidden" name="det_ids" id="det_ids" value="${det_ids}"/>
		
		<input type="hidden" name="po_tc_ids" id="po_tc_ids" value="${header_info.tcIds}"/>
		<input type="hidden" name="po_tc_cnt" id="po_tc_cnt" value="${header_info.tcCnt}"/>
		 
		 <input type="hidden" name="detail_discount" id="detail_discount" value="${sessionScope.user_info.locatonConfigMap['PODiscount']}"/>
		 
		<input type="submit" name="apo_validation_btn" id="apo_validation_btn" style="display:none;" />


<jsp:include page="../common/TaxCalculation.jsp" />
  <script>
 
  calculateTax();
 
 $('#apo_form').bootstrapValidator({
		
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	/*   excluded: ':disabled', */
	  fields: {
		 
	        po_date: {
	              validators: {
	                  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  date: {
	                      format: 'DD-MM-YYYY',
	                      message: 'The date is not a valid'
	                  }
	              }
	          },
	         
	          po_prefix: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if (value<=0) {
	                              return {
	                                  valid: false,
	                                  message: 'This field is required'
	                              };
	                          }
	                      	return true;
	                      }
	                  }
	          
	              }
	          },
	          po_no: {
	              validators: {
	            	  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if (value.length==0){
	                      		 return {
		                                    valid: false,
		                                    message: 'This field is required'
		                                };
	                      		}
	                      	else if (value<=0) {
	                              return {
	                                  valid: false,
	                                  message: 'This field must be greater than zero'
	                              };
	                          }
	                      	return true;
	                      }
	                  } ,
	                  numeric: {
		                      message: 'This field must be a number'
		                            } 
	                    }
	          },
	          buyer_name: {
		            validators: {
		                notEmpty: {
		                    message: 'This field is required'
		                },
		                callback: {
		                    message: 'This field is required',
		                    callback: function(value, validator, $field) {
		                    	if ($("#party_id").val()==0) {
		                            return {
		                                valid: false,
		                                message: 'This field is required'
		                            };
		                        }
		                    	return true;
		                    }
		                }
		          
		            }
		        },
		        po_date: {
		              validators: {
		                  notEmpty: {
		                      message: 'This field is required'
		                  },
		                  date: {
		                      format: 'DD-MM-YYYY',
		                      message: 'The date is not a valid'
		                  }
		              }
		          },
		          currency_name: {
		              validators: {
		                  notEmpty: {
		                      message: 'This field is required'
		                  },
		                    callback: {
		                        message: 'This field is required',
		                        callback: function(value, validator, $field) {
		                        	if ($("#currency_id").val()==0) {
		                                return {
		                                    valid: false,
		                                    message: 'This field is required'
		                                };
		                            }
		                        	return true;
		                        }
		                    }
		              }
		          },
	          ex_rate: {
	              validators: {
	                
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	         
	          item_name_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#item_id_0").val()==0) {
	                              return {
	                                  valid: false,
	                                  message: 'This field is required'
	                              };
	                          }
	                      	return true;
	                      }
	                  }
	              }
	          },
	          size_range_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#size_range_id_0").val()==0) {
	                              return {
	                                  valid: false,
	                                  message: 'This field is required'
	                              };
	                          }
	                      	return true;
	                      }
	                  }
	              }
	          },
	          
	          variant_name_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#variant_id_0").val()==0) {
	                              return {
	                                  valid: false,
	                                  message: 'This field is required'
	                              };
	                          }
	                      	return true;
	                      }
	                  }
	              }
	          }, 
	          qty_0: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          qty_uom_0: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  } 
	              }
	          },
	        
	          price_fcy_0: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          
	          required_date_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  date: {
	                      format: 'DD-MM-YYYY',
	                      message: 'The date is not a valid'
	                  },
	                  callback: {
	                        message: 'Select after Order date',
	                        callback: function(value, validator) {
	                            //var m = new moment(value, 'YYYY/MM/DD');
	                            //if (!m.isValid()) {
	                               // return false;
	                            //}
	                            // Check if the date in our range
	                            var dateAr=jQuery('#po_date').val().split('-');
	                            var reqDateAr=jQuery('#required_date_0').val().split('-');
	                            
	                            var trDate=moment(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0],'YYYY/MM/DD',true);
	                            var reqDate=moment(reqDateAr[2]+'/'+reqDateAr[1]+'/'+reqDateAr[0],'YYYY/MM/DD',true);
	                            
	                            //return m.isAfter(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0]) && m.isBefore(posDateAr[2]+'/'+posDateAr[1]+'/'+posDateAr[0]);
	                            return (reqDate >=trDate);
	                        }
	                    }
	              }
	          },
	        
	          <c:forEach var="detId" items="${det_id_list}">
	        
	          item_name_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#item_id_S_${detId}").val()==0) {
	                              return {
	                                  valid: false,
	                                  message: 'This field is required'
	                              };
	                          }
	                      	return true;
	                      }
	                  }
	          
	              }
	          },
	          size_range_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#size_range_id_S_${detId}").val()==0) {
	                              return {
	                                  valid: false,
	                                  message: 'This field is required'
	                              };
	                          }
	                      	return true;
	                      }
	                  }
	          
	              }
	          },
	          variant_name_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#variant_id_S_${detId}").val()==0) {
	                              return {
	                                  valid: false,
	                                  message: 'This field is required'
	                              };
	                          }
	                      	return true;
	                      }
	                  }
	              }
	          }, 
	          qty_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          qty_uom_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  } 
	              }
	          },
	       
	          price_fcy_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },
	          
	          
	          required_date_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  date: {
	                      format: 'DD-MM-YYYY',
	                      message: 'The date is not a valid'
	                  },
	                  callback: {
	                        message: 'Select after Order date',
	                        callback: function(value, validator) {
	                            //var m = new moment(value, 'YYYY/MM/DD');
	                            //if (!m.isValid()) {
	                               // return false;
	                            //}
	                            // Check if the date in our range
	                            var dateAr=jQuery('#po_date').val().split('-');
	                            var reqDateAr=jQuery('#required_date_S_${detId}').val().split('-');
	                            
	                            var trDate=moment(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0],'YYYY/MM/DD',true);
	                            var reqDate=moment(reqDateAr[2]+'/'+reqDateAr[1]+'/'+reqDateAr[0],'YYYY/MM/DD',true);
	                            
	                            //return m.isAfter(dateAr[2]+'/'+dateAr[1]+'/'+dateAr[0]) && m.isBefore(posDateAr[2]+'/'+posDateAr[1]+'/'+posDateAr[0]);
	                            return (reqDate >=trDate);
	                        }
	                    }
	              }
	          },
	         
			//for detail edit row dynamic
	          <c:forEach items="${dynamicFieldsListPODetail}" var="dynamicDetailfield">
	          <c:choose>
	          <c:when test="${not dynamicDetailfield.fixed and dynamicDetailfield.required}">
		          <c:choose>
		          <c:when test="${dynamicDetailfield.fieldTypeName == 'textbox' }">
		          		<c:choose>
		          		<c:when test="${dynamicDetailfield.dataTypeName == 'Date'}">
		          	
		          		${dynamicDetailfield.pageFieldName}_S_${detId}: {
			                 validators: {
			                     notEmpty: {
			                   	   message: 'This field is required'
			                     },
			                     date: {
			                         format: 'DD-MM-YYYY',
			                         message: 'The date is not a valid'
			                     }
			                 }
			             },
		          		</c:when>
		          		<c:otherwise>
		          		${dynamicDetailfield.pageFieldName}_S_${detId}: {
			                validators: {
			                    notEmpty: {
			                  	   message: 'This field is required'
			                    }
			                }
			            },
		          		</c:otherwise>
		          		</c:choose>
		          </c:when>
		          <c:otherwise>
			          <c:when test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
			          ${dynamicDetailfield.pageFieldName}_S_${detId}: {
			              validators: {
			                  notEmpty: {
			                	   message: 'This field is required'
			                  }
			              }
			          },
			          </c:when>
		          </c:otherwise>
		          </c:choose>
	          </c:when>
	          </c:choose>
	          </c:forEach>
	          
	          
	          </c:forEach>
	          
	          //For header dynamic
	          <c:forEach items="${dynamicFieldsListPOHeader}" var="dynamicHeaderfield">
	          <c:choose>
	          <c:when test="${not dynamicHeaderfield.fixed and dynamicHeaderfield.required}">
		          <c:choose>
		          <c:when test="${dynamicHeaderfield.fieldTypeName == 'textbox' }">
		          		<c:choose>
		          		<c:when test="${dynamicHeaderfield.dataTypeName == 'Date'}">
		          	
			          	${dynamicHeaderfield.pageFieldName}: {
			                 validators: {
			                     notEmpty: {
			                   	   message: 'This field is required'
			                     },
			                     date: {
			                         format: 'DD-MM-YYYY',
			                         message: 'The date is not a valid'
			                     }
			                 }
			             },
		          		</c:when>
		          		<c:otherwise>
			          	${dynamicHeaderfield.pageFieldName}: {
			                validators: {
			                    notEmpty: {
			                  	   message: 'This field is required'
			                    }
			                }
			            },
		          		</c:otherwise>
		          		</c:choose>
		          </c:when>
		          <c:otherwise>
			          <c:when test="${dynamicHeaderfield.fieldTypeName == 'textarea'}">
			          ${dynamicHeaderfield.pageFieldName}: {
			              validators: {
			                  notEmpty: {
			                	   message: 'This field is required'
			                  }
			              }
			          },
			          </c:when>
		          </c:otherwise>
		          </c:choose>
	          </c:when>
	          </c:choose>
	          </c:forEach>
	          
	          //For detail new row dynamic
	          <c:forEach items="${dynamicFieldsListPODetail}" var="dynamicDetailfield">
	          <c:choose>
	          <c:when test="${not dynamicDetailfield.fixed and dynamicDetailfield.required}">
		          <c:choose>
		          <c:when test="${dynamicDetailfield.fieldTypeName == 'textbox' }">
		          		<c:choose>
		          		<c:when test="${dynamicDetailfield.dataTypeName == 'Date'}">
		          		${dynamicDetailfield.pageFieldName}_0: {
			                 validators: {
			                     notEmpty: {
			                   	   message: 'This field is required'
			                     },
			                     date: {
			                         format: 'DD-MM-YYYY',
			                         message: 'The date is not a valid'
			                     }
			                 }
			             },
		          		</c:when>
		          		<c:otherwise>
		          		${dynamicDetailfield.pageFieldName}_0: {
			                validators: {
			                    notEmpty: {
			                  	   message: 'This field is required'
			                    }
			                }
			            },
		          		</c:otherwise>
		          		</c:choose>
		          </c:when>
		          <c:otherwise>
		          	<c:choose>
			          <c:when test="${dynamicDetailfield.fieldTypeName == 'textarea'}">
			          ${dynamicDetailfield.pageFieldName}_0: {
			              validators: {
			                  notEmpty: {
			                	   message: 'This field is required'
			                  }
			              }
			          },
			          </c:when>
		          	</c:choose>
		          </c:otherwise>
		          </c:choose>
	          </c:when>
	          </c:choose>
	          </c:forEach>
	          
	         
	        
	        
	      
	  }
});
 
 
 
 </script>  
 
 
 <script>
//remarks and other ref

 <c:choose>
 	<c:when test="${not empty header_info.tcList}">
 		<c:forEach var="tcVal" items="${header_info.tcList}">
 		$('#apo_form').bootstrapValidator('addField', 'tc_slno_${tcVal.sl}', {
 			 validators: {
 		       numeric: {
 		           message: 'Enter number'
 		       }
 		   }
 		});
 		</c:forEach>
 		</c:when>
 		
 		<c:otherwise>
 		
 		$('#apo_form').bootstrapValidator('addField', 'tc_slno_1', {
 			 validators: {
 		        numeric: {
 		            message: 'Enter number'
 		        }
 		    }
 		  });
 		</c:otherwise>
 </c:choose>
 </script>
 <script>
 
//---------------------------FIXED HEDER TABLE WITH AUTO RESIZE COLUMN

 var resizeCol = function() {
   var tiw = $('.table-body .table').width();
   $('.table-head').width(tiw);
   $('#data-table tr:first').children().each(function(index, element) {
     var w = $(this).width(),i = $(this).index();
   
  
   // w=w+2;

     $('#head-table th:eq(' + i + ')').width(w);
    // alert('w :'+w+' i:'+i);
   });
 }
 //resizeCol();
 $(window).on("resize", function() {
   resizeCol();
 });

 var scrollTarget = function() {
   var target = $(".table-header");
   $(".table-body").scroll(function() {
     target.prop("scrollLeft", this.scrollLeft);
   });
 }



 $tableInner = $('.table-body');
 $tableInner.attr('style', 'overflow: auto; width: auto; max-height: 460px');

 resizeCol();
 scrollTarget();

 //---------------------------FIXED HEDER TABLE WITH AUTO RESIZE COLUMN END
 
 !function ($) {
	 
		$(function(){
			
			
			 $('#buyer_name').listCustomer({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=1",
				  nameField :'#buyer_name',
				  idField:'#party_id',
				  formId:'#apo_form',
				  validateId:'buyer_name'
			  }); 
			 
			  $('#currency_name').listCurrency({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCurrency&create_new=1",
				  nameField :'#currency_name',
				  idField:'#currency_id',
				  formId:'#apo_form',
				  validateId:'currency_name'
			  }); 
			  
			  $('#season_name').listSeason({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSeason&create_new=1",
				  nameField :'#season_name',
				  idField:'#season_id',
				  formId:'#apo_form',
				  validateId:'season_name'
			  }); 
			 
			
			<c:forEach var="detId" items="${det_id_list}">
			 
			 
			$('#item_name_S_${detId}').listMaterial({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterialFG&create_new=1",
				  nameField :'#item_name_S_${detId}',
				  idField:'#item_id_S_${detId}',
				  formId:'#apo_form',
				  validateId:'item_name_S_${detId}'
				  
			  });
			 $('#variant_name_S_${detId}').listVariant({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
				  nameField :'#variant_name_S_${detId}',
				  idField:'#variant_id_S_${detId}',
				  formId:'#apo_form',
				  validateId:'variant_name_S_${detId}'
			  });
			 $('#size_range_S_${detId}').listSizeRange({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
				  nameField :'#size_range_S_${detId}',
				  idField:'#size_range_id_S_${detId}',
				  formId:'#apo_form',
				  validateId:'size_range_S_${detId}'
			  });
			
			 $('#uom_S_${detId}').listUOM({
				 
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=1",
				  nameField :'#uom_S_${detId}',
				  idField:'#uom_id_S_${detId}',
				  formId:'#apo_form',
				  validateId:'uom_S_${detId}'
			  });
			 
			 </c:forEach>
			
			  $('#item_name_0').listMaterial({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterialFG&create_new=1",
				  nameField :'#item_name_0',
				  idField:'#item_id_0',
				  formId:'#apo_form',
				  validateId:'item_name_0'
			  }); 
			  $('#variant_name_0').listVariant({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
				  nameField :'#variant_name_0',
				  idField:'#variant_id_0',
				  formId:'#apo_form',
				  validateId:'variant_name_0'
			  });
			  
			  $('#size_range_0').listSizeRange({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
				  nameField :'#size_range_0',
				  idField:'#size_range_id_0',
				  formId:'#apo_form',
				  validateId:'size_range_0'
			  });
			  
			  $('#uom_0').listUOM({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=1",
				  nameField :'#uom_0',
				  idField:'#uom_id_0',
				  formId:'#apo_form',
				  validateId:'uom_0'
			  });
			  
		
		 });
		   
		
}(window.jQuery);	
 
$(document).ready(function () {
	
	
	<c:forEach var="detId" items="${det_id_list}">
	
	jQuery('#carton_panel_${detId}_togg').click(function () {
		jQuery('#cortan_panel_${detId}').toggle();
		if(jQuery('#cortan_panel_${detId}').is(":visible")){
			jQuery('#carton_panel_${detId}_togg').removeClass("glyphicon-plus-sign").addClass("glyphicon-minus-sign");
			jQuery('#size_grid_${detId}').hide();
		} 
		else{
			jQuery('#carton_panel_${detId}_togg').removeClass("glyphicon-minus-sign").addClass("glyphicon-plus-sign");
			jQuery('#size_grid_${detId}').show();
			
		}
	});
	
	</c:forEach>
});
 
 </script>

	</form>
	
 	<jsp:include page="../common/AutoNoGeneration.jsp" >
	
		<jsp:param value="po" name="auto_modal_label"/>
		<jsp:param value="po_no" name="auto_header_no_column"/>
		<jsp:param value="po_prefix" name="page_prefix_id"/>
		<jsp:param value="po_no" name="page_auton_no_id"/>
		<jsp:param value="po_id" name="auto_header_id_column"/>
		<jsp:param value="po" name="auto_header_table_name"/>
		<jsp:param value="set_autono_purchase_order" name="auto_autono_table_name"/>
	</jsp:include> 
	

	
	
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>	
</body>
</html>