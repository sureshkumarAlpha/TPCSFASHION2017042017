<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><fmt:message bundle="${bundle}" key="Title.Title" /></title>

<jsp:include page="../common/Header.jsp" />
<script  type="text/javascript"  src="${pageContext.request.contextPath}/js/common/bootstrap/bootstrap-tabcollapse.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/panel.css" />
<style>
.container{
    margin-top:30px;
}

.filter-col{
    padding-left:10px;
    padding-right:10px;
}

</style>


</head>
<jsp:include page="../common/ValidateUser.jsp" />

<body>
	<form action="" id="tpcslogin" method="post" role="form">
		<div id="wrapper">

			<jsp:include page="../common/MainMenu.jsp">
				<jsp:param value="2" name="screen_type" />
				<jsp:param value="Inventory.Transactions.SalesReturn" name="screen_name" />
			</jsp:include>

<div id="page-wrapper">
<div id="page-inner">
        <div class="row">
                <div class="col-md-11 col-sm-8 col-xs-8">
                    <h3 class="page-header">${report_name}</h3>
                </div>
            
		</div>
        
        
   <div class='row row-no-margin'>
              <div class="col-md-8">
            <div class="panel with-nav-tabs panel-primary">
                <div class="panel-heading">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1primary" data-toggle="tab">Columns</a></li>
                            <li><a href="#tab2primary" data-toggle="tab">Grouping</a></li>
                            <li><a href="#tab3primary" data-toggle="tab">Columns to Total</a></li>
                             <li><a href="#tab4primary" data-toggle="tab">Criteria</a></li>
                            
                        </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab1primary">

											<div id="orgPopupForm" role="form">
												 ${column_pref}
											</div>
									 <div class='row'>        	         	 
            
        </div> 

										</div>
                        <div class="tab-pane fade" id="tab2primary">
                      <div class='row'>  
                         <div class='col-sm-3'>
		                <div class='form-group'>
		                    <label for="tag">Summarize by</label>
					<select class="form-control" id="group_by_column1" name="group_by_column1">
					    <option value="-1">Select</option>
					    <c:forEach var="gb" items="${group_by_column_list}">
					    <c:choose>
					    <c:when test="${gb.groupByOrder eq 1 and gb.groupByOrder ne group_cnt }">
					    <option value="${gb.columnId}" selected="selected">${gb.columnDisplayName}</option>
					    </c:when>
					    <c:otherwise>
					    <option value="${gb.columnId}">${gb.columnDisplayName}</option>
					    </c:otherwise>
					    </c:choose>
					    </c:forEach>
					  </select>
		                </div>
		            </div>
		             <div class='col-sm-3'>
		                <div class='form-group'>
		                    <label for="tag">Order by</label>
					<select class="form-control" id="group_by_column_order1" name="group_by_column_order1">
					    <option value="asc" <c:if test="${first_order_by eq 'asc' }">selected</c:if> >Ascending</option>
					    <option value="desc" <c:if test="${first_order_by eq 'desc' }">selected</c:if> >Descending</option>		
					  </select>
		                </div>
		            </div>
                        </div>
                        
                         <div class='row'>  
                         <div class='col-sm-3'>
		                <div class='form-group'>
		                    <label for="tag">and then by</label>
					<select class="form-control" id="group_by_column2" name="group_by_column2">
					    <option value="-1">Select</option>
					    <c:forEach var="gb" items="${group_by_column_list}">
					    <c:choose>
					    <c:when test="${gb.groupByOrder eq 2 and gb.groupByOrder ne group_cnt }">
					    <option value="${gb.columnId}" selected="selected">${gb.columnDisplayName}</option>
					    </c:when>
					    <c:otherwise>
					    <option value="${gb.columnId}">${gb.columnDisplayName}</option>
					    </c:otherwise>
					    </c:choose>
					    </c:forEach>
					  </select>
		                </div>
		            </div>
		             <div class='col-sm-3'>
		                <div class='form-group'>
		                    <label for="tag">Order by</label>
					<select class="form-control" id="group_by_column_order2" name="group_by_column_order2">
					    <option value="asc" <c:if test="${second_order_by eq 'asc' }">selected</c:if>>Ascending</option>
					    <option value="desc" <c:if test="${second_order_by eq 'desc' }">selected</c:if>>Descending</option>		
					  </select>
		                </div>
		            </div>
                        </div>
                        
                        <div class='row'>  
                         <div class='col-sm-3'>
		                <div class='form-group'>
		                    <label for="tag">and finally by</label>
					<select class="form-control" id="group_by_column3" name="group_by_column3">
					    <option value="-1">Select</option>
					    <c:forEach var="gb" items="${group_by_column_list}">
					    <c:choose>
					    <c:when test="${gb.groupByOrder  eq 3 and gb.groupByOrder ne group_cnt  }">
					    <option value="${gb.columnId}" selected="selected">${gb.columnDisplayName}</option>
					    </c:when>
					    <c:otherwise>
					    <option value="${gb.columnId}">${gb.columnDisplayName}</option>
					    </c:otherwise>
					    </c:choose>
					    </c:forEach>
					  </select>
		                </div>
		            </div>
		             <div class='col-sm-3'>
		                <div class='form-group'>
		                    <label for="tag">Order by</label>
					<select class="form-control" id="group_by_column_order3" name="group_by_column_order3">
					    <option value="asc" <c:if test="${third_order_by eq 'asc' }">selected</c:if>>Ascending</option>
					    <option value="desc" <c:if test="${third_order_by eq 'desc' }">selected</c:if>>Descending</option>		
					  </select>
		                </div>
		            </div>
                        </div>
                        
                        </div>
                        <div class="tab-pane fade" id="tab3primary">
                        <div class="row">             
				              
				            
				            <div class="table-responsive">
										<table class="table table-bordered table-condensed">
											<thead>
												<tr class="header">
													<th>Columns</th>
													<th>Sum</th>
													<th>Average</th>
													<th>Lowest Value</th>
													<th>Largest Value</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="ctt" items="${column_to_total_list}">
											<tr class="datarow">
												    <td>${ctt.columnDisplayName}</td>
												    
												     <td><div class="checkbox"><input type="checkbox" class="checkbox_1" id="chk_${ctt.columnId}_1" name="chk_${ctt.columnId}_1" <c:if test="${ctt.total eq 1 }">checked</c:if> value="1" /><label for="chk_${ctt.columnId}_1" class="checkbox_1"></label></div></td>
													<td><div class="checkbox"><input type="checkbox" class="checkbox_1"  id="chk_${ctt.columnId}_2" name="chk_${ctt.columnId}_2" <c:if test="${ctt.average eq 1 }">checked</c:if>  value="1" /><label for="chk_${ctt.columnId}_2" class="checkbox_1"></label></div></td>
												    <td><div class="checkbox"><input type="checkbox" class="checkbox_1"  id="chk_${ctt.columnId}_3" name="chk_${ctt.columnId}_3" <c:if test="${ctt.minimum eq 1 }">checked</c:if>  value="1" /><label for="chk_${ctt.columnId}_3" class="checkbox_1"></label></div></td>
													 <td><div class="checkbox"><input type="checkbox" class="checkbox_1"  id="chk_${ctt.columnId}_4" name="chk_${ctt.columnId}_4" <c:if test="${ctt.maximum eq 1 }">checked</c:if>  value="1" /><label for="chk_${ctt.columnId}_4" class="checkbox_1"></label></div></td>
												    
												   <%--  <td><input type="checkbox" name="chk_${ctt.columnId}_1" <c:if test="${ctt.total eq 1 }">checked</c:if> value="1" /></td>
													<td><input type="checkbox" name="chk_${ctt.columnId}_2" <c:if test="${ctt.average eq 1 }">checked</c:if>  value="1" /></td>
												    <td><input type="checkbox" name="chk_${ctt.columnId}_3" <c:if test="${ctt.minimum eq 1 }">checked</c:if>  value="1" /></td>
													 <td><input type="checkbox" name="chk_${ctt.columnId}_4" <c:if test="${ctt.maximum eq 1 }">checked</c:if>  value="1" /></td> --%>

												</tr>
											</c:forEach>
												
											</tbody>
										</table>

									</div>  
				        </div>
				        
                      
                        </div>
                        <div class="tab-pane fade" id="tab4primary">
                        
                          <!-- <div class="row">
                        <div class='col-sm-3'>
                          <label>Standard Filters</label>
                        </div>
                        </div>
                        <div class='row'>  
                         <div class='col-sm-3'>
		                <div class='form-group'>
		                    <label for="tag">Column:</label>
					<select class="form-control" id="sel1">
					    <option>Select</option>
					    <option>Group</option>
					    <option>Product</option>		
					      <option>Required Date</option>				  
					  </select>
		                </div>
		            </div>
		            
		              <div class='col-sm-3'>    
                <div class='form-group' >
                    <label for="quotationdate">Start Date</label>
		    <div class='input-group date' id='datepicker' >
			    <input type='text' class="form-control" placeholder="Select Date" />
			    <span class="input-group-addon">
				<span class="glyphicon glyphicon-calendar"></span>
			    </span>
                </div>		                
                </div>
                
                <script type="text/javascript">
			
		      jQuery('#datepicker').datepicker({
					    format: "dd-mm-yyyy"
					});  
	
        			</script>
        			
            </div>
            <div class='col-sm-3'>    
                <div class='form-group' >
                    <label for="quotationdate">End Date</label>
		    <div class='input-group date'   id='datepicker2' >
			    <input type='text' class="form-control" placeholder="Select Date" id='datepicker2' name='datepicker2'/>
			    <span class="input-group-addon">
				<span class="glyphicon glyphicon-calendar"></span>
			    </span>
                </div>		                
                </div>
                
                <script type="text/javascript">
			
		      jQuery('#datepicker2').datepicker({
					    format: "dd-mm-yyyy"
					});  
	
        			</script>
        			
            </div>
                        </div> -->
                        
                       <!--  <div class="row">
                        <div class='col-sm-3'>
                          <label>Advanced Filters</label>
                        </div>
                    </div> -->
                    
                    <%-- <div class="row">
		                        <div class="col-sm-12">
		                          <div id="lbl_query"></div>
		                          <div id="tmp_query">${final_query}</div>
		                          <input type="hidden" id="final_query" name="final_query" value="${final_query}"/>
		                          <input type="hidden" id="final_dbquery" name="final_dbquery" value="${final_dbquery}"/>
		                          <input type="hidden" id="final_dbhavingquery" name="final_dbhavingquery" value="${final_dbhavingquery}"/>
		                          
		                        </div>
		                    </div> --%>
                    <div id="criteria_row_content">
					${criteria_row}
					
					<table class="table" id="query_table">
					<tr>
					<th>Action</th>
					<th>Query</th>
					
					</tr>
					<c:set var="row_ids" value=""/>
					<c:forEach var="cq" items="${citeria_query_list }">
					<tr id="q_table_row${cq.criteriaId}">
					<td align="left">
					<a href="javascript:deleteQueryRow(${cq.criteriaId})" title="Remove"><span class="btn-label text-danger"><i class="glyphicon glyphicon-remove"></i></span></a>
					</td>
					<td>${cq.finalQuery}
					<input type="hidden" id="db_query_${cq.criteriaId}" name="db_query_${cq.criteriaId}" value="${cq.finalDbQuery}" />
					<input type="hidden" id="db_having_query_${cq.criteriaId}" name="db_having_query_${cq.criteriaId}" value="${cq.finalDbHavingQuery}" />
					<input type="hidden" id="query_${cq.criteriaId}" name="query_${cq.criteriaId}" value="${cq.finalQuery}" /></td>
					</tr>
					<c:set var="row_ids" value="${row_ids}${cq.criteriaId},"/>
					</c:forEach>
					</table>
					</div>
					
					
							<%-- <div class='row'>
								<div class='col-sm-3'>
									<div class='form-group'>
										<label for="tag">Column</label> <select
											class="form-control" id="column_name" name="column_name">
											<option value="-1">Select</option>
											<c:forEach var="cl" items="${column_list}">
												<option value="${cl.columnName}">${cl.columnDisplayName}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class='col-sm-3'>
									<div class='form-group'>
										<label for="tag">Operator</label> <select
											class="form-control" id="operator" name="operator">
											<option value="-1">Select</option>
											<c:forEach var="opt" items="${operator_list}">
												<option value="${opt.operatorDb}">${opt.operator}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class='col-sm-3'>
									<div class='form-group'>
										<label for="tag">Value</label> <input class="form-control"
											type="text" id="criteria_value" name="criteria_value" />
									</div>
								</div>

								<div class='col-sm-3'>
									<div class='form-group'>
										<input class="btn btn-primary" type="button" value="and"
											onclick="formCriteria('${criteria_row_id}','1')" /> <input
											class="btn btn-primary" type="button" value="or"
											onclick="formCriteria('${criteria_row_id}','2')" />
									</div>
								</div>
							</div> --%>

						</div>
                    </div>
                </div>
            </div>
        </div>
        </div>       
        <div class='row'>        	         	 
            <div class='col-sm-6 col-xs-12 pull-right'>               
                   <!-- <button type="button" class="btn btn-success" onclick="saveCustomizeReport(0)"><span class="btn-label"><i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save</button> -->
                   <div class="btn-group dropup">
                   
                	<button type="button" class="btn btn-success" name="save" id="save"  onclick="saveCustomizeReport(0)"  style="height:30px;">
                	<span class="btn-label"><i class="glyphicon glyphicon-floppy-disk"></i></span>&nbsp;Save</button>
                
                <button    class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="height:30px;"><span class="caret"></span></button>
                <c:if test="${rights ne null && rights.addPermission eq 1}">
                <ul class="dropdown-menu">
            	<li><a href="#"  data-toggle="modal" data-target="#reptNameModal" >Save As</a></li>
                </ul>
                </c:if>
              </div>
              	<!-- onclick="showReports()" --> 
                    <button type="button" class="btn btn-danger" onclick="closeCustomizeReport()" style="height:30px;"><span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>&nbsp;Close</button>				             
            </div>
            <div class='col-sm-6 col-xs-12'>
            </div>	    
        </div>      
<!-- <ul id="myTab" class="nav nav-tabs">
  <li class="active"><a href="#home" data-toggle="tab">Home</a></li>
  <li><a href="#profile" data-toggle="tab">Profile</a></li>

</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="home">
        <p>Raw denim you probably haven't...</p>
    </div>
    <div class="tab-pane fade" id="profile">
        <p>Food truck fixie locavore, accus...</p>
    </div>
</div> -->
<!-- <script >
$('#myTab').tabCollapse();
</script> -->
        
   </div>
  </div> 
  		</div>

		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> 
		<input type="hidden" name="request_type" id="request_type" /> 
		<input	type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		
		<input type="hidden" name="invoke_servlet" id="invoke_servlet" value="${invoke_servlet}" /> 
		<input type="hidden" name="invoke_method" id="invoke_method" value="${invoke_method}" />
		
		<input type="hidden" name="pageno" id="pageno" />
		<input type="hidden" name="row_ids" id="row_ids" value="${row_ids }"/>
		
		<input type="hidden" name="row_count" id="row_count" value="${row_count}"/>
		<input type="hidden" name="subdocument_id" id="subdocument_id" value="${subdocument_id}"/>
		<input type="hidden" name="report_url" id="report_url" value="${report_url}"/>
		<input type="hidden" name="criteria_row_no" id="criteria_row_no" value="${criteria_row_no}"/>
		<input type="hidden" name="selected_columns" id="selected_columns" />
		<input type="hidden" name="all_columns" id="all_columns" /> 
		<input type="hidden" name="is_date_field" id="is_date_field" />
		<input type="hidden" name="column_to_total_col_id" id="column_to_total_col_id" value="${column_to_total_col_id}" />
		<input type="hidden" name="report_mode" id="report_mode" value="n"/>
		
	<script language="javascript">
	
	$("#total_columns").draggable();
	$("#visible_columns").draggable();
	
	
$(document).ready(function () {
	 var allOptions1 = $('#operator1 option')
		$('#column_name1').change(function () {
			$('#operator1 option').remove();
			var classN = $('#column_name1 option:selected').prop('class');
			var opts1 = allOptions1.filter('.' + classN);
			$.each(opts1, function (i, j) {
				$(j).appendTo('#operator1');
				});
			});
	 
	 $( "#column_name1" ).change(function() {
		
	 var col=document.getElementById('column_name1');
	 var cs2 = $("option:selected", col).attr("class");
	 if(cs2=='datatype5'){
		 $('#is_date_field').val(1);
		  jQuery('#criteria_value1').datepicker({
			    format: "dd-mm-yyyy"
			});  
	 }
	 else{
		 $('#is_date_field').val(0);
		 $('#criteria_value1').datepicker('remove');
	 }
		});
	/*  if(document.getElementById("is_date_field").value==1){
	      jQuery('#criteria_value1').datepicker({
				    format: "dd-mm-yyyy"
				});  
		} */
		});
		
	
	</script>
        			
<!-- <input type="hidden" data-toggle="modal" data-target="#orgModal" id="new_report_modal"/> -->         			
<jsp:include page="/jsp/reports/NewReport.jsp" />	
	</form>
</body>
</html>
  