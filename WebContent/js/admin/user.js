function display()
		{
		var iWidth = document.getElementById('main').offsetWidth;
		//document.getElementById('header').style.width=(iWidth-5)+"px";
		//document.getElementById('tblbody').style.width=(iWidth-5)+"px";	
		var selObj = document.getElementById('selectentity');
		var selIndex = selObj.selectedIndex;
        var entityype=selObj.options[selIndex].value;	
     	
		if((entityype=='1')||(entityype=='-1'))
		{			
		document.getElementById('custdisplay').style.display="none";
		document.getElementById('factdisplay').style.display="none";
		}
		else if(entityype=='2')
		{
		document.getElementById('custdisplay').style.display="block";
		document.getElementById('factdisplay').style.display="none";
		}
		else if(entityype=='3')
		{
		document.getElementById('custdisplay').style.display="none";
		document.getElementById('factdisplay').style.display="block";
		}
		}
function displayEdit()
{
		var selObj = document.getElementById('selectentity');
		var selIndex = selObj.selectedIndex;
        var entityype=selObj.options[selIndex].value;       		
		if((entityype=='1')||(entityype=='-1'))
		{			
		document.getElementById('custdisplay').style.display="none";
		document.getElementById('factdisplay').style.display="none";
		}
		else if(entityype=='2')
		{
		document.getElementById('custdisplay').style.display="block";
		document.getElementById('custdisplay').removeAttribute('style');
		document.getElementById('factdisplay').style.display="none";
		}
		else if(entityype=='3')
		{
		document.getElementById('custdisplay').style.display="none";
		document.getElementById('factdisplay').style.display="block";
		document.getElementById('factdisplay').removeAttribute('style');
		}
   
}
function selectUserEntity(iType)
		{
		var selObj = document.getElementById('selectentity');
		var selIndex = selObj.selectedIndex;
        var entityype=selObj.options[selIndex].value;
        	
		
		if((entityype=='1')||(entityype=='-1'))
		{
		document.getElementById('custdisplay').style.display="none";
		document.getElementById('factdisplay').style.display="none";	
		
		
        var factoryList = document.getElementById('selectfactory');
		for (iLoop = 0; iLoop< factoryList.options.length; iLoop++)
        {
	        if (factoryList.options[iLoop].value.trim() == -1)
	        {
	        factoryList.options[iLoop].selected = true;
	        break;
	        }
        }	
        
        var customerList = document.getElementById('selectcustomer');
		for (iLoop = 0; iLoop< customerList.options.length; iLoop++)
        {
	        if (customerList.options[iLoop].value.trim() == -1)
	        {
	        customerList.options[iLoop].selected = true;
	        break;
	        }
        }	
        var objCustomer = document.getElementById('selectcustomer');    
   		var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
       	var objFactory = document.getElementById('selectfactory');    
   		var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
   		document.getElementById('entity_id').value=entityype;
		document.getElementById('customer_id').value=selectedCustomerValue;
		document.getElementById('factory_id').value=selectedFactoryValue;
	
		}
		else if(entityype=='2')
		{
		 var factoryList = document.getElementById('selectfactory');
		for (iLoop = 0; iLoop< factoryList.options.length; iLoop++)
        {
	        if (factoryList.options[iLoop].value.trim() == -1)
	        {
	        factoryList.options[iLoop].selected = true;
	        break;
	        }
        }	
        
		
		document.getElementById('custdisplay').style.display="table-row";
		document.getElementById('factdisplay').style.display="none";
		var objCustomer = document.getElementById('selectcustomer');    
   		var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
       	var objFactory = document.getElementById('selectfactory');    
   		var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
   		document.getElementById('entity_id').value=entityype;
		document.getElementById('customer_id').value=selectedCustomerValue;
		document.getElementById('factory_id').value=selectedFactoryValue;
	
		
		}
		else if(entityype=='3')
		{
		  var customerList = document.getElementById('selectcustomer');
		for (iLoop = 0; iLoop< customerList.options.length; iLoop++)
        {
	        if (customerList.options[iLoop].value.trim() == -1)
	        {
	        customerList.options[iLoop].selected = true;
	        break;
	        }
        }	
		
		document.getElementById('custdisplay').style.display="none";
		document.getElementById('factdisplay').style.display="table-row";
		var objCustomer = document.getElementById('selectcustomer');    
   		var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
       	var objFactory = document.getElementById('selectfactory');    
   		var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
   		document.getElementById('entity_id').value=entityype;
		document.getElementById('customer_id').value=selectedCustomerValue;
		document.getElementById('factory_id').value=selectedFactoryValue;					
		}
		
		if((entityype!=-1)&&(iType!='2'))
		{
		populateRoles(entityype,document.getElementById('customer_id').value,document.getElementById('factory_id').value);
		populateProfiles(entityype);
		}
		if(iType=='2')
		{
		var iWidth = document.getElementById('main').offsetWidth;
		//document.getElementById('header').style.width=(iWidth-5)+"px";
		//document.getElementById('tblbody').style.width=(iWidth-5)+"px";			
		document.getElementById('servlet_name').value="UserServlet";
		document.getElementById('callbackmethod_name').value="doDisplayUsers";		
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();       
		}					
		
		}
			


function displayEntityUsers()
		{
		var iWidth = document.getElementById('main').offsetWidth;
		//document.getElementById('header').style.width=(iWidth-5)+"px";
		//document.getElementById('tblbody').style.width=(iWidth-5)+"px";	
		var selObj = document.getElementById('selectentity');
		var selIndex = selObj.selectedIndex;
        var entityype=selObj.options[selIndex].value;
        var objCustomer = document.getElementById('selectcustomer');    
   		var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
       	var objFactory = document.getElementById('selectfactory');    
   		var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
		document.getElementById('entity_id').value=entityype;
		document.getElementById('customer_id').value=selectedCustomerValue;
		document.getElementById('factory_id').value=selectedFactoryValue;			
		document.getElementById('servlet_name').value="UserServlet";		
		document.getElementById('callbackmethod_name').value="doDisplayUsers";		
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();  
		
		}
	
function displayrole_profile(){

	populateRoles(document.getElementById('ho_id').value,document.getElementById('lcustomer_id').value,document.getElementById('lfactory_id').value);
	populateProfiles(document.getElementById('ho_id').value);
	
}


function populateRoles(entityId,customerId,factoryId){
		
		var selectControl = document.getElementById('roles');	
		var i;	
		selectControl.length=1;
//		opLength=opLength+1;
//		for (i=1; i<=opLength; i++) {			
//			selectControl.remove(i);	
//			i--;
//			opLength--;
//		  }

		var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'UserServlet' , callbackmethod_name : 'doGetEntityRoles',entity_id:entityId,customer_id:customerId,factory_id:factoryId},
		dataType: "xml"
		});
		request.done(function(xml) {
			try{
				
				jQuery(xml).find('role').each(function(){
						id=jQuery(this).find('roleid').text();
						name=jQuery(this).find('rolename').text();
						selectControl.options[selectControl.options.length]=new Option(name, id);
					});
				
			}catch(err){
				document.getElementById('err-msg').innerHTML = "Invalid xml information!";
				showResponseMsg('err-msg','succ-msg');
			}
			
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});	
		
	
}

//function displayRoles(request){		
//	try{	
//		    var selectControl = document.getElementById('roles');	   
//			x=request.responseXML.documentElement.getElementsByTagName("role");
//				
//		for (i=0,j=1;i<x.length;i++,j++)
//		      {
//		    	 var id=x[i].getElementsByTagName("roleid");
//		    	 var name=x[i].getElementsByTagName("rolename");	
//		    	 	
//		    	 selectControl.options[j] = new Option(name[0].firstChild.nodeValue,id[0].firstChild.nodeValue);	    	
//		      }
//		}catch(err){
//			document.getElementById('errormessage').innerHTML = "Invalid xml information!";
//		}	
//}

function populateProfiles(entityId){
		
		var selectControl = document.getElementById('profiles');	
		var i;	
		selectControl.length=1;
//		opLength=opLength+1;
//		for (i=1; i<=opLength; i++) {			
//			selectControl.remove(i);	
//			i--;
//			opLength--;
//		  }
		var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'UserServlet' , callbackmethod_name : 'doGetEntityProfiles',entity_id:entityId},
		dataType: "xml"
		});
		request.done(function(xml) {
			try{
				
				jQuery(xml).find('profile').each(function(){
						id=jQuery(this).find('profileid').text();
						name=jQuery(this).find('profilename').text();
						selectControl.options[selectControl.options.length]=new Option(name, id);
					});
				
			}catch(err){
				document.getElementById('err-msg').innerHTML = "Invalid xml information!";
				showResponseMsg('err-msg','succ-msg');
			}
			
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});	
//		var url =contextpath+"/RequestHandlerServlet";
//		var params = "servlet_name=UserServlet&callbackmethod_name=doGetEntityProfiles&entity_id="+entityId;
//		var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:displayProfiles,onFailure:displayError});
	
}

//function displayProfiles(request){		
//	try{	
//		    var selectControl = document.getElementById('profiles');	   
//			x=request.responseXML.documentElement.getElementsByTagName("profile");		
//			 for (i=0,j=1;i<x.length;i++,j++)
//		      {
//		    	 var id=x[i].getElementsByTagName("profileid");
//		    	 var name=x[i].getElementsByTagName("profilename");	    	
//		    	 selectControl.options[j] = new Option(name[0].firstChild.nodeValue,id[0].firstChild.nodeValue);	    	
//		      }
//		}catch(err){
//			//document.getElementById('errormessage').innerHTML = "Invalid xml information!";
//		}	
//}


function displayCustomerRoles()
{
		var selObj = document.getElementById('selectentity');
		var selIndex = selObj.selectedIndex;
        var entityype=selObj.options[selIndex].value;
        var objCustomer = document.getElementById('selectcustomer');    
   		var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
       	var objFactory = document.getElementById('selectfactory');    
   		var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
   		if(entityype!=-1)
		{
		populateRoles(entityype,selectedCustomerValue,selectedFactoryValue);		
		}
}

function saveUser(){

	var username = document.getElementById('user_name');
	var firstname = document.getElementById('first_name');
	var lastname = document.getElementById('last_name');
	
	var objEntity = document.getElementById('selectentity');    
    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
    
    var objCustomer = document.getElementById('selectcustomer');    
    var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
    
    var objFactory = document.getElementById('selectfactory');    
    var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
    
     var objRole = document.getElementById('roles');    
    var selectedRoleValue = objRole.options[objRole.selectedIndex].value;
    
     var objProfile = document.getElementById('profiles');    
    var selectedProfileValue = objProfile.options[objProfile.selectedIndex].value;
    
     var objLanguage = document.getElementById('selectlanguage');    
    var selectedLanguage = objLanguage.options[objLanguage.selectedIndex].value;
            
    if(username.value.trim().length==0){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_USER_NAME;//"Please enter valid user name!";
		showResponseMsg('err-msg','succ-msg');
		username.focus();
		return false;
	}else if(!isValidEmail(username.value)){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_USER_NAME;//"Please enter valid user name!";
		showResponseMsg('err-msg','succ-msg');
		username.focus();
		return false;
	}
	else if(firstname.value.trim().length==0){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_FIRST_NAME;//"Please enter valid first name!";
		showResponseMsg('err-msg','succ-msg');
		firstname.focus();
		return false;
	}else if(lastname.value.trim().length==0){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_LAST_NAME;//"Please enter valid last name!";
		showResponseMsg('err-msg','succ-msg');
		lastname.focus();
		return false;
	}
	else if(selectedEntityValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ENTITY;//"Please select valid entity!";
    	showResponseMsg('err-msg','succ-msg');
    	objEntity.focus();
    	return false;
    }
	else if((selectedEntityValue==2)&&(selectedCustomerValue==-1)){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_CUSTOMER;//"Please select valid customer!";
		showResponseMsg('err-msg','succ-msg');
		objCustomer.focus();
		return false;
	}
	else if((selectedEntityValue==3)&&(selectedFactoryValue==-1)){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_FACTORY;//"Please select valid factory!";
		showResponseMsg('err-msg','succ-msg');
		objFactory.focus();
		return false;
	}
	else if(selectedRoleValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ROLE;//"Please select valid role!";
    	showResponseMsg('err-msg','succ-msg');
    	objRole.focus();
    	return false;
    }
    else if(selectedProfileValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_PROFILE;//"Please select valid profile!";
    	showResponseMsg('err-msg','succ-msg');
    	objProfile.focus();
    	return false;
    }
     else if(selectedLanguage==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_LANGUAGE;
    	showResponseMsg('err-msg','succ-msg');
    	objLanguage.focus();
    	return false;
    }
	else{	
		
		updateUserProfile();
		
		document.getElementById('entity_id').value=selectedEntityValue;
		document.getElementById('customer_id').value=selectedCustomerValue;
		document.getElementById('factory_id').value=selectedFactoryValue;
		document.getElementById('role_id').value=selectedRoleValue;
		document.getElementById('profile_id').value=selectedProfileValue;
		document.getElementById('language_id').value=selectedLanguage;
		document.getElementById('callbackmethod_name').value="doSaveUser";		
		document.getElementById('servlet_name').value="UserServlet";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();			
		return true;
		}
}



function profileInfo(iType){

	var objEntity = document.getElementById('selectentity');    
    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;   
    var objProfile = document.getElementById('profiles');    
    var selectedProfileValue = objProfile.options[objProfile.selectedIndex].value;   
      
    if(selectedEntityValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ENTITY;//"Please select valid entity!";
    	showResponseMsg('err-msg','succ-msg');
    	objEntity.focus();
    }
	else  if(selectedProfileValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_PROFILE;//"Please select valid profile!";
    	showResponseMsg('err-msg','succ-msg');
    	objProfile.focus();
    }	
	else
	{
		
		document.getElementById('profile_id').value=selectedProfileValue;
		
	if(iType=='2')
	{
	if(document.getElementById('user_profile_id').value==selectedProfileValue)
	{
//	var userId=document.getElementById('user_id').value;
//	var strFeatures="toolbar=no,status=no,menubar=no,left=150,top=100,location=no,scrollbars=no,resizable=no,height=500,width=700";
//	window.open(contextpath+"/RequestHandlerServlet?servlet_name=UserServlet&callbackmethod_name=doDisplayProfileDetails&profile_id="+selectedProfileValue+"&type="+2+"&user_id="+userId,"hnd14",strFeatures).focus();
		document.getElementById('itype').value=2;
		profileInfoList();
	}
	else
	{
//	var strFeatures="toolbar=no,status=no,menubar=no,left=150,top=100,location=no,scrollbars=no,resizable=no,height=500,width=700";
//	window.open(contextpath+"/RequestHandlerServlet?servlet_name=UserServlet&callbackmethod_name=doDisplayProfileDetails&profile_id="+selectedProfileValue+"&type="+1+"&user_id="+0,"hnd14",strFeatures).focus();
		document.getElementById('itype').value=1;
		profileInfoList();
	}
	}
	else
	{
//	var strFeatures="toolbar=no,status=no,menubar=no,left=150,top=100,location=no,scrollbars=no,resizable=no,height=500,width=700";
//	window.open(contextpath+"/RequestHandlerServlet?servlet_name=UserServlet&callbackmethod_name=doDisplayProfileDetails&profile_id="+selectedProfileValue+"&type="+1+"&user_id="+0,"hnd14",strFeatures).focus();
		document.getElementById('itype').value=1;
		profileInfoList();
	}
	
	}

}


function profileInfoList(){
	
        var profileid=document.getElementById('profile_id').value;	
        var userid=document.getElementById('user_id').value;	
        var type=document.getElementById('itype').value;	        	
//		var iwidth = (document.getElementById('submain').offsetWidth-10);
		
		var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'UserServlet' , callbackmethod_name : 'doGetProfileDocuments',profile_id:profileid,user_id:userid,itype:type},
		dataType: "html"
		});
		request.done(function(html) {
			try{
				
				jQuery('#profile_document_list').html(html);				
			}catch(err){
				document.getElementById('err-msg').innerHTML = "Invalid xml information!";
				showResponseMsg('err-msg','succ-msg');
		    	
			}
			
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});	
		
//		var url =contextpath+"/RequestHandlerServlet";
//		var params = "servlet_name=UserServlet&callbackmethod_name=doGetProfileDocuments&profile_id="+profileid+"&bomWidth="+iwidth+"&user_id="+userid+"&itype="+type;		
//		var myAjax = new Ajax.Updater("profile_document_list",url,{method: 'POST',parameters:params});
 
}

function updateUserProfile(){
    
   if(!validateDocuments()){    	
        	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_SCREEN_NAME;//"Please select at least one screen name!";
        	showResponseMsg('err-msg','succ-msg');
        	return false;          	
    }
    else{
        getProfileDocuments();   
    	
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
    	}
    	
//    	opener.document.forms[0].entry_documents.value =document.getElementById('entry_documents').value;
//    	opener.document.forms[0].edit_documents.value =document.getElementById('edit_documents').value;
//    	opener.document.forms[0].delete_documents.value =document.getElementById('delete_documents').value;
//    	opener.document.forms[0].view_documents.value =document.getElementById('view_documents').value;
//    	opener.document.forms[0].excel_documents.value =document.getElementById('excel_documents').value;
//    	opener.document.forms[0].print_documents.value =document.getElementById('print_documents').value;
//    	opener.document.forms[0].appr_documents.value =document.getElementById('appr_documents').value;
//    	
//		self.close();
    	
    }

    
    function changeUserPageNumbers(pageno){
	var objEntity = document.getElementById('selectentity');    
    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
    
    var objCustomer = document.getElementById('selectcustomer');    
    var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
    
    var objFactory = document.getElementById('selectfactory');    
    var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
    document.getElementById('entity_id').value=selectedEntityValue;
    document.getElementById('customer_id').value=selectedCustomerValue;
    document.getElementById('factory_id').value=selectedFactoryValue;
	document.getElementById('servlet_name').value="UserServlet";
	document.getElementById('callbackmethod_name').value="doDisplayUsers";
	document.getElementById('pageno').value=pageno;
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}



function backViewUser(iType){
	
	if(iType==1)
	{
	var objEntity = document.getElementById('selectentity');    
    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;       
    var objCustomer = document.getElementById('selectcustomer');    
    var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;    
    var objFactory = document.getElementById('selectfactory');    
    var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;	
	document.getElementById('entity_id').value=selectedEntityValue;
	document.getElementById('customer_id').value=selectedCustomerValue;
	document.getElementById('factory_id').value=selectedFactoryValue;
	}
	
	document.getElementById('servlet_name').value="UserServlet";
	document.getElementById('callbackmethod_name').value="doDisplayUsers";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}

function deleteUser(iPageNO,userId){	
	bootbox.confirm("Are you sure you want to delete?", function(result) {
		 if(result){
//	if(confirm("Are you sure you want to delete?")){
		var objEntity = document.getElementById('selectentity');    
    	var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
     	var objCustomer = document.getElementById('selectcustomer');    
   		var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
       	var objFactory = document.getElementById('selectfactory');    
   		var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
   		
   		document.getElementById('pageno').value=iPageNO;
		document.getElementById('entity_id').value=selectedEntityValue;
		document.getElementById('customer_id').value=selectedCustomerValue;
		document.getElementById('factory_id').value=selectedFactoryValue;
		document.getElementById('user_id').value=userId;
		document.getElementById('servlet_name').value="UserServlet";
		document.getElementById('callbackmethod_name').value="doDeleteUser";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}	
	});
	
}


function editUser(iPageNO,userId){
	
	var objEntity = document.getElementById('selectentity');    
    	var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
     	var objCustomer = document.getElementById('selectcustomer');    
   		var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
       	var objFactory = document.getElementById('selectfactory');    
   		var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
		document.getElementById('entity_id').value=selectedEntityValue;
		document.getElementById('customer_id').value=selectedCustomerValue;
		document.getElementById('factory_id').value=selectedFactoryValue;
	
	
	document.getElementById('pageno').value=iPageNO;
	document.getElementById('user_id').value=userId;
	document.getElementById('servlet_name').value="UserServlet";
	document.getElementById('callbackmethod_name').value="doEditUser";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}


function updateUser(){

	var username = document.getElementById('user_name');
	var firstname = document.getElementById('first_name');
	var lastname = document.getElementById('last_name');
	
	var objEntity = document.getElementById('selectentity');    
    var selectedEntityValue = objEntity.options[objEntity.selectedIndex].value;
    
    var objCustomer = document.getElementById('selectcustomer');    
    var selectedCustomerValue = objCustomer.options[objCustomer.selectedIndex].value;
    
    var objFactory = document.getElementById('selectfactory');    
    var selectedFactoryValue = objFactory.options[objFactory.selectedIndex].value;
    
     var objRole = document.getElementById('roles');    
    var selectedRoleValue = objRole.options[objRole.selectedIndex].value;
    
     var objProfile = document.getElementById('profiles');    
    var selectedProfileValue = objProfile.options[objProfile.selectedIndex].value;
    
    var objLanguage = document.getElementById('selectlanguage');    
    var selectedLanguage = objLanguage.options[objLanguage.selectedIndex].value;
    
    
    
    if(username.value.trim().length==0){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_USER_NAME;//"Please enter valid user name!";
		showResponseMsg('err-msg','succ-msg');
		username.focus();
		return false;
	}else if(!isValidEmail(username.value)){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_USER_NAME;//"Please enter valid user name!";
		showResponseMsg('err-msg','succ-msg');
		username.focus();
		return false;
	}
	else if(firstname.value.trim().length==0){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_FIRST_NAME;//"Please enter valid first name!";
		showResponseMsg('err-msg','succ-msg');
		firstname.focus();
		return false;
	}else if(lastname.value.trim().length==0){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_LAST_NAME;//"Please enter valid last name!";
		showResponseMsg('err-msg','succ-msg');
		lastname.focus();
		return false;
	}
	else if(selectedEntityValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ENTITY;//"Please select valid entity!";
    	showResponseMsg('err-msg','succ-msg');
    	objEntity.focus();
    	return false;
    }
	else if((selectedEntityValue==2)&&(selectedCustomerValue==-1)){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_CUSTOMER;//"Please select valid customer!";
		showResponseMsg('err-msg','succ-msg');
		objCustomer.focus();
		return false;
	}
	else if((selectedEntityValue==3)&&(selectedFactoryValue==-1)){		
		document.getElementById('err-msg').innerHTML=localizedStrings.VALID_FACTORY;//"Please select valid factory!";
		showResponseMsg('err-msg','succ-msg');
		objFactory.focus();
		return false;
	}
	else if(selectedRoleValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ROLE;//"Please select valid role!";
    	showResponseMsg('err-msg','succ-msg');
    	objRole.focus();
    	return false;
    }
    else if(selectedProfileValue==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_PROFILE;//"Please select valid profile!";
    	showResponseMsg('err-msg','succ-msg');
    	objProfile.focus();
    	return false;
    }
     else if(selectedLanguage==-1){
    	document.getElementById('err-msg').innerHTML=localizedStrings.VALID_LANGUAGE;
    	showResponseMsg('err-msg','succ-msg');
    	objLanguage.focus();
    	return false;
    }
	else{	
		updateUserProfile();
		document.getElementById('entity_id').value=selectedEntityValue;
		document.getElementById('customer_id').value=selectedCustomerValue;
		document.getElementById('factory_id').value=selectedFactoryValue;
		document.getElementById('role_id').value=selectedRoleValue;
		document.getElementById('profile_id').value=selectedProfileValue;
		document.getElementById('language_id').value=selectedLanguage;
		document.getElementById('callbackmethod_name').value="doUpdateUser";		
		document.getElementById('servlet_name').value="UserServlet";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();			
		return true;
		}
}




function changeProfile(){	
	document.getElementById('profile_status').value=1;	

}
	
	function clearUser(iType){
	
	if(iType==1){
		document.getElementById('user_name').value="";
		document.getElementById('first_name').value="";	
		document.getElementById('last_name').value="";	
		var entityList = document.getElementById('selectentity');
		for (iLoop = 0; iLoop< entityList.options.length; iLoop++)
        {
	        if (entityList.options[iLoop].value.trim() == -1)
	        {
	        entityList.options[iLoop].selected = true;
	        break;
	        }
        }	
        
        var factoryList = document.getElementById('selectfactory');
		for (iLoop = 0; iLoop< factoryList.options.length; iLoop++)
        {
	        if (factoryList.options[iLoop].value.trim() == -1)
	        {
	        factoryList.options[iLoop].selected = true;
	        break;
	        }
        }	
        
        var customerList = document.getElementById('selectcustomer');
		for (iLoop = 0; iLoop< customerList.options.length; iLoop++)
        {
	        if (customerList.options[iLoop].value.trim() == -1)
	        {
	        customerList.options[iLoop].selected = true;
	        break;
	        }
        }
        
        var roleList = document.getElementById('roles');
		for (iLoop = 0; iLoop< roleList.options.length; iLoop++)
        {
	        if (roleList.options[iLoop].value.trim() == -1)
	        {
	        roleList.options[iLoop].selected = true;
	        break;
	        }
        }
        
         var profileList = document.getElementById('profiles');
		for (iLoop = 0; iLoop< profileList.options.length; iLoop++)
        {
	        if (profileList.options[iLoop].value.trim() == -1)
	        {
	        profileList.options[iLoop].selected = true;
	        break;
	        }
        }
        document.getElementById('custdisplay').style.display="none";
		document.getElementById('factdisplay').style.display="none";	
	}
	else{

		document.getElementById('user_name').value=document.getElementById('tmp_user_name').value;
		document.getElementById('first_name').value=document.getElementById('tmp_first_name').value;
		document.getElementById('last_name').value=document.getElementById('tmp_last_name').value;
		
		var entityype=document.getElementById('tmp_entity_id').value;		
		if((entityype=='1')||(entityype=='-1'))
		{			
		document.getElementById('custdisplay').style.display="none";
		document.getElementById('factdisplay').style.display="none";
		}
		else if(entityype=='2')
		{
		document.getElementById('custdisplay').style.display="block";
		document.getElementById('factdisplay').style.display="none";
		}
		else if(entityype=='3')
		{
		document.getElementById('custdisplay').style.display="none";
		document.getElementById('factdisplay').style.display="block";
		}	
		populateRoles(entityype,document.getElementById('tmp_customer_id').value,document.getElementById('tmp_factory_id').value);
		populateProfiles(entityype);	 
  	  
     }
	}
	
	function resetUserPassword(userName){
		
var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'UserServlet' , callbackmethod_name : 'doResetUserPassword',user_name:userName},
		dataType: "html"
		});
		request.done(function(html) {
			try{
				var message = html;
				if(message=='Reset'){
				document.getElementById('succ-msg').innerHTML="Password reset succeeded and mail sent to user.";
				showResponseMsg('err-msg','succ-msg');
				}
				else{
					document.getElementById('err-msg').innerHTML="Password reset failured.";
					showResponseMsg('err-msg','succ-msg');
				}
				
			}catch(err){
				document.getElementById('err-msg').innerHTML = "Password reset failured.";
				showResponseMsg('err-msg','succ-msg');
			}
			
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});	
		
//		var url=contextpath+"/RequestHandlerServlet?servlet_name=AdminServlet&callbackmethod_name=doResetUserPassword";		
//		var params = "user_name="+userName;	
//		var myAjax = new Ajax.Request(url,{method: 'POST',parameters:params,onSuccess:passwordResetSuccess,onFailure:passwordResetFailure});

	}

	function validateUserPassword(){
			var oldPass = document.getElementById('old_password');
			var newPass = document.getElementById('new_password').value.trim();
			var confirmPass = document.getElementById('confirm_password').value.trim();
			var secQuestion = document.getElementById('security_question');
			var secuAnswer = document.getElementById('answer');
			var days = document.getElementById('days_to_exp');
						
			if(oldPass.value.trim().length== 0){
			document.getElementById('err-msg').innerHTML=localizedStrings.VALID_PASSWORD;//"Enter the correct password!";
			showResponseMsg('err-msg','succ-msg');
			oldPass.focus();
			return false;
			}
			else if(newPass != confirmPass){
			document.getElementById('err-msg').innerHTML=localizedStrings.COMFIRM_PASSWORD;//"Confirm password!";
			showResponseMsg('err-msg','succ-msg');
			return false;
			}
			else if(secQuestion.value.trim().length == 0){
			document.getElementById('err-msg').innerHTML=localizedStrings.VALID_QUESTION;//"Enter the right question!";
			showResponseMsg('err-msg','succ-msg');
			secQuestion.focus();
			return false;
			}else if(secuAnswer.value.trim().length == 0){
			document.getElementById('err-msg').innerHTML=localizedStrings.VALID_ANSWER;//"Enter the answer!";
			showResponseMsg('err-msg','succ-msg');
			secuAnswer.focus();
			return false;
			}else if(days.value.trim().length == 0){
			document.getElementById('err-msg').innerHTML=localizedStrings.VALID_DAYS;//"Enter valid password expiry days!";
			showResponseMsg('err-msg','succ-msg');
			days.focus();
			return false;
			}else if(isNaN(days.value.trim())){
			document.getElementById('err-msg').innerHTML=localizedStrings.VALID_DAYS;//"Enter valid password expiry days!";
			showResponseMsg('err-msg','succ-msg');
			document.getElementById('days_to_exp').focus();
			return false;
			}
			return true;
	}

	function saveUsersPassword()
	{
		if(validateUserPassword()){
			document.getElementById('servlet_name').value="UserServlet";
			document.getElementById('callbackmethod_name').value="doSavePassword";
			document.forms[0].action=contextpath+"/RequestHandlerServlet";
			document.forms[0].method="POST";
			document.forms[0].submit();
		}
	}
