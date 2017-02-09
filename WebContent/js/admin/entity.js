var checkSelect= new Array();

function SearchEntity(){
	document.getElementById('pageno').value="1";
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="EntityServlet";
	document.getElementById('callbackmethod_name').value="doEntitySearch";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}

function changeEntityRightsPageNumbers(pageno){
	
	document.getElementById('servlet_name').value="EntityServlet";
	document.getElementById('callbackmethod_name').value="doDisplayEntityRights";
	document.getElementById('pageno').value=pageno;
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}
function labelchange(selectedIndex,selectedValue)
{

if(selectedIndex==0){
document.getElementById('entity').innerHTML="";
}
	else if(selectedIndex==1){		
document.getElementById('entity').innerHTML=localizedStrings.HO;
}
else if(selectedIndex==2){		
document.getElementById('entity').innerHTML=localizedStrings.CUSTOMER;
removeConfigurationFromModule();
}
else if(selectedIndex==3){		
document.getElementById('entity').innerHTML=localizedStrings.FACTORY;
removeConfigurationFromModule();
}
				
}	
function getdocuments(selectedIndex,selectedValue){
	if(selectedIndex>0){		
//        var selObj = document.getElementById('selectmodule');	    
//		var selIndex = selObj.selectedIndex;	
//        var moduleId=selObj.options[selIndex].value;        
		jQuery('#sub_document_list').html("");
		var mode=document.getElementById('mode');
        var objEntity = document.getElementById('selectentity');    
    	var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;	
    	
    	var doc= document.getElementById('selectdocument');
    	doc.options.length = 0;
//		var iwidth = (document.getElementById('submain').offsetWidth-10);	
//		var url =contextpath+"/RequestHandlerServlet";
//		var params = "servlet_name=EntityServlet&callbackmethod_name=doGetDocuments&module_id="+moduleId+"&bomWidth="+iwidth+"&entityId="+selectedEntityValue;		
//		var myAjax = new Ajax.Updater("document_list",url,{method: 'POST',parameters:params});
		
		var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'EntityServlet' , callbackmethod_name : 'doGetDocuments',module_id:selectedValue,entityId:selectedEntityValue},
		dataType: "xml"
		});
		request.done(function(xml) {
			if(jQuery(xml).find('document').length==1){
				jQuery(xml).find('document').each(function(){
				id=jQuery(this).find('id').text();
				 name=jQuery(this).find('name').text();
				 doc.options[doc.options.length]=new Option(name, id);
				 if(mode=='n'){
					 getsubdocuments(id,id);
				 }
				 else{
					 getSelecteddocuments(id,id);
				 }
				});
			}
			else{
			jQuery(xml).find('document').each(function(){
				 id=jQuery(this).find('id').text();
				 name=jQuery(this).find('name').text();
				 doc.options[doc.options.length]=new Option(name, id);	 
				
			});
			 if(mode=='n'){
				 getsubdocuments(id,id);
			 }
			 else{
				 getSelecteddocuments(id,id);
			 }
			}
			
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});
 	}
 	
	
}
function getsubdocuments(selectedIndex,selectedValue){
	if(selectedIndex>0){		
        var selObj = document.getElementById('selectdocument');	    
		var selIndex = selObj.selectedIndex;	
        var documentId=selObj.options[selIndex].value;        
        var objEntity = document.getElementById('selectentity');    
    	var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;	
    	jQuery('#sub_document_list').html("");	
		var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'EntityServlet' , callbackmethod_name : 'doGetSubDocuments',document_id:documentId,entityId:selectedEntityValue},
		dataType: "html"
		});
		request.done(function(html) {
			
			jQuery('#sub_document_list').html(html);
			
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});
 	}
 	else if(selectedIndex==0)
 	{
 		document.getElementById('sub_document_list').innerHTML="";
 	}	
	
}
function saveEntityRights(){
	
	
	var objEntity = document.getElementById('selectentity');    
    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
  
    var objModule = document.getElementById('selectdocument');    
    var selectedModuleValue = objModule.options[objModule.selectedIndex].value;
        
       
    
    if(selectedEntityValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ENTITY;//"Please select valid entity!";
    	showResponseMsg('err-msg','succ-msg');
    	objEntity.focus();
    	return false;
    }else if(selectedModuleValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_MODULE;//"Please select valid module!";
    	showResponseMsg('err-msg','succ-msg');
    	objModule.focus();
    	return false;
    }else if(!validateDocuments()){    	
        	document.getElementById('err-msg').innerHTML=localizedStrings.SELECT_AT_LEAST;//"Please select at least one screen name!";
        	showResponseMsg('err-msg','succ-msg');
        	return false;          	
    }else{
       
    	document.getElementById('document_id').value=selectedModuleValue;
    	document.getElementById('selected_documents').value=checkSelect;
    	document.getElementById('servlet_name').value="EntityServlet";
		document.getElementById('callbackmethod_name').value="doSaveEntityRights";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return true;
   }
    
	
}
function clearEntityRights(iType){	
	
	if(iType==1)
	{
	var entityDropdownList = document.getElementById('selectentity');
		for (iLoop = 0; iLoop< entityDropdownList.options.length; iLoop++)
        {
	        if (entityDropdownList.options[iLoop].value.trim() == -1)
	        {
	        entityDropdownList.options[iLoop].selected = true;
	        break;
	        }
        }   
	}
		document.getElementById('sub_document_list').innerHTML="";		
		     
        var moduleDropdownList = document.getElementById('selectmodule');
		for (iLoop = 0; iLoop< moduleDropdownList.options.length; iLoop++)
        {
	        if (moduleDropdownList.options[iLoop].value.trim() == -1)
	        {
	        moduleDropdownList.options[iLoop].selected = true;
	        break;
	        }
        }
			
}
function backViewEntityRights(){
	
	document.getElementById('servlet_name').value="EntityServlet";
	document.getElementById('callbackmethod_name').value="doDisplayEntityRights";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}
function editEntityRights(iPageNO,entityid){
	
	document.getElementById('pageno').value=iPageNO;
	document.getElementById('entityrights_id').value=entityid;
	document.getElementById('servlet_name').value="EntityServlet";
	document.getElementById('callbackmethod_name').value="doEditEntityRights";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function getSelecteddocuments(selectedIndex,selectedValue){
	if(selectedValue>0){	
	 var selEntity = document.getElementById('selectentity');	    
	 var selEntityIndex = selEntity.selectedIndex;	
     var entity=selEntity.options[selEntityIndex].value;
     var selObj = document.getElementById('selectdocument');	    
	 var selIndex = selObj.selectedIndex;	
     var documentId=selObj.options[selIndex].value;		
	 var iwidth = (document.getElementById('submain').offsetWidth-10);	
//	 var url =contextpath+"/RequestHandlerServlet";
//	 var params = "servlet_name=AdminServlet&callbackmethod_name=doGetSelectedDocuments&module_id="+moduleId+"&bomWidth="+iwidth+"&entityrights_id="+entity;		
//	 var myAjax = new Ajax.Updater("document_list",url,{method: 'POST',parameters:params});
	 jQuery('#sub_document_list').html("");
	 var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'EntityServlet' , callbackmethod_name : 'doGetSelectedDocuments',document_id:documentId,entityrights_id:entity},
		dataType: "html"
		});
		request.done(function(html) {
			
			jQuery('#sub_document_list').html(html);
			
		});
 	}
 	
		
}

function checkUncheckRights() {
    var c = document.getElementsByTagName('input');
    var cbr=document.getElementById('cb_rights').checked;
    
    for (var i = 0; i < c.length; i++) {
        if (c[i].type == 'checkbox') {
            c[i].checked = cbr;
        }
    }
}
function updateEntityRights(){
	
	
	var objEntity = document.getElementById('selectentity');    
    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
  
    var objModule = document.getElementById('selectdocument');    
    var selectedModuleValue = objModule.options[objModule.selectedIndex].value;
        
    if(selectedEntityValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ENTITY;//"Please select valid entity!";
    	showResponseMsg('err-msg','succ-msg');
    	objEntity.focus();
    	return false;
    }else if(selectedModuleValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_MODULE;//"Please select valid module!";
    	showResponseMsg('err-msg','succ-msg');
    	objModule.focus();
    	return false;
    }         	
    else{
    	validateDocuments();
    	if(checkSelect=="")
    	{
    	document.getElementById('selected_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('selected_documents').value=checkSelect;
    	}    	
    	
     	document.getElementById('entityrights_id').value=selectedEntityValue
    	document.getElementById('document_id').value=selectedModuleValue;    	
    	document.getElementById('servlet_name').value="EntityServlet";
		document.getElementById('callbackmethod_name').value="doUpdateEntityRights";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return true;
    }
}

function check(){
	
	if(iProfile == 1){
		var entityId=document.getElementById("entity_id_for_alert").value;
		var entityName="HO";
		if(entityId==2){
			entityName="Customer";
		}
		if(entityId==3){
			entityName="Factory";
		}
		
		bootbox.confirm("Do you wish to apply the Entity Permission to ALL PROFILES of this "+entityName+" Entity?", function(result) {
			 if(result){
//		if(confirm("Do you wish to apply the Entity Permission to ALL PROFILES of this "+entityName+" Entity?"))
//		{
			var objEntity = document.getElementById('selectentity');    
		    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
		    
		    
		    var url =contextpath+"/RequestHandlerServlet";
			
			var request = jQuery.ajax({
			url: url,
			type: "POST",
			data: {servlet_name : 'EntityServlet' , callbackmethod_name : 'doUpdateProfileAtEntityUpdate',entity_id:selectedEntityValue},
			dataType: "html"
			});
			request.done(function(html) {
				displaySuccessProfile(html);
				
			});
		    
//			var url =contextpath+"/RequestHandlerServlet";
//			var params = "servlet_name=EntityServlet&callbackmethod_name=doUpdateProfileAtEntityUpdate&entity_id="+selectedEntityValue;
//			var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displaySuccessProfile,onFailure:displayError});
		}
		});
	}
}
function displaySuccessProfile(message){		
	
	var data = message.split("#####");
	if(data[1]=="true"){
		
		var entityId=document.getElementById("entity_id_for_alert").value;
		var entityName="HO";
		if(entityId==2){
			entityName="Customer";
		}
		if(entityId==3){
			entityName="Factory";
		}
//		if(confirm("Profiles Permissions are updated successfully! Do you wish to apply the Entity Permission to ALL USERS of this "+entityName+" Entity?")){
		bootbox.confirm("Profiles Permissions are updated successfully! Do you wish to apply the Entity Permission to ALL USERS of this "+entityName+" Entity?", function(result) {
			 if(result){
			
			 var url =contextpath+"/RequestHandlerServlet";
				
				var request = jQuery.ajax({
				url: url,
				type: "POST",
				data: {servlet_name : 'EntityServlet' , callbackmethod_name : 'doUpdateUserAtEntityUpdate',entity_id:data[0]},
				dataType: "html"
				});
				request.done(function(html) {
					displaySuccessUser(html);
					
				});
			
//			var url =contextpath+"/RequestHandlerServlet";
//			var params = "servlet_name=AdminServlet&callbackmethod_name=doUpdateUserAtEntityUpdate&entity_id="+data[0];
//			var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displaySuccessUser,onFailure:displayError});
		}
		});
	}else{
		document.getElementById('err-msg').innerHTML = "Profile Updation Failed!";
		showResponseMsg('err-msg','succ-msg');
	}
}
function displaySuccessUser(message){		
//	var message = request.responseText;
	var data = message.split("#####");
	if(data[1]=="true"){
		document.getElementById('succ-msg').innerHTML = "Users permissions are updated successfully!";
		showResponseMsg('err-msg','succ-msg');
	}
}