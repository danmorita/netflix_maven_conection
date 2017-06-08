// Import required java libraries
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import controller.ControllerUsuario;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;


@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)throws IOException, ServletException
  {
              request.getSession().invalidate();   
              response.sendRedirect("http://localhost:8080/netflix");
        
       
  }
  
  
}