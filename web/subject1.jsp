<%-- 
    Document   : subject1
    Created on : 24 Feb, 2020, 4:15:34 PM
    Author     : shobh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
        <script>
            $(document).ready(function(){
                $("#s1").change(function(){
                    $("#td1").load("UserData?op=search&s1="+$(this).val());
                });
            });
        </script>
    </head>
    <body>
        <h1>Select </h1>
    <center>
        <div class="container" id="div1">
            <div class="row">
                <div class="col col-md-6 border-dark " style="text-align: left">
                    <form >
                        <!--<input type="text" name="subject" class="form-control" placeholder="Type Subject Name" required="required"/> -->
                        <select class="form-control" id="s1"></br>
                            <option>Select Subject</option>
                        <%
                            Connection con=null;
                            PreparedStatement smt=null;
                            Class.forName("com.mysql.jdbc.Driver");
                            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining","root","123456");
                            String sql="select *from subject";
                            smt=con.prepareStatement(sql);
                            ResultSet rs=smt.executeQuery();
                            while(rs.next())
                            {
                                %>
                                <option value="<%=rs.getString("id")%>"><%=rs.getString("name")%></option>
                                
                            
                            <%}%>
                        </select>
                        <br>
                        <br>
                        <table id="td1" class="col col-md-6" border="2">
                            <tr><td>Course</td></tr>
                        </table>
                        </br>
                        
                    </form>
                </div>
            </div>
        </div>
    </center>
    
    </body>
</html>
