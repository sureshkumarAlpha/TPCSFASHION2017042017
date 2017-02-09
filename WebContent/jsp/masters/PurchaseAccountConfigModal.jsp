

<!-- --------------------------Purchase Account Master------------------------------ -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
@media screen and (min-width: 992px) {
#validate-form-purchase_acc .modal-lg {
    width: 1200px;
}
}
</style>
<form id="validate-form-purchase_acc">
	<div class="form-horizontal trans-form">
		<div class="modal fade" id="purchaseAccountModal" role="dialog" >
			<div class="modal-dialog modal-lg">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Purchase Account Configuration</h4>
					</div>
					<div class="modal-body" style="max-height:450px;    overflow: auto;">
					
					<div id="purchase_account_1_0_content">
					<label> I will select purchase account in every Transaction manually</label>
					</div>
					
					<div id="purchase_account_2_1_content">
					
						<!--   <div class='row'>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="purchase_account">purchase account head</label>
								<div class="col-sm-8">
									<div class="required-field-block">
										<input class="form-control" id="purchase_account" name="purchase_account"
											  type="text" maxlength="50" placeholder="Type and select Account" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						
						<div class='row'>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="ed_account">ED account head </label>
								<div class="col-sm-8">
									<div class="required-field-block">
										<input class="form-control" id="ed_account" name="ed_account"
											  type="text" maxlength="50" placeholder="Type and select Account" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class='row'>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="ecess_account">Ecess account head</label>
								<div class="col-sm-8">
									<div class="required-field-block">
										<input class="form-control" id="ecess_account" name="ecess_account"
											  type="text" maxlength="50" placeholder="Type and select Account" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class='row'>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="shecess_account">SH Ecess account head</label>
								<div class="col-sm-8">
									<div class="required-field-block">
										<input class="form-control" id="shecess_account" name="shecess_account"
											  type="text" maxlength="50" placeholder="Type and select Account" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class='row'>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="vat_account">VAT account head</label>
								<div class="col-sm-8">
									<div class="required-field-block">
										<input class="form-control" id="vat_account" name="vat_account"
											  type="text" maxlength="50" placeholder="Type and select Account" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class='row'>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="service_tax_account">Service Tax account head</label>
								<div class="col-sm-8">
									<div class="required-field-block">
										<input class="form-control" id="service_tax_account" name="service_tax_account"
											  type="text" maxlength="50" placeholder="Type and select Account" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class='row'>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="gst_account">GST account head</label>
								<div class="col-sm-8">
									<div class="required-field-block">
										<input class="form-control" id="gst_account" name="gst_account"
											  type="text" maxlength="50" placeholder="Type and select Account" />
										<div class="required-icon">
											<div class="text">*</div>
										</div>
									</div>
								</div>
							</div>
						</div>   -->
						</div>
						<div id="purchase_account_3_1_content">
						 <!-- <div class="table-responsive">
            
										<table class="table table-bordered table-condensed" id="addrow_table">
											<thead>
												<tr class="header">
													<th>Product Group</th>
													<th>Code</th>
													<th>purchase Account</th>
													<th>ED Account</th>
													<th>Ecess Account</th>
													<th>SH Ecess Account</th>
													<th>VAT Account</th>
													<th>Service Tax Account</th>
													<th>GST Account</th>
												</tr>
											</thead>
											<tbody>
												<tr class="datarow">
													<td><input class="form-control" id="3_1_group_name" name="3_1_group_name" type="text" readonly></td>
													<td><input class="form-control" id="3_1_group_code" name="3_1_group_code" type="text" readonly></td>
													<td><input class="form-control" id="3_1_purchase_account" name="3_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="3_1_ed_account" name="3_1_ed_account" type="text"></td>
													<td><input class="form-control" id="3_1_ecess_account" name="3_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="3_1_shecess_account" name="3_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="3_1_vat_account" name="3_1_vat_account" type="text"></td>
													<td><input class="form-control" id="3_1_service_tax_account" name="3_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="3_1_gst_account" name="3_1_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td><input class="form-control" id="3_1_group_name" name="3_1_group_name" type="text" readonly></td>
													<td><input class="form-control" id="3_1_group_code" name="3_1_group_code" type="text" readonly></td>
													<td><input class="form-control" id="3_1_purchase_account" name="3_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="3_1_ed_account" name="3_1_ed_account" type="text"></td>
													<td><input class="form-control" id="3_1_ecess_account" name="3_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="3_1_shecess_account" name="3_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="3_1_vat_account" name="3_1_vat_account" type="text"></td>
													<td><input class="form-control" id="3_1_service_tax_account" name="3_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="3_1_gst_account" name="3_1_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td><input class="form-control" id="3_1_group_name" name="3_1_group_name" type="text" readonly></td>
													<td><input class="form-control" id="3_1_group_code" name="3_1_group_code" type="text" readonly></td>
													<td><input class="form-control" id="3_1_purchase_account" name="3_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="3_1_ed_account" name="3_1_ed_account" type="text"></td>
													<td><input class="form-control" id="3_1_ecess_account" name="3_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="3_1_shecess_account" name="3_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="3_1_vat_account" name="3_1_vat_account" type="text"></td>
													<td><input class="form-control" id="3_1_service_tax_account" name="3_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="3_1_gst_account" name="3_1_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td><input class="form-control" id="3_1_group_name" name="3_1_group_name" type="text" readonly></td>
													<td><input class="form-control" id="3_1_group_code" name="3_1_group_code" type="text" readonly></td>
													<td><input class="form-control" id="3_1_purchase_account" name="3_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="3_1_ed_account" name="3_1_ed_account" type="text"></td>
													<td><input class="form-control" id="3_1_ecess_account" name="3_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="3_1_shecess_account" name="3_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="3_1_vat_account" name="3_1_vat_account" type="text"></td>
													<td><input class="form-control" id="3_1_service_tax_account" name="3_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="3_1_gst_account" name="3_1_gst_account" type="text"></td>
													
												</tr>
											</tbody>
										</table>
										
										</div>  --> 
						</div>
						
						<div id="purchase_account_3_2_content">
					 <!-- <div class="table-responsive">
            
										<table class="table table-bordered table-condensed" id="addrow_table">
											<thead>
												<tr class="header">
													<th>Product </th>
													<th>Code</th>
													<th>purchase Account</th>
													<th>ED Account</th>
													<th>Ecess Account</th>
													<th>SH Ecess Account</th>
													<th>VAT Account</th>
													<th>Service Tax Account</th>
													<th>GST Account</th>
												</tr>
											</thead>
											<tbody>
												<tr class="datarow">
													<td><input class="form-control" id="3_2_product_name" name="3_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="3_2_product_code" name="3_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="3_2_purchase_account" name="3_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="3_2_ed_account" name="3_2_ed_account" type="text"></td>
													<td><input class="form-control" id="3_2_ecess_account" name="3_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="3_2_shecess_account" name="3_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="3_2_vat_account" name="3_2_vat_account" type="text"></td>
													<td><input class="form-control" id="3_2_service_tax_account" name="3_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="3_2_gst_account" name="3_2_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td><input class="form-control" id="3_2_product_name" name="3_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="3_2_product_code" name="3_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="3_2_purchase_account" name="3_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="3_2_ed_account" name="3_2_ed_account" type="text"></td>
													<td><input class="form-control" id="3_2_ecess_account" name="3_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="3_2_shecess_account" name="3_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="3_2_vat_account" name="3_2_vat_account" type="text"></td>
													<td><input class="form-control" id="3_2_service_tax_account" name="3_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="3_2_gst_account" name="3_2_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td><input class="form-control" id="3_2_product_name" name="3_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="3_2_product_code" name="3_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="3_2_purchase_account" name="3_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="3_2_ed_account" name="3_2_ed_account" type="text"></td>
													<td><input class="form-control" id="3_2_ecess_account" name="3_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="3_2_shecess_account" name="3_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="3_2_vat_account" name="3_2_vat_account" type="text"></td>
													<td><input class="form-control" id="3_2_service_tax_account" name="3_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="3_2_gst_account" name="3_2_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td><input class="form-control" id="3_2_product_name" name="3_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="3_2_product_code" name="3_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="3_2_purchase_account" name="3_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="3_2_ed_account" name="3_2_ed_account" type="text"></td>
													<td><input class="form-control" id="3_2_ecess_account" name="3_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="3_2_shecess_account" name="3_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="3_2_vat_account" name="3_2_vat_account" type="text"></td>
													<td><input class="form-control" id="3_2_service_tax_account" name="3_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="3_2_gst_account" name="3_2_gst_account" type="text"></td>
													
												</tr>
											</tbody>
										</table>
										
										</div>   -->
						</div>
						
						<div id="purchase_account_4_1_content">
					  <!-- <div class="table-responsive">
            
										<table class="table table-bordered table-condensed" id="addrow_table">
											<thead>
												<tr class="header">
													<th>Tax Group</th>
													<th>purchase Account</th>
													<th>ED Account</th>
													<th>Ecess Account</th>
													<th>SH Ecess Account</th>
													<th>VAT Account</th>
													<th>Service Tax Account</th>
													<th>GST Account</th>
												</tr>
											</thead>
											<tbody>
												<tr class="datarow">
													<td><input class="form-control" id="4_1_tax_group_name" name="4_1_tax_group_name" type="text" readonly></td>
													<td><input class="form-control" id="4_1_purchase_account" name="4_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="4_1_ed_account" name="4_1_ed_account" type="text"></td>
													<td><input class="form-control" id="4_1_ecess_account" name="4_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="4_1_shecess_account" name="4_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="4_1_vat_account" name="4_1_vat_account" type="text"></td>
													<td><input class="form-control" id="4_1_service_tax_account" name="4_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="4_1_gst_account" name="4_1_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td><input class="form-control" id="4_1_tax_group_name" name="4_1_tax_group_name" type="text" readonly></td>
													<td><input class="form-control" id="4_1_purchase_account" name="4_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="4_1_ed_account" name="4_1_ed_account" type="text"></td>
													<td><input class="form-control" id="4_1_ecess_account" name="4_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="4_1_shecess_account" name="4_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="4_1_vat_account" name="4_1_vat_account" type="text"></td>
													<td><input class="form-control" id="4_1_service_tax_account" name="4_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="4_1_gst_account" name="4_1_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td><input class="form-control" id="4_1_tax_group_name" name="4_1_tax_group_name" type="text" readonly></td>
													<td><input class="form-control" id="4_1_purchase_account" name="4_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="4_1_ed_account" name="4_1_ed_account" type="text"></td>
													<td><input class="form-control" id="4_1_ecess_account" name="4_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="4_1_shecess_account" name="4_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="4_1_vat_account" name="4_1_vat_account" type="text"></td>
													<td><input class="form-control" id="4_1_service_tax_account" name="4_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="4_1_gst_account" name="4_1_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td><input class="form-control" id="4_1_tax_group_name" name="4_1_tax_group_name" type="text" readonly></td>
													<td><input class="form-control" id="4_1_purchase_account" name="4_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="4_1_ed_account" name="4_1_ed_account" type="text"></td>
													<td><input class="form-control" id="4_1_ecess_account" name="4_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="4_1_shecess_account" name="4_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="4_1_vat_account" name="4_1_vat_account" type="text"></td>
													<td><input class="form-control" id="4_1_service_tax_account" name="4_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="4_1_gst_account" name="4_1_gst_account" type="text"></td>
													
												</tr>
											</tbody>
										</table>
										
										</div>  -->
						</div>
						
						<div id="purchase_account_4_2_content">
					  <!-- <div class="table-responsive">
            
										<table class="table table-bordered table-condensed" id="addrow_table">
											<thead>
												<tr class="header">
													<th>Tax Group</th>
													<th>Product</th>
													<th>Code</th>
													<th>purchase Account</th>
													<th>ED Account</th>
													<th>Ecess Account</th>
													<th>SH Ecess Account</th>
													<th>VAT Account</th>
													<th>Service Tax Account</th>
													<th>GST Account</th>
												</tr>
											</thead>
											<tbody>
												<tr class="datarow">
													<td>
													<select class="form-control" id="4_2_tax_group" name="4_2_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="4_2_product_name" name="4_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="4_2_product_code" name="4_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="4_2_purchase_account" name="4_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="4_2_ed_account" name="4_2_ed_account" type="text"></td>
													<td><input class="form-control" id="4_2_ecess_account" name="4_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="4_2_shecess_account" name="4_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="4_2_vat_account" name="4_2_vat_account" type="text"></td>
													<td><input class="form-control" id="4_2_service_tax_account" name="4_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="4_2_gst_account" name="4_2_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td>
													<select class="form-control" id="4_2_tax_group" name="4_2_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="4_2_product_name" name="4_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="4_2_product_code" name="4_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="4_2_purchase_account" name="4_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="4_2_ed_account" name="4_2_ed_account" type="text"></td>
													<td><input class="form-control" id="4_2_ecess_account" name="4_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="4_2_shecess_account" name="4_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="4_2_vat_account" name="4_2_vat_account" type="text"></td>
													<td><input class="form-control" id="4_2_service_tax_account" name="4_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="4_2_gst_account" name="4_2_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td>
													<select class="form-control" id="4_2_tax_group" name="4_2_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="4_2_product_name" name="4_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="4_2_product_code" name="4_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="4_2_purchase_account" name="4_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="4_2_ed_account" name="4_2_ed_account" type="text"></td>
													<td><input class="form-control" id="4_2_ecess_account" name="4_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="4_2_shecess_account" name="4_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="4_2_vat_account" name="4_2_vat_account" type="text"></td>
													<td><input class="form-control" id="4_2_service_tax_account" name="4_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="4_2_gst_account" name="4_2_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td>
													<select class="form-control" id="4_2_tax_group" name="4_2_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="4_2_product_name" name="4_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="4_2_product_code" name="4_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="4_2_purchase_account" name="4_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="4_2_ed_account" name="4_2_ed_account" type="text"></td>
													<td><input class="form-control" id="4_2_ecess_account" name="4_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="4_2_shecess_account" name="4_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="4_2_vat_account" name="4_2_vat_account" type="text"></td>
													<td><input class="form-control" id="4_2_service_tax_account" name="4_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="4_2_gst_account" name="4_2_gst_account" type="text"></td>
													
												</tr>
											</tbody>
										</table>
										
										</div>   -->
						</div>
						
						<div id="purchase_account_5_1_content">
						<!-- <div class="table-responsive">
            
										<table class="table table-bordered table-condensed" id="addrow_table">
											<thead>
												<tr class="header">
													<th>Type of purchase</th>
													<th>purchase Account</th>
													<th>ED Account</th>
													<th>Ecess Account</th>
													<th>SH Ecess Account</th>
													<th>VAT Account</th>
													<th>Service Tax Account</th>
													<th>GST Account</th>
												</tr>
											</thead>
											<tbody>
												<tr class="datarow">
													<td>
													<select class="form-control" id="5_1_tax_group" name="5_1_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="5_1_purchase_account" name="5_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="5_1_ed_account" name="5_1_ed_account" type="text"></td>
													<td><input class="form-control" id="5_1_ecess_account" name="5_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="5_1_shecess_account" name="5_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="5_1_vat_account" name="5_1_vat_account" type="text"></td>
													<td><input class="form-control" id="5_1_service_tax_account" name="5_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="5_1_gst_account" name="5_1_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td>
													<select class="form-control" id="5_1_tax_group" name="5_1_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="5_1_purchase_account" name="5_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="5_1_ed_account" name="5_1_ed_account" type="text"></td>
													<td><input class="form-control" id="5_1_ecess_account" name="5_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="5_1_shecess_account" name="5_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="5_1_vat_account" name="5_1_vat_account" type="text"></td>
													<td><input class="form-control" id="5_1_service_tax_account" name="5_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="5_1_gst_account" name="5_1_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td>
													<select class="form-control" id="5_1_tax_group" name="5_1_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="5_1_purchase_account" name="5_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="5_1_ed_account" name="5_1_ed_account" type="text"></td>
													<td><input class="form-control" id="5_1_ecess_account" name="5_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="5_1_shecess_account" name="5_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="5_1_vat_account" name="5_1_vat_account" type="text"></td>
													<td><input class="form-control" id="5_1_service_tax_account" name="5_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="5_1_gst_account" name="5_1_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td>
													<select class="form-control" id="5_1_tax_group" name="5_1_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="5_1_purchase_account" name="5_1_purchase_account" type="text"></td>
													<td><input class="form-control" id="5_1_ed_account" name="5_1_ed_account" type="text"></td>
													<td><input class="form-control" id="5_1_ecess_account" name="5_1_ecess_account" type="text"></td>
													<td><input class="form-control" id="5_1_shecess_account" name="5_1_shecess_account" type="text"></td>
													<td><input class="form-control" id="5_1_vat_account" name="5_1_vat_account" type="text"></td>
													<td><input class="form-control" id="5_1_service_tax_account" name="5_1_service_tax_account" type="text"></td>
													<td><input class="form-control" id="5_1_gst_account" name="5_1_gst_account" type="text"></td>
													
												</tr>
											</tbody>
										</table>
										
										</div> -->
						</div>
						
						<div id="purchase_account_5_2_content">
						<!-- <div class="table-responsive">
            
										<table class="table table-bordered table-condensed" id="addrow_table">
											<thead>
												<tr class="header">
													<th>Type of purchase</th>
													<th>Product</th>
													<th>Code</th>
													<th>purchase Account</th>
													<th>ED Account</th>
													<th>Ecess Account</th>
													<th>SH Ecess Account</th>
													<th>VAT Account</th>
													<th>Service Tax Account</th>
													<th>GST Account</th>
												</tr>
											</thead>
											<tbody>
												<tr class="datarow">
													<td>
													<select class="form-control" id="5_1_tax_group" name="5_1_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="5_2_product_name" name="5_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="5_2_product_code" name="5_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="5_2_purchase_account" name="5_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="5_2_ed_account" name="5_2_ed_account" type="text"></td>
													<td><input class="form-control" id="5_2_ecess_account" name="5_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="5_2_shecess_account" name="5_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="5_2_vat_account" name="5_2_vat_account" type="text"></td>
													<td><input class="form-control" id="5_2_service_tax_account" name="5_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="5_2_gst_account" name="5_2_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td>
													<select class="form-control" id="5_1_tax_group" name="5_1_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="5_2_product_name" name="5_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="5_2_product_code" name="5_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="5_2_purchase_account" name="5_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="5_2_ed_account" name="5_2_ed_account" type="text"></td>
													<td><input class="form-control" id="5_2_ecess_account" name="5_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="5_2_shecess_account" name="5_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="5_2_vat_account" name="5_2_vat_account" type="text"></td>
													<td><input class="form-control" id="5_2_service_tax_account" name="5_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="5_2_gst_account" name="5_2_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td>
													<select class="form-control" id="5_1_tax_group" name="5_1_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="5_2_product_name" name="5_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="5_2_product_code" name="5_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="5_2_purchase_account" name="5_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="5_2_ed_account" name="5_2_ed_account" type="text"></td>
													<td><input class="form-control" id="5_2_ecess_account" name="5_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="5_2_shecess_account" name="5_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="5_2_vat_account" name="5_2_vat_account" type="text"></td>
													<td><input class="form-control" id="5_2_service_tax_account" name="5_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="5_2_gst_account" name="5_2_gst_account" type="text"></td>
													
												</tr>
												<tr class="datarow">
													<td>
													<select class="form-control" id="5_1_tax_group" name="5_1_tax_group"/>
													<option>&lt;Select&gt;</option>
													<option>Tax Group 1</option>
													<option>Tax Group 2</option>
													</select>
													</td>
													<td><input class="form-control" id="5_2_product_name" name="5_2_product_name" type="text" readonly></td>
													<td><input class="form-control" id="5_2_product_code" name="5_2_product_code" type="text" readonly></td>
													<td><input class="form-control" id="5_2_purchase_account" name="5_2_purchase_account" type="text"></td>
													<td><input class="form-control" id="5_2_ed_account" name="5_2_ed_account" type="text"></td>
													<td><input class="form-control" id="5_2_ecess_account" name="5_2_ecess_account" type="text"></td>
													<td><input class="form-control" id="5_2_shecess_account" name="5_2_shecess_account" type="text"></td>
													<td><input class="form-control" id="5_2_vat_account" name="5_2_vat_account" type="text"></td>
													<td><input class="form-control" id="5_2_service_tax_account" name="5_2_service_tax_account" type="text"></td>
													<td><input class="form-control" id="5_2_gst_account" name="5_2_gst_account" type="text"></td>
													
												</tr>
											</tbody>
										</table>
										
										</div> -->
						</div>
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" onclick="savePurchaseAccount()">Save Configuration</button>
						<button type="button" class="btn btn-danger" id="purchase_close" data-dismiss="modal">Close</button>
						<input type="submit" name="validation_sacc_btn" id="validation_sacc_btn" style="display: none;" />
						
						<input type="hidden" name="from_po_manual_acc" id="from_po_manual_acc" />
						
						<input type="hidden" name="po_id" id="po_id" />
						<input type="hidden" name="prefix" id="prefix" />
						<input type="hidden" name="tr_no" id="tr_no" />
						<input type="hidden" name="page" id="page" />
						
						<input type="hidden" name="manual_purchase_acc_input" id="manual_purchase_acc_input" />
						<input type="hidden" name="manual_purchase_acc_input_name" id="manual_purchase_acc_input_name" />
						
						<input type="hidden" name="input_id" id="input_id" />
						<input type="hidden" name="input_name" id="input_name" />
						
						<input type="hidden" name="parent_id" id="parent_id" />
						<input type="hidden" name="child_id" id="child_id" />
						<input type="hidden" name="group_id_3_1" id="group_id_3_1" />
						<input type="hidden" name="product_id_3_2" id="product_id_3_2" />
						<input type="hidden" name="tax_group_id_4_1" id="tax_group_id_4_1" />
						<input type="hidden" name="group_id_4_2" id="group_id_4_2" />
						<input type="hidden" name="purchase_type_id_5_1" id="purchase_type_id_5_1" />
						<input type="hidden" name="group_id_5_2" id="group_id_5_2" />
						
						
						
					</div>
				</div>

			</div>
		</div>

	</div>
	
	<input type="hidden" name="servlet_name" id="servlet_name" /> 
	<input type="hidden" name="request_type" id="request_type" value="${request_type}" /> 
	<input type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
	
	<script language="javascript">
/* 	modal.$el.on('shown', function () {
		$('input:text:visible:first', this).focus();
		}); */
		$('#validate-form-purchase_acc .modal').on('shown.bs.modal', function () {
		   $('#validate-form-purchase_acc input:text:visible:first', this).focus();
			$('#validate-form-purchase_acc select:visible:first', this).focus();
		   $('#validate-form-purchase_acc textarea:visible:first', this).focus();
		});
		$('#validate-form-purchase_acc').bootstrapValidator({
			//  live: 'disabled',
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				uom_name : {
					validators : {
						notEmpty : {
							message : 'This field is required'
						}
					}
				}
			}
		});
		
		
		
	
function formPurchaseAccountWithAjax(inputIds){
	//var inputIds=jQuery('#input_id').val();
//	var arInputIds=inputIds.split(',');
//	for(var i=0;i<arInputIds.size();i++){
//
//	}

var parentId=jQuery("#validate-form-purchase_acc #parent_id").val();
var childId=jQuery("#validate-form-purchase_acc #child_id").val();
var groupId='';
if(parentId=='3' && childId=='1'){
	groupId=jQuery('#validate-form-purchase_acc #group_id_3_1').val();
}
else if(parentId=='3' && childId=='2'){
	groupId=jQuery('#validate-form-purchase_acc #product_id_3_2').val();
}
else if(parentId=='4' && childId=='1'){
	groupId=jQuery('#validate-form-purchase_acc #tax_group_id_4_1').val();
}
else if(parentId=='4' && childId=='2'){
	groupId=jQuery('#validate-form-purchase_acc #group_id_4_2').val();
}
else if(parentId=='5' && childId=='1'){
	groupId=jQuery('#validate-form-purchase_acc #sales_type_id_5_1').val();
}
else if(parentId=='5' && childId=='2'){
	groupId=jQuery('#validate-form-purchase_acc #group_id_5_2').val();
}

	var inputData='';
	
	var arInputIds=inputIds.split(',');
	
	if(parentId=='2' && childId=='1'){
		for(var j=0;j<arInputIds.length;j++){
			inputData=inputData+arInputIds[j]+":jQuery(\"#validate-form-purchase_acc #"+arInputIds[j]+"\").val(),";			
		}
	}
	else{
		var arGroupId=groupId.split(',');
		
		for(var i=0;i<arGroupId.length;i++){
			for(var j=0;j<arInputIds.length;j++){
				inputData=inputData+arInputIds[j]+"_"+parentId+"_"+childId+"_"+arGroupId[i]+":jQuery(\"#validate-form-purchase_acc #"+arInputIds[j]+"_"+parentId+"_"+childId+"_"+arGroupId[i]+"\").val(),";
			}
		}
	}
	
	var data=" servlet_name : 'PurchaseAccountConfigurationServlet' , callbackmethod_name : 'doSavePurchaseAccountConfigModalData',pi_ajax_save:'1',"+inputData+"parent_id:jQuery('#validate-form-purchase_acc #parent_id').val(),child_id:jQuery('#validate-form-purchase_acc #child_id').val(),tax_group_id:jQuery('#validate-form-purchase_acc #tax_group_id').val(),input_id:jQuery('#validate-form-purchase_acc #input_id').val(),group_id_3_1:jQuery('#validate-form-purchase_acc #group_id_3_1').val(),product_id_3_2:jQuery('#validate-form-purchase_acc #product_id_3_2').val(),tax_group_id_4_1:jQuery('#validate-form-purchase_acc #tax_group_id_4_1').val(),group_id_4_2:jQuery('#validate-form-purchase_acc #group_id_4_2').val(),sales_type_id_5_1:jQuery('#validate-form-purchase_acc #sales_type_id_5_1').val(),group_id_5_2:jQuery('#validate-form-purchase_acc #group_id_5_2').val()";
	
	var script="<script language='javascript'> function configurePurchaseAccountWithAjax(){";
	script=script+" var url ='"+contextpath+"/RequestHandlerServlet';";
	
	script=script+" var request = jQuery.ajax({";
	script=script+" url: url,";
	script=script+" type: 'POST',";
	script=script+" data: {"+data+"},";
	script=script+" dataType: 'html'";
	script=script+" });";
	script=script+" request.done(function(html) {";
	script=script+" jQuery('#purchaseAccountModal').modal('hide')";
	script=script+" });";
	script=script+" request.fail(function(jqXHR, textStatus) {";
	script=script+" alert( 'Request failed: ' + textStatus );";
	script=script+" });";
	script=script+" }</scri"+"pt>";
	
	jQuery('#form_script').html(script);
	
	
}		
		
	</script>
<div id="form_script">

</div>	
</form>

