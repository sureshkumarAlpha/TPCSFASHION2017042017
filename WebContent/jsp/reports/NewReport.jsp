<div class="form-horizontal" id="rept_model">       			
<div class="modal fade" id="reptNameModal" role="dialog">
    <div class="modal-dialog"  >
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title">New Report</h4>
        </div>
        <div class="modal-body">
  
        <div class="row">
        
        <div class="form-group">
        <label class="col-sm-3">Report Name</label>
        <div class="col-sm-8">
        <div class="required-field-block">	   
        <input class="form-control" type="text" id="new_report_name" name="new_report_name" autofocus placeholder="Enter Report Name" maxlength="200"/>
        <div class="required-icon">
			<div class="text">*</div>
		     </div>
		     </div>
        </div>
        </div>
        </div>
        
        <div class="row">
        <div class="form-group">
        <label class="col-sm-3">Report Description</label>
        <div class="col-sm-8">
        <!-- <input class="form-control" type="text" id="new_report_description" name="new_report_description" /> -->
        <textarea class="form-control" rows="2" id="new_report_description" name="new_report_description" placeholder="Enter Report Description" maxlength="500"></textarea>
        </div>
        </div>
        </div>
           
        
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="validateNewReport(1)"><i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save</button>	
          <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>&nbsp;Close</button>
        </div>
      </div>
      
    </div>
  </div>        			
  </div>      		
  <script language="javascript">
  $('#rept_model .modal').on('shown.bs.modal', function () {
	   $('input:text:visible:first', this).focus();
	});
  </script>