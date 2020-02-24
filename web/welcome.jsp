<%-- 
    Document   : welcome
    Created on : 24 Feb, 2020, 10:11:44 AM
    Author     : shobh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome <%=request.getParameter("name") %></h1>
        
    </body>
</html>
