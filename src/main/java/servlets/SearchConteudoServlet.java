import controller.ControllerPosts;
import model.Posts;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.sql.SQLException;
import database.ConexaoJDBC;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.google.gson.Gson;
import model.JsonPosts;


@WebServlet(urlPatterns = "/searchconteudo")
public class SearchConteudoServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)throws IOException
  {
    //response.getWriter().println("danilomorita");
    HttpSession session = request.getSession();
    String procura = request.getParameter("pesquisar");

    if(session.getAttribute("logado")!= null){
      if(procura != null){
            //pagina protegida por login 
          ArrayList<Posts> posts = new ArrayList<Posts>();
          JsonPosts jp = new JsonPosts();
          try{
            ControllerPosts controllerposts = new ControllerPosts();
            posts = controllerposts.findByConteudo(procura);
          }catch(Exception err){
            err.printStackTrace();
          }
          Posts p = new Posts();
          
          jp.setPostsLista(posts);
          jp.setTitulo("Resultado da pesquisa"); 
          Gson gson = new Gson();
          String json = gson.toJson(jp);
          response.getWriter().println(json);

      }else{
        //response.sendRedirect("http://localhost:8080/netflix/timeline");
        response.getWriter().println("<div class='head'><h1>logado </h1></div>");
      }
      
    }else{
      //response.sendRedirect("http://localhost:8080/netflix/us/erro.jsp?msg=Voce%20precisa%20estar%20logado%20para%20acessar%20este%20recurso");
      response.getWriter().println("<div class='head'><h1>nao logado </h1></div>");
    }

    
  }
  

}