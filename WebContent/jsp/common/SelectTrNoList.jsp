  <div class="form-horizontal trans-form"> 
  <div class="modal fade" id="TrNoModal" role="dialog">
    <div class="modal-dialog modal-lg" >
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"> <%= request.getParameter("title")%></h4>
        </div>
        <div class="modal-body" style="height:450px;overflow-y:auto;">
    <div id="trNoList" >
        </div>
        
      </div>
      <div class="modal-footer">
          <button type="button" class="btn btn-danger" id="trno_close"  data-dismiss="modal">Close</button>
        </div>
    </div>
  </div>
  </div>
</div>
