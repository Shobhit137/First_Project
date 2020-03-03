
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringEscapeUtils;

public class UserControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String op=request.getParameter("op");
        if (op != null && op.equals("varify")) {
            String userid = request.getParameter("user_id");
            if (userid == null || userid.equals("")) {
                out.print("<b> Plese fillout the userid</b>");
                return;
            }
            out.println("Hello There");
            Connection con = null;
            PreparedStatement smt = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining", "root", "123456");
                String sql = "select * from user where user_id=?";
                smt = con.prepareStatement(sql);
                smt.setString(1, userid);
                //execute the command : executeUpdate()-for insert,update and delete or executeQuery()-for select
                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    out.println("<font color='red' size='4' face='corbel'>Sorry! this userid is not available</font>");
                } else {
                    out.println("<font color='blue' size='4' face='corbel'> Congrats! this userid is available!</font>");
                }
                smt.close();
                con.close();

            } catch (Exception e) {
                System.out.println("Error : + " + e.getMessage());

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String op = request.getParameter("op");

        if (op != null && op.equals("add")) {

            //check the enctype of the incomming request -
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            String name = "";
            String fname = "";
            String dob = "";
            String gender = "";
            String userid = "";
            String password = "";
            String hobbies[] = null;
            String photo = "";
            String imagePath = "";
            String hbs = "";
            String encodedPassword = "";
            List<String> checkboxlist = new ArrayList();

            if (!isMultipart) {
                name = StringEscapeUtils.escapeHtml(request.getParameter("name"));
                fname = StringEscapeUtils.escapeHtml(request.getParameter("fname"));
                dob = request.getParameter("dob");
                gender = request.getParameter("gender");
                userid = request.getParameter("userid");
                password = request.getParameter("password");
                hobbies = request.getParameterValues("hobbies");
                photo = request.getParameter("photo");
                hbs = "";
                //Rending the Information that has been received ..
                //  out.println("Name : "+ name +"<br/> Fname " + fname + "<br/>"
                //          + "dob : " + dob + "<br/>gender : " + gender +"<br/>Hobbies : <br/>");

                for (String h : hobbies) {
                    hbs += h + ",";
                }

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.getMessage();
                }

                Iterator itr = items.iterator();
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();
                        if (fieldName.equals("name")) {
                            name = fieldValue;
                        } else if (fieldName.equals("fname")) {
                            fname = fieldValue;
                        } else if (fieldName.equals("dob")) {
                            dob = fieldValue;
                        } else if (fieldName.equals("user_id")) {
                            userid = fieldValue;
                        } else if (fieldName.equals("password")) {
                            password = fieldValue;
                        } else if (fieldName.equals("gender")) {
                            gender = fieldValue;
                        } else if (fieldName.equals("hobbies")) {
                            checkboxlist.add(fieldValue);
                        }

                    } else {
                        try {
                            photo = item.getName();
                            imagePath = "media/user/" + photo;
                            File savedFile = new File(getServletContext().getRealPath("/") + imagePath);
                            item.write(savedFile);
                        } catch (Exception e) {
                            out.println("Error  " + e.getMessage());
                        }
                    }

                    hbs = "";
                    for (String s : checkboxlist) {
                        hbs += s + ",";
                    }
                }
            }

            //=============================================//
            //JDBC Code 
            Connection con = null;
            PreparedStatement smt = null;
            encodedPassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
            try {
                out.println("Hello");
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining", "root", "123456");
                String sql = "Insert into user(name,fname,dob,gender,hobbies,user_id,password,photo) values(?,?,?,?,?,?,?,?)";
                smt = con.prepareStatement(sql);
                smt.setString(1, name);
                smt.setString(2, fname);
                smt.setString(3, dob);
                smt.setString(4, gender);
                smt.setString(5, hbs);
                smt.setString(6, userid);
                smt.setString(7, encodedPassword);
                System.out.println(imagePath);
                smt.setString(8, imagePath);
               // out.println("Hello");
                //execute the command : executeUpdate()-for insert,update and delete or executeQuery()-for select

                int n = smt.executeUpdate();

                smt.close();
                con.close();
                if (n > 0) //out.println("Data Inserted to table ...");
                {
                    response.sendRedirect("view.jsp");
                }

            } catch (Exception e) {
                System.out.println("Error : + " + e.getMessage());
//                    if (e.getMessage().contains("Duplicate")) {
//                        out.println("<font color='red' size='5' face='corbel'> the Userid you entered is not available</font>");
//                        out.println("<hr/>");
//                        RequestDispatcher rd = request.getRequestDispatcher("Register1.jsp");
//                        rd.include(request, response);
//                    }

            }
        }
        if (op != null && op.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String pic=request.getParameter("pic");
            int flag=0;
            String name = "";
            String fname = "";
            String hobbies[] = null;
            String dob = "";
            String gender = "";
            String hbs = "";
            String photo = "";
            String imagePath = "";
            List<String> checkboxlist = new ArrayList();
            
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            out.println("Hello World");
            if (!isMultipart) {
                out.println("This is Multipart");
                name = StringEscapeUtils.escapeHtml(request.getParameter("name"));
                fname = StringEscapeUtils.escapeHtml(request.getParameter("fname"));
                dob = request.getParameter("dob");
                gender = request.getParameter("gender");
                hobbies = request.getParameterValues("hobbies");
                hbs = "";
                for (String h : hobbies) {
                    hbs += h + ",";
                }
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    
                    System.out.println(e.getMessage());
                }

                Iterator itr = items.iterator();
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();
                        if (fieldName.equals("name")) {
                            name = fieldValue;
                        } else if (fieldName.equals("fname")) {
                            fname = fieldValue;
                        } else if (fieldName.equals("dob")) {
                            dob = fieldValue;
                        } else if (fieldName.equals("gender")) {
                            gender = fieldValue;
                        } else if (fieldName.equals("hobbies")) {
                            checkboxlist.add(fieldValue);
                        }

                    } else {
                        try {
                            photo = item.getName();
                            if(item.getName().isEmpty())
                                flag=1;
                            else
                            {
                                flag=0;
                            imagePath = "media/user/" + photo;
                            File savedFile = new File(getServletContext().getRealPath("/") + imagePath);
                            item.write(savedFile); }
                        } catch (Exception e) {
                            out.println("Error " + e.getMessage());
                        }
                    }
                    
                    hbs = "";
                    for (String s : checkboxlist) {
                        hbs += s + ",";
                    }
                }
            }
            ////////////////////////////////////////////////////////////////////////////
            Connection con = null;
            PreparedStatement smt = null;

            try {

                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining", "root", "123456");
                String sql = "update user set name=?,fname=?,dob=?,gender=?,hobbies=?,photo=? where id=?";
                


                smt = con.prepareStatement(sql);

                smt.setString(1, name);
                smt.setString(2, fname);

                smt.setString(3, dob);
                smt.setString(4, gender);
                smt.setString(5, hbs);
                if(flag==1)
                {
                    smt.setString(6, pic);
                }
                else
                {
                    smt.setString(6,imagePath);
                }
                smt.setInt(7,id);
                
                
                int n = smt.executeUpdate();

                smt.close();
                con.close();
                if (n > 0) //out.println("Data Inserted to table ...");
                {
                    response.sendRedirect("view.jsp");
                }

            } catch (Exception e) {
                System.out.println("Error there is error: + " + e.getMessage());

            }

        }
         if (op != null && op.equalsIgnoreCase("login")) {
                String user_id = request.getParameter("user_id");
                String password = request.getParameter("password");
                Connection con = null;
                PreparedStatement smt = null;
                String encodedpassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining", "root", "123456");
                    String sql = "select * from user where user_id=? and password=?";
                    smt = con.prepareStatement(sql);
                    smt.setString(1, user_id);
                    smt.setString(2, encodedpassword);

                    ResultSet rs = smt.executeQuery();

                    if (rs.next()) {
                        response.sendRedirect("welcome.jsp?name=" + rs.getString("name"));
                    } else {
                        response.sendRedirect("Admin.jsp?msg=Invalid Userid or Password");
                    }

                    con.close();
                    smt.close();

                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
    }

}
