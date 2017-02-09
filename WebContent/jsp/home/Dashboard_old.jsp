<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<jsp:include page="../common/ValidateUser.jsp" />
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

<div id="page-wrapper">
<div id="page-inner">


                <div class="row">
                    <div class="col-md-11 col-sm-8 col-xs-8">
                        <h3 class="page-header">
                            Tpcsfashion Dashboard
                        </h3>						
                    </div>
                  <!--   <div class="col-md-1 col-sm-4 col-xs-4 pull-right">
                        <img src="images/jenixerp.png" class="img-rounded" alt="Cinque Terre" width="150" height="60">						
                    </div> -->
                </div>			

                <div class="row">
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-green light-green">
                            <div class="panel-left pull-left light-green">
                                <i class="fa fa-bar-chart-o fa-5x"></i>
                                
                            </div>
                            <div class="panel-right ">
                            <div>
                            <h3>Rupees</h3>
								<h3>16,817,333.57</h3><h3>&nbsp;<!-- 416700 --><fmt:formatNumber type="number" maxFractionDigits="2"  value="${home_page.orderValue }" /></h3>
                               <strong> </strong>
                               </div>
                            </div>
                        </div>
                        
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4>Orders</h4>
								<!-- <div class="easypiechart" id="easypiechart-blue" data-percent="84"><span class="percent">84%</span>
								</div> -->
							</div>
					</div>
                        
                    </div>
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-green green">
                              <div class="panel-left pull-left green">
                                <i class="fa fa-shopping-cart fa-5x"></i>
								</div>
                                
                            <div class="panel-right ">
                            <div> <h3>Rupees</h3>
							<h3>10,317,976.57</h3><h3>&nbsp;<!-- 375030  --><fmt:formatNumber type="number" maxFractionDigits="2"  value="${home_page.invoiceValue }" /></h3>
                               <strong> </strong>
							</div>
                            </div>
                        </div>
                        
	                        <div class="panel panel-default">
						<div class="panel-body easypiechart-panel">
							<h4>Purchase </h4>
							<!-- <div class="easypiechart" id="easypiechart-orange" data-percent="71" ><span class="percent">71%</span>
							</div> -->
						</div>
					</div>
				
                    </div>
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-red red">
                            <div class="panel-left pull-left red">
                                <i class="fa fa fa-comments fa-5x"></i>
                               
                            </div>
                            <div class="panel-right ">
                            <div> <h3>Rupees</h3>
							<h3>8,657,976.35</h3><h3>&nbsp;<!-- 621256 --><fmt:formatNumber type="number" maxFractionDigits="2"  value="${home_page.cashBankValue }" /></h3>
                               <strong> </strong>
							</div>
                            </div>
                        </div>
                        
                        <div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>Cash & Bank balance</h4>
						<!-- <div class="easypiechart" id="easypiechart-teal" data-percent="40" ><span class="percent">40%</span>
						</div> -->
					</div>
				</div>
                        
                    </div>
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-blue blue">
                            <div class="panel-left pull-left blue">
                                <i class="fa fa-users fa-5x"></i>
                                
                            </div>
                            <div class="panel-right ">
                            <div> <h3>Rupees</h3>
							<h3>-18,277,376.87</h3><h3>&nbsp;<!-- 75600 --><fmt:formatNumber type="number" maxFractionDigits="2"  value="${home_page.receivableValue}" /></h3>
                             <strong></strong>
								</div>
                            </div>
                        </div>
                        
                        <div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4><a href="javascript:showDashboardReceivablesAgingAnalysis();">Receivables</a></h4>
						
						<!-- <div class="easypiechart" id="easypiechart-red" data-percent="60" ><span class="percent">60%</span>
						</div> -->
					</div>
				</div>
                        
                        
                    </div>
                    <div class="col-md-5ths col-sm-12 col-xs-12">
                        <div class="panel panel-primary text-center no-boder bg-color-brown brown">
                            <div class="panel-left pull-left brown">
                                <i class="fa fa-users fa-5x"></i>
                                
                            </div>
                            <div class="panel-right ">
                            <div> <h3>Rupees</h3>
							<h3>7,567,45</h3><h3>&nbsp;<!-- 44520 --><fmt:formatNumber type="number" maxFractionDigits="2"  value="${home_page.payableValue}" /></h3>
                             <strong></strong>
							</div>			
                            </div>
                        </div>
                        
                        <div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>Payables</h4>
						<!-- <div class="easypiechart" id="easypiechart-red" data-percent="60" ><span class="percent">60%</span>
						</div> -->
					</div>
				</div>
                        
                    </div>
                </div>
				
		
	
		
		<div class="row">
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
                                                <td>Order</td>
                                                <td>Month</td>
                                                <td>2,300.45</td>
                                                <td >857,716</td>
                                                <td>-855,415.42 </td>
                                                <td>6,817,333.57 </td>
                                               
                                            </tr>
                                         <tr>
                                                 <td>2</td>
                                                <td>New Business</td>
                                                <td>Month</td>
                                                <td>0</td>
                                                <td >2,300.57</td>
                                                <td>-2,300.57 </td>
                                                <td> </td>
                                               
                                            </tr>
                                         <tr>
                                                 <td>3</td>
                                                <td>Income</td>
                                                <td>Month</td>
                                                <td>0</td>
                                                <td >0</td>
                                                <td>0 </td>
                                                <td>0 </td>
                                               
                                            </tr>
                                         <tr>
                                                 <td>3</td>
                                                <td>Expense</td>
                                                <td>Month</td>
                                                <td>0</td>
                                                <td >0</td>
                                                <td>0 </td>
                                                <td>0 </td>
                                               
                                            </tr>
                                            <tr>
                                                 <td>3</td>
                                                <td>Profit</td>
                                                <td>Month</td>
                                                <td>0</td>
                                                <td >0</td>
                                                <td>0 </td>
                                                <td>0 </td>
                                               
                                            </tr>
                                            <tr>
                                                 <td>3</td>
                                                <td>Total Cash & Bank</td>
                                                <td>Month</td>
                                                <td>0</td>
                                                <td >0</td>
                                                <td>0 </td>
                                                <td>0 </td>
                                               
                                            </tr>
                                            <tr>
                                                 <td>3</td>
                                                <td>Receivables</td>
                                                <td>As on date</td>
                                                <td>0</td>
                                                <td >0</td>
                                                <td>0 </td>
                                                <td>0 </td>
                                               
                                            </tr>	
                                             <tr>
                                                 <td>3</td>
                                                <td>Payables</td>
                                                <td>As on date</td>
                                                <td>0</td>
                                                <td >0</td>
                                                <td>0 </td>
                                                <td>0 </td>
                                               
                                            </tr>				
                                         
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
		</div>
			
		<div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Monthly Cash in and out
                            </div>
                            <div class="panel-body">
                                <div id="morris-bar-chart"></div>
                            </div>
                        </div>
                    </div>
           </div>	
			
			
			
			
				<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							Profit analysis
						</div>
						<div class="panel-body">
						 <div class="table-responsive">
						 <table class="table table-striped table-bordered table-hover">
						 	<tr><td>
							<div id="morris-line-chart"></div>
							</td></tr>
							</table>
							</div>
						</div>
					</div>  
					</div>		
				</div> 
				
				
				
               

	   
				
				
				
                <div class="row">
                    <div class="col-md-4 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Tasks Panel
                            </div>
                            <div class="panel-body">
                                <div class="list-group">

                                    <a href="#" class="list-group-item">
                                        <span class="badge">7 minutes ago</span>
                                        <i class="fa fa-fw fa-comment"></i> Commented on a post
                                    </a>
                                    <a href="#" class="list-group-item">
                                        <span class="badge">16 minutes ago</span>
                                        <i class="fa fa-fw fa-truck"></i> Order 392 shipped
                                    </a>
                                    <a href="#" class="list-group-item">
                                        <span class="badge">36 minutes ago</span>
                                        <i class="fa fa-fw fa-globe"></i> Invoice 653 has paid
                                    </a>
                                    <a href="#" class="list-group-item">
                                        <span class="badge">1 hour ago</span>
                                        <i class="fa fa-fw fa-user"></i> A new user has been added
                                    </a>
                                    <a href="#" class="list-group-item">
                                        <span class="badge">1.23 hour ago</span>
                                        <i class="fa fa-fw fa-user"></i> A new user has added
                                    </a>
                                    <a href="#" class="list-group-item">
                                        <span class="badge">yesterday</span>
                                        <i class="fa fa-fw fa-globe"></i> Saved the world
                                    </a>
                                </div>
                                <!-- <div class="text-right">
                                    <a href="#">More Tasks <i class="fa fa-arrow-circle-right"></i></a>
                                </div>-->
                            </div>
                        </div>

                    </div>
                    <div class="col-md-8 col-sm-12 col-xs-12">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Projected Cash on hand
                            </div> 
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Particulars</th>
                                                <th>Receivables</th>
                                                <th>Payables</th>
                                                <th>Projected Cash on hand</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>Cash / Bank Balance</td>
                                                <td>0</td>
                                                <td >0</td>
                                                <td>215000</td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>Q1</td>
                                                <td>1089000</td>
                                                <td>655875</td>
                                                <td>648125</td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>Q2</td>
                                                <td>1089000</td>
                                                <td>655875</td>
                                                <td>1081250</td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>Q3</td>
                                                <td>1184700</td>
                                                <td>713512.5</td>
                                                <td>1552437.5</td>
                                            </tr>
                                            <tr>
                                                <td>5</td>
                                                <td>Q4</td>
                                                <td>1221000</td>
                                                <td>735375</td>
                                                <td>2038062.5</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                   
                </div>             
            </div>
                 	

                 

	   
				
				
				
                         
            </div>
         </div>       
       
          <jsp:include page="../common/Footer.jsp"/>
          
          <script src="${pageContext.request.contextPath}/js/common/dashboard/jquery.metisMenu.js"></script>
	<script src="${pageContext.request.contextPath}/js/common/dashboard/raphael-2.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/common/dashboard/morris.js"></script>
	<script src="${pageContext.request.contextPath}/js/common/dashboard/easypiechart.js"></script>
          
 <script language="javascript">
var c = 84;//Math.floor(Math.random() * 100) + 1;
var s =71;// Math.floor(Math.random() * 100) + 1;
var p = 40;//Math.floor(Math.random() * 100) + 1;
var v = 60;//Math.floor(Math.random() * 100) + 1;
loaddashboard(25,50,25,c,s,p,v);

function loaddashboard(dsales,msales,isales,c,s,p,v){
	
	    $('#easypiechart-teal').attr("data-percent",p);
	    $('#easypiechart-teal').easyPieChart({
	        scaleColor: false,
	        barColor: '#1ebfae'
	    });
	    
	    $('#easypiechart-orange').attr("data-percent",s);
	    $('#easypiechart-orange').easyPieChart({
	        scaleColor: false,
	        barColor: '#ffb53e'
	    });
	    
	    $('#easypiechart-red').attr("data-percent",v);
	    $('#easypiechart-red').easyPieChart({
	        scaleColor: false,
	        barColor: '#f9243f'
	    });
	    
	    $('#easypiechart-blue').attr("data-percent",c);
	    $('#easypiechart-blue').easyPieChart({
	        scaleColor: false,
	        barColor: '#30a5ff'
	    });
	    
       	
	    
	Morris.Bar({
        element: 'morris-bar-chart',
        data:[
               /* <c:forEach var="cio" items="${home_page.cashInOutList}">
        		{    y: '${cio.monthName}',
            		a: ${cio.debitFcy},
            		b: ${cio.creditFcy}
        		}
       			<c:if test="${cio.displayOrder ne 12}">
       				,
       			</c:if>
      			</c:forEach>  */
      		
               
       
             {
            y: 'Feb',
            a: 332800,
            b: 237970
        }, {
            y: 'Mar',
            a: 218400,
            b: 135945
        }, {
            y: 'Apr',
            a: 310000,
            b: 222335
        }, {
            y: 'May',
            a: 211200,
            b: 131705
        }, {
            y: 'Jun',
            a: 342800,
            b: 244595
        }, {
            y: 'Jul',
            a: 234400,
            b: 147207.5
        }
        , {
            y: 'Aug',
            a: 351000,
            b: 250160
        }
        , {
            y: 'Sep',
            a: 240680,
            b: 151421
        }, {
            y: 'Oct',
            a: 354920,
            b: 252836.5
        }, {
            y: 'Nov',
            a: 246880,
            b: 155661
        }, {
            y: 'Dec',
            a: 363120,
            b: 258401.5
        } 
      ]  ,
        xkey: 'y',
        /* ymax: 300, */ 
        ykeys: ['a', 'b'],
        labels: ['Cash In', 'Cash Out'],
		 barColors: ['#7fbf7f','#ffc966','#67C69D'],
        hideHover: 'auto',
        resize: true
    });
/* 	Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
            label: "Download Sales",
            value: dsales
        }, {
            label: "In-Store Sales",
            value: isales
        }, {
            label: "Mail-Order Sales",
            value: msales
        }],
		   colors: [
'#A6A6A6','#2DAFCB',
'#F98484' 
],
        resize: true
    }); */

    /* MORRIS AREA CHART
	----------------------------------------*/

   /*  Morris.Area({
        element: 'morris-area-chart',
        data: [{
            period: '2010 Q1',
            za: 2666,
            zb: null,
            zc: 2647
        }, {
            period: '2010 Q2',
            za: 2778,
            zb: 2294,
            zc: 2441
        }, {
            period: '2010 Q3',
            za: 4912,
            zb: 1969,
            zc: 2501
        }, {
            period: '2010 Q4',
            za: 3767,
            zb: 3597,
            zc: 5689
        }, {
            period: '2011 Q1',
            za: 6810,
            zb: 1914,
            zc: 2293
        }, {
            period: '2011 Q2',
            za: 5670,
            zb: 4293,
            zc: 1881
        }, {
            period: '2011 Q3',
            za: 4820,
            zb: 3795,
            zc: 1588
        }, {
            period: '2011 Q4',
            za: 15073,
            zb: 5967,
            zc: 5175
        }, {
            period: '2012 Q1',
            za: 10687,
            zb: 4460,
            zc: 2028
        }, {
            period: '2012 Q2',
            za: 8432,
            zb: 5713,
            zc: 1791
        }],
        xkey: 'period',
        ykeys: ['za', 'zb', 'zc'],
        labels: ['Zone A', 'Zone B', 'Zone C'],
        pointSize: 2,
        hideHover: 'auto',
		  pointFillColors:['#ffffff'],
		  pointStrokeColors: ['black'],
		  lineColors:['#A6A6A6','#2DAFCB'],
        resize: true
    }); */

    /* MORRIS LINE CHART
	----------------------------------------*/
     Morris.Line({
        element: 'morris-line-chart',
        data: [
               /* <c:forEach var="pa" items="${home_page.profitAnalysisList}">
       		{    y: '${pa.yearMonth}',
           		a: ${pa.income},
           		b: ${pa.expense},
           		c: ${pa.profit}
       		}
      			<c:if test="${pa.displayOrder ne 12}">
      				,
      			</c:if>
     			</c:forEach> */
     			
			   { y: '2015-01', a: 352000, b: 212000, c:140000},
			  { y: '2015-02', a: 374000,  b: 225250, c:148750},
			  { y: '2015-03', a: 363000,  b: 218625, c:144375},
			  { y: '2015-04', a: 341000,  b: 205375, c:135625},
			  { y: '2015-05', a: 363000,  b: 218625, c:144375},
			  { y: '2015-06', a: 385000,  b: 231875, c:153125},
			  { y: '2015-07', a: 390500, b: 235187.5, c:155312.5},
			  { y: '2015-08', a: 396000, b: 238500, c:157500},
			  { y: '2015-09', a: 398200, b: 239825, c:158375},
			  { y: '2015-10', a: 401500, b: 241812.5, c:159687.5},
			  { y: '2015-11', a: 407000, b: 245125, c:161875},
			  { y: '2015-12', a: 412500, b: 248437.5, c:164062.5} 	
		],
    
		 
xkey: 'y',
ykeys: ['a', 'b', 'c'],
labels: ['Income', 'Expense', 'Profit'],
fillOpacity: 0.6,
hideHover: 'auto',
behaveLikeLine: true,
resize: true,
pointFillColors:['#ffffff'],
pointStrokeColors: ['black'],
lineColors:['#4CB1CF','#f0ad4e','#5cb85c']

    }); 
    
   /*  var data = [
                { m: '2015-01', a: 50, b: 90,c:10},
                { m: '2015-02', a: 65,  b: 75,c:10},
                { m: '2015-03', a: 50,  b: 50,c:10},
                { m: '2015-04', a: 75,  b: 60,c:10},
                { m: '2015-05', a: 80,  b: 65,c:10},
                { m: '2015-06', a: 90,  b: 70,c:10},
                { m: '2015-07', a: 100, b: 75,c:10},
                { m: '2015-08', a: 115, b: 75,c:10},
                { m: '2015-09', a: 120, b: 85,c:10},
                { m: '2015-10', a: 145, b: 85,c:10},
                { m: '2015-11', a: 160, b: 95,c:10},
                { m: '2015-12', a: 160, b: 95,c:10}
              ],
              config = {
                data: data,
                xkey: 'm',
                ykeys: ['a', 'b','c'],
                labels: ['Total Income', 'Total Outcome','Expense'],
                fillOpacity: 0.6,
                hideHover: 'auto',
                behaveLikeLine: true,
                resize: true,
                pointFillColors:['#ffffff'],
                pointStrokeColors: ['black'],
                lineColors:['gray','red','blue']
            };
          config.element = 'morris-line-chart';
          Morris.Line(config); */
}



      
</script>
	

 
    
   <input type="hidden" name="pageno" id="pageno" />
   <input type="hidden" name="servlet_name" id="servlet_name"/>
   <input type="hidden" name="request_type" id="request_type"/>
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
<input type="hidden" name="invoke_servlet" id="invoke_servlet"  /> 
<input type="hidden" name="invoke_method" id="invoke_method" />

</div>
</form>
  </body>
</html>
           
<style>

.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover {
  color: #FFFFFF;
  cursor: default;
  background-color: #024688;
  border: 1px solid #ddd;
  border-bottom-color: transparent;
}

.nav-tabs>li>a {
  color: #FFFFFF;
  margin-right: 2px;
  line-height: 1.42857143;
  border: 1px solid transparent;
  border-radius: 4px 4px 0 0;
  background-color: #0B3861;
}

.nav>li>a:focus, .nav>li>a:hover {
	color: #FFFFFF ;
  text-decoration: none;
  background-color: #024688;
}
 */
.nav-tabs>li>a:hover {
  border-color: #024688 #024688 #ddd;
}
.panel-primary{
display:inline-block;
margin-bottom:30px;
width:100%;
}
.panel{
border-radius:0px;
}
</style>           

