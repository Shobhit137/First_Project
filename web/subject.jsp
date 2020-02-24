<%-- 
    Document   : subject
    Created on : 24 Feb, 2020, 7:26:46 AM
    Author     : shobh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
    <center>
        <div class="container">
            <div class="row">
                <div class="col col-md-6 border-dark" style="text-align: left">
                    <form action="SubjectController?op=add" method="post" class="form">
                        <input type="text" name="subject" class="form-control" placeholder="Enter Subject Name" required="required"/>
                        </br>
                        <h4>Select Course</h4>
                    <%
                        //fetch list of courses
                        Connection con=null;
                        PreparedStatement smt=null;
                        try{
                            Class.forName("com.mysql.jdbc.Driver");
                            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining","root","123456");
                            String sql="select *from course";
                            smt=con.prepareStatement(sql);
                            ResultSet rs=smt.executeQuery();
                            
                            while(rs.next())
                            {
                                %>
                                <input type="checkbox" name="courseIds" value="<%=rs.getString("id")%>"/><%=rs.getString("name")%><br/>
                                <%
                            }
                        }catch(Exception e)
                        {
                            System.out.println("Error"+e.getMessage());
                        }
                        %>
                        </br>
                        <input type="submit" value="save"/>
                    </form>
                </div>
            </div>
        </div>
    </center>
    </body>
</html>
