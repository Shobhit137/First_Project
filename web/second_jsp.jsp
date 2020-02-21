<%-- 
    Document   : second_jsp
    Created on : 18 Feb, 2020, 9:42:09 PM
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
        <a href="Myservlet">Click to open servlet</a>
        <br>
        <form action="Myservlet" method="get">
            <input type="submit" value="submit using GET"/>
        </form>

        <br>
        <form action="Myservlet" method="post">
            <input type="submit" value="submit using POST"/>
        </form>

    </body>
</html>
