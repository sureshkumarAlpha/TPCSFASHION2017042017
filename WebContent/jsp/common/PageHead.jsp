<%@page import="java.util.Map"%>
<%@page import="com.alpha.tpcsfashion.util.Validator"%>

<%
        		  	Map<Integer,String> subdocument=(Map<Integer,String>)session.getAttribute("subdocuments");
           			int subdocumentId=Validator.convertToInteger(request.getParameter("screen_name"));
           			String subdocumentName=subdocument.get(subdocumentId); 
           
%>

      <div class='col-sm-8'>
           <h3 class=""><%=subdocumentName %>
           </h3>
</div>