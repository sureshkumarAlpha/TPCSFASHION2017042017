<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
function calculateTax(){
	var tg;
	var qty;
	var price;
	var discount;
	var detIds=document.getElementById('det_ids').value;
	
	discount=document.getElementById('discount_percent_0').value;
	 
		var tgVal=0;
		if(document.getElementById('tax_group_id_0')!=null){
			tg=document.getElementById('tax_group_id_0');
			var tgVal=tg.options[tg.selectedIndex].value;	
		}
		qty=0;
		if(document.getElementById('qty_0')!=null){
			qty=document.getElementById('qty_0').value;	
		}
		price=0;
		if(document.getElementById('price_fcy_0')!=null){
			price=document.getElementById('price_fcy_0').value;	
		}
	 
	
	if(tgVal!=-1 <c:forEach var="detId" items="${det_id_list}">|| jQuery("#tax_group_id_S_${detId}" ).val()!=-1 </c:forEach>){
		
	 var url =contextpath+"/RequestHandlerServlet";
		jQuery.ajax({
		url: url,
		type: "POST",
		data: { servlet_name : 'CommonUtilServlet', callbackmethod_name : 'doCalculateTax',tax_group_id_0:tgVal,qty_0:qty,price_fcy_0:price,discount_percent:discount,
			 <c:forEach var="detId" items="${det_id_list}">
			 tax_group_id_${detId}:jQuery("#tax_group_id_S_${detId}" ).val(),
			 qty_${detId}:document.getElementById('qty_S_${detId}').value,
			 price_fcy_${detId}:document.getElementById('price_fcy_S_${detId}').value,
			 discount_percent_${detId}:getIfNullOrEmptyZero('discount_percent_S_${detId}'),
			</c:forEach>
			det_ids:detIds
			},
		dataType: "xml",
		error: function(jqXHR, textStatus, errorThrown) {
			alert( "Request failed: " + jqXHR);
		},
		success: function(xml) {
			
			jQuery('#tax_cal_inner').html("");
			
			jQuery(xml).find('tax_detail').each(function(){
				var taxId=jQuery(this).find('tax_id').text();
				var taxName=jQuery(this).find('tax_disp_name').text();
				//var taxPer=jQuery(this).find('tax_percent').text();
				var taxVal=parseFloat(jQuery(this).find('tax_val').text());
				
				taxVal=Number(Math.round(taxVal+'e'+${sessionScope.user_info.locatonConfigMap['RoundoffValue']})+'e-'+${sessionScope.user_info.locatonConfigMap['RoundoffValue']});
				
				jQuery("#tax_cal_inner").append(" <div class='tax-row'> <div class='col-sm-12'>" +
						" <label class='col-sm-9 col-xs-6' >"+taxName+" :</label>" +
						" <div class='col-sm-3 col-xs-6'><label>"+(taxVal)+"</label><input class='form-control text-right' id='"+taxId+"_val' name='"+taxId+"_val' type='hidden' value='"+taxVal+"'/></div>" +
						" </div>"+ 
						" </div>");
				
			});				
			calculateNetTotal(${sessionScope.user_info.locatonConfigMap['RoundoffQty']},${sessionScope.user_info.locatonConfigMap['RoundoffRate']},${sessionScope.user_info.locatonConfigMap['RoundoffValue']});	
		}
	});
	}
	else{
		jQuery("#tax_cal_inner").html("");
	}
	
	calculateNetTotal(${sessionScope.user_info.locatonConfigMap['RoundoffQty']},${sessionScope.user_info.locatonConfigMap['RoundoffRate']},${sessionScope.user_info.locatonConfigMap['RoundoffValue']});
}


//to format textbox values to 2 descimal


$( function() {
    //$('#charge_fcy1').textValueFormat();
    //$('#charge_fcy2').textValueFormat();
    //$('#charge_fcy3').textValueFormat();
    //$('#value_before_tax_fcy_0').textValueFormat();
    //<c:forEach var="detId" items="${det_id_list}">
    //$('#value_before_tax_fcy_S_${detId}').textValueFormat();
	   //</c:forEach>
    
});



(function($) {
    $.fn.textValueFormat = function() {
        this.each( function( i ) {
                if( isNaN( parseFloat( this.value ) ) ) return;
                this.value = Number(Math.round(this.value+'e'+${sessionScope.user_info.locatonConfigMap['RoundoffValue']})+'e-'+${sessionScope.user_info.locatonConfigMap['RoundoffValue']});//parseFloat(this.value).toFixed(2);
        });
        return this; 
    }
})( jQuery );

</script>