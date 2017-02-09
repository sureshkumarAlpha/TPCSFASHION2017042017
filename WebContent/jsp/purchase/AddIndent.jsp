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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/purchase/indent.js"></script>
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

<style>
.size-table tr th{
	width:100px;
}
</style>	
</head>


<jsp:include page="../common/ValidateUser.jsp" />
<body>
	<form id="aind_form" method="post" role="form" autocomplete="off">

		<div class="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Order.Transaction.Indent" name="screen_name" />
			</jsp:include>

			<div class="page-wrapper">
				<div class="page-inner">

					<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Indent</h3>
							</div>
						</div>
					</div>
					
					<div class='row'>
					
						<div class='col-sm-3'>
					
						<c:forEach items="${dynamicFieldsListINDHeader}" var="dynamicHeaderfield">
							<c:if test="${dynamicHeaderfield.alignment=='L'}">
							
							
								<%@ include file="../purchase/AddIndentHeaderDynamic.jsp" %> 
							</c:if>
						</c:forEach>
					
						</div>
					
						<div class='col-sm-3'>
						
						<c:forEach items="${dynamicFieldsListINDHeader}" var="dynamicHeaderfield">
							<c:if test="${dynamicHeaderfield.alignment=='M'}">
							
								<%@ include file="../purchase/AddIndentHeaderDynamic.jsp" %> 
							
							</c:if>
						</c:forEach>
						
						</div>
					
						<div class='col-sm-3'>
						
						<c:forEach items="${dynamicFieldsListINDHeader}" var="dynamicHeaderfield">
							<c:if test="${dynamicHeaderfield.alignment=='R'}">
							
								<%@ include file="../purchase/AddIndentHeaderDynamic.jsp" %> 
							
							</c:if>
						</c:forEach>
						
						</div>
					
					</div>
					
					<input type="hidden" name="data_table_width" id="data_table_width" value="${header_info.detTableWidth}"/>
					
					 <%@ include file="../purchase/AddIndentDetailDynamic.jsp" %> 
					 
		<c:if test="${view_mode ne 'yes' }">
					 
			<div class="row">
				<div class="col-sm-12">
				<div class="col-sm-12">
			
				<a href="javascript:validateIND('${indent.headerMode}','1');" ><span class="glyphicon glyphicon-plus" class="text-primary"></span>&nbsp;&nbsp;Add Another Line</a>
			</div>
				</div>
 		    </div>
					 
		</c:if>			 
					 
					 <br>
								

					<div class="row ">
						<div class='col-sm-3'>
						<div class="col-sm-12">
							<div class='form-group'>
								<label for="remarks">Remarks</label>
								<textarea class="form-control" rows="6" id="remarks" name="remarks" maxlength="250" placeholder="Enter Remarks">${header_info.remarks}</textarea>
							</div>
							</div>
						</div>
						</div>
				<div class="row ">
						<div class='col-sm-3'>
						<div class="col-sm-12">
							<div class='form-group'>
								<label for="other_ref">Other Reference</label>
								<textarea class="form-control" rows="6" id="other_ref" name="other_ref" maxlength="250" placeholder="Enter Other Reference">${header_info.otherRef}</textarea>
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
					<div class="col-sm-12">
					<div class="col-sm-12">
							<c:set var="idx" value="1" />
							<c:forEach var="filename" items="${attached_files}">
								 
								 <div class=" col-sm-4 notice notice-info" id="attach_file${idx}" >
								 <div class="attach-delete">
        							<a class="attach-link-delete" href="#" name="del_attach${idx}" id="del_attach${idx}" onclick="deleteINDAttachment('${idx}','${filename.indentId}','${filename.url}')">
											<span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
								</div>
								<div >
											<a class="attach-link" target="_blank" href="${indAttachPath}${filename.url}">${filename.fileName}</a>
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
					 
					<div class='row' id="tc_area">
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
													<a href="javascript:addTCRow(${tcVal.sl},'ind_tc_ids','ind_tc_cnt');" id="tc_add_${tcVal.sl}" style="display:none;"><span class="glyphicon glyphicon-plus text-primary"></span></a>
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
												<a href="javascript:addTCRow(1,'ind_tc_ids','ind_tc_cnt');" id="tc_add_1" style="display: none;">
													<span class="glyphicon glyphicon-plus text-primary"></span>
												</a>
											</div>
											<script>jQuery('#ind_tc_ids').val("1,");</script>
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
						<div class="col-sm-12">
							<div class='pull-right centered save-view'>
								<c:if test="${view_mode ne 'yes' }">
								<div class="btn-group dropup">
									<button name="save" id="save" onclick="validateIND('${indent.headerMode}','1')" class="btn btn-success ladda-button" data-style="expand-right"> <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save</button>
									<button class="btn btn-success dropdown-toggle" data-toggle="dropdown" ><span class="caret"></span></button>
									<ul class="dropdown-menu">
										<li><a href="javascript:validateIND('${indent.headerMode}','2')" >Save & Send</a></li>
										<li><a href="javascript:validateIND('${indent.headerMode}','3')">Save & New</a></li>
										<li class="divider"></li>
										<li><a href="javascript:validateIND('${indent.headerMode}','4')">Save & Close</a></li>
									</ul>
								</div>
								</c:if>
								<button type="button" class="btn btn-primary" onclick="showIndent()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>
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
		<input type="hidden" name="screen" id="screen" value="IND" />
		
		<input type="hidden" name="screen_tag" id="screen_tag" value="AIND"/>
		<input type="hidden" name="header_mode" id="header_mode" value="${indent.headerMode}" />
		<input type="hidden" name="detail_mode" id="detail_mode"  value="${indent.detailMode}"/>
		
		<input type="hidden" name="indent_id" id="indent_id" value="${header_info.indentId}" />  
		<input type="hidden" name="indent_det_id" id="indent_det_id" value="${detail_info.indentDetId}" /> 
		
		
		
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
		
		<c:set var="det_ids" value=""/>
		<c:forEach var="detId" items="${det_id_list}">
		<c:set var="det_ids" value="${det_ids}${detId},"/>
		</c:forEach>
		<input type="hidden" name="det_ids" id="det_ids" value="${det_ids}"/>
		
		<input type="hidden" name="ind_tc_ids" id="ind_tc_ids" value="${header_info.tcIds}"/>
		<input type="hidden" name="ind_tc_cnt" id="ind_tc_cnt" value="${header_info.tcCnt}"/>
		 
		<input type="submit" name="aind_validation_btn" id="aind_validation_btn" style="display:none;"/>

 <input type="hidden" id="add_new_uom" data-toggle="modal" data-target="#uomMasterModal"  />
 <input type="hidden" id="add_new_customer" data-toggle="modal" data-target="#customerMasterModal"  /> 
 <input type="hidden" id="add_new_mat" data-toggle="modal" data-target="#materialModal"  />
<script>
 
 

 $('#aind_form').bootstrapValidator({
		
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	  fields: {
		
	        indent_date: {
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
	         
	          indent_prefix: {
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
	          indent_no: {
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
	          
        	  size_range_0: {
		              validators: {
		                  callback: {
		                      message: 'This field is required',
		                      callback: function(value, validator, $field) {
		                      	if (($("#size_range_id_0").val()==0 || $("#size_range_id_0").val()=='' ) && $('#size_range_0').is('[readonly]')== false) {
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
	         
	          uom_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#uom_id_0").val()==0) {
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
	          
	          indent_qty_0: {
	              validators: {
	            	  notEmpty: {
	                      message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
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
	          uom_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  callback: {
	                      message: 'This field is required',
	                      callback: function(value, validator, $field) {
	                      	if ($("#uom_id_S_${detId}").val()==0) {
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
	          
	          if($('size_range_S_${detId}').not('[readonly]')){
	        	  
	        	 
	        	  
	        	  size_range_S_${detId}: {
		              validators: {
		                  notEmpty: {
		                	   message: 'This field is required'
		                  },
		                  callback: {
		                      message: 'This field is required',
		                      callback: function(value, validator, $field) {
		                      	if ($("#size_range_id_S_${detId}").val()==0  {
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
		          
	          }
	          
	          
	          
	          indent_qty_S_${detId}: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  numeric: {
	                      message: 'This field must be a number'
	                  }
	              }
	          },

	           
	          
			//for detail edit row dynamic
	          <c:forEach items="${dynamicFieldsListINDDetail}" var="dynamicDetailfield">
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
	          <c:forEach items="${dynamicFieldsListINDHeader}" var="dynamicHeaderfield">
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
	          <c:forEach items="${dynamicFieldsListINDDetail}" var="dynamicDetailfield">
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
	          
	       
	          required_date_0: {
	              validators: {
	                  notEmpty: {
	                	   message: 'This field is required'
	                  },
	                  date: {
	                      format: 'DD-MM-YYYY',
	                      message: 'The date is not a valid'
	                  }
	              }
	          }
	  }
});

 
 </script>
 
 
 
 <script>
//Terms and conditions validation
 <c:choose>
 	<c:when test="${not empty header_info.tcList}">
 		<c:forEach var="tcVal" items="${header_info.tcList}">
 		$('#aind_form').bootstrapValidator('addField', 'tc_slno_${tcVal.sl}', {
 			 validators: {
 		       numeric: {
 		           message: 'Enter number'
 		       }
 		   }
 		});
 		</c:forEach>
 		</c:when>
 		
 		<c:otherwise>
 		
 		$('#aind_form').bootstrapValidator('addField', 'tc_slno_1', {
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
			
			
		  	$('#indent_type').listIndentType({
				 url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetIndentType&create_new=1",
				 nameField : '#indent_type',
				 formId: '#aind_form',
				 validateId: 'indent_type'
			}) 
			 
			
			

			  <c:forEach var="detId" items="${det_id_list}">
			
			$('#party_name_S_${detId}').listCustomer({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=1",
				  nameField :'#party_name_S_${detId}',
				  idField:'#party_id_S_${detId}',
				  formId:'#aind_form',
				  validateId:'party_name_S_${detId}'
			  });
			
		
			

			$('#item_name_S_${detId}').listMaterial({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=1",
				  nameField :'#item_name_S_${detId}',
				  idField:'#item_id_S_${detId}',
				  dependIdField:'#group_id_S_${detId}',
				  formId:'#aind_form',
				  validateId:'item_name_S_${detId}'
			  });


 			 $('#group_name_S_${detId}').listGroup({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup&create_new=1",
				  nameField :'#group_name_S_${detId}',
				  idField:'#group_id_S_${detId}'
			  });
 			 
			 /*  $('#item_name_S_${detId}').listMaterial({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterialFG&create_new=1",
				  nameField :'#item_name_S_${detId}',
				  idField:'#item_id_S_${detId}',
				  formId:'#aind_form',
				  validateId:'item_name_S_+${detId}'
			  });  */
			  
			  $('#variant_name_S_${detId}').listColor({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
				  nameField :'#variant_name_S_${detId}',
				  idField:'#variant_id_S_${detId}',
				  formId:'#aind_form',
				 validateId:'variant_name_S_${detId}'
			  });
			  
			  $('#size_range_S_${detId}').listSizeRange({      
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
				  nameField :'#size_range_S_${detId}',
				  idField:'#size_range_id_S_${detId}',
				 formId:'#aind_form',
				  validateId:'size_range_S_${detId}'
			  });
			  
			  
			  
			  $('#uom_S_${detId}').listUOM({      
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=1",
				  nameField :'#uom_S_${detId}',
				  idField:'#uom_id_S_${detId}',
				 formId:'#aind_form',
				  validateId:'uom_S_${detId}'
			  });
			  
			  
			  
			  
			  </c:forEach> 
			  
			  
			  $('#uom_0').listUOM({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=1",
				  nameField :'#uom_0',
				  idField:'#uom_id_0',
				  formId:'#aind_form',
				  validateId:'uom_0'
			  });
			  
			  
			  $('#size_range_0').listSizeRange({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
				  nameField :'#size_range_0',
				  idField:'#size_range_id_0',
				  formId:'#aind_form',
				  validateId:'size_range_0'
			  });
			  
			  
			  $('#item_name_0').listMaterial({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=1",
				  nameField :'#item_name_0',
				  idField:'#item_id_0',
				  dependIdField:'#group_id_0',
				  formId:'#aind_form',
				  validateId:'item_name_0'
			  }); 




			  $('#group_name_0').listCustomer({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup&create_new=1",
				  nameField :'#group_name_0',
				  idField:'#group_id_0',
			  });
			  

			  
			  $('#variant_name_0').listColor({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=1",
				  nameField :'#variant_name_0',
				  idField:'#variant_id_0',
				  formId:'#aind_form',
				  validateId:'variant_name_0'
			  });
			  $('#party_name_0').listCustomer({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=1",
				  nameField :'#party_name_0',
				  idField:'#party_id_0',
				  formId:'#aind_form',
				  validateId:'party_name_0'
			  });
			
			  
			
			 
		 });
		   
		
}(window.jQuery);	
 

 
 </script>
 
 
 
 
 
 
 
 

 
 

	</form>
	
	       <jsp:include page="../common/SelectTrNoList.jsp">
			<jsp:param value="Select Sales Order No" name="title"/>
			</jsp:include>
			
	<jsp:include page="../masters/AddMaterialFromPage.jsp" />
		<jsp:include page="../masters/AddCustomerFromPage.jsp" />
	<jsp:include page="../masters/AddUOMFromPage.jsp" />
	<jsp:include page="../common/AutoNoGeneration.jsp" >
		<jsp:param value="Indent" name="auto_modal_label"/>
		<jsp:param value="indent_no" name="auto_header_no_column"/>
		<jsp:param value="prefix" name="page_prefix_id"/>
		<jsp:param value="indent_no" name="page_auton_no_id"/>
		<jsp:param value="indent_id" name="auto_header_id_column"/>
		<jsp:param value="indent" name="auto_header_table_name"/>
		<jsp:param value="set_autono_indent" name="auto_autono_table_name"/>
	</jsp:include>
	
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>	
</body>
</html>