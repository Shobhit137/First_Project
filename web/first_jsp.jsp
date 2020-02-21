<%-- 
    Document   : first_jsp
    Created on : 18 Feb, 2020, 8:31:04 AM
    Author     : shobhit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1>My First JSP page</h1>
        <%
            //scriptlet
            
              int a=10,b=20;
              int c=a+b;
              System.out.println("Sum is="+ c);
              out.println("<h3>Sum= "+c+" <i> </h3>");
            %>
            <%-- Comments --%>
         <%-- Expression Tag   <%=%> --%>
         <%-- JPS Declaration  <%!   %>  --%>
         
         <%-- JPS Expression used to show/print vlaue of object or variable--%>
         <b>First number is <%=a%></b>
         <br/>Second Number is <%=b%>
         <table width="400" border="1">
             <% for(int i=0;i<10;i++)
             {
                 out.println("<tr><td>Hello</td></tr");
             } %>
             
         </table>
             
             <hr>
             <b><i>
                 Squares of Number from 1 to 10
                 </i></b>
             <table width="300" border="2">
                 <tr>
                     <td>Number</td> <td>Square</td>
                 </tr>
                
             </table>
    </body>
</html>
