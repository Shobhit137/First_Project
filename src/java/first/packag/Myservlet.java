
package first.packag;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Myservlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Myservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Myservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       System.out.println("This is Get Request");
       response.setContentType("text/html");
       PrintWriter out=response.getWriter();
       out.println("<h2>This is Get Request");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       System.out.println("This is POST Request");
       response.setContentType("text/html");
       PrintWriter out=response.getWriter();
       out.println("<h2>This is POST Request");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
