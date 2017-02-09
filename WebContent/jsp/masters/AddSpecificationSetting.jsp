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
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/masters/masters.js"></script>

<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

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
	
</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body onload="${strFormOnLoadEvent}">

<form action="" method="post" role="form" id="validate_spec_form" class="trans-form" autocomplete="off">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>
			
 <div class="top-bar">
 <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-12">
                    <h2 class="add-page-header" >Specification Setting </h2>
                </div>
           </div>
<div id="page-wrapper" class="add-top-wrapper"> 
<div id="page-inner">   
                
<div class="row row-no-margin" >
				<div class='col-sm-3'  >
				<div class='form-group'>
				<label>Group Type</label>
			  	<div class="required-field-block">
				<input class="form-control" id="group_type_name" name="group_type_name" size="30" type="text" value="<c:out value="${specsetting_info.groupType.groupType}"/>"  placeholder="select group type"/>
				<input class="form-control" id="group_type" name="group_type" size="30" type="hidden" value="<c:out value="${specsetting_info.groupType.groupTypeId}"/>"  />
                     <label id="errormessage"></label>	
			 <div class="required-icon">
				    <div class="text">*</div>
			        </div>
			        </div>
					</div>
					</div>

</div>

</div>
</div>
</div>
    
<div id="page-wrapper" style="margin-top: 0;"> 
<div id="page-inner">   
 
 	<div class="row row-no-margin">
 		<div class="col-sm-4" id="header"><label>&nbsp;</label></div>
 		<div class="col-sm-4" id="header"><label>Heading</label></div>
 		<div class="col-sm-4" id="header"><label>Length of Code</label></div>
 	</div>
 
 	<div class="row row-no-margin linerow " >
	
   		<div class="col-sm-4">
			<label>Specification1 :</label>
   		</div>
     	 <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification1" name="specification1" size="30" type="text" value="<c:out value="${specsetting_info.spec1}"/>"  placeholder="Enter specification1"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
             </div>
       </div>
		 <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification1_length" name="specification1_length" size="30" type="text" value="<c:out value="${specsetting_info.specLen1}"/>"  placeholder="Enter specification1 length"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			   <div class="text">*</div>
			     </div>
			     </div>
	             </div>
        </div>

	</div>
 
 	<div class="row row-no-margin linerow " >
 
      <div class="col-sm-4">
      <label>Specification2 :</label>
      </div>
       <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification2" name="specification2" size="30" type="text" value="<c:out value="${specsetting_info.spec2}"/>"  placeholder="Enter specification2"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
             </div>
       </div>
		<div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification2_length" name="specification2_length" size="30" type="text" value="<c:out value="${specsetting_info.specLen2}"/>"  placeholder="Enter specification2 length"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			   <div class="text">*</div>
			     </div>
			     </div>
	             </div>
        </div>
           
 	</div>
    <div class="row row-no-margin linerow " >
        <div class="col-sm-4">
        <label>Specification3 :</label>
        </div>
       
        <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification3" name="specification3" size="30" type="text" value="<c:out value="${specsetting_info.spec3}"/>"  placeholder="Enter specification3"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
             </div>
       </div>
        <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification3_length" name="specification3_length" size="30" type="text" value="<c:out value="${specsetting_info.specLen3}"/>"  placeholder="Enter specification3 length"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			   <div class="text">*</div>
			     </div>
			     </div>
	             </div>
        </div>
       
    </div>
    <div class="row row-no-margin linerow " >
        <div class="col-sm-4">
        <label>Specification4 :</label>
        </div>
        <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification4" name="specification4" size="30" type="text" value="<c:out value="${specsetting_info.spec4}"/>"  placeholder="Enter specification4"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
             </div>
       </div>
        <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification4_length" name="specification4_length" size="30" type="text" value="<c:out value="${specsetting_info.specLen4}"/>"  placeholder="Enter specification4 length"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			   <div class="text">*</div>
			     </div>
			     </div>
	             </div>
        </div>
   	 </div>
     <div class="row row-no-margin linerow " >
       <div class="col-sm-4">
       <label>Specification5 :</label>
       </div>
       <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification5" name="specification5" size="30" type="text" value="<c:out value="${specsetting_info.spec5}"/>"  placeholder="Enter specification5"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
             </div>
       </div>
        <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification5_length" name="specification5_length" size="30" type="text" value="<c:out value="${specsetting_info.specLen5}"/>"  placeholder="Enter specification5 length"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			   <div class="text">*</div>
			     </div>
			     </div>
	             </div>
        </div>
     </div>
     <div class="row row-no-margin linerow " >
   	 <div class="col-sm-4">
   	 <label>Specification6 :</label>
   	 </div>
     <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification6" name="specification6" size="30" type="text" value="<c:out value="${specsetting_info.spec6}"/>"  placeholder="Enter specification6"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
             </div>
       </div>
        <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification6_length" name="specification6_length" size="30" type="text" value="<c:out value="${specsetting_info.specLen6}"/>"  placeholder="Enter specification6 length"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			   <div class="text">*</div>
			     </div>
			     </div>
	             </div>
        </div>
     </div>
      <div class="row row-no-margin linerow " >
      <div class="col-sm-4">
      <label>Specification7 :</label>
      </div>
      <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification7" name="specification7" size="30" type="text" value="<c:out value="${specsetting_info.spec7}"/>"  placeholder="Enter specification7"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
             </div>
       </div>
       <div class='col-sm-4'>    
                <div class='form-group'>
                    <div class="required-field-block">	   
                    <input class="form-control" id="specification7_length" name="specification7_length" size="30" type="text" value="<c:out value="${specsetting_info.specLen7}"/>"  placeholder="Enter specification7 length"/>
                     <label id="errormessage"></label>	
                    <div class="required-icon">
			     <div class="text">*</div>
			     </div>
			     </div>
	             </div>
       </div>
      </div>
 
<div class='row pull-right'>        	         	 
            <div class='col-sm-12 col-xs-12 centered'>   
            
            	  <c:if test="${specsetting_rights.addPermission==1 or specsetting_rights.editPermission==1 }">		
            	<div class="btn-group dropup">
                  	
                
                <button name="save" id="save"  onclick="saveSpecificationSetting('${mode}','1')"  class="btn btn-success ladda-button"  data-style="expand-right" >
			     <span class="ladda-label"> <i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save
			    </button>
			    
                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:34px;"><span class="caret"></span></button>
                <ul class="dropdown-menu">
               
                <c:if test="${specsetting_rights.addPermission==1 }">	
						<li><a href="javascript:saveSpecificationSetting('${mode}','2');">Save & New</a></li>
						</c:if>
							<c:if test="${specsetting_rights.viewPermission==1 }">
						<li><a href="javascript:saveSpecificationSetting('${mode}','3');">Save & Close</a></li>
						</c:if>
            
                </ul>
              </div>
            	</c:if>            
                  	<c:if test="${specsetting_rights.viewPermission==1 }">
                  	 <button type="button" class="btn btn-primary"  onclick="showSpecSetting()"><i class="glyphicon glyphicon-eye-open"></i><span>&nbsp;View</span></button>
                  	</c:if>		
              
            </div>            
    </div>  			
		
        <div class='row'>
        </div>    
         <div class='row'>
         </div>  
         <div class="row">       
        </div>
	 
 </div>	
   </div>
  </div> 

		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
			

<input type="hidden" name="mode" id="mode"/>
<input type="hidden" name="save_type" id="save_type" />
<input type="hidden" name="group_id" id="group_id" value="${group_info.groupId}" />
<input type="hidden" name="shortname" id="shortname"  value="${gm.shortname}"/>
<input type="hidden" name="itemtracking" id="itemtracking"  value="${gm.itemtracking}"/>
<input type="hidden" name="barcodetracking" id="barcodetracking"  value="${gm.barcodetracking}"/>
<input type="hidden" name="grouptypeid" id="grouptypeid"  value="${objCur.groupcode}"/>
<input type="hidden" name="pageno" id="pageno" />
<input type="hidden" id="add_new_GroupType" data-toggle="modal" data-target="#groupTypeMasterModal"/> 
<input type="submit" name="validation_btn" id="validation_btn" style="display:none;"/>
 <input type="hidden" name="group_type1" id="group_type1" value="${specsetting_info.groupType.groupTypeId}"/>
 <input type="hidden" name="specification11" id="specification11" value="${specsetting_info.spec1}"/>
<input type="hidden" name="specification11_length" id="specification11_length" value="${specsetting_info.specLen1}"/>
<input type="hidden" name="specification22" id="specification22" value="${specsetting_info.spec2}"/>
<input type="hidden" name="specification22_length" id="specification22_length" value="${specsetting_info.specLen2}"/>
<input type="hidden" name="specification33" id="specification33" value="${specsetting_info.spec3}"/>
<input type="hidden" name="specification33_length" id="specification33_length" value="${specsetting_info.specLen3}"/>
<input type="hidden" name="specification44" id="specification44" value="${specsetting_info.spec4}"/>
<input type="hidden" name="specification44_length" id="specification44_length" value="${specsetting_info.specLen4}"/>
<input type="hidden" name="specification55" id="specification55" value="${specsetting_info.spec5}"/>
<input type="hidden" name="specification55_length" id="specification55_length" value="${specsetting_info.specLen5}"/>
<input type="hidden" name="specification66" id="specification66" value="${specsetting_info.spec6}"/>
<input type="hidden" name="specification66_length" id="specification66_length" value="${specsetting_info.specLen6}"/>
<input type="hidden" name="specification77" id="specification77" value="${specsetting_info.spec7}"/>
<input type="hidden" name="specification77_length" id="specification77_length" value="${specsetting_info.specLen7}"/> 			
			<script type="text/javascript">
			
			$('#validate_spec_form').bootstrapValidator({
				//  live: 'disabled',
				  message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisValueIsNotValid"/>,
				  feedbackIcons: {
				      valid: 'glyphicon glyphicon-ok',
				      invalid: 'glyphicon glyphicon-remove',
				      validating: 'glyphicon glyphicon-refresh'
				  },
				  fields: {
					  group_type_name: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  },
			                  callback: {
				                    message: 'This field is required',
				                    callback: function(value, validator, $field) {
				                    	if ($("#group_type").val()=='' || $("#group_type").val()==0 ) {
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
					  specification1: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          },
			          specification1_length: {
			            validators: {
			                notEmpty: {
			                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                }
			            }
			        },
			        specification2: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			                  },
                  specification2_length: {
			            validators: {
			                notEmpty: {
			                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                }
			            }
			        },
			        specification3: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          },
			          specification3_length: {
			            validators: {
			                notEmpty: {
			                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                }
			            }
			        },
			        specification4: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          },
			          specification4_length: {
			            validators: {
			                notEmpty: {
			                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                }
			            }
			        },
			        specification5: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          },
			          specification5_length: {
			            validators: {
			                notEmpty: {
			                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                }
			            }
			        },
			        specification6: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          },
			          specification6_length: {
			            validators: {
			                notEmpty: {
			                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                }
			            }
			        },
			        specification7: {
			              validators: {
			                  notEmpty: {
			                      message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                  }
			              }
			          },
			          specification7_length: {
			            validators: {
			                notEmpty: {
			                    message: <fmt:message bundle="${bundle}" key="BootsrapValidation.ThisFieldIsRequired"/>
			                }
			            }
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
				});
			



!function ($) {

	 
	 $(function(){
		  $('#group_type_name').listGroupType({
			  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetGroupType&create_new=1",
			  nameField :'#group_type_name',
			  idField:'#group_type',
			  formId:'#validate_spec_form',
			  validateId:'group_type_name' 
		  });			 
	 });   

}(window.jQuery);

	 
</script>

	</form>
	  
 <jsp:include page="../masters/AddGroupType.jsp" />	  
 
	<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/autocomplete/jenixcloud.autocomplete.js"></script>  
</body>
</html>