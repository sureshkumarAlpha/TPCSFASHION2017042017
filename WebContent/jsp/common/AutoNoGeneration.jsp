
<%
 
String label=request.getParameter("auto_modal_label");

String pagePrefixId=request.getParameter("page_prefix_id");
String autoNoId=request.getParameter("page_auton_no_id");

String headerNoColumn=request.getParameter("auto_header_no_column");
String headerIdColumn=request.getParameter("auto_header_id_column");
String headerTypeValue=request.getParameter("auto_header_type_value");
String headerTypeColumn=request.getParameter("auto_header_type_column");

String headerTableName=request.getParameter("auto_header_table_name");

String autoNoTableName=request.getParameter("auto_autono_table_name");
%>

<!-- -------------------------Auto No------------------------------ -->
<form id="validate-form-autono" autocomplete="off">
<div class="form-horizontal trans-form">
	<div class="modal fade" id="autoNoModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"><%=label %> Numbering Generation</h4>
				</div>
				<div class="modal-body auto-no-modal" >
				
					<p>Please tell TPCS, how to generate your <%=label %> number?</p>

					<div class="row "  >
						<div class="col-md-12">
						<label >&nbsp;I want the following prefix for my <%=label %> number</label>
						<!-- class="ember-view"  -->
							<%-- <div class="radio">
								<input class="radio_1" id="all_slno_prefix"
								name="chk_prefix_opt" type="radio" value="1" checked
								onchange="displayAutoNoDiv('all_slno_prefix','tpcs_slno','man_slno','common_slno','sep_slno')"/>
								<label class="radio_1" for="all_slno_prefix">&nbsp;I want the following prefix for my <%=label %> number</label>
							</div> --%>
							
						</div>
					</div>
					
					<div id="all_slno_prefix_div"></div>

					<div id="radio_div">
						<div class="row ">
							<div class="col-md-12">
								<div class="radio">
									<input class="radio_1" id="tpcs_slno"
										name="chk_out_opt" type="radio" value="1"
										onchange="displayAutoNoDiv('all_slno_prefix','tpcs_slno','man_slno','common_slno','sep_slno')"/>
									<label class="radio_1" for="tpcs_slno">&nbsp;I want TPCS to generate my <%=label %> number</label>
								</div>
							</div>
						</div>

						<div id="tpcs_slno_div"  style="display:none">

							<div class="row ">
								<div class="col-md-1 col-xs-12">&nbsp;</div>
								<div class="col-md-10">
									<div class="radio"> 
										<input class="radio_1" id="common_slno"
											name="chk_in_opt" type="radio" value="1"
											onchange="displayAutoNoDiv('all_slno_prefix','tpcs_slno','man_slno','common_slno','sep_slno')"/>
										<label class="radio_1" for="common_slno">&nbsp;Follow one common serial number for all prefixes</label>
									</div>
								</div>
							</div>
							<div class="row ">
								<div class="col-md-2 col-xs-12">&nbsp;</div>
								<div class="col-md-10">
									<small>(ex. Ind 1, Exp 2, Exp 3, Ind 4 etc)</small>
								</div>
							</div>


							<div id="common_slno_div" >
								<div class="row ">
									<div class="col-md-2 col-xs-12">&nbsp;</div>
									<div class="col-md-10" >
									<div class="col-md-12" >
										<label for="seperate_prefex_1" > My starting <%=label %> number should be</label>
									</div>
									<div class="col-md-4" >
										<input id="autono_common_start_no" name="autono_common_start_no" maxlength="6" class="ember-view ember-text-field form-control" type="text" ">
									</div>
									</div>
								</div>
							</div>


							<div class="row ">
								<div class="col-md-1 col-xs-12">&nbsp;</div>
								<div class="col-md-10">
									<div class="radio">
										<input class="radio_1" id="sep_slno"
										name=chk_in_opt type="radio" value="0"
										onchange="displayAutoNoDiv('all_slno_prefix','tpcs_slno','man_slno','common_slno','sep_slno')"/>
										<label class="radio_1" for="sep_slno">&nbsp;Follow separate serial number for each prefix</label>
									</div>
								</div>
							</div>
							<div class="row ">
								<div class="col-md-2 col-xs-12">&nbsp;</div>
								<div class="col-md-10">
									<small>(ex. Ind 1, Exp 1, Exp 2, Ind 2 etc.)</small>
								</div>
							</div>


							<div id="sep_slno_div">
								<div id="sep_slno_prefix_div"></div>
							</div>
						</div>

						<div class="row ">
							<div class="col-md-12">
								<div class="radio">
									<input class="radio_1" id="man_slno"
									name="chk_out_opt" type="radio" value="0"
									onchange="displayAutoNoDiv('all_slno_prefix','tpcs_slno','man_slno','common_slno','sep_slno')"/>
									<label class="radio_1" for="man_slno">&nbsp;I will enter <%=label %> number manually</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
				  
					<input id="modal" name="modal" type="hidden" value="1">
					<button type="button" class="btn btn-success" onclick="saveAutoNo()">Save</button>
					<button type="button" class="btn btn-danger" id="auono_close" data-dismiss="modal">Close</button>
				</div>
			</div>
			

			
			<input type="hidden" id="page_prefix_id" name="page_prefix_id" value="<%= pagePrefixId %>" />
			<input type="hidden" id="page_auton_no_id" name="page_auton_no_id" value="<%=autoNoId %>"/>
			<input type="hidden" id="auto_header_no_column" name="auto_header_no_column" value="<%=headerNoColumn %>"/>
			<input type="hidden" id="auto_header_id_column" name="auto_header_id_column" value="<%=headerIdColumn %>"/>
			<input type="hidden" id="auto_header_type_column" name="auto_header_type_column" value="<%=headerTypeColumn %>"/>
			
			<input type="hidden" id="auto_header_type_value" name="auto_header_type_value" value="<%=headerTypeValue%>"/>
			<input type="hidden" id="auto_header_table_name" name="auto_header_table_name" value="<%=headerTableName %>"/>
			<input type="hidden" id="auto_autono_table_name" name="auto_autono_table_name" value="<%=autoNoTableName %>"/>
			<input type="hidden" name="autono_row_count" id="autono_row_count" value="${row_count}"/>
			<input type="hidden" name="autono_row_ids" id="autono_row_ids" value="${row_ids }"/>
			<input type="hidden" name="autono_delete_prefix" id="autono_delete_prefix" />			
			
		</div>
	</div>

</div>

<script language="javascript">
	
	$('#validate-form-autono .modal').on('shown.bs.modal', function () {
	 	   $('#validate-form-autono input:text:visible:first', this).focus();
	 	});
	
		$('#validate-form-autono').bootstrapValidator({
			//  live: 'disabled',
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				autono_common_start_no : {
					validators : {
						callback: {
	                        message: 'This field is required',
	                        callback: function(value, validator, $field) {
	                        	if (value.length==0){
	                        		 return {
		                                    valid: false,
		                                    message: 'This field is required'
		                                };
	                        		}
	                        	else if (value<=0) {
	                                return {
	                                    valid: false,
	                                    message: 'This field must be greater than zero'
	                                };
	                            }
	                        	return true;
	                        }
	                    } ,
	                    numeric: {
		                      message: 'This field must be a number'
		                            } 
              			}
				}
			}
		});
		runShortKey('validate-form-autono');
	</script>
	
</form>