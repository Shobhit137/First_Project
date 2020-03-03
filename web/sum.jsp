<%-- 
    Document   : sum
    Created on : 27 Feb, 2020, 7:42:30 AM
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
        <%
            int a,b,c;
            a=request.getParameter("n1")!=null?Integer.parseInt(request.getParameter("n1")):0;
            b=request.getParameter("n2")!=null?Integer.parseInt(request.getParameter("n2")):0;
            c=a+b;
            %>
            <form method="post">
                First Number:<input type="text" name="n1"/>
                Second Number:<input type="text" name="n2"/>
                <input type="submit" name="submit" value="add"/>
            </form>
            <% 
                if(request.getParameter("submit")!=null)
                {
                    %>
                    <b>First No=<%=a%></b>
                    <b>Second No=<%=b%></b>
                    <h3>Sum=<%=c%></h3>
                }
            %>
    </body>
</html>
