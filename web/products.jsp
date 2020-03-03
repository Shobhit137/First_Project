<%-- 
    Document   : products
    Created on : 28 Feb, 2020, 7:30:45 AM
    Author     : shobh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"import="com.beans.Products,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
        </head>
        <body>
        <%
            ArrayList<Products> products = new ArrayList();
            Products p = new Products(1, "Shoes", "Mens Shoes", "media/Products/shoes.jfif", 123);
        %>
    </body>
    <div class="container">
        <div class="row">
            <center>
                <h2>Tanku Shopping....</h2>
           
            <div class="col col-md-6">
                <img class="card-img-top" src="img_avatar1.png" alt="Card image" style="width:100%">
                <div class="card-body">
                    <h4 class="card-title">John Doe</h4>
                    <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
                    <a href="#" class="btn btn-primary">See Profile</a>
                </div>
            </div>
             </center>
        </div>
    </div>
</html>
