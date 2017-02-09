<div id="page-wrapper">
<div id="page-inner">
         <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h4 class="page-header">Employee</h4>
                </div>	
                <div class="col-md-1 col-sm-4 col-xs-4 pull-right">
                        <img src="src/images/jenixerp.png" class="img-rounded" alt="Cinque Terre" width="150" height="60">						
                </div>
         </div>
         <div class="row">	
		 <div class="input-group custom-search-form col-sm-3">				
                                <input type="text" class="form-control" placeholder="Customer Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                </div>
		<div class="row paginationstyle">		
			<div class='col-sm-12'>    
				 <ul class="pagination pull-right" style="padding:5px;">
					<li class="disabled"><a href="#">&laquo;</a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
				<ul class="pagination pull-left" style="padding-top:5px;">
					<li>
						<a href='#addEmployee' data-toggle="tooltip" title="Add Customer">
						<i class="fa fa-plus-circle"></i>
						</a>
					</li>
					<li>
						<a href='#' data-toggle="tooltip" title="Refresh">
						<i class="fa fa-refresh"></i>
						</a> 
					</li>
					<li>
						<a href='#' data-toggle="tooltip" title="Reorder columns">
						<i class="fa fa-reorder"></i>
						</a> 
					</li>
				</ul>
			</div>
		</div>	
		<div class="table-responsive">
		<table class="table table-bordered table-condensed">
		    <thead>
		    <tr class="header">
			<th>ACTION</th>			
			<th>EMPLOYEE CODE</th>
			<th>EMPLOYEE NAME</th>
			<th>SHORT NAME</th>
			<th>COMPANY NAME</th>
			<th>CONTACT PERSON</th>		
		    </tr>
		    </thead>
		    <tbody>
		    <tr class="datarow" ng-repeat="emp in employees">
				<td>
					&nbsp;&nbsp;
					<a href="#" data-toggle="tooltip" title="Delete">
          				<span class="glyphicon glyphicon-remove"></span>
        			</a>
        			&nbsp;&nbsp;
        			<a href="#" data-toggle="tooltip" title="Edit">
          				<span class="glyphicon glyphicon-edit"></span>
        			</a>
				</td>
				<td>{{emp.empCode}}</td>
				<td>{{emp.empName}}</td>
				<td>{{emp.shortName}}</td>
				<td>{{emp.companyName}}</td>
				<td>{{emp.contactPerson}}</td>				
			</tr>
		    </tbody>	
	    </table>
	    </div>
	    </div>              
     </div>  
  </div>  