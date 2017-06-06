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
      response.getWriter().println("<div class='head'><h1>Posts recentes </h1>");
      response.getWriter().println("<div class='usuario'>Hello "+
                        request.getSession().getAttribute("usuario")
                        +"</div><div class='menu'><ul class='opcao'>");
      response.getWriter().println("<li class='op1'><a href='http://localhost:8080/netflix/us/createpost.jsp' > Novo Post</a></li>");
      response.getWriter().println("<li class='op1'>em construção</li></ul></div></div>");
      response.getWriter().println("<div class='mid'>");
      for(String c : posts) {      
        response.getWriter().println("<div class='conteudo'>");
          String tag = "<iframe src='";
          tag +="http://localhost:8080/netflix/getpost?id=";
          tag += c;
          tag += "' scrolling='no' >";
          tag+="</iframe></div>";
           response.getWriter().println(tag);
      }
      response.getWriter().println("</div></div>");
      response.getWriter().println("<div class='rodape'>Dedicado ao mito X!</div>");
      response.getWriter().println("</body></html>");
    }else{
       response.sendRedirect("http://localhost:8080/netflix/us/entrar.html");
    }
  }

  public ArrayList<String> LoadPosts()throws SQLException{
      ArrayList<String> conteudo= new ArrayList<String>();
      ConexaoJDBC conexaojdbc = new ConexaoJDBC();
      Connection conexao = null;
    try{
      conexao = conexaojdbc.ConectaBD();
      String sql = "SELECT id FROM posts";
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