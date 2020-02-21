/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.packag;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class UserData extends HttpServlet {

    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        String op = request.getParameter("op");
        
        if (op!=null && op.equalsIgnoreCase("delete")){
            Connection con =null;
            PreparedStatement smt=null;
            int id = Integer.parseInt(request.getParameter("id"));
            
              try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining","root","123456");
            String sql = "delete from user where id=?";
            smt = con.prepareStatement(sql);
            smt.setInt(1, id);
            //execute the command : executeUpdate()-for insert,update and delete or executeQuery()-for select

            int n = smt.executeUpdate();
            
            smt.close();
            con.close();
            if (n>0)
                //out.println("Data Inserted to table ...");
                response.sendRedirect("view.jsp");
            
        }catch(Exception e){
            System.out.println("Error : + "+ e.getMessage());
        
        } 
        }
        
        //response.sendRedirect("register.jsp");
        if(op!=null && op.equalsIgnoreCase("add")) {
        String name = request.getParameter("name");
        String fname =request.getParameter("fname");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String hobbies[] = request.getParameterValues("hobbies");
        String hbs="";
        //Rending the Information that has been received ..
      //  out.println("Name : "+ name +"<br/> Fname " + fname + "<br/>"
        //        + "dob : " + dob + "<br/>gender : " + gender +"<br/>Hobbies : <br/>");
        
        for (String h : hobbies){
              hbs +=  h ;
        }
            //out.println("<li>"+ h +"</li>");
         
        /*
        Enumeration form=request.getParameterNames();
        while(form.hasMoreElements())
        {
            String name=(String)form.nextElement();
            String values[]=request.getParameterValues(name);
            out.println(name+" : ");
            
            for(String s:values)
                out.println(s+"<br/>"); 
        }*/
        
        
        // JDBC Code
        
        Connection con=null;
        PreparedStatement smt=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining","root","123456");
            String sql = "Insert into user(name,fname,dob,gender,hobbies) values(?,?,?,?,?)";
            smt = con.prepareStatement(sql);
            smt.setString(1, name);
            smt.setString(2, fname);
            smt.setString(3, dob);
            smt.setString(4, gender);
            smt.setString(5, hbs);
            
            //execute the command : executeUpdate()-for insert,update and delete or executeQuery()-for select

            int n = smt.executeUpdate();
            if (n>0)
                out.println("Data Inserted to table ...");
            smt.close();
            con.close();
        }catch(Exception e)
        {
           System.out.println("Error : + "+ e.getMessage());
        }
    }
            
    }


   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
         String op = request.getParameter("op");
        
    if(op!=null && op.equalsIgnoreCase("add")) {
        String name = request.getParameter("name");
        String fname =request.getParameter("fname");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String user_id=request.getParameter("user_id");
        String password=request.getParameter("password");
       // String rpassword=request.getParameter("rpassword");
        String hobbies[] = request.getParameterValues("hobbies");
        String hbs="";
        //Rending the Information that has been received ..
      //  out.println("Name : "+ name +"<br/> Fname " + fname + "<br/>"
        //        + "dob : " + dob + "<br/>gender : " + gender +"<br/>Hobbies : <br/>");
        
        for (String h : hobbies){
              hbs +=  h ;
        }
            //out.println("<li>"+ h +"</li>");
         
        /*
        Enumeration form=request.getParameterNames();
        while(form.hasMoreElements())
        {
            String name=(String)form.nextElement();
            String values[]=request.getParameterValues(name);
            out.println(name+" : ");
            
            for(String s:values)
                out.println(s+"<br/>"); 
        }*/
        
        
        // JDBC Code
        
        Connection con=null;
        PreparedStatement smt=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining","root","123456");
            String sql = "Insert into user(name,fname,dob,gender,hobbies,user_id,password) values(?,?,?,?,?,?,?)";
            smt = con.prepareStatement(sql);
            smt.setString(1, name);
            smt.setString(2, fname);
            smt.setString(3, dob);
            smt.setString(4, gender);
            smt.setString(5, hbs);
            smt.setString(6,user_id);
            smt.setString(7, password);
           // smt.setString(8, rpassword);
            //execute the command : executeUpdate()-for insert,update and delete or executeQuery()-for select

            int n = smt.executeUpdate();
            smt.close();
            con.close();

            if (n>0)
                response.sendRedirect("view.jsp");
        }catch(Exception e)
        {
           System.out.println("Error : + "+ e.getMessage());
        }
    }
       if (op!=null && op.equalsIgnoreCase("update")){
        int id = Integer.parseInt(request.getParameter("id"));
        
        Connection con =null;
        PreparedStatement smt = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gisttraining","root","123456");
            String sql = "update user set name=?,fname=?,dob=?,gender=?,hobbies=? where id=?";
        String name = request.getParameter("name");
        String fname =request.getParameter("fname");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String hobbies[] = request.getParameterValues("hobbies");
        String hbs="";
        
        for (String h : hobbies)
               hbs +=  h + ",";
        
            
            
            smt = con.prepareStatement(sql);
            smt.setString(1, name);
            smt.setString(2, fname);
            smt.setString(3, dob);
            smt.setString(4, gender);
            smt.setString(5, hbs);
            smt.setInt(6, id);
            
            //execute the command : executeUpdate()-for insert,update and delete or executeQuery()-for select

            int n = smt.executeUpdate();
            
            smt.close();
            con.close();
            if (n>0)
                //out.println("Data Inserted to table ...");
                response.sendRedirect("view.jsp");
            
        }catch(Exception e){
            System.out.println("Error : + "+ e.getMessage());
        
        }
         
    }}}
    
   


