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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/group/group_master.js"></script>

<style>
/* .container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
} */

</style>

<script type="text/javascript">
	var contextpath = "${pageContext.request.contextPath}";

</script>
	
	
	<script language="javascript">
var localizedStrings = {
	    	VALID_GROUP_CODE: "<fmt:message bundle="${bundle}" key="Group.ValidGroupCode"/>",
	    	VALID_GROUP_NAME: "<fmt:message bundle="${bundle}" key="Group.ValidGroupName"/>",
	    	VALID_GROUP_TYPE: "<fmt:message bundle="${bundle}" key="Group.ValidGroupType"/>",
	    	GROUP_ALREADY_EXISTS: "<fmt:message  bundle="${bundle}" key="Group.AlreadyExixts"/>",
	    	GROUP_SAVED: "<fmt:message  bundle="${bundle}" key="Group.CreateSuccessfully"/>",
	    	GROUP_SAVED_FAILED: "<fmt:message  bundle="${bundle}" key="Group.CreateGroupFailed"/>",
	    	
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

	<c:set var="strFormOnLoadEvent" value = "" />
	<c:set var="strFormOnSumbitevent" value = ""/>

	<c:forEach var="events" items="${dynamicFormEvents}">
		<c:if test="${fn:containsIgnoreCase(events.eventName, 'onload')}">
			<c:set var="strFormOnLoadEvent" value="${strFormOnLoadEvent}${events.functionName};"/>
		</c:if> 
		<c:if test="${fn:containsIgnoreCase(events.eventName, 'onsubmit')}">
			<c:set var="strFormOnSumbitevent" value="${strFormOnSumbitevent}${events.functionName};"/>
		</c:if> 
	</c:forEach>
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body onload="${strFormOnLoadEvent}">

<form action="" method="post" role="form" id="validate-form" class="trans-form" autocomplete="off">
		<div class="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
			
			
			<div class="page-wrapper">
				<div class="page-inner">
				
					<div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Item Groups</h3>
							</div>
						</div>
					</div>
					
                
<div class="row" >
<div class='col-sm-3'  >
<div class="col-sm-12">
					<div class='form-group'>
						<label>Item Group Name</label>
			  	  <div class="required-field-block">
				<input class="form-control"  type="text"    id="groupname" maxlength="50" name="groupname" value="${group_info.groupname}" placeholder="Enter Item Group Name"/>
			 <div class="required-icon">
				    <div class="text">*</div>
			        </div>
			        </div>
					</div>
					</div>
					</div>

<div class='col-sm-3'  >
<div class="col-sm-12">
					<div class='form-group'>

					<label>Item Group Code</label>
			  	  <div class="required-field-block">
				<input class="form-control" type="text"  id="groupcode" maxlength="15" name="groupcode" value="${group_info.groupcode}" placeholder="Enter Item Group Code"/>
			    <div class="required-icon">
				    <div class="text">*</div>
			        </div>
			        </div>
					</div>
					</div>
					</div>
					<div class='col-sm-3'  >
					<div class="col-sm-12">
				<div class='form-group'>
				<label>Item Group Type</label>
			  	<div class="required-field-block">
				<input class="form-control" id="group_type_name" name="group_type_name" size="30" type="text" value="<c:out value="${group_info.grouptype}"/>"  placeholder="Select Item Group Type"/>
				<input class="form-control" id="group_type_id" name="group_type_id" size="30" type="hidden" value="<c:out value="${group_info.groupTypeId}"/>"  />
                     <label id="errormessage"></label>	
			 <div class="required-icon">
				    <div class="text">*</div>
			        </div>
			        </div>
					</div>
					</div>
					</div>
					</div>
							
<div class='row'>
<div class='col-sm-3'  >
<div class="col-sm-12">
					<div class='form-group'>
	<label>Parent Group</label>
				<input class="form-control"   type="text"  id="subgroup" maxlength="50" name="subgroup" value="${group_info.groupUnderName}"  onkeyup="clearId(event,'subgroup','subgroup_id')" placeholder="Type and select Parent Group"/>
						<input type="hidden" name="subgroup_id" id="subgroup_id" value="${group_info.groupUnderId}"/>
</div>
</div>
</div>

<div class='col-sm-3'  >
<div class="col-sm-12">
<div class='form-group'>
  	<label>Status</label>
			<select class="form-control" id="group_status" name="group_status">
			<option value="1" <c:if test="${group_info.status eq 1 }">selected="selected"</c:if>>Active</option>
			<option value="0" <c:if test="${group_info.status eq 0 }">selected="selected"</c:if>>Inactive</option>
			</select>
</div>
</div>
</div>

</div>	
	<div class="row">
               <div class="col-sm-12">
                <div class="col-sm-12">
                    <h4><label class="text-primary" >Configuration</label></h4>
                </div>
                </div>
	</div>

	<div class="row">
          <div class='col-sm-3'>
 <div class="col-sm-12">					
					<div class="form-group ">
					<label> Is lot no tracking applicable ?</label>
					</div>
				</div>	
					
		</div>			
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<select class="form-control" id="lot_no_tracking" name="lot_no_tracking">
				<option value="2" <c:if test="${group_info.lotNoTracking eq 2}"> selected="selected" </c:if> >Depends on item</option>
				<option value="1" <c:if test="${group_info.lotNoTracking eq 1}"> selected="selected" </c:if> >Yes</option>
				<option value="0" <c:if test="${group_info.lotNoTracking eq 0}"> selected="selected" </c:if> >No</option>
			</select>
			</div>
		</div>	
		</div>
					
	</div>
	<div class="row">
          <div class='col-sm-3'>
           <div class="col-sm-12">
					<div class='form-group'>
						<label>Is expiry date applicable ? </label>
					</div>
					</div>
		</div>			
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<select class="form-control" id="date_expiry" name="date_expiry">
				<option value="2" <c:if test="${group_info.dateExpiry eq 2}"> selected="selected" </c:if> >Depends on item</option>
				<option value="1" <c:if test="${group_info.dateExpiry eq 1}"> selected="selected" </c:if> >Yes</option>
				<option value="0" <c:if test="${group_info.dateExpiry eq 0}"> selected="selected" </c:if> >No</option>
			</select>
			</div>
		</div>	
		</div>
					
	</div>
	<div class="row">
          <div class='col-sm-3'>
           <div class="col-sm-12">
					<div class='form-group'>
						<label>Do you want to allow negative stock ? </label>
					</div>
		</div>		
		</div>	
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<select class="form-control" id="negative_stock" name="negative_stock">
				<option value="2" <c:if test="${group_info.negativeStock eq 2}"> selected="selected" </c:if> >Depends on item</option>
				<option value="1" <c:if test="${group_info.negativeStock eq 1}"> selected="selected" </c:if> >Yes</option>
				<option value="0" <c:if test="${group_info.negativeStock eq 0}"> selected="selected" </c:if> >No</option>
			</select>
			</div>
		</div>	
		</div>
					
	</div>
	<div class="row">
          <div class='col-sm-3'>
           <div class="col-sm-12">
					<div class='form-group'>
						<label>Do you want to allow item to be issued only with reference to IO.No ? </label>
					</div>
		</div>	
		</div>		
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<select class="form-control" id="issue_with_io" name="issue_with_io">
				<option value="2" <c:if test="${group_info.issueWithIO eq 2}"> selected="selected" </c:if> >Depends on item</option>
				<option value="1" <c:if test="${group_info.issueWithIO eq 1}"> selected="selected" </c:if> >Yes</option>
				<option value="0" <c:if test="${group_info.issueWithIO eq 0}"> selected="selected" </c:if> >No</option>
			</select>
			</div>
			</div>
		</div>	
					
	</div>
	<div class="row">
          <div class='col-sm-3'>
           <div class="col-sm-12">
					<div class='form-group'>
						<label>Is Colour applicable ? </label>
					</div>
					</div>
		</div>			
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<select class="form-control" id="colour_applicable" name="colour_applicable">
				<option value="2" <c:if test="${group_info.colorApplicable eq 2}"> selected="selected" </c:if> >Depends on item</option>
				<option value="1" <c:if test="${group_info.colorApplicable eq 1}"> selected="selected" </c:if> >Yes</option>
				<option value="0" <c:if test="${group_info.colorApplicable eq 0}"> selected="selected" </c:if> >No</option>
			</select>
			</div>
		</div>	
		</div>
					
	</div>
	<div class="row">
          <div class='col-sm-3'>
           <div class="col-sm-12">
					<div class='form-group'>
						<label>Is Size applicable ? </label>
					</div>
					</div>
		</div>			
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<select class="form-control" id="size_applicable" name="size_applicable">
				<option value="2" <c:if test="${group_info.sizeApplicable eq 2}"> selected="selected" </c:if> >Depends on item</option>
				<option value="1" <c:if test="${group_info.sizeApplicable eq 1}"> selected="selected" </c:if> >Yes</option>
				<option value="0" <c:if test="${group_info.sizeApplicable eq 0}"> selected="selected" </c:if> >No</option>
			</select>
			</div>
			</div>
		</div>	
					
	</div>
	<div class="row">
          <div class='col-sm-3'>
           <div class="col-sm-12">
					<div class='form-group'>
						<label>Do you want to allow stock reservation for MRP ? </label>
					</div>
					</div>
		</div>			
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<select class="form-control" id="stock_reservation" name="stock_reservation">
				<option value="2" <c:if test="${group_info.stockReservation eq 2}"> selected="selected" </c:if> >Depends on item</option>
				<option value="1" <c:if test="${group_info.stockReservation eq 1}"> selected="selected" </c:if> >Yes</option>
				<option value="0" <c:if test="${group_info.stockReservation eq 0}"> selected="selected" </c:if> >No</option>
			</select>
			</div>
		</div>	
		</div>
					
	</div>
	<div class="row">
          <div class='col-sm-3'>
           <div class="col-sm-12">
					<div class='form-group'>
						<label>Do you want to allow inspection is required for the group ? </label>
					</div>
					</div>
		</div>			
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<select class="form-control" id="inspection_required" name="inspection_required">
				<option value="2" <c:if test="${group_info.inspectionRequired eq 2}"> selected="selected" </c:if> >Depends on item</option>
				<option value="1" <c:if test="${group_info.inspectionRequired eq 1}"> selected="selected" </c:if> >Yes</option>
				<option value="0" <c:if test="${group_info.inspectionRequired eq 0}"> selected="selected" </c:if> >No</option>
			</select>
			</div>
			</div>
		</div>	
					
	</div>
<div class="row">
          <div class='col-sm-3'>
           <div class="col-sm-12">
					<div class='form-group'>
						<label>Do you want to allow barcode is required for the group ? </label>
					</div>
					</div>
		</div>			
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<select class="form-control" id="barcode_required" name="barcode_required">
				<option value="2" <c:if test="${group_info.barcodeRequired eq 2}"> selected="selected" </c:if> >Depends on item</option>
				<option value="1" <c:if test="${group_info.barcodeRequired eq 1}"> selected="selected" </c:if> >Yes</option>
				<option value="0" <c:if test="${group_info.barcodeRequired eq 0}"> selected="selected" </c:if> >No</option>
			</select>
			</div>
		</div>	
		</div>			
	</div>
<div class="row">
          <div class='col-sm-3'>
           <div class="col-sm-12">
					<div class='form-group'>
						<label>Tax Category </label>
					</div>
		</div>	
		</div>		
		<div class="col-sm-3">
		 <div class="col-sm-12">
			<div class="form-group">
			<input class="form-control" id="tax_category" name="tax_category" size="25" type="text" value="<c:out value="${group_info.taxCategory}"/>"  placeholder="Enter Tax Category"/>
			</div>
			</div>
		</div>	
					
	</div>



						
			
		<div class='row'>
			<c:forEach items="${dynamicFieldsListOrderHeader}" var="dynamicHeaderfield" >
			         	<%@ include file="../dynamic/GroupControls.jsp" %> 
				</c:forEach>
			</div>
			
			
<div class='row'>        	         	 
            <div class='col-sm-12 '>   
            <div class='pull-right centered save-view'>
            	  <c:if test="${group_rights.addPermission==1 or group_rights.editPermission==1 }">		
            	<div class="btn-group dropup">
                  	
                
                <button name="save" id="save"  onclick="savemastergroups('${mode}','1')"  class="btn btn-success ladda-button"  data-style="expand-right">
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button>
			    
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" ><span class="caret"></span></button>
                <ul class="dropdown-menu">
               
                <c:if test="${group_rights.addPermission==1 }">	
						<li><a href="javascript:savemastergroups('${mode}','2');">Save & New</a></li>
						</c:if>
							<c:if test="${group_rights.viewPermission==1 }">
						<li><a href="javascript:savemastergroups('${mode}','3');">Save & Close</a></li>
						</c:if>
            
                </ul>
              </div>
            	</c:if>            
                  	<c:if test="${group_rights.viewPermission==1 }">
                  	 <button type="button" class="btn btn-primary"  onclick="showGroupMaster()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>
                  	</c:if>		
              
            </div>   
            </div>         
    </div>  			
		
     <div class='row'>
     <label>&nbsp;</label>
     </div>  
	<div class='row'>
     <label>&nbsp;</label>
     </div>	
   </div>
  </div> 

  		</div>

		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
			

<input type="hidden" name="mode" id="mode" value="${mode}"/>
<input type="hidden" name="save_type" id="save_type" />
<input type="hidden" name="group_id" id="group_id" value="${group_info.groupId}" />
<input type="hidden" name="shortname" id="shortname"  value="${gm.shortname}"/>
<input type="hidden" name="itemtracking" id="itemtracking"  value="${gm.itemtracking}"/>
<input type="hidden" name="barcodetracking" id="barcodetracking"  value="${gm.barcodetracking}"/>
<input type="hidden" name="grouptypeid" id="grouptypeid"  value="${objCur.groupcode}"/>
 <input type="hidden" name="pageno" id="pageno" />
 <input type="hidden" id="add_new_GroupType" data-toggle="modal" data-target="#groupTypeMasterModal"  onclick="loadDataToGroupTypeSlNo();"/> 
 
 <input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
 			
			<script type="text/javascript">
			
			$('#validate-form').bootstrapValidator({
				//  live: 'disabled',
				  message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisValueIsNotValid"/>,
				  feedbackIcons: {
				      valid: 'glyphicon glyphicon-ok',
				      invalid: 'glyphicon glyphicon-remove',
				      validating: 'glyphicon glyphicon-refresh'
				  },
				  fields: {
					  groupname: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          },
			          groupcode: {
			            validators: {
			                notEmpty: {
			                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                }
			            }
			        },
			      group_type_name: {
				              validators: {
				                  notEmpty: {
				                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
				                  },
				                  callback: {
					                    message: 'This field is required',
					                    callback: function(value, validator, $field) {
					                    	if ($("#group_type_id").val()=='' || $("#group_type_id").val()==0 ) {
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
			          grouptype: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  },
			                    callback: {
			                        message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>,
			                        callback: function(value, validator, $field) {
			                        	if (value< 1) {
			                                return {
			                                    valid: false,
			                                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                                };
			                            }
			                        	return true;
			                        }
			                    }
			              }
			          }
				  }
				});
			



!function ($) {

	 
	 $(function(){
		  $('#subgroup').listGroup({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroup",
			  nameField :'#subgroup',
			  idField:'#subgroup_id'
		  });			 
	 });  
	 
	 $(function(){
		  $('#group_type_name').listGroupType({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroupType&create_new=1",
			  nameField :'#group_type_name',
			  idField:'#group_type_id',
			  formId:'#validate-form',
			  validateId:'group_type_name' 
		  });			 
	 });

}(window.jQuery);

	 
</script>
<jsp:include page="../masters/AddGroupType.jsp" />	  
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>
	</form>
	  
</body>
</html>