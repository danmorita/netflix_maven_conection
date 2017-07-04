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


@WebServlet(urlPatterns = "/searchposts")
public class SearchServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request,
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
  
@Deprecated
  public ArrayList<String> searchPosts(String procura)throws SQLException{
      ArrayList<String> conteudo= new ArrayList<String>();
      ConexaoJDBC conexaojdbc = new ConexaoJDBC();
      Connection conexao = null;
    try{
      conexao = conexaojdbc.ConectaBD();
      String sql = "SELECT id FROM posts WHERE conteudo LIKE ? ";
      PreparedStatement preparedstatement = conexao.prepareStatement(sql);
      preparedstatement.setString(1,"%"+procura+"%");
      ResultSet result = preparedstatement.executeQuery();
        
        while(result.next()){
             conteudo.add(result.getString("id"));
          }
       }catch(Exception err){
        err.printStackTrace();  
       }finally{
        if(conexao!=  null)
          conexao.close();
       }

       return conteudo;
    }
  

@Deprecated
public void originaldoPost(HttpServletRequest request,
                    HttpServletResponse response)throws IOException
  {
    //response.getWriter().println("danilomorita");
    HttpSession session = request.getSession();
    String procura = request.getParameter("pesquisar");

    response.getWriter().println("<div class='head'><h1>Resultados da pesquisa "+procura+"</h1></div>");


    if(session.getAttribute("logado")!= null){
      if(procura != null){
            //pagina protegida por login 
          ArrayList<Posts> posts = new ArrayList<Posts>();
          try{
            ControllerPosts controllerposts = new ControllerPosts();
            posts = controllerposts.findByConteudo(procura);
          }catch(Exception err){
            err.printStackTrace();
          }
          System.out.println("posts "+posts);
          response.getWriter().println("<div class='head'><h1>Resultados da pesquisa </h1></div>");

          response.getWriter().println("<div class='mid'>");
          if(posts.size() == 0){
            response.getWriter().println("<p>Nao foram encontrados resultados para a pesquisa!</p>");
          }else{
            for(Posts c : posts) {      
            response.getWriter().println("<div class='conteudo'>");
              String tag = "<a href='http://localhost:8080/netflix/getpost?id=";
              tag +=c.getId();
              tag += "'>Ver post completo</a>";
              tag +=" <iframe src='";
              tag +="http://localhost:8080/netflix/getpost?id=";
              tag += c.getId();
              tag += "' scrolling='no' >";
              tag += "</iframe>";
              tag += "</div>";
               response.getWriter().println(tag);
          }
          }
          
          response.getWriter().println("</div>");
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