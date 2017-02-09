
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

  <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title><fmt:message bundle="${bundle}"   key="Title.Title"/></title>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/BOM.js"></script>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/common/shortcut.js"></script>
<jsp:include page="../common/Header.jsp"/>

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
<!-- <style type="text/css">
#header label {
    padding: 3px 0px;
}
</style> -->
</head>
<jsp:include page="../common/ValidateUser.jsp"/>

<body >
<form action=""  role="form" id="validate-form" method="post"  class="trans-form" autocomplete="off">



<div class="wrapper">


			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Sales.Transactions.SalesOrder" name="screen_name" />
			</jsp:include>

			<div class="page-wrapper">
				<div class="page-inner">

					<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >BOM</h3>
							</div>
						</div>
					</div>
					
					
					<div class='row'>
					
							<div class='col-sm-3'>
						
							<c:forEach items="${dynamicFieldsListBOMHeader}" var="dynamicHeaderfield">
								<c:if test="${dynamicHeaderfield.alignment=='L'}">
								
									<%@ include file="../masters/AddBOMHeaderDynamic.jsp" %> 
								
								</c:if>
							</c:forEach>
						
							</div>
						
							<div class='col-sm-3'>
							
							<c:forEach items="${dynamicFieldsListBOMHeader}" var="dynamicHeaderfield">
								<c:if test="${dynamicHeaderfield.alignment=='M'}">
								
									<%@ include file="../masters/AddBOMHeaderDynamic.jsp" %> 
								
								</c:if>
							</c:forEach>
							
							</div>
						
							<div class='col-sm-3'>
							
							<c:forEach items="${dynamicFieldsListBOMHeader}" var="dynamicHeaderfield">
								<c:if test="${dynamicHeaderfield.alignment=='R'}">
								
									<%@ include file="../masters/AddBOMHeaderDynamic.jsp" %> 
								
								</c:if>
							</c:forEach>
							
							</div>
							
					<div class='col-sm-3'  >
					<div class='col-sm-12'  >
					<c:choose>
					<c:when test="${header_info.primaryImageUrl ne null && header_info.primaryImageUrl ne '' }">
					<img src="${bomAttachPath}${header_info.primaryImageUrl}" width="200px" />
					</c:when>
					<c:otherwise>
					<img src="images/default-image.png" width="200px" />
					</c:otherwise>
					</c:choose>
					</div>
					</div>
					
					</div>
			
    
    




       

		
<!--  --------------------------------------------------Header Part End-------------------------------------------------------------------------------------------- -->
       
       <input type="hidden" name="data_table_width" id="data_table_width" value="${header_info.detTableWidth}"/>
       	 <%@ include file="../masters/AddBOMDetailDynamic.jsp" %> 
       
       
    
              
              <div class="row row-no-margin trans-footer">             
<div class="tax-cal col-sm-6 col-md-push-6">

</div>


<div class="col-sm-6 col-md-pull-6">

<%-- <div class="row row-no-margin">  
 <div class='col-sm-6'  >
<div class='form-group'>
<label >Message displayed in BOM</label>
<textarea class="form-control" rows="6"  id="bom_msg" name="bom_msg" maxlength="250"  placeholder="Enter BOM Message">${header_info.remarks}</textarea>
</div>            
</div>
</div> --%>
<%-- 
<div class="row row-no-margin">
<div class='col-sm-6'  >
<div class='form-group'>
<label >Memo for internal use</label>
<textarea class="form-control" rows="6"  id="internal_memo" name="internal_memo" maxlength="250"  placeholder="Enter Internal Memo" >${header_info.internalMemo}</textarea>
</div>            
</div>
</div> --%>
  
</div>  




</div>

<c:if test="${view_mode ne 'v' }">
<div class='row'>
 <div class="col-sm-12">
  <div class="col-sm-12">
<button class="btn btn-primary fileinput-button" onclick="addAttachment()" > <i class="glyphicon glyphicon-plus"></i> <span>Add files...</span></button>
<img id="ajax-loader" src="images/ajax-loader.gif" class="ajax-loader">
</div>
</div>
</div>
</c:if>


				
					
					<div class='row row-no-margin attach-area' >
					<div class="col-sm-12">
					<div class="col-sm-12">

							<c:set var="idx" value="1" />
							<c:forEach var="filename" items="${attached_files}">
								
								
								 
								 <div class=" col-sm-4 notice notice-info" id="attach_file${idx}" >
								<%--  <c:if test="${filename.urlType=='png' || filename.urlType=='jpg' || filename.urlType=='jpeg' || filename.urlType=='gif' || filename.urlType=='bmp' }">
								 <div style="    width: 10%;float: left;height: auto;overflow-y: visible;"><a target="_blank" href="${enquiryAttachPath}${fileNV}" title="view"><img src="${enquiryAttachPath}${fileNV}" width="100%" /></a></div>
								 </c:if> --%>
								 <c:if test="${view_mode ne 'v' }">
								 <div class="attach-delete">
        							<a class="attach-link-delete" href="#" name="del_attach${idx}" id="del_attach${idx}" onclick="deleteBOMAttachment('${idx}','${filename.bomId}','${filename.url}')">
											<span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
								</div>
								</c:if>
								<div >
											<a class="attach-link" target="_blank" href="${bomAttachPath}${filename.url}">${filename.fileName}</a>
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
						<table id="attachments" class="attach-table"
							style="border-collapse: collapse;">

						</table>
						</div>
						</div>
					</div>
					


					<!-- Remember Followers <input type="checkbox" name="remember_followers" id="remember_followers" /> -->
  
              

	
	
	

                  
              
                <div class='row '>
    <div class='col-sm-12 col-xs-12'>
    <div class='pull-right centered save-view'>   
                   
                     <c:choose>
                <c:when test="${view_mode ne 'v' }">
                   	<c:if test="${bom_rights.addPermission==1 or bom_rights.editPermission==1 }">
                   	<div class="btn-group dropup">
                  	
                <button name="save" id="save"  onclick="validateBOM('${bom.headerMode}','1',0)"  class="btn btn-success ladda-button"  data-style="expand-right">
										     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
										    </button>
                <button    class="btn btn-success dropdown-toggle" data-toggle="dropdown" ><span class="caret"></span></button>
                <ul class="dropdown-menu">
        <%--     <li><a href="javascript:validateBOM('${bom.headerMode}','2')" id="save_send" >Save & Send</a></li> --%>
            <c:if test="${bom_rights.addPermission==1}" >
             <li><a href="javascript:validateBOM('${bom.headerMode}','3',0)" id="save_new">Save & New</a></li>
           	</c:if>
           	<c:if test="${bom_rights.viewPermission==1 }">
           	       <!--  <li class="divider"></li> -->
                  <li><a  href="javascript:validateBOM('${bom.headerMode}','4',0)" id="save_close">Save & Close</a></li>
           	</c:if>
          
                </ul>
              </div>
                   	</c:if>
                  	
              
              <div class="btn-group dropup">
      <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-circle-arrow-up"></i>&nbsp;Action&nbsp;<span class="caret"></span></button>
      <ul class="dropdown-menu dropdown-menu-right">
      <%-- 	<c:if test="${bom_rights.printPermission==1 }">
      	  <li><a  href="javascript:exportSalesEnquiryToPDF(${header_info.bomId});" ><fmt:message bundle="${bundle}"   key="Button.Print"/></a></li>
      	</c:if>
      	<c:if test="${sq_rights.addPermission==1 }">
      	 <li><a  href="javascript:postSalesQuotation(${header_info.bomId},1)" >Prepare Sales Quotation</a></li>
      	</c:if> --%>
       	<c:if test="${bom_rights.deletePermission==1 }">
          <li><a href="javascript:deleteBOM(1,${header_info.bomId},1)" ><fmt:message bundle="${bundle}"   key="Button.Delete"/></a></li>
       	</c:if>
         
      </ul>
    </div>
    
   
                 				             
  	<c:if test="${bom_rights.viewPermission==1 }">
  	 <button type="button" class="btn btn-primary"  onclick="showBOM()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>
  	</c:if>
                  	
            </c:when>  
       <c:otherwise>
     <c:if test="${bom_rights.viewPermission==1 }">
  	 <button type="button" class="btn btn-primary"  onclick="showBOM()"><i class="glyphicon glyphicon-chevron-left"></i><span>&nbsp;Back</span></button>
  	</c:if>
                </c:otherwise>
                
                </c:choose>     				             
            </div>
    </div>
            </div>
                
              
    
     
            	         	 
    
    <div class='row' style="margin-top: 10px;" >        	         	 
           
    </div>
    
    </div>
    </div>
    </div>
    
<jsp:include page="../common/Footer.jsp"/>

<input type="hidden" name="servlet_name" id="servlet_name" />
 <input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
 <input type="hidden" name="pageno" id="pageno" />
 <input type="hidden" name="request_type" id="request_type" />
<input type="hidden" name="head_mode" id="head_mode" value="${bom.headerMode}" /> 
<input type="hidden" name="det_mode" id="det_mode"  value="${bom.detMode}"/>  
<input type="hidden" name="bom_det_id" id="bom_det_id" value="${bom_det_id}" />
<input type="hidden" name="bom_id" id="bom_id" value="${header_info.bomId}" />
<input type="hidden" name="edited_ids" id="edited_ids" value=""/>
<input type="hidden" name="dynedited_ids" id="dynedited_ids" value=""/>
<input type="hidden" name="edited_mode" id="edited_mode" value=""/>
 <input type="hidden" name="invoke_method" id="invoke_method" />
<input type="hidden" name="invoke_class" id="invoke_class" />
<input type="hidden" name="selected_users" id="selected_users"/>
<input type="hidden" name="selected_userremarks" id="selected_userremarks"/>
<input type="hidden" name="selected_remarks" id="selected_remarks"/>
<input type="hidden" name="material_id" id="material_id"/>
<input type="hidden" name="new_det_mode" id="new_det_mode"/>
<input type="hidden" name="prefix_name" id="prefix_name"/>
<input type="hidden" name="page" id="page"/>

<input type="hidden" name="def_cur_id" id="def_cur_id" value="${user_info.currencyId}"/>
<input type="hidden" name="def_cur_name" id="def_cur_name" value="${user_info.currencyName}"/>

<input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
<input type="hidden" id="add_new_mat" data-toggle="modal" data-target="#materialModal"   />
<input type="hidden" id="add_new_group" data-toggle="modal" data-target="#groupMasterModal"   /><!-- onclick="loadDataToGroupMasterModal()" -->
<input type="hidden" id="add_new_customer" data-toggle="modal" data-target="#customerMasterModal"   /> <!-- onclick="loadDataToCustomerMasterModal()" -->
<input type="hidden" id="add_new_uom" data-toggle="modal" data-target="#uomMasterModal"  />
<input type="hidden" id="add_new_account" data-toggle="modal" data-target="#accountModal" /><!-- onclick="loadDataToAccountModal()" -->
<input type="hidden" id="add_new_currency" data-toggle="modal" data-target="#currencyMasterModal"   />
<input type="hidden" id="add_new_accountgroup" data-toggle="modal" data-target="#accountGroupModal"  onclick="loadDataToGroupModal();"  />
<input type="hidden" id="add_new_branch" data-toggle="modal" data-target="#branchMasterModal"   />
<input type="hidden" id="add_new_variant" data-toggle="modal" data-target="#VariantModal"   />
<input type="hidden" id="trno_quotation" data-toggle="modal" data-target="#trNoModal"   />

 <input type="hidden" id="alt_mat_modal" data-toggle="modal" data-target="#alternateMaterialModal"  onclick="loadDataToAltMaterialModal()"  />
 <input type="hidden" id="add_bom_comp" data-toggle="modal" data-target="#addBOCompModal"    onclick="loadDataToBOMCompModal()" />


<input type="hidden" id="autono_table_name" name="autono_table_name" value="set_autono_sales_quotation"  />
<input type="hidden" name="tr_tag" id="tr_tag"  />
<input type="hidden" name="view_mode" id="view_mode" value="${view_mode}" />
     
<input type="hidden" name="row_count" id="row_count" value="${row_count}"/>
<input type="hidden" name="save_type" id="save_type" value="${save_type}" />
<input type="hidden" name="det_mat_id" id="det_mat_id"  value="${det_mat_id}" />
<input type="hidden" name="det_comp_id" id="det_comp_id"  value="${det_comp_id}" />

       
<input type="submit" name="valid_det" id="valid_det" style="display:none;"/>

<c:set var="det_ids" value=""/>
<c:forEach var="detId" items="${det_id_list}">
<c:set var="det_ids" value="${det_ids}${detId},"/>
</c:forEach>
<input type="hidden" name="det_ids" id="det_ids" value="${det_ids}"/>

  
       
     
  
   
     
<script language="javascript">

$(function(){
	$('[data-toggle="tooltip"]').tooltip();
	});




</script>
<script>
$('#validate-form').bootstrapValidator({
	
	  message: 'This value is not valid',
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	/*   excluded: ':disabled', */
	  fields: {
		  bom_no: {
	            validators: {
	                notEmpty: {
	                    message: 'This field is required'
	          
	            }
	          }
	        },  
	 /*        product: {
	            validators: {
	                notEmpty: {
	                    message: 'This field is required'
	                },
	                callback: {
	                    message: 'This field is required',
	                    callback: function(value, validator, $field) {
	                    	if ($("#product_id").val()==0) {
	                            return {
	                                valid: false,
	                                message: 'This field is required'
	                            };
	                        }
	                    	return true;
	                    }
	                }
	          
	            }
	        }, */
          tr_date: {
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
        
          material_0: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if ($("#material_id_0").val()==0) {
                              return {
                                  valid: false,
                                  message: 'This field is required'
                              };
                          }
                      	else if ($("#component_id_0").val()>0 && $("#material_id_0").val()==$("#component_id_0").val()) {
                            return {
                                valid: false,
                                message: 'Material,component should not equal'
                            };
                        }
                      	return true;
                      }
                  }
              }
          }, component_0: {
              validators: {
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                         if ($("#material_id_0").val()>0 && $("#material_id_0").val()==$("#component_id_0").val()) {
                                  return {
                                      valid: false,
                                      message: 'Material,component should not equal'
                                  };
                              }
                      	return true;
                      }
                  }
              }
          },
     /*      operation_0: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if ($("#operation_id_0").val()==0) {
                              return {
                                  valid: false,
                                  message: 'This field is required'
                              };
                          }
                      	return true;
                      }
                  }
              }
          },  */ 
          required_qty_0: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  numeric: {
                      message: 'This field must be a number'
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
 
        
     
          <c:forEach var="detId" items="${det_id_list}">
          material_S_${detId}: {
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
                      	else   if ($("#component_id_S_${detId}").val()>0 && $("#item_id_S_${detId}").val()==$("#component_id_S_${detId}").val()) {
                            return {
                                valid: false,
                                message: 'Material,component should not equal'
                            };
                        }
                      	return true;
                      }
                  }
          
              }
          }, component_S_${detId}: {
              validators: {
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                         if ($("#component_id_S_${detId}").val()>0 && $("#item_id_S_${detId}").val()==$("#component_id_S_${detId}").val()) {
                                  return {
                                      valid: false,
                                      message: 'Material,component should not equal'
                                  };
                              }
                      	return true;
                      }
                  }
              }
          },
          required_qty_S_${detId}: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          },
       /*    operation_S_${detId}: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if ($("#operation_id_S_${detId}").val()==0) {
                              return {
                                  valid: false,
                                  message: 'This field is required'
                              };
                          }
                      	return true;
                      }
                  }
          
              }
          }, */
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
          quantity_S_${detId}: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  },
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          },
          required_qty_S_${detId}: {
              validators: {
                  notEmpty: {
                	   message: 'This field is required'
                  }
              }
          },
     
          </c:forEach>
          required_qty_0: {
              validators: {
            	  notEmpty: {
                      message: 'This field is required'
                  },
                  numeric: {
                      message: 'This field must be a number'
                  }
              }
          }
          
	  }
	});

 
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
	
		
		
		
		  $('#customer').listCustomer({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetCustomer&create_new=0",
			  nameField :'#customer',
			  idField:'#customer_id',
			  formId:'#validate-form',
			  validateId:'customer'
		  });
		  
		  $('#product').listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetFGMaterial&create_new=0",
			  nameField :'#product',
			  idField:'#product_id'
		  }); 
	
		  $('#parent_bom_no').listBOM({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetBOM&create_new=0",
			  nameField :'#parent_bom_no',
			  idField:'#parent_bom_id',
			  formId:'#validate-form',
			  validateId:'parent_bom_no'
		  });
	
		  $('#material_0').listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=0",
			  nameField :'#material_0',
			  idField:'#material_id_0',
			  dependIdField:'#group_id_0',
			  formId:'#validate-form',
			  validateId:'material_0'
		  });
		  
		  $('#group_name_0').listGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup&create_new=0",
			  nameField :'#group_name_0',
			  idField:'#group_id_0'
		  });
		  
		  $('#component_0').listMaterialComponent({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterialComponent&create_new=0",
			  nameField :'#component_0',
			  idField:'#component_id_0',
			  formId:'#validate-form',
			  validateId:'component_0'
		  });
		  
		  $('#uom_0').listUOM({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=0",
			  nameField :'#uom_0',
			  idField:'#uom_id_0',
			  formId:'#validate-form',
			  validateId:'uom_0'
		  });
		  
		  $('#variant_0').listVariant({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=0",
			  nameField :'#variant_0',
			  idField:'#variant_id_0'
			  //formId:'#validate-form',
			  //validateId:'variant_0'
		  });
		  
		  $('#supplier_0').listSupplier({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSupplier&create_new=0",
			  nameField :'#supplier_0',
			  idField:'#supplier_id_0',
			  formId:'#validate-form',
			  validateId:'supplier_0'
		 });
		  
	 	  $('#operation_0').listOperation({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetOperation&create_new=0",
			  nameField :'#operation_0',
			  idField:'#operation_id_0'
		 }); 
		  
		  $('#size_schedule_0').listSizeShedule({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeShedule&create_new=0",
			  nameField :'#size_schedule_0',
			  idField:'#size_schedule_id_0'
		 });
		  
		  
		  <c:forEach var="detId" items="${det_id_list}">
		  $('#material_S_'+${detId}).listMaterial({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterial&create_new=0",
			  nameField :'#material_S_'+${detId},
			  idField:'#item_id_S_'+${detId},
			  dependIdField:'#group_id_S_'+${detId},
			  formId:'#validate-form',
			  validateId:'material_S_'+${detId}
		  });
		  
		  $('#component_S_'+${detId}).listMaterialComponent({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetMaterialComponent&create_new=0",
			  nameField :'#component_S_'+${detId},
			  idField:'#component_id_S_'+${detId},
			  formId:'#validate-form',
			  validateId:'component_S_'+${detId}
		  });
		  
	 	  
		  $('#operation_S_'+${detId}).listOperation({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetOperation&create_new=0",
			  nameField :'#operation_S_'+${detId},
			  idField:'#operation_id_S_'+${detId}
		 }); 
		  $('#uom_S_'+${detId}).listUOM({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM&create_new=0",
			  nameField :'#uom_S_'+${detId},
			  idField:'#uom_id_S_'+${detId},
			  formId:'#validate-form',
			  validateId:'uom_S_'+${detId}
		  });
		  
		  $('#supplier_S_'+${detId}).listSupplier({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSupplier&create_new=0",
			  nameField :'#supplier_S_'+${detId},
			  idField:'#supplier_id_S_'+${detId},
			  formId:'#validate-form',
			  validateId:'supplier_S_'+${detId}
		 });
		  
		  $('#variant_S_'+${detId}).listVariant({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetVariant&create_new=0",
			  nameField :'#variant_S_'+${detId},
			  idField:'#variant_id_S_'+${detId}
			  //formId:'#validate-form',
			  //validateId:'variant_0'
		  });
		  
		  
		  $('#size_schedule_S_'+${detId}).listSizeShedule({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeShedule&create_new=0",
			  nameField :'#size_schedule_S_'+${detId},
			  idField:'#size_schedule_id_S_'+${detId}
		 });
		  
		  
		  $('#group_name_S_'+${detId}).listGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup&create_new=0",
			  nameField :'#group_name_S_'+${detId},
			  idField:'#group_id_S_'+${detId}
		  });
		  
		  
		  </c:forEach>
		  
		  
		
		  
	 });
}(window.jQuery);
	 

</script>	
 <script type="text/javascript">
/*  $(function () {
     $('#grid_table input[type=text]').focus(function () {
         $(this).parent().parent().css('width', '30%');
     });
     $('#grid_table input[type=text]').blur(function () {
         $(this).parent().parent().css('width', '0%');
     });
     
     $('#grid_table textarea').focus(function () {
         $(this).parent().parent().css('width', '30%');
     });
     $('#grid_table textarea').blur(function () {
         $(this).parent().parent().css('width', '0%');
     });
     
 
 }); */
 
 
//	self.find('.dropdown-menu-form').on('click', function(e){e.stopPropagation()});
 
 </script>

      </form>

      
       
            <jsp:include page="../masters/AddAlternateMaterial.jsp" />
                <jsp:include page="../masters/AddBOMComponents.jsp" />
            
              <script>    
   <c:if test="${save_type eq 'altMat'}" >

getAltMaterial('${bom.headerMode}','${bom_det_id}');

</c:if>    

<c:if test="${save_type eq 'bomComp'}" >

getBOMComponents('${bom.headerMode}','${bom_det_id}');

</c:if>

  </script>  
  
  <script>
     shortcut.add("alt+a", function() {
    	validateBOM('${bom.headerMode}','1',0);
    });  
 
</script>
   
  
    <jsp:include page="../common/SelectTrNoList.jsp">
			<jsp:param value="Select BOM No" name="title"/>
			</jsp:include>
			
			     
      <jsp:include page="../masters/AddGroupFromPage.jsp" />
        <jsp:include page="../masters/AddMaterialFromPage.jsp" />
          <jsp:include page="../masters/AddCustomerFromPage.jsp" />
          <%-- <jsp:include page="../sales/AutoGenerationNoForSalesEnquiry.jsp" /> --%>
            <jsp:include page="../masters/AddUOMFromPage.jsp" />
             <jsp:include page="../masters/AddCurrencyFromPage.jsp" />
            
            
            
			
             <script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script> 
      </body>
      </html>