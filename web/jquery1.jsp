<%-- 
    Document   : jquery1
    Created on : 24 Feb, 2020, 8:22:03 AM
    Author     : shobh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
        <script type="text/javascript">
            $(document).ready(function()
                    alert('hello');)
                    
                 $("button").onclick(function() {
                     alert('hello');
                 })   
            </script>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
