function displayBOActionList(actionType,pageno,boId,boDetId) {

	if(actionType==1){
		viewBuyerOrder('e',boId);
	}
	else if(actionType==2){
		editBuyerOrder('e',boId);
	}
	else if(actionType==3){
		deleteBuyerOrder(pageno,boId,boDetId,0);
	}
	else if(actionType==5){
		cancelBuyerOrder(pageno,boId);
	}
	else if(actionType==8){
		closeBuyerOrder(pageno,boId);
	}
}
function changeBOPageNumbers(pageno){
	
	document.getElementById('servlet_name').value="BuyerOrderServlet";
	document.getElementById('callbackmethod_name').value="doDisplayBuyerOrder";
	document.getElementById('request_type').value="Search";
	document.getElementById('pageno').value=pageno;
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function searchBuyerOrder(){
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="BuyerOrderServlet";
	document.getElementById('callbackmethod_name').value="doDisplayBuyerOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
function viewBuyerOrder(headMode,boId){
	document.getElementById('header_mode').value=headMode;
	document.getElementById('bo_id').value=boId;
	document.getElementById('view_mode').value="yes";
	document.getElementById('servlet_name').value="BuyerOrderServlet";
	document.getElementById('callbackmethod_name').value="doAddBuyerOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function editBuyerOrder(headMode,boId){
	document.getElementById('header_mode').value=headMode;
	document.getElementById('bo_id').value=boId;
	document.getElementById('servlet_name').value="BuyerOrderServlet";
	document.getElementById('callbackmethod_name').value="doAddBuyerOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function amendBuyerOrder(headMode,boId){
	bootbox.confirm("Do you want to post new amendment?", function(result) {
		 if(result){
	//document.getElementById('request_type').value="Normal";
	document.getElementById('header_mode').value=headMode;
	document.getElementById('bo_id').value=boId;
	document.getElementById('servlet_name').value="BuyerOrderServlet";
	document.getElementById('callbackmethod_name').value="doAddBuyerOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
		 }
	});
	
	
}
function deleteBuyerOrder(pageno,boId,boDetId,page){
	
	if(boId<=0){
		document.getElementById('warning-msg').innerHTML="Please save the transaction";
		showWarningMsg('warning-msg');
		 return false;
		 } 	
	else{
		
		 bootbox.confirm("Are you sure you want to delete?", function(result) {
			 if(result){
				    document.getElementById('servlet_name').value="BuyerOrderServlet";
					document.getElementById('callbackmethod_name').value="doDeleteBuyerOrder";
					document.getElementById('bo_id').value=boId;
//					document.getElementById('bo_det_id').value=boDetId;
					document.forms[0].action=contextpath+"/RequestHandlerServlet?page="+page;	
					document.forms[0].method="POST";	
					document.forms[0].submit();	
			 }
			});
	}
}
function deleteBuyerOrderDet(boId,boDetId){
	
	bootbox.confirm("Are you sure you want to delete?", function(result) {
		 if(result){
			 document.getElementById('servlet_name').value="BuyerOrderServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBuyerOrderDetail";
				document.getElementById('bo_id').value=boId;
				document.getElementById('bo_det_id').value=boDetId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
		 }
	});
}
function cancelBuyerOrder(pageno,boId){
	
	bootbox.confirm("Are you sure you want to cancel?", function(result) {
		 if(result){
			 document.getElementById('servlet_name').value="BuyerOrderServlet";
				document.getElementById('callbackmethod_name').value="doCancelBuyerOrder";
				document.getElementById('bo_id').value=boId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
		 }
	});
}
function closeBuyerOrder(pageno,boId){
	
	bootbox.confirm("Are you sure you want to cancel?", function(result) {
		 if(result){
			 document.getElementById('servlet_name').value="BuyerOrderServlet";
				document.getElementById('callbackmethod_name').value="doCloseBuyerOrder";
				document.getElementById('bo_id').value=boId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
		 }
	});
}
function clearNewBODetail(id){
	setVal('item_name_0','');
	setVal('item_id_0','');
	setVal('colour_name_0','');
	setVal('colour_id_0','');
	setVal('buyer_style_no_0','');
	setVal('size_range_0','');
	setVal('size_range_id_0','');
	setVal('qty_0','');
	setVal('rate_0','');
	setVal('required_date_0','');
	setVal('possible_date_0','');
	setVal('mrp_0','');
	setVal('barcode_0','');
	setVal('remark_0','');

}

function clearBOSearch(){
	setVal('bo_buyer_name','');
	setVal('bo_buyer_id','');
	setVal('bo_from_date','');
	setVal('bo_to_date','');
	setVal('bo_buyer_po_no','');
	setVal('bo_season_name','');
	setVal('bo_season_id','');
	setVal('bo_agent_name','');
	setVal('bo_agent_id','');
	setVal('bo_customer_plan_no','');
	setVal('bo_special_instruction','');
	setVal('bo_packing_labeling','');
	setVal('bo_packing_labeling','');
	
}
function deleteBOAttachment(idx,boId,fileName){
	
	 bootbox.confirm(localizedStrings.DELETE_ATTACHMENT, function(result) {
		 if(result){
			 document.getElementById("ajax-loader").style.display="block";
				
				var url =contextpath+"/RequestHandlerServlet";
				
				var request = jQuery.ajax({
				url: url,
				type: "POST",
				data: {servlet_name : 'BuyerOrderServlet' , callbackmethod_name : 'doDeleteAttachment',bo_id:boId , file_name:fileName,idx:idx},
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
function deleteBOBulkTransaction(){
	 var cnt = jQuery("input[name='buyer_order_id']:checked").length;
	
	 if (cnt < 1){
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		//document.getElementByName('sales_enqid').focus();
		showWarningMsg('warning-msg');
//		return false;
     } 	
	else{

		bootbox.confirm("Are you sure you want to delete?", function(result) {  
			if(result){
				document.getElementById('servlet_name').value="BuyerOrderServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBulkBuyerOrder";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();	
			}
		});
	}

}
function getSizeRangeSizeGrid(boDetId,styleField,sizeRangeField){

	var boId=jQuery('#bo_id').val();
	var itemId=jQuery('#'+styleField).val();
	var sizeRangeId=jQuery('#'+sizeRangeField).val();
	
	if(sizeRangeId!=0){
		var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
			url: url,
			type: "POST",
			data: {
				servlet_name : 'BuyerOrderServlet', callbackmethod_name : 'doGetSizeRangeSizeGrid',
				size_range_id:sizeRangeId,item_id:itemId,bo_id:boId,bo_det_id:boDetId
			},
			dataType: "xml",
			success: function(xml) {
				jQuery(xml).find('size').each(function(){
					
					
					var sizeExist=jQuery(this).find('size_exists').text();
					var sizeGrid=jQuery(this).find('size_grid').text();
					
					if(sizeExist=="1"){
						jQuery('#size_grid_'+boDetId).html(sizeGrid);	
					}
					else{
						jQuery('#size_grid_'+boDetId).html("");
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
function getSizeRangeSizeData(rowId){
	  //if(validateCriteriaQuery(rowNo)){
	
	var sizeRangeId=jQuery('#packing_size_range_id_'+rowId).val();
	var rowCnt=$('#packing_row_count').val();
//	$('#packing_row_ids').val($('#packing_row_ids').val()+','+rowCnt);
	
	
	if(sizeRangeId!=0){
		var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
			url: url,
			type: "POST",
			data: {
				servlet_name : 'BuyerOrderServlet', callbackmethod_name : 'doGetSizeRangeSize',
				size_range_id:sizeRangeId,packing_row_id:rowId
			},
			dataType: "xml",
			success: function(xml) {
				jQuery(xml).find('size').each(function(){
					
					var sizeGrid=jQuery(this).find('size_grid').text();
					
						jQuery('#size_row_'+rowId).html(sizeGrid);	
						addPackingToCortan(rowId);
					
				});
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert( "Request failed: " + jqXHR);
			}
		});
	}
}
function addPackingType(dedId){
	
	var rowCnt=$('#packing_row_count').val();
//	cstCtrTmpRowCnt=cstCtrRowCnt;
	$('#packing_row_ids').val($('#packing_row_ids').val()+','+rowCnt);	
	
	 var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'BuyerOrderServlet', callbackmethod_name : 'doGetPackingRow',
			packing_row_count:rowCnt
			},
		dataType: "xml",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + jqXHR);
		},
		success: function(xml) {
			
			jQuery(xml).find('packing_row').each(function(){
				
				var data=jQuery(this).find('data').text();
				
				  $('#size_row_'+dedId).after(data);
				  
				  $(function(){
						$('[data-toggle="tooltip"]').tooltip();
					});
				  
				  !function ($) {
						 
						$(function(){
							
							  $('#packing_size_range_'+rowCnt).listSizeRange({
								  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSizeRange&create_new=1",
								  nameField :'#packing_size_range_'+rowCnt,
								  idField:'#packing_size_range_id_'+rowCnt,
								  formId:'#abo_form',
								  validateId:'packing_size_range_'+rowCnt
									  
							  });
						});
				  }(window.jQuery);
				  
				  $('#abo_form').bootstrapValidator('addField', 'packing_size_range_'+rowCnt, {
					  validators: {
		                  callback: {
		                      message: 'This field is required',
		                      callback: function(value, validator, $field) {
		                      	if ($("#packing_size_range_id_"+rowCnt).val()==0) {
		                              return {
		                                  valid: false,
		                                  message: 'This field is required'
		                              };
		                          }
		                      	return true;
		                      }
		                  }
		              }
			        });
			});
		}
		});
}

function deletePackingType(rowId){
	jQuery('.packing_row_'+rowId).remove();
	jQuery('#size_row_'+rowId).remove();
	
	
	var rIds=$('#packing_row_ids').val();
	$('#packing_row_ids').val(rIds.replace(','+rowId,''));
}
function validateBO(headMode,saveType){
	
	if(validateTranDate('bo_date','fin_startdate','fin_enddate','books_bgng_date','current_date','abo_form')){
		
		if(headMode=='n'){
			saveBuyerOrder(saveType);
			document.getElementById('abo_validation_btn').disabled=false;
			document.getElementById('abo_validation_btn').click();
		}
		else{
			if(validateFixedDetailBO()){
				saveBuyerOrder(saveType);
				document.getElementById('abo_validation_btn').disabled=false;
				document.getElementById('abo_validation_btn').click();
			}
			else{
				saveBuyerOrder(saveType);
				document.getElementById('new_det_mode').value='0';//New Detail Row Should Not Insert
				document.forms[0].submit();
				
			}
		}	
	}
}
function saveBuyerOrder(saveType){
	
	
//	document.getElementById('bo_prefix').disabled = false;;
	document.getElementById('save_type').value=saveType;
	document.getElementById('request_type').value="Normal";
	document.getElementById('invoke_class').value="com.alpha.tpcsfashion.servlet.BuyerOrderServlet";
	document.getElementById('invoke_method').value="doSaveBuyerOrder";
	document.forms[0].action=contextpath+"/FileUploadServlet";
	
	if(navigator.appName == "Microsoft Internet Explorer"){
		document.forms[0].encoding="multipart/form-data";
	}
	else{
		document.forms[0].enctype="multipart/form-data";	
	}
	document.forms[0].method="POST";
}
function validateFixedDetailBO(){
	
	if(document.getElementById('item_id_0').value.trim().length!=0){
		return true;
	}
	else if(document.getElementById('size_range_0').value.trim().length!=0){
		return true;
	}
	else if(document.getElementById('required_date_0').value.trim().length!=0){
		return true;
	}
	else if(document.getElementById('possible_date_0').value.trim().length!=0){
		return true;
	}
	 
	else{
		return false;
	}
	

}

function calculateTotalQty(detailId){
	
	jQuery('#edited_size_det_ids').val(jQuery('#edited_size_det_ids').val()+','+detailId);
	
//	var sizeTotQty=jQuery('#size_tot_qty_'+detailId).val();
	var sizeTotQty=0;//getIfNullOrEmptyZero('size_tot_qty_'+detailId)
	var sizeIds=jQuery('#size_ids_'+detailId).val();
	var arSizeIds=sizeIds.split(',');
	jQuery.each( arSizeIds, function( i, sizeId ) {
		sizeTotQty=parseFloat(sizeTotQty)+parseFloat(getIfNullOrEmptyZero('size_qty_'+detailId+'_'+sizeId));
	});
	
	jQuery('#size_tot_qty_'+detailId).val(sizeTotQty);
	if(detailId>0){
		jQuery('#qty_S_'+detailId).val(sizeTotQty);
		jQuery('#abo_form').bootstrapValidator('revalidateField', 'qty_S_'+detailId);
		setEditedId('qty_S_'+detailId);
	}
	else{
		jQuery('#qty_'+detailId).val(sizeTotQty);
		jQuery('#abo_form').bootstrapValidator('revalidateField', 'qty_'+detailId);
//		setEditedId('qty_'+detailId);
	}
}

function calculateTotalPackingSizeQty(detailId){
	
//	jQuery('#edited_size_det_ids').val(jQuery('#edited_size_det_ids').val()+detailId+",");
	
//	var sizeTotQty=jQuery('#size_tot_qty_'+detailId).val();
	var sizeTotQty=0;//getIfNullOrEmptyZero('size_tot_qty_'+detailId)
	var sizeIds=jQuery('#packing_size_ids_'+detailId).val();
	var arSizeIds=sizeIds.split(',');
	jQuery.each( arSizeIds, function( i, sizeId ) {
		sizeTotQty=parseFloat(sizeTotQty)+parseFloat(getIfNullOrEmptyZero('packing_size_qty_'+detailId+'_'+sizeId));
	});
	
	jQuery('#packing_size_tot_qty_'+detailId).val(sizeTotQty);
//	if(detailId>0){
//		jQuery('#qty_S_'+detailId).val(sizeTotQty);
//		jQuery('#abo_form').bootstrapValidator('revalidateField', 'qty_S_'+detailId);
//		setEditedId('qty_S_'+detailId);
//	}
//	else{
//		jQuery('#qty_'+detailId).val(sizeTotQty);
//		jQuery('#abo_form').bootstrapValidator('revalidateField', 'qty_'+detailId);
//		setEditedId('qty_'+detailId);
//	}
}


function loadCortanDetail(detId){
	
////		var cortanLocIds=jQuery('#cortan_loc_ids_'+detId).val();
////		var cortanLocRowId=jQuery('#cortan_loc_cnt_'+detId).val();
////		cortanLocIds=cortanLocIds+","+cortanLocRowId;
////		jQuery('#cortan_loc_ids_'+detId).val(cortanLocIds);
////		jQuery('#cortan_loc_cnt_'+detId).val((parseInt(cortanLocRowId)+1));
////	
////	
//	
//	if(detId==0){
//	var packingRowIds=jQuery('#packing_row_ids').val();
//	var arPackingRowIds=packingRowIds.split(',');
//	
//	var data="";
//	var packingTH="<tr class=\"header-det\">" +
//			"<th id=\"corton_loc_th\">Destination</th>";
//	
////	var packingTD="<tr>" +
////			"<td>" +
////			"<div class=\"form-group\" >" +
////			"<input type=\"text\" class=\"form-control\" id=\"cortan_location_"+detId+"_"+cortanLocRowId+"\" name=\"cortan_location_"+detId+"_"+cortanLocRowId+"\" maxlength=\"8\" placeholder=\"Enter Location\">" +
////			"</div>" +
////			"</td>";
//	
//	jQuery.each( arPackingRowIds, function( i, packingId ) {
//		
//		if(jQuery('#packing_size_ids_'+packingId).length>0){
//		var packingType=jQuery('#packing_type_'+packingId).val();
//		var sizeRange=jQuery('#packing_size_range_'+packingId);
//		packingTH=packingTH+" <th  id=\"cortan_pack_th_"+detId+"_"+packingId+"\">"+packingType+"</th>";
////		packingTD=packingTD+"<td>" +
////				"<div class=\"form-group\" >" +
////				"<input type=\"text\" class=\"form-control\" id=\"cortan_qty_"+detId+"_"+cortanLocRowId+"_"+packingId+"\" name=\"cortan_qty_"+detId+"_"+cortanLocRowId+"_"+packingId+"\" onkeyup=\"calculateLocationTotal("+detId+","+cortanLocRowId+","+packingId+");\" maxlength=\"8\" placeholder=\"Enter qty\">" +
////				"</div>" +
////				"</td>";
//		}
//		//sizeTotQty=parseFloat(sizeTotQty)+parseFloat(getIfNullOrEmptyZero('packing_size_qty_'+detailId+'_'+sizeId));
//	});
//	
//	packingTH=packingTH+" <th id=\"cortan_tot_th_"+detId+"\">Total</th>" +
//			"<th>&nbsp;</th>" +
//			"</tr>";
//	
////	packingTD=packingTD+" <td>" +
////			"<div class=\"form-group\" >" +
//////			"<label id=\"cortan_tot_qty_lbl_"+detId+"_"+cortanLocRowId+"\" ></label>" +
////			"<input type=\"text\" class=\"form-control\" id=\"cortan_tot_qty_"+detId+"_"+cortanLocRowId+"\" name=\"cortan_tot_qty_"+detId+"_"+cortanLocRowId+"\" />" +
////				"</div>" +
////			"</td>" +
////			"<td><a href=\"javascript:addLocationRow("+detId+")\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Add\" data-original-title=\"Add\"><span class=\"glyphicon glyphicon-plus text-primary\"></span></a></td>" +
////	"</tr>";
//
////	cortanLocIds=cortanLocIds+""+cortanLocRowId+",";
////	cortanLocRowId=cortanLocRowId+1;
//	
////	var cortanLocCnt=jQuery('#cortan_loc_cnt_'+detId).val(cortanLocRowId);
//
//	
//	data=data+packingTH;
//	
//	jQuery('.cortan_table_'+detId).html(data);
//	
//	addLocationRow(detId);
//	addLocationRow(detId);
//	
//	}
//	else{
////		var detIds=jQuery('#det_ids').val();
////		var arDetIds=detIds.split(',');
////		jQuery.each( arDetIds, function( i, detId ) {
////			if(detId!=''){
////				var cortanLocIds=jQuery('#cortan_loc_ids_'+detId).val();
////				var arCortanLocIds=cortanLocIds.split(',');
////				
////				var totQty=0;
////				jQuery.each( arCortanLocIds, function( i, cortanLocRowId ) {
////					
////				});
////				
////			}
////			
////		});
//	}
}

function addPackingToCortan(packingId){
	
	var detIds="0,"+jQuery('#det_ids').val();
//	if(detIds!=''){
	var arDetIds=detIds.split(',');
	jQuery.each( arDetIds, function( i, detId ) {
		if(detId!=''){
			var cortanLocIds=jQuery('#cortan_loc_ids_'+detId).val();
			var arCortanLocIds=cortanLocIds.split(',');
			
			var packingTH="";
			var packingTD="";
			
			jQuery.each( arCortanLocIds, function( i, cortanLocRowId ) {
				packingTH="";
				packingTD="";
				
//				var packingRowIds=jQuery('#packing_row_ids').val();
//				var arPackingRowIds=packingRowIds.split(',');
//
//				jQuery.each( arPackingRowIds, function( i, packingId ) {
				
					var packingType=jQuery('#packing_type_'+packingId).val();
					if(packingType!=''){
					if(jQuery('#cortan_pack_th_'+detId+'_'+packingId).length<=0){
						packingTH=packingTH+" <th  id=\"cortan_pack_th_"+detId+"_"+packingId+"\">"+packingType+"</th>";			
					}
					else{
						jQuery("#cortan_pack_th_"+detId+"_"+packingId).html(packingType);						
					}
					if(jQuery('#cortan_pack_td_'+detId+'_'+cortanLocRowId+'_'+packingId).length<=0){
						packingTD=packingTD+"<td id=\"cortan_pack_td_"+detId+"_"+cortanLocRowId+"_"+packingId+"\" >" +
						"<div class=\"form-group\" >" +
						"<input type=\"text\" class=\"form-control\" id=\"cortan_qty_"+detId+"_"+cortanLocRowId+"_"+packingId+"\" name=\"cortan_qty_"+detId+"_"+cortanLocRowId+"_"+packingId+"\" onkeyup=\"calculateLocationTotal("+detId+","+cortanLocRowId+","+packingId+");\" maxlength=\"8\" placeholder=\"Enter qty\">" +
						"</div>" +
						"</td>";
					}
					
					}
					
//				});
				
				jQuery("#cortan_tot_td_"+detId+"_"+cortanLocRowId).before(packingTD);
				
			});
			
//			alert(packingTH);
			jQuery("#cortan_tot_th_"+detId).before(packingTH);
			
		}
		
	});
//	}
}

function addLocationRow(detId){

	var cortanLocIds="0";
	var cortanLocRowId=0;
	
	if(jQuery('#cortan_loc_cnt_'+detId).val()!=''){
		cortanLocRowId=jQuery('#cortan_loc_cnt_'+detId).val();	
	}
	
	if(jQuery('#cortan_loc_ids_'+detId).val()!=''){
		cortanLocIds=jQuery('#cortan_loc_ids_'+detId).val();
	}
	if(cortanLocRowId!=0){
		cortanLocIds=cortanLocIds+","+cortanLocRowId;
	}
	
	
	jQuery('#cortan_loc_ids_'+detId).val(cortanLocIds);
	jQuery('#cortan_loc_cnt_'+detId).val((parseInt(cortanLocRowId)+1));

	var packingTD="<tr id=\"corton_loc_"+detId+"_"+cortanLocRowId+"\">" +
	"<td id=\"corton_loc_td_"+detId+"_"+cortanLocRowId+"\">" +
	"<div class=\"form-group\" >" +
	"<input type=\"text\" class=\"form-control\" id=\"cortan_location_"+detId+"_"+cortanLocRowId+"\" name=\"cortan_location_"+detId+"_"+cortanLocRowId+"\" maxlength=\"8\" placeholder=\"Enter Location\">" +
	"</div>" +
	"</td>";


	var packingRowIds=jQuery('#packing_row_ids').val();
	var arPackingRowIds=packingRowIds.split(',');

	jQuery.each( arPackingRowIds, function( i, packingId ) {

		if(jQuery('#packing_size_ids_'+packingId).length>0){
			var packingType=jQuery('#packing_type_'+packingId).val();
			var sizeRange=jQuery('#packing_size_range_'+packingId);

			packingTD=packingTD+"<td id=\"cortan_pack_td_"+detId+"_"+cortanLocRowId+"_"+packingId+"\" >" +
			"<div class=\"form-group\" >" +
			"<input type=\"text\" class=\"form-control\" id=\"cortan_qty_"+detId+"_"+cortanLocRowId+"_"+packingId+"\" name=\"cortan_qty_"+detId+"_"+cortanLocRowId+"_"+packingId+"\" onkeyup=\"calculateLocationTotal("+detId+","+cortanLocRowId+","+packingId+");\" maxlength=\"8\" placeholder=\"Enter qty\">" +
			"</div>" +
			"</td>";
		}
	});

	packingTD=packingTD+" <td id=\"cortan_tot_td_"+detId+"_"+cortanLocRowId+"\">" +
	"<div class=\"form-group\" >" +
	"<input type=\"text\" class=\"form-control\" id=\"cortan_tot_qty_"+detId+"_"+cortanLocRowId+"\" name=\"cortan_tot_qty_"+detId+"_"+cortanLocRowId+"\" tabindex=\"-1\" readonly  />" +
	"</div>" +
	"</td>" +
	"<td>" +
	"<a href=\"javascript:addLocationRow("+detId+")\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Add\" data-original-title=\"Add\"><span class=\"span-icon glyphicon glyphicon-plus text-primary\"></span></a>" +
	"<a href=\"javascript:deleteLocationRow("+detId+","+cortanLocRowId+")\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Remove\" data-original-title=\"Remove\"><span class=\"span-icon glyphicon glyphicon-remove icon-delete\"></span></a>" +
			"</td>" +
	"</tr>";

	jQuery(".cortan_table_"+detId).append(packingTD);
	
	$(function(){
		$('[data-toggle="tooltip"]').tooltip();
	});

}
function deleteLocationRow(detId,cortanLocRowId){
	jQuery('#cortan_loc_ids_'+detId).val(jQuery('#cortan_loc_ids_'+detId).val().replace(','+cortanLocRowId,''));
	jQuery('#corton_loc_'+detId+'_'+cortanLocRowId).remove();
	
}
function calculateLocationTotal(detId,cortanLocRowId,packingId){
	if(jQuery('#packing_row_ids').length>0){
	var packingRowIds=jQuery('#packing_row_ids').val();
	var arPackingRowIds=packingRowIds.split(',');
	
	var locTotQty=0.0;
	
	jQuery.each( arPackingRowIds, function( i, packingId ) {
		if(jQuery('#packing_size_ids_'+packingId).length>0){
			locTotQty=parseFloat(locTotQty)+parseFloat(getIfNullOrEmptyZero("cortan_qty_"+detId+"_"+cortanLocRowId+"_"+packingId));
		}
	});
	
	jQuery("#cortan_tot_qty_"+detId+"_"+cortanLocRowId).val(locTotQty);
//	jQuery("#cortan_tot_qty_lbl_"+detId+"_"+cortanLocRowId).html(locTotQty);
	
	calculateCortanTotQty(detId);
	}
}

function calculateCortanTotQty(detId){
	var cortanLocIds=jQuery('#cortan_loc_ids_'+detId).val();
	var arCortanLocIds=cortanLocIds.split(',');
	
	var totQty=0;
	jQuery.each( arCortanLocIds, function( i, cortanLocRowId ) {
		
		var packingRowIds=jQuery('#packing_row_ids').val();
		var arPackingRowIds=packingRowIds.split(',');
		var locTotQty=0.0;
		jQuery.each( arPackingRowIds, function( i, packingId ) {
		
			var cartonQty=getIfNullOrEmptyZero("cortan_qty_"+detId+"_"+cortanLocRowId+"_"+packingId);
			
			if(jQuery('#packing_size_ids_'+packingId).length>0){
			var packingSizeIds=jQuery('#packing_size_ids_'+packingId).val();
			var arPackingSizeIds=packingSizeIds.split(',');
			
			jQuery.each( arPackingSizeIds, function( i, sizeId ) {
				var sizeQty=getIfNullOrEmptyZero('packing_size_qty_'+packingId+'_'+sizeId);
				totQty=parseFloat(totQty)+(parseFloat(cartonQty)*parseFloat(sizeQty));
			});
			}
		});
	});
	
	if(detId>0){
		jQuery('#qty_S_'+detId).val(totQty);
		jQuery('#abo_form').bootstrapValidator('revalidateField', 'qty_S_'+detId);
		setEditedId('qty_S_'+detId);
	}
	else{
		jQuery('#qty_'+detId).val(totQty);
		jQuery('#abo_form').bootstrapValidator('revalidateField', 'qty_'+detId);
		//setEditedId('qty_'+detId);
	}
//	
//	var packingRowIds=jQuery('#packing_row_ids').val();
//	var arPackingRowIds=packingRowIds.split(',');
//	
//	var locTotQty=0.0;
//	jQuery.each( arPackingRowIds, function( i, packingId ) {
//		locTotQty=parseFloat(locTotQty)+parseFloat(jQuery("#cortan_qty_"+detId+"_"+cortanLocRowId+"_"+packingId).val());
//	});
//	
//	jQuery("#cortan_tot_qty_"+detId+"_"+cortanLocRowId).val(locTotQty);
//	jQuery("#cortan_tot_qty_lbl_"+detId+"_"+cortanLocRowId).html(locTotQty);
}

function closeBulkBuyerOrder(){
	
	var cnt = jQuery("input[name='bo_chk_id']:checked").length;
	if (cnt < 1){
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
		
		bootbox.confirm("Do you want to Close?", function(result) {
			if(result){
				document.getElementById('request_type').value="Normal";
				document.getElementById('servlet_name').value="BuyerOrderServlet";
				document.getElementById('callbackmethod_name').value="doCloseBulkBuyerOrder";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}
}



function deleteBulkBuyerOrder(){
	
	var cnt = jQuery("input[name='bo_chk_id']:checked").length;
	if (cnt < 1){
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    }
	else{
		
		bootbox.confirm("Do you want to Delete?", function(result) {
			if(result){
				document.getElementById('request_type').value="Normal";
				document.getElementById('servlet_name').value="BuyerOrderServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBulkBuyerOrder";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}
}

function cancelBulkBuyerOrder(){
	
	var cnt = jQuery("input[name='bo_chk_id']:checked").length;
	if (cnt < 1){
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
		
		bootbox.confirm("Do you want to Cancel?", function(result) {
			if(result){
				document.getElementById('request_type').value="Normal";
				document.getElementById('servlet_name').value="BuyerOrderServlet";
				document.getElementById('callbackmethod_name').value="doCancelBulkBuyerOrder";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}
}