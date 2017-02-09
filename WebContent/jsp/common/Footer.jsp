<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>

<%
Calendar cal = Calendar.getInstance();
SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
String currentDate = formatter.format(cal.getTime());
%>

<div id="ajaxloader" style="display:none"></div> 
<input type="hidden" name="fin_startdate" id="fin_startdate" value="${sessionScope.user_info.startDate}" />
<input type="hidden" name="fin_enddate" id="fin_enddate"  value="${sessionScope.user_info.endDate}" />
<input type="hidden" name="books_bgng_date" id="books_bgng_date"  value="${sessionScope.user_info.booksBegingDate}" />
<input type="hidden" name="current_date" id="current_date"  value="<%=currentDate%>" />
<%-- <script src="${pageContext.request.contextPath}/js/common/morris/raphael-2.1.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/morris/morris.js"></script>
<script src="${pageContext.request.contextPath}/js/common/easypiechart.js"></script> --%>
   
	