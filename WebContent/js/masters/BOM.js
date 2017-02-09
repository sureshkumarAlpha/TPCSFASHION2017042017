function displayBOMActionList(actionType,pageno,bomId,bomNo)
{
	
	
	if(actionType==1){
		printBOM(bomId,0);
	}
	else if(actionType== 4){
		deleteBOM(pageno,bomId,0);
	}
	else if(actionType==3){
		editBOM('e',bomId);
	}
	else if(actionType==2){
		viewBOMTransaction('e',bomId);
	}
	else if(actionType==5){
		amendBOM('a',bomId);
	}
	else if(actionType==6){
		lockBOMTransaction(bomId,bomNo);
	}
	else if(actionType==7){
		unlockBOMTransaction(bomId,bomNo);
	}
}


function printBOM(bomId,page)
{
	document.getElementById('bom_id').value=bomId;
	document.getElementById('servlet_name').value="BOMServlet";
//	document.getElementById('callbackmethod_name').value="doPrintBOMTransactionTOJasperPDF";
	document.getElementById('callbackmethod_name').value="doExportBOMTransactionToPDF";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}

function unlockBOMTransaction(bomId,bomNo)
{
	bootbox.confirm("Do you want to unlock this BOM :"+bomNo+"?", function(result) {
		 if(result){
	document.getElementById('bom_id').value=bomId;
	document.getElementById('bom_no').value=bomNo;
	document.getElementById('lock_status').value="0";
	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doDisplayBOMLock";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
		 }
	});
	
	
}



function lockBOMTransaction(bomId,bomNo)
{
	bootbox.confirm("Do you want to lock this BOM :"+bomNo+"?", function(result) {
		 if(result){
	document.getElementById('bom_id').value=bomId;
	document.getElementById('bom_no').value=bomNo;
	document.getElementById('lock_status').value="1";
	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doDisplayBOMLock";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
		 }
	});
	
	
}

function amendBOM(headMode,bomId)
{
	bootbox.confirm("Do you want to post new amendment?", function(result) {
		 if(result){
	document.getElementById('head_mode').value=headMode;
	document.getElementById('bom_id').value=bomId;
	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doNewBOM";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
		 }
	});
	
	
}

function viewBOMTransaction(headMode,bomId){
	document.getElementById('head_mode').value=headMode;
	document.getElementById('view_mode').value="v";
	document.getElementById('bom_id').value=bomId;
	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doNewBOM";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function editBOM(headMode,bomId)
{
	document.getElementById('head_mode').value=headMode;
	document.getElementById('bom_id').value=bomId;
	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doNewBOM";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}


function deleteBOM(pageno,bomId,page)
{

	if(bomId<=0){
		document.getElementById('warning-msg').innerHTML="Please save the transaction";
		showWarningMsg('warning-msg');
		return false;
	} 	
	else{

		bootbox.confirm("Are you sure you want to delete?", function(result) {
			if(result){
				document.getElementById('servlet_name').value="BOMServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBOM";
				document.getElementById('bom_id').value=bomId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet?page="+page;	
				document.forms[0].method="POST";	
				document.forms[0].submit();	
			}
		});
	}

}



function validateBOM(headMode,saveType,detId){
	
	
//	if(validateTrDate())
//		{
		if(headMode=='n'){

			saveBOM(saveType,detId);
			document.getElementById('validation_btn').click();
		}
		else{
			if(validateFixedDetailBOM()){
				
				
				saveBOM(saveType,detId);
				document.getElementById('validation_btn').click();
			}
			else{
				saveBOM(saveType,detId);
				document.getElementById('new_det_mode').value='0';//New Detail Row Should Not Insert
				document.forms[0].submit();

			}

		}
		
		//}
	

}





function validateFixedDetailBOM(){

	if(document.getElementById('material_id_0').value.trim().length!=0)
	{
		return true;
	}
	else if(document.getElementById('operation_id_0').value.trim().length!=0)
	{
		return true;
	}
	else if(document.getElementById('uom_0').value.trim().length!=0)
	{
		return true;
	}
	else if(document.getElementById('required_qty_0').value.trim().length!=0 && document.getElementById('required_qty_0').value>0)
	{
		return true;
	}

	else{
		return false;
	}


}




function clearNewBOMDetail()
{
	setVal('material_0','');
	setVal('material_id_0','');
	setVal('operation_0','');
	setVal('operation_id_0','');
	setVal('supplier_0','');
	setVal('supplier_id_0','');
	setVal('variant_0','');
	setVal('variant_id_0','');
	setVal('uom_0','');
	setVal('uom_id_0','');
	setVal('required_qty_0','');
	setVal('wastage_per_0','');
	setVal('purchase_per_0','');
	setVal('issue_per_0','');

}



function changeBOMPageNumbers(pageno){

	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doDisplayBOM";
	document.getElementById('request_type').value="Search";
	document.getElementById('pageno').value=pageno;
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";

	document.forms[0].submit();
} 


function deleteBOMDet(bomId,bomDetId)
{
	bootbox.confirm("Are you sure you want to delete?", function(result) {
		if(result){
			document.getElementById('servlet_name').value="BOMServlet";
			document.getElementById('callbackmethod_name').value="doDeleteBOMDetail";
			document.getElementById('bom_id').value=bomId;
			document.getElementById('bom_det_id').value=bomDetId;
			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
			document.forms[0].method="POST";	
			document.forms[0].submit();
		}
	});
}

function searchBOM()
{
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doSearchBOM";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}


function clearBOMSearch(){

	setVal('bom_customer','');
	setVal('bom_customer_id','');
	setVal('bom_type','-1');
	setVal('bom_from_date','');
	setVal('bom_to_date','');
	setVal('product','');
	setVal('product_id','');
	setVal('bom_no','');
	setVal('material','');
	setVal('material_id','');
	setVal('operation','');
	setVal('operation_id','');
	setVal('group','');
	setVal('group_id','');
}





function getAltMaterial(mode,detId){
	
	jQuery('#bom_det_id').val(detId);
	
	
	if(detId>0){
		document.getElementById('alt_mat_modal').click();	
	}
	
	
}


function getBOMComponents(mode,detId){
	
	jQuery('#bom_det_id').val(detId);
	if(detId>0){
	document.getElementById('add_bom_comp').click();
	}
}

function loadDataToAltMaterialModal(){

	var bomDetId=document.getElementById('bom_det_id').value;
	
	var rowCnt=document.getElementById('row_count').value;

       //alert(bomDetId);
	var url =contextpath+"/RequestHandlerServlet";

	var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'BOMServlet' , callbackmethod_name : 'doGetAltMaterialModalData',bom_det_id:bomDetId},
		dataType: "xml"
	});
	request.done(function(xml) {
		
		jQuery(xml).find('mat_data').each(function(){
			data=jQuery(this).find('mat_table').text();
			
		
			document.getElementById('alt_mat_det').innerHTML=data;
		});
		
		jQuery(xml).find('auto_complete_field').each(function(){
//			data=jQuery(this).find('auto_complete_field').XML();
			
			 var name=jQuery(this).find('name').text();
			 var id=jQuery(this).find('id').text();
			 var method=jQuery(this).find('method').text();
			 
	
			 
		    	 !function ($) {
		 			$(function(){
		 				  $('#validate-form-altMat #'+name).listAltMaterial({
		 					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name="+method+"",
		 					  nameField :'#validate-form-altMat #'+name,
		 					  idField :'#validate-form-altMat #'+id,
		 					 createNew:'0'
		 				  });
		 			 });
		 		}(window.jQuery);
			
//			document.getElementById('alt_mat_auto_complete').innerHTML=data;
		});
		
		jQuery(xml).find('mat_rows').each(function(){
			rowCnt=jQuery(this).find('mat_rowcnt').text();
			rowIds=jQuery(this).find('mat_rowIds').text();
			document.getElementById('row_count').value=rowCnt;
			document.getElementById('row_ids').value=rowIds;
		});



	});
	request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
	});

}


function addAnotherLineInAltMat(rowID){
	rowCnt=$('#row_count').val();

	var allRowIds=$('#row_ids').val();
	$('#row_ids').val(allRowIds.replace(rowID+",",rowID+","+rowCnt+","));	
	$('#addrow_table tr#n_table_row'+(rowID)+'').after('<tr id="n_table_row'+rowCnt+'">'+
			'<td><input class="form-control" id="alt_mat_'+rowCnt+'" name="alt_mat_'+rowCnt+'" size="30" type="text" value="" placeholder="Alternate Material"> '+
			'<input  id="alt_mat_id_'+rowCnt+'" name="alt_mat_id_'+rowCnt+'" size="30" type="hidden" value="" ></td>'+
			'<td><input class="form-control" id="alt_per_'+rowCnt+'" name="alt_per_'+rowCnt+'" size="30" type="text" value=""  placeholder="Alternate Per" >  </td>'+
			'<td ><a href="javascript:addAnotherLineInAltMat('+rowCnt+')" style="vertical-align: bottom;" data-toggle="tooltip" title="Add"><span class="glyphicon glyphicon-plus text-primary"></span></a>&nbsp;<a href="javascript:deleteRowForAltMat('+rowCnt+')" style="vertical-align: bottom;" data-toggle="tooltip" title="Remove" ><span class="glyphicon glyphicon-remove icon-delete"></span></a>&nbsp; '+
			'</td>'+	
	'</tr>');
	
	 !function ($) {
			$(function(){
				  $('#validate-form-altMat #alt_mat_'+rowCnt).listAltMaterial({
					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetAltMaterial",
					  nameField :'#validate-form-altMat #alt_mat_'+rowCnt,
					  idField :'#validate-form-altMat #alt_mat_id_'+rowCnt,
					  createNew:'0'
				  });
				  rowCnt=parseInt(rowCnt)+1;

					$('#row_count').val(rowCnt);
			 });
		}(window.jQuery);
	

}


function deleteRowForAltMat(rowNo){
	$('#addrow_table #n_table_row'+rowNo).remove();
	var rowIds=$('#row_ids').val();
	$('#row_ids').val(rowIds.replace(rowNo+",",""));
}



function saveAlternateMaterial(){
	
	
	var bomDetId=jQuery('#bom_det_id').val();
	
	var servletName="BOMServlet";
     var methodName="doSaveAlternateMaterial";
	
	
	var data=jQuery('#validate-form-altMat').serialize()+ '&servlet_name='+servletName+'&callbackmethod_name='+methodName+'&bom_det_id='+bomDetId;
		//if(jQuery('#validate-form-altMat .has-error').length==0){
		 var url =contextpath+"/RequestHandlerServlet";
			jQuery.ajax({
			url: url,
			type: "POST",
			data:data,
			dataType: "html",
			error: function(jqXHR, textStatus, errorThrown) {
				alert( "Request failed: " + errorThrown);
			},
			success: function(msg) {
				
				
				if(msg=='1'){
					jQuery('#succ-msg').html("Alternate material saved successfully");
					showResponseMsg('err-msg','succ-msg');	
					document.getElementById('mat_close').click();
				}
				else if(msg=='0'){
					jQuery('#err-msg').html("Alternate material not saved");
					showResponseMsg('err-msg','succ-msg');
				}
	
				 	
			}
		});
		//}
}



/*function saveBOM(saveType,detId){
	
	
	var bomDetId=jQuery('#bom_det_id').val();
	
	
	
	var servletName="com.alpha.tpcsfashion.servlet.BOMServlet";
     var methodName="doSaveBOM";
	
	
	var data=jQuery('#validate-form').serialize()+ '&invoke_class='+servletName+'&invoke_method='+methodName+'&bom_det_id='+bomDetId;
	
	alert(data);
		//if(jQuery('#validate-form-altMat .has-error').length==0){
		 var url =contextpath+"/FileUploadServlet";
			jQuery.ajax({
			url: url,
			type: "POST",
			data:data,
			dataType: "html",
			error: function(jqXHR, textStatus, errorThrown) {
				alert( "Request failed: " + errorThrown);
			},
			success: function(msg) {
				
				
				if(msg=='1'){
					jQuery('#succ-msg').html("Alternate material saved successfully");
					showResponseMsg('err-msg','succ-msg');	
					document.getElementById('mat_close').click();
				}
				else if(msg=='0'){
					jQuery('#err-msg').html("Alternate material not saved");
					showResponseMsg('err-msg','succ-msg');
				}
	
				 	
			}
		});
		//}
}*/
function saveBOM(saveType,detId)
{
	document.getElementById('save_type').value=saveType;
	document.getElementById('bom_det_id').value=detId;
	document.getElementById('request_type').value="Normal";
	document.getElementById('invoke_class').value="com.alpha.tpcsfashion.servlet.BOMServlet";
	document.getElementById('invoke_method').value="doSaveBOM";
	document.forms[0].action=contextpath+"/FileUploadServlet";

	if(navigator.appName == "Microsoft Internet Explorer"){
		document.forms[0].encoding="multipart/form-data";
	}
	else{
		document.forms[0].enctype="multipart/form-data";	
	}
	document.forms[0].method="POST";
}


function deleteAltMaterial(maTId,rowId,detID)
{
	if(detID>0){
		bootbox.confirm("Are you sure you want to delete?", function(result) {
			if(result){
				   var url =contextpath+"/RequestHandlerServlet";
					var request = jQuery.ajax({
					url: url,
					type: "POST",
					data: {
						servlet_name : 'BOMServlet' , callbackmethod_name : 'doDeleteAltMaterial',mat_id:maTId,det_id:detID
					},
					dataType: "html"
					});
					request.done(function(msg) {
						
					    	 
					    	 if(msg==1){
								 document.getElementById('succ-msg').innerHTML="Alternate material deleted successfully";
								 showResponseMsg('err-msg','succ-msg');
								 deleteRowForAltMat(rowId);
							
							}
							else{
								 document.getElementById('err-msg').innerHTML="Alternate material delete failed";
								 showResponseMsg('err-msg','succ-msg');
							}
					    	   
					    	 
						
					});
					request.fail(function(jqXHR, textStatus) {
					alert( "Request failed: " + textStatus );
					});
		}
		});
	}
}



function deleteBOMComponents(compId,rowId,detID)
{
	
//	alert(compId);
	if(compId>0){
		bootbox.confirm("Are you sure you want to delete?", function(result) {
			if(result){
				   var url =contextpath+"/RequestHandlerServlet";
					var request = jQuery.ajax({
					url: url,
					type: "POST",
					data: {
						servlet_name : 'BOMServlet' , callbackmethod_name : 'doDeleteBOMComponet',component_id:compId,det_id:detID
					},
					dataType: "html"
					});
					request.done(function(msg) {
						
					    	 
					    	 if(msg==1){
								 document.getElementById('succ-msg').innerHTML="BOM Component deleted successfully";
								 showResponseMsg('err-msg','succ-msg');
								 deleteRowForBOMComp(rowId);
							
							}
							else{
								 document.getElementById('err-msg').innerHTML="BOM Component delete failed";
								 showResponseMsg('err-msg','succ-msg');
							}
					    	   
					    	 
						
					});
					request.fail(function(jqXHR, textStatus) {
					alert( "Request failed: " + textStatus );
					});
		}
		});
	}else{
		 deleteRowForBOMComp(rowId);
	}
}


function getBOMMaterialData(matId,detId){
	
	
	 var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'BOMServlet', callbackmethod_name : 'doGetMatData',
			material_id:matId
			},
		dataType: "xml",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + jqXHR);
		},
		success: function(xml) {
			
			jQuery(xml).find('material_data').each(function(){
				 
				var desc=jQuery(this).find('desc').text();
				var uom=jQuery(this).find('uom').text();
				var uomId=jQuery(this).find('uom_id').text();
				var rate=jQuery(this).find('rate').text();
				
				
				if(detId==0){
		
					
					var exRate=jQuery("#exchange_rate").val();
					
					if(exRate>0 && rate>0){
						rate =parseFloat(rate)/parseFloat(exRate);
					}
					jQuery("#uom_0").val( uom );
					jQuery("#uom_id_0").val( uomId );
					
					if(jQuery('#material_id_0').val().length!=0 && jQuery('#material_id_0').val()!=0 ){
						
						$('#validate-form').bootstrapValidator('revalidateField', 'uom_0');
					}
				}
				else{
					
					
					jQuery("#uom_S_"+detId).val( uom );
					jQuery("#uom_id_S_"+detId).val( uomId );
					
					
					if(jQuery('#material_id_S_'+detId).val().length!=0 && jQuery('#material_id_S_'+detId).val()!=0 ){
					$('#validate-form').bootstrapValidator('revalidateField', 'uom_S_'+detId);
					}
				}
				
				
				
			});
			 	
		}
	});
	
}




function deleteBOMAttachment(idx,bomId,fileName){

	bootbox.confirm(localizedStrings.DELETE_ATTACHMENT, function(result) {
		if(result){
			document.getElementById("ajax-loader").style.display="block";

			var url =contextpath+"/RequestHandlerServlet";

			var request = jQuery.ajax({
				url: url,
				type: "POST",
				data: {servlet_name : 'BOMServlet' , callbackmethod_name : 'doDeleteAttachment',bom_id:bomId , file_name:fileName,idx:idx},
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


function saveBOMLock(){
	
	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doSaveBOMLock";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}





function loadDataToBOMCompModal(){

	var bomDetId=document.getElementById('bom_det_id').value;
	
	
	
	var rowCnt=document.getElementById('row_count').value;

       //alert(bomDetId);
	var url =contextpath+"/RequestHandlerServlet";

	var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'BOMServlet' , callbackmethod_name : 'doGetBOMCompModalData',bom_det_id:bomDetId},
		dataType: "xml"
	});
	request.done(function(xml) {
		
		jQuery(xml).find('comp_data').each(function(){
			data=jQuery(this).find('comp_table').text();
			
		
			document.getElementById('bom_comp_det').innerHTML=data;
		});
		
		
		jQuery(xml).find('comp_auto_complete_field').each(function(){
			 var name=jQuery(this).find('name').text();
			 var id=jQuery(this).find('id').text();
			 var method=jQuery(this).find('method').text();

			 !function ($) {
		 			$(function(){
		 				  $('#validate-form-bom-comp #'+name).listBOMComp({
		 					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name="+method+"",
		 					  nameField :'#validate-form-bom-comp #'+name,
		 					 idField :'#validate-form-bom-comp #'+id,
		 					 createNew:'0'
		 				  });
		 			 });
		 		}(window.jQuery);
			
		});
		
		jQuery(xml).find('uom_auto_complete_field').each(function(){
			 var name=jQuery(this).find('name').text();
			 var id=jQuery(this).find('id').text();
			 var method=jQuery(this).find('method').text();
			 
	
			 
		    	 !function ($) {
		 			$(function(){
		 				  $('#validate-form-bom-comp #'+name).listUOM({
		 					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name="+method+"",
		 					  nameField :'#validate-form-bom-comp #'+name,
		 					 idField :'#validate-form-bom-comp #'+id,
		 					 createNew:'0'
		 				  });
		 			 });
		 		}(window.jQuery);
			
		});
		
		jQuery(xml).find('comp_rows').each(function(){
			rowCnt=jQuery(this).find('com_rowcnt').text();
			rowIds=jQuery(this).find('comp_rowIds').text();
			document.getElementById('row_count').value=rowCnt;
			document.getElementById('row_ids').value=rowIds;
		});



	});
	request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
	});

}


function calBOMCompReqQty(id){
	var length=jQuery('#comp_length_'+id).val();
	var width=jQuery('#comp_width_'+id).val();
	var nos=jQuery('#no_of_parts_'+id).val();
	if((length=='' || length==0) || (width=='' || width==0)){
		jQuery('#reqd_qty_'+id).val(nos);
	}else{
		jQuery('#reqd_qty_'+id).val(length*width*nos);	
	}
	
}

function addAnotherLineInBOMComp(rowID){
	rowCnt=$('#row_count').val();

	var allRowIds=$('#row_ids').val();
	$('#row_ids').val(allRowIds.replace(rowID+",",rowID+","+rowCnt+","));	

	$('#addrow_table tr#n_table_row'+(rowID)+'').after('<tr id="n_table_row'+rowCnt+'">'+
			'<td><input class="form-control" id="bom_comp_'+rowCnt+'" name="bom_comp_'+rowCnt+'" size="30" type="text" value="" placeholder="Component">'+
			'<input  id="bom_comp_id_'+rowCnt+'" name="bom_comp_id_'+rowCnt+'"  type="hidden" ></td>'+
			'<td><input class="form-control" id="comp_length_'+rowCnt+'" name="comp_length_'+rowCnt+'" onblur=\"calBOMCompReqQty('+rowCnt+');\"  size="30" type="text" value=""  placeholder="Length" >  </td>'+
			'<td><input class="form-control" id="comp_width_'+rowCnt+'" name="comp_width_'+rowCnt+'" onblur=\"calBOMCompReqQty('+rowCnt+');\" size="30" type="text" value=""  placeholder="Width" >  </td>'+
			'<td><input class="form-control" id="no_of_parts_'+rowCnt+'" name="no_of_parts_'+rowCnt+'" onblur=\"calBOMCompReqQty('+rowCnt+');\"  size="30" type="text" value=""  placeholder="No. of parts" >  </td>'+
			'<td><input class="form-control" id="meas_uom_'+rowCnt+'" name="meas_uom_'+rowCnt+'" size="30" type="text" value=""  placeholder="UOM" > '+
			'<input id="meas_uom_id_'+rowCnt+'" name="meas_uom_id_'+rowCnt+'"  type="hidden"  ></td>'+
			'<td><input class="form-control" id="reqd_qty_'+rowCnt+'" name="reqd_qty_'+rowCnt+'" size="30" type="text" value=""  placeholder="Reqd Qty" >  </td>'+
			'<td ><a href="javascript:saveBOMCopmponent(document.getElementById(\'bom_comp_id_'+rowCnt+'\').value,'+rowCnt+')" style="vertical-align: bottom;" data-toggle="tooltip" title="Save"><span class="glyphicon glyphicon-save text-primary"></span></a>&nbsp;'+
			'<a href="javascript:addAnotherLineInBOMComp('+rowCnt+')" style="vertical-align: bottom;" data-toggle="tooltip" title="Add"><span class="glyphicon glyphicon-plus text-primary"></span></a>&nbsp;<a href="javascript:deleteBOMComponents(document.getElementById(\'bom_comp_id_'+rowCnt+'\').value,'+rowCnt+',0)" style="vertical-align: bottom;" data-toggle="tooltip" title="Remove" ><span class="glyphicon glyphicon-remove icon-delete"></span></a>&nbsp; '+
			'</td>'+	
	'</tr>');
	 !function ($) {
			$(function(){
				  $('#validate-form-bom-comp #bom_comp_'+rowCnt).listBOMComp({
					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetBOMComp",
					  nameField :'#validate-form-bom-comp #bom_comp_'+rowCnt,
					 createNew:'0'
				  });
			
				  $('#validate-form-bom-comp #meas_uom_'+rowCnt).listUOM({
					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetUOM",
					  nameField :'#validate-form-bom-comp #meas_uom_'+rowCnt,
					  idField :'#validate-form-bom-comp #meas_uom_id_'+rowCnt,
					 createNew:'0'
				  });
				  
				  rowCnt=parseInt(rowCnt)+1;

					$('#row_count').val(rowCnt);
			 });
			
		}(window.jQuery);

	

}


function deleteRowForBOMComp(rowNo){
	
	$('#addrow_table #n_table_row'+rowNo).remove();
	var rowIds=$('#row_ids').val();
	$('#row_ids').val(rowIds.replace(rowNo+",",""));
}



function saveBOMCopmponent(compId,rowId){
	
	
	var bomDetId=jQuery('#bom_det_id').val();
	var bomComp=document.getElementById('bom_comp_'+rowId).value.trim();
	var compLength=document.getElementById('comp_length_'+rowId).value.trim();
	var compWidth=document.getElementById('comp_width_'+rowId).value.trim();
	var noOfParts=document.getElementById('no_of_parts_'+rowId).value.trim();
	

	var reqQty=document.getElementById('reqd_qty_'+rowId).value.trim();
	var uom=document.getElementById('meas_uom_'+rowId).value.trim();
	var mainpageUom=document.getElementById('uom_S_'+bomDetId).value.trim();
	
		//if(jQuery('#validate-form-altMat .has-error').length==0){
		 var url =contextpath+"/RequestHandlerServlet";
			jQuery.ajax({
			url: url,
			type: "POST",
			data: {
				servlet_name : 'BOMServlet' , callbackmethod_name : 'doSaveBOMCopmponent',
				bom_comp:bomComp,comp_length:compLength,comp_width:compWidth,no_of_parts:noOfParts,reqd_qty:reqQty,meas_uom:uom,bom_det_id:bomDetId,component_id:compId,main_uom:mainpageUom
				},
			dataType: "xml",
			error: function(jqXHR, textStatus, errorThrown) {
				alert( "Request failed: " + errorThrown);
			},
			success: function(xml) {
				
				
				jQuery(xml).find('bom_status').each(function(){
					
			    	 msg=jQuery(this).find('msg').text();
			    	 qty=jQuery(this).find('req_qty').text();
			    	 compId=jQuery(this).find('bom_comp_id').text();
			
			    		
			    		if(msg=='1'){
							jQuery('#succ-msg').html("BOM Components saved successfully");
							showResponseMsg('err-msg','succ-msg');
							jQuery('#bom_comp_id_'+rowId).val(compId);
						//	document.getElementById('comp_close').click();
						}
						else if(msg=='0'){
							jQuery('#err-msg').html("BOM Components not saved");
							showResponseMsg('err-msg','succ-msg');
						}
			    	   if(qty>0){
			    		   
			    		   jQuery('#required_qty_S_'+bomDetId).val(qty);
			    	   }
			    	 
			});
				
		
				 	
			}
		});
		//}
}






function deleteAltMaterial(maTId,rowId,detID)
{
	if(detID>0){
		bootbox.confirm("Are you sure you want to delete?", function(result) {
			if(result){
				   var url =contextpath+"/RequestHandlerServlet";
					var request = jQuery.ajax({
					url: url,
					type: "POST",
					data: {
						servlet_name : 'BOMServlet' , callbackmethod_name : 'doDeleteAltMaterial',mat_id:maTId,det_id:detID
					},
					dataType: "html"
					});
					request.done(function(msg) {
						
					    	 
					    	 if(msg==1){
								 document.getElementById('succ-msg').innerHTML="Alternate material deleted successfully";
								 showResponseMsg('err-msg','succ-msg');
								 deleteRowForAltMat(rowId);
							
							}
							else{
								 document.getElementById('err-msg').innerHTML="Alternate material delete failed";
								 showResponseMsg('err-msg','succ-msg');
							}
					    	   
					    	 
						
					});
					request.fail(function(jqXHR, textStatus) {
					alert( "Request failed: " + textStatus );
					});
		}
		});
	}
}




function selectBOMNoDet(){

	var custId=jQuery('#customer_id').val();
	var url =contextpath+"/RequestHandlerServlet";
	
	var request = jQuery.ajax({
	url: url,
	type: "POST",
	data: {servlet_name : 'BOMServlet' , callbackmethod_name : 'doGetBOMNoDetails',customer_id:custId},
	dataType: "html"
	});
	request.done(function(msg) {
	
		jQuery("#trNoList").html( msg );
		
	});
	request.fail(function(jqXHR, textStatus) {
	alert( "Request failed: " + textStatus );
	
	});
	
	
}


function selectedBOMNo(bomNo,bomId,custId,cust)
{
	 document.getElementById('parent_bom_no').value=bomNo;
	 document.getElementById('parent_bom_id').value=bomId;
	 
	 var pageCustId=jQuery('#customer_id').val();
	 
	 if(pageCustId=='' || pageCustId==0){
		 
		 document.getElementById('customer_id').value=custId;
		 document.getElementById('customer').value=cust;
	 }
	 
	 document.getElementById('trno_close').click();
}



function addBOMDetailFrmParent(mode){
	 
	
		
		 $('#validate-form').bootstrapValidator('revalidateField', 'bom_no');
		  $('#validate-form').bootstrapValidator('revalidateField', 'tr_date');
		
		
		  if(mode=='n'){
			  $('#validate-form').find(".has-error :input:first").focus();
			  
			    document.getElementById('servlet_name').value="BOMServlet";
				document.getElementById('callbackmethod_name').value="doAddBOMDetail";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";
				if(jQuery('#validate-form .has-error').length==0){
					document.forms[0].submit();	
				}
		  }
		  else{
				  
				    document.getElementById('servlet_name').value="BOMServlet";
					document.getElementById('callbackmethod_name').value="doAddBOMDetail";
					document.forms[0].action=contextpath+"/RequestHandlerServlet";	
					document.forms[0].method="POST";
					document.forms[0].submit();	
			  
		  }
	
}


function chkBomNoExist(){
	
	
	var bomNo=jQuery('#bom_no').val();
	var bomId=jQuery('#bom_id').val();
	
	
	
	var url =contextpath+"/RequestHandlerServlet";
	
	var request = jQuery.ajax({
	url: url,
	type: "POST",
	data: {
		servlet_name : 'BOMServlet', callbackmethod_name : 'doChkBOMNoExist',bom_no:bomNo,bom_id:bomId
	},
	dataType: "html"
	});
	request.done(function(msg) {
	if(msg==1){
		 document.getElementById('warning-msg').innerHTML="BOM No Already Exist";
		 showWarningMsg('warning-msg');
	}
	});
	request.fail(function(jqXHR, textStatus) {
	alert( "Request failed: " + textStatus );
	});
	
}


function showBOMDetailgrid(bomId){
	
	
	if (jQuery('#add_row_'+bomId).length==1)
	{
		jQuery('#add_row_'+bomId).remove();
     }else{
		
			
	 var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'BOMServlet', callbackmethod_name : 'doshowBOMDetails',bom_id:bomId
			},
		dataType: "html",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + jqXHR);
		},
		success: function(data) {
			
			if (jQuery('#add_row_'+bomId).length==1){
				jQuery('#add_row_'+bomId).remove();
		     }
			jQuery('#bom_row_'+bomId).after(data);
			
		}
	});
	
 }
}


