
//Material Master

function saveMaterial(mode,saveType)
{
//if(validateMaterial()){
	document.getElementById('mode').value=mode;
	document.getElementById('save_type').value=saveType;
	document.getElementById('request_type').value="Normal";
	document.getElementById('invoke_class').value="com.alpha.tpcsfashion.servlet.MaterialMasterServlet";
	document.getElementById('invoke_method').value="doSaveMaterialMaster";
	document.forms[0].action=contextpath+"/FileUploadServlet";
	
	if(navigator.appName == "Microsoft Internet Explorer"){
		document.forms[0].encoding="multipart/form-data";
	}
	else{
		document.forms[0].enctype="multipart/form-data";	
	}
	document.forms[0].method="POST";
	document.getElementById('validation_btn').click();

//}

}

function changePageNumbersinMaterialMaster(pageno){	
	
	document.getElementById('servlet_name').value="MaterialMasterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayMaterialMaster";
	document.getElementById('request_type').value="Search";
	document.getElementById('pageno').value=pageno;
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}


function displayMaterialActionList(actionType,pageno,matId)
{
	if(actionType== 1){
		
		deleteMaterialMaster(matId,pageno);
	}
	else if(actionType==2){
		
		editMaterialMaster('e',matId);
	}else
		{
		//DeActiveOperation(opId,pageno)
		}
	
}

function editMaterialMaster(editmode,matId)
{
	document.getElementById('mode').value=editmode;
	document.getElementById('mat_id').value=matId;
	document.getElementById('servlet_name').value="MaterialMasterServlet";
	document.getElementById('callbackmethod_name').value="doEditMaterialMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}



function deleteMaterialMaster(matId,pageno){	
	
	bootbox.confirm(localizedStrings.SURE_TO_DELETE_ITEM, function(result) {
		if(result){
		document.getElementById('pageno').value=pageno;
		document.getElementById('mat_id').value=matId;
		document.getElementById('servlet_name').value="MaterialMasterServlet";
		document.getElementById('callbackmethod_name').value="doDeleteMaterialMaster";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
	});
}


function searchMaterialMaster()
{
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="MaterialMasterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayMaterialMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}


function clearMaterialMaster(){
	document.getElementById('group_name').value="";
	document.getElementById('group_id').value="";
	document.getElementById('mat_code').value="";
	
	document.getElementById('mat_name').value="";
}
//	document.getElementById('spec1').value="";
//	document.getElementById('spec2').value="";
//	document.getElementById('spec3').value="";
//	document.getElementById('spec4').value="";
//	document.getElementById('spec5').value="";
//
//	document.getElementById('spec6').value="";
//
//	document.getElementById('spec7').value="";
//	document.getElementById('basic_unit').value="";
//	document.getElementById('stock_location').value="";
//	document.getElementById('bin_no').value="";
//	document.getElementById('reorder_level').value="";
//	document.getElementById('standard_rate').value="";
//	document.getElementById('selling_price').value="";
//	document.getElementById('excess_allowance').value="";


function activeBulkMaterialMaster(pageNo,active_mode)  
{
	
	var cnt = jQuery("input[name='bulkmatId']:checked").length;
	if (cnt < 1) 
	{
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
		return false;
	} 	
	else{
		bootbox.confirm(localizedStrings.SURE_TO_CHANGE_BULKACTION, function(result) {
			if(result){
				document.getElementById('pageno').value=pageNo;
				document.getElementById('active_mode').value=active_mode;
				document.getElementById('servlet_name').value="MaterialMasterServlet";
				document.getElementById('callbackmethod_name').value="doBulkActionActiveMaterialMaster";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();	
			} 
		});
	}

}



function deleteBulkMaterialMaster(pageno)
{
	
	 var cnt = jQuery("input[name='bulkmatId']:checked").length;
     if (cnt < 1) 
     {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
		return false;
     } 	
	else{

		bootbox.confirm("Are you sure you want to delete?", function(result) {
			if(result){
				document.getElementById('servlet_name').value="MaterialMasterServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBulkMaterialMaster";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}

}
function deleteMaterialAttachment(idx,matId,fileName,type){

	 bootbox.confirm(localizedStrings.DELETE_ATTACHMENT, function(result) {
		 if(result){
				if(type==1){
		document.getElementById("ajax-loader").style.display="block";
				}
				else if(type==2){
					document.getElementById("img-ajax-loader").style.display="none";
				}

		var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'MaterialMasterServlet' , callbackmethod_name : 'doDeleteAttachment',material_id:matId , file_name:fileName,idx:idx},
		dataType: "html"
		});
		request.done(function(msg) {
		
			var msg=msg.split('&1');
			if(msg[0]=='1')
				{
				if(type==1){	
				deleteTableRow("attach_file"+msg[1]);
				document.getElementById("ajax-loader").style.display="none";
				}
				else if(type==2){
					jQuery('#images_'+idx).remove();
					document.getElementById("img-ajax-loader").style.display="none";
				}
				
				
				document.getElementById('succ-msg').innerHTML=localizedStrings.ATTACHMENT_DELETED;//"Attachment deleted successfully.";
				showResponseMsg('err-msg','succ-msg');
					
				}
			else{
				if(type==1){
					document.getElementById("ajax-loader").style.display="block";
							}
							else if(type==2){
								document.getElementById("img-ajax-loader").style.display="none";
							}
				
				document.getElementById('err-msg').innerHTML=localizedStrings.ATTACHMENT_NOT_DELETED;//"Attachment not deleted.";
				showResponseMsg('err-msg','succ-msg');
			}
		});
			request.fail(function(jqXHR, textStatus) {
				alert( "Request failed: " + textStatus );
				document.getElementById('errormessage').innerHTML=localizedStrings.ATTACHMENT_NOT_DELETED;//"Attachment not deleted.";
				showResponseMsg('errormessage','succ-msg');
				if(type==1){
					document.getElementById("ajax-loader").style.display="block";
							}
							else if(type==2){
								document.getElementById("img-ajax-loader").style.display="none";
							}
				});
		

			 }
	 }); 
}	


function getItemGroupData(groupId){
	
	 var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'MaterialMasterServlet', callbackmethod_name : 'doGetItemGroupData',
			group_id:groupId
			},
		dataType: "xml",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + jqXHR);
		},
		success: function(xml) {
			
			jQuery(xml).find('itemgroup_data').each(function(){
				 
				var groupType=jQuery(this).find('groupType').text();
				var itemTracking=jQuery(this).find('itemTracking').text();
				var expiryApplicable=jQuery(this).find('expiryApplicable').text();
				var allowNegativeStock=jQuery(this).find('allowNegativeStock').text();
				var issueWithIo=jQuery(this).find('issueWithIo').text(); 
				var taxCategory=jQuery(this).find('taxCategory').text();
				var sizeApplicable=jQuery(this).find('sizeApplicable').text();
				var colourApplicable=jQuery(this).find('colourApplicable').text();
				var reservationApplicable=jQuery(this).find('reservationApplicable').text();
				var inspectionRequired=jQuery(this).find('inspectionRequired').text();
				var barcodeRequired=jQuery(this).find('barcodeRequired').text();
				
				jQuery("#groupType").val(groupType);
				jQuery("#tax_category").val(taxCategory);
				
				if(groupType=='Finished Goods')
				{
					jQuery("#productDet").show();
				}else{
					jQuery("#productDet").hide();
				}
				
				if(allowNegativeStock==0){
					jQuery("#negative_stock_no").prop("checked", true)
				}
				else if(allowNegativeStock==1){
					jQuery("#negative_stock_yes").prop("checked", true)
				}
				else
				{
					jQuery("#allowNagative_stock").show();
				}
				
				if(itemTracking==0){
					jQuery("#item_traking_no").prop("checked", true)
				}
				else if(itemTracking==1){
					jQuery("#item_traking_yes").prop("checked", true)
				}
				else
				{
					jQuery("#itemTraking").show();	
				}
				
				if(expiryApplicable==0){
					jQuery("#expiry_no").prop("checked", true)
				}
				else if(expiryApplicable==1){
					jQuery("#expiry_yes").prop("checked", true)
				}
				else
				{
					jQuery("#expiryDate").show();	
				}
				if(issueWithIo==0){
					jQuery("#issueWithIo_no").prop("checked", true)
				}
				else if(issueWithIo==1){
					jQuery("#issueWithIo_yes").prop("checked", true)
				}
				else
				{
					jQuery("#issue_WithIo").show();	
				}
				if(sizeApplicable==0){
					jQuery("#sizeapplicble_no").prop("checked", true)
				}
				else if(sizeApplicable==1){
					jQuery("#sizeapplicble_yes").prop("checked", true)
				}
				else
				{
					jQuery("#sizeApplicble").show();	
				}
				if(colourApplicable==0){
					jQuery("#colorapplicble_no").prop("checked", true)
				}
				else if(colourApplicable==1){
					jQuery("#colorapplicble_yes").prop("checked", true)
				}
				else
				{
					jQuery("#colorApplicble").show();	
				}
				
				if(reservationApplicable==0){
					jQuery("#reservationapplicble_no").prop("checked", true)
				}
				else if(reservationApplicable==1){
					jQuery("#reservationapplicble_yes").prop("checked", true)
				}
				else
				{
					jQuery("#reservationApplicble").show();	
				}
				if(inspectionRequired==0){
					jQuery("#inspectionrequired_no").prop("checked", true)
				}
				else if(inspectionRequired==1){
					jQuery("#inspectionrequired_yes").prop("checked", true)
				}
				else
				{
					jQuery("#inspectionRequired").show();	
				}
				if(barcodeRequired==0){
					jQuery("#barcoderequired_no").prop("checked", true)
				}
				else if(barcodeRequired==1){
					jQuery("#barcoderequired_yes").prop("checked", true)
				}
				else
				{
					jQuery("#barcodeRequired").show();	
				}
				
				
			});
			 	
		}
	});
	
}





