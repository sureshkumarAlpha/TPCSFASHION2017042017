toastr.options = {
		  "closeButton": true,
		  "debug": false,
		  "newestOnTop": false,
		  "progressBar": false,
		  "positionClass": "toast-top-center",
		  "preventDuplicates": false,
		  "onclick": null,
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "5000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasisaveDynamicFormFieldng": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
		} 


function createNewMaterial(){
	document.getElementById("add_new_mat").click();
	
	}

function createNewGroup(){
	 document.getElementById("add_new_group").click();
	
	}

function createNewUOM(){
	 document.getElementById("add_new_uom").click();
}

function createNewVariant(){
	document.getElementById("add_new_variant").click();
	
}

var i=0;
//function addAttachment(){
//	
//	  i=parseInt(i)+1;
//	  
//	  var table=document.getElementById('attachments');
//		var rowCount=table.rows.length;
//		var row=table.insertRow(rowCount);
//		
//		row.setAttribute("id","attach_row"+i);
//		row.setAttribute("class","attach-file-row");
//		
//		
//	    var cell1=row.insertCell(0);
//	    cell1.setAttribute("width","50px");
//	    
//	    
//	    //attachments.appendChild(document.createTextNode( '\u00A0' ));
//	    
////	    <a href="#" name="delete_${det.invDetId}" id="delete_${det.invDetId}" onclick="deleteSIDetail('${siheader.invId}','${det.invDetId}')"><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
//	  
//	  var button = document.createElement("a");
//	 
//	    //Assign different attributes to the element.
////	    button.setAttribute("type", "button");
//	  button.setAttribute("href", "javascript:cancelAttachment("+i+");");
//	    button.setAttribute("name", i);
//	    //button.setAttribute("value", "x");
//	    button.setAttribute("id", i);
//	    button.setAttribute("class","btn btn-default");
////	    button.setAttribute("class", "btn btn-danger destroy");
////	    button.setAttribute("onclick", "");
//	   
//	    
//	    var italic=document.createElement("span");
//	    italic.setAttribute("class", "glyphicon glyphicon-remove text-danger");
//	    button.appendChild(italic);
//	    
////	    var span=document.createElement("span");
////	    span.appendChild(document.createTextNode( ' Cancel' ));
////	    button.appendChild(span);
//	    //Append the element in page (in div).
//	    //attachments.appendChild(button);
//	    cell1.appendChild(button);
//		
//		
//		var cell2=row.insertCell(1);
//		cell2.setAttribute("width","500px");
//	  
//	    //Create an input type dynamically.
//	    var file = document.createElement("input");
//	 
//	    //Assign different attributes to the element.
//	    file.setAttribute("type", "file");
//	    file.setAttribute("name", i);
//	    file.setAttribute("id", "file"+i);
//	    file.setAttribute("multiple", "multiple");
//	    file.setAttribute("class", "form-control");
//	 
//	    //var attachments = document.getElementById("attachments");
//	 
//	    //Append the element in page (in div).
//	    //attachments.appendChild(file);
//	    cell2.appendChild(file);
//	    document.getElementById('file'+i).setAttribute("name",'file_'+1);
//	    
//	    if(navigator.appName == "Microsoft Internet Explorer"){
//	    	
//	    	document.getElementById(i).outerHTML=document.getElementById(i).outerHTML;
//	    }
//	    
//	    
//	    //-------------------------for customer factory radio buttons
//	    
//	   /* var cell3=row.insertCell(2);
//	    
//	    var fty = document.createElement("input");
//	    fty.setAttribute("type", "radio");
//	    fty.setAttribute("name", "rad"+i);
//	    fty.setAttribute("id", "fty_"+i);
//	    fty.setAttribute("onclick", "javascript:changeFileAttribute('fty_"+i+"')");
//	    cell3.appendChild(fty);
//	    //attachments.appendChild(fty);
//	    
//	    
//	    
//	    var fSpan=document.createElement('label'); 
//	    fSpan.innerHTML="Factory"; 
//	    fSpan.setAttribute("id", "sfty_"+i);
//	    fSpan.setAttribute("for", "fty_"+i);
//	    cell3.appendChild(fSpan);
//	    //attachments.appendChild(fSpan); 
//	    
//	    var cust = document.createElement("input");
//	    cust.setAttribute("type", "radio");
//	    cust.setAttribute("name", "rad"+i);
//	    cust.setAttribute("id", "cust_"+i);
//	    cust.setAttribute("onclick", "javascript:changeFileAttribute('cust_"+i+"')");
//	    cell3.appendChild(cust);
//	    //attachments.appendChild(cust);
//	    
//	    var cSpan=document.createElement('label'); 
//	    cSpan.innerHTML="Customer"; 
//	    cSpan.setAttribute("id", "scust_"+i);
//	    cSpan.setAttribute("for", "cust_"+i);
//	    cell3.appendChild(cSpan);
//	    //attachments.appendChild(cSpan);
//	    
//	    var all = document.createElement("input");
//	    all.setAttribute("type", "radio");
//	    all.setAttribute("name", "rad"+i);
//	    all.setAttribute("id", "all_"+i);
//	    all.setAttribute("checked", "checked");
//	    all.checked=true;
//	    all.setAttribute("onclick", "javascript:changeFileAttribute('all_"+i+"')");
//	    cell3.appendChild(all);
//	    //attachments.appendChild(all);
//	    
//	    var aSpan=document.createElement('label');
//	    aSpan.setAttribute("id", "sall_"+i);
//	    aSpan.innerHTML="All";
//	    aSpan.setAttribute("for", "all_"+i);
//	    cell3.appendChild(aSpan);*/
//	    //attachments.appendChild(aSpan);
//
//	
//	}
function addAttachment(){
	
	  i=parseInt(i)+1;
	  
	  var table=document.getElementById('attachments');
		var rowCount=table.rows.length;
		var row=table.insertRow(rowCount);
		
		row.setAttribute("id","attach_row"+i);
		row.setAttribute("class","attach-file-row");
		
		
	   
		
		
		var cell2=row.insertCell(0);
		cell2.setAttribute("width","500px");
	  
	    //Create an input type dynamically.
	    var file = document.createElement("input");
	 
	    //Assign different attributes to the element.
	    file.setAttribute("type", "file");
	    file.setAttribute("name", i);
	    file.setAttribute("id", "file"+i);
	    file.setAttribute("multiple", "multiple");
	    file.setAttribute("class", "form-control");
	 
	    //var attachments = document.getElementById("attachments");
	 
	    //Append the element in page (in div).
	    //attachments.appendChild(file);
	    cell2.appendChild(file);
	    
	    var cell1=row.insertCell(1);
	    cell1.setAttribute("width","100px");
	    
	    
	    //attachments.appendChild(document.createTextNode( '\u00A0' ));
	    
//	    <a href="#" name="delete_${det.invDetId}" id="delete_${det.invDetId}" onclick="deleteSIDetail('${siheader.invId}','${det.invDetId}')"><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
	  
		  button = document.createElement("a");
			 
		    //Assign different attributes to the element.
//		    button.setAttribute("type", "button");
		  	button.setAttribute("href", "javascript:addAttachment();");
		    button.setAttribute("name", "add_"+i);
		    //button.setAttribute("value", "x");
		    button.setAttribute("id", "add_"+i);
		    button.setAttribute("data-toggle", "tooltip");
		    button.setAttribute("title", "Add");
//		    button.setAttribute("class","btn btn-default");
//		    button.setAttribute("class", "btn btn-danger destroy");
//		    button.setAttribute("onclick", "");
		   
		    
		    var italic=document.createElement("span");
		    italic.setAttribute("class", "span-icon glyphicon glyphicon-plus text-primary");
		    button.appendChild(italic);
		    cell1.appendChild(button); 
	    
		    cell1.appendChild(document.createTextNode( '\u00A0' ));
		    
	  button = document.createElement("a");
	 
	    //Assign different attributes to the element.
//	    button.setAttribute("type", "button");
	  button.setAttribute("href", "javascript:cancelAttachment("+i+");");
	    button.setAttribute("name", "remove_"+i);
	    //button.setAttribute("value", "x");
	    button.setAttribute("id", "remove_"+i);
	    button.setAttribute("data-toggle", "tooltip");
	    button.setAttribute("title", "Remove");
//	    button.setAttribute("class","btn btn-default");
//	    button.setAttribute("class", "btn btn-danger destroy");
//	    button.setAttribute("onclick", "");
	   
	    
	    var italic=document.createElement("span");
	    italic.setAttribute("class", "span-icon glyphicon glyphicon-remove icon-delete");
	    button.appendChild(italic);
	    
//	    var span=document.createElement("span");
//	    span.appendChild(document.createTextNode( ' Cancel' ));
//	    button.appendChild(span);
	    //Append the element in page (in div).
	    //attachments.appendChild(button);
	    cell1.appendChild(button);
	    
	    
	    
	    
	    //attachments.appendChild(document.createTextNode( '\u00A0' ));
	    
//	    <a href="#" name="delete_${det.invDetId}" id="delete_${det.invDetId}" onclick="deleteSIDetail('${siheader.invId}','${det.invDetId}')"><span class="glyphicon glyphicon-trash text-danger grid-icon-alone"></span></a>
	  

//	    var span=document.createElement("span");
//	    span.appendChild(document.createTextNode( ' Cancel' ));
//	    button.appendChild(span);
	    //Append the element in page (in div).
	    //attachments.appendChild(button);
	    cell1.appendChild(button);
	    
	    document.getElementById('file'+i).setAttribute("name",'file_'+1);
	    
	    if(navigator.appName == "Microsoft Internet Explorer"){
	    	
	    	document.getElementById(i).outerHTML=document.getElementById(i).outerHTML;
	    }
	    
	    $(function(){
			$('[data-toggle="tooltip"]').tooltip();
		});
	    
	    //-------------------------for customer factory radio buttons
	    
	   /* var cell3=row.insertCell(2);
	    
	    var fty = document.createElement("input");
	    fty.setAttribute("type", "radio");
	    fty.setAttribute("name", "rad"+i);
	    fty.setAttribute("id", "fty_"+i);
	    fty.setAttribute("onclick", "javascript:changeFileAttribute('fty_"+i+"')");
	    cell3.appendChild(fty);
	    //attachments.appendChild(fty);
	    
	    
	    
	    var fSpan=document.createElement('label'); 
	    fSpan.innerHTML="Factory"; 
	    fSpan.setAttribute("id", "sfty_"+i);
	    fSpan.setAttribute("for", "fty_"+i);
	    cell3.appendChild(fSpan);
	    //attachments.appendChild(fSpan); 
	    
	    var cust = document.createElement("input");
	    cust.setAttribute("type", "radio");
	    cust.setAttribute("name", "rad"+i);
	    cust.setAttribute("id", "cust_"+i);
	    cust.setAttribute("onclick", "javascript:changeFileAttribute('cust_"+i+"')");
	    cell3.appendChild(cust);
	    //attachments.appendChild(cust);
	    
	    var cSpan=document.createElement('label'); 
	    cSpan.innerHTML="Customer"; 
	    cSpan.setAttribute("id", "scust_"+i);
	    cSpan.setAttribute("for", "cust_"+i);
	    cell3.appendChild(cSpan);
	    //attachments.appendChild(cSpan);
	    
	    var all = document.createElement("input");
	    all.setAttribute("type", "radio");
	    all.setAttribute("name", "rad"+i);
	    all.setAttribute("id", "all_"+i);
	    all.setAttribute("checked", "checked");
	    all.checked=true;
	    all.setAttribute("onclick", "javascript:changeFileAttribute('all_"+i+"')");
	    cell3.appendChild(all);
	    //attachments.appendChild(all);
	    
	    var aSpan=document.createElement('label');
	    aSpan.setAttribute("id", "sall_"+i);
	    aSpan.innerHTML="All";
	    aSpan.setAttribute("for", "all_"+i);
	    cell3.appendChild(aSpan);*/
	    //attachments.appendChild(aSpan);

	
	}
function cancelAttachment(id){
	  
	deleteTableRow("attach_row"+id);
}
function deleteTableRow(rowid){   
  var row = document.getElementById(rowid);
  row.parentNode.removeChild(row);
}


//Dynamic Order Field Configuration
function showDynamicFieldsConfig(){	
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="doConfigDynamicOrder";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}
function setVal(str,val){
	
	if(document.getElementById(str)!=null){
		document.getElementById(str).value=val;
		}
}
function organizeColumns(documentid){

	
	var url =contextpath+"/RequestHandlerServlet";
	
	var request = jQuery.ajax({
	url: url,
	type: "POST",
	data: {servlet_name : 'ColumnPreferenceServlet' , callbackmethod_name : 'doDisplayColumnPreferencePage',document_id:documentid},
	dataType: "html"
	});
	request.done(function(msg) {
	
		jQuery("#orgPopupForm").html( msg );
		
	});
	request.fail(function(jqXHR, textStatus) {
	alert( "Request failed: " + textStatus );
	
	});
	
	
}

function addToVisible(){
	
//	with(document.forms[0])
//	{
		var k=0;
		if(total_columns.options.length==0)
		{
			document.getElementById('error_message').innerHTML = localizedStrings.NO_COLUMNS;
			return false;
		}
		for (var i=0;i<total_columns.options.length;i++ )
		{	
			if(total_columns.options[i].selected)
			{
				k=k+1;
				visible_columns.options[visible_columns.options.length]=new Option(total_columns.options[i].text, total_columns.options[i].value);
				total_columns.options[i--]=null;
	
			}
		}
		if(k==0)
		{
			document.getElementById('error_message').innerHTML = localizedStrings.SLCT_COLUMN_CHANGE_ORDER;
			return false;
		}
//	}
	//return false;
}

function addToHidden(){
	
//	with (document.forms[0])
//	{
		var k=0;
		if(visible_columns.options.length==0)
		{
			document.getElementById('error_message').innerHTML = localizedStrings.NO_COLUMNS;
			return false;
		}
		for(var i=0;i<visible_columns.options.length;i++)
		{
			if(visible_columns.options[i].selected)
			{
				k=k+1;
				total_columns.options[total_columns.options.length]=new Option(visible_columns.options[i].text,visible_columns.options[i].value);
				visible_columns.options[i--]=null;
			}
		}	
		if(k==0)
		{
			document.getElementById('error_message').innerHTML = localizedStrings.SLCT_COLUMN_CHANGE_ORDER;
			return false;
		}
//	}
	
}

function moveToUp() {
	
	var column = document.getElementById("visible_columns"); 
	if(column.selectedIndex==-1){
		document.getElementById('error_message').innerHTML = localizedStrings.SLCT_COLUMN_CHANGE_ORDER;
		return false;
	}
	if (column.selectedIndex > 0) {       
		for (var count = 0; count < column.options.length; count++) {	 
			if (column.options[count].selected == true) {                          	   
				var tem = column.options[count].text;
				var val = column.options[count].value;
				column.options[count].text = column.options[count - 1].text;
				column.options[count].value = column.options[count-1].value; 
				column.options[count - 1].text = tem;
				column.options[count - 1].value = val;
				column.options[count].selected=false;
				column.options[count - 1].selected=true;      
			}
		}
	}
	else {
		return false;
	}

}

function moveToDown() {
	
	var column = document.getElementById("visible_columns");
	var total = 0;
	for(var iCount=0;iCount<column.options.length;iCount++){
		if (column.options[iCount].selected == true) {
			total=total+1;
		}
	}	
	if(column.selectedIndex==-1){
		document.getElementById('error_message').innerHTML = localizedStrings.SLCT_COLUMN_CHANGE_ORDER;
		return false;
	}
	for (var count = column.options.length-2; count >= 0; count--) {	
		if (column.options[count].selected == true) {
			if(column.selectedIndex==column.options.length-total){
				return false;
			}else{
				var tem = column.options[count+1].text;
				var val = column.options[count+1].value;
				column.options[count+1].text = column.options[count].text;
				column.options[count+1].value = column.options[count].value;
				column.options[count].text = tem;
				column.options[count].value =val;
				column.options[count].selected=false;
				column.options[count+1].selected=true;         
			}
		}
	}       

}

function disableDataType(compType){
	var compType = document.getElementById('componentType');	
	try{
		if(compType.value != 1){			
			document.getElementById('attributeDataType').selectedIndex = 0;
			document.getElementById('attributeDataType').disabled = true;		
		}else if(document.getElementById('fixed') != null && document.getElementById('fixed').value != "true"){			
			document.getElementById('attributeDataType').disabled = false;
		}else {
			document.getElementById('attributeDataType').selectedIndex = 0;
			document.getElementById('attributeDataType').disabled = true;
		}
		
	}catch(err){
		alert(err);
	}
}
function setTmpCalendardate(frmCalendarDate)
{

document.getElementById('tmp_calendar_date').value=frmCalendarDate;


}


function editDynamicFormField(fieldName){
	document.getElementById("rowSelected").value = fieldName;
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="editDynamicFormField";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}

function deleteDynamicFormField(fieldName){
	
	bootbox.confirm("This operation will drop the column and all the data related to the column will be lost. \n Are you sure you want to delete this Field?", function(result) {
		 if(result){
			  document.getElementById("rowSelected").value = fieldName;
				document.getElementById('servlet_name').value="DynamicOrderConfig";
				document.getElementById('callbackmethod_name').value="doDeleteDynamicFormField";	
				document.forms[0].action=contextpath+"/RequestHandlerServlet";
				document.forms[0].method="POST";
				document.forms[0].submit();	 
		 }
	});
}

function clearAllFields(){
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="clearAllFields";	
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}

function saveDynamicFormField(){
	/*var label = document.getElementById('attributeLabel').value.trim();
	var name = document.getElementById('attributeName').value.trim();
	var dbname = document.getElementById('attributeDBName').value.trim();
	
	if(label.length == 0){
		document.getElementById('messages').innerHTML= localizedStrings.VALID_LABEL;
		document.getElementById('attributeLabel').focus();
	}else if(name.length == 0){
		document.getElementById('messages').innerHTML= localizedStrings.VALID_ATTR_NAME;
		document.getElementById('attributeName').focus();
	}else if(dbname.length == 0){
		document.getElementById('messages').innerHTML= localizedStrings.VALID_ATTR_DB_NAME;
		document.getElementById('attributeDBName').focus();
	}
	else{*/
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="doSaveDynamicFormField";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	//document.forms[0].submit();	
	document.getElementById('validation_btn').click();
	//}
}

/*function saveFieldStructure(){	
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="doSaveFieldStructure";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}*/
function displayOrderTableData(tableId){	
	/*var dataSavedFlag = document.getElementById('dataSavedFlag').value;
	var r = true;
	if(dataSavedFlag != "true"){
		r = confirm("Data is Not saved, sure you want to continue? ");
	}
	if(r == true){*/
	document.getElementById('tableId').value = tableId;	
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="displayOrderTableData";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";	
	document.forms[0].submit();
	//}
	
}
function openDefaultValues(){
	
	
	var fieldId = document.getElementById('fieldId').value;
	var fieldName = document.getElementById('attributeDBName').value;
	var compType = document.getElementById("componentType").value;	
	var length = document.getElementById("attributeLength").value;	
	if(fieldName.trim() == ""){
		alert('Enter the Field Name');
	}else if(length.trim() == ""){
		alert('Enter the Length');
	}else if(lengthValidate(length)) {	
	var strFeatures="toolbar=no,status=no,menubar=no,left=300,top=150,resizable=no,height=400,width=600";
	window.open(contextpath+'/jsp/dynamic/DynamicDefaultValue.jsp?fieldId='+fieldId+'&fieldName='+fieldName+'&compType='+compType+'&length='+length,'DefaultValuesEdit',strFeatures).focus();
	}
}
function lengthValidate(length){	
	if(parseInt(length) > 100){
		alert('Field length must be less than 100');
		return false;
	}
	return true;
}
function saveDefaultValues(selectedIndex){	
	try{
	document.getElementById('selectedIndex').value = selectedIndex;
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="saveDefaultValues";	
	document.forms["defaultValueForm"].method="POST";
	document.forms["defaultValueForm"].submit();
	}catch(err){
		alert(err);
	}
}
function editDefaultValue(selectedIndex){
	document.getElementById('selectedIndex').value = selectedIndex;
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="editDefaultValue";	
	document.forms["defaultValueForm"].method="POST";
	document.forms["defaultValueForm"].submit();
}

function deleteDefaultValue(selectedIndex){
	document.getElementById('selectedIndex').value = selectedIndex;
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="deleteDefaultValue";	
	document.forms["defaultValueForm"].method="POST";
	document.forms["defaultValueForm"].submit();
}
//Screen Configuration

function showDynamicFieldsConfigForScreens(){
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="doConfigDynamicScreens";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}

function showConfigScreensForId(screenId){
	/*if(screenId!=-1){
		var dataSavedFlag = document.getElementById('dataSavedFlag').value;
		var r = true;
		if(dataSavedFlag != "true"){
			r = confirm("Data is Not saved, sure you want to continue? ");
		}
		if(r == true){*/
		document.getElementById('screenId').value = screenId;
		document.getElementById('servlet_name').value="DynamicOrderConfig";
		document.getElementById('callbackmethod_name').value="doConfigDynamicScreens";
		document.forms[0].action=contextpath+"/RequestHandlerServlet";
		document.forms[0].method="POST";
		document.forms[0].submit();
	/*	}
	}*/
}
function doRefreshFieldsListOnScreen(){		
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="doRefreshFieldsListOnScreen";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}

function backToScreen()
{
	document.getElementById('servlet_name').value=document.getElementById('invoke_servlet').value;
	document.getElementById('callbackmethod_name').value=document.getElementById('invoke_method').value;	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	

}

function openFormJavaScript(){
	var fieldId = "-1";
	var fieldName = "formScript";	
	var strFeatures="toolbar=no,status=no,menubar=no,left=300,top=150,resizable=no,height=410,width=600";
	window.open(contextpath+'/jsp/dynamic/DynamicFieldScript.jsp?fieldId='+fieldId+'&fieldName='+fieldName+'&isFormScript=true','FormScript',strFeatures).focus();
	
}

function doRefreshFieldsListOnScreenOnDoubleClick(){
	try{
	var objSelTables = document.getElementById('selectedStructTableIds');
	var selectedTableId = objSelTables.options[objSelTables.selectedIndex].value;	
	var objAttributeTables = document.getElementById('attrTableName');
	
	for(var i=0; i< objAttributeTables.options.length; i++){		
		if(objAttributeTables.options[i].value == selectedTableId){
			objAttributeTables.options[i].selected = true;			
		}
	}
	
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="doRefreshFieldsListOnScreen";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
	}catch(err){
		
	}
}

function editDynamicScreenField(fieldName){
	document.getElementById("rowSelected").value = fieldName;
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="editScreenFormField";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}

function saveDynamicScreenField(){
	if(document.getElementById('isEditField') == null || document.getElementById('isEditField').value != "true"){
		alert('Adding new field is not allowed.');
	}
	else{	
		  var label = document.getElementById('attributeLabel').value.trim();
//		  var alignment = document.getElementById('attributeFieldAlignment').value.trim();
				if(label.length == 0){
					document.getElementById('err-msg').innerHTML= localizedStrings.VALID_LABEL;
					document.getElementById('attributeLabel').focus();
					showResponseMsg('err-msg','succ-msg');
				}
//				else if(alignment.length == 0){
//					document.getElementById('err-msg').innerHTML= localizedStrings.VALID_ATTR_ALIGNMENT;
//					document.getElementById('attributeFieldAlignment').focus();
//					showResponseMsg('err-msg','succ-msg');
//				}
				
				else{			
					document.getElementById('servlet_name').value="DynamicOrderConfig";
					document.getElementById('callbackmethod_name').value="doSaveScreenFormField";	
					document.forms[0].action=contextpath+"/RequestHandlerServlet";
					document.forms[0].method="POST";
					document.forms[0].submit();	
		}
	}
}

function clearAllScreenFields(){
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="clearAllScreenFields";	
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}

function saveFieldStructureForScreens(){	
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="doSaveFieldStructureForScreens";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();	
}

function saveFieldLevelScript(){	
	try{
		var isFormScript = document.getElementById('isFormScript').value;		
		var compType = "";
		if(isFormScript == "true"){
			window.opener.document.getElementById('dataSavedFlag').value = false;
			compType = document.getElementById("formEventTypeId").value;						
		}
		else {
			compType = document.getElementById("compEventTypeId").value
		}
		if(compType =="select"){
			alert('Please select a Event.');
		}
		else{
			document.getElementById('servlet_name').value="DynamicOrderConfig";
			document.getElementById('callbackmethod_name').value="saveFieldLevelScript";	
			document.forms["fieldScript"].method="POST";
			document.forms["fieldScript"].submit();
		}
	}catch(err){
		alert(err);
	}
}

function removeTableFromScreen(){	
	document.getElementById('servlet_name').value="DynamicOrderConfig";
	document.getElementById('callbackmethod_name').value="removeTableFromScreen";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}
function openFieldJavaScript(){
	var fieldId = document.getElementById('fieldId').value;
	var fieldName = document.getElementById('attributeDBName').value;
	if(fieldName.trim() == ""){
		alert('Enter the Field Name');
	}else {
	var strFeatures="height=410,width=600,left=300,top=150,toolbar=no,status=no,menubar=no,resizable=no";
	window.open(contextpath+'/jsp/dynamic/DynamicFieldScript.jsp?fieldId='+fieldId+'&fieldName='+fieldName,'FieldScript',strFeatures).focus();
	}
}

function enterKeyPress(e,name)
{
 
        if (window.event) { e = window.event; }
        if (e.keyCode == 13)
        {
                document.getElementById(name).click();
        }
}

function createNewGroupType(){
	
	document.getElementById("add_new_GroupType").click();


}
function setEditedId(editedId)
{
	
	document.getElementById('edited_ids').value=document.getElementById('edited_ids').value+editedId+",";
	document.getElementById('edited_mode').value='e';
}

function setDynEditedId(dyneditedId)
{
	document.getElementById('dynedited_ids').value=document.getElementById('dynedited_ids').value+dyneditedId+",";
	document.getElementById('edited_mode').value='e';	
	
}

function getVal(str){
	var val;	
	val=document.getElementById(str).value;
	return val;
}
function setVal(str,val){
	if(document.getElementById(str)!=null){
			document.getElementById(str).value=val;
		}
}
function displayHome(){ 
	
	var objCompany = document.getElementById('company_name');
	var selectedCompany = objCompany.options[objCompany.selectedIndex].value;
	var split = selectedCompany.split(',');
	var iCompanyId = split[0];
	var iPartyId = split[1];
	var iCurrencydefaultId = split[2];
	
	var objYear = document.getElementById('year');
	var selectedYear = objYear.options[objYear.selectedIndex].value;
	
	if(selectedCompany==-1){ 
	 document.getElementById('errormessage').innerHTML="Please select valid company!";
	 return false;
	}else if(selectedYear==-1){ 
	 document.getElementById('errormessage').innerHTML="Please select valid year value!";
	 return false;
	}
	else{ 
	document.getElementById('company_id').value=iCompanyId;
	document.getElementById('party_id').value=iPartyId;
	document.getElementById('currency_defaultid').value=iCurrencydefaultId;
	document.getElementById('year_id').value=selectedYear;	
	document.getElementById('servlet_name').value="HomePageServlet";
	document.getElementById('callbackmethod_name').value="doSetInSession";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	}
}
function logout(){
	document.forms[0].action=contextpath+"/jsp/login/Login.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
function changeCompanyYear(){
	document.getElementById('servlet_name').value="HomePageServlet";
	document.getElementById('callbackmethod_name').value="doChangeCompanyYear";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
function showHome(){
	
	document.forms[0].action=contextpath+"/jsp/home/Dashboard.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
	
}
function getAutoSlNo(prefix){
	
	var pagePrefixId=jQuery('#page_prefix_id').val();//si_prefix
	var pageAutoNoId=jQuery('#page_auton_no_id').val();//invoice_no
	var headerNoColumn=jQuery('#auto_header_no_column').val();//invoice_no
	var headerIdColumn=jQuery('#auto_header_id_column').val();//invoice_id
	var headerTypeValue=jQuery('#auto_header_type_value').val();//
	var headerTypeColumn=jQuery('#auto_header_type_column').val();// 
	var headerTableName=jQuery('#auto_header_table_name').val();//sales_invoice
	var autoNoTableName=jQuery('#auto_autono_table_name').val();//set_autono_sales_invoice
	
	 var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'CommonUtilServlet', callbackmethod_name : 'doGetAutoSlNo',
			prefix:prefix,
			page_prefix_id:pagePrefixId,page_auton_no_id:pageAutoNoId,
			auto_header_no_column:headerNoColumn,auto_header_id_column:headerIdColumn,auto_header_type_column:headerTypeColumn,auto_header_type_value:headerTypeValue,auto_header_table_name:headerTableName,auto_autono_table_name:autoNoTableName
			},
		dataType: "xml",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + jqXHR);
		},
		success: function(xml) {
			
			jQuery(xml).find('auto_last_no').each(function(){
				 
				var lastno1=jQuery(this).find('last_no').text();
				var autoNo=jQuery(this).find('auto_no').text();
				if(parseInt(autoNo)==0){
					jQuery("#"+pageAutoNoId).val("");	
				}
				else{
					jQuery("#"+pageAutoNoId).val( lastno1 );
				}
				
				$('#validate-form').bootstrapValidator('revalidateField', pageAutoNoId);
				$('#validate-form').bootstrapValidator('revalidateField', pagePrefixId);
				
			});
			 	
		}
	});
	
}
function getAutoNoData()
{
	var pagePrefixId=jQuery('#page_prefix_id').val();//si_prefix
	var pageAutoNoId=jQuery('#page_auton_no_id').val();//invoice_no
	var headerNoColumn=jQuery('#auto_header_no_column').val();//invoice_no
	var headerIdColumn=jQuery('#auto_header_id_column').val();//invoice_id
	var headerTypeValue=jQuery('#auto_header_type_value').val();//
	var headerTypeColumn=jQuery('#auto_header_type_column').val();// 
	var headerTableName=jQuery('#auto_header_table_name').val();//sales_invoice
	var autoNoTableName=jQuery('#auto_autono_table_name').val();//set_autono_sales_invoice
	
	
	 var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'CommonUtilServlet', callbackmethod_name : 'doGetAutoNoData',
			page_prefix_id:pagePrefixId,page_auton_no_id:pageAutoNoId,
			auto_header_no_column:headerNoColumn,auto_header_id_column:headerIdColumn,auto_header_type_column:headerTypeColumn,auto_header_type_value:headerTypeValue,auto_header_table_name:headerTableName,auto_autono_table_name:autoNoTableName
			},
		dataType: "xml",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + jqXHR);
		},
		success: function(xml) {
			jQuery(xml).find('auto_detail').each(function(){
				 
				var allPrefix=jQuery(this).find('all_prefix').text();
				var autoPrefix=jQuery(this).find('auto_prefix').text();
				var checkType=jQuery(this).find('checked_type').text();
				var commonStartNo=jQuery(this).find('common_start_no').text();
				var initialStage=jQuery(this).find('initial_stage').text();
				
				jQuery('#autono_common_start_no').val(commonStartNo);
				if(checkType=='manual'){
					$('#man_slno').prop('checked', true);
					if(parseInt(initialStage)==0){
						$('#tpcs_slno').attr("disabled", true) ;
					}
				}
				else if(checkType=='common'){
					$('#tpcs_slno').prop('checked', true);
					$('#common_slno').prop('checked', true);
					
					if(parseInt(initialStage)==0){
						$("#autono_common_start_no").prop("readonly", true);
						$('#sep_slno').attr("disabled", true) ;
						$('#man_slno').attr("disabled", true) ;
					}
					
				}
				else if(checkType=='seperate'){
					$('#tpcs_slno').prop('checked', true);
					$('#sep_slno').prop('checked', true);
					if(parseInt(initialStage)==0){
						$('#common_slno').attr("disabled", true) ;
						$('#man_slno').attr("disabled", true) ;
					}
				}
				else{
					$('#tpcs_slno').prop('checked', true);
					$('#common_slno').prop('checked', true);
				}
				displayAutoNoDiv('all_slno_prefix','tpcs_slno','man_slno','common_slno','sep_slno');
				jQuery('#autono_row_ids').val(jQuery(this).find('row_ids').text());
				jQuery('#autono_row_count').val(jQuery(this).find('row_cnt').text());
				
				document.getElementById('all_slno_prefix_div').innerHTML=allPrefix;
				document.getElementById('sep_slno_prefix_div').innerHTML=autoPrefix;
			});
			 	
		}
	});
}

function deletePrefixFromTable(idx,prefix){
	
	var pagePrefixId=jQuery('#page_prefix_id').val();//si_prefix
	var pageAutoNoId=jQuery('#page_auton_no_id').val();//invoice_no
	var headerNoColumn=jQuery('#auto_header_no_column').val();//invoice_no
	var headerIdColumn=jQuery('#auto_header_id_column').val();//invoice_id
	var headerTypeValue=jQuery('#auto_header_type_value').val();//
	var headerTypeColumn=jQuery('#auto_header_type_column').val();// 
	var headerTableName=jQuery('#auto_header_table_name').val();//sales_invoice
	var autoNoTableName=jQuery('#auto_autono_table_name').val();//set_autono_sales_invoice
	
	if(jQuery('#auto_addrow_table tr').length>1){
		deleteRowForAutoNo(idx);
		jQuery('#autono_delete_prefix').val(jQuery('#autono_delete_prefix').val()+prefix+",")
	}
//	 var url =contextpath+"/RequestHandlerServlet";
//		jQuery.ajax({
//		url: url,
//		type: "POST",
//		data: {
//			servlet_name : 'SalesInvoiceServlet', callbackmethod_name : 'doDeleteAutoNoPrefix',prefix:prefix,
//			page_prefix_id:pagePrefixId,page_auton_no_id:pageAutoNoId,
//			auto_header_no_column:headerNoColumn,auto_header_id_column:headerIdColumn,auto_header_type_column:headerTypeColumn,auto_header_type_value:headerTypeValue,auto_header_table_name:headerTableName,auto_autono_table_name:autoNoTableName
//			},
//		dataType: "xml",
//		error: function(jqXHR, textStatus, errorThrown) {
//			alert( "Request failed: " + jqXHR);
//		},
//		success: function(xml) {
//			jQuery(xml).find('delete_prefix').each(function(){
//				var prefixDeleted=jQuery(this).find('prefix_deleted').text();
//				if(parseInt(prefixDeleted)==1){
//					deleteRowForAutoNo(idx);
//					// jQuery('table#auto_addrow_table #prefix_row_'+idx).remove();
//					// jQuery('table#auto_getrow_table #sep_prefix_row_'+idx).remove();
//					 
//				}
//			});
//		}
//		});
}
function saveAutoNo(){
	
	var pagePrefixId=jQuery('#page_prefix_id').val();//si_prefix
	var pageAutoNoId=jQuery('#page_auton_no_id').val();//invoice_no
	var headerNoColumn=jQuery('#auto_header_no_column').val();//invoice_no
	var headerIdColumn=jQuery('#auto_header_id_column').val();//invoice_id
	var headerTypeValue=jQuery('#auto_header_type_value').val();//
	var headerTypeColumn=jQuery('#auto_header_type_column').val();// 
	var headerTableName=jQuery('#auto_header_table_name').val();//sales_invoice
	var autoNoTableName=jQuery('#auto_autono_table_name').val();//set_autono_sales_invoice
	
	var deleteprefix=jQuery('#autono_delete_prefix').val();
	
	
	
	if(jQuery('#tpcs_slno').is(":checked")==false && jQuery('#man_slno').is(":checked")==false){
		document.getElementById('err-msg').innerHTML="Please select numbering option";
		showResponseMsg('err-msg','succ-msg');
		return false;
	}
	else{
		
		$('#common_slno').attr("disabled", false) ;
		$('#sep_slno').attr("disabled", false) ;
		$('#man_slno').attr("disabled", false) ;

	
		var rowIds=jQuery('#autono_row_ids').val();
		
		if(rowIds!='' || deleteprefix!=''){
			
			var arPrefix = [];
			var arRowIds = rowIds.split(',');


			for(var i = 0; i < arRowIds.length; i++) {
				if(arRowIds[i]!=''){
					arPrefix.push(jQuery('#autono_prefix_'+arRowIds[i]).val().trim());
				}
			}
			var commonSlno=jQuery('#autono_common_start_no').val();

			var arSlno= [];
			for(var i = 0; i < arRowIds.length; i++) {
				if(arRowIds[i]!=''){
					arSlno.push(jQuery('#autono_sep_start_no_'+arRowIds[i]).val().trim());
				}
			}

			var comSlno=0;
			var sepSlno=0;
			var manSlno=0;
			if(jQuery('#common_slno').is(":checked")){
				comSlno=1;
			}
			if(jQuery('#sep_slno').is(":checked")){
				sepSlno=1;
			}
			if(jQuery('#man_slno').is(":checked")){
				manSlno=1;	
			}

			var url =contextpath+"/RequestHandlerServlet";
			jQuery.ajax({
				url: url,
				type: "POST",
				data: {
					servlet_name : 'CommonUtilServlet', callbackmethod_name : 'doSaveAutoNo',
					all_prefix:arPrefix,common_slno:commonSlno,all_sep_slno:arSlno,is_common:comSlno,is_seperate:sepSlno,is_manual:manSlno,delete_prefix:deleteprefix,
					page_prefix_id:pagePrefixId,page_auton_no_id:pageAutoNoId,
					auto_header_no_column:headerNoColumn,auto_header_id_column:headerIdColumn,auto_header_type_column:headerTypeColumn,auto_header_type_value:headerTypeValue,auto_header_table_name:headerTableName,auto_autono_table_name:autoNoTableName
				},
				dataType: "xml",
				error: function(jqXHR, textStatus, errorThrown) {
					alert( "Request failed: " + jqXHR);
				},
				success: function(xml) {

					$('#'+pagePrefixId).find('option:gt(0)').remove();
					jQuery(xml).find('auto_number_prefix').each(function(){
						var prefix=jQuery(this).find('auto_prefix').text();
						$('#'+pagePrefixId).append( new Option(prefix,prefix) );
					});

					var length = $('#'+pagePrefixId).children('option').length;
					var firstVal=$('#'+pagePrefixId).children('option:first').val();
					if(parseInt(length)==2 && parseInt(firstVal)==-1){
						$('#'+pagePrefixId).children('option:not(:first)').attr("selected", "selected");
						getAutoSlNo($('#'+pagePrefixId+" option:selected").val());
					}

					if(manSlno==0){
						$("#"+pageAutoNoId).prop("readonly", true);
					}
					else{
						$("#"+pageAutoNoId).prop("readonly", false);
					}
					jQuery('#'+pageAutoNoId).val('');
					document.getElementById("auono_close").click();
					
				}
			});
		}
		else{
			document.getElementById("auono_close").click();
		}
	}
	
	
}
function displayAutoNoDiv(allSlnoPrefix,tpcsSlno,manSlno,commonSlno,sepSlno){
	
//	if(jQuery('#'+allSlnoPrefix ).is(":checked")){
//		jQuery('#all_slno_prefix_div').show();
//		jQuery('#tpcs_slno_div').hide();
//	}
	if(jQuery('#'+tpcsSlno ).is(":checked")){
//		jQuery('#all_slno_prefix_div').show();
		jQuery('#tpcs_slno_div').show();
		
		if(jQuery('#'+commonSlno ).is(":checked")==false && jQuery('#'+sepSlno ).is(":checked")==false){
			$('#common_slno').prop('checked', true);
		}
		
		if(jQuery('#'+commonSlno ).is(":checked")){
//			jQuery('#all_slno_prefix_div').show();
			jQuery('#common_slno_div').show();
			jQuery('#sep_slno_div').hide();
		}
		else if(jQuery('#'+sepSlno ).is(":checked")){
//			jQuery('#all_slno_prefix_div').hide();
			jQuery('#common_slno_div').hide();
			jQuery('#sep_slno_div').show();
		}
	}
	else if(jQuery('#'+manSlno ).is(":checked")){
//		jQuery('#all_slno_prefix_div').show();
		jQuery('#tpcs_slno_div').hide();
	}
}

function addAnotherLineAutoNo(){
	  rowCnt=$('#autono_row_count').val();
	  $('#autono_row_ids').val($('#autono_row_ids').val()+rowCnt+",");	
	
	  $('#auto_addrow_table tr:last').after('<tr id=\"prefix_row_'+rowCnt+'\">'+
			'<td ><input class="form-control" id="autono_prefix_'+rowCnt+'" name="autono_prefix_'+rowCnt+'" onblur="setPrefixToSeperate(this.value,\''+rowCnt+'\')" maxlength="15" size="30" type="text" placeholder="Prefix" /></td>'+
			'<td style="width: 52px;"><a href=\"javascript:addAnotherLineAutoNo();\" style=\"vertical-align: bottom;\" data-toggle=\"tooltip\" title=\"Add\" ><span class=\"span-icon glyphicon glyphicon-plus text-primary\"></span></a>&nbsp;<a href="javascript:deleteRowForAutoNo('+rowCnt+')" style="vertical-align: bottom;" data-toggle="tooltip" title="Remove" ><span class="span-icon glyphicon glyphicon-remove icon-delete"></span></a></td></tr>');
	  
	  $('#auto_getrow_table tr:last').after('<tr id=\"sep_prefix_row_'+rowCnt+'\" >'+
				'<td id="seperate_prefix_'+rowCnt+'">&nbsp;</td>'+
				'<td><input class="form-control" id="autono_sep_start_no_'+rowCnt+'" name="autono_sep_start_no_'+rowCnt+'"  size="30" type="text" max="999999" maxlength="6" placeholder="Starting No." onkeyup="validateAutoSlno(this)" onblur="setAutoSlnoIsEmpty(this)" value="1"/></td></tr>');
	  
		rowCnt=parseInt(rowCnt)+1;
		
		$('#autono_row_count').val(rowCnt);
		
		$(function(){
			$('[data-toggle="tooltip"]').tooltip();
		});

}
function setPrefixToSeperate(prefix,idx){
	jQuery('#seperate_prefix_'+idx).html(prefix);
}

function validateAutoSlno(id){
	if(isNaN(id.value.trim()) || parseInt(id.value)==0){
		id.value=1;
		}
}
function setAutoSlnoIsEmpty(id){
	if(id.value.trim().length==0 || isNaN(id.value.trim()) || parseInt(id.value)==0){
		id.value=1;
		}
}
function deleteRowForAutoNo(rowNo){
	if(jQuery('#auto_addrow_table tr').length>1){
		  jQuery('table#auto_addrow_table #prefix_row_'+rowNo).remove();
		  jQuery('table#auto_getrow_table #sep_prefix_row_'+rowNo).remove();
		  var rowIds=$('#autono_row_ids').val();
		  jQuery('#autono_row_ids').val(rowIds.replace(rowNo+",",""));
	}
}

//Group Type Form Page
function loadDataToGroupTypeSlNo(){
	var url =contextpath+"/RequestHandlerServlet";
	
	var request = jQuery.ajax({
	url: url,
	type: "POST",
	data: {servlet_name : 'GroupMasterServlet' , callbackmethod_name : 'doGetPopupGroupTypeDisplaySlNo'},
	dataType: "xml"
	});
	request.done(function(xml) {
		
		jQuery(xml).find('grouptypeslno').each(function(){
	    	 groupTypeSlno=jQuery(this).find('grouptypeiddisplayslno').text();

	    	 
	    	 document.getElementById('seq_no').value=groupTypeSlno;
//	    	 
//	    	 $('#account_group_type').append($('<option>', { 
//	    	        value: groupTypeId,
//	    	        text : groupTypeName 
//	    	    }));
	});
	});
	request.fail(function(jqXHR, textStatus) {
	alert( "Request failed: " + textStatus );
	});
	
}

function saveGroupTypeFormPage(){
	
	var grouptype_name=jQuery('#form_group_type_name').val();
	var parentgroup_type_id=jQuery('#parent_group_type_id').val();
	var parentgroup_type_name=jQuery('#parent_group_type_name').val();
	var seqno=jQuery('#seq_no').val(); 
		
	$('#validate_form_grouptype').bootstrapValidator('validate');
	$('#validate_form_grouptype').find(".has-error :input:first").focus();
	
	if(jQuery('#validate_form_grouptype .has-error').length==0){
        var url =contextpath+"/RequestHandlerServlet";
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'GroupMasterServlet' , callbackmethod_name : 'doSaveGroupTypeFromPage',
			grouptype_name:grouptype_name,parentgroup_type_name:parentgroup_type_name,parentgroup_type_id:parentgroup_type_id,seqno:seqno
			
		},
		dataType: "html"
		});
		request.done(function(msg) {
			
			if(msg==2){
				 document.getElementById('warning-msg').innerHTML="Unable to save.\nReason: Item Group Type or Display Sl.no must be unique";
				showWarningMsg('warning-msg');
				jQuery('#validate_form_grouptype #group_type_name').focus();
			}
			else if(msg==1){
				 document.getElementById('succ-msg').innerHTML=grouptypelocalizedStrings.GROUPTYPE_SAVED;
				 showResponseMsg('err-msg','succ-msg');
				document.getElementById("close_btn").click();
			}
			else{
				 document.getElementById('err-msg').innerHTML=grouptypelocalizedStrings.GROUPTYPE_SAVED_FAILED;
				 showResponseMsg('err-msg','succ-msg');
				document.getElementById("close_btn").click();
			}
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});

}
	
}


function showBuyerOrder(headerMode){
	
//	document.forms[0].action=contextpath+"/jsp/order/BuyerOrder.jsp";	
//	document.forms[0].method="POST";	
//	document.forms[0].submit();
	
	document.getElementById('servlet_name').value="BuyerOrderServlet";
	document.getElementById('callbackmethod_name').value="doDisplayBuyerOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
	
}
function addBuyerOrder(headerMode){
	
//	document.forms[0].action=contextpath+"/jsp/order/AddBuyerOrder.jsp";	
//	document.forms[0].method="POST";	
//	document.forms[0].submit();	
	
	document.getElementById('servlet_name').value="BuyerOrderServlet";
	document.getElementById('callbackmethod_name').value="doAddBuyerOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?header_mode="+headerMode;	
	document.forms[0].method="POST";	
	document.forms[0].submit();
	
}
function showInternalOrder(){
	
	document.forms[0].action=contextpath+"/jsp/order/InternalOrder.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
	
}
function addInternalOrder(){
	
	document.forms[0].action=contextpath+"/jsp/order/AddInternalOrder.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
	
}
function showInternalOrderRMR(){
	
	document.forms[0].action=contextpath+"/jsp/order/InternalOrderRMR.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
	
}
function addInternalOrderRMR(){
	
	document.forms[0].action=contextpath+"/jsp/order/AddInternalOrderRMR.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
	
}
function showIndent(){
	
	document.getElementById('servlet_name').value="IndentServlet";
	document.getElementById('callbackmethod_name').value="doDisplayIndent";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}	
	
function addIndent(headerMode){
	
	document.getElementById('servlet_name').value="IndentServlet";
	document.getElementById('callbackmethod_name').value="doAddIndent";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?header_mode="+headerMode;	
	document.forms[0].method="POST";	
	document.forms[0].submit();
	
	
}

function createNewCustomer(){
//	clearCustomerModal();
	document.getElementById("add_new_customer").click();
	 
	
	}


function saveUOMFromPage(){
	var uomName=document.getElementById('uom_name').value.trim();
	
	 //$('#validate-form-uom').bootstrapValidator('revalidateField', 'uom_name');
	 $('#validate-form-uom').bootstrapValidator('validate');
	 $('#validate-form-uom').find(".has-error :input:first").focus();
	 
	 if(jQuery('#validate-form-uom .has-error').length==0){
			var url =contextpath+"/RequestHandlerServlet";
			var request = jQuery.ajax({
			url: url,
			type: "POST",
			data: {
				servlet_name : 'MaterialMasterServlet' , callbackmethod_name : 'doSaveUOMFromPage',
				uom_name:uomName
				},
				dataType: "html"
			});
			request.done(function(msg) {
				if(msg==2){
					
					 document.getElementById('warning-msg').innerHTML="UOM not inserted.\nUOM name ("+uomName+") already exists";
					 showWarningMsg('warning-msg');
					 jQuery('#validate-form-uom #uom_name').focus();
				}
				else if(msg==1){
					document.getElementById('succ-msg').innerHTML=uomlocalizedStrings.UOM_SAVED;
					showResponseMsg('err-msg','succ-msg');
					document.getElementById("uom_close").click();
					clearUomModal();
				}
				else{
					document.getElementById('err-msg').innerHTML=uomlocalizedStrings.UOM_SAVED_FAILED;
					showResponseMsg('err-msg','succ-msg');
					document.getElementById("uom_close").click();
				}
			});
			request.fail(function(jqXHR, textStatus) {
			alert( "Request failed: " + textStatus );
			});
		}
}


function saveCustomerFromPage(){
	var customerCode=document.getElementById('customer_code').value.trim();
	var customerName=document.getElementById('company_name').value.trim();
	
		
	$('#validate-form-customer').bootstrapValidator('validate');
	
	if(jQuery('#validate-form-customer #tab1primary .has-error').length>0){
		$('#validate-form-customer .nav-tabs a[href="#tab1primary"]').tab('show');
	}
	else if(jQuery('#validate-form-customer #tab4primary .has-error').length>0){
		$('#validate-form-customer .nav-tabs a[href="#tab4primary"]').tab('show');
	}
	
	$('#validate-form-customer').find(".has-error :input:first").focus();	
	
	var data=jQuery('#validate-form-customer').serialize()+ '&servlet_name=CustomerServlet&callbackmethod_name=doSaveCustomerFromPage';
//	data.push({name: 'servlet_name', value: 'CustomerServlet'});
//	data.push({name: 'callbackmethod_name', value: 'doSaveCustomerFromPage'});
	
	if(jQuery('#validate-form-customer .has-error').length==0){
        var url =contextpath+"/RequestHandlerServlet";
		var request = jQuery.ajax({
		url: url,
		type: "POST",
		data:data, 
//		{
//			servlet_name : 'CustomerServlet' , callbackmethod_name : 'doSaveCustomerFromPage',
//			customer_code:customerCode,company_name:customerName,display_name:displayName,contact_salutation:contactSalution,first_name:firstName,last_name:lastName,
//			email:email,phone:phone,contact_desg:contactDesg,party_tag:partyTagVal,status:status,currency:currency,credit_days:creditDays,payment_terms:paymentTerms,bill_street:billStreet,bill_location:billLoc,bill_city:billCity,
//			bill_state:billState,bill_zip:billZip,bill_country:billCountry,company_phone:compPhone,company_email:compEmail,company_fax:compFax,ship_street:shipStreet,ship_location:shipLoc,ship_city:shipCity,
//			ship_state:shipState,ship_zip:shipZip,ship_country:shipCountry,ship_phone:shipPhone,vat:vat,service_no:serNo,pan_no:panNo,insurance:insurance,cin_no:cinNo,account_group_id:accGroupId,
//			bill_tracking:billTracking,notes:notes,party_id:custId,customer_mode:custMode,gst_no:gstNo,ssn_no:ssnNo,
//			mulEmail
			
			//$('#validate-form-customer').serialize(),
//		},
		dataType: "html"
		});
		request.done(function(msg) {
			
			if(msg==2){
				 document.getElementById('warning-msg').innerHTML="Associate not inserted.\nAssociate code ("+customerCode+") or name ("+customerName+") already exists";
				 showWarningMsg('warning-msg');
				 jQuery('#validate-form-customer #customer_code').focus();
			}
			else if(msg==3){
				 document.getElementById('warning-msg').innerHTML="Associate not inserted.\nAssociate code ("+customerCode+") already exists";
				 showWarningMsg('warning-msg');
				 jQuery('#validate-form-customer #customer_code').focus();
			}
			else if(msg==4){
				 document.getElementById('warning-msg').innerHTML="Associate not inserted.\nAssociate name ("+customerName+") already exists";
				 showWarningMsg('warning-msg');
				 jQuery('#validate-form-customer #customer_name').focus();
			}
			else if(msg==1){
				
				
				var custAccId=jQuery('#validate-form-customer #customer_acc_id').val();
				
				if(custAccId>0){//In Update
					
					 jQuery('#validate-form #customer').val(customerName);
					 jQuery('#validate-form #customer').focus();
					// $('#validate-form').bootstrapValidator('revalidateField', 'customer');
				}
				
				 document.getElementById('succ-msg').innerHTML=customerlocalizedStrings.CUSTOMER_SAVED;
				 showResponseMsg('err-msg','succ-msg');
				 
				 
				 
				 $('#validate-form-customer #close_btn').click();
				 
				
				
				clearCustomerModal();
				
//				$('#customerMasterModal').on('hidden.bs.modal', function () {
//				    $(this).find("input,textarea,select").val('').end();
//
//				});
//				$('#validate-form-customer').on('hidden.bs.modal', '.modal', function () {
//				    $(this).removeData('bs.modal');
//				});
			}
			else{
				 document.getElementById('err-msg').innerHTML=customerlocalizedStrings.CUSTOMER_SAVED_FAILED;
				 showResponseMsg('err-msg','succ-msg');
				document.getElementById("close_btn").click();
			}
		});
		request.fail(function(jqXHR, textStatus) {
		alert( "Request failed: " + textStatus );
		});

}
	//$( "#validate-form-customer" ).serialize(),
//	jQuery('#servlet_name').val("CustomerServlet");
//	jQuery('#callbackmethod_name').val("doSaveCustomerFromPage");
//	$("#validate-form-customer" ).serialize(),
//	if(document.getElementById('validation_customer_btn').disabled==false){
//		
//		
//
//		var url =contextpath+"/RequestHandlerServlet";
//		var request = jQuery.ajax({
//		url: url,
//		type: "POST",
//		data: 
//			{ 
//			servlet_name : 'CustomerServlet' , callbackmethod_name : 'doSaveCustomerFromPage',
//			customer_code:customerCode,company_name:customerName,display_name:displayName,contact_salutation:contactSalution,first_name:firstName,last_name:lastName,
//			email:email,phone:phone,contact_desg:contactDesg,party_tag:partyTagVal,status:status,currency:currency,credit_days:creditDays,payment_terms:paymentTerms,bill_street:billStreet,bill_location:billLoc,bill_city:billCity,
//			bill_state:billState,bill_zip:billZip,bill_country:billCountry,company_phone:compPhone,company_email:compEmail,company_fax:compFax,ship_street:shipStreet,ship_location:shipLoc,ship_city:shipCity,
//			ship_state:shipState,ship_zip:shipZip,ship_country:shipCountry,ship_phone:shipPhone,vat:vat,service_no:serNo,pan_no:panNo,insurance:insurance,account_group_id:accGroupId,
//			bill_tracking:billTracking,notes:notes,customer_id:custId,customer_mode:custMode
//			},
//		dataType: "html"
//		});
//		request.done(function(msg) {
//			
//			if(msg==2){
//				 document.getElementById('warning-msg').innerHTML="Associate not inserted.\nAssociate code ("+customerCode+") or name ("+customerName+") already exists";
//				 showWarningMsg('warning-msg');
//				 jQuery('#validate-form-customer #customer_code').focus();
//			}
//			else if(msg==3){
//				 document.getElementById('warning-msg').innerHTML="Associate not inserted.\nAssociate code ("+customerCode+") already exists";
//				 showWarningMsg('warning-msg');
//				 jQuery('#validate-form-customer #customer_code').focus();
//			}
//			else if(msg==4){
//				 document.getElementById('warning-msg').innerHTML="Associate not inserted.\nAssociate name ("+customerName+") already exists";
//				 showWarningMsg('warning-msg');
//				 jQuery('#validate-form-customer #customer_name').focus();
//			}
//			else if(msg==1){
//				 document.getElementById('succ-msg').innerHTML=customerlocalizedStrings.CUSTOMER_SAVED;
//				 showResponseMsg('err-msg','succ-msg');
//				document.getElementById("close_btn").click();
//				if(trTag=='CHART'){
//					showChartOfAccounts();
//					}
//				else if(trTag=='GL'){
//					
//				}
//			}
//			else{
//				 document.getElementById('err-msg').innerHTML=customerlocalizedStrings.CUSTOMER_SAVED_FAILED;
//				 showResponseMsg('err-msg','succ-msg');
//				document.getElementById("close_btn").click();
//			}
//		});
//		request.fail(function(jqXHR, textStatus) {
//		alert( "Request failed: " + textStatus );
//		});
//	}

}
//PURCHASE_ORDER
function showPurchaseOrder(){
	
	document.getElementById('servlet_name').value="PurchaseOrderServlet";	
	document.getElementById('callbackmethod_name').value="doDisplayPurchaseOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}

function addPurchaseOrder(headerMode){
	document.getElementById('servlet_name').value="PurchaseOrderServlet";
	document.getElementById('callbackmethod_name').value="doAddPurchaseOrder";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?header_mode="+headerMode;	
	document.forms[0].method="POST";	
	document.forms[0].submit();

}

//Approved Price List

function showApprovedPriceList()
{
	document.getElementById('servlet_name').value="ApprovedPriceListServlet";
	document.getElementById('callbackmethod_name').value="doDisplayApprovedPriceList";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
			
}

function showOpeningStock()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/OpeningStock.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function addOpeningStock()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/AddOpeningStock.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showGoodsReceipt()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/GoodsReceipt.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function addGoodsReceipt()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/AddGoodsReceipt.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showPurchaseReturn()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/PurchaseReturn.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function addPurchaseReturn()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/AddPurchaseReturn.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showGateEntry()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/GateEntry.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function addGateEntry()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/AddGateEntry.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}

function showWarehouse()
{
	document.getElementById('servlet_name').value="WarehouseMasterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayWarehouse";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}	

function addWarehouse(){
	document.forms[0].action=contextpath+"/jsp/Inventory/addWarehouse.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function showIssues(){
	document.forms[0].action=contextpath+"/jsp/Inventory/showIssues.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function addIssues(){
	document.forms[0].action=contextpath+"/jsp/Inventory/addIssues.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function showReturns(){
	document.forms[0].action=contextpath+"/jsp/Inventory/showReturns.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function addReturns(){
	document.forms[0].action=contextpath+"/jsp/Inventory/addReturns.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function showSalesDespatch(){
	document.forms[0].action=contextpath+"/jsp/Inventory/ShowSalesDespatch.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function addSalesDespatch(){
	document.forms[0].action=contextpath+"/jsp/Inventory/AddSalesDespatch.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function showSalesReturn(){
	document.forms[0].action=contextpath+"/jsp/Inventory/ShowSalesReturn.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function addSalesReturn(){
	document.forms[0].action=contextpath+"/jsp/Inventory/AddSalesReturn.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function showTransfers(){
	document.forms[0].action=contextpath+"/jsp/Inventory/ShowTransfers.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function addTransfers(){
	document.forms[0].action=contextpath+"/jsp/Inventory/AddTransfers.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function showAdjustments(){
	document.forms[0].action=contextpath+"/jsp/Inventory/ShowAdjustments.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function addAdjustments(){
	document.forms[0].action=contextpath+"/jsp/Inventory/AddAdjustments.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}


function showJobcard()
{
	document.forms[0].action=contextpath+"/jsp/production/Jobcard.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function addJobcard()
{
	document.forms[0].action=contextpath+"/jsp/production/AddJobcard.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}


/*admin*/
function showEntityRights(){
	document.getElementById('servlet_name').value="EntityServlet";
	document.getElementById('callbackmethod_name').value="doDisplayEntityRights";
//	document.getElementById('pageno').value="1";
	if(document.getElementById('request_type')){
		document.getElementById('request_type').value="Normal";
	}
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function addNewRights(){
	document.getElementById('servlet_name').value="EntityServlet";
	document.getElementById('callbackmethod_name').value="doNewRights";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}
function showProfile(){
	document.getElementById('servlet_name').value="ProfileServlet";
	document.getElementById('callbackmethod_name').value="doDisplayProfile";
	document.getElementById('pageno').value="1";
	document.getElementById('request_type').value="Normal";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function addNewProfile(){
	document.getElementById('servlet_name').value="ProfileServlet";
	document.getElementById('callbackmethod_name').value="doNewProfile";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function showUsers(){
	document.getElementById('servlet_name').value="UserServlet";
	document.getElementById('callbackmethod_name').value="doDisplayUsers";
	document.getElementById('pageno').value="1";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function addNewUser(){

	document.getElementById('servlet_name').value="UserServlet";
	document.getElementById('callbackmethod_name').value="doNewUser";	
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}
function showPasswordChange()

{
	//document.getElementById('request_type').value="Normal";
	document.getElementById('servlet_name').value="UserServlet";
	document.getElementById('callbackmethod_name').value="doDisplayChangePassword";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
	
}

function showGroupMaster()
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="GroupMasterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayGroupMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
	
}

function showMaterial()
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('servlet_name').value="MaterialMasterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayMaterialMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}



function addMaterial(mode)
{

	
	document.getElementById('request_type').value="Normal";
	document.getElementById('servlet_name').value="MaterialMasterServlet";
	document.getElementById('callbackmethod_name').value="doNewMaterialMaster";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?mode="+mode;	
	document.forms[0].method="POST";	
	document.forms[0].submit();	

}
function showTaxGroup()
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="TaxGroupServlet";
	document.getElementById('callbackmethod_name').value="doDisplayTaxGroup";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
	
}

function addTaxGroup(Mode){
	
	document.getElementById('request_type').value="Normal";
	document.getElementById('mode').value=Mode; 
	document.getElementById('servlet_name').value="TaxGroupServlet";
	document.getElementById('callbackmethod_name').value="doNewTaxGroup";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showStorageLocation(){
	document.forms[0].action=contextpath+"/jsp/masters/StorageLocation.jsp";
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function addStorageLocation(){
	document.forms[0].action=contextpath+"/jsp/masters/AddStorageLocation.jsp";
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function showCustomer(){
	document.getElementById('request_type').value="Normal";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="CustomerServlet";
	document.getElementById('callbackmethod_name').value="doDisplayCustomer";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function addCustomer(Mode){
	
	document.getElementById('request_type').value="Normal";
	document.getElementById('mode').value=Mode; 
	document.getElementById('servlet_name').value="CustomerServlet";
	document.getElementById('callbackmethod_name').value="doNewCustomer";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showEmployees(){
	
	document.forms[0].action=contextpath+"/jsp/masters/Employees.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function addEmployees(){
	
	document.forms[0].action=contextpath+"/jsp/masters/AddEmployees.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
 
function showBOM(){
	
	document.getElementById('request_type').value="Normal";
	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doDisplayBOM";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();			
}


function addBOM(headMode)
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('servlet_name').value="BOMServlet";
	document.getElementById('callbackmethod_name').value="doNewBOM";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?head_mode="+headMode;	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}








function clearId(e,nameField,idField){
	if(e.keyCode==8 || e.keyCode==46){
		document.getElementById(idField).value='';
	}
}





var checkSelect= new Array();
function validateDocuments(){
    var countcheck=0;
	
	
    var arr = document.getElementsByTagName("INPUT");
    
    for (i=0; i<arr.length ;i++)
    {
	    if (arr[i].type== "checkbox" && arr[i].name!='cb_rights')
	    {
		    if(arr[i].checked)
		    {    		  
		    	checkSelect[countcheck] = arr[i].value;
			    countcheck = countcheck + 1;	    
		    }
	    }
    }
    
    if(countcheck==0){
    	return false;
    }
    else{ 
    	return true;
    }
}
function getProfileDocuments(){
    var entrycountcheck=0;	
    var editcountcheck=0;	
    var deletecountcheck=0;	
    var viewcountcheck=0;	
    var excelcountcheck=0;	
    var printcountcheck=0;	
    var apprcountcheck=0;	
    var arr = document.getElementsByTagName("INPUT");
    
    for (i=0; i<arr.length ;i++)
    {
    if (arr[i].type== "checkbox")
    {
	    if(arr[i].checked)
	    {    
	    if((arr[i].name).substring(3,0)=="ent")
	    {
	    entrySelect[entrycountcheck] = arr[i].value;
	     entrycountcheck = entrycountcheck + 1;	
	    }
	    else if((arr[i].name).substring(3,0)=="edi")
	    {
	    editSelect[editcountcheck] = arr[i].value;
	     editcountcheck = editcountcheck + 1;	
	    }
	     else if((arr[i].name).substring(3,0)=="del")
	    {
	    deleteSelect[deletecountcheck] = arr[i].value;
	     deletecountcheck = deletecountcheck + 1;	
	    }
	     else if((arr[i].name).substring(3,0)=="vie")
	    {
	    viewSelect[viewcountcheck] = arr[i].value;
	     viewcountcheck = viewcountcheck + 1;	
	    }
	     else if((arr[i].name).substring(3,0)=="exc")
	    {
	    excelSelect[excelcountcheck] = arr[i].value;
	     excelcountcheck = excelcountcheck + 1;	
	    }
	     else if((arr[i].name).substring(3,0)=="pri")
	    {
	    printSelect[printcountcheck] = arr[i].value;
	     printcountcheck = printcountcheck + 1;	
	    }
	     else if((arr[i].name).substring(3,0)=="app")
	    {
	    apprSelect[apprcountcheck] = arr[i].value;
	     apprcountcheck = apprcountcheck + 1;	
	    }
	    	 
	    }
    }
    }
    
}
var entrySelect= new Array();
var editSelect= new Array();
var deleteSelect= new Array();
var viewSelect= new Array();
var excelSelect= new Array();
var printSelect= new Array();
var apprSelect= new Array();
function checkuncheckprofileentryrights(){
	   
    var arr = document.getElementsByTagName("INPUT");
    
    for (i=0; i<arr.length ;i++)
    {
     if((arr[i].name).substring(9,0)=="headerent")
     {
      if(arr[i].checked)
      {
      for (k=0;k<arr.length;k++)
      {
       if((arr[k].name).substring(3,0)=="ent")
	    {
	    arr[k].checked=true;
	    }
      }
      }
      else
      {
      for (k=0; k<arr.length ;k++)
      {
       if((arr[k].name).substring(3,0)=="ent")
	    {
	    arr[k].checked=false;
	    }
      }
      }
     }
    }
}
function checkuncheckprofileviewrights(){
	   
    var arr = document.getElementsByTagName("INPUT");
    
    for (i=0; i<arr.length ;i++)
    {     
     
      if((arr[i].name).substring(9,0)=="headervie")
     {
      if(arr[i].checked)
      {
      for (k=0;k<arr.length;k++)
      {
       if((arr[k].name).substring(3,0)=="vie")
	    {
	    arr[k].checked=true;
	    }
      }
      }
      else
      {
      for (k=0; k<arr.length ;k++)
      {
       if((arr[k].name).substring(3,0)=="vie")
	    {
	    arr[k].checked=false;
	    }
      }
      }
     }
     
    }//end outer loop
}//end fucntion


 function checkuncheckprofileexcelrights(){
   
    var arr = document.getElementsByTagName("INPUT");
    
    for (i=0; i<arr.length ;i++)
    {     
     
     if((arr[i].name).substring(9,0)=="headerexc")
     {
      if(arr[i].checked)
      {
      for (k=0;k<arr.length;k++)
      {
       if((arr[k].name).substring(3,0)=="exc")
	    {
	    arr[k].checked=true;
	    }
      }
      }
      else
      {
      for (k=0; k<arr.length ;k++)
      {
       if((arr[k].name).substring(3,0)=="exc")
	    {
	    arr[k].checked=false;
	    }
      }
      }
     }
    }//end outer loop
}//end fucntion
 
 
  function checkuncheckprofileprintrights(){
   
    var arr = document.getElementsByTagName("INPUT");
    
    for (i=0; i<arr.length ;i++)
    {
     
     
    if((arr[i].name).substring(9,0)=="headerpri")
     {
      if(arr[i].checked)
      {
      for (k=0;k<arr.length;k++)
      {
       if((arr[k].name).substring(3,0)=="pri")
	    {
	    arr[k].checked=true;
	    }
      }
      }
      else
      {
      for (k=0; k<arr.length ;k++)
      {
       if((arr[k].name).substring(3,0)=="pri")
	    {
	    arr[k].checked=false;
	    }
      }
      }
     }
    }//end outer loop
}//end fucntion
 
 
  function checkuncheckprofileapprovalrights(){
   
    var arr = document.getElementsByTagName("INPUT");
    
    for (i=0; i<arr.length ;i++)
    {
  if((arr[i].name).substring(9,0)=="headerapp")
     {
      if(arr[i].checked)
      {
      for (k=0;k<arr.length;k++)
      {
       if((arr[k].name).substring(3,0)=="app")
	    {
	    arr[k].checked=true;
	    }
      }
      }
      else
      {
      for (k=0; k<arr.length ;k++)
      {
       if((arr[k].name).substring(3,0)=="app")
	    {
	    arr[k].checked=false;
	    }
      }
      }
     }
    }//end outer loop
}//end fucntion
  function checkuncheckprofileeditrights(){
	   
	    var arr = document.getElementsByTagName("INPUT");
	    document.get
	    
	    for (i=0; i<arr.length ;i++)
	    {
	     
	     
	    if((arr[i].name).substring(9,0)=="headeredi")
	     {
	      if(arr[i].checked)
	      {
	      for (k=0;k<arr.length;k++)
	      {
	       if((arr[k].name).substring(3,0)=="edi")
		    {
		    arr[k].checked=true;
		    }
	      }
	      }
	      else
	      {
	      for (k=0; k<arr.length ;k++)
	      {
	       if((arr[k].name).substring(3,0)=="edi")
		    {
		    arr[k].checked=false;
		    }
	      }
	      }
	     }
	     
	    }//end outer loop
	}//end fucntion
  function checkuncheckprofiledeleterights(){
	   
	    var arr = document.getElementsByTagName("INPUT");
	    
	    for (i=0; i<arr.length ;i++)
	    {
	     
	     
	    if((arr[i].name).substring(9,0)=="headerdel")
	     {
	      if(arr[i].checked)
	      {
	      for (k=0;k<arr.length;k++)
	      {
	       if((arr[k].name).substring(3,0)=="del")
		    {
		    arr[k].checked=true;
		    }
	      }
	      }
	      else
	      {
	      for (k=0; k<arr.length ;k++)
	      {
	       if((arr[k].name).substring(3,0)=="del")
		    {
		    arr[k].checked=false;
		    }
	      }
	      }
	     }
	     
	     
	    }//end outer loop
	}//end fucntion
  function isValidEmail(emailStr)
  {
      
  /* The following variable tells the rest of the function whether or not
  to verify that the address ends in a two-letter country or well-known
  TLD.  1 means check it, 0 means don't. */

  var checkTLD=0;

  /* The following is the list of known TLDs that an e-mail address must end with. */

  var knownDomsPat=/^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum|uk|fr)$/;

  /* The following pattern is used to check if the entered e-mail address
  fits the user@domain format.  It also is used to separate the username
  from the domain. */

  var emailPat=/^(.+)@(.+)$/;

  /* The following string represents the pattern for matching all special
  characters.  We don't want to allow special characters in the address. 
  These characters include ( ) < > @ , ; : \ " . [ ] */

  var specialChars="\\(\\)><@,;:\\\\\\\"\\.\\[\\]";

  /* The following string represents the range of characters allowed in a 
  username or domainname.  It really states which chars aren't allowed.*/

  var validChars="\[^\\s" + specialChars + "\]";

  /* The following pattern applies if the "user" is a quoted string (in
  which case, there are no rules about which characters are allowed
  and which aren't; anything goes).  E.g. "jiminy cricket"@disney.com
  is a legal e-mail address. */

  var quotedUser="(\"[^\"]*\")";

  /* The following pattern applies for domains that are IP addresses,
  rather than symbolic names.  E.g. joe@[123.124.233.4] is a legal
  e-mail address. NOTE: The square brackets are required. */

  var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;

  /* The following string represents an atom (basically a series of non-special characters.) */

  var atom=validChars + '+';

  /* The following string represents one word in the typical username.
  For example, in john.doe@somewhere.com, john and doe are words.
  Basically, a word is either an atom or quoted string. */

  var word="(" + atom + "|" + quotedUser + ")";

  // The following pattern describes the structure of the user

  var userPat=new RegExp("^" + word + "(\\." + word + ")*$");

  /* The following pattern describes the structure of a normal symbolic
  domain, as opposed to ipDomainPat, shown above. */

  var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$");

  /* Finally, let's start trying to figure out if the supplied address is valid. */

  /* Begin with the coarse pattern to simply break up user@domain into
  different pieces that are easy to analyze. */

  var matchArray=emailStr.match(emailPat);

  if (matchArray==null) 
  {



  //alert("Email address seems incorrect (check @ and .'s)");
  return false;
  }
  var user=matchArray[1];
  var domain=matchArray[2];

  // Start by checking that only basic ASCII characters are in the strings (0-127).

  for (i=0; i<user.length; i++) 
  {
  if (user.charCodeAt(i)>127) 
  {	
  	return false;
  }
  }
  for (i=0; i<domain.length; i++) 
  {
  if (domain.charCodeAt(i)>127) 
  {	
  	return false;
  }
  }


  if (user.match(userPat)==null) 
  {	
  return false;
  }

  /* if the e-mail address is at an IP address (as opposed to a symbolic
   host name) make sure the IP address is valid. */

  var IPArray=domain.match(ipDomainPat);
  if (IPArray!=null) 
  {

  // this is an IP address

  for (var i=1;i<=4;i++) 
  {
  	if (IPArray[i]>255) 
  	{	
  		return false;
  	}
  }
  return true;
  }



  var atomPat=new RegExp("^" + atom + "$");
  var domArr=domain.split(".");
  var len=domArr.length;
  for (i=0;i<len;i++) 
  {
  if (domArr[i].search(atomPat)==-1) 
  {
  	return false;
  }
  }

  /* domain name seems valid, but now make sure that it ends in a
   known top-level domain (like com, edu, gov) or a two-letter word,
  representing country (uk, nl), and that there's a hostname preceding 
  the domain or country. */

  if (checkTLD && domArr[domArr.length-1].length!=2 && 
  domArr[domArr.length-1].search(knownDomsPat)==-1) 
  {

  return false;
  }

  // Make sure there's a host name preceding the domain.

  if (len<2) 
  {

  return false;
  }

  // If we've gotten this far, everything's valid!
  return true; 
  }
  function viewTableTextStyle(selector){
		var reDate=/^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$/
		var reNum=/^[0-9.%$()]+$/
		$(selector+" td").each(function(){
		    if($(this).text().match(reDate)){
		      $(this).addClass("nowrap");
		    } 
		    else  if($(this).text().match(reNum)){
		    	$(this).addClass("text-right");
		    }
		  });
	}
  /* Specification Setting */

  function showSpecSetting(){
  	
  	document.getElementById('servlet_name').value="SpecificationSettingServlet";
  	document.getElementById('callbackmethod_name').value="doDisplaySpecificationSetting";
  	document.forms[0].action=contextpath+"/RequestHandlerServlet";
  	document.getElementById('pageno').value="1";
  	document.forms[0].method="POST";
  	document.forms[0].submit();
  	
  }
  

/*Reports */
function showAllReports()
{
	document.forms[0].action=contextpath+"/jsp/reports/AllReports.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showIndentReport()
{
	document.forms[0].action=contextpath+"/jsp/PurchaseOrder/IndentReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showBuyerOrderReport()
{
	document.forms[0].action=contextpath+"/jsp/order/BuyerOrderReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}

function showInternalOrderReport()
{
	document.forms[0].action=contextpath+"/jsp/order/InternalOrderReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}



function showOrderStatusReport()
{
	document.forms[0].action=contextpath+"/jsp/order/OrderStatusReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}

function showOrderLeadTimeReport()
{
	document.forms[0].action=contextpath+"/jsp/order/OrderLeadTimeReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		

}

function showPriceComparisionReport()
{
	document.forms[0].action=contextpath+"/jsp/PurchaseOrder/PriceComparisionReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showMatAvgLeadTimeReport()
{
	document.forms[0].action=contextpath+"/jsp/PurchaseOrder/MatAvgLeadTimeReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		

}


function showPurchaseOrderReport()
{
	document.forms[0].action=contextpath+"/jsp/PurchaseOrder/PurchaseOrderReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showPurchaseOrderWithSizeReport()
{
	document.forms[0].action=contextpath+"/jsp/PurchaseOrder/PurchaseOrderWithSizeReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showPOSummaryReport()
{
	document.forms[0].action=contextpath+"/jsp/PurchaseOrder/POSummaryReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showMaterialStatusReport()
{
	document.forms[0].action=contextpath+"/jsp/PurchaseOrder/MaterialStatuseReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showStockStatementReport()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/StockStatementReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showStockLedgerReport()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/StockLedgerReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showMatrialReceiptSummaryReport()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/MatrialReceiptSummaryReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}
function showMatrialIssueSummaryReport()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/MatrialIssueSummaryReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();		
}

function showStkAgingAnalysisReport()
{
	
	document.forms[0].action=contextpath+"/jsp/Inventory/StkAgingAnalysisReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function showPlannedVsActualConsumption()
{
	document.forms[0].action=contextpath+"/jsp/Inventory/PlannedVsActualConsumptionReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	

}

function showProductionStart()
{
	document.forms[0].action=contextpath+"/jsp/production/ProductionStart.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}


function addProductionStart()
{
	document.forms[0].action=contextpath+"/jsp/production/AddProductionStart.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function showProductionEnd()
{
	document.forms[0].action=contextpath+"/jsp/production/ProductionEnd.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}


function addProductionEnd()
{
	document.forms[0].action=contextpath+"/jsp/production/AddProductionEnd.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}
function showOperationOrStage()
{
//	document.forms[0].action=contextpath+"/jsp/masters/OpeartionOrStage.jsp";	
//	document.forms[0].method="POST";	
//	document.forms[0].submit();	
	
	document.getElementById('request_type').value="Normal";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="OperationOrStageServlet";
	document.getElementById('callbackmethod_name').value="doDisplayOperationOrStage";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}


function showWIPReport()
{
	document.forms[0].action=contextpath+"/jsp/production/WIPReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function showProductionOutputReport()
{
	document.forms[0].action=contextpath+"/jsp/production/ProductionOutputReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

function showRejectionReworkReport()
{
	document.forms[0].action=contextpath+"/jsp/production/RejectionReworkReport.jsp";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
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

function validateStartAndEndDates(sdate,edate){	
	
	var objStartDate = strToDate(sdate);
    objStartDate.setHours(0);
    objStartDate.setMinutes(0);
    objStartDate.setSeconds(0);
    objStartDate.setMilliseconds(0);



    var objEndDate = strToDate(edate);
    objEndDate.setHours(0);
    objEndDate.setMinutes(0);
    objEndDate.setSeconds(0);
    objEndDate.setMilliseconds(0);

    StartDate=new Date(objStartDate);       
    EndDate=new Date(objEndDate);
    CurrentDate=new Date();

    if((StartDate.getTime() - EndDate.getTime())>0){
    	return false;
    }
    else{
    	return true;
    }
    	
    	    
}


function strToDate(strDate)
{ 
	var nSD = strDate.substring(0,strDate.indexOf("-"));
	nSD = nSD.trim();
	strDate = strDate.substring((strDate.indexOf("-")+1),strDate.length);
	var nSM = strDate.substring(0,strDate.indexOf("-"));
	nSM = nSM.trim();	
	strDate = strDate.substring((strDate.indexOf("-")+1),strDate.length);
	var nSY = strDate.trim();
	var objDate = new Date();
	objDate.setYear(nSY);
	objDate.setMonth(nSM - 1);
	objDate.setDate(nSD);
	return objDate;
}


function validateTrDate()
{
	
	var entryDate=jQuery('#tr_date').val();
	var finStartDate=jQuery('#fin_startdate').val();
	var finEndDate=jQuery('#fin_enddate').val();
	var booksDate=jQuery('#books_bgng_date').val();
	var currentDate=jQuery('#current_date').val();
	
	
	
	if(validateStartAndEndDates(finStartDate,booksDate)){//if books date greater than finStartDate
		finStartDate=booksDate;
	}
	
	
	if(!validateStartAndEndDates(entryDate,currentDate))//if entry date greater than Current date
	{
	 document.getElementById('warning-msg').innerHTML="Transaction date can't be a future date";
	 showWarningMsg('warning-msg');
	 jQuery('#validate-form #tr_date').focus();
	 return false;
	}
else if((!validateStartAndEndDates(finStartDate,entryDate)) || (!validateStartAndEndDates(entryDate,finEndDate))){//if entry date less than finStartDate or entry date greater than finEndDate
	document.getElementById('warning-msg').innerHTML="Transaction date should be between "+finStartDate+" and "+finEndDate;
	 showWarningMsg('warning-msg');
	 jQuery('#validate-form #tr_date').focus();
	 return false;
}
else{
	return true;
}
}
function addTCRow(curId,addToId,addToCnt){
	var idx=parseInt(jQuery('#'+addToCnt).val())+1;//curId+1;
	
	  var tcRow='<div class="row " id="tc_row_'+idx+'">'+
	  '<div class="col-sm-1-5  col-xs-3">'+
	  '<div class="form-group">'+
	  '<input type="text" class="form-control text-center" id="tc_slno_'+idx+'" name="tc_slno_'+idx+'"/>'+
	  '</div>'+
	  '</div>'+
	  '<div class="col-sm-3  col-xs-7">'+
	  '<div class="form-group">'+
	  '<input type="text" class="form-control" id="tc_text_'+idx+'" name="tc_text_'+idx+'" maxlength="50" />'+
	  '</div>'+
	  '</div>'+
	  '<div class="col-sm-6  col-xs-7">'+
	  '<div class="form-group">'+
	  '<input type="text" class="form-control" id="tc_particular_'+idx+'" name="tc_particular_'+idx+'" maxlength="250" />'+
	  '</div>'+
	  '</div>'+
	  '<div class="col-sm-1">'+
 	  '<a href="javascript:addTCRow('+idx+',\''+addToId+'\',\''+addToCnt+'\');" id="tc_add_'+idx+'" data-toggle="tooltip"  title="Add" style="display:none;"><span class="span-icon glyphicon glyphicon-plus text-primary"></span></a>'+
	  '</div>'+
	  '</div>';
	  $('#tc_area #tc_row_'+curId).after(tcRow);
	  
	  $(function(){
			$('[data-toggle="tooltip"]').tooltip();
		});
	  
	  var tcIds=jQuery('#'+addToId).val();//jQuery('#'+addToId).val()+idx+",");
	  tcIds=tcIds.replace(curId+",", curId+","+idx+",");
	  jQuery('#'+addToId).val(tcIds);
//	  $('#'+addToId).replaceWith( "<h2>New heading</h2>" );
	  
	  jQuery('#'+addToCnt).val(parseInt(jQuery('#'+addToCnt).val())+1);
	  
	    jQuery('#tc_particular_'+idx).focus(function(e) {
	    	jQuery('#tc_add_'+(idx-1)).hide();
	    	jQuery('#tc_add_'+(idx+1)).hide();
	    	jQuery('#tc_add_'+idx).show();
		}); 
	    jQuery('#tc_slno_'+idx).focus(function(e) {
	    	jQuery('#tc_add_'+(idx-1)).hide();
	    	jQuery('#tc_add_'+(idx+1)).hide();
	    	jQuery('#tc_add_'+idx).hide();
		});
	    
	    
	    jQuery('#tc_slno_'+idx).focus();
	    
	    $('#abo_form').bootstrapValidator('addField', 'tc_slno_'+idx, {
	    	 validators: {
                 numeric: {
                     message: 'Enter number'
                 }
             }
		  });
		

}


/*-----------SEASON------------*/

function showSeason()
{
	
	document.getElementById('request_type').value="Normal";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="SeasonServlet";
	document.getElementById('callbackmethod_name').value="doDisplaySeason";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
/*-------Currency Master---------*/
function showCurrencyMaster()
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="CurrencyServlet";
	document.getElementById('callbackmethod_name').value="doDisplayCurrency";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}


function showSizeRange()
{
		
	document.getElementById('request_type').value="Normal";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="SizeRangeServlet";
	document.getElementById('callbackmethod_name').value="doDisplaySizeRange";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}

function addSizeRange(mode)
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('mode').value=mode; 
	document.getElementById('servlet_name').value="SizeRangeServlet";
	document.getElementById('callbackmethod_name').value="doNewSizeRange";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();	
}

//------------------Size Mapping ----------------------------------------------
function showSizeMapping()
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="SizeMappingServlet";
	document.getElementById('callbackmethod_name').value="doDisplaySizeMapping";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}

//VARIANT
function showVariant()
{
	
	document.getElementById('request_type').value="Normal";
	document.getElementById('pageno').value="1";
	document.getElementById('servlet_name').value="VariantServlet";
	document.getElementById('callbackmethod_name').value="doDisplayVariant";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
function createNewsize(){
	 document.getElementById("add_new_size").click();
	
	}

function saveSizeMaster(){

	var sizeName=document.getElementById('size_name').value.trim();
	var isActive=document.getElementById('is_active').value.trim();
	
	 //$('#validate-form-uom').bootstrapValidator('revalidateField', 'uom_name');
	 $('#validate-form-uom').bootstrapValidator('validate');
	 $('#validate-form-uom').find(".has-error :input:first").focus();
	 
	 if(jQuery('#validate-form-uom .has-error').length==0){
			var url =contextpath+"/RequestHandlerServlet";
			var request = jQuery.ajax({
			url: url,
			type: "POST",
			data: {
				servlet_name : 'SizeRangeServlet' , callbackmethod_name : 'doSaveSizeMaster',
				size_name:sizeName,is_active:isActive
				},
				dataType: "html"
			});
			request.done(function(msg) {
		
				if(msg==2){
					
					 document.getElementById('warning-msg').innerHTML="Size Name not inserted.\nsize name ("+sizeName+") already exists";
					 showWarningMsg('warning-msg');
					 jQuery('#validate-form-sizeMaster #size_name').focus();
				}
				else if(msg==1){
					document.getElementById('succ-msg').innerHTML=sizelocalizedStrings.SIZE_SAVED;
					showResponseMsg('err-msg','succ-msg');
					document.getElementById("uom_close").click();
					
					clearUomModal();
				}
				else{
					document.getElementById('err-msg').innerHTML=sizelocalizedStrings.SIZE_SAVED_FAILED;
					showResponseMsg('err-msg','succ-msg');
					document.getElementById("uom_close").click();
				}
			});
			request.fail(function(jqXHR, textStatus) {
			alert( "Request failed: " + textStatus );
			});
		}
}

function setFocusWidth(pageFieldName,fieldId, fieldLength, normalLength){
	
	
	
	
	var dataTblWidth=jQuery('#data_table_width').val();
	jQuery(function () {
	     $('#'+fieldId).focus(function () {
	    	 $('.'+pageFieldName+'_col').css('width', (fieldLength*7)+'px');
	    	 
	    	 $('.'+pageFieldName+'_lbl').css("font-size", "16px");//14px medium

	    	 $('#head-table').css('width', (((parseInt(dataTblWidth)+parseInt(fieldLength*7)))-parseInt(fieldLength))+'px');
	    	 $('#data-table').css('width', (((parseInt(dataTblWidth)+parseInt(fieldLength*7)))-parseInt(fieldLength))+'px');
	    	 
	    	 $('#'+fieldId).get(0).scrollLeft = $('#'+fieldId).get(0).scrollWidth; 
	         //$(this).css('width', (fieldLength*7)+'px');
	     });
	     $('#'+fieldId).blur(function () {
	    	 $('.'+pageFieldName+'_col').css('width', normalLength+'px');
	    	 
	    	 $('.'+pageFieldName+'_lbl').css("font-size", "12px");//12px small
	    	 
	    	 $('#head-table').css('width', (dataTblWidth)+'px');
	    	 $('#data-table').css('width', (dataTblWidth)+'px');
//	         $(this).css('width', normalLength+'px');
	     });
 
	 });
} 

function getIfNullOrEmptyZero(str){
	var val=0;
	if(document.getElementById(str)!==null)
		{
		if(document.getElementById(str).value!=''){
			val=document.getElementById(str).value.trim();
		}
		}
	return val;
}

function runShortKey(thisFormId){
	Mousetrap.bind('alt+b', function(e, combo) {
		//alert('#'+thisFormId+"_back");
		//jQuery('#'+thisFormId+"_back").click();
	    console.log(thisFormId+'-'+combo); // logs 'ctrl+shift+up'
	});
	Mousetrap.bind('alt+s', function(e, combo) {
		//alert('#'+thisFormId+"_back");
		//jQuery('#'+thisFormId+"_back").click();
	    console.log(thisFormId+'-'+combo); // logs 'ctrl+shift+up'
	});
}
function calculateAmt(rowId,qtyRoundOff,rateRoundOff,valRoundOff){
	var qty;
	var qtyVal;
	var price;
	var priceVal;
	var amt;
	var amtVal;
	var discount;
	var discountVal;
	
	var poDis=jQuery('#detail_discount').val();
//	parseFloat((discount*100)/subTotal)
	if(rowId==0){
		
		qty=document.getElementById('qty_'+rowId);
		qtyVal=qty.value.trim();
		if(qtyVal==''){
			qtyVal=0;	
		}
		price=document.getElementById('price_fcy_'+rowId);
		priceVal=price.value.trim();
		if(priceVal==''){
			priceVal=0;	
		}
		
		if(poDis=='Yes'){
			
			discountVal=getIfNullOrEmptyZero('discount_'+rowId);
		}
		
		if(document.getElementById('value_before_tax_fcy_'+rowId)!=null){
			amt=document.getElementById('value_before_tax_fcy_'+rowId);
			
			amtVal=amt.value.trim();
			if(amtVal==''){
				amtVal=0;	
			}
		}
	}
	else{
		qty=document.getElementById('qty_S_'+rowId);
		qtyVal=qty.value.trim();
		if(qtyVal==''){
			qtyVal=0;	
		}
		price=document.getElementById('price_fcy_S_'+rowId);
		priceVal=price.value.trim();
		if(priceVal==''){
			priceVal=0;	
		}
		
		if(poDis=='Yes'){
			discountVal=getIfNullOrEmptyZero('discount_percent_S_'+rowId);
		}
		
		if(document.getElementById('value_before_tax_fcy_S_'+rowId)!=null){
			amt=document.getElementById('value_before_tax_fcy_S_'+rowId);
			amtVal=amt.value.trim();
			if(amtVal==''){
				amtVal=0;	
			}
		}
	}
	
	qtyVal=Number(Math.round(qtyVal+'e'+qtyRoundOff)+'e-'+qtyRoundOff); //qtyVal.toFixed(qtyRoundOff);
	priceVal=Number(Math.round(priceVal+'e'+rateRoundOff)+'e-'+rateRoundOff); //priceVal.toFixed(rateRoundOff);
	discountVal=Number(Math.round(discountVal+'e'+rateRoundOff)+'e-'+rateRoundOff);
//	amt.value=(qtyVal*priceVal);

	
	if(poDis=='Yes'){
			var tot=parseFloat((qtyVal*priceVal));
			var dis=parseFloat((tot*discountVal)/100);
			var totamtdis=parseFloat(tot-dis);
			if(amt != undefined){
				amt.value=Number(Math.round(totamtdis+'e'+valRoundOff)+'e-'+valRoundOff);
			}
		}
		else{
			if(amt != undefined){
				amt.value=Number(Math.round(qtyVal*priceVal+'e'+valRoundOff)+'e-'+valRoundOff);
			}
		}
	
	
	calculateSubTotal(qtyRoundOff,rateRoundOff,valRoundOff);
	calculateTax();
}
function calculateSubTotal(qtyRoundOff,rateRoundOff,valRoundOff){
	var amt=0;
	var amtVal=0;
	var detIds=document.getElementById('det_ids').value;
	var arDetIds=new Array();
	arDetIds=detIds.split(',');
	for(var i=0;i<arDetIds.length;i++){
		if(document.getElementById('value_before_tax_fcy_S_'+arDetIds[i])!=null && document.getElementById('value_before_tax_fcy_S_'+arDetIds[i])!=''){
			amtVal=document.getElementById('value_before_tax_fcy_S_'+arDetIds[i]).value;
			if(amtVal==''){
				amtVal=0;
			}
			amt=parseFloat(amt)+parseFloat(amtVal);
		}
	}
	amtVal=document.getElementById('value_before_tax_fcy_0').value;
	if(amtVal==''){
		amtVal=0;
	}
	amt=parseFloat(amt)+parseFloat(amtVal);
	
	amt=Number(Math.round(amt+'e'+valRoundOff)+'e-'+valRoundOff);
	document.getElementById('sub_total').value=amt;
	jQuery("#sub_total_lbl").html(amt);
	
	calculateNetTotal(qtyRoundOff,rateRoundOff,valRoundOff);
}
function calculateNetTotal(qtyRoundOff,rateRoundOff,valRoundOff){
	var netTotal=0;
	var subTotal=getIfNullOrEmptyZero("sub_total");
	var discountType=jQuery("#discount_type").val();
	var discount=getIfNullOrEmptyZero('discount_per');
	if(discountType=='val'){
		jQuery("#discount_0").val(parseFloat((discount*100)/subTotal));
	}
	else if(discountType=='per'){
		jQuery("#discount_0").val(parseFloat(discount));
	}

	
	
	var poDis=jQuery('#detail_discount').val();
	if(poDis=='Yes'){
		subTotal=parseFloat(subTotal);
		subTotal=Number(Math.round(subTotal+'e'+valRoundOff)+'e-'+valRoundOff);
		
	}else{
		var discountPer=getIfNullOrEmptyZero('discount_0');
		var discountVal=parseFloat(subTotal)*parseFloat(discountPer/100);
		subTotal=parseFloat(subTotal)-parseFloat(discountVal);
		subTotal=Number(Math.round(subTotal+'e'+valRoundOff)+'e-'+valRoundOff);
	}
	jQuery("#tot_val_aft_dis_val_lbl").html(subTotal);
	jQuery("#tot_val_aft_dis_val").val(subTotal);
	
	var edVal=getIfNullOrEmptyZero('1_val');
	var ecessVal=getIfNullOrEmptyZero('2_val');
	var shecessVal=getIfNullOrEmptyZero('3_val');
	var vatVal=getIfNullOrEmptyZero('4_val');
	var cstVal=getIfNullOrEmptyZero('5_val');
	var gstVal=getIfNullOrEmptyZero('7_val');
	var serviceVal=getIfNullOrEmptyZero('6_val');
	
	
//	var charge1=getIfNullOrEmptyZero('charge_fcy1');
//	var charge2=getIfNullOrEmptyZero('charge_fcy2');
//	var charge3=getIfNullOrEmptyZero('charge_fcy3');
	
	//GRN CHARGES
	var otherCharges=getIfNullOrEmptyZero('other_charges');
	var frieghtCharges=getIfNullOrEmptyZero('freight_charges');
	var servCharges=getIfNullOrEmptyZero('service_charges');
	var insurance=getIfNullOrEmptyZero('insurance');
	var packingCharges=getIfNullOrEmptyZero('packing_charges');
	var customsDuty=getIfNullOrEmptyZero('customs_duty');
	
	var grnCharges=parseFloat(otherCharges)+parseFloat(frieghtCharges)+parseFloat(servCharges)+parseFloat(insurance)+parseFloat(packingCharges)+parseFloat(customsDuty);
	
	netTotal=parseFloat(subTotal)+parseFloat(edVal)+parseFloat(ecessVal)+parseFloat(shecessVal)+parseFloat(vatVal)+parseFloat(cstVal)+parseFloat(gstVal)+parseFloat(serviceVal)+parseFloat(grnCharges);
//	parseFloat(charge1)+parseFloat(charge2)+parseFloat(charge3)+
	netTotal=Number(Math.round(netTotal+'e'+valRoundOff)+'e-'+valRoundOff);
	jQuery("#net_total").val(netTotal);
	jQuery("#net_total_lbl").html(netTotal);
	
	//calculateBalanceDue();
	
	
}






function calculateDisPer(){
	var subTotal=getIfNullOrEmptyZero("sub_total");
	var discountType=jQuery("#discount_type").val();
	var discount=getIfNullOrEmptyZero('discount_per');
	if(discountType=='val'){
		jQuery("#discount_0").val(parseFloat((discount*100)/subTotal));
	}
	else if(discountType=='per'){
		jQuery("#discount_0").val(parseFloat(discount));
	}
	calculateTax();
}


/*Reports */
function showIndentApprovalRegister(subdocumentId)
{
	document.getElementById('request_type').value="Normal";
	document.getElementById('servlet_name').value="IndentApprovalRegisterServlet";
	document.getElementById('callbackmethod_name').value="doDisplayIndentApprovalRegister";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?subdocument_id="+subdocumentId;	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}

function showAllReports()
{
	
	document.getElementById('servlet_name').value="CustomizeReportServlet";
	document.getElementById('callbackmethod_name').value="doShowCustomizeReport";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
function customizeReport(subdocumentId){
	
	document.getElementById('servlet_name').value="CustomizeReportServlet";
	document.getElementById('callbackmethod_name').value="doDisplayCustomizeReport";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?subdocument_id="+subdocumentId;	
	document.forms[0].method="POST";	
	document.forms[0].submit();
}
function editReport(subdocumentId){
	
	  document.getElementById('subdocument_id').value=subdocumentId;
	  
	  jQuery('#new_report_name').val(jQuery('#rept_name_'+subdocumentId).html());
	  jQuery('#new_report_description').val(jQuery('#rept_desc_'+subdocumentId).html());
	  
	  document.getElementById('open_report_modal').click();
}

function deleteReport(subdocumentId){
	  
	  bootbox.confirm("Are you sure you want to delete?", function(result) {
		  if(result){
			  document.getElementById('subdocument_id').value=subdocumentId;
			  document.getElementById('servlet_name').value="CustomizeReportServlet";
			  document.getElementById('callbackmethod_name').value="doDeleteCustomizeReport";
			  document.forms[0].action=contextpath+"/RequestHandlerServlet";
			  document.forms[0].method="POST";
			  document.forms[0].submit();
		  }
	  });
}
function validateNewReport(type){
	  reptName=$('#new_report_name');
	  if(reptName.val().length==0){
		  document.getElementById('err-msg').innerHTML="Please enter valid Report Name";
			showResponseMsg('err-msg','succ-msg');
			reptName.focus();	  
			return false;
	  }
	  else{
		  var rm=document.getElementById('report_mode').value;
		  if(rm=='n'){
			  saveCustomizeReport(type);			  
		  }
		  else if(rm=='e'){
			  document.getElementById('servlet_name').value="CustomizeReportServlet";
			  	document.getElementById('callbackmethod_name').value="doSaveCustomizeReportName";
			  	document.forms[0].action=contextpath+"/RequestHandlerServlet";
			  	document.forms[0].method="POST";
			  	document.forms[0].submit();
		  }
	  }
}
function saveCustomizeReport(type){
	  
	  var visiblecolumn = document.getElementById("visible_columns"); 
		var totalcolumn = document.getElementById("total_columns"); 
		//var iDivWidth = document.getElementById("divWidth").value;
		var iCount = visiblecolumn.options.length;
		var selectedFields="",totalColumns="";	
		if(iCount==0){		
			document.getElementById('error_message').innerHTML = localizedStrings.SELECT_AT_LEAST;
			return false;
		}
		for (var i=0;i<visiblecolumn.options.length;i++ )
		{
			
			selectedFields = selectedFields+visiblecolumn.options[i].value+",";
			totalColumns = totalColumns+visiblecolumn.options[i].value+"C1,";
		}
		for (var i=0;i<totalcolumn.options.length;i++ ) 
		{
			totalColumns = totalColumns+totalcolumn.options[i].value+"C2,"; 
		}
		selectedFields = selectedFields.substring(0, selectedFields.length-1);	
		totalColumns = totalColumns.substring(0, totalColumns.length-1);
		$('#selected_columns').val(selectedFields);
		$('#all_columns').val(totalColumns);
		
	document.getElementById('servlet_name').value="CustomizeReportServlet";
	document.getElementById('callbackmethod_name').value="doSaveCustomizeReport";
	document.forms[0].action=contextpath+"/RequestHandlerServlet?type="+type;
	document.forms[0].method="POST";
	document.forms[0].submit();
}
function closeCustomizeReport(){
	document.getElementById('servlet_name').value="CustomizeReportServlet";
	document.getElementById('callbackmethod_name').value="doCloseCustomizeReport";
	document.forms[0].action=contextpath+"/RequestHandlerServlet";
	document.forms[0].method="POST";
	document.forms[0].submit();
}


function formCriteria(rowNo,type){
	  if(validateCriteriaQuery(rowNo)){
	  
	  rowCnt=$('#row_count').val();
	  $('#row_ids').val($('#row_ids').val()+rowCnt+",");	  
	  
	  var condition=" and ";
	  if(type==2){
		  condition=" or ";
	  }
	  
	  	var objColumnName= document.getElementById('column_name1');
		var columnVal= objColumnName.options[objColumnName.selectedIndex].value;
		var columnText= objColumnName.options[objColumnName.selectedIndex].text;
		
		var cs1 = $("option:selected", objColumnName).attr("class");
		var objOperator= document.getElementById('operator1');
		var operatorVal= objOperator.options[objOperator.selectedIndex].value;
		var operatorText= objOperator.options[objOperator.selectedIndex].text;
		
		var value=$('#criteria_value'+rowNo).val();
		
		
		var objColumnNameVal= document.getElementById('column_name_val1');
		var columnNameVal= objColumnNameVal.options[objColumnNameVal.selectedIndex].value;
		var columnNameText= objColumnNameVal.options[objColumnNameVal.selectedIndex].text;
		var cs2 = $("option:selected", objColumnNameVal).attr("class");

		var havingQuery='';
		var dbQuery='';
		var query='';
		if(value==''){
			query=condition+columnText+" "+operatorText+" "+columnNameText+" ";
			value=columnNameVal;
			cs1=cs2;
		}
		else{
			query=condition+columnText+" "+operatorText+" "+value+" ";
		}
	  
		if(cs1=='datatype7'){
			havingQuery=condition+columnVal+" "+operatorVal.replace('_val',value);
		  }
		else{
			if(cs1=='datatype5'){
				dbQuery=condition+columnVal+" "+operatorVal.replace('_val',"convert(datetime,'"+value+"',105)");
			}
			else{
				if(operatorVal.indexOf('like')!=-1){
					dbQuery=condition+columnVal+" "+operatorVal.replace('_val',value);	
				}
				else if(operatorVal.indexOf('in')!=-1){
					
					var tVal=value.replace(',','squote,squote');
					dbQuery=condition+columnVal+" "+operatorVal.replace('_val',"squote"+tVal+"squote");
				}
				else{
					dbQuery=condition+columnVal+" "+operatorVal.replace('_val',"squote"+value+"squote");
				}
				
			}
		  }
		  
		$('#query_table tr:last').after('<tr id="q_table_row'+rowCnt+'"><td align="left"><a href="javascript:deleteQueryRow('+rowCnt+')" title="Remove"><span class="btn-label text-danger"><i class="glyphicon glyphicon-remove"></i></span></a></td><td>'+query+'<input type="hidden" id="db_query_'+rowCnt+'" name="db_query_'+rowCnt+'" value="'+dbQuery+'" /><input type="hidden" id="db_having_query_'+rowCnt+'" name="db_having_query_'+rowCnt+'" value="'+havingQuery+'" /><input type="hidden" id="query_'+rowCnt+'" name="query_'+rowCnt+'" value="'+query+'" /></td></tr>');
	  
		rowCnt=parseInt(rowCnt)+1;
		
		$('#row_count').val(rowCnt);
		$('#column_name1').val("-1");
		$('#operator1').val("-1");
		$('#criteria_value1').val("");
		$('#column_name_val1').val("-1");
		
	  }
//	  $('#row_ids').val($('#row_ids').val()+rowNo+',');
//	  var rowIds=$('#row_ids').val();
//	  //vRowIds=rowIds.length-1;
//	  var arRowId=new Array();
//	  arRowId=rowIds.split(',');
//	  alert(arRowId.length);
//	  var condition="";
//	  if(rowNo>1){
//		  condition=" and ";
//		  if(type==2){
//			  condition=" or ";
//		  }
//	  }	  
//	  var objColumnName= document.getElementById('column_name'+rowNo);
//		var columnVal= objColumnName.options[objColumnName.selectedIndex].value;
//		var columnText= objColumnName.options[objColumnName.selectedIndex].text;
//		
//		var cs1 = $("option:selected", objColumnName).attr("class");
//		var objOperator= document.getElementById('operator'+rowNo);
//		var operatorVal= objOperator.options[objOperator.selectedIndex].value;
//		var operatorText= objOperator.options[objOperator.selectedIndex].text;
//	  
//		alert('column :'+columnVal+' operator :'+operatorVal+' condition :'+condition);
//		alert('column :'+columnText+' operator :'+operatorText+' condition :'+condition);
//		
//	  var value=$('#criteria_value'+rowNo).val();
//	  var havingQuery='';
//	  var dbQuery='';
//	  if(cs1=='datatype7'){
//		havingQuery=condition+columnVal+" "+operatorVal.replace('_val',"squote"+value+"squote");
//	  }
//	  else{
//	  dbQuery=condition+columnVal+" "+operatorVal.replace('_val',"squote"+value+"squote");
//	  }
//	  var query=condition+columnText+" "+operatorText+" "+value+" ";
//
//	  var tmpQuery="<input type='hidden' id='query"+rowNo+"' name='query"+rowNo+"' value='"+query+"'/><input type='hidden' id='db_query"+rowNo+"' name='db_query"+rowNo+"' value='"+dbQuery+"'/><input type='hidden' id='db_having_query"+rowNo+"' name='db_having_query"+rowNo+"' value='"+havingQuery+"'/>";
//	  $('#tmp_query').append(tmpQuery);
//	  
//	  
//	  $('#lbl_query').html("");
//	  $('#final_query').val("");
//	  $('#final_dbquery').val("");
//	  $('#final_dbhavingquery').val("");
//	  alert('after clear');
//	  for(var i=0;i<arRowId.length;i++){
//		  if(arRowId[i]!=''){
//			  alert('#query'+arRowId[i]);
//			  $('#lbl_query').append($('#query'+arRowId[i]).val());
//			  $('#final_query').val($('#final_query').val()+$('#query'+arRowId[i]).val());
//			  $('#final_dbquery').val($('#final_dbquery').val()+$('#db_query'+arRowId[i]).val());
//			  $('#final_dbhavingquery').val($('#final_dbhavingquery').val()+$('#db_having_query'+arRowId[i]).val());
//		  }
//	  }
//	  
//	  $('#btn-col'+rowNo).html("<input class='btn btn-primary' type='button' value='Delete' onclick='deleteCriteriaRow("+rowNo+")' />");
//	  
//	  rowNo=parseInt(rowNo)+1;
//	  
//	  var subdocumentId=$('#subdocument_id').val();
	  
	  
	  
//	  var url =contextpath+"/RequestHandlerServlet";
//		var request = jQuery.ajax({
//		url: url,
//		type: "POST",
//		data: {servlet_name : 'CustomizeReportServlet' , callbackmethod_name : 'formPage',subdocument_id:subdocumentId,criteria_row_no:rowNo},
//		dataType: "html"
//		});
//		request.done(function(msg) {
//		
//			jQuery("#criteria_row_content").append( msg );
//			
//		});
//		request.fail(function(jqXHR, textStatus) {
//		alert( "Request failed: " + textStatus );
//		
//		});  
}


function validateCriteriaQuery(rowNo){
	  var objColumnName= document.getElementById('column_name1');
		var columnVal= objColumnName.options[objColumnName.selectedIndex].value;
		
		var objOperator= document.getElementById('operator1');
		var operatorVal= objOperator.options[objOperator.selectedIndex].value;
		
		var val=document.getElementById('criteria_value'+rowNo);
		
		var objColumnNameVal= document.getElementById('column_name_val1');
		var columnNameVal= objColumnNameVal.options[objColumnNameVal.selectedIndex].value;
		
		if(columnVal=='-1'){
			document.getElementById('err-msg').innerHTML="Please select valid Column.";
			showResponseMsg('err-msg','succ-msg');
			objColumnName.focus();
			return false;
		}
		else if(operatorVal=='-1'){
			document.getElementById('err-msg').innerHTML="Please select valid Operator.";
			showResponseMsg('err-msg','succ-msg');
			objOperator.focus();
			return false;
		}
		else if(val.value=='' && columnNameVal=='-1'){
			document.getElementById('err-msg').innerHTML="Please enter valid Value or select Column";
			showResponseMsg('err-msg','succ-msg');
			val.focus();
			return false;
		}
		else{
		return true;
		}
}



function deleteQueryRow(rowNo){
	  $('table#query_table #q_table_row'+rowNo).remove();
	  var rowIds=$('#row_ids').val();
	  $('#row_ids').val(rowIds.replace(rowNo+",",""));
}


function getItemData(itmId,detId,trTag){
	
	 var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: {
			servlet_name : 'IndentServlet', callbackmethod_name : 'doGetItemData',
			item_id:itmId,tr_tag:trTag
			},
		dataType: "xml",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + jqXHR);
		},
		success: function(xml) {
			
			jQuery(xml).find('item_data').each(function(){
				 
				var szeApp=jQuery(this).find('szeApp').text();
				
				if(detId==0){
			
					if(szeApp==0){
						jQuery("#size_range_0").prop("readonly", true);	
						jQuery('#size_range_0').attr('tabindex', "-1");
						$('#aind_form').bootstrapValidator('revalidateField', 'size_range_0');
						 
					}else if(szeApp==1){
						jQuery("#size_range_0").prop("readonly", false);
						jQuery('#size_range_0').removeAttr( "tabindex" );
						$('#aind_form').bootstrapValidator('revalidateField', 'size_range_0');
					}
				}
				else{
					if(szeApp==0){
						jQuery("#size_range_S_"+detId).prop("readonly", true);
						jQuery('#size_range_S_'+detId).attr('tabindex', "-1");
						$('#aind_form').bootstrapValidator('revalidateField', 'size_range_S_'+detId);
					}else if(szeApp==1){
						jQuery("#size_range_S_"+detId).prop("readonly", false);
						jQuery('#size_range_S_'+detId).removeAttr( "tabindex" );
						$('#aind_form').bootstrapValidator('revalidateField', 'size_range_S_'+detId);
					}
				}
				
			});
		}
	});
}