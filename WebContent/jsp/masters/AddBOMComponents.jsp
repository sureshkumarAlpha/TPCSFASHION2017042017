    <!-- --------------------------GROUP MASTER------------------------------ -->
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

  <form id="validate-form-bom-comp" autocomplete="off">
  
  <div class="modal fade" id="addBOCompModal" role="dialog">
    <div class="modal-dialog modal-lg" >
    
      <!-- Modal content-->
      <div class="modal-content">
   <!--      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" id="acc_head"></h4>
        </div> -->
        <div class="modal-body" style="height:450px;overflow-y:auto;">
<div class="table-responsive" id="bom_comp_det">

</div>
       
       <div id="date_picker" ></div> 
      </div>
      <div class="modal-footer">
   <!--    <button type="button" class="btn btn-success" onclick="saveBOMCopmponents()">Save</button> -->
          <button type="button" class="btn btn-danger" id="comp_close"  data-dismiss="modal">Close</button>
        
   <input type="hidden" name="row_ids" id="row_ids" value="${row_ids }"/>
        
        </div>
    </div>
  </div>
   
   <script type="text/javascript">

$('.modal').on('shown.bs.modal', function () {
	   $('input:text:visible:first', this).focus();
		$('select:visible:first', this).focus();
	   $('textarea:visible:first', this).focus();
	});
	</script>
	


</div>
</form>