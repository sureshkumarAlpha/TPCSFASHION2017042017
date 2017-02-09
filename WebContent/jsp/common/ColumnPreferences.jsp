<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">	    
		var localizedPreferenceStrings = {
				    SELECT_AT_LEAST: "<fmt:message bundle="${bundle}" key="ColumnPreference.SelectAtleast"/>",
				    NO_COLUMNS: "<fmt:message bundle="${bundle}" key="ColumnPreference.NoColumns"/>",
				    SLCT_COLUMN_CHANGE_ORDER: "<fmt:message bundle="${bundle}" key="ColumnPreference.SelectColumnToOrder"/>"
			};	
	</script>
<!-- class="form-horizontal" -->
<div > 
  <div class="modal fade" id="orgModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Column Preferences</h4>
        </div>
        <div class="modal-body">
  
        
          <div id="orgPopupForm" role="form">

         </div> 
        
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-success" onclick="saveSettings()">Save</button>	
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
          
           <input type="hidden" id="subdocument_id"  name="subdocument_id"   />
           
        </div>
      </div>
      
    </div>
  </div>
</div>


