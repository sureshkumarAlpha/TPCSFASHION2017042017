
function getentitydocumentlist(selectedIndex,selectedValue){
	if(selectedIndex>0){		
        var selObj = document.getElementById('selectentity');
        var profileId= document.getElementById('profile_id').value;
        
		var selIndex = selObj.selectedIndex;	
        var entityid=selObj.options[selIndex].value;
        
        var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'ProfileServlet' , callbackmethod_name : 'doGetEntityDocuments',entity_id:entityid,profile_id:profileId},
		dataType: "html"
		});
		request.done(function(html) {
			
			jQuery('#entity_document_list').html(html);
			
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});
        
//		var url =contextpath+"/RequestHandlerServlet";
//		var params = "servlet_name=AdminServlet&callbackmethod_name=doGetEntityDocuments&entity_id="+entityid;		
//		var myAjax = new Ajax.Updater("entity_document_list",url,{method: 'POST',parameters:params});
 	} 	
 	else if(selectedIndex==0)
 	{
 	document.getElementById('entity_document_list').innerHTML="";
 	}	
 	
}

function saveProfile(){
	
	
	var objEntity = document.getElementById('selectentity');    
    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
   
    if(document.getElementById('profile_name').value==""){
    document.getElementById('err-msg').innerHTML=localizedStrings.VALID_PROFILE;//"Please enter a profile name!";
    showResponseMsg('err-msg','succ-msg');
    document.getElementById('profile_name').focus();
    	return false;
    }else if(selectedEntityValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ENTITY;//"Please select valid entity!";
    	showResponseMsg('err-msg','succ-msg');
    	objEntity.focus();
    	return false;
    }
    else if(!validateDocuments()){    	
        	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_SCREEN;//"Please select at least one screen name!";
        	showResponseMsg('err-msg','succ-msg');
        	return false;          	
    }
    else{
        getProfileDocuments();       
    	document.getElementById('entity_id').value=selectedEntityValue;
    	if(entrySelect=="")
    	{
    	document.getElementById('entry_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('entry_documents').value=entrySelect;
    	}
    	if(editSelect=="")
    	{
    	document.getElementById('edit_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('edit_documents').value=editSelect;
    	}
    	if(deleteSelect=="")
    	{
    	document.getElementById('delete_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('delete_documents').value=deleteSelect;
    	}
    	if(viewSelect=="")
    	{
    	document.getElementById('view_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('view_documents').value=viewSelect;
    	}
    	if(excelSelect=="")
    	{
    	document.getElementById('excel_documents').value="0"
    	}
    	else
    	{
    	document.getElementById('excel_documents').value=excelSelect;
    	}
    	if(printSelect=="")
    	{    		
    		document.getElementById('print_documents').value="0";
    	}
    	else
    	{
    		document.getElementById('print_documents').value=printSelect;
    	}
    	if(apprSelect=="")
    	{
    	document.getElementById('appr_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('appr_documents').value=apprSelect;
    	}
    	
    	
    	document.getElementById('servlet_name').value="ProfileServlet";
		document.getElementById('callbackmethod_name').value="doSaveProfile";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return true;
    }
	
}

function changeProfilePageNumbers(pageno){
	
	document.getElementById('servlet_name').value="ProfileServlet";
	document.getElementById('callbackmethod_name').value="doDisplayProfile";
	document.getElementById('pageno').value=pageno;
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}


function deleteProfile(iPageNO,entityid,profileid){
	bootbox.confirm("Are you sure you want to delete selected Profile?", function(result) {
		 if(result){
//	if(confirm("Are you sure you want to delete selected Profile?")){
		document.getElementById('pageno').value=iPageNO;
		document.getElementById('profile_id').value=profileid;	
		document.getElementById('servlet_name').value="ProfileServlet";
		document.getElementById('callbackmethod_name').value="doDeleteProfile";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
		 });
}
function editProfile(iPageNO,entityid,profileid){	
	var entityId = entityid;	    
	var profileId = profileid;
	var iwidth = (document.getElementById('main').offsetWidth-15);	
	var pageno = document.getElementById('pageno').value;
	var iType=1;

	document.getElementById('servlet_name').value="ProfileServlet";	
    document.getElementById('callbackmethod_name').value="doEditProfile";   
	document.forms[0].action=contextpath+"/RequestHandlerServlet?entity_id="+entityId+"&profile_id="+profileId+"&iType="+iType;
	document.forms[0].method="POST";
	document.forms[0].submit();
	
	/* var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'ProfileServlet' , callbackmethod_name : 'doGetEntitySelectedDocuments',entity_id:entityId,profile_id:profileId,iType:iType},
		dataType: "html"
		});
		request.done(function(html) {
			
//			var profileinfo = html;		
		    //document.getElementById('pageno').value="1";
//			document.getElementById('entity_document_list').value=profileinfo;
			
//			jQuery('#entity_document_list').val(html);
			
			document.getElementById('servlet_name').value="ProfileServlet";	
		    document.getElementById('callbackmethod_name').value="doEditProfile";   
			document.forms[0].action=contextpath+"/RequestHandlerServlet?entity_id="+entityId+"&profile_id="+profileId+"&iType="+iType;
			document.forms[0].method="POST";
			document.forms[0].submit();
			
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});*/
	
//	var url =contextpath+"/RequestHandlerServlet";
//	var params;
//	params = "servlet_name=AdminServlet&callbackmethod_name=doGetEntitySelectedDocuments&entity_id="+entityId+"&profile_id="+profileId+"&bomWidth="+iwidth+"&pageno="+pageno+"&iType="+iType;		
//	var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displayProfile,onFailure:displayError});
}


function updateProfile(){
	
	var objEntity = document.getElementById('selectentity');    
    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
   
    if(document.getElementById('profile_name').value==""){
    document.getElementById('err-msg').innerHTML=localizedStrings.VALID_PROFILE;//"Please enter a profile name!";
    showResponseMsg('err-msg','succ-msg');
    document.getElementById('profile_name').focus();
    	return false;
    }else if(selectedEntityValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ENTITY;//"Please select valid entity!";
    	showResponseMsg('err-msg','succ-msg');
    	objEntity.focus();
    	return false;
    }
    else if(!validateDocuments()){    	
        	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_SCREEN;//"Please select at least one screen name!";
        	showResponseMsg('err-msg','succ-msg');
        	return false;          	
    }
    else{
        getProfileDocuments(); 
        //alert(document.getElementById('profile_name').value);
        //document.getElementById('entity_id').value=document.getElementById('profile_name').value;      
    	document.getElementById('entity_id').value=selectedEntityValue;
    	if(entrySelect=="")
    	{
    	document.getElementById('entry_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('entry_documents').value=entrySelect;
    	}
    	if(editSelect=="")
    	{
    	document.getElementById('edit_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('edit_documents').value=editSelect;
    	}
    	if(deleteSelect=="")
    	{
    	document.getElementById('delete_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('delete_documents').value=deleteSelect;
    	}
    	if(viewSelect=="")
    	{
    	document.getElementById('view_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('view_documents').value=viewSelect;
    	}
    	if(excelSelect=="")
    	{
    	document.getElementById('excel_documents').value="0"
    	}
    	else
    	{
    	document.getElementById('excel_documents').value=excelSelect;
    	}
    	if(printSelect=="")
    	{    		
    		document.getElementById('print_documents').value="0";
    	}
    	else
    	{
    		document.getElementById('print_documents').value=printSelect;
    	}
    	if(apprSelect=="")
    	{
    	document.getElementById('appr_documents').value="0";
    	}
    	else
    	{
    	document.getElementById('appr_documents').value=apprSelect;
    	}
    	
    	
    	document.getElementById('servlet_name').value="ProfileServlet";
		document.getElementById('callbackmethod_name').value="doUpdateProfile";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return true;
		
    }
	
}
function backViewProfile(){
	
	document.getElementById('servlet_name').value="ProfileServlet";
	document.getElementById('callbackmethod_name').value="doDisplayProfile";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}

function clearUserProfile()
{
	setVal('profile','');
	setVal('entity','');
}

function clearProfile(iType){	
	
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
        
        document.getElementById('profile_name').value="";	
		document.getElementById('entity_document_list').innerHTML="";	 
	}
	else
	{
	document.getElementById('profile_name').value=document.getElementById('tmp_profile_name').value;
	document.getElementById('selectentity').value=document.getElementById('tmp_selectentity').value;
	var previousValue = document.getElementById('tmp_selectentity').value;
    var myDropdownList = document.forms[0].elements['selectentity'];
    
    for (var iLoop = 0; iLoop< myDropdownList.options.length; iLoop++)
    {
	    if (myDropdownList.options[iLoop].value.trim() == previousValue)
	    {   
		    myDropdownList.options[iLoop].selected = true;
		    break;
	    }    
    }   
    	var objEntity = document.getElementById('selectentity');    
    	var entityid = objEntity.options[objEntity.selectedIndex].value;     		
		var iwidth = (document.getElementById('submain').offsetWidth-10);
		var profile_id=document.getElementById('profile_id');	
		var pageno=document.getElementById('pageno');
		var iType=2;
		
		 var url =contextpath+"/RequestHandlerServlet";
			
			var request = jQuery.ajax({
			url: url,
			type: "POST",
			data: {servlet_name : 'ProfileServlet' , callbackmethod_name : 'doGetEntitySelectedDocuments',entity_id:entityid,profile_id:profile_id,iType:iType},
			dataType: "html"
			});
			request.done(function(html) {
				
				jQuery('#entity_document_list').html(html);
				
			});
			request.fail(function(jqXHR, textStatus) {
			alert( "Request failed: " + textStatus );
			});
		
//		var url =contextpath+"/RequestHandlerServlet";
//		var params = "servlet_name=ProfileServlet&callbackmethod_name=doGetEntitySelectedDocuments&entity_id="+entityid+"&profile_id="+profile_id+"&bomWidth="+iwidth+"&pageno="+pageno+"&iType="+iType;		
//		var myAjax = new Ajax.Updater("entity_document_list",url,{method: 'POST',parameters:params});
	
	}
		
}
function SearchProfile(){
	document.getElementById('pageno').value="1";
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="ProfileServlet";
	document.getElementById('callbackmethod_name').value="doProfileSearch";
	//document.getElementById('pageno').value="1";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}

