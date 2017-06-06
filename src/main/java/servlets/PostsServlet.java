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

@WebServlet(urlPatterns = "/savepost")
public class PostsServlet extends HttpServlet {
 
	 @Override
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)throws IOException
	  {
	      	String conteudo  = request.getParameter("conteudo");
	      	try{
	      		SavePost(StringUtil.toPercentEncode(conteudo));
	      		response.sendRedirect("http://localhost:8080/netflix/timeline");
	      	}catch(Exception err){
				//tratar
				err.printStackTrace();	
			}
	      	
			
	  }

	  public void SavePost(String cont)throws SQLException{
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
