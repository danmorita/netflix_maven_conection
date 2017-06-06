// Import required java libraries
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import model.Usuario;
import controller.ControllerUsuario;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)throws IOException, ServletException
  {
      	String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        System.out.println("email :"+email);
        System.out.println("senha :"+senha);

        //validacao dos campos 
        Usuario user = null;
        try{
           user = ControllerUsuario.LoadByEmail(email);
         }catch(Exception err){
            err.printStackTrace();
         }
       

        if( user == null){
          //nao existe usuario com esse email
          response.sendRedirect("http://localhost:8080/netflix/us/entrar.jsp?emailerro=true&email="+email);

        }else{
          //existe usuario com esse email
          if(senha.equals(user.getSenha())){
                //logado com sucesso
              request.getSession().setAttribute("logado", new Boolean(true));
              request.getSession().setAttribute("usuario", user.getNome());
              if(user.getAdmin()){
                 request.getSession().setAttribute("admin",new Boolean(true));

              }
             
              response.sendRedirect("http://localhost:8080/netflix/timeline");
          }else{
                // senha incorreta 
              response.sendRedirect("http://localhost:8080/netflix/us/entrar.jsp?senhaerro=true&email="+email);
          }
        }
  }
  
}