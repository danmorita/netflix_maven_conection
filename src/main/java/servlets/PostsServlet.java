package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import database.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.StringUtil;
import controller.ControllerPosts;
import model.Posts;

@WebServlet(urlPatterns = "/savepost")
public class PostsServlet extends HttpServlet {
 
	 @Override
	  public void doPost(HttpServletRequest request,
	                    HttpServletResponse response)throws IOException
	  {
	      	String conteudo  = request.getParameter("conteudo");
	      	String titulo  = request.getParameter("titulo");
	      	String idFile = request.getParameter("idFile");

	      	System.out.println("conteudo" + conteudo);
	      	System.out.println("titulo" + titulo);
	      	System.out.println("idFile" + idFile + "-+");
	      	Posts post = new Posts();
	      	post.setConteudo(conteudo+idFile.trim());
	      	post.setTitulo(titulo);
	      	// post.setIdFile(0);
	      	

	      	try{
	      		ControllerPosts savepost = new ControllerPosts();
	      		savepost.savePost(post);
	      		response.sendRedirect("http://localhost:8080/netflix/timeline");
	      	}catch(Exception err){
				//tratar
				err.printStackTrace();	
			}
	      	
			
	  }

	  @Deprecated
	  public void savePost(String cont)throws SQLException{
	  	ConexaoJDBC conexaojdbc = new ConexaoJDBC();
			Connection conexao = null;
			try{
				conexao = conexaojdbc.ConectaBD();
				String sql = "INSERT INTO Posts"
				+"(conteudo)"
				+"VALUES(?);";
				System.out.println(cont);
				PreparedStatement preparedstatement = conexao.prepareStatement(sql);
	            preparedstatement.setString(1,cont);
	            preparedstatement.executeUpdate();

	            

			}catch(Exception err){
				//tratar
				err.printStackTrace();	
			}finally{
				if(conexao!=null)
					conexao.close();
			}
	  }
}
