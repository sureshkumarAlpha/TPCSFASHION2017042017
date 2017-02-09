<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- <!DOCTYPE html> -->
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title><fmt:message bundle="${bundle}" key="Title.Title" /></title>


<jsp:include page="../common/Header.jsp"/>

</head>
<jsp:include page="../common/ValidateUser.jsp"/>
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
<style>
.panel-right h3{
    font-size: 32px;
}
.panel-right div{
    padding-top: 35px;
    }
</style>
  <body>
   <form name="dashboard" id="dashboard" method="post">
     

 <jsp:include page="../common/MainMenu.jsp"> 
 <jsp:param value="9" name="screen_type"/>
		<jsp:param value="Admin.Home" name="screen_name"/>
		<jsp:param value="0" name="sub_document_type"/>
		</jsp:include>

<div class="page-wrapper">
<div class="page-inner">
                
                <div class="row header-row">
						<div class="col-sm-12 col-xs-12">
							<div class='col-sm-8'>
								<h3 >Dashboard</h3>
							</div>
						</div>
				</div>
                
                
                <div class="row">
                <div class="col-sm-12">
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-green light-green">
                            <div class="panel-left pull-left light-green">
                                <i class="fa fa-bar-chart-o fa-5x"></i>
                                
                            </div>
                            <div class="panel-right ">
                            <div>
								<h3>INR</h3><h3>&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2"  value="23" />&nbsp;Cr</h3>
                               <strong> </strong>
                               </div>
                            </div>
                        </div>
                        
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>Leather stock value </h4>
							</div>
					</div>
                        
                    </div>
                    
                    
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-green green">
                            <div class="panel-left pull-left green">
                                <i class="fa fa-shopping-cart fa-5x"></i>
                                
                            </div>
                            <div class="panel-right ">
                            <div>
								<h3>INR</h3><h3>&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2"  value="8.2" />&nbsp;Cr</h3>
                               <strong> </strong>
                               </div>
                            </div>
                        </div>
                        
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>Non-Leather stock value</h4>
							</div>
					</div>
                        
                    </div>
                    
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder  bg-color-red red">
                            <div class="panel-left pull-left red">
                                <i class="fa fa fa-comments fa-5x"></i>
                                
                            </div>
                            <div class="panel-right ">
                            <div>
								<h3>INR</h3><h3>&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2"  value="12.5" />&nbsp;Cr</h3>
                               <strong> </strong>
                               </div>
                            </div>
                        </div>
                        
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>WIP value</h4>
							</div>
					</div>
                        
                    </div>
                    
                    
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-blue blue">
                            <div class="panel-left pull-left blue">
                                <i class="fa fa-users fa-5x"></i>
                                
                            </div>
                            <div class="panel-right ">
                            <div>
								<h3>INR</h3><h3>&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2"  value="1.25" />&nbsp;Cr</h3>
                               <strong> </strong>
                               </div>
                            </div>
                        </div>
                        
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>FG Stock value</h4>
							</div>
					</div>
                        
                    </div>
                    
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-brown brown">
                            <div class="panel-left pull-left brown">
                                <i class="fa fa-users fa-5x"></i>
                                
                            </div>
                            <div class="panel-right ">
                            <div>
								<h3>INR</h3><h3>&nbsp;<fmt:formatNumber type="number" maxFractionDigits="2"  value="45" />&nbsp;Cr</h3>
                               <strong> </strong>
                               </div>
                            </div>
                        </div>
                        
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>Pending order value</h4>
							</div>
					</div>
                        
                    </div>
                     
                     
                     
                     
                </div>           
                </div>     
                
<div class="row">
<div class="col-sm-12">
<div class="col-md-4 col-sm-12 col-xs-12">

                        <div class="panel panel-default">
                        
                            <div class="panel-heading">
                               Store-Wise stock
                            </div>
                            <div class="panel-body">
                            <div class="col-md-9 col-md-offset-2 col-sm-12 col-xs-12">
                                <div id="store_wise_stock"></div>
                            </div>
                        </div>
                        </div>
                    </div>
                    
                    

<div class="col-md-4 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Stage-Wise stock
                            </div>
                            <div class="panel-body">
                            <div class="col-md-9 col-md-offset-2 col-sm-12 col-xs-12">
                                <div id="stage_wise_stock"></div>
                            </div>
                        </div>
                        </div>
                    </div>
                    
<div class="col-md-4 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                               Leather stock age-wise
                            </div>
                            <div class="panel-body">
                            <div class="col-md-9 col-md-offset-2 col-sm-12 col-xs-12">
                                <div id="ltr_stk_val_age"></div>
                            </div>
                        </div>
                        </div>
                    </div>                    
                    
</div>
</div>


<%
Calendar currentDate = Calendar.getInstance();
SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
String dateNow = formatter.format(currentDate.getTime());

currentDate.add(Calendar.DATE, 1);  
String dateNowPlus1 = formatter.format(currentDate.getTime());

currentDate.add(Calendar.DATE, 2);  
String dateNowPlus3 = formatter.format(currentDate.getTime());

currentDate.add(Calendar.DATE, 4);  
String dateNowPlus7 = formatter.format(currentDate.getTime());

%>


<div class="row">
<div class="col-sm-12">
		<div class="col-md-12 col-sm-12 col-xs-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                           Next Shipments
                            </div> 
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Customer</th>
                                                <th>Order No</th>
                                                <th>Shipment Date</th>
                                                <th>Product</th>
                                                <th>Order Qty</th>
                                                <th>Order Value</th>
                                                 <th>Current Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                         	<tr>
                                                <td>1</td>
                                                <td>Customer A</td>
                                                <td >45781</td>
                                                <td><%=dateNowPlus1%></td>
                                                <td>Bag</td>
                                                <td>3250</td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="4075500" /></td>
                                                <td>Packing</td>
                                            </tr>
                                            
                                            <tr>
                                                <td>2</td>
                                                <td>Customer B</td>
                                                <td >45748</td>
                                                <td><%=dateNowPlus1%></td>
                                                <td>SLG</td>
                                                <td>3450 </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3643200" /> </td>
                                                <td>Packing</td>
                                            </tr>
                                            
                                           <tr>
                                                <td>3</td>
                                                <td>Customer A</td>
                                                <td >45757</td>
                                               <td><%=dateNowPlus3%></td>
                                                <td>Footwear</td>
                                                <td>4570 </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="6937260" /> </td>
                                                <td>QA</td>
                                            </tr>
                                            
                                           <tr>
                                                <td>4</td>
                                                <td>Customer A</td>
                                                <td >45785</td>
                                                 <td><%=dateNowPlus3%></td>
                                                <td>Jacket</td>
                                                <td>2540 </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3520440" /> </td>
                                                <td>Final</td>
                                            </tr>
                                            
                                            <tr>
                                                <td>5</td>
                                                <td>Customer C</td>
                                                <td >45784</td>
                                               <td><%=dateNowPlus7%></td>
                                                <td>Bag</td>
                                                <td>3540 </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="4672800" /> </td>
                                                <td>Components</td>
                                            </tr>
                                            
                                           
 										</tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
		</div>
		</div>
<div class="row">
<div class="col-sm-12">
		<div class="col-md-12 col-sm-12 col-xs-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                           Production report
                            </div> 
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Operation</th>
                                                <th>Week 1</th>
                                                <th>Week 2</th>
                                                <th>Week 3</th>
                                                <th>Week 4</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                         	<tr>
                                                <td>1</td>
                                                <td>Cutting</td>
                                                <td ><fmt:formatNumber type="number" maxFractionDigits="2"  value="3523" /></td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3423" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3654" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3514" /> </td>
                                            </tr>
                                            
                                            <tr>
                                                <td>2</td>
                                                <td>Components</td>
                                                <td ><fmt:formatNumber type="number" maxFractionDigits="2"  value="3754" /></td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3523" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3426" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3654" /> </td>
                                            </tr>
                                            
                                            <tr>
                                                <td>3</td>
                                                <td>Final Stitching</td>
                                                <td ><fmt:formatNumber type="number" maxFractionDigits="2"  value="3654" /></td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3754" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3523" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3426" /> </td>
                                            </tr>
                                            
                                            <tr>
                                                <td>4</td>
                                                <td>QA</td>
                                                <td ><fmt:formatNumber type="number" maxFractionDigits="2"  value="3395" /></td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3654" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3695" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3585" /> </td>
                                            </tr>
                                            
                                            <tr>
                                                <td>5</td>
                                                <td>Packing</td>
                                                <td ><fmt:formatNumber type="number" maxFractionDigits="2"  value="3354" /></td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3568" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3674" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3457" /> </td>
                                            </tr>
                                            
                                           
 										</tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
		</div>
		</div>
<div class="row">
<div class="col-sm-12">
		<div class="col-md-12 col-sm-12 col-xs-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                            Order Profitability
                            </div> 
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Order No</th>
                                                <th>Customer</th>
                                                <th>Article</th>
                                                <th>Order Qty</th>
                                                <th>Leather cost</th>
                                                <th>Material cost</th>
                                                 <th>Labour & Overheads</th>
                                                <th>COGS</th>
                                                <th>Cost Per Unit in USD</th>
                                                <th>Selling Price USD</th>
                                                <th>Selling Value</th>
                                                <th>Profit / loss</th>
                                                <th>%</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                         <tr>
                                                <td>1</td>
                                                <td>324</td>
                                                <td>Customer A</td>
                                                <td >Woven Belt</td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="25000" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="437500" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="825000" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="189375" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="1451875" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="9" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="10" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="1650000" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="198125" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="12.0" /> </td>
                                                
                                                
                                            </tr>
                                            
                                            <tr>
                                                <td>2</td>
                                                <td>325</td>
                                                <td>Customer A</td>
                                                <td >Passcase Wallet</td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3520" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="677600" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="1045440" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="86152" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="1809192" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="8" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="9" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="2090880" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="281688" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="13.5" /> </td>
                                                
                                                
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>330</td>
                                                <td>Customer A</td>
                                                <td >Cemented Shoe</td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="4570" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="2227875" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="3317820" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="388199" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="5933894" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="20" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="22" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="6635640" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="701746" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="10.6" /> </td>
                                                
                                                
                                            </tr>
                                            
                                            <tr>
                                                <td>4</td>
                                                <td>335</td>
                                                <td>Customer B</td>
                                                <td >Tote Bag</td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="2500" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="1096875" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="1485000" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="154913" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="2736788" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="17" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="18" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="2970000" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="233213" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="7.9" /> </td>
                                                
                                                
                                            </tr>
                                            
                                            <tr>
                                                <td>5</td>
                                                <td>342</td>
                                                <td>Customer C</td>
                                                <td >Formal Jacket</td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="1250" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="584375" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="866250" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="72531" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="1523156" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="18" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="21" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="1732500" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="209344" /> </td>
                                                <td><fmt:formatNumber type="number" maxFractionDigits="2"  value="12.1" /> </td>
                                                
                                                
                                            </tr>
 										</tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
		</div>
		</div>
<div class="row">
<div class="col-sm-12">
	<div class="col-md-12 col-sm-12 col-xs-12 text-left">
		<div style="height:30px;">
			<input type="checkbox" id="ship_qty_val" name="ship_qty_val" />
			<label for="ship_qty_val" >Delivery Month &  order, shipment quantity</label>
		</div>
	</div>
</div>
</div>		
<div class="row" id="ship_val" >
<div class="col-sm-12">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                              Delivery Month &  order, shipment value in Crore
                            </div>
                            <div class="panel-body">
                                <div id="month_order_ship_val"></div>
                            </div>
                        </div>
                    </div>
           </div>	
           </div>
<div class="row" id="ship_qty" >
<div class="col-sm-12">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                              Delivery Month &  order, shipment quantity
                            </div>
                            <div class="panel-body">
                                <div id="month_order_ship_qty"></div>
                            </div>
                        </div>
                    </div>
           </div>	 
           </div>
<div class="row">
<div class="col-sm-12">
	<div class="col-md-12 col-sm-12 col-xs-12 text-left">
		<div style="height:30px;">
			<input type="checkbox" id="chk_plan_vs_act_val" name="chk_plan_vs_act_val" />
			<label for="chk_plan_vs_act_val" > Plan vs actual shipment quantity</label>
		</div>
	</div>
	</div>
</div>           
<div class="row" >
<div class="col-sm-12" id="plan_vs_act_val">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                              Plan vs actual shipment value in Crore
                            </div>
                            <div class="panel-body">
                                <div id="month_plan_vs_actual_val"></div>
                            </div>
                        </div>
                    </div>
           </div>
           </div>
           
<div class="row" >
<div class="col-sm-12" id="plan_vs_act_qty">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                              Plan vs actual shipment quantity
                            </div>
                            <div class="panel-body">
                                <div id="month_plan_vs_actual_qty"></div>
                            </div>
                        </div>
                    </div>
           </div>           
</div>           
<div class="row">
<div class="col-sm-12">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                             Excess consumption (value in Crore)
                            </div>
                            <div class="panel-body">
                                <div id="month_chem_consump_val"></div>
                            </div>
                        </div>
                    </div>
           </div>           
</div>           

           
<div class="row">
<div class="col-sm-12">
		<div class="col-md-12 col-sm-12 col-xs-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Key Performance Indicator
                            </div> 
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Indicator</th>
                                                <th>Analysis Period</th>
                                                <th>Current Value</th>
                                                <th>Previous Value</th>
                                                <th>Change</th>
                                                <th>Cumulative Value</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <tr>
                                                <td>1</td>
                                                <td class="text-left">Order value</td>
                                            	<td>Month</td>
                                                <td>6.2</td>
                                                <td>6.3</td>
                                                <td>-0.1</td>
                                                <td>24.8</td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td class="text-left">Purchase value</td>
                                                <td>Month</td>
                                                <td>20</td>
                                                <td>20</td>
                                                <td>0</td>
                                                <td>80</td>
                                               
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td class="text-left">Shipment value</td>
                                                <td>Month</td>
                                                <td>6.2</td>
                                                <td>6.5</td>
                                                <td>-0.3</td>
                                                <td>25.4</td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                               	<td class="text-left">Leather consumption</td>
                                               	<td>Month</td>
                                                <td>25</td>
                                                <td>22</td>
                                                <td>-3</td>
                                                <td>100</td>
                                            </tr>
                                            <tr>
                                                <td>5</td>
                                                <td class="text-left">Material Consumption</td>
                                               	<td>Month</td>
                                                <td>20</td>
                                                <td>20</td>
                                                <td>0</td>
                                                <td>80</td>
                                            </tr>
                                            <tr>
                                                <td>6</td>
                                                <td class="text-left" colspan="6"><b>Rejection during Production</b></td>
                                            </tr>
                                            
                                            <tr>
                                                <td></td>
                                                <td class="text-left">Cutting</td>
                                                <td>Month</td>
                                                <td>150</td>
                                                <td>120</td>
                                                <td>-30</td>
                                                <td>2200</td>
                                            </tr>
                                            
                                            <tr>
                                                <td></td>
                                                <td class="text-left">Components</td>
                                                <td>Month</td>
                                                <td>347</td>
                                                <td>350</td>
                                                <td>3</td>
                                                <td>3500</td>
                                            </tr>
                                            
                                            <tr>
                                                <td></td>
                                                <td class="text-left">Assembly</td>
                                                <td>Month</td>
                                                <td>345</td>
                                                <td>323</td>
                                                <td>-22</td>
                                                <td>3452</td>
                                            </tr>
                                            
                                             <tr>
                                                <td></td>
                                                <td class="text-left">QA</td>
                                                <td>Month</td>
                                                <td>215</td>
                                                <td>220</td>
                                                <td>5</td>
                                                <td>2145</td>
                                            </tr>
                                            
                                            <tr>
                                                <td>7</td>
                                                <td class="text-left">Excess Purchase</td>
                                              	<td>Month</td>
                                                <td>0.12</td>
                                                <td>0.11</td>
                                                <td>-0.01</td>
                                                <td>1.1</td>
                                            </tr>
                                            <tr>
                                                <td>8</td>
                                                <td class="text-left">Excess Consumption</td>
                                               <td>Month</td>
                                                <td>0.45</td>
                                                <td>0.42</td>
                                                <td>-0.03</td>
                                                <td>2.25</td>
                                            </tr>
                                             
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
</div>                    
  <script src="${pageContext.request.contextPath}/js/common/dashboard/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath}/js/common/dashboard/raphael-2.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/common/dashboard/morris.js"></script>
	<script src="${pageContext.request.contextPath}/js/common/dashboard/easypiechart.js"></script>
 
<script type="text/javascript">


//Donut chart
//Chemical stock value , in each store
    Morris.Donut({
        element: 'store_wise_stock',
        data: [
               {label: "Leather Store", value: 23},
               {label: "Non-Leather Store", value: 8.2},
               {label: "Sample Store", value: 1.2},
               {label: "Reject/Non Moving", value: 0.85},
             ],
		   colors: [
'#A6A6A6','#2DAFCB',
'#F98484' ,'#f0ad4e'
],
        resize: true
    });
    
// Leather stock value stage wise    
    Morris.Donut({
        element: 'stage_wise_stock',
        data: [
               {label: "Cutting ", value: 1.25},
               {label: "Components ", value: 4.75},
               {label: "Final Stitch", value: 5.25},
               {label: "QA", value: 1}
             ],
		   colors: [
'#A6A6A6','#2DAFCB',
'#F98484' ,'#f0ad4e'
],
        resize: true
    });
    
// Leather stock value    
    Morris.Donut({
        element: 'ltr_stk_val_age',
        data: [
               {label: "< 6 Months", value: 17.25},
               {label: "<1 Year", value: 4.5},
               {label: ">1 Year", value: 1.25},
             ],
		   colors: [
'#A6A6A6','#2DAFCB',
'#F98484' ,'#f0ad4e'
],
        resize: true
    });
    
//Bar chart

//Month and Order Ship value
    Morris.Bar({
        element: 'month_order_ship_val',
        data:[
        {
            y: 'Jan',
            a: 5.5,
			b: 5.2
        },
        {
            y: 'Feb',
            a: 5.2,
			b: 5.1
        }, {
            y: 'Mar',
            a: 5.6,
            b: 5.6
        }, {
            y: 'Apr',
            a: 5.8,
			b: 5.9
        }, {
            y: 'May',
            a: 5.5,
			b: 5.4
        }, {
            y: 'Jun',
            a: 5.4,
			b: 5.4
        }, {
            y: 'Jul',
            a: 5.5,
			b: 5.5
        }
        , {
            y: 'Aug',
            a: 5.7,
			b: 5.5
        }
        , {
            y: 'Sep',
            a: 5.9,
			b: 5.8
        }, {
            y: 'Oct',
            a: 6.2,
			b: 6.4
        }, {
            y: 'Nov',
            a: 6.3,
			b: 6.5
        }, {
            y: 'Dec',
            a: 6.2,
			b: 6.2
        } ] 
        ,
        xkey: 'y',
        /* ymax: 300, */
        ykeys: ['a', 'b'],
        labels: ['Order Value(Cr)','Shipment Value(Cr)'],
		 barColors: ['#2DAFCB','#A6A6A6'],
        hideHover: 'auto',
        resize: true
    });    
    
  //Month and Order Ship qty  
    Morris.Bar({
        element: 'month_order_ship_qty',
        data:[
        {
            y: 'Jan',
            a: 458333,
			b: 433333

        },
        {
            y: 'Feb',
            a: 433333,
			b: 425000

        }, {
            y: 'Mar',
            a: 466667,
            b: 466667
        }, {
            y: 'Apr',
            a: 483333,
			b: 491667
        }, {
            y: 'May',
            a: 458333,
			b: 450000
        }, {
            y: 'Jun',
            a: 450000,
			b: 450000
        }, {
            y: 'Jul',
            a: 458333,
			b: 458333
        }
        , {
            y: 'Aug',
            a: 475000,
			b: 458333
        }
        , {
            y: 'Sep',
            a: 491667,
			b: 483333
        }, {
            y: 'Oct',
            a: 516667,
			b: 533333
        }, {
            y: 'Nov',
            a: 525000,
			b: 541667

        }, {
            y: 'Dec',
            a: 516667,
			b: 516667
        } ] 
        ,
        xkey: 'y',
        /* ymax: 300, */
        ykeys: ['a', 'b'],
        labels: ['Order Qty(SFT)','Shipment Qty(SFT)'],
		 barColors: ['#2DAFCB','#A6A6A6'],
        hideHover: 'auto',
        resize: true
    });   
  
// Excess chemical consumption value
   Morris.Bar({
        element: 'month_chem_consump_val',
        data:[
        {
            y: 'Jan',
            a: 0.75,
//			b: 237970
        },
        {
            y: 'Feb',
            a: 0.5,
//            b: 237970
        }, {
            y: 'Mar',
            a: 2.1,
//			b: 135945
        }, {
            y: 'Apr',
          	a: 2.8,
//			b: 222335
        }, {
            y: 'May',
            a: 1,
//			b: 131705
        }, {
            y: 'Jun',
            a: 1,
//			b: 244595
        }, {
            y: 'Jul',
            a: 2.56,
//			b: 147207.5
        }
        , {
            y: 'Aug',
            a: 1.8,
//			b: 250160
        }
        , {
            y: 'Sep',
            a: 0.75,
//			b: 151421
        }, {
            y: 'Oct',
            a: 1.1,
//			b: 252836.5
        }, {
            y: 'Nov',
            a: 1.6,
//			b: 155661
        }, {
            y: 'Dec',
            a: 1.8,
//			b: 258401.5
        } ] 
        ,
        xkey: 'y',
        /* ymax: 300, */ 
        ykeys: ['a'],
        labels: ['Value'],
		 barColors: ['#2DAFCB','#A6A6A6'],
        hideHover: 'auto',
        resize: true
    });   
    
   
//Line chart
// Plan vs actual shipment 
 Morris.Line({
        element: 'month_plan_vs_actual_qty',
        data: [
				{ y: '2015-01', a: 440833, b: 430833},
				{ y: '2015-02', a: 402000, b: 422000},
				{ y: '2015-03', a: 520667, b: 468667},
				{ y: '2015-04', a: 414667, b: 476667},
				{ y: '2015-05', a: 500000, b: 455000},
				{ y: '2015-06', a: 469000, b: 446000},
				{ y: '2015-07', a: 416833, b: 461833},
				{ y: '2015-08', a: 451833, b: 463833},
				{ y: '2015-09', a: 565333, b: 481333},
				{ y: '2015-10', a: 469333, b: 529333},
				{ y: '2015-11', a: 588967, b: 543967},
				{ y: '2015-12', a: 461667, b: 515667}
			],
    
		 
xkey: 'y',
ykeys: ['a', 'b'],
labels: ['Plan Qty', 'Actual Qty'],
fillOpacity: 0.6,
hideHover: 'auto',
behaveLikeLine: true,
resize: true,
pointFillColors:['#ffffff'],
pointStrokeColors: ['black'],
lineColors:['#4CB1CF','#f0ad4e','#5cb85c']

    }); 
    
//Plan vs actual shipment 
 Morris.Line({
        element: 'month_plan_vs_actual_val',
        data: [
				{ y: '2015-01', a: 5.3, b: 5.1},
				{ y: '2015-02', a: 5.1, b: 5.2},
				{ y: '2015-03', a: 5.4, b: 5.3},
				{ y: '2015-04', a: 5.2, b: 5.4},
				{ y: '2015-05', a: 5.2, b: 5.3},
				{ y: '2015-06', a: 5.4, b: 5.8},
				{ y: '2015-07', a: 5.4, b: 5.3},
				{ y: '2015-08', a: 5.4, b: 5.4},
				{ y: '2015-09', a: 5.7, b: 5.6},
				{ y: '2015-10', a: 6.1, b: 5.8},
				{ y: '2015-11', a: 6.2, b: 5.9},
				{ y: '2015-12', a: 6.1, b: 6}
			],
    
		 
xkey: 'y',
ykeys: ['a', 'b'],
labels: ['Plan Value', 'Actual Value'],
fillOpacity: 0.6,
hideHover: 'auto',
behaveLikeLine: true,
resize: true,
pointFillColors:['#ffffff'],
pointStrokeColors: ['black'],
lineColors:['#4CB1CF','#f0ad4e','#5cb85c']

    });     
    
    
    
    
 vph = jQuery(window).height();
 jQuery('#page-wrapper').height(vph-72);

 jQuery('#ship_qty').hide();
 
 jQuery('#plan_vs_act_qty').hide();
 
 jQuery(document).ready(function(){
	 
	 jQuery("#ship_qty_val").click(function() {
		 
		 if(jQuery('#ship_qty_val').is(":checked")){
			 
			 jQuery("#ship_qty").show();
			 jQuery("#ship_val").hide();
		 }
		 else{
			 
			 jQuery("#ship_qty").hide();
			 jQuery("#ship_val").show();
		 }
	 });
	 
	jQuery("#chk_plan_vs_act_val").click(function() {
		 
		 if(jQuery('#chk_plan_vs_act_val').is(":checked")){
			 
			 jQuery("#plan_vs_act_qty").show();
			 jQuery("#plan_vs_act_val").hide();
		 }
		 else{
			 
			 jQuery("#plan_vs_act_qty").hide();
			 jQuery("#plan_vs_act_val").show();
		 }
	 });
	 
	 
 });
 
 
</script>


</div>
</div>
</div>


 
<input type="hidden" name="servlet_name" id="servlet_name"/>
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
<input type="hidden" name="pageno" id="pageno" value="${page_no}"/>
<input type="hidden" name="total_pages" id="total_pages" value="${page_count}"/>
<input type="hidden" name="request_type" id="request_type" value="${type_of_request}"/>
<input type="hidden" name="process_id" id="process_id" value="${process_id}"/>
<input type="hidden" name="auto_id" id="auto_id" value="${auto_id}"/>
<input type="hidden" name="mode" id="mode" value="${mode}"/>
 
</form>
</body>
</html>