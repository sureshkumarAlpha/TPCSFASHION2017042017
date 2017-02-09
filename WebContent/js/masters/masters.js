
function addOperationOrStage(mode)
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('mode').value=mode; 
	document.getElementById('servlet_name').value="OperationOrStageServlet";
	document.getElementById('callbackmethod_name').value="doNewOperationOrStage";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function saveoprationStage(mode,saveType)
{
	
	document.getElementById('mode').value=mode;
	
	document.getElementById('save_type').value=saveType;
	document.getElementById('servlet_name').value="OperationOrStageServlet";
	document.getElementById('callbackmethod_name').value="doSaveOperationOrStage";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
//	document.forms[0].submit();	
	document.getElementById('validation_btn').click();
}

function OperationActionList(actionType,opId,pageno)
{
	if(actionType== 1){
		
		deleteOperationMaster(opId,pageno);
	}
	else if(actionType==2){
		
		editOperationMaster('e',opId);
	}else
		{
		DeActiveOperation(opId,pageno)
		}
	
}
function editOperationMaster(editmode,opId)
{
	document.getElementById('mode').value=editmode;
	document.getElementById('operation_id').value=opId;
	document.getElementById('servlet_name').value="OperationOrStageServlet";
	document.getElementById('callbackmethod_name').value="doEditOperationOrStage";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
function deleteOperationMaster(opId,pageno){	
	
	bootbox.confirm(localizedStrings.SURE_TO_DELETE_GROUP, function(result) {
		if(result){
		document.getElementById('pageno').value=pageno;
		document.getElementById('operation_id').value=opId;
		document.getElementById('servlet_name').value="OperationOrStageServlet";
		document.getElementById('callbackmethod_name').value="doDeleteOperationOrStageMaster";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
	});
}

function searchOperationMaster()
{
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="OperationOrStageServlet";
	document.getElementById('callbackmethod_name').value="doDisplayOperationOrStage";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function clearOperationSearch()
{
	document.getElementById('operation').value="";
	document.getElementById('operation_id').value="";
	document.getElementById('seq_no').value="";
}
function changeOperationOrStagePageNumber(pageNo)
{
	document.getElementById('pageno').value=pageNo;
	document.getElementById('servlet_name').value="OperationOrStageServlet";
	document.getElementById('callbackmethod_name').value="doDisplayOperationOrStage";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function DeActiveOperation(opId,pageNo)
{
	document.getElementById('pageno').value=pageNo;
	document.getElementById('operation_id').value=opId;
	document.getElementById('servlet_name').value="OperationOrStageServlet";
	document.getElementById('callbackmethod_name').value="doDeActiveOperationOrStage";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

//function DeActiveOperation(opId,pageNo)
//{
//	document.getElementById('pageno').value=pageNo;
//	document.getElementById('operation_id').value=opId;
//	document.getElementById('servlet_name').value="OperationOrStageServlet";
//	document.getElementById('callbackmethod_name').value="doDeActiveOperationOrStage";
//	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
//	document.forms[0].method="POST";	
//	document.forms[0].submit();	
//}
function activeBulkOperation(pageNo,active_mode)  
{
	var cnt = jQuery("input[name='bulkoperatonId']:checked").length;
	if (cnt < 1) 
    {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
		return false;
    } 	
	document.getElementById('pageno').value=pageNo;
	document.getElementById('active_mode').value=active_mode;
	document.getElementById('servlet_name').value="OperationOrStageServlet";
	document.getElementById('callbackmethod_name').value="doBulkActionOperationOrStage";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

// Spec Setting
function addNewSpecificationSetting(){		
	
	document.getElementById('servlet_name').value="SpecificationSettingServlet";
	document.getElementById('callbackmethod_name').value="doNewSpecificationSetting";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}

function saveSpecificationSetting(mode,savetype){ 
	if(mode=='n'){
	document.getElementById('mode').value=mode;
	document.getElementById('save_type').value=savetype;
	document.getElementById('servlet_name').value="SpecificationSettingServlet";
	document.getElementById('callbackmethod_name').value="doSaveSpecificationSetting";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.getElementById('validation_btn').click();
	}
	else
	{
		document.getElementById('mode').value=mode;
		document.getElementById('save_type').value=savetype;
		document.getElementById('servlet_name').value="SpecificationSettingServlet";
		document.getElementById('callbackmethod_name').value="doupdateSpecificationSetting";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
}
function specSettingActionList(actionType,pageno,groupTypeId,spec1,specLen1,spec2,specLen2,spec3,specLen3,spec4,specLen4,spec5,specLen5,spec6,specLen6,spec7,specLen7)
{
	 if(actionType== 1)
	 {
		 deleteSpecSetting(pageno,groupTypeId,spec1,specLen1,spec2,specLen2,spec3,specLen3,spec4,specLen4,spec5,specLen5,spec6,specLen6,spec7,specLen7);
		 }
	 else if(actionType==2)
	 {
		 editSpecSetting('e',pageno,groupTypeId,spec1,specLen1,spec2,specLen2,spec3,specLen3,spec4,specLen4,spec5,specLen5,spec6,specLen6,spec7,specLen7);
	}
	
}

function editSpecSetting(mode,pageno,groupTypeId,spec1,specLen1,spec2,specLen2,spec3,specLen3,spec4,specLen4,spec5,specLen5,spec6,specLen6,spec7,specLen7){	
	
	document.getElementById('mode').value=mode;
	document.getElementById('pageno').value=pageno;
	document.getElementById('group_type').value=groupTypeId;
	document.getElementById('specification1').value=spec1;
	document.getElementById('specification1_length').value=specLen1;
	document.getElementById('specification2').value=spec2;
	document.getElementById('specification2_length').value=specLen2;
	document.getElementById('specification3').value=spec3;
	document.getElementById('specification3_length').value=specLen3;
	document.getElementById('specification4').value=spec4;
	document.getElementById('specification4_length').value=specLen4;
	document.getElementById('specification5').value=spec5;
	document.getElementById('specification5_length').value=specLen5;
	document.getElementById('specification6').value=spec6;
	document.getElementById('specification6_length').value=specLen6;
	document.getElementById('specification7').value=spec7;
	document.getElementById('specification7_length').value=specLen7;

	document.getElementById('servlet_name').value="SpecificationSettingServlet";
	document.getElementById('callbackmethod_name').value="doEditSpecificationSetting";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}

function deleteSpecSetting(pageno,groupTypeId,spec1,specLen1,spec2,specLen2,spec3,specLen3,spec4,specLen4,spec5,specLen5,spec6,specLen6,spec7,specLen7)
{	
	
	if(confirm("Are you sure you want to delete selected specification setting ?")){
		
	document.getElementById('pageno').value=pageno;
	document.getElementById('group_type').value=groupTypeId;
	document.getElementById('specification1').value=spec1;
	document.getElementById('specification1_length').value=specLen1;
	document.getElementById('specification2').value=spec2;
	document.getElementById('specification2_length').value=specLen2;
	document.getElementById('specification3').value=spec3;
	document.getElementById('specification3_length').value=specLen3;
	document.getElementById('specification4').value=spec4;
	document.getElementById('specification4_length').value=specLen4;
	document.getElementById('specification5').value=spec5;
	document.getElementById('specification5_length').value=specLen5;
	document.getElementById('specification6').value=spec6;
	document.getElementById('specification6_length').value=specLen6;
	document.getElementById('specification7').value=spec7;
	document.getElementById('specification7_length').value=specLen7;

	document.getElementById('servlet_name').value="SpecificationSettingServlet";
	document.getElementById('callbackmethod_name').value="doDeleteSpecificationSetting";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	}
}

function changeSpecSettingPageNumber(pageNo)
{
	document.getElementById('pageno').value=pageNo;
	document.getElementById('servlet_name').value="SpecificationSettingServlet";
	document.getElementById('callbackmethod_name').value="doDisplaySpecificationSetting";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function searchspecSetting(){
  	
	document.getElementById('request_type').value="Search";
  	document.getElementById('servlet_name').value="SpecificationSettingServlet";
  	document.getElementById('callbackmethod_name').value="doDisplaySpecificationSetting";
  	document.forms[0].action=contextpath+"/RequestHandlerServlet";
  	document.getElementById('pageno').value="1";
  	document.forms[0].method="POST";
  	document.forms[0].submit();
  	
  }
function clearSearchSpecSetting()
{
	document.getElementById('group_type_name').value="";
	document.getElementById('group_type_id').value="";
	document.getElementById('specification').value="";
}


//Warehouse master


function addNewWarehouse(){		
	
	document.getElementById('servlet_name').value="WarehouseMasterServlet";
	document.getElementById('callbackmethod_name').value="doNewWarehouse";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}

function saveWarehouse(mode,saveType)
{
	//System.out.println("Warehouse");
	document.getElementById('mode').value=mode;
	
	document.getElementById('save_type').value=saveType;
	document.getElementById('servlet_name').value="WarehouseMasterServlet";
	document.getElementById('callbackmethod_name').value="doSaveWarehouse";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	//document.forms[0].submit();	
	document.getElementById('validation_btn').click();
}
function changeWarehousePageNumber(pageNo)
{
	document.getElementById('pageno').value=pageNo;
	document.getElementById('servlet_name').value="WarehouseMasterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayWarehouse";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function warehouseActionList(actionType,stId,pageno)
{
	if(actionType== 1){
		
		deleteWarehouse(stId,pageno);
	}
	else  if(actionType==2)
		{

		editWarehouse('e',stId);
		}
     else
       {
        DeActiveWarehouse(stId,pageno)
       }

	
}

function editWarehouse(mode,storeId)
{
	document.getElementById('mode').value=mode;
	document.getElementById('store_id').value=storeId;
	document.getElementById('servlet_name').value="WarehouseMasterServlet";
	document.getElementById('callbackmethod_name').value="doEditWarehouse";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}

function deleteWarehouse(storeId,pageno){	
	
	bootbox.confirm(localizedStrings.SURE_TO_DELETE_WAREHOUSE, function(result) {
		if(result){
		document.getElementById('pageno').value=pageno;
		document.getElementById('store_id').value=storeId;
		document.getElementById('servlet_name').value="WarehouseMasterServlet";
		document.getElementById('callbackmethod_name').value="doDeleteWarehouse";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	
	}
	
});
}	
		
  function searchWarehouse()
{
			document.getElementById('request_type').value="Search";
			document.getElementById('servlet_name').value="WarehouseMasterServlet";
			document.getElementById('callbackmethod_name').value="doDisplayWarehouse";
			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
			document.forms[0].method="POST";	
			document.forms[0].submit();	
}
		
  
  
function clearWarehouseSearch()
{
	document.getElementById('store_name').value="";
	document.getElementById('store_id').value="";
	document.getElementById('store_code').value="";
}
		
  function DeActiveWarehouse(stId,pageNo)
  {
  	document.getElementById('pageno').value=pageNo;
  	document.getElementById('store_id').value=stId;
  	document.getElementById('servlet_name').value="WarehouseMasterServlet";
  	document.getElementById('callbackmethod_name').value="doDeActiveWarehouse";
  	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
  	document.forms[0].method="POST";	
  	document.forms[0].submit();	
  }	
  
  function activeBulkWarehouse(pageNo,active_mode)  
  {
  	var cnt = jQuery("input[name='bulkoperatonId']:checked").length;
  	if (cnt < 1) 
      {
  		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
  		showWarningMsg('warning-msg');
  		return false;
      } 	
  	document.getElementById('pageno').value=pageNo;
  	document.getElementById('active_mode').value=active_mode;
  	document.getElementById('servlet_name').value="WarehouseMasterServlet";
  	document.getElementById('callbackmethod_name').value="doBulkActionWarehouse";
  	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
  	document.forms[0].method="POST";	
  	document.forms[0].submit();	
  }
  function deleteBulkWarehouse(pageno)
  {
  	 var cnt = jQuery("input[name='store_id']:checked").length;
       if (cnt < 1) 
       {
  		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
  		//document.getElementByName('sales_enqid').focus();
  		showWarningMsg('warning-msg');
//  		return false;
       } 	
  	else{

  		bootbox.confirm("Are you sure you want to delete?", function(result) {
  			if(result){
  				document.getElementById('servlet_name').value="WarehouseMasterServlet";
  				document.getElementById('callbackmethod_name').value=" doDeleteBulkWarehouse";
  				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
  				document.forms[0].method="POST";	
  				document.forms[0].submit();
  			}
  		});
  	}

  }
//Approved Price List 
  function addApprovedPriceList(mode)
  {
  	document.getElementById('request_type').value="Normal";
  	document.getElementById('mode').value=mode; 
    	document.getElementById('servlet_name').value="ApprovedPriceListServlet";
    	document.getElementById('callbackmethod_name').value="doNewApprovedPriceList";
    	document.forms[0].action=contextpath+"/RequestHandlerServlet";
    	document.getElementById('pageno').value="1";
    	document.forms[0].method="POST";
    	document.forms[0].submit();
    	
  }

  function getSizeDetailsGrid(purchaseprice_id){
			var sizeRrangeId=jQuery('#size_range_id_0').val();
			var sizemode=jQuery('#mode').val();
			
			if(sizeRrangeId!=0){
//			var purchaseprice_id=jQuery('#purchase_price_id').val();
		 var url =contextpath+"/RequestHandlerServlet";
			jQuery.ajax({
			url: url,
			type: "POST",
			data: {
				servlet_name : 'ApprovedPriceListServlet', callbackmethod_name : 'doGetSizeGrid',
				size_Rrange_Id:sizeRrangeId,mode:sizemode,purchase_price_id:purchaseprice_id
				},
			dataType: "html",
			error: function(jqXHR, textStatus, errorThrown) {
				alert( "Request failed: " + jqXHR);
			},
			success: function(html) {
				jQuery('#size_grid').html(html);
				 	
			}
		});
			var detailId=document.getElementById('purchase_price').value;
			 detailId=detailId+purchaseprice_id+',';
			 setVal('purchase_price',detailId);
		}
	}
  function setEditedSizeId(id) {

		var val=getVal(id);
		if(val.trim().length==0){
				setVal(id,0);
			}
		
		var updateIds=document.getElementById('update').value;
		updateIds=updateIds+id+',';
		setVal('update',updateIds);
		
		removeDuplicateIds('update');
	}
  function removeDuplicateIds(str){

		var ids=getVal(str);
		var ar1 = new Array();
		ar1=ids.split(',');
		setVal(str,ar1.unique());
	}
  
 
  function validateAppPriceList(mode,savetype)
  {
	  if(mode=='n'){

		  dosaveApprovedPriceList(mode,savetype);
			document.getElementById('validation_btn').click();
		}
		else{
			if(validateFixedDetailAppPeiceList()){
				dosaveApprovedPriceList(mode,savetype);
				document.getElementById('validation_btn').click();
			}
			else{
				dosaveApprovedPriceList(mode,savetype);
				document.forms[0].submit();

			}

		}  
  }
  function validateFixedDetailAppPeiceList(){

		if(document.getElementById('mat_id_0').value.trim().length!=0)
		{
			return true;
		}
		else if(document.getElementById('color_id_0').value.trim().length!=0)
		{
			return true;
		}

		else{
			return false;
		}


	} 
  function  dosaveApprovedPriceList(mode,savetype)
  {
	  		
	  		document.getElementById('request_type').value="Normal";
	  		document.getElementById('mode').value=mode; 
	  		document.getElementById('save_type').value=savetype; 
	    	document.getElementById('servlet_name').value="ApprovedPriceListServlet";
	    	document.getElementById('callbackmethod_name').value="doSaveApprovedPriceList";
	    	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	    	document.getElementById('pageno').value="1";
	    	document.forms[0].method="POST";
//	    	document.forms[0].submit();
//	    	document.getElementById('validation_btn').click();
  }
  
  function clearNewDetail()
  {
  	setVal('mat_id_0','');
  	setVal('mat_0','');
  	setVal('variant_id_0','');
  	setVal('variant_name_0','');
  	setVal('uom_0','');
  	setVal('rate_0','');
  	setVal('currency_name_0','');
  	setVal('lead_time_days_0','');
  	setVal('supplier_desc_0','');
  	setVal('moq_0','');
  	
  }	
  
  function deleteApprovedPriceListDet(supplierId,priceDetId)
  {
	  bootbox.confirm("Are you sure you want to delete?", function(result) {
		  if(result){
			  document.getElementById('servlet_name').value="ApprovedPriceListServlet";
			  document.getElementById('callbackmethod_name').value="doDeleteApprovedPriceListDetail";
			  document.getElementById('supplier_id').value=supplierId;
			  document.getElementById('purchase_price_id').value=priceDetId;
			  document.forms[0].action=contextpath+"/RequestHandlerServlet";	
			  document.forms[0].method="POST";	
			  document.forms[0].submit();
		  }
	  });
  } 
  
  function displayApprovedPriceListActionList(actionType,pageno,suppId,priceId)
  {
   if(actionType==2){
	   deleteApprovedPriceList(pageno,suppId,0);
  	} 
 	else if(actionType==1){
 		editApprovedPriceList('e',suppId,priceId);
  	} 

  }
  
  function editApprovedPriceList(mode,suppId,priceId)
  {
  	document.getElementById('mode').value=mode;
  	document.getElementById('supplier_id').value=suppId;
  	document.getElementById('purchase_price_id').value=priceId;
  	document.getElementById('servlet_name').value="ApprovedPriceListServlet";
  	document.getElementById('callbackmethod_name').value="doNewApprovedPriceList";
  	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
  	document.forms[0].method="POST";	
  	document.forms[0].submit();	
  }
  function deleteApprovedPriceList(pageno,priceid,page)
  {

  	if(priceid<=0){
  		document.getElementById('warning-msg').innerHTML="Please save the transaction";
  		showWarningMsg('warning-msg');
  		return false;
  	} 	
  	else{

  		bootbox.confirm("Are you sure you want to delete?", function(result) {
  			if(result){
  				document.getElementById('servlet_name').value="ApprovedPriceListServlet";
  				document.getElementById('callbackmethod_name').value="doDeleteApprovedPriceList";
  				document.getElementById('purchase_price_id').value=priceid;
  				document.forms[0].action=contextpath+"/RequestHandlerServlet?page="+page;	
  				document.forms[0].method="POST";	
  				document.forms[0].submit();	
  			}
  		});
  	}

  }
	
  function searchApprovedPriceList()
  {
	document.getElementById('request_type').value="Search";
  	document.getElementById('servlet_name').value="ApprovedPriceListServlet";
  	document.getElementById('callbackmethod_name').value="doDisplayApprovedPriceList";
  	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
  	document.forms[0].method="POST";	
  	document.forms[0].submit();
  			
  }	
  
  function clearApprovedPriceList()
  {
  	setVal('Supplier_name','');
  	setVal('supplier_id','');
  	setVal('material','');
  	setVal('material_id','');
  }	
  function dodeleteBulkApprovedPriceList()
  {
	  var cnt = jQuery("input[name='SupplierApl_Id']:checked").length;
	  if (cnt < 1) 
	     {
			document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
			showWarningMsg('warning-msg');
	     } 	
		else{
	  
	  bootbox.confirm("Are you sure you want to delete?", function(result) {
		  if(result){
			  document.getElementById('servlet_name').value="ApprovedPriceListServlet";
			  document.getElementById('callbackmethod_name').value="doDeleteBulkApprovedPriceList";
			  document.forms[0].action=contextpath+"/RequestHandlerServlet";	
			  document.forms[0].method="POST";	
			  document.forms[0].submit();
		  }
	  });
  }  
  } 
  function changeApprovedPriceListPageNO(pageNo)
  {
  	document.getElementById('pageno').value=pageNo;
  	document.getElementById('servlet_name').value="ApprovedPriceListServlet";
  	document.getElementById('callbackmethod_name').value="doDisplayApprovedPriceList";
  	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
  	document.forms[0].method="POST";	
  	document.forms[0].submit();	
  }
  
  function searchApprovedPriceList()
  {
  	document.getElementById('request_type').value="Search";
  	document.getElementById('servlet_name').value="ApprovedPriceListServlet";
  	document.getElementById('callbackmethod_name').value="doDisplayApprovedPriceList";
  	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
  	document.forms[0].method="POST";	
  	document.forms[0].submit();	
  }
  
  
  
  function deleteBulkOperation(pageNo,active_mode)  
  {
  	var cnt = jQuery("input[name='bulkoperatonId']:checked").length;
  	if (cnt < 1) 
  	{
  		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
  		showWarningMsg('warning-msg');
  		return false;
  	} 	
  	else{
  		
  		bootbox.confirm(localizedStrings.SURE_TO_DELETE_OPERATION, function(result) {
  			if(result){		
  	document.getElementById('pageno').value=pageNo;
  	document.getElementById('active_mode').value=active_mode;
  	document.getElementById('servlet_name').value="OperationOrStageServlet";
  	document.getElementById('callbackmethod_name').value="doDeleteBulkOperation";
  	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
  	document.forms[0].method="POST";	
  	document.forms[0].submit();
  			}
  		});
  	}
  }


  function SizeRangeActionList(actionType,coId,pageno)
	{
		if(actionType== 1){

			deleteColorMaster(coId,pageno);
		}
		else if(actionType==2){

			editColorMaster('e',coId);
		}
		function deleteSizeRangeMaster(coId,pageno){	

			bootbox.confirm(localizedStrings.SURE_TO_DELETE_GROUP, function(result) {
				if(result){
					document.getElementById('pageno').value=pageno;
					document.getElementById('color_id').value=coId;
					document.getElementById('servlet_name').value="SizeRangeServlet";
					document.getElementById('callbackmethod_name').value="doDeleteSizeRangeMaster";	
					document.forms[0].action=contextpath+"/RequestHandlerServlet";
					document.forms[0].method="POST";
					document.forms[0].submit();
				}
			});
		}
	}

  
  function displayColorActionList(actionType,pageno,col)
  {
  	 if(actionType== 1){ deleteColorMaster(col,pageno);}
  	 
  	 else if(actionType==2){editColorMaster('e',col);}
  	
  }
  
  function addAnotherLineForSizeRange(){

	  var sll=document.getElementById('row_count').value;
	 
	 
	  rowCnt=$('#row_count').val();
		
		var rowIds=$('#row_ids').val();

	 	  if(rowIds!='' && rowIds.length>0){
	 	   rowIds= rowIds.substring(0, rowIds.length - 1);
	 	  }
	 	  
	var arRowIds=rowIds.split(',');

	var lb=0;
	var lbb=0;
	var diff=1;
	  	var val=1;
	if(arRowIds.length>1){
	 
	lb=arRowIds[arRowIds.length-1];
	 
	lbb=arRowIds[arRowIds.length-2];
	var v1=document.getElementById('si_no_'+lb).value;
	var v2=document.getElementById('si_no_'+lbb).value;
	diff=Number(v1)-Number(v2);
	val=Number(v1)+diff;
	}
	else if(arRowIds.length==1){
	 	lb=arRowIds[arRowIds.length-1];
	var v3=document.getElementById('si_no_'+lb).value;
	val=Number(v3)+1;
	}

		
		$('#size_range_group_row_out .size_range_row:last').after('<div class="row row-no-margin linerow size_range_row" id="size_range_row_'+rowCnt+'">'+
				'<div class="col-sm-3" >'+
				'<div class="form-group">'+
				'<input class="form-control" id="si_no_'+rowCnt+'" name="si_no_'+rowCnt+'"  size="30" type="text" placeholder="Enter SI No" value="'+Math.abs(val)+'" />'+
				'</div>'+
				'</div>'+
				'<div class="col-sm-4" >'+
				'<div class="form-group">'+
				'<div class="required-field-block">'+
				'<input class="form-control" id="size_name_'+rowCnt+'"'+
					'name="size_name_'+rowCnt+'"'+
					'size="30" type="text" placeholder="Enter Size" />'+
				'<input type="hidden" id="size_id_'+rowCnt+'" name="size_id_'+rowCnt+'" size="30" />'+
				'<div class="required-icon">'+
				'<div class="text">*</div>'+
				'</div>'+
				'</div>'+
				'</div>'+
				'</div>'+
			'<div class="col-sm-3" >'+
		  		'<div class="form-group">'+
		      		          '<select name="is_active_det_'+rowCnt+'" id="is_active_det_'+rowCnt+'" class="form-control" >'+
					          '	<option value="1">Yes</option>'+
								'	<option value="2">No</option>'+
								'</select>'+  
				          	  '</div>'+
				'</div>'+ 		
				'<div class="col-sm-2 linerow-icon" >'+
				'<a href="javascript:addAnotherLineForSizeRange()" data-toggle="tooltip" title="Add" ><span class="glyphicon glyphicon-plus text-primary "></span></a>'+
				'<a href="javascript:deleteSizeRow('+rowCnt+')" data-toggle="tooltip" title="Delete"><span class="glyphicon glyphicon-trash icon-delete"></span></a>'+
				'</div>'+
		'</div>');

		
		!function ($) {
			 
			$(function(){
				
				$('#size_name_'+rowCnt).listSizeRange({
				  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name=doGetSize&create_new=1",
				  nameField :'#size_name_'+rowCnt,
				  idField:'#size_id_'+rowCnt
			  });
					
			 });
	 }(window.jQuery);	
		
		
	 $('#row_ids').val($('#row_ids').val()+rowCnt+",");	
		rowCnt=parseInt(rowCnt)+1;

		$('#row_count').val(rowCnt);


		// }

	}

	function deleteSizeRow(rowNo){
		jQuery('#size_range_row_'+rowNo).remove();
		
		  var rowIds=$('#row_ids').val();
		  $('#row_ids').val(rowIds.replace(rowNo+",",""));
	}

	
	function saveSizeRange(mode,saveType)
	{
		
		if(validateSizeRange()){
			
			document.getElementById('mode').value=mode;
			document.getElementById('save_type').value=saveType;
			document.getElementById('request_type').value="Normal";
			document.getElementById('servlet_name').value="SizeRangeServlet";
			document.getElementById('callbackmethod_name').value="doSaveSizeRange";
			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
			document.forms[0].method="POST";
			document.getElementById('validation_btn').disabled=false;
			document.getElementById('validation_btn').click();
		}

	}
	
	function validateSizeRange(){
		
		  var rowIds=document.getElementById('row_ids').value;
		  var sizeM='';
		  var siNo='';
		 var ids = rowIds.substring(0, rowIds.length - 1);
		 var lastId= ids.substr(ids.length -1);
		 var siz="";
		 var size1="";
		   for (var i = 0; i <= lastId; i++) {
			   sizeM= document.getElementById('size_id_'+i).value;
			// alert("size Value==="+sizeM);
			   if(sizeM>0){
					  siz =siz+sizeM+",";
					// alert("BEFORE Dupli==="+siz);
					  size1='';
					  size1=size1+removeDuplicate(siz);
					  var sizeN = siz.length;
				//  alert("BEFORE Size Length=="+sizeN);
					//  alert("AFTER Dupli==="+size1);
					  var sizeN1 = size1.length;
					//  alert("AFTER Size1 Length=="+sizeN1);
					  
					}
			   }
		   if(sizeN==sizeN1)
			  {
				  return true; 
			  }else{
				  document.getElementById('req_err_msg').innerHTML = "Size should be different";
				  sizeN.focus();
					return false;
				  
			  }
	}
		
	function removeDuplicate(siz){
		arr =  $.unique(siz.split(','));
		siz= arr.join(',');
		return siz; 
	}
	

	function SizeRangeActionList(actionType,sizeRangeId,pageno)
	{

		if(actionType== 1){

			deleteSizeMaster(sizeRangeId,pageno);
		}
		else if(actionType==2){

			editSizeMaster('e',sizeRangeId);
		}
	}

	function deleteSizeMaster(sizeRangeId,pageno){	

		bootbox.confirm(localizedStrings.SURE_TO_DELETE_SIZE_RANGE, function(result) {
			if(result){
				document.getElementById('pageno').value=pageno;
				document.getElementById('size_range_id').value=sizeRangeId;
				document.getElementById('servlet_name').value="SizeRangeServlet";
				document.getElementById('callbackmethod_name').value="doDeleteSizeMaster";	
				document.forms[0].action=contextpath+"/RequestHandlerServlet";
				document.forms[0].method="POST";
				document.forms[0].submit();
			}
		});
	}

	function editSizeMaster(editmode,sizeRangeId){

				document.getElementById('mode').value=editmode;
				document.getElementById('size_range_id').value=sizeRangeId;
				document.getElementById('servlet_name').value="SizeRangeServlet";
				document.getElementById('callbackmethod_name').value="doNewSizeRange";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
	function searchSizeRange()
	{
		document.getElementById('request_type').value="Search";
		document.getElementById('servlet_name').value="SizeRangeServlet";
		document.getElementById('callbackmethod_name').value="doDisplaySizeRange";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}


	function clearSizeRange()
	{
		document.getElementById('size_range').value="";
		document.getElementById('size_range_id').value="";
		document.getElementById('applicable_to').value="";
	}

	function deleteBulkSizeRange(pageno)
	{
		
		 var cnt = jQuery("input[name='size_range_Id']:checked").length;
	     if (cnt < 1) 
	     {
			document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
			showWarningMsg('warning-msg');
			return false;
	     } 	
		else{

			bootbox.confirm("Are you sure you want to delete?", function(result) {
				if(result){
					document.getElementById('servlet_name').value="SizeRangeServlet";
					document.getElementById('callbackmethod_name').value="doDeleteBulkSizeRange";
					document.forms[0].action=contextpath+"/RequestHandlerServlet";	
					document.forms[0].method="POST";	
					document.forms[0].submit();
				}
			});
		}

	}

	function activeBulkSizeRange(pageNo,active_mode)  
	{
		
		var cnt = jQuery("input[name='size_range_Id']:checked").length;
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
					document.getElementById('servlet_name').value="SizeRangeServlet";
					document.getElementById('callbackmethod_name').value="doBulkActionActiveSizeRange";
					document.forms[0].action=contextpath+"/RequestHandlerServlet";	
					document.forms[0].method="POST";	
					document.forms[0].submit();	
				} 
			});
		}

	}
	function changeSizeRangePageNumber(pageNo)
	{
		
//		 document.getElementById('request_type').value="Search";
		document.getElementById('pageno').value=pageNo;
		document.getElementById('servlet_name').value="SizeRangeServlet";
		document.getElementById('callbackmethod_name').value="doDisplaySizeRange";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}
	/*----------SEASON-----------*/
	function addSeason(mode)
	{
		document.getElementById('request_type').value="Normal";
		document.getElementById('mode').value=mode; 
		document.getElementById('servlet_name').value="SeasonServlet";
		document.getElementById('callbackmethod_name').value="doNewSeason";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}

	function saveSeasonConfiguration(mode,saveType)
	{
		var sdate = document.getElementById('from_date').value;
		var edate = document.getElementById('to_date').value;	
		
		if(!validateStartAndEndDates(sdate,edate)){
			document.getElementById('warning-msg').innerHTML = "To date must be greater than From date!";
			showWarningMsg('warning-msg');
			return false;
		}
		else {
		document.getElementById('mode').value=mode;
		document.getElementById('save_type').value=saveType;
		document.getElementById('servlet_name').value="SeasonServlet";
		document.getElementById('callbackmethod_name').value="doSaveSeason";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
//		document.forms[0].submit();	
		document.getElementById('validation_btn').click();
		}
		}

	function SeasonActionList(actionType,seId,pageno)
	{
		if(actionType== 1){

			deleteSeasonMaster(seId,pageno);
		}
		else if(actionType==2){

			editSeasonMaster('e',seId);
		}
		/*else
			{
			DeActiveColor(coId,pageno)
			}*/

	}

	function deleteSeasonMaster(seId)
	{
		bootbox.confirm("Are you sure you want to delete?", function(result) {
			if(result){
		document.getElementById('season_id').value=seId;
		document.getElementById('servlet_name').value="SeasonServlet";
		document.getElementById('callbackmethod_name').value="doDeleteSeasonMaster";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();
			}
		});
	}
	function editSeasonMaster(mode,seId){	
			
		document.getElementById('season_id').value=seId;
		document.getElementById('mode').value=mode;
		document.getElementById('servlet_name').value="SeasonServlet";
		document.getElementById('callbackmethod_name').value="doEditSeason";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
	function activeBulkSeason(pageNo,active_mode)  
	{
			var cnt = jQuery("input[name='bulkSeasonId']:checked").length;
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
						document.getElementById('servlet_name').value="SeasonServlet";
						document.getElementById('callbackmethod_name').value="doBulkActionSeason";
						document.forms[0].action=contextpath+"/RequestHandlerServlet";	
						document.forms[0].method="POST";	
						document.forms[0].submit();	
					} 
				});
			}
	}
	function deleteBulkSeasonRange(pageno)
	{
		
		 var cnt = jQuery("input[name='bulkSeasonId']:checked").length;
	     if (cnt < 1) 
	     {
			document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
			showWarningMsg('warning-msg');
			return false;
	     } 	
		else{

			bootbox.confirm("Are you sure you want to delete?", function(result) {
				if(result){
					document.getElementById('servlet_name').value="SeasonServlet";
					document.getElementById('callbackmethod_name').value="doDeleteBulkSeason";
					document.forms[0].action=contextpath+"/RequestHandlerServlet";	
					document.forms[0].method="POST";	
					document.forms[0].submit();
				}
			});
		}

	}
	function searchSeason()
	{
		document.getElementById('request_type').value="Search";
		document.getElementById('servlet_name').value="SeasonServlet";
		document.getElementById('callbackmethod_name').value="doDisplaySeason";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}
	function clearSeasonSearch()
	{
		document.getElementById('season_name').value="";
		document.getElementById('from_date').value="";
		document.getElementById('to_date').value="";
	}
	function changeSeasonPageNumber(pageNo)
	{
		document.getElementById('request_type').value="Search";
		document.getElementById('pageno').value=pageNo;
		document.getElementById('servlet_name').value="SeasonServlet";
		document.getElementById('callbackmethod_name').value="doDisplaySeason";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}
	/*----------currency---------*/
	function addCurrency(mode)
	{
		document.getElementById('request_type').value="Normal";
		document.getElementById('mode').value=mode; 
		document.getElementById('servlet_name').value="CurrencyServlet";
		document.getElementById('callbackmethod_name').value="doNewCurrency";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}

	function saveCurrencyConfiguration(mode,saveType)
	{
		
		document.getElementById('mode').value=mode;
		
		document.getElementById('save_type').value=saveType;
		document.getElementById('servlet_name').value="CurrencyServlet";
		document.getElementById('callbackmethod_name').value="doSaveCurrency";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
//		document.forms[0].submit();	
		document.getElementById('validation_btn').click();
	}



	function CurrencyActionList(actionType,cuId,pageno)
	{
		if(actionType== 1){
			
			deleteCurrencyMaster(cuId,pageno);
		}
		else if(actionType==2){
			
			editCurrencyMaster('e',cuId);
		}
		
	}




	function editCurrencyMaster(editmode,cuId){

		document.getElementById('mode').value=editmode;
		document.getElementById('currency_id').value=cuId;
		document.getElementById('servlet_name').value="CurrencyServlet";
		document.getElementById('callbackmethod_name').value="doEditCurrencyMaster";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();
	}



	function deleteCurrencyMaster(cuId,pageno){	
		
		bootbox.confirm(localizedStrings.SURE_TO_DELETE_GROUP, function(result) {
			if(result){
			document.getElementById('pageno').value=pageno;
			document.getElementById('currency_id').value=cuId;
			document.getElementById('servlet_name').value="CurrencyServlet";
			document.getElementById('callbackmethod_name').value="doDeleteCurrencyMaster";	
			document.forms[0].action=contextpath+"/RequestHandlerServlet";
			document.forms[0].method="POST";
			document.forms[0].submit();
		}
		});
	}



	function searchCurrecnyMaster()
	{
		document.getElementById('request_type').value="Search";
		document.getElementById('servlet_name').value="CurrencyServlet";
		document.getElementById('callbackmethod_name').value="doDisplayCurrency";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}




	function clearCurrencySearch()
	{
		document.getElementById('currency_name').value="";
		document.getElementById('coin_name').value="";
		}




	function changeCurrencyPageNumber(pageNo)
	{
		document.getElementById('request_type').value="Search";
		document.getElementById('pageno').value=pageNo;
		document.getElementById('servlet_name').value="CurrencyServlet";
		document.getElementById('callbackmethod_name').value="doDisplayCurrency";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}





	function activeBulkCurrency(pageNo,active_mode)  
	{
		var cnt = jQuery("input[name='bulkcurrencyId']:checked").length;
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
		document.getElementById('servlet_name').value="CurrencyServlet";
		document.getElementById('callbackmethod_name').value="doBulkActionCurrency";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
			} 
		});
		}
		}

	function deleteBulkCurrency(pageno)
	{
		
		 var cnt = jQuery("input[name='bulkcurrencyid']:checked").length;
	     if (cnt < 1) 
	     {
			document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
			showWarningMsg('warning-msg');
			return false;
	     } 	
		else{

			bootbox.confirm(localizedStrings.SURE_TO_DELETE_BULKACTION, function(result) {
				if(result){
					document.getElementById('servlet_name').value="CurrencyServlet";
					document.getElementById('callbackmethod_name').value="doDeleteBulkCurrency";
					document.forms[0].action=contextpath+"/RequestHandlerServlet";	
					document.forms[0].method="POST";	
					document.forms[0].submit();
				}
			});
		}

	}
// Add Size Mapping
function addSizeMapping(mode)
	{
		document.getElementById('request_type').value="Normal";
		document.getElementById('mode').value=mode; 
		document.getElementById('servlet_name').value="SizeMappingServlet";
		document.getElementById('callbackmethod_name').value="doNewSizeMapping";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";	
		document.forms[0].submit();	
	}
	
function getSizeMappingDetailsGrid(sizesched_id){
	var sizeRrangeId=jQuery('#size_range_id').val();
	var sizemode=jQuery('#mode').val();
//	var purchaseprice_id=jQuery('#purchase_price_id').val();

 var url =contextpath+"/RequestHandlerServlet";
 var request =jQuery.ajax({
	url: url,
	type: "POST",
	data: {
		servlet_name : 'SizeMappingServlet', callbackmethod_name : 'doGetSizeGrid',
		size_Rrange_Id:sizeRrangeId,mode:sizemode,size_sched_id:sizesched_id
		},
	dataType: "xml",
	/*error: function(jqXHR, textStatus, errorThrown) {
		alert( "Request failed: " + jqXHR);
	},
	success: function(html) {
		jQuery('#size_grid').html(html);
		 	
	}*/
});
	request.done(function(xml) {
		jQuery(xml).find('size_map').each(function(){
			
	    	 data=jQuery(this).find('data').text();
	    	 jQuery('#validate_sizemapping #size_grid').html(data);    	
	    	 
	    		    	 
	    	 jQuery(xml).find('auto_complete_field').each(function(){
	    		 id=jQuery(this).find('id').text();
	    		 name=jQuery(this).find('name').text();
	    		 dependId=jQuery(this).find('dependId').text();
	    		 
	    		 
	    		 method=jQuery(this).find('method').text();
//	    		 jQuery('#input_id').val(jQuery('#input_id').val()+id+",");
	    		 
			    	 !function ($) {
			 			$(function(){
			 				  $('#validate_sizemapping #'+name).listSizeRangeSize({
			 					  url:contextpath + "/RequestHandlerServlet?servlet_name=AutoCompleteServlet&callbackmethod_name="+method+"&create_new=1",
			 					  nameField :'#validate_sizemapping #'+name,
			 					  idField:'#validate_sizemapping #'+id,
			 					  dependIdField:'#validate_sizemapping #'+dependId
			 					 // formId:'#validate-form',
			 					 // validateId:'customer'
			 				  });
			 			 });
			 		}(window.jQuery);
	 		
	    		});
	    	
		});
	});
	
	var detailId=document.getElementById('size_sched').value;
	 detailId=detailId+sizesched_id+',';
	 setVal('size_sched',detailId);
}

function  saveSizeMapping(mode,savetype)
{
	  		
	  		document.getElementById('request_type').value="Normal";
	  		document.getElementById('mode').value=mode; 
	  		document.getElementById('save_type').value=savetype; 
	    	document.getElementById('servlet_name').value="SizeMappingServlet";
	    	document.getElementById('callbackmethod_name').value="doSaveSizeMapping";
	    	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	    	document.getElementById('pageno').value="1";
	    	document.forms[0].method="POST";
	    	document.getElementById('validation_btn').click();
}
function displaySizeMappingActionList(actionType,pageno,sizeSchedId,sizeSchedDetId)
{
 if(actionType==2){
	   deleteSizeMapping(pageno,sizeSchedId,0);
	} 
	else if(actionType==1){
//		editApprovedPriceList('e',suppId,priceId);
		editSizeMapping('e',sizeSchedId,sizeSchedDetId);
	} 

}
function editSizeMapping(mode,sizeSchedId,sizeSchedDetId)
{
	document.getElementById('mode').value=mode;
	document.getElementById('size_sched_id').value=sizeSchedId;
	document.getElementById('size_sched_Detid').value=sizeSchedDetId;
	document.getElementById('servlet_name').value="SizeMappingServlet";
	document.getElementById('callbackmethod_name').value="doEditSizeMapping";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function deleteSizeMapping(pageno,sizeSchedId,page)
{

	if(sizeSchedId<=0){
		document.getElementById('warning-msg').innerHTML="Please save the transaction";
		showWarningMsg('warning-msg');
		return false;
	} 	
	else{

		bootbox.confirm("Are you sure you want to delete?", function(result) {
			if(result){
				document.getElementById('servlet_name').value="SizeMappingServlet";
				document.getElementById('callbackmethod_name').value="doDeleteSizeMapping";
				document.getElementById('size_sched_id').value=sizeSchedId;
				document.forms[0].action=contextpath+"/RequestHandlerServlet?page="+page;	
				document.forms[0].method="POST";	
				document.forms[0].submit();	
			}
		});
	}

}

function ActionSizeMappingDetailList(actionType,sizeSchedId,sizeSchedDetId,pageno)
{
 if(actionType==1){
	   deleteSizeMappingDetail(sizeSchedId,sizeSchedDetId);
	} 
	else if(actionType==2){
//		editApprovedPriceList('e',suppId,priceId);
		editSizeMappingDetail('e',sizeSchedId,sizeSchedDetId);
	} 
}

function editSizeMappingDetail(mode,sizeSchedId,sizeSchedDetId)
{
	document.getElementById('mode').value=mode;
	document.getElementById('size_sched_id').value=sizeSchedId;
	document.getElementById('size_sched_Detid').value=sizeSchedDetId;
	document.getElementById('servlet_name').value="SizeMappingServlet";
	document.getElementById('callbackmethod_name').value="doEditSizeMappingDetails";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function deleteSizeMappingDetail(sizeSchedId,sizeSchedDetId)
{
	  bootbox.confirm("Are you sure you want to delete?", function(result) {
		  if(result){
			  document.getElementById('servlet_name').value="SizeMappingServlet";
			  document.getElementById('callbackmethod_name').value="doDeleteSizeMappingDetail";
			  document.getElementById('size_sched_id').value=sizeSchedId;
			  document.getElementById('size_sched_Detid').value=sizeSchedDetId;
			  document.forms[0].action=contextpath+"/RequestHandlerServlet";	
			  document.forms[0].method="POST";	
			  document.forms[0].submit();
		  }
	  });
}
function changeSizeMappingPageNumber(pageNo)
{
	document.getElementById('pageno').value=pageNo;
	document.getElementById('servlet_name').value="SizeMappingServlet";
	document.getElementById('callbackmethod_name').value="doDisplaySizeMapping";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function searchSizemapping()
{
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="SizeMappingServlet";
	document.getElementById('callbackmethod_name').value="doDisplaySizeMapping";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function clearSizemapping()
{
	setVal('schedule','');
	setVal('size_range','');
	setVal('size_range_id','');
	
}

//VARIANT_MASTER
function addVariant(mode)
{
	
	document.getElementById('request_type').value="Normal";
	document.getElementById('mode').value=mode; 
	document.getElementById('servlet_name').value="VariantServlet";
	document.getElementById('callbackmethod_name').value="doNewVariant";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function saveVariantConfiguration(mode,saveType)
{

	document.getElementById('mode').value=mode;
	
	document.getElementById('save_type').value=saveType;
	document.getElementById('servlet_name').value="VariantServlet";
	document.getElementById('callbackmethod_name').value="doSaveVariant";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
//	document.forms[0].submit();	
	document.getElementById('validation_btn').click();
}
function displayVariantActionList(actionType,pageno,vaId)
{

	if(actionType== 1){
		deleteVariantMaster(vaId,pageno);
	}
	else if(actionType==2){
		editVariantMaster('e',vaId);
	}
	/*else
		{
		DeActiveColor(coId,pageno)
		}*/
	
}
function editVariantMaster(editmode,vaId){
	document.getElementById('mode').value=editmode;
	document.getElementById('variant_id').value=vaId;
	document.getElementById('servlet_name').value="VariantServlet";
	document.getElementById('callbackmethod_name').value="doEditVariant";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();

}
function deleteVariantMaster(vaId,pageno){	

	bootbox.confirm(localizedStrings.SURE_TO_DELETE_GROUP, function(result) {
		
		if(result){
			document.getElementById('pageno').value=pageno;
		document.getElementById('variant_id').value=vaId;
		document.getElementById('servlet_name').value="VariantServlet";
		document.getElementById('callbackmethod_name').value="doDeleteVariantMaster";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
	});
}
function searchVariantMaster()
{
	document.getElementById('request_type').value="Search";
	document.getElementById('servlet_name').value="VariantServlet";
	document.getElementById('callbackmethod_name').value="doDisplayVariant";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function clearVariantSearch()
{
	document.getElementById('variant_code').value="";
	document.getElementById('variant_id').value="";
	document.getElementById('variant_name').value="";
}
function changeVariantPageNumber(pageNo)
{
	document.getElementById('request_type').value="Search";
	document.getElementById('pageno').value=pageNo;
	document.getElementById('servlet_name').value="VariantServlet";
	document.getElementById('callbackmethod_name').value="doDisplayVariant";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function activeBulkVariant(pageNo,active_mode)  
{
	var cnt = jQuery("input[name='bulkvariantId']:checked").length;
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
	document.getElementById('servlet_name').value="VariantServlet";
	document.getElementById('callbackmethod_name').value="doBulkActionVariant";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
		} 
	});
	}

}

function getItemGroupData(matId,detId){
	
	 var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'ApprovedPriceListServlet', callbackmethod_name : 'doGetItemMasterData',
			mat_id:matId
			},
		dataType: "xml",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + jqXHR);
		},
		success: function(xml) {
			
			jQuery(xml).find('item_data').each(function(){
				 
				var currency=jQuery(this).find('currency').text();
				var uom=jQuery(this).find('uom').text();
				var cost=jQuery(this).find('cost').text();
				
				if(detId==0){
					jQuery("#currency_name_0").val(currency);
					jQuery("#uom_0").val(uom);
					/*if(cost>0)
						{
						jQuery("#rate_0").val(cost);
						}
					else{
						jQuery("#rate_0").val('');
					}*/
					
					/*if(jQuery('#material_id_0').val().length!=0 && jQuery('#material_id_0').val()!=0 ){
						
						//$('#validate-form').bootstrapValidator('revalidateField', 'price_0');
						$('#validate-form').bootstrapValidator('revalidateField', 'sku_0');
						//$('#validate-form').bootstrapValidator('revalidateField', 'uom_id_0');
					}*/
				}
				else{
					jQuery("#currency_name_"+detId).val(currency);
					jQuery("#uom_S_"+detId).val(uom);
					
					/*if(cost>0)
					{
						jQuery("#rate_S_"+detId).val(cost);
					}
					else{
					jQuery("#rate_S_"+detId).val('');
					}*/
					
					/*if(jQuery('#material_id_S_'+detId).val().length!=0 && jQuery('#material_id_S_'+detId).val()!=0 ){
					//$('#validate-form').bootstrapValidator('revalidateField', 'price_fcy_S_'+detId);
					$('#validate-form').bootstrapValidator('revalidateField', 'sku_S_'+detId);
					//$('#validate-form').bootstrapValidator('revalidateField', 'uom_id_S_'+detId);
					}*/
				}
				
				
			});
			 	
		}
	});
	
}

//**************************TAX GROUP***********************************************

function addAnotherLineForTaxGroup(){
	  //if(validateCriteriaQuery(rowNo)){
	  
	  rowCnt=$('#row_count').val();
	  $('#row_ids').val($('#row_ids').val()+rowCnt+",");	
	

	  
	  $('#tax_group_row_out .tax_group_row:last').after('<div class="row row-no-margin linerow tax_group_row" id="tax_group_row_'+rowCnt+'">'+
		
		'<div class="col-sm-4" >'+
		'<div class="form-group">'+
    		'<div class="required-field-block">	 '+
			          '<select name="tax_id_'+rowCnt+'" id="tax_id_'+rowCnt+'" class="form-control" >'+
							'<option value="-1">&lt;--Select Tax--&gt;</option>'+
							 
						'</select>'+  
		             '<div class="required-icon">'+
						'<div class="text">*</div>'+
				     '</div>'+
			     '</div>'+
        '</div>'+
		'</div>'+
		
		'<div class="col-sm-6" >'+
			'<div class="form-group">'+
				'<input class="form-control" id="tax_percentage_'+rowCnt+'" name="tax_percentage_'+rowCnt+'"  size="30" type="text" placeholder="Enter Tax Percentage"/>'+
			'</div>'+
		'</div>'+
			  
		
//		'<div class="col-sm-3" >'+
//		'<div class="form-group">'+
//			'<input class="form-control" id="tax_slno_'+rowCnt+'" name="tax_slno_'+rowCnt+'"  size="30" type="text" placeholder="Enter Display Order"/>'+
//		'</div>'+
//		'</div>'+
		
		'<div class="col-sm-2 linerow-icon" >'+
		'<a href="javascript:addAnotherLineForTaxGroup()" data-toggle="tooltip" title="Add" ><span class="glyphicon glyphicon-plus text-primary "></span></a>'+
		'<a href="javascript:deleteRowForTaxGroup('+rowCnt+')" data-toggle="tooltip" title="Delete"><span class="glyphicon glyphicon-trash icon-delete"></span></a>'+
		'</div>'+
	'</div>');
	  
	    loadDataToTax(rowCnt);
	  
		rowCnt=parseInt(rowCnt)+1;
		
		$('#row_count').val(rowCnt);
		
		
	 // }

}


function loadDataToTax(rowNo){
		selectControl=document.getElementById('tax_id_'+rowNo);
		
		var url =contextpath+"/RequestHandlerServlet";
		
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {servlet_name : 'TaxGroupServlet' , callbackmethod_name : 'doGetTax'},
		dataType: "xml"
		});
		request.done(function(xml) {
			selectControl.length=1;
			jQuery(xml).find('tax').each(function(){
				
		    	 taxId=jQuery(this).find('taxid').text();
		    	 taxName=jQuery(this).find('taxname').text();
		    	 selectControl.options[selectControl.options.length]=new Option(taxName, taxId);
		    	 
		});
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});
		
	}


function deleteRowForTaxGroup(rowNo){
//	  $('table#tax_row_table #n_table_row'+rowNo).remove();
	
	jQuery('#tax_group_row_'+rowNo).remove();
	
	  var rowIds=$('#row_ids').val();
	  $('#row_ids').val(rowIds.replace(rowNo+",",""));
}

function changePageNumbersinTaxGroupMaster(pageno){	
	
	document.getElementById('servlet_name').value="TaxGroupServlet";
	document.getElementById('callbackmethod_name').value="doDisplayTaxGroup";
	document.getElementById('request_type').value="Search";
	document.getElementById('pageno').value=pageno;
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}
function displayTaxGroupActionList(actionType,taxGroupId,pageno)
{
	 if(actionType== 1){ 
		 deleteTaxGroup(taxGroupId);
		 }
	 else if(actionType==2){
		 editTaxGroup('e',taxGroupId);
		 }
	
}

function deleteTaxGroup(taxGroupId)
{
	bootbox.confirm("Are you sure you want to delete?", function(result) {
		if(result){
			document.getElementById('tax_group_id').value=taxGroupId;
			document.getElementById('servlet_name').value="TaxGroupServlet";
			document.getElementById('callbackmethod_name').value="doDeleteTaxGroup";
			document.forms[0].action=contextpath+"/RequestHandlerServlet";	
			document.forms[0].method="POST";	
			document.forms[0].submit();
		}
	});
}

function deleteTaxRow(Id,taxGroupId)
{
	document.getElementById('tax_group_id').value=taxGroupId;
	document.getElementById('tax_id').value=Id;
	document.getElementById('servlet_name').value="TaxGroupServlet";
	document.getElementById('callbackmethod_name').value="doDeleteTaxGroupRow";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}

function editTaxGroup(mode,taxGroupId){	
	
	if(taxGroupId<0)
		{
		document.getElementById('warning-msg').innerHTML="Please select Tax Group!";
		showWarningMsg('warning-msg');	
		}
	else{
		document.getElementById('tax_group_id').value=taxGroupId;
		document.getElementById('mode').value=mode;
		document.getElementById('servlet_name').value="TaxGroupServlet";
		document.getElementById('callbackmethod_name').value="doNewTaxGroup";	
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
	
	
}

function saveTaxGroup(mode,saveType)
{
	//alert(validateTaxGroup());
	if(validateTaxGroup()){
		//alert('save');
		document.getElementById('mode').value=mode;
		document.getElementById('save_type').value=saveType;
		document.getElementById('request_type').value="Normal";
		document.getElementById('servlet_name').value="TaxGroupServlet";
		document.getElementById('callbackmethod_name').value="doSaveTaxGroup";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";	
		document.forms[0].method="POST";
		document.getElementById('validation_btn').disabled=false;
		document.getElementById('validation_btn').click();
		/*document.forms[0].submit();*/
	}
	

}

function validateTaxGroup(){
	
	  var rowIds=document.getElementById('row_ids').value;
	  var taxId='';
	  var taxPer='';
	
	 var ids = rowIds.substring(0, rowIds.length - 1);
//	 alert("ID"+ids);
	 var lastId= ids.substr(ids.length - 1);
	//alert("LastId"+lastId);
/*	 if(document.getElementById('tax_group').value.trim().length==0)
		{
			document.getElementById('req_err_msg').innerHTML = "Please select tax group";
			document.getElementById('tax_group').focus();
			return false;
		}
	 */
	 var dup='';
	   for (var i = 0; i <= lastId; i++) {
		 //  alert('i-for--'+i);
		   taxId= document.getElementById('tax_id_'+i);
		   taxPer=document.getElementById('tax_percentage_'+i);
	 //  alert("TaxID Value"+taxId.value);
	//   alert("Tax ID Length"+taxPer.value.trim().length);
		   if(taxId.value>0){
			   dup=dup+taxId.value;
			   }
		 
		 // alert("DUP"+dup);
		   
		   var hasDuplicates = (/([1-9]).*?\1/).test(dup);     
		//  alert("repeating string "+hasDuplicates);
		   
		   if(taxId.value<0&&taxPer.value>0){
				document.getElementById('req_err_msg').innerHTML = "Please select tax";
				taxId.focus();
				return false;
			}
		   else if(taxId.value>0&& (taxPer.value.trim().length==0 || taxPer.value==0)){
					document.getElementById('req_err_msg').innerHTML = "Please select tax percentage";
					taxPer.focus();
					return false;
				}
		   else if(hasDuplicates){
				document.getElementById('req_err_msg').innerHTML = "Tax should be different";
				taxId.focus();
				return false;
			}
		}
	   
	   return true;
}
function clearTaxGroup(){
	
	setVal('tax_group','');
	setVal('tax','');
}

function searchTaxGroup()
{
	document.getElementById('request_type').value="Search";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="TaxGroupServlet";
	document.getElementById('callbackmethod_name').value="doDisplayTaxGroup";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
	
}

function saveLocationConfiguration(divName,type){
	document.getElementById('div_name').value=divName;
	document.getElementById('servlet_name').value="LocationConfigurationServlet";
	document.getElementById('callbackmethod_name').value="doSaveLocationConfiguration";
	document.getElementById('pageno').value="1";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?type="+type;	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}

function activeBulkCustomer(pageNo,active_mode)  
{
	var cnt = jQuery("input[name='customer_id']:checked").length;
	if (cnt < 1) 
  {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
		return false;
  } 	
	document.getElementById('pageno').value=pageNo;
	document.getElementById('active_mode').value=active_mode;
	document.getElementById('servlet_name').value="CustomerServlet";
	document.getElementById('callbackmethod_name').value="doBulkActiveCustomer";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function deleteBulkCustomer(pageno)
{
	 var cnt = jQuery("input[name='customer_id']:checked").length;
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
				document.getElementById('servlet_name').value="CustomerServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBulkCustomer";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}

}
function deleteBulTaxkGroup(pageno)
{
	 var cnt = jQuery("input[name='taxgroup_id']:checked").length;
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
				document.getElementById('servlet_name').value="TaxGroupServlet";
				document.getElementById('callbackmethod_name').value="doDeleteBulkTaxGroup";
				document.forms[0].action=contextpath+"/RequestHandlerServlet";	
				document.forms[0].method="POST";	
				document.forms[0].submit();
			}
		});
	}

}

function activeBulkTaxGroup(pageNo,active_mode)  
{
	var cnt = jQuery("input[name='taxgroup_id']:checked").length;
	if (cnt < 1) 
  {
		document.getElementById('warning-msg').innerHTML = "Please select Transaction!";
		showWarningMsg('warning-msg');
		return false;
  } 	
	document.getElementById('pageno').value=pageNo;
	document.getElementById('active_mode').value=active_mode;
	document.getElementById('servlet_name').value="TaxGroupServlet";
	document.getElementById('callbackmethod_name').value="doBulkActiveTaxgroup";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}