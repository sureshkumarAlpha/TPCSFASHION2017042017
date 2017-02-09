
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/masters/masters.js"></script>


<!-- <style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style> -->
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="validate-form" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>

<div id="page-wrapper">
<div id="page-inner">
        <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h3 class="add-page-header">Add Tax Group</h3>
                </div>
		</div>
        <div class='row'>
            <div class='col-sm-3'>    
                <div class='form-group'>
                    <label for="customer_code">Tax Group</label>	
                     <div class="required-field-block">	   
                    <input class="form-control" id="tax_group" name="tax_group" size="30" type="text" value="${tax_group}" placeholder="Enter Tax Group"/>
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
            
          <%--   <div class='col-sm-3'>
                <div class='form-group'>
                    <label for="company_name">Tax</label>  
                    <div class="required-field-block">	                  
                    <select name="tax" id="tax" class="form-control" >
						<option value="-1">&lt;--Select Tax--&gt;</option>
						<c:forEach var="objTax" items="${taxList}">
							<c:choose>
								<c:when test="${objTax.taxId==taxG_info.taxId}">
									<option value="${objTax.taxId}" selected="selected">${objTax.taxName}</option>
								</c:when>
								<c:otherwise>
									<option value="${objTax.taxId}">${objTax.taxName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>                    
               <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div> --%>
        </div>	
        
 <%--        <div class='row'>             
            <div class='col-sm-3'>
                <div class='form-group'>   
                	<label for="first_name">Tax Percentage</label>                       
                    <input class="form-control" id="tax_percentage" name="tax_percentage" value="${taxG_info.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
                </div>
            </div>   
            <div class='col-sm-3'>
                <div class='form-group'>   
                	<label for="last_name">Display Order</label>                    
                    <input class="form-control" id="display_order" name="display_order" value="${taxG_info.displayOrder}" size="30" type="text" placeholder="Enter Display Order"/>                   
                </div>
            </div>           
        </div> --%>
        
        
        <div class='row'>             
				            <div class="table-responsive" style="padding-left: 15px;">
										<table class="table table-bordered table-condensed"  id="addrow_table">
								
											<tbody>
											<c:set var="k" value="${0}"/>
											<c:forEach var="obj" items="${taxGroupInfo}">
												<tr class="datarow">
														<td>
												<div class="required-field-block">	                  
								                    <select name="tax_id_${k}" id="tax_id_${k}" class="form-control" >
														<option value="-1">&lt;--Select Tax--&gt;</option>
														<c:forEach var="objTax" items="${taxList}">
															<c:choose>
																<c:when test="${objTax.taxId==obj.taxId}">
																	<option value="${objTax.taxId}" selected="selected">${objTax.taxName}</option>
																</c:when>
																<c:otherwise>
																	<option value="${objTax.taxId}">${objTax.taxName}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>                    
								               <div class="required-icon">
											<div class="text">*</div>
										     </div>
										     </div>
													</td>
													<td> <input class="form-control" id="tax_percentage_${k}" name="tax_percentage_${k}" value="${obj.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/></td>
												<td><input class="form-control" id="display_order_${k}" name="display_order_${k}" value="${obj.displayOrder}" size="30" type="text" placeholder="Enter Display Order"/>  </td>
												<td style="vertical-align: middle;"><a href="javascript:deleteTaxRow(document.getElementById('tax_id_${k}').value,${tax_group_id})" data-toggle="tooltip" title="Delete"><span class="glyphicon glyphicon-trash icon-delete"></span></a></td>
												</tr>
												<c:set var="k" value="${k+1}"/>
											</c:forEach>
											<c:if test="${mode eq 'n'}">
											
											
											<tr class="datarow">
												<td>
												<div class="required-field-block">	                  
								                    <select name="tax_id_0" id="tax_id_0" class="form-control" >
														<option value="-1">&lt;--Select Tax--&gt;</option>
														<c:forEach var="objTax" items="${taxList}">
															<c:choose>
																<c:when test="${objTax.taxId==taxG_info.taxId}">
																	<option value="${objTax.taxId}" selected="selected">${objTax.taxName}</option>
																</c:when>
																<c:otherwise>
																	<option value="${objTax.taxId}">${objTax.taxName}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>                    
								               <div class="required-icon">
											<div class="text">*</div>
										     </div>
										     </div>
													</td>
													<td>
													<div class="required-field-block">	        
													<input class="form-control" id="tax_percentage_0" name="tax_percentage_0" value="${taxG_info.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
													  <div class="required-icon">
													<div class="text">*</div>
												     </div>
												     </div>
													</td>
													<td><input class="form-control" id="display_order_0" name="display_order_0" value="${taxG_info.displayOrder}" size="30" type="text" placeholder="Enter Display Order"/>  </td>
													<td>&nbsp;</td>
												</tr>
												<tr class="datarow">
												<td>
												<div class="required-field-block">	                  
								                    <select name="tax_id_1" id="tax_id_1" class="form-control" >
														<option value="-1">&lt;--Select Tax--&gt;</option>
														<c:forEach var="objTax" items="${taxList}">
															<c:choose>
																<c:when test="${objTax.taxId==taxG_info.taxId}">
																	<option value="${objTax.taxId}" selected="selected">${objTax.taxName}</option>
																</c:when>
																<c:otherwise>
																	<option value="${objTax.taxId}">${objTax.taxName}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>                    
								               <div class="required-icon">
											<div class="text">*</div>
										     </div>
										     </div>
													</td>
														<td>
													<div class="required-field-block">	        
													<input class="form-control" id="tax_percentage_1" name="tax_percentage_1" value="${taxG_info.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
													  <div class="required-icon">
													<div class="text">*</div>
												     </div>
												     </div>
													</td>
													<td><input class="form-control" id="display_order_1" name="display_order_1" value="${taxG_info.displayOrder}" size="30" type="text" placeholder="Enter Display Order"/>  </td>
													<td>&nbsp;</td>
												</tr>
												<tr class="datarow">
												<td>
												<div class="required-field-block">	                  
								                    <select name="tax_id_2" id="tax_id_2" class="form-control" >
														<option value="-1">&lt;--Select Tax--&gt;</option>
														<c:forEach var="objTax" items="${taxList}">
															<c:choose>
																<c:when test="${objTax.taxId==taxG_info.taxId}">
																	<option value="${objTax.taxId}" selected="selected">${objTax.taxName}</option>
																</c:when>
																<c:otherwise>
																	<option value="${objTax.taxId}">${objTax.taxName}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>                    
								               <div class="required-icon">
											<div class="text">*</div>
										     </div>
										     </div>
													</td>
															<td>
													<div class="required-field-block">	        
													<input class="form-control" id="tax_percentage_2" name="tax_percentage_2" value="${taxG_info.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
													  <div class="required-icon">
													<div class="text">*</div>
												     </div>
												     </div>
													</td>
													<td><input class="form-control" id="display_order_2" name="display_order_2" value="${taxG_info.displayOrder}" size="30" type="text" placeholder="Enter Display Order"/>  </td>
													<td>&nbsp;</td>
												</tr>
												<tr class="datarow">
												<td>
												<div class="required-field-block">	                  
								                    <select name="tax_id_3" id="tax_id_3" class="form-control" >
														<option value="-1">&lt;--Select Tax--&gt;</option>
														<c:forEach var="objTax" items="${taxList}">
															<c:choose>
																<c:when test="${objTax.taxId==taxG_info.taxId}">
																	<option value="${objTax.taxId}" selected="selected">${objTax.taxName}</option>
																</c:when>
																<c:otherwise>
																	<option value="${objTax.taxId}">${objTax.taxName}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>                    
								               <div class="required-icon">
											<div class="text">*</div>
										     </div>
										     </div>
													</td>
																	<td>
													<div class="required-field-block">	        
													<input class="form-control" id="tax_percentage_3" name="tax_percentage_3" value="${taxG_info.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
													  <div class="required-icon">
													<div class="text">*</div>
												     </div>
												     </div>
													</td>
													<td><input class="form-control" id="display_order_3" name="display_order_3" value="${taxG_info.displayOrder}" size="30" type="text" placeholder="Enter Display Order"/>  </td>
													<td>&nbsp;</td>
												</tr>
											</c:if>
											
											</tbody>
											
										
														
														
										</table>
														<div>
														<span class="glyphicon glyphicon-plus" style="font-size: 12px; color: #1864ac;"></span>
														<a href="javascript:addAnotherLineForTaxGroup()" style="padding-left: 5px; color: #1864ac;">Add Another Line</a>
														</div>
									</div>   
				        </div>
        <div class='row'>       
            <div class='col-sm-12'>
            </div>
        </div>
        <div class='row pull-left'>        	         	 
            <div class='col-sm-12 col-xs-12 centered'>               
                  	<div class="btn-group dropup">
                <c:choose>
					<c:when test="${mode eq 'e'}">
					<button name="update" id="update"  onclick="saveTaxGroup('${mode}','1')"  class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Update
			    </button>
					</c:when>
					<c:otherwise>
					<button name="save" id="save"  onclick="saveTaxGroup('n','1')"  class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button>
					</c:otherwise>
				</c:choose>
                
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                
                <c:choose>
					<c:when test="${mode eq 'e'}">
						<li><a href="javascript:saveTaxGroup('${mode}','2');">Update & New</a></li>
						<li class="divider"></li>
						<li><a href="javascript:saveTaxGroup('${mode}','3');">Update & Close</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:saveTaxGroup('n','2');">Save & New</a></li>
						<li class="divider"></li>
						<li><a href="javascript:saveTaxGroup('n','3');">Save & Close</a></li>
					</c:otherwise>
				</c:choose>
            
                </ul>
              </div>
                   <button type="button" class="btn btn-danger" onclick="showTaxGroup()">Cancel</button>						             
            </div>            
    </div>  
     <div class='row'>       
        </div>
   </div>
  </div> 
  		</div>

		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" /> 
		<input type="hidden" name="pageno" id="pageno" /> 
		<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="tax_group_id" id="tax_group_id" value="${tax_group_id}" />
		<input type="hidden" name="tax_id" id="tax_id"  />
		<input type="hidden" name="save_type" id="save_type" value="${save_type}" />
		<input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
		<input type="hidden" name="row_count" id="row_count" value="${row_count}"/>
<c:choose>
<c:when test="${mode eq 'n'}">
<input type="hidden" name="row_ids" id="row_ids" value="0,1,2,3,${row_ids }"/>
</c:when>
<c:otherwise>
<c:set var="a" value="0"/>
<c:set var="b" value=""/>
<c:set var="c" value=""/>
<c:forEach begin="1" end="${k}" varStatus="loop">

<c:set var="b" value="${a},"/> 
<c:set var="c" value="${c}${b}"/>
	<c:set var="a" value="${a+1}"/>
</c:forEach>
<input type="hidden" name="row_ids" id="row_ids" value="${c}${row_ids }"/>
</c:otherwise>
</c:choose>
 
 
<script type="text/javascript">


	
$('#validate-form').bootstrapValidator({
	//  live: 'disabled',
	  message: 'This value is not valid',
	  framework: 'bootstrap',
      err: {
          container: 'tooltip'
      },
      row: {
          selector: 'td'
      },
	  feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	  },
	  fields: {
	/* 	  tax_group: {
              validators: {
                  notEmpty: {
                      message: 'This field is required'
                  }
              }
          }, */
	    'tax_percentage_0': {
            validators: {
            	 notEmpty: {
                     message: 'This field is required'
                 },
                numeric: {
                    message: 'This field must be a number'
                }
            }
        },
        tax_percentage_1: {
            validators: {
            	 notEmpty: {
                     message: 'This field is required'
                 },
                numeric: {
                    message: 'This field must be a number'
                }
            }
        }
  /*         tax_id_0: {
            validators: {
                notEmpty: {
                    message: 'This field is required'
                },
                  callback: {
                      message: 'This field is required',
                      callback: function(value, validator, $field) {
                      	if (value< 1) {
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
        tax_percentage_0: {
            validators: {
                numeric: {
                    message: 'This field must be a number'
                }
            }
        },
        display_order_0: {
            validators: {
                numeric: {
                    message: 'This field must be a number'
                }
            }
        } */
          
	  }
	});
	
	
	
	 
</script>

 
	</form>
</body>
</html>
  
