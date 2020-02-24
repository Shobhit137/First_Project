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
        <center>
            <h2>list of Users</h2>
            <table border="2" width="100%" cellspacing="0">
                <tr>

                    <th>ID</th>
                    <th>Name</th>
                    <th>Father's Name</th>
                    <th>DOB</th>
                    <th>Gender</th>
                    <th>Hobbies</th>
                    <th>Operations</th>
                </tr>
            <%
                Connection con = null;
                PreparedStatement smt;

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining", "root", "123456");
                    String sql = "select * from user";
                    smt = con.prepareStatement(sql);
                    ResultSet rs = smt.executeQuery();
                    while (rs.next()) {%>
            <tr>
                <td><%=rs.getString("id")%></td>
                <td><%=rs.getString("name")%></td>
                <td><%=rs.getString("fname")%></td>
                <td><%=rs.getString("dob")%></td>
                <td><%=rs.getString("gender")%></td>
                <td><%=rs.getString("hobbies")%></td>
                <td><a href="edit.jsp?id=<%=rs.getString("id")%> " class="btn btn-success"><i class="fa fa-pencil" aria-hidden="true"></i>Edit
                          </a>  ||  <a href="UserData?op=delete&id=<%=rs.getString("id")%>"class="btn btn-danger" onclick="return confirmation()" ><i class="fa fa-trash" aria-hidden="true"></i>Delete</a></td>
            </tr>
            <%}

                    con.close();
                    smt.close();
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            %>

        </table>
        <br/>
        <a href="Register1.jsp" class="btn btn-primary"><i class="fa fa-plus" aria-hidden="true"></i>Add More Records</a>
    </center>
</body>
</html>
