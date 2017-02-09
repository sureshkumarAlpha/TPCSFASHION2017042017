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
                <div class="col-md-11 col-sm-12 col-xs-8">
                    <h3 class="page-header">Specifications</h3>
                </div>	
                <!-- <div class="col-md-1 col-sm-12 col-xs-4 pull-right">
                        <img src="src/images/jenixerp.png" class="img-rounded" alt="Cinque Terre" width="150" height="60">						
                </div> -->
         </div>
         
         
         <div class="row" >		
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
						<a href="javascript:addSpecification()" data-toggle="tooltip" title="Add Sales Return">
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
		<div class="row table-responsive">
		<table class="table table-bordered table-condensed">

   <thead>

	 <tr class="header">	
         
					
		 <th valign="middle" nowrap="nowrap" align="center">Action</th>
		  <th valign="middle" nowrap="nowrap" align="center" >Group Type</th>
		 <th valign="middle" nowrap="nowrap" align="center" >Specification</th>
		 <th valign="middle" nowrap="nowrap" align="center" >Specification value</th>		 
		 <th valign="middle" nowrap="nowrap" align="center" >Specification Code</th>

 	</tr>	
  </thead>
	<tbody>				   
		<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >Finished Goods</td>
			<td >Product </td>
			<td >Indoor Lights </td>
			<td >PDT001</td>
	    </tr>
		<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html"  >Edit</a>
			</td>
			<td >Finished Goods</td>
			<td >Product</td>
			<td >Outdoor Lights</td>
			<td >PDT002</td>
	    </tr>
		
		<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >Finished Goods</td>
			<td >Brand</td>
			<td >XYZ</td>
			<td >BN001</td>
	    </tr>						
		<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >Finished Goods</td>
			<td >Brand</td>
			<td >ABC</td>
			<td >BN002</td>
	    </tr>	
		<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >Finished Goods</td>
			<td >Design</td>
			<td >No.7315</td>
			<td >DN001</td>
	    </tr>	
		<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >Finished Goods</td>
			<td >Design</td>
			<td >No.7316</td>
			<td >DN002</td>
	    </tr>	
				<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >Finished Goods</td>
			<td >Design</td>
			<td >No.7315</td>
			<td >DN003</td>
	    </tr>			<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc"  href="edit_specmaster.html">Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>			<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>
					<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>			<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>			<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>			<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc"  href="edit_specmaster.html">Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>
					<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>			<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>			<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>			<tr >
		   <td valign="middle"  nowrap="nowrap" align="left">
			<a class="advsrc" >Delete&nbsp; </a>|  
			<a class="advsrc" href="edit_specmaster.html" >Edit</a>
			</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
	    </tr>
	  </tbody>
		</table>
		    </div>
</div>
</div>
</div>

		<jsp:include page="../common/ColumnPreferences.jsp" />
		<jsp:include page="../common/Footer.jsp" />

		<input type="hidden" name="servlet_name" id="servlet_name" /> <input
			type="hidden" name="request_type" id="request_type" /> <input
			type="hidden" name="callbackmethod_name" id="callbackmethod_name" />
		<input type="hidden" name="pageno" id="pageno" value="${pc.pageNo}" />
		<input type="hidden" name="total_pages" id="total_pages"
			value="${pc.pageCount}" />

	</form>
</body>
</html>