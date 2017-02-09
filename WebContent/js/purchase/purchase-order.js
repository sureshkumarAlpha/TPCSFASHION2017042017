function displayPOActionList(actionType,pageno,poId,poDetId) {

	
	if(actionType==1){
		viewPurchaseOrder('e',poId);
	}
	else if(actionType==2){
		editPurchaseOrder('e',poId);
	}
	else if(actionType==3){
		deletePurchaseOrder(pageno,poId,poDetId,0);
	}
	else if(actionType==5){
		cancelPurchaseOrder(pageno,poId);
	}
	else if(actionType==8){
		closePurchaseOrder(pageno,poId);
	}
}
function changePOPageNo(pageno){
	
	document.getElementById('servlet_name').value="PurchaseOrderServlet";
	document.getElementById('callbackmethod_name').value="doDisplayPurchaseOrder";
	document.getElementById('request_type').value="Search";
	document.getElementById('pageno').value=pageno;
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function searchPurchaseOrder()
{
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="PurchaseOrderServlet";
	document.getElementById('callbackmethod_name').value="doDisplayPurchaseOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
function viewPurchaseOrder(headMode,poId){
	document.getElementById('header_mode').value=headMode;
	document.getElementById('po_id').value=poId;
	document.getElementById('view_mode').value="yes";
	document.getElementById('servlet_name').value="PurchaseOrderServlet";
	document.getElementById('callbackmethod_name').value="doAddPurchaseOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function editPurchaseOrder(headMode,poId){
	document.getElementById('header_mode').value=headMode;
	document.getElementById('po_id').value=poId;
	document.getElementById('servlet_name').value="PurchaseOrderServlet";
	document.getElementById('callbackmethod_name').value="doAddPurchaseOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function deletePurchaseOrder(pageno,poId,poDetId,page){
    if(poId<=0){
	document.getElementById('warning-msg').innerHTML="Please save the transaction";
	showWarningMsg('warning-msg');
	 return false;
	 } 	
else{
	
	 bootbox.confirm("Are you sure you want to delete?", function(result) {
		 if(result){
			 
			    document.getElementById('servlet_name').value="PurchaseOrderServlet";
				document.getElementById('callbackmethod_name').value="doDeletePurchaseOrder";
				document.getElementById('po_id').value=poId;
				document.getElementById('po_det_id').value=poDetId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet?page="+page;	
				document.forms[0].method="POST";	
				document.forms[0].submit();	
		 }
		});
}
}
function deletePurchaseOrderDet(poId,poDetId){
    bootbox.confirm("Are you sure you want to delete?", function(result) {
	 if(result){
		    document.getElementById('servlet_name').value="PurchaseOrderServlet";
			document.getElementById('callbackmethod_name').value="doDeletePurchaseOrderDetail";
			document.getElementById('po_id').value=poId;
			document.getElementById('po_det_id').value=poDetId;
			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
			document.forms[0].method="POST";	
			document.forms[0].submit();	
	 }
});
}
function cancelPurchaseOrder(pageno,poId){
	
	bootbox.confirm("Are you sure you want to cancel?", function(result) {
		 if(result){
			 document.getElementById('servlet_name').value="PurchaseOrderServlet";
				document.getElementById('callbackmethod_name').value="doCancelPurchaseOrder";
				document.getElementById('po_id').value=poId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
		 }
	});
}
function closePurchaseOrder(pageno,poId){
	bootbox.confirm("Are you sure you want to close?", function(result) {
		 if(result){
			 document.getElementById('servlet_name').value="PurchaseOrderServlet";
				document.getElementById('callbackmethod_name').value="doClosePurchaseOrder";
				document.getElementById('po_id').value=poId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
		 }
	});
}
function clearNewPODetail(id){
		setVal('sl_no_0','');
		setVal('item_name_0','');
		setVal('item_id_0','');
		setVal('colour_name_0','');
		setVal('colour_id_0','');
		setVal('size_range_0','');
		setVal('size_range_id_0','');
		setVal('uom_0','');
		setVal('qty_0','');
		setVal('price_0','');
		setVal('qty_uom_0','');
		setVal('price_fcy_0','');
		setVal('remark1_0','');
		setVal('remark2_0','');
		setVal('remark3_0','');
		setVal('required_date_0','');
		setVal('remarks_0','');
	}	
function clearPOSearch(){
		setVal('po_buyer_name','');
		setVal('po_buyer_id','');
		setVal('po_from_date','');
		setVal('po_to_date','');
		setVal('po_no','');
		
	}
function deletePOAttachment(idx,poId,fileName){
	
	 bootbox.confirm(localizedStrings.DELETE_ATTACHMENT, function(result) {
		 if(result){
			 document.getElementById("ajax-loader").style.display="block";
				
				var url =contextpath+"/RequestHandlerServlet";
				
				var request = jQuery.ajax({
				url: url,
				type: "POST",
				data: {servlet_name : 'PurchaseOrderServlet' , callbackmethod_name : 'doDeleteAttachment',po_id:poId , file_name:fileName,idx:idx},
				dataType: "html"
				});
				request.done(function(msg) {
					var msg=msg.split('&1');
					if(msg[0]=='1')
						{
						deleteTableRow("attach_file"+msg[1]);
						document.getElementById('succ-msg').innerHTML=localizedStrings.ATTACHMENT_DELETED;//"Attachment deleted successfully.";
						showResponseMsg('err-msg','succ-msg');
						document.getElementById("ajax-loader").style.display="none";
							
						}
					else{
						document.getElementById('err-msg').innerHTML=localizedStrings.ATTACHMENT_NOT_DELETED;//"Attachment not deleted.";
						showResponseMsg('err-msg','succ-msg');
						document.getElementById("ajax-loader").style.display="none";
						
					}
					
				});
				request.fail(function(jqXHR, textStatus) {
				alert( "Request failed: " + textStatus );
				document.getElementById('errormessage').innerHTML=localizedStrings.ATTACHMENT_NOT_DELETED;//"Attachment not deleted.";
				showResponseMsg('errormessage','succ-msg');
				document.getElementById("ajax-loader").style.display="none";
				});
		 }
		}); 
}
function getSizeRangePOSizeGrid(poDetId,styleField,sizeRangeField){

	
	var poId=jQuery('#po_id').val();
	var itemId=jQuery('#'+styleField).val();
	var sizeRangeId=jQuery('#'+sizeRangeField).val();
	
	if(sizeRangeId!=0){
		var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
			url: url,
			type: "POST",
			data: {
				servlet_name : 'PurchaseOrderServlet', callbackmethod_name : 'doGetPOSizeRangeSizeGrid',
				size_range_id:sizeRangeId,item_id:itemId,po_id:poId,po_det_id:poDetId
			},
			dataType: "xml",
			success: function(xml) {
				jQuery(xml).find('size').each(function(){
					
					var sizeExist=jQuery(this).find('size_exists').text();
					var sizeGrid=jQuery(this).find('size_grid').text();
					
					if(sizeExist=="1"){
						jQuery('#size_grid_'+poDetId).html(sizeGrid);	
					}
					else{
						jQuery('#size_grid_'+poDetId).html("");
					}
					
				});
				
//				jQuery('#size_grid').html(html);

			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert( "Request failed: " + jqXHR);
			}
		});
//		var detailId=document.getElementById('purchase_price').value;
//		detailId=detailId+purchaseprice_id+',';
//		setVal('purchase_price',detailId);
	}
}
function validatePO(headMode,saveType){
	
		//if(validateTranDate('po_date','abo_form')){
		
		if(headMode=='n'){
			savePurchaseOrder(saveType,headMode);
		
			document.getElementById('apo_validation_btn').disabled=false;
			document.getElementById('apo_validation_btn').click();
	}
		else{
			if(validateFixedDetailPO()){
				
				savePurchaseOrder(saveType);
				document.getElementById('apo_validation_btn').disabled=false;
				document.getElementById('apo_validation_btn').click();
			}
			else{
				savePurchaseOrder(saveType);
				document.getElementById('new_det_mode').value='0';//New Detail Row Should Not Insert
				document.forms[0].submit();
				
			}
		}	
	}
function savePurchaseOrder(saveType,headMode){
	//	document.getElementById('bo_prefix').disabled = false;;
	document.getElementById('save_type').value=saveType;
	document.getElementById('request_type').value="Normal";
	document.getElementById('invoke_class').value="com.alpha.tpcsfashion.servlet.PurchaseOrderServlet";
	document.getElementById('invoke_method').value="doSavePurchaseOrder";
	document.forms[0].action=contextpath+"/FileUploadServlet";
	
	if(navigator.appName == "Microsoft Internet Explorer"){
		document.forms[0].encoding="multipart/form-data";
	}
	else{
		document.forms[0].enctype="multipart/form-data";	
	}
	document.forms[0].method="POST";
  }


function validateFixedDetailPO(){
	
	if(document.getElementById('item_id_0').value.trim().length!=0){
		return true;
	}
	else if(document.getElementById('size_range_id_0').value.trim().length!=0){
		return true;
	}
	
	else if(document.getElementById('required_date_0').value.trim().length!=0){
		return true;
	}
	 
	else{
		return false;
	}
 }
function validateTranDate(trDate,finStartDate,finEndDate,bookBeginDate,curDate,validateField){
	
	var entryDate=jQuery('#'+trDate).val();
	var finStartDate=jQuery('#'+finStartDate).val();
	var finEndDate=jQuery('#'+finEndDate).val();
	var booksDate=jQuery('#'+bookBeginDate).val();
	var currentDate=jQuery('#'+curDate).val();
	
	

	if(validateStartAndEndDates(finStartDate,booksDate)){//if books date greater than finStartDate
		finStartDate=booksDate;
	}


	if(!validateStartAndEndDates(entryDate,currentDate))//if entry date greater than Current date
	{
		document.getElementById('warning-msg').innerHTML="Transaction date can't be a future date";
		showWarningMsg('warning-msg');
		jQuery('#'+validateField+' #'+trDate).focus();
		return false;
	}
	else if((!validateStartAndEndDates(finStartDate,entryDate)) || (!validateStartAndEndDates(entryDate,finEndDate))){//if entry date less than finStartDate or entry date greater than finEndDate
		document.getElementById('warning-msg').innerHTML="Transaction date should be between "+finStartDate+" and "+finEndDate;
		showWarningMsg('warning-msg');
		jQuery('#'+validateField+' #'+trDate).focus();
		return false;
	}
	else{
		return true;
	}
  }


function calculateTotalQty(detailId,qtyRoundOff,rateRoundOff,valRoundOff){
	
	jQuery('#edited_size_det_ids').val(jQuery('#edited_size_det_ids').val()+','+detailId);
	
//	var sizeTotQty=jQuery('#size_tot_qty_'+detailId).val();
	var sizeTotQty=0;//getIfNullOrEmptyZero('size_tot_qty_'+detailId)
	var sizeTotPrice=0;
	var sizeTotValue=0;
	
	var sizeIds=jQuery('#size_ids_'+detailId).val();
	var arSizeIds=sizeIds.split(',');
	jQuery.each( arSizeIds, function( i, sizeId ) {
		sizeQty=getIfNullOrEmptyZero('size_qty_'+detailId+'_'+sizeId);
		sizePrice=getIfNullOrEmptyZero('size_rate_'+detailId+'_'+sizeId);
		
		sizeTotQty=parseFloat(sizeTotQty)+parseFloat(sizeQty);
		sizeTotPrice=parseFloat(sizeTotPrice)+parseFloat(sizePrice);
		sizeTotValue=parseFloat(sizeTotValue)+(parseFloat(sizeQty)*parseFloat(sizePrice));
		
		
	});
	
	jQuery('#size_tot_qty_'+detailId).val(sizeTotQty);
	jQuery('#size_tot_price_'+detailId).val(sizeTotPrice);
	var detPrice=parseFloat(sizeTotValue)/parseFloat(sizeTotQty);
	if(detailId>0){
		jQuery('#qty_S_'+detailId).val(sizeTotQty);
		jQuery('#apo_form').bootstrapValidator('revalidateField', 'qty_S_'+detailId);
		setEditedId('qty_S_'+detailId);
		
		jQuery('#price_fcy_S_'+detailId).val(Number(Math.round(detPrice+'e'+rateRoundOff)+'e-'+rateRoundOff));
		jQuery('#apo_form').bootstrapValidator('revalidateField', 'price_fcy_S_'+detailId);
		setEditedId('price_fcy_S_'+detailId);
	}
	else{
		jQuery('#qty_'+detailId).val(sizeTotQty);
		jQuery('#apo_form').bootstrapValidator('revalidateField', 'qty_'+detailId);
		
		jQuery('#price_fcy_'+detailId).val(Number(Math.round(detPrice+'e'+rateRoundOff)+'e-'+rateRoundOff));
		jQuery('#apo_form').bootstrapValidator('revalidateField', 'price_fcy_'+detailId);
	}
	
	calculateAmt(detailId,qtyRoundOff,rateRoundOff,valRoundOff);
}


//function calculateTotalPrice(detailId,qtyRoundOff,rateRoundOff,valRoundOff){
//	
//	jQuery('#edited_size_det_ids').val(jQuery('#edited_size_det_ids').val()+','+detailId);
//	
////	var sizeTotQty=jQuery('#size_tot_qty_'+detailId).val();
//	var sizeTotQty=0;
//	var sizeTotPrice=0;//getIfNullOrEmptyZero('size_tot_qty_'+detailId)
//	var sizeTotValue=0;
//	var sizeIds=jQuery('#size_ids_'+detailId).val();
//	var arSizeIds=sizeIds.split(',');
//	jQuery.each( arSizeIds, function( i, sizeId ) {
//		sizeTotPrice=parseFloat(sizeTotPrice)+parseFloat(getIfNullOrEmptyZero('size_rate_'+detailId+'_'+sizeId));
//		sizeTotQty=parseFloat(sizeTotQty)+parseFloat(getIfNullOrEmptyZero('size_qty_'+detailId+'_'+sizeId));
//		sizeTotValue=parseFloat(sizeTotValue)+(parseFloat(getIfNullOrEmptyZero('size_qty_'+detailId+'_'+sizeId))*parseFloat(getIfNullOrEmptyZero('size_rate_'+detailId+'_'+sizeId)));
//	});
//	
//	jQuery('#size_tot_price_'+detailId).val(sizeTotPrice);
//	var actPrice=parseFloat(sizeTotValue)/parseFloat(sizeTotQty);
//	
//	if(detailId>0){
//		jQuery('#price_fcy_S_'+detailId).val(Number(Math.round(actPrice+'e'+rateRoundOff)+'e-'+rateRoundOff));
//		jQuery('#apo_form').bootstrapValidator('revalidateField', 'price_fcy_S_'+detailId);
//		setEditedId('price_fcy_S_'+detailId);
//	}
//	else{
//		jQuery('#price_fcy_'+detailId).val(Number(Math.round(actPrice+'e'+rateRoundOff)+'e-'+rateRoundOff));
//		jQuery('#apo_form').bootstrapValidator('revalidateField', 'price_fcy_'+detailId);
////		setEditedId('qty_'+detailId);
//	}
//}


function closeBulkPurchaseOrder(){
	
	var cnt = jQuery("input[name='po_chk_id']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
		
		bootbox.confirm("Do you want to Close?", function(result) {
			if(result){
				document.getElementById('request_type').value="Normal";
				document.getElementById('servlet_name').value="PurchaseOrderServlet";
				document.getElementById('callbackmethod_name').value="doCloseBulkPurchaseOrder";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}
}



function deleteBulkPurchaseOrder(){
	
	var cnt = jQuery("input[name='po_chk_id']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
		
		bootbox.confirm("Do you want to Delete?", function(result) {
			if(result){
				document.getElementById('request_type').value="Normal";
				document.getElementById('servlet_name').value="PurchaseOrderServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBulkPurchaseOrder";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}
}

function cancelBulkPurchaseOrder(){
	
	var cnt = jQuery("input[name='po_chk_id']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
		
		bootbox.confirm("Do you want to Cancel?", function(result) {
			if(result){
				document.getElementById('request_type').value="Normal";
				document.getElementById('servlet_name').value="PurchaseOrderServlet";
				document.getElementById('callbackmethod_name').value="doCancelBulkPurchaseOrder";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}
}

	
