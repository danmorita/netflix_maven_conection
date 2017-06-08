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




@WebServlet(urlPatterns = "/timeline")
public class TimeLineServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)throws IOException
  {
    HttpSession session = request.getSession();

    if(session.getAttribute("logado")!= null){

      //pagina protegida por login 
      ArrayList<String> posts = new ArrayList<String>();
      try{

        posts = LoadPosts();
      }catch(Exception err){
        err.printStackTrace();
      }
      System.out.println("posts "+posts);
      response.getWriter().println("<html><head><link rel='stylesheet'type='text/css' href='../netflix/CSS/timeline.css'/></head><body background='../netflix/Imagens/linho-cinza-textura-de-fundo_1053-253.jpg'>");
      response.getWriter().println("<div class='menu'><ul class='opcao'>");
      response.getWriter().println("<li class='op1'><a href='http://localhost:8080/netflix/timeline' > Home</a></li>");
      response.getWriter().println("<li class='op1'><a href='http://localhost:8080/netflix/us/createpost.jsp' > Novo Post</a></li>");
      response.getWriter().println("<li class='op12'>Logged as "+
      request.getSession().getAttribute("usuario"));
      response.getWriter().println("<li class='op12'>");
      response.getWriter().println("<form class='search' action='http://localhost:8080/netflix/searchposts' method='GET'>");
      response.getWriter().println("<input type='text' name='value' size='8'></input>");
      response.getWriter().println("<input type='image' src='http://localhost:8080/netflix/Imagens/search.png' width='25' alt='submit'></input>");
      response.getWriter().println("</form></li>");
      response.getWriter().println("<li class='op1'><a href='http://localhost:8080/netflix/logout' >Sair</a></li>");
      
     response.getWriter().println("</ul></div></div>");
      response.getWriter().println("<div class='head'><h1>Posts recentes </h1></div>");

      response.getWriter().println("<div class='mid'>");
      for(String c : posts) {      
        response.getWriter().println("<div class='conteudo'>");
          String tag = "<a href='http://localhost:8080/netflix/getpost?id=";
          tag +=c;
          tag += "'>Ver post completo</a>";
          tag +=" <iframe src='";
          tag +="http://localhost:8080/netflix/getpost?id=";
          tag += c;
          tag += "' scrolling='no' >";
          tag += "</iframe>";
          tag += "</div>";
           response.getWriter().println(tag);
      }
      response.getWriter().println("</div></div>");
      response.getWriter().println("<div class='rodape'>Dedicado ao mito X!</div>");
      response.getWriter().println("</body></html>");
    }else{
       response.sendRedirect("http://localhost:8080/netflix/us/erro.jsp?msg=Voce%20precisa%20estar%20logado%20para%20acessar%20este%20recurso");
    }
  }

  public ArrayList<String> LoadPosts()throws SQLException{
      ArrayList<String> conteudo= new ArrayList<String>();
      ConexaoJDBC conexaojdbc = new ConexaoJDBC();
      Connection conexao = null;
    try{
      conexao = conexaojdbc.ConectaBD();
      String sql = "SELECT id FROM posts ORDER BY id DESC";
      PreparedStatement preparedstatement = conexao.prepareStatement(sql);
        ResultSet result = preparedstatement.executeQuery();
        
        while(result.next()){
          //encontrou
          System.out.println("acou 1");
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
  
}