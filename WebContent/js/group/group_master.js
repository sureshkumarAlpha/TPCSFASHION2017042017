function addGroupMaster(mode)
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('mode').value=mode; 
	document.getElementById('servlet_name').value="GroupMasterServlet";
	document.getElementById('callbackmethod_name').value="doNewGroupMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}


function savemastergroups(mode,saveType)
{
	
	document.getElementById('mode').value=mode;
	
	var objItemTracking = document.getElementById('itemtracking');
	var ItemTracking="0";
	var objBarCodeTracking = document.getElementById('barcodetracking');
	var BarCodeTracking="0";
	
	if(objItemTracking.checked){
		ItemTracking="1";
	}
	if(objBarCodeTracking.checked){
		BarCodeTracking="1";
	}
	
	document.getElementById('itemtracking').value=ItemTracking;
	document.getElementById('barcodetracking').value=BarCodeTracking;
	document.getElementById('save_type').value=saveType;
	document.getElementById('servlet_name').value="GroupMasterServlet";
	document.getElementById('callbackmethod_name').value="doSaveGroupMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
//	document.forms[0].submit();	
	document.getElementById('validation_btn').click();
}


//--------POPUP GROUP

function savePopupGroup(popId){
	/*document.getElementById('error_message').innerHTML="";
	document.getElementById('success_message').innerHTML="";*/
	document.getElementById('pop_validation_message').innerHTML="";
	
	var groupCode=document.getElementById('group_code').value.trim();
	var groupName=document.getElementById('group_name').value.trim();
	var groupType=document.getElementById('group_type');
	var groupTypeValue = groupType.options[groupType.selectedIndex].value;
	var subGroupId=document.getElementById('subgroup_id').value.trim();
	var objItemTracking = document.getElementById('itemtracking');
	var ItemTracking="0";
	var objBarCodeTracking = document.getElementById('barcodetracking');
	var BarCodeTracking="0";
	
	if(objItemTracking.checked){
		ItemTracking="1";
	}
	if(objBarCodeTracking.checked){
		BarCodeTracking="1";
	}
	
	if(groupCode.length==0){		
		document.getElementById('pop_validation_message').innerHTML=localizedStrings.VALID_GROUP_CODE;
		return false;
	}
	else if(groupName.length==0){
		document.getElementById('pop_validation_message').innerHTML=localizedStrings.VALID_GROUP_NAME;
		return false;
	}
	else if(groupTypeValue==-1){	
		document.getElementById('pop_validation_message').innerHTML=localizedStrings.VALID_GROUP_TYPE;
		return false;
	}
	else{
		var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'GroupMasterServlet' , callbackmethod_name : 'doSaveGroupFromPage',
			group_code:groupCode,group_name:groupName,group_type:groupTypeValue,sub_groupid:subGroupId,item_tracking:ItemTracking,barcode_tracking:BarCodeTracking
			},
		dataType: "xml",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + errorThrown);
//			console.log('Error: ' + errorThrown);
		},
		success: function(xml) {
			
			jQuery(xml).find('group').each(function(){
				
				 var id=jQuery(this).find('groupid').text();
				
				if(id=='exists'){
					document.getElementById('pop_validation_message').innerHTML=localizedStrings.GROUP_ALREADY_EXISTS;
					return false;
				}
				else if(id=="failed"){
					document.getElementById('pop_validation_message').innerHTML=localizedStrings.GROUP_SAVED_FAILED;
					return false;
				}
				else{
					
			    	
			    	 var name=jQuery(this).find('groupname').text();
			    	 
			    	 
			    /*	 	elementId=document.getElementById('element_id').value.trim();
				    	group=document.getElementById(elementId);
				    	if(group!=null){
					        var opt = document.createElement("option");
					        opt.value = id;
					        opt.text = name;
					        group.options.add(opt);
					        
				    	}*/
				    
				    	document.getElementById('pop_validation_message').innerHTML="";
				        document.getElementById('succ-msg').innerHTML=localizedStrings.GROUP_SAVED;
				      /*  window.opener.location.reload();
				        closepopup(popId);	*/
				        //window.opener.$.fn.listGroup();		
				    	parent.window.location.reload(true);
				    	 closepopup(popId);
				    	
				}
			    	 
				
			});
			 
		}
	});
		
	}
	
	

}



function clearSearchGroupMaster()
{
	document.getElementById('group_type_id').value="";
	document.getElementById('group_type_name').value="";
	document.getElementById('group').value="";
	document.getElementById('group_id').value="";
	document.getElementById('groupcode').value="";
	
	document.getElementById('dynamic_field_1').value=-1;
	document.getElementById('dynamic_field_1_val').value="";
	
	document.getElementById('dynamic_field_2').value=-1;
	document.getElementById('dynamic_field_2_val').value="";
}

function backGroupMaster()
{
	document.getElementById('servlet_name').value="GroupMasterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayGroupMaster";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}
function deleteGroupMaster(groupId,pageno){	
	bootbox.confirm(localizedStrings.SURE_TO_DELETE_GROUP, function(result) {
		if(result){
		document.getElementById('pageno').value=pageno;
		document.getElementById('group_id').value=groupId;
		document.getElementById('servlet_name').value="GroupMasterServlet";
		document.getElementById('callbackmethod_name').value="doDeleteGroupMaster";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
	});
}





function editGroupMaster(editmode,groupId)
{
	document.getElementById('mode').value=editmode;
	document.getElementById('group_id').value=groupId;
	document.getElementById('servlet_name').value="GroupMasterServlet";
	document.getElementById('callbackmethod_name').value="doEditGroupMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}


function changeGroupMasterPageNumber(pageNo)
{
	document.getElementById('pageno').value=pageNo;
	document.getElementById('servlet_name').value="GroupMasterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayGroupMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
	
function searchGroupMaster()
{
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="GroupMasterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayGroupMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}


function groupActionList(actionType,groupId,pageno)
{
	 if(actionType== 1){ deleteGroupMaster(groupId,pageno);}
	 else if(actionType==2){editGroupMaster('e',groupId);}
	
}

function activeBulkGroupMaster(pageNo,active_mode)  
{
	var cnt = jQuery("input[name='groupmaster_id']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
		return false;
    } 	
	document.getElementById('pageno').value=pageNo;
	document.getElementById('active_mode').value=active_mode;
	document.getElementById('servlet_name').value="GroupMasterServlet";
	document.getElementById('callbackmethod_name').value="doBulkActiveGroupMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function deleteBulkGroupMaster(pageno)
{
	 var cnt = jQuery("input[name='groupmaster_id']:checked").length;
     if (cnt < 1) 
     {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		//document.getElementByName('sales_enqid').focus();
		showWarningMsg('warning-msg');
//		return false;
     } 	
	else{

		bootbox.confirm("Are you sure you want to delete?", function(result) {
			if(result){
				document.getElementById('pageno').value=pageno;
				document.getElementById('servlet_name').value="GroupMasterServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBulkGroupMaster";	
				document.forms[0].action=contextpath+"/RequestHandlerServlet";
				document.forms[0].method="POST";
				document.forms[0].submit();
			}
		});
	}

}
