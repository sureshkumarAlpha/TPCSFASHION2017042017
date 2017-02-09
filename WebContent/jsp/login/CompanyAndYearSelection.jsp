<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>TPCSFASHION</title>
<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/css/jenix.css" rel="stylesheet"/>

<link type="text/css" href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" /> 
<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/custom-styles.css" rel="stylesheet"/>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.js"></script>
  <script  type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/js/common/bootstrap/bootstrap.min.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/login/login.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script language="javascript">
var contextpath = "${pageContext.request.contextPath}";
</script>
<style>
body{
    background: #f9f9f9;
}
#page-wrapper{
background:transparent;
}
.sign-up-xs-out,
.sign-up-sm-out,
.sign-up-md-out, 
.sign-up-lg-out {
    border: 1px solid #eee;
    padding: 10px;
    background: #fff;
    border-radius: 5px;
}
.sign-up-xs-out{
    margin-top: 15%;
}


@media (min-width: 768px) {
    .sign-up-sm-out{
        margin-top: 8%;
    }
}

@media (min-width: 992px) {
    .sign-up-md-out{
        margin-top: 8%;
    }
}
@media (min-width: 1200px) {
    .sign-up-lg-out{
        margin-top: 8%;
    }
}
#page-wrapper {
    background: transparent;
}
#page-inner {
    width: 100%;
    background-color: transparent;
    padding: 10px;
    margin: 0px;
}
</style>
</head>
<body>
<form name="tpcslogin" id="tpcslogin" method="post">

<div id="wrapper">
		
		<div id="page-wrapper">
				<div id="page-inner">
				
				<div class=" col-xs-12 col-sm-7 col-md-7 col-lg-5 col-centered sign-up-xs-out sign-up-sm-out sign-up-md-out sign-up-lg-out">
				
					<div class="row">
								<div class="col-xs-12 col-sm-6 col-md-6 col-centered ">
									<img src="${pageContext.request.contextPath}/images/tpcsfashion.png" class="img-rounded img-responsive col-centered " alt="TPCS Fashion" height="50">
								</div>
					</div>
				
					<div class="row row-no-margin">
						<div id="message" class="text-center"></div>
					</div>
				

					<div class="row row-no-margin">
								
								<div class="col-xs-12 col-sm-8 col-centered">
									<label for="login_name">Company </label>
									<div class="form-group ">
										<select name="company_name" id="company_name"
											class="form-control">
											<c:forEach var="company" items="${company_list}">
												<option
													value="${company.companyId},${company.partyId},${company.currencyId}"><c:out
														value="${company.companyName}" /></option>
											</c:forEach>
										</select>
							        </div>
								</div>
								
					</div>

					<div class="row row-no-margin">
								 
								<div class="col-xs-12 col-sm-8 col-centered">
									<label for="organization">Year</label>
									<div class="form-group "> 
										<select name="year" id="year" class="form-control">
											<c:forEach var="objyear" items="${year_list}">
												<option
													value="<c:out value="${objyear.yearId},${objyear.startDate},${objyear.endDate}"/>"><c:out
														value="${objyear.startDate}" /> -
													<c:out value="${objyear.endDate}" /></option>
											</c:forEach>
										</select>
									</div>	
								</div>
					</div>
					
					 
					
					<div class="row row-no-margin " style="    margin-bottom: 20px;	">
						<div class="col-xs-6 col-sm-4 col-centered ">
							<button type="button" name="login" id="login" class="btn btn-success pull-left ladda-button btn-block" data-style="expand-right" onclick="displayHome()" >
								<span class="ladda-label"> <i class="glyphicon glyphicon-ok "></i></span>&nbsp;OK
							<span class="ladda-spinner"></span></button>
						</div>
						

					</div>
					
					<div class="row row-no-margin " style="font-size: 10px; text-align: center;    margin-bottom: 20px;">
						<p style="margin: 0px;">TERMS & CONDITIONS | PRIVACY POLICY | OUR AFFILIATE PROGRAM</p>
						<p style="margin: 0px;">© COPYRIGHTS JENIXCLOUD SOFTWARE SOLUTIONS PVT LTD. ALL RIGHTS RESERVED 2016</p>

					</div>
					
				</div>
			</div>
		</div>
		</div>
		<%-- <div id="wrapper">

			<div class="container">
				<div id="loginbox" style="margin-top: 100px;"
					class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-primary" style="border-radius: 15px;">
						<!-- <div class="panel-heading">
								<div class="panel-title">Sign In</div>
								<div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div>
							</div> -->

						<div class="panel-body">


							<div class="row">
								<div class="col-xs-12 col-sm-9 col-sm-offset-1 col-md-9 col-md-offset-1"
									style="text-align: center;">
									<img
										src="${pageContext.request.contextPath}/images/tpcsfashion.png"
										class="img-rounded img-responsive" alt="Cinque Terre" height="80" />
								</div>
							</div>
							<div class='row'>
								<div id="message" style="text-align: center;">&nbsp;</div>
							</div>

							<div class="row">
								<div class="col-sm-10 col-xs-10 col-sm-offset-1 col-md-offset-1">
									<label for="tag">Company</label>
									<div style="margin-bottom: 25px">
										<select name="company_name" id="company_name"
											class="form-control">
											<c:forEach var="company" items="${company_list}">
												<option
													value="${company.companyId},${company.partyId},${company.currencyId}"><c:out
														value="${company.companyName}" /></option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-10 col-xs-10 col-sm-offset-1 col-md-offset-1">
									<label for="tag">Year</label>
									<div style="margin-bottom: 25px">
										<select name="year" id="year" class="form-control">
											<c:forEach var="objyear" items="${year_list}">
												<option
													value="<c:out value="${objyear.yearId},${objyear.startDate},${objyear.endDate}"/>"><c:out
														value="${objyear.startDate}" /> -
													<c:out value="${objyear.endDate}" /></option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="row">

								<div style="margin-top: 10px; text-align: center;"
									class="form-group">
									<div class="col-sm-12 controls">
										<!-- Button -->
										<div class="col-sm-4 col-xs-4 col-md-offset-4 col-xs-offset-4">
											<button id="ok" onclick="displayHome()"
												class="btn btn-success btn-block">OK</button>
										</div>
									</div>
								</div>
							</div>
								
							<div class="row" style="font-size: 10px; text-align: center;  margin-top: 10px;">
								<p style="margin: 0px;">TERMS & CONDITIONS | PRIVACY POLICY
									| OUR AFFILIATE PROGRAM</p>
								<p style="margin: 0px;">© COPYRIGHTS ALPHA SYSTEMS PVT LTD.
									ALL RIGHTS RESERVED 2012</p>

							</div>
						</div>
					</div>
				</div>
			</div>

		</div> --%>

<input type="hidden" name="company_id" id="company_id"/>
<input type="hidden" name="party_id" id="party_id" value=""/>
<input type="hidden" name="currency_defaultid" id="currency_defaultid" value="${company.currencyId}"/>
<input type="hidden" name="year_id" id="year_id"/>
<input type="hidden" name="servlet_name" id="servlet_name"/>
<input type="hidden" name="callbackmethod_name" id="callbackmethod_name"/>
<input type="hidden" name="pageno" id="pageno" value="1"/>
</form>
</body>
</html>




