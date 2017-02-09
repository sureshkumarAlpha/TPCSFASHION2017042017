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
	<form action="" id="validate-form" method="post" role="form" autocomplete="off">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>


<div class="top-bar">
 <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" >Tax Group</h2>
                </div>
           </div>
<div id="page-wrapper" class="add-top-wrapper"> 
   <div id="page-inner">   
                
<div class="row" >
  <div class='col-sm-3'>    
                <div class='form-group'>
                    <label for="tax_group">Tax Group</label>	
                     <div class="required-field-block">	   
                    <input class="form-control" id="tax_group" name="tax_group" size="30" type="text" maxlength="50" value="<c:out value="${tax_group}"/>" autofocus placeholder="Enter Tax Group"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div>
  	 <div class="col-sm-3">
			<div class="form-group">
  			<label>Status</label>
			<select class="form-control" id="tax_status" name="tax_status">
			<option value="1" <c:if test="${status eq 1 }"> selected </c:if> >Active</option>
			<option value="0"  <c:if test="${status eq 0 }"> selected </c:if> >Inactive</option>
			</select>
			</div>
	</div>
</div>

</div>
</div>
</div>
    
<div id="page-wrapper" style="margin-top: 0;"> 
        <div id="page-inner">   
        <!-- <div class="row">
               <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" >Tax Group</h2>
                </div>
		</div> -->
      
      <%--       <div class='col-sm-3'>    
                <div class='form-group'>
                    <label for="tax_group">Tax Group</label>	
                     <div class="required-field-block">	   
                    <input class="form-control" id="tax_group" name="tax_group" size="30" type="text" value="${tax_group}" placeholder="Enter Tax Group"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
                </div>
            </div> --%>
              <div class='row'>
         <div class='row'>  
        <lable id="req_err_msg" style="color: #a94442;margin-left: 440px;">
        </lable>
        </div>
        
        <div class='row row-no-margin'>
	        <div class="col-sm-6" id="tax_group_row_out">
	        	
	        	<div class="row row-no-margin" >
	        		<div class="col-sm-4" id="header"><label>Tax Group</label></div>
	        		<div class="col-sm-6" id="header"><label>Percentage</label></div>
	        		<!-- <div class="col-sm-3" id="header"><label>Display Order</label></div> -->
	        		<div class="col-sm-2" id="header"><label>&nbsp;</label></div>
	        	</div>
	        
	        	<c:set var="k" value="${0}"/>
	        	
	        	<c:forEach var="obj" items="${taxGroupInfo}">
		        	<div class="row row-no-margin linerow tax_group_row" id="tax_group_row_${k}">
	
		        		<div class="col-sm-4" >
			        		<div class="form-group">
				        		<div class="required-field-block">	 
							         
							          <select name="tax_id_${k}" id="tax_id_${k}" class="form-control" >
										<option value="-1">&lt;--Select Tax--&gt;</option>
										<c:forEach var="objTax" items="${taxList}">
											<c:choose>
												<c:when test="${objTax.taxId==obj.taxId}">
													<option value="${objTax.taxId}" selected="selected"><c:out value="${objTax.taxName}"/></option>
												</c:when>
												<c:otherwise>
													<option value="${objTax.taxId}"><c:out value="${objTax.taxName}"/></option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
									
						             <div class="required-icon">
										<div class="text">*</div>
								     </div>
							     </div>
				            </div>
		        		</div>
		        		<%-- <div class="col-sm-4">
								<div class="form-group">
								<div class="required-field-block">
								<input class="form-control" id="tax_name_${k}" name="tax_name_${k}" value="${objTax.taxName}"	size="30" type="text" placeholder="Enter Tax" />
								<input type="hidden" id="tax_id_${k}" name="tax_id_${k}" size="30" />
								<div class="required-icon">
								<div class="text">*</div>
								</div>
								</div>
								</div>
								</div> --%>
												
		        		<%-- <div class="col-sm-4" >
		        			<div class="form-group">
								<input class="form-control" id="tax_id_${k}" name="tax_id_${k}" value="${objTax.taxName}" size="30" type="text" placeholder="Enter Tax"/>
							</div>
						</div> --%>
		        		<div class="col-sm-6" >
		        			<div class="form-group">
								<input class="form-control" id="tax_percentage_${k}" name="tax_percentage_${k}" value="${obj.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
							</div>
						</div>
						<%-- <div class="col-sm-3" >
		        			<div class="form-group">
								<input class="form-control" id="tax_slno_${k}" name="tax_slno_${k}"  value="${obj.taxSlno}" size="30" type="text" placeholder="Enter Display Order"/>
							</div>
						</div> --%>
						<div class="col-sm-2 linerow-icon" >
							<a href="javascript:addAnotherLineForTaxGroup()" data-toggle="tooltip" title="Add" ><span class="glyphicon glyphicon-plus text-primary "></span></a>
							<a href="javascript:deleteTaxRow(document.getElementById('tax_id_${k}').value,${tax_group_id})" data-toggle="tooltip" title="Delete"><span class="glyphicon glyphicon-trash icon-delete"></span></a>
						</div>
		        	</div>
		        	<c:set var="k" value="${k+1}"/>
	        	</c:forEach>
	        	
	        	
	        	<c:if test="${mode eq 'n'}">
	        	<div class="row row-no-margin linerow tax_group_row" id="tax_group_row_0">
	
		        		<div class="col-sm-4" >
			        		<div class="form-group">
				        		<div class="required-field-block">	 
							          
							          <select name="tax_id_0" id="tax_id_0" class="form-control" >
											<option value="-1">&lt;--Select Tax--&gt;</option>
											<c:forEach var="objTax" items="${taxList}">
												<c:choose>
													<c:when test="${objTax.taxId==taxG_info.taxId}">
														<option value="${objTax.taxId}" selected="selected"><c:out value="${objTax.taxName}"/></option>
													</c:when>
													<c:otherwise>
														<option value="${objTax.taxId}"><c:out value="${objTax.taxName}"/></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>  
										
						             <div class="required-icon">
										<div class="text">*</div>
								     </div>
							     </div>
				            </div>
		        		</div>
		        	<%-- 	<div class="col-sm-4">
								<div class="form-group">
								<div class="required-field-block">
								<input class="form-control" id="tax_name_0" name="tax_name_0" value="${objTax.taxName}"	size="30" type="text" placeholder="Enter Tax" />
								<input type="hidden" id="tax_id_0" name="tax_id_0" size="30" />
								<div class="required-icon">
								<div class="text">*</div>
								</div>
								</div>
								</div>
								</div> --%>
		        		<%-- <div class="col-sm-4" >
		        			<div class="form-group">
								<input class="form-control" id="tax_id_0" name="tax_id_0" value="${objTax.taxName}" size="30" type="text" placeholder="Select Tax"/>
							</div>
						</div> --%>
		        		<div class="col-sm-6" >
		        			<div class="form-group">
								<input class="form-control" id="tax_percentage_0" name="tax_percentage_0" value="${taxG_info.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
							</div>
						</div>
						
						<!-- <div class="col-sm-3" >
		        			<div class="form-group">
								<input class="form-control" id="tax_slno_0" name="tax_slno_0" value="" size="30" type="text" placeholder="Enter Display Order"/>
							</div>
						</div> -->
						
                        <div class="col-sm-2" >&nbsp;</div>
		        	</div>
		        	
		        <!-- 	 2nd row -->
		        
		        <div class="row row-no-margin linerow tax_group_row" id="tax_group_row_1">
	
		        		<div class="col-sm-4" >
			        		<div class="form-group">
				        		<div class="required-field-block">	 
							          <select name="tax_id_1" id="tax_id_1" class="form-control" >
											<option value="-1">&lt;--Select Tax--&gt;</option>
											<c:forEach var="objTax" items="${taxList}">
												<c:choose>
													<c:when test="${objTax.taxId==taxG_info.taxId}">
														<option value="${objTax.taxId}" selected="selected"><c:out value="${objTax.taxName}"/></option>
													</c:when>
													<c:otherwise>
														<option value="${objTax.taxId}"><c:out value="${objTax.taxName}"/></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>  
						             <div class="required-icon">
										<div class="text">*</div>
								     </div>
							     </div>
				            </div>
		        		</div>
		        		<%-- <div class="col-sm-4">
								<div class="form-group">
								<div class="required-field-block">
								<input class="form-control" id="tax_name_1" name="tax_name_1" value="${objTax.taxName}"	size="30" type="text" placeholder="Enter Tax" />
								<input type="hidden" id="tax_id_1" name="tax_id_1" size="30" />
								<div class="required-icon">
								<div class="text">*</div>
								</div>
								</div>
								</div>
								</div> --%>
		        		<div class="col-sm-6" >
		        			<div class="form-group">
								<input class="form-control" id="tax_percentage_1" name="tax_percentage_1" value="${taxG_info.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
							</div>
						</div>
						<!-- <div class="col-sm-3" >
		        			<div class="form-group">
								<input class="form-control" id="tax_slno_1" name="tax_slno_1" value="" size="30" type="text" placeholder="Enter Display Order"/>
							</div>
						</div> -->
						<div class="col-sm-2" >&nbsp;</div>
		        	</div>
		        	
		        	
		        	<!-- 	 3rd row -->
		        
		        <div class="row row-no-margin linerow tax_group_row" id="tax_group_row_2">
	
		        		<div class="col-sm-4" >
			        		<div class="form-group">
				        		<div class="required-field-block">	 
							          <select name="tax_id_2" id="tax_id_2" class="form-control" >
											<option value="-1">&lt;--Select Tax--&gt;</option>
											<c:forEach var="objTax" items="${taxList}">
												<c:choose>
													<c:when test="${objTax.taxId==taxG_info.taxId}">
														<option value="${objTax.taxId}" selected="selected"><c:out value="${objTax.taxName}"/></option>
													</c:when>
													<c:otherwise>
														<option value="${objTax.taxId}"><c:out value="${objTax.taxName}"/></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>  
						             <div class="required-icon">
										<div class="text">*</div>
								     </div>
							     </div>
				            </div>
		        		</div>
		        	<%-- 	<div class="col-sm-4">
								<div class="form-group">
								<div class="required-field-block">
								<input class="form-control" id="tax_name_2" name="tax_name_2" value="${objTax.taxName}"	size="30" type="text" placeholder="Enter Tax" />
								<input type="hidden" id="tax_id_2" name="tax_id_2" size="30" />
								<div class="required-icon">
								<div class="text">*</div>
								</div>
								</div>
								</div>
								</div> --%>
		        		<div class="col-sm-6" >
		        			<div class="form-group">
								<input class="form-control" id="tax_percentage_2" name="tax_percentage_2" value="${taxG_info.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
							</div>
						</div>
						<!-- <div class="col-sm-3" >
		        			<div class="form-group">
								<input class="form-control" id="tax_slno_2" name="tax_slno_2" value="" size="30" type="text" placeholder="Enter Display Order"/>
							</div>
						</div> -->
						<div class="col-sm-2" >&nbsp;</div>
		        	</div>
		        	
		        	<!-- 	 4th row -->
		        
		        <div class="row row-no-margin linerow tax_group_row" id="tax_group_row_3">
	
		        		<div class="col-sm-4" >
			        		<div class="form-group">
				        		<div class="required-field-block">	 
							          <select name="tax_id_3" id="tax_id_3" class="form-control" >
											<option value="-1">&lt;--Select Tax--&gt;</option>
											<c:forEach var="objTax" items="${taxList}">
												<c:choose>
													<c:when test="${objTax.taxId==taxG_info.taxId}">
														<option value="${objTax.taxId}" selected="selected"><c:out value="${objTax.taxName}"/></option>
													</c:when>
													<c:otherwise>
														<option value="${objTax.taxId}"><c:out value="${objTax.taxName}"/></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>  
						             <div class="required-icon">
										<div class="text">*</div>
								     </div>
							     </div>
				            </div>
		        		</div>
		        		<%-- <div class="col-sm-4">
								<div class="form-group">
								<div class="required-field-block">
								<input class="form-control" id="tax_name_3" name="tax_name_3" value="${objTax.taxName}"	size="30" type="text" placeholder="Enter Tax" />
								<input type="hidden" id="tax_id_3" name="tax_id_3" size="30" />
								<div class="required-icon">
								<div class="text">*</div>
								</div>
								</div>
								</div>
								</div> --%>
		        		<div class="col-sm-6" >
		        			<div class="form-group">
								<input class="form-control" id="tax_percentage_3" name="tax_percentage_3" value="${taxG_info.taxPercent}" size="30" type="text" placeholder="Enter Tax Percentage"/>
							</div>
						</div>
						<!-- <div class="col-sm-3" >
		        			<div class="form-group">
								<input class="form-control" id="tax_slno_3" name="tax_slno_3" value="" size="30" type="text" placeholder="Enter Display Order"/>
							</div>
						</div> -->
						<div class="col-sm-2 linerow-icon" >
						<a href="javascript:addAnotherLineForTaxGroup()" data-toggle="tooltip" title="Add" ><span class="glyphicon glyphicon-plus text-primary "></span></a>
						</div>
		        	</div>
		        	
	        	</c:if>
	        	
	        </div>
	        
	         <div class="col-sm-6" >
		         <div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title "><span class="text-primary">Help text</span></h3>
					</div>
					<div class="panel-body">
						<p>A tax group can have more than one taxes. Create a separate tax group if % or tax name is different. You will select the tax group in the sales related entries to apply one or more taxes as configured for the tax group. If there is a single tax structure across the country, then you will have a single tax group. If tax is different for different commodity or region (sales within state , sales to other states, exports) you will have different tax groups.</p>
					</div>
				</div>
	         </div>
        </div>
        
				            
				        
				      
        <div class='row' style="margin-top: 15px;">       
            <div class='col-sm-12'>
            </div>
        </div>
       <%--  <div class='pull-left'>        	         	 
            <div class='col-sm-12 col-xs-12 centered'>
            	<c:if test="${tg_rights ne null && tg_rights.addPermission eq 1 }">               
                  	<div class="btn-group dropdown">
                <button name="save" id="save"  onclick="saveTaxGroup('${mode}','1')"  class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button>
			    
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                
                <li><a href="javascript:saveTaxGroup('${mode}','2');">Save & New</a></li>
				<li><a href="javascript:saveTaxGroup('${mode}','3');">Save & Close</a></li>
						
            
                </ul>
              </div>
              </c:if>
               <button type="button" class="btn btn-primary"  onclick="showTaxGroup()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
            </div>            
    </div>   --%>
     <%-- <div class='pull-left'>        	         	 
            <div class='col-sm-12 col-xs-12 centered'>               
                  	<div class="btn-group dropdown">
                <button name="save" id="save"  onclick="saveTaxGroup('${mode}','1')"  class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button>
			    <!--  <button name="save" id="save"  class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button> -->
			    
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                
                <li><a href="javascript:saveTaxGroup('${mode}','2');">Save & New</a></li>
				<li><a href="javascript:saveTaxGroup('${mode}','3');">Save & Close</a></li>
						
            
                </ul>
              </div>
              <div class="btn-group dropdown">


				<button name="save" id="save" onclick="saveSizeRange('${mode}','1')" 
				class="btn btn-success ladda-button" data-style="expand-right">
					<span class="ladda-label">
					 <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
					  <span class="ladda-spinner"></span><span class="ladda-spinner"></span>
						</button>
					<button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height: 34px;">
							<span class="caret"></span>
								</button>
									<ul class="dropdown-menu">
								<li><a	href="javascript:saveTaxGroup('${mode}','2');">Save &amp; New</a></li>

			            	<li><a href="javascript:saveTaxGroup('${mode}','3');">Save	&amp; Close</a></li>
											</ul>
										</div>
               <button type="button" class="btn btn-primary"  onclick="showTaxGroup()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
            </div>            
    </div>  --%>
     <div class='pull-left'>        	         	 
            <div class='col-sm-12 col-xs-12 centered'>
            	<c:if test="${tg_rights ne null && tg_rights.addPermission eq 1 }">               
                  	<div class="btn-group dropdown">
                <button name="save" id="save"  onclick="saveTaxGroup('${mode}','1')"  class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button>
			    
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                
                <li><a href="javascript:saveTaxGroup('${mode}','2');">Save & New</a></li>
				<li><a href="javascript:saveTaxGroup('${mode}','3');">Save & Close</a></li>
						
            
                </ul>
              </div>
              </c:if>
               <button type="button" class="btn btn-primary"  onclick="showTaxGroup()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>	
            </div>            
    </div>   
     <div class='row'>       
        </div>
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
<c:if test="${k>0}">
<c:forEach begin="1" end="${k}" varStatus="loop">

<c:set var="b" value="${a},"/> 
<c:set var="c" value="${c}${b}"/>
	<c:set var="a" value="${a+1}"/>
</c:forEach>
</c:if>
<input type="hidden" name="row_ids" id="row_ids" value="${c}${row_ids }"/>
</c:otherwise>
</c:choose>
 
 
<script type="text/javascript">


$(document).ready(function() {
$('#validate-form').bootstrapValidator({
	//  live: 'disabled',
	     excluded: ':disabled',
	  framework: 'bootstrap',
	  message: 'This value is not valid',
	
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
	 	  tax_group: {
              validators: {
                  notEmpty: {
                      message: 'This field is required'
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
	
});
	
	 
</script>
 <!--  <script>		  
	!function ($) {
	 
		$(function(){
			
			<c:forEach var="rowId" items="${row_id_list}">
			
			$('#size_name_${rowId}').listTaxGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetTaxGroup&create_new=1",
			  nameField :'#size_name_${rowId}',
			  idField:'#tax_id_${rowId}'
				//  validateId:'size_name_${rowId}'
		  });
			</c:forEach>
				
		 });
 }(window.jQuery);	
</script>  -->
 
	</form>
</body>
</html>
  
