/**
 * 
 */
/*
var first=new Array();
var second=new Array();
var selectedRow = null ;
var isExplorer =  (navigator.appName.indexOf('Explorer') == -1) ? false : true;

function selectRow( obj ){
    if ( selectedRow )
         selectedRow.style.backgroundColor= 'white';
         obj.style.backgroundColor= 'red';
         selectedRow = obj;
}

function move( direction ){

try{
    
	if ( !selectedRow )
         return;
    
    var newPosition;
    
    if (direction == -1){	 
	     oldPosition = Number(selectedRow.getAttribute('pos') );
		 newPosition = Number(selectedRow.getAttribute('pos') -1);
    }else{
		 oldPosition = Number(selectedRow.getAttribute('pos'));
		 newPosition = oldPosition+1;
    }
   
    var actual = document.getElementById('pos'+ oldPosition);
    var target = document.getElementById( 'pos' +  newPosition);
   
    if (!target)
         return;
	
 
	 for(var i=0;i<target.cells.length;i++){
	    var targetCell = target.cells[i];
	    var actualCell = actual.cells[i];
	    var targetCellData = targetCell.innerHTML;
	    var actualCellData = actualCell.innerHTML;
	    targetCell.innerHTML = actualCellData;
	    actualCell.innerHTML = targetCellData;  
	 }
	 
	 actual.style.backgroundColor= 'white';
	 target.style.backgroundColor= 'red'; 
	 selectedRow=target;
	 
	}catch( e){
		alert(e);
	}
}  

function saveColumnPreference(field){
	var iCount =0;
	var selectedFields="",totalColumns="";	
	for (i = 0; i < field.length; i++){
		if(field[i].checked){
			selectedFields = selectedFields+field[i].value+",";
			totalColumns = totalColumns+field[i].value+"C1,";
			iCount++;
		}else{
			totalColumns = totalColumns+field[i].value+"C2,";
		}
	}
	if(iCount==0){
		//alert('Please select atleast one column');
		document.getElementById('error_message').innerHTML = localizedStrings.SELECT_AT_LEAST;
		return false;
	}
	selectedFields = selectedFields.substring(0, selectedFields.length-1);	
	totalColumns = totalColumns.substring(0, totalColumns.length-1);	
	window.opener.getData(selectedFields,totalColumns);
	window.close();	
}

function checkAll(mainchk){
	
	var field = document.getElementsByTagName('input');	
	for(var i=0;i<field.length;i++){
	if(field[i].type=="checkbox" && field[i].name!="chkmain")
		field[i].checked=mainchk.checked;
	}
}

function saveSettings(){
	var visiblecolumn = document.getElementById("visible_columns"); 
	var totalcolumn = document.getElementById("total_columns"); 
	var iDivWidth = document.getElementById("divWidth").value;
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
	
	window.opener.getData(selectedFields,totalColumns,iDivWidth);
	window.close();	
	
}
*/
/*newly added*/

function saveSettings(){
	var visiblecolumn = document.getElementById("visible_columns"); 
	var totalcolumn = document.getElementById("total_columns"); 
	//var iDivWidth = document.getElementById("divWidth").value;
	var iCount = visiblecolumn.options.length;
	var selectedFields="",totalColumns="";	
	if(iCount==0){		
		document.getElementById('error_message').innerHTML = localizedPreferenceStrings.SELECT_AT_LEAST;
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

//	getData(selectedFields,totalColumns);
	getData(totalColumns,selectedFields);
	
	window.close();	
	
}

//newly added ends
//function addToVisible(){
//	alert('column Add');
//	
//	with(document.forms[0])
//	{
//		var k=0;
//		if(total_columns.options.length==0)
//		{
//			document.getElementById('error_message').innerHTML = localizedStrings.NO_COLUMNS;
//			return false;
//		}
//		for (var i=0;i<total_columns.options.length;i++ )
//		{	
//			if(total_columns.options[i].selected)
//			{
//				k=k+1;
//				visible_columns.options[visible_columns.options.length]=new Option(total_columns.options[i].text, total_columns.options[i].value);
//				total_columns.options[i--]=null;
//			}
//		}
//		if(k==0)
//		{
//			document.getElementById('error_message').innerHTML = localizedStrings.SLCT_COLUMN_CHANGE_ORDER;
//			return false;
//		}
//	}
//	
//}

function addToHidden(){
	
	with (document.forms[0])
	{
		var k=0;
		if(visible_columns.options.length==0)
		{
			document.getElementById('error_message').innerHTML = localizedPreferenceStrings.NO_COLUMNS;
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
			document.getElementById('error_message').innerHTML = localizedPreferenceStrings.SLCT_COLUMN_CHANGE_ORDER;
			return false;
		}
	}
	
}

function moveToUp() {
	
	var column = document.getElementById("visible_columns"); 
	if(column.selectedIndex==-1){
		document.getElementById('error_message').innerHTML = localizedPreferenceStrings.SLCT_COLUMN_CHANGE_ORDER;
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
		document.getElementById('error_message').innerHTML = localizedPreferenceStrings.SLCT_COLUMN_CHANGE_ORDER;
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





