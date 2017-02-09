
	function validateIND(headMode,saveType){

	if(validateTranDate('indent_date','fin_startdate','fin_enddate','books_bgng_date','current_date','aind_form')){
		
		if(headMode=='n'){
			
			saveIndent(saveType);
			document.getElementById('aind_validation_btn').disabled=false;
			document.getElementById('aind_validation_btn').click();
		}
		else{
			if(validateFixedDetailIND()){
				saveIndent(saveType);
				document.getElementById('aind_validation_btn').disabled=false;
				document.getElementById('aind_validation_btn').click();
			}
			else{
				saveIndent(saveType);
				document.getElementById('new_det_mode').value='0';//New Detail Row Should Not Insert
				document.forms[0].submit();
				 
			}
		}	
	
		}
	}


	function saveIndent(saveType){
		
//		alert('in save method');
//		document.getElementById('prefix').disabled = false;;
		document.getElementById('save_type').value=saveType;
		document.getElementById('request_type').value="Normal";
		document.getElementById('invoke_class').value="com.alpha.tpcsfashion.servlet.IndentServlet";
		document.getElementById('invoke_method').value="doSaveIndent";
		document.forms[0].action=contextpath+"/FileUploadServlet";
		
		if(navigator.appName == "Microsoft Internet Explorer"){
			document.forms[0].encoding="multipart/form-data";
		}
		else{
			document.forms[0].enctype="multipart/form-data";	
		}
		document.forms[0].method="POST";
	}



	function validateFixedDetailIND(){
		
		
		if(document.getElementById('item_id_0').value.trim().length!=0){
			return true;
		}
		 if(document.getElementById('size_range_0').value.trim().length!=0){
			return true;
		}
		else if(document.getElementById('required_date_0').value.trim().length!=0){
			return true;
		}
		
		else{
			return false;
		}
		

	}
	

	
	
	
	
	
	
	function clearINDSearch(){
		setVal('indent_name','');
		setVal('indent_id','');
		setVal('indent_from_date','');
		setVal('indent_to_date','');
		setVal('indent_no','');
		setVal('deptartment','');
		
		
	}
	
	
	function clearNewINDDetail(id){
		setVal('indent_line_no_0','');
		setVal('item_name_0','');
		setVal('item_id_0','');
		setVal('colour_name_0','');
		setVal('colour_id_0','');
		setVal('size_range_0','');
		setVal('size_range_id_0','');
		setVal('indent_qty_0','');
		setVal('uom_0','');
		setVal('buyer_name_0','');
		setVal('buyer_id_0','');
		setVal('required_date_0','');
		setVal('remark_0','');

	}

	
	
function displayINDActionList(actionType,pageno,indentId,indentDetId) {
		
		
		
		
		if(actionType==1){
			viewIndent('e',indentId);
		}
		else if(actionType==2){
			editIndent('e',indentId);
		}
		else if(actionType==3){
			deleteIndent(pageno,indentId,indentDetId,0);
		}
		
		else if(actionType==4){
			printIndent(pageno,indentId);
		}
		
		else if(actionType==5){
			cancelIndent(pageno,indentId);
		}
		
		else if(actionType==7){
			closeIndent(pageno,indentId);
		}
		
		else if(actionType==8){
			closeIndent(pageno,indentId);
		}
}
	
	
	
function changeINDPageNo(pageno){
		
		document.getElementById('servlet_name').value="IndentServlet";
		document.getElementById('callbackmethod_name').value="doDisplayIndent";
		document.getElementById('request_type').value="Search";
		document.getElementById('pageno').value=pageno;
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
function searchIndent(){
		document.getElementById('request_type').value="Search";
		document.getElementById('servlet_name').value="IndentServlet";
		document.getElementById('callbackmethod_name').value="doDisplayIndent";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();
	}
function viewIndent(headMode,indentId){
		
		document.getElementById('header_mode').value=headMode;
		document.getElementById('indent_id').value=indentId;
		document.getElementById('view_mode').value="yes";
		document.getElementById('servlet_name').value="IndentServlet";
		document.getElementById('callbackmethod_name').value="doAddIndent";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}
function editIndent(headMode,indentId){
		
		document.getElementById('header_mode').value=headMode;
		document.getElementById('indent_id').value=indentId;
		document.getElementById('servlet_name').value="IndentServlet";
		document.getElementById('callbackmethod_name').value="doAddIndent";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}
function amendIndent(headMode,indentId){
		bootbox.confirm("Do you want to post new amendment?", function(result) {
			 if(result){
		//document.getElementById('request_type').value="Normal";
		document.getElementById('header_mode').value=headMode;
		document.getElementById('indent_id').value=indentId;
		document.getElementById('servlet_name').value="IndentServlet";
		document.getElementById('callbackmethod_name').value="doAddIndent";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();
			 }
		});
		
		
	}
	
function printIndent(headMode,indentId){

		if(indentId<=0){
			document.getElementById('warning-msg').innerHTML="Please save the transaction";
			showWarningMsg('warning-msg');
			return false;
		} 	
		else{
			document.getElementById('request_type').value="Normal";
			document.getElementById('header_mode').value=headMode;
			document.getElementById('indent_id').value=indentId;
			document.getElementById('servlet_name').value="IndentServlet";
			document.getElementById('callbackmethod_name').value="doPrintIndent";
			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
			document.forms[0].method="POST";	
			document.forms[0].submit();

		}

	}
	
	
	
function deleteIndent(pageno,indentId,indentDetId,page){
		
		if(indentId<=0){
			document.getElementById('warning-msg').innerHTML="Please save the transaction";
			showWarningMsg('warning-msg');
			 return false;
			 } 	
		else{
			 bootbox.confirm("Are you sure you want to delete?", function(result) {
				 if(result){
					    document.getElementById('servlet_name').value="IndentServlet";
						document.getElementById('callbackmethod_name').value="doDeleteIndent";
						document.getElementById('indent_id').value=indentId;
						document.forms[0].action=contextpath+"/RequestHandlerServlet?page="+page;	
						document.forms[0].method="POST";	
						document.forms[0].submit();	
				 }
				});
		}
	}
	
	
function deleteIndentDet(indentId,indentDetId){
		
		bootbox.confirm("Are you sure you want to delete?", function(result) {
			 if(result){
				 document.getElementById('servlet_name').value="IndentServlet";
					document.getElementById('callbackmethod_name').value="doDeleteIndentDetail";
					document.getElementById('indent_id').value=indentId;
					document.getElementById('indent_det_id').value=indentDetId;
					document.forms[0].action=contextpath+"/RequestHandlerServlet";	
					document.forms[0].method="POST";	
					document.forms[0].submit();
			 }
		});
	}
	
	
	
function cancelIndent(pageno,indentId){
	
	bootbox.confirm("Are you sure you want to cancel?", function(result) {
		 if(result){
			 	document.getElementById('request_type').value="Normal";
			 	document.getElementById('servlet_name').value="IndentServlet";
				document.getElementById('callbackmethod_name').value="doCancelIndent";
				document.getElementById('indent_id').value=indentId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
		 }
	});
}
	

	
function closeIndent(pageno,indentId){
	
	bootbox.confirm("Are you sure you want to cancel?", function(result) {
		 if(result){
			 	document.getElementById('servlet_name').value="IndentServlet";
				document.getElementById('callbackmethod_name').value="doCloseIndent";
				document.getElementById('indent_id').value=indentId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
		 }
	});
}
	
	
	


function exportBulkIndentToPDF(){
	
	var cnt = jQuery("input[name='indent_id']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
	document.getElementById('pageno').value="1";
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="IndentServlet";
	document.getElementById('callbackmethod_name').value="doExportBulkIndentPdf";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?";
	document.forms[0].method="post";
	document.forms[0].submit();
	}
}

	
	
function exportSendBulkIndent(){
	
	var cnt = jQuery("input[name='indent_id']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
	document.getElementById('pageno').value="1";
	document.getElementById('request_type').value="Normal";
	document.getElementById('servlet_name').value="IndentServlet";
	document.getElementById('callbackmethod_name').value="doSendBulkSalesEnquiryPdf";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?";
	document.forms[0].method="post";
	document.forms[0].submit();
	}
}	
	


function getINDSizeRangeSizeGrid(indentDetId,styleField,sizeRangeField){
	
	

	var indentId=jQuery('#indent_id').val();
	var itemId=jQuery('#'+styleField).val();
	var sizeRangeId=jQuery('#'+sizeRangeField).val();
	
	if(sizeRangeId!=0){
		
		var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
			url: url,
			type: "POST",
			data: {
				servlet_name : 'IndentServlet', callbackmethod_name : 'doGetINDSizeRangeSizeGrid',
				size_range_id:sizeRangeId,item_id:itemId,indent_id:indentId,indent_det_id:indentDetId
			},
			dataType: "xml",
			success: function(xml) {
				jQuery(xml).find('size').each(function(){
					
					var sizeExist=jQuery(this).find('size_exists').text();
					var sizeGrid=jQuery(this).find('size_grid').text();
					
					if(sizeExist=="1"){
						jQuery('#size_grid_'+indentDetId).html(sizeGrid);	
					}
					else{
						jQuery('#size_grid_'+indentDetId).html("");
					}
					
				});
				

			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert( "Request failed: " + jqXHR);
			}
		});
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
		jQuery('#indent_qty_S_'+detailId).val(sizeTotQty);
		jQuery('#aind_form').bootstrapValidator('revalidateField', 'indent_qty_S_'+detailId);
		setEditedId('indent_qty_S_'+detailId);
	}
	else{
		jQuery('#indent_qty_'+detailId).val(sizeTotQty);
		jQuery('#aind_form').bootstrapValidator('revalidateField', 'indent_qty_'+detailId);
//		setEditedId('qty_'+detailId);
	}
}




function closeBulkIndent(){
	
	var cnt = jQuery("input[name='indent_chk_id']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
		
		bootbox.confirm("Do you want to Close?", function(result) {
			if(result){
				document.getElementById('request_type').value="Normal";
				document.getElementById('servlet_name').value="IndentServlet";
				document.getElementById('callbackmethod_name').value="doCloseBulkIndent";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}
}



function deleteBulkIndent(){
	
	var cnt = jQuery("input[name='indent_chk_id']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
		
		bootbox.confirm("Do you want to Delete?", function(result) {
			if(result){
				document.getElementById('request_type').value="Normal";
				document.getElementById('servlet_name').value="IndentServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBulkIndent";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}
}

function cancelBulkIndent(){
	
	var cnt = jQuery("input[name='indent_chk_id']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
    } 	
	else{
		
		bootbox.confirm("Do you want to Cancel?", function(result) {
			if(result){
				document.getElementById('request_type').value="Normal";
				document.getElementById('servlet_name').value="IndentServlet";
				document.getElementById('callbackmethod_name').value="doCancelBulkIndent";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}
}

	
function selectSoNoDet(detId){

	var url =contextpath+"/RequestHandlerServlet";
	
	var request = jQuery.ajax({
	url: url,
	type: "POST",
	data: {servlet_name : 'IndentServlet' , callbackmethod_name : 'doGetSoNoDetails',indent_det_id:detId},
	dataType: "html"
	});
	request.done(function(msg) {
	
		jQuery("#trNoList").html( msg );
		
	});
	request.fail(function(jqXHR, textStatus) {
	alert( "Request failed: " + textStatus );
	
	});
	
}

function selectedSONo(indDetId,soNo,soDetId,soPrd,soCol,soSze,soRqDt){
	
	if(indDetId>0){
		
		
		 document.getElementById('so_no_S_'+indDetId).value=soNo+" / "+soPrd+" / "+soCol+" / "+soSze+" / "+soRqDt;
		 document.getElementById('so_det_id_S_'+indDetId).value=soDetId;
		 
			document.getElementById('edited_ids').value=document.getElementById('edited_ids').value+'so_det_id_S_'+indDetId+",";
			document.getElementById('edited_mode').value='e';
			
			
		 document.getElementById('trno_close').click();
		 
	}else{
		
		
		 document.getElementById('so_no_'+indDetId).value=soNo+" / "+soPrd+" / "+soCol+" / "+soSze+" / "+soRqDt;
		 document.getElementById('so_det_id_'+indDetId).value=soDetId;
		 
		 document.getElementById('trno_close').click();
		 
		
	}

}


//report
function changeIndentApprovalRegisterPageNumbers(pageno){

	document.getElementById('servlet_name').value="IndentApprovalRegisterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayIndentApprovalRegister";
	document.getElementById('request_type').value="Search";
	document.getElementById('pageno').value=pageno;
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
} 
function searchIndentApprovalRegister(){
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="IndentApprovalRegisterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayIndentApprovalRegister";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
function clearIndentApprovalRegister(){
	setVal('variant_name','');
	setVal('variant_id','');
	setVal('supplier_name','');
	setVal('supplier_id','');
	setVal('uom','');
	setVal('uom','');
}