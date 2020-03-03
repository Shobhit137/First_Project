<%-- 
    Document   : view
    Created on : 20 Feb, 2020, 7:46:51 AM
    Author     : shobh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*;"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
            <script type="text/javascript">
                function confirmation()
                {
                    if (confirm("Do you really want to delete"))
                    {
                        return true;
                    } else
                    {
                        return false;
                    }
                }
            </script>
        </head>
        <body>
        <%!
            int start=0;
            int end=3;
            int total=0;
            %>
<!--            <div class="container">
                <div class="row">
                    <div class="col col-md-12">-->
        <center>
            <h2>list of Users</h2>
            <table border="5" width="100" cellspacing="1" class="table bg-light" cellpadding="2">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Father's Name</th>
                    <th>User Id</th>
                    <th>DOB</th>
                    <th>Gender</th>
                    <th>Hobbies</th>
                    <th>Photo</th>
                    <th>Operations</th>
                    <th>
                </tr>
            <%
               if(request.getParameter("start")!=null)
                 start=Integer.parseInt(request.getParameter("start"));
                
                Connection con = null;
                PreparedStatement smt;

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining", "root", "123456");
                    //String sql="select *from user";
                   String sql="select count(*) from user";
                    smt=con.prepareStatement(sql);
                    ResultSet rs=smt.executeQuery(sql);
                    if(rs.next())
                     total=rs.getInt(1);
                    
                    
                    sql = "select * from user limit ?,?";
                   
                    smt = con.prepareStatement(sql);
                   
                   smt.setInt(1, start);
                   smt.setInt(2, end);
                    rs = smt.executeQuery();
                    
                    while (rs.next()) {%>
            <tr>
                <td><%=rs.getString("id")%></td>
                <td><%=rs.getString("name")%></td>
                <td><%=rs.getString("fname")%></td>
                 <td><%=rs.getString("user_id")%></td>
                <td><%=rs.getString("dob")%></td>
                <td><%=rs.getString("gender")%></td>
                <td><%=rs.getString("hobbies")%></td>
                <td><%=rs.getString("photo")%></td>
                <td><img src="<%=rs.getString("photo")%>" style="width:60px; height:60px;" class="img img-thumbnail"></td>
                <td><a href="edit.jsp?id=<%=rs.getString("id")%> " class="btn btn-success"><i class="fa fa-pencil" aria-hidden="true"></i>Edit
                          </a>  ||  <a href="UserData?op=delete&id=<%=rs.getString("id")%>" class="btn btn-danger" onclick="return confirmation()" ><i class="fa fa-trash" aria-hidden="true"></i>Delete</a></td>
            </tr>
            <%}

                    con.close();
                    smt.close();
                } catch (Exception e) {
                    System.out.println("Error:" + e.getMessage());
                }
            %>

        </table>
            <span>
                <center>
                <a href="view.jsp?start=<%=start-end%>"class="btn btn-primary <%if(start==0)out.println("disabled");%>" style="float:left;">Prev</a>
                <a href="view.jsp?start=<%=start+end%>" class="btn btn-primary <%if(total-start<=end)out.println("disabled");%>" style="float:right;">Next</a>
                <%for(int i=0;i<=total/end;i++){%>
                <a href="view.jsp?start=<%=i*end%>" class="btn btn-success <%if(i==Math.floor(start/end))out.println("btn-dark");%>">Page<%=i+1%></a>
                 <%}%>
                 </center>
            </span>
                 <br>  
        <br/><br/>
        <a href="Register1.jsp" class="btn btn-primary"><i class="fa fa-plus" aria-hidden="true"></i>Add More Records</a>
    </center>
<!--</div>
</div>
</div>-->
</body>
</html>
